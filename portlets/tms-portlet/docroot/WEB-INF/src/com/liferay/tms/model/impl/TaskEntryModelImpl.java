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

package com.liferay.tms.model.impl;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.model.impl.ExpandoBridgeImpl;

import com.liferay.tms.model.TaskEntry;
import com.liferay.tms.model.TaskEntrySoap;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="TaskEntryModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class TaskEntryModelImpl extends BaseModelImpl {
	public static final String TABLE_NAME = "TMS_TaskEntry";
	public static final Object[][] TABLE_COLUMNS = {
			{ "taskEntryId", new Integer(Types.BIGINT) },
			

			{ "groupId", new Integer(Types.BIGINT) },
			

			{ "companyId", new Integer(Types.BIGINT) },
			

			{ "userId", new Integer(Types.BIGINT) },
			

			{ "userName", new Integer(Types.VARCHAR) },
			

			{ "createDate", new Integer(Types.TIMESTAMP) },
			

			{ "modifiedDate", new Integer(Types.TIMESTAMP) },
			

			{ "projectEntryId", new Integer(Types.BIGINT) },
			

			{ "projectMilestoneId", new Integer(Types.BIGINT) },
			

			{ "title", new Integer(Types.VARCHAR) },
			

			{ "description", new Integer(Types.VARCHAR) },
			

			{ "assigneeUserId", new Integer(Types.BIGINT) },
			

			{ "resolverUserId", new Integer(Types.BIGINT) },
			

			{ "dueDate", new Integer(Types.TIMESTAMP) },
			

			{ "finishDate", new Integer(Types.TIMESTAMP) },
			

			{ "status", new Integer(Types.INTEGER) }
		};
	public static final String TABLE_SQL_CREATE = "create table TMS_TaskEntry (taskEntryId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,projectEntryId LONG,projectMilestoneId LONG,title VARCHAR(75) null,description VARCHAR(75) null,assigneeUserId LONG,resolverUserId LONG,dueDate DATE null,finishDate DATE null,status INTEGER)";
	public static final String TABLE_SQL_DROP = "drop table TMS_TaskEntry";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.tms.model.TaskEntry"),
			true);

	public static TaskEntry toModel(TaskEntrySoap soapModel) {
		TaskEntry model = new TaskEntryImpl();

		model.setTaskEntryId(soapModel.getTaskEntryId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setProjectEntryId(soapModel.getProjectEntryId());
		model.setProjectMilestoneId(soapModel.getProjectMilestoneId());
		model.setTitle(soapModel.getTitle());
		model.setDescription(soapModel.getDescription());
		model.setAssigneeUserId(soapModel.getAssigneeUserId());
		model.setResolverUserId(soapModel.getResolverUserId());
		model.setDueDate(soapModel.getDueDate());
		model.setFinishDate(soapModel.getFinishDate());
		model.setStatus(soapModel.getStatus());

		return model;
	}

	public static List<TaskEntry> toModels(TaskEntrySoap[] soapModels) {
		List<TaskEntry> models = new ArrayList<TaskEntry>(soapModels.length);

		for (TaskEntrySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.tms.model.TaskEntry"));

	public TaskEntryModelImpl() {
	}

	public long getPrimaryKey() {
		return _taskEntryId;
	}

	public void setPrimaryKey(long pk) {
		setTaskEntryId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_taskEntryId);
	}

	public long getTaskEntryId() {
		return _taskEntryId;
	}

	public void setTaskEntryId(long taskEntryId) {
		if (taskEntryId != _taskEntryId) {
			_taskEntryId = taskEntryId;
		}
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		if (groupId != _groupId) {
			_groupId = groupId;
		}
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		if (companyId != _companyId) {
			_companyId = companyId;
		}
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		if (userId != _userId) {
			_userId = userId;
		}
	}

	public String getUserName() {
		return GetterUtil.getString(_userName);
	}

	public void setUserName(String userName) {
		if (((userName == null) && (_userName != null)) ||
				((userName != null) && (_userName == null)) ||
				((userName != null) && (_userName != null) &&
				!userName.equals(_userName))) {
			_userName = userName;
		}
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		if (((createDate == null) && (_createDate != null)) ||
				((createDate != null) && (_createDate == null)) ||
				((createDate != null) && (_createDate != null) &&
				!createDate.equals(_createDate))) {
			_createDate = createDate;
		}
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		if (((modifiedDate == null) && (_modifiedDate != null)) ||
				((modifiedDate != null) && (_modifiedDate == null)) ||
				((modifiedDate != null) && (_modifiedDate != null) &&
				!modifiedDate.equals(_modifiedDate))) {
			_modifiedDate = modifiedDate;
		}
	}

	public long getProjectEntryId() {
		return _projectEntryId;
	}

	public void setProjectEntryId(long projectEntryId) {
		if (projectEntryId != _projectEntryId) {
			_projectEntryId = projectEntryId;
		}
	}

	public long getProjectMilestoneId() {
		return _projectMilestoneId;
	}

	public void setProjectMilestoneId(long projectMilestoneId) {
		if (projectMilestoneId != _projectMilestoneId) {
			_projectMilestoneId = projectMilestoneId;
		}
	}

	public String getTitle() {
		return GetterUtil.getString(_title);
	}

	public void setTitle(String title) {
		if (((title == null) && (_title != null)) ||
				((title != null) && (_title == null)) ||
				((title != null) && (_title != null) && !title.equals(_title))) {
			_title = title;
		}
	}

	public String getDescription() {
		return GetterUtil.getString(_description);
	}

	public void setDescription(String description) {
		if (((description == null) && (_description != null)) ||
				((description != null) && (_description == null)) ||
				((description != null) && (_description != null) &&
				!description.equals(_description))) {
			_description = description;
		}
	}

	public long getAssigneeUserId() {
		return _assigneeUserId;
	}

	public void setAssigneeUserId(long assigneeUserId) {
		if (assigneeUserId != _assigneeUserId) {
			_assigneeUserId = assigneeUserId;
		}
	}

	public long getResolverUserId() {
		return _resolverUserId;
	}

	public void setResolverUserId(long resolverUserId) {
		if (resolverUserId != _resolverUserId) {
			_resolverUserId = resolverUserId;
		}
	}

	public Date getDueDate() {
		return _dueDate;
	}

	public void setDueDate(Date dueDate) {
		if (((dueDate == null) && (_dueDate != null)) ||
				((dueDate != null) && (_dueDate == null)) ||
				((dueDate != null) && (_dueDate != null) &&
				!dueDate.equals(_dueDate))) {
			_dueDate = dueDate;
		}
	}

	public Date getFinishDate() {
		return _finishDate;
	}

	public void setFinishDate(Date finishDate) {
		if (((finishDate == null) && (_finishDate != null)) ||
				((finishDate != null) && (_finishDate == null)) ||
				((finishDate != null) && (_finishDate != null) &&
				!finishDate.equals(_finishDate))) {
			_finishDate = finishDate;
		}
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		if (status != _status) {
			_status = status;
		}
	}

	public TaskEntry toEscapedModel() {
		if (isEscapedModel()) {
			return (TaskEntry)this;
		}
		else {
			TaskEntry model = new TaskEntryImpl();

			model.setNew(isNew());
			model.setEscapedModel(true);

			model.setTaskEntryId(getTaskEntryId());
			model.setGroupId(getGroupId());
			model.setCompanyId(getCompanyId());
			model.setUserId(getUserId());
			model.setUserName(HtmlUtil.escape(getUserName()));
			model.setCreateDate(getCreateDate());
			model.setModifiedDate(getModifiedDate());
			model.setProjectEntryId(getProjectEntryId());
			model.setProjectMilestoneId(getProjectMilestoneId());
			model.setTitle(HtmlUtil.escape(getTitle()));
			model.setDescription(HtmlUtil.escape(getDescription()));
			model.setAssigneeUserId(getAssigneeUserId());
			model.setResolverUserId(getResolverUserId());
			model.setDueDate(getDueDate());
			model.setFinishDate(getFinishDate());
			model.setStatus(getStatus());

			model = (TaskEntry)Proxy.newProxyInstance(TaskEntry.class.getClassLoader(),
					new Class[] { TaskEntry.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = new ExpandoBridgeImpl(TaskEntry.class.getName(),
					getPrimaryKey());
		}

		return _expandoBridge;
	}

	public Object clone() {
		TaskEntryImpl clone = new TaskEntryImpl();

		clone.setTaskEntryId(getTaskEntryId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setProjectEntryId(getProjectEntryId());
		clone.setProjectMilestoneId(getProjectMilestoneId());
		clone.setTitle(getTitle());
		clone.setDescription(getDescription());
		clone.setAssigneeUserId(getAssigneeUserId());
		clone.setResolverUserId(getResolverUserId());
		clone.setDueDate(getDueDate());
		clone.setFinishDate(getFinishDate());
		clone.setStatus(getStatus());

		return clone;
	}

	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}

		TaskEntryImpl taskEntry = (TaskEntryImpl)obj;

		long pk = taskEntry.getPrimaryKey();

		if (getPrimaryKey() < pk) {
			return -1;
		}
		else if (getPrimaryKey() > pk) {
			return 1;
		}
		else {
			return 0;
		}
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		TaskEntryImpl taskEntry = null;

		try {
			taskEntry = (TaskEntryImpl)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = taskEntry.getPrimaryKey();

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

	private long _taskEntryId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _projectEntryId;
	private long _projectMilestoneId;
	private String _title;
	private String _description;
	private long _assigneeUserId;
	private long _resolverUserId;
	private Date _dueDate;
	private Date _finishDate;
	private int _status;
	private transient ExpandoBridge _expandoBridge;
}