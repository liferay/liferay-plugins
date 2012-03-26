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

package com.liferay.calendar.search;

import com.liferay.portal.kernel.dao.search.DAOParamUtil;

import javax.portlet.PortletRequest;

/**
 * @author Eduardo Lundgren
 * @author Fabio Pezzutto
 */
public class CalendarResourceSearchTerms extends CalendarResourceDisplayTerms {

	public CalendarResourceSearchTerms(PortletRequest portletRequest) {
		super(portletRequest);

		active = DAOParamUtil.getBoolean(portletRequest, ACTIVE);
		code = DAOParamUtil.getLike(portletRequest, CODE);
		description = DAOParamUtil.getLike(portletRequest, DESCRIPTION);
		name = DAOParamUtil.getLike(portletRequest, NAME);
		type = DAOParamUtil.getString(portletRequest, TYPE);
	}

}