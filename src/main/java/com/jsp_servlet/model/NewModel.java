package com.jsp_servlet.model;

public class NewModel extends AbstractModel<NewModel> {
	private String title;
	private String thmbnail;
	private String shortDescription;
	private String content;
	private Long categoryId;
	private String categoryCode;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}



	public String getThmbnail() {
		return thmbnail;
	}

	public void setThmbnail(String thmbnail) {
		this.thmbnail = thmbnail;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	@Override
	public String toString() {
		return "NewModel [title=" + title + ", thmbnail=" + thmbnail + ", shortDescription=" + shortDescription
				+ ", content=" + content + ", categoryId=" + categoryId + ", categoryCode=" + categoryCode + "]";
	}


	
}
