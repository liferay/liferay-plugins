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

package com.liferay.themeresourceimporter.importer;

import com.liferay.portal.kernel.util.FileUtil;
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

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Ryan Park
 */
public class FileImporter {

	public static void importResource(
			long companyId, String name, File resourceDir)
		throws Exception {

		Map<Locale, String> nameMap = new HashMap<Locale, String>();

		nameMap.put(Locale.US, name);

		User user = UserLocalServiceUtil.getDefaultUser(companyId);

		LayoutSetPrototype layoutSetPrototype =
			LayoutSetPrototypeLocalServiceUtil.addLayoutSetPrototype(
				user.getUserId(), companyId, nameMap, StringPool.BLANK, true,
				true, new ServiceContext());

		Group group = layoutSetPrototype.getGroup();

		File structuresDir = new File(resourceDir, "/structures");

		if (structuresDir.exists() && structuresDir.isDirectory()) {
			createStructures(
				user.getUserId(), group.getGroupId(), structuresDir);
		}

		File templatesDir = new File(resourceDir, "/templates");

		if (templatesDir.exists() && templatesDir.isDirectory()) {
			createTemplates(
				user.getUserId(), group.getGroupId(), StringPool.BLANK,
				templatesDir);
		}

		File articlesDir = new File(resourceDir, "/articles");

		if (articlesDir.exists() && articlesDir.isDirectory()) {
			createArticles(
				user.getUserId(), group.getGroupId(), StringPool.BLANK,
				StringPool.BLANK, articlesDir);
		}

		File documentsDir = new File(resourceDir, "/documents");

		if (documentsDir.exists() && documentsDir.isDirectory()) {
			createDocuments(user.getUserId(), group.getGroupId(), documentsDir);
		}
	}

	protected static void createArticles(
			long userId, long groupId, String journalStructureId,
			String journalTemplateId, File dir)
		throws Exception {

		File[] files = dir.listFiles();

		if (files == null) {
			return;
		}

		for (File file : files) {
			if (file.isDirectory()) {
				continue;
			}

			String name = getName(file.getName());

			Map<Locale, String> titleMap = new HashMap<Locale, String>();

			titleMap.put(Locale.US, name);

			String content = new String(FileUtil.getBytes(file));

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setScopeGroupId(groupId);

			Map<String, byte[]> images = new HashMap<String, byte[]>();

			JournalArticle journalArticle =
				JournalArticleLocalServiceUtil.addArticle(
					userId, groupId, 0, 0, StringPool.BLANK, true,
					JournalArticleConstants.VERSION_DEFAULT, titleMap, null,
					content, "general", journalStructureId, journalTemplateId,
					StringPool.BLANK, 1, 1, 2010, 0, 0, 0, 0, 0, 0, 0, true, 0,
					0, 0, 0, 0, true, true, false, StringPool.BLANK, null,
					images, StringPool.BLANK, serviceContext);

			JournalArticleLocalServiceUtil.updateStatus(
				userId, groupId, journalArticle.getArticleId(),
				journalArticle.getVersion(), WorkflowConstants.STATUS_APPROVED,
				StringPool.BLANK, serviceContext);
		}
	}

	protected static void createDocuments(long userId, long groupId, File dir)
		throws Exception {
	}

	protected static void createStructures(long userId, long groupId, File dir)
		throws Exception {

		File[] files = dir.listFiles();

		if (files == null) {
			return;
		}

		for (File file : files) {
			if (file.isDirectory()) {
				continue;
			}

			String name = getName(file.getName());

			Map<Locale, String> nameMap = new HashMap<Locale, String>();

			nameMap.put(Locale.US, name);

			String xsd = new String(FileUtil.getBytes(file));

			JournalStructure journalStructure =
				JournalStructureLocalServiceUtil.addStructure(
					userId, groupId, StringPool.BLANK, true, StringPool.BLANK,
					nameMap, null, xsd, new ServiceContext());

			File resourceDir = getResourceDir(dir);

			File templatesDir = new File(resourceDir, "/templates/" + name);

			if (templatesDir.exists() && templatesDir.isDirectory()) {
				createTemplates(
					userId, groupId, journalStructure.getStructureId(),
					templatesDir);
			}
		}
	}

	protected static void createTemplates(
			long userId, long groupId, String journalStructureId, File dir)
		throws Exception {

		File[] files = dir.listFiles();

		if (files == null) {
			return;
		}

		for (File file : files) {
			if (file.isDirectory()) {
				continue;
			}

			String name = getName(file.getName());

			Map<Locale, String> nameMap = new HashMap<Locale, String>();

			nameMap.put(Locale.US, name);

			String xsl = new String(FileUtil.getBytes(file));

			JournalTemplate journalTemplate =
				JournalTemplateLocalServiceUtil.addTemplate(
					userId, groupId, StringPool.BLANK, true, journalStructureId,
					nameMap, null, xsl, true,
					JournalTemplateConstants.LANG_TYPE_VM, false, false,
					StringPool.BLANK, null, new ServiceContext());

			File resourceDir = getResourceDir(dir);

			File articlesDir = new File(resourceDir, "/articles/" + name);

			if (articlesDir.exists() && articlesDir.isDirectory()) {
				createArticles(
					userId, groupId, journalStructureId,
					journalTemplate.getTemplateId(), articlesDir);
			}
		}
	}

	protected static String getName(String fileName) {
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

	protected static File getResourceDir(File file) {
		File resourceDir = file;

		while (resourceDir != null) {
			resourceDir = resourceDir.getParentFile();

			String name = resourceDir.getName();

			if (resourceDir.isDirectory() && name.equals("resources")) {
				return resourceDir;
			}
		}

		return null;
	}

}