/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.so.messaging;

import com.liferay.deploylistener.messaging.BaseDeployListenerMessageListener;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.LayoutSetPrototype;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.service.LayoutSetPrototypeLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.so.service.SocialOfficeServiceUtil;
import com.liferay.so.util.InstanceUtil;
import com.liferay.so.util.LayoutSetPrototypeUtil;
import com.liferay.so.util.SocialOfficeConstants;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Jonathan Lee
 */
public class SODeployListenerMessageListener
	extends BaseDeployListenerMessageListener {

	@Override
	public void onBeforeUndeploy(Message message) throws Exception {
		List<Company> companies = CompanyLocalServiceUtil.getCompanies();

		for (Company company : companies) {
			socialOfficeCleanup(company.getCompanyId());
		}
	}

	protected void removeSocialOfficeLayoutSetPrototypes(long companyId)
		throws Exception {

		LayoutSetPrototype layoutSetPrototype =
			LayoutSetPrototypeUtil.fetchLayoutSetPrototype(
				companyId, SocialOfficeConstants.LAYOUT_SET_PROTOTYPE_KEY_SITE);

		if (layoutSetPrototype != null) {
			try {
				LayoutSetPrototypeLocalServiceUtil.deleteLayoutSetPrototype(
					layoutSetPrototype);
			}
			catch (Exception e) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"Unable to delete site template: " +
							layoutSetPrototype.getName());
				}
			}
		}

		layoutSetPrototype = LayoutSetPrototypeUtil.fetchLayoutSetPrototype(
			companyId,
			SocialOfficeConstants.LAYOUT_SET_PROTOTYPE_KEY_USER_PRIVATE);

		if (layoutSetPrototype != null) {
			try {
				LayoutSetPrototypeLocalServiceUtil.deleteLayoutSetPrototype(
					layoutSetPrototype);
			}
			catch (Exception e) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"Unable to delete site template: " +
							layoutSetPrototype.getName());
				}
			}
		}

		layoutSetPrototype = LayoutSetPrototypeUtil.fetchLayoutSetPrototype(
			companyId,
			SocialOfficeConstants.LAYOUT_SET_PROTOTYPE_KEY_USER_PUBLIC);

		if (layoutSetPrototype != null) {
			try {
				LayoutSetPrototypeLocalServiceUtil.deleteLayoutSetPrototype(
					layoutSetPrototype);
			}
			catch (Exception e) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"Unable to delete site template: " +
							layoutSetPrototype.getName());
				}
			}
		}
	}

	protected void removeSocialOfficeRole(long companyId) throws Exception {
		Role role = RoleLocalServiceUtil.fetchRole(
			companyId, "Social Office User");

		if (role != null) {
			try {
				RoleLocalServiceUtil.deleteRole(role);
			}
			catch (Exception e) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to delete role Social Office User");
				}
			}
		}
	}

	protected void socialOfficeCleanup(long companyId) throws Exception {
		updateUsers(companyId);
		updateGroups(companyId);

		removeSocialOfficeRole(companyId);
		removeSocialOfficeLayoutSetPrototypes(companyId);

		InstanceUtil.setInitialized(companyId, false);
	}

	protected void updateGroups(long companyId) throws Exception {
		List<Group> groups = GroupLocalServiceUtil.getCompanyGroups(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (Group group : groups) {
			if (!group.isRegularSite()) {
				continue;
			}

			if (SocialOfficeServiceUtil.isSocialOfficeGroup(
				group.getGroupId())) {

				if (group.hasPublicLayouts()) {
					updateLayoutSetPrototype(
						LayoutSetLocalServiceUtil.getLayoutSet(
							group.getGroupId(), false));
				}

				if (group.hasPrivateLayouts()) {
					updateLayoutSetPrototype(
						LayoutSetLocalServiceUtil.getLayoutSet(
							group.getGroupId(), true));
				}
			}
		}
	}

	private void updateLayoutSetPrototype(LayoutSet layoutSet)
		throws Exception {

		UnicodeProperties settingsProperties =
			layoutSet.getSettingsProperties();

		settingsProperties.remove("last-merge-time");

		layoutSet.setSettingsProperties(settingsProperties);

		layoutSet.setLayoutSetPrototypeUuid(StringPool.BLANK);
		layoutSet.setLayoutSetPrototypeLinkEnabled(false);

		LayoutSetLocalServiceUtil.updateLayoutSet(layoutSet);

		LayoutSetLocalServiceUtil.updateLookAndFeel(
			layoutSet.getGroupId(), null, null, StringPool.BLANK, false);
	}

	protected void updateUsers(long companyId) throws Exception {
		List<User> users = UserLocalServiceUtil.getCompanyUsers(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		Role role = RoleLocalServiceUtil.fetchRole(
			companyId, "Social Office User");

		if (role == null) {
			return;
		}

		for (User user : users) {
			if (UserLocalServiceUtil.hasRoleUser(
				companyId, role.getName(), user.getUserId(), true)) {

				UserLocalServiceUtil.unsetRoleUsers(
					role.getRoleId(), new long[] {user.getUserId()});
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		SODeployListenerMessageListener.class);

}