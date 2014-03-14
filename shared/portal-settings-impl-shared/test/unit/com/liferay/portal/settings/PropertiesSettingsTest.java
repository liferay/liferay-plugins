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

package com.liferay.portal.settings;

import com.liferay.portal.settings.impl.PropertiesSettings;

import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Iván Zaera
 */
public class PropertiesSettingsTest {

	public PropertiesSettingsTest() {
		_TEST_PROPERTIES.put(_KEY, _VALUE);
		_TEST_PROPERTIES.put(_ARRAY_KEY, _ARRAY_VALUE);
	}

	@Test
	public void testGetValuesWithDefaultValue() {
		String[] defaultValue = {"default0", "default1"};

		Assert.assertArrayEquals(
			defaultValue,
			_propertiesSettings.getValues("missing_key", defaultValue));
	}

	@Test
	public void testGetValuesWithExistingKey() {
		Assert.assertArrayEquals(
			new String[] {"value0", "value1", "value2"},
			_propertiesSettings.getValues(_ARRAY_KEY, null));
	}

	@Test
	public void testGetValuesWithMissingKey() {
		Assert.assertArrayEquals(
			null, _propertiesSettings.getValues("missing_key", null));
	}

	@Test
	public void testGetValueWithDefaultValue() {
		Assert.assertEquals(
			"default", _propertiesSettings.getValue("missing_key", "default"));
	}

	@Test
	public void testGetValueWithExistingKey() {
		Assert.assertEquals(_VALUE, _propertiesSettings.getValue(_KEY, null));
	}

	@Test
	public void testGetValueWithMissingKey() {
		Assert.assertEquals(
			null, _propertiesSettings.getValue("missing_key", null));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testSetValueIsNotSupported() {
		_propertiesSettings.setValue(_KEY, _VALUE);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testSetValuesIsNotSupported() {
		_propertiesSettings.setValues(_KEY, new String[] {_VALUE});
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testStoreIsNotSupported() throws Exception {
		_propertiesSettings.store();
	}

	private static final String _ARRAY_KEY = "multi_key";

	private static final String _ARRAY_VALUE = "value0,value1,value2";

	private static final String _KEY = "key";

	private static final String _VALUE = "value";

	private Properties _TEST_PROPERTIES = new Properties();

	private PropertiesSettings _propertiesSettings = new PropertiesSettings(
		_TEST_PROPERTIES);

}