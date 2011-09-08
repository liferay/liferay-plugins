/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

/**
 * @author Marcellus Tavares
 */
public class KaleoDefinitionQuery {

	public KaleoDefinitionQuery(ServiceContext serviceContext) {
		_serviceContext = serviceContext;

		_companyId = serviceContext.getCompanyId();
		_userId = serviceContext.getUserId();
	}

	public long getCompanyId() {
		return _companyId;
	}

	public int getEnd() {
		return _end;
	}

	public String getName() {
		return _name;
	}

	public OrderByComparator getOrderByComparator() {
		return _orderByComparator;
	}

	public Long getScope() {
		return _scope;
	}

	public ServiceContext getServiceContext() {
		return _serviceContext;
	}

	public int getStart() {
		return _start;
	}

	public long getUserId() {
		return _userId;
	}

	public Boolean isActive() {
		return _active;
	}

	public boolean isAndOperator() {
		return _andOperator;
	}

	public void setActive(Boolean active) {
		_active = active;
	}

	public void setAndOperator(boolean andOperator) {
		_andOperator = andOperator;
	}

	public void setEnd(int end) {
		_end = end;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setOrderByComparator(OrderByComparator orderByComparator) {
		_orderByComparator = orderByComparator;
	}

	public void setScope(Long scope) {
		_scope = scope;
	}

	public void setServiceContext(ServiceContext serviceContext) {
		_serviceContext = serviceContext;
	}

	public void setStart(int start) {
		_start = start;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	private Boolean _active;
	private boolean _andOperator = true;
	private long _companyId;
	private int _end = QueryUtil.ALL_POS;
	private String _name;
	private OrderByComparator _orderByComparator;
	private Long _scope;
	private ServiceContext _serviceContext;
	private int _start = QueryUtil.ALL_POS;
	private long _userId;

}