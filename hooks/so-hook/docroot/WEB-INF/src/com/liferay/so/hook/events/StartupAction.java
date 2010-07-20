/**
 * Copyright (c) 2008-2010 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.so.hook.events;

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portlet.PortletPreferencesThreadLocal;
import com.liferay.so.util.InstanceUtil;

/**
 * @author Brian Wing Shun Chan
 * @author Ryan Park
 */
public class StartupAction extends SimpleAction {

	public void run(String[] ids) throws ActionException {
		try {
			for (String id : ids) {
				doRun(GetterUtil.getLong(id));
			}
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	protected void doRun(long companyId) throws Exception {
		try {
			PortletPreferencesThreadLocal.setStrict(false);

			InstanceUtil.initRuntime(companyId);

			if (isFirstRun(companyId)) {
				return;
			}

			InstanceUtil.initInstance(companyId);
		}
		finally {
			PortletPreferencesThreadLocal.setStrict(true);
		}
	}

	protected boolean isFirstRun(long companyId) throws Exception {
		Group group = GroupLocalServiceUtil.getGroup(
			companyId, GroupConstants.GUEST);

		Layout layout = LayoutLocalServiceUtil.getLayout(
			group.getGroupId(), false, 1);

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		if (!layoutTypePortlet.hasPortletId("47")) {
			return true;
		}

		return false;
	}

}