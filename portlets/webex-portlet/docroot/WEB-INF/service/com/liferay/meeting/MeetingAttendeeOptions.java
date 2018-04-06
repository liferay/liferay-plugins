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
public class MeetingAttendeeOptions implements Serializable {

	public int getMaxParticipants() {
		return _maxParticipants;
	}

	public boolean isAutoAcceptRegistration() {
		return _autoAcceptRegistration;
	}

	public boolean isExcludePassword() {
		return _excludePassword;
	}

	public boolean isJoinRequiresAccount() {
		return _joinRequiresAccount;
	}

	public boolean isRegistrationRequired() {
		return _registrationRequired;
	}

	public boolean isRequestAttendeeDetails() {
		return _requestAttendeeDetails;
	}

	public boolean isSendEmailInvitation() {
		return _sendEmailInvitation;
	}

	public void setAutoAcceptRegistration(boolean autoAcceptRegistration) {
		_autoAcceptRegistration = autoAcceptRegistration;
	}

	public void setExcludePassword(boolean excludePassword) {
		_excludePassword = excludePassword;
	}

	public void setJoinRequiresAccount(boolean joinRequiresAccount) {
		_joinRequiresAccount = joinRequiresAccount;
	}

	public void setMaxParticipants(int maxParticipants) {
		_maxParticipants = maxParticipants;
	}

	public void setRegistrationRequired(boolean registrationRequired) {
		_registrationRequired = registrationRequired;
	}

	public void setRequestAttendeeDetails(boolean requestAttendeeDetails) {
		_requestAttendeeDetails = requestAttendeeDetails;
	}

	public void setSendEmailInvitation(boolean sendEmailInvitation) {
		_sendEmailInvitation = sendEmailInvitation;
	}

	private boolean _autoAcceptRegistration;
	private boolean _excludePassword;
	private boolean _joinRequiresAccount;
	private int _maxParticipants;
	private boolean _registrationRequired;
	private boolean _requestAttendeeDetails;
	private boolean _sendEmailInvitation;

}