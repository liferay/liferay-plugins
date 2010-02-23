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

package com.liferay.so.model;

/**
 * <a href="ProjectsEntryWrapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class ProjectsEntryWrapper implements ProjectsEntry {
	public ProjectsEntryWrapper(ProjectsEntry projectsEntry) {
		_projectsEntry = projectsEntry;
	}

	public long getPrimaryKey() {
		return _projectsEntry.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_projectsEntry.setPrimaryKey(pk);
	}

	public long getProjectsEntryId() {
		return _projectsEntry.getProjectsEntryId();
	}

	public void setProjectsEntryId(long projectsEntryId) {
		_projectsEntry.setProjectsEntryId(projectsEntryId);
	}

	public long getCompanyId() {
		return _projectsEntry.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_projectsEntry.setCompanyId(companyId);
	}

	public long getUserId() {
		return _projectsEntry.getUserId();
	}

	public void setUserId(long userId) {
		_projectsEntry.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _projectsEntry.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_projectsEntry.setUserUuid(userUuid);
	}

	public java.lang.String getUserName() {
		return _projectsEntry.getUserName();
	}

	public void setUserName(java.lang.String userName) {
		_projectsEntry.setUserName(userName);
	}

	public java.util.Date getCreateDate() {
		return _projectsEntry.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_projectsEntry.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _projectsEntry.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_projectsEntry.setModifiedDate(modifiedDate);
	}

	public java.lang.String getTitle() {
		return _projectsEntry.getTitle();
	}

	public void setTitle(java.lang.String title) {
		_projectsEntry.setTitle(title);
	}

	public java.lang.String getDescription() {
		return _projectsEntry.getDescription();
	}

	public void setDescription(java.lang.String description) {
		_projectsEntry.setDescription(description);
	}

	public java.util.Date getStartDate() {
		return _projectsEntry.getStartDate();
	}

	public void setStartDate(java.util.Date startDate) {
		_projectsEntry.setStartDate(startDate);
	}

	public java.util.Date getEndDate() {
		return _projectsEntry.getEndDate();
	}

	public void setEndDate(java.util.Date endDate) {
		_projectsEntry.setEndDate(endDate);
	}

	public java.lang.String getData() {
		return _projectsEntry.getData();
	}

	public void setData(java.lang.String data) {
		_projectsEntry.setData(data);
	}

	public com.liferay.so.model.ProjectsEntry toEscapedModel() {
		return _projectsEntry.toEscapedModel();
	}

	public boolean isNew() {
		return _projectsEntry.isNew();
	}

	public boolean setNew(boolean n) {
		return _projectsEntry.setNew(n);
	}

	public boolean isCachedModel() {
		return _projectsEntry.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_projectsEntry.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _projectsEntry.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_projectsEntry.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _projectsEntry.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _projectsEntry.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_projectsEntry.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _projectsEntry.clone();
	}

	public int compareTo(com.liferay.so.model.ProjectsEntry projectsEntry) {
		return _projectsEntry.compareTo(projectsEntry);
	}

	public int hashCode() {
		return _projectsEntry.hashCode();
	}

	public java.lang.String toString() {
		return _projectsEntry.toString();
	}

	public java.lang.String toXmlString() {
		return _projectsEntry.toXmlString();
	}

	public ProjectsEntry getWrappedProjectsEntry() {
		return _projectsEntry;
	}

	private ProjectsEntry _projectsEntry;
}