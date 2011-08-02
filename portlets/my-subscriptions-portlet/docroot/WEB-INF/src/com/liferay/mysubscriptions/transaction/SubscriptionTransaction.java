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
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.Subscription;

import java.util.List;

/**
 * @author Peter Shin
 */
@Transactional(isolation = Isolation.PORTAL)
public interface SubscriptionTransaction {

	public List<Subscription> getSubscriptions(
			long userId, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException;

	public int getSubscriptionsCount(long userId)
		throws SystemException;

}