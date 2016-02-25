package com.yodadoc.v1.core.entity.folder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.yodadoc.v1.core.entity.PersistentObject;

@Entity
@Table(name = "FOLDER")
@DynamicUpdate
public class Folder extends PersistentObject {
	private static final long serialVersionUID = -5199801833397382721L;

	@Id
	@GeneratedValue(generator = "identity", strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "identity", strategy = "identity")
	@Column(name = "ID", unique = true, nullable = false, precision = 22)
	private Long id;

	@Column(name = "NAME", nullable = false, length = 255)
	private String name;
	@Column(name = "DESCRIPTION", nullable = false, length = 255)
	private String description;
	@Column(name = "PARENT_ID", nullable = false)
	private Long parentId;
	@Column(name = "POSITION", nullable = false)
	private int position = 1;
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS", nullable = false, length = 50)
	private FolderStatus status = FolderStatus.active;

	public Long getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the parentId
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * @return the status
	 */
	public FolderStatus getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(FolderStatus status) {
		this.status = status;
	}

}
