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

package com.liferay.welcome.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.PortalClassInvoker;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.LayoutSetPrototype;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.service.LayoutSetPrototypeLocalServiceUtil;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Ryan Park
 */
public class ResourcesImporterMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		String servletContextName = message.getString("servletContextName");

		if (!servletContextName.equals("welcome-theme")) {
			return;
		}

		long companyId = message.getLong("companyId");

		Group group = GroupLocalServiceUtil.getGroup(
			companyId, GroupConstants.GUEST);

		if (group.getPublicLayoutsPageCount() != 1) {
			return;
		}

		List<Layout> layouts = LayoutLocalServiceUtil.getLayouts(
			group.getGroupId(), false);

		Layout layout = layouts.get(0);

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		List<String> portletIds = layoutTypePortlet.getPortletIds();

		if (portletIds.size() != 2) {
			return;
		}

		for (String portletId : portletIds) {
			if (!portletId.equals("47") && !portletId.equals("58")) {
				return;
			}
		}

		long layoutSetPrototypeId = message.getLong("layoutSetPrototypeId");

		if (_log.isInfoEnabled()) {
			_log.info(
				"Apply content and pages from layout set prototype " +
					layoutSetPrototypeId + " onto the Guest group");
		}

		LayoutLocalServiceUtil.deleteLayouts(group.getGroupId(), false, null);

		LayoutSetPrototype layoutSetPrototype =
			LayoutSetPrototypeLocalServiceUtil.getLayoutSetPrototype(
				layoutSetPrototypeId);

		LayoutSetLocalServiceUtil.updateLayoutSetPrototypeLinkEnabled(
			group.getGroupId(), false, true, layoutSetPrototype.getUuid());

		PortalClassInvoker.invoke(
			true, _mergeLayoutSetProtypeLayoutsMethodKey, group,
			group.getPublicLayoutSet());

		LayoutLocalServiceUtil.getLayouts(
			group.getGroupId(), false,
			LayoutConstants.DEFAULT_PARENT_LAYOUT_ID);
	}

	private static Log _log = LogFactoryUtil.getLog(
		ResourcesImporterMessageListener.class);

	private static MethodKey _mergeLayoutSetProtypeLayoutsMethodKey =
		new MethodKey(
			"com.liferay.portlet.sites.util.SitesUtil",
			"mergeLayoutSetProtypeLayouts", Group.class, LayoutSet.class);

}