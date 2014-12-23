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

package com.liferay.sync.servlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.servlet.PortalSessionThreadLocal;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.zip.ZipWriter;
import com.liferay.portal.kernel.zip.ZipWriterFactoryUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Image;
import com.liferay.portal.model.ImageConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ImageServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;
import com.liferay.portlet.documentlibrary.model.DLFileVersion;
import com.liferay.portlet.documentlibrary.service.DLAppServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileVersionLocalServiceUtil;
import com.liferay.sync.SyncSiteUnavailableException;
import com.liferay.sync.model.SyncDLFileVersionDiff;
import com.liferay.sync.service.SyncDLFileVersionDiffLocalServiceUtil;
import com.liferay.sync.util.PortletPropsValues;
import com.liferay.sync.util.SyncUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Dennis Ju
 */
public class DownloadServlet extends HttpServlet {

	@Override
	public void service(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		try {
			HttpSession session = request.getSession();

			if (PortalSessionThreadLocal.getHttpSession() == null) {
				PortalSessionThreadLocal.setHttpSession(session);
			}

			User user = PortalUtil.getUser(request);

			PermissionChecker permissionChecker =
				PermissionCheckerFactoryUtil.create(user);

			PermissionThreadLocal.setPermissionChecker(permissionChecker);

			String path = HttpUtil.fixPath(request.getPathInfo());
			String[] pathArray = StringUtil.split(path, CharPool.SLASH);

			if (pathArray[0].equals("image")) {
				long imageId = GetterUtil.getLong(pathArray[1]);

				sendImage(response, imageId);
			}
			else if (pathArray[0].equals("zip")) {
				String zipFileIds = ParamUtil.get(
					request, "zipFileIds", StringPool.BLANK);

				if (Validator.isNull(zipFileIds)) {
					throw new IllegalArgumentException(
						"Missing parameter zipFileIds");
				}

				JSONArray zipFileIdsJSONArray = JSONFactoryUtil.createJSONArray(
					zipFileIds);

				sendZipFile(response, user, zipFileIdsJSONArray);
			}
			else {
				long groupId = GetterUtil.getLong(pathArray[0]);
				String uuid = pathArray[1];

				Group group = GroupLocalServiceUtil.fetchGroup(groupId);

				if ((group == null) || !SyncUtil.isSyncEnabled(group)) {
					response.setHeader(
						_ERROR_HEADER,
						SyncSiteUnavailableException.class.getName());

					ServletResponseUtil.write(response, new byte[0]);

					return;
				}

				boolean patch = ParamUtil.getBoolean(request, "patch");

				if (patch) {
					sendPatch(request, response, user, groupId, uuid);
				}
				else {
					sendFile(request, response, groupId, uuid);
				}
			}
		}
		catch (NoSuchFileEntryException nsfee) {
			PortalUtil.sendError(
				HttpServletResponse.SC_NOT_FOUND, nsfee, request, response);
		}
		catch (Exception e) {
			PortalUtil.sendError(e, request, response);
		}
	}

	protected File getDeltaFile(
			long userId, long fileEntryId, String sourceVersion,
			String targetVersion)
		throws PortalException {

		File sourceFile = DLFileEntryLocalServiceUtil.getFile(
			userId, fileEntryId, sourceVersion, false);
		File targetFile = DLFileEntryLocalServiceUtil.getFile(
			userId, fileEntryId, targetVersion, false);

		return SyncUtil.getFileDelta(sourceFile, targetFile);
	}

	protected DownloadServletInputStream getFileDownloadServletInputStream(
			long groupId, String uuid, String version)
		throws Exception {

		FileEntry fileEntry = DLAppServiceUtil.getFileEntryByUuidAndGroupId(
			uuid, groupId);

		if (Validator.isNull(version)) {
			version = fileEntry.getVersion();
		}

		FileVersion fileVersion = fileEntry.getFileVersion(version);

		InputStream inputStream = fileVersion.getContentStream(false);

		return new DownloadServletInputStream(
			inputStream, fileVersion.getMimeType(), fileVersion.getSize());
	}

	protected DownloadServletInputStream getPatchDownloadServletInputStream(
			User user, long groupId, String uuid, String sourceVersion,
			String targetVersion)
		throws Exception {

		FileEntry fileEntry = DLAppServiceUtil.getFileEntryByUuidAndGroupId(
			uuid, groupId);

		DLFileVersion sourceFileVersion =
			DLFileVersionLocalServiceUtil.getFileVersion(
				fileEntry.getFileEntryId(), sourceVersion);
		DLFileVersion targetFileVersion =
			DLFileVersionLocalServiceUtil.getFileVersion(
				fileEntry.getFileEntryId(), targetVersion);

		if (!PortletPropsValues.SYNC_FILE_DIFF_CACHE_ENABLED) {
			File deltaFile = getDeltaFile(
				user.getUserId(), fileEntry.getFileEntryId(), sourceVersion,
				targetVersion);

			return new DownloadServletInputStream(
				new FileInputStream(deltaFile), deltaFile.length());
		}

		SyncDLFileVersionDiff syncDLFileVersionDiff =
			SyncDLFileVersionDiffLocalServiceUtil.fetchSyncDLFileVersionDiff(
				fileEntry.getFileEntryId(),
				sourceFileVersion.getFileVersionId(),
				targetFileVersion.getFileVersionId());

		if (syncDLFileVersionDiff != null) {
			SyncDLFileVersionDiffLocalServiceUtil.refreshExpirationDate(
				syncDLFileVersionDiff.getSyncDLFileVersionDiffId());
		}
		else {
			File deltaFile = getDeltaFile(
				user.getUserId(), fileEntry.getFileEntryId(), sourceVersion,
				targetVersion);

			syncDLFileVersionDiff =
				SyncDLFileVersionDiffLocalServiceUtil.addSyncDLFileVersionDiff(
					fileEntry.getFileEntryId(),
					sourceFileVersion.getFileVersionId(),
					targetFileVersion.getFileVersionId(), deltaFile);
		}

		FileEntry dataFileEntry = PortletFileRepositoryUtil.getPortletFileEntry(
			syncDLFileVersionDiff.getDataFileEntryId());

		return new DownloadServletInputStream(
			dataFileEntry.getContentStream(), dataFileEntry.getSize());
	}

