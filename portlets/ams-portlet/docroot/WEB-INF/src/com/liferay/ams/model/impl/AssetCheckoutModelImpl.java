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

import com.liferay.ams.model.AssetCheckout;
import com.liferay.ams.model.AssetCheckoutSoap;

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
 * <a href="AssetCheckoutModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class AssetCheckoutModelImpl extends BaseModelImpl {
	public static final String TABLE_NAME = "AMS_AssetCheckout";
	public static final Object[][] TABLE_COLUMNS = {
			{ "assetCheckoutId", new Integer(Types.BIGINT) },
			

			{ "companyId", new Integer(Types.BIGINT) },
			

			{ "userId", new Integer(Types.BIGINT) },
			

			{ "userName", new Integer(Types.VARCHAR) },
			

			{ "createDate", new Integer(Types.TIMESTAMP) },
			

			{ "modifiedDate", new Integer(Types.TIMESTAMP) },
			

			{ "assetEntryId", new Integer(Types.BIGINT) },
			

			{ "checkOutDate", new Integer(Types.TIMESTAMP) },
			

			{ "expectedCheckInDate", new Integer(Types.TIMESTAMP) },
			

			{ "actualCheckInDate", new Integer(Types.TIMESTAMP) }
		};
	public static final String TABLE_SQL_CREATE = "create table AMS_AssetCheckout (assetCheckoutId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,assetEntryId LONG,checkOutDate DATE null,expectedCheckInDate DATE null,actualCheckInDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table AMS_AssetCheckout";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.ams.model.AssetCheckout"),
			true);

	public static AssetCheckout toModel(AssetCheckoutSoap soapModel) {
		AssetCheckout model = new AssetCheckoutImpl();

		model.setAssetCheckoutId(soapModel.getAssetCheckoutId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setAssetEntryId(soapModel.getAssetEntryId());
		model.setCheckOutDate(soapModel.getCheckOutDate());
		model.setExpectedCheckInDate(soapModel.getExpectedCheckInDate());
		model.setActualCheckInDate(soapModel.getActualCheckInDate());

		return model;
	}

	public static List<AssetCheckout> toModels(AssetCheckoutSoap[] soapModels) {
		List<AssetCheckout> models = new ArrayList<AssetCheckout>(soapModels.length);

		for (AssetCheckoutSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.ams.model.AssetCheckout"));

	public AssetCheckoutModelImpl() {
	}

	public long getPrimaryKey() {
		return _assetCheckoutId;
	}

	public void setPrimaryKey(long pk) {
		setAssetCheckoutId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_assetCheckoutId);
	}

	public long getAssetCheckoutId() {
		return _assetCheckoutId;
	}

	public void setAssetCheckoutId(long assetCheckoutId) {
		if (assetCheckoutId != _assetCheckoutId) {
			_assetCheckoutId = assetCheckoutId;
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

	public long getAssetEntryId() {
		return _assetEntryId;
	}

	public void setAssetEntryId(long assetEntryId) {
		if (assetEntryId != _assetEntryId) {
			_assetEntryId = assetEntryId;
		}
	}

	public Date getCheckOutDate() {
		return _checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		if (((checkOutDate == null) && (_checkOutDate != null)) ||
				((checkOutDate != null) && (_checkOutDate == null)) ||
				((checkOutDate != null) && (_checkOutDate != null) &&
				!checkOutDate.equals(_checkOutDate))) {
			_checkOutDate = checkOutDate;
		}
	}

	public Date getExpectedCheckInDate() {
		return _expectedCheckInDate;
	}

	public void setExpectedCheckInDate(Date expectedCheckInDate) {
		if (((expectedCheckInDate == null) && (_expectedCheckInDate != null)) ||
				((expectedCheckInDate != null) &&
				(_expectedCheckInDate == null)) ||
				((expectedCheckInDate != null) &&
				(_expectedCheckInDate != null) &&
				!expectedCheckInDate.equals(_expectedCheckInDate))) {
			_expectedCheckInDate = expectedCheckInDate;
		}
	}

	public Date getActualCheckInDate() {
		return _actualCheckInDate;
	}

	public void setActualCheckInDate(Date actualCheckInDate) {
		if (((actualCheckInDate == null) && (_actualCheckInDate != null)) ||
				((actualCheckInDate != null) && (_actualCheckInDate == null)) ||
				((actualCheckInDate != null) && (_actualCheckInDate != null) &&
				!actualCheckInDate.equals(_actualCheckInDate))) {
			_actualCheckInDate = actualCheckInDate;
		}
	}

	public AssetCheckout toEscapedModel() {
		if (isEscapedModel()) {
			return (AssetCheckout)this;
		}
		else {
			AssetCheckout model = new AssetCheckoutImpl();

			model.setNew(isNew());
			model.setEscapedModel(true);

			model.setAssetCheckoutId(getAssetCheckoutId());
			model.setCompanyId(getCompanyId());
			model.setUserId(getUserId());
			model.setUserName(HtmlUtil.escape(getUserName()));
			model.setCreateDate(getCreateDate());
			model.setModifiedDate(getModifiedDate());
			model.setAssetEntryId(getAssetEntryId());
			model.setCheckOutDate(getCheckOutDate());
			model.setExpectedCheckInDate(getExpectedCheckInDate());
			model.setActualCheckInDate(getActualCheckInDate());

			model = (AssetCheckout)Proxy.newProxyInstance(AssetCheckout.class.getClassLoader(),
					new Class[] { AssetCheckout.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = new ExpandoBridgeImpl(AssetCheckout.class.getName(),
					getPrimaryKey());
		}

		return _expandoBridge;
	}

	public Object clone() {
		AssetCheckoutImpl clone = new AssetCheckoutImpl();

		clone.setAssetCheckoutId(getAssetCheckoutId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setAssetEntryId(getAssetEntryId());
		clone.setCheckOutDate(getCheckOutDate());
		clone.setExpectedCheckInDate(getExpectedCheckInDate());
		clone.setActualCheckInDate(getActualCheckInDate());

		return clone;
	}

	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}

		AssetCheckoutImpl assetCheckout = (AssetCheckoutImpl)obj;

		long pk = assetCheckout.getPrimaryKey();

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

		AssetCheckoutImpl assetCheckout = null;

		try {
			assetCheckout = (AssetCheckoutImpl)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = assetCheckout.getPrimaryKey();

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
	private transient ExpandoBridge _expandoBridge;
}