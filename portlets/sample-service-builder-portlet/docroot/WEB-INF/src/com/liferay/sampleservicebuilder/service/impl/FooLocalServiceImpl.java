/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.sampleservicebuilder.service.impl;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.User;
import com.liferay.sampleservicebuilder.model.Foo;
import com.liferay.sampleservicebuilder.service.base.FooLocalServiceBaseImpl;
import com.liferay.sampleservicebuilder.service.persistence.FooUtil;

import java.util.Date;
import java.util.List;

/**
 * <a href="FooLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
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

		long fooId = CounterLocalServiceUtil.increment();

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