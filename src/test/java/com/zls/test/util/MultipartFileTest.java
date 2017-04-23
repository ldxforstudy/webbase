package com.zls.test.util;

import java.io.File;
import java.io.IOException;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

/**
 * 使用HttpClient测试文件上传.
 * 
 * @author 承项
 * @date 2015年9月18日下午5:19:21
 */
public class MultipartFileTest {

	@Test
	public void testUpload() throws IOException {
		// 接收图片消息推送的url
		String url = "http://127.0.0.1:8080/wechat/pushWxMsg?msgType=img";
		// 待上传的图片,路径请自我调整
		String filePath = "D:\\hzw.jpg";
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;

		httpClient = HttpClients.createDefault();
		try {
			HttpPost httpPost = new HttpPost(url);
			FileBody file = new FileBody(new File(filePath));
			// 这个JSON字符串是模拟GET消息里的图片消息类型,记得把target字段改成你测试的微信号openId,当前是我的
			String msg = "{\"target_type\":\"users\",\"target\":[\"oCjH2srbBv7QoNw2Sm3vp_PpWM4Q\"],\"from\":\"nimei\",\"sourceChannel\":2,\"destChannel\":null,\"msg\":{\"type\":\"image\"}}";
			StringBody msgBody = new StringBody(msg, ContentType.create(
					"text/plain", Consts.UTF_8));
			HttpEntity reqEntity = MultipartEntityBuilder.create()
					.addPart("file", file).addPart("msgBody", msgBody).build();
			httpPost.setEntity(reqEntity);
//			System.out.println("请求行:"+httpPost.getRequestLine());
			
			response = httpClient.execute(httpPost);
			
//			System.out.println("----------------------");
//			System.out.println(response.getStatusLine());
			
			HttpEntity respEntity = response.getEntity();
			if (respEntity != null){
				System.err.println(EntityUtils.toString(respEntity));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			response.close();
			httpClient.close();
		}
	}
}
