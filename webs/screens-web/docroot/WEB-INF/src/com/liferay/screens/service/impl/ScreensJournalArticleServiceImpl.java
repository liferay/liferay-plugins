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
	public String getJournalArticleContent(long classPK, Locale locale)
		throws PortalException, SystemException {

		JournalArticleResource journalArticleResource =
			journalArticleResourceLocalService.getArticleResource(classPK);

		return journalArticleLocalService.getArticleContent(
			journalArticleResource.getGroupId(),
			journalArticleResource.getArticleId(), null, getLanguageId(locale),
			null);
	}

	@Override
	public String getJournalArticleContent(
			long classPK, long ddmTemplateId, Locale locale)
		throws PortalException, SystemException {

		JournalArticleResource journalArticleResource =
			journalArticleResourceLocalService.getArticleResource(classPK);

		return journalArticleLocalService.getArticleContent(
			journalArticleResource.getGroupId(),
			journalArticleResource.getArticleId(), null,
			getDDMTemplateKey(ddmTemplateId), getLanguageId(locale), null);
	}

	@Override
	public String getJournalArticleContent(
			long groupId, String articleId, long ddmTemplateId, Locale locale)
		throws PortalException, SystemException {

		return journalArticleLocalService.getArticleContent(
			groupId, articleId, null, getDDMTemplateKey(ddmTemplateId),
			getLanguageId(locale), null);
	}

	protected String getDDMTemplateKey(long ddmTemplateId)
		throws PortalException, SystemException {

		String ddmTemplateKey = null;

		DDMTemplate ddmTemplate = DDMTemplateServiceUtil.getTemplate(
			ddmTemplateId);

		if (ddmTemplate != null) {
			ddmTemplateKey = ddmTemplate.getTemplateKey();
		}

		return ddmTemplateKey;
	}

	protected String getLanguageId(Locale locale) {
		if (locale == null) {
			locale = LocaleUtil.getSiteDefault();
		}

		return LocaleUtil.toLanguageId(locale);
	}

}