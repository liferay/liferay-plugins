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

package com.liferay.wsrp.proxy;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import oasis.names.tc.wsrp.v1.bind.WSRP_v1_PortletManagement_Binding_SOAPStub;
import oasis.names.tc.wsrp.v1.intf.WSRP_v1_PortletManagement_PortType;

/**
 * @author Michael Young
 */
public class PortletManagementServiceHandler implements InvocationHandler {

	public PortletManagementServiceHandler(
		WSRP_v1_PortletManagement_Binding_SOAPStub portletManagementService) {

		_portletManagementService = portletManagementService;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
		throws Throwable {

		try {
			return doInvoke(proxy, method, args);
		}
		catch (Throwable t) {
			_log.error(t, t);

			throw t;
		}
	}

	public Object doInvoke(Object proxy, Method method, Object[] args)
		throws Exception {

		System.out.println(_portletManagementService.getClass());

		return null;
	}

	private static Log _log = LogFactoryUtil.getLog(
		PortletManagementServiceHandler.class);

	private WSRP_v1_PortletManagement_PortType _portletManagementService;

}