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

package com.liferay.sampleservicebuilder.service.impl;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.sampleservicebuilder.model.Foo;
import com.liferay.sampleservicebuilder.service.base.FooLocalServiceBaseImpl;
import com.liferay.sampleservicebuilder.util.LocalObject;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class FooLocalServiceImpl extends FooLocalServiceBaseImpl {

	public void addFoo(
			String field1, boolean field2, int field3, Date field4,
			String field5, ServiceContext serviceContext)
		throws PortalException {

		// Foo

		User user = userLocalService.getUserById(serviceContext.getUserId());
		long groupId = serviceContext.getScopeGroupId();
		Date now = new Date();

		long fooId = counterLocalService.increment();

		Foo foo = fooPersistence.create(fooId);

		foo.setGroupId(groupId);
		foo.setCompanyId(user.getCompanyId());
		foo.setUserId(user.getUserId());
		foo.setUserName(user.getFullName());
		foo.setCreateDate(serviceContext.getCreateDate(now));
		foo.setModifiedDate(serviceContext.getModifiedDate(now));
		foo.setField1(field1);
		foo.setField2(field2);
		foo.setField3(field3);
		foo.setField4(field4);
		foo.setField5(field5);
		foo.setExpandoBridgeAttributes(serviceContext);

		fooPersistence.update(foo);

		// Asset

		updateAsset(
			user.getUserId(), foo, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames());
	}

	@Override
	public Foo deleteFoo(Foo foo) {
		try {
			assetEntryLocalService.deleteEntry(
				Foo.class.getName(), foo.getFooId());
		}
		catch (PortalException pe) {
		}

		return fooPersistence.remove(foo);
	}

	@Override
	public Foo deleteFoo(long fooId) {
		Foo foo = fooPersistence.fetchByPrimaryKey(fooId);

		if (foo == null) {
			return null;
		}

		return deleteFoo(foo);
	}

	public List<Foo> getFoos(int start, int end, OrderByComparator<Foo> obc) {
		return fooPersistence.findAll(start, end, obc);
	}

	public List<Foo> getFoos(OrderByComparator<Foo> obc) {
		return getFoos(QueryUtil.ALL_POS, QueryUtil.ALL_POS, obc);
	}

	public Object getLocalObject() throws Exception {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		return InstanceFactory.newInstance(
			contextClassLoader, LocalObject.class.getName());
	}

	public void updateAsset(
			long userId, Foo foo, long[] assetCategoryIds,
			String[] assetTagNames)
		throws PortalException {

		assetEntryLocalService.updateEntry(
			userId, foo.getGroupId(), null, null, Foo.class.getName(),
			foo.getFooId(), foo.getUuid(), 0, assetCategoryIds, assetTagNames,
			true, null, null, null, ContentTypes.TEXT_PLAIN_UTF8,
			foo.getField1(), null, foo.getField1(), null, null, 0, 0,
			null, false);
	}

	public void updateFoo(
			long fooId, String field1, boolean field2, int field3, Date field4,
			String field5, ServiceContext serviceContext)
		throws PortalException {

		User user = userPersistence.findByPrimaryKey(
			serviceContext.getUserId());

		Foo foo = fooPersistence.findByPrimaryKey(fooId);

		foo.setModifiedDate(new Date());
		foo.setField1(field1);
		foo.setField2(field2);
		foo.setField3(field3);
		foo.setField4(field4);
		foo.setField5(field5);
		foo.setExpandoBridgeAttributes(serviceContext);

		fooPersistence.update(foo);

		updateAsset(
			user.getUserId(), foo, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames());
	}

}