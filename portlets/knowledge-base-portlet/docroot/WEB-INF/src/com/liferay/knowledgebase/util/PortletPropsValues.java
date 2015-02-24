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

package com.liferay.knowledgebase.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
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

	public static final String
		ADMIN_EMAIL_KB_ARTICLE_SUGGESTION_IN_PROGRESS_BODY = PortletProps.get(
			PortletPropsKeys.
				ADMIN_EMAIL_KB_ARTICLE_SUGGESTION_IN_PROGRESS_BODY);

	public static final boolean
		ADMIN_EMAIL_KB_ARTICLE_SUGGESTION_IN_PROGRESS_ENABLED =
			GetterUtil.getBoolean(
				PortletProps.get(
					PortletPropsKeys.
						ADMIN_EMAIL_KB_ARTICLE_SUGGESTION_IN_PROGRESS_ENABLED));

	public static final String
		ADMIN_EMAIL_KB_ARTICLE_SUGGESTION_IN_PROGRESS_SUBJECT =
			PortletProps.get(
				PortletPropsKeys.
					ADMIN_EMAIL_KB_ARTICLE_SUGGESTION_IN_PROGRESS_SUBJECT);

	public static final String ADMIN_EMAIL_KB_ARTICLE_SUGGESTION_RECEIVED_BODY =
		PortletProps.get(
			PortletPropsKeys.ADMIN_EMAIL_KB_ARTICLE_SUGGESTION_RECEIVED_BODY);

	public static final boolean
		ADMIN_EMAIL_KB_ARTICLE_SUGGESTION_RECEIVED_ENABLED =
			GetterUtil.getBoolean(
				PortletProps.get(
					PortletPropsKeys.
						ADMIN_EMAIL_KB_ARTICLE_SUGGESTION_RECEIVED_ENABLED));

	public static final String
		ADMIN_EMAIL_KB_ARTICLE_SUGGESTION_RECEIVED_SUBJECT = PortletProps.get(
			PortletPropsKeys.
				ADMIN_EMAIL_KB_ARTICLE_SUGGESTION_RECEIVED_SUBJECT);

	public static final String ADMIN_EMAIL_KB_ARTICLE_SUGGESTION_RESOLVED_BODY =
		PortletProps.get(
			PortletPropsKeys.ADMIN_EMAIL_KB_ARTICLE_SUGGESTION_RESOLVED_BODY);

	public static final boolean
		ADMIN_EMAIL_KB_ARTICLE_SUGGESTION_RESOLVED_ENABLED =
			GetterUtil.getBoolean(
				PortletProps.get(
					PortletPropsKeys.
						ADMIN_EMAIL_KB_ARTICLE_SUGGESTION_RESOLVED_ENABLED));

	public static final String
		ADMIN_EMAIL_KB_ARTICLE_SUGGESTION_RESOLVED_SUBJECT = PortletProps.get(
			PortletPropsKeys.
				ADMIN_EMAIL_KB_ARTICLE_SUGGESTION_RESOLVED_SUBJECT);

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

	public static final int KNOWLEDGE_BASE_RATINGS_NUMBER_OF_STARS =
		GetterUtil.getInteger(
			PortletProps.get(
				PortletPropsKeys.
					KNOWLEDGE_BASE_RATINGS_NUMBER_OF_STARS),
			GetterUtil.getInteger(
				PropsUtil.get(PropsKeys.RATINGS_DEFAULT_NUMBER_OF_STARS)));

	public static final String KNOWLEDGE_BASE_SOCIAL_BOOKMARKS_DISPLAY_STYLE =
		PortletProps.get(
			PortletPropsKeys.KNOWLEDGE_BASE_SOCIAL_BOOKMARKS_DISPLAY_STYLE);

	public static final String KNOWLEDGE_BASE_SOURCE_URL_EDIT_MESSAGE_KEY =
		PortletProps.get(
			PortletPropsKeys.KNOWLEDGE_BASE_SOURCE_URL_EDIT_MESSAGE_KEY);

	public static final boolean KNOWLEDGE_BASE_SOURCE_URL_ENABLED =
		GetterUtil.getBoolean(
			PortletProps.get(
				PortletPropsKeys.KNOWLEDGE_BASE_SOURCE_URL_ENABLED));

	public static final String[] MARKDOWN_IMPORTER_ARTICLE_EXTENSIONS =
		PortletProps.getArray(
			PortletPropsKeys.MARKDOWN_IMPORTER_ARTICLE_EXTENSIONS);

	public static final String MARKDOWN_IMPORTER_ARTICLE_INTRO =
		GetterUtil.getString(
			PortletProps.get(PortletPropsKeys.MARKDOWN_IMPORTER_ARTICLE_INTRO));

	public static final String[] MARKDOWN_IMPORTER_IMAGE_FILE_EXTENSIONS =
		PortletProps.getArray(
			PortletPropsKeys.MARKDOWN_IMPORTER_IMAGE_FILE_EXTENSIONS);

	public static final String MARKDOWN_IMPORTER_IMAGE_FOLDER =
		GetterUtil.getString(
			PortletProps.get(PortletPropsKeys.MARKDOWN_IMPORTER_IMAGE_FOLDER));

}