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

package com.liferay.netvibes.model;

import java.io.Serializable;

/**
 * <a href="NetvibesSearchResult.java.html"><b><i>View Source</i></b></a>
 *
 * @author Julio Camarero
 */
public class NetvibesSearchResult implements Serializable {

	public NetvibesSearchResult() {
	}

	public NetvibesSearchResult(
		String query, String sort, int category,String type, String region,
		int page) {

		_query = query;
		_sort = sort;
		_category = category;
		_type = type;
		_region = region;
		_page = page;
	}

	public String getQuery() {
		return _query;
	}

	public void setQuery(String query) {
		_query = query;
	}

	public String getSort() {
		return _sort;
	}

	public void setSort(String sort) {
		_sort = sort;
	}

	public String getRegion() {
		return _region;
	}

	public void setRegion(String region) {
		_region = region;
	}

	public int getCategory() {
		return _category;
	}

	public void setCategory(int category) {
		_category = category;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	public int getPage() {
		return _page;
	}

	public void setPage(int page) {
		_page = page;
	}

	private String _query;
	private String _sort;
	private int _category;
	private String _region;
	private String _type;
	private int _page;

}