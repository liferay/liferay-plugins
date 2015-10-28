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

package com.liferay.so.messaging;

import com.liferay.deploylistener.messaging.BaseDeployListenerMessageListener;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.LayoutSetPrototype;
import com.liferay.portal.model.Role;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.service.LayoutSetPrototypeLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
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

			// SOS-1574

			//cleanUpSocialOffice(company.getCompanyId());
		}
	}

	protected void cleanUpSocialOffice(long companyId) throws Exception {
		updateGroups(companyId);

		deleteSocialOfficeUserRole(companyId);

		deleteSocialOfficeLayoutSetPrototypes(companyId);

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

		try {
			ExpandoColumn expandoColumn =
				ExpandoColumnLocalServiceUtil.getColumn(
					companyId, LayoutSetPrototype.class.getName(),
					ExpandoTableConstants.DEFAULT_TABLE_NAME,
					SocialOfficeConstants.LAYOUT_SET_PROTOTYPE_KEY);

			ExpandoValueLocalServiceUtil.deleteColumnValues(
				expandoColumn.getColumnId());
		}
		catch (Exception e) {
			_log.error(e, e);
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

	protected void updateGroups(long companyId) throws PortalException {
		ActionableDynamicQuery actionableDynamicQuery =
			GroupLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setCompanyId(companyId);
		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<Group>() {

				@Override
				public void performAction(Group group) throws PortalException {
					if (!group.isRegularSite()) {
						return;
					}

					if (!SocialOfficeServiceUtil.isSocialOfficeGroup(
							group.getGroupId())) {

						return;
					}

					if (group.hasPrivateLayouts()) {
						updateLayoutSetPrototype(group.getGroupId(), true);
					}

					if (group.hasPublicLayouts()) {
						updateLayoutSetPrototype(group.getGroupId(), false);
					}

					UnicodeProperties typeSettingsProperties =
						group.getTypeSettingsProperties();

					typeSettingsProperties.remove(
						"customJspServletContextName");

					GroupLocalServiceUtil.updateGroup(
						group.getGroupId(), typeSettingsProperties.toString());
				}

			});

		actionableDynamicQuery.performActions();
	}

	protected void updateLayoutSetPrototype(long groupId, boolean privateLayout)
		throws PortalException {

		// Layout

		LayoutSet layoutSet = LayoutSetLocalServiceUtil.getLayoutSet(
			groupId, privateLayout);

		LayoutSetPrototype layoutSetPrototype =
			LayoutSetPrototypeLocalServiceUtil.getLayoutSetPrototype(
				layoutSet.getLayoutSetPrototypeId());

		Group layoutSetPrototypeGroup = layoutSetPrototype.getGroup();

		List<Layout> layoutSetPrototypeLayouts =
			LayoutLocalServiceUtil.getLayouts(
				layoutSetPrototypeGroup.getGroupId(), true);

		String[] layoutSetPrototypeLayoutUuids =
			new String[layoutSetPrototypeLayouts.size()];

		for (int i = 0; i < layoutSetPrototypeLayouts.size(); i++) {
			Layout layout = layoutSetPrototypeLayouts.get(i);

			layoutSetPrototypeLayoutUuids[i] = layout.getUuid();
		}

		List<Layout> layouts = LayoutLocalServiceUtil.getLayouts(
			groupId, privateLayout);

		for (Layout layout : layouts) {
			if (ArrayUtil.contains(
					layoutSetPrototypeLayoutUuids,
					layout.getSourcePrototypeLayoutUuid())) {

				layout.setLayoutPrototypeUuid(StringPool.BLANK);
				layout.setLayoutPrototypeLinkEnabled(false);
				layout.setSourcePrototypeLayoutUuid(StringPool.BLANK);

				LayoutLocalServiceUtil.updateLayout(layout);
			}
		}

		// Layout set

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