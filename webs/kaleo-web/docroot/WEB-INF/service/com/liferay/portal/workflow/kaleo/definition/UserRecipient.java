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

package com.liferay.portal.workflow.kaleo.definition;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

/**
 * <a href="UserRecipient.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class UserRecipient extends Recipient {

	public UserRecipient() {
		super(RecipientType.USER);
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (!(obj instanceof UserRecipient)) {
			return false;
		}

		UserRecipient userRecipient = (UserRecipient)obj;

		if (Validator.equals(_emailAddress, userRecipient._emailAddress) &&
			Validator.equals(_screenName, userRecipient._screenName) &&
			(_userId == userRecipient._userId)) {

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

	public int hashCode() {
		return _emailAddress.concat(_screenName).concat(
			String.valueOf(_userId)).hashCode();
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = GetterUtil.getString(emailAddress);
	}

	public void setScreenName(String screenName) {
		_screenName = GetterUtil.getString(screenName);
	}

	public void setUserId(long userId) {
		_userId = userId;
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

	private String _emailAddress = StringPool.BLANK;
	private String _screenName = StringPool.BLANK;
	private long _userId;

}