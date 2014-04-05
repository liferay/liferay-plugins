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

package com.liferay.portal.workflow.kaleo;

import com.liferay.compat.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.DefaultWorkflowDefinition;
import com.liferay.portal.workflow.kaleo.export.DefinitionExporterUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinition;
import com.liferay.portal.workflow.kaleo.service.KaleoDefinitionLocalServiceUtil;

/**
 * @author Michael C. Han
 */
public class WorkflowDefinitionAdapter extends DefaultWorkflowDefinition {

	public WorkflowDefinitionAdapter(KaleoDefinition kaleoDefinition) {
		setActive(kaleoDefinition.getActive());

		String content = kaleoDefinition.getContent();

		if (Validator.isNull(content)) {
			try {
				content = DefinitionExporterUtil.export(
					kaleoDefinition.getKaleoDefinitionId());

				kaleoDefinition.setContent(content);

				KaleoDefinitionLocalServiceUtil.updateKaleoDefinition(
					kaleoDefinition);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn("Unable to export definition to string", e);
				}
			}
		}

		setContent(content);

		setName(kaleoDefinition.getName());
		setTitle(kaleoDefinition.getTitle());
		setVersion(kaleoDefinition.getVersion());
	}

	@Override
	public String getTitle(String languageId) {
		return LocalizationUtil.getLocalization(getTitle(), languageId);
	}

	private static Log _log = LogFactoryUtil.getLog(
		WorkflowDefinitionAdapter.class);

}