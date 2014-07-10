/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.workflow.kaleo.service.persistence;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class KaleoTaskInstanceTokenQuery {

	public KaleoTaskInstanceTokenQuery(ServiceContext serviceContext) {
		_serviceContext = serviceContext;
		_companyId = serviceContext.getCompanyId();
		_userId = serviceContext.getUserId();
	}

	public Long[] getAssetPrimaryKeys() {
		return _assetPrimaryKeys;
	}

	public String[] getAssetTypes() {
		return _assetTypes;
	}

	public String getAssigneeClassName() {
		return _assigneeClassName;
	}

	public Long getAssigneeClassPK() {
		return _assigneeClassPK;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public Date getDueDateGT() {
		return _dueDateGT;
	}

	public Date getDueDateLT() {
		return _dueDateLT;
	}

	public int getEnd() {
		return _end;
	}

	public Long getKaleoInstanceId() {
		return _kaleoInstanceId;
	}

	public OrderByComparator<KaleoTaskInstanceToken> getOrderByComparator() {
		return _orderByComparator;
	}

	public List<Long> getRoleIds() {
		return _roleIds;
	}

	public ServiceContext getServiceContext() {
		return _serviceContext;
	}

	public int getStart() {
		return _start;
	}

	public String getTaskName() {
		return _taskName;
	}

	public long getUserId() {
		return _userId;
	}

	public boolean isAndOperator() {
		return _andOperator;
	}

	public Boolean isCompleted() {
		return _completed;
	}

	public Boolean isSearchByUserRoles() {
		return _searchByUserRoles;
	}

	public void setAndOperator(boolean andOperator) {
		_andOperator = andOperator;
	}

	public void setAssetPrimaryKeys(Long[] assetPrimaryKeys) {
		_assetPrimaryKeys = assetPrimaryKeys;
	}

	public void setAssetTypes(String[] assetTypes) {
		_assetTypes = assetTypes;
	}

	public void setAssigneeClassName(String assigneeClassName) {
		_assigneeClassName = assigneeClassName;
	}

	public void setAssigneeClassPK(Long assigneeClassPK) {
		_assigneeClassPK = assigneeClassPK;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public void setCompleted(Boolean completed) {
		_completed = completed;
	}

	public void setDueDateGT(Date dueDateGT) {
		_dueDateGT = dueDateGT;
	}

	public void setDueDateLT(Date dueDateLT) {
		_dueDateLT = dueDateLT;
	}

	public void setEnd(int end) {
		_end = end;
	}

	public void setKaleoInstanceId(Long kaleoInstanceId) {
		_kaleoInstanceId = kaleoInstanceId;
	}

	public void setOrderByComparator(
		OrderByComparator<KaleoTaskInstanceToken> orderByComparator) {

		_orderByComparator = orderByComparator;
	}

	public void setRoleIds(List<Long> roleIds) {
		_roleIds = roleIds;
	}

	public void setSearchByUserRoles(Boolean searchByUserRoles) {
		_searchByUserRoles = searchByUserRoles;
	}

	public void setServiceContext(ServiceContext serviceContext) {
		_serviceContext = serviceContext;
	}

	public void setStart(int start) {
		_start = start;
	}

	public void setTaskName(String taskName) {
		_taskName = taskName;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	private boolean _andOperator = true;
	private Long[] _assetPrimaryKeys;
	private String[] _assetTypes;
	private String _assigneeClassName;
	private Long _assigneeClassPK;
	private long _companyId;
	private Boolean _completed;
	private Date _dueDateGT;
	private Date _dueDateLT;
	private int _end = QueryUtil.ALL_POS;
	private Long _kaleoInstanceId;
	private OrderByComparator<KaleoTaskInstanceToken> _orderByComparator;
	private List<Long> _roleIds;
	private Boolean _searchByUserRoles;
	private ServiceContext _serviceContext;
	private int _start = QueryUtil.ALL_POS;
	private String _taskName;
	private long _userId;

}