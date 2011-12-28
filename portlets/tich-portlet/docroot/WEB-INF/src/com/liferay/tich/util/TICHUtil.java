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

import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.portal.kernel.webcache.WebCachePoolUtil;
import com.liferay.tich.model.Event;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class TICHUtil {

	public static List<Event> getEvents() {
		WebCacheItem wci = new EventsWebCacheItem();

		String key = TICHUtil.class.getName();

		List<Event> events = (List<Event>)WebCachePoolUtil.get(key, wci);

		try {
			if (events.size() > 0) {
				Event event = events.get(0);
			}
		}
		catch (ClassCastException cce) {
			WebCachePoolUtil.remove(key);

			events = (List<Event>)WebCachePoolUtil.get(key, wci);
		}

		return events;
	}

}