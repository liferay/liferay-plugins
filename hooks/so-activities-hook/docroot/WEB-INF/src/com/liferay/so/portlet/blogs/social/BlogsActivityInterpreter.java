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

package com.liferay.so.portlet.blogs.social;

import com.liferay.compat.portal.service.ServiceContext;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityConstants;
import com.liferay.so.activities.model.BaseSocialActivityInterpreter;
import com.liferay.so.util.Time;

import java.text.Format;

import java.util.Date;

/**
 * @author Evan Thibodeau
 */
public class BlogsActivityInterpreter extends BaseSocialActivityInterpreter {

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected String getBody(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		AssetRenderer assetRenderer = getAssetRenderer(activity);

		String pageTitle = wrapLink(
			getLinkURL(activity, serviceContext),
			HtmlUtil.escape(
				assetRenderer.getTitle(serviceContext.getLocale())));

		LiferayPortletRequest liferayPortletRequest =
			serviceContext.getLiferayPortletRequest();

		if (Validator.isNotNull(
				assetRenderer.getIconPath(liferayPortletRequest))) {

			pageTitle = wrapLink(
				getLinkURL(activity, serviceContext),
				assetRenderer.getIconPath(liferayPortletRequest),
				HtmlUtil.escape(
					assetRenderer.getTitle(serviceContext.getLocale())));
		}

		BlogsEntry entry = BlogsEntryLocalServiceUtil.getEntry(
			activity.getClassPK());

		String content = HtmlUtil.extractText(entry.getContent());

		StringBundler sb = new StringBundler(5);

		sb.append("<div class=\"activity-body\"><div class=\"title\">");
		sb.append(pageTitle);
		sb.append("</div><div class='blogs-page-content'>");
		sb.append(StringUtil.shorten(content, 200));
		sb.append("</div></div>");

		return sb.toString();
	}

	@Override
	protected String getLink(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		return wrapLink(
			getLinkURL(activity, serviceContext),
			serviceContext.translate("view-blog"));
	}

	@Override
	protected String getTitle(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		String userName = getUserName(activity.getUserId(), serviceContext);

		Format dateFormatDate = getFormatDateTime(
			serviceContext.getLocale(), serviceContext.getTimeZone());

		StringBundler sb = new StringBundler(10);

		sb.append("<div class=\"activity-header\">");
		sb.append("<div class=\"activity-time\" title=\"");
		sb.append(dateFormatDate.format(new Date(activity.getCreateDate())));
		sb.append("\">");
		sb.append(
			Time.getRelativeTimeSpan(
				activity.getCreateDate(), serviceContext.getLocale(),
				serviceContext.getTimeZone()));
		sb.append("</div><div class=\"activity-user-name\">");

		if (activity.getGroupId() != serviceContext.getScopeGroupId()) {
			String groupName = getGroupName(
				activity.getGroupId(), serviceContext);

			Object[] titleArguments = new Object[] {userName, groupName};

			sb.append(serviceContext.translate("x-in-x", titleArguments));
		}
		else {
			sb.append(userName);
		}

		sb.append("</div></div><div class=\"activity-action\">");

		int activityType = activity.getType();

		String actionPattern = null;

		if ((activityType == _ADD_COMMENT) ||
			(activityType == SocialActivityConstants.TYPE_ADD_COMMENT)) {

			actionPattern = "commented-on-a-blog-entry";
		}
		else if (activityType == _ADD_ENTRY) {
			actionPattern = "wrote-a-new-blog-entry";
		}
		else if (activityType == _UPDATE_ENTRY) {
			actionPattern = "updated-a-blog-entry";
		}

		sb.append(serviceContext.translate(actionPattern));
		sb.append("</div>");

		return sb.toString();
	}

	private static final int _ADD_COMMENT = 1;

	private static final int _ADD_ENTRY = 2;

	private static final String[] _CLASS_NAMES = new String[] {
		BlogsEntry.class.getName()
	};

	private static final int _UPDATE_ENTRY = 3;

}