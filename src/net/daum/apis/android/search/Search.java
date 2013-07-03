package net.daum.apis.android.search;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import com.google.api.client.http.HttpRequest;

import net.daum.apis.android.common.DaumOpenApiSDKException;
import net.daum.apis.android.conn.RequestListener;
import net.daum.apis.android.conn.RequestUrl;
import net.daum.apis.android.conn.ResponseData;


public interface Search {
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



