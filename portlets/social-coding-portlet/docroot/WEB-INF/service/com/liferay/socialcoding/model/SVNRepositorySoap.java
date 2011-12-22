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
public class SVNRepositorySoap implements Serializable {
	public static SVNRepositorySoap toSoapModel(SVNRepository model) {
		SVNRepositorySoap soapModel = new SVNRepositorySoap();

		soapModel.setSvnRepositoryId(model.getSvnRepositoryId());
		soapModel.setUrl(model.getUrl());
		soapModel.setRevisionNumber(model.getRevisionNumber());

		return soapModel;
	}

	public static SVNRepositorySoap[] toSoapModels(SVNRepository[] models) {
		SVNRepositorySoap[] soapModels = new SVNRepositorySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SVNRepositorySoap[][] toSoapModels(SVNRepository[][] models) {
		SVNRepositorySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SVNRepositorySoap[models.length][models[0].length];
		}
		else {
			soapModels = new SVNRepositorySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SVNRepositorySoap[] toSoapModels(List<SVNRepository> models) {
		List<SVNRepositorySoap> soapModels = new ArrayList<SVNRepositorySoap>(models.size());

		for (SVNRepository model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SVNRepositorySoap[soapModels.size()]);
	}

	public SVNRepositorySoap() {
	}

	public long getPrimaryKey() {
		return _svnRepositoryId;
	}

	public void setPrimaryKey(long pk) {
		setSvnRepositoryId(pk);
	}

	public long getSvnRepositoryId() {
		return _svnRepositoryId;
	}

	public void setSvnRepositoryId(long svnRepositoryId) {
		_svnRepositoryId = svnRepositoryId;
	}

	public String getUrl() {
		return _url;
	}

	public void setUrl(String url) {
		_url = url;
	}

	public long getRevisionNumber() {
		return _revisionNumber;
	}

	public void setRevisionNumber(long revisionNumber) {
		_revisionNumber = revisionNumber;
	}

	private long _svnRepositoryId;
	private String _url;
	private long _revisionNumber;
}