/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.knowledgebase.admin.importer;

import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.service.ServiceContext;

import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author James Hinkey
 */
public class KBArticleImporterContext {

	public KBArticleImporterContext(
		String fileName, ServiceContext serviceContext) {

		File tempFile = new File(fileName);
		_fileName = tempFile.getName();

		_serviceContext = serviceContext;
	}

	public void addFileEntry(String name, FileEntry fileEntry) {
		_fileEntries.add(fileEntry);
		_fileEntriesMap.put(name, fileEntry);
	}

	public FileEntry getFileEntry(String name) {
		return _fileEntriesMap.get(name);
	}

	public String getFileName() {
		return _fileName;
	}

	public ServiceContext getServiceContext() {
		return _serviceContext;
	}

	public long getUserId() {
		return _serviceContext.getUserId();
	}

	private List<FileEntry> _fileEntries = new ArrayList<FileEntry>();
	private Map<String, FileEntry> _fileEntriesMap =
		new HashMap<String, FileEntry>();
	private String _fileName;
	private ServiceContext _serviceContext;

}