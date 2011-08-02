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

package com.liferay.mysubscriptions.transaction;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.Subscription;
import com.liferay.portal.service.persistence.SubscriptionUtil;

import java.util.List;

/**
 * @author Peter Shin
 */
public class SubscriptionTransactionImpl implements SubscriptionTransaction {

	public List<Subscription> getSubscriptions(
			long userId, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return SubscriptionUtil.findByUserId(
			userId, start, end, orderByComparator);
	}

	public int getSubscriptionsCount(long userId) throws SystemException {
		return SubscriptionUtil.countByUserId(userId);
	}

}