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

package com.liferay.resourcesimporter.util;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.LayoutPrototype;
import com.liferay.portal.kernel.model.LayoutSetPrototype;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutPrototypeLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutSetPrototypeLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public abstract class BaseImporter implements Importer {

	@Override
	public void afterPropertiesSet() throws Exception {
		User user = UserLocalServiceUtil.getDefaultUser(companyId);

		userId = user.getUserId();

		if (isCompanyGroup()) {
			return;
		}

		Group group = null;

		if (targetClassName.equals(LayoutSetPrototype.class.getName())) {
			LayoutSetPrototype layoutSetPrototype = getLayoutSetPrototype(
				companyId, targetValue);

			if (layoutSetPrototype != null) {
				existing = true;
			}
			else {
				layoutSetPrototype =
					LayoutSetPrototypeLocalServiceUtil.addLayoutSetPrototype(
						userId, companyId, getTargetValueMap(),
						new HashMap<Locale, String>(), true, true,
						new ServiceContext());
			}

			group = layoutSetPrototype.getGroup();

			targetClassPK = layoutSetPrototype.getLayoutSetPrototypeId();
		}
		else if (targetClassName.equals(Group.class.getName())) {
			if (targetValue.equals(GroupConstants.GLOBAL)) {
				group = GroupLocalServiceUtil.getCompanyGroup(companyId);
			}
			else if (targetValue.equals(GroupConstants.GUEST)) {
				group = GroupLocalServiceUtil.getGroup(
					companyId, GroupConstants.GUEST);

				List<Layout> layouts = LayoutLocalServiceUtil.getLayouts(
					group.getGroupId(), false,
					LayoutConstants.DEFAULT_PARENT_LAYOUT_ID, false, 0, 1);

				if (!layouts.isEmpty()) {
					Layout layout = layouts.get(0);

					LayoutTypePortlet layoutTypePortlet =
						(LayoutTypePortlet)layout.getLayoutType();

					List<String> portletIds = layoutTypePortlet.getPortletIds();

					if (portletIds.size() != 2) {
						existing = true;
					}

					for (String portletId : portletIds) {
						if (!portletId.equals("47") &&
							!portletId.equals("58")) {

							existing = true;
						}
					}
				}
			}
			else {
				group = GroupLocalServiceUtil.fetchGroup(
					companyId, targetValue);

				if (group != null) {
					existing = true;
				}
				else {
					group = GroupLocalServiceUtil.addGroup(
						userId, GroupConstants.DEFAULT_PARENT_GROUP_ID,
						StringPool.BLANK,
						GroupConstants.DEFAULT_PARENT_GROUP_ID,
						GroupConstants.DEFAULT_LIVE_GROUP_ID, targetValue,
						StringPool.BLANK, GroupConstants.TYPE_SITE_OPEN, true,
						GroupConstants.DEFAULT_MEMBERSHIP_RESTRICTION, null,
						true, true, new ServiceContext());
				}
			}

			targetClassPK = group.getGroupId();
		}

		if (group != null) {
			groupId = group.getGroupId();
		}
	}

	@Override
	public long getGroupId() {
		return groupId;
	}

	@Override
	public String getTargetClassName() {
		return targetClassName;
	}

	@Override
	public long getTargetClassPK() {
		return targetClassPK;
	}

	public Map<Locale, String> getTargetValueMap() {
		Map<Locale, String> targetValueMap = new HashMap<>();

		Locale locale = LocaleUtil.getDefault();

		targetValueMap.put(locale, targetValue);

		return targetValueMap;
	}

	@Override
	public boolean isCompanyGroup() throws Exception {
		Group group = GroupLocalServiceUtil.fetchGroup(groupId);

		if (group == null) {
			return false;
		}

		return group.isCompany();
	}

	@Override
	public boolean isDeveloperModeEnabled() {
		return developerModeEnabled;
	}

	@Override
	public boolean isExisting() {
		return existing;
	}

	@Override
	public void setAppendVersion(boolean appendVersion) {
		this.appendVersion = appendVersion;
	}

	@Override
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	@Override
	public void setDeveloperModeEnabled(boolean developerModeEnabled) {
		this.developerModeEnabled = developerModeEnabled;
	}

	@Override
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	@Override
	public void setResourcesDir(String resourcesDir) {
		this.resourcesDir = resourcesDir;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Override
	public void setServletContextName(String servletContextName) {
		this.servletContextName = servletContextName;
	}

	@Override
	public void setTargetClassName(String targetClassName) {
		this.targetClassName = targetClassName;
	}

	@Override
	public void setTargetValue(String targetValue) {
		this.targetValue = targetValue;
	}

	@Override
	public void setUpdateModeEnabled(boolean updateModeEnabled) {
		this.updateModeEnabled = updateModeEnabled;
	}

	@Override
	public void setVersion(String version) {
		this.version = version;
	}

	protected LayoutPrototype getLayoutPrototype(long companyId, String name) {
		Locale locale = LocaleUtil.getDefault();

		List<LayoutPrototype> layoutPrototypes =
			LayoutPrototypeLocalServiceUtil.search(
				companyId, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		for (LayoutPrototype layoutPrototype : layoutPrototypes) {
			if (name.equals(layoutPrototype.getName(locale))) {
				return layoutPrototype;
			}
		}

		return null;
	}

	protected LayoutSetPrototype getLayoutSetPrototype(
			long companyId, String name)
		throws Exception {

		Locale locale = LocaleUtil.getDefault();

		List<LayoutSetPrototype> layoutSetPrototypes =
			LayoutSetPrototypeLocalServiceUtil.search(
				companyId, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		for (LayoutSetPrototype layoutSetPrototype : layoutSetPrototypes) {
			if (name.equals(layoutSetPrototype.getName(locale))) {
				return layoutSetPrototype;
			}
		}

		return null;
	}

	protected boolean appendVersion;
	protected long companyId;
	protected boolean developerModeEnabled;
	protected boolean existing;
	protected long groupId;
	protected String resourcesDir;
	protected ServletContext servletContext;
	protected String servletContextName;
	protected String targetClassName;
	protected long targetClassPK;
	protected String targetValue;
	protected boolean updateModeEnabled;
	protected long userId;
	protected String version;

}