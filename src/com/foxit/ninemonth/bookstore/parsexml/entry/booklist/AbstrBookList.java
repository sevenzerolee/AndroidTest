package com.foxit.ninemonth.bookstore.parsexml.entry.booklist;

import java.util.List;

import com.foxit.ninemonth.bookstore.parsexml.entry.AbstrEntry;
import com.foxit.ninemonth.bookstore.parsexml.entry.book.AbstrBookSummary;
import com.foxit.ninemonth.bookstore.parsexml.entry.link.AbstrLink;
import com.foxit.ninemonth.bookstore.parsexml.entry.link.AbstrTitleLink;

/**
 * 
 * @author sevenzero
   *
 * @since 2012-8-17
   *
 */
public abstract class AbstrBookList extends AbstrEntry {
	
	private String totalResults;
	private String itemsPerPage;
	
	private AbstrLink self;
	private AbstrLink search;
	private AbstrLink parent;
	
	private AbstrTitleLink first;
	private AbstrTitleLink previous;
	private AbstrTitleLink next;
	private AbstrTitleLink last;
	
	private List<AbstrBookSummary> list;
	
	protected AbstrBookList() {}

	public String getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(String totalResults) {
		this.totalResults = totalResults;
	}

	public String getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(String itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	public AbstrLink getSelf() {
		return self;
	}

	public void setSelf(AbstrLink self) {
		this.self = self;
	}

	public AbstrLink getSearch() {
		return search;
	}

	public void setSearch(AbstrLink search) {
		this.search = search;
	}

	public AbstrLink getParent() {
		return parent;
	}

	public void setParent(AbstrLink parent) {
		this.parent = parent;
	}

	public AbstrTitleLink getFirst() {
		return first;
	}

	public void setFirst(AbstrTitleLink first) {
		this.first = first;
	}

	public AbstrTitleLink getPrevious() {
		return previous;
	}

	public void setPrevious(AbstrTitleLink previous) {
		this.previous = previous;
	}

	public AbstrTitleLink getNext() {
		return next;
	}

	public void setNext(AbstrTitleLink next) {
		this.next = next;
	}

	public AbstrTitleLink getLast() {
		return last;
	}

	public void setLast(AbstrTitleLink last) {
		this.last = last;
	}

	public List<AbstrBookSummary> getList() {
		return list;
	}

	public void setList(List<AbstrBookSummary> list) {
		this.list = list;
	}

}
