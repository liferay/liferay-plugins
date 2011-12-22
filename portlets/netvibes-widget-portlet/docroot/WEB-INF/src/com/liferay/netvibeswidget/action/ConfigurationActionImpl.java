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

package com.liferay.netvibeswidget.action;

import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

/**
 * @author Jorge Ferrer
 * @author Julio Camarero
 * @author Peter Shin
 */
public class ConfigurationActionImpl extends DefaultConfigurationAction {

	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String[] htmlAttributes = StringUtil.split(
			getParameter(actionRequest, "htmlAttributes"), StringPool.NEW_LINE);

		for (String htmlAttribute : htmlAttributes) {
			int pos = htmlAttribute.indexOf(StringPool.EQUAL);

			if (pos == -1) {
				continue;
			}

			String key = htmlAttribute.substring(0, pos);
			String value = htmlAttribute.substring(
				pos + 1, htmlAttribute.length());

			setPreference(actionRequest, key, value);
		}

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

}