/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.bookmarks.model.BookmarksEntry;
import com.liferay.bookmarks.service.BookmarksEntryLocalServiceUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.so.activities.util.SocialActivityKeyConstants;
import com.liferay.social.kernel.model.SocialActivity;
import com.liferay.social.kernel.model.SocialActivityFeedEntry;
import com.liferay.social.kernel.model.SocialActivitySet;
import com.liferay.social.kernel.service.SocialActivityLocalServiceUtil;
import com.liferay.social.kernel.service.SocialActivitySetLocalServiceUtil;

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
			SocialActivitySet activitySet = null;

			SocialActivity activity =
				SocialActivityLocalServiceUtil.getActivity(activityId);

			if (activity.getType() ==
					SocialActivityKeyConstants.BOOKMARKS_UPDATE_ENTRY) {

				activitySet =
					SocialActivitySetLocalServiceUtil.getClassActivitySet(
						activity.getUserId(), activity.getClassNameId(),
						activity.getClassPK(), activity.getType());
			}

			if ((activitySet != null) && !isExpired(activitySet, false)) {
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

		if (activitySet.getType() ==
				SocialActivityKeyConstants.BOOKMARKS_UPDATE_ENTRY) {

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

		sb.append(HtmlUtil.stripHtml(entry.getDescription()));

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
				entry.getUrl(),
				assetRenderer.getIconPath(liferayPortletRequest),
				HtmlUtil.escape(
					assetRenderer.getTitle(serviceContext.getLocale())));
		}

		return wrapLink(entry.getUrl(), entry.getName());
	}

	@Override
	protected SocialActivityFeedEntry getSubfeedEntry(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		String title = getBookmarkLink(
			activity.getClassName(), activity.getClassPK(), serviceContext);

		AssetRenderer assetRenderer = getAssetRenderer(
			activity.getClassName(), activity.getClassPK());

		String body = StringUtil.shorten(
			HtmlUtil.escape(assetRenderer.getSummary()), 200);

		return new SocialActivityFeedEntry(title, body);
	}

	@Override
	protected String getTitlePattern(
		String groupName, SocialActivity activity) {

		if (activity.getType() ==
				SocialActivityKeyConstants.BOOKMARKS_ADD_ENTRY) {

			return "added-a-new-bookmark";
		}
		else if (activity.getType() ==
					SocialActivityKeyConstants.BOOKMARKS_UPDATE_ENTRY) {

			return "updated-a-bookmark";
		}

		return StringPool.BLANK;
	}

	@Override
	protected String getTitlePattern(
		String groupName, SocialActivitySet activitySet) {

		if (activitySet.getType() ==
				SocialActivityKeyConstants.BOOKMARKS_ADD_ENTRY) {

			return "added-x-new-bookmarks";
		}
		else if (activitySet.getType() ==
					SocialActivityKeyConstants.BOOKMARKS_UPDATE_ENTRY) {

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

	private static final String[] _CLASS_NAMES =
		{BookmarksEntry.class.getName()};

}