package com.foxit.ninemonth.bookstore.parsexml.entry.feed;

import java.util.List;

import com.foxit.ninemonth.bookstore.parsexml.entry.AbstrEntry;
import com.foxit.ninemonth.bookstore.parsexml.entry.link.AbstrLink;

/**
 * 
 * @author sevenzero
   *
 * @since 2012-8-21
   *
 */
public abstract class AbstrBookCategory extends AbstrEntry {
	
	private AbstrLink self;
	private AbstrLink start;
	private AbstrLink search;
	private AbstrLink parent;
	
	private AbstrFeedCategory shelf;
	
	private List<AbstrFeedCategory> list;
	
	protected AbstrBookCategory() {}

	public AbstrFeedCategory getShelf() {
		return shelf;
	}

	public void setShelf(AbstrFeedCategory shelf) {
		this.shelf = shelf;
	}

	public AbstrLink getParent() {
		return parent;
	}

	public void setParent(AbstrLink parent) {
		this.parent = parent;
	}

	public AbstrLink getSelf() {
		return self;
	}

	public void setSelf(AbstrLink self) {
		this.self = self;
	}

	public AbstrLink getStart() {
		return start;
	}

	public void setStart(AbstrLink start) {
		this.start = start;
	}

	public AbstrLink getSearch() {
		return search;
	}

	public void setSearch(AbstrLink search) {
		this.search = search;
	}

	public List<AbstrFeedCategory> getList() {
		return list;
	}

	public void setList(List<AbstrFeedCategory> list) {
		this.list = list;
	}

}
