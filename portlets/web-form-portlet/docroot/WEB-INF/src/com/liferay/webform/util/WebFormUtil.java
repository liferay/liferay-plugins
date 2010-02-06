/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.webform.util;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.mozilla.javascript.Context;
import com.liferay.mozilla.javascript.Scriptable;
import com.liferay.mozilla.javascript.ScriptableObject;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.expando.NoSuchTableException;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoRowLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.util.portlet.PortletProps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * <a href="WebFormUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Daniel Weisser
 * @author Jorge Ferrer
 * @author Alberto Montero
 * @author Julio Camarero
 * @author Brian Wing Shun Chan
 *
 */
public class WebFormUtil {

	public static final boolean VALIDATION_SCRIPT_ENABLED =
		GetterUtil.getBoolean(PortletProps.get("validation.script.enabled"));

	public static ExpandoTable addTable(String tableName)
		throws PortalException, SystemException {

		try {
			ExpandoTableLocalServiceUtil.deleteTable(
				WebFormUtil.class.getName(), tableName);
		}
		catch (NoSuchTableException nste) {
		}

		return ExpandoTableLocalServiceUtil.addTable(
			WebFormUtil.class.getName(), tableName);
	}

	public static ExpandoTable checkTable(
			String tableName, PortletPreferences preferences)
		throws Exception {

		ExpandoTable expandoTable = null;

		try {
			expandoTable = ExpandoTableLocalServiceUtil.getTable(
				WebFormUtil.class.getName(), tableName);
		}
		catch (NoSuchTableException nste) {
			expandoTable = addTable(tableName);

			int i = 1;

			String fieldLabel = preferences.getValue(
				"fieldLabel" + i, StringPool.BLANK);

			while ((i == 1) || (Validator.isNotNull(fieldLabel))) {
				ExpandoColumnLocalServiceUtil.addColumn(
					expandoTable.getTableId(), fieldLabel,
					ExpandoColumnConstants.STRING);

				i++;

				fieldLabel = preferences.getValue(
					"fieldLabel" + i, StringPool.BLANK);
			}
		}

		return expandoTable;
	}

	public static String getNewDatabaseTableName(String portletId)
		throws SystemException {

		long formId = CounterLocalServiceUtil.increment(
			WebFormUtil.class.getName());

		return portletId + StringPool.UNDERLINE + formId;
	}

	public static int getTableRowsCount(String tableName)
		throws SystemException {

		return ExpandoRowLocalServiceUtil.getRowsCount(
			WebFormUtil.class.getName(), tableName);
	}

	public static String[] split(String s) {
		return split(s, StringPool.COMMA);
	}

	public static String[] split(String s, String delimiter) {
		if (s == null || delimiter == null) {
			return new String[0];
		}

		s = s.trim();

		if (!s.endsWith(delimiter)) {
			StringBuilder sb = new StringBuilder();

			sb.append(s);
			sb.append(delimiter);

			s = sb.toString();
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
			String currentFieldValue, Map<String,String> fieldsMap,
			String validationScript)
		throws Exception {

		boolean validationResult = false;

		Context context = Context.enter();

		StringBuilder sb = new StringBuilder();

		sb.append("currentFieldValue = String('" + currentFieldValue + "');\n");

		sb.append("var fieldsMap = {};\n");

		for (String key : fieldsMap.keySet()) {
			sb.append("fieldsMap['");
			sb.append(key);
			sb.append("'] = '");
			sb.append(fieldsMap.get(key));
			sb.append("';\n");
		}

		sb.append("function validation(currentFieldValue, fieldsMap) {\n");
		sb.append(validationScript);
		sb.append("};\n");
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
				validationResult = ((Boolean)obj).booleanValue();
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