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

package com.liferay.google.apps.connector;

import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Brian Wing Shun Chan
 */
public class GUser {

	public String getFirstName() {
		return _firstName;
	}

	public String getFullName() {
		return _firstName.concat(StringPool.SPACE).concat(_lastName);
	}

	public String getLastName() {
		return _lastName;
	}

	public long getUserId() {
		return _userId;
	}

	public boolean isActive() {
		return _active;
	}

	public boolean isAdministrator() {
		return _administrator;
	}

	public boolean isAgreedToTermsOfUse() {
		return _agreedToTermsOfUse;
	}

	public void setActive(boolean active) {
		_active = active;
	}

	public void setAdministrator(boolean administrator) {
		_administrator = administrator;
	}

	public void setAgreedToTermsOfUse(boolean agreedToTermsOfUse) {
		_agreedToTermsOfUse = agreedToTermsOfUse;
	}

	public void setFirstName(String firstName) {
		_firstName = firstName;
	}

	public void setLastName(String lastName) {
		_lastName = lastName;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	private boolean _active;
	private boolean _administrator;
	private boolean _agreedToTermsOfUse;
	private String _firstName;
	private String _lastName;
	private long _userId;

}