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

package com.liferay.wsrp.bind;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.wsrp.proxy.TypeConvertorUtil;

import java.rmi.RemoteException;

import oasis.names.tc.wsrp.v1.intf.WSRP_v1_ServiceDescription_PortType;
import oasis.names.tc.wsrp.v1.types.GetServiceDescription;
import oasis.names.tc.wsrp.v1.types.ServiceDescription;
import oasis.names.tc.wsrp.v2.intf.WSRP_v2_ServiceDescription_PortType;

/**
 * @author Michael Young
 */
public class V1ServiceDescriptionServiceImpl
	extends BaseServiceImpl implements WSRP_v1_ServiceDescription_PortType {

	public ServiceDescription getServiceDescription(
			GetServiceDescription v1GetServiceDescription)
		throws RemoteException {

		try {
			return doGetServiceDescription(v1GetServiceDescription);
		}
		catch (RemoteException re) {
			_log.error(re, re);

			throw re;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	protected ServiceDescription doGetServiceDescription(
			GetServiceDescription v1GetServiceDescription)
		throws Exception {

		oasis.names.tc.wsrp.v2.types.GetServiceDescription
			v2GetServiceDescription =
				(oasis.names.tc.wsrp.v2.types.GetServiceDescription)
					TypeConvertorUtil.convert(v1GetServiceDescription, 1);

		oasis.names.tc.wsrp.v2.types.ServiceDescription v2ServiceDescription =
			_v2ServiceDescriptionService.getServiceDescription(
				v2GetServiceDescription);

		ServiceDescription v1ServiceDescription =
			(ServiceDescription)TypeConvertorUtil.convert(
				v2ServiceDescription, 2);

		return v1ServiceDescription;
	}

	private static Log _log = LogFactoryUtil.getLog(
		V1ServiceDescriptionServiceImpl.class);

	private static WSRP_v2_ServiceDescription_PortType
		_v2ServiceDescriptionService = new V2ServiceDescriptionServiceImpl();

}