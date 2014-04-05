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

package com.liferay.shibboleth.hook.events;

import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.shibboleth.util.PropsKeys;
import com.liferay.shibboleth.util.PropsValues;
import com.liferay.shibboleth.util.ShibbolethUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Eric Chin
 */
public class ShibbolethLogoutAction extends Action {

	@Override
	public void run(HttpServletRequest request, HttpServletResponse response)
		throws ActionException {

		try {
			long companyId = PortalUtil.getCompanyId(request);

			if (!ShibbolethUtil.isEnabled(companyId)) {
				return;
			}

			String pathInfo = request.getPathInfo();

			if (pathInfo.contains("/portal/logout")) {
				HttpSession session = request.getSession();

				String shibbolethLogoutURL = PrefsPropsUtil.getString(
					companyId, PropsKeys.SHIBBOLETH_LOGOUT_URL,
					PropsValues.SHIBBOLETH_LOGOUT_URL);

				session.invalidate();

				if (Validator.isNotNull(shibbolethLogoutURL)) {
					response.sendRedirect(shibbolethLogoutURL);
				}
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ShibbolethLogoutAction.class);

}