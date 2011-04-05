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

import com.liferay.documentlibrary.client.remote.RemoteFileSystemUtil;
import com.liferay.documentlibrary.client.remote.data.Folder;
import com.liferay.documentlibrary.client.remote.listener.BootstrapFileEntryListener;
import com.liferay.documentlibrary.client.remote.listener.BootstrapFolderListener;
import com.liferay.documentlibrary.client.remote.listener.FolderListener;
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
public class FolderHandler extends BaseHandler {

	public FolderHandler(
		FolderListener folderListener, Folder parentFolder) {

		_folderListener = folderListener;
		_parentFolder = parentFolder;
	}

	public Response onCompleted() throws Exception {
		ResponseBuilder responseBuilder = getResponseBuilder();

		Response response = responseBuilder.build();

		try {
			JSONArray foldersJson = new JSONArray(response.getResponseBody());

			Map<Long, Folder> foldersMap = new HashMap<Long, Folder>();

			for (int i = 0; i < foldersJson.length(); i++) {
				JSONObject folderJson = foldersJson.getJSONObject(i);

				Folder folder = new Folder(folderJson);

				folder.setParentFolder(_parentFolder);

				foldersMap.put(folder.getFolderId(), folder);
			}

			_folderListener.handleFolders(foldersMap);

			for (Folder folderData : foldersMap.values()) {
				RemoteFileSystemUtil.bootstrapFolders(
					folderData, new BootstrapFolderListener());

				RemoteFileSystemUtil.bootstrapFiles(
					folderData,	new BootstrapFileEntryListener());
			}
		}
		catch (Exception e) {
			_log.error(e.getMessage());
		}

		return response;
	}

	private static Log _log = LogFactoryUtil.getLog(
		FolderHandler.class.getName());

	private FolderListener _folderListener;
	private Folder _parentFolder;

}