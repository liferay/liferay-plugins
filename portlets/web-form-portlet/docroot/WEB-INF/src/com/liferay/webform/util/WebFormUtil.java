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

package com.liferay.webform.util;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.NoSuchTableException;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoRowLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

/**
 * @author Daniel Weisser
 * @author Jorge Ferrer
 * @author Alberto Montero
 * @author Julio Camarero
 * @author Brian Wing Shun Chan
 */
public class WebFormUtil {

	public static ExpandoTable addTable(long companyId, String tableName)
		throws PortalException, SystemException {

		try {
			ExpandoTableLocalServiceUtil.deleteTable(
				companyId, WebFormUtil.class.getName(), tableName);
		}
		catch (NoSuchTableException nste) {
		}

		return ExpandoTableLocalServiceUtil.addTable(
			companyId, WebFormUtil.class.getName(), tableName);
	}

	public static ExpandoTable checkTable(
			long companyId, String tableName, PortletPreferences preferences)
		throws Exception {

		ExpandoTable expandoTable = null;

		try {
			expandoTable = ExpandoTableLocalServiceUtil.getTable(
				companyId, WebFormUtil.class.getName(), tableName);
		}
		catch (NoSuchTableException nste) {
			expandoTable = addTable(companyId, tableName);

			int i = 1;

			String fieldLabel = preferences.getValue(
				"fieldLabel" + i, StringPool.BLANK);
			String fieldType = preferences.getValue(
				"fieldType" + i, StringPool.BLANK);

			while ((i == 1) || Validator.isNotNull(fieldLabel)) {
				if (!StringUtil.equalsIgnoreCase(fieldType, "paragraph")) {
					ExpandoColumnLocalServiceUtil.addColumn(
						expandoTable.getTableId(), fieldLabel,
						ExpandoColumnConstants.STRING);
				}

				i++;

				fieldLabel = preferences.getValue(
					"fieldLabel" + i, StringPool.BLANK);
				fieldType = preferences.getValue(
					"fieldType" + i, StringPool.BLANK);
			}
		}

		return expandoTable;
	}

	public static String getEmailFromAddress(
			PortletPreferences preferences, long companyId)
		throws SystemException {

		return PortalUtil.getEmailFromAddress(
			preferences, companyId, PortletPropsValues.EMAIL_FROM_ADDRESS);
	}

	public static String getEmailFromName(
			PortletPreferences preferences, long companyId)
		throws SystemException {

		return PortalUtil.getEmailFromName(
			preferences, companyId, PortletPropsValues.EMAIL_FROM_NAME);
	}

	public static String getFileName(
		ThemeDisplay themeDisplay, String portletId) {

		StringBuffer sb = new StringBuffer(8);

		sb.append(PortletPropsValues.DATA_ROOT_DIR);
		sb.append(StringPool.FORWARD_SLASH);
		sb.append(themeDisplay.getScopeGroupId());
		sb.append(StringPool.FORWARD_SLASH);
		sb.append(themeDisplay.getPlid());
		sb.append(StringPool.FORWARD_SLASH);
		sb.append(portletId);
		sb.append(".csv");

		return sb.toString();
	}

	public static String getNewDatabaseTableName(String portletId)
		throws SystemException {

		long formId = CounterLocalServiceUtil.increment(
			WebFormUtil.class.getName());

		return portletId + StringPool.UNDERLINE + formId;
	}

	public static int getTableRowsCount(long companyId, String tableName)
		throws SystemException {

		return ExpandoRowLocalServiceUtil.getRowsCount(
			companyId, WebFormUtil.class.getName(), tableName);
	}

	public static String[] split(String s) {
		return split(s, StringPool.COMMA);
	}

	public static String[] split(String s, String delimiter) {
		if ((s == null) || (delimiter == null)) {
			return new String[0];
		}

		s = s.trim();

		if (!s.endsWith(delimiter)) {
			s = s.concat(delimiter);
		}

		if (s.equals(delimiter)) {
			return new String[0];
		}

		List<String> nodeValues = new ArrayList<String>();

		if (delimiter.equals("\n") || delimiter.equals("\r")) {
			try {
				BufferedReader br = new BufferedReader(new StringReader(s));

				String line = null;

				while ((line = br.readLine()) != null) {
					nodeValues.add(line);
				}

				br.close();
			}
			catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		else {
			int offset = 0;
			int pos = s.indexOf(delimiter, offset);

			while (pos != -1) {
				nodeValues.add(new String(s.substring(offset, pos)));

				offset = pos + delimiter.length();
				pos = s.indexOf(delimiter, offset);
			}
		}

		return nodeValues.toArray(new String[nodeValues.size()]);
	}

	public static boolean validate(
			String currentFieldValue, Map<String, String> fieldsMap,
			String validationScript)
		throws Exception {

		boolean validationResult = false;

		Context context = Context.enter();

		StringBundler sb = new StringBundler();

		sb.append("currentFieldValue = String('");
		sb.append(HtmlUtil.escapeJS(currentFieldValue));
		sb.append("');\n");

		sb.append("var fieldsMap = {};\n");

		for (String key : fieldsMap.keySet()) {
			sb.append("fieldsMap['");
			sb.append(key);
			sb.append("'] = '");

			String value = StringUtil.replace(
				fieldsMap.get(key), new String[] {"\r\n", "\r", "\n"},
				new String[] {"\\n", "\\n", "\\n"});

			sb.append(HtmlUtil.escapeJS(value));
			sb.append("';\n");
		}

		sb.append("function validation(currentFieldValue, fieldsMap) {\n");
		sb.append(validationScript);
		sb.append("}\n");
		sb.append("internalValidationResult = ");
		sb.append("validation(currentFieldValue, fieldsMap);");

		String script = sb.toString();

		try {
			Scriptable scope = context.initStandardObjects();

			Object jsFieldsMap = Context.javaToJS(fieldsMap, scope);

			ScriptableObject.putProperty(scope, "jsFieldsMap", jsFieldsMap);

			context.evaluateString(scope, script, "Validation Script", 1, null);

			Object obj = ScriptableObject.getProperty(
				scope, "internalValidationResult");

			if (obj instanceof Boolean) {
				validationResult = (Boolean)obj;
			}
			else {
				throw new Exception("The script must return a boolean value");
			}
		}
		catch (Exception e) {
			String msg =
				"The following script has execution errors:\n" +
					validationScript + "\n" + e.getMessage();

			_log.error(msg);

			throw new Exception(msg, e);
		}
		finally {
			Context.exit();
		}

		return validationResult;
	}

	private static Log _log = LogFactoryUtil.getLog(WebFormUtil.class);

}