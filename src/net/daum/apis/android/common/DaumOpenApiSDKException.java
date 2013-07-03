package net.daum.apis.android.common;

import com.google.api.client.util.Key;

/*****
 * Exception 처리를 위한 클래스
 * @author JuaKim
 *
 */
public class DaumOpenApiSDKException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3571384256448911004L;

	/** 에러코드. */
	@Key
	private String code;
	
	/**
	 * 에러 메시지
	 */
	@Key
	private String message;
	
	/** 
	 * Exception 생성자
	 */
	public DaumOpenApiSDKException()
	{
		super();
	}
	
	/** 
	 * Exception 생성자
	 * @param message 에러메시지
	 */
	public DaumOpenApiSDKException(String message)
	{
		super();
		this.message = message;
	}
	
	/** 
	 * Exception 생성자
	 * @param code 에러코드
	 * @param message 에러메시지
	 */
	public DaumOpenApiSDKException(String code, String message)
	{
		super();
		this.code = code;
		this.message = message;
	}
	
	/**
	 * 오류코드 반환
	 * @return 오류보드
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * 오류코드 설정
	 * @param code 오류코드
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * 오류메시지 반환
	 * @return 오류메시지
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * 오류메시지 설정
	 * @param message 오류메시지
	 */
	public void setMessage(String message) {
		this.message=  message;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		if (code == null) {
			sb.append(message + "\n");
		}
		else if(message == null ){
			sb.append(code + "\n");
		}
		else {
			sb.append("code : " + code + "\n");
			sb.append("message : " + message + "\n");
		}
		
		sb.append(super.toString());
		return sb.toString();
	}
}

