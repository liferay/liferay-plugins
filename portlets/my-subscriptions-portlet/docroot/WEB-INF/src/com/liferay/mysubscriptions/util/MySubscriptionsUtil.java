/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.Portal;
import com.liferay.portal.util.PortalUtil;
import com.liferay.knowledgebase.util.PortletKeys;
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

		if (className.equals(_KNOWLEDGE_BASE_MODEL_CLASSNAME)) {
			return PortalUtil.getLayoutFullURL(
				classPK, PortletKeys.KNOWLEDGE_BASE_DISPLAY);
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
			long plid = PortalUtil.getPlidFromPortletId(
				themeDisplay.getScopeGroupId(), PortletKeys.WIKI);

			if (plid == 0) {
				return null;
			}

			StringBundler sb = new StringBundler(5);

			Layout layout = LayoutLocalServiceUtil.getLayout(plid);

			String layoutFullURL = PortalUtil.getLayoutFullURL(
				layout, themeDisplay);

			sb.append(layoutFullURL);

			sb.append(Portal.FRIENDLY_URL_SEPARATOR);
			sb.append("wiki/");
			sb.append(classPK);
			sb.append("/all_pages");

			return sb.toString();
		}

		return null;
	}

	public static String getTitleText(
			Locale locale, String className, long classPK, String title)
		throws PortalException, SystemException {

		if (Validator.isNotNull(title)) {
			return title;
		}

		if (className.equals(BlogsEntry.class.getName())) {
			title = "Blog at ";
		}
		else if (className.equals(BookmarksFolder.class.getName())) {
			BookmarksFolder bookmarksFolder =
				BookmarksFolderLocalServiceUtil.getBookmarksFolder(classPK);

			return bookmarksFolder.getName();
		}
		else if (className.equals(_KNOWLEDGE_BASE_MODEL_CLASSNAME)) {
			title = "Knowledge Base Article at ";
		}
		else if (className.equals(Layout.class.getName())) {
			Layout layout = LayoutLocalServiceUtil.getLayout(classPK);

			return layout.getName(locale);
		}
		else if (className.equals(MBCategory.class.getName())) {
			title = "Message Board at ";
		}
		else if (className.equals(WikiNode.class.getName())) {
			WikiNode wikiNode = WikiNodeLocalServiceUtil.getWikiNode(classPK);

			return wikiNode.getName();
		}

		Group group = GroupLocalServiceUtil.fetchGroup(classPK);

		if (group != null) {
			title += group.getDescriptiveName(locale);
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

	private static final String _KNOWLEDGE_BASE_MODEL_CLASSNAME =
		"com.liferay.knowledgebase.model.KBArticle";

}