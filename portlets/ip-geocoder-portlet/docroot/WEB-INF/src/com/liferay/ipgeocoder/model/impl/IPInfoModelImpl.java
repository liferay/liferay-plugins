/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

package com.liferay.ipgeocoder.model.impl;

import com.liferay.ipgeocoder.model.IPInfo;
import com.liferay.ipgeocoder.model.IPInfoSoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;

import com.liferay.portlet.service.BaseModelImpl;
import com.liferay.portlet.service.PropsUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="IPInfoModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class IPInfoModelImpl extends BaseModelImpl {
	public static final String TABLE_NAME = "IPGeocoder_IPInfo";
	public static final Object[][] TABLE_COLUMNS = {
			{ "ipInfoId", new Integer(Types.BIGINT) },
			

			{ "createDate", new Integer(Types.TIMESTAMP) },
			

			{ "ipAddress", new Integer(Types.VARCHAR) },
			

			{ "latitude", new Integer(Types.DOUBLE) },
			

			{ "longitude", new Integer(Types.DOUBLE) },
			

			{ "city", new Integer(Types.VARCHAR) },
			

			{ "country", new Integer(Types.VARCHAR) }
		};
	public static final String TABLE_SQL_CREATE = "create table IPGeocoder_IPInfo (ipInfoId LONG not null primary key,createDate DATE null,ipAddress VARCHAR(75) null,latitude DOUBLE,longitude DOUBLE,city VARCHAR(75) null,country VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table IPGeocoder_IPInfo";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean CACHE_ENABLED = GetterUtil.getBoolean(PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.ipgeocoder.model.IPInfo"),
			true);

	public static IPInfo toModel(IPInfoSoap soapModel) {
		IPInfo model = new IPInfoImpl();

		model.setIpInfoId(soapModel.getIpInfoId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setIpAddress(soapModel.getIpAddress());
		model.setLatitude(soapModel.getLatitude());
		model.setLongitude(soapModel.getLongitude());
		model.setCity(soapModel.getCity());
		model.setCountry(soapModel.getCountry());

		return model;
	}

	public static List<IPInfo> toModels(IPInfoSoap[] soapModels) {
		List<IPInfo> models = new ArrayList<IPInfo>(soapModels.length);

		for (IPInfoSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(PropsUtil.get(
				"lock.expiration.time.com.liferay.ipgeocoder.model.IPInfo"));

	public IPInfoModelImpl() {
	}

	public long getPrimaryKey() {
		return _ipInfoId;
	}

	public void setPrimaryKey(long pk) {
		setIpInfoId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_ipInfoId);
	}

	public long getIpInfoId() {
		return _ipInfoId;
	}

	public void setIpInfoId(long ipInfoId) {
		if (ipInfoId != _ipInfoId) {
			_ipInfoId = ipInfoId;
		}
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		if (((createDate == null) && (_createDate != null)) ||
				((createDate != null) && (_createDate == null)) ||
				((createDate != null) && (_createDate != null) &&
				!createDate.equals(_createDate))) {
			_createDate = createDate;
		}
	}

	public String getIpAddress() {
		return GetterUtil.getString(_ipAddress);
	}

	public void setIpAddress(String ipAddress) {
		if (((ipAddress == null) && (_ipAddress != null)) ||
				((ipAddress != null) && (_ipAddress == null)) ||
				((ipAddress != null) && (_ipAddress != null) &&
				!ipAddress.equals(_ipAddress))) {
			_ipAddress = ipAddress;
		}
	}

	public double getLatitude() {
		return _latitude;
	}

	public void setLatitude(double latitude) {
		if (latitude != _latitude) {
			_latitude = latitude;
		}
	}

	public double getLongitude() {
		return _longitude;
	}

	public void setLongitude(double longitude) {
		if (longitude != _longitude) {
			_longitude = longitude;
		}
	}

	public String getCity() {
		return GetterUtil.getString(_city);
	}

	public void setCity(String city) {
		if (((city == null) && (_city != null)) ||
				((city != null) && (_city == null)) ||
				((city != null) && (_city != null) && !city.equals(_city))) {
			_city = city;
		}
	}

	public String getCountry() {
		return GetterUtil.getString(_country);
	}

	public void setCountry(String country) {
		if (((country == null) && (_country != null)) ||
				((country != null) && (_country == null)) ||
				((country != null) && (_country != null) &&
				!country.equals(_country))) {
			_country = country;
		}
	}

	public IPInfo toEscapedModel() {
		if (isEscapedModel()) {
			return (IPInfo)this;
		}
		else {
			IPInfo model = new IPInfoImpl();

			model.setEscapedModel(true);

			model.setIpInfoId(getIpInfoId());
			model.setCreateDate(getCreateDate());
			model.setIpAddress(HtmlUtil.escape(getIpAddress()));
			model.setLatitude(getLatitude());
			model.setLongitude(getLongitude());
			model.setCity(HtmlUtil.escape(getCity()));
			model.setCountry(HtmlUtil.escape(getCountry()));

			model = (IPInfo)Proxy.newProxyInstance(IPInfo.class.getClassLoader(),
					new Class[] { IPInfo.class }, new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public Object clone() {
		IPInfoImpl clone = new IPInfoImpl();

		clone.setIpInfoId(getIpInfoId());
		clone.setCreateDate(getCreateDate());
		clone.setIpAddress(getIpAddress());
		clone.setLatitude(getLatitude());
		clone.setLongitude(getLongitude());
		clone.setCity(getCity());
		clone.setCountry(getCountry());

		return clone;
	}

	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}

		IPInfoImpl ipInfo = (IPInfoImpl)obj;

		long pk = ipInfo.getPrimaryKey();

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

		IPInfoImpl ipInfo = null;

		try {
			ipInfo = (IPInfoImpl)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = ipInfo.getPrimaryKey();

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

	private long _ipInfoId;
	private Date _createDate;
	private String _ipAddress;
	private double _latitude;
	private double _longitude;
	private String _city;
	private String _country;
}