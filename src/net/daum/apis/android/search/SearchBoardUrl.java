package net.daum.apis.android.search;

import net.daum.apis.android.common.DaumOpenApiCommon;
import net.daum.apis.android.common.DaumOpenApiCommon.OutputType;
import net.daum.apis.android.common.DaumOpenApiCommon.SortType;
import net.daum.apis.android.conn.RequestUrl;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;

/** URL for 게시판 검색 API **/
public class SearchBoardUrl extends GenericUrl implements RequestUrl{	
	/**
	 * API키 
	 */
	@Key("apikey")
	private String apikey = DaumOpenApiCommon.getApikey();
	/**
	 * 검색을 원하는 질의어
	 */
	
	@Key("q")
	private String query;

	/**
	 * 한 페이지에 출력될 결과수 기본값 10, 최소 1, 최대 20
	 */
	@Key("result")
	private int result;

	/**
	 * 검색 결과 페이지 번호
	 *  기본값 1, 최소 1, 최대 500
	 */
	@Key("pageno")
	private int pageno;
	/**
	 * 검색 결과의 정렬순서
	 *  accu : 정확도순, date : 최신글순
	 */
	@Key("sort")
	private String sort;
	
	/**
	 * 결과형식
	 * json(기본값), xml	
	 */
	@Key
	private String output;
	
	public SearchBoardUrl(String encodedUrl) {
		super(encodedUrl);
	}
	
	public SearchBoardUrl(String encodedUrl, String q) {
		super(encodedUrl);
		this.query = q;
		this.result = 10;
		this.pageno = 1;
		this.sort = SortType.ACCU.getValue();
		this.output = OutputType.JSON.getValue();
	}
	
	public SearchBoardUrl(String encodedUrl, String q, int result, int pageno, SortType sort, OutputType output) {
		super(encodedUrl);
		this.query = q;
		this.result = result;
		this.pageno = pageno;
		this.sort = sort.getValue();
		this.output = output.getValue();
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public void setPageno(int pageno) {
		this.pageno = pageno;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public void setOutput(String output) {
		this.output = output;
	}

}