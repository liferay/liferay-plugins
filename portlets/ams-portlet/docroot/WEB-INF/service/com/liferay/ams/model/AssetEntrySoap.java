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
 * <a href="AssetEntrySoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class AssetEntrySoap implements Serializable {
	public static AssetEntrySoap toSoapModel(AssetEntry model) {
		AssetEntrySoap soapModel = new AssetEntrySoap();

		soapModel.setAssetEntryId(model.getAssetEntryId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setAssetDefinitionId(model.getAssetDefinitionId());
		soapModel.setSerialNumber(model.getSerialNumber());
		soapModel.setInactiveDate(model.getInactiveDate());
		soapModel.setActive(model.getActive());

		return soapModel;
	}

	public static AssetEntrySoap[] toSoapModels(List<AssetEntry> models) {
		List<AssetEntrySoap> soapModels = new ArrayList<AssetEntrySoap>(models.size());

		for (AssetEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AssetEntrySoap[soapModels.size()]);
	}

	public AssetEntrySoap() {
	}

	public long getPrimaryKey() {
		return _assetEntryId;
	}

	public void setPrimaryKey(long pk) {
		setAssetEntryId(pk);
	}

	public long getAssetEntryId() {
		return _assetEntryId;
	}

	public void setAssetEntryId(long assetEntryId) {
		_assetEntryId = assetEntryId;
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

	public long getAssetDefinitionId() {
		return _assetDefinitionId;
	}

	public void setAssetDefinitionId(long assetDefinitionId) {
		_assetDefinitionId = assetDefinitionId;
	}

	public String getSerialNumber() {
		return _serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		_serialNumber = serialNumber;
	}

	public Date getInactiveDate() {
		return _inactiveDate;
	}

	public void setInactiveDate(Date inactiveDate) {
		_inactiveDate = inactiveDate;
	}

	public boolean getActive() {
		return _active;
	}

	public boolean isActive() {
		return _active;
	}

	public void setActive(boolean active) {
		_active = active;
	}

	private long _assetEntryId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _assetDefinitionId;
	private String _serialNumber;
	private Date _inactiveDate;
	private boolean _active;
}