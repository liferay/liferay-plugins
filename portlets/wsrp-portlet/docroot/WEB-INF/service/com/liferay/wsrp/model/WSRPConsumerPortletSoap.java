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

package com.liferay.wsrp.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="WSRPConsumerPortletSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WSRPConsumerPortletSoap implements Serializable {
	public static WSRPConsumerPortletSoap toSoapModel(WSRPConsumerPortlet model) {
		WSRPConsumerPortletSoap soapModel = new WSRPConsumerPortletSoap();

		soapModel.setWsrpConsumerPortletId(model.getWsrpConsumerPortletId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setWsrpConsumerId(model.getWsrpConsumerId());
		soapModel.setName(model.getName());
		soapModel.setPortletHandle(model.getPortletHandle());

		return soapModel;
	}

	public static WSRPConsumerPortletSoap[] toSoapModels(
		WSRPConsumerPortlet[] models) {
		WSRPConsumerPortletSoap[] soapModels = new WSRPConsumerPortletSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WSRPConsumerPortletSoap[][] toSoapModels(
		WSRPConsumerPortlet[][] models) {
		WSRPConsumerPortletSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WSRPConsumerPortletSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WSRPConsumerPortletSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WSRPConsumerPortletSoap[] toSoapModels(
		List<WSRPConsumerPortlet> models) {
		List<WSRPConsumerPortletSoap> soapModels = new ArrayList<WSRPConsumerPortletSoap>(models.size());

		for (WSRPConsumerPortlet model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WSRPConsumerPortletSoap[soapModels.size()]);
	}

	public WSRPConsumerPortletSoap() {
	}

	public long getPrimaryKey() {
		return _wsrpConsumerPortletId;
	}

	public void setPrimaryKey(long pk) {
		setWsrpConsumerPortletId(pk);
	}

	public long getWsrpConsumerPortletId() {
		return _wsrpConsumerPortletId;
	}

	public void setWsrpConsumerPortletId(long wsrpConsumerPortletId) {
		_wsrpConsumerPortletId = wsrpConsumerPortletId;
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

	public long getWsrpConsumerId() {
		return _wsrpConsumerId;
	}

	public void setWsrpConsumerId(long wsrpConsumerId) {
		_wsrpConsumerId = wsrpConsumerId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getPortletHandle() {
		return _portletHandle;
	}

	public void setPortletHandle(String portletHandle) {
		_portletHandle = portletHandle;
	}

	private long _wsrpConsumerPortletId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _wsrpConsumerId;
	private String _name;
	private String _portletHandle;
}