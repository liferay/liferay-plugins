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

package com.liferay.twitter.util;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HttpUtil;

/**
 * @author Shinn Lok
 */
public class APITimelineProcessor implements TimelineProcessor {

	public JSONArray getUserTimelineJSONArray(
		String twitterScreenName, long sinceId) {

		try {
			String url = _URL + twitterScreenName + ".json";

			if (sinceId > 0) {
				url += "?since_id=" + sinceId;
			}

			return JSONFactoryUtil.createJSONArray(HttpUtil.URLtoString(url));
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}

			return null;
		}
	}

	private static final String _URL =
		"http://api.twitter.com/1/statuses/user_timeline/";

	private static Log _log = LogFactoryUtil.getLog(APITimelineProcessor.class);

}