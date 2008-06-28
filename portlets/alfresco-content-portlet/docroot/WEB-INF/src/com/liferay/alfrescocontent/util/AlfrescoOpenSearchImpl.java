/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.alfrescocontent.util;

import com.liferay.portal.kernel.search.OpenSearch;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.util.portlet.PortletProps;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

	public String search(HttpServletRequest req, String url)
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
			xml = HttpUtil.URLtoString(
				url, HOST, PORT, REALM, USERNAME, PASSWORD);
		}
		catch (IOException ioe) {
			_log.error("Unable to search with " + url, ioe);
		}

		return xml;
	}

	public String search(
			HttpServletRequest req, String keywords, int startPage,
			int itemsPerPage)
		throws SearchException {

		String url =
			PROTOCOL + "://" + SEARCH_URL + "?q=" +
				HttpUtil.encodeURL(keywords) + "&p=" + startPage + "&c=" +
					itemsPerPage + "&guest=&format=atom";

		return search(req, url);
	}

	private static Log _log = LogFactory.getLog(AlfrescoOpenSearchImpl.class);

}