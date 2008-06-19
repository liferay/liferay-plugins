/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
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
 * <a href="EventsWebCacheItem.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
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

				if (x != -1 && y != -1) {
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