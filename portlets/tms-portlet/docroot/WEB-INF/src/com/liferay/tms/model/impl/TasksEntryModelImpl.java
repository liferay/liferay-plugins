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
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.model.impl.ExpandoBridgeImpl;

import com.liferay.tms.model.TasksEntry;
import com.liferay.tms.model.TasksEntrySoap;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="TasksEntryModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class TasksEntryModelImpl extends BaseModelImpl<TasksEntry> {
	public static final String TABLE_NAME = "TMS_TasksEntry";
	public static final Object[][] TABLE_COLUMNS = {
			{ "tasksEntryId", new Integer(Types.BIGINT) },
			

			{ "groupId", new Integer(Types.BIGINT) },
			

			{ "companyId", new Integer(Types.BIGINT) },
			

			{ "userId", new Integer(Types.BIGINT) },
			

			{ "userName", new Integer(Types.VARCHAR) },
			

			{ "createDate", new Integer(Types.TIMESTAMP) },
			

			{ "modifiedDate", new Integer(Types.TIMESTAMP) },
			

			{ "title", new Integer(Types.VARCHAR) },
			

			{ "priority", new Integer(Types.INTEGER) },
			

			{ "assigneeUserId", new Integer(Types.BIGINT) },
			

			{ "resolverUserId", new Integer(Types.BIGINT) },
			

			{ "dueDate", new Integer(Types.TIMESTAMP) },
			

			{ "finishDate", new Integer(Types.TIMESTAMP) },
			

			{ "status", new Integer(Types.INTEGER) }
		};
	public static final String TABLE_SQL_CREATE = "create table TMS_TasksEntry (tasksEntryId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,title VARCHAR(75) null,priority INTEGER,assigneeUserId LONG,resolverUserId LONG,dueDate DATE null,finishDate DATE null,status INTEGER)";
	public static final String TABLE_SQL_DROP = "drop table TMS_TasksEntry";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.tms.model.TasksEntry"),
			true);

	public static TasksEntry toModel(TasksEntrySoap soapModel) {
		TasksEntry model = new TasksEntryImpl();

		model.setTasksEntryId(soapModel.getTasksEntryId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setTitle(soapModel.getTitle());
		model.setPriority(soapModel.getPriority());
		model.setAssigneeUserId(soapModel.getAssigneeUserId());
		model.setResolverUserId(soapModel.getResolverUserId());
		model.setDueDate(soapModel.getDueDate());
		model.setFinishDate(soapModel.getFinishDate());
		model.setStatus(soapModel.getStatus());

		return model;
	}

	public static List<TasksEntry> toModels(TasksEntrySoap[] soapModels) {
		List<TasksEntry> models = new ArrayList<TasksEntry>(soapModels.length);

		for (TasksEntrySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.tms.model.TasksEntry"));

	public TasksEntryModelImpl() {
	}

	public long getPrimaryKey() {
		return _tasksEntryId;
	}

	public void setPrimaryKey(long pk) {
		setTasksEntryId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_tasksEntryId);
	}

	public long getTasksEntryId() {
		return _tasksEntryId;
	}

	public void setTasksEntryId(long tasksEntryId) {
		if (tasksEntryId != _tasksEntryId) {
			_tasksEntryId = tasksEntryId;
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

	public int getPriority() {
		return _priority;
	}

	public void setPriority(int priority) {
		if (priority != _priority) {
			_priority = priority;
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

	public TasksEntry toEscapedModel() {
		if (isEscapedModel()) {
			return (TasksEntry)this;
		}
		else {
			TasksEntry model = new TasksEntryImpl();

			model.setNew(isNew());
			model.setEscapedModel(true);

			model.setTasksEntryId(getTasksEntryId());
			model.setGroupId(getGroupId());
			model.setCompanyId(getCompanyId());
			model.setUserId(getUserId());
			model.setUserName(HtmlUtil.escape(getUserName()));
			model.setCreateDate(getCreateDate());
			model.setModifiedDate(getModifiedDate());
			model.setTitle(HtmlUtil.escape(getTitle()));
			model.setPriority(getPriority());
			model.setAssigneeUserId(getAssigneeUserId());
			model.setResolverUserId(getResolverUserId());
			model.setDueDate(getDueDate());
			model.setFinishDate(getFinishDate());
			model.setStatus(getStatus());

			model = (TasksEntry)Proxy.newProxyInstance(TasksEntry.class.getClassLoader(),
					new Class[] { TasksEntry.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = new ExpandoBridgeImpl(TasksEntry.class.getName(),
					getPrimaryKey());
		}

		return _expandoBridge;
	}

	public Object clone() {
		TasksEntryImpl clone = new TasksEntryImpl();

		clone.setTasksEntryId(getTasksEntryId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setTitle(getTitle());
		clone.setPriority(getPriority());
		clone.setAssigneeUserId(getAssigneeUserId());
		clone.setResolverUserId(getResolverUserId());
		clone.setDueDate(getDueDate());
		clone.setFinishDate(getFinishDate());
		clone.setStatus(getStatus());

		return clone;
	}

	public int compareTo(TasksEntry tasksEntry) {
		int value = 0;

		if (getPriority() < tasksEntry.getPriority()) {
			value = -1;
		}
		else if (getPriority() > tasksEntry.getPriority()) {
			value = 1;
		}
		else {
			value = 0;
		}

		value = value * -1;

		if (value != 0) {
			return value;
		}

		value = DateUtil.compareTo(getCreateDate(), tasksEntry.getCreateDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		TasksEntry tasksEntry = null;

		try {
			tasksEntry = (TasksEntry)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = tasksEntry.getPrimaryKey();

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

	private long _tasksEntryId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _title;
	private int _priority;
	private long _assigneeUserId;
	private long _resolverUserId;
	private Date _dueDate;
	private Date _finishDate;
	private int _status;
	private transient ExpandoBridge _expandoBridge;
}