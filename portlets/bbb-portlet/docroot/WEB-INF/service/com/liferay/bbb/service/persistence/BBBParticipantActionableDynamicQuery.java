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

package com.liferay.bbb.service.persistence;

import com.liferay.bbb.model.BBBParticipant;
import com.liferay.bbb.service.BBBParticipantLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;

/**
 * @author Shinn Lok
 * @deprecated As of 7.0.0, replaced by {@link BBBParticipantLocalServiceUtil#getActionableDynamicQuery()}
 * @generated
 */
@Deprecated
public abstract class BBBParticipantActionableDynamicQuery
	extends BaseActionableDynamicQuery {
	public BBBParticipantActionableDynamicQuery() {
		setBaseLocalService(BBBParticipantLocalServiceUtil.getService());
		setClass(BBBParticipant.class);

		setClassLoader(com.liferay.bbb.service.ClpSerializer.class.getClassLoader());

		setPrimaryKeyPropertyName("bbbParticipantId");
	}
}