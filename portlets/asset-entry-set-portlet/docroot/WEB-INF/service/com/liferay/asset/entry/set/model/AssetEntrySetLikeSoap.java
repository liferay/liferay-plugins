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

package com.liferay.asset.entry.set.model;

import com.liferay.asset.entry.set.service.persistence.AssetEntrySetLikePK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.asset.entry.set.service.http.AssetEntrySetLikeServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.asset.entry.set.service.http.AssetEntrySetLikeServiceSoap
 * @generated
 */
public class AssetEntrySetLikeSoap implements Serializable {
	public static AssetEntrySetLikeSoap toSoapModel(AssetEntrySetLike model) {
		AssetEntrySetLikeSoap soapModel = new AssetEntrySetLikeSoap();

		soapModel.setAssetEntrySetId(model.getAssetEntrySetId());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());

		return soapModel;
	}

	public static AssetEntrySetLikeSoap[] toSoapModels(
		AssetEntrySetLike[] models) {
		AssetEntrySetLikeSoap[] soapModels = new AssetEntrySetLikeSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AssetEntrySetLikeSoap[][] toSoapModels(
		AssetEntrySetLike[][] models) {
		AssetEntrySetLikeSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AssetEntrySetLikeSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AssetEntrySetLikeSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AssetEntrySetLikeSoap[] toSoapModels(
		List<AssetEntrySetLike> models) {
		List<AssetEntrySetLikeSoap> soapModels = new ArrayList<AssetEntrySetLikeSoap>(models.size());

		for (AssetEntrySetLike model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AssetEntrySetLikeSoap[soapModels.size()]);
	}

	public AssetEntrySetLikeSoap() {
	}

	public AssetEntrySetLikePK getPrimaryKey() {
		return new AssetEntrySetLikePK(_assetEntrySetId, _classNameId, _classPK);
	}

	public void setPrimaryKey(AssetEntrySetLikePK pk) {
		setAssetEntrySetId(pk.assetEntrySetId);
		setClassNameId(pk.classNameId);
		setClassPK(pk.classPK);
	}

	public long getAssetEntrySetId() {
		return _assetEntrySetId;
	}

	public void setAssetEntrySetId(long assetEntrySetId) {
		_assetEntrySetId = assetEntrySetId;
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

	private long _assetEntrySetId;
	private long _classNameId;
	private long _classPK;
}