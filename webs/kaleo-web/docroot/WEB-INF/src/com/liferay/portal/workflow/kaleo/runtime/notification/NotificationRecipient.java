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

package com.liferay.portal.workflow.kaleo.runtime.notification;

import com.liferay.portal.kernel.util.HashCode;
import com.liferay.portal.kernel.util.HashCodeFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.workflow.kaleo.definition.NotificationReceptionType;

import java.io.UnsupportedEncodingException;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * @author Michael C. Han
 */
public class NotificationRecipient {

	public NotificationRecipient(
		String emailAddress,
		NotificationReceptionType notificationReceptionType) {

		_emailAddress = emailAddress;
		_notificationReceptionType = notificationReceptionType;
	}

	public NotificationRecipient(
		User user, NotificationReceptionType notificationReceptionType) {

		_notificationReceptionType = notificationReceptionType;

		_companyId = user.getCompanyId();
		_emailAddress = user.getEmailAddress();
		_fullName = user.getFullName();
		_screenName = user.getScreenName();
		_userId = user.getUserId();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NotificationRecipient)) {
			return false;
		}

		NotificationRecipient notificationRecipient =
			(NotificationRecipient)obj;

		if (Validator.equals(_companyId, notificationRecipient._companyId) &&
			Validator.equals(
				_emailAddress, notificationRecipient._emailAddress) &&
			Validator.equals(_userId, notificationRecipient._userId)) {

			return true;
		}

		return false;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public String getEmailAddress() {
		return _emailAddress;
	}

	public String getFullName() {
		return _fullName;
	}

	public InternetAddress getInternetAddress()
		throws AddressException, UnsupportedEncodingException {

		if (Validator.isNull(_fullName)) {
			return new InternetAddress(_emailAddress);
		}
		else {
			return new InternetAddress(_emailAddress, _fullName);
		}
	}

	public NotificationReceptionType getNotificationReceptionType() {
		return _notificationReceptionType;
	}

	public String getScreenName() {
		return _screenName;
	}

	public long getUserId() {
		return _userId;
	}

	@Override
	public int hashCode() {
		HashCode hashCode = HashCodeFactoryUtil.getHashCode();

		hashCode.append(_companyId);

		if (Validator.isNotNull(_emailAddress)) {
			hashCode.append(_emailAddress);
		}

		hashCode.append(_userId);

		return hashCode.toHashCode();
	}

	private long _companyId;
	private String _emailAddress;
	private String _fullName;
	private NotificationReceptionType _notificationReceptionType;
	private String _screenName;
	private long _userId;

}