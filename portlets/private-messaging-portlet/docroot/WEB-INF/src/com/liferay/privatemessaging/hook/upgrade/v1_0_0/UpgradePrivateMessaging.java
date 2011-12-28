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

package com.liferay.privatemessaging.hook.upgrade.v1_0_0;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portlet.messageboards.service.MBThreadLocalServiceUtil;
import com.liferay.privatemessaging.model.UserThread;
import com.liferay.privatemessaging.service.UserThreadLocalServiceUtil;
import com.liferay.privatemessaging.util.PrivateMessagingConstants;

import java.util.List;

/**
 * @author Scott Lee
 */
public class UpgradePrivateMessaging extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		List<UserThread> userThreads =
			UserThreadLocalServiceUtil.getUserThreads(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (UserThread userThread : userThreads) {
			MBThreadLocalServiceUtil.moveThread(
				GroupConstants.DEFAULT_PARENT_GROUP_ID,
				PrivateMessagingConstants.PRIVATE_MESSAGING_CATEGORY_ID,
				userThread.getMbThreadId());
		}
	}

}