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

package com.liferay.compat.hook.action;

import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalClassInvoker;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.Ticket;
import com.liferay.portal.model.TicketConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.AuthTokenUtil;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.TicketLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.PortletURLFactoryUtil;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Jonathan Lee
 */
public class CompatUpdatePasswordAction extends BaseStrutsAction {

	@Override
	public String execute(
		StrutsAction originalStrutsAction, HttpServletRequest request,
			HttpServletResponse response)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		Ticket ticket = getTicket(request);

		if (!themeDisplay.isSignedIn() && (ticket == null)) {
			return originalStrutsAction.execute(request, response);
		}

		String cmd = ParamUtil.getString(request, Constants.CMD);

		if (Validator.isNull(cmd)) {
			return originalStrutsAction.execute(request, response);
		}

		try {
			updatePassword(request, response, themeDisplay, ticket);

			String redirect = ParamUtil.getString(request, "referer");

			if (Validator.isNull(redirect)) {
				PortletURL portletURL = PortletURLFactoryUtil.create(
					request, PortletKeys.LOGIN, themeDisplay.getPlid(),
					PortletRequest.RENDER_PHASE);

				redirect = portletURL.toString();
			}

			response.sendRedirect(redirect);

			return null;
		}
		catch (Exception e) {
			return originalStrutsAction.execute(request, response);
		}
	}

	protected Ticket getTicket(HttpServletRequest request) {
		String ticketKey = ParamUtil.getString(request, "ticketKey");

		if (Validator.isNull(ticketKey)) {
			return null;
		}

		try {
			Ticket ticket = TicketLocalServiceUtil.getTicket(ticketKey);

			if (ticket.getType() != TicketConstants.TYPE_PASSWORD) {
				return null;
			}

			if (!ticket.isExpired()) {
				return ticket;
			}
			else {
				TicketLocalServiceUtil.deleteTicket(ticket);
			}
		}
		catch (Exception e) {
		}

		return null;
	}

	protected boolean isValidatePassword(HttpServletRequest request) {
		HttpSession session = request.getSession();

		Boolean setupWizardPasswordUpdated = (Boolean)session.getAttribute(
			"SETUP_WIZARD_PASSWORD_UPDATED");

		if ((setupWizardPasswordUpdated != null) &&
			setupWizardPasswordUpdated) {

			return false;
		}

		return true;
	}

	protected void updatePassword(
			HttpServletRequest request, HttpServletResponse response,
			ThemeDisplay themeDisplay, Ticket ticket)
		throws Exception {

		AuthTokenUtil.check(request);

		long userId = 0;

		if (ticket != null) {
			userId = ticket.getClassPK();
		}
		else {
			userId = themeDisplay.getUserId();
		}

		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		boolean passwordReset = false;

		boolean previousValidate =
			(Boolean)PortalClassInvoker.invoke(true, _isValidate);

		try {
			boolean currentValidate = isValidatePassword(request);

			PortalClassInvoker.invoke(true, _setValidate, currentValidate);

			UserLocalServiceUtil.updatePassword(
				userId, password1, password2, passwordReset);
		}
		finally {
			PortalClassInvoker.invoke(true, _setValidate, previousValidate);
		}

		if (ticket != null) {
			TicketLocalServiceUtil.deleteTicket(ticket);

			User user = UserLocalServiceUtil.getUser(userId);

			Company company = CompanyLocalServiceUtil.getCompanyById(
				user.getCompanyId());

			String login = null;

			String authType = company.getAuthType();

			if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
				login = user.getEmailAddress();
			}
			else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
				login = user.getScreenName();
			}
			else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
				login = String.valueOf(userId);
			}

			PortalClassInvoker.invoke(
				true, _login, request, response, login, password1, false, null);
		}
		else if (GetterUtil.getBoolean(
			PropsUtil.get(PropsKeys.SESSION_STORE_PASSWORD))) {

			HttpSession session = request.getSession();

			session.setAttribute(WebKeys.USER_PASSWORD, password1);
		}
	}

	private static final String _LoginUtil_CLASS_NAME =
		"com.liferay.portlet.login.util.LoginUtil";

	private static final String _PwdToolkitUtilThreadLocal_CLASS_NAME =
		"com.liferay.portal.security.pwd.PwdToolkitUtilThreadLocal";

	private static MethodKey _isValidate =
		new MethodKey(_PwdToolkitUtilThreadLocal_CLASS_NAME, "isValidate");

	private static MethodKey _login=
		new MethodKey(
			_LoginUtil_CLASS_NAME, "login", HttpServletRequest.class,
			HttpServletResponse.class, String.class, String.class,
			boolean.class, String.class);

	private static MethodKey _setValidate =
		new MethodKey(
			_PwdToolkitUtilThreadLocal_CLASS_NAME, "setValidate",
			boolean.class);

}