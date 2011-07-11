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

import com.liferay.hr.model.HRTask;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing HRTask in entity cache.
 *
 * @author Wesley Gong
 * @see HRTask
 * @generated
 */
public class HRTaskCacheModel implements CacheModel<HRTask> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(55);

		sb.append("{hrTaskId=");
		sb.append(hrTaskId);
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
		sb.append(", hrBillabilityId=");
		sb.append(hrBillabilityId);
		sb.append(", hrProjectId=");
		sb.append(hrProjectId);
		sb.append(", hrTaskStatusId=");
		sb.append(hrTaskStatusId);
		sb.append(", parentHRTaskId=");
		sb.append(parentHRTaskId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", estimatedStartDate=");
		sb.append(estimatedStartDate);
		sb.append(", estimatedEndDate=");
		sb.append(estimatedEndDate);
		sb.append(", estimatedHours=");
		sb.append(estimatedHours);
		sb.append(", estimatedHoursCost=");
		sb.append(estimatedHoursCost);
		sb.append(", estimatedHoursCostCurrencyCode=");
		sb.append(estimatedHoursCostCurrencyCode);
		sb.append(", estimatedExpenses=");
		sb.append(estimatedExpenses);
		sb.append(", estimatedExpensesCurrencyCode=");
		sb.append(estimatedExpensesCurrencyCode);
		sb.append(", actualStartDate=");
		sb.append(actualStartDate);
		sb.append(", actualEndDate=");
		sb.append(actualEndDate);
		sb.append(", actualHours=");
		sb.append(actualHours);
		sb.append(", actualHoursCost=");
		sb.append(actualHoursCost);
		sb.append(", actualHoursCostCurrencyCode=");
		sb.append(actualHoursCostCurrencyCode);
		sb.append(", actualExpenses=");
		sb.append(actualExpenses);
		sb.append(", actualExpensesCurrencyCode=");
		sb.append(actualExpensesCurrencyCode);
		sb.append("}");

		return sb.toString();
	}

	public HRTask toEntityModel() {
		HRTaskImpl hrTaskImpl = new HRTaskImpl();

		hrTaskImpl.setHrTaskId(hrTaskId);
		hrTaskImpl.setGroupId(groupId);
		hrTaskImpl.setCompanyId(companyId);
		hrTaskImpl.setUserId(userId);

		if (userName == null) {
			hrTaskImpl.setUserName(StringPool.BLANK);
		}
		else {
			hrTaskImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			hrTaskImpl.setCreateDate(null);
		}
		else {
			hrTaskImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			hrTaskImpl.setModifiedDate(null);
		}
		else {
			hrTaskImpl.setModifiedDate(new Date(modifiedDate));
		}

		hrTaskImpl.setHrBillabilityId(hrBillabilityId);
		hrTaskImpl.setHrProjectId(hrProjectId);
		hrTaskImpl.setHrTaskStatusId(hrTaskStatusId);
		hrTaskImpl.setParentHRTaskId(parentHRTaskId);

		if (name == null) {
			hrTaskImpl.setName(StringPool.BLANK);
		}
		else {
			hrTaskImpl.setName(name);
		}

		if (description == null) {
			hrTaskImpl.setDescription(StringPool.BLANK);
		}
		else {
			hrTaskImpl.setDescription(description);
		}

		if (estimatedStartDate == Long.MIN_VALUE) {
			hrTaskImpl.setEstimatedStartDate(null);
		}
		else {
			hrTaskImpl.setEstimatedStartDate(new Date(estimatedStartDate));
		}

		if (estimatedEndDate == Long.MIN_VALUE) {
			hrTaskImpl.setEstimatedEndDate(null);
		}
		else {
			hrTaskImpl.setEstimatedEndDate(new Date(estimatedEndDate));
		}

		hrTaskImpl.setEstimatedHours(estimatedHours);
		hrTaskImpl.setEstimatedHoursCost(estimatedHoursCost);

		if (estimatedHoursCostCurrencyCode == null) {
			hrTaskImpl.setEstimatedHoursCostCurrencyCode(StringPool.BLANK);
		}
		else {
			hrTaskImpl.setEstimatedHoursCostCurrencyCode(estimatedHoursCostCurrencyCode);
		}

		hrTaskImpl.setEstimatedExpenses(estimatedExpenses);

		if (estimatedExpensesCurrencyCode == null) {
			hrTaskImpl.setEstimatedExpensesCurrencyCode(StringPool.BLANK);
		}
		else {
			hrTaskImpl.setEstimatedExpensesCurrencyCode(estimatedExpensesCurrencyCode);
		}

		if (actualStartDate == Long.MIN_VALUE) {
			hrTaskImpl.setActualStartDate(null);
		}
		else {
			hrTaskImpl.setActualStartDate(new Date(actualStartDate));
		}

		if (actualEndDate == Long.MIN_VALUE) {
			hrTaskImpl.setActualEndDate(null);
		}
		else {
			hrTaskImpl.setActualEndDate(new Date(actualEndDate));
		}

		hrTaskImpl.setActualHours(actualHours);
		hrTaskImpl.setActualHoursCost(actualHoursCost);

		if (actualHoursCostCurrencyCode == null) {
			hrTaskImpl.setActualHoursCostCurrencyCode(StringPool.BLANK);
		}
		else {
			hrTaskImpl.setActualHoursCostCurrencyCode(actualHoursCostCurrencyCode);
		}

		hrTaskImpl.setActualExpenses(actualExpenses);

		if (actualExpensesCurrencyCode == null) {
			hrTaskImpl.setActualExpensesCurrencyCode(StringPool.BLANK);
		}
		else {
			hrTaskImpl.setActualExpensesCurrencyCode(actualExpensesCurrencyCode);
		}

		hrTaskImpl.resetOriginalValues();

		return hrTaskImpl;
	}

	public long hrTaskId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long hrBillabilityId;
	public long hrProjectId;
	public long hrTaskStatusId;
	public long parentHRTaskId;
	public String name;
	public String description;
	public long estimatedStartDate;
	public long estimatedEndDate;
	public double estimatedHours;
	public double estimatedHoursCost;
	public String estimatedHoursCostCurrencyCode;
	public double estimatedExpenses;
	public String estimatedExpensesCurrencyCode;
	public long actualStartDate;
	public long actualEndDate;
	public double actualHours;
	public double actualHoursCost;
	public String actualHoursCostCurrencyCode;
	public double actualExpenses;
	public String actualExpensesCurrencyCode;
}