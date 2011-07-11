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

import com.liferay.hr.model.HRProject;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing HRProject in entity cache.
 *
 * @author Wesley Gong
 * @see HRProject
 * @generated
 */
public class HRProjectCacheModel implements CacheModel<HRProject> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(51);

		sb.append("{hrProjectId=");
		sb.append(hrProjectId);
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
		sb.append(", hrClientId=");
		sb.append(hrClientId);
		sb.append(", hrProjectStatusId=");
		sb.append(hrProjectStatusId);
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

	public HRProject toEntityModel() {
		HRProjectImpl hrProjectImpl = new HRProjectImpl();

		hrProjectImpl.setHrProjectId(hrProjectId);
		hrProjectImpl.setGroupId(groupId);
		hrProjectImpl.setCompanyId(companyId);
		hrProjectImpl.setUserId(userId);

		if (userName == null) {
			hrProjectImpl.setUserName(StringPool.BLANK);
		}
		else {
			hrProjectImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			hrProjectImpl.setCreateDate(null);
		}
		else {
			hrProjectImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			hrProjectImpl.setModifiedDate(null);
		}
		else {
			hrProjectImpl.setModifiedDate(new Date(modifiedDate));
		}

		hrProjectImpl.setHrClientId(hrClientId);
		hrProjectImpl.setHrProjectStatusId(hrProjectStatusId);

		if (name == null) {
			hrProjectImpl.setName(StringPool.BLANK);
		}
		else {
			hrProjectImpl.setName(name);
		}

		if (description == null) {
			hrProjectImpl.setDescription(StringPool.BLANK);
		}
		else {
			hrProjectImpl.setDescription(description);
		}

		if (estimatedStartDate == Long.MIN_VALUE) {
			hrProjectImpl.setEstimatedStartDate(null);
		}
		else {
			hrProjectImpl.setEstimatedStartDate(new Date(estimatedStartDate));
		}

		if (estimatedEndDate == Long.MIN_VALUE) {
			hrProjectImpl.setEstimatedEndDate(null);
		}
		else {
			hrProjectImpl.setEstimatedEndDate(new Date(estimatedEndDate));
		}

		hrProjectImpl.setEstimatedHours(estimatedHours);
		hrProjectImpl.setEstimatedHoursCost(estimatedHoursCost);

		if (estimatedHoursCostCurrencyCode == null) {
			hrProjectImpl.setEstimatedHoursCostCurrencyCode(StringPool.BLANK);
		}
		else {
			hrProjectImpl.setEstimatedHoursCostCurrencyCode(estimatedHoursCostCurrencyCode);
		}

		hrProjectImpl.setEstimatedExpenses(estimatedExpenses);

		if (estimatedExpensesCurrencyCode == null) {
			hrProjectImpl.setEstimatedExpensesCurrencyCode(StringPool.BLANK);
		}
		else {
			hrProjectImpl.setEstimatedExpensesCurrencyCode(estimatedExpensesCurrencyCode);
		}

		if (actualStartDate == Long.MIN_VALUE) {
			hrProjectImpl.setActualStartDate(null);
		}
		else {
			hrProjectImpl.setActualStartDate(new Date(actualStartDate));
		}

		if (actualEndDate == Long.MIN_VALUE) {
			hrProjectImpl.setActualEndDate(null);
		}
		else {
			hrProjectImpl.setActualEndDate(new Date(actualEndDate));
		}

		hrProjectImpl.setActualHours(actualHours);
		hrProjectImpl.setActualHoursCost(actualHoursCost);

		if (actualHoursCostCurrencyCode == null) {
			hrProjectImpl.setActualHoursCostCurrencyCode(StringPool.BLANK);
		}
		else {
			hrProjectImpl.setActualHoursCostCurrencyCode(actualHoursCostCurrencyCode);
		}

		hrProjectImpl.setActualExpenses(actualExpenses);
		hrProjectImpl.setActualExpensesCurrencyCode(actualExpensesCurrencyCode);

		hrProjectImpl.resetOriginalValues();

		return hrProjectImpl;
	}

	public long hrProjectId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long hrClientId;
	public long hrProjectStatusId;
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
	public double actualExpensesCurrencyCode;
}