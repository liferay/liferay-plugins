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

package com.liferay.so.portlet.calendar.social;

import com.liferay.compat.portal.service.ServiceContext;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.calendar.model.CalEvent;
import com.liferay.portlet.calendar.service.CalEventLocalServiceUtil;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.so.activities.model.BaseSocialActivityInterpreter;
import com.liferay.so.util.Time;

import java.text.Format;

import java.util.Date;

/**
 * @author Evan Thibodeau
 */
public class CalendarActivityInterpreter extends BaseSocialActivityInterpreter {

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

		Format dateFormatDate = getFormatDateTime(
			serviceContext.getLocale(), serviceContext.getTimeZone());

		CalEvent event = CalEventLocalServiceUtil.getEvent(
			activity.getClassPK());

		StringBundler sb = new StringBundler(15);

		sb.append("<div class=\"activity-body\"><div class=\"title\">");
		sb.append(pageTitle);
		sb.append("</div><div class=\"date\"><strong>");
		sb.append(serviceContext.translate("date"));
		sb.append(": </strong>");
		sb.append(dateFormatDate.format((event.getStartDate())));
		sb.append("</div><div class=\"location\"><strong>");
		sb.append(serviceContext.translate("location"));
		sb.append(": </strong>");
		sb.append(event.getLocation());
		sb.append("</div><div class=\"description\"><strong>");
		sb.append(serviceContext.translate("description"));
		sb.append(": </strong>");
		sb.append(
			StringUtil.shorten(
				assetRenderer.getSummary(serviceContext.getLocale()), 200));
		sb.append("</div></div>");

		return sb.toString();
	}

	@Override
	protected String getLink(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		return wrapLink(
			getLinkURL(activity, serviceContext),
			serviceContext.translate("view-calendar"));
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

		if (activityType == _ADD_EVENT) {
			actionPattern = "added-a-new-calendar-event";
		}
		else if (activityType == _UPDATE_EVENT) {
			actionPattern = "updated-a-calendar-event";
		}

		sb.append(serviceContext.translate(actionPattern));
		sb.append("</div>");

		return sb.toString();
	}

	private static final int _ADD_EVENT = 1;

	private static final String[] _CLASS_NAMES = new String[] {
		CalEvent.class.getName()
	};

	private static final int _UPDATE_EVENT = 2;

}