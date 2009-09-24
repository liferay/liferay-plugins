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
 * <a href="JIRAChangeItemWrapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class JIRAChangeItemWrapper implements JIRAChangeItem {
	public JIRAChangeItemWrapper(JIRAChangeItem jiraChangeItem) {
		_jiraChangeItem = jiraChangeItem;
	}

	public long getPrimaryKey() {
		return _jiraChangeItem.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_jiraChangeItem.setPrimaryKey(pk);
	}

	public long getJiraChangeItemId() {
		return _jiraChangeItem.getJiraChangeItemId();
	}

	public void setJiraChangeItemId(long jiraChangeItemId) {
		_jiraChangeItem.setJiraChangeItemId(jiraChangeItemId);
	}

	public long getJiraChangeGroupId() {
		return _jiraChangeItem.getJiraChangeGroupId();
	}

	public void setJiraChangeGroupId(long jiraChangeGroupId) {
		_jiraChangeItem.setJiraChangeGroupId(jiraChangeGroupId);
	}

	public java.lang.String getField() {
		return _jiraChangeItem.getField();
	}

	public void setField(java.lang.String field) {
		_jiraChangeItem.setField(field);
	}

	public java.lang.String getOldValue() {
		return _jiraChangeItem.getOldValue();
	}

	public void setOldValue(java.lang.String oldValue) {
		_jiraChangeItem.setOldValue(oldValue);
	}

	public java.lang.String getOldString() {
		return _jiraChangeItem.getOldString();
	}

	public void setOldString(java.lang.String oldString) {
		_jiraChangeItem.setOldString(oldString);
	}

	public java.lang.String getNewValue() {
		return _jiraChangeItem.getNewValue();
	}

	public void setNewValue(java.lang.String newValue) {
		_jiraChangeItem.setNewValue(newValue);
	}

	public java.lang.String getNewString() {
		return _jiraChangeItem.getNewString();
	}

	public void setNewString(java.lang.String newString) {
		_jiraChangeItem.setNewString(newString);
	}

	public com.liferay.socialcoding.model.JIRAChangeItem toEscapedModel() {
		return _jiraChangeItem.toEscapedModel();
	}

	public boolean isNew() {
		return _jiraChangeItem.isNew();
	}

	public boolean setNew(boolean n) {
		return _jiraChangeItem.setNew(n);
	}

	public boolean isCachedModel() {
		return _jiraChangeItem.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_jiraChangeItem.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _jiraChangeItem.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_jiraChangeItem.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _jiraChangeItem.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _jiraChangeItem.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_jiraChangeItem.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _jiraChangeItem.clone();
	}

	public int compareTo(
		com.liferay.socialcoding.model.JIRAChangeItem jiraChangeItem) {
		return _jiraChangeItem.compareTo(jiraChangeItem);
	}

	public int hashCode() {
		return _jiraChangeItem.hashCode();
	}

	public java.lang.String toString() {
		return _jiraChangeItem.toString();
	}

	public java.lang.String toXmlString() {
		return _jiraChangeItem.toXmlString();
	}

	private JIRAChangeItem _jiraChangeItem;
}