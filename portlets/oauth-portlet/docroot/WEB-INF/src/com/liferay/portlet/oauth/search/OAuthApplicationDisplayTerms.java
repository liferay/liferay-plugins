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

package com.liferay.portlet.oauth.search;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.oauth.util.OAuthConstants;

import javax.portlet.PortletRequest;

/**
 *
 * @author Igor Beslic
 * @author Raymond Augé
 *
 */
public class OAuthApplicationDisplayTerms extends DisplayTerms
		implements OAuthConstants {

	public OAuthApplicationDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		applicationId = ParamUtil.getInteger(portletRequest, APPLICATION_ID);
		name = ParamUtil.getString(portletRequest, NAME);
	}

	public int getApplicationId() {
		return applicationId;
	}

	public String getName() {
		return name;
	}

	protected int applicationId;
	protected String name;

}