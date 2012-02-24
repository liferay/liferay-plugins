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

package com.liferay.opensocial.shindig.servlet;

import com.google.inject.Injector;

import com.liferay.opensocial.shindig.util.ShindigUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CookieKeys;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.AuthenticatedUserUUIDStoreUtil;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.util.CookieUtil;
import com.liferay.util.Encryptor;
import com.liferay.util.EncryptorException;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServletRequest;

import org.apache.shindig.common.servlet.InjectedFilter;

import static org.apache.shindig.common.servlet.GuiceServletContextListener.*;

/**
 * @author Michael Young
 * @author Dennis Ju
 */
public class ShindigFilter extends InjectedFilter {

	public void destroy() {
	}

	public void doFilter(
			ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain)
		throws IOException, ServletException {

		if (injector == null) {
			HttpServletRequest httpServletRequest =
				(HttpServletRequest)servletRequest;

			_init(httpServletRequest.getSession().getServletContext());
		}

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (permissionChecker == null) {
			setPermissionChecker(servletRequest);
		}

		String host = servletRequest.getServerName().concat(
			StringPool.COLON).concat(
				String.valueOf(servletRequest.getServerPort()));

		ShindigUtil.setHost(host);

		filterChain.doFilter(servletRequest, servletResponse);
	}

	public void init(FilterConfig config) throws ServletException {

		// LPS-23577

		if (ServerDetector.isWebSphere()) {
			injector = null;
		}
		else {
			super.init(config);
		}
	}

	protected boolean setPermissionChecker(ServletRequest servletRequest) {
		String companyIdString = CookieUtil.get(
			(HttpServletRequest)servletRequest, CookieKeys.COMPANY_ID);

		if (Validator.isNull(companyIdString)) {
			return false;
		}

		long companyId = GetterUtil.getLong(companyIdString);

		String userUUID = StringPool.BLANK;

		try {
			Company company = CompanyLocalServiceUtil.fetchCompany(companyId);

			if (company == null) {
				return false;
			}

			String userUUIDString = CookieUtil.get(
				(HttpServletRequest)servletRequest, CookieKeys.USER_UUID);

			if (Validator.isNull(userUUIDString)) {
				return false;
			}

			userUUID = GetterUtil.getString(
				Encryptor.decrypt(company.getKeyObj(), userUUIDString));
		}
		catch (EncryptorException ee) {
			return false;
		}
		catch (Exception e) {
			_log.error(e, e);

			return false;
		}

		if (!AuthenticatedUserUUIDStoreUtil.exists(userUUID)) {
			return false;
		}

		String userIdString = userUUID.substring(
			0, userUUID.indexOf(StringPool.PERIOD));

		long userId = GetterUtil.getLong(userIdString);

		try {
			User user = UserLocalServiceUtil.getUserById(userId);

			PrincipalThreadLocal.setName(userIdString);

			PermissionChecker permissionChecker =
				PermissionCheckerFactoryUtil.create(user);

			PermissionThreadLocal.setPermissionChecker(permissionChecker);
		}
		catch (Exception e) {
			_log.error(e, e);

			return false;
		}

		return true;
	}

	private void _init(ServletContext context) throws ServletException {
		injector = (Injector)context.getAttribute(INJECTOR_ATTRIBUTE);

		if (injector == null) {
			injector = (Injector)context.getAttribute(INJECTOR_NAME);

			if (injector == null) {
				throw new UnavailableException(
					"Guice Injector not found! Make sure you registered " +
						GuiceServletContextListener.class.getName() +
							" as a listener");
			}
		}

		injector.injectMembers(this);
	}

	private static Log _log = LogFactoryUtil.getLog(ShindigFilter.class);

}