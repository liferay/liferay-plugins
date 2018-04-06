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

package com.liferay.meeting.webex.util;

import com.liferay.portal.kernel.util.StringPool;

import com.webex.schemas.x2002.x06.common.TimeZoneType;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author Anant Singh
 */
public class TimeZoneUtil {

	public static TimeZoneType.Enum convert(String timeZoneString) {
		int x = timeZoneString.indexOf(StringPool.OPEN_PARENTHESIS);
		int y = timeZoneString.indexOf(StringPool.CLOSE_PARENTHESIS);

		if ((x == -1) || (y == -1)) {
			return null;
		}

		return _timeZoneTypeEnumsByShortName.get(
			timeZoneString.substring(x + 1, y));
	}

	public static TimeZoneType.Enum convert(TimeZone timeZone) {
		return _timeZoneTypeEnumsByTimeZone.get(timeZone);
	}

	public static TimeZone convert(TimeZoneType.Enum timeZoneTypeEnum) {
		return _timeZones.get(timeZoneTypeEnum);
	}

	public void setTimeZones(
		Map<TimeZone, TimeZoneType.Enum> timeZoneTypeEnums) {

		for (Map.Entry<TimeZone, TimeZoneType.Enum> entry :
				timeZoneTypeEnums.entrySet()) {

			TimeZone timeZone = entry.getKey();
			TimeZoneType.Enum timeZoneTypeEnum = entry.getValue();

			_timeZones.put(timeZoneTypeEnum, timeZone);
			_timeZoneTypeEnumsByTimeZone.put(timeZone, timeZoneTypeEnum);

			String timeZoneTypeEnumString = timeZoneTypeEnum.toString();

			int x = timeZoneTypeEnumString.indexOf(StringPool.OPEN_PARENTHESIS);
			int y = timeZoneTypeEnumString.indexOf(
				StringPool.CLOSE_PARENTHESIS);

			if ((x != -1) && (y != -1)) {
				String shortName = timeZoneTypeEnumString.substring(x + 1, y);

				_timeZoneTypeEnumsByShortName.put(shortName, timeZoneTypeEnum);
			}
		}
	}

	private static Map<TimeZoneType.Enum, TimeZone> _timeZones =
		new HashMap<>();
	private static Map<String, TimeZoneType.Enum>
		_timeZoneTypeEnumsByShortName = new HashMap<>();
	private static Map<TimeZone, TimeZoneType.Enum>
		_timeZoneTypeEnumsByTimeZone = new HashMap<>();

}