/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

package com.liferay.jbpm.util;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import org.jbpm.context.def.VariableAccess;

/**
 * <a href="TaskFormElement.java.html"><b><i>View Source</i></b></a>
 *
 * @author Charles May
 *
 */
public class TaskFormElement implements Serializable {

	public static final String TYPE_CHECKBOX = "checkbox";

	public static final String TYPE_DATE = "date";

	public static final String TYPE_EMAIL = "email";

	public static final String TYPE_NUMBER = "number";

	public static final String TYPE_PASSWORD = "password";

	public static final String TYPE_PHONE = "phone";

	public static final String TYPE_RADIO = "radio";

	public static final String TYPE_SELECT = "select";

	public static final String TYPE_TEXT = "text";

	public static final String TYPE_TEXTAREA = "textarea";

	public TaskFormElement() {
	}

	public TaskFormElement(VariableAccess variableAccess, String value) {
		_variableName = variableAccess.getVariableName();

		String[] dataElements = StringUtil.split(
			_variableName, StringPool.COLON);

		_type = dataElements[0];
		_displayName = dataElements[1];

		if (_type.equals(TYPE_CHECKBOX) || _type.equals(TYPE_RADIO) ||
			_type.equals(TYPE_SELECT)) {

			String[] values = StringUtil.split(
				dataElements[2], StringPool.COMMA);

			for (int i = 0; i < values.length; i++) {
				_valueList.add(values[i]);
			}
		}

		_value = value;
		_readable = variableAccess.isReadable();
		_writable = variableAccess.isWritable();
		_required = variableAccess.isRequired();
	}

	public String getDisplayName() {
		return _displayName;
	}

	public void setDisplayName(String displayName) {
		_displayName = displayName;
	}

	public String getDateName() {
		if (Validator.isNotNull(_displayName)) {
			return StringUtil.replace(
				_displayName, StringPool.DASH, StringPool.UNDERLINE);
		}
		else {
			return _displayName;
		}
	}

	public String getVariableName() {
		return _variableName;
	}

	public void setVariableName(String variableName) {
		_variableName = variableName;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	public String getValue() {
		return _value;
	}

	public void setValue(String value) {
		_value = value;
	}

	public List getValueList() {
		return _valueList;
	}

	public void setValueList(List valueList) {
		_valueList = valueList;
	}

	public boolean isReadable() {
		return _readable;
	}

	public void setReadable(boolean readable) {
		_readable = readable;
	}

	public boolean isRequired() {
		return _required;
	}

	public void setRequired(boolean required) {
		_required = required;
	}

	public boolean isWritable() {
		return _writable;
	}

	public void setWritable(boolean writable) {
		_writable = writable;
	}

	public boolean isReadOnly() {
		return !_writable;
	}

	private String _displayName;
	private String _variableName;
	private String _type;
	private String _value;
	private List _valueList = new ArrayList();
	private boolean _readable = true;
	private boolean _writable = true;
	private boolean _required = true;

}