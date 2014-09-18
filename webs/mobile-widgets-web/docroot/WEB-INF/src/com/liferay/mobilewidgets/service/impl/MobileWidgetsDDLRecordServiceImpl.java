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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import com.liferay.mobilewidgets.service.base.MobileWidgetsDDLRecordServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.dynamicdatalists.model.DDLRecord;
import com.liferay.portlet.dynamicdatamapping.storage.Field;
import com.liferay.portlet.dynamicdatamapping.storage.FieldConstants;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;

/**
 * @author José Manuel Navarro
 */
public class MobileWidgetsDDLRecordServiceImpl
	extends MobileWidgetsDDLRecordServiceBaseImpl {

	public int getDDLRecordsCount(long recordSetId, long userId)
			throws SystemException {

		return ddlRecordPersistence.countByR_U(recordSetId, userId);
	}

	public List<Map<String, String>> getDDLRecords(
				long recordSetId, long userId, int start, int end,
				Locale locale)
			throws PortalException, SystemException {

		List<DDLRecord> records = ddlRecordPersistence.findByR_U(
			recordSetId, userId, start, end);

		List<Map<String, String>> recordValuesMaps =
			new ArrayList<Map<String, String>>(records.size());

		for (DDLRecord record : records) {
			Map<String, String> recordValues = getDDLRecordValues(
				record.getRecordId(), locale);

			recordValuesMaps.add(recordValues);
		}

		return recordValuesMaps;
	}

	public Map<String, String> getDDLRecordValues(
			long ddlRecordId, Locale locale)
		throws PortalException, SystemException {

		Map<String, String> ddlRecordValues = new HashMap<String, String>();

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

			ddlRecordValues.put(field.getName(), fieldValue);
		}

		return ddlRecordValues;
	}

	protected boolean isDateField(String fieldType) {
		if (fieldType.indexOf(FieldConstants.DATE) != -1) {
			return true;
		}

		return false;
	}

}