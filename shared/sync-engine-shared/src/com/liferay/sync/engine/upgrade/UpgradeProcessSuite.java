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

package com.liferay.sync.engine.upgrade;

/**
 * @author Shinn Lok
 */
public class UpgradeProcessSuite {

	public void upgrade() throws Exception {
		upgrade(UpgradeSyncAccount.class);
		upgrade(UpgradeSyncFile.class);
		upgrade(UpgradeSyncSite.class);
	}

	protected void upgrade(Class<?> upgradeProcessClass) throws Exception {
		UpgradeProcess upgradeProcess =
			(UpgradeProcess)upgradeProcessClass.newInstance();

		upgradeProcess.upgrade();
	}

}