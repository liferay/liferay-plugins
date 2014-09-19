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

package com.liferay.mobilewidgets.service.impl;

import com.liferay.mobilewidgets.service.base.MobileWidgetsDDLRecordServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portlet.dynamicdatalists.model.DDLRecord;
import com.liferay.portlet.dynamicdatamapping.storage.Field;
import com.liferay.portlet.dynamicdatamapping.storage.FieldConstants;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;

import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * @author José Manuel Navarro
 */
public class MobileWidgetsDDLRecordServiceImpl
	extends MobileWidgetsDDLRecordServiceBaseImpl {

	@Override
	public JSONObject getDDLRecord(long ddlRecordId, Locale locale)
		throws PortalException, SystemException {

		JSONObject ddlRecordJSONObject = JSONFactoryUtil.createJSONObject();

		DDLRecord ddlRecord = ddlRecordPersistence.findByPrimaryKey(
			ddlRecordId);

		Fields fields = ddlRecord.getFields();

		Set<Locale> availableLocales = fields.getAvailableLocales();

		if ((locale == null) || !availableLocales.contains(locale)) {
			locale = fields.getDefaultLocale();
		}

		for (Field field : fields) {
			String fieldValue = String.valueOf(field.getValue(locale));

			if (isDateField(field.getType())) {
				fieldValue = field.getRenderedValue(locale);
			}

			ddlRecordJSONObject.put(field.getName(), fieldValue);
		}

		return ddlRecordJSONObject;
	}

	@Override
	public JSONArray getDDLRecords(
			long ddlRecordSetId, long userId, Locale locale, int start, int end)
		throws PortalException, SystemException {

		JSONArray ddlRecordsJSONArray = JSONFactoryUtil.createJSONArray();

		List<DDLRecord> ddlRecords = ddlRecordPersistence.findByR_U(
			ddlRecordSetId, userId, start, end);

		for (DDLRecord ddlRecord : ddlRecords) {
			JSONObject ddlRecordJSONObject = getDDLRecord(
				ddlRecord.getRecordId(), locale);

			ddlRecordJSONObject.put(
				"modelAttributes",
				JSONFactoryUtil.looseSerialize(ddlRecord.getModelAttributes()));

			ddlRecordsJSONArray.put(ddlRecordJSONObject);
		}

		return ddlRecordsJSONArray;
	}

	@Override
	public int getDDLRecordsCount(long ddlRecordSetId, long userId)
		throws SystemException {

		return ddlRecordPersistence.countByR_U(ddlRecordSetId, userId);
	}

	protected boolean isDateField(String fieldType) {
		if (fieldType.indexOf(FieldConstants.DATE) != -1) {
			return true;
		}

		return false;
	}

}