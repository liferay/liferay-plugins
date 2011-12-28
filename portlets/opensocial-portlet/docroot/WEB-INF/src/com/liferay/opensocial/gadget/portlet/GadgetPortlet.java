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

package com.liferay.opensocial.gadget.portlet;

import com.liferay.opensocial.gadget.action.ConfigurationActionImpl;
import com.liferay.opensocial.model.Gadget;
import com.liferay.opensocial.shindig.util.ShindigUtil;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.theme.PortletDisplay;

import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;

import org.apache.shindig.gadgets.spec.GadgetSpec;

/**
 * @author Michael Young
 */
public class GadgetPortlet extends BaseGadgetPortlet {

	@Override
	protected Gadget getGadget(RenderRequest renderRequest) throws Exception {
		PortletConfig portletConfig = getPortletConfig();

		return ShindigUtil.getGadget(portletConfig.getPortletName());
	}

	@Override
	protected void overrideConfiguration(
			GadgetSpec gadgetSpec, Portlet portlet,
			PortletDisplay portletDisplay)
		throws Exception {

		String urlConfiguration = portletDisplay.getURLConfiguration();

		if (ShindigUtil.hasUserPrefs(gadgetSpec)) {
			portlet.setConfigurationActionClass(
				ConfigurationActionImpl.class.getName());

			urlConfiguration = urlConfiguration.replaceAll(
				"edit_permissions", "edit_configuration");
		}
		else {
			portlet.setConfigurationActionClass(null);

			urlConfiguration = urlConfiguration.replaceAll(
				"edit_configuration", "edit_permissions");
		}

		portletDisplay.setURLConfiguration(urlConfiguration);
	}

}