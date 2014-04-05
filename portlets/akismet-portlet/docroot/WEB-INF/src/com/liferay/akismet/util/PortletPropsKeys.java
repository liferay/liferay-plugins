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

package com.liferay.akismet.util;

/**
 * @author Amos Fong
 */
public interface PortletPropsKeys {

	public static final String AKISMET_API_KEY = "akismet.api.key";

	public static final String AKISMET_CHECK_THRESHOLD =
		"akismet.check.threshold";

	public static final String AKISMET_DISCUSSIONS_CHECK_ENABLED =
		"akismet.discussions.check.enabled";

	public static final String AKISMET_MESSAGE_BOARDS_CHECK_ENABLED =
		"akismet.message.boards.check.enabled";

	public static final String AKISMET_REPORTABLE_TIME =
		"akismet.reportable.time";

	public static final String AKISMET_RETAIN_SPAM_TIME =
		"akismet.retain.spam.time";

	public static final String AKISMET_WIKI_CHECK_ENABLED =
		"akismet.wiki.check.enabled";

}