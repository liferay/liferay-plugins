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

package com.liferay.googlegadget.model;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 *
 */
public class GGData {

	public GGData(List categories, List entries, GGPagination pagination) {
		_categories = categories;
		_entries = entries;
		_pagination = pagination;
	}

	public List getCategories() {
		return _categories;
	}

	public void setCategories(List categories) {
		_categories = categories;
	}

	public List getEntries() {
		return _entries;
	}

	public void setEntries(List entries) {
		_entries = entries;
	}

	public GGPagination getPagination() {
		return _pagination;
	}

	public void setPagination(GGPagination pagination) {
		_pagination = pagination;
	}

	private List _categories;
	private List _entries;
	private GGPagination _pagination;

}