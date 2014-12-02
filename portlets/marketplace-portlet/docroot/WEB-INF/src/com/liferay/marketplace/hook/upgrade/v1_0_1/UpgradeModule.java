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

package com.liferay.marketplace.hook.upgrade.v1_0_1;

import com.liferay.marketplace.model.Module;
import com.liferay.marketplace.service.ModuleLocalServiceUtil;
import com.liferay.marketplace.util.ContextUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

/**
 * @author Joan Kim
 */
public class UpgradeModule extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateModule();
	}

	protected void updateModule() throws Exception {
		List<Module> modules = ModuleLocalServiceUtil.getModules(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (Module module : modules) {
			if (Validator.isNull(module.getContextName())) {
				continue;
			}

			runSQL(
				"update Marketplace_Module set contextName = '" +
					ContextUtil.getContextName(module.getContextName()) +
						"' where moduleId = " + module.getModuleId());
		}
	}

}