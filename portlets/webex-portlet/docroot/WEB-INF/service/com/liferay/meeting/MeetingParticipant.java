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

import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

/**
 * @author Brian Wing Shun Chan
 */
public class MeetingParticipant implements Serializable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MeetingParticipant)) {
			return false;
		}

		MeetingParticipant meetingParticipant = (MeetingParticipant)obj;

		if (Objects.equals(_emailAddress, meetingParticipant._emailAddress)) {
			return true;
		}

		return false;
	}

	public String getEmailAddress() {
		return _emailAddress;
	}

	public String getFirstName() {
		return _firstName;
	}

	public String getFullName() {
		return _firstName.concat(_lastName);
	}

	public String getLastName() {
		return _lastName;
	}

	public Locale getLocale() {
		return _locale;
	}

	public String getLogin() {
		return _login;
	}

	public TimeZone getTimeZone() {
		return _timeZone;
	}

	@Override
	public int hashCode() {
		return _emailAddress.hashCode();
	}

	public boolean isSendInvitation() {
		return _sendInvitation;
	}

	public boolean isSendReminder() {
		return _sendReminder;
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;
	}

	public void setFirstName(String firstName) {
		_firstName = firstName;
	}

	public void setLastName(String lastName) {
		_lastName = lastName;
	}

	public void setLocale(Locale locale) {
		_locale = locale;
	}

	public void setLogin(String login) {
		_login = login;
	}

	public void setSendInvitation(boolean sendInvitation) {
		_sendInvitation = sendInvitation;
	}

	public void setSendReminder(boolean sendReminder) {
		_sendReminder = sendReminder;
	}

	public void setTimeZone(TimeZone timeZone) {
		_timeZone = timeZone;
	}

	private String _emailAddress;
	private String _firstName;
	private String _lastName;
	private Locale _locale;
	private String _login;
	private boolean _sendInvitation = true;
	private boolean _sendReminder = true;
	private TimeZone _timeZone;

}