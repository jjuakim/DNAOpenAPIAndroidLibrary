package net.daum.apis.android.search.datamodel;


import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.daum.apis.android.conn.ResponseData;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public class SearchVideoResult  {
	@Key("channel")
	private VideoData videoData;
	
	public VideoData getVideoData()
	{
		return videoData;
	}
	
	public static class VideoData extends GenericJson implements ResponseData{
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
		private List<VideoItem> items;	//개별 검색 결과 정보
		
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
		public List<VideoItem> getItems() {
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
			
			Iterator<VideoItem> iter = items.iterator();
			while(iter.hasNext())
			{
				VideoItem item = iter.next();
				resultString += "\t" + item.toString() + "\n";
			}

			return resultString;
		}
		
	}
	
	public static class VideoItem {
		@Key
		private String title; // string 개별 검색 결과의 제목
		@Key
		private String description; // string 개별 검색 결과의 본문 요약
		@Key
		private String tag;	//string	태그목록
		@Key
		private String thumbnail;	//string	썸네일 URL
		@Key
		private String link; // string 동영상 Play URL
		@Key
		private String cpname;	//string	출처
		@Key
		private String author; 	//spring	작성자
		@Key
		private String player_url;	//string	원본 동영상의 URL (tv팟 출처, 바로보기 허용 영상만 값 존재)
		@Key
		private String pubDate;	//	date	날짜
		@Key
		private int playtime;	//integer	재생시간
		@Key
		private int bitrate;	//integer	동영상의 BitRate
		@Key
		private String thumb_0;	//string	첫번째 썸네일 URL
		@Key("time_0")
		private String _time_0;
		private int time_0;		//integer	첫번째 썸네일의 시간
		@Key
		private String thumb_1;	//string	두번째 썸네일 URL	
		@Key("time_1")
		private String _time_1;		//integer	두번째 썸네일의 시간
		private int time_1;		//integer	두번째 썸네일의 시간
		@Key
		private String thumb_2; //string	세번째 썸네일 URL
		@Key("time_2")
		private String _time_2;
		private int time_2;	//integer	세번째 썸네일의 시간
		@Key
		private String thumb_3;	//string	네번째 썸네일 URL
		@Key("time_3")
		private String _time_3;
		private int time_3;	//integer	네번째 썸네일의 시간
		@Key
		private String thumb_4;	//string	다섯번째 썸네일 URL
		@Key("time_4")
		private String _time_4;
		private int time_4;	//integer	다섯번째 썸네일의 시간
		@Key
		private String thumb_5;	//string	여섯번째 썸네일 URL
		@Key("time_5")
		private String _time_5;
		private int time_5;	//integer	여섯번째 썸네일의 시간
		
		
		@Override 
		public String toString(){
			String resultString = "\t[Item]" + "\n";
			resultString += "\tTitle : " + title + "\n";
			resultString += "\tDescription : " + description + "\n";
			resultString += "\tTag : " + tag + "\n";
			resultString += "\tThumbnail : " + thumbnail + "\n";
			resultString += "\tLink : " + link + "\n";
			resultString += "\tCPname : " + cpname + "\n";
			resultString += "\tAuthor : " + author + "\n";
			resultString += "\tTag : " + tag + "\n";
			resultString += "\tPlayerUrl : " + player_url + "\n";
			resultString += "\tPubDate : " + pubDate + "\n";
			resultString += "\tPlaytime : " + playtime + "\n";
			resultString += "\tBitrate : " + bitrate + "\n";
			resultString += "\tThumb_0 : " + thumb_0 + "\n";
			resultString += "\tTime_0 : " + _time_0 + "\n";
			resultString += "\tThumb_1 : " + thumb_1 + "\n";
			resultString += "\tTime_1 : " + _time_1 + "\n";
			resultString += "\tThumb_2 : " + thumb_2+ "\n";
			resultString += "\tTime_2 : " + time_2 + "\n";
			resultString += "\tThumb_3 : " + thumb_3 + "\n";
			resultString += "\tTime_3 : " + time_3 + "\n";
			resultString += "\tThumb_4 : " + thumb_4 + "\n";
			resultString += "\tTime_4 : " + time_4 + "\n";
			resultString += "\tThumb_5 : " + thumb_5 + "\n";
			resultString += "\tTime_5 : " + time_5 + "\n";

			
			return resultString;
		}

		public String getTitle() {
			return title;
		}

		public String getDescription() {
			return description;
		}

		public String getTag() {
			return tag;
		}

		public String getThumbnail() {
			return thumbnail;
		}

		public String getLink() {
			return link;
		}

		public String getCpname() {
			return cpname;
		}

		public String getAuthor() {
			return author;
		}

		public String getPlayer_url() {
			return player_url;
		}

		public String getPubDate() {
			return pubDate;
		}

		public int getPlaytime() {
			return playtime;
		}

		public int getBitrate() {
			return bitrate;
		}

		public String getThumb_0() {
			return thumb_0;
		}

		public int getTime_0() {
			if(_time_0.equals("")) 
				this.time_0 = 0;
			else 
				this.time_0 = Integer.parseInt(_time_0);
			return time_0;
		}

		public String getThumb_1() {
			return thumb_1;
		}

		public int getTime_1() {
			if(_time_1.equals("")) 
				this.time_1 = 0;
			else 
				this.time_1 = Integer.parseInt(_time_1);
			return time_1;
		}

		public String getThumb_2() {
			return thumb_2;
		}

		public int getTime_2() {
			if(_time_2.equals("")) 
				this.time_2 = 0;
			else 
				this.time_2 = Integer.parseInt(_time_2);
			return time_2;
		}

		public String getThumb_3() {
			return thumb_3;
		}

		public int getTime_3() {
			if(_time_3.equals("")) 
				this.time_3 = 0;
			else 
				this.time_3 = Integer.parseInt(_time_3);
			return time_3;
		}

		public String getThumb_4() {
			return thumb_4;
		}

		public int getTime_4() {
			if(_time_4.equals("")) 
				this.time_4 = 0;
			else 
				this.time_4 = Integer.parseInt(_time_4);
			return time_4;
		}

		public String getThumb_5() {
			return thumb_5;
		}

		public int getTime_5() {
			if(_time_5.equals("")) 
				this.time_5 = 0;
			else 
				this.time_5 = Integer.parseInt(_time_5);
			return time_5;
		}
		
	}
	
}