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

package com.liferay.opensocial.messaging;

import com.liferay.opensocial.service.GadgetLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;

/**
 * <a href="OpenSocialMessageListener.java.html"><b><i>View Source</i></b></a>
 *
 * @author Hugo Huijser
 */
public class OpenSocialMessageListener implements MessageListener {

	public void receive(Message message){
		try {
			doReceive(message);
		}
		catch (Exception e) {
			_log.error("Unable to process message " + message, e);
		}
	}

	protected void doReceive(Message message) throws Exception {
		String command = message.getString("command");
		long companyId = message.getLong("companyId");
		String name = message.getString("name");
		String url = message.getString("url");
		long gadgetId = message.getLong("gadgetId");

		if (command.equals("add")) {
			GadgetLocalServiceUtil.initGadget(companyId, gadgetId, name, url);
		}
		else if (command.equals("delete")) {
			GadgetLocalServiceUtil.destroyGadget(
				companyId, gadgetId, name, url);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		OpenSocialMessageListener.class);

}