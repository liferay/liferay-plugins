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

package com.liferay.so.portlet.messageboards.social;

import com.liferay.compat.portal.service.ServiceContext;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.so.activities.model.BaseSocialActivityInterpreter;
import com.liferay.so.util.Time;

import java.text.Format;

import java.util.Date;

/**
 * @author Evan Thibodeau
 */
public class MBActivityInterpreter extends BaseSocialActivityInterpreter {

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected String getBody(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		AssetRenderer assetRenderer = getAssetRenderer(activity);

		String linkURL = getLinkURL(activity, serviceContext);

		LiferayPortletRequest liferayPortletRequest =
			serviceContext.getLiferayPortletRequest();

		String pageTitle = wrapLink(
			linkURL,
			HtmlUtil.escape(
				assetRenderer.getTitle(serviceContext.getLocale())));

		if (Validator.isNotNull(
				assetRenderer.getIconPath(liferayPortletRequest))) {

			pageTitle = wrapLink(
				linkURL, assetRenderer.getIconPath(liferayPortletRequest),
				HtmlUtil.escape(
					assetRenderer.getTitle(serviceContext.getLocale())));
		}

		StringBundler sb = new StringBundler(5);

		sb.append("<div class=\"activity-body\"><div class=\"title\">");
		sb.append(pageTitle);
		sb.append("</div><div class='forum-page-content'>");
		sb.append(
			StringUtil.shorten(
				HtmlUtil.extractText(
					assetRenderer.getSummary(
						serviceContext.getLocale())), 200));
		sb.append("</div></div>");

		return sb.toString();
	}

	@Override
	protected String getLink(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		return wrapLink(
			getLinkURL(activity, serviceContext),
			serviceContext.translate("view-forum"));
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

		String receiverUserName = getUserName(
			activity.getReceiverUserId(), serviceContext);

		MBMessage message = MBMessageLocalServiceUtil.getMessage(
			activity.getClassPK());

		sb = new StringBundler(4);

		sb.append(serviceContext.getPortalURL());
		sb.append(serviceContext.getPathMain());
		sb.append("/message_boards/find_category?mbCategoryId=");
		sb.append(message.getCategoryId());

		String categoryURL = sb.toString();
		String categoryName = message.getCategory().getName();

		String categoryLink = wrapLink(categoryURL, categoryName);

		Object[] actionPatternArguments = new Object[] {
			receiverUserName, categoryLink
		};

		int activityType = activity.getType();

		if ((activityType == _REPLY_MESSAGE) ||
			(activity.getReceiverUserId() > 0)) {

			String actionPattern = "replied-to-x-forum-post";

			if (message.getCategoryId() > 0) {
				actionPattern += "-in-x";

				sb.append(
					serviceContext.translate(
						actionPattern, actionPatternArguments));
			}
			else {
				sb.append(
					serviceContext.translate(actionPattern, receiverUserName));
			}
		}
		else if (activityType == _ADD_MESSAGE) {
			String actionPattern = "wrote-a-new-forum-post";

			if (message.getCategoryId() > 0) {
				actionPattern += "-in-x";

				sb.append(
					serviceContext.translate(actionPattern, categoryLink));
			}
			else {
				sb.append(serviceContext.translate(actionPattern));
			}
		}

		sb.append("</div>");

		return sb.toString();
	}

	private static final int _ADD_MESSAGE = 1;

	private static final String[] _CLASS_NAMES = new String[] {
		MBMessage.class.getName()
	};

	private static final int _REPLY_MESSAGE = 2;

}