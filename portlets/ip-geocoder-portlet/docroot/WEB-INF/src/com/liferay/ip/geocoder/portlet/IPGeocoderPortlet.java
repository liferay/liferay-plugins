/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.ip.geocoder.portlet;

import com.liferay.ip.geocoder.IPGeocoder;
import com.liferay.ip.geocoder.IPInfo;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.util.PortalUtil;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

/**
 * @author Julio Camarero
 */
public class IPGeocoderPortlet extends MVCPortlet {

	@Override
	public void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			renderRequest);

		ServiceReference<IPGeocoder> serviceReference =
			_bundleContext.getServiceReference(IPGeocoder.class);

		if (serviceReference != null) {
			IPGeocoder ipGeocoder = _bundleContext.getService(serviceReference);

			if (ipGeocoder != null) {
				IPInfo ipInfo = ipGeocoder.getIPInfo(request.getRemoteAddr());

				renderRequest.setAttribute(IPInfo.class.getName(), ipInfo);
			}
		}

		super.doView(renderRequest, renderResponse);
	}

	@Override
	public void init() throws PortletException {
		Bundle bundle = FrameworkUtil.getBundle(IPGeocoderPortlet.class);

		_bundleContext = bundle.getBundleContext();

		super.init();
	}

	private static BundleContext _bundleContext;

}