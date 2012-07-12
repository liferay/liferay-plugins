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
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.LayoutSetPrototype;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.service.LayoutSetPrototypeLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 * @author Ryan Park
 */
public class ResourcesImporterMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		String servletContextName = message.getString("servletContextName");

		if (!servletContextName.equals("so-welcome-theme")) {
			return;
		}

		long companyId = message.getLong("companyId");

		Group group = GroupLocalServiceUtil.getGroup(
			companyId, GroupConstants.GUEST);

		long layoutSetPrototypeId = message.getLong("layoutSetPrototypeId");

		if (_log.isInfoEnabled()) {
			_log.info(
				"Apply content and pages from layout set prototype " +
					layoutSetPrototypeId + " onto the Guest group");
		}

		LayoutSetPrototype layoutSetPrototype =
			LayoutSetPrototypeLocalServiceUtil.getLayoutSetPrototype(
				layoutSetPrototypeId);

		LayoutSetLocalServiceUtil.updateLayoutSetPrototypeLinkEnabled(
			group.getGroupId(), false, true, layoutSetPrototype.getUuid());

		LayoutLocalServiceUtil.updatePriorities(group.getGroupId(), false);

		PortalClassInvoker.invoke(
			true, _mergeLayoutSetProtypeLayoutsMethodKey, group,
			group.getPublicLayoutSet());
	}

	private static Log _log = LogFactoryUtil.getLog(
		ResourcesImporterMessageListener.class);

	private static MethodKey _mergeLayoutSetProtypeLayoutsMethodKey =
		new MethodKey(
			"com.liferay.portlet.sites.util.SitesUtil",
			"mergeLayoutSetProtypeLayouts", Group.class, LayoutSet.class);

}