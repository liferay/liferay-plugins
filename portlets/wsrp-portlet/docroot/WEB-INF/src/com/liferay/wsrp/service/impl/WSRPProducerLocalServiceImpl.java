/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.wsrp.service.impl;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.wsrp.WSRPProducerNameException;
import com.liferay.wsrp.model.WSRPProducer;
import com.liferay.wsrp.service.base.WSRPProducerLocalServiceBaseImpl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * <a href="WSRPProducerLocalServiceImpl.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WSRPProducerLocalServiceImpl
	extends WSRPProducerLocalServiceBaseImpl {

	public static final String WSRP_GROUP_NAME = "WSRP";

	public WSRPProducer addWSRPProducer(
			long userId, String name, String portletIds)
		throws PortalException, SystemException {

		// WSRP producer

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		validate(name);

		long wsrpProducerId = CounterLocalServiceUtil.increment();

		WSRPProducer wsrpProducer = wsrpProducerPersistence.create(
			wsrpProducerId);

		wsrpProducer.setCompanyId(user.getCompanyId());
		wsrpProducer.setCreateDate(now);
		wsrpProducer.setModifiedDate(now);
		wsrpProducer.setName(name);
		wsrpProducer.setPortletIds(portletIds);

		wsrpProducerPersistence.update(wsrpProducer, false);

		// Group

		addGroup(user);

		return wsrpProducer;
	}

	public void deleteWSRPProducer(long wsrpProducerId)
		throws PortalException, SystemException {

		WSRPProducer wsrpProducer = wsrpProducerPersistence.findByPrimaryKey(
			wsrpProducerId);

		deleteWSRPProducer(wsrpProducer);
	}

	public void deleteWSRPProducer(WSRPProducer wsrpProducer)
		throws SystemException {

		wsrpProducerPersistence.remove(wsrpProducer);
	}

	public List<WSRPProducer> getWSRPProducers(
			long companyId, int start, int end)
		throws SystemException {

		return wsrpProducerPersistence.findByCompanyId(companyId, start, end);
	}

	public int getWSRPProducersCount(long companyId) throws SystemException {
		return wsrpProducerPersistence.countByCompanyId(companyId);
	}

	public WSRPProducer updateWSRPProducer(
			long wsrpProducerId, String name, String portletIds)
		throws PortalException, SystemException {

		validate(name);

		WSRPProducer wsrpProducer = wsrpProducerPersistence.findByPrimaryKey(
			wsrpProducerId);

		wsrpProducer.setModifiedDate(new Date());
		wsrpProducer.setName(name);
		wsrpProducer.setPortletIds(portletIds);

		wsrpProducerPersistence.update(wsrpProducer, false);

		return wsrpProducer;
	}

	protected void addGroup(User user) throws PortalException, SystemException {
		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		int type = GroupConstants.TYPE_COMMUNITY_PRIVATE;

		params.put("type", type);

		List<Group> groups = GroupLocalServiceUtil.search(
			user.getCompanyId(), WSRP_GROUP_NAME, null, params, 0, 1);

		if (!groups.isEmpty()) {
			return;
		}

		Group group = GroupLocalServiceUtil.addGroup(
			user.getUserId(), null, 0, 0, WSRP_GROUP_NAME, null, type, null,
			true, null);

		LayoutLocalServiceUtil.addLayout(
			user.getUserId(), group.getGroupId(), false,
			LayoutConstants.DEFAULT_PARENT_LAYOUT_ID, "Portlets", null, null,
			LayoutConstants.TYPE_PORTLET, false, "/portlets", null);
	}

	protected void validate(String name) throws PortalException {
		if (Validator.isNull(name)) {
			throw new WSRPProducerNameException();
		}
	}

}