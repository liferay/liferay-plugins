/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.so.hook.events;

import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.so.util.GroupConstants;

import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jonathan Lee
 */
public class ServicePostAction extends Action {

	@Override
	public void run(HttpServletRequest request, HttpServletResponse response) {
		try {
			doRun(request, response);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}
	}

	protected void checkSocialOfficeUsersConfigured(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!themeDisplay.isSignedIn()) {
			return;
		}

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		if (!permissionChecker.isCompanyAdmin() ||
			isSocialOfficeUsersConfigured(themeDisplay.getCompanyId())) {

			return;
		}

		User user = themeDisplay.getUser();

		if (user.isPasswordReset()) {
			return;
		}

		if (!user.isAgreedToTermsOfUse()) {
			boolean termsOfUseRequired = false;

			try {
				termsOfUseRequired = PrefsPropsUtil.getBoolean(
					user.getCompanyId(), PropsKeys.TERMS_OF_USE_REQUIRED);
			}
			catch (Exception e) {
			}

			if (termsOfUseRequired) {
				return;
			}
		}

		if (Validator.isNull(user.getReminderQueryQuestion()) ||
			Validator.isNull(user.getReminderQueryAnswer())) {

			if (PrefsPropsUtil.getBoolean(
					user.getCompanyId(),
					PropsKeys.USERS_REMINDER_QUERIES_ENABLED)) {

				return;
			}
		}

		if (Validator.isNotNull(
				request.getParameter(
					"_1_WAR_soconfigurationsportlet_mvcPath"))) {

			setSocialOfficeUsersConfigured(themeDisplay.getCompanyId(), true);

			return;
		}

		Group group = GroupLocalServiceUtil.getGroup(
			themeDisplay.getCompanyId(), GroupConstants.CONTROL_PANEL);

		PortletURL portletURL = PortletURLFactoryUtil.create(
			request, "1_WAR_soconfigurationsportlet",
			group.getDefaultPrivatePlid(), PortletRequest.RENDER_PHASE);

		portletURL.setParameter("mvcPath", "/view.jsp");
		portletURL.setPortletMode(PortletMode.VIEW);
		portletURL.setWindowState(WindowState.MAXIMIZED);

		response.sendRedirect(portletURL.toString());
	}

	protected void doRun(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		checkSocialOfficeUsersConfigured(request, response);
	}

	protected boolean isSocialOfficeUsersConfigured(long companyId) {
		try {
			Group group = GroupLocalServiceUtil.getCompanyGroup(companyId);

			UnicodeProperties typeSettingsProperties =
				group.getTypeSettingsProperties();

			return GetterUtil.getBoolean(
				typeSettingsProperties.getProperty(
					"social-office-users-configured"));
		}
		catch (Exception e) {
			return false;
		}
	}

	protected void setSocialOfficeUsersConfigured(
			long companyId, boolean socialOfficeUsersConfigured)
		throws Exception {

		Group group = GroupLocalServiceUtil.getCompanyGroup(companyId);

		UnicodeProperties typeSettingsProperties =
			group.getTypeSettingsProperties();

		typeSettingsProperties.setProperty(
			"social-office-users-configured",
			String.valueOf(socialOfficeUsersConfigured));

		GroupLocalServiceUtil.updateGroup(
			group.getGroupId(), typeSettingsProperties.toString());
	}

	private static Log _log = LogFactoryUtil.getLog(ServicePostAction.class);

}