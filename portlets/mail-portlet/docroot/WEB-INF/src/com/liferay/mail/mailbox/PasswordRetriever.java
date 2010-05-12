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

package com.liferay.mail.mailbox;

import com.liferay.mail.model.Account;
import com.liferay.mail.service.AccountLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <a href="PasswordRetriever.java.html"><b><i>View Source</i></b></a>
 *
 * @author Mike Han
 * @author Scott Lee
 */
public class PasswordRetriever {

	public PasswordRetriever(HttpServletRequest request) {
		_request = request;
	}

	public String getPassword(long accountId)
		throws PortalException, SystemException {

		if (Validator.isNull(_request)) {
			throw new IllegalStateException("Uninitialized retriever");
		}

		Account account = AccountLocalServiceUtil.getAccount(accountId);

		if (account.isSavePassword()) {
			return account.getPasswordDecrypted();
		}
		else {
			HttpSession session = _request.getSession();

			return (String)session.getAttribute(getPasswordKey(accountId));
		}
	}

	public void removePassword(long accountId) {
		HttpSession session = _request.getSession();

		session.removeAttribute(getPasswordKey(accountId));
	}

	public void setPassword(long accountId, String password) {
		HttpSession session = _request.getSession();

		session.setAttribute(getPasswordKey(accountId), password);
	}

	public String getPasswordKey(long accountId) {
		return "mail_p_key_" + accountId;
	}

	private HttpServletRequest _request;

}