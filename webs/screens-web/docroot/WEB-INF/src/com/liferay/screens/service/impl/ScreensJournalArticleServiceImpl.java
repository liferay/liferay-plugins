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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ClassResolverUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.PortalClassInvoker;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
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

		checkPermission(getPermissionChecker(), classPK, ActionKeys.VIEW);

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

		checkPermission(getPermissionChecker(), classPK, ActionKeys.VIEW);

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

		checkPermission(
			getPermissionChecker(), groupId, articleId, ActionKeys.VIEW);

		return journalArticleLocalService.getArticleContent(
			groupId, articleId, null, getDDMTemplateKey(ddmTemplateId),
			getLanguageId(locale), null);
	}

	protected void checkPermission(
			PermissionChecker permissionChecker, long resourcePrimKey,
			String actionId)
		throws PortalException, SystemException {

		try {
			PortalClassInvoker.invoke(
				false, _checkPermissionMethodKey1, permissionChecker,
				resourcePrimKey, actionId);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	protected void checkPermission(
			PermissionChecker permissionChecker, long groupId, String articleId,
			String actionId)
		throws PortalException, SystemException {

		try {
			PortalClassInvoker.invoke(
				false, _checkPermissionMethodKey2, permissionChecker, groupId,
				articleId, actionId);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			_log.error(e, e);
		}
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

	private static final MethodKey _checkPermissionMethodKey1 =
		new MethodKey(
			ClassResolverUtil.resolveByPortalClassLoader(
				"com.liferay.portlet.journal.service.permission." +
					"JournalArticlePermission"),
			"check", PermissionChecker.class, long.class, String.class);
	private static final MethodKey _checkPermissionMethodKey2 =
		new MethodKey(
			ClassResolverUtil.resolveByPortalClassLoader(
				"com.liferay.portlet.journal.service.permission." +
					"JournalArticlePermission"),
			"check", PermissionChecker.class, long.class, String.class,
			String.class);

	private static Log _log = LogFactoryUtil.getLog(
		ScreensJournalArticleServiceImpl.class);

}