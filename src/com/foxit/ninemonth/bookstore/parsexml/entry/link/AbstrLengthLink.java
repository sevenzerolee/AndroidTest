package com.foxit.ninemonth.bookstore.parsexml.entry.link;

/**
 * 
 * @author sevenzero
   *
 * @since 2012-8-17
   *
 */
public abstract class AbstrLengthLink extends AbstrTitleLink {
	
	private String linkLength;
	
	protected AbstrLengthLink() {}

	public String getLinkLength() {
		return linkLength;
	}

	public void setLinkLength(String linkLength) {
		this.linkLength = linkLength;
	}
	
}
