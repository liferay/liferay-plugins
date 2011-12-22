/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.kaleo;

import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.workflow.DefaultWorkflowDefinition;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinition;

/**
 * @author Michael C. Han
 */
public class WorkflowDefinitionAdapter extends DefaultWorkflowDefinition {

	public WorkflowDefinitionAdapter(KaleoDefinition kaleoDefinition) {
		setActive(kaleoDefinition.getActive());
		setContent(kaleoDefinition.getContent());
		setName(kaleoDefinition.getName());
		setTitle(kaleoDefinition.getTitle());
		setVersion(kaleoDefinition.getVersion());
	}

	@Override
	public String getTitle(String languageId) {
		return LocalizationUtil.getLocalization(getTitle(), languageId);
	}

}