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

package com.liferay.portal.workflow.kaleo.definition;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Michael C. Han
 */
public class UserAssignment extends Assignment {

	public UserAssignment() {
		super(AssignmentType.USER);
	}

	public void configureParent(DefinitionNode parentNode) {
		if (parentNode instanceof Recipients) {
			Recipients recipients = (Recipients)parentNode;

			UserRecipient userRecipient = new UserRecipient(
				getUserId(), getScreenName(), getEmailAddress());

			recipients.addRecipient(userRecipient);
		}
		else if (parentNode instanceof Assignments) {
			Assignments assignments = (Assignments)parentNode;

			assignments.addAssignment(this);
		}
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (!(obj instanceof UserAssignment)) {
			return false;
		}

		UserAssignment userAssignment = (UserAssignment)obj;

		if (Validator.equals(_emailAddress, userAssignment._emailAddress) &&
			Validator.equals(_screenName, userAssignment._screenName) &&
			(_userId == userAssignment._userId)) {

			return true;
		}

		return true;
	}

	public String getEmailAddress() {
		return _emailAddress;
	}

	public String getScreenName() {
		return _screenName;
	}

	public long getUserId() {
		return _userId;
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;
	}

	public void setScreenName(String screenName) {
		_screenName = screenName;
	}

	public void setUserId(String userId) {
		 _userId = GetterUtil.getLong(userId);
	}

	public int hashCode() {
		return _emailAddress.concat(_screenName).concat(
			String.valueOf(_userId)).hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{emailAddress=");
		sb.append(_emailAddress);
		sb.append(", screenName=");
		sb.append(_screenName);
		sb.append(", userId=");
		sb.append(_userId);
		sb.append("}");

		return sb.toString();
	}

	private String _emailAddress = "";
	private String _screenName = "";
	private long _userId;

}