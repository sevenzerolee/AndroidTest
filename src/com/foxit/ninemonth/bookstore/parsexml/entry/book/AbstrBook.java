package com.foxit.ninemonth.bookstore.parsexml.entry.book;

import com.foxit.ninemonth.bookstore.parsexml.entry.AbstrEntry;
import com.foxit.ninemonth.bookstore.parsexml.entry.link.AbstrLengthLink;
import com.foxit.ninemonth.bookstore.parsexml.entry.link.AbstrLink;
import com.foxit.ninemonth.bookstore.parsexml.entry.link.AbstrTitleLink;

/**
 * 
 * @author sevenzero
   *
 * @since 2012-8-16
   *
 */
public abstract class AbstrBook extends AbstrEntry {
	
	private String cateTerm;
	private String cateLabel;
	private String authorName;
	private String dcIdentifier;
	private String dcLanguage;
	private String dcIssued;
	private String dcPublisher;
	
	private AbstrTitleLink comment;
	private AbstrLink image;
	private AbstrLink thumb;
	private AbstrLengthLink pdf;
	private AbstrLengthLink epub;
	
	protected AbstrBook() {}

	public String getCateTerm() {
		return cateTerm;
	}

	public void setCateTerm(String cateTerm) {
		this.cateTerm = cateTerm;
	}

	public String getCateLabel() {
		return cateLabel;
	}

	public void setCateLabel(String cateLabel) {
		this.cateLabel = cateLabel;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getDcIdentifier() {
		return dcIdentifier;
	}

	public void setDcIdentifier(String dcIdentifier) {
		this.dcIdentifier = dcIdentifier;
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

	public String getDcPublisher() {
		return dcPublisher;
	}

	public void setDcPublisher(String dcPublisher) {
		this.dcPublisher = dcPublisher;
	}

	public AbstrTitleLink getComment() {
		return comment;
	}

	public void setComment(AbstrTitleLink comment) {
		this.comment = comment;
	}

	public AbstrLink getImage() {
		return image;
	}

	public void setImage(AbstrLink image) {
		this.image = image;
	}

	public AbstrLink getThumb() {
		return thumb;
	}

	public void setThumb(AbstrLink thumb) {
		this.thumb = thumb;
	}

	public AbstrLengthLink getPdf() {
		return pdf;
	}

	public void setPdf(AbstrLengthLink pdf) {
		this.pdf = pdf;
	}

	public AbstrLengthLink getEpub() {
		return epub;
	}

	public void setEpub(AbstrLengthLink epub) {
		this.epub = epub;
	}

}
