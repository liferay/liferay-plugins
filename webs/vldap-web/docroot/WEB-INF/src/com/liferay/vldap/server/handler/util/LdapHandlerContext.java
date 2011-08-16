/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.vldap.server.handler.util;

import com.liferay.portal.model.Company;
import com.liferay.portal.model.User;

import javax.security.sasl.SaslServer;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class LdapHandlerContext {

	public Company getCompany() {
		return _company;
	}

	public SaslCallbackHandler getSaslCallbackHandler() {
		return _saslCallbackHandler;
	}

	public SaslServer getSaslServer() {
		return _saslServer;
	}

	public User getUser() {
		return _user;
	}

	public void setCompany(Company company) {
		_company = company;
	}

	public void setSaslCallbackHandler(
		SaslCallbackHandler saslCallbackHandler) {

		_saslCallbackHandler = saslCallbackHandler;
	}

	public void setSaslServer(SaslServer saslServer) {
		_saslServer = saslServer;
	}

	public void setUser(User user) {
		_user = user;
	}

	private Company _company;
	private SaslCallbackHandler _saslCallbackHandler;
	private SaslServer _saslServer;
	private User _user;

}