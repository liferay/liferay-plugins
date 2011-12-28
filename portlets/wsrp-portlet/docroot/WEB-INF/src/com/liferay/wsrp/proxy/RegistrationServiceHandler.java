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

import oasis.names.tc.wsrp.v1.bind.WSRP_v1_Registration_Binding_SOAPStub;
import oasis.names.tc.wsrp.v1.intf.WSRP_v1_Registration_PortType;
import oasis.names.tc.wsrp.v1.types.RegistrationContext;
import oasis.names.tc.wsrp.v1.types.RegistrationData;
import oasis.names.tc.wsrp.v2.types.Register;

/**
 * @author Michael Young
 */
public class RegistrationServiceHandler implements InvocationHandler {

	public RegistrationServiceHandler(
		WSRP_v1_Registration_Binding_SOAPStub registrationDescriptionService) {

		_registrationDescriptionService = registrationDescriptionService;
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

		Register register = (Register)args[0];

		Object v1Bean = TypeConvertorUtil.convert(
			register.getRegistrationData(), 2);

		RegistrationData registrationData = (RegistrationData)v1Bean;

		RegistrationContext registrationContext =
			_registrationDescriptionService.register(registrationData);

		Object v2Bean = TypeConvertorUtil.convert(registrationContext, 1);

		return v2Bean;
	}

	private static Log _log = LogFactoryUtil.getLog(
		RegistrationServiceHandler.class);

	private WSRP_v1_Registration_PortType _registrationDescriptionService;

}