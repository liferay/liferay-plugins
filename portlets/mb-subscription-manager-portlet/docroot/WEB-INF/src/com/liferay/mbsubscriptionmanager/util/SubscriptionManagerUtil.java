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

package com.liferay.mbsubscriptionmanager.util;

import com.liferay.message.boards.kernel.model.MBCategory;
import com.liferay.message.boards.kernel.service.MBCategoryLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

/**
 * @author Shinn Lok
 */
public class SubscriptionManagerUtil {

	public static List<MBCategory> getMBCategories(
		long groupId, String name, int start, int end) {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			MBCategory.class, PortalClassLoaderUtil.getClassLoader());

		Property property = PropertyFactoryUtil.forName("groupId");

		dynamicQuery.add(property.eq(groupId));

		if (Validator.isNotNull(name)) {
			name = StringPool.PERCENT + name + StringPool.PERCENT;

			property = PropertyFactoryUtil.forName("name");

			dynamicQuery.add(property.like(name));
		}

		dynamicQuery.addOrder(OrderFactoryUtil.asc("name"));

		return MBCategoryLocalServiceUtil.dynamicQuery(
			dynamicQuery, start, end);
	}

	public static int getMBCategoriesCount(long groupId, String name) {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			MBCategory.class, PortalClassLoaderUtil.getClassLoader());

		Property property = PropertyFactoryUtil.forName("groupId");

		dynamicQuery.add(property.eq(groupId));

		if (Validator.isNotNull(name)) {
			name = StringPool.PERCENT + name + StringPool.PERCENT;

			property = PropertyFactoryUtil.forName("name");

			dynamicQuery.add(property.like(name));
		}

		return (int)MBCategoryLocalServiceUtil.dynamicQueryCount(dynamicQuery);
	}

}