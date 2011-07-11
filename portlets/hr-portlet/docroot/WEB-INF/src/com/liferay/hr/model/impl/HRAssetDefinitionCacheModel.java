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

import com.liferay.hr.model.HRAssetDefinition;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing HRAssetDefinition in entity cache.
 *
 * @author Wesley Gong
 * @see HRAssetDefinition
 * @generated
 */
public class HRAssetDefinitionCacheModel implements CacheModel<HRAssetDefinition> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{hrAssetDefinitionId=");
		sb.append(hrAssetDefinitionId);
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
		sb.append(", hrAssetProductId=");
		sb.append(hrAssetProductId);
		sb.append(", hrAssetTypeId=");
		sb.append(hrAssetTypeId);
		sb.append(", hrAssetVendorId=");
		sb.append(hrAssetVendorId);
		sb.append(", definitionNumber=");
		sb.append(definitionNumber);
		sb.append(", orderId=");
		sb.append(orderId);
		sb.append(", orderDate=");
		sb.append(orderDate);
		sb.append(", quantity=");
		sb.append(quantity);
		sb.append(", individualPrice=");
		sb.append(individualPrice);
		sb.append("}");

		return sb.toString();
	}

	public HRAssetDefinition toEntityModel() {
		HRAssetDefinitionImpl hrAssetDefinitionImpl = new HRAssetDefinitionImpl();

		hrAssetDefinitionImpl.setHrAssetDefinitionId(hrAssetDefinitionId);
		hrAssetDefinitionImpl.setGroupId(groupId);
		hrAssetDefinitionImpl.setCompanyId(companyId);
		hrAssetDefinitionImpl.setUserId(userId);

		if (userName == null) {
			hrAssetDefinitionImpl.setUserName(StringPool.BLANK);
		}
		else {
			hrAssetDefinitionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			hrAssetDefinitionImpl.setCreateDate(null);
		}
		else {
			hrAssetDefinitionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			hrAssetDefinitionImpl.setModifiedDate(null);
		}
		else {
			hrAssetDefinitionImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (hrAssetProductId == null) {
			hrAssetDefinitionImpl.setHrAssetProductId(StringPool.BLANK);
		}
		else {
			hrAssetDefinitionImpl.setHrAssetProductId(hrAssetProductId);
		}

		hrAssetDefinitionImpl.setHrAssetTypeId(hrAssetTypeId);
		hrAssetDefinitionImpl.setHrAssetVendorId(hrAssetVendorId);

		if (definitionNumber == null) {
			hrAssetDefinitionImpl.setDefinitionNumber(StringPool.BLANK);
		}
		else {
			hrAssetDefinitionImpl.setDefinitionNumber(definitionNumber);
		}

		if (orderId == Long.MIN_VALUE) {
			hrAssetDefinitionImpl.setOrderId(null);
		}
		else {
			hrAssetDefinitionImpl.setOrderId(new Date(orderId));
		}

		if (orderDate == Long.MIN_VALUE) {
			hrAssetDefinitionImpl.setOrderDate(null);
		}
		else {
			hrAssetDefinitionImpl.setOrderDate(new Date(orderDate));
		}

		hrAssetDefinitionImpl.setQuantity(quantity);
		hrAssetDefinitionImpl.setIndividualPrice(individualPrice);

		hrAssetDefinitionImpl.resetOriginalValues();

		return hrAssetDefinitionImpl;
	}

	public long hrAssetDefinitionId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String hrAssetProductId;
	public long hrAssetTypeId;
	public long hrAssetVendorId;
	public String definitionNumber;
	public long orderId;
	public long orderDate;
	public int quantity;
	public double individualPrice;
}