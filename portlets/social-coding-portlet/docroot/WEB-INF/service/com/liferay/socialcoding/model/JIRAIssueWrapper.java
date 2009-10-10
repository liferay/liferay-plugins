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

package com.liferay.socialcoding.model;

/**
 * <a href="JIRAIssueWrapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class JIRAIssueWrapper implements JIRAIssue {
	public JIRAIssueWrapper(JIRAIssue jiraIssue) {
		_jiraIssue = jiraIssue;
	}

	public long getPrimaryKey() {
		return _jiraIssue.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_jiraIssue.setPrimaryKey(pk);
	}

	public long getJiraIssueId() {
		return _jiraIssue.getJiraIssueId();
	}

	public void setJiraIssueId(long jiraIssueId) {
		_jiraIssue.setJiraIssueId(jiraIssueId);
	}

	public java.util.Date getCreateDate() {
		return _jiraIssue.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_jiraIssue.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _jiraIssue.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_jiraIssue.setModifiedDate(modifiedDate);
	}

	public long getProjectId() {
		return _jiraIssue.getProjectId();
	}

	public void setProjectId(long projectId) {
		_jiraIssue.setProjectId(projectId);
	}

	public java.lang.String getKey() {
		return _jiraIssue.getKey();
	}

	public void setKey(java.lang.String key) {
		_jiraIssue.setKey(key);
	}

	public java.lang.String getSummary() {
		return _jiraIssue.getSummary();
	}

	public void setSummary(java.lang.String summary) {
		_jiraIssue.setSummary(summary);
	}

	public java.lang.String getDescription() {
		return _jiraIssue.getDescription();
	}

	public void setDescription(java.lang.String description) {
		_jiraIssue.setDescription(description);
	}

	public java.lang.String getReporterJiraUserId() {
		return _jiraIssue.getReporterJiraUserId();
	}

	public void setReporterJiraUserId(java.lang.String reporterJiraUserId) {
		_jiraIssue.setReporterJiraUserId(reporterJiraUserId);
	}

	public java.lang.String getAssigneeJiraUserId() {
		return _jiraIssue.getAssigneeJiraUserId();
	}

	public void setAssigneeJiraUserId(java.lang.String assigneeJiraUserId) {
		_jiraIssue.setAssigneeJiraUserId(assigneeJiraUserId);
	}

	public java.lang.String getResolution() {
		return _jiraIssue.getResolution();
	}

	public void setResolution(java.lang.String resolution) {
		_jiraIssue.setResolution(resolution);
	}

	public java.lang.String getStatus() {
		return _jiraIssue.getStatus();
	}

	public void setStatus(java.lang.String status) {
		_jiraIssue.setStatus(status);
	}

	public com.liferay.socialcoding.model.JIRAIssue toEscapedModel() {
		return _jiraIssue.toEscapedModel();
	}

	public boolean isNew() {
		return _jiraIssue.isNew();
	}

	public boolean setNew(boolean n) {
		return _jiraIssue.setNew(n);
	}

	public boolean isCachedModel() {
		return _jiraIssue.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_jiraIssue.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _jiraIssue.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_jiraIssue.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _jiraIssue.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _jiraIssue.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_jiraIssue.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _jiraIssue.clone();
	}

	public int compareTo(com.liferay.socialcoding.model.JIRAIssue jiraIssue) {
		return _jiraIssue.compareTo(jiraIssue);
	}

	public int hashCode() {
		return _jiraIssue.hashCode();
	}

	public java.lang.String toString() {
		return _jiraIssue.toString();
	}

	public java.lang.String toXmlString() {
		return _jiraIssue.toXmlString();
	}

	public JIRAIssue getWrappedJIRAIssue() {
		return _jiraIssue;
	}

	private JIRAIssue _jiraIssue;
}