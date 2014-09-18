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

package com.liferay.marketplace.hook.upgrade.v1_0_1;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Ryan Park
 * @author Joan Kim
 */
public class UpgradeModule extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		upgradeModule();
	}

	protected void upgradeModule() throws Exception {
		if (!tableHasColumn("Marketplace_Module", "bundleSymbolicName")) {
			runSQL(
				"alter table Marketplace_Module add column " +
					"bundleSymbolicName VARCHAR(500)");
		}

		if (!tableHasColumn("Marketplace_Module", "bundleVersion")) {
			runSQL(
				"alter table Marketplace_Module add column bundleVersion " +
					"VARCHAR(75)");
		}

		try {
			runSQL(
				"create index IX_5848F52D on Marketplace_Module " +
					"(appId, bundleSymbolicName, bundleVersion)");
			runSQL(
				"create index IX_DD03D499 on Marketplace_Module " +
					"(bundleSymbolicName)");
		}
		catch (Exception e) {
		}
	}

}