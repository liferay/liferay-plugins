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

package com.liferay.portal.saml.hook;

import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.saml.util.PropsKeys;
import com.liferay.portal.saml.util.SAMLUtil;
import com.liferay.portal.util.PortalUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Mika Koivisto
 */
public class SLOHook extends Action{

	public void run(HttpServletRequest request, HttpServletResponse response)
		throws ActionException {

		long companyId = PortalUtil.getCompanyId(request);

		try {
			boolean enabled = GetterUtil.getBoolean(
				PrefsPropsUtil.getString(companyId, PropsKeys.SAML_IDP_ENABLED));

			if (enabled) {
				SAMLUtil.performLogout(request, response);
			}
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
		
	}

}
