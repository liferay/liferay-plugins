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

package com.liferay.knowledgebase.hook.upgrade;

import com.liferay.knowledgebase.hook.upgrade.v1_1_0.UpgradeClassName;
import com.liferay.knowledgebase.hook.upgrade.v1_1_0.UpgradeExpandoTable;
import com.liferay.knowledgebase.hook.upgrade.v1_1_0.UpgradeKBArticle;
import com.liferay.knowledgebase.hook.upgrade.v1_1_0.UpgradeKBComment;
import com.liferay.knowledgebase.hook.upgrade.v1_1_0.UpgradeKBTemplate;
import com.liferay.knowledgebase.hook.upgrade.v1_1_0.UpgradePortletPreferences;
import com.liferay.knowledgebase.hook.upgrade.v1_1_0.UpgradeResourceAction;
import com.liferay.knowledgebase.hook.upgrade.v1_1_0.UpgradeResourcePermission;
import com.liferay.portal.kernel.cache.MultiVMPoolUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.ResourceActionLocalServiceUtil;

/**
 * @author Peter Shin
 */
public class UpgradeProcess_1_1_0 extends UpgradeProcess {

	@Override
	public int getThreshold() {
		return 110;
	}

	protected void clearCache() throws Exception {
		ClassNameLocalServiceUtil.checkClassNames();
		ResourceActionLocalServiceUtil.checkResourceActions();

		MultiVMPoolUtil.clear();
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgrade(UpgradeClassName.class);
		upgrade(UpgradeExpandoTable.class);
		upgrade(UpgradeKBArticle.class);
		upgrade(UpgradeKBComment.class);
		upgrade(UpgradeKBTemplate.class);
		upgrade(UpgradePortletPreferences.class);
		upgrade(UpgradeResourceAction.class);
		upgrade(UpgradeResourcePermission.class);

		clearCache();
	}

}