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

package com.liferay.knowledgebase.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.util.portlet.PortletProps;

/**
 * @author Peter Shin
 */
public class PortletPropsValues {

	public static final String ADMIN_EMAIL_FROM_ADDRESS = PortletProps.get(
		PortletPropsKeys.ADMIN_EMAIL_FROM_ADDRESS);

	public static final String ADMIN_EMAIL_FROM_NAME = PortletProps.get(
		PortletPropsKeys.ADMIN_EMAIL_FROM_NAME);

	public static final String ADMIN_EMAIL_KB_ARTICLE_ADDED_BODY =
		PortletProps.get(PortletPropsKeys.ADMIN_EMAIL_KB_ARTICLE_ADDED_BODY);

	public static final boolean ADMIN_EMAIL_KB_ARTICLE_ADDED_ENABLED =
		GetterUtil.getBoolean(
			PortletProps.get(
				PortletPropsKeys.ADMIN_EMAIL_KB_ARTICLE_ADDED_ENABLED));

	public static final String ADMIN_EMAIL_KB_ARTICLE_ADDED_SUBJECT =
		PortletProps.get(PortletPropsKeys.ADMIN_EMAIL_KB_ARTICLE_ADDED_SUBJECT);

	public static final String ADMIN_EMAIL_KB_ARTICLE_UPDATED_BODY =
		PortletProps.get(PortletPropsKeys.ADMIN_EMAIL_KB_ARTICLE_UPDATED_BODY);

	public static final boolean ADMIN_EMAIL_KB_ARTICLE_UPDATED_ENABLED =
		GetterUtil.getBoolean(
			PortletProps.get(
				PortletPropsKeys.ADMIN_EMAIL_KB_ARTICLE_UPDATED_ENABLED));

	public static final String ADMIN_EMAIL_KB_ARTICLE_UPDATED_SUBJECT =
		PortletProps.get(
			PortletPropsKeys.ADMIN_EMAIL_KB_ARTICLE_UPDATED_SUBJECT);

	public static final String[] ADMIN_KB_ARTICLE_DEFAULT_SECTIONS =
		PortletProps.getArray(
			PortletPropsKeys.ADMIN_KB_ARTICLE_DEFAULT_SECTIONS);

	public static final boolean ADMIN_KB_ARTICLE_INCREMENT_PRIORITY_ENABLED =
		GetterUtil.getBoolean(
			PortletProps.get(
				PortletPropsKeys.ADMIN_KB_ARTICLE_INCREMENT_PRIORITY_ENABLED));

	public static final String[] ADMIN_KB_ARTICLE_SECTIONS =
		PortletProps.getArray(PortletPropsKeys.ADMIN_KB_ARTICLE_SECTIONS);

}