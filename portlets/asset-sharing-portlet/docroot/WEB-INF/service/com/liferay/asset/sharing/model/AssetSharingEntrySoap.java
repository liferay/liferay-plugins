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

package com.liferay.asset.sharing.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.asset.sharing.service.persistence.AssetSharingEntryPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.asset.sharing.service.http.AssetSharingEntryServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.asset.sharing.service.http.AssetSharingEntryServiceSoap
 * @generated
 */
@ProviderType
public class AssetSharingEntrySoap implements Serializable {
	public static AssetSharingEntrySoap toSoapModel(AssetSharingEntry model) {
		AssetSharingEntrySoap soapModel = new AssetSharingEntrySoap();

		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setSharedToClassNameId(model.getSharedToClassNameId());
		soapModel.setSharedToClassPK(model.getSharedToClassPK());

		return soapModel;
	}

	public static AssetSharingEntrySoap[] toSoapModels(
		AssetSharingEntry[] models) {
		AssetSharingEntrySoap[] soapModels = new AssetSharingEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AssetSharingEntrySoap[][] toSoapModels(
		AssetSharingEntry[][] models) {
		AssetSharingEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AssetSharingEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new AssetSharingEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AssetSharingEntrySoap[] toSoapModels(
		List<AssetSharingEntry> models) {
		List<AssetSharingEntrySoap> soapModels = new ArrayList<AssetSharingEntrySoap>(models.size());

		for (AssetSharingEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AssetSharingEntrySoap[soapModels.size()]);
	}

	public AssetSharingEntrySoap() {
	}

	public AssetSharingEntryPK getPrimaryKey() {
		return new AssetSharingEntryPK(_classNameId, _classPK,
			_sharedToClassNameId, _sharedToClassPK);
	}

	public void setPrimaryKey(AssetSharingEntryPK pk) {
		setClassNameId(pk.classNameId);
		setClassPK(pk.classPK);
		setSharedToClassNameId(pk.sharedToClassNameId);
		setSharedToClassPK(pk.sharedToClassPK);
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

	public long getSharedToClassNameId() {
		return _sharedToClassNameId;
	}

	public void setSharedToClassNameId(long sharedToClassNameId) {
		_sharedToClassNameId = sharedToClassNameId;
	}

	public long getSharedToClassPK() {
		return _sharedToClassPK;
	}

	public void setSharedToClassPK(long sharedToClassPK) {
		_sharedToClassPK = sharedToClassPK;
	}

	private long _classNameId;
	private long _classPK;
	private long _sharedToClassNameId;
	private long _sharedToClassPK;
}