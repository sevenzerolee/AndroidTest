package com.foxit.ninemonth.bookstore.parsexml.spi;

/**
 * 
 * @author sevenzero
   *
 * @since 2012-6-11
   *
 */
public interface TagAndAttrSpi {
	
	// 根目录级状态标志
	static final int STATE_LEVEL = 0;
	// 二级状态标志
	static final int STATE_LEVEL_2 = 2;
	// 
	static final int STATE_LEVEL_3 = 3;
	//
	static final int STATE_LEVEL_4 = 4;
	
	
	static final String TAG_FEED = "feed";
	
	static final String TAG_ENTRY = "entry";
	
	static final String TAG_TITLE = "title";
	
	static final String TAG_ID = "id";
	
	static final String TAG_UPDATED = "updated";
	
	
	static final String TAG_LINK = "link";
	
	static final String TAG_LINK_REL_ATTR = "rel";
	
	static final String TAG_LINK_HREF_ATTR = "href";
	
	static final String TAG_LINK_TYPE_ATTR = "type";
	
	static final String TAG_LINK_TITLE_ATTR = "title";
	
	static final String TAG_LINK_LENGTH_ATTR = "length";
	
	
	static final String TAG_ENTRY_DC_LANGUAGE = "dc:language";
	
	static final String TAG_ENTRY_DC_ISSUED = "dc:issued";
	
	static final String TAG_ENTRY_DC_IDENTIFIER = "dc:identifier";
	
	static final String TAG_ENTRY_DC_PUBLISHER = "dc:publisher";
	
	
	static final String TAG_ENTRY_CONTENT = "content";
	
	static final String TAG_ENTRY_CONTENT_TYPE_ATTR = "type";
	
	static final String TAG_ENTRY_CATEGORY = "category";
	
	static final String TAG_ENTRY_CATEGORY_TERM_ATTR = "term";
			
	static final String TAG_ENTRY_CATEGORY_LABEL_ATTR = "label";
	
	static final String TAG_AUTHOR = "author";
	
	static final String TAG_AUTHOR_NAME = "name";
	
	static final String TAG_OPENSEARCH_TOTAL_RESULTS = "opensearch:totalResults";
	
	static final String TAG_OPENSEARCH_ITEMS_PERPAGE = "opensearch:itemsPerPage";
	
	static final String TAG_OPENSEARCH_QUERY = "opensearch:Query";
	
	static final String TAG_OPENSEARCH_QUERY_ROLE_ATTR = "role";
	
	static final String TAG_OPENSEARCH_QUERY_KEY_ATTR = "key";
	
	static final String TAG_OPENSEARCH_QUERY_STARTPAGE_ATTR = "startPage";
	
	// 
	// 
	static final String JOYREAD = "joyread";
	
	static final String IS_SUCCESS = "isSuccess";
	
	static final String RESPONSE = "response";
	
	static final String VERSION = "version";
	
	static final String RESPONSE_CODE = "responseCode";
	
	static final String RESULT_MESSAGE = "resultMessage";
	
	static final String ENCRYPT_TYPE = "encryptType";
	
	static final String ENCRYPT_KEY = "encryptKey";
	
	static final String CONTENT = "content";
	
	
	static final String AD_COLUMN = "AdColumn";
	
	static final String AD_COLUMN_TYPE = "adColumnType";
	
	static final String AD_COLUMN_ID = "id";
	
	static final String AD_COLUMN_URL = "adColumnUrl";
	
	static final String LINK_TO = "linkTo";
	
	static final String PICTURE = "picture";
	
	static final String AD_COLUMN_CONTENT = "content";
	
	static final String RECOMMEND_LOCATION = "recommendLocation";
	
}
