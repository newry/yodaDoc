package com.yodadoc.v1.intf.request.document;

public class Parameters {
	private Boolean onlyFolders;
	private Long folderId;

	/**
	 * @param onlyFolders
	 *            the onlyFolders to set
	 */
	public void setOnlyFolders(Boolean onlyFolders) {
		this.onlyFolders = onlyFolders;
	}

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

	public Boolean getOnlyFolders() {
		return onlyFolders;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Parameters [onlyFolders=").append(onlyFolders).append(", folderId=").append(folderId)
				.append("]");
		return builder.toString();
	}
}
