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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.LayoutSetPrototype;
import com.liferay.portal.model.Role;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.service.LayoutSetPrototypeLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.so.service.SocialOfficeServiceUtil;
import com.liferay.so.util.InstanceUtil;
import com.liferay.so.util.LayoutSetPrototypeUtil;
import com.liferay.so.util.RoleConstants;
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
			cleanUpSocialOffice(company.getCompanyId());
		}
	}

	protected void cleanUpSocialOffice(long companyId) throws Exception {
		updateGroups(companyId);

		deleteSocialOfficeLayoutSetPrototypes(companyId);
		deleteSocialOfficeUserRole(companyId);

		InstanceUtil.setInitialized(companyId, false);
	}

	protected void deleteSocialOfficeLayoutSetPrototypes(long companyId)
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
				_log.error(e, e);
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
				_log.error(e, e);
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
				_log.error(e, e);
			}
		}
	}

	protected void deleteSocialOfficeUserRole(long companyId) throws Exception {
		Role role = RoleLocalServiceUtil.fetchRole(
			companyId, RoleConstants.SOCIAL_OFFICE_USER);

		if (role != null) {
			try {
				RoleLocalServiceUtil.deleteRole(role);
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
	}

	protected void updateGroups(long companyId) throws Exception {
		int count = GroupLocalServiceUtil.getCompanyGroupsCount(companyId);

		int pages = count / Indexer.DEFAULT_INTERVAL;

		for (int i = 0; i <= pages; i++) {
			int start = (i * Indexer.DEFAULT_INTERVAL);
			int end = start + Indexer.DEFAULT_INTERVAL;

			List<Group> groups = GroupLocalServiceUtil.getCompanyGroups(
				companyId, start, end);

			for (Group group : groups) {
				if (!group.isRegularSite()) {
					continue;
				}

				if (!SocialOfficeServiceUtil.isSocialOfficeGroup(
						group.getGroupId())) {

					continue;
				}

				if (group.hasPrivateLayouts()) {
					updateLayoutSetPrototype(group.getGroupId(), true);
				}

				if (group.hasPublicLayouts()) {
					updateLayoutSetPrototype(group.getGroupId(), false);
				}
			}
		}
	}

	protected void updateLayoutSetPrototype(long groupId, boolean privateLayout)
		throws Exception {

		LayoutSet layoutSet = LayoutSetLocalServiceUtil.getLayoutSet(
			groupId, privateLayout);

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

	private static Log _log = LogFactoryUtil.getLog(
		SODeployListenerMessageListener.class);

}