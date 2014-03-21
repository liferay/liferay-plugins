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

import java.io.IOException;

import javax.portlet.ValidatorException;

/**
 * @author Iván Zaera
 */
public class BaseServiceSettings implements Settings {

	public BaseServiceSettings(Settings settings, FallbackKeys fallbackKeys) {
		FallbackKeySettings fallbackKeySettings = new FallbackKeySettings(
			settings, fallbackKeys);

		typedSettings = new TypedSettings(fallbackKeySettings);
	}

	@Override
	public String getValue(String key, String defaultValue) {
		return typedSettings.getValue(key, defaultValue);
	}

	@Override
	public String[] getValues(String key, String[] defaultValue) {
		return typedSettings.getValues(key, defaultValue);
	}

	@Override
	public Settings setValue(String key, String value) {
		return typedSettings.setValue(key, value);
	}

	@Override
	public Settings setValues(String key, String[] values) {
		return typedSettings.setValues(key, values);
	}

	@Override
	public void store() throws IOException, ValidatorException {
		typedSettings.store();
	}

	protected TypedSettings typedSettings;

}