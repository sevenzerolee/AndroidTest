package com.foxit.ninemonth.bookstore.parsexml.entry.bookinfo;

import com.foxit.ninemonth.bookstore.parsexml.entry.AbstrEntry;
import com.foxit.ninemonth.bookstore.parsexml.entry.book.AbstrBookDetail;
import com.foxit.ninemonth.bookstore.parsexml.entry.link.AbstrLink;

/**
 * 
 * @author sevenzero
   *
 * @since 2012-8-20
 * 
 * Book Detail Info Entry
   *
 */
public abstract class AbstrBookInfo extends AbstrEntry {
	
	private AbstrLink self;
	
	private AbstrBookDetail bookDetail;
	
	protected AbstrBookInfo() {}

	public AbstrLink getSelf() {
		return self;
	}

	public void setSelf(AbstrLink self) {
		this.self = self;
	}

	public AbstrBookDetail getBookDetail() {
		return bookDetail;
	}

	public void setBookDetail(AbstrBookDetail bookDetail) {
		this.bookDetail = bookDetail;
	}

}
