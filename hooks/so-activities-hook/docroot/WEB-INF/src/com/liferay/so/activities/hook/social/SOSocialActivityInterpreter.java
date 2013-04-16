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

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.asset.AssetRendererFactoryRegistryUtil;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.AssetRendererFactory;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivitySet;

import java.text.Format;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Brian Wing Shun Chan
 * @author Ryan Park
 * @author Matthew Kong
 */
public abstract class SOSocialActivityInterpreter
	extends BaseSocialActivityInterpreter {

	@Override
	public String getSelector() {
		return _SELECTOR;
	}

	protected AssetRenderer getAssetRenderer(SocialActivity activity)
		throws Exception {

		AssetRendererFactory assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
				activity.getClassName());

		return assetRendererFactory.getAssetRenderer(activity.getClassPK());
	}

	protected Format getFormatDateTime(Locale locale, TimeZone timezone) {
		return FastDateFormatFactoryUtil.getSimpleDateFormat(
			"EEEE, MMMMM dd, yyyy 'at' h:mm a", locale, timezone);
	}

	protected String getLinkURL(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		AssetRenderer assetRenderer = getAssetRenderer(activity);

		return assetRenderer.getURLViewInContext(
			serviceContext.getLiferayPortletRequest(),
			serviceContext.getLiferayPortletResponse(), null);
	}

	protected String getRelativeTimeDescription(
		long milliseconds, Locale locale, TimeZone timeZone) {

		Format timeFormat = FastDateFormatFactoryUtil.getTime(locale, timeZone);

		int daysBetween = DateUtil.getDaysBetween(
			new Date(milliseconds), new Date(), timeZone);

		long millisAgo = System.currentTimeMillis() - milliseconds;

		if (millisAgo <= Time.MINUTE) {
			return "about-a-minute-ago";
		}
		else if (millisAgo < Time.HOUR) {
			return LanguageUtil.format(
				locale, "x-minutes-ago", (millisAgo / Time.MINUTE));
		}
		else if ((millisAgo / Time.HOUR) == 1) {
			return "about-an-hour-ago";
		}
		else if ((millisAgo < Time.DAY) || (daysBetween == 0)) {
			return LanguageUtil.format(
				locale, "x-hours-ago", (millisAgo / Time.HOUR));
		}
		else if (daysBetween == 1) {
			return LanguageUtil.format(
				locale, "yesterday-at-x", timeFormat.format(milliseconds));
		}

		Format dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"EEEE, MMMMM dd, yyyy", locale, timeZone);

		return dateFormat.format(milliseconds);
	}

	@Override
	protected String getTitle(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		StringBundler sb = new StringBundler(10);

		sb.append("<div class=\"activity-header\">");
		sb.append("<div class=\"activity-time\" title=\"");

		Format dateFormatDate = getFormatDateTime(
			serviceContext.getLocale(), serviceContext.getTimeZone());

		Date activityDate = new Date(activity.getCreateDate());

		sb.append(dateFormatDate.format(activityDate));

		sb.append("\">");

		String relativeTimeDescription = getRelativeTimeDescription(
			activity.getCreateDate(), serviceContext.getLocale(),
			serviceContext.getTimeZone());

		sb.append(relativeTimeDescription);

		sb.append("</div><div class=\"activity-user-name\">");

		String userName = getUserName(activity.getUserId(), serviceContext);

		if (activity.getGroupId() != serviceContext.getScopeGroupId()) {
			String groupName = getGroupName(
				activity.getGroupId(), serviceContext);

			Object[] userArguments = new Object[] {userName, groupName};

			sb.append(serviceContext.translate("x-in-x", userArguments));
		}
		else {
			sb.append(userName);
		}

		sb.append("</div></div><div class=\"activity-action\">");

		String titlePattern = getTitlePattern(null, activity);

		Object[] titleArguments = getTitleArguments(
			null, activity, null, null, serviceContext);

		sb.append(serviceContext.translate(titlePattern, titleArguments));

		sb.append("</div>");

		return sb.toString();
	}

	@Override
	protected Object[] getTitleArguments(
			String groupName, SocialActivity socialActivity, String link,
			String title, ServiceContext serviceContext)
		throws Exception {

		return null;
	}

	@Override
	protected boolean hasPermissions(
			PermissionChecker permissionChecker, SocialActivity activity,
			String actionId, ServiceContext serviceContext)
		throws Exception {

		return permissionChecker.hasPermission(
			activity.getGroupId(), activity.getClassName(),
			activity.getClassPK(), ActionKeys.VIEW);
	}

	protected boolean isExpired(SocialActivitySet activitySet) {
		long age = System.currentTimeMillis() - activitySet.getCreateDate();

		if (age > (Time.HOUR * 8)) {
			return true;
		}

		return false;
	}

	protected String wrapLink(String link, String iconPath, String text) {
		StringBundler sb = new StringBundler(5);

		sb.append("<span><img class=\"icon\" src=\"");
		sb.append(iconPath);
		sb.append("\"></span><span>");
		sb.append(text);
		sb.append("</span>");

		return wrapLink(link, sb.toString());
	}

	private static final String _SELECTOR = "SO";

}