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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.parsers.bbcode.BBCodeTranslatorUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.asset.AssetRendererFactoryRegistryUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.AssetRendererFactory;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.portlet.social.model.SocialActivitySet;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import com.liferay.portlet.social.service.SocialActivitySetLocalServiceUtil;
import com.liferay.portlet.trash.util.TrashUtil;
import com.liferay.so.activities.util.PortletPropsValues;
import com.liferay.wiki.model.WikiPageResource;

import java.text.DateFormat;
import java.text.Format;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		return super.doInterpret(activity, serviceContext);
	}

	@Override
	protected SocialActivityFeedEntry doInterpret(
			SocialActivitySet activitySet, ServiceContext serviceContext)
		throws Exception {

		List<SocialActivity> viewableActivities = getViewableActivities(
			activitySet, serviceContext);

		if (viewableActivities.isEmpty()) {
			return null;
		}

		if (viewableActivities.size() == 1) {
			SocialActivity activity = viewableActivities.get(0);

			return doInterpret(activity, serviceContext);
		}

		String link = getLink(activitySet, serviceContext);

		String title = getTitle(activitySet, serviceContext);

		if (Validator.isNull(title)) {
			return null;
		}

		String body = getBody(activitySet, serviceContext);

		return new SocialActivityFeedEntry(link, title, body);
	}

	protected List<Long> getActivitySetUserIds(long activitySetId) {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			SocialActivity.class);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("activitySetId", activitySetId));
		dynamicQuery.setProjection(
			ProjectionFactoryUtil.distinct(
				ProjectionFactoryUtil.property("userId")));

		return SocialActivityLocalServiceUtil.dynamicQuery(dynamicQuery);
	}

	protected AssetRenderer getAssetRenderer(String className, long classPK)
		throws Exception {

		AssetRendererFactory assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
				className);

		return assetRendererFactory.getAssetRenderer(classPK);
	}

	protected String getAttachmentTitle(
			SocialActivity activity, WikiPageResource pageResource,
			ServiceContext serviceContext)
		throws Exception {

		return null;
	}

	protected String getBody(
			SocialActivitySet activitySet, ServiceContext serviceContext)
		throws Exception {

		StringBundler sb = new StringBundler();

		sb.append("<div class=\"grouped-activity-body-container\">");
		sb.append("<div class=\"grouped-activity-body\">");

		List<SocialActivity> activities = getViewableActivities(
			activitySet, serviceContext);

		for (SocialActivity activity : activities) {
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
			sb.append("</span><span class=\"activity-subentry-link\">");
			sb.append(subfeedEntry.getLink());
			sb.append("</span></div>");
		}

		sb.append("</div></div>");

		return sb.toString();
	}

	protected long getDisplayDate(SocialActivity activity) throws Exception {
		long activitySetId = activity.getActivitySetId();

		if (activitySetId > 0) {
			SocialActivitySet socialActivitySet =
				SocialActivitySetLocalServiceUtil.fetchSocialActivitySet(
					activitySetId);

			if ((socialActivitySet != null) &&
				(socialActivitySet.getActivityCount() == 1) &&
				(socialActivitySet.getModifiedDate() >
					socialActivitySet.getCreateDate())) {

				return socialActivitySet.getModifiedDate();
			}
		}

		return activity.getCreateDate();
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

		String url = assetRenderer.getURLViewInContext(
			serviceContext.getLiferayPortletRequest(),
			serviceContext.getLiferayPortletResponse(), null);

		return addNoSuchEntryRedirect(url, className, classPK, serviceContext);
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

		String link = getLink(activity, serviceContext);

		String className = activity.getClassName();

		String title = getPageTitle(
			className, activity.getClassPK(), serviceContext);

		AssetRenderer assetRenderer = getAssetRenderer(
			className, activity.getClassPK());

		String body = assetRenderer.getSummary();

		if (className.equals(MBMessage.class.getName())) {
			MBMessage mbMessage = MBMessageLocalServiceUtil.getMBMessage(
				activity.getClassPK());

			if (mbMessage.isFormatBBCode()) {
				body = HtmlUtil.extractText(
					BBCodeTranslatorUtil.getHTML(mbMessage.getBody()));
			}
		}

		body = StringUtil.shorten(HtmlUtil.escape(body), 200);

		return new SocialActivityFeedEntry(link, title, body);
	}

	protected String getTitle(
			long activitySetId, long groupId, long userId, long displayDate,
			ServiceContext serviceContext)
		throws Exception {

		StringBundler sb = new StringBundler(8);

		sb.append("<div class=\"activity-header\">");
		sb.append("<div class=\"activity-user-name\">");

		String userName = getUserName(userId, serviceContext);

		int otherUsersCount = 0;

		if (activitySetId > 0) {
			List<Long> userIds = getActivitySetUserIds(activitySetId);

			otherUsersCount = userIds.size() - 1;
		}

		if ((groupId != serviceContext.getScopeGroupId()) && (groupId > 0)) {
			String groupName = getGroupName(groupId, serviceContext);

			if (otherUsersCount > 0) {
				sb.append(
					serviceContext.translate(
						"x-and-x-others-in-x",
						new Object[] {userName, otherUsersCount, groupName}));
			}
			else {
				sb.append(
					serviceContext.translate(
						"x-in-x", new Object[] {userName, groupName}));
			}
		}
		else if (otherUsersCount > 0) {
			sb.append(
				serviceContext.translate(
					"x-and-x-others",
					new Object[] {userName, otherUsersCount}));
		}
		else {
			sb.append(userName);
		}

		sb.append("</div><div class=\"activity-time\" title=\"");

		Format dateFormatDate = FastDateFormatFactoryUtil.getDateTime(
			DateFormat.FULL, DateFormat.SHORT, serviceContext.getLocale(),
			serviceContext.getTimeZone());

		Date activityDate = new Date(displayDate);

		sb.append(dateFormatDate.format(activityDate));

		sb.append("\">");

		Format dateFormat = FastDateFormatFactoryUtil.getDate(
			DateFormat.FULL, serviceContext.getLocale(),
			serviceContext.getTimeZone());

		String relativeTimeDescription = Time.getRelativeTimeDescription(
			displayDate, serviceContext.getLocale(),
			serviceContext.getTimeZone(), dateFormat);

		sb.append(relativeTimeDescription);

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
				0, activity.getGroupId(), activity.getUserId(),
				getDisplayDate(activity), serviceContext));
		sb.append("<div class=\"activity-action\">");

		String titlePattern = getTitlePattern(null, activity);

		if (Validator.isNull(titlePattern)) {
			return null;
		}

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
				activitySet.getActivitySetId(), activitySet.getGroupId(),
				activitySet.getUserId(), activitySet.getModifiedDate(),
				serviceContext));
		sb.append("<div class=\"activity-action\">");

		String titlePattern = getTitlePattern(null, activitySet);

		if (Validator.isNull(titlePattern)) {
			return null;
		}

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

		List<SocialActivity> viewableActivities = getViewableActivities(
			activitySet, serviceContext);

		return new Object[] {viewableActivities.size()};
	}

	protected String getTitlePattern(
			String groupName, SocialActivitySet activitySet)
		throws Exception {

		return StringPool.BLANK;
	}

	protected List<SocialActivity> getViewableActivities(
			SocialActivitySet activitySet, ServiceContext serviceContext)
		throws Exception {

		List<SocialActivity> viewableActivities = new ArrayList<>();

		List<SocialActivity> activities =
			SocialActivityLocalServiceUtil.getActivitySetActivities(
				activitySet.getActivitySetId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		for (SocialActivity activity : activities) {
			if (!hasPermissions(activity, serviceContext)) {
				continue;
			}

			Group group = GroupLocalServiceUtil.fetchGroup(
				activity.getGroupId());

			if ((group != null) && group.isUser()) {
				continue;
			}

			if (TrashUtil.isInTrash(
					activity.getClassName(), activity.getClassPK())) {

				continue;
			}

			if (!isVisible(activity)) {
				continue;
			}

			viewableActivities.add(activity);
		}

		return viewableActivities;
	}

	@Override
	protected boolean hasPermissions(
			PermissionChecker permissionChecker, SocialActivity activity,
			String actionId, ServiceContext serviceContext)
		throws Exception {

		AssetEntry assetEntry = activity.getAssetEntry();

		AssetRenderer assetRenderer = assetEntry.getAssetRenderer();

		return assetRenderer.hasViewPermission(permissionChecker);
	}

	protected boolean hasPermissions(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		ThemeDisplay themeDisplay = serviceContext.getThemeDisplay();

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		return hasPermissions(
			permissionChecker, activity, ActionKeys.VIEW, serviceContext);
	}

	protected boolean hasPermissions(
			SocialActivitySet activitySet, ServiceContext serviceContext)
		throws Exception {

		ThemeDisplay themeDisplay = serviceContext.getThemeDisplay();

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		List<SocialActivity> activities =
			SocialActivityLocalServiceUtil.getActivitySetActivities(
				activitySet.getActivitySetId(), 0, 1);

		if (!activities.isEmpty()) {
			SocialActivity activity = activities.get(0);

			return hasPermissions(
				permissionChecker, activity, ActionKeys.VIEW, serviceContext);
		}

		return false;
	}

	protected boolean isExpired(
		SocialActivitySet activitySet, boolean comment) {

		long age = System.currentTimeMillis() - activitySet.getCreateDate();

		long timeWindow =
			Time.MINUTE *
				PortletPropsValues.SOCIAL_ACTIVITY_SETS_BUNDLING_TIME_WINDOW;

		if (comment) {
			timeWindow =
				Time.MINUTE *
					PortletPropsValues.
						SOCIAL_ACTIVITY_SETS_COMMENTS_BUNDLING_TIME_WINDOW;
		}

		if (age > timeWindow) {
			return true;
		}

		return false;
	}

	protected boolean isVisible(SocialActivity activity) throws Exception {
		return true;
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