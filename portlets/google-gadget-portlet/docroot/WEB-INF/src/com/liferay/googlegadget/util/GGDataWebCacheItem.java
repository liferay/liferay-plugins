/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.googlegadget.util;

import com.liferay.googlegadget.model.GGCategory;
import com.liferay.googlegadget.model.GGData;
import com.liferay.googlegadget.model.GGEntry;
import com.liferay.googlegadget.model.GGPagination;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.webcache.WebCacheException;
import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.util.portlet.PortletProps;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Brian Wing Shun Chan
 * @author Alberto Montero
 *
 */
public class GGDataWebCacheItem implements WebCacheItem {

	public GGDataWebCacheItem(String url) {
		_url = url;
	}

	public Object convert(String key) throws WebCacheException {
		try {
			String html = HttpUtil.URLtoString(_url);

			List<GGCategory> categories = new ArrayList<GGCategory>();

			Matcher matcher = _categoriesPattern.matcher(html);

			while (matcher.find()) {
				String categoryId = matcher.group(1);
				String name = matcher.group(2);

				GGCategory category = new GGCategory(categoryId, name);

				categories.add(category);
			}

			List<GGEntry> entries = new ArrayList<GGEntry>();

			int prevStart = -1;
			int moreStart = -1;

			matcher = _queryPattern.matcher(_url);

			if (matcher.find()) {
				matcher = _queryEntriesPattern.matcher(html);

				while (matcher.find()) {
					String gadgetId = matcher.group(1);
					String name = matcher.group(3);
					String image = HttpUtil.decodeURL(matcher.group(2));

					GGEntry entry = new GGEntry(gadgetId, name, image);

					entries.add(entry);
				}

				matcher = _queryPaginationPattern.matcher(html);

				if (matcher.find()) {
					int start = GetterUtil.getInteger(matcher.group(1));
					int end = GetterUtil.getInteger(matcher.group(2));

					prevStart = 2 * start - end - 2;
					moreStart = end;
				}
			}
			else {
				matcher = _categoriesEntriesPattern.matcher(html);

				while (matcher.find()) {
					String gadgetId = matcher.group(1);
					String name = matcher.group(2);
					String image = HttpUtil.decodeURL(matcher.group(3));

					GGEntry entry = new GGEntry(gadgetId, name, image);

					entries.add(entry);
				}

				matcher = _paginationPattern.matcher(html);

				if (matcher.find()) {
					String paginationHtml = matcher.group(1);

					matcher = _paginationMorePattern.matcher(paginationHtml);

					if (matcher.find()) {
						moreStart = GetterUtil.getInteger(matcher.group(1));
					}

					matcher = _paginationPrevPattern.matcher(paginationHtml);

					if (matcher.find()) {
						prevStart = GetterUtil.getInteger(matcher.group(1));

						if (prevStart == moreStart) {
							prevStart = 0;
						}
					}
				}
			}

			GGPagination pagination = new GGPagination(prevStart, moreStart);

			return new GGData(categories, entries, pagination);
		}
		catch (Exception e) {
			throw new WebCacheException(_url + " " + e.toString());
		}
	}

	public long getRefreshTime() {
		return _REFRESH_TIME;
	}

	private static final long _REFRESH_TIME = Time.MINUTE * 5;

	private static final Pattern _categoriesEntriesPattern = Pattern.compile(
		PortletProps.get("regex.categories.entries"), Pattern.DOTALL);
	private static final Pattern _categoriesPattern = Pattern.compile(
		PortletProps.get("regex.categories"), Pattern.DOTALL);
	private static final Pattern _queryEntriesPattern = Pattern.compile(
		PortletProps.get("regex.query.entries"), Pattern.DOTALL);
	private static final Pattern _queryPaginationPattern = Pattern.compile(
		PortletProps.get("regex.query.pagination"), Pattern.DOTALL);
	private static final Pattern _queryPattern = Pattern.compile(
		PortletProps.get("regex.query"), Pattern.DOTALL);
	private static final Pattern _paginationMorePattern = Pattern.compile(
		PortletProps.get("regex.categories.pagination.more"), Pattern.DOTALL);
	private static final Pattern _paginationPattern = Pattern.compile(
		PortletProps.get("regex.pagination"), Pattern.DOTALL);
	private static final Pattern _paginationPrevPattern = Pattern.compile(
		PortletProps.get("regex.categories.pagination.prev"), Pattern.DOTALL);

	private String _url;

}