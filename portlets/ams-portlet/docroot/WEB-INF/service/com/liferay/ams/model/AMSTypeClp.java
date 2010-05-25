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

package com.liferay.ams.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

/**
 * <a href="AMSTypeClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class AMSTypeClp extends BaseModelImpl<AMSType> implements AMSType {
	public AMSTypeClp() {
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
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public AMSType toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			return (AMSType)Proxy.newProxyInstance(AMSType.class.getClassLoader(),
				new Class[] { AMSType.class }, new AutoEscapeBeanHandler(this));
		}
	}

	public Object clone() {
		AMSTypeClp clone = new AMSTypeClp();

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

		AMSTypeClp amsType = null;

		try {
			amsType = (AMSTypeClp)obj;
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
}