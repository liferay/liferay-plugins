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

package com.liferay.portal.saml.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 */
public class SSOSessionClp extends BaseModelImpl<SSOSession>
	implements SSOSession {
	public SSOSessionClp() {
	}

	public long getPrimaryKey() {
		return _ssoSessionId;
	}

	public void setPrimaryKey(long pk) {
		setSsoSessionId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_ssoSessionId);
	}

	public long getSsoSessionId() {
		return _ssoSessionId;
	}

	public void setSsoSessionId(long ssoSessionId) {
		_ssoSessionId = ssoSessionId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
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

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getLastActiveDate() {
		return _lastActiveDate;
	}

	public void setLastActiveDate(Date lastActiveDate) {
		_lastActiveDate = lastActiveDate;
	}

	public String getKey() {
		return _key;
	}

	public void setKey(String key) {
		_key = key;
	}

	public boolean isExpired() {
		throw new UnsupportedOperationException();
	}

	public SSOSession toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			return (SSOSession)Proxy.newProxyInstance(SSOSession.class.getClassLoader(),
				new Class[] { SSOSession.class },
				new AutoEscapeBeanHandler(this));
		}
	}

	public Object clone() {
		SSOSessionClp clone = new SSOSessionClp();

		clone.setSsoSessionId(getSsoSessionId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setCreateDate(getCreateDate());
		clone.setLastActiveDate(getLastActiveDate());
		clone.setKey(getKey());

		return clone;
	}

	public int compareTo(SSOSession ssoSession) {
		long pk = ssoSession.getPrimaryKey();

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

		SSOSessionClp ssoSession = null;

		try {
			ssoSession = (SSOSessionClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = ssoSession.getPrimaryKey();

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
		StringBundler sb = new StringBundler(13);

		sb.append("{ssoSessionId=");
		sb.append(getSsoSessionId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", lastActiveDate=");
		sb.append(getLastActiveDate());
		sb.append(", key=");
		sb.append(getKey());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(22);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.saml.model.SSOSession");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>ssoSessionId</column-name><column-value><![CDATA[");
		sb.append(getSsoSessionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>lastActiveDate</column-name><column-value><![CDATA[");
		sb.append(getLastActiveDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>key</column-name><column-value><![CDATA[");
		sb.append(getKey());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _ssoSessionId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private Date _createDate;
	private Date _lastActiveDate;
	private String _key;
}