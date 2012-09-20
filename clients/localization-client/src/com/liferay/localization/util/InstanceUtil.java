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

package com.liferay.localization.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletPreferencesThreadLocal;

import java.util.Locale;
import java.util.Map;

/**
 * @author Eric Min
 * @author Brian Wing Shun Chan
 */
public class InstanceUtil {

	public static void initInstance(long companyId) {
		try {
			PortletPreferencesThreadLocal.setStrict(false);

			_localizeRoleNames(companyId);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
		finally {
			PortletPreferencesThreadLocal.setStrict(true);
		}
	}

	private static Locale _getLocale() {
		return LocaleUtil.fromLanguageId(PortletPropsValues.LANGUAGE_ID);
	}

	private static void _localizeRoleNames(long companyId) throws Exception {

		// Regular roles

		for (String name : PortalUtil.getSystemRoles()) {
			Role role = RoleLocalServiceUtil.getRole(companyId, name);

			Map<Locale, String> descriptionMap = role.getDescriptionMap();
			Map<Locale, String> titleMap = role.getTitleMap();

			if (name.equals(RoleConstants.ADMINISTRATOR)) {
				descriptionMap.put(
					_getLocale(),
					PortletPropsValues.ROLE_ADMINISTRATOR_DESCRIPTION);
				titleMap.put(
					_getLocale(), PortletPropsValues.ROLE_ADMINISTRATOR_NAME);
			}
			else if (name.equals(RoleConstants.GUEST)) {
				descriptionMap.put(
					_getLocale(), PortletPropsValues.ROLE_GUEST_DESCRIPTION);
				titleMap.put(_getLocale(), PortletPropsValues.ROLE_GUEST_NAME);
			}
			else if (name.equals(RoleConstants.OWNER)) {
				descriptionMap.put(
					_getLocale(), PortletPropsValues.ROLE_OWNER_DESCRIPTION);
				titleMap.put(_getLocale(), PortletPropsValues.ROLE_OWNER_NAME);
			}
			else if (name.equals(RoleConstants.POWER_USER)) {
				descriptionMap.put(
					_getLocale(),
					PortletPropsValues.ROLE_POWER_USER_DESCRIPTION);
				titleMap.put(
					_getLocale(), PortletPropsValues.ROLE_POWER_USER_NAME);
			}
			else if (name.equals(RoleConstants.USER)) {
				descriptionMap.put(
					_getLocale(), PortletPropsValues.ROLE_USER_DESCRIPTION);
				titleMap.put(_getLocale(), PortletPropsValues.ROLE_USER_NAME);
			}

			RoleLocalServiceUtil.updateRole(
				role.getRoleId(), name, titleMap, descriptionMap,
				RoleConstants.TYPE_REGULAR_LABEL);
		}

		// Organization roles

		for (String name : PortalUtil.getSystemOrganizationRoles()) {
			Role role = RoleLocalServiceUtil.getRole(companyId, name);

			Map<Locale, String> descriptionMap = role.getDescriptionMap();
			Map<Locale, String> titleMap = role.getTitleMap();

			if (name.equals(RoleConstants.ORGANIZATION_ADMINISTRATOR)) {
				descriptionMap.put(
					_getLocale(),
					PortletPropsValues.
						ROLE_ORGANIZATION_ADMINISTRATOR_DESCRIPTION);
				titleMap.put(
					_getLocale(),
					PortletPropsValues.ROLE_ORGANIZATION_ADMINISTRATOR_NAME);
			}
			else if (name.equals(RoleConstants.ORGANIZATION_OWNER)) {
				descriptionMap.put(
					_getLocale(),
					PortletPropsValues.ROLE_ORGANIZATION_OWNER_DESCRIPTION);
				titleMap.put(
					_getLocale(),
					PortletPropsValues.ROLE_ORGANIZATION_OWNER_NAME);
			}
			else if (name.equals(RoleConstants.ORGANIZATION_USER)) {
				descriptionMap.put(
					_getLocale(),
					PortletPropsValues.ROLE_ORGANIZATION_USER_DESCRIPTION);
				titleMap.put(
					_getLocale(),
					PortletPropsValues.ROLE_ORGANIZATION_USER_NAME);
			}

			RoleLocalServiceUtil.updateRole(
				role.getRoleId(), name, titleMap, descriptionMap,
				RoleConstants.TYPE_ORGANIZATION_LABEL);
		}

		// Site roles

		for (String name : PortalUtil.getSystemSiteRoles()) {
			Role role = RoleLocalServiceUtil.getRole(companyId, name);

			Map<Locale, String> descriptionMap = role.getDescriptionMap();
			Map<Locale, String> titleMap = role.getTitleMap();

			if (name.equals(RoleConstants.SITE_ADMINISTRATOR)) {
				descriptionMap.put(
					_getLocale(),
					PortletPropsValues.ROLE_SITE_ADMINISTRATOR_DESCRIPTION);
				titleMap.put(
					_getLocale(),
					PortletPropsValues.ROLE_SITE_ADMINISTRATOR_NAME);
			}
			else if (name.equals(RoleConstants.SITE_MEMBER)) {
				descriptionMap.put(
					_getLocale(),
					PortletPropsValues.ROLE_SITE_MEMBER_DESCRIPTION);
				titleMap.put(
					_getLocale(), PortletPropsValues.ROLE_SITE_MEMBER_NAME);
			}
			else if (name.equals(RoleConstants.SITE_OWNER)) {
				descriptionMap.put(
					_getLocale(),
					PortletPropsValues.ROLE_SITE_OWNER_DESCRIPTION);
				titleMap.put(
					_getLocale(), PortletPropsValues.ROLE_SITE_OWNER_NAME);
			}

			RoleLocalServiceUtil.updateRole(
				role.getRoleId(), name, titleMap, descriptionMap,
				RoleConstants.TYPE_SITE_LABEL);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(InstanceUtil.class);

}