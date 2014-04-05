/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
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
	public void onAfterUpdate(User user) {
		try {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Updating private messages user name for user " +
						user.getUserId());
			}

			UserThreadLocalServiceUtil.updateUserName(user);
		}
		catch (Exception e) {
			_log.error(
				"Unable to update private messages user name for user " +
					user.getUserId());
		}
	}

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