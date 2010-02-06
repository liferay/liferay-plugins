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

package com.liferay.socialcoding.model;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

/**
 * <a href="JIRAChangeItemClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class JIRAChangeItemClp extends BaseModelImpl<JIRAChangeItem>
	implements JIRAChangeItem {
	public JIRAChangeItemClp() {
	}

	public long getPrimaryKey() {
		return _jiraChangeItemId;
	}

	public void setPrimaryKey(long pk) {
		setJiraChangeItemId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_jiraChangeItemId);
	}

	public long getJiraChangeItemId() {
		return _jiraChangeItemId;
	}

	public void setJiraChangeItemId(long jiraChangeItemId) {
		_jiraChangeItemId = jiraChangeItemId;
	}

	public long getJiraChangeGroupId() {
		return _jiraChangeGroupId;
	}

	public void setJiraChangeGroupId(long jiraChangeGroupId) {
		_jiraChangeGroupId = jiraChangeGroupId;
	}

	public String getField() {
		return _field;
	}

	public void setField(String field) {
		_field = field;
	}

	public String getOldValue() {
		return _oldValue;
	}

	public void setOldValue(String oldValue) {
		_oldValue = oldValue;
	}

	public String getOldString() {
		return _oldString;
	}

	public void setOldString(String oldString) {
		_oldString = oldString;
	}

	public String getNewValue() {
		return _newValue;
	}

	public void setNewValue(String newValue) {
		_newValue = newValue;
	}

	public String getNewString() {
		return _newString;
	}

	public void setNewString(String newString) {
		_newString = newString;
	}

	public JIRAChangeItem toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			JIRAChangeItem model = new JIRAChangeItemClp();

			model.setEscapedModel(true);

			model.setJiraChangeItemId(getJiraChangeItemId());
			model.setJiraChangeGroupId(getJiraChangeGroupId());
			model.setField(HtmlUtil.escape(getField()));
			model.setOldValue(HtmlUtil.escape(getOldValue()));
			model.setOldString(HtmlUtil.escape(getOldString()));
			model.setNewValue(HtmlUtil.escape(getNewValue()));
			model.setNewString(HtmlUtil.escape(getNewString()));

			model = (JIRAChangeItem)Proxy.newProxyInstance(JIRAChangeItem.class.getClassLoader(),
					new Class[] { JIRAChangeItem.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public Object clone() {
		JIRAChangeItemClp clone = new JIRAChangeItemClp();

		clone.setJiraChangeItemId(getJiraChangeItemId());
		clone.setJiraChangeGroupId(getJiraChangeGroupId());
		clone.setField(getField());
		clone.setOldValue(getOldValue());
		clone.setOldString(getOldString());
		clone.setNewValue(getNewValue());
		clone.setNewString(getNewString());

		return clone;
	}

	public int compareTo(JIRAChangeItem jiraChangeItem) {
		long pk = jiraChangeItem.getPrimaryKey();

		if (getPrimaryKey() < pk) {
			return -1;
		}
		else if (getPrimaryKey() > pk) {
			return 1;
		}
		else {
			return 0;
		}
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		JIRAChangeItemClp jiraChangeItem = null;

		try {
			jiraChangeItem = (JIRAChangeItemClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = jiraChangeItem.getPrimaryKey();

		if (getPrimaryKey() == pk) {
			return true;
		}
		else {
			return false;
		}
	}

	public int hashCode() {
		return (int)getPrimaryKey();
	}

	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{jiraChangeItemId=");
		sb.append(getJiraChangeItemId());
		sb.append(", jiraChangeGroupId=");
		sb.append(getJiraChangeGroupId());
		sb.append(", field=");
		sb.append(getField());
		sb.append(", oldValue=");
		sb.append(getOldValue());
		sb.append(", oldString=");
		sb.append(getOldString());
		sb.append(", newValue=");
		sb.append(getNewValue());
		sb.append(", newString=");
		sb.append(getNewString());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(25);

		sb.append("<model><model-name>");
		sb.append("com.liferay.socialcoding.model.JIRAChangeItem");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>jiraChangeItemId</column-name><column-value><![CDATA[");
		sb.append(getJiraChangeItemId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>jiraChangeGroupId</column-name><column-value><![CDATA[");
		sb.append(getJiraChangeGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>field</column-name><column-value><![CDATA[");
		sb.append(getField());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>oldValue</column-name><column-value><![CDATA[");
		sb.append(getOldValue());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>oldString</column-name><column-value><![CDATA[");
		sb.append(getOldString());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>newValue</column-name><column-value><![CDATA[");
		sb.append(getNewValue());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>newString</column-name><column-value><![CDATA[");
		sb.append(getNewString());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _jiraChangeItemId;
	private long _jiraChangeGroupId;
	private String _field;
	private String _oldValue;
	private String _oldString;
	private String _newValue;
	private String _newString;
}