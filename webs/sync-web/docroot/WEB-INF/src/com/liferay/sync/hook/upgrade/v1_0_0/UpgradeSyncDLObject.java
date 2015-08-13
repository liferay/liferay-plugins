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

package com.liferay.sync.hook.upgrade.v1_0_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portlet.documentlibrary.model.DLFileEntryConstants;
import com.liferay.sync.model.SyncConstants;
import com.liferay.sync.service.SyncDLObjectLocalServiceUtil;
import com.liferay.sync.util.VerifyUtil;

/**
 * @author Dennis Ju
 */
public class UpgradeSyncDLObject extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try {
			runSQL("drop index IX_69ADEDD1 on SyncDLObject");
		}
		catch (Exception e) {
		}

		SyncDLObjectLocalServiceUtil.deleteSyncDLObjects(
			DLFileEntryConstants.PRIVATE_WORKING_COPY_VERSION,
			SyncConstants.TYPE_FILE);

		VerifyUtil.verify();
	}

}