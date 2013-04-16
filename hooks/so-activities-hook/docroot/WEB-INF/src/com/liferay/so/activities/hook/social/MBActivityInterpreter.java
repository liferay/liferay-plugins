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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivitySet;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import com.liferay.portlet.social.service.SocialActivitySetLocalServiceUtil;

/**
 * @author Evan Thibodeau
 * @author Matthew Kong
 */
public class MBActivityInterpreter extends SOSocialActivityInterpreter {

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected long getActivitySetId(long activityId) {
		try {
			SocialActivity activity =
				SocialActivityLocalServiceUtil.getActivity(activityId);

			if (((activity.getType() == _ACTIVITY_KEY_ADD_MESSAGE) &&
				 (activity.getReceiverUserId() > 0)) ||
				(activity.getType() == _ACTIVITY_KEY_REPLY_MESSAGE)) {

				SocialActivitySet activitySet =
					SocialActivitySetLocalServiceUtil.getClassActivitySet(
						activity.getUserId(), activity.getClassNameId(),
						activity.getClassPK(), activity.getType());

				if ((activitySet != null) && !isExpired(activitySet)) {
					return activitySet.getActivitySetId();
				}
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

		StringBundler sb = new StringBundler(5);

		sb.append("<div class=\"activity-body\"><div class=\"title\">");

		String pageTitle = StringPool.BLANK;

		String linkURL = getLinkURL(activity, serviceContext);

		AssetRenderer assetRenderer = getAssetRenderer(activity);

		LiferayPortletRequest liferayPortletRequest =
			serviceContext.getLiferayPortletRequest();

		if (Validator.isNotNull(
				assetRenderer.getIconPath(liferayPortletRequest))) {

			pageTitle = wrapLink(
				linkURL, assetRenderer.getIconPath(liferayPortletRequest),
				HtmlUtil.escape(
					assetRenderer.getTitle(serviceContext.getLocale())));
		}
		else {
			pageTitle = wrapLink(
				linkURL,
				HtmlUtil.escape(
					assetRenderer.getTitle(serviceContext.getLocale())));
		}

		sb.append(pageTitle);

		sb.append("</div><div class=\"forum-page-content\">");
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
	protected Object[] getTitleArguments(
			String groupName, SocialActivity activity, String link,
			String title, ServiceContext serviceContext)
		throws Exception {

		StringBundler sb = new StringBundler(4);

		sb.append(serviceContext.getPortalURL());
		sb.append(serviceContext.getPathMain());
		sb.append("/message_boards/find_category?mbCategoryId=");

		MBMessage message = MBMessageLocalServiceUtil.getMessage(
			activity.getClassPK());

		sb.append(message.getCategoryId());

		String categoryURL = sb.toString();
		String categoryName = message.getCategory().getName();

		String categoryLink = wrapLink(categoryURL, categoryName);

		if ((activity.getType() == _ACTIVITY_KEY_REPLY_MESSAGE) ||
			(activity.getReceiverUserId() > 0)) {

			String receiverUserName = getUserName(
				activity.getReceiverUserId(), serviceContext);

			if (message.getCategoryId() > 0) {
				return new Object[] {receiverUserName, categoryLink};
			}

			return new Object[] {receiverUserName};
		}
		else if (activity.getType() == _ACTIVITY_KEY_ADD_MESSAGE) {
			if (message.getCategoryId() > 0) {
				return new Object[] {categoryLink};
			}
		}

		return null;
	}

	@Override
	protected String getTitlePattern(String groupName, SocialActivity activity)
		throws Exception {

		String titlePattern = StringPool.BLANK;

		if ((activity.getType() == _ACTIVITY_KEY_REPLY_MESSAGE) ||
			(activity.getReceiverUserId() > 0)) {

			titlePattern = "replied-to-x-forum-post";
		}
		else if (activity.getType() == _ACTIVITY_KEY_ADD_MESSAGE) {
			titlePattern = "wrote-a-new-forum-post";
		}
		else {
			return StringPool.BLANK;
		}

		MBMessage message = MBMessageLocalServiceUtil.getMessage(
			activity.getClassPK());

		if (message.getCategoryId() > 0) {
			titlePattern = titlePattern.concat("-in-x");
		}

		return titlePattern;
	}

	/**
	 * {@link
	 * com.liferay.portlet.messageboards.social.MBActivityKeys#ADD_MESSAGE}
	 */
	private static final int _ACTIVITY_KEY_ADD_MESSAGE = 1;

	/**
	 * {@link
	 * com.liferay.portlet.messageboards.social.MBActivityKeys#REPLY_MESSAGE}
	 */
	private static final int _ACTIVITY_KEY_REPLY_MESSAGE = 2;

	private static final String[] _CLASS_NAMES = {MBMessage.class.getName()};

}