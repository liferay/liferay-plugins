/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.compat.hook.webdav;

import com.liferay.compat.hook.filter.CompatWebDAVThreadLocal;
import com.liferay.compat.portlet.documentlibrary.util.DLUtil;
import com.liferay.compat.util.CompatConstants;
import com.liferay.portal.DuplicateLockException;
import com.liferay.portal.InvalidLockException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.webdav.Resource;
import com.liferay.portal.kernel.webdav.Status;
import com.liferay.portal.kernel.webdav.WebDAVException;
import com.liferay.portal.kernel.webdav.WebDAVRequest;
import com.liferay.portal.kernel.webdav.WebDAVStorage;
import com.liferay.portal.kernel.webdav.WebDAVStorageWrapper;
import com.liferay.portal.kernel.webdav.WebDAVUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Lock;
import com.liferay.portal.model.RepositoryEntry;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RepositoryEntryLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextThreadLocal;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLAppServiceUtil;
import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.File;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class CompatDLWebDAVStorageImpl extends WebDAVStorageWrapper {

	public CompatDLWebDAVStorageImpl(WebDAVStorage webDAVStorage) {
		super(webDAVStorage);
	}

	@Override
	public Resource getResource(WebDAVRequest webDAVRequest)
		throws WebDAVException {

		Resource resource = super.getResource(webDAVRequest);

		if (isInstanceOfDLFileEntryResourceImpl(resource)) {
			return toCompatResource(resource);
		}

		return resource;
	}

	@Override
	public List<Resource> getResources(WebDAVRequest webDAVRequest)
		throws WebDAVException {

		List<Resource> resources = super.getResources(webDAVRequest);

		List<Resource> compatResources = new ArrayList<Resource>(
			resources.size());

		for (Resource resource : resources) {
			if (isInstanceOfDLFileEntryResourceImpl(resource)) {
				Resource compatResource = toCompatResource(resource);

				compatResources.add(compatResource);
			}
			else {
				compatResources.add(resource);
			}
		}

		return compatResources;
	}

	@Override
	public Status lockResource(
			WebDAVRequest webDAVRequest, String owner, long timeout)
		throws WebDAVException {

		Resource resource = getResource(webDAVRequest);

		Lock lock = null;
		int status = HttpServletResponse.SC_OK;

		try {
			if (resource == null) {
				status = HttpServletResponse.SC_CREATED;

				HttpServletRequest request =
					webDAVRequest.getHttpServletRequest();

				String[] pathArray = webDAVRequest.getPathArray();

				long companyId = webDAVRequest.getCompanyId();
				long groupId = webDAVRequest.getGroupId();
				long parentFolderId = getParentFolderId(companyId, pathArray);
				String title = WebDAVUtil.getResourceName(pathArray);

				String contentType = GetterUtil.get(
					request.getHeader(HttpHeaders.CONTENT_TYPE),
					ContentTypes.APPLICATION_OCTET_STREAM);

				if (contentType.equals(ContentTypes.APPLICATION_OCTET_STREAM)) {
					contentType = MimeTypesUtil.getContentType(
						request.getInputStream(), title);
				}

				String description = StringPool.BLANK;
				String changeLog = StringPool.BLANK;

				File file = FileUtil.createTempFile(
					FileUtil.getExtension(title));

				file.createNewFile();

				ServiceContext serviceContext = new ServiceContext();

				serviceContext.setAddGroupPermissions(
					isAddGroupPermissions(groupId));
				serviceContext.setAddGuestPermissions(true);

				FileEntry fileEntry = DLAppServiceUtil.addFileEntry(
					groupId, parentFolderId, title, contentType, title,
					description, changeLog, file, serviceContext);

				resource = toResource(webDAVRequest, fileEntry, false);
			}

			if (isInstanceOfDLFileEntryResourceImpl(
					super.getResource(webDAVRequest))) {

				FileEntry fileEntry = (FileEntry)resource.getModel();

				ServiceContext serviceContext = new ServiceContext();

				serviceContext.setAttribute(
					DLUtil.MANUAL_CHECK_IN_REQUIRED,
					CompatWebDAVThreadLocal.isManualCheckInRequired());

				DLAppServiceUtil.checkOutFileEntry(
					fileEntry.getFileEntryId(), owner, timeout, serviceContext);

				lock = fileEntry.getLock();
			}
			else {
				boolean inheritable = false;

				long depth = WebDAVUtil.getDepth(
					webDAVRequest.getHttpServletRequest());

				if (depth != 0) {
					inheritable = true;
				}

				Folder folder = (Folder)resource.getModel();

				lock = DLAppServiceUtil.lockFolder(
					folder.getRepositoryId(), folder.getFolderId(), owner,
					inheritable, timeout);
			}
		}
		catch (Exception e) {

			// DuplicateLock is 423 not 501

			if (!(e instanceof DuplicateLockException)) {
				throw new WebDAVException(e);
			}

			status = WebDAVUtil.SC_LOCKED;
		}

		return new Status(lock, status);
	}

	@Override
	public boolean unlockResource(WebDAVRequest webDAVRequest, String token)
		throws WebDAVException {

		Resource resource = getResource(webDAVRequest);

		try {
			if (isInstanceOfDLFileEntryResourceImpl(
					super.getResource(webDAVRequest))) {

				FileEntry fileEntry = (FileEntry)resource.getModel();

				// Do not allow WebDAV to check in a file entry if it requires
				// a manual check in

				if (isManualCheckInRequired(fileEntry)) {
					return false;
				}

				ServiceContext serviceContext = new ServiceContext();

				serviceContext.setAttribute(DLUtil.WEBDAV_CHECK_IN_MODE, true);

				ServiceContextThreadLocal.pushServiceContext(serviceContext);

				try {
					DLAppServiceUtil.checkInFileEntry(
						fileEntry.getFileEntryId(), token);
				}
				finally {
					ServiceContextThreadLocal.popServiceContext();
				}

				if (webDAVRequest.isAppleDoubleRequest()) {
					DLAppServiceUtil.deleteFileEntry(
						fileEntry.getFileEntryId());
				}
			}
			else {
				Folder folder = (Folder)resource.getModel();

				DLAppServiceUtil.unlockFolder(
					folder.getRepositoryId(), folder.getParentFolderId(),
					folder.getName(), token);
			}

			return true;
		}
		catch (Exception e) {
			if (e instanceof InvalidLockException) {
				if (_log.isWarnEnabled()) {
					_log.warn(e.getMessage());
				}
			}
			else {
				if (_log.isWarnEnabled()) {
					_log.warn("Unable to unlock file entry", e);
				}
			}
		}

		return false;
	}

	protected long getParentFolderId(long companyId, String[] pathArray)
		throws Exception {

		WebDAVStorage webDAVStorage = getWrappedWebDAVStorage();

		Class<?> clazz = webDAVStorage.getClass();

		Method method = clazz.getDeclaredMethod(
			"getParentFolderId", long.class, String[].class);

		method.setAccessible(true);

		return (Long)method.invoke(webDAVStorage, companyId, pathArray);
	}

	protected boolean isAddGroupPermissions(long groupId) throws Exception {
		Group group = GroupLocalServiceUtil.getGroup(groupId);

		if (!group.isUser()) {
			return true;
		}
		else {
			return false;
		}
	}

	protected boolean isInstanceOfDLFileEntryResourceImpl(Resource resource) {
		Class<?> clazz = resource.getClass();

		String className = clazz.getName();

		if (className.equals(
				CompatConstants.CLASS_NAME_DL_FILE_ENTRY_RESOURCE_IMPL)) {

			return true;
		}

		return false;
	}

	protected boolean isManualCheckInRequired(FileEntry fileEntry)
		throws Exception {

		Class<?> clazz = fileEntry.getClass();

		String className = clazz.getName();

		if (className.equals(CompatConstants.CLASS_NAME_LIFERAY_FILE_ENTRY)) {
			Method method = clazz.getMethod("getDLFileEntry");

			DLFileEntry dlFileEntry = (DLFileEntry)method.invoke(fileEntry);

			ExpandoBridge expandoBridge = dlFileEntry.getExpandoBridge();

			return GetterUtil.getBoolean(
				expandoBridge.getAttribute(
					DLUtil.MANUAL_CHECK_IN_REQUIRED, false));
		}
		else {
			try {
				RepositoryEntry repositoryEntry =
					RepositoryEntryLocalServiceUtil.getRepositoryEntry(
						fileEntry.getFileEntryId());

				ExpandoBridge expandoBridge =
					repositoryEntry.getExpandoBridge();

				return GetterUtil.getBoolean(
					expandoBridge.getAttribute(
						DLUtil.MANUAL_CHECK_IN_REQUIRED, false));
			}
			catch (Exception e) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to retrieve repository entry", e);
				}

				return false;
			}
		}
	}

	protected Resource toCompatResource(Resource resource) {
		ClassLoader classLoader = PortalClassLoaderUtil.getClassLoader();

		return (Resource)ProxyUtil.newProxyInstance(
			classLoader, new Class<?>[] {Resource.class},
			new CompatResourceInvocationHandler(resource));
	}

	protected Resource toResource(
		WebDAVRequest webDAVRequest, FileEntry fileEntry, boolean appendPath) {

		try {
			WebDAVStorage webDAVStorage = getWrappedWebDAVStorage();

			Class<?> clazz = webDAVStorage.getClass();

			Method method = clazz.getDeclaredMethod(
				"toResource",
				new Class<?>[] {
					WebDAVRequest.class, FileEntry.class, boolean.class
				});

			method.setAccessible(true);

			return (Resource)method.invoke(
				webDAVStorage, webDAVRequest, fileEntry, appendPath);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		CompatConstants.CLASS_NAME_DL_WEBDAV_STORAGE_IMPL);

}