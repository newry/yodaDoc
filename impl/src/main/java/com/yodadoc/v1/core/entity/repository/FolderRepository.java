package com.yodadoc.v1.core.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.yodadoc.v1.core.entity.folder.Folder;

public interface FolderRepository extends CrudRepository<Folder, Long> {

	@Query("SELECT f FROM Folder f WHERE f.parentId=:parentId and f.status <> 'deleted'")
	public List<Folder> findByParentId(@Param("parentId") Long parentId);
}
