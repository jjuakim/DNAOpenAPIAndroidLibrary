package net.daum.apis.android.search;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import net.daum.apis.android.common.DaumOpenApiCommon.OutputType;
import net.daum.apis.android.common.DaumOpenApiCommon.SearchVideoSortType;
import net.daum.apis.android.common.DaumOpenApiSDKException;
import net.daum.apis.android.conn.RequestListener;
import net.daum.apis.android.conn.RequestUrl;
import net.daum.apis.android.conn.ResponseData;
import net.daum.apis.android.search.datamodel.SearchVideoResult;
import net.daum.apis.android.search.datamodel.SearchVideoResult.VideoData;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonObjectParser;

public class SearchVideo extends AsyncTask<RequestUrl, Integer, Object> implements Search{
	//http Request 관련 
	private SearchVideoUrl requestUrl = new SearchVideoUrl(); 
	public final HttpTransport HTTP_TRANSPORT = newCompatibleTransport();
	
	@Override
	public HttpTransport newCompatibleTransport() {
		return isGingerbreadOrHigher() ? new NetHttpTransport() : new ApacheHttpTransport();
	}

	@Override
	public boolean isGingerbreadOrHigher() {
		return Build.VERSION.SDK_INT >= GINGERBREAD;
	}
	
	//동기화처리 관련
	private RequestListener listener; 
	
	//에러핸들링
	/** The exception. */
	private DaumOpenApiSDKException exception;
	private static final String TAG = "SearchVideo";
	
	public SearchVideo(String q) {	
		super();
		requestUrl.setQuery(q);
		requestUrl.setResult(10);
		requestUrl.setPageno(1);
		requestUrl.setSort(SearchVideoSortType.ACCURACY.getValue());
		requestUrl.setOutput(OutputType.JSON.getValue());
	}
	
	public SearchVideo(String q, int result, int pageno, SearchVideoSortType sort, OutputType output) {
		super();
		requestUrl.setQuery(q);
		requestUrl.setResult(result);
		requestUrl.setPageno(pageno);
		requestUrl.setSort(sort.getValue());
		requestUrl.setOutput(output.getValue());
	}
	
	
	
	@Override
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
	public ResponseData run() throws InterruptedException, ExecutionException {
		ResponseData result;
		if(Build.VERSION.SDK_INT >= 11)
			result = (VideoData)this.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, requestUrl).get();
		else 
			result = (VideoData)this.execute(requestUrl).get();
		return result;
	}

	@Override
	public void runAsync(RequestListener listener)
			throws DaumOpenApiSDKException, IOException, InterruptedException,
			ExecutionException {
		this.listener = listener;
		if(Build.VERSION.SDK_INT >= 11)
			this.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, requestUrl);
		else
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
					SearchVideoResult channel = response.parseAs(SearchVideoResult.class);
					if(channel.getVideoData() == null) {
						//다시연결해서 에러메시지 받아옴
						DaumOpenApiSDKException errorResponse = request.execute().parseAs(DaumOpenApiSDKException.class);
						this.exception = errorResponse;
						throw exception;
					}
					else {
						//데이터 정상 수신
						return channel.getVideoData();
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
				listener.onComplete((ResponseData)result);
		}
	}	
	@Override 
	protected void onCancelled(){
		if (exception != null) {	
			listener.onDaumOpnApiSDKException(exception);
		}
	}
	

}
