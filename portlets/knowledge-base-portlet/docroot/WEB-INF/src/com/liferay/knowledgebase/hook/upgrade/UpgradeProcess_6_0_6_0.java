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

package com.liferay.knowledgebase.hook.upgrade;

import com.liferay.knowledgebase.util.ReleaseInfo;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Peter Shin
 */
public class UpgradeProcess_6_0_6_0 extends UpgradeProcess {

	public int getThreshold() {
		return ReleaseInfo.RELEASE_6_0_6_0_BUILD_NUMBER;
	}

	protected void doUpgrade() throws Exception {
		runSQL("alter table KB_Article add status INTEGER");
		runSQL("alter table KB_Article add statusByUserId LONG");
		runSQL("alter table KB_Article add statusByUserName VARCHAR(75)");
		runSQL("alter table KB_Article add statusDate DATE");

		runSQL("update KB_Article set status = 0");
		runSQL("update KB_Article set statusByUserId = userId");
		runSQL("update KB_Article set statusByUserName = userName");
		runSQL("update KB_Article set statusDate = modifiedDate");
	}

}