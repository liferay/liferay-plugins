/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.so.messaging;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.PortletClassInvoker;
import com.liferay.portal.service.PortletLocalServiceUtil;

/**
 * @author Ryan Park
 */
public class HotDeployMessageListener extends BaseMessageListener {

	protected void deploy(Message message) throws Exception {
		String servletContextName = message.getString("servletContextName");

		if (servletContextName.equals("contacts-portlet")) {
			registerContactsExtension();
		}
		else if (servletContextName.equals("so-portlet")) {
			long companyId = message.getLong("companyId");

			if (PortletLocalServiceUtil.hasPortlet(
					companyId, "1_WAR_contactsportlet")) {

				registerContactsExtension();
			}
		}
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		String command = message.getString("command");

		if (command.equals("deploy")) {
			deploy(message);
		}
	}

	protected void registerContactsExtension() throws Exception {
		PortletClassInvoker.invoke(
			false, "1_WAR_contactsportlet", _registerMethodKey, "so-portlet",
			"/contacts/projects.jsp");
	}

	private MethodKey _registerMethodKey = new MethodKey(
		"com.liferay.contacts.util.ContactsExtensionsUtil", "register",
		String.class, String.class);

}