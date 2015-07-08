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

package com.liferay.calendar.search;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequest;

/**
 * @author Eduardo Lundgren
 * @author Fabio Pezzutto
 */
public class CalendarResourceDisplayTerms extends DisplayTerms {

	public static final String ACTIVE = "active";

	public static final String CODE = "code";

	public static final String DESCRIPTION = "description";

	public static final String NAME = "name";

	public static final String SCOPE = "scope";

	public CalendarResourceDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		active = ParamUtil.getBoolean(portletRequest, ACTIVE, true);
		code = ParamUtil.getString(portletRequest, CODE);
		description = ParamUtil.getString(portletRequest, DESCRIPTION);
		name = ParamUtil.getString(portletRequest, NAME);
		scope = ParamUtil.getLong(portletRequest, SCOPE);
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public long getScope() {
		return scope;
	}

	public boolean isActive() {
		return active;
	}

	protected boolean active;
	protected String code;
	protected String description;
	protected String name;
	protected long scope;

}