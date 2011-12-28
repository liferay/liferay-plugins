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

package com.liferay.knowledgebase.hook.upgrade;

import com.liferay.knowledgebase.hook.upgrade.v1_2_0.UpgradeKBArticle;
import com.liferay.knowledgebase.hook.upgrade.v1_2_0.UpgradeKBStructure;
import com.liferay.knowledgebase.hook.upgrade.v1_2_0.UpgradeKBTemplate;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Peter Shin
 */
public class UpgradeProcess_1_2_0 extends UpgradeProcess {

	@Override
	public int getThreshold() {
		return 120;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgrade(UpgradeKBArticle.class);
		upgrade(UpgradeKBStructure.class);
		upgrade(UpgradeKBTemplate.class);
	}

}