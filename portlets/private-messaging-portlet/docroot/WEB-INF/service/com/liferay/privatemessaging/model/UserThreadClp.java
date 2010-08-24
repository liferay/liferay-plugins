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

package com.liferay.privatemessaging.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

/**
 * @author Brian Wing Shun Chan
 */
public class UserThreadClp extends BaseModelImpl<UserThread>
	implements UserThread {
	public UserThreadClp() {
	}

	public long getPrimaryKey() {
		return _userThreadId;
	}

	public void setPrimaryKey(long pk) {
		setUserThreadId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_userThreadId);
	}

	public long getUserThreadId() {
		return _userThreadId;
	}

	public void setUserThreadId(long userThreadId) {
		_userThreadId = userThreadId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public long getMbThreadId() {
		return _mbThreadId;
	}

	public void setMbThreadId(long mbThreadId) {
		_mbThreadId = mbThreadId;
	}

	public boolean getRead() {
		return _read;
	}

	public boolean isRead() {
		return _read;
	}

	public void setRead(boolean read) {
		_read = read;
	}

	public UserThread toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			return (UserThread)Proxy.newProxyInstance(UserThread.class.getClassLoader(),
				new Class[] { UserThread.class },
				new AutoEscapeBeanHandler(this));
		}
	}

	public Object clone() {
		UserThreadClp clone = new UserThreadClp();

		clone.setUserThreadId(getUserThreadId());
		clone.setUserId(getUserId());
		clone.setMbThreadId(getMbThreadId());
		clone.setRead(getRead());

		return clone;
	}

	public int compareTo(UserThread userThread) {
		long pk = userThread.getPrimaryKey();

		if (getPrimaryKey() < pk) {
			return -1;
		}
		else if (getPrimaryKey() > pk) {
			return 1;
		}
		else {
			return 0;
		}
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		UserThreadClp userThread = null;

		try {
			userThread = (UserThreadClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = userThread.getPrimaryKey();

		if (getPrimaryKey() == pk) {
			return true;
		}
		else {
			return false;
		}
	}

	public int hashCode() {
		return (int)getPrimaryKey();
	}

	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{userThreadId=");
		sb.append(getUserThreadId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", mbThreadId=");
		sb.append(getMbThreadId());
		sb.append(", read=");
		sb.append(getRead());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(16);

		sb.append("<model><model-name>");
		sb.append("com.liferay.privatemessaging.model.UserThread");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>userThreadId</column-name><column-value><![CDATA[");
		sb.append(getUserThreadId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>mbThreadId</column-name><column-value><![CDATA[");
		sb.append(getMbThreadId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>read</column-name><column-value><![CDATA[");
		sb.append(getRead());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _userThreadId;
	private long _userId;
	private String _userUuid;
	private long _mbThreadId;
	private boolean _read;
}