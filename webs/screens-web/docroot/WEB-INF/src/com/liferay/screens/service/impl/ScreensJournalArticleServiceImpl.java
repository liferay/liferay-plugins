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

package com.liferay.screens.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.service.DDMTemplateServiceUtil;
import com.liferay.portlet.journal.model.JournalArticleResource;
import com.liferay.screens.service.base.ScreensJournalArticleServiceBaseImpl;

import java.util.Locale;

/**
 * @author Javier Gamarra
 */
public class ScreensJournalArticleServiceImpl
	extends ScreensJournalArticleServiceBaseImpl {

	@Override
	public String getJournalArticle(int groupId, int classPK, Locale locale)
		throws PortalException, SystemException {

		JournalArticleResource journalArticleResource =
			journalArticleResourceLocalService.getArticleResource(classPK);

		String languageId = getLanguageId(locale);

		return journalArticleLocalService.getArticleContent(
			groupId, journalArticleResource.getArticleId(), null, languageId,
			null);
	}

	public String getJournalArticleByStructureId(
		long groupId, java.lang.String articleId, long templateId, Locale locale) throws PortalException, SystemException {

		String languageId = getLanguageId(locale);

		DDMTemplate ddmTemplate = DDMTemplateServiceUtil.getTemplate(templateId);
		String ddmTemplateKey = null;

		if (ddmTemplate != null) {
			ddmTemplateKey = ddmTemplate.getTemplateKey();
		}

		return journalArticleLocalService.getArticleContent(
			groupId, articleId, null, ddmTemplateKey, languageId, null);
	}

	private String getLanguageId(Locale locale) {
		Locale currentLocale = locale;

		if (currentLocale == null) {
			currentLocale = LocaleUtil.getSiteDefault();
		}

		String languageId = LocaleUtil.toLanguageId(currentLocale);
		return languageId;
	}

}