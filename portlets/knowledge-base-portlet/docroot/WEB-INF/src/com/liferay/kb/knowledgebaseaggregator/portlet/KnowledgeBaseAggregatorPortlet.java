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

package com.liferay.kb.knowledgebaseaggregator.portlet;

import com.liferay.kb.knowledgebase.service.KBArticleServiceUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.jsp.JSPPortlet;
import com.liferay.util.servlet.ServletResponseUtil;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletResponse;

/**
 * <a href="KnowledgeBaseAggregatorPortlet.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Peter Shin
 *
 */
public class KnowledgeBaseAggregatorPortlet extends JSPPortlet {

	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortletException {

		try {
			String actionName = ParamUtil.getString(
				resourceRequest, "actionName");

			if (actionName.equals("rss")) {
				getRSS(resourceRequest, resourceResponse);
			}
		}
		catch (Exception e) {
		}
	}

	protected void getRSS(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long groupId = ParamUtil.getLong(resourceRequest, "groupId");
		int rssMaxItems = ParamUtil.getInteger(resourceRequest, "rssMaxItems");
		String rssDisplayStyle = ParamUtil.getString(
			resourceRequest, "rssDisplayStyle");
		int rssAbstractLength = ParamUtil.getInteger(
			resourceRequest, "rssAbstractLength");
		String rssType = ParamUtil.getString(resourceRequest, "rssType");
		double rssVersion = ParamUtil.getDouble(resourceRequest, "rssVersion");

		String feedURL = PortalUtil.getPortalURL(themeDisplay) +
			PortalUtil.getLayoutURL(themeDisplay);
		String description = LanguageUtil.get(
			themeDisplay.getLocale(), "knowledge-base-articles");

		String rss = StringPool.BLANK;

		if (groupId > 0) {
			rss = KBArticleServiceUtil.getGroupArticlesRSS(
				groupId, rssMaxItems, rssType, rssVersion, rssDisplayStyle,
				rssAbstractLength, description, feedURL, themeDisplay);
		}
		else {
			rss = KBArticleServiceUtil.getCompanyArticlesRSS(
				themeDisplay.getCompanyId(), rssMaxItems, rssType, rssVersion,
				rssDisplayStyle, rssAbstractLength, description, feedURL,
				themeDisplay);
		}

		HttpServletResponse response = PortalUtil.getHttpServletResponse(
			resourceResponse);

		ServletResponseUtil.sendFile(
			response, null, rss.getBytes(StringPool.UTF8),
			ContentTypes.TEXT_XML_UTF8);
	}

}