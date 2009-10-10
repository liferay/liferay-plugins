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
 * <a href="WorkflowJobSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowJobSoap implements Serializable {
	public static WorkflowJobSoap toSoapModel(WorkflowJob model) {
		WorkflowJobSoap soapModel = new WorkflowJobSoap();

		soapModel.setWorkflowJobId(model.getWorkflowJobId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setSetupId(model.getSetupId());
		soapModel.setWorkflowDefinitionId(model.getWorkflowDefinitionId());
		soapModel.setWorkflowInstanceId(model.getWorkflowInstanceId());
		soapModel.setElementName(model.getElementName());
		soapModel.setCause(model.getCause());
		soapModel.setDueDate(model.getDueDate());
		soapModel.setNotBeforeDate(model.getNotBeforeDate());
		soapModel.setExceptionCount(model.getExceptionCount());

		return soapModel;
	}

	public static WorkflowJobSoap[] toSoapModels(WorkflowJob[] models) {
		WorkflowJobSoap[] soapModels = new WorkflowJobSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WorkflowJobSoap[][] toSoapModels(WorkflowJob[][] models) {
		WorkflowJobSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WorkflowJobSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WorkflowJobSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WorkflowJobSoap[] toSoapModels(List<WorkflowJob> models) {
		List<WorkflowJobSoap> soapModels = new ArrayList<WorkflowJobSoap>(models.size());

		for (WorkflowJob model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WorkflowJobSoap[soapModels.size()]);
	}

	public WorkflowJobSoap() {
	}

	public long getPrimaryKey() {
		return _workflowJobId;
	}

	public void setPrimaryKey(long pk) {
		setWorkflowJobId(pk);
	}

	public long getWorkflowJobId() {
		return _workflowJobId;
	}

	public void setWorkflowJobId(long workflowJobId) {
		_workflowJobId = workflowJobId;
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

	public String getSetupId() {
		return _setupId;
	}

	public void setSetupId(String setupId) {
		_setupId = setupId;
	}

	public long getWorkflowDefinitionId() {
		return _workflowDefinitionId;
	}

	public void setWorkflowDefinitionId(long workflowDefinitionId) {
		_workflowDefinitionId = workflowDefinitionId;
	}

	public long getWorkflowInstanceId() {
		return _workflowInstanceId;
	}

	public void setWorkflowInstanceId(long workflowInstanceId) {
		_workflowInstanceId = workflowInstanceId;
	}

	public String getElementName() {
		return _elementName;
	}

	public void setElementName(String elementName) {
		_elementName = elementName;
	}

	public String getCause() {
		return _cause;
	}

	public void setCause(String cause) {
		_cause = cause;
	}

	public Date getDueDate() {
		return _dueDate;
	}

	public void setDueDate(Date dueDate) {
		_dueDate = dueDate;
	}

	public Date getNotBeforeDate() {
		return _notBeforeDate;
	}

	public void setNotBeforeDate(Date notBeforeDate) {
		_notBeforeDate = notBeforeDate;
	}

	public int getExceptionCount() {
		return _exceptionCount;
	}

	public void setExceptionCount(int exceptionCount) {
		_exceptionCount = exceptionCount;
	}

	private long _workflowJobId;
	private long _companyId;
	private Date _createDate;
	private String _setupId;
	private long _workflowDefinitionId;
	private long _workflowInstanceId;
	private String _elementName;
	private String _cause;
	private Date _dueDate;
	private Date _notBeforeDate;
	private int _exceptionCount;
}