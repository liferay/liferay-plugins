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

package com.liferay.wol.model;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;

/**
 * <a href="JIRAActionClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class JIRAActionClp extends BaseModelImpl implements JIRAAction {
	public JIRAActionClp() {
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
		_jiraActionId = jiraActionId;
	}

	public String getJiraUserId() {
		return _jiraUserId;
	}

	public void setJiraUserId(String jiraUserId) {
		_jiraUserId = jiraUserId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getJiraIssueId() {
		return _jiraIssueId;
	}

	public void setJiraIssueId(long jiraIssueId) {
		_jiraIssueId = jiraIssueId;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	public String getBody() {
		return _body;
	}

	public void setBody(String body) {
		_body = body;
	}

	public String getJiraGroupName() {
		return _jiraGroupName;
	}

	public void setJiraGroupName(String jiraGroupName) {
		_jiraGroupName = jiraGroupName;
	}

	public JIRAAction toEscapedModel() {
		if (isEscapedModel()) {
			return (JIRAAction)this;
		}
		else {
			JIRAAction model = new JIRAActionClp();

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

	public Object clone() {
		JIRAActionClp clone = new JIRAActionClp();

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

	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}

		JIRAActionClp jiraAction = (JIRAActionClp)obj;

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

		JIRAActionClp jiraAction = null;

		try {
			jiraAction = (JIRAActionClp)obj;
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
}