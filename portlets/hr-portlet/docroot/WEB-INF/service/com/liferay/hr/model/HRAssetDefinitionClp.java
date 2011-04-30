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

package com.liferay.hr.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 */
public class HRAssetDefinitionClp extends BaseModelImpl<HRAssetDefinition>
	implements HRAssetDefinition {
	public HRAssetDefinitionClp() {
	}

	public Class<?> getModelClass() {
		return HRAssetDefinition.class;
	}

	public String getModelClassName() {
		return HRAssetDefinition.class.getName();
	}

	public long getPrimaryKey() {
		return _hrAssetDefinitionId;
	}

	public void setPrimaryKey(long pk) {
		setHrAssetDefinitionId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_hrAssetDefinitionId);
	}

	public long getHrAssetDefinitionId() {
		return _hrAssetDefinitionId;
	}

	public void setHrAssetDefinitionId(long hrAssetDefinitionId) {
		_hrAssetDefinitionId = hrAssetDefinitionId;
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

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
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

	public String getHrAssetProductId() {
		return _hrAssetProductId;
	}

	public void setHrAssetProductId(String hrAssetProductId) {
		_hrAssetProductId = hrAssetProductId;
	}

	public long getHrAssetTypeId() {
		return _hrAssetTypeId;
	}

	public void setHrAssetTypeId(long hrAssetTypeId) {
		_hrAssetTypeId = hrAssetTypeId;
	}

	public long getHrAssetVendorId() {
		return _hrAssetVendorId;
	}

	public void setHrAssetVendorId(long hrAssetVendorId) {
		_hrAssetVendorId = hrAssetVendorId;
	}

	public String getDefinitionNumber() {
		return _definitionNumber;
	}

	public void setDefinitionNumber(String definitionNumber) {
		_definitionNumber = definitionNumber;
	}

	public Date getOrderId() {
		return _orderId;
	}

	public void setOrderId(Date orderId) {
		_orderId = orderId;
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

	public double getIndividualPrice() {
		return _individualPrice;
	}

	public void setIndividualPrice(double individualPrice) {
		_individualPrice = individualPrice;
	}

	public HRAssetDefinition toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			return (HRAssetDefinition)Proxy.newProxyInstance(HRAssetDefinition.class.getClassLoader(),
				new Class[] { HRAssetDefinition.class },
				new AutoEscapeBeanHandler(this));
		}
	}

	public Object clone() {
		HRAssetDefinitionClp clone = new HRAssetDefinitionClp();

		clone.setHrAssetDefinitionId(getHrAssetDefinitionId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setHrAssetProductId(getHrAssetProductId());
		clone.setHrAssetTypeId(getHrAssetTypeId());
		clone.setHrAssetVendorId(getHrAssetVendorId());
		clone.setDefinitionNumber(getDefinitionNumber());
		clone.setOrderId(getOrderId());
		clone.setOrderDate(getOrderDate());
		clone.setQuantity(getQuantity());
		clone.setIndividualPrice(getIndividualPrice());

		return clone;
	}

	public int compareTo(HRAssetDefinition hrAssetDefinition) {
		long pk = hrAssetDefinition.getPrimaryKey();

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

		HRAssetDefinitionClp hrAssetDefinition = null;

		try {
			hrAssetDefinition = (HRAssetDefinitionClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = hrAssetDefinition.getPrimaryKey();

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

	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{hrAssetDefinitionId=");
		sb.append(getHrAssetDefinitionId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", hrAssetProductId=");
		sb.append(getHrAssetProductId());
		sb.append(", hrAssetTypeId=");
		sb.append(getHrAssetTypeId());
		sb.append(", hrAssetVendorId=");
		sb.append(getHrAssetVendorId());
		sb.append(", definitionNumber=");
		sb.append(getDefinitionNumber());
		sb.append(", orderId=");
		sb.append(getOrderId());
		sb.append(", orderDate=");
		sb.append(getOrderDate());
		sb.append(", quantity=");
		sb.append(getQuantity());
		sb.append(", individualPrice=");
		sb.append(getIndividualPrice());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(49);

		sb.append("<model><model-name>");
		sb.append("com.liferay.hr.model.HRAssetDefinition");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>hrAssetDefinitionId</column-name><column-value><![CDATA[");
		sb.append(getHrAssetDefinitionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hrAssetProductId</column-name><column-value><![CDATA[");
		sb.append(getHrAssetProductId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hrAssetTypeId</column-name><column-value><![CDATA[");
		sb.append(getHrAssetTypeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hrAssetVendorId</column-name><column-value><![CDATA[");
		sb.append(getHrAssetVendorId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>definitionNumber</column-name><column-value><![CDATA[");
		sb.append(getDefinitionNumber());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>orderId</column-name><column-value><![CDATA[");
		sb.append(getOrderId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>orderDate</column-name><column-value><![CDATA[");
		sb.append(getOrderDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>quantity</column-name><column-value><![CDATA[");
		sb.append(getQuantity());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>individualPrice</column-name><column-value><![CDATA[");
		sb.append(getIndividualPrice());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _hrAssetDefinitionId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _hrAssetProductId;
	private long _hrAssetTypeId;
	private long _hrAssetVendorId;
	private String _definitionNumber;
	private Date _orderId;
	private Date _orderDate;
	private int _quantity;
	private double _individualPrice;
}