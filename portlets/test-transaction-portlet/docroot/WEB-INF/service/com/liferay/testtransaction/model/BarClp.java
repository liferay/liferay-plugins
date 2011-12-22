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

package com.liferay.testtransaction.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.liferay.testtransaction.service.BarLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

/**
 * @author Brian Wing Shun Chan
 */
public class BarClp extends BaseModelImpl<Bar> implements Bar {
	public BarClp() {
	}

	public Class<?> getModelClass() {
		return Bar.class;
	}

	public String getModelClassName() {
		return Bar.class.getName();
	}

	public long getPrimaryKey() {
		return _barId;
	}

	public void setPrimaryKey(long primaryKey) {
		setBarId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_barId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public long getBarId() {
		return _barId;
	}

	public void setBarId(long barId) {
		_barId = barId;
	}

	public String getText() {
		return _text;
	}

	public void setText(String text) {
		_text = text;
	}

	public void persist() throws SystemException {
		BarLocalServiceUtil.updateBar(this);
	}

	@Override
	public Bar toEscapedModel() {
		return (Bar)Proxy.newProxyInstance(Bar.class.getClassLoader(),
			new Class[] { Bar.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		BarClp clone = new BarClp();

		clone.setBarId(getBarId());
		clone.setText(getText());

		return clone;
	}

	public int compareTo(Bar bar) {
		int value = 0;

		value = getText().compareTo(bar.getText());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		BarClp bar = null;

		try {
			bar = (BarClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = bar.getPrimaryKey();

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
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{barId=");
		sb.append(getBarId());
		sb.append(", text=");
		sb.append(getText());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(10);

		sb.append("<model><model-name>");
		sb.append("com.liferay.testtransaction.model.Bar");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>barId</column-name><column-value><![CDATA[");
		sb.append(getBarId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>text</column-name><column-value><![CDATA[");
		sb.append(getText());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _barId;
	private String _text;
}