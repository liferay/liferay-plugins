/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.jbpm;

import org.jbpm.graph.exe.ProcessInstance;

/**
 * <a href="ProcessInstanceExtensionImpl.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Marcellus Tavares
 */
public class ProcessInstanceExtensionImpl {

	public ProcessInstanceExtensionImpl() {
	}

	public ProcessInstanceExtensionImpl(
		long companyId, long groupId, long userId, String className,
		long classPK, ProcessInstance processInstance) {

		_companyId = companyId;
		_groupId = groupId;
		_userId = userId;
		_className = className;
		_classPK = classPK;
		_processInstance = processInstance;
	}

	public String getClassName() {
		return _className;
	}

	public long getClassPK() {
		return _classPK;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public ProcessInstance getProcessInstance() {
		return _processInstance;
	}

	public long getProcessInstanceExtensionId() {
		return _processInstanceExtensionId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setClassName(String className) {
		_className = className;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public void setProcessInstance(ProcessInstance processInstance) {
		_processInstance = processInstance;
	}

	public void setProcessInstanceExtensionId(long processInstanceExtensionId) {
		_processInstanceExtensionId = processInstanceExtensionId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	private String _className;
	private long _classPK;
	private long _companyId;
	private long _groupId;
	private ProcessInstance _processInstance;
	private long _processInstanceExtensionId;
	private long _userId;

}