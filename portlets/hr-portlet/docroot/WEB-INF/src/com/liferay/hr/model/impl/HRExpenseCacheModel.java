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

package com.liferay.hr.model.impl;

import com.liferay.hr.model.HRExpense;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing HRExpense in entity cache.
 *
 * @author Wesley Gong
 * @see HRExpense
 * @generated
 */
public class HRExpenseCacheModel implements CacheModel<HRExpense> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(39);

		sb.append("{hrExpenseId=");
		sb.append(hrExpenseId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", hrExpenseAccountId=");
		sb.append(hrExpenseAccountId);
		sb.append(", hrExpenseTypeId=");
		sb.append(hrExpenseTypeId);
		sb.append(", hrUserId=");
		sb.append(hrUserId);
		sb.append(", expenseDate=");
		sb.append(expenseDate);
		sb.append(", expenseAmount=");
		sb.append(expenseAmount);
		sb.append(", expenseHRExpenseCurrencyId=");
		sb.append(expenseHRExpenseCurrencyId);
		sb.append(", reimbursementAmount=");
		sb.append(reimbursementAmount);
		sb.append(", reimbursementHRExpenseCurrencyId=");
		sb.append(reimbursementHRExpenseCurrencyId);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append("}");

		return sb.toString();
	}

	public HRExpense toEntityModel() {
		HRExpenseImpl hrExpenseImpl = new HRExpenseImpl();

		hrExpenseImpl.setHrExpenseId(hrExpenseId);
		hrExpenseImpl.setGroupId(groupId);
		hrExpenseImpl.setCompanyId(companyId);
		hrExpenseImpl.setUserId(userId);

		if (userName == null) {
			hrExpenseImpl.setUserName(StringPool.BLANK);
		}
		else {
			hrExpenseImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			hrExpenseImpl.setCreateDate(null);
		}
		else {
			hrExpenseImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			hrExpenseImpl.setModifiedDate(null);
		}
		else {
			hrExpenseImpl.setModifiedDate(new Date(modifiedDate));
		}

		hrExpenseImpl.setHrExpenseAccountId(hrExpenseAccountId);
		hrExpenseImpl.setHrExpenseTypeId(hrExpenseTypeId);
		hrExpenseImpl.setHrUserId(hrUserId);

		if (expenseDate == Long.MIN_VALUE) {
			hrExpenseImpl.setExpenseDate(null);
		}
		else {
			hrExpenseImpl.setExpenseDate(new Date(expenseDate));
		}

		hrExpenseImpl.setExpenseAmount(expenseAmount);
		hrExpenseImpl.setExpenseHRExpenseCurrencyId(expenseHRExpenseCurrencyId);
		hrExpenseImpl.setReimbursementAmount(reimbursementAmount);
		hrExpenseImpl.setReimbursementHRExpenseCurrencyId(reimbursementHRExpenseCurrencyId);
		hrExpenseImpl.setStatus(status);
		hrExpenseImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			hrExpenseImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			hrExpenseImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			hrExpenseImpl.setStatusDate(null);
		}
		else {
			hrExpenseImpl.setStatusDate(new Date(statusDate));
		}

		hrExpenseImpl.resetOriginalValues();

		return hrExpenseImpl;
	}

	public long hrExpenseId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long hrExpenseAccountId;
	public long hrExpenseTypeId;
	public long hrUserId;
	public long expenseDate;
	public double expenseAmount;
	public long expenseHRExpenseCurrencyId;
	public double reimbursementAmount;
	public long reimbursementHRExpenseCurrencyId;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
}