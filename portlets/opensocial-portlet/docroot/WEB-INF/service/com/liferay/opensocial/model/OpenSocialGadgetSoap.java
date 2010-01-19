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

package com.liferay.opensocial.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="OpenSocialGadgetSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class OpenSocialGadgetSoap implements Serializable {
	public static OpenSocialGadgetSoap toSoapModel(OpenSocialGadget model) {
		OpenSocialGadgetSoap soapModel = new OpenSocialGadgetSoap();

		soapModel.setOpenSocialGadgetId(model.getOpenSocialGadgetId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setUrl(model.getUrl());
		soapModel.setXml(model.getXml());

		return soapModel;
	}

	public static OpenSocialGadgetSoap[] toSoapModels(OpenSocialGadget[] models) {
		OpenSocialGadgetSoap[] soapModels = new OpenSocialGadgetSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static OpenSocialGadgetSoap[][] toSoapModels(
		OpenSocialGadget[][] models) {
		OpenSocialGadgetSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new OpenSocialGadgetSoap[models.length][models[0].length];
		}
		else {
			soapModels = new OpenSocialGadgetSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static OpenSocialGadgetSoap[] toSoapModels(
		List<OpenSocialGadget> models) {
		List<OpenSocialGadgetSoap> soapModels = new ArrayList<OpenSocialGadgetSoap>(models.size());

		for (OpenSocialGadget model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new OpenSocialGadgetSoap[soapModels.size()]);
	}

	public OpenSocialGadgetSoap() {
	}

	public long getPrimaryKey() {
		return _openSocialGadgetId;
	}

	public void setPrimaryKey(long pk) {
		setOpenSocialGadgetId(pk);
	}

	public long getOpenSocialGadgetId() {
		return _openSocialGadgetId;
	}

	public void setOpenSocialGadgetId(long openSocialGadgetId) {
		_openSocialGadgetId = openSocialGadgetId;
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
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getUrl() {
		return _url;
	}

	public void setUrl(String url) {
		_url = url;
	}

	public String getXml() {
		return _xml;
	}

	public void setXml(String xml) {
		_xml = xml;
	}

	private long _openSocialGadgetId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _url;
	private String _xml;
}