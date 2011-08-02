/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.mysubscriptions.util.comparator;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.Subscription;

/**
 * @author Peter Shin
 */
public class SubscriptionClassPKComparator extends OrderByComparator {

	public static String ORDER_BY_ASC = "classPK ASC";

	public static String ORDER_BY_DESC = "classPK DESC";

	public static String[] ORDER_BY_FIELDS = {"classPK"};

	public SubscriptionClassPKComparator() {
		this(false);
	}

	public SubscriptionClassPKComparator(boolean ascending) {
		_ascending = ascending;
	}

	public int compare(Object obj1, Object obj2) {
		Subscription subscription1 = (Subscription)obj1;
		Subscription subscription2 = (Subscription)obj2;

		int value = 0;

		if (subscription1.getClassPK() < subscription2.getClassPK()) {
			value = -1;
		}
		else if (subscription1.getClassPK() > subscription2.getClassPK()) {
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