/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

package com.liferay.wikinavigation.pagehierarchy.portlet;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.wiki.model.WikiNode;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.service.WikiNodeServiceUtil;
import com.liferay.portlet.wiki.service.WikiPageServiceUtil;
import com.liferay.util.bridges.jsp.JSPPortlet;
import com.liferay.wikinavigation.WebKeys;
import com.liferay.wikinavigation.WikiNavigationConstants;
import com.liferay.wikinavigation.util.EntryDisplay;

import java.io.IOException;

import java.util.LinkedList;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * <a href="PageHierarchyPortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Thiago Moreira
 * @author Jorge Ferrer
 *
 */
public class PageHierarchyPortlet extends JSPPortlet {

	public void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			com.liferay.portal.kernel.util.WebKeys.THEME_DISPLAY);

		try {
			PortletPreferences prefs = renderRequest.getPreferences();

			long nodeId = GetterUtil.getLong(
				prefs.getValue("node-id", StringPool.BLANK));
			int depth = GetterUtil.getInteger(
				prefs.getValue("depth", StringPool.BLANK),
				WikiNavigationConstants.DEPTH_ALL);

			if (nodeId <= 0) {
				String wikiName = PropsUtil.get("wiki.initial.node.name");

				try {
					WikiNode node = WikiNodeServiceUtil.getNode(
						themeDisplay.getScopeGroupId(), wikiName);
					nodeId = node.getNodeId();
				}
				catch (Exception e) {
				}
			}

			if (nodeId > 0) {
				List<WikiPage> wikiPages = WikiPageServiceUtil.getNodePages(
					nodeId, WikiNavigationConstants.MAX_PAGES);

				List<EntryDisplay> entries = _buildNavigationTree(
					wikiPages, renderResponse.createRenderURL(),
					depth, 1);

				renderRequest.setAttribute(WebKeys.HIERARCHY_ENTRIES, entries);
			}
		}
		catch (Exception e) {
			SessionErrors.add(renderRequest, e.getClass().getName(), e);
		}

		super.doView(renderRequest, renderResponse);
	}

	private List<EntryDisplay> _buildNavigationTree(
			List<WikiPage> pages, PortletURL portletURL, int depth,
			int curDepth)
		throws Exception {

		List<EntryDisplay> entries = new LinkedList<EntryDisplay>();

		for (WikiPage wikiPage : pages) {

			if ((wikiPage.getParentPage() != null) && (curDepth == 1)) {
				continue;
			}

			String title = wikiPage.getTitle();
			WikiNode wikiNode = wikiPage.getNode();

			portletURL.setParameter(
				"nodeId", String.valueOf(wikiNode.getNodeId()));
			portletURL.setParameter("title", title);

			EntryDisplay parentEntryDisplay = new EntryDisplay();

			parentEntryDisplay.setLabel(title);
			parentEntryDisplay.setUrl(portletURL.toString());

			if ((depth == WikiNavigationConstants.DEPTH_ALL) ||
				(curDepth < depth)) {

				parentEntryDisplay.addChildren(
					_buildNavigationTree(
						wikiPage.getChildPages(), portletURL, depth,
						curDepth + 1));
			}

			entries.add(parentEntryDisplay);
		}

		return entries;
	}

}