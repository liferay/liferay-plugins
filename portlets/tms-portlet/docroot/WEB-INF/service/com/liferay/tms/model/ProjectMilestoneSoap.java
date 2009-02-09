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

package com.liferay.tms.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="ProjectMilestoneSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ProjectMilestoneSoap implements Serializable {
	public static ProjectMilestoneSoap toSoapModel(ProjectMilestone model) {
		ProjectMilestoneSoap soapModel = new ProjectMilestoneSoap();

		soapModel.setProjectMilestoneId(model.getProjectMilestoneId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTitle(model.getTitle());
		soapModel.setDescription(model.getDescription());
		soapModel.setProjectEntryId(model.getProjectEntryId());
		soapModel.setDueDate(model.getDueDate());
		soapModel.setFinishDate(model.getFinishDate());

		return soapModel;
	}

	public static ProjectMilestoneSoap[] toSoapModels(
		List<ProjectMilestone> models) {
		List<ProjectMilestoneSoap> soapModels = new ArrayList<ProjectMilestoneSoap>(models.size());

		for (ProjectMilestone model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ProjectMilestoneSoap[soapModels.size()]);
	}

	public ProjectMilestoneSoap() {
	}

	public long getPrimaryKey() {
		return _projectMilestoneId;
	}

	public void setPrimaryKey(long pk) {
		setProjectMilestoneId(pk);
	}

	public long getProjectMilestoneId() {
		return _projectMilestoneId;
	}

	public void setProjectMilestoneId(long projectMilestoneId) {
		_projectMilestoneId = projectMilestoneId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
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

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public long getProjectEntryId() {
		return _projectEntryId;
	}

	public void setProjectEntryId(long projectEntryId) {
		_projectEntryId = projectEntryId;
	}

	public Date getDueDate() {
		return _dueDate;
	}

	public void setDueDate(Date dueDate) {
		_dueDate = dueDate;
	}

	public Date getFinishDate() {
		return _finishDate;
	}

	public void setFinishDate(Date finishDate) {
		_finishDate = finishDate;
	}

	private long _projectMilestoneId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _title;
	private String _description;
	private long _projectEntryId;
	private Date _dueDate;
	private Date _finishDate;
}