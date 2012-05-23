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
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portal.model.Theme;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ThemeLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
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

import java.io.File;

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
 */
public class FileSystemImporter extends BaseImporter {

	public void importResources() throws Exception {
		File dlDocumentsDir = new File(
			resourcesDir, "/document_library/documents");

		if (dlDocumentsDir.exists() && dlDocumentsDir.isDirectory()) {
			addDLFileEntries(dlDocumentsDir);
		}

		File journalArticlesDir = new File(resourcesDir, "/journal/articles");

		if (journalArticlesDir.exists() && journalArticlesDir.isDirectory()) {
			addJournalArticles(
			StringPool.BLANK, StringPool.BLANK, journalArticlesDir);
		}

		File journalStructuresDir = new File(
			resourcesDir, "/journal/structures");

		if (journalStructuresDir.exists() &&
			journalStructuresDir.isDirectory()) {

			addJournalStructures(journalStructuresDir);
		}

		File journalTemplatesDir = new File(resourcesDir, "/journal/templates");

		if (journalTemplatesDir.exists() && journalTemplatesDir.isDirectory()) {
			addJournalTemplates(StringPool.BLANK, journalTemplatesDir);
		}

		File sitemapJSONFile = new File(resourcesDir, "sitemap.json");

		if (sitemapJSONFile.exists()) {
			addLayouts(journalArticlesDir, sitemapJSONFile);
		}
	}

	protected void addDLFileEntries(File dir) throws Exception {
		File[] files = listFiles(dir);

		for (File file : files) {
			String mimeType = MimeTypesUtil.getContentType(file);

			byte[] bytes = FileUtil.getBytes(file);

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setScopeGroupId(groupId);

			FileEntry fileEntry = DLAppLocalServiceUtil.addFileEntry(
				userId, groupId, 0, file.getName(), mimeType, file.getName(),
				StringPool.BLANK, StringPool.BLANK, bytes, serviceContext);

			_fileEntries.put(file.getName(), fileEntry);
		}
	}

	protected void addJournalArticles(
			String journalStructureId, String journalTemplateId, File dir)
		throws Exception {

		File[] files = listFiles(dir);

		for (File file : files) {
			String journalArticleId = getJournalArticleId(file.getName());

			String title = getName(file.getName());

			Map<Locale, String> titleMap = getNameMap(title);

			String content = new String(FileUtil.getBytes(file));

			content = processJournalArticleContent(content);

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setScopeGroupId(groupId);

			JournalArticle journalArticle =
				JournalArticleLocalServiceUtil.addArticle(
					userId, groupId, 0, 0, 0, journalArticleId, false,
					JournalArticleConstants.VERSION_DEFAULT, titleMap, null,
					content, "general", journalStructureId, journalTemplateId,
					StringPool.BLANK, 1, 1, 2010, 0, 0, 0, 0, 0, 0, 0, true, 0,
					0, 0, 0, 0, true, true, false, StringPool.BLANK, null,
					new HashMap<String, byte[]>(), StringPool.BLANK,
					serviceContext);

			JournalArticleLocalServiceUtil.updateStatus(
				userId, groupId, journalArticle.getArticleId(),
				journalArticle.getVersion(), WorkflowConstants.STATUS_APPROVED,
				StringPool.BLANK, serviceContext);
		}
	}

	protected void addJournalStructures(File dir) throws Exception {
		File[] files = listFiles(dir);

		for (File file : files) {
			String name = getName(file.getName());

			Map<Locale, String> nameMap = getNameMap(name);

			String xsd = new String(FileUtil.getBytes(file));

			JournalStructure journalStructure =
				JournalStructureLocalServiceUtil.addStructure(
					userId, groupId, StringPool.BLANK, true, StringPool.BLANK,
					nameMap, null, xsd, new ServiceContext());

			File resourcesDir = getResourcesDir(dir);

			File journalTemplatesDir = new File(
				resourcesDir, "/journal/templates/" + name);

			if (journalTemplatesDir.exists() &&
				journalTemplatesDir.isDirectory()) {

				addJournalTemplates(
					journalStructure.getStructureId(), journalTemplatesDir);
			}
		}
	}

	protected void addJournalTemplates(String journalStructureId, File dir)
		throws Exception {

		File[] files = listFiles(dir);

		for (File file : files) {
			String name = getName(file.getName());

			Map<Locale, String> nameMap = getNameMap(name);

			String xsl = new String(FileUtil.getBytes(file));

			JournalTemplate journalTemplate =
				JournalTemplateLocalServiceUtil.addTemplate(
					userId, groupId, StringPool.BLANK, true, journalStructureId,
					nameMap, null, xsl, true,
					JournalTemplateConstants.LANG_TYPE_VM, false, false,
					StringPool.BLANK, null, new ServiceContext());

			File resourcesDir = getResourcesDir(dir);

			File journalArticlesDir = new File(
				resourcesDir, "/journal/articles/" + name);

			if (journalArticlesDir.exists() &&
				journalArticlesDir.isDirectory()) {

				addJournalArticles(
					journalStructureId, journalTemplate.getTemplateId(),
					journalArticlesDir);
			}
		}
	}

