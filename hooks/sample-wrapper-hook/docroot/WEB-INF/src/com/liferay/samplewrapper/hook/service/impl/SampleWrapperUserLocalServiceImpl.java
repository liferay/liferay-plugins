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

package com.liferay.samplewrapper.hook.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.UserLocalServiceWrapper;
import com.liferay.samplewrapper.hook.model.impl.SampleWrapperUserImpl;

/**
 * @author Brian Wing Shun Chan
 */
public class SampleWrapperUserLocalServiceImpl extends UserLocalServiceWrapper {

	public SampleWrapperUserLocalServiceImpl(
		UserLocalService userLocalService) {

		super(userLocalService);
	}

	@Override
	public User getUserById(long userId) throws PortalException {
		System.out.println(
			"Called SampleWrapperUserLocalServiceImpl.getUserById(" + userId +
				")");

		User user = super.getUserById(userId);

		return new SampleWrapperUserImpl(user);
	}

}