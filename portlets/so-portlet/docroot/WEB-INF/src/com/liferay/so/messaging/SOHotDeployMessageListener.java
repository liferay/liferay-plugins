/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.messaging.HotDeployMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.service.PortletLocalServiceUtil;
import com.liferay.portal.kernel.util.ClassResolverUtil;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.PortletClassInvoker;
import com.liferay.so.service.ClpSerializer;

/**
 * @author Ryan Park
 */
public class SOHotDeployMessageListener extends HotDeployMessageListener {

	public SOHotDeployMessageListener(String... servletContextNames) {
		super(servletContextNames);
	}

	@Override
	protected void onDeploy(Message message) throws Exception {
		long companyId = message.getLong("companyId");

		if (PortletLocalServiceUtil.hasPortlet(
				companyId, "1_WAR_contactsportlet")) {

			registerContactsExtension();
		}
	}

	protected void registerContactsExtension() throws Exception {
		if (_registerMethodKey == null) {
			try {
				_registerMethodKey = new MethodKey(
					ClassResolverUtil.resolveByPortletClassLoader(
						"com.liferay.contacts.util.ContactsExtensionsUtil",
						"contacts-portlet"),
					"register", String.class, String.class);
			}
			catch (RuntimeException re) {
				return;
			}
		}

		PortletClassInvoker.invoke(
			"1_WAR_contactsportlet", _registerMethodKey,
			ClpSerializer.getServletContextName(), "/contacts/projects.jsp");
	}

	private MethodKey _registerMethodKey;

}