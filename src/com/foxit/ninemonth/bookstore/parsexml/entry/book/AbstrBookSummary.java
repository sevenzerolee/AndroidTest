package com.foxit.ninemonth.bookstore.parsexml.entry.book;

import com.foxit.ninemonth.bookstore.parsexml.entry.link.AbstrTitleLink;

/**
 * 
 * @author sevenzero
   *
 * @since 2012-8-17
   *
 */
public abstract class AbstrBookSummary extends AbstrBook {
	
	private AbstrTitleLink detail;
	
	protected AbstrBookSummary() {}

	public AbstrTitleLink getDetail() {
		return detail;
	}

	public void setDetail(AbstrTitleLink detail) {
		this.detail = detail;
	}

}
