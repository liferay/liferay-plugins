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

		// Article

		Map<Locale, String> nameMap = new HashMap<Locale, String>();

		nameMap.put(LocaleUtil.getDefault(), "Article");

		Map<Locale, String> descriptionMap = new HashMap<Locale, String>();

		descriptionMap.put(
			LocaleUtil.getDefault(),
			"This structure accommodates article title, both main, and " +
				"preview images, and the main article body.");

		String xsd = getFileAsString("/structures/article.xml");

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddCommunityPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		JournalStructureLocalServiceUtil.addStructure(
			userId, groupId, "ARTICLE", false, StringPool.BLANK, nameMap,
			descriptionMap, xsd, serviceContext);

		// Carousel

		nameMap.put(LocaleUtil.getDefault(), "Carousel");

		descriptionMap.put(
			LocaleUtil.getDefault(),
			"This is a simple carousel structure designed to handle other " +
				"necessary carousel configurations.");

		xsd = getFileAsString("/structures/multiple_item_carousel.xml");

		JournalStructureLocalServiceUtil.addStructure(
			userId, groupId, "MULTIPLE-ITEM-CAROUSEL", false, StringPool.BLANK,
			nameMap, descriptionMap, xsd, serviceContext);

		// Multiple Item

		nameMap.put(LocaleUtil.getDefault(), "Multiple Item");

		descriptionMap.put(
			LocaleUtil.getDefault(),
			"This is a simple structure with a single repeatable element " +
				"that includes an HTML field, and text-box for a title and " +
					"URL designation.");

		xsd = getFileAsString("/structures/multiple_item.xml");

		JournalStructureLocalServiceUtil.addStructure(
			userId, groupId, "MULTIPLE-ITEM", false, StringPool.BLANK, nameMap,
			descriptionMap, xsd, serviceContext);
	}

	protected void addJournalTemplates(long groupId, long userId)
		throws Exception {

		// Regular Article Description

		Map<Locale, String> nameMap = new HashMap<Locale, String>();

		nameMap.put(LocaleUtil.getDefault(), "Regular Article Description");

		Map<Locale, String> descriptionMap = new HashMap<Locale, String>();

		descriptionMap.put(
			LocaleUtil.getDefault(),
			"This template only displays brief descriptions of web content");

		String xsl = getFileAsString("/templates/article_description.vm");

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddCommunityPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		JournalTemplateLocalServiceUtil.addTemplate(
			userId, groupId, "ARTICLE-DESCRIPTION", false, "ARTICLE", nameMap,
			descriptionMap, xsl, true, "vm", true, false, StringPool.BLANK,
			null, serviceContext);

		// Regular Article

		nameMap.put(LocaleUtil.getDefault(), "Regular Article");

		descriptionMap.put(
			LocaleUtil.getDefault(),
			"This is the regular article template, it handles basic article " +
				"content like, titles, main image, body, and author " +
					"information.");

		xsl = getFileAsString("/templates/regular_article.vm");

		JournalTemplateLocalServiceUtil.addTemplate(
			userId, groupId, "REGULAR-ARTICLE", false, "ARTICLE", nameMap,
			descriptionMap, xsl, true, "vm", true, false, StringPool.BLANK,
			null, serviceContext);

		// Carousel

		nameMap.put(LocaleUtil.getDefault(), "Carousel");

		descriptionMap.put(
			LocaleUtil.getDefault(),
			"This is the carousel template that utilizes Alloy UI to display " +
				"repeatable content as a slideshow.");

		xsl = getFileAsString("/templates/multiple_item_carousel.vm");

		JournalTemplateLocalServiceUtil.addTemplate(
			userId, groupId, "MULTIPLE-ITEM-CAROUSEL", false,
			"MULTIPLE-ITEM-CAROUSEL", nameMap, descriptionMap, xsl, true, "vm",
			true, false, StringPool.BLANK, null, serviceContext);

		// Featured Items

		nameMap.put(LocaleUtil.getDefault(), "Featured Items");

		descriptionMap.put(
			LocaleUtil.getDefault(),
			"This is a template that utilizes the Multiple Item Structure, " +
				"and displays the data as Featured Items.");

		xsl = getFileAsString("/templates/multiple_item_feature.vm");

		JournalTemplateLocalServiceUtil.addTemplate(
			userId, groupId, "MULTIPLE-ITEM-FEATURE", false, "MULTIPLE-ITEM",
			nameMap, descriptionMap, xsl, true, "vm", true, false,
			StringPool.BLANK, null, serviceContext);
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