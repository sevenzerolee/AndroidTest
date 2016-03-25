package com.foxit.ninemonth.bookstore.parsexml.entry.ads;

/**
 * 
 * @author sevenzero
   *
 * @since 2012-8-20
   *
 */
public abstract class AbstrAdsColumn {
	
	private String adColumnType;
	private String id;
	private String adColumnUrl;
	private String linkTo;
	private String content;
	private String recommendLocation;
	
	protected AbstrAdsColumn() {}

	public String getAdColumnType() {
		return adColumnType;
	}

	public void setAdColumnType(String adColumnType) {
		this.adColumnType = adColumnType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAdColumnUrl() {
		return adColumnUrl;
	}

	public void setAdColumnUrl(String adColumnUrl) {
		this.adColumnUrl = adColumnUrl;
	}

	public String getLinkTo() {
		return linkTo;
	}

	public void setLinkTo(String linkTo) {
		this.linkTo = linkTo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRecommendLocation() {
		return recommendLocation;
	}

	public void setRecommendLocation(String recommendLocation) {
		this.recommendLocation = recommendLocation;
	}

}
