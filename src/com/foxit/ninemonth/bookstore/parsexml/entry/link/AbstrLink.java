package com.foxit.ninemonth.bookstore.parsexml.entry.link;

/**
 * 
 * @author sevenzero
   *
 * @since 2012-6-11
   *
 */
public abstract class AbstrLink {
	
	private String linkType;
	private String linkRel;
	private String linkHref;
	
	protected AbstrLink() {}

	public String getLinkType() {
		return linkType;
	}

	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}

	public String getLinkRel() {
		return linkRel;
	}

	public void setLinkRel(String linkRel) {
		this.linkRel = linkRel;
	}

	public String getLinkHref() {
		return linkHref;
	}

	public void setLinkHref(String linkHref) {
		this.linkHref = linkHref;
	}

}
