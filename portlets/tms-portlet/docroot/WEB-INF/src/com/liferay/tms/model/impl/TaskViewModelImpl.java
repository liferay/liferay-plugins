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

import com.liferay.tms.model.TaskView;
import com.liferay.tms.model.TaskViewSoap;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="TaskViewModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class TaskViewModelImpl extends BaseModelImpl {
	public static final String TABLE_NAME = "TMS_TaskView";
	public static final Object[][] TABLE_COLUMNS = {
			{ "taskViewId", new Integer(Types.BIGINT) },
			

			{ "groupId", new Integer(Types.BIGINT) },
			

			{ "companyId", new Integer(Types.VARCHAR) },
			

			{ "userId", new Integer(Types.VARCHAR) },
			

			{ "userName", new Integer(Types.VARCHAR) },
			

			{ "createDate", new Integer(Types.TIMESTAMP) },
			

			{ "modifiedDate", new Integer(Types.TIMESTAMP) },
			

			{ "title", new Integer(Types.VARCHAR) },
			

			{ "tags", new Integer(Types.VARCHAR) },
			

			{ "notTags", new Integer(Types.VARCHAR) },
			

			{ "assigneeUserId", new Integer(Types.BIGINT) },
			

			{ "reporterUserId", new Integer(Types.BIGINT) },
			

			{ "includeProjectTasks", new Integer(Types.INTEGER) },
			

			{ "isPrivate", new Integer(Types.BOOLEAN) }
		};
	public static final String TABLE_SQL_CREATE = "create table TMS_TaskView (taskViewId LONG not null primary key,groupId LONG,companyId VARCHAR(75) null,userId VARCHAR(75) null,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,title VARCHAR(75) null,tags VARCHAR(75) null,notTags VARCHAR(75) null,assigneeUserId LONG,reporterUserId LONG,includeProjectTasks INTEGER,isPrivate BOOLEAN)";
	public static final String TABLE_SQL_DROP = "drop table TMS_TaskView";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.tms.model.TaskView"),
			true);

	public static TaskView toModel(TaskViewSoap soapModel) {
		TaskView model = new TaskViewImpl();

		model.setTaskViewId(soapModel.getTaskViewId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setTitle(soapModel.getTitle());
		model.setTags(soapModel.getTags());
		model.setNotTags(soapModel.getNotTags());
		model.setAssigneeUserId(soapModel.getAssigneeUserId());
		model.setReporterUserId(soapModel.getReporterUserId());
		model.setIncludeProjectTasks(soapModel.getIncludeProjectTasks());
		model.setIsPrivate(soapModel.getIsPrivate());

		return model;
	}

	public static List<TaskView> toModels(TaskViewSoap[] soapModels) {
		List<TaskView> models = new ArrayList<TaskView>(soapModels.length);

		for (TaskViewSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.tms.model.TaskView"));

	public TaskViewModelImpl() {
	}

	public long getPrimaryKey() {
		return _taskViewId;
	}

	public void setPrimaryKey(long pk) {
		setTaskViewId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_taskViewId);
	}

	public long getTaskViewId() {
		return _taskViewId;
	}

	public void setTaskViewId(long taskViewId) {
		if (taskViewId != _taskViewId) {
			_taskViewId = taskViewId;
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

	public String getCompanyId() {
		return GetterUtil.getString(_companyId);
	}

	public void setCompanyId(String companyId) {
		if (((companyId == null) && (_companyId != null)) ||
				((companyId != null) && (_companyId == null)) ||
				((companyId != null) && (_companyId != null) &&
				!companyId.equals(_companyId))) {
			_companyId = companyId;
		}
	}

	public String getUserId() {
		return GetterUtil.getString(_userId);
	}

	public void setUserId(String userId) {
		if (((userId == null) && (_userId != null)) ||
				((userId != null) && (_userId == null)) ||
				((userId != null) && (_userId != null) &&
				!userId.equals(_userId))) {
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

	public String getTags() {
		return GetterUtil.getString(_tags);
	}

	public void setTags(String tags) {
		if (((tags == null) && (_tags != null)) ||
				((tags != null) && (_tags == null)) ||
				((tags != null) && (_tags != null) && !tags.equals(_tags))) {
			_tags = tags;
		}
	}

	public String getNotTags() {
		return GetterUtil.getString(_notTags);
	}

	public void setNotTags(String notTags) {
		if (((notTags == null) && (_notTags != null)) ||
				((notTags != null) && (_notTags == null)) ||
				((notTags != null) && (_notTags != null) &&
				!notTags.equals(_notTags))) {
			_notTags = notTags;
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

	public long getReporterUserId() {
		return _reporterUserId;
	}

	public void setReporterUserId(long reporterUserId) {
		if (reporterUserId != _reporterUserId) {
			_reporterUserId = reporterUserId;
		}
	}

	public int getIncludeProjectTasks() {
		return _includeProjectTasks;
	}

	public void setIncludeProjectTasks(int includeProjectTasks) {
		if (includeProjectTasks != _includeProjectTasks) {
			_includeProjectTasks = includeProjectTasks;
		}
	}

	public boolean getIsPrivate() {
		return _isPrivate;
	}

	public boolean isIsPrivate() {
		return _isPrivate;
	}

	public void setIsPrivate(boolean isPrivate) {
		if (isPrivate != _isPrivate) {
			_isPrivate = isPrivate;
		}
	}

	public TaskView toEscapedModel() {
		if (isEscapedModel()) {
			return (TaskView)this;
		}
		else {
			TaskView model = new TaskViewImpl();

			model.setNew(isNew());
			model.setEscapedModel(true);

			model.setTaskViewId(getTaskViewId());
			model.setGroupId(getGroupId());
			model.setCompanyId(HtmlUtil.escape(getCompanyId()));
			model.setUserId(HtmlUtil.escape(getUserId()));
			model.setUserName(HtmlUtil.escape(getUserName()));
			model.setCreateDate(getCreateDate());
			model.setModifiedDate(getModifiedDate());
			model.setTitle(HtmlUtil.escape(getTitle()));
			model.setTags(HtmlUtil.escape(getTags()));
			model.setNotTags(HtmlUtil.escape(getNotTags()));
			model.setAssigneeUserId(getAssigneeUserId());
			model.setReporterUserId(getReporterUserId());
			model.setIncludeProjectTasks(getIncludeProjectTasks());
			model.setIsPrivate(getIsPrivate());

			model = (TaskView)Proxy.newProxyInstance(TaskView.class.getClassLoader(),
					new Class[] { TaskView.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = new ExpandoBridgeImpl(TaskView.class.getName(),
					getPrimaryKey());
		}

		return _expandoBridge;
	}

	public Object clone() {
		TaskViewImpl clone = new TaskViewImpl();

		clone.setTaskViewId(getTaskViewId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setTitle(getTitle());
		clone.setTags(getTags());
		clone.setNotTags(getNotTags());
		clone.setAssigneeUserId(getAssigneeUserId());
		clone.setReporterUserId(getReporterUserId());
		clone.setIncludeProjectTasks(getIncludeProjectTasks());
		clone.setIsPrivate(getIsPrivate());

		return clone;
	}

	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}

		TaskViewImpl taskView = (TaskViewImpl)obj;

		long pk = taskView.getPrimaryKey();

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

		TaskViewImpl taskView = null;

		try {
			taskView = (TaskViewImpl)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = taskView.getPrimaryKey();

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
	private transient ExpandoBridge _expandoBridge;
}