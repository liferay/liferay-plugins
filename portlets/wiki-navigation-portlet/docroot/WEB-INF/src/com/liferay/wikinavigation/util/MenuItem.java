/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

		Matcher matcher = pattern.matcher(wikiPage.getContent());

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