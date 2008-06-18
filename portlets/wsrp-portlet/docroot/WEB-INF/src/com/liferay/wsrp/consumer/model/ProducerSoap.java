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

package com.liferay.wsrp.consumer.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="ProducerSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ProducerSoap implements Serializable {
	public static ProducerSoap toSoapModel(Producer model) {
		ProducerSoap soapModel = new ProducerSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setProducerId(model.getProducerId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setWsdlURL(model.getWsdlURL());
		soapModel.setMarkupEndpoint(model.getMarkupEndpoint());
		soapModel.setPortletManagementEndpoint(model.getPortletManagementEndpoint());
		soapModel.setRegistrationEndpoint(model.getRegistrationEndpoint());
		soapModel.setServiceDescriptionEndpoint(model.getServiceDescriptionEndpoint());
		soapModel.setRegistrationContext(model.getRegistrationContext());
		soapModel.setServiceDescription(model.getServiceDescription());

		return soapModel;
	}

	public static ProducerSoap[] toSoapModels(List<Producer> models) {
		List<ProducerSoap> soapModels = new ArrayList<ProducerSoap>(models.size());

		for (Producer model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ProducerSoap[soapModels.size()]);
	}

	public ProducerSoap() {
	}

	public long getPrimaryKey() {
		return _producerId;
	}

	public void setPrimaryKey(long pk) {
		setProducerId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getProducerId() {
		return _producerId;
	}

	public void setProducerId(long producerId) {
		_producerId = producerId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
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

	public String getWsdlURL() {
		return _wsdlURL;
	}

	public void setWsdlURL(String wsdlURL) {
		_wsdlURL = wsdlURL;
	}

	public String getMarkupEndpoint() {
		return _markupEndpoint;
	}

	public void setMarkupEndpoint(String markupEndpoint) {
		_markupEndpoint = markupEndpoint;
	}

	public String getPortletManagementEndpoint() {
		return _portletManagementEndpoint;
	}

	public void setPortletManagementEndpoint(String portletManagementEndpoint) {
		_portletManagementEndpoint = portletManagementEndpoint;
	}

	public String getRegistrationEndpoint() {
		return _registrationEndpoint;
	}

	public void setRegistrationEndpoint(String registrationEndpoint) {
		_registrationEndpoint = registrationEndpoint;
	}

	public String getServiceDescriptionEndpoint() {
		return _serviceDescriptionEndpoint;
	}

	public void setServiceDescriptionEndpoint(String serviceDescriptionEndpoint) {
		_serviceDescriptionEndpoint = serviceDescriptionEndpoint;
	}

	public String getRegistrationContext() {
		return _registrationContext;
	}

	public void setRegistrationContext(String registrationContext) {
		_registrationContext = registrationContext;
	}

	public String getServiceDescription() {
		return _serviceDescription;
	}

	public void setServiceDescription(String serviceDescription) {
		_serviceDescription = serviceDescription;
	}

	private String _uuid;
	private long _producerId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _wsdlURL;
	private String _markupEndpoint;
	private String _portletManagementEndpoint;
	private String _registrationEndpoint;
	private String _serviceDescriptionEndpoint;
	private String _registrationContext;
	private String _serviceDescription;
}