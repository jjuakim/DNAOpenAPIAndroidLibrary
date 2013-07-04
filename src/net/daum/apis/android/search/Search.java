package net.daum.apis.android.search;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import android.os.Build;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import net.daum.apis.android.common.DaumOpenApiSDKException;
import net.daum.apis.android.conn.RequestListener;
import net.daum.apis.android.conn.RequestUrl;
import net.daum.apis.android.conn.ResponseData;


public interface Search {
	
	public static final int BOARD = 0;
	public static final int VIDEO = 1;
	public static final int BLOG = 2;
	public static final int WEB = 3;
	public static final int IMAGE = 4;
	public static final int KNOWLEDGE = 5;
	public static final int BOOK = 6;
	public static final int CAFE = 7;
	
	public static final int GINGERBREAD = 9;
	public static final JsonFactory JSON_FACTORY = new JacksonFactory();
	
	/**
	 * 안드로이드 버전에 따라 http관련 클래스를 다르게 생성한다.
	 * @return HttpTransport Http전송클래스
	 */
	public abstract HttpTransport newCompatibleTransport();
	
	/**
	 * 안드로이드 버전 검사
	 * @return 현재 안드로이드머신 버전이 진저브래드(API 9 level) 이상인지 여부
	 */
	 boolean isGingerbreadOrHigher(); 
	
	 /**
	  * Http Request를 생성한다. 
	  * @param url 리퀘스트를 요청할 url
	  * @return httpRequest 객체
	  * @throws IOException 
	  */
	 public abstract HttpRequest createRequest(RequestUrl url) throws IOException;
	 
	/***
	 * http Request를 동기방식으로 수행한다.
	 * @return  request 결과값을 해당 API의 데이터모델에 담아 반환한다.
	 */
	 public abstract ResponseData run() throws InterruptedException, ExecutionException ;
	 
 	/**
 	 * *
 	 * Http requset를 비동기 방식으로 수행한다.
 	 *
 	 * @param listener the listener
 	 * @throws DaumOpenApiSDKException the daum open api sdk exception
 	 * @throws IOException Signals that an I/O exception has occurred.
 	 * @throws InterruptedException the interrupted exception
 	 * @throws ExecutionException the execution exception
 	 */
	 public abstract void runAsync(RequestListener listener) throws DaumOpenApiSDKException, IOException, InterruptedException, ExecutionException;
	
	
}



