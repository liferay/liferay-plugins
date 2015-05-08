/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

import aQute.bnd.annotation.ProviderType;

import com.liferay.ams.model.Checkout;

import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Checkout in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Checkout
 * @generated
 */
@ProviderType
public class CheckoutCacheModel implements CacheModel<Checkout>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CheckoutCacheModel)) {
			return false;
		}

		CheckoutCacheModel checkoutCacheModel = (CheckoutCacheModel)obj;

		if (checkoutId == checkoutCacheModel.checkoutId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, checkoutId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{checkoutId=");
		sb.append(checkoutId);
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
		sb.append(", assetId=");
		sb.append(assetId);
		sb.append(", checkOutDate=");
		sb.append(checkOutDate);
		sb.append(", expectedCheckInDate=");
		sb.append(expectedCheckInDate);
		sb.append(", actualCheckInDate=");
		sb.append(actualCheckInDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Checkout toEntityModel() {
		CheckoutImpl checkoutImpl = new CheckoutImpl();

		checkoutImpl.setCheckoutId(checkoutId);
		checkoutImpl.setCompanyId(companyId);
		checkoutImpl.setUserId(userId);

		if (userName == null) {
			checkoutImpl.setUserName(StringPool.BLANK);
		}
		else {
			checkoutImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			checkoutImpl.setCreateDate(null);
		}
		else {
			checkoutImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			checkoutImpl.setModifiedDate(null);
		}
		else {
			checkoutImpl.setModifiedDate(new Date(modifiedDate));
		}

		checkoutImpl.setAssetId(assetId);

		if (checkOutDate == Long.MIN_VALUE) {
			checkoutImpl.setCheckOutDate(null);
		}
		else {
			checkoutImpl.setCheckOutDate(new Date(checkOutDate));
		}

		if (expectedCheckInDate == Long.MIN_VALUE) {
			checkoutImpl.setExpectedCheckInDate(null);
		}
		else {
			checkoutImpl.setExpectedCheckInDate(new Date(expectedCheckInDate));
		}

		if (actualCheckInDate == Long.MIN_VALUE) {
			checkoutImpl.setActualCheckInDate(null);
		}
		else {
			checkoutImpl.setActualCheckInDate(new Date(actualCheckInDate));
		}

		checkoutImpl.resetOriginalValues();

		return checkoutImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		checkoutId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		assetId = objectInput.readLong();
		checkOutDate = objectInput.readLong();
		expectedCheckInDate = objectInput.readLong();
		actualCheckInDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(checkoutId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);
		objectOutput.writeLong(assetId);
		objectOutput.writeLong(checkOutDate);
		objectOutput.writeLong(expectedCheckInDate);
		objectOutput.writeLong(actualCheckInDate);
	}

	public long checkoutId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long assetId;
	public long checkOutDate;
	public long expectedCheckInDate;
	public long actualCheckInDate;
}