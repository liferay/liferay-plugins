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
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author    Brian Wing Shun Chan
 * @generated
 */
public class SVNRevisionSoap implements Serializable {
	public static SVNRevisionSoap toSoapModel(SVNRevision model) {
		SVNRevisionSoap soapModel = new SVNRevisionSoap();

		soapModel.setSvnRevisionId(model.getSvnRevisionId());
		soapModel.setSvnUserId(model.getSvnUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setSvnRepositoryId(model.getSvnRepositoryId());
		soapModel.setRevisionNumber(model.getRevisionNumber());
		soapModel.setComments(model.getComments());

		return soapModel;
	}

	public static SVNRevisionSoap[] toSoapModels(SVNRevision[] models) {
		SVNRevisionSoap[] soapModels = new SVNRevisionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SVNRevisionSoap[][] toSoapModels(SVNRevision[][] models) {
		SVNRevisionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SVNRevisionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SVNRevisionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SVNRevisionSoap[] toSoapModels(List<SVNRevision> models) {
		List<SVNRevisionSoap> soapModels = new ArrayList<SVNRevisionSoap>(models.size());

		for (SVNRevision model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SVNRevisionSoap[soapModels.size()]);
	}

	public SVNRevisionSoap() {
	}

	public long getPrimaryKey() {
		return _svnRevisionId;
	}

	public void setPrimaryKey(long pk) {
		setSvnRevisionId(pk);
	}

	public long getSvnRevisionId() {
		return _svnRevisionId;
	}

	public void setSvnRevisionId(long svnRevisionId) {
		_svnRevisionId = svnRevisionId;
	}

	public String getSvnUserId() {
		return _svnUserId;
	}

	public void setSvnUserId(String svnUserId) {
		_svnUserId = svnUserId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public long getSvnRepositoryId() {
		return _svnRepositoryId;
	}

	public void setSvnRepositoryId(long svnRepositoryId) {
		_svnRepositoryId = svnRepositoryId;
	}

	public long getRevisionNumber() {
		return _revisionNumber;
	}

	public void setRevisionNumber(long revisionNumber) {
		_revisionNumber = revisionNumber;
	}

	public String getComments() {
		return _comments;
	}

	public void setComments(String comments) {
		_comments = comments;
	}

	private long _svnRevisionId;
	private String _svnUserId;
	private Date _createDate;
	private long _svnRepositoryId;
	private long _revisionNumber;
	private String _comments;
}