/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

	public Object convert(String key) throws WebCacheException {
		List<Event> events = new ArrayList<Event>();

		try {
			String text = HttpUtil.URLtoString(
				"http://www.studylight.org/his/tich");

			int x = text.indexOf("<table cellpadding=3 cellspacing=3>");
			x = text.indexOf("<tr>", x);

			int y = text.indexOf("<tr><td align=center", x);

			text = HtmlUtil.stripComments(text.substring(x, y)).trim();

			String[] array = StringUtil.split(text, "<tr>");

			for (int i = 0; i < array.length; i++) {
				x = array[i].indexOf("<b>");
				y = array[i].indexOf("</b>");

				if ((x != -1) && (y != -1)) {
					int year = GetterUtil.getInteger(StringUtil.extractDigits(
						array[i].substring(x + 3, y)));

					String description = HtmlUtil.extractText(
						array[i].substring(y, array[i].length())).trim();

					if (description.startsWith("- ")) {
						description = description.substring(
							2, description.length());
					}

					Event event = new Event(year, description);

					events.add(event);
				}
			}
		}
		catch (Exception e) {
			throw new WebCacheException(e);
		}

		return events;
	}

	public long getRefreshTime() {
		return _REFRESH_TIME;
	}

	private static final long _REFRESH_TIME = Time.HOUR;

}