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

package com.liferay.samplelar.service.persistence;

import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;

import com.liferay.samplelar.model.SampleLARBooking;
import com.liferay.samplelar.service.SampleLARBookingLocalServiceUtil;

/**
 * @author Mate Thurzo
 * @deprecated As of 7.0.0, replaced by {@link SampleLARBookingLocalServiceUtil#getActionableDynamicQuery()}
 * @generated
 */
@Deprecated
public abstract class SampleLARBookingActionableDynamicQuery
	extends DefaultActionableDynamicQuery {
	public SampleLARBookingActionableDynamicQuery() {
		setBaseLocalService(SampleLARBookingLocalServiceUtil.getService());

		setClassLoader(com.liferay.samplelar.service.ClpSerializer.class.getClassLoader());

		setModelClass(SampleLARBooking.class);

		setPrimaryKeyPropertyName("sampleLARBookingId");
	}
}