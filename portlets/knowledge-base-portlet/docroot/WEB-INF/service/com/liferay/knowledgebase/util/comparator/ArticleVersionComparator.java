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

package com.liferay.knowledgebase.util.comparator;

import com.liferay.knowledgebase.model.Article;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Brian Wing Shun Chan
 */
public class ArticleVersionComparator extends OrderByComparator {

	public static String ORDER_BY_ASC = "version ASC, createDate DESC";

	public static String ORDER_BY_DESC = "version DESC, createDate ASC";

	public static String[] ORDER_BY_FIELDS = {"version"};

	public ArticleVersionComparator() {
		this(false);
	}

	public ArticleVersionComparator(boolean ascending) {
		_ascending = ascending;
	}

	public int compare(Object obj1, Object obj2) {
		Article article1 = (Article)obj1;
		Article article2 = (Article)obj2;

		int value = 0;

		if (article1.getVersion() < article2.getVersion()) {
			value = -1;
		}
		else if (article1.getVersion() > article2.getVersion()) {
			value = 1;
		}

		if (_ascending) {
			return value;
		}
		else {
			return -value;
		}
	}

	public String getOrderBy() {
		if (_ascending) {
			return ORDER_BY_ASC;
		}
		else {
			return ORDER_BY_DESC;
		}
	}

	public String[] getOrderByFields() {
		return ORDER_BY_FIELDS;
	}

	public boolean isAscending() {
		return _ascending;
	}

	private boolean _ascending;

}