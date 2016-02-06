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

package com.liferay.so.hook.upgrade.v3_0_0;

import com.liferay.portal.kernel.model.LayoutSetPrototype;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.so.service.SocialOfficeServiceUtil;
import com.liferay.so.util.LayoutSetPrototypeUtil;
import com.liferay.so.util.SocialOfficeConstants;
import com.liferay.so.util.SocialOfficeUtil;

/**
 * @author Lin Cui
 */
public class UpgradeLayoutSetPrototype extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		for (long companyId : PortalUtil.getCompanyIds()) {
			updateLayoutSetPrototype(companyId);
		}
	}

	protected void updateLayoutSetPrototype(long companyId) throws Exception {
		for (String layoutSetPrototypeKey : _LAYOUT_SET_PROTOTYPE_KEYS) {
			LayoutSetPrototype layoutSetPrototype =
				LayoutSetPrototypeUtil.fetchLayoutSetPrototype(
					companyId, layoutSetPrototypeKey);

			if ((layoutSetPrototype != null) &&
				!SocialOfficeServiceUtil.isSocialOfficeGroup(
					layoutSetPrototype.getGroupId())) {

				SocialOfficeUtil.enableSocialOffice(
					layoutSetPrototype.getGroup());
			}
		}
	}

	private static final String[] _LAYOUT_SET_PROTOTYPE_KEYS = new String[] {
		SocialOfficeConstants.LAYOUT_SET_PROTOTYPE_KEY_SITE,
		SocialOfficeConstants.LAYOUT_SET_PROTOTYPE_KEY_USER_PRIVATE,
		SocialOfficeConstants.LAYOUT_SET_PROTOTYPE_KEY_USER_PUBLIC
	};

}