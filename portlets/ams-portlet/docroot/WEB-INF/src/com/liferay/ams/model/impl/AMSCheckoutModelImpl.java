/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

import com.liferay.ams.model.AMSCheckout;
import com.liferay.ams.model.AMSCheckoutSoap;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="AMSCheckoutModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the AMS_AMSCheckout table in the
 * database.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AMSCheckoutImpl
 * @see       com.liferay.ams.model.AMSCheckout
 * @see       com.liferay.ams.model.AMSCheckoutModel
 * @generated
 */
public class AMSCheckoutModelImpl extends BaseModelImpl<AMSCheckout> {
	public static final String TABLE_NAME = "AMS_AMSCheckout";
	public static final Object[][] TABLE_COLUMNS = {
			{ "amsCheckoutId", new Integer(Types.BIGINT) },
			{ "companyId", new Integer(Types.BIGINT) },
			{ "userId", new Integer(Types.BIGINT) },
			{ "userName", new Integer(Types.VARCHAR) },
			{ "createDate", new Integer(Types.TIMESTAMP) },
			{ "modifiedDate", new Integer(Types.TIMESTAMP) },
			{ "amsAssetId", new Integer(Types.BIGINT) },
			{ "checkOutDate", new Integer(Types.TIMESTAMP) },
			{ "expectedCheckInDate", new Integer(Types.TIMESTAMP) },
			{ "actualCheckInDate", new Integer(Types.TIMESTAMP) }
		};
	public static final String TABLE_SQL_CREATE = "create table AMS_AMSCheckout (amsCheckoutId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,amsAssetId LONG,checkOutDate DATE null,expectedCheckInDate DATE null,actualCheckInDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table AMS_AMSCheckout";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.ams.model.AMSCheckout"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.ams.model.AMSCheckout"),
			true);

	public static AMSCheckout toModel(AMSCheckoutSoap soapModel) {
		AMSCheckout model = new AMSCheckoutImpl();

		model.setAmsCheckoutId(soapModel.getAmsCheckoutId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setAmsAssetId(soapModel.getAmsAssetId());
		model.setCheckOutDate(soapModel.getCheckOutDate());
		model.setExpectedCheckInDate(soapModel.getExpectedCheckInDate());
		model.setActualCheckInDate(soapModel.getActualCheckInDate());

		return model;
	}

	public static List<AMSCheckout> toModels(AMSCheckoutSoap[] soapModels) {
		List<AMSCheckout> models = new ArrayList<AMSCheckout>(soapModels.length);

		for (AMSCheckoutSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.ams.model.AMSCheckout"));

	public AMSCheckoutModelImpl() {
	}

	public long getPrimaryKey() {
		return _amsCheckoutId;
	}

	public void setPrimaryKey(long pk) {
		setAmsCheckoutId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_amsCheckoutId);
	}

	public long getAmsCheckoutId() {
		return _amsCheckoutId;
	}

	public void setAmsCheckoutId(long amsCheckoutId) {
		_amsCheckoutId = amsCheckoutId;
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
		if (_userName == null) {
			return StringPool.BLANK;
		}
		else {
			return _userName;
		}
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

	public long getAmsAssetId() {
		return _amsAssetId;
	}

	public void setAmsAssetId(long amsAssetId) {
		_amsAssetId = amsAssetId;
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

	public AMSCheckout toEscapedModel() {
		if (isEscapedModel()) {
			return (AMSCheckout)this;
		}
		else {
			return (AMSCheckout)Proxy.newProxyInstance(AMSCheckout.class.getClassLoader(),
				new Class[] { AMSCheckout.class },
				new AutoEscapeBeanHandler(this));
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
					AMSCheckout.class.getName(), getPrimaryKey());
		}

		return _expandoBridge;
	}

	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		getExpandoBridge().setAttributes(serviceContext);
	}

	public Object clone() {
		AMSCheckoutImpl clone = new AMSCheckoutImpl();

		clone.setAmsCheckoutId(getAmsCheckoutId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setAmsAssetId(getAmsAssetId());
		clone.setCheckOutDate(getCheckOutDate());
		clone.setExpectedCheckInDate(getExpectedCheckInDate());
		clone.setActualCheckInDate(getActualCheckInDate());

		return clone;
	}

	public int compareTo(AMSCheckout amsCheckout) {
		long pk = amsCheckout.getPrimaryKey();

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

		AMSCheckout amsCheckout = null;

		try {
			amsCheckout = (AMSCheckout)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = amsCheckout.getPrimaryKey();

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
		StringBundler sb = new StringBundler(21);

		sb.append("{amsCheckoutId=");
		sb.append(getAmsCheckoutId());
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
		sb.append(", amsAssetId=");
		sb.append(getAmsAssetId());
		sb.append(", checkOutDate=");
		sb.append(getCheckOutDate());
		sb.append(", expectedCheckInDate=");
		sb.append(getExpectedCheckInDate());
		sb.append(", actualCheckInDate=");
		sb.append(getActualCheckInDate());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(34);

		sb.append("<model><model-name>");
		sb.append("com.liferay.ams.model.AMSCheckout");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>amsCheckoutId</column-name><column-value><![CDATA[");
		sb.append(getAmsCheckoutId());
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
			"<column><column-name>amsAssetId</column-name><column-value><![CDATA[");
		sb.append(getAmsAssetId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>checkOutDate</column-name><column-value><![CDATA[");
		sb.append(getCheckOutDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>expectedCheckInDate</column-name><column-value><![CDATA[");
		sb.append(getExpectedCheckInDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>actualCheckInDate</column-name><column-value><![CDATA[");
		sb.append(getActualCheckInDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _amsCheckoutId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _amsAssetId;
	private Date _checkOutDate;
	private Date _expectedCheckInDate;
	private Date _actualCheckInDate;
	private transient ExpandoBridge _expandoBridge;
}