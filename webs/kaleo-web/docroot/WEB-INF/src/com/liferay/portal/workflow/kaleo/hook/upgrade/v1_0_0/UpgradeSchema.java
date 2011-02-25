/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.kaleo.hook.upgrade.v1_0_0;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Marcellus Tavares
 */
public class UpgradeSchema extends UpgradeProcess {

	protected void doUpgrade() throws Exception {
		try {
			runSQL(_CREATE_KALEO_TIMER);
		}
		catch (Exception e) {
			_log.warn("KaleoTimer table already exists.");
		}

		try {
			runSQL(_CREATE_KALEO_TIMER_INSTANCE_TOKEN);
		}
		catch (Exception e) {
			_log.warn("KaleoTimerInstanceToken table already exists.");
		}
	}

	private static final String _CREATE_KALEO_TIMER =
		"create table KaleoTimer (" +
			"kaleoTimerId LONG not null primary key," +
			"groupId LONG," +
			"companyId LONG," +
			"userId LONG," +
			"userName VARCHAR(200) null," +
			"createDate DATE null," +
			"modifiedDate DATE null," +
			"kaleoDefinitionId LONG," +
			"kaleoNodeId LONG," +
			"parentKaleoNodeId LONG," +
			"name VARCHAR(75) null," +
			"defaultTimer BOOLEAN," +
			"description VARCHAR(75) null," +
			"duration DOUBLE," +
			"scale VARCHAR(75) null" +
		")";

	private static final String _CREATE_KALEO_TIMER_INSTANCE_TOKEN =
		"create table KaleoTimerInstanceToken (" +
			"kaleoTimerInstanceTokenId LONG not null primary key," +
			"groupId LONG," +
			"companyId LONG," +
			"userId LONG," +
			"userName VARCHAR(200) null," +
			"createDate DATE null," +
			"modifiedDate DATE null," +
			"kaleoDefinitionId LONG," +
			"kaleoInstanceId LONG," +
			"kaleoInstanceTokenId LONG," +
			"kaleoTimerId LONG," +
			"kaleoTimerName VARCHAR(200) null," +
			"completionUserId LONG," +
			"completed BOOLEAN," +
			"completionDate DATE null," +
			"workflowContext TEXT null" +
		")";

	private static Log _log = LogFactoryUtil.getLog(UpgradeSchema.class);

}