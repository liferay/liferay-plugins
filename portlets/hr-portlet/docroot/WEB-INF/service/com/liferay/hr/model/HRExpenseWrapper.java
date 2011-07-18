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

/**
 * <p>
 * This class is a wrapper for {@link HRExpense}.
 * </p>
 *
 * @author    Wesley Gong
 * @see       HRExpense
 * @generated
 */
public class HRExpenseWrapper implements HRExpense {
	public HRExpenseWrapper(HRExpense hrExpense) {
		_hrExpense = hrExpense;
	}

	public Class<?> getModelClass() {
		return HRExpense.class;
	}

	public String getModelClassName() {
		return HRExpense.class.getName();
	}

	/**
	* Returns the primary key of this h r expense.
	*
	* @return the primary key of this h r expense
	*/
	public long getPrimaryKey() {
		return _hrExpense.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r expense.
	*
	* @param primaryKey the primary key of this h r expense
	*/
	public void setPrimaryKey(long primaryKey) {
		_hrExpense.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the hr expense ID of this h r expense.
	*
	* @return the hr expense ID of this h r expense
	*/
	public long getHrExpenseId() {
		return _hrExpense.getHrExpenseId();
	}

	/**
	* Sets the hr expense ID of this h r expense.
	*
	* @param hrExpenseId the hr expense ID of this h r expense
	*/
	public void setHrExpenseId(long hrExpenseId) {
		_hrExpense.setHrExpenseId(hrExpenseId);
	}

	/**
	* Returns the group ID of this h r expense.
	*
	* @return the group ID of this h r expense
	*/
	public long getGroupId() {
		return _hrExpense.getGroupId();
	}

	/**
	* Sets the group ID of this h r expense.
	*
	* @param groupId the group ID of this h r expense
	*/
	public void setGroupId(long groupId) {
		_hrExpense.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this h r expense.
	*
	* @return the company ID of this h r expense
	*/
	public long getCompanyId() {
		return _hrExpense.getCompanyId();
	}

	/**
	* Sets the company ID of this h r expense.
	*
	* @param companyId the company ID of this h r expense
	*/
	public void setCompanyId(long companyId) {
		_hrExpense.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this h r expense.
	*
	* @return the user ID of this h r expense
	*/
	public long getUserId() {
		return _hrExpense.getUserId();
	}

	/**
	* Sets the user ID of this h r expense.
	*
	* @param userId the user ID of this h r expense
	*/
	public void setUserId(long userId) {
		_hrExpense.setUserId(userId);
	}

	/**
	* Returns the user uuid of this h r expense.
	*
	* @return the user uuid of this h r expense
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrExpense.getUserUuid();
	}

	/**
	* Sets the user uuid of this h r expense.
	*
	* @param userUuid the user uuid of this h r expense
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_hrExpense.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this h r expense.
	*
	* @return the user name of this h r expense
	*/
	public java.lang.String getUserName() {
		return _hrExpense.getUserName();
	}

	/**
	* Sets the user name of this h r expense.
	*
	* @param userName the user name of this h r expense
	*/
	public void setUserName(java.lang.String userName) {
		_hrExpense.setUserName(userName);
	}

	/**
	* Returns the create date of this h r expense.
	*
	* @return the create date of this h r expense
	*/
	public java.util.Date getCreateDate() {
		return _hrExpense.getCreateDate();
	}

	/**
	* Sets the create date of this h r expense.
	*
	* @param createDate the create date of this h r expense
	*/
	public void setCreateDate(java.util.Date createDate) {
		_hrExpense.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this h r expense.
	*
	* @return the modified date of this h r expense
	*/
	public java.util.Date getModifiedDate() {
		return _hrExpense.getModifiedDate();
	}

	/**
	* Sets the modified date of this h r expense.
	*
	* @param modifiedDate the modified date of this h r expense
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_hrExpense.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the hr expense account ID of this h r expense.
	*
	* @return the hr expense account ID of this h r expense
	*/
	public long getHrExpenseAccountId() {
		return _hrExpense.getHrExpenseAccountId();
	}

	/**
	* Sets the hr expense account ID of this h r expense.
	*
	* @param hrExpenseAccountId the hr expense account ID of this h r expense
	*/
	public void setHrExpenseAccountId(long hrExpenseAccountId) {
		_hrExpense.setHrExpenseAccountId(hrExpenseAccountId);
	}

	/**
	* Returns the hr expense type ID of this h r expense.
	*
	* @return the hr expense type ID of this h r expense
	*/
	public long getHrExpenseTypeId() {
		return _hrExpense.getHrExpenseTypeId();
	}

	/**
	* Sets the hr expense type ID of this h r expense.
	*
	* @param hrExpenseTypeId the hr expense type ID of this h r expense
	*/
	public void setHrExpenseTypeId(long hrExpenseTypeId) {
		_hrExpense.setHrExpenseTypeId(hrExpenseTypeId);
	}

	/**
	* Returns the hr user ID of this h r expense.
	*
	* @return the hr user ID of this h r expense
	*/
	public long getHrUserId() {
		return _hrExpense.getHrUserId();
	}

	/**
	* Sets the hr user ID of this h r expense.
	*
	* @param hrUserId the hr user ID of this h r expense
	*/
	public void setHrUserId(long hrUserId) {
		_hrExpense.setHrUserId(hrUserId);
	}

	/**
	* Returns the hr user uuid of this h r expense.
	*
	* @return the hr user uuid of this h r expense
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getHrUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrExpense.getHrUserUuid();
	}

	/**
	* Sets the hr user uuid of this h r expense.
	*
	* @param hrUserUuid the hr user uuid of this h r expense
	*/
	public void setHrUserUuid(java.lang.String hrUserUuid) {
		_hrExpense.setHrUserUuid(hrUserUuid);
	}

	/**
	* Returns the expense date of this h r expense.
	*
	* @return the expense date of this h r expense
	*/
	public java.util.Date getExpenseDate() {
		return _hrExpense.getExpenseDate();
	}

	/**
	* Sets the expense date of this h r expense.
	*
	* @param expenseDate the expense date of this h r expense
	*/
	public void setExpenseDate(java.util.Date expenseDate) {
		_hrExpense.setExpenseDate(expenseDate);
	}

	/**
	* Returns the expense amount of this h r expense.
	*
	* @return the expense amount of this h r expense
	*/
	public double getExpenseAmount() {
		return _hrExpense.getExpenseAmount();
	}

	/**
	* Sets the expense amount of this h r expense.
	*
	* @param expenseAmount the expense amount of this h r expense
	*/
	public void setExpenseAmount(double expenseAmount) {
		_hrExpense.setExpenseAmount(expenseAmount);
	}

	/**
	* Returns the expense h r expense currency ID of this h r expense.
	*
	* @return the expense h r expense currency ID of this h r expense
	*/
	public long getExpenseHRExpenseCurrencyId() {
		return _hrExpense.getExpenseHRExpenseCurrencyId();
	}

	/**
	* Sets the expense h r expense currency ID of this h r expense.
	*
	* @param expenseHRExpenseCurrencyId the expense h r expense currency ID of this h r expense
	*/
	public void setExpenseHRExpenseCurrencyId(long expenseHRExpenseCurrencyId) {
		_hrExpense.setExpenseHRExpenseCurrencyId(expenseHRExpenseCurrencyId);
	}

	/**
	* Returns the reimbursement amount of this h r expense.
	*
	* @return the reimbursement amount of this h r expense
	*/
	public double getReimbursementAmount() {
		return _hrExpense.getReimbursementAmount();
	}

	/**
	* Sets the reimbursement amount of this h r expense.
	*
	* @param reimbursementAmount the reimbursement amount of this h r expense
	*/
	public void setReimbursementAmount(double reimbursementAmount) {
		_hrExpense.setReimbursementAmount(reimbursementAmount);
	}

	/**
	* Returns the reimbursement h r expense currency ID of this h r expense.
	*
	* @return the reimbursement h r expense currency ID of this h r expense
	*/
	public long getReimbursementHRExpenseCurrencyId() {
		return _hrExpense.getReimbursementHRExpenseCurrencyId();
	}

	/**
	* Sets the reimbursement h r expense currency ID of this h r expense.
	*
	* @param reimbursementHRExpenseCurrencyId the reimbursement h r expense currency ID of this h r expense
	*/
	public void setReimbursementHRExpenseCurrencyId(
		long reimbursementHRExpenseCurrencyId) {
		_hrExpense.setReimbursementHRExpenseCurrencyId(reimbursementHRExpenseCurrencyId);
	}

	/**
	* Returns the status of this h r expense.
	*
	* @return the status of this h r expense
	*/
	public int getStatus() {
		return _hrExpense.getStatus();
	}

	/**
	* Sets the status of this h r expense.
	*
	* @param status the status of this h r expense
	*/
	public void setStatus(int status) {
		_hrExpense.setStatus(status);
	}

	/**
	* Returns the status by user ID of this h r expense.
	*
	* @return the status by user ID of this h r expense
	*/
	public long getStatusByUserId() {
		return _hrExpense.getStatusByUserId();
	}

	/**
	* Sets the status by user ID of this h r expense.
	*
	* @param statusByUserId the status by user ID of this h r expense
	*/
	public void setStatusByUserId(long statusByUserId) {
		_hrExpense.setStatusByUserId(statusByUserId);
	}

	/**
	* Returns the status by user uuid of this h r expense.
	*
	* @return the status by user uuid of this h r expense
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getStatusByUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrExpense.getStatusByUserUuid();
	}

	/**
	* Sets the status by user uuid of this h r expense.
	*
	* @param statusByUserUuid the status by user uuid of this h r expense
	*/
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_hrExpense.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Returns the status by user name of this h r expense.
	*
	* @return the status by user name of this h r expense
	*/
	public java.lang.String getStatusByUserName() {
		return _hrExpense.getStatusByUserName();
	}

	/**
	* Sets the status by user name of this h r expense.
	*
	* @param statusByUserName the status by user name of this h r expense
	*/
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_hrExpense.setStatusByUserName(statusByUserName);
	}

	/**
	* Returns the status date of this h r expense.
	*
	* @return the status date of this h r expense
	*/
	public java.util.Date getStatusDate() {
		return _hrExpense.getStatusDate();
	}

	/**
	* Sets the status date of this h r expense.
	*
	* @param statusDate the status date of this h r expense
	*/
	public void setStatusDate(java.util.Date statusDate) {
		_hrExpense.setStatusDate(statusDate);
	}

	/**
	* @deprecated Renamed to {@link #isApproved()}
	*/
	public boolean getApproved() {
		return _hrExpense.getApproved();
	}

	/**
	* Returns <code>true</code> if this h r expense is approved.
	*
	* @return <code>true</code> if this h r expense is approved; <code>false</code> otherwise
	*/
	public boolean isApproved() {
		return _hrExpense.isApproved();
	}

	/**
	* Returns <code>true</code> if this h r expense is a draft.
	*
	* @return <code>true</code> if this h r expense is a draft; <code>false</code> otherwise
	*/
	public boolean isDraft() {
		return _hrExpense.isDraft();
	}

	/**
	* Returns <code>true</code> if this h r expense is expired.
	*
	* @return <code>true</code> if this h r expense is expired; <code>false</code> otherwise
	*/
	public boolean isExpired() {
		return _hrExpense.isExpired();
	}

	/**
	* Returns <code>true</code> if this h r expense is pending.
	*
	* @return <code>true</code> if this h r expense is pending; <code>false</code> otherwise
	*/
	public boolean isPending() {
		return _hrExpense.isPending();
	}

	public boolean isNew() {
		return _hrExpense.isNew();
	}

	public void setNew(boolean n) {
		_hrExpense.setNew(n);
	}

	public boolean isCachedModel() {
		return _hrExpense.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_hrExpense.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _hrExpense.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_hrExpense.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _hrExpense.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_hrExpense.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrExpense.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrExpense.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new HRExpenseWrapper((HRExpense)_hrExpense.clone());
	}

	public int compareTo(com.liferay.hr.model.HRExpense hrExpense) {
		return _hrExpense.compareTo(hrExpense);
	}

	@Override
	public int hashCode() {
		return _hrExpense.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.hr.model.HRExpense> toCacheModel() {
		return _hrExpense.toCacheModel();
	}

	public com.liferay.hr.model.HRExpense toEscapedModel() {
		return new HRExpenseWrapper(_hrExpense.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _hrExpense.toString();
	}

	public java.lang.String toXmlString() {
		return _hrExpense.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_hrExpense.persist();
	}

	public HRExpense getWrappedHRExpense() {
		return _hrExpense;
	}

	public void resetOriginalValues() {
		_hrExpense.resetOriginalValues();
	}

	private HRExpense _hrExpense;
}