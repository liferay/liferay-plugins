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

package com.liferay.portal.workflow.edoras.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="WorkflowDefinitionSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowDefinitionSoap implements Serializable {
	public static WorkflowDefinitionSoap toSoapModel(WorkflowDefinition model) {
		WorkflowDefinitionSoap soapModel = new WorkflowDefinitionSoap();

		soapModel.setWorkflowDefinitionId(model.getWorkflowDefinitionId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setVersion(model.getVersion());
		soapModel.setDesignerVersion(model.getDesignerVersion());
		soapModel.setModelXml(model.getModelXml());
		soapModel.setGraphicalXml(model.getGraphicalXml());
		soapModel.setPersistent(model.getPersistent());

		return soapModel;
	}

	public static WorkflowDefinitionSoap[] toSoapModels(
		WorkflowDefinition[] models) {
		WorkflowDefinitionSoap[] soapModels = new WorkflowDefinitionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WorkflowDefinitionSoap[][] toSoapModels(
		WorkflowDefinition[][] models) {
		WorkflowDefinitionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WorkflowDefinitionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WorkflowDefinitionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WorkflowDefinitionSoap[] toSoapModels(
		List<WorkflowDefinition> models) {
		List<WorkflowDefinitionSoap> soapModels = new ArrayList<WorkflowDefinitionSoap>(models.size());

		for (WorkflowDefinition model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WorkflowDefinitionSoap[soapModels.size()]);
	}

	public WorkflowDefinitionSoap() {
	}

	public long getPrimaryKey() {
		return _workflowDefinitionId;
	}

	public void setPrimaryKey(long pk) {
		setWorkflowDefinitionId(pk);
	}

	public long getWorkflowDefinitionId() {
		return _workflowDefinitionId;
	}

	public void setWorkflowDefinitionId(long workflowDefinitionId) {
		_workflowDefinitionId = workflowDefinitionId;
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

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
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

	public double getVersion() {
		return _version;
	}

	public void setVersion(double version) {
		_version = version;
	}

	public String getDesignerVersion() {
		return _designerVersion;
	}

	public void setDesignerVersion(String designerVersion) {
		_designerVersion = designerVersion;
	}

	public String getModelXml() {
		return _modelXml;
	}

	public void setModelXml(String modelXml) {
		_modelXml = modelXml;
	}

	public String getGraphicalXml() {
		return _graphicalXml;
	}

	public void setGraphicalXml(String graphicalXml) {
		_graphicalXml = graphicalXml;
	}

	public boolean getPersistent() {
		return _persistent;
	}

	public boolean isPersistent() {
		return _persistent;
	}

	public void setPersistent(boolean persistent) {
		_persistent = persistent;
	}

	private long _workflowDefinitionId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private double _version;
	private String _designerVersion;
	private String _modelXml;
	private String _graphicalXml;
	private boolean _persistent;
}