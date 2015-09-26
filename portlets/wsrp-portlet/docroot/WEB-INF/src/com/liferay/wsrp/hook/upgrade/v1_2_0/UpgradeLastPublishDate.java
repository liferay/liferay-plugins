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

package com.liferay.wsrp.hook.upgrade.v1_2_0;

import com.liferay.portal.kernel.upgrade.BaseUpgradeLastPublishDate;

/**
 * @author Mate Thurzo
 */
public class UpgradeLastPublishDate extends BaseUpgradeLastPublishDate {

	@Override
	protected void doUpgrade() throws Exception {
		runSQL(
			"alter table WSRP_WSRPConsumerPortlet add lastPublishDate DATE " +
				"null");

		updateLastPublishDates("1_WAR_wsrpportlet", "WSRP_WSRPConsumerPortlet");

		runSQL("alter table WSRP_WSRPConsumer add lastPublishDate DATE null");

		updateLastPublishDates("1_WAR_wsrpportlet", "WSRP_WSRPConsumer");

		runSQL("alter table WSRP_WSRPProducer add lastPublishDate DATE null");

		updateLastPublishDates("1_WAR_wsrpportlet", "WSRP_WSRPProducer");
	}

}