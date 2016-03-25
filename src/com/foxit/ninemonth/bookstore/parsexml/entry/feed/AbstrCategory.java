package com.foxit.ninemonth.bookstore.parsexml.entry.feed;

import com.foxit.ninemonth.bookstore.parsexml.entry.AbstrEntry;
import com.foxit.ninemonth.bookstore.parsexml.entry.link.AbstrLink;

/**
 * 
 * @author sevenzero
   *
 * @since 2012-8-16
   *
 */
public class AbstrCategory extends AbstrEntry {
	
	private AbstrLink self;
	private AbstrLink start;
	private AbstrLink search;
	
	private AbstrFeedCategory shelf;
	
	private AbstrFeedCategory recommended;
	private AbstrFeedCategory facet;
	private AbstrFeedCategory category;
	
	protected AbstrCategory() {}

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

	public AbstrFeedCategory getShelf() {
		return shelf;
	}

	public void setShelf(AbstrFeedCategory shelf) {
		this.shelf = shelf;
	}

	public AbstrFeedCategory getRecommended() {
		return recommended;
	}

	public void setRecommended(AbstrFeedCategory recommended) {
		this.recommended = recommended;
	}

	public AbstrFeedCategory getFacet() {
		return facet;
	}

	public void setFacet(AbstrFeedCategory facet) {
		this.facet = facet;
	}

	public AbstrFeedCategory getCategory() {
		return category;
	}

	public void setCategory(AbstrFeedCategory category) {
		this.category = category;
	}

}
