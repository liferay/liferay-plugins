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

package com.liferay.ams.model.impl;

import com.liferay.ams.model.AssetEntry;
import com.liferay.ams.model.AssetEntrySoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.model.impl.ExpandoBridgeImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="AssetEntryModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class AssetEntryModelImpl extends BaseModelImpl {
	public static final String TABLE_NAME = "AMS_AssetEntry";
	public static final Object[][] TABLE_COLUMNS = {
			{ "assetEntryId", new Integer(Types.BIGINT) },
			

			{ "companyId", new Integer(Types.BIGINT) },
			

			{ "userId", new Integer(Types.BIGINT) },
			

			{ "userName", new Integer(Types.VARCHAR) },
			

			{ "createDate", new Integer(Types.TIMESTAMP) },
			

			{ "modifiedDate", new Integer(Types.TIMESTAMP) },
			

			{ "assetDefinitionId", new Integer(Types.BIGINT) },
			

			{ "serialNumber", new Integer(Types.VARCHAR) },
			

			{ "inactiveDate", new Integer(Types.TIMESTAMP) },
			

			{ "active_", new Integer(Types.BOOLEAN) }
		};
	public static final String TABLE_SQL_CREATE = "create table AMS_AssetEntry (assetEntryId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,assetDefinitionId LONG,serialNumber VARCHAR(75) null,inactiveDate DATE null,active_ BOOLEAN)";
	public static final String TABLE_SQL_DROP = "drop table AMS_AssetEntry";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.ams.model.AssetEntry"),
			true);

	public static AssetEntry toModel(AssetEntrySoap soapModel) {
		AssetEntry model = new AssetEntryImpl();

		model.setAssetEntryId(soapModel.getAssetEntryId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setAssetDefinitionId(soapModel.getAssetDefinitionId());
		model.setSerialNumber(soapModel.getSerialNumber());
		model.setInactiveDate(soapModel.getInactiveDate());
		model.setActive(soapModel.getActive());

		return model;
	}

	public static List<AssetEntry> toModels(AssetEntrySoap[] soapModels) {
		List<AssetEntry> models = new ArrayList<AssetEntry>(soapModels.length);

		for (AssetEntrySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.ams.model.AssetEntry"));

	public AssetEntryModelImpl() {
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
		if (assetEntryId != _assetEntryId) {
			_assetEntryId = assetEntryId;
		}
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		if (companyId != _companyId) {
			_companyId = companyId;
		}
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		if (userId != _userId) {
			_userId = userId;
		}
	}

	public String getUserName() {
		return GetterUtil.getString(_userName);
	}

	public void setUserName(String userName) {
		if (((userName == null) && (_userName != null)) ||
				((userName != null) && (_userName == null)) ||
				((userName != null) && (_userName != null) &&
				!userName.equals(_userName))) {
			_userName = userName;
		}
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		if (((createDate == null) && (_createDate != null)) ||
				((createDate != null) && (_createDate == null)) ||
				((createDate != null) && (_createDate != null) &&
				!createDate.equals(_createDate))) {
			_createDate = createDate;
		}
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		if (((modifiedDate == null) && (_modifiedDate != null)) ||
				((modifiedDate != null) && (_modifiedDate == null)) ||
				((modifiedDate != null) && (_modifiedDate != null) &&
				!modifiedDate.equals(_modifiedDate))) {
			_modifiedDate = modifiedDate;
		}
	}

	public long getAssetDefinitionId() {
		return _assetDefinitionId;
	}

	public void setAssetDefinitionId(long assetDefinitionId) {
		if (assetDefinitionId != _assetDefinitionId) {
			_assetDefinitionId = assetDefinitionId;
		}
	}

	public String getSerialNumber() {
		return GetterUtil.getString(_serialNumber);
	}

	public void setSerialNumber(String serialNumber) {
		if (((serialNumber == null) && (_serialNumber != null)) ||
				((serialNumber != null) && (_serialNumber == null)) ||
				((serialNumber != null) && (_serialNumber != null) &&
				!serialNumber.equals(_serialNumber))) {
			_serialNumber = serialNumber;
		}
	}

	public Date getInactiveDate() {
		return _inactiveDate;
	}

	public void setInactiveDate(Date inactiveDate) {
		if (((inactiveDate == null) && (_inactiveDate != null)) ||
				((inactiveDate != null) && (_inactiveDate == null)) ||
				((inactiveDate != null) && (_inactiveDate != null) &&
				!inactiveDate.equals(_inactiveDate))) {
			_inactiveDate = inactiveDate;
		}
	}

	public boolean getActive() {
		return _active;
	}

	public boolean isActive() {
		return _active;
	}

	public void setActive(boolean active) {
		if (active != _active) {
			_active = active;
		}
	}

	public AssetEntry toEscapedModel() {
		if (isEscapedModel()) {
			return (AssetEntry)this;
		}
		else {
			AssetEntry model = new AssetEntryImpl();

			model.setNew(isNew());
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

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = new ExpandoBridgeImpl(AssetEntry.class.getName(),
					getPrimaryKey());
		}

		return _expandoBridge;
	}

	public Object clone() {
		AssetEntryImpl clone = new AssetEntryImpl();

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

		AssetEntryImpl assetEntry = (AssetEntryImpl)obj;

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

		AssetEntryImpl assetEntry = null;

		try {
			assetEntry = (AssetEntryImpl)obj;
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
	private transient ExpandoBridge _expandoBridge;
}