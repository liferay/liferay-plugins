/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

import com.liferay.knowledgebase.model.KBFolder;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Adolfo Pérez
 */
public class KBFolderNameComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC = "KBFolder.name ASC";

	public static final String ORDER_BY_DESC = "KBFolder.name DESC";

	public static final String[] ORDER_BY_FIELDS = {"name"};

	public KBFolderNameComparator() {
		this(false);
	}

	public KBFolderNameComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		KBFolder kbFolder1 = (KBFolder)obj1;
		KBFolder kbFolder2 = (KBFolder)obj2;

		String name1 = StringUtil.toLowerCase(
			GetterUtil.getString(kbFolder1.getName()));
		String name2 = StringUtil.toLowerCase(
			GetterUtil.getString(kbFolder2.getName()));

		int value = name1.compareTo(name2);

		if (_ascending) {
			return value;
		}
		else {
			return -value;
		}
	}

	@Override
	public String getOrderBy() {
		if (_ascending) {
			return ORDER_BY_ASC;
		}
		else {
			return ORDER_BY_DESC;
		}
	}

	@Override
	public String[] getOrderByFields() {
		return ORDER_BY_FIELDS;
	}

	@Override
	public boolean isAscending() {
		return _ascending;
	}

	private final boolean _ascending;

}