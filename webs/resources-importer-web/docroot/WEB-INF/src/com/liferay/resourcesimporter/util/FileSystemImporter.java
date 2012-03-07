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

import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.LayoutSetPrototype;
import com.liferay.portal.model.User;
import com.liferay.portal.service.LayoutSetPrototypeLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
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
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Ryan Park
 */
public class FileSystemImporter {

	public void importResources(
			long companyId, Map<Locale, String> layoutSetPrototypeNameMap,
			File resourcesDir)
		throws Exception {

		User user = UserLocalServiceUtil.getDefaultUser(companyId);

		LayoutSetPrototype layoutSetPrototype =
			LayoutSetPrototypeLocalServiceUtil.addLayoutSetPrototype(
				user.getUserId(), companyId, layoutSetPrototypeNameMap,
				StringPool.BLANK, true, true, new ServiceContext());

		Group group = layoutSetPrototype.getGroup();

		File dlDocumentsDir = new File(
			resourcesDir, "/document_library/documents");

		if (dlDocumentsDir.exists() && dlDocumentsDir.isDirectory()) {
			createDLFileEntries(
				user.getUserId(), group.getGroupId(), dlDocumentsDir);
		}

		File journalArticlesDir = new File(resourcesDir, "/journal/articles");

		if (journalArticlesDir.exists() && journalArticlesDir.isDirectory()) {
			createJournalArticles(
				user.getUserId(), group.getGroupId(), StringPool.BLANK,
				StringPool.BLANK, journalArticlesDir);
		}

		File journalStructuresDir = new File(
			resourcesDir, "/journal/structures");

		if (journalStructuresDir.exists() &&
			journalStructuresDir.isDirectory()) {

			createJournalStructures(
				user.getUserId(), group.getGroupId(), journalStructuresDir);
		}

		File journalTemplatesDir = new File(resourcesDir, "/journal/templates");

		if (journalTemplatesDir.exists() && journalTemplatesDir.isDirectory()) {
			createJournalTemplates(
				user.getUserId(), group.getGroupId(), StringPool.BLANK,
				journalTemplatesDir);
		}
	}

	protected void createDLFileEntries(long userId, long groupId, File dir)
		throws Exception {
	}

	protected void createJournalArticles(
			long userId, long groupId, String journalStructureId,
			String journalTemplateId, File dir)
		throws Exception {

		File[] files = listFiles(dir);

		for (File file : files) {
			String title = getName(file.getName());

			Map<Locale, String> titleMap = getNameMap(title);

			String content = new String(FileUtil.getBytes(file));

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setScopeGroupId(groupId);

			JournalArticle journalArticle =
				JournalArticleLocalServiceUtil.addArticle(
					userId, groupId, 0, 0, StringPool.BLANK, true,
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

	protected void createJournalStructures(long userId, long groupId, File dir)
		throws Exception {

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

				createJournalTemplates(
					userId, groupId, journalStructure.getStructureId(),
					journalTemplatesDir);
			}
		}
	}

	protected void createJournalTemplates(
			long userId, long groupId, String journalStructureId, File dir)
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

				createJournalArticles(
					userId, groupId, journalStructureId,
					journalTemplate.getTemplateId(), journalArticlesDir);
			}
		}
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

}