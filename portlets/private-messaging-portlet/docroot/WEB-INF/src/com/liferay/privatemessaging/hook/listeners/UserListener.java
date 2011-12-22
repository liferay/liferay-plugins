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

package com.liferay.privatemessaging.hook.listeners;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.model.User;
import com.liferay.privatemessaging.service.UserThreadLocalServiceUtil;

/**
 * @author Scott Lee
 */
public class UserListener extends BaseModelListener<User> {

	@Override
	public void onBeforeRemove(User user) {
		try {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Removing private messages for user " +
						user.getUserId());
			}

			UserThreadLocalServiceUtil.deleteUser(user.getUserId());
		}
		catch (Exception e) {
			_log.error(
				"Unable to remove private messages for user " +
					user.getUserId());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(UserListener.class);

}