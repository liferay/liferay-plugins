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

package com.liferay.hook.expando.mongodb.service.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.ValueDataException;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;

/**
 * @author Raymond Augé
 */
public class MongoExpandoValue implements ExpandoValue {

	public Object clone() {
		ExpandoValue clone = new MongoExpandoValue();

		clone.setValueId(getValueId());
		clone.setCompanyId(getCompanyId());
		clone.setTableId(getTableId());
		clone.setColumnId(getColumnId());
		clone.setRowId(getRowId());
		clone.setClassNameId(getClassNameId());
		clone.setClassPK(getClassPK());
		clone.setData(getData());

		return clone;
	}

	public int compareTo(ExpandoValue expandoValue) {
		int value = 0;

		if (getTableId() < expandoValue.getTableId()) {
			value = -1;
		}
		else if (getTableId() > expandoValue.getTableId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (getRowId() < expandoValue.getRowId()) {
			value = -1;
		}
		else if (getRowId() > expandoValue.getRowId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (getColumnId() < expandoValue.getColumnId()) {
			value = -1;
		}
		else if (getColumnId() > expandoValue.getColumnId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		ExpandoValue expandoValue = null;

		try {
			expandoValue = (ExpandoValue)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = expandoValue.getPrimaryKey();

		if (getPrimaryKey() == pk) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean getBoolean() throws PortalException, SystemException {
		validate(ExpandoColumnConstants.BOOLEAN);

		return GetterUtil.getBoolean(getData());
	}

	public boolean[] getBooleanArray() throws PortalException, SystemException {
		validate(ExpandoColumnConstants.BOOLEAN_ARRAY);

		return GetterUtil.getBooleanValues(StringUtil.split(getData()));
	}

	public String getClassName() {
		if (getClassNameId() <= 0) {
			return StringPool.BLANK;
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public long getClassPK() {
		return _classPK;
	}

	public long getColumnId() {
		return _columnId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public String getData() {
		return _data;
	}

	public Date getDate() throws PortalException, SystemException {
		validate(ExpandoColumnConstants.DATE);

		return new Date(GetterUtil.getLong(getData()));
	}

	public Date[] getDateArray() throws PortalException, SystemException {
		validate(ExpandoColumnConstants.DATE_ARRAY);

		String[] data = StringUtil.split(getData());

		Date[] dateArray = new Date[data.length];

		for (int i = 0; i < data.length; i++) {
			dateArray[i] = new Date(GetterUtil.getLong(data[i]));
		}

		return dateArray;
	}

	public double getDouble() throws PortalException, SystemException {
		validate(ExpandoColumnConstants.DOUBLE);

		return GetterUtil.getDouble(getData());
	}

	public double[] getDoubleArray() throws PortalException, SystemException {
		validate(ExpandoColumnConstants.DOUBLE_ARRAY);

		return GetterUtil.getDoubleValues(StringUtil.split(getData()));
	}

	public ExpandoBridge getExpandoBridge() {
		throw new UnsupportedOperationException();
	}

	public float getFloat() throws PortalException, SystemException {
		validate(ExpandoColumnConstants.FLOAT);

		return GetterUtil.getFloat(getData());
	}

	public float[] getFloatArray() throws PortalException, SystemException {
		validate(ExpandoColumnConstants.FLOAT_ARRAY);

		return GetterUtil.getFloatValues(StringUtil.split(getData()));
	}

	public int getInteger() throws PortalException, SystemException {
		validate(ExpandoColumnConstants.INTEGER);

		return GetterUtil.getInteger(getData());
	}

	public int[] getIntegerArray() throws PortalException, SystemException {
		validate(ExpandoColumnConstants.INTEGER_ARRAY);

		return GetterUtil.getIntegerValues(StringUtil.split(getData()));
	}

	public long getLong() throws PortalException, SystemException {
		validate(ExpandoColumnConstants.LONG);

		return GetterUtil.getLong(getData());
	}

	public long[] getLongArray() throws PortalException, SystemException {
		validate(ExpandoColumnConstants.LONG_ARRAY);

		return GetterUtil.getLongValues(StringUtil.split(getData()));
	}

	public long getPrimaryKey() {
		return _valueId;
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_valueId);
	}

	public long getRowId() {
		return _rowId;
	}

	public short getShort() throws PortalException, SystemException {
		validate(ExpandoColumnConstants.SHORT);

		return GetterUtil.getShort(getData());
	}

	public short[] getShortArray() throws PortalException, SystemException {
		validate(ExpandoColumnConstants.SHORT_ARRAY);

		return GetterUtil.getShortValues(StringUtil.split(getData()));
	}

	public String getString() throws PortalException, SystemException {
		validate(ExpandoColumnConstants.STRING);

		return getData();
	}

	public String[] getStringArray() throws PortalException, SystemException {
		validate(ExpandoColumnConstants.STRING_ARRAY);

		return StringUtil.split(getData());
	}

	public long getTableId() {
		return _tableId;
	}

	public long getValueId() {
		return _valueId;
	}

	public int hashCode() {
		return (int)getPrimaryKey();
	}

	public boolean isCachedModel() {
		return _cachedModel;
	}

	public boolean isEscapedModel() {
		return _escapedModel;
	}

	public boolean isNew() {
		return _new;
	}

	public void setBoolean(boolean data)
		throws PortalException, SystemException {

		validate(ExpandoColumnConstants.BOOLEAN);

		setData(String.valueOf(data));
	}

	public void setBooleanArray(boolean[] data)
		throws PortalException, SystemException {

		validate(ExpandoColumnConstants.BOOLEAN_ARRAY);

		setData(StringUtil.merge(data));
	}

	public void setCachedModel(boolean cachedModel) {
		_cachedModel = cachedModel;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public void setColumnId(long columnId) {
		_columnId = columnId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public void setData(String data) {
		_data = data;
	}

	public void setDate(Date data)
		throws PortalException, SystemException {

		validate(ExpandoColumnConstants.DATE);

		setData(String.valueOf(data.getTime()));
	}

	public void setDateArray(Date[] data)
		throws PortalException, SystemException {

		validate(ExpandoColumnConstants.DATE_ARRAY);

		setData(StringUtil.merge(data));
	}

	public void setDouble(double data)
		throws PortalException, SystemException {

		validate(ExpandoColumnConstants.DOUBLE);

		setData(String.valueOf(data));
	}

	public void setDoubleArray(double[] data)
		throws PortalException, SystemException {

		validate(ExpandoColumnConstants.DOUBLE_ARRAY);

		setData(StringUtil.merge(data));
	}

	public void setEscapedModel(boolean escapedModel) {
		_escapedModel = escapedModel;
	}

	public void setExpandoBridgeAttributes(ServiceContext arg0) {
		throw new UnsupportedOperationException();
	}

	public void setFloat(float data)
		throws PortalException, SystemException {

		validate(ExpandoColumnConstants.FLOAT);

		setData(String.valueOf(data));
	}

	public void setFloatArray(float[] data)
		throws PortalException, SystemException {

		validate(ExpandoColumnConstants.FLOAT_ARRAY);

		setData(StringUtil.merge(data));
	}

	public void setInteger(int data)
		throws PortalException, SystemException {

		validate(ExpandoColumnConstants.INTEGER);

		setData(String.valueOf(data));
	}

	public void setIntegerArray(int[] data)
		throws PortalException, SystemException {

		validate(ExpandoColumnConstants.INTEGER_ARRAY);

		setData(StringUtil.merge(data));
	}

	public void setLong(long data)
		throws PortalException, SystemException {

		validate(ExpandoColumnConstants.LONG);

		setData(String.valueOf(data));
	}

	public void setLongArray(long[] data)
		throws PortalException, SystemException {

		validate(ExpandoColumnConstants.LONG_ARRAY);

		setData(StringUtil.merge(data));
	}

	public void setNew(boolean n) {
		_new = n;
	}

	public void setPrimaryKey(long pk) {
		setValueId(pk);
	}

	public void setRowId(long rowId) {
		_rowId = rowId;
	}

	public void setShort(short data)
		throws PortalException, SystemException {

		validate(ExpandoColumnConstants.SHORT);

		setData(String.valueOf(data));
	}

	public void setShortArray(short[] data)
		throws PortalException, SystemException {

		validate(ExpandoColumnConstants.SHORT_ARRAY);

		setData(StringUtil.merge(data));
	}

	public void setString(String data) throws PortalException, SystemException {
		validate(ExpandoColumnConstants.STRING);

		setData(data);
	}

	public void setStringArray(String[] data)
		throws PortalException, SystemException {

		validate(ExpandoColumnConstants.STRING_ARRAY);

		setData(StringUtil.merge(data));
	}

	public void setTableId(long tableId) {
		_tableId = tableId;
	}

	public void setValueId(long valueId) {
		_valueId = valueId;
	}

	public ExpandoValue toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			return (ExpandoValue)Proxy.newProxyInstance(
				ExpandoValue.class.getClassLoader(),
					new Class[] { ExpandoValue.class },
					new AutoEscapeBeanHandler(this));
		}
	}

	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{valueId=");
		sb.append(getValueId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", tableId=");
		sb.append(getTableId());
		sb.append(", columnId=");
		sb.append(getColumnId());
		sb.append(", rowId=");
		sb.append(getRowId());
		sb.append(", classNameId=");
		sb.append(getClassNameId());
		sb.append(", classPK=");
		sb.append(getClassPK());
		sb.append(", data=");
		sb.append(getData());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(28);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portlet.expando.model.ExpandoValue");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>valueId</column-name><column-value><![CDATA[");
		sb.append(getValueId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>tableId</column-name><column-value><![CDATA[");
		sb.append(getTableId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>columnId</column-name><column-value><![CDATA[");
		sb.append(getColumnId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>rowId</column-name><column-value><![CDATA[");
		sb.append(getRowId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classNameId</column-name><column-value><![CDATA[");
		sb.append(getClassNameId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classPK</column-name><column-value><![CDATA[");
		sb.append(getClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>data</column-name><column-value><![CDATA[");
		sb.append(getData());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	protected void validate(int type) throws PortalException, SystemException {
		ExpandoColumn column = ExpandoColumnLocalServiceUtil.getColumn(
			getColumnId());

		if (column.getType() != type) {
			throw new ValueDataException(
				"Column " + getColumnId() + " has type " +
					ExpandoColumnConstants.getTypeLabel(column.getType()) +
						" and is not compatible with type " +
							ExpandoColumnConstants.getTypeLabel(type));
		}
	}

	private boolean _new;
	private boolean _cachedModel;
	private boolean _escapedModel;
	private long _valueId;
	private long _companyId;
	private long _tableId;
	private long _columnId;
	private long _rowId;
	private long _classNameId;
	private long _classPK;
	private String _data;

}