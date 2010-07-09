/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.wikinavigation.service.permission;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassInvoker;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.wiki.model.WikiPage;

/**
 * @author Jorge Ferrer
 * @author Peter Shin
 */
public class WikiPagePermission {

	public static boolean contains(
		PermissionChecker permissionChecker, WikiPage wikiPage,
		String actionId) {

		try {
			Object[] args = new Object[] {
				permissionChecker, wikiPage, actionId
			};

			return (Boolean)PortalClassInvoker.invoke(
				_CLASS_NAME_WIKI_PAGE_PERMISSION, "contains", args);
		}
		catch (Exception e) {
			_log.error(e, e);

			return false;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(WikiPagePermission.class);

	private static final String _CLASS_NAME_WIKI_PAGE_PERMISSION =
		"com.liferay.portlet.wiki.service.permission.WikiPagePermission";

}