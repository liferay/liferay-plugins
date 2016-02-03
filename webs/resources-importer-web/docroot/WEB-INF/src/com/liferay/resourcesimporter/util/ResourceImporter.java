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

package com.liferay.resourcesimporter.util;

import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplateConstants;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.InputStream;

import java.net.URL;
import java.net.URLConnection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Raymond Augé
 * @author Ryan Park
 */
public class ResourceImporter extends FileSystemImporter {

	@Override
	public void importResources() throws Exception {
		doImportResources();
	}

	@Override
	protected void addApplicationDisplayTemplate(
			String parentDirName, String dirName, long classNameId)
		throws Exception {

		StringBundler sb = new StringBundler(4);

		sb.append(resourcesDir);
		sb.append(parentDirName);
		sb.append("/");
		sb.append(dirName);

		Set<String> resourcePaths = servletContext.getResourcePaths(
			sb.toString());

		if (resourcePaths == null) {
			return;
		}

		for (String resourcePath : resourcePaths) {
			URL url = servletContext.getResource(resourcePath);

			URLConnection urlConnection = url.openConnection();

			String script = StringUtil.read(urlConnection.getInputStream());

			if (Validator.isNull(script)) {
				continue;
			}

			File file = new File(resourcePath);

			addApplicationDisplayTemplate(script, file, classNameId);
		}
	}

	@Override
	protected void addDDLDisplayTemplates(
			String ddmStructureKey, String dirName, String fileName)
		throws Exception {

		DDMStructure ddmStructure = DDMStructureLocalServiceUtil.getStructure(
			groupId, PortalUtil.getClassNameId(DDLRecordSet.class),
			ddmStructureKey);

		StringBundler sb = new StringBundler(4);

		sb.append(resourcesDir);
		sb.append(dirName);
		sb.append(StringPool.SLASH);
		sb.append(fileName);

		Set<String> resourcePaths = servletContext.getResourcePaths(
			sb.toString());

		if (resourcePaths == null) {
			return;
		}

		for (String resourcePath : resourcePaths) {
			URL url = servletContext.getResource(resourcePath);

			URLConnection urlConnection = url.openConnection();

			String script = StringUtil.read(urlConnection.getInputStream());

			if (Validator.isNull(script)) {
				return;
			}

			addDDMTemplate(
				groupId, ddmStructure.getStructureId(), resourcePath,
				getDDMTemplateLanguage(resourcePath), script,
				DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY, StringPool.BLANK);
		}
	}

	@Override
	protected void addDDLFormTemplates(
			String ddmStructureKey, String dirName, String fileName)
		throws Exception {

		DDMStructure ddmStructure = DDMStructureLocalServiceUtil.getStructure(
			groupId, PortalUtil.getClassNameId(DDLRecordSet.class),
			ddmStructureKey);

		StringBundler sb = new StringBundler(4);

		sb.append(resourcesDir);
		sb.append(dirName);
		sb.append(StringPool.SLASH);
		sb.append(fileName);

		Set<String> resourcePaths = servletContext.getResourcePaths(
			sb.toString());

		if (resourcePaths == null) {
			return;
		}

		for (String resourcePath : resourcePaths) {
			URL url = servletContext.getResource(resourcePath);

			URLConnection urlConnection = url.openConnection();

			String script = StringUtil.read(urlConnection.getInputStream());

			if (Validator.isNull(script)) {
				return;
			}

			addDDMTemplate(
				groupId, ddmStructure.getStructureId(), resourcePath, "xsd",
				script, DDMTemplateConstants.TEMPLATE_TYPE_FORM,
				DDMTemplateConstants.TEMPLATE_MODE_CREATE);
		}
	}

	@Override
	protected void addDDLStructures(String dirName) throws Exception {
		Set<String> resourcePaths = servletContext.getResourcePaths(
			resourcesDir.concat(dirName));

		if (resourcePaths == null) {
			return;
		}

		for (String resourcePath : resourcePaths) {
			File file = new File(resourcePath);

			URL url = servletContext.getResource(resourcePath);

			URLConnection urlConnection = url.openConnection();

			addDDMStructures(
				FileUtil.stripExtension(file.getName()),
				urlConnection.getInputStream());
		}
	}

