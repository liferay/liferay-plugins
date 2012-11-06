/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.kernel.dao.orm;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.BaseLocalService;

/**
 * @author Brian Wing Shun Chan
 */
public interface ActionableDynamicQuery {

	public void performActions() throws PortalException, SystemException;

	public void setBaseLocalService(BaseLocalService baseLocalService)
		throws SystemException;

	public void setClass(Class<?> clazz);

	public void setClassLoader(ClassLoader classLoader);

	public void setCompanyId(long companyId);

	public void setGroupId(long groupId);

	public void setInterval(int interval);

	public void setPrimaryKeyPropertyName(String primaryKeyPropertyName);

}