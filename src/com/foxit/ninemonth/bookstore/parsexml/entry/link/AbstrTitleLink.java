package com.foxit.ninemonth.bookstore.parsexml.entry.link;

/**
 * 
 * @author sevenzero
   *
 * @since 2012-8-16
   *
 */
public abstract class AbstrTitleLink extends AbstrLink {
	
	private String linkTitle;
	
	protected AbstrTitleLink() {}

	public String getLinkTitle() {
		return linkTitle;
	}

	public void setLinkTitle(String linkTitle) {
		this.linkTitle = linkTitle;
	}

}
