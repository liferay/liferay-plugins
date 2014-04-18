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

package com.liferay.compat.portal.kernel.notifications;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.notifications.UserNotificationFeedEntry;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.UserNotificationEvent;
import com.liferay.portal.service.ServiceContext;

/**
 * @author Jonathan Lee
 */
public abstract class BaseUserNotificationHandler
	extends com.liferay.portal.kernel.notifications.
		BaseUserNotificationHandler {

	@Override
	@SuppressWarnings("unused")
	public UserNotificationFeedEntry interpret(
			UserNotificationEvent userNotificationEvent,
			ServiceContext serviceContext)
		throws PortalException {

		try {
			UserNotificationFeedEntry userNotificationFeedEntry = doInterpret(
				userNotificationEvent, serviceContext);

			if (userNotificationFeedEntry != null) {
				userNotificationFeedEntry.setPortletId(getPortletId());
			}

			return userNotificationFeedEntry;
		}
		catch (Exception e) {
			_log.error("Unable to interpret notification", e);
		}

		return null;
	}

	protected String getBodyTemplate() throws Exception {
		if (isActionable()) {
			StringBundler sb = new StringBundler(5);

			sb.append("<div class=\"title\">[$TITLE$]</div><div ");
			sb.append("class=\"body\"><a class=\"btn btn-action ");
			sb.append("btn-success\" href=\"[$CONFIRM_URL$]\">[$CONFIRM$]</a>");
			sb.append("<a class=\"btn btn-action btn-warning\" href=\"");
			sb.append("[$IGNORE_URL$]\">[$IGNORE$]</a></div>");

			return sb.toString();
		}
		else {
			return "<div class=\"title\">[$TITLE$]</div><div class=\"body\">" +
				"[$BODY$]</div>";
		}
	}

	protected boolean isActionable() {
		return _actionable;
	}

	protected void setActionable(boolean actionable) {
		_actionable = actionable;
	}

	private static Log _log = LogFactoryUtil.getLog(
		BaseUserNotificationHandler.class);

	private boolean _actionable;

}