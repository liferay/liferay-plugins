/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.sync.engine;

import com.liferay.sync.engine.upgrade.UpgradeProcessSuite;
import com.liferay.sync.engine.util.PropsKeys;
import com.liferay.sync.engine.util.PropsUtil;

import org.junit.Before;

/**
 * @author Shinn Lok
 */
public abstract class BaseTestCase {

	@Before
	public void setUp() throws Exception {
		PropsUtil.set(PropsKeys.SYNC_DATABASE_NAME, "liferay-sync-test");

		UpgradeProcessSuite upgradeProcessSuite = new UpgradeProcessSuite();

		upgradeProcessSuite.upgrade();
	}

}