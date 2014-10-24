/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.microblogs.microblogs.notifications;

import com.liferay.microblogs.model.MicroblogsEntry;
import com.liferay.microblogs.model.MicroblogsEntryConstants;
import com.liferay.microblogs.service.MicroblogsEntryLocalServiceUtil;
import com.liferay.microblogs.util.MicroblogsUtil;
import com.liferay.microblogs.util.PortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.notifications.BaseUserNotificationHandler;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserNotificationEvent;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.UserNotificationEventLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.AssetRendererFactoryRegistryUtil;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.AssetRendererFactory;

/**
 * @author Jonathan Lee
 */
public class MicroblogsUserNotificationHandler
	extends BaseUserNotificationHandler {

	public MicroblogsUserNotificationHandler() {
		setPortletId(PortletKeys.MICROBLOGS);
	}

	@Override
	protected String getBody(
			UserNotificationEvent userNotificationEvent,
			ServiceContext serviceContext)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			userNotificationEvent.getPayload());

		long microblogsEntryId = jsonObject.getLong("classPK");

		MicroblogsEntry microblogsEntry =
			MicroblogsEntryLocalServiceUtil.fetchMicroblogsEntry(
				microblogsEntryId);

		if (microblogsEntry == null) {
			UserNotificationEventLocalServiceUtil.deleteUserNotificationEvent(
				userNotificationEvent.getUserNotificationEventId());

			return null;
		}

		int notificationType = jsonObject.getInt("notificationType");

		String title = getBodyTitle(
			microblogsEntry, notificationType, serviceContext);

		String body = MicroblogsUtil.getProcessedContent(
			StringUtil.shorten(microblogsEntry.getContent(), 50),
			serviceContext);

		return StringUtil.replace(
			getBodyTemplate(), new String[] {"[$BODY$]", "[$TITLE$]"},
			new String[] {body, title});
	}

	protected String getBodyTitle(
			MicroblogsEntry microblogsEntry, int notificationType,
			ServiceContext serviceContext)
		throws PortalException {

		String title = StringPool.BLANK;

		String userFullName = HtmlUtil.escape(
			PortalUtil.getUserName(
				microblogsEntry.getUserId(), StringPool.BLANK));

		if (notificationType ==
				MicroblogsEntryConstants.NOTIFICATION_TYPE_REPLY) {

			title = serviceContext.translate(
				"x-commented-on-your-post", userFullName);
		}
		else if (notificationType ==
					MicroblogsEntryConstants.
						NOTIFICATION_TYPE_REPLY_TO_REPLIED) {

			User receiverUser = UserLocalServiceUtil.fetchUser(
				microblogsEntry.getReceiverUserId());

			if (receiverUser != null) {
				title = serviceContext.translate(
					"x-also-commented-on-x's-post", userFullName,
					receiverUser.getFullName());
			}
		}
		else if (notificationType ==
					MicroblogsEntryConstants.
						NOTIFICATION_TYPE_REPLY_TO_TAGGED) {

			title = serviceContext.translate(
				"x-commented-on-a-post-you-are-tagged-in", userFullName);
		}
		else if (notificationType ==
					MicroblogsEntryConstants.NOTIFICATION_TYPE_TAG) {

			title = serviceContext.translate(
				"x-tagged-you-in-a-post", userFullName);
		}

		return title;
	}

	@Override
	protected String getLink(
			UserNotificationEvent userNotificationEvent,
			ServiceContext serviceContext)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			userNotificationEvent.getPayload());

		long microblogsEntryId = jsonObject.getLong("classPK");

		AssetRendererFactory assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
				MicroblogsEntry.class.getName());

		AssetRenderer assetRenderer = assetRendererFactory.getAssetRenderer(
			microblogsEntryId);

		return assetRenderer.getURLViewInContext(
			serviceContext.getLiferayPortletRequest(),
			serviceContext.getLiferayPortletResponse(), null);
	}

}