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

import java.util.HashSet;
import java.util.Set;

/**
 * @author Marcellus Tavares
 */
public class RoleAssignments extends DefinitionNode {

	public void addRoleAssignment(RoleAssignment roleAssignment) {
		_roleAssignments.add(roleAssignment);
	}

	public void configureParent(DefinitionNode parentNode) {
		if (parentNode instanceof Recipients) {
			Recipients recipients = (Recipients)parentNode;

			for (RoleAssignment roleAssignment : _roleAssignments) {
				RoleRecipient roleRecipient = null;

				if (roleAssignment.getRoleId() > 0) {
					roleRecipient = new RoleRecipient(
						roleAssignment.getRoleId(),
						roleAssignment.getRoleType());
				}
				else {
					roleRecipient = new RoleRecipient(
						roleAssignment.getRoleName(),
						roleAssignment.getRoleType());

					roleRecipient.setAutoCreate(roleAssignment.isAutoCreate());
				}

				recipients.addRecipient(roleRecipient);
			}
		}
		else if (parentNode instanceof Assignments) {
			Assignments assignments = (Assignments)parentNode;

			for (RoleAssignment roleAssignment : _roleAssignments) {
				assignments.addAssignment(roleAssignment);
			}
		}
	}

	private Set<RoleAssignment> _roleAssignments =
		new HashSet<RoleAssignment>();

}