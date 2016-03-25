package com.foxit.ninemonth.bookstore.parsexml.entry.book;

/**
 * 
 * @author sevenzero
   *
 * @since 2012-8-20
   *
 */
public abstract class AbstrBookDetail extends AbstrBook {
	
	private String content;
	private String contentType;
	
	protected AbstrBookDetail() {}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
