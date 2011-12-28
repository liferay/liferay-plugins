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

package com.liferay.googlemaps.portlet;

import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletSession;

/**
 * @author Brian Wing Shun Chan
 */
public class GoogleMapsPortlet extends MVCPortlet {

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		PortletSession portletSession = actionRequest.getPortletSession();

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (cmd.equals("saveDirectionsAddress")) {
			String directionsAddress = ParamUtil.getString(
				actionRequest, "directionsAddress");

			portletSession.setAttribute("directionsAddress", directionsAddress);
		}
		else if (cmd.equals("saveMapAddress")) {
			String mapAddress = ParamUtil.getString(
				actionRequest, "mapAddress");

			portletSession.setAttribute("mapAddress", mapAddress);
		}
	}

}