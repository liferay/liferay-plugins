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

package com.liferay.share.service.persistence;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;

import com.liferay.share.model.EntitySocialFeed;
import com.liferay.share.service.EntitySocialFeedLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 * @deprecated As of 7.0.0, replaced by {@link EntitySocialFeedLocalServiceUtil#getActionableDynamicQuery()}
 * @generated
 */
@Deprecated
public abstract class EntitySocialFeedActionableDynamicQuery
	extends BaseActionableDynamicQuery {
	public EntitySocialFeedActionableDynamicQuery() {
		setBaseLocalService(EntitySocialFeedLocalServiceUtil.getService());
		setClass(EntitySocialFeed.class);

		setClassLoader(com.liferay.share.service.ClpSerializer.class.getClassLoader());

		setPrimaryKeyPropertyName("primaryKey.classNameId");
	}
}