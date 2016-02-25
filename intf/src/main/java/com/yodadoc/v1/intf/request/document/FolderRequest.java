package com.yodadoc.v1.intf.request.document;

public class FolderRequest {
	private Parameters params;

	/**
	 * @return the params
	 */
	public Parameters getParams() {
		return params;
	}

	/**
	 * @param params
	 *            the params to set
	 */
	public void setParams(Parameters params) {
		this.params = params;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FolderRequest [params=").append(params).append("]");
		return builder.toString();
	}
}
