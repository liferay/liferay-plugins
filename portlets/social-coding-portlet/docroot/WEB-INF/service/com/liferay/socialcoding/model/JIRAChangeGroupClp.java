/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.socialcoding.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.liferay.socialcoding.service.JIRAChangeGroupLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 */
public class JIRAChangeGroupClp extends BaseModelImpl<JIRAChangeGroup>
	implements JIRAChangeGroup {
	public JIRAChangeGroupClp() {
	}

	public Class<?> getModelClass() {
		return JIRAChangeGroup.class;
	}

	public String getModelClassName() {
		return JIRAChangeGroup.class.getName();
	}

	public long getPrimaryKey() {
		return _jiraChangeGroupId;
	}

	public void setPrimaryKey(long primaryKey) {
		setJiraChangeGroupId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_jiraChangeGroupId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public long getJiraChangeGroupId() {
		return _jiraChangeGroupId;
	}

	public void setJiraChangeGroupId(long jiraChangeGroupId) {
		_jiraChangeGroupId = jiraChangeGroupId;
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

	public long getJiraIssueId() {
		return _jiraIssueId;
	}

	public void setJiraIssueId(long jiraIssueId) {
		_jiraIssueId = jiraIssueId;
	}

	public void persist() throws SystemException {
		JIRAChangeGroupLocalServiceUtil.updateJIRAChangeGroup(this);
	}

	@Override
	public JIRAChangeGroup toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			return (JIRAChangeGroup)Proxy.newProxyInstance(JIRAChangeGroup.class.getClassLoader(),
				new Class[] { JIRAChangeGroup.class },
				new AutoEscapeBeanHandler(this));
		}
	}

	@Override
	public Object clone() {
		JIRAChangeGroupClp clone = new JIRAChangeGroupClp();

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

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		JIRAChangeGroupClp jiraChangeGroup = null;

		try {
			jiraChangeGroup = (JIRAChangeGroupClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = jiraChangeGroup.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{jiraChangeGroupId=");
		sb.append(getJiraChangeGroupId());
		sb.append(", jiraUserId=");
		sb.append(getJiraUserId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", jiraIssueId=");
		sb.append(getJiraIssueId());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(16);

		sb.append("<model><model-name>");
		sb.append("com.liferay.socialcoding.model.JIRAChangeGroup");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>jiraChangeGroupId</column-name><column-value><![CDATA[");
		sb.append(getJiraChangeGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>jiraUserId</column-name><column-value><![CDATA[");
		sb.append(getJiraUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>jiraIssueId</column-name><column-value><![CDATA[");
		sb.append(getJiraIssueId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _jiraChangeGroupId;
	private String _jiraUserId;
	private Date _createDate;
	private long _jiraIssueId;
}