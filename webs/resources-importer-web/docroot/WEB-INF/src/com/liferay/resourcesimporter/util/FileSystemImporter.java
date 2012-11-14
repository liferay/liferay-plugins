/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.LayoutSetPrototype;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.LayoutTypePortletConstants;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portal.model.Theme;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.service.LayoutSetPrototypeLocalServiceUtil;
import com.liferay.portal.service.RepositoryLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ThemeLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.asset.model.AssetTag;
import com.liferay.portlet.asset.service.AssetTagLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.documentlibrary.util.DLUtil;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.model.JournalArticleConstants;
import com.liferay.portlet.journal.model.JournalStructure;
import com.liferay.portlet.journal.model.JournalTemplate;
import com.liferay.portlet.journal.model.JournalTemplateConstants;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portlet.journal.service.JournalStructureLocalServiceUtil;
import com.liferay.portlet.journal.service.JournalTemplateLocalServiceUtil;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.PortletPreferences;

/**
 * @author Ryan Park
 * @author Raymond Augé
 */
public class FileSystemImporter extends BaseImporter {

	public void importResources() throws Exception {
		_resourcesDir = new File(resourcesDir);

		if (!_resourcesDir.isDirectory() || !_resourcesDir.canRead()) {
			throw new IllegalArgumentException(
				"Unaccessible resource directory " + resourcesDir);
		}

		doImportResources();
	}

	protected void addDLFileEntries(String fileEntriesDirName)
		throws Exception {

		File dlDocumentsDir = new File(_resourcesDir, fileEntriesDirName);

		if (!dlDocumentsDir.isDirectory()|| !dlDocumentsDir.canRead()) {
			return;
		}

		File[] files = listFiles(dlDocumentsDir);

		for (File file : files) {
			InputStream inputStream = null;

			try {
				inputStream = new BufferedInputStream(
					new FileInputStream(file));

				doAddDLFileEntries(file.getName(), inputStream, file.length());
			}
			finally {
				if (inputStream != null) {
					inputStream.close();
				}
			}
		}
	}

	protected void addJournalArticles(
			String journalStructureId, String journalTemplateId,
			String articlesDirName)
		throws Exception {

		File journalArticlesDir = new File(_resourcesDir, articlesDirName);

		if (!journalArticlesDir.isDirectory() ||
			!journalArticlesDir.canRead()) {

			return;
		}

		File[] files = listFiles(journalArticlesDir);

		for (File file : files) {
			InputStream inputStream = null;

			try {
				inputStream = new BufferedInputStream(
					new FileInputStream(file));

				doAddJournalArticles(
					journalStructureId, journalTemplateId, file.getName(),
					inputStream);
			}
			finally {
				if (inputStream != null) {
					inputStream.close();
				}
			}
		}
	}

	protected void addJournalStructures(
			String parentStructureId, String structuresDirName)
		throws Exception {

		File journalStructuresDir = new File(_resourcesDir, structuresDirName);

		if (!journalStructuresDir.isDirectory() ||
			!journalStructuresDir.canRead()) {

			return;
		}

		File[] files = listFiles(journalStructuresDir);

		for (File file : files) {
			InputStream inputStream = null;

			try {
				inputStream = new BufferedInputStream(
					new FileInputStream(file));

				doAddJournalStructures(
					parentStructureId, file.getName(), inputStream);
			}
			finally {
				if (inputStream != null) {
					inputStream.close();
				}
			}
		}
	}

	protected void addJournalTemplates(
			String journalStructureId, String templatesDirName)
		throws Exception {

		File journalTemplatesDir = new File(_resourcesDir, templatesDirName);

		if (!journalTemplatesDir.isDirectory() ||
			!journalTemplatesDir.canRead()) {

			return;
		}

		File[] files = listFiles(journalTemplatesDir);

		for (File file : files) {
			InputStream inputStream = null;

			try {
				inputStream = new BufferedInputStream(
					new FileInputStream(file));

				doAddJournalTemplates(
					journalStructureId, file.getName(), inputStream);
			}
			finally {
				if (inputStream != null) {
					inputStream.close();
				}
			}
		}
	}

