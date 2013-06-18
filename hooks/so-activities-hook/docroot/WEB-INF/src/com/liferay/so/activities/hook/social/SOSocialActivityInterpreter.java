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

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.asset.AssetRendererFactoryRegistryUtil;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.AssetRendererFactory;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.portlet.social.model.SocialActivitySet;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

import java.text.Format;

import java.util.Date;
import java.util.List;
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

	@Override
	protected SocialActivityFeedEntry doInterpret(
			SocialActivitySet activitySet, ServiceContext serviceContext)
		throws Exception {

		if (activitySet.getActivityCount() == 1) {
			List<SocialActivity> activities =
				SocialActivityLocalServiceUtil.getActivitySetActivities(
					activitySet.getActivitySetId(), 0, 1);

			if (!activities.isEmpty()) {
				SocialActivity activity = activities.get(0);

				return doInterpret(activity, serviceContext);
			}
		}

		String link = getLink(activitySet, serviceContext);

		String title = getTitle(activitySet, serviceContext);

		if (Validator.isNull(title)) {
			return null;
		}

		String body = getBody(activitySet, serviceContext);

		if (Validator.isNull(body)) {
			return null;
		}

		return new SocialActivityFeedEntry(link, title, body);
	}

	protected AssetRenderer getAssetRenderer(String className, long classPK)
		throws Exception {

		AssetRendererFactory assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
				className);

		return assetRendererFactory.getAssetRenderer(classPK);
	}

	protected String getBody(
			SocialActivitySet activitySet, ServiceContext serviceContext)
		throws Exception {

		StringBundler sb = new StringBundler();

		sb.append("<div class=\"activity-body\">");

		int viewableActivities = 0;

		List<SocialActivity> activities =
			SocialActivityLocalServiceUtil.getActivitySetActivities(
				activitySet.getActivitySetId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		for (SocialActivity activity : activities) {
			ThemeDisplay themeDisplay = serviceContext.getThemeDisplay();

			PermissionChecker permissionChecker =
				themeDisplay.getPermissionChecker();

			if (!hasPermissions(
					permissionChecker, activity, ActionKeys.VIEW,
					serviceContext)) {

				continue;
			}

			SocialActivityFeedEntry subfeedEntry = getSubfeedEntry(
				activity, serviceContext);

			if (subfeedEntry == null) {
				continue;
			}

			sb.append("<div class=\"activity-subentry\">");
			sb.append("<span class=\"activity-subentry-title\">");
			sb.append(subfeedEntry.getTitle());
			sb.append("</span><span class=\"activity-subentry-body\">");
			sb.append(subfeedEntry.getBody());
			sb.append("</span></div>");

			viewableActivities++;
		}

		if (viewableActivities == 0) {
			return null;
		}

		sb.append("</div>");

		return sb.toString();
	}

	protected Format getFormatDateTime(Locale locale, TimeZone timezone) {
		return FastDateFormatFactoryUtil.getSimpleDateFormat(
			"EEEE, MMMMM dd, yyyy 'at' h:mm a", locale, timezone);
	}

	protected String getLink(
			SocialActivitySet activitySet, ServiceContext serviceContext)
		throws Exception {

		return null;
	}

	protected String getLinkURL(
			String className, long classPK, ServiceContext serviceContext)
		throws Exception {

		AssetRenderer assetRenderer = getAssetRenderer(className, classPK);

		return assetRenderer.getURLViewInContext(
			serviceContext.getLiferayPortletRequest(),
			serviceContext.getLiferayPortletResponse(), null);
	}

	protected String getPageTitle(
			String className, long classPK, ServiceContext serviceContext)
		throws Exception {

		String linkURL = getLinkURL(className, classPK, serviceContext);

		AssetRenderer assetRenderer = getAssetRenderer(className, classPK);

		LiferayPortletRequest liferayPortletRequest =
			serviceContext.getLiferayPortletRequest();

		if (Validator.isNotNull(
				assetRenderer.getIconPath(liferayPortletRequest))) {

			return wrapLink(
				linkURL, assetRenderer.getIconPath(liferayPortletRequest),
				HtmlUtil.escape(
					assetRenderer.getTitle(serviceContext.getLocale())));
		}

		return wrapLink(
			linkURL,
			HtmlUtil.escape(
				assetRenderer.getTitle(serviceContext.getLocale())));
	}

	protected SocialActivityFeedEntry getSubfeedEntry(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		String title = getPageTitle(
			activity.getClassName(), activity.getClassPK(), serviceContext);

		AssetRenderer assetRenderer = getAssetRenderer(
			activity.getClassName(), activity.getClassPK());

		String body = StringUtil.shorten(
			assetRenderer.getSummary(serviceContext.getLocale()), 200);

		return new SocialActivityFeedEntry(title, body);
	}

	protected String getTitle(
			long groupId, long userId, long createDate,
			ServiceContext serviceContext)
		throws Exception {

		StringBundler sb = new StringBundler(8);

		sb.append("<div class=\"activity-header\">");
		sb.append("<div class=\"activity-time\" title=\"");

		Format dateFormatDate = getFormatDateTime(
			serviceContext.getLocale(), serviceContext.getTimeZone());

		Date activityDate = new Date(createDate);

		sb.append(dateFormatDate.format(activityDate));

		sb.append("\">");

		String relativeTimeDescription = Time.getRelativeTimeDescription(
			createDate, serviceContext.getLocale(),
			serviceContext.getTimeZone());

		sb.append(relativeTimeDescription);

		sb.append("</div><div class=\"activity-user-name\">");

		String userName = getUserName(userId, serviceContext);

		if (groupId != serviceContext.getScopeGroupId()) {
			String groupName = getGroupName(groupId, serviceContext);

			Object[] userArguments = new Object[] {userName, groupName};

			sb.append(serviceContext.translate("x-in-x", userArguments));
		}
		else {
			sb.append(userName);
		}

		sb.append("</div></div>");

		return sb.toString();
	}

	@Override
	protected String getTitle(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		StringBundler sb = new StringBundler(4);

		sb.append(
			getTitle(
				activity.getGroupId(), activity.getUserId(),
				activity.getCreateDate(), serviceContext));
		sb.append("<div class=\"activity-action\">");

		String titlePattern = getTitlePattern(null, activity);

		Object[] titleArguments = getTitleArguments(
			null, activity, null, null, serviceContext);

		sb.append(serviceContext.translate(titlePattern, titleArguments));

		sb.append("</div>");

		return sb.toString();
	}

	protected String getTitle(
			SocialActivitySet activitySet, ServiceContext serviceContext)
		throws Exception {

		StringBundler sb = new StringBundler(4);

		sb.append(
			getTitle(
				activitySet.getGroupId(), activitySet.getUserId(),
				activitySet.getCreateDate(), serviceContext));
		sb.append("<div class=\"activity-action\">");

		String titlePattern = getTitlePattern(null, activitySet);

		Object[] titleArguments = getTitleArguments(
			null, activitySet, null, null, serviceContext);

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

	protected Object[] getTitleArguments(
			String groupName, SocialActivitySet activitySet, String link,
			String title, ServiceContext serviceContext)
		throws Exception {

		return new Object[] {activitySet.getActivityCount()};
	}

	protected String getTitlePattern(
			String groupName, SocialActivitySet activitySet)
		throws Exception {

		return StringPool.BLANK;
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

		sb.append("<a href=\"");
		sb.append(link);
		sb.append("\">");
		sb.append("<span><img class=\"icon\" src=\"");
		sb.append(iconPath);
		sb.append("\"></span><span>");
		sb.append(text);
		sb.append("</span>");
		sb.append("</a>");

		return sb.toString();
	}

	private static final String _SELECTOR = "SO";

}