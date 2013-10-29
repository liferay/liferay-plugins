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

package com.liferay.privatemessaging.service.impl;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.privatemessaging.model.UserThread;
import com.liferay.privatemessaging.service.base.UserThreadServiceBaseImpl;

import java.util.List;

/**
 * @author Bruno Farache
 */
public class UserThreadServiceImpl extends UserThreadServiceBaseImpl {

	public List<UserThread> getUserUserThreads(boolean deleted)
		throws PrincipalException, SystemException {

		return userThreadLocalService.getUserUserThreads(getUserId(), deleted);
	}

}