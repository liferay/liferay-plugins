/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

package com.liferay.wol.model.impl;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;

import com.liferay.portlet.service.BaseModelImpl;
import com.liferay.portlet.service.PropsUtil;

import com.liferay.wol.model.MeetupRegistration;
import com.liferay.wol.model.MeetupRegistrationSoap;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="MeetupRegistrationModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class MeetupRegistrationModelImpl extends BaseModelImpl {
	public static final String TABLE_NAME = "WOL_MeetupRegistration";
	public static final Object[][] TABLE_COLUMNS = {
			{ "meetupRegistrationId", new Integer(Types.BIGINT) },
			

			{ "companyId", new Integer(Types.BIGINT) },
			

			{ "userId", new Integer(Types.BIGINT) },
			

			{ "userName", new Integer(Types.VARCHAR) },
			

			{ "createDate", new Integer(Types.TIMESTAMP) },
			

			{ "modifiedDate", new Integer(Types.TIMESTAMP) },
			

			{ "meetupEntryId", new Integer(Types.BIGINT) },
			

			{ "status", new Integer(Types.INTEGER) },
			

			{ "comments", new Integer(Types.VARCHAR) }
		};
	public static final String TABLE_SQL_CREATE = "create table WOL_MeetupRegistration (meetupRegistrationId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,meetupEntryId LONG,status INTEGER,comments STRING null)";
	public static final String TABLE_SQL_DROP = "drop table WOL_MeetupRegistration";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean CACHE_ENABLED = GetterUtil.getBoolean(PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.wol.model.MeetupRegistration"),
			true);

	public static MeetupRegistration toModel(MeetupRegistrationSoap soapModel) {
		MeetupRegistration model = new MeetupRegistrationImpl();

		model.setMeetupRegistrationId(soapModel.getMeetupRegistrationId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setMeetupEntryId(soapModel.getMeetupEntryId());
		model.setStatus(soapModel.getStatus());
		model.setComments(soapModel.getComments());

		return model;
	}

	public static List<MeetupRegistration> toModels(
		MeetupRegistrationSoap[] soapModels) {
		List<MeetupRegistration> models = new ArrayList<MeetupRegistration>(soapModels.length);

		for (MeetupRegistrationSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(PropsUtil.get(
				"lock.expiration.time.com.liferay.wol.model.MeetupRegistration"));

	public MeetupRegistrationModelImpl() {
	}

	public long getPrimaryKey() {
		return _meetupRegistrationId;
	}

	public void setPrimaryKey(long pk) {
		setMeetupRegistrationId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_meetupRegistrationId);
	}

	public long getMeetupRegistrationId() {
		return _meetupRegistrationId;
	}

	public void setMeetupRegistrationId(long meetupRegistrationId) {
		if (meetupRegistrationId != _meetupRegistrationId) {
			_meetupRegistrationId = meetupRegistrationId;
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

	public long getMeetupEntryId() {
		return _meetupEntryId;
	}

	public void setMeetupEntryId(long meetupEntryId) {
		if (meetupEntryId != _meetupEntryId) {
			_meetupEntryId = meetupEntryId;
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

	public String getComments() {
		return GetterUtil.getString(_comments);
	}

	public void setComments(String comments) {
		if (((comments == null) && (_comments != null)) ||
				((comments != null) && (_comments == null)) ||
				((comments != null) && (_comments != null) &&
				!comments.equals(_comments))) {
			_comments = comments;
		}
	}

	public MeetupRegistration toEscapedModel() {
		if (isEscapedModel()) {
			return (MeetupRegistration)this;
		}
		else {
			MeetupRegistration model = new MeetupRegistrationImpl();

			model.setEscapedModel(true);

			model.setMeetupRegistrationId(getMeetupRegistrationId());
			model.setCompanyId(getCompanyId());
			model.setUserId(getUserId());
			model.setUserName(HtmlUtil.escape(getUserName()));
			model.setCreateDate(getCreateDate());
			model.setModifiedDate(getModifiedDate());
			model.setMeetupEntryId(getMeetupEntryId());
			model.setStatus(getStatus());
			model.setComments(HtmlUtil.escape(getComments()));

			model = (MeetupRegistration)Proxy.newProxyInstance(MeetupRegistration.class.getClassLoader(),
					new Class[] { MeetupRegistration.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public Object clone() {
		MeetupRegistrationImpl clone = new MeetupRegistrationImpl();

		clone.setMeetupRegistrationId(getMeetupRegistrationId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setMeetupEntryId(getMeetupEntryId());
		clone.setStatus(getStatus());
		clone.setComments(getComments());

		return clone;
	}

	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}

		MeetupRegistrationImpl meetupRegistration = (MeetupRegistrationImpl)obj;

		int value = 0;

		value = DateUtil.compareTo(getModifiedDate(),
				meetupRegistration.getModifiedDate());

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

		MeetupRegistrationImpl meetupRegistration = null;

		try {
			meetupRegistration = (MeetupRegistrationImpl)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = meetupRegistration.getPrimaryKey();

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

	private long _meetupRegistrationId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _meetupEntryId;
	private int _status;
	private String _comments;
}