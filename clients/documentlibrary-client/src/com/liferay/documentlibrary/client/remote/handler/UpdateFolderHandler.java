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

import com.liferay.documentlibrary.client.data.FolderData;
import com.liferay.documentlibrary.client.remote.data.Folder;
import com.liferay.documentlibrary.client.util.AppPropsValues;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;

import com.ning.http.client.Response.ResponseBuilder;
import com.ning.http.client.Response;

import org.json.JSONObject;

/**
 * @author Gail Hernandez
 */
public class UpdateFolderHandler extends BaseHandler {

	public UpdateFolderHandler(FolderData folderData) {
		_folderData = folderData;
	}

	public Response onCompleted() throws Exception {
		ResponseBuilder responseBuilder = getResponseBuilder();

		Response response = responseBuilder.build();

		try {
			JSONObject folderJson = new JSONObject(response.getResponseBody());

			Folder folder = new Folder(folderJson);

			if (folder.getFolderId() == 0) {
				_log.info("Error adding new folder " +
					response.getResponseBody());
			}
			else {
				_folderData.setId(folder.getFolderId());
				_folderData.setParentFolderId(folder.getParentFolderId());
				_folderData.setRepositoryId(
					GetterUtil.getLong(AppPropsValues.REPOSITORY_ID));

				if (_log.isInfoEnabled()) {
					_log.info(
						"Updated server with new folder " +
						_folderData.getTitle());
				}
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return response;
	}

	private static Log _log = LogFactoryUtil.getLog(
		UpdateFolderHandler.class.getName());

	private FolderData _folderData;

}