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

package com.liferay.bi.report.model.impl;

import com.liferay.bi.report.model.RequestedReport;
import com.liferay.bi.report.model.RequestedReportSoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.model.impl.ExpandoBridgeImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="RequestedReportModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class RequestedReportModelImpl extends BaseModelImpl<RequestedReport> {
	public static final String TABLE_NAME = "Report_RequestedReport";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", new Integer(Types.VARCHAR) },
			

			{ "requestId", new Integer(Types.BIGINT) },
			

			{ "companyId", new Integer(Types.BIGINT) },
			

			{ "groupId", new Integer(Types.BIGINT) },
			

			{ "userId", new Integer(Types.BIGINT) },
			

			{ "createDate", new Integer(Types.TIMESTAMP) },
			

			{ "modifiedDate", new Integer(Types.TIMESTAMP) },
			

			{ "definitionId", new Integer(Types.BIGINT) },
			

			{ "requestStatus", new Integer(Types.VARCHAR) },
			

			{ "isSchedule", new Integer(Types.BOOLEAN) }
		};
	public static final String TABLE_SQL_CREATE = "create table Report_RequestedReport (uuid_ VARCHAR(75) null,requestId LONG not null primary key,companyId LONG,groupId LONG,userId LONG,createDate DATE null,modifiedDate DATE null,definitionId LONG,requestStatus VARCHAR(75) null,isSchedule BOOLEAN)";
	public static final String TABLE_SQL_DROP = "drop table Report_RequestedReport";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.bi.report.model.RequestedReport"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.bi.report.model.RequestedReport"),
			true);

	public static RequestedReport toModel(RequestedReportSoap soapModel) {
		RequestedReport model = new RequestedReportImpl();

		model.setUuid(soapModel.getUuid());
		model.setRequestId(soapModel.getRequestId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setGroupId(soapModel.getGroupId());
		model.setUserId(soapModel.getUserId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setDefinitionId(soapModel.getDefinitionId());
		model.setRequestStatus(soapModel.getRequestStatus());
		model.setIsSchedule(soapModel.getIsSchedule());

		return model;
	}

	public static List<RequestedReport> toModels(
		RequestedReportSoap[] soapModels) {
		List<RequestedReport> models = new ArrayList<RequestedReport>(soapModels.length);

		for (RequestedReportSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.bi.report.model.RequestedReport"));

	public RequestedReportModelImpl() {
	}

	public long getPrimaryKey() {
		return _requestId;
	}

	public void setPrimaryKey(long pk) {
		setRequestId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_requestId);
	}

	public String getUuid() {
		return GetterUtil.getString(_uuid);
	}

	public void setUuid(String uuid) {
		_uuid = uuid;

		if (_originalUuid == null) {
			_originalUuid = uuid;
		}
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	public long getRequestId() {
		return _requestId;
	}

	public void setRequestId(long requestId) {
		_requestId = requestId;

		if (!_setOriginalRequestId) {
			_setOriginalRequestId = true;

			_originalRequestId = requestId;
		}
	}

	public long getOriginalRequestId() {
		return _originalRequestId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = groupId;
		}
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
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

	public long getDefinitionId() {
		return _definitionId;
	}

	public void setDefinitionId(long definitionId) {
		_definitionId = definitionId;
	}

	public String getRequestStatus() {
		return GetterUtil.getString(_requestStatus);
	}

	public void setRequestStatus(String requestStatus) {
		_requestStatus = requestStatus;
	}

	public boolean getIsSchedule() {
		return _isSchedule;
	}

	public boolean isIsSchedule() {
		return _isSchedule;
	}

	public void setIsSchedule(boolean isSchedule) {
		_isSchedule = isSchedule;
	}

	public RequestedReport toEscapedModel() {
		if (isEscapedModel()) {
			return (RequestedReport)this;
		}
		else {
			RequestedReport model = new RequestedReportImpl();

			model.setNew(isNew());
			model.setEscapedModel(true);

			model.setUuid(HtmlUtil.escape(getUuid()));
			model.setRequestId(getRequestId());
			model.setCompanyId(getCompanyId());
			model.setGroupId(getGroupId());
			model.setUserId(getUserId());
			model.setCreateDate(getCreateDate());
			model.setModifiedDate(getModifiedDate());
			model.setDefinitionId(getDefinitionId());
			model.setRequestStatus(HtmlUtil.escape(getRequestStatus()));
			model.setIsSchedule(getIsSchedule());

			model = (RequestedReport)Proxy.newProxyInstance(RequestedReport.class.getClassLoader(),
					new Class[] { RequestedReport.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = new ExpandoBridgeImpl(RequestedReport.class.getName(),
					getPrimaryKey());
		}

		return _expandoBridge;
	}

	public Object clone() {
		RequestedReportImpl clone = new RequestedReportImpl();

		clone.setUuid(getUuid());
		clone.setRequestId(getRequestId());
		clone.setCompanyId(getCompanyId());
		clone.setGroupId(getGroupId());
		clone.setUserId(getUserId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setDefinitionId(getDefinitionId());
		clone.setRequestStatus(getRequestStatus());
		clone.setIsSchedule(getIsSchedule());

		return clone;
	}

	public int compareTo(RequestedReport requestedReport) {
		int value = 0;

		value = DateUtil.compareTo(getModifiedDate(),
				requestedReport.getModifiedDate());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		RequestedReport requestedReport = null;

		try {
			requestedReport = (RequestedReport)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = requestedReport.getPrimaryKey();

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

	private String _uuid;
	private String _originalUuid;
	private long _requestId;
	private long _originalRequestId;
	private boolean _setOriginalRequestId;
	private long _companyId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _definitionId;
	private String _requestStatus;
	private boolean _isSchedule;
	private transient ExpandoBridge _expandoBridge;
}