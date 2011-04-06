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

package com.liferay.contacts.messaging;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.PortletClassInvoker;
import com.liferay.portal.service.PortletLocalServiceUtil;

/**
 * @author Ryan Park
 */
public class HotDeployMessageListener extends BaseMessageListener {

	protected void doReceive(Message message) throws Exception {
		String command = message.getString("command");
		String servletContextName = message.getString("servletContextName");

		if (!command.equals("deploy")) {
			return;
		}

		if (servletContextName.equals("chat-portlet")) {
			registerChatExtension();
		}

		if (servletContextName.equals(_SERVLET_CONTEXT_NAME)) {
			if (PortletLocalServiceUtil.hasPortlet(
					message.getLong("companyId"), "1_WAR_chatportlet")) {

				registerChatExtension();
			}
		}
	}

	protected void registerChatExtension() throws Exception {
		PortletClassInvoker.invoke(
			false, "1_WAR_chatportlet", _registerMethodKey,
			_SERVLET_CONTEXT_NAME, _EXTENSION_PATH);
	}

	private final String _EXTENSION_PATH = "/chat-ext.jsp";

	private final String _SERVLET_CONTEXT_NAME = "contacts-portlet";

	private MethodKey _registerMethodKey = new MethodKey(
		"com.liferay.chat.util.ChatExtensionsUtil", "register",
		String.class, String.class);

}