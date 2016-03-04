package com.yodadoc.v1.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yodadoc.v1.core.entity.document.Document;
import com.yodadoc.v1.core.entity.folder.Folder;
import com.yodadoc.v1.core.entity.repository.DocumentRepository;
import com.yodadoc.v1.core.entity.repository.FolderRepository;
import com.yodadoc.v1.intf.request.document.FolderRequest;
import com.yodadoc.v1.intf.request.document.Parameters;
import com.yodadoc.v1.intf.request.document.UpdateParameters;
import com.yodadoc.v1.intf.request.document.UpdateRequest;
import com.yodadoc.v1.intf.response.document.AbstractFileEntity;
import com.yodadoc.v1.intf.response.document.FileEntity;
import com.yodadoc.v1.intf.response.document.FolderEntity;
import com.yodadoc.v1.intf.response.document.FolderResponse;
import com.yodadoc.v1.intf.response.document.OperationResponse;

@RestController
public class FolderController {
	private static final Logger LOG = LoggerFactory.getLogger(FolderController.class);
	@Autowired
	private FolderRepository folderRepository;
	@Autowired
	private DocumentRepository docRepository;

	@RequestMapping(value = "/folder/v1/listFolder", method = RequestMethod.POST)
	@PreAuthorize("hasRole('USER')")
	public FolderResponse list(@RequestBody FolderRequest folderRequest) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		LOG.info("folderRequest={}, user={}", folderRequest, authentication.getName());
		Parameters params = folderRequest.getParams();
		Long folderId = params.getFolderId();
		FolderResponse response = new FolderResponse();
		List<AbstractFileEntity> result = new ArrayList<AbstractFileEntity>();
		response.setResult(result);
		List<Folder> folders = folderRepository.findByParentId(folderId);
		for (Folder folder : folders) {
			FolderEntity file = new FolderEntity();
			file.setId(folder.getId());
			file.setParentFolderId(folder.getParentId());
			file.setName(folder.getName());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
			String date = sdf.format(folder.getDateLastModified().getTime());
			file.setDate(date);
			result.add(file);
		}
		if (params.getOnlyFolders() != null && !params.getOnlyFolders().booleanValue()) {
			List<Document> docs = docRepository.findByFolderId(folderId);
			for (Document doc : docs) {
				FileEntity file = new FileEntity();
				file.setId(doc.getId());
				file.setParentFolderId(doc.getFolder().getId());
				file.setName(doc.getFileName());
				file.setVersion("1.0");
				file.setStatus("checkin");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
				sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
				String date = sdf.format(doc.getDateLastModified().getTime());
				file.setDate(date);
				file.setSize(doc.getFileSize());
				result.add(file);
			}
		}
		return response;
	}

	@RequestMapping(value = "/folder/v1/rename", method = RequestMethod.POST)
	@PreAuthorize("hasRole('USER')")
	public OperationResponse rename(@RequestBody UpdateRequest updateRequest) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		LOG.info("updateRequest={}, user={}", updateRequest, authentication.getName());
		OperationResponse response = new OperationResponse();
		UpdateParameters params = updateRequest.getParams();
		Long id = params.getId();
		Long folderId = params.getFolderId();
		Long newFolderId = params.getNewFolderId();
		String name = params.getName();
		String type = params.getType();
		if ("file".equals(type)) {
			// document
			Document document = docRepository.findOne(id);
			if (document != null) {
				document.setFileName(name);
				if (folderId != newFolderId) {
					document.setFolder(folderRepository.findOne(newFolderId));
				}
				docRepository.save(document);
			} else {
				response.getResult().setSuccess(false);
				response.getResult().setError("document was not found");
			}
		} else {
			// folder
			Folder folder = folderRepository.findOne(id);
			if (folder != null) {
				folder.setName(name);
				if (folderId != newFolderId) {
					if (folder.getParentId() == -1L) {
						response.getResult().setSuccess(false);
						response.getResult().setError("Cannot move root folder");
						return response;
					}
					folder.setParentId(newFolderId);
				}
				folderRepository.save(folder);
			} else {
				response.getResult().setSuccess(false);
				response.getResult().setError("folder was not found");
			}
		}
		return response;
	}
}
