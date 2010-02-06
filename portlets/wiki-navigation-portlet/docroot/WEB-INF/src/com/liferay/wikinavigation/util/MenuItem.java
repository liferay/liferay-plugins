/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.wikinavigation.util;

import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.wiki.model.WikiNode;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.service.WikiPageServiceUtil;

import java.io.Serializable;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.PortletURL;

/**
 * <a href="MenuItem.java.html"><b><i>View Source</i></b></a>
 *
 * @author Thiago Moreira
 * @author Peter Shin
 */
public class MenuItem implements Serializable {

	public static List<MenuItem> fromWikiPage(
		WikiPage wikiPage, PortletURL portletURL) {

		return _fromWikiPage(wikiPage, portletURL);
	}

	public static List<MenuItem> fromWikiNode(
		long nodeId, int depth, PortletURL portletURL) {

		List<WikiPage> wikiPages = null;

		try {
			wikiPages = WikiPageServiceUtil.getNodePages(
				nodeId, WikiNavigationConstants.MAX_PAGES);
		}
		catch (Exception e) {
			return new LinkedList<MenuItem>();
		}

		return _fromWikiNode(wikiPages, 1, depth, portletURL);
	}

	public MenuItem() {
		_children = new LinkedList<MenuItem>();
	}

	public void addChild(MenuItem child) {
		_children.add(child);
	}

	public void addChildren(List<MenuItem> children) {
		_children.addAll(children);
	}

	public List<MenuItem> getChildren() {
		return _children;
	}

	public boolean getExternalURL() {
		return _externalURL;
	}

	public String getLabel() {
		return _label;
	}

	public String getURL() {
		return _url;
	}

	public void setChildren(List<MenuItem> children) {
		_children = children;
	}

	public void setExternalURL(boolean externalURL) {
		_externalURL = externalURL;
	}

	public void setLabel(String label) {
		_label = label;
	}

	public void setURL(String url) {
		_url = url;
	}

	private static List<MenuItem> _fromWikiNode(
		List<WikiPage> wikiPages, int curDepth, int depth,
		PortletURL portletURL) {

		List<MenuItem> menuItems = new LinkedList<MenuItem>();

		for (WikiPage wikiPage : wikiPages) {
			if ((wikiPage.getParentPage() != null) && (curDepth == 1)) {
				continue;
			}

			String title = wikiPage.getTitle();
			WikiNode wikiNode = wikiPage.getNode();

			portletURL.setParameter(
				"nodeId", String.valueOf(wikiNode.getNodeId()));
			portletURL.setParameter("title", title);

			MenuItem menuItem = new MenuItem();

			menuItem.setLabel(title);
			menuItem.setURL(portletURL.toString());

			if ((depth >= curDepth) ||
				(depth == WikiNavigationConstants.DEPTH_ALL)) {

				List<MenuItem> children = _fromWikiNode(
					wikiPage.getChildPages(), curDepth + 1, depth, portletURL);

				menuItem.addChildren(children);
			}

			menuItems.add(menuItem);
		}

		return menuItems;
	}

	public static List<MenuItem> _fromWikiPage(
		WikiPage wikiPage, PortletURL portletURL) {

		List<MenuItem> menuItems = new LinkedList<MenuItem>();

		Pattern pattern = Pattern.compile(
			"((==\\s((.)*)\\s==)*(\\Q[[\\E((.)*)\\Q]]\\E)*)*");

		Matcher matcher =  pattern.matcher(wikiPage.getContent());

		MenuItem menuItem = null;

		while (matcher.find()) {
			String title = matcher.group(3);

			if (title != null) {
				menuItem = new MenuItem();

				menuItem.setLabel(title);

				menuItems.add(menuItem);
			}

			if (menuItem == null) {
				menuItem = new MenuItem();

				menuItem.setLabel(StringPool.BLANK);

				menuItems.add(menuItem);
			}

			String s = matcher.group(6);

			if (s != null) {
				MenuItem childMenuItem = new MenuItem();

				int index = s.indexOf(StringPool.PIPE);

				String label = null;
				String url = null;

				if (index != -1) {
					label = s.substring(index + 1, s.length());
					url = s.substring(0, index);

					if (!url.startsWith(Http.HTTP)) {
						long nodeId = wikiPage.getNodeId();

						portletURL.setParameter("title", url);
						portletURL.setParameter(
							"nodeId", String.valueOf(nodeId));

						url = portletURL.toString();
					}
					else {
						childMenuItem.setExternalURL(true);
					}
				}
				else {
					label = s;
					url = s;

					if (!url.startsWith(Http.HTTP)) {
						long nodeId = wikiPage.getNodeId();

						portletURL.setParameter("title", url);
						portletURL.setParameter(
							"nodeId", String.valueOf(nodeId));

						url = portletURL.toString();
					}
				}

				childMenuItem.setLabel(label);
				childMenuItem.setURL(url);

				menuItem.addChild(childMenuItem);
			}
		}

		return menuItems;
	}

	private List<MenuItem> _children;
	private boolean _externalURL;
	private String _label;
	private String _url;

}