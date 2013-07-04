package net.daum.apis.android.search.datamodel;


import java.util.Iterator;
import java.util.List;

import net.daum.apis.android.conn.ResponseData;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public class SearchBoardResult  {
	@Key("channel")
	private BoardData boardData;
	
	public BoardData getBoardData()
	{
		return boardData;
	}
	
	public static class BoardData extends GenericJson implements ResponseData{
		@Key
		private String title;
		@Key
		private String link;
		@Key
		private String description;
		@Key
		private String lastBuildDate;
		@Key
		private int totalCount;	//전체 검색 결과의 수
		@Key
		private int pageCount;	//  전체 검색 결과의 페이지수
		@Key
		private int result;	//	integer	한 페이지에 출력될 결과수
		@Key("item")
		private List<BoardItem> items;	//개별 검색 결과 정보
		
		public String getTitle() {
			return title;
		}
		
		public String getLink() {
			return link;
		}

		public String getDescription() {
			return description;
		}
		
		public String getLastBuildDate() {
			return lastBuildDate;
		}
		public int getTotalCount() {
			return totalCount;
		}
		public int getPageCount() {
			return pageCount;
		}
		public int getResult() {
			return result;
		}
		public List<BoardItem> getItems() {
			return items;
		}
		
		@Override 
		public String toString(){
			String resultString = "";
			resultString += "Title : " + title + "\n";
			resultString += "Link : " + link + "\n";
			resultString += "Description : " + description + "\n";
			resultString += "LastBuildDate : " + lastBuildDate + "\n";
			resultString += "TotalCount : " + totalCount + "\n";
			resultString += "PageCount : " + pageCount + "\n";
			resultString += "Result : " + result + "\n";
			resultString += "Items : \n";
			
			Iterator<BoardItem> iter = items.iterator();
			while(iter.hasNext())
			{
				BoardItem item = iter.next();
				resultString += "\t" + item.toString() + "\n";
			}

			return resultString;
		}
		
	}
	
	public static class BoardItem {
		@Key
		private String title; // string 개별 검색 결과의 제목
		@Key
		private String description; // string 개별 검색 결과의 본문 요약
		@Key
		private String link; // string 개별 검색 결과의 link url
		@Key
		private String comment; // string 관련 링크
		@Key
		private String author; // string 출처
		@Key
		private String pubDate; // 등록일

		public String getTitle() {
			return title;
		}


		public String getDescription() {
			return description;
		}

		public String getLink() {
			return link;
		}


		public String getComment() {
			return comment;
		}


		public String getAuthor() {
			return author;
		}


		public String getPubDate() {
			return pubDate;
		}
		
		@Override 
		public String toString(){
			String resultString = "\t[Item]" + "\n";
			resultString += "\tTitle : " + title + "\n";
			resultString += "\tDescription : " + description + "\n";
			resultString += "\tLink : " + link + "\n";
			resultString += "\tComment : " + comment + "\n";
			resultString += "\tAuthor : " + author + "\n";
			resultString += "\tPubDate : " + pubDate + "\n";
			
			return resultString;
		}
		
	}
	
}