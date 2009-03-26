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

package com.liferay.sd.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="JIRAChangeGroupSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class JIRAChangeGroupSoap implements Serializable {
	public static JIRAChangeGroupSoap toSoapModel(JIRAChangeGroup model) {
		JIRAChangeGroupSoap soapModel = new JIRAChangeGroupSoap();

		soapModel.setJiraChangeGroupId(model.getJiraChangeGroupId());
		soapModel.setJiraUserId(model.getJiraUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setJiraIssueId(model.getJiraIssueId());

		return soapModel;
	}

	public static JIRAChangeGroupSoap[] toSoapModels(
		List<JIRAChangeGroup> models) {
		List<JIRAChangeGroupSoap> soapModels = new ArrayList<JIRAChangeGroupSoap>(models.size());

		for (JIRAChangeGroup model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new JIRAChangeGroupSoap[soapModels.size()]);
	}

	public JIRAChangeGroupSoap() {
	}

	public long getPrimaryKey() {
		return _jiraChangeGroupId;
	}

	public void setPrimaryKey(long pk) {
		setJiraChangeGroupId(pk);
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

	private long _jiraChangeGroupId;
	private String _jiraUserId;
	private Date _createDate;
	private long _jiraIssueId;
}