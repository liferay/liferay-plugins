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

package com.liferay.sync.model;

import com.liferay.portal.configuration.ConfigurationFactoryImpl;
import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.kernel.configuration.ConfigurationFactoryUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.sync.model.impl.SyncDLObjectImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Shinn Lok
 */
public class SyncDLObjectUpdateTest {

	@Before
	public void setUp() throws Exception {
		ConfigurationFactoryUtil.setConfigurationFactory(
			new ConfigurationFactoryImpl());

		JSONFactoryUtil jsonFactoryUtil = new JSONFactoryUtil();

		jsonFactoryUtil.setJSONFactory(new JSONFactoryImpl());

		PortletClassLoaderUtil.setServletContextName("sync-web");
	}

	@Test
	public void testToString() {
		List<SyncDLObject> syncDLObjects = new ArrayList<>(3);

		SyncDLObject syncDLObject = new SyncDLObjectImpl();

		syncDLObject.setDescription(
			"These values should be escaped: \", \\, \\b, \\f, \\n, \\r, \\t");
		syncDLObject.setLockExpirationDate(new Date());

		syncDLObjects.add(syncDLObject);
		syncDLObjects.add(syncDLObject);
		syncDLObjects.add(syncDLObject);

		SyncDLObjectUpdate syncDLObjectUpdate = new SyncDLObjectUpdate(
			syncDLObjects, syncDLObjects.size(), System.currentTimeMillis());

		String expectedJSON = JSONFactoryUtil.looseSerializeDeep(
			syncDLObjectUpdate);

		String actualJSON = syncDLObjectUpdate.toString();

		Assert.assertEquals(
			expectedJSON.replace(StringPool.SPACE, StringPool.BLANK),
			actualJSON.replace(StringPool.SPACE, StringPool.BLANK));
	}

}