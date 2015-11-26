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

package com.liferay.weather.portlet;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.weather.model.Weather;
import com.liferay.weather.util.WeatherUtil;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletPreferences;
import javax.portlet.PreferencesValidator;
import javax.portlet.ValidatorException;

/**
 * @author Brian Wing Shun Chan
 */
public class WeatherPreferencesValidator implements PreferencesValidator {

	@Override
	public void validate(PortletPreferences preferences)
		throws ValidatorException {

		List<String> badZips = new ArrayList<>();

		String apiKey = preferences.getValue("apiKey", StringPool.BLANK);

		if (Validator.isNull(apiKey)) {
			return;
		}

		String[] zips = preferences.getValues("zips", new String[0]);

		for (String zip : zips) {
			Weather weather = WeatherUtil.getWeather(zip, apiKey);

			if (weather == null) {
				badZips.add(zip);
			}
		}

		if (badZips.size() > 0) {
			throw new ValidatorException("Failed to retrieve zips", badZips);
		}
	}

}