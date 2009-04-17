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

import com.liferay.bi.report.model.ReportRequest;
import com.liferay.bi.report.model.ReportRequestSoap;

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
 * <a href="ReportRequestModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ReportRequestModelImpl extends BaseModelImpl<ReportRequest> {
	public static final String TABLE_NAME = "Report_ReportRequest";
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
			

			{ "isSchdule", new Integer(Types.BOOLEAN) }
		};
	public static final String TABLE_SQL_CREATE = "create table Report_ReportRequest (uuid_ VARCHAR(75) null,requestId LONG not null primary key,companyId LONG,groupId LONG,userId LONG,createDate DATE null,modifiedDate DATE null,definitionId LONG,requestStatus VARCHAR(75) null,isSchdule BOOLEAN)";
	public static final String TABLE_SQL_DROP = "drop table Report_ReportRequest";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.bi.report.model.ReportRequest"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.bi.report.model.ReportRequest"),
			true);

	public static ReportRequest toModel(ReportRequestSoap soapModel) {
		ReportRequest model = new ReportRequestImpl();

		model.setUuid(soapModel.getUuid());
		model.setRequestId(soapModel.getRequestId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setGroupId(soapModel.getGroupId());
		model.setUserId(soapModel.getUserId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setDefinitionId(soapModel.getDefinitionId());
		model.setRequestStatus(soapModel.getRequestStatus());
		model.setIsSchdule(soapModel.getIsSchdule());

		return model;
	}

	public static List<ReportRequest> toModels(ReportRequestSoap[] soapModels) {
		List<ReportRequest> models = new ArrayList<ReportRequest>(soapModels.length);

		for (ReportRequestSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.bi.report.model.ReportRequest"));

	public ReportRequestModelImpl() {
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
		if ((uuid != null) && !uuid.equals(_uuid)) {
			_uuid = uuid;

			if (_originalUuid == null) {
				_originalUuid = uuid;
			}
		}
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	public long getRequestId() {
		return _requestId;
	}

	public void setRequestId(long requestId) {
		if (requestId != _requestId) {
			_requestId = requestId;

			if (!_setOriginalRequestId) {
				_setOriginalRequestId = true;

				_originalRequestId = requestId;
			}
		}
	}

	public long getOriginalRequestId() {
		return _originalRequestId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		if (companyId != _companyId) {
			_companyId = companyId;
		}
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		if (groupId != _groupId) {
			_groupId = groupId;

			if (!_setOriginalGroupId) {
				_setOriginalGroupId = true;

				_originalGroupId = groupId;
			}
		}
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		if (userId != _userId) {
			_userId = userId;
		}
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		if ((createDate != _createDate) ||
				((createDate != null) && !createDate.equals(_createDate))) {
			_createDate = createDate;
		}
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		if ((modifiedDate != _modifiedDate) ||
				((modifiedDate != null) && !modifiedDate.equals(_modifiedDate))) {
			_modifiedDate = modifiedDate;
		}
	}

	public long getDefinitionId() {
		return _definitionId;
	}

	public void setDefinitionId(long definitionId) {
		if (definitionId != _definitionId) {
			_definitionId = definitionId;

			if (!_setOriginalDefinitionId) {
				_setOriginalDefinitionId = true;

				_originalDefinitionId = definitionId;
			}
		}
	}

	public long getOriginalDefinitionId() {
		return _originalDefinitionId;
	}

	public String getRequestStatus() {
		return GetterUtil.getString(_requestStatus);
	}

	public void setRequestStatus(String requestStatus) {
		if ((requestStatus != _requestStatus) ||
				((requestStatus != null) &&
				!requestStatus.equals(_requestStatus))) {
			_requestStatus = requestStatus;
		}
	}

	public boolean getIsSchdule() {
		return _isSchdule;
	}

	public boolean isIsSchdule() {
		return _isSchdule;
	}

	public void setIsSchdule(boolean isSchdule) {
		if (isSchdule != _isSchdule) {
			_isSchdule = isSchdule;
		}
	}

	public ReportRequest toEscapedModel() {
		if (isEscapedModel()) {
			return (ReportRequest)this;
		}
		else {
			ReportRequest model = new ReportRequestImpl();

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
			model.setIsSchdule(getIsSchdule());

			model = (ReportRequest)Proxy.newProxyInstance(ReportRequest.class.getClassLoader(),
					new Class[] { ReportRequest.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = new ExpandoBridgeImpl(ReportRequest.class.getName(),
					getPrimaryKey());
		}

		return _expandoBridge;
	}

	public Object clone() {
		ReportRequestImpl clone = new ReportRequestImpl();

		clone.setUuid(getUuid());
		clone.setRequestId(getRequestId());
		clone.setCompanyId(getCompanyId());
		clone.setGroupId(getGroupId());
		clone.setUserId(getUserId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setDefinitionId(getDefinitionId());
		clone.setRequestStatus(getRequestStatus());
		clone.setIsSchdule(getIsSchdule());

		return clone;
	}

	public int compareTo(ReportRequest reportRequest) {
		int value = 0;

		value = DateUtil.compareTo(getModifiedDate(),
				reportRequest.getModifiedDate());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		ReportRequest reportRequest = null;

		try {
			reportRequest = (ReportRequest)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = reportRequest.getPrimaryKey();

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
	private long _originalDefinitionId;
	private boolean _setOriginalDefinitionId;
	private String _requestStatus;
	private boolean _isSchdule;
	private transient ExpandoBridge _expandoBridge;
}