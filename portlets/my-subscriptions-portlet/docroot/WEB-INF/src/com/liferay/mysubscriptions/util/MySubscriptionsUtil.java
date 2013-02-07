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

package com.liferay.mysubscriptions.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.Portal;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.asset.AssetRendererFactoryRegistryUtil;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.AssetRendererFactory;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.bookmarks.model.BookmarksFolder;
import com.liferay.portlet.bookmarks.service.BookmarksFolderLocalServiceUtil;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.MBThread;
import com.liferay.portlet.messageboards.service.MBThreadLocalServiceUtil;
import com.liferay.portlet.wiki.model.WikiNode;
import com.liferay.portlet.wiki.service.WikiNodeLocalServiceUtil;

import java.util.Locale;

/**
 * @author Peter Shin
 * @author Jonathan Lee
 */
public class MySubscriptionsUtil {

	public static AssetRenderer getAssetRenderer(
		String className, long classPK) {

		try {
			return doGetAssetRenderer(className, classPK);
		}
		catch (Exception e) {
		}

		return null;
	}

	public static String getAssetURLViewInContext(
			ThemeDisplay themeDisplay, String className, long classPK)
		throws PortalException, SystemException {

		if (className.equals(BlogsEntry.class.getName())) {
			return PortalUtil.getLayoutFullURL(classPK, PortletKeys.BLOGS);
		}

		if (className.equals(Layout.class.getName())) {
			return PortalUtil.getLayoutFullURL(
				LayoutLocalServiceUtil.getLayout(classPK), themeDisplay);
		}

		if (className.equals(MBCategory.class.getName())) {
			return PortalUtil.getLayoutFullURL(
				classPK, PortletKeys.MESSAGE_BOARDS);
		}

		if (className.equals(WikiNode.class.getName())) {
			long wikiPlid;

			wikiPlid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), PortletKeys.WIKI);
			if (wikiPlid == 0) {
				wikiPlid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), PortletKeys.WIKI_DISPLAY);
			}
			Layout wikiLayout = null;
			if (wikiPlid != 0) {
				wikiLayout = LayoutLocalServiceUtil.getLayout(wikiPlid);

				String layoutFullURL = PortalUtil.getLayoutFullURL(wikiLayout, themeDisplay);

				StringBuilder wikiNodeURL = new StringBuilder();

				wikiNodeURL.append(layoutFullURL);
				wikiNodeURL.append(Portal.FRIENDLY_URL_SEPARATOR);
				wikiNodeURL.append("wiki");
				wikiNodeURL.append(StringPool.SLASH);
				wikiNodeURL.append(classPK);
				wikiNodeURL.append(StringPool.SLASH);
				wikiNodeURL.append("all_pages");

				return wikiNodeURL.toString();
			}
			else {
				return "";
			}
		}

		return null;
	}

	public static String getTitleText(
			Locale locale, String className, long classPK, String title)
		throws PortalException, SystemException {

		if (Validator.isNotNull(title)) {
			return title;
		}

		if (className.equals(BookmarksFolder.class.getName())) {
			return BookmarksFolderLocalServiceUtil.getBookmarksFolder(classPK).getName();
		}
		else if (className.equals(BlogsEntry.class.getName())) {
			title = "Blog at ";
		}
		else if (className.equals(Layout.class.getName())) {
			return LayoutLocalServiceUtil.getLayout(classPK).getName(locale);
		}
		else if (className.equals(MBCategory.class.getName())) {
			title = "Message Board at ";
		}
		else if (className.equals(WikiNode.class.getName())) {
			return WikiNodeLocalServiceUtil.getWikiNode(classPK).getName();
		}

		try {
			Group group = GroupLocalServiceUtil.getGroup(classPK);

			title += group.getDescriptiveName(locale);
		}
		catch (Exception e) {
		}

		if (Validator.isNull(title)) {
			title = String.valueOf(classPK);
		}

		return title;
	}

	protected static AssetRenderer doGetAssetRenderer(
			String className, long classPK)
		throws Exception {

		if (className.equals(MBThread.class.getName())) {
			className = MBMessage.class.getName();

			MBThread mbThread = MBThreadLocalServiceUtil.getThread(classPK);

			classPK = mbThread.getRootMessageId();
		}

		AssetRendererFactory assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
				className);

		return assetRendererFactory.getAssetRenderer(classPK);
	}

}