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

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.sasl.AuthorizeCallback;
import javax.security.sasl.RealmCallback;

import org.apache.directory.shared.ldap.model.name.Dn;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class SaslCallbackHandler implements CallbackHandler {

	public Dn getName() {
		return _name;
	}

	public String getRealm() {
		return _realm;
	}

	public void handle(Callback[] callbacks)
		throws IOException, UnsupportedCallbackException {

		for (Callback callback : callbacks) {
			try {
				handle(callback);
			}
			catch (IOException ioe) {
				throw ioe;
			}
			catch (UnsupportedCallbackException uce) {
				throw uce;
			}
			catch (Exception e) {
				throw new IOException(e.getMessage());
			}
		}
	}

	protected void handle(Callback callback) throws Exception {
		if (callback instanceof AuthorizeCallback) {
			AuthorizeCallback authorizeCallback =
				(AuthorizeCallback)callback;

			handleAuthorizeCallback(authorizeCallback);
		}
		else if (callback instanceof NameCallback) {
			NameCallback nameCallback = (NameCallback)callback;

			handleNameCallback(nameCallback);
		}
		else if (callback instanceof PasswordCallback) {
			PasswordCallback passwordCallback =
				(PasswordCallback)callback;

			handlePasswordCallback(passwordCallback);
		}
		else if (callback instanceof RealmCallback) {
			RealmCallback realmCallback = (RealmCallback)callback;

			handleRealmCallback(realmCallback);
		}
		else {
			throw new UnsupportedCallbackException(callback);
		}
	}

	protected void handleAuthorizeCallback(
		AuthorizeCallback authorizeCallback) {

		authorizeCallback.setAuthorized(true);
	}

	protected void handleNameCallback(NameCallback nameCallback)
		throws Exception {

		_name = new Dn(nameCallback.getDefaultName());
	}

	protected void handlePasswordCallback(PasswordCallback passwordCallback) {
		String password = "hellojon";

		passwordCallback.setPassword(password.toCharArray());
	}

	protected void handleRealmCallback(RealmCallback realmCallback) {
		_realm = realmCallback.getDefaultText();
	}

	private Dn _name;
	private String _realm;

}