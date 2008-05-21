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

package com.liferay.ipgeocoder.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="IPInfoSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class IPInfoSoap implements Serializable {
	public static IPInfoSoap toSoapModel(IPInfo model) {
		IPInfoSoap soapModel = new IPInfoSoap();

		soapModel.setIpInfoId(model.getIpInfoId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setIpAddress(model.getIpAddress());
		soapModel.setLatitude(model.getLatitude());
		soapModel.setLongitude(model.getLongitude());
		soapModel.setCity(model.getCity());
		soapModel.setCountry(model.getCountry());

		return soapModel;
	}

	public static IPInfoSoap[] toSoapModels(List<IPInfo> models) {
		List<IPInfoSoap> soapModels = new ArrayList<IPInfoSoap>(models.size());

		for (IPInfo model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new IPInfoSoap[soapModels.size()]);
	}

	public IPInfoSoap() {
	}

	public long getPrimaryKey() {
		return _ipInfoId;
	}

	public void setPrimaryKey(long pk) {
		setIpInfoId(pk);
	}

	public long getIpInfoId() {
		return _ipInfoId;
	}

	public void setIpInfoId(long ipInfoId) {
		_ipInfoId = ipInfoId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public String getIpAddress() {
		return _ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		_ipAddress = ipAddress;
	}

	public double getLatitude() {
		return _latitude;
	}

	public void setLatitude(double latitude) {
		_latitude = latitude;
	}

	public double getLongitude() {
		return _longitude;
	}

	public void setLongitude(double longitude) {
		_longitude = longitude;
	}

	public String getCity() {
		return _city;
	}

	public void setCity(String city) {
		_city = city;
	}

	public String getCountry() {
		return _country;
	}

	public void setCountry(String country) {
		_country = country;
	}

	private long _ipInfoId;
	private Date _createDate;
	private String _ipAddress;
	private double _latitude;
	private double _longitude;
	private String _city;
	private String _country;
}