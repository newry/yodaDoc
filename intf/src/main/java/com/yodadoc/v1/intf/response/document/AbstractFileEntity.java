package com.yodadoc.v1.intf.response.document;

public abstract class AbstractFileEntity {
	private Long id;
	private Long parentFolderId;
	private String name;
	private String date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentFolderId() {
		return parentFolderId;
	}

	public void setParentFolderId(Long parentFolderId) {
		this.parentFolderId = parentFolderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public abstract String getVersion();

	public abstract String getType();

	public abstract String getStatus();

	public abstract long getSize();

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
