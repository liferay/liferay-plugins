/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

	public UserAssignment(long userId, String screenName, String emailAddress) {
		super(AssignmentType.USER);

		_userId = userId;
		_screenName = GetterUtil.getString(screenName);
		_emailAddress = GetterUtil.getString(emailAddress);
	}

	@Override
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

	@Override
	public int hashCode() {
		return _emailAddress.concat(_screenName).concat(
			String.valueOf(_userId)).hashCode();
	}

	@Override
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

	private String _emailAddress;
	private String _screenName;
	private long _userId;

}