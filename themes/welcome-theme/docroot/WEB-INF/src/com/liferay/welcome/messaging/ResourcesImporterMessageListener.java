/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.welcome.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.service.GroupLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class ResourcesImporterMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		String servletContextName = message.getString("servletContextName");

		if (!servletContextName.equals("welcome-theme")) {
			return;
		}

		long groupId = message.getLong("groupId");

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		Group guestGroup = GroupLocalServiceUtil.getGroup(
			group.getCompanyId(), GroupConstants.GUEST);

		if (_log.isInfoEnabled()) {
			_log.info(
				"Apply content and pages from group " + groupId +
					" onto the Guest group");
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ResourcesImporterMessageListener.class);

}