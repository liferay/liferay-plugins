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

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.settings.Settings;

/**
 * @author Iván Zaera
 */
public class SettingsHelper {

	public void setValues(Settings sourceSettings, Settings targetSettings) {
		for (String name : targetSettings.getSetKeys()) {
			targetSettings.reset(name);
		}

		for (String name : sourceSettings.getSetKeys()) {
			String[] values = sourceSettings.getValues(
				name, StringPool.EMPTY_ARRAY);

			if (values.length == 1) {
				targetSettings.setValue(name, values[0]);
			}
			else {
				targetSettings.setValues(name, values);
			}
		}
	}

}