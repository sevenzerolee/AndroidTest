package com.foxit.ninemonth.bookstore.parsexml.entry.booklist;

/**
 * 
 * @author sevenzero
   *
 * @since 2012-8-20
   *
 */
public abstract class AbstrSearchBookList extends AbstrBookList {
	
	private String queryRole;
	private String queryKey;
	private String queryStartPage;

	protected AbstrSearchBookList() {}

	public String getQueryRole() {
		return queryRole;
	}

	public void setQueryRole(String queryRole) {
		this.queryRole = queryRole;
	}

	public String getQueryKey() {
		return queryKey;
	}

	public void setQueryKey(String queryKey) {
		this.queryKey = queryKey;
	}

	public String getQueryStartPage() {
		return queryStartPage;
	}

	public void setQueryStartPage(String queryStartPage) {
		this.queryStartPage = queryStartPage;
	}
	
}
