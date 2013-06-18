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

package com.liferay.so.activities.hook.social;

import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.bookmarks.model.BookmarksEntry;
import com.liferay.portlet.bookmarks.service.BookmarksEntryLocalServiceUtil;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivitySet;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import com.liferay.portlet.social.service.SocialActivitySetLocalServiceUtil;

import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Evan Thibodeau
 * @author Matthew Kong
 */
public class BookmarksActivityInterpreter extends SOSocialActivityInterpreter {

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected long getActivitySetId(long activityId) {
		try {
			SocialActivity activity =
				SocialActivityLocalServiceUtil.getActivity(activityId);

			SocialActivitySet activitySet = null;

			if (activity.getType() == _ACTIVITY_KEY_ADD_ENTRY) {
				activitySet =
					SocialActivitySetLocalServiceUtil.getUserActivitySet(
						activity.getGroupId(), activity.getUserId(),
						activity.getClassNameId(), activity.getType());
			}
			else if (activity.getType() == _ACTIVITY_KEY_UPDATE_ENTRY) {
				activitySet =
					SocialActivitySetLocalServiceUtil.getClassActivitySet(
						activity.getUserId(), activity.getClassNameId(),
						activity.getClassPK(), activity.getType());
			}

			if ((activitySet != null) && !isExpired(activitySet)) {
				return activitySet.getActivitySetId();
			}
		}
		catch (Exception e) {
		}

		return 0;
	}

	@Override
	protected String getBody(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		return getBody(
			activity.getClassName(), activity.getClassPK(), serviceContext);
	}

	@Override
	protected String getBody(
			SocialActivitySet activitySet, ServiceContext serviceContext)
		throws Exception {

		if (activitySet.getType() == _ACTIVITY_KEY_UPDATE_ENTRY) {
			return getBody(
				activitySet.getClassName(), activitySet.getClassPK(),
				serviceContext);
		}

		return super.getBody(activitySet, serviceContext);
	}

	protected String getBody(
			String className, long classPK, ServiceContext serviceContext)
		throws Exception {

		StringBundler sb = new StringBundler(5);

		sb.append("<div class=\"activity-body\"><div class=\"title\">");
		sb.append(getBookmarkLink(className, classPK, serviceContext));
		sb.append("</div><div class=\"bookmarks-page-content\">");

		BookmarksEntry entry = BookmarksEntryLocalServiceUtil.getEntry(classPK);

		sb.append(entry.getDescription());

		sb.append("</div></div>");

		return sb.toString();
	}

	protected String getBookmarkLink(
			String className, long classPK, ServiceContext serviceContext)
		throws Exception {

		BookmarksEntry entry = BookmarksEntryLocalServiceUtil.getEntry(classPK);

		String faviconUrl = HttpUtil.getDomain(entry.getUrl()) + "/favicon.ico";

		AssetRenderer assetRenderer = getAssetRenderer(className, classPK);

		LiferayPortletRequest liferayPortletRequest =
			serviceContext.getLiferayPortletRequest();

		if (ping(faviconUrl)) {
			return wrapLink(entry.getUrl(), faviconUrl, entry.getName());
		}
		else if (Validator.isNotNull(
					assetRenderer.getIconPath(liferayPortletRequest))) {

			return wrapLink(
				getLinkURL(className, classPK, serviceContext),
				assetRenderer.getIconPath(liferayPortletRequest),
				HtmlUtil.escape(
					assetRenderer.getTitle(serviceContext.getLocale())));
		}

		return wrapLink(entry.getUrl(), entry.getName());
	}

	@Override
	protected String getTitlePattern(
		String groupName, SocialActivity activity) {

		if (activity.getType() == _ACTIVITY_KEY_ADD_ENTRY) {
			return "added-a-new-bookmark";
		}
		else if (activity.getType() == _ACTIVITY_KEY_UPDATE_ENTRY) {
			return "updated-a-bookmark";
		}

		return StringPool.BLANK;
	}

	@Override
	protected String getTitlePattern(
		String groupName, SocialActivitySet activitySet) {

		if (activitySet.getType() == _ACTIVITY_KEY_ADD_ENTRY) {
			return "added-x-new-bookmarks";
		}
		else if (activitySet.getType() == _ACTIVITY_KEY_UPDATE_ENTRY) {
			return "made-x-updates-to-a-bookmark";
		}

		return StringPool.BLANK;
	}

	protected boolean ping(String urlString) {
		urlString = urlString.replaceFirst("https", "http");

		try {
			URL url = new URL(urlString);

			HttpURLConnection httpURLConnection =
				(HttpURLConnection)url.openConnection();

			httpURLConnection.setConnectTimeout(500);
			httpURLConnection.setReadTimeout(500);
			httpURLConnection.setRequestMethod("HEAD");

			int responseCode = httpURLConnection.getResponseCode();

			if ((responseCode < HttpServletResponse.SC_BAD_REQUEST) &&
				(responseCode >= HttpServletResponse.SC_OK)) {

				return true;
			}
		}
		catch (IOException ioe) {
		}

		return false;
	}

	/**
	 * {@link
	 * com.liferay.portlet.bookmarks.social.BookmarksActivityKeys#ADD_ENTRY}
	 */
	private static final int _ACTIVITY_KEY_ADD_ENTRY = 1;

	/**
	 * {@link
	 * com.liferay.portlet.bookmarks.social.BookmarksActivityKeys#UPDATE_ENTRY}
	 */
	private static final int _ACTIVITY_KEY_UPDATE_ENTRY = 2;

	private static final String[] _CLASS_NAMES =
		{BookmarksEntry.class.getName()};

}