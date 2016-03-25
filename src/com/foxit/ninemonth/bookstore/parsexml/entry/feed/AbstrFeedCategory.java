package com.foxit.ninemonth.bookstore.parsexml.entry.feed;

import com.foxit.ninemonth.bookstore.parsexml.entry.AbstrEntry;
import com.foxit.ninemonth.bookstore.parsexml.entry.link.AbstrLink;

/**
 * 
 * @author sevenzero
 *
 * @since 2012-6-9
 * 
 * 9yue Abstract Category
 *
 */
public abstract class AbstrFeedCategory extends AbstrEntry {
	
	private String dcLanguage;
	private String dcIssued;
	
	private AbstrLink detail;
	
	protected AbstrFeedCategory() {}

	public AbstrLink getDetail() {
		return detail;
	}

	public void setDetail(AbstrLink detail) {
		this.detail = detail;
	}

	public String getDcLanguage() {
		return dcLanguage;
	}

	public void setDcLanguage(String dcLanguage) {
		this.dcLanguage = dcLanguage;
	}

	public String getDcIssued() {
		return dcIssued;
	}

	public void setDcIssued(String dcIssued) {
		this.dcIssued = dcIssued;
	}

}
