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

package com.liferay.socialnetworking.model;

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
public class MeetupsEntrySoap implements Serializable {
	public static MeetupsEntrySoap toSoapModel(MeetupsEntry model) {
		MeetupsEntrySoap soapModel = new MeetupsEntrySoap();

		soapModel.setMeetupsEntryId(model.getMeetupsEntryId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTitle(model.getTitle());
		soapModel.setDescription(model.getDescription());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setEndDate(model.getEndDate());
		soapModel.setTotalAttendees(model.getTotalAttendees());
		soapModel.setMaxAttendees(model.getMaxAttendees());
		soapModel.setPrice(model.getPrice());
		soapModel.setThumbnailId(model.getThumbnailId());

		return soapModel;
	}

	public static MeetupsEntrySoap[] toSoapModels(MeetupsEntry[] models) {
		MeetupsEntrySoap[] soapModels = new MeetupsEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MeetupsEntrySoap[][] toSoapModels(MeetupsEntry[][] models) {
		MeetupsEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new MeetupsEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new MeetupsEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MeetupsEntrySoap[] toSoapModels(List<MeetupsEntry> models) {
		List<MeetupsEntrySoap> soapModels = new ArrayList<MeetupsEntrySoap>(models.size());

		for (MeetupsEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MeetupsEntrySoap[soapModels.size()]);
	}

	public MeetupsEntrySoap() {
	}

	public long getPrimaryKey() {
		return _meetupsEntryId;
	}

	public void setPrimaryKey(long pk) {
		setMeetupsEntryId(pk);
	}

	public long getMeetupsEntryId() {
		return _meetupsEntryId;
	}

	public void setMeetupsEntryId(long meetupsEntryId) {
		_meetupsEntryId = meetupsEntryId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public int getTotalAttendees() {
		return _totalAttendees;
	}

	public void setTotalAttendees(int totalAttendees) {
		_totalAttendees = totalAttendees;
	}

	public int getMaxAttendees() {
		return _maxAttendees;
	}

	public void setMaxAttendees(int maxAttendees) {
		_maxAttendees = maxAttendees;
	}

	public double getPrice() {
		return _price;
	}

	public void setPrice(double price) {
		_price = price;
	}

	public long getThumbnailId() {
		return _thumbnailId;
	}

	public void setThumbnailId(long thumbnailId) {
		_thumbnailId = thumbnailId;
	}

	private long _meetupsEntryId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _title;
	private String _description;
	private Date _startDate;
	private Date _endDate;
	private int _totalAttendees;
	private int _maxAttendees;
	private double _price;
	private long _thumbnailId;
}