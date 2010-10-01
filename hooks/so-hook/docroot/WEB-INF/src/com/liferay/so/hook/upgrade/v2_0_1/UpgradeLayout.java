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

package com.liferay.so.hook.upgrade.v2_0_1;

import com.liferay.portal.NoSuchLayoutException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import java.util.List;

/**
 * @author Ryan Park
 */
public class UpgradeLayout extends UpgradeProcess {

	protected void doUpgrade() throws Exception {
		List<Group> groups = GroupLocalServiceUtil.getGroups(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (Group group : groups) {
			String name = group.getName();

			if (!group.isCommunity() ||
				name.equals(GroupConstants.CONTROL_PANEL) ||
				name.equals(GroupConstants.GUEST)) {

				continue;
			}

			long userId = PortalUtil.getValidUserId(
				group.getCompanyId(), group.getCreatorUserId());

			Layout layout = null;

			try {
				layout = LayoutLocalServiceUtil.getFriendlyURLLayout(
					group.getGroupId(), group.hasPrivateLayouts(),
					"/documents");
			}
			catch (NoSuchLayoutException nsle) {
				continue;
			}

			LayoutLocalServiceUtil.updateFriendlyURL(
				layout.getPlid(), "/document");
		}
	}

}