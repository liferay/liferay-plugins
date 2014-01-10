/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.so.hook.upgrade.v3_0_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.LayoutSetPrototype;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
import com.liferay.so.util.LayoutSetPrototypeUtil;
import com.liferay.so.util.SocialOfficeConstants;

/**
 * @author Lin Cui
 */
public class UpgradeExpandoValue extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateExpandoValue();
	}

	protected void updateExpandoValue() throws Exception {
		for (long companyId : PortalUtil.getCompanyIds()) {
			String[] layoutSetPrototypeKeys = new String[] {
				SocialOfficeConstants.LAYOUT_SET_PROTOTYPE_KEY_SITE,
				SocialOfficeConstants.LAYOUT_SET_PROTOTYPE_KEY_USER_PRIVATE};

			for (String layoutSetPrototypeKey : layoutSetPrototypeKeys) {
				LayoutSetPrototype layoutSetPrototype =
					LayoutSetPrototypeUtil.fetchLayoutSetPrototype(
						companyId, layoutSetPrototypeKey);

				if (layoutSetPrototype == null) {
					continue;
				}

				long layoutSetPrototypeId =
					layoutSetPrototype.getLayoutSetPrototypeId();

				Group group = GroupLocalServiceUtil.getLayoutSetPrototypeGroup(
						companyId, layoutSetPrototypeId);

				ExpandoValueLocalServiceUtil.addValue(
					companyId, Group.class.getName(),
					ExpandoTableConstants.DEFAULT_TABLE_NAME,
					"socialOfficeEnabled", group.getGroupId(), true);
			}
		}
	}

}