	protected void addLayout(long parentLayoutId, JSONObject layoutJSONObject)
		throws Exception {

		String name = layoutJSONObject.getString("name");
		String title = layoutJSONObject.getString("title");
		boolean hidden = layoutJSONObject.getBoolean("hidden");

		String friendlyURL = layoutJSONObject.getString("friendlyURL");

		if (Validator.isNotNull(friendlyURL) &&
			!friendlyURL.startsWith(StringPool.SLASH)) {

			friendlyURL = StringPool.SLASH + friendlyURL;
		}

		Layout layout = LayoutLocalServiceUtil.addLayout(
			userId, groupId, privateLayout, parentLayoutId, name, title,
			StringPool.BLANK, LayoutConstants.TYPE_PORTLET, hidden, friendlyURL,
			serviceContext);

		String typeSettings = layoutJSONObject.getString("typeSettings");

		layout.setTypeSettings(typeSettings);

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		String layoutTemplateId = layoutJSONObject.getString(
			"layoutTemplateId", _defaultLayoutTemplateId);

		if (Validator.isNotNull(layoutTemplateId)) {
			layoutTypePortlet.setLayoutTemplateId(
				userId, layoutTemplateId, false);
		}

		JSONArray columnsJSONArray = layoutJSONObject.getJSONArray("columns");

		addLayoutColumns(
			layout, LayoutTypePortletConstants.COLUMN_PREFIX, columnsJSONArray);

		LayoutLocalServiceUtil.updateLayout(
			groupId, layout.isPrivateLayout(), layout.getLayoutId(),
			layout.getTypeSettings());

		JSONArray layoutsJSONArray = layoutJSONObject.getJSONArray("layouts");

		addLayouts(layout.getLayoutId(), layoutsJSONArray);
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

			if (rootPortletId.equals(PortletKeys.JOURNAL_CONTENT) &&
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

	protected void addLayouts(long parentLayoutId, JSONArray layoutsJSONArray)
		throws Exception {

		if (layoutsJSONArray == null) {
			return;
		}

		for (int i = 0; i < layoutsJSONArray.length(); i++) {
			JSONObject layoutJSONObject = layoutsJSONArray.getJSONObject(i);

			addLayout(parentLayoutId, layoutJSONObject);
		}
	}

	protected void doAddDLFileEntries(
			String fileName, InputStream inputStream, long length)
		throws Exception {

		String mimeType = MimeTypesUtil.getContentType(fileName);

		String title = FileUtil.stripExtension(fileName);

		setServiceContext(fileName);

		FileEntry fileEntry = DLAppLocalServiceUtil.addFileEntry(
			userId, groupId, 0, fileName, mimeType, title, StringPool.BLANK,
			StringPool.BLANK, inputStream, length, serviceContext);

		_fileEntries.put(fileName, fileEntry);
	}

	protected void doAddJournalArticles(
			String journalStructureId, String journalTemplateId,
			String fileName, InputStream inputStream)
		throws Exception {

		String journalArticleId = getJournalId(fileName);

		String title = FileUtil.stripExtension(fileName);

		Map<Locale, String> titleMap = getMap(title);

		JSONObject assetJSONObject = _assetJSONObjectMap.get(fileName);

		Map<Locale, String> descriptionMap = null;

		if (assetJSONObject != null) {
			String abstractSummary = assetJSONObject.getString(
				"abstractSummary");

			descriptionMap = getMap(abstractSummary);
		}

		String content = StringUtil.read(inputStream);

		content = processJournalArticleContent(content);

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

		JournalArticle journalArticle =
			JournalArticleLocalServiceUtil.addArticle(
				userId, groupId, 0, 0, 0, journalArticleId, false,
				JournalArticleConstants.VERSION_DEFAULT, titleMap,
				descriptionMap, content, "general", journalStructureId,
				journalTemplateId, StringPool.BLANK, 1, 1, 2010, 0, 0, 0, 0, 0,
				0, 0, true, 0, 0, 0, 0, 0, true, true, smallImage,
				smallImageURL, null, new HashMap<String, byte[]>(),
				StringPool.BLANK, serviceContext);

		JournalArticleLocalServiceUtil.updateStatus(
			userId, groupId, journalArticle.getArticleId(),
			journalArticle.getVersion(), WorkflowConstants.STATUS_APPROVED,
			StringPool.BLANK, serviceContext);
	}

	protected void doAddJournalStructures(
			String parentStructureId, String fileName, InputStream inputStream)
		throws Exception {

		String journalStructureId = getJournalId(fileName);

		String name = FileUtil.stripExtension(fileName);

		Map<Locale, String> nameMap = getMap(name);

		String xsd = StringUtil.read(inputStream);

		setServiceContext(fileName);

		JournalStructure journalStructure =
			JournalStructureLocalServiceUtil.addStructure(
				userId, groupId, journalStructureId, false, parentStructureId,
				nameMap, null, xsd, serviceContext);

		addJournalTemplates(
			journalStructure.getStructureId(),
			_JOURNAL_TEMPLATES_DIR_NAME + name);

		if (Validator.isNull(parentStructureId)) {
			addJournalStructures(
				journalStructure.getStructureId(),
				_JOURNAL_STRUCTURES_DIR_NAME + name);
		}
	}

	protected void doAddJournalTemplates(
			String journalStructureId, String fileName, InputStream inputStream)
		throws Exception {

		String journalTemplateId = getJournalId(fileName);

		String name = FileUtil.stripExtension(fileName);

		Map<Locale, String> nameMap = getMap(name);

		String xsl = StringUtil.read(inputStream);

		xsl = replaceFileEntryURL(xsl);

		setServiceContext(fileName);

		JournalTemplate journalTemplate =
			JournalTemplateLocalServiceUtil.addTemplate(
				userId, groupId, journalTemplateId, false, journalStructureId,
				nameMap, null, xsl, true, JournalTemplateConstants.LANG_TYPE_VM,
				false, false, StringPool.BLANK, null, serviceContext);

		addJournalArticles(
			journalStructureId, journalTemplate.getTemplateId(),
			_JOURNAL_ARTICLES_DIR_NAME + name);
	}

	protected void doImportResources() throws Exception {
		serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);

		if (!privateLayout) {
			serviceContext.setAddGuestPermissions(true);
		}

		serviceContext.setScopeGroupId(groupId);

		setupAssets("assets.json");
		setupSettings("settings.json");
		setupSitemap("sitemap.json");
	}

	protected JSONObject getDefaultPortletJSONObject(String journalArticleId) {
		JSONObject portletJSONObject = JSONFactoryUtil.createJSONObject();

		portletJSONObject.put("portletId", PortletKeys.JOURNAL_CONTENT);

		JSONObject portletPreferencesJSONObject =
			JSONFactoryUtil.createJSONObject();

		portletPreferencesJSONObject.put("articleId", journalArticleId);
		portletPreferencesJSONObject.put("groupId", groupId);
		portletPreferencesJSONObject.put("portletSetupShowBorders", false);

		portletJSONObject.put(
			"portletPreferences", portletPreferencesJSONObject);

		return portletJSONObject;
	}

	protected InputStream getInputStream(String fileName) throws Exception {
		File file = new File(_resourcesDir, fileName);

		if (!file.exists() || file.isDirectory() || !file.canRead()) {
			return null;
		}

		return new BufferedInputStream(new FileInputStream(file));
	}

	protected String getJournalId(String fileName) {
		String id = FileUtil.stripExtension(fileName);

		id = id.toUpperCase();

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

	protected Map<Locale, String> getMap(String value) {
		Map<Locale, String> map = new HashMap<Locale, String>();

		Locale locale = LocaleUtil.getDefault();

		map.put(locale, value);

		return map;
	}

	protected File[] listFiles(File dir) {
		File[] files = dir.listFiles();

		if (files == null) {
			return new File[0];
		}

		List<File> filesList = new ArrayList<File>();

		for (File file : files) {
			if (file.isFile()) {
				filesList.add(file);
			}
		}

		return filesList.toArray(new File[filesList.size()]);
	}

	protected String processJournalArticleContent(String content)
		throws Exception {

		content = replaceFileEntryURL(content);

		if (content.contains("<?xml version=\"1.0\"")) {
			return content;
		}

		StringBundler sb = new StringBundler(13);

		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<root available-locales=\"");
		sb.append(LocaleUtil.getDefault());
		sb.append("\" default-locale=\"");
		sb.append(LocaleUtil.getDefault());
		sb.append("\">");
		sb.append("<static-content language-id=\"");
		sb.append(LocaleUtil.getDefault());
		sb.append("\">");
		sb.append("<![CDATA[");
		sb.append(content);
		sb.append("]]>");
		sb.append("</static-content></root>");

		return sb.toString();
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

		if (assetJSONObject == null) {
			return;
		}

		String[] assetTagNames = getJSONArrayAsStringArray(
			assetJSONObject, "tags");

		serviceContext.setAssetTagNames(assetTagNames);
	}

	protected void setupAssets(JSONArray assetsJSONArray) {
		if (assetsJSONArray == null) {
			return;
		}

		for (int i = 0; i < assetsJSONArray.length(); i++) {
			JSONObject assetJSONObject = assetsJSONArray.getJSONObject(i);

			String name = assetJSONObject.getString("name");

			_assetJSONObjectMap.put(name, assetJSONObject);
		}
	}

	protected void setupAssets(String fileName) throws Exception {
		List<AssetTag> assetTags = AssetTagLocalServiceUtil.getGroupTags(
			groupId);

		for (AssetTag assetTag : assetTags) {
			AssetTagLocalServiceUtil.deleteAssetTag(assetTag);
		}

		RepositoryLocalServiceUtil.deleteRepositories(groupId);

		JournalArticleLocalServiceUtil.deleteArticles(groupId);

		JournalTemplateLocalServiceUtil.deleteTemplates(groupId);

		JournalStructureLocalServiceUtil.deleteStructures(groupId);

		JSONObject jsonObject = getJSONObject(fileName);

		if (jsonObject != null) {
			JSONArray assetsJSONArray = jsonObject.getJSONArray("assets");

			setupAssets(assetsJSONArray);
		}

		addDLFileEntries(_DL_DOCUMENTS_DIR_NAME);

		addJournalArticles(
			StringPool.BLANK, StringPool.BLANK, _JOURNAL_ARTICLES_DIR_NAME);

		addJournalStructures(StringPool.BLANK, _JOURNAL_STRUCTURES_DIR_NAME);

		addJournalTemplates(StringPool.BLANK, _JOURNAL_TEMPLATES_DIR_NAME);
	}

	protected void setupSettings(String fileName) throws Exception {
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

	protected void setupSitemap(String fileName) throws Exception {
		LayoutLocalServiceUtil.deleteLayouts(
			groupId, privateLayout, new ServiceContext());

		JSONObject jsonObject = getJSONObject(fileName);

		if (jsonObject == null) {
			return;
		}

		_defaultLayoutTemplateId = jsonObject.getString(
			"layoutTemplateId", StringPool.BLANK);

		updateLayoutSetThemeId(jsonObject);

		JSONArray layoutsJSONArray = jsonObject.getJSONArray("layouts");

		addLayouts(LayoutConstants.DEFAULT_PARENT_LAYOUT_ID, layoutsJSONArray);
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
				groupId, privateLayout, themeId, null, null, false);
		}
	}

	protected ServiceContext serviceContext;

	private static final String _DL_DOCUMENTS_DIR_NAME =
		"/document_library/documents/";

	private static final String _JOURNAL_ARTICLES_DIR_NAME =
		"/journal/articles/";

	private static final String _JOURNAL_STRUCTURES_DIR_NAME =
		"/journal/structures/";

	private static final String _JOURNAL_TEMPLATES_DIR_NAME =
		"/journal/templates/";

	private Map<String, JSONObject> _assetJSONObjectMap =
		new HashMap<String, JSONObject>();
	private String _defaultLayoutTemplateId;
	private Map<String, FileEntry> _fileEntries =
		new HashMap<String, FileEntry>();
	private Pattern _fileEntryPattern = Pattern.compile(
		"\\[\\$FILE=([^\\$]+)\\$\\]");
	private File _resourcesDir;

}