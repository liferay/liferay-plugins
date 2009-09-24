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
 * <a href="JIRAActionWrapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class JIRAActionWrapper implements JIRAAction {
	public JIRAActionWrapper(JIRAAction jiraAction) {
		_jiraAction = jiraAction;
	}

	public long getPrimaryKey() {
		return _jiraAction.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_jiraAction.setPrimaryKey(pk);
	}

	public long getJiraActionId() {
		return _jiraAction.getJiraActionId();
	}

	public void setJiraActionId(long jiraActionId) {
		_jiraAction.setJiraActionId(jiraActionId);
	}

	public java.lang.String getJiraUserId() {
		return _jiraAction.getJiraUserId();
	}

	public void setJiraUserId(java.lang.String jiraUserId) {
		_jiraAction.setJiraUserId(jiraUserId);
	}

	public java.util.Date getCreateDate() {
		return _jiraAction.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_jiraAction.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _jiraAction.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_jiraAction.setModifiedDate(modifiedDate);
	}

	public long getJiraIssueId() {
		return _jiraAction.getJiraIssueId();
	}

	public void setJiraIssueId(long jiraIssueId) {
		_jiraAction.setJiraIssueId(jiraIssueId);
	}

	public java.lang.String getType() {
		return _jiraAction.getType();
	}

	public void setType(java.lang.String type) {
		_jiraAction.setType(type);
	}

	public java.lang.String getBody() {
		return _jiraAction.getBody();
	}

	public void setBody(java.lang.String body) {
		_jiraAction.setBody(body);
	}

	public java.lang.String getJiraGroupName() {
		return _jiraAction.getJiraGroupName();
	}

	public void setJiraGroupName(java.lang.String jiraGroupName) {
		_jiraAction.setJiraGroupName(jiraGroupName);
	}

	public com.liferay.socialcoding.model.JIRAAction toEscapedModel() {
		return _jiraAction.toEscapedModel();
	}

	public boolean isNew() {
		return _jiraAction.isNew();
	}

	public boolean setNew(boolean n) {
		return _jiraAction.setNew(n);
	}

	public boolean isCachedModel() {
		return _jiraAction.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_jiraAction.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _jiraAction.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_jiraAction.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _jiraAction.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _jiraAction.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_jiraAction.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _jiraAction.clone();
	}

	public int compareTo(com.liferay.socialcoding.model.JIRAAction jiraAction) {
		return _jiraAction.compareTo(jiraAction);
	}

	public int hashCode() {
		return _jiraAction.hashCode();
	}

	public java.lang.String toString() {
		return _jiraAction.toString();
	}

	public java.lang.String toXmlString() {
		return _jiraAction.toXmlString();
	}

	private JIRAAction _jiraAction;
}