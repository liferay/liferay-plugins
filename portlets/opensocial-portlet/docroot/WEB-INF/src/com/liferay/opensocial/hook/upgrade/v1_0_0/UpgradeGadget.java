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

package com.liferay.opensocial.hook.upgrade.v1_0_0;

import com.liferay.opensocial.model.Gadget;
import com.liferay.opensocial.service.GadgetLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.service.ResourceLocalServiceUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Dennis Ju
 */
public class UpgradeGadget extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		for (Gadget gadget : GadgetLocalServiceUtil.getGadgets(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS)) {

			ResourceLocalServiceUtil.addResources(
				gadget.getCompanyId(), 0, 0, Gadget.class.getName(),
				gadget.getGadgetId(), false, false, false);
		}
	}

}