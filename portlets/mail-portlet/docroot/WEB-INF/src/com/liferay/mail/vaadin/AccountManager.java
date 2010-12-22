/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.mail.vaadin;

import com.liferay.mail.MailException;
import com.liferay.mail.mailbox.Mailbox;
import com.liferay.mail.mailbox.MailboxFactoryUtil;
import com.liferay.mail.mailbox.PasswordRetriever;
import com.liferay.mail.model.Account;
import com.liferay.mail.service.AccountLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;

import java.util.List;

/**
 * @author Henri Sara
 */
public class AccountManager {

	/**
	 * Returns the list of mail accounts for a user.
	 */
	public List<Account> getAccounts(User user) throws SystemException {
		return AccountLocalServiceUtil.getAccounts(user.getUserId());
	}

	/**
	 * Returns the (decrypted) password for a user, either from the account or
	 * from the session.
	 */
	public String getPassword(Account account, Controller controller)
		throws PortalException, SystemException {

		return controller.getPasswordRetriever().getPassword(account.getAccountId());
	}

	/**
	 * Creates a new account and returns failure message key or
	 * <code>null</code> if successful.
	 */
	public String addAccount(Account account, Controller controller)
			throws PortalException, SystemException {
		try {
			PasswordRetriever passwordRetriever = controller.getPasswordRetriever();
			User user = controller.getUser();

			Mailbox mailbox = MailboxFactoryUtil.getMailbox(user.getUserId(),
					account.getProtocol());

			Account createdAccount = mailbox.addAccount(account.getAddress(),
					account.getPersonalName(), account.getProtocol(), account
							.getIncomingHostName(), account.getIncomingPort(),
					account.isIncomingSecure(), account.getOutgoingHostName(),
					account.getOutgoingPort(), account.isOutgoingSecure(),
					account.getLogin(), account.getPasswordDecrypted(), account
							.isSavePassword(), account.getSignature(), account
							.isUseSignature(), account.getFolderPrefix(),
					account.getDefaultSender());

			if (!account.isSavePassword()) {
				passwordRetriever.setPassword(createdAccount.getAccountId(),
						account.getPasswordDecrypted());
			}

			mailbox = MailboxFactoryUtil.getMailbox(user
					.getUserId(), createdAccount.getAccountId(), passwordRetriever
					.getPassword(createdAccount.getAccountId()));

			mailbox.synchronize();

			return null;
		}
		catch (MailException me) {
			if (me.getType() == MailException.ACCOUNT_ALREADY_EXISTS) {
				return "an-account-with-the-same-address-already-exists";
			}

			_log.error(me, me);

			return "unable-to-add-account";
		}
	}

	/**
	 * Updates an account and returns failure message key or <code>null</code>
	 * if successful.
	 */
	public String updateAccount(Account account, Controller controller)
			throws PortalException, SystemException {

		try {
			PasswordRetriever passwordRetriever = controller.getPasswordRetriever();
			if (account.isSavePassword()) {
				passwordRetriever.removePassword(account.getAccountId());
			}

			String password = account.getPasswordDecrypted();
			if (Validator.isNull(password)) {
				String oldPassword = passwordRetriever.getPassword(
					account.getAccountId());

				if (Validator.isNull(oldPassword)) {
					throw new MailException("no password");
				}
				else {
					password = oldPassword;
				}
			}

			Mailbox mailbox = MailboxFactoryUtil.getMailbox(
				controller.getUser().getUserId(), account.getAccountId(), password);

			mailbox.updateAccount(account.getAccountId(), account
					.getPersonalName(), password, account.isSavePassword(),
					account.getSignature(), account.isUseSignature(), account
							.getFolderPrefix(), account.getDefaultSender());

			return null;
		}
		catch (MailException me) {
			_log.error(me, me);

			return "unable-to-update-account";
		}
	}

	private static Log _log = LogFactoryUtil.getLog(AccountManager.class);

}