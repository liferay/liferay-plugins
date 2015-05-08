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

package com.liferay.testtransaction.model;

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
public class BarSoap implements Serializable {
	public static BarSoap toSoapModel(Bar model) {
		BarSoap soapModel = new BarSoap();

		soapModel.setBarId(model.getBarId());
		soapModel.setText(model.getText());

		return soapModel;
	}

	public static BarSoap[] toSoapModels(Bar[] models) {
		BarSoap[] soapModels = new BarSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static BarSoap[][] toSoapModels(Bar[][] models) {
		BarSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new BarSoap[models.length][models[0].length];
		}
		else {
			soapModels = new BarSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static BarSoap[] toSoapModels(List<Bar> models) {
		List<BarSoap> soapModels = new ArrayList<BarSoap>(models.size());

		for (Bar model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new BarSoap[soapModels.size()]);
	}

	public BarSoap() {
	}

	public long getPrimaryKey() {
		return _barId;
	}

	public void setPrimaryKey(long pk) {
		setBarId(pk);
	}

	public long getBarId() {
		return _barId;
	}

	public void setBarId(long barId) {
		_barId = barId;
	}

	public String getText() {
		return _text;
	}

	public void setText(String text) {
		_text = text;
	}

	private long _barId;
	private String _text;
}