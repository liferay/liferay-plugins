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

package com.liferay.vldap.server.handler;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.sasl.AuthorizeCallback;
import javax.security.sasl.RealmCallback;

import org.apache.directory.shared.ldap.name.DN;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class SaslCallbackHandler implements CallbackHandler {

	public DN getName() {
		return _name;
	}

	public String getRealm() {
		return _realm;
	}

	public void handle(Callback[] callbacks)
		throws UnsupportedCallbackException {

		for (Callback callback : callbacks) {
			handle(callback);
		}
	}

	protected void handle(Callback callback)
		throws UnsupportedCallbackException {

		if (callback instanceof RealmCallback) {
			RealmCallback realmCallback = (RealmCallback)callback;

			_realm = realmCallback.getDefaultText();
		}
		else if (callback instanceof NameCallback) {
			NameCallback nameCallback = (NameCallback)callback;

			//_name = nameCallback.getDefaultName();
		}
		else if (callback instanceof PasswordCallback) {
			PasswordCallback passwordCallback =
				(PasswordCallback)callback;

			String password = "hellojon";

			passwordCallback.setPassword(password.toCharArray());
		}
		else if (callback instanceof AuthorizeCallback) {
			AuthorizeCallback authorizeCallback =
				(AuthorizeCallback)callback;

			authorizeCallback.setAuthorized(true);
		}
		else {
			throw new UnsupportedCallbackException(callback);
		}
	}

	private DN _name;
	private String _realm;

}