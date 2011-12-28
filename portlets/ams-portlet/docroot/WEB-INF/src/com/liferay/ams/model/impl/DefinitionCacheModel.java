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

package com.liferay.ams.model.impl;

import com.liferay.ams.model.Definition;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing Definition in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Definition
 * @generated
 */
public class DefinitionCacheModel implements CacheModel<Definition>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{definitionId=");
		sb.append(definitionId);
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
		sb.append(", typeId=");
		sb.append(typeId);
		sb.append(", manufacturer=");
		sb.append(manufacturer);
		sb.append(", model=");
		sb.append(model);
		sb.append(", orderDate=");
		sb.append(orderDate);
		sb.append(", quantity=");
		sb.append(quantity);
		sb.append(", price=");
		sb.append(price);
		sb.append("}");

		return sb.toString();
	}

	public Definition toEntityModel() {
		DefinitionImpl definitionImpl = new DefinitionImpl();

		definitionImpl.setDefinitionId(definitionId);
		definitionImpl.setGroupId(groupId);
		definitionImpl.setCompanyId(companyId);
		definitionImpl.setUserId(userId);

		if (userName == null) {
			definitionImpl.setUserName(StringPool.BLANK);
		}
		else {
			definitionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			definitionImpl.setCreateDate(null);
		}
		else {
			definitionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			definitionImpl.setModifiedDate(null);
		}
		else {
			definitionImpl.setModifiedDate(new Date(modifiedDate));
		}

		definitionImpl.setTypeId(typeId);

		if (manufacturer == null) {
			definitionImpl.setManufacturer(StringPool.BLANK);
		}
		else {
			definitionImpl.setManufacturer(manufacturer);
		}

		if (model == null) {
			definitionImpl.setModel(StringPool.BLANK);
		}
		else {
			definitionImpl.setModel(model);
		}

		if (orderDate == Long.MIN_VALUE) {
			definitionImpl.setOrderDate(null);
		}
		else {
			definitionImpl.setOrderDate(new Date(orderDate));
		}

		definitionImpl.setQuantity(quantity);
		definitionImpl.setPrice(price);

		definitionImpl.resetOriginalValues();

		return definitionImpl;
	}

	public long definitionId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long typeId;
	public String manufacturer;
	public String model;
	public long orderDate;
	public int quantity;
	public double price;
}