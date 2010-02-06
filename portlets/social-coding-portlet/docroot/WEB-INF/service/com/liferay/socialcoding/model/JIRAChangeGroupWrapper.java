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

/**
 * <a href="JIRAChangeGroupWrapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class JIRAChangeGroupWrapper implements JIRAChangeGroup {
	public JIRAChangeGroupWrapper(JIRAChangeGroup jiraChangeGroup) {
		_jiraChangeGroup = jiraChangeGroup;
	}

	public long getPrimaryKey() {
		return _jiraChangeGroup.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_jiraChangeGroup.setPrimaryKey(pk);
	}

	public long getJiraChangeGroupId() {
		return _jiraChangeGroup.getJiraChangeGroupId();
	}

	public void setJiraChangeGroupId(long jiraChangeGroupId) {
		_jiraChangeGroup.setJiraChangeGroupId(jiraChangeGroupId);
	}

	public java.lang.String getJiraUserId() {
		return _jiraChangeGroup.getJiraUserId();
	}

	public void setJiraUserId(java.lang.String jiraUserId) {
		_jiraChangeGroup.setJiraUserId(jiraUserId);
	}

	public java.util.Date getCreateDate() {
		return _jiraChangeGroup.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_jiraChangeGroup.setCreateDate(createDate);
	}

	public long getJiraIssueId() {
		return _jiraChangeGroup.getJiraIssueId();
	}

	public void setJiraIssueId(long jiraIssueId) {
		_jiraChangeGroup.setJiraIssueId(jiraIssueId);
	}

	public com.liferay.socialcoding.model.JIRAChangeGroup toEscapedModel() {
		return _jiraChangeGroup.toEscapedModel();
	}

	public boolean isNew() {
		return _jiraChangeGroup.isNew();
	}

	public boolean setNew(boolean n) {
		return _jiraChangeGroup.setNew(n);
	}

	public boolean isCachedModel() {
		return _jiraChangeGroup.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_jiraChangeGroup.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _jiraChangeGroup.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_jiraChangeGroup.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _jiraChangeGroup.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _jiraChangeGroup.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_jiraChangeGroup.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _jiraChangeGroup.clone();
	}

	public int compareTo(
		com.liferay.socialcoding.model.JIRAChangeGroup jiraChangeGroup) {
		return _jiraChangeGroup.compareTo(jiraChangeGroup);
	}

	public int hashCode() {
		return _jiraChangeGroup.hashCode();
	}

	public java.lang.String toString() {
		return _jiraChangeGroup.toString();
	}

	public java.lang.String toXmlString() {
		return _jiraChangeGroup.toXmlString();
	}

	public JIRAChangeGroup getWrappedJIRAChangeGroup() {
		return _jiraChangeGroup;
	}

	private JIRAChangeGroup _jiraChangeGroup;
}