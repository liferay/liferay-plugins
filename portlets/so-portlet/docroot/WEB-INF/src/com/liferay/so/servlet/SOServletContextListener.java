/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.so.servlet;

import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.HotDeployMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.util.BasePortalLifecycle;
import com.liferay.portal.kernel.util.ClassResolverUtil;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.PortletClassInvoker;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.so.service.ClpSerializer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Ryan Park
 */
public class SOServletContextListener
	extends BasePortalLifecycle implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		portalDestroy();
	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		registerPortalLifecycle();
	}

	@Override
	protected void doPortalDestroy() throws Exception {
		MessageBusUtil.unregisterMessageListener(
			DestinationNames.HOT_DEPLOY, _hotDeployMessageListener);
	}

	@Override
	protected void doPortalInit() {
		_hotDeployMessageListener = new HotDeployMessageListener(
			ClpSerializer.getServletContextName(), "contacts-portlet") {

			@Override
			protected void onDeploy(Message message) throws Exception {
				long companyId = message.getLong("companyId");

				if (PortletLocalServiceUtil.hasPortlet(
						companyId, "1_WAR_contactsportlet")) {

					registerContactsExtension();
				}
			}

		};

		MessageBusUtil.registerMessageListener(
			DestinationNames.HOT_DEPLOY, _hotDeployMessageListener);
	}

	protected void registerContactsExtension() throws Exception {
		PortletClassInvoker.invoke(
			false, "1_WAR_contactsportlet", _registerMethodKey,
			ClpSerializer.getServletContextName(), "/contacts/projects.jsp");
	}

	private MessageListener _hotDeployMessageListener;
	private MethodKey _registerMethodKey = new MethodKey(
		ClassResolverUtil.resolveByPortletClassLoader(
			"com.liferay.contacts.util.ContactsExtensionsUtil",
			"contacts-portlet"),
		"register", String.class, String.class);

}