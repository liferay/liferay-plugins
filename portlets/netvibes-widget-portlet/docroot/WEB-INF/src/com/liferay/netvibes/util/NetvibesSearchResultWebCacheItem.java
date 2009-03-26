/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

package com.liferay.netvibes.util;

import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.webcache.WebCacheException;
import com.liferay.portal.kernel.webcache.WebCacheItem;

/**
 * <a href="NetvibesSearchResultWebCacheItem.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Julio Camarero
 *
 */
public class NetvibesSearchResultWebCacheItem implements WebCacheItem {

	public NetvibesSearchResultWebCacheItem(
		String query, String sort, int category, String region, int page) {

		_query = query;
		_sort = sort;
		_category = category;
		_type = "uwa";
		_region = region;
		_page = page;
	}

	public Object convert(String key) throws WebCacheException {
		String searchResult = null;

		try {
			searchResult = HtmlUtil.stripComments(HttpUtil.URLtoString(
				"http://api.eco.netvibes.com/search/?" +
					"&format=json" +
					"&thumbwidth=160&thumbheight=120" +
					"&limit=" + NetvibesUtil.NUMBER_OF_WIDGETS_PER_PAGE +
					"&query=" + HttpUtil.encodeURL(_query) +
					"&sort=" + HttpUtil.encodeURL(_sort) +
					"&category=" + (_category) +
					"&type=" + HttpUtil.encodeURL(_type) +
					"&region=" + HttpUtil.encodeURL(_region) +
					"&page=" + (_page)));

		}
		catch (Exception e) {
			throw new WebCacheException(
					"&limit="+ NetvibesUtil.NUMBER_OF_WIDGETS_PER_PAGE +
					"&query=" + HttpUtil.encodeURL(_query) +
					"&sort=" + HttpUtil.encodeURL(_sort) +
					"&category=" + (_category) +
					"&type=" + HttpUtil.encodeURL(_type) +
					"&region=" + HttpUtil.encodeURL(_region) +
					"&page=" + (_page) );
		}

		return searchResult;
	}

	public long getRefreshTime() {
		return _REFRESH_TIME;
	}

	private static final long _REFRESH_TIME = Time.MINUTE * 20;

	private String _query;
	private String _sort;
	private int _category;
	private String _region;
	private String _type;
	private int _page;

}