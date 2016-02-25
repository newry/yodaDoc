package com.yodadoc.v1.intf.response.document;

public class FolderEntity extends AbstractFileEntity {
	@Override
	public String getVersion() {
		return "";
	}

	@Override
	public String getType() {
		return "dir";
	}

	@Override
	public String getStatus() {
		return "";
	}

	@Override
	public long getSize() {
		return 0;
	}
}
