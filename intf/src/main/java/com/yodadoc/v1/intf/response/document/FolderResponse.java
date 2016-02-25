package com.yodadoc.v1.intf.response.document;

import java.util.List;

public class FolderResponse {
	private List<AbstractFileEntity> result;

	public List<AbstractFileEntity> getResult() {
		return result;
	}

	public void setResult(List<AbstractFileEntity> result) {
		this.result = result;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FolderResponse [result=").append(result).append("]");
		return builder.toString();
	}
}
