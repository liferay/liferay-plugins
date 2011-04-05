/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.documentlibrary.client.remote.handler;

import com.liferay.documentlibrary.client.remote.data.FileEntry;
import com.liferay.documentlibrary.client.remote.data.Folder;
import com.liferay.documentlibrary.client.remote.listener.FileEntryListener;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.ning.http.client.Response.ResponseBuilder;
import com.ning.http.client.Response;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Gail Hernandez
 */
public class FileEntryHandler extends BaseHandler {

	public FileEntryHandler(
		FileEntryListener fileEntryListener, Folder parentFolder) {

		_fileEntryListener = fileEntryListener;
		_parentFolder = parentFolder;

	}

	public Response onCompleted() throws Exception {
		ResponseBuilder responseBuilder = getResponseBuilder();

		Response response = responseBuilder.build();

		try {
			JSONArray fileEntriesJson = new JSONArray(
				response.getResponseBody());

			Map<Long, FileEntry> fileEntriesMap =
				new HashMap<Long, FileEntry>();

			for (int i = 0; i < fileEntriesJson.length(); i++) {
				JSONObject fileEntry = fileEntriesJson.getJSONObject(i);

				FileEntry fileData = new FileEntry(fileEntry);

				fileEntriesMap.put(fileData.getFileEntryId(), fileData);
			}

			_fileEntryListener.handleFiles(_parentFolder, fileEntriesMap);

		}
		catch (Exception e) {
			_log.error(e.getMessage());
		}

		return response;
	}

	private static Log _log = LogFactoryUtil.getLog(
		FileEntryHandler.class.getName());

	private FileEntryListener _fileEntryListener;
	private Folder _parentFolder;

}