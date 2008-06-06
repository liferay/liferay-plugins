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

import com.liferay.wol.model.MeetupEntry;
import com.liferay.wol.model.MeetupEntrySoap;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="MeetupEntryModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class MeetupEntryModelImpl extends BaseModelImpl {
	public static final String TABLE_NAME = "WOL_MeetupEntry";
	public static final Object[][] TABLE_COLUMNS = {
			{ "meetupEntryId", new Integer(Types.BIGINT) },
			

			{ "companyId", new Integer(Types.BIGINT) },
			

			{ "userId", new Integer(Types.BIGINT) },
			

			{ "userName", new Integer(Types.VARCHAR) },
			

			{ "createDate", new Integer(Types.TIMESTAMP) },
			

			{ "modifiedDate", new Integer(Types.TIMESTAMP) },
			

			{ "title", new Integer(Types.VARCHAR) },
			

			{ "description", new Integer(Types.VARCHAR) },
			

			{ "startDate", new Integer(Types.TIMESTAMP) },
			

			{ "endDate", new Integer(Types.TIMESTAMP) },
			

			{ "addressId", new Integer(Types.BIGINT) },
			

			{ "totalAttendees", new Integer(Types.INTEGER) },
			

			{ "maxAttendees", new Integer(Types.INTEGER) },
			

			{ "price", new Integer(Types.DOUBLE) }
		};
	public static final String TABLE_SQL_CREATE = "create table WOL_MeetupEntry (meetupEntryId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,title VARCHAR(75) null,description VARCHAR(75) null,startDate DATE null,endDate DATE null,addressId LONG,totalAttendees INTEGER,maxAttendees INTEGER,price DOUBLE)";
	public static final String TABLE_SQL_DROP = "drop table WOL_MeetupEntry";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean CACHE_ENABLED = GetterUtil.getBoolean(PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.wol.model.MeetupEntry"),
			true);

	public static MeetupEntry toModel(MeetupEntrySoap soapModel) {
		MeetupEntry model = new MeetupEntryImpl();

		model.setMeetupEntryId(soapModel.getMeetupEntryId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setTitle(soapModel.getTitle());
		model.setDescription(soapModel.getDescription());
		model.setStartDate(soapModel.getStartDate());
		model.setEndDate(soapModel.getEndDate());
		model.setAddressId(soapModel.getAddressId());
		model.setTotalAttendees(soapModel.getTotalAttendees());
		model.setMaxAttendees(soapModel.getMaxAttendees());
		model.setPrice(soapModel.getPrice());

		return model;
	}

	public static List<MeetupEntry> toModels(MeetupEntrySoap[] soapModels) {
		List<MeetupEntry> models = new ArrayList<MeetupEntry>(soapModels.length);

		for (MeetupEntrySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(PropsUtil.get(
				"lock.expiration.time.com.liferay.wol.model.MeetupEntry"));

	public MeetupEntryModelImpl() {
	}

	public long getPrimaryKey() {
		return _meetupEntryId;
	}

	public void setPrimaryKey(long pk) {
		setMeetupEntryId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_meetupEntryId);
	}

	public long getMeetupEntryId() {
		return _meetupEntryId;
	}

	public void setMeetupEntryId(long meetupEntryId) {
		if (meetupEntryId != _meetupEntryId) {
			_meetupEntryId = meetupEntryId;
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

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		if (((startDate == null) && (_startDate != null)) ||
				((startDate != null) && (_startDate == null)) ||
				((startDate != null) && (_startDate != null) &&
				!startDate.equals(_startDate))) {
			_startDate = startDate;
		}
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		if (((endDate == null) && (_endDate != null)) ||
				((endDate != null) && (_endDate == null)) ||
				((endDate != null) && (_endDate != null) &&
				!endDate.equals(_endDate))) {
			_endDate = endDate;
		}
	}

	public long getAddressId() {
		return _addressId;
	}

	public void setAddressId(long addressId) {
		if (addressId != _addressId) {
			_addressId = addressId;
		}
	}

	public int getTotalAttendees() {
		return _totalAttendees;
	}

	public void setTotalAttendees(int totalAttendees) {
		if (totalAttendees != _totalAttendees) {
			_totalAttendees = totalAttendees;
		}
	}

	public int getMaxAttendees() {
		return _maxAttendees;
	}

	public void setMaxAttendees(int maxAttendees) {
		if (maxAttendees != _maxAttendees) {
			_maxAttendees = maxAttendees;
		}
	}

	public double getPrice() {
		return _price;
	}

	public void setPrice(double price) {
		if (price != _price) {
			_price = price;
		}
	}

	public MeetupEntry toEscapedModel() {
		if (isEscapedModel()) {
			return (MeetupEntry)this;
		}
		else {
			MeetupEntry model = new MeetupEntryImpl();

			model.setEscapedModel(true);

			model.setMeetupEntryId(getMeetupEntryId());
			model.setCompanyId(getCompanyId());
			model.setUserId(getUserId());
			model.setUserName(HtmlUtil.escape(getUserName()));
			model.setCreateDate(getCreateDate());
			model.setModifiedDate(getModifiedDate());
			model.setTitle(HtmlUtil.escape(getTitle()));
			model.setDescription(HtmlUtil.escape(getDescription()));
			model.setStartDate(getStartDate());
			model.setEndDate(getEndDate());
			model.setAddressId(getAddressId());
			model.setTotalAttendees(getTotalAttendees());
			model.setMaxAttendees(getMaxAttendees());
			model.setPrice(getPrice());

			model = (MeetupEntry)Proxy.newProxyInstance(MeetupEntry.class.getClassLoader(),
					new Class[] { MeetupEntry.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public Object clone() {
		MeetupEntryImpl clone = new MeetupEntryImpl();

		clone.setMeetupEntryId(getMeetupEntryId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setTitle(getTitle());
		clone.setDescription(getDescription());
		clone.setStartDate(getStartDate());
		clone.setEndDate(getEndDate());
		clone.setAddressId(getAddressId());
		clone.setTotalAttendees(getTotalAttendees());
		clone.setMaxAttendees(getMaxAttendees());
		clone.setPrice(getPrice());

		return clone;
	}

	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}

		MeetupEntryImpl meetupEntry = (MeetupEntryImpl)obj;

		int value = 0;

		value = DateUtil.compareTo(getStartDate(), meetupEntry.getStartDate());

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

		MeetupEntryImpl meetupEntry = null;

		try {
			meetupEntry = (MeetupEntryImpl)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = meetupEntry.getPrimaryKey();

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

	private long _meetupEntryId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _title;
	private String _description;
	private Date _startDate;
	private Date _endDate;
	private long _addressId;
	private int _totalAttendees;
	private int _maxAttendees;
	private double _price;
}