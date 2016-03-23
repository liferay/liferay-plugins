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

package com.liferay.screens.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ClassResolverUtil;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.PortalClassInvoker;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.dynamicdatalists.model.DDLRecord;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordSet;
import com.liferay.portlet.dynamicdatamapping.storage.Field;
import com.liferay.portlet.dynamicdatamapping.storage.FieldConstants;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;
import com.liferay.screens.service.base.ScreensDDLRecordServiceBaseImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * @author José Manuel Navarro
 */
public class ScreensDDLRecordServiceImpl
	extends ScreensDDLRecordServiceBaseImpl {

	@Override
	public JSONObject getDDLRecord(long ddlRecordId, Locale locale)
		throws PortalException, SystemException {

		DDLRecord ddlRecord = ddlRecordPersistence.findByPrimaryKey(
			ddlRecordId);

		checkPermission(
			getPermissionChecker(), ddlRecord.getRecordSet(), ActionKeys.VIEW);

		Fields fields = ddlRecord.getFields();

		Set<Locale> availableLocales = fields.getAvailableLocales();

		if ((locale == null) || !availableLocales.contains(locale)) {
			locale = fields.getDefaultLocale();
		}

		return getDDLRecordJSONObject(ddlRecord, locale);
	}

	@Override
	public JSONArray getDDLRecords(
			long ddlRecordSetId, Locale locale, int start, int end)
		throws PortalException, SystemException {

		checkPermission(
			getPermissionChecker(), ddlRecordSetId, ActionKeys.VIEW);

		List<DDLRecord> ddlRecords = ddlRecordPersistence.findByRecordSetId(
			ddlRecordSetId, start, end);

		return getDDLRecordsJSONArray(ddlRecords, locale);
	}

	@Override
	public JSONArray getDDLRecords(
			long ddlRecordSetId, long userId, Locale locale, int start, int end)
		throws PortalException, SystemException {

		checkPermission(
			getPermissionChecker(), ddlRecordSetId, ActionKeys.VIEW);

		List<DDLRecord> ddlRecords = ddlRecordPersistence.findByR_U(
			ddlRecordSetId, userId, start, end);

		return getDDLRecordsJSONArray(ddlRecords, locale);
	}

	@Override
	public int getDDLRecordsCount(long ddlRecordSetId) throws SystemException {
		try {
			checkPermission(
				getPermissionChecker(), ddlRecordSetId, ActionKeys.VIEW);
		}
		catch (PortalException pe) {
			throw new SystemException(pe);
		}

		return ddlRecordPersistence.countByRecordSetId(ddlRecordSetId);
	}

	@Override
	public int getDDLRecordsCount(long ddlRecordSetId, long userId)
		throws SystemException {

		try {
			checkPermission(
				getPermissionChecker(), ddlRecordSetId, ActionKeys.VIEW);
		}
		catch (PortalException pe) {
			throw new SystemException(pe);
		}

		return ddlRecordPersistence.countByR_U(ddlRecordSetId, userId);
	}

	protected void checkPermission(
			PermissionChecker permissionChecker, DDLRecordSet recordSet,
			String actionId)
		throws PortalException, SystemException {

		try {
			PortalClassInvoker.invoke(
				false, _checkPermissionMethodKey1, permissionChecker, recordSet,
				actionId);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	protected void checkPermission(
			PermissionChecker permissionChecker, long recordSetId,
			String actionId)
		throws PortalException, SystemException {

		try {
			PortalClassInvoker.invoke(
				false, _checkPermissionMethodKey2, permissionChecker,
				recordSetId, actionId);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	protected JSONObject getDDLRecordJSONObject(
			DDLRecord ddlRecord, Locale locale)
		throws PortalException, SystemException {

		JSONObject ddlRecordJSONObject = JSONFactoryUtil.createJSONObject();

		ddlRecordJSONObject.put(
			"modelAttributes",
			JSONFactoryUtil.createJSONObject(
				JSONFactoryUtil.looseSerialize(
					ddlRecord.getModelAttributes())));

		Map<String, Object> ddlRecordMap = new HashMap<String, Object>();

		Fields fields = ddlRecord.getFields();

		Set<Locale> availableLocales = fields.getAvailableLocales();

		if ((locale == null) || !availableLocales.contains(locale)) {
			locale = fields.getDefaultLocale();
		}

		for (Field field : fields) {
			Object fieldValue = getFieldValue(field, locale);

			if (fieldValue != null) {
				ddlRecordMap.put(field.getName(), fieldValue);
			}
			else {
				for (Locale availableLocale : availableLocales) {
					fieldValue = getFieldValue(field, availableLocale);

					if (fieldValue != null) {
						ddlRecordMap.put(field.getName(), fieldValue);

						break;
					}
				}
			}
		}

		ddlRecordJSONObject.put(
			"modelValues",
			JSONFactoryUtil.createJSONObject(
				JSONFactoryUtil.looseSerialize(ddlRecordMap)));

		return ddlRecordJSONObject;
	}

	protected JSONArray getDDLRecordsJSONArray(
			List<DDLRecord> ddlRecords, Locale locale)
		throws PortalException, SystemException {

		JSONArray ddlRecordsJSONArray = JSONFactoryUtil.createJSONArray();

		for (DDLRecord ddlRecord : ddlRecords) {
			JSONObject ddlRecordJSONObject = getDDLRecordJSONObject(
				ddlRecord, locale);

			ddlRecordsJSONArray.put(ddlRecordJSONObject);
		}

		return ddlRecordsJSONArray;
	}

	protected Object getFieldValue(Field field, Locale locale)
		throws PortalException, SystemException {

		String fieldValueString = StringUtil.valueOf(field.getValue(locale));

		if (fieldValueString == null) {
			return null;
		}

		if (fieldValueString.isEmpty()) {
			return null;
		}

		String dataType = field.getDataType();

		if (dataType.equals(FieldConstants.BOOLEAN)) {
			return Boolean.valueOf(fieldValueString);
		}
		else if (dataType.equals(FieldConstants.DATE)) {
			return field.getRenderedValue(locale);
		}
		else if (dataType.equals(FieldConstants.DOCUMENT_LIBRARY)) {
			return JSONFactoryUtil.looseSerialize(
				JSONFactoryUtil.looseDeserialize(fieldValueString));
		}
		else if (dataType.equals(FieldConstants.FLOAT) ||
				 dataType.equals(FieldConstants.NUMBER)) {

			if (Validator.isNull(fieldValueString)) {
				return null;
			}

			return Float.valueOf(fieldValueString);
		}
		else if (dataType.equals(FieldConstants.INTEGER)) {
			if (Validator.isNull(fieldValueString)) {
				return null;
			}

			return Integer.valueOf(fieldValueString);
		}
		else if (dataType.equals(FieldConstants.LONG)) {
			if (Validator.isNull(fieldValueString)) {
				return null;
			}

			return Long.valueOf(fieldValueString);
		}
		else if (dataType.equals(FieldConstants.SHORT)) {
			if (Validator.isNull(fieldValueString)) {
				return null;
			}

			return Short.valueOf(fieldValueString);
		}

		return fieldValueString;
	}

	private static final MethodKey _checkPermissionMethodKey1 =
		new MethodKey(
			ClassResolverUtil.resolveByPortalClassLoader(
				"com.liferay.portlet.dynamicdatalists.service.permission." +
					"DDLRecordSetPermission"),
			"check", PermissionChecker.class, DDLRecordSet.class, String.class);
	private static final MethodKey _checkPermissionMethodKey2 =
		new MethodKey(
			ClassResolverUtil.resolveByPortalClassLoader(
				"com.liferay.portlet.dynamicdatalists.service.permission." +
					"DDLRecordSetPermission"),
			"check", PermissionChecker.class, long.class, String.class);

	private static Log _log = LogFactoryUtil.getLog(
		ScreensDDLRecordServiceImpl.class);

}