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

package com.liferay.socialcoding.model;

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
public class JIRAProjectSoap implements Serializable {
	public static JIRAProjectSoap toSoapModel(JIRAProject model) {
		JIRAProjectSoap soapModel = new JIRAProjectSoap();

		soapModel.setJiraProjectId(model.getJiraProjectId());
		soapModel.setKey(model.getKey());
		soapModel.setName(model.getName());

		return soapModel;
	}

	public static JIRAProjectSoap[] toSoapModels(JIRAProject[] models) {
		JIRAProjectSoap[] soapModels = new JIRAProjectSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static JIRAProjectSoap[][] toSoapModels(JIRAProject[][] models) {
		JIRAProjectSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new JIRAProjectSoap[models.length][models[0].length];
		}
		else {
			soapModels = new JIRAProjectSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static JIRAProjectSoap[] toSoapModels(List<JIRAProject> models) {
		List<JIRAProjectSoap> soapModels = new ArrayList<JIRAProjectSoap>(models.size());

		for (JIRAProject model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new JIRAProjectSoap[soapModels.size()]);
	}

	public JIRAProjectSoap() {
	}

	public long getPrimaryKey() {
		return _jiraProjectId;
	}

	public void setPrimaryKey(long pk) {
		setJiraProjectId(pk);
	}

	public long getJiraProjectId() {
		return _jiraProjectId;
	}

	public void setJiraProjectId(long jiraProjectId) {
		_jiraProjectId = jiraProjectId;
	}

	public String getKey() {
		return _key;
	}

	public void setKey(String key) {
		_key = key;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	private long _jiraProjectId;
	private String _key;
	private String _name;
}