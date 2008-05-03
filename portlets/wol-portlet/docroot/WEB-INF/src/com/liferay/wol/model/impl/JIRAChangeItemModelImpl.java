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

package com.liferay.wol.model.impl;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;

import com.liferay.portlet.service.BaseModelImpl;
import com.liferay.portlet.service.PropsUtil;

import com.liferay.wol.model.JIRAChangeItem;
import com.liferay.wol.model.JIRAChangeItemSoap;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="JIRAChangeItemModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class JIRAChangeItemModelImpl extends BaseModelImpl {
	public static final String TABLE_NAME = "changeitem";
	public static final Object[][] TABLE_COLUMNS = {
			{ "id", new Integer(Types.BIGINT) },
			

			{ "groupid", new Integer(Types.BIGINT) },
			

			{ "field", new Integer(Types.VARCHAR) },
			

			{ "oldValue", new Integer(Types.VARCHAR) },
			

			{ "oldString", new Integer(Types.VARCHAR) },
			

			{ "newValue", new Integer(Types.VARCHAR) },
			

			{ "newString", new Integer(Types.VARCHAR) }
		};
	public static final String TABLE_SQL_CREATE = "create table changeitem (id LONG not null primary key,groupid LONG,field VARCHAR(75) null,oldValue VARCHAR(75) null,oldString VARCHAR(75) null,newValue VARCHAR(75) null,newString VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table changeitem";
	public static final String DATA_SOURCE = "jiraDataSource";
	public static final String SESSION_FACTORY = "jiraSessionFactory";
	public static final String TX_MANAGER = "jiraTransactionManager";
	public static final boolean CACHE_ENABLED = GetterUtil.getBoolean(PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.wol.model.JIRAChangeItem"),
			true);

	public static JIRAChangeItem toModel(JIRAChangeItemSoap soapModel) {
		JIRAChangeItem model = new JIRAChangeItemImpl();

		model.setJiraChangeItemId(soapModel.getJiraChangeItemId());
		model.setJiraChangeGroupId(soapModel.getJiraChangeGroupId());
		model.setField(soapModel.getField());
		model.setOldValue(soapModel.getOldValue());
		model.setOldString(soapModel.getOldString());
		model.setNewValue(soapModel.getNewValue());
		model.setNewString(soapModel.getNewString());

		return model;
	}

	public static List<JIRAChangeItem> toModels(JIRAChangeItemSoap[] soapModels) {
		List<JIRAChangeItem> models = new ArrayList<JIRAChangeItem>(soapModels.length);

		for (JIRAChangeItemSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(PropsUtil.get(
				"lock.expiration.time.com.liferay.wol.model.JIRAChangeItem"));

	public JIRAChangeItemModelImpl() {
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
		if (jiraChangeItemId != _jiraChangeItemId) {
			_jiraChangeItemId = jiraChangeItemId;
		}
	}

	public long getJiraChangeGroupId() {
		return _jiraChangeGroupId;
	}

	public void setJiraChangeGroupId(long jiraChangeGroupId) {
		if (jiraChangeGroupId != _jiraChangeGroupId) {
			_jiraChangeGroupId = jiraChangeGroupId;
		}
	}

	public String getField() {
		return GetterUtil.getString(_field);
	}

	public void setField(String field) {
		if (((field == null) && (_field != null)) ||
				((field != null) && (_field == null)) ||
				((field != null) && (_field != null) && !field.equals(_field))) {
			_field = field;
		}
	}

	public String getOldValue() {
		return GetterUtil.getString(_oldValue);
	}

	public void setOldValue(String oldValue) {
		if (((oldValue == null) && (_oldValue != null)) ||
				((oldValue != null) && (_oldValue == null)) ||
				((oldValue != null) && (_oldValue != null) &&
				!oldValue.equals(_oldValue))) {
			_oldValue = oldValue;
		}
	}

	public String getOldString() {
		return GetterUtil.getString(_oldString);
	}

	public void setOldString(String oldString) {
		if (((oldString == null) && (_oldString != null)) ||
				((oldString != null) && (_oldString == null)) ||
				((oldString != null) && (_oldString != null) &&
				!oldString.equals(_oldString))) {
			_oldString = oldString;
		}
	}

	public String getNewValue() {
		return GetterUtil.getString(_newValue);
	}

	public void setNewValue(String newValue) {
		if (((newValue == null) && (_newValue != null)) ||
				((newValue != null) && (_newValue == null)) ||
				((newValue != null) && (_newValue != null) &&
				!newValue.equals(_newValue))) {
			_newValue = newValue;
		}
	}

	public String getNewString() {
		return GetterUtil.getString(_newString);
	}

	public void setNewString(String newString) {
		if (((newString == null) && (_newString != null)) ||
				((newString != null) && (_newString == null)) ||
				((newString != null) && (_newString != null) &&
				!newString.equals(_newString))) {
			_newString = newString;
		}
	}

	public JIRAChangeItem toEscapedModel() {
		if (isEscapedModel()) {
			return (JIRAChangeItem)this;
		}
		else {
			JIRAChangeItem model = new JIRAChangeItemImpl();

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
		JIRAChangeItemImpl clone = new JIRAChangeItemImpl();

		clone.setJiraChangeItemId(getJiraChangeItemId());
		clone.setJiraChangeGroupId(getJiraChangeGroupId());
		clone.setField(getField());
		clone.setOldValue(getOldValue());
		clone.setOldString(getOldString());
		clone.setNewValue(getNewValue());
		clone.setNewString(getNewString());

		return clone;
	}

	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}

		JIRAChangeItemImpl jiraChangeItem = (JIRAChangeItemImpl)obj;

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

		JIRAChangeItemImpl jiraChangeItem = null;

		try {
			jiraChangeItem = (JIRAChangeItemImpl)obj;
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

	private long _jiraChangeItemId;
	private long _jiraChangeGroupId;
	private String _field;
	private String _oldValue;
	private String _oldString;
	private String _newValue;
	private String _newString;
}