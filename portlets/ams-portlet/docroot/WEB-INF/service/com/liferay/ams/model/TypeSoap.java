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

package com.liferay.ams.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class TypeSoap implements Serializable {
	public static TypeSoap toSoapModel(Type model) {
		TypeSoap soapModel = new TypeSoap();

		soapModel.setTypeId(model.getTypeId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setName(model.getName());

		return soapModel;
	}

	public static TypeSoap[] toSoapModels(Type[] models) {
		TypeSoap[] soapModels = new TypeSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TypeSoap[][] toSoapModels(Type[][] models) {
		TypeSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TypeSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TypeSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TypeSoap[] toSoapModels(List<Type> models) {
		List<TypeSoap> soapModels = new ArrayList<TypeSoap>(models.size());

		for (Type model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TypeSoap[soapModels.size()]);
	}

	public TypeSoap() {
	}

	public long getPrimaryKey() {
		return _typeId;
	}

	public void setPrimaryKey(long pk) {
		setTypeId(pk);
	}

	public long getTypeId() {
		return _typeId;
	}

	public void setTypeId(long typeId) {
		_typeId = typeId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	private long _typeId;
	private long _groupId;
	private String _name;
}