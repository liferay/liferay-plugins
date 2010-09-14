/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Scott Lee
 */
public class UpgradePrivateMessaging extends UpgradeProcess {

	protected void doUpgrade() throws Exception {
		StringBundler sb = new StringBundler(4);

		sb.append("update MBMessage set categoryId = -2 where messageId in (");
		sb.append("select messageId from (select MBMessage.messageId from ");
		sb.append("MBMessage inner join PM_UserThread on ");
		sb.append("MBMessage.threadId = PM_UserThread.mbThreadId) as temp)");

		runSQL(sb.toString());

		sb.setIndex(0);

		sb.append("update MBThread set categoryId = -5 where threadId in ");
		sb.append("(select mbThreadId from PM_UserThread)");

		runSQL(sb.toString());
	}

}