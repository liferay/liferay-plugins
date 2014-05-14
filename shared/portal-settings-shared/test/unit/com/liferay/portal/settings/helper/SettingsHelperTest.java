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

package com.liferay.portal.settings.helper;

import com.liferay.portal.settings.InMemorySettings;
import com.liferay.portal.settings.Settings;

import org.junit.Assert;
import org.junit.Test;

import org.powermock.api.mockito.PowerMockito;

/**
 * @author Iván Zaera
 */
public class SettingsHelperTest extends PowerMockito {

	@Test
	public void testSetValues() {
		Settings sourceSettings = new InMemorySettings();
		Settings targetSettings = new InMemorySettings();

		sourceSettings.setValue("key0", "value0");
		sourceSettings.setValue("key1", "value1");

		targetSettings.setValue("otherKey", "otherValue");

		_settingsHelper.setValues(sourceSettings, targetSettings);

		Assert.assertEquals(2, targetSettings.getSetKeys().size());
		Assert.assertEquals("value0", targetSettings.getValue("key0", null));
		Assert.assertEquals("value1", targetSettings.getValue("key1", null));
	}

	private SettingsHelper _settingsHelper = new SettingsHelper();

}