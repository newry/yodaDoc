package com.yodadoc.v1.intf.request.document;

public class UpdateRequest {
	private UpdateParameters params;

	/**
	 * @return the params
	 */
	public UpdateParameters getParams() {
		return params;
	}

	/**
	 * @param params
	 *            the params to set
	 */
	public void setParams(UpdateParameters params) {
		this.params = params;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FolderRequest [params=").append(params).append("]");
		return builder.toString();
	}
}
