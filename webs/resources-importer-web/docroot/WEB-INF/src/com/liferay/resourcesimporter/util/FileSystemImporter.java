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

import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xml.Attribute;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.LayoutPrototype;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.LayoutSetPrototype;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.LayoutTypePortletConstants;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portal.model.Theme;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutPrototypeLocalServiceUtil;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.service.LayoutSetPrototypeLocalServiceUtil;
import com.liferay.portal.service.RepositoryLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextThreadLocal;
import com.liferay.portal.service.ThemeLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetTag;
import com.liferay.portlet.asset.service.AssetTagLocalServiceUtil;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.documentlibrary.DuplicateFileException;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFolderLocalServiceUtil;
import com.liferay.portlet.documentlibrary.util.DLUtil;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructureConstants;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.service.DDMTemplateLocalServiceUtil;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.model.JournalArticleConstants;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portlet.journal.service.JournalArticleServiceUtil;
import com.liferay.portlet.journal.util.JournalConverterUtil;
import com.liferay.wiki.model.WikiPage;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.PortletPreferences;

/**
 * @author Ryan Park
 * @author Raymond Augé
 */
public class FileSystemImporter extends BaseImporter {

	@Override
	public void importResources() throws Exception {
		_resourcesDir = new File(resourcesDir);

		if (!_resourcesDir.isDirectory() || !_resourcesDir.canRead()) {
			throw new IllegalArgumentException(
				"Unaccessible resource directory " + resourcesDir);
		}

		doImportResources();
	}

	protected void addApplicationDisplayTemplate(
			String script, File file, long classNameId)
		throws PortalException {

		String fileName = FileUtil.stripExtension(file.getName());

		String name = getName(fileName);

		DDMTemplate ddmTemplate = DDMTemplateLocalServiceUtil.fetchTemplate(
			groupId, classNameId, getKey(fileName));

		if (ddmTemplate != null) {
			if (!developerModeEnabled) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"DDM template with name " + name + " and version " +
							version + " already exists");
				}

