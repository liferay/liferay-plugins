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

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;

/**
 * <a href="AssetEntryClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class AssetEntryClp extends BaseModelImpl implements AssetEntry {
	public AssetEntryClp() {
	}

	public long getPrimaryKey() {
		return _assetEntryId;
	}

	public void setPrimaryKey(long pk) {
		setAssetEntryId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_assetEntryId);
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

	public AssetEntry toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			AssetEntry model = new AssetEntryClp();

			model.setEscapedModel(true);

			model.setAssetEntryId(getAssetEntryId());
			model.setCompanyId(getCompanyId());
			model.setUserId(getUserId());
			model.setUserName(HtmlUtil.escape(getUserName()));
			model.setCreateDate(getCreateDate());
			model.setModifiedDate(getModifiedDate());
			model.setAssetDefinitionId(getAssetDefinitionId());
			model.setSerialNumber(HtmlUtil.escape(getSerialNumber()));
			model.setInactiveDate(getInactiveDate());
			model.setActive(getActive());

			model = (AssetEntry)Proxy.newProxyInstance(AssetEntry.class.getClassLoader(),
					new Class[] { AssetEntry.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public Object clone() {
		AssetEntryClp clone = new AssetEntryClp();

		clone.setAssetEntryId(getAssetEntryId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setAssetDefinitionId(getAssetDefinitionId());
		clone.setSerialNumber(getSerialNumber());
		clone.setInactiveDate(getInactiveDate());
		clone.setActive(getActive());

		return clone;
	}

	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}

		AssetEntryClp assetEntry = (AssetEntryClp)obj;

		long pk = assetEntry.getPrimaryKey();

		if (getPrimaryKey() < pk) {
			return -1;
		}
		else if (getPrimaryKey() > pk) {
			return 1;
		}
		else {
			return 0;
		}
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		AssetEntryClp assetEntry = null;

		try {
			assetEntry = (AssetEntryClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = assetEntry.getPrimaryKey();

		if (getPrimaryKey() == pk) {
			return true;
		}
		else {
			return false;
		}
	}

	public int hashCode() {
		return (int)getPrimaryKey();
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