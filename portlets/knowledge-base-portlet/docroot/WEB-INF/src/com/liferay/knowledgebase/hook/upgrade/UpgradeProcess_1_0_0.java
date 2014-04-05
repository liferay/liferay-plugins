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

package com.liferay.knowledgebase.hook.upgrade;

import com.liferay.knowledgebase.hook.upgrade.v1_0_0.UpgradeRatingsEntry;
import com.liferay.knowledgebase.hook.upgrade.v1_0_0.UpgradeRatingsStats;
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
		upgrade(UpgradeRatingsEntry.class);
		upgrade(UpgradeRatingsStats.class);
	}

}