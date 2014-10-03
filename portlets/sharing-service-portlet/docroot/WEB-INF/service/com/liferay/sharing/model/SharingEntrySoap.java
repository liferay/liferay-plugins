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

package com.liferay.sharing.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.sharing.service.persistence.SharingEntryPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.sharing.service.http.SharingEntryServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.sharing.service.http.SharingEntryServiceSoap
 * @generated
 */
@ProviderType
public class SharingEntrySoap implements Serializable {
	public static SharingEntrySoap toSoapModel(SharingEntry model) {
		SharingEntrySoap soapModel = new SharingEntrySoap();

		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setSharingClassNameId(model.getSharingClassNameId());
		soapModel.setSharingClassPK(model.getSharingClassPK());

		return soapModel;
	}

	public static SharingEntrySoap[] toSoapModels(SharingEntry[] models) {
		SharingEntrySoap[] soapModels = new SharingEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SharingEntrySoap[][] toSoapModels(SharingEntry[][] models) {
		SharingEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SharingEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new SharingEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SharingEntrySoap[] toSoapModels(List<SharingEntry> models) {
		List<SharingEntrySoap> soapModels = new ArrayList<SharingEntrySoap>(models.size());

		for (SharingEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SharingEntrySoap[soapModels.size()]);
	}

	public SharingEntrySoap() {
	}

	public SharingEntryPK getPrimaryKey() {
		return new SharingEntryPK(_classNameId, _classPK, _sharingClassNameId,
			_sharingClassPK);
	}

	public void setPrimaryKey(SharingEntryPK pk) {
		setClassNameId(pk.classNameId);
		setClassPK(pk.classPK);
		setSharingClassNameId(pk.sharingClassNameId);
		setSharingClassPK(pk.sharingClassPK);
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

	public long getSharingClassNameId() {
		return _sharingClassNameId;
	}

	public void setSharingClassNameId(long sharingClassNameId) {
		_sharingClassNameId = sharingClassNameId;
	}

	public long getSharingClassPK() {
		return _sharingClassPK;
	}

	public void setSharingClassPK(long sharingClassPK) {
		_sharingClassPK = sharingClassPK;
	}

	private long _classNameId;
	private long _classPK;
	private long _sharingClassNameId;
	private long _sharingClassPK;
}