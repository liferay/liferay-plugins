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

package com.liferay.so.activities.model;

import com.liferay.compat.portal.service.ServiceContext;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.asset.AssetRendererFactoryRegistryUtil;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.AssetRendererFactory;
import com.liferay.so.activities.util.SocialActivitiesConstants;
import com.liferay.so.activities.util.Time;

import java.text.Format;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Brian Wing Shun Chan
 * @author Ryan Park
 * @author Matthew Kong
 */
public abstract class SOBaseSocialActivityInterpreter
	extends BaseSocialActivityInterpreter {

	@Override
	public String getSelector() {
		return SocialActivitiesConstants.SO_ACTIVITY_INTERPRETER_SELECTOR;
	}

	protected AssetRenderer getAssetRenderer(
			com.liferay.portlet.social.model.SocialActivity activity)
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
			com.liferay.portlet.social.model.SocialActivity activity,
			ServiceContext serviceContext)
		throws Exception {

		AssetRenderer assetRenderer = getAssetRenderer(activity);

		return assetRenderer.getURLViewInContext(
			serviceContext.getLiferayPortletRequest(),
			serviceContext.getLiferayPortletResponse(), null);
	}

	@Override
	protected String getTitle(
			com.liferay.portlet.social.model.SocialActivity activity,
			ServiceContext serviceContext)
		throws Exception {

		StringBundler sb = new StringBundler(10);

		sb.append("<div class=\"activity-header\">");
		sb.append("<div class=\"activity-time\" title=\"");

		Format dateFormatDate = getFormatDateTime(
			serviceContext.getLocale(), serviceContext.getTimeZone());

		Date activityDate = new Date(activity.getCreateDate());

		sb.append(dateFormatDate.format(activityDate));

		sb.append("\">");

		String relativeTimeSpan = Time.getRelativeTimeSpan(
			activity.getCreateDate(), serviceContext.getLocale(),
			serviceContext.getTimeZone());

		sb.append(relativeTimeSpan);

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
			String groupName,
			com.liferay.portlet.social.model.SocialActivity socialActivity,
			String link, String title, ServiceContext serviceContext)
		throws Exception {

		return null;
	}

	@Override
	protected boolean hasPermissions(
			PermissionChecker permissionChecker,
			com.liferay.portlet.social.model.SocialActivity activity,
			String actionId, ServiceContext serviceContext)
		throws Exception {

		return permissionChecker.hasPermission(
			activity.getGroupId(), activity.getClassName(),
			activity.getClassPK(), ActionKeys.VIEW);
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

}