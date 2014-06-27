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

package com.liferay.mail.hook.events;

import com.liferay.mail.model.Account;
import com.liferay.mail.util.MailManager;
import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Scott Lee
 */
public class LoginPostAction extends Action {

	@Override
	public void run(HttpServletRequest request, HttpServletResponse response)
		throws ActionException {

		try {
			initiateSynchronization(request);
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	protected void initiateSynchronization(HttpServletRequest request)
		throws PortalException {

		MailManager mailManager = MailManager.getInstance(request);

		List<Account> accounts = mailManager.getAccounts();

		for (Account account : accounts) {
			mailManager.synchronizeAccount(account.getAccountId());
		}
	}

}