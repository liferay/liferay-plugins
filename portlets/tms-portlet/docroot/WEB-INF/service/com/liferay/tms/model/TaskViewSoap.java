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
 * <a href="TaskViewSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class TaskViewSoap implements Serializable {
	public static TaskViewSoap toSoapModel(TaskView model) {
		TaskViewSoap soapModel = new TaskViewSoap();

		soapModel.setTaskViewId(model.getTaskViewId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTitle(model.getTitle());
		soapModel.setTags(model.getTags());
		soapModel.setNotTags(model.getNotTags());
		soapModel.setAssigneeUserId(model.getAssigneeUserId());
		soapModel.setReporterUserId(model.getReporterUserId());
		soapModel.setIncludeProjectTasks(model.getIncludeProjectTasks());
		soapModel.setIsPrivate(model.getIsPrivate());

		return soapModel;
	}

	public static TaskViewSoap[] toSoapModels(List<TaskView> models) {
		List<TaskViewSoap> soapModels = new ArrayList<TaskViewSoap>(models.size());

		for (TaskView model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TaskViewSoap[soapModels.size()]);
	}

	public TaskViewSoap() {
	}

	public long getPrimaryKey() {
		return _taskViewId;
	}

	public void setPrimaryKey(long pk) {
		setTaskViewId(pk);
	}

	public long getTaskViewId() {
		return _taskViewId;
	}

	public void setTaskViewId(long taskViewId) {
		_taskViewId = taskViewId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public String getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(String companyId) {
		_companyId = companyId;
	}

	public String getUserId() {
		return _userId;
	}

	public void setUserId(String userId) {
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

	public String getTags() {
		return _tags;
	}

	public void setTags(String tags) {
		_tags = tags;
	}

	public String getNotTags() {
		return _notTags;
	}

	public void setNotTags(String notTags) {
		_notTags = notTags;
	}

	public long getAssigneeUserId() {
		return _assigneeUserId;
	}

	public void setAssigneeUserId(long assigneeUserId) {
		_assigneeUserId = assigneeUserId;
	}

	public long getReporterUserId() {
		return _reporterUserId;
	}

	public void setReporterUserId(long reporterUserId) {
		_reporterUserId = reporterUserId;
	}

	public int getIncludeProjectTasks() {
		return _includeProjectTasks;
	}

	public void setIncludeProjectTasks(int includeProjectTasks) {
		_includeProjectTasks = includeProjectTasks;
	}

	public boolean getIsPrivate() {
		return _isPrivate;
	}

	public boolean isIsPrivate() {
		return _isPrivate;
	}

	public void setIsPrivate(boolean isPrivate) {
		_isPrivate = isPrivate;
	}

	private long _taskViewId;
	private long _groupId;
	private String _companyId;
	private String _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _title;
	private String _tags;
	private String _notTags;
	private long _assigneeUserId;
	private long _reporterUserId;
	private int _includeProjectTasks;
	private boolean _isPrivate;
}