package com.foxit.ninemonth.bookstore.parsexml.entry.link;

/**
 * 
 * @author sevenzero
   *
 * @since 2012-8-14
   *
 */
public class BaseLink extends AbstrLink {
	
	public static final String LINK_REL_SELF   = "self";
	public static final String LINK_REL_START  = "start";
	public static final String LINK_REL_SEARCH = "search";
	public static final String LINK_REL_PARENT = "parent";
	
	public static final String LINK_REL_IMAGE = "http://opds-spec.org/image";
	public static final String LINK_REL_THUMB = "http://opds-spec.org/image/thumbnail";
	
	public BaseLink() {}

}
