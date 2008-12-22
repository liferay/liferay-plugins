/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.ams.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="AssetCheckoutSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class AssetCheckoutSoap implements Serializable {
	public static AssetCheckoutSoap toSoapModel(AssetCheckout model) {
		AssetCheckoutSoap soapModel = new AssetCheckoutSoap();

		soapModel.setAssetCheckoutId(model.getAssetCheckoutId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setAssetEntryId(model.getAssetEntryId());
		soapModel.setCheckOutDate(model.getCheckOutDate());
		soapModel.setExpectedCheckInDate(model.getExpectedCheckInDate());
		soapModel.setActualCheckInDate(model.getActualCheckInDate());

		return soapModel;
	}

	public static AssetCheckoutSoap[] toSoapModels(List<AssetCheckout> models) {
		List<AssetCheckoutSoap> soapModels = new ArrayList<AssetCheckoutSoap>(models.size());

		for (AssetCheckout model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AssetCheckoutSoap[soapModels.size()]);
	}

	public AssetCheckoutSoap() {
	}

	public long getPrimaryKey() {
		return _assetCheckoutId;
	}

	public void setPrimaryKey(long pk) {
		setAssetCheckoutId(pk);
	}

	public long getAssetCheckoutId() {
		return _assetCheckoutId;
	}

	public void setAssetCheckoutId(long assetCheckoutId) {
		_assetCheckoutId = assetCheckoutId;
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

	public long getAssetEntryId() {
		return _assetEntryId;
	}

	public void setAssetEntryId(long assetEntryId) {
		_assetEntryId = assetEntryId;
	}

	public Date getCheckOutDate() {
		return _checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		_checkOutDate = checkOutDate;
	}

	public Date getExpectedCheckInDate() {
		return _expectedCheckInDate;
	}

	public void setExpectedCheckInDate(Date expectedCheckInDate) {
		_expectedCheckInDate = expectedCheckInDate;
	}

	public Date getActualCheckInDate() {
		return _actualCheckInDate;
	}

	public void setActualCheckInDate(Date actualCheckInDate) {
		_actualCheckInDate = actualCheckInDate;
	}

	private long _assetCheckoutId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _assetEntryId;
	private Date _checkOutDate;
	private Date _expectedCheckInDate;
	private Date _actualCheckInDate;
}