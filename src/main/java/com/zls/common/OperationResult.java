package com.zls.common;

import java.io.Serializable;

/**
 * 接口调用的响应结果
 * @author dingxiangliu
 *
 * 时间:		2015年8月29日
 * 工程名:	zwpldx-study
 */
public class OperationResult<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static enum OperationResultCode{
		FAIL(1), SUCCESS(2);
		private OperationResultCode(Integer value){
			this.value = value;
		}
		private Integer value;
		
		public Integer getValue(){
			return this.value;
		}
	}
	
	private OperationResultCode code;
	private T data;
	private String description;
	public OperationResultCode getCode() {
		return code;
	}
	public void setCode(OperationResultCode code) {
		this.code = code;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
