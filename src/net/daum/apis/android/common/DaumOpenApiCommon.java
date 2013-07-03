package net.daum.apis.android.common;

// TODO: Auto-generated Javadoc
/**
 * The Class DaumOpenApiCommon.
 */
public class DaumOpenApiCommon {
	
	/**
	 * The Enum OutputType.
	 */
	public enum OutputType {
		
		/** The xml. */
		XML("xml"),
		
		/** The json. */
		JSON("json");
		
		/** The value. */
		private String value;
		
		/**
		 * Instantiates a new output type.
		 *
		 * @param value the value
		 */
		private OutputType(String value) {
			this.value = value;
		}
		
		/**
		 * Gets the value.
		 *
		 * @return the value
		 */
		public String getValue()
		{
			return this.value;
		}
	}
	
	/**
	 * The Enum SortType.
	 */
	public enum SortType {
		
		/** The accu. */
		ACCU("accu"),
		
		/** The date. */
		DATE("date");
		
		/** The value. */
		private String value;
		
		/**
		 * Instantiates a new sort type.
		 *
		 * @param value the value
		 */
		SortType(String value){
			this.value = value;
		}
		
		/**
		 * Gets the value.
		 *
		 * @return the value
		 */
		public String getValue(){
			return this.value;
		}
	}
	
	/** The Constant SEARCH_BOARD_REQUEST_URL. */
	public static final String SEARCH_BOARD_REQUEST_URL = "http://apis.daum.net/search/board";
	
	/** The Constant BLOG_REQUEST_URL. */
	public static final String BLOG_REQUEST_URL = "http://apis.daum.net/search/blog";
	
	/** The apikey. */
	private static String APIKEY = "";
	
	/**
	 * Sets the apikey.
	 *
	 * @param key the new apikey
	 */
	public static void setApikey(String key)
	{
		APIKEY = key;
	}
	
	/**
	 * Gets the apikey.
	 *
	 * @return the apikey
	 */
	public static String getApikey()
	{
		return APIKEY;
	}
}
