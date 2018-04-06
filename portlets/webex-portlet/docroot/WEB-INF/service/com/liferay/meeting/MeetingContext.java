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

package com.liferay.meeting;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 */
public class MeetingContext implements Serializable {

	public String getApiURL() {
		return _apiURL;
	}

	public String getLogin() {
		return _login;
	}

	public String getName() {
		return _name;
	}

	public String getPartnerKey() {
		return _partnerKey;
	}

	public String getPassword() {
		return _password;
	}

	public long getSiteKey() {
		return _siteKey;
	}

	public void setApiURL(String apiURL) {
		_apiURL = apiURL;
	}

	public void setLogin(String login) {
		_login = login;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setPartnerKey(String partnerKey) {
		_partnerKey = partnerKey;
	}

	public void setPassword(String password) {
		_password = password;
	}

	public void setSiteKey(long siteKey) {
		_siteKey = siteKey;
	}

	private String _apiURL;
	private String _login;
	private String _name;
	private String _partnerKey;
	private String _password;
	private long _siteKey;

}