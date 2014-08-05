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

package com.liferay.sync.engine.documentlibrary.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Dennis Ju
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SyncUser {

	public long getCompanyId() {
		return _companyId;
	}

	public String getEmailAddress() {
		return _emailAddress;
	}

	public String getFirstName() {
		return _firstName;
	}

	public String getJobTitle() {
		return _jobTitle;
	}

	public String getLanguageId() {
		return _languageId;
	}

	public String getLastName() {
		return _lastName;
	}

	public String getMiddleName() {
		return _middleName;
	}

	public long getPortraitId() {
		return _portraitId;
	}

	public String getScreenName() {
		return _screenName;
	}

	public int getStatus() {
		return _status;
	}

	public String getTimeZoneId() {
		return _timeZoneId;
	}

	public long getUserId() {
		return _userId;
	}

	public String getUuid() {
		return _uuid;
	}

	public boolean isLockout() {
		return _lockout;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;
	}

	public void setFirstName(String firstName) {
		_firstName = firstName;
	}

	public void setJobTitle(String jobTitle) {
		_jobTitle = jobTitle;
	}

	public void setLanguageId(String languageId) {
		_languageId = languageId;
	}

	public void setLastName(String lastName) {
		_lastName = lastName;
	}

	public void setLockout(boolean lockout) {
		_lockout = lockout;
	}

	public void setMiddleName(String middleName) {
		_middleName = middleName;
	}

	public void setPortraitId(long portraitId) {
		_portraitId = portraitId;
	}

	public void setScreenName(String screenName) {
		_screenName = screenName;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public void setTimeZoneId(String timeZoneId) {
		_timeZoneId = timeZoneId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	private long _companyId;
	private String _emailAddress;
	private String _firstName;
	private String _jobTitle;
	private String _languageId;
	private String _lastName;
	private boolean _lockout;
	private String _middleName;
	private long _portraitId;
	private String _screenName;
	private int _status;
	private String _timeZoneId;
	private long _userId;
	private String _uuid;

}