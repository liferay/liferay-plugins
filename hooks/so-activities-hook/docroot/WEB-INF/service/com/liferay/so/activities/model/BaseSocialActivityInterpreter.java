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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.asset.AssetRendererFactoryRegistryUtil;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.AssetRendererFactory;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.so.activities.service.SocialActivityLocalServiceUtil;
import com.liferay.so.activities.util.SocialActivitiesConstants;

import java.text.Format;

import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Brian Wing Shun Chan
 * @author Ryan Park
 * @author Matthew Kong
 */
public abstract class BaseSocialActivityInterpreter
	extends com.liferay.compat.portlet.social.model.
				BaseSocialActivityInterpreter
	implements SocialActivityInterpreter {

	@Override
	public String getSelector() {
		return SocialActivitiesConstants.SO_ACTIVITY_INTERPRETER_SELECTOR;
	}

	public SocialActivityFeedEntry interpret(
		SocialActivitySet activitySet,
		com.liferay.portal.service.ServiceContext serviceContext) {

		try {
			return doInterpret(activitySet, new ServiceContext(serviceContext));
		}
		catch (Exception e) {
			_log.error("Unable to interpret activity", e);
		}

		return null;
	}

	protected SocialActivityFeedEntry doInterpret(
		SocialActivitySet activitySet, ServiceContext serviceContext) {

		try {
			List<SocialActivity> activities =
				SocialActivityLocalServiceUtil.getActivitySetActivities(
					activitySet.getActivitySetId(), 0, 1);

			if (!activities.isEmpty()) {
				SocialActivity activity = activities.get(0);

				return doInterpret(
					activity.getPortalSocialActivity(), serviceContext);
			}
		}
		catch (Exception e) {
			_log.error("Unable to interpret activity set", e);
		}

		return null;
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
			_DATE_TIME_FORMAT, locale, timezone);
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

	private static final String _DATE_TIME_FORMAT =
		"EEEE, MMMMM dd, yyyy 'at' h:mm a";

	private static Log _log = LogFactoryUtil.getLog(
		BaseSocialActivityInterpreter.class);

}