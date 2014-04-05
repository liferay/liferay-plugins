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

package com.liferay.wsrp.messaging;

import com.liferay.portal.kernel.messaging.HotDeployMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.wsrp.service.WSRPConsumerPortletLocalServiceUtil;
import com.liferay.wsrp.util.ExtensionHelperUtil;

/**
 * @author Shuyang Zhou
 */
public class WSRPHotDeployMessageListener extends HotDeployMessageListener {

	public WSRPHotDeployMessageListener(String... servletContextNames) {
		super(servletContextNames);
	}

	@Override
	protected void onDeploy(Message message) throws Exception {
		ExtensionHelperUtil.initialize();

		WSRPConsumerPortletLocalServiceUtil.destroyWSRPConsumerPortlets();

		WSRPConsumerPortletLocalServiceUtil.initWSRPConsumerPortlets();
	}

	@Override
	protected void onUndeploy(Message message) throws Exception {
		WSRPConsumerPortletLocalServiceUtil.destroyWSRPConsumerPortlets();
	}

}