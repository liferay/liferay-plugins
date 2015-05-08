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

package com.liferay.akismet.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class AkismetDataSoap implements Serializable {
	public static AkismetDataSoap toSoapModel(AkismetData model) {
		AkismetDataSoap soapModel = new AkismetDataSoap();

		soapModel.setAkismetDataId(model.getAkismetDataId());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setType(model.getType());
		soapModel.setPermalink(model.getPermalink());
		soapModel.setReferrer(model.getReferrer());
		soapModel.setUserAgent(model.getUserAgent());
		soapModel.setUserIP(model.getUserIP());
		soapModel.setUserURL(model.getUserURL());

		return soapModel;
	}

	public static AkismetDataSoap[] toSoapModels(AkismetData[] models) {
		AkismetDataSoap[] soapModels = new AkismetDataSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AkismetDataSoap[][] toSoapModels(AkismetData[][] models) {
		AkismetDataSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AkismetDataSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AkismetDataSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AkismetDataSoap[] toSoapModels(List<AkismetData> models) {
		List<AkismetDataSoap> soapModels = new ArrayList<AkismetDataSoap>(models.size());

		for (AkismetData model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AkismetDataSoap[soapModels.size()]);
	}

	public AkismetDataSoap() {
	}

	public long getPrimaryKey() {
		return _akismetDataId;
	}

	public void setPrimaryKey(long pk) {
		setAkismetDataId(pk);
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

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
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

	private long _akismetDataId;
	private Date _modifiedDate;
	private long _classNameId;
	private long _classPK;
	private String _type;
	private String _permalink;
	private String _referrer;
	private String _userAgent;
	private String _userIP;
	private String _userURL;
}