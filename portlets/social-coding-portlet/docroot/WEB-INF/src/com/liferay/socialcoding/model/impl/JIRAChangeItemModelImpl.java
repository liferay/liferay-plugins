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

package com.liferay.socialcoding.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import com.liferay.socialcoding.model.JIRAChangeItem;
import com.liferay.socialcoding.model.JIRAChangeItemModel;

import java.io.Serializable;

import java.sql.Types;

/**
 * The base model implementation for the JIRAChangeItem service. Represents a row in the &quot;changeitem&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.socialcoding.model.JIRAChangeItemModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link JIRAChangeItemImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAChangeItemImpl
 * @see com.liferay.socialcoding.model.JIRAChangeItem
 * @see com.liferay.socialcoding.model.JIRAChangeItemModel
 * @generated
 */
public class JIRAChangeItemModelImpl extends BaseModelImpl<JIRAChangeItem>
	implements JIRAChangeItemModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a j i r a change item model instance should use the {@link com.liferay.socialcoding.model.JIRAChangeItem} interface instead.
	 */
	public static final String TABLE_NAME = "changeitem";
	public static final Object[][] TABLE_COLUMNS = {
			{ "id", Types.BIGINT },
			{ "groupid", Types.BIGINT },
			{ "field", Types.VARCHAR },
			{ "oldValue", Types.VARCHAR },
			{ "oldString", Types.VARCHAR },
			{ "newValue", Types.VARCHAR },
			{ "newString", Types.VARCHAR }
		};
	public static final String TABLE_SQL_CREATE = "create table changeitem (id LONG not null primary key,groupid LONG,field VARCHAR(75) null,oldValue VARCHAR(75) null,oldString VARCHAR(75) null,newValue VARCHAR(75) null,newString VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table changeitem";
	public static final String DATA_SOURCE = "jiraDataSource";
	public static final String SESSION_FACTORY = "jiraSessionFactory";
	public static final String TX_MANAGER = "jiraTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.socialcoding.model.JIRAChangeItem"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.socialcoding.model.JIRAChangeItem"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.socialcoding.model.JIRAChangeItem"),
			true);
	public static long JIRACHANGEGROUPID_COLUMN_BITMASK = 1L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.socialcoding.model.JIRAChangeItem"));

	public JIRAChangeItemModelImpl() {
	}

	public long getPrimaryKey() {
		return _jiraChangeItemId;
	}

	public void setPrimaryKey(long primaryKey) {
		setJiraChangeItemId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_jiraChangeItemId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public Class<?> getModelClass() {
		return JIRAChangeItem.class;
	}

	public String getModelClassName() {
		return JIRAChangeItem.class.getName();
	}

	public long getJiraChangeItemId() {
		return _jiraChangeItemId;
	}

	public void setJiraChangeItemId(long jiraChangeItemId) {
		_jiraChangeItemId = jiraChangeItemId;
	}

	public long getJiraChangeGroupId() {
		return _jiraChangeGroupId;
	}

	public void setJiraChangeGroupId(long jiraChangeGroupId) {
		_columnBitmask |= JIRACHANGEGROUPID_COLUMN_BITMASK;

		if (!_setOriginalJiraChangeGroupId) {
			_setOriginalJiraChangeGroupId = true;

			_originalJiraChangeGroupId = _jiraChangeGroupId;
		}

		_jiraChangeGroupId = jiraChangeGroupId;
	}

	public long getOriginalJiraChangeGroupId() {
		return _originalJiraChangeGroupId;
	}

	public String getField() {
		if (_field == null) {
			return StringPool.BLANK;
		}
		else {
			return _field;
		}
	}

	public void setField(String field) {
		_field = field;
	}

	public String getOldValue() {
		if (_oldValue == null) {
			return StringPool.BLANK;
		}
		else {
			return _oldValue;
		}
	}

	public void setOldValue(String oldValue) {
		_oldValue = oldValue;
	}

	public String getOldString() {
		if (_oldString == null) {
			return StringPool.BLANK;
		}
		else {
			return _oldString;
		}
	}

	public void setOldString(String oldString) {
		_oldString = oldString;
	}

	public String getNewValue() {
		if (_newValue == null) {
			return StringPool.BLANK;
		}
		else {
			return _newValue;
		}
	}

	public void setNewValue(String newValue) {
		_newValue = newValue;
	}

	public String getNewString() {
		if (_newString == null) {
			return StringPool.BLANK;
		}
		else {
			return _newString;
		}
	}

	public void setNewString(String newString) {
		_newString = newString;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public JIRAChangeItem toEscapedModel() {
		if (_escapedModelProxy == null) {
			_escapedModelProxy = (JIRAChangeItem)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelProxyInterfaces,
					new AutoEscapeBeanHandler(this));
		}

		return _escapedModelProxy;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(0,
					JIRAChangeItem.class.getName(), getPrimaryKey());
		}

		return _expandoBridge;
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		getExpandoBridge().setAttributes(serviceContext);
	}

	@Override
	public Object clone() {
		JIRAChangeItemImpl jiraChangeItemImpl = new JIRAChangeItemImpl();

		jiraChangeItemImpl.setJiraChangeItemId(getJiraChangeItemId());
		jiraChangeItemImpl.setJiraChangeGroupId(getJiraChangeGroupId());
		jiraChangeItemImpl.setField(getField());
		jiraChangeItemImpl.setOldValue(getOldValue());
		jiraChangeItemImpl.setOldString(getOldString());
		jiraChangeItemImpl.setNewValue(getNewValue());
		jiraChangeItemImpl.setNewString(getNewString());

		jiraChangeItemImpl.resetOriginalValues();

		return jiraChangeItemImpl;
	}

	public int compareTo(JIRAChangeItem jiraChangeItem) {
		long primaryKey = jiraChangeItem.getPrimaryKey();

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

		JIRAChangeItem jiraChangeItem = null;

		try {
			jiraChangeItem = (JIRAChangeItem)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = jiraChangeItem.getPrimaryKey();

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
		JIRAChangeItemModelImpl jiraChangeItemModelImpl = this;

		jiraChangeItemModelImpl._originalJiraChangeGroupId = jiraChangeItemModelImpl._jiraChangeGroupId;

		jiraChangeItemModelImpl._setOriginalJiraChangeGroupId = false;

		jiraChangeItemModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<JIRAChangeItem> toCacheModel() {
		JIRAChangeItemCacheModel jiraChangeItemCacheModel = new JIRAChangeItemCacheModel();

		jiraChangeItemCacheModel.jiraChangeItemId = getJiraChangeItemId();

		jiraChangeItemCacheModel.jiraChangeGroupId = getJiraChangeGroupId();

		jiraChangeItemCacheModel.field = getField();

		String field = jiraChangeItemCacheModel.field;

		if ((field != null) && (field.length() == 0)) {
			jiraChangeItemCacheModel.field = null;
		}

		jiraChangeItemCacheModel.oldValue = getOldValue();

		String oldValue = jiraChangeItemCacheModel.oldValue;

		if ((oldValue != null) && (oldValue.length() == 0)) {
			jiraChangeItemCacheModel.oldValue = null;
		}

		jiraChangeItemCacheModel.oldString = getOldString();

		String oldString = jiraChangeItemCacheModel.oldString;

		if ((oldString != null) && (oldString.length() == 0)) {
			jiraChangeItemCacheModel.oldString = null;
		}

		jiraChangeItemCacheModel.newValue = getNewValue();

		String newValue = jiraChangeItemCacheModel.newValue;

		if ((newValue != null) && (newValue.length() == 0)) {
			jiraChangeItemCacheModel.newValue = null;
		}

		jiraChangeItemCacheModel.newString = getNewString();

		String newString = jiraChangeItemCacheModel.newString;

		if ((newString != null) && (newString.length() == 0)) {
			jiraChangeItemCacheModel.newString = null;
		}

		return jiraChangeItemCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{jiraChangeItemId=");
		sb.append(getJiraChangeItemId());
		sb.append(", jiraChangeGroupId=");
		sb.append(getJiraChangeGroupId());
		sb.append(", field=");
		sb.append(getField());
		sb.append(", oldValue=");
		sb.append(getOldValue());
		sb.append(", oldString=");
		sb.append(getOldString());
		sb.append(", newValue=");
		sb.append(getNewValue());
		sb.append(", newString=");
		sb.append(getNewString());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(25);

		sb.append("<model><model-name>");
		sb.append("com.liferay.socialcoding.model.JIRAChangeItem");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>jiraChangeItemId</column-name><column-value><![CDATA[");
		sb.append(getJiraChangeItemId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>jiraChangeGroupId</column-name><column-value><![CDATA[");
		sb.append(getJiraChangeGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>field</column-name><column-value><![CDATA[");
		sb.append(getField());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>oldValue</column-name><column-value><![CDATA[");
		sb.append(getOldValue());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>oldString</column-name><column-value><![CDATA[");
		sb.append(getOldString());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>newValue</column-name><column-value><![CDATA[");
		sb.append(getNewValue());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>newString</column-name><column-value><![CDATA[");
		sb.append(getNewString());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = JIRAChangeItem.class.getClassLoader();
	private static Class<?>[] _escapedModelProxyInterfaces = new Class[] {
			JIRAChangeItem.class
		};
	private long _jiraChangeItemId;
	private long _jiraChangeGroupId;
	private long _originalJiraChangeGroupId;
	private boolean _setOriginalJiraChangeGroupId;
	private String _field;
	private String _oldValue;
	private String _oldString;
	private String _newValue;
	private String _newString;
	private transient ExpandoBridge _expandoBridge;
	private long _columnBitmask;
	private JIRAChangeItem _escapedModelProxy;
}