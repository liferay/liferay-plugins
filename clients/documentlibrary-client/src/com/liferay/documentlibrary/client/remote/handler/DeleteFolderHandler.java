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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;

import com.ning.http.client.Response.ResponseBuilder;
import com.ning.http.client.Response;

/**
 * @author Gail Hernandez
 */
public class DeleteFolderHandler extends BaseHandler {

	public DeleteFolderHandler(FolderData folderData) {
		_folderData = folderData;
	}

	public Response onCompleted() throws Exception {
		ResponseBuilder responseBuilder = getResponseBuilder();

		Response response = responseBuilder.build();

		try {
			String responseBody = response.getResponseBody();

			if (responseBody.length() > 2) {
				if (_log.isInfoEnabled()) {
					_log.info("Error updating folder " +
						_folderData.getTitle() + StringPool.SPACE +
						response.getResponseBody());
				}
			}
			else {
				if (_log.isInfoEnabled()) {
					_log.info("Updated server with deleted folder " +
						_folderData.getTitle());
				}
			}
		}
		catch (Exception e) {
			_log.error(e.getMessage());
		}

		return response;
	}

	private static Log _log = LogFactoryUtil.getLog(
		DeleteFolderHandler.class.getName());

	private FolderData _folderData;

}