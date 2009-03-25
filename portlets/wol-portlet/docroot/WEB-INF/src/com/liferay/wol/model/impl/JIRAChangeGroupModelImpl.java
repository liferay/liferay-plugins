/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.model.impl.ExpandoBridgeImpl;

import com.liferay.wol.model.JIRAChangeGroup;
import com.liferay.wol.model.JIRAChangeGroupSoap;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="JIRAChangeGroupModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class JIRAChangeGroupModelImpl extends BaseModelImpl<JIRAChangeGroup> {
	public static final String TABLE_NAME = "changegroup";
	public static final Object[][] TABLE_COLUMNS = {
			{ "id", new Integer(Types.BIGINT) },
			

			{ "author", new Integer(Types.VARCHAR) },
			

			{ "created", new Integer(Types.TIMESTAMP) },
			

			{ "issueid", new Integer(Types.BIGINT) }
		};
	public static final String TABLE_SQL_CREATE = "create table changegroup (id LONG not null primary key,author VARCHAR(75) null,created DATE null,issueid LONG)";
	public static final String TABLE_SQL_DROP = "drop table changegroup";
	public static final String DATA_SOURCE = "jiraDataSource";
	public static final String SESSION_FACTORY = "jiraSessionFactory";
	public static final String TX_MANAGER = "jiraTransactionManager";
	public static final boolean CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.wol.model.JIRAChangeGroup"),
			true);

	public static JIRAChangeGroup toModel(JIRAChangeGroupSoap soapModel) {
		JIRAChangeGroup model = new JIRAChangeGroupImpl();

		model.setJiraChangeGroupId(soapModel.getJiraChangeGroupId());
		model.setJiraUserId(soapModel.getJiraUserId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setJiraIssueId(soapModel.getJiraIssueId());

		return model;
	}

	public static List<JIRAChangeGroup> toModels(
		JIRAChangeGroupSoap[] soapModels) {
		List<JIRAChangeGroup> models = new ArrayList<JIRAChangeGroup>(soapModels.length);

		for (JIRAChangeGroupSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.wol.model.JIRAChangeGroup"));

	public JIRAChangeGroupModelImpl() {
	}

	public long getPrimaryKey() {
		return _jiraChangeGroupId;
	}

	public void setPrimaryKey(long pk) {
		setJiraChangeGroupId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_jiraChangeGroupId);
	}

	public long getJiraChangeGroupId() {
		return _jiraChangeGroupId;
	}

	public void setJiraChangeGroupId(long jiraChangeGroupId) {
		if (jiraChangeGroupId != _jiraChangeGroupId) {
			_jiraChangeGroupId = jiraChangeGroupId;
		}
	}

	public String getJiraUserId() {
		return GetterUtil.getString(_jiraUserId);
	}

	public void setJiraUserId(String jiraUserId) {
		if (((jiraUserId == null) && (_jiraUserId != null)) ||
				((jiraUserId != null) && (_jiraUserId == null)) ||
				((jiraUserId != null) && (_jiraUserId != null) &&
				!jiraUserId.equals(_jiraUserId))) {
			_jiraUserId = jiraUserId;
		}
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		if (((createDate == null) && (_createDate != null)) ||
				((createDate != null) && (_createDate == null)) ||
				((createDate != null) && (_createDate != null) &&
				!createDate.equals(_createDate))) {
			_createDate = createDate;
		}
	}

	public long getJiraIssueId() {
		return _jiraIssueId;
	}

	public void setJiraIssueId(long jiraIssueId) {
		if (jiraIssueId != _jiraIssueId) {
			_jiraIssueId = jiraIssueId;
		}
	}

	public JIRAChangeGroup toEscapedModel() {
		if (isEscapedModel()) {
			return (JIRAChangeGroup)this;
		}
		else {
			JIRAChangeGroup model = new JIRAChangeGroupImpl();

			model.setNew(isNew());
			model.setEscapedModel(true);

			model.setJiraChangeGroupId(getJiraChangeGroupId());
			model.setJiraUserId(HtmlUtil.escape(getJiraUserId()));
			model.setCreateDate(getCreateDate());
			model.setJiraIssueId(getJiraIssueId());

			model = (JIRAChangeGroup)Proxy.newProxyInstance(JIRAChangeGroup.class.getClassLoader(),
					new Class[] { JIRAChangeGroup.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = new ExpandoBridgeImpl(JIRAChangeGroup.class.getName(),
					getPrimaryKey());
		}

		return _expandoBridge;
	}

	public Object clone() {
		JIRAChangeGroupImpl clone = new JIRAChangeGroupImpl();

		clone.setJiraChangeGroupId(getJiraChangeGroupId());
		clone.setJiraUserId(getJiraUserId());
		clone.setCreateDate(getCreateDate());
		clone.setJiraIssueId(getJiraIssueId());

		return clone;
	}

	public int compareTo(JIRAChangeGroup jiraChangeGroup) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(),
				jiraChangeGroup.getCreateDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		JIRAChangeGroup jiraChangeGroup = null;

		try {
			jiraChangeGroup = (JIRAChangeGroup)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = jiraChangeGroup.getPrimaryKey();

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

	private long _jiraChangeGroupId;
	private String _jiraUserId;
	private Date _createDate;
	private long _jiraIssueId;
	private transient ExpandoBridge _expandoBridge;
}