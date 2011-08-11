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

package com.liferay.defaultwebcontent.hook.upgrade.v1_0_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.journal.service.JournalStructureLocalServiceUtil;
import com.liferay.portlet.journal.service.JournalTemplateLocalServiceUtil;

import java.io.InputStream;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Ryan Park
 */
public class UpgradeJournal extends UpgradeProcess {

	protected void addJournalStructures(long groupId, long userId)
		throws Exception {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddCommunityPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		Map<Locale, String> descriptionMap = new HashMap<Locale, String>();
		Map<Locale, String> nameMap = new HashMap<Locale, String>();

		// Article

		String structureId = "ARTICLE";

		nameMap.put(LocaleUtil.getDefault(), "Article");
		descriptionMap.put(
			LocaleUtil.getDefault(),
			"This structure accommodates article title, both main, and " +
				"preview images, and the main article body.");

		String xsd = getFileAsString("/structures/article.xml");

		JournalStructureLocalServiceUtil.addStructure(
			userId, groupId, structureId, false, StringPool.BLANK, nameMap,
			descriptionMap, xsd, serviceContext);

		// Carousel

		nameMap.clear();
		descriptionMap.clear();

		structureId = "MULTIPLE-ITEM-CAROUSEL";

		nameMap.put(LocaleUtil.getDefault(), "Carousel");
		descriptionMap.put(
			LocaleUtil.getDefault(),
			"This is a simple carousel structure designed to handle other " +
				"necessary carousel configurations.");

		xsd = getFileAsString(
			"/structures/multiple_item_carousel.xml");

		JournalStructureLocalServiceUtil.addStructure(
			userId, groupId, structureId, false, StringPool.BLANK, nameMap,
			descriptionMap, xsd, serviceContext);

		// Multiple Item

		nameMap.clear();
		descriptionMap.clear();

		structureId = "MULTIPLE-ITEM";

		nameMap.put(LocaleUtil.getDefault(), "Multiple Item");
		descriptionMap.put(
			LocaleUtil.getDefault(),
			"This is a simple structure with a single repeatable element " +
				"that includes an HTML field, and text-box for a title and " +
					"url designation.");

		xsd = getFileAsString("/structures/multiple_item.xml");

		JournalStructureLocalServiceUtil.addStructure(
			userId, groupId, structureId, false, StringPool.BLANK, nameMap,
			descriptionMap, xsd, serviceContext);
	}

	protected void addJournalTemplates(long groupId, long userId)
		throws Exception {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddCommunityPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		Map<Locale, String> descriptionMap = new HashMap<Locale, String>();
		Map<Locale, String> nameMap = new HashMap<Locale, String>();

		// Regular Article Description

		String templateId = "ARTICLE-DESCRIPTION";
		String structureId = "ARTICLE";

		nameMap.put(LocaleUtil.getDefault(), "Regular Article Description");
		descriptionMap.put(
			LocaleUtil.getDefault(),
			"This template only displays brief descriptions of web content");

		String type = "vm";
		String xsl = getFileAsString(
			"/templates/article_description.vm");

		JournalTemplateLocalServiceUtil.addTemplate(
			userId, groupId, templateId, false, structureId, nameMap,
			descriptionMap, xsl, true, type, true, false, StringPool.BLANK,
			null, serviceContext);

		// Regular Article

		nameMap.clear();
		descriptionMap.clear();

		templateId = "REGULAR-ARTICLE";

		nameMap.put(LocaleUtil.getDefault(), "Regular Article");
		descriptionMap.put(
			LocaleUtil.getDefault(),
			"This is the regular article template, it handles basic article " +
				"content like, titles, main image, body, and author " +
					"information.");

		xsl = getFileAsString("/templates/regular_article.vm");

		JournalTemplateLocalServiceUtil.addTemplate(
			userId, groupId, templateId, false, structureId, nameMap,
			descriptionMap, xsl, true, type, true, false, StringPool.BLANK,
			null, serviceContext);

		// Carousel

		nameMap.clear();
		descriptionMap.clear();

		templateId = "MULTIPLE-ITEM-CAROUSEL";
		structureId = "MULTIPLE-ITEM-CAROUSEL";

		nameMap.put(LocaleUtil.getDefault(), "Carousel");
		descriptionMap.put(
			LocaleUtil.getDefault(),
			"This is the carousel template that utilizes Alloy UI to display " +
				"repeatable content as a slideshow.");

		xsl = getFileAsString("/templates/multiple_item_carousel.vm");

		JournalTemplateLocalServiceUtil.addTemplate(
			userId, groupId, templateId, false, structureId, nameMap,
			descriptionMap, xsl, true, type, true, false, StringPool.BLANK,
			null, serviceContext);

		// Featured Items

		nameMap.clear();
		descriptionMap.clear();

		templateId = "MULTIPLE-ITEM-FEATURE";
		structureId = "MULTIPLE-ITEM";

		nameMap.put(LocaleUtil.getDefault(), "Featured Items");
		descriptionMap.put(
			LocaleUtil.getDefault(),
			"This is a template that utilizes the Multiple Item Structure, " +
				"and displays the data as Featured Items.");

		xsl = getFileAsString("/templates/multiple_item_feature.vm");

		JournalTemplateLocalServiceUtil.addTemplate(
			userId, groupId, templateId, false, structureId, nameMap,
			descriptionMap, xsl, true, type, true, false, StringPool.BLANK,
			null, serviceContext);
	}

	@Override
	protected void doUpgrade() throws Exception {
		long companyId = PortalUtil.getDefaultCompanyId();

		Group group = GroupLocalServiceUtil.getCompanyGroup(companyId);

		long groupId = group.getGroupId();
		long userId = UserLocalServiceUtil.getDefaultUserId(companyId);

		addJournalStructures(groupId, userId);
		addJournalTemplates(groupId, userId);
	}

	protected String getFileAsString(String path) throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();

		InputStream is = classLoader.getResourceAsStream("/resources" + path);

		return new String(FileUtil.getBytes(is));
	}

}