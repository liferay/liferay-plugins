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

package com.liferay.socialcoding.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author    Brian Wing Shun Chan
 * @generated
 */
public class JIRAChangeItemSoap implements Serializable {
	public static JIRAChangeItemSoap toSoapModel(JIRAChangeItem model) {
		JIRAChangeItemSoap soapModel = new JIRAChangeItemSoap();

		soapModel.setJiraChangeItemId(model.getJiraChangeItemId());
		soapModel.setJiraChangeGroupId(model.getJiraChangeGroupId());
		soapModel.setField(model.getField());
		soapModel.setOldValue(model.getOldValue());
		soapModel.setOldString(model.getOldString());
		soapModel.setNewValue(model.getNewValue());
		soapModel.setNewString(model.getNewString());

		return soapModel;
	}

	public static JIRAChangeItemSoap[] toSoapModels(JIRAChangeItem[] models) {
		JIRAChangeItemSoap[] soapModels = new JIRAChangeItemSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static JIRAChangeItemSoap[][] toSoapModels(JIRAChangeItem[][] models) {
		JIRAChangeItemSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new JIRAChangeItemSoap[models.length][models[0].length];
		}
		else {
			soapModels = new JIRAChangeItemSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static JIRAChangeItemSoap[] toSoapModels(List<JIRAChangeItem> models) {
		List<JIRAChangeItemSoap> soapModels = new ArrayList<JIRAChangeItemSoap>(models.size());

		for (JIRAChangeItem model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new JIRAChangeItemSoap[soapModels.size()]);
	}

	public JIRAChangeItemSoap() {
	}

	public long getPrimaryKey() {
		return _jiraChangeItemId;
	}

	public void setPrimaryKey(long pk) {
		setJiraChangeItemId(pk);
	}

	public long getJiraChangeItemId() {
		return _jiraChangeItemId;
	}

	public void setJiraChangeItemId(long jiraChangeItemId) {
		_jiraChangeItemId = jiraChangeItemId;
	}

	public long getJiraChangeGroupId() {
		return _jiraChangeGroupId;
	}

	public void setJiraChangeGroupId(long jiraChangeGroupId) {
		_jiraChangeGroupId = jiraChangeGroupId;
	}

	public String getField() {
		return _field;
	}

	public void setField(String field) {
		_field = field;
	}

	public String getOldValue() {
		return _oldValue;
	}

	public void setOldValue(String oldValue) {
		_oldValue = oldValue;
	}

	public String getOldString() {
		return _oldString;
	}

	public void setOldString(String oldString) {
		_oldString = oldString;
	}

	public String getNewValue() {
		return _newValue;
	}

	public void setNewValue(String newValue) {
		_newValue = newValue;
	}

	public String getNewString() {
		return _newString;
	}

	public void setNewString(String newString) {
		_newString = newString;
	}

	private long _jiraChangeItemId;
	private long _jiraChangeGroupId;
	private String _field;
	private String _oldValue;
	private String _oldString;
	private String _newValue;
	private String _newString;
}