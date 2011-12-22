/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.contacts.messaging;

import com.liferay.contacts.util.ContactsExtensionsUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.PortletClassInvoker;
import com.liferay.portal.service.PortletLocalServiceUtil;

/**
 * @author Ryan Park
 */
public class HotDeployMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		String command = message.getString("command");

		if (command.equals("deploy")) {
			deploy(message);
		}
		else if (command.equals("undeploy")) {
			undeploy(message);
		}
	}

	protected void deploy(Message message) throws Exception {
		String servletContextName = message.getString("servletContextName");

		if (servletContextName.equals("chat-portlet")) {
			registerChatExtension();
		}
		else if (servletContextName.equals("contacts-portlet")) {
			long companyId = message.getLong("companyId");

			if (PortletLocalServiceUtil.hasPortlet(
					companyId, "1_WAR_chatportlet")) {

				registerChatExtension();
			}
		}
	}

	protected void undeploy(Message message) throws Exception {
		String servletContextName = message.getString("servletContextName");

		ContactsExtensionsUtil.unregister(servletContextName);
	}

	protected void registerChatExtension() throws Exception {
		PortletClassInvoker.invoke(
			false, "1_WAR_chatportlet", _registerMethodKey, "contacts-portlet",
			"/chat/view.jsp");
	}

	private MethodKey _registerMethodKey = new MethodKey(
		"com.liferay.chat.util.ChatExtensionsUtil", "register", String.class,
		String.class);

}