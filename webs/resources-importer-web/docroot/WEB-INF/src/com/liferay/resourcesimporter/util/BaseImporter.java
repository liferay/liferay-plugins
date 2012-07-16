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

package com.liferay.resourcesimporter.util;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.LayoutSetPrototype;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutSetPrototypeLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;

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

	public void afterPropertiesSet() throws Exception {
		User user = UserLocalServiceUtil.getDefaultUser(companyId);

		userId = user.getUserId();

		Group group = null;

		if (destinationClassName.equals(LayoutSetPrototype.class.getName())) {
			if (!hasLayoutSetPrototype(companyId, destinationName)) {
				LayoutSetPrototype layoutSetPrototype =
					LayoutSetPrototypeLocalServiceUtil.addLayoutSetPrototype(
						userId, companyId, getDestinationNameMap(),
						StringPool.BLANK, true, true, new ServiceContext());

				group = layoutSetPrototype.getGroup();

				destinationClassPK =
					layoutSetPrototype.getLayoutSetPrototypeId();
				privateLayout = true;
			}
		}
		else if (destinationClassName.equals(Group.class.getName())) {
			if (destinationName.equals(GroupConstants.GUEST)) {
				Group guestGroup = GroupLocalServiceUtil.getGroup(
					companyId, GroupConstants.GUEST);

				List<Layout> layouts = LayoutLocalServiceUtil.getLayouts(
					guestGroup.getGroupId(), false,
					LayoutConstants.DEFAULT_PARENT_LAYOUT_ID, false, 0, 1);

				if (!layouts.isEmpty()) {
					Layout layout = layouts.get(0);

					LayoutTypePortlet layoutTypePortlet =
						(LayoutTypePortlet)layout.getLayoutType();

					List<String> portletIds = layoutTypePortlet.getPortletIds();

					if (portletIds.size() != 2) {
						return;
					}

					for (String portletId : portletIds) {
						if (!portletId.equals("47") &&
							!portletId.equals("58")) {

							return;
						}
					}
				}

				group = guestGroup;
			}
			else if (!hasGroup(companyId, destinationName)) {
				group = GroupLocalServiceUtil.addGroup(
					userId, 0, StringPool.BLANK, 0, destinationName,
					StringPool.BLANK, GroupConstants.TYPE_SITE_OPEN, null, true,
					true, new ServiceContext());
			}

			if (group != null) {
				destinationClassPK = group.getGroupId();
				privateLayout = false;
			}
		}

		if (group == null) {
			return;
		}

		groupId = group.getGroupId();
	}

	public long getDestinationClassPK() {
		return destinationClassPK;
	}

	public long getGroupId() {
		return groupId;
	}

	public Map<Locale, String> getDestinationNameMap() {
		Map<Locale, String> destinationNameMap = new HashMap<Locale, String>();

		Locale locale = LocaleUtil.getDefault();

		destinationNameMap.put(locale, destinationName);

		return destinationNameMap;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public void setDestinationClassName(String destinationClassName) {

		this.destinationClassName = destinationClassName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public void setResourcesDir(String resourcesDir) {
		this.resourcesDir = resourcesDir;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public void setServletContextName(String servletContextName) {
		this.servletContextName = servletContextName;
	}

	protected boolean hasGroup(long companyId, String name) throws Exception {
		Group group = GroupLocalServiceUtil.fetchGroup(companyId, name);

		if (group != null) {
			return true;
		}

		return false;
	}

	protected boolean hasLayoutSetPrototype(long companyId, String name)
		throws Exception {

		Locale locale = LocaleUtil.getDefault();

		List<LayoutSetPrototype> layoutSetPrototypes =
			LayoutSetPrototypeLocalServiceUtil.search(
				companyId, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		for (LayoutSetPrototype layoutSetPrototype : layoutSetPrototypes) {
			if (name.equals(layoutSetPrototype.getName(locale))) {
				return true;
			}
		}

		return false;
	}

	protected long companyId;
	protected long groupId;
	protected String destinationClassName;
	protected long destinationClassPK;
	protected String destinationName;
	protected boolean privateLayout;
	protected String resourcesDir;
	protected ServletContext servletContext;
	protected String servletContextName;
	protected long userId;

}