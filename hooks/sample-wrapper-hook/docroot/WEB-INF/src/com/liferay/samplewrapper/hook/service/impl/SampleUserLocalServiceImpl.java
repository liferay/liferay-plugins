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

package com.liferay.samplewrapper.hook.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserLocalServiceWrapper;
import com.liferay.samplewrapper.hook.model.impl.SampleUserImpl;

/**
 * @author Brian Wing Shun Chan
 */
public class SampleUserLocalServiceImpl extends UserLocalServiceWrapper {

	public SampleUserLocalServiceImpl(UserLocalService userLocalService) {
		super(userLocalService);
	}

	@Override
	public User getUserById(long userId)
		throws PortalException, SystemException {

		System.out.println(
			"Called SampleUserLocalServiceImpl.getUserById(" + userId + ")");

		User user = super.getUserById(userId);

		return new SampleUserImpl(user);
	}

}