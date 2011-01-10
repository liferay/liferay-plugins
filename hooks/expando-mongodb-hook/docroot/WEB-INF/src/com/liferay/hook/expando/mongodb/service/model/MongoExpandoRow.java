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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.model.ExpandoRow;

import java.io.Serializable;

import java.lang.reflect.Proxy;

/**
 * @author Raymond Augé
 */
public class MongoExpandoRow implements ExpandoRow {

	public MongoExpandoRow() {
	}

	public Object clone() {
		MongoExpandoRow clone = new MongoExpandoRow();

		clone.setRowId(getRowId());
		clone.setCompanyId(getCompanyId());
		clone.setTableId(getTableId());
		clone.setClassPK(getClassPK());

		return clone;
	}

	public int compareTo(ExpandoRow expandoRow) {
		long pk = expandoRow.getPrimaryKey();

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

		ExpandoRow expandoRow = null;

		try {
			expandoRow = (ExpandoRow)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = expandoRow.getPrimaryKey();

		if (getPrimaryKey() == pk) {
			return true;
		}
		else {
			return false;
		}
	}

	public long getClassPK() {
		return _classPK;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public ExpandoBridge getExpandoBridge() {
		return null;
	}

	public long getPrimaryKey() {
		return _rowId;
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_rowId);
	}

	public long getRowId() {
		return _rowId;
	}

	public long getTableId() {
		return _tableId;
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

	public void setCachedModel(boolean cachedModel) {
		_cachedModel = cachedModel;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public void setEscapedModel(boolean escapedModel) {
		_escapedModel = escapedModel;
	}

	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		throw new UnsupportedOperationException();
	}

	public void setNew(boolean n) {
		_new = n;
	}

	public void setPrimaryKey(long pk) {
		setRowId(pk);
	}

	public void setRowId(long rowId) {
		_rowId = rowId;
	}

	public void setTableId(long tableId) {
		_tableId = tableId;
	}

	public ExpandoRow toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			return (ExpandoRow)Proxy.newProxyInstance(
				ExpandoRow.class.getClassLoader(),
					new Class[] { ExpandoRow.class },
					new AutoEscapeBeanHandler(this));
		}
	}

	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{rowId=");
		sb.append(getRowId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", tableId=");
		sb.append(getTableId());
		sb.append(", classPK=");
		sb.append(getClassPK());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(16);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portlet.expando.model.ExpandoRow");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>rowId</column-name><column-value><![CDATA[");
		sb.append(getRowId());
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
			"<column><column-name>classPK</column-name><column-value><![CDATA[");
		sb.append(getClassPK());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private boolean _cachedModel;
	private long _classPK;
	private long _companyId;
	private boolean _escapedModel;
	private boolean _new;
	private long _rowId;
	private long _tableId;

}