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

package com.liferay.akismet.hook.upgrade;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Peter Shin
 */
public class UpgradeProcess_1_0_0 extends UpgradeProcess {

	@Override
	public int getThreshold() {
		return 100;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasColumn("Akismet_AkismetData", "mbMessageId")) {
			return;
		}

		runSQL("alter table Akismet_AkismetData add column classNameId LONG");
		runSQL("alter table Akismet_AkismetData add column classPK LONG");

		runSQL(
			"update Akismet_AkismetData set classNameId = (select " +
				"classNameId from ClassName_ where value = " +
					"'com.liferay.portlet.messageboards.model.MBMessage')");
		runSQL("update Akismet_AkismetData set classPK = mbMessageId");

		runSQL("alter table Akismet_AkismetData drop column mbMessageId");
	}

}