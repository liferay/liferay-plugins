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

package com.liferay.portal.settings;

import org.junit.Assert;
import org.junit.Test;

import org.powermock.api.mockito.PowerMockito;

/**
 * @author Iván Zaera
 */
public class InMemorySettingsTest extends PowerMockito {

	@Test
	public void testSetAndGetValue() {
		_inMemorySettings.setValue("key", "value");

		Assert.assertEquals(1, _inMemorySettings.getSetKeys().size());
		Assert.assertEquals("value", _inMemorySettings.getValue("key", null));
	}

	@Test
	public void testSetAndGetValues() {
		_inMemorySettings.setValues("key", new String[] {"value0", "value1"});

		Assert.assertEquals(1, _inMemorySettings.getSetKeys().size());

		String[] values = _inMemorySettings.getValues("key", null);

		Assert.assertEquals(2, values.length);
		Assert.assertEquals("value0", values[0]);
		Assert.assertEquals("value1", values[1]);

		Assert.assertEquals("value0", _inMemorySettings.getValue("key", null));
	}

	private InMemorySettings _inMemorySettings = new InMemorySettings();

}