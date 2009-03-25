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

import com.liferay.wol.model.JIRAAction;
import com.liferay.wol.model.JIRAActionSoap;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="JIRAActionModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class JIRAActionModelImpl extends BaseModelImpl<JIRAAction> {
	public static final String TABLE_NAME = "jiraaction";
	public static final Object[][] TABLE_COLUMNS = {
			{ "id", new Integer(Types.BIGINT) },
			

			{ "author", new Integer(Types.VARCHAR) },
			

			{ "created", new Integer(Types.TIMESTAMP) },
			

			{ "updated", new Integer(Types.TIMESTAMP) },
			

			{ "issueid", new Integer(Types.BIGINT) },
			

			{ "actiontype", new Integer(Types.VARCHAR) },
			

			{ "actionbody", new Integer(Types.VARCHAR) },
			

			{ "actionlevel", new Integer(Types.VARCHAR) }
		};
	public static final String TABLE_SQL_CREATE = "create table jiraaction (id LONG not null primary key,author VARCHAR(75) null,created DATE null,updated DATE null,issueid LONG,actiontype VARCHAR(75) null,actionbody VARCHAR(75) null,actionlevel VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table jiraaction";
	public static final String DATA_SOURCE = "jiraDataSource";
	public static final String SESSION_FACTORY = "jiraSessionFactory";
	public static final String TX_MANAGER = "jiraTransactionManager";
	public static final boolean CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.wol.model.JIRAAction"),
			true);

	public static JIRAAction toModel(JIRAActionSoap soapModel) {
		JIRAAction model = new JIRAActionImpl();

		model.setJiraActionId(soapModel.getJiraActionId());
		model.setJiraUserId(soapModel.getJiraUserId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setJiraIssueId(soapModel.getJiraIssueId());
		model.setType(soapModel.getType());
		model.setBody(soapModel.getBody());
		model.setJiraGroupName(soapModel.getJiraGroupName());

		return model;
	}

	public static List<JIRAAction> toModels(JIRAActionSoap[] soapModels) {
		List<JIRAAction> models = new ArrayList<JIRAAction>(soapModels.length);

		for (JIRAActionSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.wol.model.JIRAAction"));

	public JIRAActionModelImpl() {
	}

	public long getPrimaryKey() {
		return _jiraActionId;
	}

	public void setPrimaryKey(long pk) {
		setJiraActionId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_jiraActionId);
	}

	public long getJiraActionId() {
		return _jiraActionId;
	}

	public void setJiraActionId(long jiraActionId) {
		if (jiraActionId != _jiraActionId) {
			_jiraActionId = jiraActionId;
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

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		if (((modifiedDate == null) && (_modifiedDate != null)) ||
				((modifiedDate != null) && (_modifiedDate == null)) ||
				((modifiedDate != null) && (_modifiedDate != null) &&
				!modifiedDate.equals(_modifiedDate))) {
			_modifiedDate = modifiedDate;
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

	public String getType() {
		return GetterUtil.getString(_type);
	}

	public void setType(String type) {
		if (((type == null) && (_type != null)) ||
				((type != null) && (_type == null)) ||
				((type != null) && (_type != null) && !type.equals(_type))) {
			_type = type;
		}
	}

	public String getBody() {
		return GetterUtil.getString(_body);
	}

	public void setBody(String body) {
		if (((body == null) && (_body != null)) ||
				((body != null) && (_body == null)) ||
				((body != null) && (_body != null) && !body.equals(_body))) {
			_body = body;
		}
	}

	public String getJiraGroupName() {
		return GetterUtil.getString(_jiraGroupName);
	}

	public void setJiraGroupName(String jiraGroupName) {
		if (((jiraGroupName == null) && (_jiraGroupName != null)) ||
				((jiraGroupName != null) && (_jiraGroupName == null)) ||
				((jiraGroupName != null) && (_jiraGroupName != null) &&
				!jiraGroupName.equals(_jiraGroupName))) {
			_jiraGroupName = jiraGroupName;
		}
	}

	public JIRAAction toEscapedModel() {
		if (isEscapedModel()) {
			return (JIRAAction)this;
		}
		else {
			JIRAAction model = new JIRAActionImpl();

			model.setNew(isNew());
			model.setEscapedModel(true);

			model.setJiraActionId(getJiraActionId());
			model.setJiraUserId(HtmlUtil.escape(getJiraUserId()));
			model.setCreateDate(getCreateDate());
			model.setModifiedDate(getModifiedDate());
			model.setJiraIssueId(getJiraIssueId());
			model.setType(HtmlUtil.escape(getType()));
			model.setBody(HtmlUtil.escape(getBody()));
			model.setJiraGroupName(HtmlUtil.escape(getJiraGroupName()));

			model = (JIRAAction)Proxy.newProxyInstance(JIRAAction.class.getClassLoader(),
					new Class[] { JIRAAction.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = new ExpandoBridgeImpl(JIRAAction.class.getName(),
					getPrimaryKey());
		}

		return _expandoBridge;
	}

	public Object clone() {
		JIRAActionImpl clone = new JIRAActionImpl();

		clone.setJiraActionId(getJiraActionId());
		clone.setJiraUserId(getJiraUserId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setJiraIssueId(getJiraIssueId());
		clone.setType(getType());
		clone.setBody(getBody());
		clone.setJiraGroupName(getJiraGroupName());

		return clone;
	}

	public int compareTo(JIRAAction jiraAction) {
		int value = 0;

		value = DateUtil.compareTo(getModifiedDate(),
				jiraAction.getModifiedDate());

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

		JIRAAction jiraAction = null;

		try {
			jiraAction = (JIRAAction)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = jiraAction.getPrimaryKey();

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

	private long _jiraActionId;
	private String _jiraUserId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _jiraIssueId;
	private String _type;
	private String _body;
	private String _jiraGroupName;
	private transient ExpandoBridge _expandoBridge;
}