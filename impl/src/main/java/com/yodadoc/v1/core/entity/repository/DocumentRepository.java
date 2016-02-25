package com.yodadoc.v1.core.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.yodadoc.v1.core.entity.document.Document;

public interface DocumentRepository extends CrudRepository<Document, Long> {

	@Query("SELECT d FROM Document d WHERE d.folder.id=:folderId and d.status <> 'deleted'")
	public List<Document> findByFolderId(@Param("folderId") Long folderId);
}
