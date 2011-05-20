/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.hr.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author    Wesley Gong
 * @generated
 */
public class HRExpenseSoap implements Serializable {
	public static HRExpenseSoap toSoapModel(HRExpense model) {
		HRExpenseSoap soapModel = new HRExpenseSoap();

		soapModel.setHrExpenseId(model.getHrExpenseId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setHrExpenseAccountId(model.getHrExpenseAccountId());
		soapModel.setHrExpenseTypeId(model.getHrExpenseTypeId());
		soapModel.setHrUserId(model.getHrUserId());
		soapModel.setExpenseDate(model.getExpenseDate());
		soapModel.setExpenseAmount(model.getExpenseAmount());
		soapModel.setExpenseHRExpenseCurrencyId(model.getExpenseHRExpenseCurrencyId());
		soapModel.setReimbursementAmount(model.getReimbursementAmount());
		soapModel.setReimbursementHRExpenseCurrencyId(model.getReimbursementHRExpenseCurrencyId());
		soapModel.setStatus(model.getStatus());
		soapModel.setStatusByUserId(model.getStatusByUserId());
		soapModel.setStatusByUserName(model.getStatusByUserName());
		soapModel.setStatusDate(model.getStatusDate());

		return soapModel;
	}

	public static HRExpenseSoap[] toSoapModels(HRExpense[] models) {
		HRExpenseSoap[] soapModels = new HRExpenseSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HRExpenseSoap[][] toSoapModels(HRExpense[][] models) {
		HRExpenseSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HRExpenseSoap[models.length][models[0].length];
		}
		else {
			soapModels = new HRExpenseSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HRExpenseSoap[] toSoapModels(List<HRExpense> models) {
		List<HRExpenseSoap> soapModels = new ArrayList<HRExpenseSoap>(models.size());

		for (HRExpense model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HRExpenseSoap[soapModels.size()]);
	}

	public HRExpenseSoap() {
	}

	public long getPrimaryKey() {
		return _hrExpenseId;
	}

	public void setPrimaryKey(long pk) {
		setHrExpenseId(pk);
	}

	public long getHrExpenseId() {
		return _hrExpenseId;
	}

	public void setHrExpenseId(long hrExpenseId) {
		_hrExpenseId = hrExpenseId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
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

	public long getHrExpenseAccountId() {
		return _hrExpenseAccountId;
	}

	public void setHrExpenseAccountId(long hrExpenseAccountId) {
		_hrExpenseAccountId = hrExpenseAccountId;
	}

	public long getHrExpenseTypeId() {
		return _hrExpenseTypeId;
	}

	public void setHrExpenseTypeId(long hrExpenseTypeId) {
		_hrExpenseTypeId = hrExpenseTypeId;
	}

	public long getHrUserId() {
		return _hrUserId;
	}

	public void setHrUserId(long hrUserId) {
		_hrUserId = hrUserId;
	}

	public Date getExpenseDate() {
		return _expenseDate;
	}

	public void setExpenseDate(Date expenseDate) {
		_expenseDate = expenseDate;
	}

	public double getExpenseAmount() {
		return _expenseAmount;
	}

	public void setExpenseAmount(double expenseAmount) {
		_expenseAmount = expenseAmount;
	}

	public long getExpenseHRExpenseCurrencyId() {
		return _expenseHRExpenseCurrencyId;
	}

	public void setExpenseHRExpenseCurrencyId(long expenseHRExpenseCurrencyId) {
		_expenseHRExpenseCurrencyId = expenseHRExpenseCurrencyId;
	}

	public double getReimbursementAmount() {
		return _reimbursementAmount;
	}

	public void setReimbursementAmount(double reimbursementAmount) {
		_reimbursementAmount = reimbursementAmount;
	}

	public long getReimbursementHRExpenseCurrencyId() {
		return _reimbursementHRExpenseCurrencyId;
	}

	public void setReimbursementHRExpenseCurrencyId(
		long reimbursementHRExpenseCurrencyId) {
		_reimbursementHRExpenseCurrencyId = reimbursementHRExpenseCurrencyId;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public long getStatusByUserId() {
		return _statusByUserId;
	}

	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;
	}

	public String getStatusByUserName() {
		return _statusByUserName;
	}

	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;
	}

	public Date getStatusDate() {
		return _statusDate;
	}

	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

	private long _hrExpenseId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _hrExpenseAccountId;
	private long _hrExpenseTypeId;
	private long _hrUserId;
	private Date _expenseDate;
	private double _expenseAmount;
	private long _expenseHRExpenseCurrencyId;
	private double _reimbursementAmount;
	private long _reimbursementHRExpenseCurrencyId;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
}