	protected void addLayout(long parentLayoutId, JSONObject layoutJSONObject)
		throws Exception {

		String name = layoutJSONObject.getString("name");
		String title = layoutJSONObject.getString("title");

		String friendlyURL = layoutJSONObject.getString("friendlyURL");

		if (Validator.isNotNull(friendlyURL) &&
			!friendlyURL.startsWith(StringPool.SLASH)) {

			friendlyURL = StringPool.SLASH + friendlyURL;
		}

		Layout layout = LayoutLocalServiceUtil.addLayout(
			userId, groupId, true, parentLayoutId, name, title,
			StringPool.BLANK, LayoutConstants.TYPE_PORTLET, false, friendlyURL,
			new ServiceContext());

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		String layoutTemplateId = layoutJSONObject.getString(
			"layoutTemplateId", _defaultLayoutTemplateId);

		if (Validator.isNotNull(layoutTemplateId)) {
			layoutTypePortlet.setLayoutTemplateId(
				userId, layoutTemplateId, false);
		}

		JSONArray columnsJSONArray = layoutJSONObject.getJSONArray("columns");

		addLayoutColumns(layout, columnsJSONArray);

		LayoutLocalServiceUtil.updateLayout(
			groupId, layout.isPrivateLayout(), layout.getLayoutId(),
			layout.getTypeSettings());

		JSONArray layoutsJSONArray = layoutJSONObject.getJSONArray("layouts");

		addLayouts(layout.getLayoutId(), layoutsJSONArray);
	}

	protected void addLayoutColumn(
			Layout layout, String columnId, JSONArray columnJSONArray)
		throws Exception {

		for (int i = 0; i < columnJSONArray.length(); i++) {
			JSONObject portletJSONObject = columnJSONArray.getJSONObject(i);

			if (portletJSONObject == null) {
				String articleId = getJournalArticleId(
					columnJSONArray.getString(i));

				portletJSONObject = getDefaultPortletJSONObject(articleId);
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

				value = getJournalArticleId(value);
			}

			portletSetup.setValue(key, value);
		}

		portletSetup.store();
	}

	protected void addLayoutColumns(Layout layout, JSONArray columnsJSONArray)
		throws Exception {

		for (int i = 0; i < columnsJSONArray.length(); i++) {
			JSONArray columnJSONArray = columnsJSONArray.getJSONArray(i);

			addLayoutColumn(layout, "column-" + (i + 1), columnJSONArray);
		}
	}

	protected void addLayouts(File journalArticlesDir, File sitemapJSONFile)
		throws Exception {

		String sitemapJSON = getSitemapJSON(sitemapJSONFile);

		JSONObject sitemapJSONObject = JSONFactoryUtil.createJSONObject(
			sitemapJSON);

		LayoutLocalServiceUtil.deleteLayouts(
			groupId, true, new ServiceContext());

		_defaultLayoutTemplateId = sitemapJSONObject.getString(
			"layoutTemplateId", StringPool.BLANK);

		updateLayoutSetThemeId(sitemapJSONObject);

		JSONArray layoutsJSONArray = sitemapJSONObject.getJSONArray("layouts");

		addLayouts(LayoutConstants.DEFAULT_PARENT_LAYOUT_ID, layoutsJSONArray);
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

	protected JSONObject getDefaultPortletJSONObject(String articleId) {
		JSONObject portletJSONObject = JSONFactoryUtil.createJSONObject();

		portletJSONObject.put("portletId", PortletKeys.JOURNAL_CONTENT);

		JSONObject portletPreferencesJSONObject =
			JSONFactoryUtil.createJSONObject();

		portletPreferencesJSONObject.put("articleId", articleId);
		portletPreferencesJSONObject.put("groupId", groupId);

		portletJSONObject.put(
			"portletPreferences", portletPreferencesJSONObject);

		return portletJSONObject;
	}

	protected String getJournalArticleId(String fileName) {
		String journalArticleId = getName(fileName);

		journalArticleId = journalArticleId.toUpperCase();

		return StringUtil.replace(
			journalArticleId, StringPool.SPACE, StringPool.DASH);
	}

	protected String getName(String fileName) {
		int x = fileName.lastIndexOf(StringPool.SLASH);

		if (x < 0) {
			x = 0;
		}

		int y = fileName.lastIndexOf(StringPool.PERIOD);

		if (y < 0) {
			y = fileName.length();
		}

		return fileName.substring(x, y);
	}

	protected Map<Locale, String> getNameMap(String name) {
		Map<Locale, String> nameMap = new HashMap<Locale, String>();

		Locale locale = LocaleUtil.getDefault();

		nameMap.put(locale, name);

		return nameMap;
	}

	protected File getResourcesDir(File file) {
		File resourceDir = file;

		while (resourceDir != null) {
			resourceDir = resourceDir.getParentFile();

			String name = resourceDir.getName();

			if (resourceDir.isDirectory() &&
				name.equals("resources-importer")) {

				return resourceDir;
			}
		}

		return null;
	}

	protected String getSitemapJSON(File sitemapJSONFile) throws Exception {
		String sitemapJSON = FileUtil.read(sitemapJSONFile);

		return StringUtil.replace(
			sitemapJSON,
			new String[] {"${companyId}", "${groupId}", "${groupId}"},
			new String[] {
				String.valueOf(companyId), String.valueOf(groupId),
				String.valueOf(userId)
			});
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
				groupId, true, themeId, null, null, false);
		}
	}

	private String _defaultLayoutTemplateId;
	private Map<String, FileEntry> _fileEntries =
		new HashMap<String, FileEntry>();
	private Pattern _fileEntryPattern = Pattern.compile(
		"\\[\\$FILE=([^\\$]+)\\$\\]");

}