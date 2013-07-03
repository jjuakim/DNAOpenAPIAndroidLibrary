package net.daum.apis.android.search;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import net.daum.apis.android.common.DaumOpenApiCommon;
import net.daum.apis.android.common.DaumOpenApiSDKException;
import net.daum.apis.android.common.DaumOpenApiCommon.OutputType;
import net.daum.apis.android.common.DaumOpenApiCommon.SortType;
import net.daum.apis.android.conn.RequestListener;
import net.daum.apis.android.conn.RequestUrl;
import net.daum.apis.android.search.datamodel.BoardResult;
import net.daum.apis.android.search.datamodel.BoardResult.BoardData;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.LoggingOutputStream;

public class SearchBoard extends AsyncTask<RequestUrl, Integer, Object> implements Search{
	//http Request 관련 
	private SearchBoardUrl requestUrl = new SearchBoardUrl(DaumOpenApiCommon.SEARCH_BOARD_REQUEST_URL); 
	private static final int GINGERBREAD = 9;
	public static final JsonFactory JSON_FACTORY = new JacksonFactory();
	public static final HttpTransport HTTP_TRANSPORT = newCompatibleTransport();
	
	//동기화처리 관련
	private RequestListener listener; 
	
	//에러핸들링
	/** The exception. */
	private DaumOpenApiSDKException exception;
	private static final String TAG = "SearchBoard";

	
	/**
	 * Instantiates a new search board.
	 *
	 * @param q the q
	 */
	public SearchBoard(String q) {	
		super();
		requestUrl.setQuery(q);
		requestUrl.setResult(10);
		requestUrl.setPageno(1);
		requestUrl.setSort(SortType.ACCU.getValue());
		requestUrl.setOutput(OutputType.JSON.getValue());
	}
	
	public SearchBoard(String q, int result, int pageno, SortType sort, OutputType output) {
		super();
		requestUrl.setQuery(q);
		requestUrl.setResult(result);
		requestUrl.setPageno(pageno);
		requestUrl.setSort(sort.getValue());
		requestUrl.setOutput(output.getValue());
	}
	
	/**
	 * 안드로이드 버전에 따라 http관련 클래스를 다르게 생성한다.
	 * @return HttpTransport Http전송클래스
	 */
	private static HttpTransport newCompatibleTransport() {
		return isGingerbreadOrHigher() ? new NetHttpTransport() : new ApacheHttpTransport();
	}
	
	/**
	 * 안드로이드 버전 검사
	 * @return 현재 안드로이드머신 버전이 진저브래드(API 9 level) 이상인지 여부
	 */
	private static boolean isGingerbreadOrHigher() {
		return Build.VERSION.SDK_INT >= GINGERBREAD;
	} 
	
	public HttpRequest createRequest(RequestUrl url) throws IOException {
		HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
			@Override
			public void initialize(HttpRequest request) throws IOException {
				request.setParser(new JsonObjectParser(JSON_FACTORY));
			}
		});
		HttpRequest request = requestFactory.buildGetRequest((GenericUrl) url);
		return request;
	}
	
	
	@Override
	public Object run() throws InterruptedException, ExecutionException {
		Object result;
		if(Build.VERSION.SDK_INT >= 11)
			result = this.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, requestUrl).get();
		else 
			result = this.execute(requestUrl).get();
		return result;
	}
	
	
	@Override
	public void runAsync(final RequestListener listener) throws InterruptedException, ExecutionException, DaumOpenApiSDKException{
		this.listener = listener;
		this.execute(requestUrl);
	}
	
	@SuppressWarnings("unused")
	@Override
	protected Object doInBackground(RequestUrl... urls) {
		try {
			for (int i = 0; i < urls.length; i++) {
				HttpRequest request = createRequest(urls[i]);
				HttpResponse response = request.execute();
				
				if(response.getStatusCode() != 200) {
					//성공아니면 에러 던저주고
					this.exception = new DaumOpenApiSDKException(Integer.toString(response.getStatusCode()), response.getStatusMessage());
					throw exception;
				}
				else {
					BoardResult channel = response.parseAs(BoardResult.class);
					if(channel.getBoardData() == null) {
						//다시연결해서 에러메시지 받아옴
						DaumOpenApiSDKException errorResponse = request.execute().parseAs(DaumOpenApiSDKException.class);
						this.exception = errorResponse;
						throw exception;
					}
					else {
						//데이터 정상 수신
						return channel.getBoardData();
					}
				}
			}
		} catch (IOException e) {
			Log.e(TAG, e.toString());
			e.printStackTrace();
		} catch (DaumOpenApiSDKException e) {
			if(listener != null)
				cancel(true);
			else 
				return exception;
		}
		return null;
	}
	
	/**
	 * doInBackground작업이 성공적으로 종료된 후 호출되는 UI 쓰레드 
	 */
	@Override 
	protected void onPostExecute (Object result)
	{
		if(exception == null && listener != null) {
				listener.onComplete(result);
		}
	}	
	@Override 
	protected void onCancelled(){
		if (exception != null) {	
			listener.onDaumOpnApiSDKException(exception);
		}
	}
	
	
}
