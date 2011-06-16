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

package com.liferay.portal.workflow.jbpm;

import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.workflow.DefaultWorkflowDefinition;

import org.jbpm.graph.def.ProcessDefinition;

/**
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class WorkflowDefinitionImpl extends DefaultWorkflowDefinition {

	public WorkflowDefinitionImpl(
		ProcessDefinition processDefinition, String title, boolean active) {

		setActive(active);
		setName(processDefinition.getName());
		setTitle(title);
		setVersion(processDefinition.getVersion());
	}

	@Override
	public String getTitle(String languageId) {
		return LocalizationUtil.getLocalization(getTitle(), languageId);
	}

}