package com.yodadoc.v1.core.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.TimeZone;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class PersistentObject implements Serializable {

	private static final long serialVersionUID = 2164829167665250690L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_ADDED", nullable = false)
	private Calendar dateAdded = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_LAST_MODIFIED", nullable = false)
	private Calendar dateLastModified = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

	@Column(name = "CREATOR", nullable = false, length = 255)
	private String creator;

	public Calendar getDateAdded() {
		return dateAdded;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Calendar getDateLastModified() {
		return dateLastModified;
	}

}