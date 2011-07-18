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

import com.liferay.hr.service.HRExpenseLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;

/**
 * @author Wesley Gong
 */
public class HRExpenseClp extends BaseModelImpl<HRExpense> implements HRExpense {
	public HRExpenseClp() {
	}

	public Class<?> getModelClass() {
		return HRExpense.class;
	}

	public String getModelClassName() {
		return HRExpense.class.getName();
	}

	public long getPrimaryKey() {
		return _hrExpenseId;
	}

	public void setPrimaryKey(long primaryKey) {
		setHrExpenseId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_hrExpenseId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
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

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
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

	public String getHrUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getHrUserId(), "uuid", _hrUserUuid);
	}

	public void setHrUserUuid(String hrUserUuid) {
		_hrUserUuid = hrUserUuid;
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

	public String getStatusByUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getStatusByUserId(), "uuid",
			_statusByUserUuid);
	}

	public void setStatusByUserUuid(String statusByUserUuid) {
		_statusByUserUuid = statusByUserUuid;
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

	/**
	 * @deprecated {@link #isApproved}
	 */
	public boolean getApproved() {
		return isApproved();
	}

	public boolean isApproved() {
		if (getStatus() == WorkflowConstants.STATUS_APPROVED) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isDraft() {
		if (getStatus() == WorkflowConstants.STATUS_DRAFT) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isExpired() {
		if (getStatus() == WorkflowConstants.STATUS_EXPIRED) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isPending() {
		if (getStatus() == WorkflowConstants.STATUS_PENDING) {
			return true;
		}
		else {
			return false;
		}
	}

	public void persist() throws SystemException {
		HRExpenseLocalServiceUtil.updateHRExpense(this);
	}

	@Override
	public HRExpense toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			return (HRExpense)Proxy.newProxyInstance(HRExpense.class.getClassLoader(),
				new Class[] { HRExpense.class }, new AutoEscapeBeanHandler(this));
		}
	}

	@Override
	public Object clone() {
		HRExpenseClp clone = new HRExpenseClp();

		clone.setHrExpenseId(getHrExpenseId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setHrExpenseAccountId(getHrExpenseAccountId());
		clone.setHrExpenseTypeId(getHrExpenseTypeId());
		clone.setHrUserId(getHrUserId());
		clone.setExpenseDate(getExpenseDate());
		clone.setExpenseAmount(getExpenseAmount());
		clone.setExpenseHRExpenseCurrencyId(getExpenseHRExpenseCurrencyId());
		clone.setReimbursementAmount(getReimbursementAmount());
		clone.setReimbursementHRExpenseCurrencyId(getReimbursementHRExpenseCurrencyId());
		clone.setStatus(getStatus());
		clone.setStatusByUserId(getStatusByUserId());
		clone.setStatusByUserName(getStatusByUserName());
		clone.setStatusDate(getStatusDate());

		return clone;
	}

	public int compareTo(HRExpense hrExpense) {
		long primaryKey = hrExpense.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		HRExpenseClp hrExpense = null;

		try {
			hrExpense = (HRExpenseClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = hrExpense.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(39);

		sb.append("{hrExpenseId=");
		sb.append(getHrExpenseId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", hrExpenseAccountId=");
		sb.append(getHrExpenseAccountId());
		sb.append(", hrExpenseTypeId=");
		sb.append(getHrExpenseTypeId());
		sb.append(", hrUserId=");
		sb.append(getHrUserId());
		sb.append(", expenseDate=");
		sb.append(getExpenseDate());
		sb.append(", expenseAmount=");
		sb.append(getExpenseAmount());
		sb.append(", expenseHRExpenseCurrencyId=");
		sb.append(getExpenseHRExpenseCurrencyId());
		sb.append(", reimbursementAmount=");
		sb.append(getReimbursementAmount());
		sb.append(", reimbursementHRExpenseCurrencyId=");
		sb.append(getReimbursementHRExpenseCurrencyId());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append(", statusByUserId=");
		sb.append(getStatusByUserId());
		sb.append(", statusByUserName=");
		sb.append(getStatusByUserName());
		sb.append(", statusDate=");
		sb.append(getStatusDate());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(61);

		sb.append("<model><model-name>");
		sb.append("com.liferay.hr.model.HRExpense");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>hrExpenseId</column-name><column-value><![CDATA[");
		sb.append(getHrExpenseId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hrExpenseAccountId</column-name><column-value><![CDATA[");
		sb.append(getHrExpenseAccountId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hrExpenseTypeId</column-name><column-value><![CDATA[");
		sb.append(getHrExpenseTypeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hrUserId</column-name><column-value><![CDATA[");
		sb.append(getHrUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>expenseDate</column-name><column-value><![CDATA[");
		sb.append(getExpenseDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>expenseAmount</column-name><column-value><![CDATA[");
		sb.append(getExpenseAmount());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>expenseHRExpenseCurrencyId</column-name><column-value><![CDATA[");
		sb.append(getExpenseHRExpenseCurrencyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>reimbursementAmount</column-name><column-value><![CDATA[");
		sb.append(getReimbursementAmount());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>reimbursementHRExpenseCurrencyId</column-name><column-value><![CDATA[");
		sb.append(getReimbursementHRExpenseCurrencyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusByUserId</column-name><column-value><![CDATA[");
		sb.append(getStatusByUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusByUserName</column-name><column-value><![CDATA[");
		sb.append(getStatusByUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusDate</column-name><column-value><![CDATA[");
		sb.append(getStatusDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _hrExpenseId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _hrExpenseAccountId;
	private long _hrExpenseTypeId;
	private long _hrUserId;
	private String _hrUserUuid;
	private Date _expenseDate;
	private double _expenseAmount;
	private long _expenseHRExpenseCurrencyId;
	private double _reimbursementAmount;
	private long _reimbursementHRExpenseCurrencyId;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserUuid;
	private String _statusByUserName;
	private Date _statusDate;
}