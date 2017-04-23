package com.zls.util;



import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.UnsupportedCharsetException;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 此工具类将结合连接池配置在Spring容器中,参考配置如下.
 * 
 * <pre>
 * bean id="poolConnManager"
 * class="org.apache.http.impl.conn.PoolingHttpClientConnectionManager"
 * destroy-method="close">
 * /bean>
 * bean id="httpClientBuilder" class="org.apache.http.impl.client.HttpClientBuilder"
 * factory-method="create">
 * property name="connectionManager" ref="poolConnManager" />
 * /bean>
 * bean id="httpClient" factory-bean="httpClientBuilder"
 * factory-method="build" />
 * 为了保证javadoc显示,所以，对每一个标签都去掉了起始标签的左尖括号,请自行保证标签的完整性
 * </pre>
 * 
 * @author 承项
 * @date 2015年9月8日下午6:15:14
 */
public class HttpClientUtil {
  private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
  private static final String CHARSET = "UTF-8";
  private final static ObjectMapper om = new ObjectMapper();

  /** http客户端 */
  private CloseableHttpClient httpClient;

  static {
    om.setSerializationInclusion(Include.NON_NULL);
  }


  /**
   * 空构造函数
   */
  public HttpClientUtil() {

  }

  /**
   * 发送Get请求,无论协议是http或者https
   * 
   * @param url
   * @param param
   * @param retType
   * @return
   * @throws WeixinException
   */
  public <T> T doGet(String url, Object param, Class<T> retType) {
    logger.debug("doGet [url={},param={}] start.",url, param);
    long start = 0l;
    long end = 0l;
    HttpGet httpGet = null;
    CloseableHttpResponse response = null;
    T respObj = null;
    if (StringUtils.isBlank(url)) {
      return respObj;
    }
    if (retType == null) {
      logger.warn("HTTP GET [url={}],未指定响应结果类型.",url);
      return respObj;
    }
    try {
      httpGet = new HttpGet(url);

      /**
       * 1.根据param构建请求url 2.发送请求 3.处理响应 暂时Get方式不接受参数,需要在调用处自行拼装好完整url
       */
      response = httpClient.execute(httpGet);
      respObj = handleGetResponse(url, response, retType);

      // 释放http连接到连接池
      response.close();
      httpClient.close();

    } catch (IOException e) {
      logger.error("HTTP GET 请求失败.", e);
    } finally {
      try {
        if (httpClient != null) {
          httpClient.close();
        }
        if (response != null) {
          response.close();
        }
      } catch (IOException e) {
        logger.error("HTTP GET [url=" + url + "],关闭连接失败", e);
      }
    }
    logger.debug("doGet [url={}, param={}, respObj={}] end. Cost:{}毫秒 ", url, param, respObj, (end-start));
    return respObj;
  }

  /**
   * Post请求,无论协议是http或者https
   * 
   * @param url
   * @param param
   * @param retType
   * @return
   */
  public <T> T doPost(String url, Object param, Class<T> retType){
    logger.debug("doPost [url={}] start.",url);
    long start = 0l;
    long end = 0l;
    HttpPost httpPost = null;
    T respObj = null;
    CloseableHttpResponse response = null;
    if (StringUtils.isBlank(url)) {
      return respObj;
    }
    if (retType == null) {
      logger.warn("HTTP POST [url={}],未指定响应结果类型.", url);
      return respObj;
    }

    try {
      httpPost = new HttpPost(url);
      /**
       * 1.填充参数. 2.发送. 3.解析返回值
       */

      httpPost.setEntity(buildPostParam(param));
      response = httpClient.execute(httpPost);
      respObj = handlePostResponse(url, response, retType);

      // 释放连接到连接池
      response.close();
//      httpClient.close();
    } catch (Exception e) {
      // post请求失败,打印异常
      logger.error("HTTP POST [url=" + url + "],请求失败.", e);
    } finally {
      try {
        if (response != null) {
          response.close();
        }
      } catch (IOException e) {
        logger.error("HTTP GET [url=" + url + "],关闭连接失败", e);
      }
    }
    logger.debug("doPost [url={}, param={}, respObj={}] end. Cost:{}毫秒.", url, param, respObj, (end-start));
    return respObj;
  }

  /**
   * 处理Get请求响应
   * 
   * @param response
   * @param retType
   * @return
   */
  private <T> T handleGetResponse(String url, CloseableHttpResponse response, Class<T> retType) {
    HttpEntity entity = null;
    String respString = null;
    T respObj = null;

    try {
      entity = response.getEntity();
      if (entity != null) {
        if (retType.equals(File.class)) {
          // TODO 如果是下载文件

        } else {
          respString = EntityUtils.toString(entity, CHARSET);
          if (!StringUtils.isBlank(respString)) {
            // 实体content不为空
            try {
              respObj = om.readValue(respString, retType);
            } catch (Exception e) {
              // content无法解析成目标类型
              logger.warn("HTTP GET [url={},响应entity的content解析成{}类型失败.", url, retType.getName());
            }

          } else {
            logger.warn("HTTP GET [url={}],响应entity的content为空.", url);
          }
        }
      } else {
        logger.warn("HTTP GET [url={}],响应entity为空.", url);
      }
    } catch (ParseException | IOException e) {
      logger.error("解析entity出错", e);
    }

    return respObj;
  }

  /**
   * 处理post响应结果
   * 
   * @param url
   * @param response
   * @param retType
   * @return
   */
  private <T> T handlePostResponse(String url, CloseableHttpResponse response, Class<T> retType) {
    HttpEntity entity = null;
    String respString = null;
    T respObj = null;

    try {
      entity = response.getEntity();
      if (entity != null) {
        respString = EntityUtils.toString(entity, CHARSET);
        if (!StringUtils.isBlank(respString)) {
          try {
            respObj = om.readValue(respString, retType);
          } catch (IOException e) {
            // content无法解析成目标类型
            logger.warn("HTTP POST [url={}],响应entity的content解析成{}类型失败.", url, retType.getName());
          }
        } else {
          logger.warn("HTTP POST [url={}],响应entity的content为空.", url);
        }
      } else {
        logger.warn("HTTP POST [url={}],响应entity为空.", url);
      }
    } catch (ParseException | IOException e) {
      logger.error("解析entity出错", e);
    }

    return respObj;
  }

  /**
   * 构建Post请求参数
   * TODO 上传文件未实现
   * @return
   * @throws Exception
   */
  private HttpEntity buildPostParam(Object param) throws Exception {
    StringEntity entity = null;

    try {
      if (param instanceof String) {
        entity = new StringEntity(param.toString(), CHARSET);
      } else {
        entity = new StringEntity(om.writeValueAsString(param), CHARSET);
      }
    } catch (UnsupportedCharsetException | JsonProcessingException e) {
      logger.error("HTTP POST 填充请求参数失败", e);
      throw e;
    }
    entity.setContentEncoding(CHARSET);
    entity.setContentType("application/json");

    return entity;
  }

  public void setHttpClient(CloseableHttpClient httpClient) {
    this.httpClient = httpClient;
  }

}
