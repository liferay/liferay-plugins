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

import com.liferay.tms.model.ProjectMilestone;
import com.liferay.tms.model.ProjectMilestoneSoap;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="ProjectMilestoneModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ProjectMilestoneModelImpl extends BaseModelImpl {
	public static final String TABLE_NAME = "TMS_ProjectMilestone";
	public static final Object[][] TABLE_COLUMNS = {
			{ "projectMilestoneId", new Integer(Types.BIGINT) },
			

			{ "companyId", new Integer(Types.BIGINT) },
			

			{ "userId", new Integer(Types.BIGINT) },
			

			{ "userName", new Integer(Types.VARCHAR) },
			

			{ "createDate", new Integer(Types.TIMESTAMP) },
			

			{ "modifiedDate", new Integer(Types.TIMESTAMP) },
			

			{ "title", new Integer(Types.VARCHAR) },
			

			{ "description", new Integer(Types.VARCHAR) },
			

			{ "projectEntryId", new Integer(Types.BIGINT) },
			

			{ "dueDate", new Integer(Types.TIMESTAMP) },
			

			{ "finishDate", new Integer(Types.TIMESTAMP) }
		};
	public static final String TABLE_SQL_CREATE = "create table TMS_ProjectMilestone (projectMilestoneId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,title VARCHAR(75) null,description VARCHAR(75) null,projectEntryId LONG,dueDate DATE null,finishDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table TMS_ProjectMilestone";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.tms.model.ProjectMilestone"),
			true);

	public static ProjectMilestone toModel(ProjectMilestoneSoap soapModel) {
		ProjectMilestone model = new ProjectMilestoneImpl();

		model.setProjectMilestoneId(soapModel.getProjectMilestoneId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setTitle(soapModel.getTitle());
		model.setDescription(soapModel.getDescription());
		model.setProjectEntryId(soapModel.getProjectEntryId());
		model.setDueDate(soapModel.getDueDate());
		model.setFinishDate(soapModel.getFinishDate());

		return model;
	}

	public static List<ProjectMilestone> toModels(
		ProjectMilestoneSoap[] soapModels) {
		List<ProjectMilestone> models = new ArrayList<ProjectMilestone>(soapModels.length);

		for (ProjectMilestoneSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.tms.model.ProjectMilestone"));

	public ProjectMilestoneModelImpl() {
	}

	public long getPrimaryKey() {
		return _projectMilestoneId;
	}

	public void setPrimaryKey(long pk) {
		setProjectMilestoneId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_projectMilestoneId);
	}

	public long getProjectMilestoneId() {
		return _projectMilestoneId;
	}

	public void setProjectMilestoneId(long projectMilestoneId) {
		if (projectMilestoneId != _projectMilestoneId) {
			_projectMilestoneId = projectMilestoneId;
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

	public long getProjectEntryId() {
		return _projectEntryId;
	}

	public void setProjectEntryId(long projectEntryId) {
		if (projectEntryId != _projectEntryId) {
			_projectEntryId = projectEntryId;
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

	public ProjectMilestone toEscapedModel() {
		if (isEscapedModel()) {
			return (ProjectMilestone)this;
		}
		else {
			ProjectMilestone model = new ProjectMilestoneImpl();

			model.setNew(isNew());
			model.setEscapedModel(true);

			model.setProjectMilestoneId(getProjectMilestoneId());
			model.setCompanyId(getCompanyId());
			model.setUserId(getUserId());
			model.setUserName(HtmlUtil.escape(getUserName()));
			model.setCreateDate(getCreateDate());
			model.setModifiedDate(getModifiedDate());
			model.setTitle(HtmlUtil.escape(getTitle()));
			model.setDescription(HtmlUtil.escape(getDescription()));
			model.setProjectEntryId(getProjectEntryId());
			model.setDueDate(getDueDate());
			model.setFinishDate(getFinishDate());

			model = (ProjectMilestone)Proxy.newProxyInstance(ProjectMilestone.class.getClassLoader(),
					new Class[] { ProjectMilestone.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = new ExpandoBridgeImpl(ProjectMilestone.class.getName(),
					getPrimaryKey());
		}

		return _expandoBridge;
	}

	public Object clone() {
		ProjectMilestoneImpl clone = new ProjectMilestoneImpl();

		clone.setProjectMilestoneId(getProjectMilestoneId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setTitle(getTitle());
		clone.setDescription(getDescription());
		clone.setProjectEntryId(getProjectEntryId());
		clone.setDueDate(getDueDate());
		clone.setFinishDate(getFinishDate());

		return clone;
	}

	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}

		ProjectMilestoneImpl projectMilestone = (ProjectMilestoneImpl)obj;

		long pk = projectMilestone.getPrimaryKey();

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

		ProjectMilestoneImpl projectMilestone = null;

		try {
			projectMilestone = (ProjectMilestoneImpl)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = projectMilestone.getPrimaryKey();

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
	private transient ExpandoBridge _expandoBridge;
}