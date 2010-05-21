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

package com.liferay.wsrp.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.wsrp.model.WSRPConsumerPortlet;
import com.liferay.wsrp.service.WSRPConsumerPortletLocalServiceUtil;

/**
 * <a href="WSRPRepositoryMessageListener.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Hugo Huijser
 */
public class WSRPRepositoryMessageListener implements MessageListener {

	public void receive(Message message){
		try {
			doReceive(message);
		}
		catch (Exception e) {
			_log.error("Unable to process message " + message, e);
		}
	}

	protected void doReceive(Message message) throws Exception {
		String wsrpConsumerPortletInfo =
			(String)message.get("wsrpConsumerPortlet");

		String command = (String)message.get("command");

		WSRPConsumerPortlet wsrpConsumerPortlet =
			(WSRPConsumerPortlet)Base64.stringToObject(
				wsrpConsumerPortletInfo,
				PortletClassLoaderUtil.getClassLoader());

		if (command.equals("add")) {
			String userToken = (String)message.get("userToken");

			WSRPConsumerPortletLocalServiceUtil.initWSRPConsumerPortlet(
				wsrpConsumerPortlet, userToken);
		}
		else if (command.equals("delete")) {
			WSRPConsumerPortletLocalServiceUtil.destroyWSRPConsumerPortlet(
				wsrpConsumerPortlet);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		WSRPRepositoryMessageListener.class);

}