	protected void processException(
		String zipFileId, String exception, JSONArray errorsJSONArray) {

		JSONObject errorJSONObject = JSONFactoryUtil.createJSONObject();

		JSONObject exceptionJSONObject = JSONFactoryUtil.createJSONObject();

		exceptionJSONObject.put("exception", exception);

		errorJSONObject.put(zipFileId, exceptionJSONObject);

		errorsJSONArray.put(errorJSONObject);
	}

	protected void sendFile(
			HttpServletRequest request, HttpServletResponse response,
			long groupId, String uuid)
		throws Exception {

		String version = ParamUtil.getString(request, "version");

		DownloadServletInputStream downloadServletInputStream =
			getFileDownloadServletInputStream(groupId, uuid, version);

		ServletResponseUtil.write(
			response, downloadServletInputStream.getInputStream(),
			downloadServletInputStream.getSize());
	}

	protected void sendImage(HttpServletResponse response, long imageId)
		throws Exception {

		Image image = ImageServiceUtil.getImage(imageId);

		String type = image.getType();

		if (!type.equals(ImageConstants.TYPE_NOT_AVAILABLE)) {
			String contentType = MimeTypesUtil.getExtensionContentType(type);

			response.setContentType(contentType);
		}

		ServletResponseUtil.write(response, image.getTextObj());
	}

	protected void sendPatch(
			HttpServletRequest request, HttpServletResponse response, User user,
			long groupId, String uuid)
		throws Exception {

		String sourceVersion = ParamUtil.getString(request, "sourceVersion");

		if (Validator.isNull(sourceVersion)) {
			throw new IllegalArgumentException("Missing source version");
		}

		String targetVersion = ParamUtil.getString(request, "targetVersion");

		if (Validator.isNull(targetVersion)) {
			throw new IllegalArgumentException("Missing target version");
		}

		DownloadServletInputStream downloadServletInputStream =
			getPatchDownloadServletInputStream(
				user, groupId, uuid, sourceVersion, targetVersion);

		ServletResponseUtil.write(
			response, downloadServletInputStream.getInputStream(),
			downloadServletInputStream.getSize());
	}

	protected void sendZipFile(
			HttpServletResponse response, User user,
			JSONArray zipFileIdsJSONArray)
		throws Exception {

		ZipWriter zipWriter = ZipWriterFactoryUtil.getZipWriter();

		JSONArray errorsJSONArray = JSONFactoryUtil.createJSONArray();

		for (int i = 0; i < zipFileIdsJSONArray.length(); i++) {
			JSONObject zipObjectJSONObject = zipFileIdsJSONArray.getJSONObject(
				i);

			long groupId = zipObjectJSONObject.getLong("groupId");
			String zipFileId = zipObjectJSONObject.getString("zipFileId");

			Group group = GroupLocalServiceUtil.fetchGroup(groupId);

			if ((group == null) || !SyncUtil.isSyncEnabled(group)) {
				processException(
					zipFileId, SyncSiteUnavailableException.class.getName(),
					errorsJSONArray);

				continue;
			}

			try {
				String uuid = zipObjectJSONObject.getString("uuid");

				if (zipObjectJSONObject.getBoolean("patch")) {
					String sourceVersion = zipObjectJSONObject.getString(
						"sourceVersion");
					String targetVersion = zipObjectJSONObject.getString(
						"targetVersion");

					if (Validator.isNull(sourceVersion) ||
						Validator.isNull(targetVersion)) {

						processException(
							zipFileId, IllegalArgumentException.class.getName(),
							errorsJSONArray);

						continue;
					}

					DownloadServletInputStream downloadServletInputStream =
						getPatchDownloadServletInputStream(
							user, groupId, uuid, sourceVersion, targetVersion);

					zipWriter.addEntry(
						zipFileId, downloadServletInputStream.getInputStream());
				}
				else {
					DownloadServletInputStream downloadServletInputStream =
						getFileDownloadServletInputStream(
							groupId, uuid,
							zipObjectJSONObject.getString("version"));

					zipWriter.addEntry(
						zipFileId, downloadServletInputStream.getInputStream());
				}
			}
			catch (Exception e) {
				processException(
					zipFileId, e.getClass().getName(), errorsJSONArray);
			}
		}

		zipWriter.addEntry("errors.json", errorsJSONArray.toString());

		File file = zipWriter.getFile();

		ServletResponseUtil.write(
			response, new FileInputStream(file), file.length());
	}

	private static final String _ERROR_HEADER = "Sync-Error";

}