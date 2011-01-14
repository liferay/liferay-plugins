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

package com.liferay.portal.saml.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.saml.session.SessionManagerUtil;
import com.liferay.portal.saml.util.PropsKeys;
import com.liferay.portal.saml.util.SAMLMetadataUtil;
import com.liferay.portal.saml.util.WebKeys;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.util.CookieUtil;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author Mika Koivisto
 */
public class KeepaliveAction implements StrutsAction {

	public String execute(
		HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		User user = PortalUtil.getUser(request);

		long companyId = PortalUtil.getCompanyId(request);

		boolean enabled = GetterUtil.getBoolean(
			PrefsPropsUtil.getString(companyId, PropsKeys.SAML_IDP_ENABLED));

		if (user != null && enabled) {
			doKeepAlive(user, request, response);
		}
		else {
			return "/portal/status";
		}

		return null;
	}

	protected void doKeepAlive(
			User user, HttpServletRequest request, HttpServletResponse response)
		throws PortalException, SystemException {

		List<String> urls = new ArrayList<String>();

		String issuer = ParamUtil.getString(request, "entityId");

		String ssoSessionId =
			CookieUtil.get(request, WebKeys.SSOSESSIONID);

		List<String> sps = SessionManagerUtil.getActiveSPs(ssoSessionId);

		for (String spIssuer : sps) {
			if (issuer.equals(spIssuer)) {
				continue;
			}

			String keepaliveURL = SAMLMetadataUtil.getKeepaliveURL(
				user.getCompanyId(), spIssuer);

			if (Validator.isNotNull(keepaliveURL)) {
				urls.add(keepaliveURL);
			}
		}

		request.setAttribute(WebKeys.KEEPALIVE_URL_LIST, urls);

		response.setHeader(
			HttpHeaders.CACHE_CONTROL,
			HttpHeaders.CACHE_CONTROL_NO_CACHE_NO_STORE_VALUE);
		response.setHeader(
			HttpHeaders.PRAGMA, HttpHeaders.CACHE_CONTROL_NO_CACHE_VALUE);

		response.setContentType(ContentTypes.TEXT_JAVASCRIPT);

		try {
			request.getRequestDispatcher(
			"/html/portal/saml/keepalive.jsp").include(request, response);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
}
}
