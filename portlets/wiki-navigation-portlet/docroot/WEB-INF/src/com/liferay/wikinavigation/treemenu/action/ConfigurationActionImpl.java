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

package com.liferay.wikinavigation.treemenu.action;

import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portlet.wiki.NoSuchNodeException;
import com.liferay.portlet.wiki.service.WikiNodeServiceUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

/**
 * @author Thiago Moreira
 * @author Jorge Ferrer
 * @author Peter Shin
 */
public class ConfigurationActionImpl extends DefaultConfigurationAction {

	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		validateNode(actionRequest);

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	protected void validateNode(ActionRequest actionRequest) throws Exception{
		long selNodeId = GetterUtil.getLong(
			getParameter(actionRequest, "selNodeId"));

		try {
			WikiNodeServiceUtil.getNode(selNodeId);
		}
		catch (NoSuchNodeException nsne) {
			SessionErrors.add(actionRequest, nsne.getClass().getName());
		}
	}

}