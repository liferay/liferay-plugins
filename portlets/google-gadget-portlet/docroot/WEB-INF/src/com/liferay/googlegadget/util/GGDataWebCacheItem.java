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

package com.liferay.googlegadget.util;

import com.liferay.googlegadget.model.GGCategory;
import com.liferay.googlegadget.model.GGData;
import com.liferay.googlegadget.model.GGEntry;
import com.liferay.googlegadget.model.GGPagination;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.webcache.WebCacheException;
import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.util.Time;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <a href="GGDataWebCacheItem.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class GGDataWebCacheItem implements WebCacheItem {

	public GGDataWebCacheItem(String url) {
		_url = url;
	}

	public Object convert(String key) throws WebCacheException {
		try {
			String html = HttpUtil.URLtoString(_url);

			List categories = new ArrayList();

			Matcher matcher = _categoriesPattern.matcher(html);

			while (matcher.find()) {
				String categoryId = matcher.group(1);
				String name = matcher.group(2);

				GGCategory category = new GGCategory(categoryId, name);

				categories.add(category);
			}

			List entries = new ArrayList();

			matcher = _queryPattern.matcher(_url);

			if (matcher.find()) {
				matcher = _queryEntriesPattern.matcher(html);

				while (matcher.find()) {
					String gadgetId = matcher.group(1);
					String name = matcher.group(3);
					String image = matcher.group(2);

					GGEntry entry = new GGEntry(gadgetId, name, image);

					entries.add(entry);
				}

				matcher = _queryPaginationPattern.matcher(html);
			}
			else {
				matcher = _categoriesEntriesPattern.matcher(html);

				while (matcher.find()) {
					String gadgetId = matcher.group(2);
					String name = matcher.group(1);
					String image = matcher.group(3);

					GGEntry entry = new GGEntry(gadgetId, name, image);

					entries.add(entry);
				}

				matcher = _categoriesPaginationPattern.matcher(html);
			}

			int start = 0;
			int end = 0;
			int total = 0;

			if (matcher.find()) {
				start = GetterUtil.getInteger(matcher.group(1));
				end = GetterUtil.getInteger(matcher.group(2));
				total = GetterUtil.getInteger(matcher.group(3));
			}

			GGPagination pagination = new GGPagination(
				GGPagination.DEFAULT_DELTA, start, end, total);

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

	private static final Pattern _categoriesPattern = Pattern.compile(
		PortletProps.get("regex.categories"), Pattern.DOTALL);

	private static final Pattern _categoriesEntriesPattern = Pattern.compile(
		PortletProps.get("regex.categories.entries"), Pattern.DOTALL);

	private static final Pattern _categoriesPaginationPattern = Pattern.compile(
		PortletProps.get("regex.categories.pagination"), Pattern.DOTALL);

	private static final Pattern _queryPattern = Pattern.compile(
		PortletProps.get("regex.query"), Pattern.DOTALL);

	private static final Pattern _queryEntriesPattern = Pattern.compile(
		PortletProps.get("regex.query.entries"), Pattern.DOTALL);

	private static final Pattern _queryPaginationPattern = Pattern.compile(
		PortletProps.get("regex.query.pagination"), Pattern.DOTALL);

	private String _url;

}