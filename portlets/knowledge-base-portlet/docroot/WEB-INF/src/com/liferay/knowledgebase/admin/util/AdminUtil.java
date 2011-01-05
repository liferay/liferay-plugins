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

package com.liferay.knowledgebase.admin.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.ContentUtil;
import com.liferay.util.portlet.PortletProps;

import javax.portlet.PortletPreferences;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class AdminUtil {

	public static String getEmailArticleAddedBody(
		PortletPreferences preferences) {

		String emailArticleAddedBody = preferences.getValue(
			"emailArticleAddedBody", StringPool.BLANK);

		if (Validator.isNotNull(emailArticleAddedBody)) {
			return emailArticleAddedBody;
		}

		return ContentUtil.get(
			PortletProps.get("admin.email.article.added.body"));
	}

	public static boolean getEmailArticleAddedEnabled(
		PortletPreferences preferences) {

		String emailArticleAddedEnabled = preferences.getValue(
			"emailArticleAddedEnabled", StringPool.BLANK);

		if (Validator.isNotNull(emailArticleAddedEnabled)) {
			return GetterUtil.getBoolean(emailArticleAddedEnabled);
		}

		return GetterUtil.getBoolean(
			PortletProps.get("admin.email.article.added.enabled"));
	}

	public static String getEmailArticleAddedSubject(
		PortletPreferences preferences) {

		String emailArticleAddedSubject = preferences.getValue(
			"emailArticleAddedSubject", StringPool.BLANK);

		if (Validator.isNotNull(emailArticleAddedSubject)) {
			return emailArticleAddedSubject;
		}

		return ContentUtil.get(
			PortletProps.get("admin.email.article.added.subject"));
	}

	public static String getEmailArticleUpdatedBody(
		PortletPreferences preferences) {

		String emailArticleUpdatedBody = preferences.getValue(
			"emailArticleUpdatedBody", StringPool.BLANK);

		if (Validator.isNotNull(emailArticleUpdatedBody)) {
			return emailArticleUpdatedBody;
		}

		return ContentUtil.get(
			PortletProps.get("admin.email.article.updated.body"));
	}

	public static boolean getEmailArticleUpdatedEnabled(
		PortletPreferences preferences) {

		String emailArticleUpdatedEnabled = preferences.getValue(
			"emailArticleUpdatedEnabled", StringPool.BLANK);

		if (Validator.isNotNull(emailArticleUpdatedEnabled)) {
			return GetterUtil.getBoolean(emailArticleUpdatedEnabled);
		}

		return GetterUtil.getBoolean(
			PortletProps.get("admin.email.article.updated.enabled"));
	}

	public static String getEmailArticleUpdatedSubject(
		PortletPreferences preferences) {

		String emailArticleUpdatedSubject = preferences.getValue(
			"emailArticleUpdatedSubject", StringPool.BLANK);

		if (Validator.isNotNull(emailArticleUpdatedSubject)) {
			return emailArticleUpdatedSubject;
		}

		return ContentUtil.get(
			PortletProps.get("admin.email.article.updated.subject"));
	}

	public static String getEmailFromAddress(PortletPreferences preferences) {
		return preferences.getValue(
			"emailFromAddress", PortletProps.get("admin.email.from.address"));
	}

	public static String getEmailFromName(PortletPreferences preferences) {
		return preferences.getValue(
			"emailFromName", PortletProps.get("admin.email.from.name"));
	}

}