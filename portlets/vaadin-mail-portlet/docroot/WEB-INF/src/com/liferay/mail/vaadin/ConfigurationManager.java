/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.mail.vaadin;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.util.portlet.PortletProps;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Henri Sara
 */
public class ConfigurationManager {

	public List<Integer> getIncomingPorts() {

		return getIntegerList(PortletProps.get("incoming.ports"));
	}

	public List<Integer> getOutgoingPorts() {

		return getIntegerList(PortletProps.get("outgoing.ports"));
	}

	private List<Integer> getIntegerList(String propertyValue) {

		List<Integer> result = new ArrayList<Integer>();
		int[] values = GetterUtil.getIntegerValues(propertyValue.split(","));
		for (int value : values) {
			result.add(value);
		}
		return result;
	}

}