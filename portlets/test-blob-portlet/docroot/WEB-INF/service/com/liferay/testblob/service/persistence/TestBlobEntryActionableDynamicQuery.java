/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.testblob.service.persistence;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

import com.liferay.testblob.model.TestBlobEntry;
import com.liferay.testblob.service.TestBlobEntryLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class TestBlobEntryActionableDynamicQuery
	extends BaseActionableDynamicQuery {
	public TestBlobEntryActionableDynamicQuery() throws SystemException {
		setBaseLocalService(TestBlobEntryLocalServiceUtil.getService());
		setClass(TestBlobEntry.class);

		setClassLoader(com.liferay.testblob.service.ClpSerializer.class.getClassLoader());

		setPrimaryKeyPropertyName("testBlobEntryId");
	}
}