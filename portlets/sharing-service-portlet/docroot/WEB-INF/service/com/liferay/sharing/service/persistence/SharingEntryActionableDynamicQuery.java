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

package com.liferay.sharing.service.persistence;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;

import com.liferay.sharing.model.SharingEntry;
import com.liferay.sharing.service.SharingEntryLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 * @deprecated As of 7.0.0, replaced by {@link SharingEntryLocalServiceUtil#getActionableDynamicQuery()}
 * @generated
 */
@Deprecated
public abstract class SharingEntryActionableDynamicQuery
	extends BaseActionableDynamicQuery {
	public SharingEntryActionableDynamicQuery() {
		setBaseLocalService(SharingEntryLocalServiceUtil.getService());
		setClass(SharingEntry.class);

		setClassLoader(com.liferay.sharing.service.ClpSerializer.class.getClassLoader());

		setPrimaryKeyPropertyName("primaryKey.classNameId");
	}
}