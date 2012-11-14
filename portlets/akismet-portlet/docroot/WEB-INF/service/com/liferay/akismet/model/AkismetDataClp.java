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

package com.liferay.akismet.model;

import com.liferay.akismet.service.AkismetDataLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class AkismetDataClp extends BaseModelImpl<AkismetData>
	implements AkismetData {
	public AkismetDataClp() {
	}

	public Class<?> getModelClass() {
		return AkismetData.class;
	}

	public String getModelClassName() {
		return AkismetData.class.getName();
	}

	public long getPrimaryKey() {
		return _akismetDataId;
	}

	public void setPrimaryKey(long primaryKey) {
		setAkismetDataId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_akismetDataId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("akismetDataId", getAkismetDataId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("mbMessageId", getMbMessageId());
		attributes.put("type", getType());
		attributes.put("permalink", getPermalink());
		attributes.put("referrer", getReferrer());
		attributes.put("userAgent", getUserAgent());
		attributes.put("userIP", getUserIP());
		attributes.put("userURL", getUserURL());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long akismetDataId = (Long)attributes.get("akismetDataId");

		if (akismetDataId != null) {
			setAkismetDataId(akismetDataId);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long mbMessageId = (Long)attributes.get("mbMessageId");

		if (mbMessageId != null) {
			setMbMessageId(mbMessageId);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String permalink = (String)attributes.get("permalink");

		if (permalink != null) {
			setPermalink(permalink);
		}

		String referrer = (String)attributes.get("referrer");

		if (referrer != null) {
			setReferrer(referrer);
		}

		String userAgent = (String)attributes.get("userAgent");

		if (userAgent != null) {
			setUserAgent(userAgent);
		}

		String userIP = (String)attributes.get("userIP");

		if (userIP != null) {
			setUserIP(userIP);
		}

		String userURL = (String)attributes.get("userURL");

		if (userURL != null) {
			setUserURL(userURL);
		}
	}

	public long getAkismetDataId() {
		return _akismetDataId;
	}

	public void setAkismetDataId(long akismetDataId) {
		_akismetDataId = akismetDataId;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getMbMessageId() {
		return _mbMessageId;
	}

	public void setMbMessageId(long mbMessageId) {
		_mbMessageId = mbMessageId;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	public String getPermalink() {
		return _permalink;
	}

	public void setPermalink(String permalink) {
		_permalink = permalink;
	}

	public String getReferrer() {
		return _referrer;
	}

	public void setReferrer(String referrer) {
		_referrer = referrer;
	}

	public String getUserAgent() {
		return _userAgent;
	}

	public void setUserAgent(String userAgent) {
		_userAgent = userAgent;
	}

	public String getUserIP() {
		return _userIP;
	}

	public void setUserIP(String userIP) {
		_userIP = userIP;
	}

	public String getUserURL() {
		return _userURL;
	}

	public void setUserURL(String userURL) {
		_userURL = userURL;
	}

	public BaseModel<?> getAkismetDataRemoteModel() {
		return _akismetDataRemoteModel;
	}

	public void setAkismetDataRemoteModel(BaseModel<?> akismetDataRemoteModel) {
		_akismetDataRemoteModel = akismetDataRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			AkismetDataLocalServiceUtil.addAkismetData(this);
		}
		else {
			AkismetDataLocalServiceUtil.updateAkismetData(this);
		}
	}

	@Override
	public AkismetData toEscapedModel() {
		return (AkismetData)ProxyUtil.newProxyInstance(AkismetData.class.getClassLoader(),
			new Class[] { AkismetData.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		AkismetDataClp clone = new AkismetDataClp();

		clone.setAkismetDataId(getAkismetDataId());
		clone.setModifiedDate(getModifiedDate());
		clone.setMbMessageId(getMbMessageId());
		clone.setType(getType());
		clone.setPermalink(getPermalink());
		clone.setReferrer(getReferrer());
		clone.setUserAgent(getUserAgent());
		clone.setUserIP(getUserIP());
		clone.setUserURL(getUserURL());

		return clone;
	}

	public int compareTo(AkismetData akismetData) {
		long primaryKey = akismetData.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		AkismetDataClp akismetData = null;

		try {
			akismetData = (AkismetDataClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = akismetData.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{akismetDataId=");
		sb.append(getAkismetDataId());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", mbMessageId=");
		sb.append(getMbMessageId());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", permalink=");
		sb.append(getPermalink());
		sb.append(", referrer=");
		sb.append(getReferrer());
		sb.append(", userAgent=");
		sb.append(getUserAgent());
		sb.append(", userIP=");
		sb.append(getUserIP());
		sb.append(", userURL=");
		sb.append(getUserURL());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("com.liferay.akismet.model.AkismetData");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>akismetDataId</column-name><column-value><![CDATA[");
		sb.append(getAkismetDataId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>mbMessageId</column-name><column-value><![CDATA[");
		sb.append(getMbMessageId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>permalink</column-name><column-value><![CDATA[");
		sb.append(getPermalink());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>referrer</column-name><column-value><![CDATA[");
		sb.append(getReferrer());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userAgent</column-name><column-value><![CDATA[");
		sb.append(getUserAgent());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userIP</column-name><column-value><![CDATA[");
		sb.append(getUserIP());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userURL</column-name><column-value><![CDATA[");
		sb.append(getUserURL());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _akismetDataId;
	private Date _modifiedDate;
	private long _mbMessageId;
	private String _type;
	private String _permalink;
	private String _referrer;
	private String _userAgent;
	private String _userIP;
	private String _userURL;
	private BaseModel<?> _akismetDataRemoteModel;
}