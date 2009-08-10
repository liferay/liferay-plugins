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
 * <a href="WSRPProducerSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WSRPProducerSoap implements Serializable {
	public static WSRPProducerSoap toSoapModel(WSRPProducer model) {
		WSRPProducerSoap soapModel = new WSRPProducerSoap();

		soapModel.setWsrpProducerId(model.getWsrpProducerId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setPortletIds(model.getPortletIds());

		return soapModel;
	}

	public static WSRPProducerSoap[] toSoapModels(WSRPProducer[] models) {
		WSRPProducerSoap[] soapModels = new WSRPProducerSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WSRPProducerSoap[][] toSoapModels(WSRPProducer[][] models) {
		WSRPProducerSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WSRPProducerSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WSRPProducerSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WSRPProducerSoap[] toSoapModels(List<WSRPProducer> models) {
		List<WSRPProducerSoap> soapModels = new ArrayList<WSRPProducerSoap>(models.size());

		for (WSRPProducer model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WSRPProducerSoap[soapModels.size()]);
	}

	public WSRPProducerSoap() {
	}

	public long getPrimaryKey() {
		return _wsrpProducerId;
	}

	public void setPrimaryKey(long pk) {
		setWsrpProducerId(pk);
	}

	public long getWsrpProducerId() {
		return _wsrpProducerId;
	}

	public void setWsrpProducerId(long wsrpProducerId) {
		_wsrpProducerId = wsrpProducerId;
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

	public String getPortletIds() {
		return _portletIds;
	}

	public void setPortletIds(String portletIds) {
		_portletIds = portletIds;
	}

	private long _wsrpProducerId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _portletIds;
}