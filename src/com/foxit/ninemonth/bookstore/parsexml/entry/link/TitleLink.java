package com.foxit.ninemonth.bookstore.parsexml.entry.link;

/**
 * 
 * @author sevenzero
   *
 * @since 2012-8-16
   *
 */
public class TitleLink extends AbstrTitleLink {
	
	public static final String LINK_REL           = "alternate";
	public static final String LINK_TYPE          = "application/atom+xml";
	public static final String LINK_TITLE_COMMENT = "评论";
	public static final String LINK_TITLE_DETAIL  = "查看明细";
	
	public static final String LINK_REL_FIRST     = "first";
	public static final String LINK_REL_PREVIOUSE = "previous";
	public static final String LINK_REL_NEXT      = "next";
	public static final String LINK_REL_LAST      = "last";
	
	public TitleLink() {}

}
