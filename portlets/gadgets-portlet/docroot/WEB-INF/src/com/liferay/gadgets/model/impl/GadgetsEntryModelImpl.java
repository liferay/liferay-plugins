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

package com.liferay.gadgets.model.impl;

import com.liferay.gadgets.model.GadgetsEntry;
import com.liferay.gadgets.model.GadgetsEntrySoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="GadgetsEntryModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class GadgetsEntryModelImpl extends BaseModelImpl<GadgetsEntry> {
	public static final String TABLE_NAME = "Gadgets_GadgetsEntry";
	public static final Object[][] TABLE_COLUMNS = {
			{ "gadgetsEntryId", new Integer(Types.BIGINT) },
			{ "companyId", new Integer(Types.BIGINT) },
			{ "createDate", new Integer(Types.TIMESTAMP) },
			{ "modifiedDate", new Integer(Types.TIMESTAMP) },
			{ "name", new Integer(Types.VARCHAR) },
			{ "url", new Integer(Types.VARCHAR) },
			{ "xml", new Integer(Types.VARCHAR) }
		};
	public static final String TABLE_SQL_CREATE = "create table Gadgets_GadgetsEntry (gadgetsEntryId LONG not null primary key,companyId LONG,createDate DATE null,modifiedDate DATE null,name VARCHAR(75) null,url VARCHAR(75) null,xml VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table Gadgets_GadgetsEntry";
	public static final String ORDER_BY_JPQL = " ORDER BY gadgetsEntry.name ASC";
	public static final String ORDER_BY_SQL = " ORDER BY Gadgets_GadgetsEntry.name ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.gadgets.model.GadgetsEntry"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.gadgets.model.GadgetsEntry"),
			true);

	public static GadgetsEntry toModel(GadgetsEntrySoap soapModel) {
		GadgetsEntry model = new GadgetsEntryImpl();

		model.setGadgetsEntryId(soapModel.getGadgetsEntryId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setName(soapModel.getName());
		model.setUrl(soapModel.getUrl());
		model.setXml(soapModel.getXml());

		return model;
	}

	public static List<GadgetsEntry> toModels(GadgetsEntrySoap[] soapModels) {
		List<GadgetsEntry> models = new ArrayList<GadgetsEntry>(soapModels.length);

		for (GadgetsEntrySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.gadgets.model.GadgetsEntry"));

	public GadgetsEntryModelImpl() {
	}

	public long getPrimaryKey() {
		return _gadgetsEntryId;
	}

	public void setPrimaryKey(long pk) {
		setGadgetsEntryId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_gadgetsEntryId);
	}

	public long getGadgetsEntryId() {
		return _gadgetsEntryId;
	}

	public void setGadgetsEntryId(long gadgetsEntryId) {
		_gadgetsEntryId = gadgetsEntryId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
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

	public String getName() {
		return GetterUtil.getString(_name);
	}

	public void setName(String name) {
		_name = name;
	}

	public String getUrl() {
		return GetterUtil.getString(_url);
	}

	public void setUrl(String url) {
		_url = url;
	}

	public String getXml() {
		return GetterUtil.getString(_xml);
	}

	public void setXml(String xml) {
		_xml = xml;
	}

	public GadgetsEntry toEscapedModel() {
		if (isEscapedModel()) {
			return (GadgetsEntry)this;
		}
		else {
			GadgetsEntry model = new GadgetsEntryImpl();

			model.setNew(isNew());
			model.setEscapedModel(true);

			model.setGadgetsEntryId(getGadgetsEntryId());
			model.setCompanyId(getCompanyId());
			model.setCreateDate(getCreateDate());
			model.setModifiedDate(getModifiedDate());
			model.setName(HtmlUtil.escape(getName()));
			model.setUrl(HtmlUtil.escape(getUrl()));
			model.setXml(HtmlUtil.escape(getXml()));

			model = (GadgetsEntry)Proxy.newProxyInstance(GadgetsEntry.class.getClassLoader(),
					new Class[] { GadgetsEntry.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(GadgetsEntry.class.getName(),
					getPrimaryKey());
		}

		return _expandoBridge;
	}

	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		getExpandoBridge().setAttributes(serviceContext);
	}

	public Object clone() {
		GadgetsEntryImpl clone = new GadgetsEntryImpl();

		clone.setGadgetsEntryId(getGadgetsEntryId());
		clone.setCompanyId(getCompanyId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setName(getName());
		clone.setUrl(getUrl());
		clone.setXml(getXml());

		return clone;
	}

	public int compareTo(GadgetsEntry gadgetsEntry) {
		int value = 0;

		value = getName().compareTo(gadgetsEntry.getName());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		GadgetsEntry gadgetsEntry = null;

		try {
			gadgetsEntry = (GadgetsEntry)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = gadgetsEntry.getPrimaryKey();

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
		StringBundler sb = new StringBundler(15);

		sb.append("{gadgetsEntryId=");
		sb.append(getGadgetsEntryId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", url=");
		sb.append(getUrl());
		sb.append(", xml=");
		sb.append(getXml());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(25);

		sb.append("<model><model-name>");
		sb.append("com.liferay.gadgets.model.GadgetsEntry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>gadgetsEntryId</column-name><column-value><![CDATA[");
		sb.append(getGadgetsEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
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
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>url</column-name><column-value><![CDATA[");
		sb.append(getUrl());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>xml</column-name><column-value><![CDATA[");
		sb.append(getXml());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _gadgetsEntryId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _url;
	private String _xml;
	private transient ExpandoBridge _expandoBridge;
}