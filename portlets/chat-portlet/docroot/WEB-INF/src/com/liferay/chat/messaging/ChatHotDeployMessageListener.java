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

package com.liferay.chat.messaging;

import com.liferay.chat.util.ChatExtensionsUtil;
import com.liferay.portal.kernel.messaging.HotDeployMessageListener;
import com.liferay.portal.kernel.messaging.Message;

/**
 * @author Ryan Park
 */
public class ChatHotDeployMessageListener extends HotDeployMessageListener {

	@Override
	protected void onUndeploy(Message message) throws Exception {
		ChatExtensionsUtil.unregister(message.getString("servletContextName"));
	}

}