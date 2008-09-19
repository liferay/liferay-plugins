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

package com.liferay.kb.knowledgebase.portlet;

import com.liferay.kb.knowledgebase.KnowledgeBaseKeys;
import com.liferay.portal.kernel.portlet.BaseFriendlyURLMapper;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Map;

import javax.portlet.PortletMode;
import javax.portlet.WindowState;

/**
 * <a href="KnowledgeBaseFriendlyURLMapper.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Jorge Ferrer
 *
 */
public class KnowledgeBaseFriendlyURLMapper extends BaseFriendlyURLMapper {

	public static final String MAPPING = "kb";

	public String buildPath(LiferayPortletURL portletURL) {
		String friendlyURLPath = null;

		String view = GetterUtil.getString(
			portletURL.getParameter("view"));

		if (view.equals("view_article")) {
			String title = portletURL.getParameter("title");

			StringBuilder sb = new StringBuilder();

			if (Validator.isNotNull(title)) {
				sb.append(StringPool.SLASH);
				sb.append(MAPPING);
				sb.append(StringPool.SLASH);

				sb.append(HttpUtil.encodeURL(title));

				portletURL.addParameterIncludedInPath("title");

				WindowState windowState = portletURL.getWindowState();

				if (!windowState.equals(WindowState.NORMAL)) {
					sb.append(StringPool.SLASH);
					sb.append(windowState);
				}
			}

			friendlyURLPath = sb.toString();
		}
		else if (view.equals("view_tagged_articles")) {
			String tag = portletURL.getParameter("tag");

			StringBuilder sb = new StringBuilder();

			if (Validator.isNotNull(tag)) {
				sb.append(StringPool.SLASH);
				sb.append(MAPPING);
				sb.append(StringPool.SLASH);
				sb.append("tag");
				sb.append(StringPool.SLASH);

				sb.append(HttpUtil.encodeURL(tag));

				portletURL.addParameterIncludedInPath("tag");
			}

			friendlyURLPath = sb.toString();
		}

		if (Validator.isNotNull(friendlyURLPath)) {
			portletURL.addParameterIncludedInPath("p_p_id");

			portletURL.addParameterIncludedInPath("view");
		}

		return friendlyURLPath;
	}

	public String getMapping() {
		return MAPPING;
	}

	public String getPortletId() {
		return _PORTLET_ID;
	}

	public void populateParams(
		String friendlyURLPath, Map<String, String[]> params) {

		addParam(params, "p_p_id", _PORTLET_ID);
		addParam(params, "p_p_lifecycle", "0");
		addParam(params, "p_p_mode", PortletMode.VIEW);

		int x = friendlyURLPath.indexOf(StringPool.SLASH, 1);

		String[] urlFragments = StringUtil.split(
			friendlyURLPath.substring(x + 1), StringPool.SLASH);

		if (urlFragments.length >= 1) {
			String urlFragment0 = HttpUtil.decodeURL(urlFragments[0]);

			if (urlFragment0.equals("tag") && (urlFragments.length >= 2)) {
				addParam(params, "view", "view_tagged_articles");

				String tag = HttpUtil.decodeURL(urlFragments[1]);

				addParam(params, "tag", tag);
			}
			else {
				addParam(params, "view", "view_article");

				addParam(params, "title", urlFragment0);

				if (urlFragments.length >= 2) {
					String windowState = urlFragments[1];

					addParam(params, "p_p_state", windowState);
				}
			}
		}
		else {
			addParam(params, "view", "view_all_articles");
		}
	}

	private static final String _PORTLET_ID = KnowledgeBaseKeys.PORTLET_ID;

}