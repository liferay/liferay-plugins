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
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * The implementation of the mobile widgets d d l remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.mobilewidgets.service.MobileWidgetsDDLService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Jose Manuel Navarro
 * @see com.liferay.mobilewidgets.service.base.MobileWidgetsDDLServiceBaseImpl
 * @see com.liferay.mobilewidgets.service.MobileWidgetsDDLServiceUtil
 */
public class MobileWidgetsDDLServiceImpl
	extends MobileWidgetsDDLServiceBaseImpl {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.mobilewidgets.service.MobileWidgetsDDLServiceUtil} to access the mobile widgets d d l remote service.
	 */

	public Map<String, String> getDDLRecordValues(long recordId, Locale locale)
		throws PortalException, SystemException {

		Map<String, String> recordValues = new HashMap<String, String>();

		DDLRecord record = ddlRecordLocalService.getDDLRecord(recordId);

		Fields fields = record.getFields();

		Set<Locale> availableLocales = fields.getAvailableLocales();

		if ((locale == null) || !availableLocales.contains(locale)) {
			locale = fields.getDefaultLocale();
		}

		Iterator<Field> fieldsIterator = fields.iterator();

		while (fieldsIterator.hasNext()) {
			Field currentField = fieldsIterator.next();

			String fieldType = currentField.getType();
			String fieldValue;

			if (fieldType.indexOf(FieldConstants.DATE) != -1) {
				fieldValue = currentField.getRenderedValue(locale);
			}
			else {
				fieldValue = String.valueOf(currentField.getValue(locale));
			}

			recordValues.put(currentField.getName(), fieldValue);
		}

		return recordValues;
	}

}