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

package com.liferay.wsrp.hook.upgrade.v1_0_0;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.wsrp.model.WSRPConsumerPortlet;
import com.liferay.wsrp.service.WSRPConsumerPortletLocalServiceUtil;

import java.util.List;

/**
 * @author Michael Young
 */
public class UpgradeWSRPConsumerPortlet extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		List<WSRPConsumerPortlet> wsrpConsumerPortlets =
			WSRPConsumerPortletLocalServiceUtil.getWSRPConsumerPortlets(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (WSRPConsumerPortlet wsrpConsumerPortlet : wsrpConsumerPortlets) {
			WSRPConsumerPortletLocalServiceUtil.updateWSRPConsumerPortlet(
				wsrpConsumerPortlet);
		}
	}

}