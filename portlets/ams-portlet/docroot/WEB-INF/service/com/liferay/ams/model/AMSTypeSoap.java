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

package com.liferay.ams.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="AMSTypeSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * {@link com.liferay.ams.service.http.AMSTypeServiceSoap}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.ams.service.http.AMSTypeServiceSoap
 * @generated
 */
public class AMSTypeSoap implements Serializable {
	public static AMSTypeSoap toSoapModel(AMSType model) {
		AMSTypeSoap soapModel = new AMSTypeSoap();

		soapModel.setAmsTypeId(model.getAmsTypeId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setName(model.getName());

		return soapModel;
	}

	public static AMSTypeSoap[] toSoapModels(AMSType[] models) {
		AMSTypeSoap[] soapModels = new AMSTypeSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AMSTypeSoap[][] toSoapModels(AMSType[][] models) {
		AMSTypeSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AMSTypeSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AMSTypeSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AMSTypeSoap[] toSoapModels(List<AMSType> models) {
		List<AMSTypeSoap> soapModels = new ArrayList<AMSTypeSoap>(models.size());

		for (AMSType model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AMSTypeSoap[soapModels.size()]);
	}

	public AMSTypeSoap() {
	}

	public long getPrimaryKey() {
		return _amsTypeId;
	}

	public void setPrimaryKey(long pk) {
		setAmsTypeId(pk);
	}

	public long getAmsTypeId() {
		return _amsTypeId;
	}

	public void setAmsTypeId(long amsTypeId) {
		_amsTypeId = amsTypeId;
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

	private long _amsTypeId;
	private long _groupId;
	private String _name;
}