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

package com.liferay.localization.util;

import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.DuplicateColumnNameException;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.util.portlet.PortletProps;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * @author Eric Min
 * @author Brian Wing Shun Chan
 */
public class InstanceUtil implements PortletPropsKeys {

	public static void initInstance(long companyId) {
		try {
			_localizeRoleNames(companyId);
			_localizeUsers(companyId);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static void _localizeRoleNames(long companyId) throws Exception {
		for (String languageId : PortletPropsValues.LANGUAGE_IDS) {
			_localizeRoleNames(companyId, languageId);
		}
	}

	private static void _localizeRoleNames(long companyId, String languageId)
		throws Exception {

		ServiceContext serviceContext = new ServiceContext();

		// Regular roles

		for (String name : PortalUtil.getSystemRoles()) {
			Role role = RoleLocalServiceUtil.loadGetRole(companyId, name);

			Map<Locale, String> descriptionMap = role.getDescriptionMap();
			Map<Locale, String> titleMap = role.getTitleMap();

			if (name.equals(RoleConstants.ADMINISTRATOR)) {
				_putMap(
					descriptionMap, languageId, ROLE_ADMINISTRATOR_DESCRIPTION);
				_putMap(titleMap, languageId, ROLE_ADMINISTRATOR_NAME);
			}
			else if (name.equals(RoleConstants.GUEST)) {
				_putMap(descriptionMap, languageId, ROLE_GUEST_DESCRIPTION);
				_putMap(titleMap, languageId, ROLE_GUEST_NAME);
			}
			else if (name.equals(RoleConstants.OWNER)) {
				_putMap(descriptionMap, languageId, ROLE_OWNER_DESCRIPTION);
				_putMap(titleMap, languageId, ROLE_OWNER_NAME);
			}
			else if (name.equals(RoleConstants.POWER_USER)) {
				_putMap(
					descriptionMap, languageId, ROLE_POWER_USER_DESCRIPTION);
				_putMap(titleMap, languageId, ROLE_POWER_USER_NAME);
			}
			else if (name.equals(RoleConstants.USER)) {
				_putMap(descriptionMap, languageId, ROLE_USER_DESCRIPTION);
				_putMap(titleMap, languageId, ROLE_USER_NAME);
			}

			RoleLocalServiceUtil.updateRole(
				role.getRoleId(), name, titleMap, descriptionMap,
				RoleConstants.TYPE_REGULAR_LABEL, serviceContext);
		}

		// Organization roles

		for (String name : PortalUtil.getSystemOrganizationRoles()) {
			Role role = RoleLocalServiceUtil.loadGetRole(companyId, name);

			Map<Locale, String> descriptionMap = role.getDescriptionMap();
			Map<Locale, String> titleMap = role.getTitleMap();

			if (name.equals(RoleConstants.ORGANIZATION_ADMINISTRATOR)) {
				_putMap(
					descriptionMap, languageId,
					ROLE_ORGANIZATION_ADMINISTRATOR_DESCRIPTION);
				_putMap(
					titleMap, languageId, ROLE_ORGANIZATION_ADMINISTRATOR_NAME);
			}
			else if (name.equals(RoleConstants.ORGANIZATION_OWNER)) {
				_putMap(
					descriptionMap, languageId,
					ROLE_ORGANIZATION_OWNER_DESCRIPTION);
				_putMap(titleMap, languageId, ROLE_ORGANIZATION_OWNER_NAME);
			}
			else if (name.equals(RoleConstants.ORGANIZATION_USER)) {
				_putMap(
					descriptionMap, languageId,
					ROLE_ORGANIZATION_USER_DESCRIPTION);
				_putMap(titleMap, languageId, ROLE_ORGANIZATION_USER_NAME);
			}

			RoleLocalServiceUtil.updateRole(
				role.getRoleId(), name, titleMap, descriptionMap,
				RoleConstants.TYPE_ORGANIZATION_LABEL, serviceContext);
		}

		// Site roles

		for (String name : PortalUtil.getSystemSiteRoles()) {
			Role role = RoleLocalServiceUtil.loadGetRole(companyId, name);

			Map<Locale, String> descriptionMap = role.getDescriptionMap();
			Map<Locale, String> titleMap = role.getTitleMap();

			if (name.equals(RoleConstants.SITE_ADMINISTRATOR)) {
				_putMap(
					descriptionMap, languageId,
					ROLE_SITE_ADMINISTRATOR_DESCRIPTION);
				_putMap(titleMap, languageId, ROLE_SITE_ADMINISTRATOR_NAME);
			}
			else if (name.equals(RoleConstants.SITE_MEMBER)) {
				_putMap(
					descriptionMap, languageId, ROLE_SITE_MEMBER_DESCRIPTION);
				_putMap(titleMap, languageId, ROLE_SITE_MEMBER_NAME);
			}
			else if (name.equals(RoleConstants.SITE_OWNER)) {
				_putMap(
					descriptionMap, languageId, ROLE_SITE_OWNER_DESCRIPTION);
				_putMap(titleMap, languageId, ROLE_SITE_OWNER_NAME);
			}

			RoleLocalServiceUtil.updateRole(
				role.getRoleId(), name, titleMap, descriptionMap,
				RoleConstants.TYPE_SITE_LABEL, serviceContext);
		}
	}

	private static void _localizeUsers(long companyId) throws Exception {
		Company company = CompanyLocalServiceUtil.getCompany(companyId);

		ExpandoBridge expandoBridge = company.getExpandoBridge();

		String attributeName =
			"localizationUpdated_" + PortletPropsValues.COMPANY_DEFAULT_LOCALE;

		boolean localizationUpdated = GetterUtil.getBoolean(
			expandoBridge.getAttribute(attributeName, false));

		if (localizationUpdated) {
			return;
		}

		try {
			expandoBridge.addAttribute(
				attributeName, ExpandoColumnConstants.BOOLEAN, Boolean.FALSE,
				false);
		}
		catch (DuplicateColumnNameException dcne) {
		}

		expandoBridge.setAttribute(attributeName, Boolean.TRUE, false);

		CompanyLocalServiceUtil.updateCompany(company);

		ActionableDynamicQuery actionableDynamicQuery =
			UserLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					Property property = PropertyFactoryUtil.forName(
						"createDate");

					dynamicQuery.add(property.eqProperty("modifiedDate"));
				}

			});
		actionableDynamicQuery.setCompanyId(companyId);
		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<User>() {

				@Override
				public void performAction(User user) throws PortalException {
					user.setModifiedDate(new Date());
					user.setLanguageId(
						PortletPropsValues.COMPANY_DEFAULT_LOCALE);
					user.setTimeZoneId(
						PortletPropsValues.COMPANY_DEFAULT_TIME_ZONE);

					UserLocalServiceUtil.updateUser(user);
				}

			});

		actionableDynamicQuery.performActions();
	}

	private static void _putMap(
		Map<Locale, String> map, String languageId, String key) {

		Locale locale = LocaleUtil.fromLanguageId(languageId);

		String value = PortletProps.get(key, new Filter(languageId));

		map.put(locale, value);
	}

	private static Log _log = LogFactoryUtil.getLog(InstanceUtil.class);

}