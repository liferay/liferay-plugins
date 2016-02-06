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

package com.liferay.chat.hook.listeners;

import com.liferay.chat.jabber.JabberUtil;
import com.liferay.chat.model.Status;
import com.liferay.chat.service.EntryLocalServiceUtil;
import com.liferay.chat.service.StatusLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.User;

/**
 * @author Scott Lee
 * @author Bruno Farache
 */
public class UserModelListener extends BaseModelListener<User> {

	@Override
	public void onAfterRemove(User user) {
		try {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Removing chat entries and status for user " +
						user.getUserId());
			}

			EntryLocalServiceUtil.deleteEntries(user.getUserId());

			Status status = StatusLocalServiceUtil.getUserStatus(
				user.getUserId());

			if (status != null) {
				StatusLocalServiceUtil.deleteStatus(status);
			}
		}
		catch (Exception e) {
			_log.error(
				"Unable to remove chat entries and status for user " +
					user.getUserId());
		}
	}

	@Override
	public void onAfterUpdate(User user) {
		JabberUtil.updatePassword(
			user.getUserId(), user.getPasswordUnencrypted());
	}

	private static Log _log = LogFactoryUtil.getLog(UserModelListener.class);

}