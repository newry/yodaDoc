package com.yodadoc.v1.intf.request.document;

public class UpdateParameters {
	private Long id;
	private Long folderId;
	private Long newFolderId;
	private String name;
	private String type;

	/**
	 * @return the folderId
	 */
	public Long getFolderId() {
		return folderId;
	}

	/**
	 * @param folderId
	 *            the folderId to set
	 */
	public void setFolderId(Long folderId) {
		this.folderId = folderId;
	}

	public Long getNewFolderId() {
		return newFolderId;
	}

	public void setNewFolderId(Long newFolderId) {
		this.newFolderId = newFolderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UpdateParameters [id=").append(id).append(", folderId=").append(folderId)
				.append(", newFolderId=").append(newFolderId).append(", name=").append(name).append(", type=")
				.append(type).append("]");
		return builder.toString();
	}

}
