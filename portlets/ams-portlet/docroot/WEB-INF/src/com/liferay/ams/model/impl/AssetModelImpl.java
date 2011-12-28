/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import com.liferay.ams.model.Asset;
import com.liferay.ams.model.AssetModel;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;

/**
 * The base model implementation for the Asset service. Represents a row in the &quot;AMS_Asset&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.ams.model.AssetModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AssetImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetImpl
 * @see com.liferay.ams.model.Asset
 * @see com.liferay.ams.model.AssetModel
 * @generated
 */
public class AssetModelImpl extends BaseModelImpl<Asset> implements AssetModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a asset model instance should use the {@link com.liferay.ams.model.Asset} interface instead.
	 */
	public static final String TABLE_NAME = "AMS_Asset";
	public static final Object[][] TABLE_COLUMNS = {
			{ "assetId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "definitionId", Types.BIGINT },
			{ "serialNumber", Types.VARCHAR },
			{ "inactiveDate", Types.TIMESTAMP },
			{ "active_", Types.BOOLEAN }
		};
	public static final String TABLE_SQL_CREATE = "create table AMS_Asset (assetId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,definitionId LONG,serialNumber VARCHAR(75) null,inactiveDate DATE null,active_ BOOLEAN)";
	public static final String TABLE_SQL_DROP = "drop table AMS_Asset";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.ams.model.Asset"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.ams.model.Asset"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = false;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.ams.model.Asset"));

	public AssetModelImpl() {
	}

	public long getPrimaryKey() {
		return _assetId;
	}

	public void setPrimaryKey(long primaryKey) {
		setAssetId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_assetId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public Class<?> getModelClass() {
		return Asset.class;
	}

	public String getModelClassName() {
		return Asset.class.getName();
	}

	public long getAssetId() {
		return _assetId;
	}

	public void setAssetId(long assetId) {
		_assetId = assetId;
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

	public long getDefinitionId() {
		return _definitionId;
	}

	public void setDefinitionId(long definitionId) {
		_definitionId = definitionId;
	}

	public String getSerialNumber() {
		if (_serialNumber == null) {
			return StringPool.BLANK;
		}
		else {
			return _serialNumber;
		}
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

	@Override
	public Asset toEscapedModel() {
		if (_escapedModelProxy == null) {
			_escapedModelProxy = (Asset)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelProxyInterfaces,
					new AutoEscapeBeanHandler(this));
		}

		return _escapedModelProxy;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
					Asset.class.getName(), getPrimaryKey());
		}

		return _expandoBridge;
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		getExpandoBridge().setAttributes(serviceContext);
	}

	@Override
	public Object clone() {
		AssetImpl assetImpl = new AssetImpl();

		assetImpl.setAssetId(getAssetId());
		assetImpl.setCompanyId(getCompanyId());
		assetImpl.setUserId(getUserId());
		assetImpl.setUserName(getUserName());
		assetImpl.setCreateDate(getCreateDate());
		assetImpl.setModifiedDate(getModifiedDate());
		assetImpl.setDefinitionId(getDefinitionId());
		assetImpl.setSerialNumber(getSerialNumber());
		assetImpl.setInactiveDate(getInactiveDate());
		assetImpl.setActive(getActive());

		assetImpl.resetOriginalValues();

		return assetImpl;
	}

	public int compareTo(Asset asset) {
		long primaryKey = asset.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		Asset asset = null;

		try {
			asset = (Asset)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = asset.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public void resetOriginalValues() {
	}

	@Override
	public CacheModel<Asset> toCacheModel() {
		AssetCacheModel assetCacheModel = new AssetCacheModel();

		assetCacheModel.assetId = getAssetId();

		assetCacheModel.companyId = getCompanyId();

		assetCacheModel.userId = getUserId();

		assetCacheModel.userName = getUserName();

		String userName = assetCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			assetCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			assetCacheModel.createDate = createDate.getTime();
		}
		else {
			assetCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			assetCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			assetCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		assetCacheModel.definitionId = getDefinitionId();

		assetCacheModel.serialNumber = getSerialNumber();

		String serialNumber = assetCacheModel.serialNumber;

		if ((serialNumber != null) && (serialNumber.length() == 0)) {
			assetCacheModel.serialNumber = null;
		}

		Date inactiveDate = getInactiveDate();

		if (inactiveDate != null) {
			assetCacheModel.inactiveDate = inactiveDate.getTime();
		}
		else {
			assetCacheModel.inactiveDate = Long.MIN_VALUE;
		}

		assetCacheModel.active = getActive();

		return assetCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{assetId=");
		sb.append(getAssetId());
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
		sb.append(", definitionId=");
		sb.append(getDefinitionId());
		sb.append(", serialNumber=");
		sb.append(getSerialNumber());
		sb.append(", inactiveDate=");
		sb.append(getInactiveDate());
		sb.append(", active=");
		sb.append(getActive());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(34);

		sb.append("<model><model-name>");
		sb.append("com.liferay.ams.model.Asset");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>assetId</column-name><column-value><![CDATA[");
		sb.append(getAssetId());
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
			"<column><column-name>definitionId</column-name><column-value><![CDATA[");
		sb.append(getDefinitionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>serialNumber</column-name><column-value><![CDATA[");
		sb.append(getSerialNumber());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>inactiveDate</column-name><column-value><![CDATA[");
		sb.append(getInactiveDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>active</column-name><column-value><![CDATA[");
		sb.append(getActive());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = Asset.class.getClassLoader();
	private static Class<?>[] _escapedModelProxyInterfaces = new Class[] {
			Asset.class
		};
	private long _assetId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _definitionId;
	private String _serialNumber;
	private Date _inactiveDate;
	private boolean _active;
	private transient ExpandoBridge _expandoBridge;
	private Asset _escapedModelProxy;
}