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

package com.liferay.mail.hook.listeners;

import com.liferay.mail.mailbox.Mailbox;
import com.liferay.mail.mailbox.MailboxFactoryUtil;
import com.liferay.mail.model.Account;
import com.liferay.mail.service.AccountLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.StringPool;

import java.util.List;

/**
 * @author Scott Lee
 */
public class UserModelListener extends BaseModelListener<User> {

	@Override
	public void onAfterRemove(User user) {
		try {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Removing mail accounts for user " + user.getUserId());
			}

			List<Account> accounts = AccountLocalServiceUtil.getAccounts(
				user.getUserId());

			for (Account account : accounts) {
				Mailbox mailbox = MailboxFactoryUtil.getMailbox(
					user.getUserId(), account.getAccountId(), StringPool.BLANK);

				mailbox.deleteAccount();
			}
		}
		catch (Exception e) {
			_log.error(
				"Unable to remove mail accounts for user " + user.getUserId());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(UserModelListener.class);

}