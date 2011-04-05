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

package com.liferay.documentlibrary.client.remote;

import com.liferay.documentlibrary.client.data.FileData;
import com.liferay.documentlibrary.client.data.FolderData;
import com.liferay.documentlibrary.client.remote.data.Folder;
import com.liferay.documentlibrary.client.remote.handler.DeleteFolderHandler;
import com.liferay.documentlibrary.client.remote.handler.FileContentHandler;
import com.liferay.documentlibrary.client.remote.handler.FileEntryHandler;
import com.liferay.documentlibrary.client.remote.handler.FolderHandler;
import com.liferay.documentlibrary.client.remote.handler.UpdateFolderHandler;
import com.liferay.documentlibrary.client.remote.listener.FileEntryListener;
import com.liferay.documentlibrary.client.remote.listener.FolderListener;
import com.liferay.documentlibrary.client.remote.listener.RepositoryListener;
import com.liferay.documentlibrary.client.util.AppPropsValues;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import com.ning.http.client.AsyncHandler;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Realm.RealmBuilder;
import com.ning.http.client.Realm;
import com.ning.http.client.Request;
import com.ning.http.client.RequestBuilder;
import com.ning.http.client.Response;
import com.ning.http.client.resumable.ResumableAsyncHandler;

import java.io.UnsupportedEncodingException;

import java.net.URLEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gail Hernandez
 */
public class RemoteFileSystemImpl implements RemoteFileSystem {

	public RemoteFileSystemImpl() {
	}

	public void addFolder(FolderData folderData) {
		String folderName = _urlEncoded(folderData.getTitle());

		String url = StringUtil.replace(
			_ADD_FOLDER_TEMPLATE,
			new String[] {"$SERVER_URL", "$REPOSITORY_ID", "$PARENT_FOLDER_ID",
				"$FOLDER_NAME"},
			new String[] {
				AppPropsValues.SERVER_URL, AppPropsValues.REPOSITORY_ID,
				Long.toString(folderData.getParentFolderId()),
				folderName});

		AsyncHandler<Response> asyncHandler = new UpdateFolderHandler(
			folderData);

		_executeRequest(url, "POST", asyncHandler);
	}

	public void bootstrapFiles(
		Folder parentFolder, FileEntryListener fileEntryListener) {

		long folderId = parentFolder.getFolderId();

		String folderIdString = Long.toString(folderId);

		String url = StringUtil.replace(
			_GET_FILE_ENTRIES_TEMPLATE,
			new String[] {"$SERVER_URL", "$REPOSITORY_ID", "$FOLDER_ID"},
			new String[] {
				AppPropsValues.SERVER_URL, AppPropsValues.REPOSITORY_ID,
				folderIdString});

		AsyncHandler<Response> asyncHandler = new FileEntryHandler(
				fileEntryListener, parentFolder);

		_executeRequest(url, "GET", asyncHandler);
	}

	public void bootstrapFolders(
		Folder parentFolder, FolderListener folderListener) {

		long folderId = parentFolder.getFolderId();

		String folderIdString = Long.toString(folderId);

		String url = StringUtil.replace(
			_GET_FOLDERS_TEMPLATE,
			new String[] {"$SERVER_URL", "$REPOSITORY_ID", "$PARENT_FOLDER_ID"},
			new String[] {
				AppPropsValues.SERVER_URL, AppPropsValues.REPOSITORY_ID,
				folderIdString});

		AsyncHandler<Response> asyncHandler = new FolderHandler(
			folderListener, parentFolder);

		_executeRequest(url, "GET", asyncHandler);
	}

	public void deleteFolder(FolderData folderData) {
		String url = StringUtil.replace(
			_DELETE_FOLDER_TEMPLATE,
			new String[] {"$SERVER_URL", "$REPOSITORY_ID", "$FOLDER_ID"},
			new String[] {
				AppPropsValues.SERVER_URL, AppPropsValues.REPOSITORY_ID,
				Long.toString(folderData.getId())});

		AsyncHandler<Response> asyncHandler = new DeleteFolderHandler(
				folderData);

		_executeRequest(url, "POST", asyncHandler);
	}

