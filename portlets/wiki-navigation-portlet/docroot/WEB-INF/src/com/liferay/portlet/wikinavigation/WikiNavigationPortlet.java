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

package com.liferay.portlet.wikinavigation;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.wiki.NoSuchNodeException;
import com.liferay.portlet.wiki.NoSuchPageException;
import com.liferay.portlet.wiki.model.WikiNode;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil;
import com.liferay.portlet.wiki.service.WikiPageServiceUtil;
import com.liferay.util.bridges.jsp.JSPPortlet;

import java.io.IOException;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * <a href="WikiNavigationPortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Thiago Moreira
 *
 */
public class WikiNavigationPortlet extends JSPPortlet {

	public void doView(RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws IOException, PortletException {

		String view = null;
		Boolean decorate = Boolean.TRUE;
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(
			com.liferay.portal.kernel.util.WebKeys.THEME_DISPLAY);

		try {
			PortletPreferences prefs = renderRequest.getPreferences();

			long nodeId = GetterUtil.getLong(
				prefs.getValue("node-id", StringPool.BLANK));
			String title = prefs.getValue("title", null);
			String type = prefs.getValue("type", WebKeys.TREE_VIEW);
			int displayDepth = GetterUtil.getInteger(
				prefs.getValue("displayDepth", StringPool.BLANK));
			String selectedTitle = ParamUtil.getString(renderRequest, "title");
			List<EntryDisplay> entries = Collections.EMPTY_LIST;

			view = "view_" + type;

			if (!themeDisplay.getPortletDisplay().isShowConfigurationIcon() &&
					type.equals(WebKeys.MENU_VIEW)) {

				decorate = Boolean.FALSE;
			}

			if (Validator.isNotNull(title)) {

				WikiPage wikiPage = null;
				try {
					wikiPage = WikiPageServiceUtil.getPage(nodeId, title);
				}
				catch (NoSuchPageException nspe) {
					wikiPage = WikiPageServiceUtil.getPage(
						nodeId, "FRONT_PAGE");
				}

				entries = _buildNavigationTree(
					wikiPage, renderResponse.createActionURL());
			}
			else if (nodeId != 0) {
				int total = WikiPageLocalServiceUtil.getPagesCount(
					nodeId, true);

				List<WikiPage> wikiPages = WikiPageLocalServiceUtil.getPages(
					nodeId, true, 0, total);

				entries = _buildNavigationTree(
					wikiPages, themeDisplay.getPermissionChecker(),
					selectedTitle, renderResponse.createActionURL(),
					displayDepth, 0);

			}
			else {
				view = "portlet_not_setup";
				decorate = Boolean.TRUE;
			}

			renderRequest.setAttribute(WebKeys.ENTRIES, entries);
		}
		catch (NoSuchNodeException nsne) {
			view = "portlet_not_setup";
		}
		catch (Exception pe) {
			view = "error";
			SessionErrors.add(renderRequest, pe.getClass().getName());
		}

		renderRequest.setAttribute(WebKeys.VIEW, view);
		renderRequest.setAttribute(WebKeys.PORTLET_DECORATE, decorate);
		super.doView(renderRequest, renderResponse);
	}

	private List<EntryDisplay> _buildNavigationTree(
			WikiPage page, PortletURL portletURL)
		throws Exception {

		String regex = "((==\\s((.)*)\\s==)*(\\Q[[\\E((.)*)\\Q]]\\E)*)*";

		Pattern pattern = Pattern.compile(regex);

		List<EntryDisplay> entries = new LinkedList<EntryDisplay>();
		Matcher matcher =  pattern.matcher(page.getContent());
		EntryDisplay parent = null;
		while (matcher.find()) {
			String title = matcher.group(3);
			if (title != null) {
				parent = new EntryDisplay();
				parent.setLabel(title);
				entries.add(parent);
			}
			String temp = matcher.group(6);
			if (temp != null) {
				EntryDisplay child = new EntryDisplay();

				int index = temp.indexOf("|");
				if (index != -1) {
					String url = temp.substring(0, index);
					String label = temp.substring(index+1, temp.length());

					if (!url.startsWith("http")) {
						portletURL.setParameter("title", url);
						portletURL.setParameter(
							"nodeId", String.valueOf(page.getNodeId()));
						url = portletURL.toString();
					}
					else {
						child.setExternalURL(true);
					}
					child.setLabel(label);
					child.setUrl(url);
				}
				else {
					child.setLabel(temp);
					String url = temp;
					if (!url.startsWith("http")) {
						portletURL.setParameter("title", url);
						portletURL.setParameter(
							"nodeId", String.valueOf(page.getNodeId()));
						url = portletURL.toString();
					}
					child.setUrl(url);
				}

				parent.addChild(child);
			}
		}

		return entries;
	}

	private  List<EntryDisplay> _buildNavigationTree(List<WikiPage> pages,
			PermissionChecker permissionChecker, String titleSelected,
			PortletURL portletURL, int displayDepth, int actualDepth)
		throws Exception {

		List<EntryDisplay> entries = new LinkedList<EntryDisplay>();

		for (WikiPage wikiPage : pages) {

			if ((wikiPage.getParentPage() != null && actualDepth == 0)
				|| (!contains(permissionChecker, wikiPage, ActionKeys.VIEW))) {

				continue;
			}

			String title = wikiPage.getTitle();
			WikiNode wikiNode = wikiPage.getNode();
			EntryDisplay parent = new EntryDisplay();

			portletURL.setParameter("nodeId",
				String.valueOf(wikiNode.getNodeId()));
			portletURL.setParameter("title", title);

			parent.setLabel(title);
			parent.setUrl(portletURL.toString());

			if ((displayDepth == -1) || (actualDepth < displayDepth)) {

				parent.addChildren(_buildNavigationTree(
					wikiPage.getChildPages(), permissionChecker, titleSelected,
					portletURL, displayDepth, (actualDepth+1)));
			}

			entries.add(parent);
		}

		return entries;
	}

	public static boolean contains(
		PermissionChecker permissionChecker, WikiPage page, String actionId) {

		if (permissionChecker.hasOwnerPermission(
				page.getCompanyId(), WikiPage.class.getName(), page.getPageId(),
				page.getUserId(), actionId)) {

			return true;
		}

		WikiNode node = page.getNode();

		return permissionChecker.hasPermission(
			node.getGroupId(), WikiPage.class.getName(),
			page.getResourcePrimKey(), actionId);
	}

}