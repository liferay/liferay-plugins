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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.journal.service.JournalStructureLocalServiceUtil;
import com.liferay.portlet.journal.service.JournalTemplateLocalServiceUtil;

import java.io.InputStream;

/**
 * @author Ryan Park
 */
public class UpgradeJournal extends UpgradeProcess {

	protected void addJournalStructures(long groupId, long userId)
		throws Exception {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddCommunityPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		// Article

		String structureId = "ARTICLE";
		String name = "Article";
		String description = "This structure accommodates article title, " +
			"both main, and preview images, and the main article body.";
		String xsd = getFileAsString("/structures/article.xml");

		JournalStructureLocalServiceUtil.addStructure(
			userId, groupId, structureId, false, StringPool.BLANK, name,
			description, xsd, serviceContext);

		// Carousel

		structureId = "MULTIPLE-ITEM-CAROUSEL";
		name = "Carousel";
		description = "This is a simple carousel structure designed to " +
			"handle other necessary carousel configurations.";
		xsd = getFileAsString(
			"/structures/multiple_item_carousel.xml");

		JournalStructureLocalServiceUtil.addStructure(
			userId, groupId, structureId, false, StringPool.BLANK, name,
			description, xsd, serviceContext);

		// Multiple Item

		structureId = "MULTIPLE-ITEM";
		name = "Multiple Item";
		description = "This is a simple structure with a single repeatable " +
			"element that includes an HTML field, and text-box for a title " +
				"and url designation.";
		xsd = getFileAsString("/structures/multiple_item.xml");

		JournalStructureLocalServiceUtil.addStructure(
			userId, groupId, structureId, false, StringPool.BLANK, name,
			description, xsd, serviceContext);
	}

	protected void addJournalTemplates(long groupId, long userId)
		throws Exception {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddCommunityPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		// Regular Article Description

		String templateId = "ARTICLE-DESCRIPTION";
		String structureId = "ARTICLE";
		String name = "Regular Article Description";
		String description = "This template only displays brief descriptions " +
			"of web content.";
		String type = "vm";
		String xsl = getFileAsString(
			"/templates/article_description.vm");

		JournalTemplateLocalServiceUtil.addTemplate(
			userId, groupId, templateId, false, structureId, name,
			description, xsl, true, type, true, false, StringPool.BLANK, null,
			serviceContext);

		// Regular Article

		templateId = "REGULAR-ARTICLE";
		name = "Regular Article";
		description = "This is the regular article template, it handles " +
			"basic article content like, titles, main image, body, and " +
				"author information.";
		xsl = getFileAsString("/templates/regular_article.vm");

		JournalTemplateLocalServiceUtil.addTemplate(
			userId, groupId, templateId, false, structureId, name,
			description, xsl, true, type, true, false, StringPool.BLANK, null,
			serviceContext);

		// Carousel

		templateId = "MULTIPLE-ITEM-CAROUSEL";
		structureId = "MULTIPLE-ITEM-CAROUSEL";
		name = "Carousel";
		description = "This is the carousel template that utilizes Alloy UI " +
			"to display repeatable content as a slideshow.";
		xsl = getFileAsString("/templates/multiple_item_carousel.vm");

		JournalTemplateLocalServiceUtil.addTemplate(
			userId, groupId, templateId, false, structureId, name,
			description, xsl, true, type, true, false, StringPool.BLANK, null,
			serviceContext);

		// Featured Items

		templateId = "MULTIPLE-ITEM-FEATURE";
		structureId = "MULTIPLE-ITEM";
		name = "Featured Items";
		description = "This is a template that utilizes the Multiple Item " +
			"Structure, and displays the data as Featured Items.";
		xsl = getFileAsString("/templates/multiple_item_feature.vm");

		JournalTemplateLocalServiceUtil.addTemplate(
			userId, groupId, templateId, false, structureId, name,
			description, xsl, true, type, true, false, StringPool.BLANK, null,
			serviceContext);
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