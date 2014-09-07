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
public class GGroupMember {

	public String getEmailAddress() {
		return _emailAddress;
	}

	public GGroup getGGroup() {
		return _gGroup;
	}

	public GUser getGUser() {
		return _gUser;
	}

	public String getType() {
		return _type;
	}

	public boolean isDirect() {
		return _direct;
	}

	public boolean isEveryone() {
		if (_emailAddress.equals(StringPool.STAR)) {
			return true;
		}
		else {
			return false;
		}
	}

	public void setDirect(boolean direct) {
		_direct = direct;
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;
	}

	public void setGGroup(GGroup gGroup) {
		_gGroup = gGroup;
	}

	public void setGUser(GUser gUser) {
		_gUser = gUser;
	}

	public void setType(String type) {
		_type = type;
	}

	private boolean _direct;
	private String _emailAddress = StringPool.BLANK;
	private GGroup _gGroup;
	private GUser _gUser;
	private String _type;

}