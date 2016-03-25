package com.foxit.ninemonth.bookstore.parsexml.entry;

/**
 * 
 * @author sevenzero
   *
 * @since 2012-8-16
   *
 */
public abstract class AbstrEntry {
	
	private String title;
	private String id;
	private String updated;
	
	protected AbstrEntry() {}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

}
