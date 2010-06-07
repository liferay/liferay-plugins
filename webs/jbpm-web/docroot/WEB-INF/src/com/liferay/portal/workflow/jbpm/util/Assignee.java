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

package com.liferay.portal.workflow.jbpm.util;

/**
 * <a href="Assignee.java.html"><b><i>View Source</i></b></a>
 *
 * @author Marcellus Tavares
 */
public class Assignee {

	public String getAssigneeClassName() {
		return _assigneeClassName;
	}

	public long getAssigneeClassPK() {
		return _assigneeClassPK;
	}

	public void setAssigneeClassName(String assigneeClassName) {
		_assigneeClassName = assigneeClassName;
	}

	public void setAssigneeClassPK(long assigneeClassPK) {
		_assigneeClassPK = assigneeClassPK;
	}

	private String _assigneeClassName;
	private long _assigneeClassPK;

}