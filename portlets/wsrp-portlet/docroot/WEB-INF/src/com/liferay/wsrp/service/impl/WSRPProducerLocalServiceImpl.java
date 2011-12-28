/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.util.PwdGenerator;
import com.liferay.wsrp.NoSuchProducerException;
import com.liferay.wsrp.WSRPProducerNameException;
import com.liferay.wsrp.model.WSRPProducer;
import com.liferay.wsrp.service.base.WSRPProducerLocalServiceBaseImpl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class WSRPProducerLocalServiceImpl
	extends WSRPProducerLocalServiceBaseImpl {

	public WSRPProducer addWSRPProducer(
			long userId, String name, String version, String portletIds,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		Group group = addGroup(userId, name);

		return addWSRPProducer(
			userId, group.getGroupId(), name, version, portletIds,
			serviceContext);
	}

	public WSRPProducer addWSRPProducer(
			long userId, long groupId, String name, String version,
			String portletIds, ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		portletIds = transformPortletIds(portletIds);
		Date now = new Date();

		validate(name);

		long wsrpProducerId = CounterLocalServiceUtil.increment();

		WSRPProducer wsrpProducer = wsrpProducerPersistence.create(
			wsrpProducerId);

		wsrpProducer.setUuid(serviceContext.getUuid());
		wsrpProducer.setGroupId(groupId);
		wsrpProducer.setCompanyId(user.getCompanyId());
		wsrpProducer.setCreateDate(now);
		wsrpProducer.setModifiedDate(now);
		wsrpProducer.setName(name);
		wsrpProducer.setVersion(version);
		wsrpProducer.setPortletIds(portletIds);

		wsrpProducerPersistence.update(wsrpProducer, false);

		return wsrpProducer;
	}

	@Override
	public void deleteWSRPProducer(long wsrpProducerId)
		throws PortalException, SystemException {

		WSRPProducer wsrpProducer = wsrpProducerPersistence.findByPrimaryKey(
			wsrpProducerId);

		deleteWSRPProducer(wsrpProducer);
	}

	@Override
	public void deleteWSRPProducer(WSRPProducer wsrpProducer)
		throws PortalException, SystemException {

		// WSRP producer

		wsrpProducerPersistence.remove(wsrpProducer);

		// Group

		groupLocalService.deleteGroup(wsrpProducer.getGroupId());
	}

	public WSRPProducer getWSRPProducer(String wsrpProducerUuid)
		throws PortalException, SystemException {

		List<WSRPProducer> wsrpProducers = wsrpProducerPersistence.findByUuid(
			wsrpProducerUuid);

		if (wsrpProducers.isEmpty()) {
			throw new NoSuchProducerException(
				"No WSRP producer exists with uuid " + wsrpProducerUuid);
		}

		return wsrpProducers.get(0);
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
			long wsrpProducerId, String name, String version, String portletIds)
		throws PortalException, SystemException {

		// WSRP producer

		portletIds = transformPortletIds(portletIds);

		validate(name);

		WSRPProducer wsrpProducer = wsrpProducerPersistence.findByPrimaryKey(
			wsrpProducerId);

		wsrpProducer.setModifiedDate(new Date());
		wsrpProducer.setName(name);
		wsrpProducer.setVersion(version);
		wsrpProducer.setPortletIds(portletIds);

		wsrpProducerPersistence.update(wsrpProducer, false);

		// Group

		updateGroup(wsrpProducer, name);

		return wsrpProducer;
	}

	protected Group addGroup(long userId, String name)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		name = getGroupName(name);

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		int type = GroupConstants.TYPE_SITE_SYSTEM;

		params.put("type", type);

		List<Group> groups = groupLocalService.search(
			user.getCompanyId(), name, null, params, 0, 1);

		if (!groups.isEmpty()) {
			return groups.get(0);
		}

		Group group = groupLocalService.addGroup(
			user.getUserId(), null, 0, 0, name, null, type, null, true, true,
			null);

		layoutLocalService.addLayout(
			user.getUserId(), group.getGroupId(), false,
			LayoutConstants.DEFAULT_PARENT_LAYOUT_ID, "Portlets", null, null,
			LayoutConstants.TYPE_PORTLET, false, "/portlets",
			new ServiceContext());

		return group;
	}

	/**
	 * @see com.liferay.portal.model.impl.LayoutTypePortletImpl
	 */
	protected String getFullInstanceSeparator() {
		String instanceId = PwdGenerator.getPassword(
			PwdGenerator.KEY1 + PwdGenerator.KEY2 + PwdGenerator.KEY3, 4);

		return PortletConstants.INSTANCE_SEPARATOR + instanceId;
	}

	protected String getGroupName(String name) {
		return _WSRP_GROUP_NAME + StringPool.MINUS + name;
	}

	protected String transformPortletIds(String portletIds) {
		String[] portletIdsArray = StringUtil.split(portletIds);

		for (int i = 0; i < portletIdsArray.length; i++) {
			String portletId = portletIdsArray[i];

			if (portletId.contains(PortletConstants.INSTANCE_SEPARATOR)) {
				continue;
			}

			Portlet portlet = PortletLocalServiceUtil.getPortletById(portletId);

			if (!portlet.isInstanceable()) {
				continue;
			}

			String instanceId = PwdGenerator.getPassword(
				PwdGenerator.KEY1 + PwdGenerator.KEY2 + PwdGenerator.KEY3, 4);

			portletIdsArray[i] =
				portletId + PortletConstants.INSTANCE_SEPARATOR + instanceId;
		}

		return StringUtil.merge(portletIdsArray);
	}

	protected void updateGroup(WSRPProducer wsrpProducer, String name)
		throws PortalException, SystemException {

		Group group = groupLocalService.getGroup(wsrpProducer.getGroupId());

		group.setName(getGroupName(name));

		groupLocalService.updateGroup(group);
	}

	protected void validate(String name) throws PortalException {
		if (Validator.isNull(name)) {
			throw new WSRPProducerNameException();
		}
	}

	private static final String _WSRP_GROUP_NAME = "WSRP";

}