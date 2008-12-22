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

import com.liferay.ams.model.AssetDefinition;
import com.liferay.ams.model.AssetDefinitionSoap;

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
 * <a href="AssetDefinitionModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class AssetDefinitionModelImpl extends BaseModelImpl {
	public static final String TABLE_NAME = "AMS_AssetDefinition";
	public static final Object[][] TABLE_COLUMNS = {
			{ "assetDefinitionId", new Integer(Types.BIGINT) },
			

			{ "groupId", new Integer(Types.BIGINT) },
			

			{ "companyId", new Integer(Types.BIGINT) },
			

			{ "userId", new Integer(Types.BIGINT) },
			

			{ "userName", new Integer(Types.VARCHAR) },
			

			{ "createDate", new Integer(Types.TIMESTAMP) },
			

			{ "modifiedDate", new Integer(Types.TIMESTAMP) },
			

			{ "assetTypeId", new Integer(Types.BIGINT) },
			

			{ "manufacturer", new Integer(Types.VARCHAR) },
			

			{ "model", new Integer(Types.VARCHAR) },
			

			{ "orderDate", new Integer(Types.TIMESTAMP) },
			

			{ "quantity", new Integer(Types.INTEGER) },
			

			{ "price", new Integer(Types.DOUBLE) }
		};
	public static final String TABLE_SQL_CREATE = "create table AMS_AssetDefinition (assetDefinitionId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,assetTypeId LONG,manufacturer VARCHAR(75) null,model VARCHAR(75) null,orderDate DATE null,quantity INTEGER,price DOUBLE)";
	public static final String TABLE_SQL_DROP = "drop table AMS_AssetDefinition";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.ams.model.AssetDefinition"),
			true);

	public static AssetDefinition toModel(AssetDefinitionSoap soapModel) {
		AssetDefinition model = new AssetDefinitionImpl();

		model.setAssetDefinitionId(soapModel.getAssetDefinitionId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setAssetTypeId(soapModel.getAssetTypeId());
		model.setManufacturer(soapModel.getManufacturer());
		model.setModel(soapModel.getModel());
		model.setOrderDate(soapModel.getOrderDate());
		model.setQuantity(soapModel.getQuantity());
		model.setPrice(soapModel.getPrice());

		return model;
	}

	public static List<AssetDefinition> toModels(
		AssetDefinitionSoap[] soapModels) {
		List<AssetDefinition> models = new ArrayList<AssetDefinition>(soapModels.length);

		for (AssetDefinitionSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.ams.model.AssetDefinition"));

	public AssetDefinitionModelImpl() {
	}

	public long getPrimaryKey() {
		return _assetDefinitionId;
	}

	public void setPrimaryKey(long pk) {
		setAssetDefinitionId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_assetDefinitionId);
	}

	public long getAssetDefinitionId() {
		return _assetDefinitionId;
	}

	public void setAssetDefinitionId(long assetDefinitionId) {
		if (assetDefinitionId != _assetDefinitionId) {
			_assetDefinitionId = assetDefinitionId;
		}
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		if (groupId != _groupId) {
			_groupId = groupId;
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

	public long getAssetTypeId() {
		return _assetTypeId;
	}

	public void setAssetTypeId(long assetTypeId) {
		if (assetTypeId != _assetTypeId) {
			_assetTypeId = assetTypeId;
		}
	}

	public String getManufacturer() {
		return GetterUtil.getString(_manufacturer);
	}

	public void setManufacturer(String manufacturer) {
		if (((manufacturer == null) && (_manufacturer != null)) ||
				((manufacturer != null) && (_manufacturer == null)) ||
				((manufacturer != null) && (_manufacturer != null) &&
				!manufacturer.equals(_manufacturer))) {
			_manufacturer = manufacturer;
		}
	}

	public String getModel() {
		return GetterUtil.getString(_model);
	}

	public void setModel(String model) {
		if (((model == null) && (_model != null)) ||
				((model != null) && (_model == null)) ||
				((model != null) && (_model != null) && !model.equals(_model))) {
			_model = model;
		}
	}

	public Date getOrderDate() {
		return _orderDate;
	}

	public void setOrderDate(Date orderDate) {
		if (((orderDate == null) && (_orderDate != null)) ||
				((orderDate != null) && (_orderDate == null)) ||
				((orderDate != null) && (_orderDate != null) &&
				!orderDate.equals(_orderDate))) {
			_orderDate = orderDate;
		}
	}

	public int getQuantity() {
		return _quantity;
	}

	public void setQuantity(int quantity) {
		if (quantity != _quantity) {
			_quantity = quantity;
		}
	}

	public double getPrice() {
		return _price;
	}

	public void setPrice(double price) {
		if (price != _price) {
			_price = price;
		}
	}

	public AssetDefinition toEscapedModel() {
		if (isEscapedModel()) {
			return (AssetDefinition)this;
		}
		else {
			AssetDefinition model = new AssetDefinitionImpl();

			model.setNew(isNew());
			model.setEscapedModel(true);

			model.setAssetDefinitionId(getAssetDefinitionId());
			model.setGroupId(getGroupId());
			model.setCompanyId(getCompanyId());
			model.setUserId(getUserId());
			model.setUserName(HtmlUtil.escape(getUserName()));
			model.setCreateDate(getCreateDate());
			model.setModifiedDate(getModifiedDate());
			model.setAssetTypeId(getAssetTypeId());
			model.setManufacturer(HtmlUtil.escape(getManufacturer()));
			model.setModel(HtmlUtil.escape(getModel()));
			model.setOrderDate(getOrderDate());
			model.setQuantity(getQuantity());
			model.setPrice(getPrice());

			model = (AssetDefinition)Proxy.newProxyInstance(AssetDefinition.class.getClassLoader(),
					new Class[] { AssetDefinition.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = new ExpandoBridgeImpl(AssetDefinition.class.getName(),
					getPrimaryKey());
		}

		return _expandoBridge;
	}

	public Object clone() {
		AssetDefinitionImpl clone = new AssetDefinitionImpl();

		clone.setAssetDefinitionId(getAssetDefinitionId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setAssetTypeId(getAssetTypeId());
		clone.setManufacturer(getManufacturer());
		clone.setModel(getModel());
		clone.setOrderDate(getOrderDate());
		clone.setQuantity(getQuantity());
		clone.setPrice(getPrice());

		return clone;
	}

	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}

		AssetDefinitionImpl assetDefinition = (AssetDefinitionImpl)obj;

		long pk = assetDefinition.getPrimaryKey();

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

		AssetDefinitionImpl assetDefinition = null;

		try {
			assetDefinition = (AssetDefinitionImpl)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = assetDefinition.getPrimaryKey();

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

	private long _assetDefinitionId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _assetTypeId;
	private String _manufacturer;
	private String _model;
	private Date _orderDate;
	private int _quantity;
	private double _price;
	private transient ExpandoBridge _expandoBridge;
}