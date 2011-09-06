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

package com.liferay.ddlform.portlet;

import com.liferay.ddlform.DuplicateSubmissionException;
import com.liferay.ddlform.util.DDLFormUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.dynamicdatalists.model.DDLRecord;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordSet;
import com.liferay.portlet.dynamicdatalists.service.DDLRecordServiceUtil;
import com.liferay.portlet.dynamicdatalists.service.DDLRecordSetLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.storage.Field;
import com.liferay.portlet.dynamicdatamapping.storage.FieldConstants;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.Serializable;

import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Marcellus Tavares
 */
public class DDLFormPortlet extends MVCPortlet {

	public void saveData(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long recordSetId = ParamUtil.getLong(actionRequest, "recordSetId");

		validate(recordSetId, actionRequest);

		DDLRecordSet recordSet = DDLRecordSetLocalServiceUtil.getRecordSet(
			recordSetId);

		DDMStructure ddmStructure = recordSet.getDDMStructure();

		Set<String> fieldNames = ddmStructure.getFieldNames();

		Fields fields = new Fields();

		for (String fieldName : fieldNames) {
			Field field = new Field();

			field.setName(fieldName);

			String fieldDataType = ddmStructure.getFieldDataType(fieldName);

			String fieldValue = ParamUtil.getString(actionRequest, fieldName);

			Serializable fieldValueSerializable =
				FieldConstants.getSerializable(fieldDataType, fieldValue);

			field.setValue(fieldValueSerializable);

			fields.put(field);
		}

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			DDLRecord.class.getName(), actionRequest);

		DDLRecordServiceUtil.addRecord(
			themeDisplay.getScopeGroupId(), recordSetId, 0, fields,
			serviceContext);
	}

	protected void validate(long recordSetId, ActionRequest actionRequest)
		throws PortalException, SystemException {

		boolean multipleSubmissions = ParamUtil.getBoolean(
			actionRequest, "multipleSubmissions");

		if (!multipleSubmissions &&
			DDLFormUtil.hasSubmitted(actionRequest, recordSetId)) {

			throw new DuplicateSubmissionException();
		}
	}

}