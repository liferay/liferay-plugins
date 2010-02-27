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

package com.liferay.alfrescocontent.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.OpenSearch;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.util.portlet.PortletProps;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

/**
 * <a href="AlfrescoOpenSearchImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Charles May
 * @author Brian Wing Shun Chan
 *
 */
public class AlfrescoOpenSearchImpl implements OpenSearch {

	public static final boolean ENABLED = GetterUtil.getBoolean(
		PortletProps.get("open.search.enabled"));

	public static final String PROTOCOL = PortletProps.get(
		"open.search.protocol");

	public static final String HOST = PortletProps.get("open.search.host");

	public static final int PORT = GetterUtil.getInteger(
		PortletProps.get("open.search.port"));

	public static final String REALM = PortletProps.get(
		"open.search.realm");

	public static final String USERNAME = PortletProps.get(
		"open.search.username");

	public static final String PASSWORD = PortletProps.get(
		"open.search.password");

	public static final String PATH = PortletProps.get("open.search.path");

	public static final String SEARCH_URL =
		HOST + StringPool.COLON + PORT + PATH;

	public boolean isEnabled() {
		return ENABLED;
	}

	public String search(HttpServletRequest request, String url)
		throws SearchException {

		String xml = StringPool.BLANK;

		if (!ENABLED) {
			if (_log.isDebugEnabled()) {
				_log.debug("Search is disabled");
			}

			return xml;
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Search with " + url);
		}

		try {
			Http.Options options = new Http.Options();

			options.setAuth(HOST, PORT, REALM, USERNAME, PASSWORD);
			options.setLocation(url);

			xml = HttpUtil.URLtoString(options);
		}
		catch (IOException ioe) {
			_log.error("Unable to search with " + url, ioe);
		}

		return xml;
	}

	public String search(
			HttpServletRequest request, long userId, String keywords,
			int startPage, int itemsPerPage, String format)
		throws SearchException {

		return search(
			request, 0, userId, keywords, startPage, itemsPerPage, format);
	}

	public String search(
			HttpServletRequest request, long groupId, long userId,
			String keywords, int startPage, int itemsPerPage, String format)
		throws SearchException {

		String url =
			PROTOCOL + "://" + SEARCH_URL + "?q=" +
				HttpUtil.encodeURL(keywords) + "&p=" + startPage + "&c=" +
					itemsPerPage + "&guest=&format=" + format;

		return search(request, url);
	}

	private static Log _log =
		 LogFactoryUtil.getLog(AlfrescoOpenSearchImpl.class);

}