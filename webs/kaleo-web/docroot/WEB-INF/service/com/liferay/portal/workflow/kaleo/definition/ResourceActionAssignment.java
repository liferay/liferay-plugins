/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.kaleo.definition;

/**
 * @author Marcellus Tavares
 */
public class ResourceActionAssignment extends Assignment {

	public ResourceActionAssignment() {
		super(AssignmentType.RESOURCE_ACTION);
	}

	public void configureParent(DefinitionNode parentNode) {
		ResourceActionAssignments resourceActionAssignments =
			(ResourceActionAssignments)parentNode;

		resourceActionAssignments.addResourceActionAssignment(this);
	}

	public String getActionId() {
		return _actionId;
	}

	public void setValue(String value) {
		_actionId = value;
	}

	private String _actionId;

}