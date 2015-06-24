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
import com.liferay.portlet.journal.model.JournalArticleResource;
import com.liferay.screens.service.base.ScreensJournalArticleServiceBaseImpl;

import java.util.Locale;

/**
 * The implementation of the screens journal article remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.screens.service.ScreensJournalArticleService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author José Manuel Navarro
 * @see com.liferay.screens.service.base.ScreensJournalArticleServiceBaseImpl
 * @see com.liferay.screens.service.ScreensJournalArticleServiceUtil
 */
public class ScreensJournalArticleServiceImpl
	extends ScreensJournalArticleServiceBaseImpl {
	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.screens.service.ScreensJournalArticleServiceUtil} to access the screens journal article remote service.
	 */

	@Override
	public String getJournalArticle(
			Integer groupId, Integer classPK, Locale locale)
		throws PortalException, SystemException {

		Locale journalLocale = locale;

		if (journalLocale == null) {
			journalLocale = LocaleUtil.getSiteDefault();
		}

		JournalArticleResource resource = journalArticleResourceLocalService.getArticleResource(classPK);

		return journalArticleLocalService.getArticleContent(
			groupId, resource.getArticleId(), null, journalLocale.toString(), null);
	}
}