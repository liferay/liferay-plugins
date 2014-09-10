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

import com.liferay.mobilewidgets.service.base.MobileWidgetsDDLServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.dynamicdatalists.model.DDLRecord;
import com.liferay.portlet.dynamicdatamapping.storage.Field;
import com.liferay.portlet.dynamicdatamapping.storage.FieldConstants;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * @author Jose Manuel Navarro
 */
public class MobileWidgetsDDLServiceImpl
	extends MobileWidgetsDDLServiceBaseImpl {

	public Map<String, String> getDDLRecordValues(long recordId, Locale locale)
		throws PortalException, SystemException {

		Map<String, String> recordValues = new HashMap<String, String>();

		DDLRecord record = ddlRecordLocalService.getDDLRecord(recordId);

		Fields fields = record.getFields();

		Set<Locale> availableLocales = fields.getAvailableLocales();

		if ((locale == null) || !availableLocales.contains(locale)) {
			locale = fields.getDefaultLocale();
		}

		for (Field field : fields) {
			String fieldValue = String.valueOf(field.getValue(locale));

			if (isDateField(field.getType())) {
				fieldValue = field.getRenderedValue(locale);
			}

			recordValues.put(field.getName(), fieldValue);
		}

		return recordValues;
	}

	protected boolean isDateField(String fieldType) {
		if (fieldType.indexOf(FieldConstants.DATE) != -1) {
			return true;
		}

		return false;
	}

}