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

import com.liferay.ams.model.AMSType;
import com.liferay.ams.model.AMSTypeSoap;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="AMSTypeModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the AMS_AMSType table in the
 * database.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AMSTypeImpl
 * @see       com.liferay.ams.model.AMSType
 * @see       com.liferay.ams.model.AMSTypeModel
 * @generated
 */
public class AMSTypeModelImpl extends BaseModelImpl<AMSType> {
	public static final String TABLE_NAME = "AMS_AMSType";
	public static final Object[][] TABLE_COLUMNS = {
			{ "amsTypeId", new Integer(Types.BIGINT) },
			{ "groupId", new Integer(Types.BIGINT) },
			{ "name", new Integer(Types.VARCHAR) }
		};
	public static final String TABLE_SQL_CREATE = "create table AMS_AMSType (amsTypeId LONG not null primary key,groupId LONG,name VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table AMS_AMSType";
	public static final String ORDER_BY_JPQL = " ORDER BY amsType.name ASC";
	public static final String ORDER_BY_SQL = " ORDER BY AMS_AMSType.name ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.ams.model.AMSType"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.ams.model.AMSType"),
			true);

	public static AMSType toModel(AMSTypeSoap soapModel) {
		AMSType model = new AMSTypeImpl();

		model.setAmsTypeId(soapModel.getAmsTypeId());
		model.setGroupId(soapModel.getGroupId());
		model.setName(soapModel.getName());

		return model;
	}

	public static List<AMSType> toModels(AMSTypeSoap[] soapModels) {
		List<AMSType> models = new ArrayList<AMSType>(soapModels.length);

		for (AMSTypeSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.ams.model.AMSType"));

	public AMSTypeModelImpl() {
	}

	public long getPrimaryKey() {
		return _amsTypeId;
	}

	public void setPrimaryKey(long pk) {
		setAmsTypeId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_amsTypeId);
	}

	public long getAmsTypeId() {
		return _amsTypeId;
	}

	public void setAmsTypeId(long amsTypeId) {
		_amsTypeId = amsTypeId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public String getName() {
		if (_name == null) {
			return StringPool.BLANK;
		}
		else {
			return _name;
		}
	}

	public void setName(String name) {
		_name = name;
	}

	public AMSType toEscapedModel() {
		if (isEscapedModel()) {
			return (AMSType)this;
		}
		else {
			return (AMSType)Proxy.newProxyInstance(AMSType.class.getClassLoader(),
				new Class[] { AMSType.class }, new AutoEscapeBeanHandler(this));
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(0,
					AMSType.class.getName(), getPrimaryKey());
		}

		return _expandoBridge;
	}

	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		getExpandoBridge().setAttributes(serviceContext);
	}

	public Object clone() {
		AMSTypeImpl clone = new AMSTypeImpl();

		clone.setAmsTypeId(getAmsTypeId());
		clone.setGroupId(getGroupId());
		clone.setName(getName());

		return clone;
	}

	public int compareTo(AMSType amsType) {
		int value = 0;

		value = getName().compareTo(amsType.getName());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		AMSType amsType = null;

		try {
			amsType = (AMSType)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = amsType.getPrimaryKey();

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
		StringBundler sb = new StringBundler(7);

		sb.append("{amsTypeId=");
		sb.append(getAmsTypeId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", name=");
		sb.append(getName());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("com.liferay.ams.model.AMSType");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>amsTypeId</column-name><column-value><![CDATA[");
		sb.append(getAmsTypeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _amsTypeId;
	private long _groupId;
	private String _name;
	private transient ExpandoBridge _expandoBridge;
}