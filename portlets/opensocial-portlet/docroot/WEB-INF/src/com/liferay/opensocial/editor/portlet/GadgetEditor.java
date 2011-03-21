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

package com.liferay.opensocial.editor.portlet;

import com.liferay.opensocial.shindig.util.ShindigUtil;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.service.DLAppServiceUtil;
import com.liferay.portlet.documentlibrary.util.comparator.RepositoryModelNameComparator;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.liferay.util.servlet.PortletResponseUtil;

import java.io.IOException;
import java.io.InputStream;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author Dennis Ju
 */
public class GadgetEditor extends MVCPortlet {

	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException, IOException {

		try {
			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("addFileEntry")) {
				serveAddFileEntry(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("addFolder")) {
				serveAddFolder(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("deleteFileEntry")) {
				serveDeleteFileEntry(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("deleteFolder")) {
				serveDeleteFolder(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("getFileEntryContent")) {
				serveGetFileEntryContent(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("getFolderChildren")) {
				serveGetFolderChildren(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("getRenderParameters")) {
				serveGetRenderParameters(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("updateFileEntry")) {
				serveUpdateFileEntry(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("updateFolder")) {
				serveUpdateFileEntry(resourceRequest, resourceResponse);
			}
		}
		catch (IOException ioe) {
			serveException(ioe, resourceRequest, resourceResponse);
			
			throw ioe;
		}
		catch (PortletException pe) {
			serveException(pe, resourceRequest, resourceResponse);
			
			throw pe;
		}
		catch (Exception e) {
			serveException(e, resourceRequest, resourceResponse);
			
			throw new PortletException(e);
		}
  	}

	protected void serveAddFileEntry(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long folderId = ParamUtil.getLong(resourceRequest, "folderId");

		Folder folder = DLAppServiceUtil.getFolder(folderId);

		long repositoryId = folder.getRepositoryId();

		String fileEntryTitle = ParamUtil.getString(
			resourceRequest, "fileEntryTitle");

		String content = ParamUtil.getString(resourceRequest, "content");

		byte[] bytes = content.getBytes(StringPool.UTF8);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddCommunityPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		String extension = FileUtil.getExtension(fileEntryTitle);

		serviceContext.setAttribute("extension", extension);
		serviceContext.setAttribute("sourceFileName", fileEntryTitle);
		serviceContext.setScopeGroupId(folder.getGroupId());

		FileEntry fileEntry = DLAppServiceUtil.addFileEntry(
			repositoryId, folderId, fileEntryTitle, StringPool.BLANK,
			StringPool.BLANK, bytes, serviceContext);

		long fileEntryId = fileEntry.getFileEntryId();

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("fileEntryId", String.valueOf(fileEntryId));

		InputStream is = new UnsyncByteArrayInputStream(
			jsonObject.toString().getBytes());

		String contentType = ContentTypes.TEXT_JAVASCRIPT;

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, null, is, contentType);
	}

	protected void serveAddFolder(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long parentFolderId = ParamUtil.getLong(
			resourceRequest, "parentFolderId");

		Folder parentFolder = DLAppServiceUtil.getFolder(parentFolderId);

		long repositoryId = parentFolder.getRepositoryId();

		String folderName = ParamUtil.getString(resourceRequest, "folderName");

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddCommunityPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setScopeGroupId(parentFolder.getGroupId());

		Folder folder = DLAppServiceUtil.addFolder(
			repositoryId, parentFolderId, folderName, StringPool.BLANK,
			serviceContext);

		long folderId = folder.getFolderId();

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("folderId", folderId);

		InputStream is = new UnsyncByteArrayInputStream(
			jsonObject.toString().getBytes());

		String contentType = ContentTypes.TEXT_JAVASCRIPT;

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, null, is, contentType);
	}

	protected void serveDeleteFileEntry(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long fileEntryId = ParamUtil.getLong(resourceRequest, "fileEntryId");

		DLAppServiceUtil.deleteFileEntry(fileEntryId);
	}

	protected void serveDeleteFolder(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long folderId = ParamUtil.getLong(resourceRequest, "folderId");

		DLAppServiceUtil.deleteFolder(folderId);
	}

	protected void serveException(
			Exception exception, ResourceRequest resourceRequest,
			ResourceResponse resourceResponse)
		throws IOException {
	
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("error", exception.getLocalizedMessage());

		InputStream is = new UnsyncByteArrayInputStream(
			jsonObject.toString().getBytes());
	
		String contentType = ContentTypes.TEXT_JAVASCRIPT;
	
		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, null, is, contentType);
	}

	protected void serveGetFileEntryContent(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long fileEntryId = ParamUtil.getLong(resourceRequest, "fileEntryId");

		FileEntry fileEntry = DLAppServiceUtil.getFileEntry(fileEntryId);

		String content = StringUtil.read(fileEntry.getContentStream());

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("content", content);

		InputStream is = new UnsyncByteArrayInputStream(
			jsonObject.toString().getBytes());

		String contentType = ContentTypes.TEXT_JAVASCRIPT;

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, null, is, contentType);
	}

	protected void serveGetFolderChildren(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long repositoryId = ParamUtil.getLong(resourceRequest, "repositoryId");
		long folderId = ParamUtil.getLong(resourceRequest, "folderId");

		List<Folder> folders = DLAppServiceUtil.getFolders(
			repositoryId, folderId);

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		if (folders.size() > 0) {
			for (Folder folder : folders) {
				folders = ListUtil.sort(
					folders, new RepositoryModelNameComparator(true));

				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

				jsonObject.put("id", String.valueOf(folder.getFolderId()));
				jsonObject.put("label", folder.getName());
				jsonObject.put("leaf", false);
				jsonObject.put("type", "io");

				jsonArray.put(jsonObject);
			}
		}

		boolean getFileEntries = ParamUtil.getBoolean(
			resourceRequest, "getFileEntries");

		if (getFileEntries) {
			List<FileEntry> fileEntries = DLAppServiceUtil.getFileEntries(
				repositoryId, folderId);

			if (fileEntries.size() > 0) {
				fileEntries = ListUtil.sort(
					fileEntries, new RepositoryModelNameComparator(true));
				
				for (FileEntry fileEntry : fileEntries) {
					JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

					jsonObject.put(
						"id", String.valueOf(fileEntry.getFileEntryId()));
					jsonObject.put("label", fileEntry.getTitle());
					jsonObject.put("leaf", true);

					jsonArray.put(jsonObject);
				}
			}
		}

		InputStream is = new UnsyncByteArrayInputStream(
			jsonArray.toString().getBytes());

		String contentType = ContentTypes.TEXT_JAVASCRIPT;

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, null, is, contentType);
	}

	protected void serveGetRenderParameters(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String content = ParamUtil.getString(resourceRequest, "content");

		boolean isContentValid = ShindigUtil.isContentValid(content);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("isContentValid", isContentValid);

		if (isContentValid) {
			String url = "http://localhost/raw.xml";

			long moduleId = ShindigUtil.getModuleId(
				resourceResponse.getNamespace());

			boolean requiresPubsub = ShindigUtil.isRequiresPubsubFromContent(
				content);

			String ownerId = ShindigUtil.getOwnerId(themeDisplay.getLayout());

			String currentURL = PortalUtil.getCurrentURL(resourceRequest);

			String secureToken = ShindigUtil.createSecurityToken(
				ownerId, themeDisplay.getUserId(), url,
				PortalUtil.getPortalURL(themeDisplay), url, moduleId,
				currentURL);

			jsonObject.put("appId", url);
			jsonObject.put("moduleId", moduleId);
			jsonObject.put("requiresPubsub", requiresPubsub);
			jsonObject.put("secureToken", secureToken);
			jsonObject.put("specUrl", url);
		}

		InputStream is = new UnsyncByteArrayInputStream(
			jsonObject.toString().getBytes());

		String contentType = ContentTypes.TEXT_JAVASCRIPT;

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, null, is, contentType);
	}

	protected void serveUpdateFileEntry(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long fileEntryId = ParamUtil.getLong(resourceRequest, "fileEntryId");

		String fileEntryTitle = ParamUtil.getString(
			resourceRequest, "fileEntryTitle");

		FileEntry fileEntry = DLAppServiceUtil.getFileEntry(fileEntryId);

		byte[] bytes = null;

		String content = ParamUtil.getString(resourceRequest, "content");

		if (!content.isEmpty()) {
			bytes = content.getBytes(StringPool.UTF8);
		}

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCreateDate(fileEntry.getCreateDate());
		serviceContext.setModifiedDate(fileEntry.getModifiedDate());

		DLAppServiceUtil.updateFileEntry(
			fileEntryId, fileEntryTitle, StringPool.BLANK, StringPool.BLANK,
			StringPool.BLANK, false, bytes, serviceContext);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		InputStream is = new UnsyncByteArrayInputStream(
			jsonObject.toString().getBytes());

		String contentType = ContentTypes.TEXT_JAVASCRIPT;

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, null, is, contentType);
	}

}