	public void getFileContent(FileData fileData) {
		String fileIdString = fileData.getUuid().toString();

		String url = StringUtil.replace(
			_GET_FILE_CONTENT_TEMPLATE,
			new String[] {"$SERVER_URL", "$REPOSITORY_ID", "$FILE_UUID"},
			new String[] {
				AppPropsValues.SERVER_URL, AppPropsValues.REPOSITORY_ID,
				fileIdString});

		ResumableAsyncHandler<Response> resumableAsyncHandler =
			new FileContentHandler(fileData);

		_executeRequest(url, "GET", resumableAsyncHandler);
	}

	public void syncRepositories(RepositoryListener repositoryListener) {
		List<String> groupIds = new ArrayList<String>();

		groupIds.add(AppPropsValues.GROUP_ID);

		repositoryListener.handleRepositories(groupIds);
	}

	public void updateFolder(FolderData folderData) {
		String folderName = _urlEncoded(folderData.getTitle());

		String url = StringUtil.replace(
			_UPDATE_FOLDER_TEMPLATE,
			new String[] {"$SERVER_URL", "$REPOSITORY_ID", "$FOLDER_ID",
				"$FOLDER_NAME"},
			new String[] {
				AppPropsValues.SERVER_URL, AppPropsValues.REPOSITORY_ID,
				Long.toString(folderData.getId()), folderName});

		AsyncHandler<Response> asyncHandler = new UpdateFolderHandler(
			folderData);

		_executeRequest(url, "POST", asyncHandler);
	}

	private void _executeRequest(
		String url, String method, AsyncHandler<Response> asyncHandler) {

		if (_log.isInfoEnabled()) {
			 _log.info("Connecting with " + url);
		}

		RequestBuilder requestBuilder = new RequestBuilder();

		requestBuilder.setMethod(method);
		requestBuilder.setUrl(url);

		RealmBuilder realmBuilder = new RealmBuilder();

		realmBuilder.setPrincipal(AppPropsValues.USERNAME);
		realmBuilder.setPassword(AppPropsValues.PASSWORD);
		realmBuilder.setScheme(Realm.AuthScheme.BASIC);

		Realm realm = realmBuilder.build();

		requestBuilder.setRealm(realm);

		Request request = requestBuilder.build();

		try {
			AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

			asyncHttpClient.executeRequest(request, asyncHandler);
		}
		catch(Exception e) {
			_log.error(e, e);
		}
	}

	private String _urlEncoded(String template) {
		try {
			return URLEncoder.encode(template, "UTF8");
		}
		catch (UnsupportedEncodingException e) {
			_log.error(e,e);
		}

		return StringPool.BLANK;
	}

	private static final String _ADD_FOLDER_TEMPLATE =
		"$SERVER_URL/tunnel-web/secure/rest/dlapp/add-folder/repository-id" +
		"/$REPOSITORY_ID/parent-folder-id/$PARENT_FOLDER_ID/name/$FOLDER_NAME" +
		"/description/-";

	private static final String _DELETE_FOLDER_TEMPLATE =
		"$SERVER_URL/tunnel-web/secure/rest/dlapp/delete-folder/repository-id" +
		"/$REPOSITORY_ID/folder-id/$FOLDER_ID";

	private static final String _GET_FOLDERS_TEMPLATE =
		"$SERVER_URL/tunnel-web/secure/rest/dlapp/get-folders/repository-id" +
		"/$REPOSITORY_ID/parent-folder-id/$PARENT_FOLDER_ID";

	private static final String _GET_FILE_ENTRIES_TEMPLATE =
		"$SERVER_URL/tunnel-web/secure/rest/dlapp/get-file-entries" +
		"/repository-id/$REPOSITORY_ID/folder-id/$FOLDER_ID";

	private static final String _GET_FILE_CONTENT_TEMPLATE =
		"$SERVER_URL/documents/$REPOSITORY_ID/$FILE_UUID";

	private static final String _UPDATE_FOLDER_TEMPLATE =
		"$SERVER_URL/tunnel-web/secure/rest/dlapp/update-folder/repository-id" +
		"/$REPOSITORY_ID/folder-id/$FOLDER_ID/name/$FOLDER_NAME" +
		"/description/-";

	private static Log _log = LogFactoryUtil.getLog(
		RemoteFileSystem.class.getName());

}