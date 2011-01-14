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
public class SPSessionClp extends BaseModelImpl<SPSession> implements SPSession {
	public SPSessionClp() {
	}

	public long getPrimaryKey() {
		return _spSessionId;
	}

	public void setPrimaryKey(long pk) {
		setSpSessionId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_spSessionId);
	}

	public long getSpSessionId() {
		return _spSessionId;
	}

	public void setSpSessionId(long spSessionId) {
		_spSessionId = spSessionId;
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

	public long getSsoSessionId() {
		return _ssoSessionId;
	}

	public void setSsoSessionId(long ssoSessionId) {
		_ssoSessionId = ssoSessionId;
	}

	public String getIssuer() {
		return _issuer;
	}

	public void setIssuer(String issuer) {
		_issuer = issuer;
	}

	public SPSession toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			return (SPSession)Proxy.newProxyInstance(SPSession.class.getClassLoader(),
				new Class[] { SPSession.class }, new AutoEscapeBeanHandler(this));
		}
	}

	public Object clone() {
		SPSessionClp clone = new SPSessionClp();

		clone.setSpSessionId(getSpSessionId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setCreateDate(getCreateDate());
		clone.setSsoSessionId(getSsoSessionId());
		clone.setIssuer(getIssuer());

		return clone;
	}

	public int compareTo(SPSession spSession) {
		long pk = spSession.getPrimaryKey();

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

		SPSessionClp spSession = null;

		try {
			spSession = (SPSessionClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = spSession.getPrimaryKey();

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

		sb.append("{spSessionId=");
		sb.append(getSpSessionId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", ssoSessionId=");
		sb.append(getSsoSessionId());
		sb.append(", issuer=");
		sb.append(getIssuer());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(22);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.saml.model.SPSession");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>spSessionId</column-name><column-value><![CDATA[");
		sb.append(getSpSessionId());
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
			"<column><column-name>ssoSessionId</column-name><column-value><![CDATA[");
		sb.append(getSsoSessionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>issuer</column-name><column-value><![CDATA[");
		sb.append(getIssuer());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _spSessionId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private Date _createDate;
	private long _ssoSessionId;
	private String _issuer;
}