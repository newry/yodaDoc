package com.yodadoc.v1.intf.response.document;

public class FileEntity extends AbstractFileEntity {
	private String version;
	private String status;
	private long size;

	@Override
	public String getVersion() {
		return version;
	}

	@Override
	public String getType() {
		return "file";
	}

	@Override
	public String getStatus() {
		return status;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}
}
