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

package com.liferay.opensocial.adhocgadget.portlet;

import com.liferay.opensocial.gadget.portlet.BaseGadgetPortlet;
import com.liferay.opensocial.model.Gadget;
import com.liferay.opensocial.shindig.util.ShindigUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Portlet;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;

/**
 * @author Michael Young
 */
public class AdhocGadgetPortlet extends BaseGadgetPortlet {

	@Override
	protected Gadget getGadget(RenderRequest renderRequest) throws Exception {
		Portlet portlet = (Portlet)renderRequest.getAttribute(
			WebKeys.RENDER_PORTLET);

		PortletPreferences portletPreferences =
			PortletPreferencesFactoryUtil.getPortletSetup(
				renderRequest, portlet.getPortletId());

		return ShindigUtil.getGadget(portletPreferences);
	}

}