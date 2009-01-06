/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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
 * <a href="AssetDefinitionClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class AssetDefinitionClp extends BaseModelImpl implements AssetDefinition {
	public AssetDefinitionClp() {
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
		_assetDefinitionId = assetDefinitionId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
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

	public long getAssetTypeId() {
		return _assetTypeId;
	}

	public void setAssetTypeId(long assetTypeId) {
		_assetTypeId = assetTypeId;
	}

	public String getManufacturer() {
		return _manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		_manufacturer = manufacturer;
	}

	public String getModel() {
		return _model;
	}

	public void setModel(String model) {
		_model = model;
	}

	public Date getOrderDate() {
		return _orderDate;
	}

	public void setOrderDate(Date orderDate) {
		_orderDate = orderDate;
	}

	public int getQuantity() {
		return _quantity;
	}

	public void setQuantity(int quantity) {
		_quantity = quantity;
	}

	public double getPrice() {
		return _price;
	}

	public void setPrice(double price) {
		_price = price;
	}

	public AssetDefinition toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			AssetDefinition model = new AssetDefinitionClp();

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

	public Object clone() {
		AssetDefinitionClp clone = new AssetDefinitionClp();

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

		AssetDefinitionClp assetDefinition = (AssetDefinitionClp)obj;

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

		AssetDefinitionClp assetDefinition = null;

		try {
			assetDefinition = (AssetDefinitionClp)obj;
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
}