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
import com.liferay.wsrp.model.WSRPProducer;
import com.liferay.wsrp.service.WSRPProducerLocalServiceUtil;

import java.util.List;

/**
 * @author Michael Young
 */
public class UpgradeWSRPProducer extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		runSQL("update WSRP_WSRPProducer set version = '2.0' where verion is " +
			"null");

		List<WSRPProducer> wsrpProducers =
			WSRPProducerLocalServiceUtil.getWSRPProducers(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (WSRPProducer wsrpProducer : wsrpProducers) {
			WSRPProducerLocalServiceUtil.updateWSRPProducer(wsrpProducer);
		}
	}

}