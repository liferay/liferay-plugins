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

package com.liferay.defaultwebcontent.hook.upgrade.v1_0_0;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMStructureConstants;
import com.liferay.dynamic.data.mapping.model.DDMTemplateConstants;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.util.JournalConverterUtil;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.upgrade.util.UpgradeProcessUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import java.io.InputStream;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Ryan Park
 */
public class UpgradeJournal extends UpgradeProcess {

	protected void addJournalStructures(
			long groupId, long userId, Locale locale)
		throws Exception {

		// Article

		Map<Locale, String> nameMap = new HashMap<>();

		nameMap.put(locale, "Article");

		Map<Locale, String> descriptionMap = new HashMap<>();

		descriptionMap.put(
			locale,
			"This structure accommodates article title, both main, and " +
				"preview images, and the main article body.");

		String xsd = getFileAsString("/structures/article.xml");

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		DDMStructure ddmStructure = DDMStructureLocalServiceUtil.addStructure(
			userId, groupId, 0, PortalUtil.getClassNameId(JournalArticle.class),
			"ARTICLE", nameMap, descriptionMap,
			JournalConverterUtil.getDDMXSD(xsd), "xml",
			DDMStructureConstants.TYPE_DEFAULT, serviceContext);

		_ddmStructureIds.put(
			groupId + "#" + "ARTICLE", ddmStructure.getStructureId());

		// Carousel

		nameMap.put(locale, "Carousel");

		descriptionMap.put(
			locale,
			"This is a simple carousel structure designed to handle other " +
				"necessary carousel configurations.");

		xsd = getFileAsString("/structures/multiple_item_carousel.xml");

		ddmStructure = DDMStructureLocalServiceUtil.addStructure(
			userId, groupId, 0, PortalUtil.getClassNameId(JournalArticle.class),
			"MULTIPLE-ITEM-CAROUSEL", nameMap, descriptionMap,
			JournalConverterUtil.getDDMXSD(xsd), "xml",
			DDMStructureConstants.TYPE_DEFAULT, serviceContext);

		_ddmStructureIds.put(
			groupId + "#" + "MULTIPLE-ITEM-CAROUSEL",
			ddmStructure.getStructureId());

		// Multiple Item

		nameMap.put(locale, "Multiple Item");

		descriptionMap.put(
			locale,
			"This is a simple structure with a single repeatable element " +
				"that includes an HTML field, and text-box for a title and " +
					"URL designation.");

		xsd = getFileAsString("/structures/multiple_item.xml");

		ddmStructure = DDMStructureLocalServiceUtil.addStructure(
			userId, groupId, 0, PortalUtil.getClassNameId(JournalArticle.class),
			"MULTIPLE-ITEM", nameMap, descriptionMap,
			JournalConverterUtil.getDDMXSD(xsd), "xml",
			DDMStructureConstants.TYPE_DEFAULT, serviceContext);

		_ddmStructureIds.put(
			groupId + "#" + "MULTIPLE-ITEM", ddmStructure.getStructureId());
	}

	protected void addJournalTemplates(long groupId, long userId, Locale locale)
		throws Exception {

		// Regular Article Description

		long classPK = _ddmStructureIds.get(groupId + "#" + "ARTICLE");

		Map<Locale, String> nameMap = new HashMap<>();

		nameMap.put(locale, "Regular Article Description");

		Map<Locale, String> descriptionMap = new HashMap<>();

		descriptionMap.put(
			locale,
			"This template only displays brief descriptions of web content");

		String script = getFileAsString("/templates/article_description.vm");

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		DDMTemplateLocalServiceUtil.addTemplate(
			userId, groupId, PortalUtil.getClassNameId(DDMStructure.class),
			classPK, PortalUtil.getClassNameId(JournalArticle.class),
			"ARTICLE-DESCRIPTION", nameMap, descriptionMap,
			DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY,
			DDMTemplateConstants.TEMPLATE_MODE_CREATE,
			TemplateConstants.LANG_TYPE_VM, script, true, false,
			StringPool.BLANK, null, serviceContext);

		// Regular Article

		nameMap.put(locale, "Regular Article");

		descriptionMap.put(
			locale,
			"This is the regular article template, it handles basic article " +
				"content like, titles, main image, body, and author " +
					"information.");

		script = getFileAsString("/templates/regular_article.vm");

		DDMTemplateLocalServiceUtil.addTemplate(
			userId, groupId, PortalUtil.getClassNameId(DDMStructure.class),
			classPK, PortalUtil.getClassNameId(JournalArticle.class),
			"REGULAR-ARTICLE", nameMap, descriptionMap,
			DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY,
			DDMTemplateConstants.TEMPLATE_MODE_CREATE,
			TemplateConstants.LANG_TYPE_VM, script, true, false,
			StringPool.BLANK, null, serviceContext);

		// Carousel

		classPK = _ddmStructureIds.get(
			groupId + "#" + "MULTIPLE-ITEM-CAROUSEL");

		nameMap.put(locale, "Carousel");

		descriptionMap.put(
			locale,
			"This is the carousel template that utilizes Alloy UI to display " +
				"repeatable content as a slideshow.");

		script = getFileAsString("/templates/multiple_item_carousel.vm");

		DDMTemplateLocalServiceUtil.addTemplate(
			userId, groupId, PortalUtil.getClassNameId(DDMStructure.class),
			classPK, PortalUtil.getClassNameId(JournalArticle.class),
			"MULTIPLE-ITEM-CAROUSEL", nameMap, descriptionMap,
			DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY,
			DDMTemplateConstants.TEMPLATE_MODE_CREATE,
			TemplateConstants.LANG_TYPE_VM, script, true, false,
			StringPool.BLANK, null, serviceContext);

		// Featured Items

		classPK = _ddmStructureIds.get(groupId + "#" + "MULTIPLE-ITEM");

		nameMap.put(locale, "Featured Items");

		descriptionMap.put(
			locale,
			"This is a template that utilizes the Multiple Item Structure, " +
				"and displays the data as Featured Items.");

		script = getFileAsString("/templates/multiple_item_feature.vm");

		DDMTemplateLocalServiceUtil.addTemplate(
			userId, groupId, PortalUtil.getClassNameId(DDMStructure.class),
			classPK, PortalUtil.getClassNameId(JournalArticle.class),
			"MULTIPLE-ITEM-FEATURE", nameMap, descriptionMap,
			DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY,
			DDMTemplateConstants.TEMPLATE_MODE_CREATE,
			TemplateConstants.LANG_TYPE_VM, script, true, false,
			StringPool.BLANK, null, serviceContext);
	}

	@Override
	protected void doUpgrade() throws Exception {
		long companyId = PortalUtil.getDefaultCompanyId();

		Group group = GroupLocalServiceUtil.getCompanyGroup(companyId);

		long groupId = group.getGroupId();
		long userId = UserLocalServiceUtil.getDefaultUserId(companyId);

		String languageId = UpgradeProcessUtil.getDefaultLanguageId(companyId);

		Locale locale = LocaleUtil.fromLanguageId(languageId);

		addJournalStructures(groupId, userId, locale);
		addJournalTemplates(groupId, userId, locale);
	}

	protected String getFileAsString(String path) throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();

		InputStream is = classLoader.getResourceAsStream("/resources" + path);

		return new String(FileUtil.getBytes(is));
	}

	private Map<String, Long> _ddmStructureIds = new HashMap<>();

}