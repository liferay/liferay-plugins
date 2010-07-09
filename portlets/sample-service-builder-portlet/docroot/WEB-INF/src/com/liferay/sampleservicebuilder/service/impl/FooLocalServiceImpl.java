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

package com.liferay.sampleservicebuilder.service.impl;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.User;
import com.liferay.sampleservicebuilder.model.Foo;
import com.liferay.sampleservicebuilder.service.base.FooLocalServiceBaseImpl;
import com.liferay.sampleservicebuilder.service.persistence.FooUtil;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 *
 */
public class FooLocalServiceImpl extends FooLocalServiceBaseImpl {

	public void addFoo(
			long userId, String field1, boolean field2, int field3, Date field4,
			String field5)
		throws PortalException, SystemException {

		User user = userLocalService.getUserById(userId);
		Date now = new Date();

		long fooId = counterLocalService.increment();

		Foo foo = FooUtil.create(fooId);

		foo.setCompanyId(user.getCompanyId());
		foo.setUserId(user.getUserId());
		foo.setUserName(user.getFullName());
		foo.setCreateDate(now);
		foo.setModifiedDate(now);
		foo.setField1(field1);
		foo.setField2(field2);
		foo.setField3(field3);
		foo.setField4(field4);
		foo.setField5(field5);

		FooUtil.update(foo, false);
	}

	public List<Foo> getFoos(OrderByComparator obc) throws SystemException {
		return getFoos(QueryUtil.ALL_POS, QueryUtil.ALL_POS, obc);
	}

	public List<Foo> getFoos(int start, int end, OrderByComparator obc)
		throws SystemException {

		return FooUtil.findAll(start, end, obc);
	}

	public void updateFoo(
			long fooId, String field1, boolean field2, int field3, Date field4,
			String field5)
		throws PortalException, SystemException {

		Foo foo = FooUtil.findByPrimaryKey(fooId);

		foo.setModifiedDate(new Date());
		foo.setField1(field1);
		foo.setField2(field2);
		foo.setField3(field3);
		foo.setField4(field4);
		foo.setField5(field5);

		FooUtil.update(foo, false);
	}

}