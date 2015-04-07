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

package com.liferay.tich.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.webcache.WebCacheException;
import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.tich.model.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class EventsWebCacheItem implements WebCacheItem {

	@Override
	public Object convert(String key) throws WebCacheException {
		List<Event> events = new ArrayList<>();

		try {
			String text = HttpUtil.URLtoString(
				"http://www.studylight.org/his/tich");

			int x = text.indexOf("<div class=\"border_bottom_medium\"");
			int y = text.indexOf("<p class=\"small\">", x);

			text = HtmlUtil.stripHtml(text.substring(x, y));

			String[] entries = StringUtil.split(
				text, "\n" + StringPool.DOUBLE_SPACE + "\n\t");

			for (String entry : entries) {
				String[] entryArray = StringUtil.split(entry, "\n\t");

				if (entryArray.length > 1) {
					Event event = new Event(
						GetterUtil.getInteger(entryArray[0]), entryArray[1]);

					events.add(event);
				}
			}
		}
		catch (Exception e) {
			throw new WebCacheException(e);
		}

		return events;
	}

	@Override
	public long getRefreshTime() {
		return _REFRESH_TIME;
	}

	private static final long _REFRESH_TIME = Time.HOUR;

}