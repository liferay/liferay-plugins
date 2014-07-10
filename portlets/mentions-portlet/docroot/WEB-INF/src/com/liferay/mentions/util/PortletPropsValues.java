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

package com.liferay.mentions.util;

import com.liferay.util.portlet.PortletProps;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletPropsValues {

	public static final String ASSET_ENTRY_MENTION_EMAIL_BODY =
		PortletProps.get(PortletPropsKeys.ASSET_ENTRY_MENTION_EMAIL_BODY);

	public static final String ASSET_ENTRY_MENTION_EMAIL_SUBJECT =
		PortletProps.get(PortletPropsKeys.ASSET_ENTRY_MENTION_EMAIL_SUBJECT);

	public static final String COMMENT_MENTION_EMAIL_BODY = PortletProps.get(
		PortletPropsKeys.COMMENT_MENTION_EMAIL_BODY);

	public static final String COMMENT_MENTION_EMAIL_SUBJECT = PortletProps.get(
		PortletPropsKeys.COMMENT_MENTION_EMAIL_SUBJECT);

}