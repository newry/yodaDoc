package com.yodadoc.v1.core.entity.document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.yodadoc.v1.core.entity.PersistentObject;
import com.yodadoc.v1.core.entity.folder.Folder;

@Entity
@Table(name = "DOCUMENT")
@DynamicUpdate
public class Document extends PersistentObject {
	private static final long serialVersionUID = 71215755549548442L;

	@Id
	@GeneratedValue(generator = "identity", strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "identity", strategy = "identity")
	@Column(name = "ID", unique = true, nullable = false, precision = 22)
	private Long id;

	@Column(name = "TITLE", nullable = false, length = 255)
	private String title;

	@Column(name = "COMMENT", nullable = false, length = 255)
	private String comment;

	@Column(name = "FILE_NAME", nullable = false, length = 255)
	private String fileName;

	@Column(name = "FILE_SIZE", nullable = false, precision = 22)
	private long fileSize;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS", nullable = false, length = 50)
	private DocumentStatus status = DocumentStatus.active;

	@ManyToOne(targetEntity = Folder.class, optional = false)
	@JoinColumn(name = "FOLDER_ID", nullable = false)
	private Folder folder;

	public Long getId() {
		return id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the fileSize
	 */
	public long getFileSize() {
		return fileSize;
	}

	/**
	 * @param fileSize
	 *            the fileSize to set
	 */
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * @return the status
	 */
	public DocumentStatus getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(DocumentStatus status) {
		this.status = status;
	}

	/**
	 * @return the folder
	 */
	public Folder getFolder() {
		return folder;
	}

	/**
	 * @param folder
	 *            the folder to set
	 */
	public void setFolder(Folder folder) {
		this.folder = folder;
	}
}
