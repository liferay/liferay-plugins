/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.kaleo.parser;

import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.workflow.kaleo.definition.Definition;
import com.liferay.portal.workflow.kaleo.definition.Join;

/**
 * @author Michael C. Han
 * @author Marcellus Tavares
 */
public class JoinNodeValidator extends BaseNodeValidator<Join> {

	@Override
	protected void doValidate(Definition definition, Join join)
		throws WorkflowException {

		if (join.getIncomingTransitionsCount() == 0) {
			throw new WorkflowException(
				"No incoming transition found for join " + join.getName());
		}

		if (join.getOutgoingTransitionsCount() == 0) {
			throw new WorkflowException(
				"No outgoing transition found for join " + join.getName());
		}
	}

}