	@Override
	protected void addDDMStructures(String parentStructureId, String dirName)
		throws Exception {

		Set<String> resourcePaths = servletContext.getResourcePaths(
			resourcesDir.concat(dirName));

		if (resourcePaths == null) {
			return;
		}

		for (String resourcePath : resourcePaths) {
			if (resourcePath.endsWith(StringPool.SLASH)) {
				continue;
			}

			String name = FileUtil.getShortFileName(resourcePath);

			URL url = servletContext.getResource(resourcePath);

			URLConnection urlConnection = url.openConnection();

			addDDMStructures(
				parentStructureId, name, urlConnection.getInputStream());
		}
	}

	@Override
	protected void addDDMTemplates(String ddmStructureKey, String dirName)
		throws Exception {

		Set<String> resourcePaths = servletContext.getResourcePaths(
			resourcesDir.concat(dirName));

		if (resourcePaths == null) {
			return;
		}

		for (String resourcePath : resourcePaths) {
			if (resourcePath.endsWith(StringPool.SLASH)) {
				continue;
			}

			String name = FileUtil.getShortFileName(resourcePath);

			URL url = servletContext.getResource(resourcePath);

			URLConnection urlConnection = url.openConnection();

			addDDMTemplates(
				ddmStructureKey, name, urlConnection.getInputStream());
		}
	}

	@Override
	protected void addDLFileEntries(String dirName) throws Exception {
		Set<String> resourcePaths = servletContext.getResourcePaths(
			resourcesDir.concat(dirName));

		if (resourcePaths == null) {
			return;
		}

		for (String resourcePath : resourcePaths) {
			if (resourcePath.endsWith(StringPool.SLASH)) {
				addDLFolder(
					DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, resourcePath);
			}
			else {
				addDLFileEntry(resourcePath);
			}
		}
	}

	protected void addDLFileEntry(String resourcePath) throws Exception {
		Long parentFolderId = _folderIds.get(
			FileUtil.getPath(resourcePath) + StringPool.SLASH);

		if (parentFolderId == null) {
			parentFolderId = 0L;
		}

		URL url = servletContext.getResource(resourcePath);

		URLConnection urlConnection = url.openConnection();

		addDLFileEntry(
			parentFolderId, FileUtil.getShortFileName(resourcePath),
			urlConnection.getInputStream(), urlConnection.getContentLength());
	}

	@Override
	protected long addDLFolder(long parentFolderId, String resourcePath)
		throws Exception {

		long folderId = super.addDLFolder(
			parentFolderId,
			FileUtil.getShortFileName(FileUtil.getPath(resourcePath)));

		_folderIds.put(resourcePath, folderId);

		Set<String> resourcePaths = servletContext.getResourcePaths(
			resourcePath);

		if ((resourcePaths == null) || resourcePaths.isEmpty()) {
			return folderId;
		}

		for (String curResourcePath : resourcePaths) {
			if (curResourcePath.endsWith(StringPool.SLASH)) {
				addDLFolder(folderId, curResourcePath);
			}
			else {
				addDLFileEntry(curResourcePath);
			}
		}

		return folderId;
	}

	@Override
	protected void addJournalArticles(
			String ddmStructureKey, String ddmTemplateKey, String dirName)
		throws Exception {

		Set<String> resourcePaths = servletContext.getResourcePaths(
			resourcesDir.concat(dirName));

		if (resourcePaths == null) {
			return;
		}

		for (String resourcePath : resourcePaths) {
			if (resourcePath.endsWith(StringPool.SLASH)) {
				continue;
			}

			String name = FileUtil.getShortFileName(resourcePath);

			URL url = servletContext.getResource(resourcePath);

			URLConnection urlConnection = url.openConnection();

			addJournalArticles(
				ddmStructureKey, ddmTemplateKey, name,
				urlConnection.getInputStream());
		}
	}

	@Override
	protected void addLayoutPrototype(String dirName) throws Exception {
		Set<String> resourcePaths = servletContext.getResourcePaths(
			resourcesDir.concat(dirName));

		if (resourcePaths == null) {
			return;
		}

		for (String resourcePath : resourcePaths) {
			String extension = FileUtil.getExtension(resourcePath);

			if (!extension.equals("json")) {
				return;
			}

			URL url = servletContext.getResource(resourcePath);

			URLConnection urlConnection = url.openConnection();

			addLayoutPrototype(urlConnection.getInputStream());
		}
	}

	@Override
	protected InputStream getInputStream(String fileName) throws Exception {
		URL url = servletContext.getResource(resourcesDir.concat(fileName));

		if (url == null) {
			return null;
		}

		URLConnection urlConnection = url.openConnection();

		return urlConnection.getInputStream();
	}

	private Map<String, Long> _folderIds = new HashMap<>();

}