				return;
			}

			if (!updateModeEnabled) {
				DDMTemplateLocalServiceUtil.deleteTemplate(ddmTemplate);
			}
		}

		try {
			if (!updateModeEnabled || (ddmTemplate == null)) {
				DDMTemplateLocalServiceUtil.addTemplate(
					userId, groupId, classNameId, 0,
					PortalUtil.getClassNameId(JournalArticle.class),
					getKey(fileName), getMap(name), null,
					DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY,
					StringPool.BLANK, getDDMTemplateLanguage(file.getName()),
					script, false, false, StringPool.BLANK, null,
					serviceContext);
			}
			else {
				DDMTemplateLocalServiceUtil.updateTemplate(
					userId, ddmTemplate.getTemplateId(),
					ddmTemplate.getClassPK(), getMap(name), null,
					DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY,
					StringPool.BLANK, getDDMTemplateLanguage(file.getName()),
					script, false, serviceContext);
			}
		}
		catch (PortalException e) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to import application display template " +
						file.getName(),
					e);
			}

			throw e;
		}
	}

	protected void addApplicationDisplayTemplate(
			String parentDirName, String dirName, long classNameId)
		throws Exception {

		File dir = new File(
			_resourcesDir, parentDirName + StringPool.SLASH + dirName);

		if (!dir.isDirectory() || !dir.canRead()) {
			return;
		}

		File[] files = listFiles(dir);

		for (File file : files) {
			String script = StringUtil.read(getInputStream(file));

			if (Validator.isNull(script)) {
				continue;
			}

			addApplicationDisplayTemplate(script, file, classNameId);
		}
	}

	protected void addApplicationDisplayTemplates(String dirName)
		throws Exception {

		for (Object[] applicationDisplayTemplateType :
				_APPLICATION_DISPLAY_TEMPLATE_TYPES) {

			Class<?> clazz = (Class<?>)applicationDisplayTemplateType[1];

			addApplicationDisplayTemplate(
				dirName, (String)applicationDisplayTemplateType[0],
				PortalUtil.getClassNameId(clazz));
		}
	}

	protected void addDDLDisplayTemplates(
			String ddmStructureKey, String dirName, String fileName)
		throws Exception {

		DDMStructure ddmStructure = DDMStructureLocalServiceUtil.getStructure(
			groupId, PortalUtil.getClassNameId(DDLRecordSet.class),
			ddmStructureKey);

		File dir = new File(
			_resourcesDir, dirName + StringPool.SLASH + fileName);

		if (!dir.isDirectory() || !dir.canRead()) {
			return;
		}

		File[] files = listFiles(dir);

		for (File file : files) {
			String script = StringUtil.read(getInputStream(file));

			if (Validator.isNull(script)) {
				return;
			}

			addDDMTemplate(
				groupId, ddmStructure.getStructureId(), file.getName(),
				getDDMTemplateLanguage(file.getName()), script,
				DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY, null);
		}
	}

	protected void addDDLFormTemplates(
			String ddmStructureKey, String dirName, String fileName)
		throws Exception {

		DDMStructure ddmStructure = DDMStructureLocalServiceUtil.getStructure(
			groupId, PortalUtil.getClassNameId(DDLRecordSet.class),
			ddmStructureKey);

		File dir = new File(
			_resourcesDir, dirName + StringPool.SLASH + fileName);

		if (!dir.isDirectory() || !dir.canRead()) {
			return;
		}

		File[] files = listFiles(dir);

		for (File file : files) {
			String script = StringUtil.read(getInputStream(file));

			if (Validator.isNull(script)) {
				return;
			}

			addDDMTemplate(
				groupId, ddmStructure.getStructureId(), file.getName(), "xsd",
				script, DDMTemplateConstants.TEMPLATE_TYPE_FORM,
				DDMTemplateConstants.TEMPLATE_MODE_CREATE);
		}
	}

	protected void addDDLStructures(String dirName) throws Exception {
		File dir = new File(_resourcesDir, dirName);

		if (!dir.isDirectory() || !dir.canRead()) {
			return;
		}

		File[] files = listFiles(dir);

		for (File file : files) {
			String fileName = FileUtil.stripExtension(file.getName());

			addDDMStructures(fileName, getInputStream(file));
		}
	}

	protected void addDDMStructures(String fileName, InputStream inputStream)
		throws Exception {

		fileName = FileUtil.stripExtension(fileName);

		String name = getName(fileName);

		DDMStructure ddmStructure = DDMStructureLocalServiceUtil.fetchStructure(
			groupId, PortalUtil.getClassNameId(DDLRecordSet.class),
			getKey(fileName));

		if (ddmStructure != null) {
			if (!developerModeEnabled) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"DDM structure with name " + name + " and version " +
							version + " already exists");
				}

				return;
			}

			if (!updateModeEnabled) {
				DDMStructureLocalServiceUtil.deleteDDMStructure(ddmStructure);
			}
		}

		try {
			if (!updateModeEnabled || (ddmStructure == null)) {
				ddmStructure = DDMStructureLocalServiceUtil.addStructure(
					userId, groupId,
					DDMStructureConstants.DEFAULT_PARENT_STRUCTURE_ID,
					PortalUtil.getClassNameId(DDLRecordSet.class),
					getKey(fileName), getMap(name), null,
					StringUtil.read(inputStream),
					PropsUtil.get(PropsKeys.DYNAMIC_DATA_LISTS_STORAGE_TYPE),
					DDMStructureConstants.TYPE_DEFAULT, serviceContext);
			}
			else {
				ddmStructure = DDMStructureLocalServiceUtil.updateStructure(
					ddmStructure.getStructureId(),
					DDMStructureConstants.DEFAULT_PARENT_STRUCTURE_ID,
					getMap(name), null, StringUtil.read(inputStream),
					serviceContext);
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to import DDM structure " + fileName, e);
			}

			throw e;
		}

		addDDLDisplayTemplates(
			ddmStructure.getStructureKey(),
			_DDL_STRUCTURE_DISPLAY_TEMPLATE_DIR_NAME, fileName);

		addDDLFormTemplates(
			ddmStructure.getStructureKey(),
			_DDL_STRUCTURE_FORM_TEMPLATE_DIR_NAME, fileName);
	}

	protected void addDDMStructures(
			String parentDDMStructureKey, String dirName)
		throws Exception {

		File dir = new File(_resourcesDir, dirName);

		if (!dir.isDirectory() || !dir.canRead()) {
			return;
		}

		File[] files = listFiles(dir);

		for (File file : files) {
			InputStream inputStream = null;

			try {
				inputStream = new BufferedInputStream(
					new FileInputStream(file));

				addDDMStructures(
					parentDDMStructureKey, file.getName(), inputStream);
			}
			finally {
				if (inputStream != null) {
					inputStream.close();
				}
			}
		}
	}

	protected void addDDMStructures(
			String parentDDMStructureKey, String fileName,
			InputStream inputStream)
		throws Exception {

		fileName = FileUtil.stripExtension(fileName);

		String name = getName(fileName);

		DDMStructure ddmStructure = DDMStructureLocalServiceUtil.fetchStructure(
			groupId, PortalUtil.getClassNameId(JournalArticle.class),
			getKey(fileName));

		if (ddmStructure != null) {
			if (!developerModeEnabled) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"DDM structure with name " + name + " and version " +
							version + " already exists");
				}

				return;
			}

			if (!updateModeEnabled) {
				DDMStructureLocalServiceUtil.deleteDDMStructure(ddmStructure);
			}
		}

		String xsd = StringUtil.read(inputStream);

		if (isJournalStructureXSD(xsd)) {
			xsd = JournalConverterUtil.getDDMXSD(xsd);
		}

		setServiceContext(fileName);

		try {
			if (!updateModeEnabled || (ddmStructure == null)) {
				ddmStructure = DDMStructureLocalServiceUtil.addStructure(
					userId, groupId, parentDDMStructureKey,
					PortalUtil.getClassNameId(JournalArticle.class),
					getKey(fileName), getMap(name), null, xsd,
					PropsUtil.get(PropsKeys.JOURNAL_ARTICLE_STORAGE_TYPE),
					DDMStructureConstants.TYPE_DEFAULT, serviceContext);
			}
			else {
				DDMStructure parentStructure =
					DDMStructureLocalServiceUtil.fetchStructure(
						groupId,
						PortalUtil.getClassNameId(JournalArticle.class),
						parentDDMStructureKey);

				long parentDDMStructureId =
					DDMStructureConstants.DEFAULT_PARENT_STRUCTURE_ID;

				if (parentStructure != null) {
					parentDDMStructureId = parentStructure.getStructureId();
				}

				ddmStructure = DDMStructureLocalServiceUtil.updateStructure(
					ddmStructure.getStructureId(), parentDDMStructureId,
					getMap(name), null, xsd, serviceContext);
			}
		}
		catch (PortalException e) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to import DDM structure " + fileName, e);
			}

			throw e;
		}

		_ddmStructures.add(ddmStructure.getStructureKey());

		addDDMTemplates(
			ddmStructure.getStructureKey(),
			_JOURNAL_DDM_TEMPLATES_DIR_NAME + fileName);

		if (Validator.isNull(parentDDMStructureKey)) {
			addDDMStructures(
				ddmStructure.getStructureKey(),
				_JOURNAL_DDM_STRUCTURES_DIR_NAME + fileName);
		}
	}

	protected void addDDMTemplate(
			long templateGroupId, long ddmStructureId, String fileName,
			String language, String script, String type, String mode)
		throws Exception {

		fileName = FileUtil.getShortFileName(fileName);

		fileName = FileUtil.stripExtension(fileName);

		String name = getName(fileName);

		DDMTemplate ddmTemplate = DDMTemplateLocalServiceUtil.fetchTemplate(
			groupId, PortalUtil.getClassNameId(DDMStructure.class),
			getKey(fileName));

		if (ddmTemplate != null) {
			if (!developerModeEnabled) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"DDM template with name " + name + " and version " +
							version + " already exists");
				}

				return;
			}

			if (!updateModeEnabled) {
				DDMTemplateLocalServiceUtil.deleteTemplate(ddmTemplate);
			}
		}

		try {
			if (!updateModeEnabled || (ddmTemplate == null)) {
				DDMTemplateLocalServiceUtil.addTemplate(
					userId, templateGroupId,
					PortalUtil.getClassNameId(DDMStructure.class),
					ddmStructureId,
					PortalUtil.getClassNameId(JournalArticle.class),
					getKey(fileName), getMap(name), null, type, mode, language,
					script, false, false, StringPool.BLANK, null,
					serviceContext);
			}
			else {
				DDMTemplateLocalServiceUtil.updateTemplate(
					userId, ddmTemplate.getTemplateId(),
					PortalUtil.getClassNameId(DDMStructure.class), getMap(name),
					null, DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY, null,
					language, script, false, false, null, null, serviceContext);
			}
		}
		catch (PortalException e) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to import DDM template " + fileName, e);
			}

			throw e;
		}
	}

	protected void addDDMTemplates(String ddmStructureKey, String dirName)
		throws Exception {

		File dir = new File(_resourcesDir, dirName);

		if (!dir.isDirectory() || !dir.canRead()) {
			return;
		}

		File[] files = listFiles(dir);

		for (File file : files) {
			InputStream inputStream = null;

			try {
				inputStream = new BufferedInputStream(
					new FileInputStream(file));

				addDDMTemplates(ddmStructureKey, file.getName(), inputStream);
			}
			finally {
				if (inputStream != null) {
					inputStream.close();
				}
			}
		}
	}

	protected void addDDMTemplates(
			String ddmStructureKey, String fileName, InputStream inputStream)
		throws Exception {

		fileName = FileUtil.stripExtension(fileName);

		String name = getName(fileName);
		String language = getDDMTemplateLanguage(fileName);

		String xsl = StringUtil.read(inputStream);

		setServiceContext(fileName);

		DDMStructure ddmStructure = DDMStructureLocalServiceUtil.getStructure(
			groupId, PortalUtil.getClassNameId(JournalArticle.class),
			ddmStructureKey);

		DDMTemplate ddmTemplate = DDMTemplateLocalServiceUtil.fetchTemplate(
			groupId, PortalUtil.getClassNameId(DDMStructure.class),
			getKey(fileName));

		if (ddmTemplate != null) {
			if (!developerModeEnabled) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"DDM template with name " + name + " and version " +
							version + " already exists");
				}

				return;
			}

			if (!updateModeEnabled) {
				DDMTemplateLocalServiceUtil.deleteTemplate(ddmTemplate);
			}
		}

		try {
			if (!updateModeEnabled || (ddmTemplate == null)) {
				ddmTemplate = DDMTemplateLocalServiceUtil.addTemplate(
					userId, groupId,
					PortalUtil.getClassNameId(DDMStructure.class),
					ddmStructure.getStructureId(),
					PortalUtil.getClassNameId(JournalArticle.class),
					getKey(fileName), getMap(name), null,
					DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY, null, language,
					replaceFileEntryURL(xsl), false, false, null, null,
					serviceContext);
			}
			else {
				ddmTemplate = DDMTemplateLocalServiceUtil.updateTemplate(
					userId, ddmTemplate.getTemplateId(),
					PortalUtil.getClassNameId(DDMStructure.class), getMap(name),
					null, DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY, null,
					language, replaceFileEntryURL(xsl), false, false, null,
					null, serviceContext);
			}
		}
		catch (PortalException e) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to import DDM template " + fileName, e);
			}

			throw e;
		}

		addJournalArticles(
			ddmStructureKey, ddmTemplate.getTemplateKey(),
			_JOURNAL_ARTICLES_DIR_NAME + fileName);
	}

	protected void addDLFileEntries(String dirName) throws Exception {
		File dir = new File(_resourcesDir, dirName);

		if (!dir.isDirectory()|| !dir.canRead()) {
			return;
		}

		File[] files = dir.listFiles();

		if (ArrayUtil.isEmpty(files)) {
			return;
		}

		for (File file : files) {
			if (file.isDirectory()) {
				addDLFolder(DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, file);
			}
			else {
				addDLFileEntry(
					DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, file);
			}
		}
	}

	protected void addDLFileEntry(long parentFolderId, File file)
		throws Exception {

		InputStream inputStream = null;

		try {
			inputStream = new BufferedInputStream(new FileInputStream(file));

			addDLFileEntry(
				parentFolderId, file.getName(), inputStream, file.length());
		}
		finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}

	protected void addDLFileEntry(
			long parentFolderId, String fileName, InputStream inputStream,
			long length)
		throws Exception {

		String title = FileUtil.stripExtension(fileName);

		setServiceContext(fileName);

		FileEntry fileEntry = null;

		try {
			try {
				fileEntry = DLAppLocalServiceUtil.addFileEntry(
					userId, groupId, parentFolderId, fileName,
					MimeTypesUtil.getContentType(fileName), title,
					StringPool.BLANK, StringPool.BLANK, inputStream, length,
					serviceContext);
			}
			catch (DuplicateFileException dfe) {
				fileEntry = DLAppLocalServiceUtil.getFileEntry(
					groupId, parentFolderId, title);

				String previousVersion = fileEntry.getVersion();

				fileEntry = DLAppLocalServiceUtil.updateFileEntry(
					userId, fileEntry.getFileEntryId(), fileName,
					MimeTypesUtil.getContentType(fileName), title,
					StringPool.BLANK, StringPool.BLANK, true, inputStream,
					length, serviceContext);

				DLFileEntryLocalServiceUtil.deleteFileVersion(
					fileEntry.getUserId(), fileEntry.getFileEntryId(),
					previousVersion);
			}
		}
		catch (PortalException e) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to import DL file entry " + fileName, e);
			}

			throw e;
		}

		addPrimaryKey(DLFileEntry.class.getName(), fileEntry.getPrimaryKey());

		_fileEntries.put(fileName, fileEntry);
	}

	protected long addDLFolder(long parentFolderId, File folder)
		throws Exception {

		long folderId = addDLFolder(parentFolderId, folder.getName());

		File[] files = folder.listFiles();

		if (ArrayUtil.isEmpty(files)) {
			return folderId;
		}

		for (File file : files) {
			if (file.isDirectory()) {
				addDLFolder(folderId, file);
			}
			else {
				addDLFileEntry(folderId, file);
			}
		}

		return folderId;
	}

	protected long addDLFolder(long parentFolderId, String folderName)
		throws Exception {

		DLFolder dlFolder = DLFolderLocalServiceUtil.fetchFolder(
			groupId, parentFolderId, folderName);

		if (dlFolder == null) {
			dlFolder = DLFolderLocalServiceUtil.addFolder(
				userId, groupId, groupId, false, parentFolderId, folderName,
				null, false, serviceContext);
		}

		addPrimaryKey(DLFolder.class.getName(), dlFolder.getPrimaryKey());

		return dlFolder.getFolderId();
	}

	protected void addJournalArticles(
			String ddmStructureKey, String ddmTemplateKey, String dirName)
		throws Exception {

		File dir = new File(_resourcesDir, dirName);

		if (!dir.isDirectory() || !dir.canRead()) {
			return;
		}

		File[] files = listFiles(dir);

		for (File file : files) {
			InputStream inputStream = null;

			try {
				inputStream = new BufferedInputStream(
					new FileInputStream(file));

				addJournalArticles(
					ddmStructureKey, ddmTemplateKey, file.getName(),
					inputStream);
			}
			finally {
				if (inputStream != null) {
					inputStream.close();
				}
			}
		}
	}

	protected void addJournalArticles(
			String ddmStructureKey, String ddmTemplateKey, String fileName,
			InputStream inputStream)
		throws Exception {

		String title = FileUtil.stripExtension(fileName);

		JSONObject assetJSONObject = _assetJSONObjectMap.get(fileName);

		Map<Locale, String> descriptionMap = null;

		boolean indexable = true;

		if (assetJSONObject != null) {
			String abstractSummary = assetJSONObject.getString(
				"abstractSummary");

			descriptionMap = getMap(abstractSummary);

			indexable = GetterUtil.getBoolean(
				assetJSONObject.getString("indexable"), true);
		}

		String content = StringUtil.read(inputStream);

		content = replaceFileEntryURL(content);

		Locale articleDefaultLocale = LocaleUtil.fromLanguageId(
			LocalizationUtil.getDefaultLanguageId(content));

		boolean smallImage = false;
		String smallImageURL = StringPool.BLANK;

		if (assetJSONObject != null) {
			String smallImageFileName = assetJSONObject.getString("smallImage");

			if (Validator.isNotNull(smallImageFileName)) {
				smallImage = true;

				FileEntry fileEntry = _fileEntries.get(smallImageFileName);

				if (fileEntry != null) {
					smallImageURL = DLUtil.getPreviewURL(
						fileEntry, fileEntry.getFileVersion(), null,
						StringPool.BLANK);
				}
			}
		}

		setServiceContext(fileName);

		String journalArticleId = getJournalId(fileName);

		JournalArticle journalArticle =
			JournalArticleLocalServiceUtil.fetchLatestArticle(
				groupId, journalArticleId, WorkflowConstants.STATUS_ANY);

		try {
			if (journalArticle == null) {
				journalArticle = JournalArticleLocalServiceUtil.addArticle(
					userId, groupId, 0, 0, 0, journalArticleId, false,
					JournalArticleConstants.VERSION_DEFAULT,
					getMap(articleDefaultLocale, title), descriptionMap,
					content, ddmStructureKey, ddmTemplateKey, StringPool.BLANK,
					1, 1, 2010, 0, 0, 0, 0, 0, 0, 0, true, 0, 0, 0, 0, 0, true,
					indexable, smallImage, smallImageURL, null,
					new HashMap<String, byte[]>(), StringPool.BLANK,
					serviceContext);
			}
			else {
				journalArticle = JournalArticleLocalServiceUtil.updateArticle(
					userId, groupId, 0, journalArticleId,
					journalArticle.getVersion(),
					getMap(articleDefaultLocale, title), descriptionMap,
					content, ddmStructureKey, ddmTemplateKey, StringPool.BLANK,
					1, 1, 2010, 0, 0, 0, 0, 0, 0, 0, true, 0, 0, 0, 0, 0, true,
					indexable, smallImage, smallImageURL, null,
					new HashMap<String, byte[]>(), StringPool.BLANK,
					serviceContext);
			}

			JournalArticleLocalServiceUtil.updateStatus(
				userId, groupId, journalArticle.getArticleId(),
				journalArticle.getVersion(), WorkflowConstants.STATUS_APPROVED,
				StringPool.BLANK, new HashMap<String, Serializable>(),
				serviceContext);
		}
		catch (PortalException e) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to import journal article " + fileName, e);
			}

			throw e;
		}

		addPrimaryKey(
			JournalArticle.class.getName(), journalArticle.getPrimaryKey());
	}

	protected void addLayout(
			boolean privateLayout, long parentLayoutId,
			JSONObject layoutJSONObject)
		throws Exception {

		if (targetClassName.equals(LayoutSetPrototype.class.getName())) {
			privateLayout = true;
		}

		Map<Locale, String> nameMap = getMap(layoutJSONObject, "name");
		Map<Locale, String> titleMap = getMap(layoutJSONObject, "title");

		String type = layoutJSONObject.getString("type");

		if (Validator.isNull(type)) {
			type = LayoutConstants.TYPE_PORTLET;
		}

		String typeSettings = layoutJSONObject.getString("typeSettings");

		boolean hidden = layoutJSONObject.getBoolean("hidden");

		Map<Locale, String> friendlyURLMap = new HashMap<>();

		String friendlyURL = layoutJSONObject.getString("friendlyURL");

		if (Validator.isNotNull(friendlyURL) &&
			!friendlyURL.startsWith(StringPool.SLASH)) {

			friendlyURL = StringPool.SLASH + friendlyURL;
		}

		friendlyURLMap.put(LocaleUtil.getDefault(), friendlyURL);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(companyId);
		serviceContext.setScopeGroupId(groupId);
		serviceContext.setUserId(userId);

		ServiceContextThreadLocal.pushServiceContext(serviceContext);

		try {
			String layoutPrototypeName = layoutJSONObject.getString(
				"layoutPrototypeName");

			String layoutPrototypeUuid = null;

			if (Validator.isNotNull(layoutPrototypeName)) {
				LayoutPrototype layoutPrototype = getLayoutPrototype(
					companyId, layoutPrototypeName);

				layoutPrototypeUuid = layoutPrototype.getUuid();
			}
			else {
				layoutPrototypeUuid = layoutJSONObject.getString(
					"layoutPrototypeUuid");
			}

			if (Validator.isNotNull(layoutPrototypeUuid)) {
				boolean layoutPrototypeLinkEnabled = GetterUtil.getBoolean(
					layoutJSONObject.getString("layoutPrototypeLinkEnabled"),
					false);

				serviceContext.setAttribute(
					"layoutPrototypeLinkEnabled", layoutPrototypeLinkEnabled);

				serviceContext.setAttribute(
					"layoutPrototypeUuid", layoutPrototypeUuid);
			}

			Layout layout = LayoutLocalServiceUtil.addLayout(
				userId, groupId, privateLayout, parentLayoutId, nameMap,
				titleMap, null, null, null, type, typeSettings, hidden,
				friendlyURLMap, serviceContext);

			LayoutTypePortlet layoutTypePortlet =
				(LayoutTypePortlet)layout.getLayoutType();

			String layoutTemplateId = layoutJSONObject.getString(
				"layoutTemplateId", _defaultLayoutTemplateId);

			if (Validator.isNotNull(layoutTemplateId)) {
				layoutTypePortlet.setLayoutTemplateId(
					userId, layoutTemplateId, false);
			}

			JSONArray columnsJSONArray = layoutJSONObject.getJSONArray(
				"columns");

			addLayoutColumns(
				layout, LayoutTypePortletConstants.COLUMN_PREFIX,
				columnsJSONArray);

			LayoutLocalServiceUtil.updateLayout(
				groupId, layout.isPrivateLayout(), layout.getLayoutId(),
				layout.getTypeSettings());

			JSONArray layoutsJSONArray = layoutJSONObject.getJSONArray(
				"layouts");

			addLayouts(privateLayout, layout.getLayoutId(), layoutsJSONArray);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to import layout " + layoutJSONObject, e);
			}

			throw e;
		}
		finally {
			ServiceContextThreadLocal.popServiceContext();
		}
	}

	protected void addLayoutColumn(
			Layout layout, String columnId, JSONArray columnJSONArray)
		throws Exception {

		if (columnJSONArray == null) {
			return;
		}

		for (int i = 0; i < columnJSONArray.length(); i++) {
			JSONObject portletJSONObject = columnJSONArray.getJSONObject(i);

			if (portletJSONObject == null) {
				String journalArticleId = getJournalId(
					columnJSONArray.getString(i));

				portletJSONObject = getDefaultPortletJSONObject(
					journalArticleId);
			}

			addLayoutColumnPortlet(layout, columnId, portletJSONObject);
		}
	}

	protected void addLayoutColumnPortlet(
			Layout layout, String columnId, JSONObject portletJSONObject)
		throws Exception {

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		String rootPortletId = portletJSONObject.getString("portletId");

		if (Validator.isNull(rootPortletId)) {
			throw new ImporterException("portletId is not specified");
		}

		String portletId = layoutTypePortlet.addPortletId(
			userId, rootPortletId, columnId, -1, false);

		JSONObject portletPreferencesJSONObject =
			portletJSONObject.getJSONObject("portletPreferences");

		if ((portletPreferencesJSONObject == null) ||
			(portletPreferencesJSONObject.length() == 0)) {

			return;
		}

		PortletPreferences portletSetup =
			PortletPreferencesFactoryUtil.getLayoutPortletSetup(
				layout, portletId);

		Iterator<String> iterator = portletPreferencesJSONObject.keys();

		while (iterator.hasNext()) {
			String key = iterator.next();

			String value = portletPreferencesJSONObject.getString(key);

			if (rootPortletId.equals(_JOURNAL_CONTENT_PORTLET_ID) &&
				key.equals("articleId")) {

				value = getJournalId(value);
			}

			portletSetup.setValue(key, value);
		}

		portletSetup.store();

		if (rootPortletId.equals(PortletKeys.NESTED_PORTLETS)) {
			JSONArray columnsJSONArray =
				portletPreferencesJSONObject.getJSONArray("columns");

			StringBundler sb = new StringBundler(4);

			sb.append(StringPool.UNDERLINE);
			sb.append(portletId);
			sb.append(StringPool.DOUBLE_UNDERLINE);
			sb.append(LayoutTypePortletConstants.COLUMN_PREFIX);

			addLayoutColumns(layout, sb.toString(), columnsJSONArray);
		}
	}

	protected void addLayoutColumns(
			Layout layout, String columnPrefix, JSONArray columnsJSONArray)
		throws Exception {

		if (columnsJSONArray == null) {
			return;
		}

		for (int i = 0; i < columnsJSONArray.length(); i++) {
			JSONArray columnJSONArray = columnsJSONArray.getJSONArray(i);

			addLayoutColumn(layout, columnPrefix + (i + 1), columnJSONArray);
		}
	}

	protected void addLayoutPrototype(InputStream inputStream)
		throws Exception {

		String content = StringUtil.read(inputStream);

		if (Validator.isNull(content)) {
			return;
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(content);

		JSONObject layoutTemplateJSONObject = jsonObject.getJSONObject(
			"layoutTemplate");

		Map<Locale, String> nameMap = getMap(
			layoutTemplateJSONObject.getString("name"));

		String name = nameMap.get(Locale.getDefault());

		Map<Locale, String> descriptionMap = getMap(
			layoutTemplateJSONObject, "description");

		String uuid = layoutTemplateJSONObject.getString("uuid");

		LayoutPrototype layoutPrototype = getLayoutPrototype(companyId, name);

		if (layoutPrototype != null) {
			if (!developerModeEnabled) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"Layout prototype with name " + name +
							" already exists for company " + companyId);
				}

				return;
			}

			if (!updateModeEnabled) {
				LayoutPrototypeLocalServiceUtil.deleteLayoutPrototype(
					layoutPrototype);
			}
		}

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(companyId);
		serviceContext.setUserId(userId);

		if (Validator.isNotNull(uuid)) {
			serviceContext.setUuid(uuid);
		}

		try {
			if (!updateModeEnabled || (layoutPrototype == null)) {
				layoutPrototype =
					LayoutPrototypeLocalServiceUtil.addLayoutPrototype(
						userId, companyId, getMap(name), descriptionMap, true,
						serviceContext);
			}
			else {
				layoutPrototype =
					LayoutPrototypeLocalServiceUtil.updateLayoutPrototype(
						layoutPrototype.getLayoutPrototypeId(), getMap(name),
						descriptionMap, layoutPrototype.isActive(),
						serviceContext);
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to import layout prototype " + name, e);
			}

			throw e;
		}

		JSONArray columnsJSONArray = layoutTemplateJSONObject.getJSONArray(
			"columns");

		Layout layout = layoutPrototype.getLayout();

		addLayoutColumns(
			layout, LayoutTypePortletConstants.COLUMN_PREFIX, columnsJSONArray);

		LayoutLocalServiceUtil.updateLayout(
			layout.getGroupId(), layout.isPrivateLayout(), layout.getLayoutId(),
			layout.getTypeSettings());
	}

	protected void addLayoutPrototype(String dirName) throws Exception {
		File layoutTemplatesDir = new File(_resourcesDir, dirName);

		if (!layoutTemplatesDir.isDirectory() ||
			!layoutTemplatesDir.canRead()) {

			return;
		}

		File[] files = listFiles(layoutTemplatesDir);

		for (File file : files) {
			addLayoutPrototype(getInputStream(file));
		}
	}

	protected void addLayouts(
			boolean privateLayout, long parentLayoutId,
			JSONArray layoutsJSONArray)
		throws Exception {

		if (layoutsJSONArray == null) {
			return;
		}

		for (int i = 0; i < layoutsJSONArray.length(); i++) {
			JSONObject layoutJSONObject = layoutsJSONArray.getJSONObject(i);

			addLayout(privateLayout, parentLayoutId, layoutJSONObject);
		}
	}

	protected void addPrimaryKey(String className, long primaryKey) {
		Set<Long> primaryKeys = _primaryKeys.get(className);

		if (primaryKeys == null) {
			primaryKeys = new HashSet<>();

			_primaryKeys.put(className, primaryKeys);
		}

		primaryKeys.add(primaryKey);
	}

	protected void doImportResources() throws Exception {
		serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setScopeGroupId(groupId);

		boolean indexReadOnly = SearchEngineUtil.isIndexReadOnly();

		try {
			SearchEngineUtil.setIndexReadOnly(true);

			setUpAssets("assets.json");
			setUpSettings("settings.json");
			setUpSitemap("sitemap.json");

			SearchEngineUtil.setIndexReadOnly(false);

			long startTime = System.currentTimeMillis();

			if (_log.isDebugEnabled()) {
				_log.debug("Commence indexing");
			}

			index();

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Indexing completed in " +
						(System.currentTimeMillis() - startTime) + "ms");
			}
		}
		finally {
			SearchEngineUtil.setIndexReadOnly(indexReadOnly);
		}
	}

	protected String getDDMTemplateLanguage(String fileName) {
		String extension = FileUtil.getExtension(fileName);

		if (extension.equals(TemplateConstants.LANG_TYPE_CSS) ||
			extension.equals(TemplateConstants.LANG_TYPE_FTL) ||
			extension.equals(TemplateConstants.LANG_TYPE_VM) ||
			extension.equals(TemplateConstants.LANG_TYPE_XSL)) {

			return extension;
		}

		return TemplateConstants.LANG_TYPE_VM;
	}

	protected JSONObject getDefaultPortletJSONObject(String journalArticleId) {
		JSONObject portletJSONObject = JSONFactoryUtil.createJSONObject();

		portletJSONObject.put("portletId", _JOURNAL_CONTENT_PORTLET_ID);

		JSONObject portletPreferencesJSONObject =
			JSONFactoryUtil.createJSONObject();

		portletPreferencesJSONObject.put("articleId", journalArticleId);
		portletPreferencesJSONObject.put("groupId", groupId);
		portletPreferencesJSONObject.put("portletSetupShowBorders", false);

		portletJSONObject.put(
			"portletPreferences", portletPreferencesJSONObject);

		return portletJSONObject;
	}

	protected InputStream getInputStream(File file) throws Exception {
		if (!file.exists() || file.isDirectory() || !file.canRead()) {
			return null;
		}

		return new BufferedInputStream(new FileInputStream(file));
	}

	protected InputStream getInputStream(String fileName) throws Exception {
		File file = new File(_resourcesDir, fileName);

		return getInputStream(file);
	}

	protected String getJournalId(String fileName) {
		String id = FileUtil.stripExtension(fileName);

		id = StringUtil.toUpperCase(id);

		return StringUtil.replace(id, StringPool.SPACE, StringPool.DASH);
	}

	protected String[] getJSONArrayAsStringArray(
		JSONObject jsonObject, String key) {

		JSONArray jsonArray = jsonObject.getJSONArray(key);

		if (jsonArray != null) {
			return ArrayUtil.toStringArray(jsonArray);
		}

		return new String[0];
	}

	protected JSONObject getJSONObject(String fileName) throws Exception {
		String json = null;

		InputStream inputStream = getInputStream(fileName);

		if (inputStream == null) {
			return null;
		}

		try {
			json = StringUtil.read(inputStream);
		}
		finally {
			inputStream.close();
		}

		json = StringUtil.replace(
			json, new String[] {"${companyId}", "${groupId}", "${userId}"},
			new String[] {
				String.valueOf(companyId), String.valueOf(groupId),
				String.valueOf(userId)
			});

		return JSONFactoryUtil.createJSONObject(json);
	}

	protected String getKey(String name) {
		name = StringUtil.replace(name, StringPool.SPACE, StringPool.DASH);

		name = StringUtil.toUpperCase(name);

		if (appendVersion) {
			name = name + StringPool.DASH + version;
		}

		return name;
	}

	protected Map<Locale, String> getMap(
		JSONObject layoutJSONObject, String name) {

		Map<Locale, String> map = new HashMap<>();

		JSONObject jsonObject = layoutJSONObject.getJSONObject(
			name.concat("Map"));

		if (jsonObject != null) {
			map = (Map<Locale, String>)LocalizationUtil.deserialize(jsonObject);

			if (!map.containsKey(LocaleUtil.getDefault())) {
				Collection<String> values = map.values();

				Iterator<String> iterator = values.iterator();

				map.put(LocaleUtil.getDefault(), iterator.next());
			}
		}
		else {
			String value = layoutJSONObject.getString(name);

			map.put(LocaleUtil.getDefault(), value);
		}

		return map;
	}

	protected Map<Locale, String> getMap(Locale locale, String value) {
		Map<Locale, String> map = new HashMap<>();

		map.put(locale, value);

		return map;
	}

	protected Map<Locale, String> getMap(String value) {
		return getMap(LocaleUtil.getDefault(), value);
	}

	protected String getName(String name) {
		if (!appendVersion) {
			return name;
		}

		return name + " - " + version;
	}

	protected void index() {
		for (Map.Entry<String, Set<Long>> primaryKeysEntry :
				_primaryKeys.entrySet()) {

			String className = primaryKeysEntry.getKey();

			Set<Long> primaryKeys = primaryKeysEntry.getValue();

			Indexer indexer = IndexerRegistryUtil.getIndexer(className);

			if (indexer == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"No indexer within the IndexerRegistry for: " +
							className);
				}

				continue;
			}

			if (_log.isDebugEnabled()) {
				_log.debug("Indexing: " + className);
			}

			for (long primaryKey : primaryKeys) {
				try {
					indexer.reindex(className, primaryKey);
				}
				catch (SearchException e) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"Cannot index entry: className=" + className +
								", primaryKey=" + primaryKey,
							e);
					}
				}
			}
		}

		if (_ddmStructures.isEmpty()) {
			return;
		}

		Set<Long> primaryKeys = _primaryKeys.get(
			JournalArticle.class.getName());

		Indexer indexer = IndexerRegistryUtil.getIndexer(
			JournalArticle.class.getName());

		for (String ddmStructureKey : _ddmStructures) {
			List<JournalArticle> journalArticles =
				JournalArticleServiceUtil.getArticlesByStructureId(
					getGroupId(), ddmStructureKey, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null);

			for (JournalArticle journalArticle : journalArticles) {
				if ((primaryKeys != null) &&
					primaryKeys.contains(journalArticle.getPrimaryKey())) {

					continue;
				}

				try {
					indexer.reindex(
						JournalArticle.class.getName(),
						journalArticle.getPrimaryKey());
				}
				catch (SearchException e) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"Cannot index entry: className=" +
								JournalArticle.class.getName() +
								", primaryKey=" +
								journalArticle.getPrimaryKey(),
							e);
					}
				}
			}
		}
	}

	protected boolean isJournalStructureXSD(String xsd) throws Exception {
		Document document = SAXReaderUtil.read(xsd);

		Element rootElement = document.getRootElement();

		Attribute availableLocalesAttribute = rootElement.attribute(
			"available-locales");

		if (availableLocalesAttribute == null) {
			return true;
		}

		return false;
	}

	protected File[] listFiles(File dir) {
		File[] files = dir.listFiles();

		if (files == null) {
			return new File[0];
		}

		List<File> filesList = new ArrayList<>();

		for (File file : files) {
			if (file.isFile()) {
				filesList.add(file);
			}
		}

		return filesList.toArray(new File[filesList.size()]);
	}

	protected String replaceFileEntryURL(String content) throws Exception {
		Matcher matcher = _fileEntryPattern.matcher(content);

		while (matcher.find()) {
			String fileName = matcher.group(1);

			FileEntry fileEntry = _fileEntries.get(fileName);

			String fileEntryURL = StringPool.BLANK;

			if (fileEntry != null) {
				fileEntryURL = DLUtil.getPreviewURL(
					fileEntry, fileEntry.getFileVersion(), null,
					StringPool.BLANK);
			}

			content = matcher.replaceFirst(fileEntryURL);

			matcher.reset(content);
		}

		return content;
	}

	protected void setServiceContext(String name) {
		JSONObject assetJSONObject = _assetJSONObjectMap.get(name);

		String[] assetTagNames = null;

		if (assetJSONObject != null) {
			assetTagNames = getJSONArrayAsStringArray(assetJSONObject, "tags");
		}

		serviceContext.setAssetTagNames(assetTagNames);
	}

	protected void setUpAssets(JSONArray assetsJSONArray) {
		if (assetsJSONArray == null) {
			return;
		}

		for (int i = 0; i < assetsJSONArray.length(); i++) {
			JSONObject assetJSONObject = assetsJSONArray.getJSONObject(i);

			String name = assetJSONObject.getString("name");

			_assetJSONObjectMap.put(name, assetJSONObject);
		}
	}

	protected void setUpAssets(String fileName) throws Exception {
		if (!updateModeEnabled && !isCompanyGroup()) {
			List<AssetTag> assetTags = AssetTagLocalServiceUtil.getGroupTags(
				groupId);

			for (AssetTag assetTag : assetTags) {
				AssetTagLocalServiceUtil.deleteAssetTag(assetTag);
			}

			RepositoryLocalServiceUtil.deleteRepositories(groupId);

			JournalArticleLocalServiceUtil.deleteArticles(groupId);

			DDMTemplateLocalServiceUtil.deleteTemplates(groupId);

			DDMStructureLocalServiceUtil.deleteStructures(groupId);
		}

		JSONObject jsonObject = getJSONObject(fileName);

		if (jsonObject != null) {
			JSONArray assetsJSONArray = jsonObject.getJSONArray("assets");

			setUpAssets(assetsJSONArray);
		}

		addDLFileEntries(_DL_DOCUMENTS_DIR_NAME);

		addApplicationDisplayTemplates(_APPLICATION_DISPLAY_TEMPLATE_DIR_NAME);

		addDDLStructures(_DDL_STRUCTURE_DIR_NAME);

		addDDMStructures(StringPool.BLANK, _JOURNAL_DDM_STRUCTURES_DIR_NAME);

		addDDMTemplates(StringPool.BLANK, _JOURNAL_DDM_TEMPLATES_DIR_NAME);

		addLayoutPrototype(_LAYOUT_PROTOTYPE_DIR_NAME);
	}

	protected void setUpSettings(String fileName) throws Exception {
		if (targetClassName.equals(Group.class.getName())) {
			return;
		}

		JSONObject jsonObject = getJSONObject(fileName);

		if (jsonObject == null) {
			return;
		}

		LayoutSetPrototype layoutSetPrototype =
			LayoutSetPrototypeLocalServiceUtil.getLayoutSetPrototype(
				getTargetClassPK());

		String layoutSetPrototypeSettings = jsonObject.getString(
			"layoutSetPrototypeSettings", StringPool.BLANK);

		layoutSetPrototype.setSettings(layoutSetPrototypeSettings);

		LayoutSetPrototypeLocalServiceUtil.updateLayoutSetPrototype(
			layoutSetPrototype);
	}

	protected void setUpSitemap(String fileName) throws Exception {
		LayoutLocalServiceUtil.deleteLayouts(
			groupId, true, new ServiceContext());

		LayoutLocalServiceUtil.deleteLayouts(
			groupId, false, new ServiceContext());

		JSONObject jsonObject = getJSONObject(fileName);

		if (jsonObject == null) {
			return;
		}

		_defaultLayoutTemplateId = jsonObject.getString(
			"layoutTemplateId", StringPool.BLANK);

		updateLayoutSetThemeId(jsonObject);

		JSONArray layoutsJSONArray = jsonObject.getJSONArray("layouts");

		if (layoutsJSONArray != null) {
			addLayouts(
				false, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
				layoutsJSONArray);
		}
		else {
			JSONArray publicPagesJSONArray = jsonObject.getJSONArray(
				"publicPages");

			if (publicPagesJSONArray != null) {
				addLayouts(
					false, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
					publicPagesJSONArray);
			}

			JSONArray privatePagesJSONArray = jsonObject.getJSONArray(
				"privatePages");

			if (privatePagesJSONArray != null) {
				addLayouts(
					true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
					privatePagesJSONArray);
			}
		}
	}

	protected void updateLayoutSetThemeId(JSONObject sitemapJSONObject)
		throws Exception {

		String themeId = sitemapJSONObject.getString("themeId");

		if (Validator.isNotNull(themeId)) {
			Theme theme = ThemeLocalServiceUtil.fetchTheme(companyId, themeId);

			if (theme == null) {
				themeId = null;
			}
		}

		if (Validator.isNull(themeId)) {
			int pos = servletContextName.indexOf("-theme");

			if (pos != -1) {
				themeId =
					servletContextName.substring(0, pos) +
						PortletConstants.WAR_SEPARATOR + servletContextName;

				themeId = PortalUtil.getJsSafePortletId(themeId);

				Theme theme = ThemeLocalServiceUtil.fetchTheme(
					companyId, themeId);

				if (theme == null) {
					themeId = null;
				}
			}
		}

		if (Validator.isNotNull(themeId)) {
			LayoutSetLocalServiceUtil.updateLookAndFeel(
				groupId, themeId, null, null, false);
		}
	}

	protected ServiceContext serviceContext;

	private static final String _APPLICATION_DISPLAY_TEMPLATE_DIR_NAME =
		"/templates/application_display";

	private static final Object[][] _APPLICATION_DISPLAY_TEMPLATE_TYPES =
		new Object[][] {
			{"asset_category", AssetCategory.class},
			{"asset_entry", AssetEntry.class}, {"asset_tag", AssetTag.class},
			{"blogs_entry", BlogsEntry.class},
			{"document_library", FileEntry.class},
			{"site_map", LayoutSet.class},
			{"wiki_page", WikiPage.class}
		};

	private static final String _DDL_STRUCTURE_DIR_NAME =
		"/templates/dynamic_data_list/structure";

	private static final String _DDL_STRUCTURE_DISPLAY_TEMPLATE_DIR_NAME =
		"/templates/dynamic_data_list/display_template";

	private static final String _DDL_STRUCTURE_FORM_TEMPLATE_DIR_NAME =
		"/templates/dynamic_data_list/form_template";

	private static final String _DL_DOCUMENTS_DIR_NAME =
		"/document_library/documents/";

	private static final String _JOURNAL_ARTICLES_DIR_NAME =
		"/journal/articles/";

	private static final String _JOURNAL_CONTENT_PORTLET_ID =
		"com_liferay_journal_content_web_portlet_JournalContentPortlet";

	private static final String _JOURNAL_DDM_STRUCTURES_DIR_NAME =
		"/journal/structures/";

	private static final String _JOURNAL_DDM_TEMPLATES_DIR_NAME =
		"/journal/templates/";

	private static final String _LAYOUT_PROTOTYPE_DIR_NAME = "/templates/page";

	private static Log _log = LogFactoryUtil.getLog(FileSystemImporter.class);

	private Map<String, JSONObject> _assetJSONObjectMap = new HashMap<>();
	private Set<String> _ddmStructures = new HashSet<>();
	private String _defaultLayoutTemplateId;
	private Map<String, FileEntry> _fileEntries = new HashMap<>();
	private Pattern _fileEntryPattern = Pattern.compile(
		"\\[\\$FILE=([^\\$]+)\\$\\]");
	private Map<String, Set<Long>> _primaryKeys = new HashMap<>();
	private File _resourcesDir;

}