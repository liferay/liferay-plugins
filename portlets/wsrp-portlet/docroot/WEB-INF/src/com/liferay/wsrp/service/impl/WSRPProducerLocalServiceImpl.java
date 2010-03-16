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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.util.PwdGenerator;
import com.liferay.wsrp.WSRPProducerNameException;
import com.liferay.wsrp.model.WSRPProducer;
import com.liferay.wsrp.service.base.WSRPProducerLocalServiceBaseImpl;

import java.util.Date;
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

	public WSRPProducer addWSRPProducer(
			long companyId, String name, String portletIds)
		throws PortalException, SystemException {

		Date now = new Date();

		validate(name);

		portletIds = buildInstanceablePortletIds(portletIds);

		long wsrpProducerId = CounterLocalServiceUtil.increment();

		WSRPProducer wsrpProducer = wsrpProducerPersistence.create(
			wsrpProducerId);

		wsrpProducer.setCompanyId(companyId);
		wsrpProducer.setCreateDate(now);
		wsrpProducer.setModifiedDate(now);
		wsrpProducer.setName(name);
		wsrpProducer.setPortletIds(portletIds);

		wsrpProducerPersistence.update(wsrpProducer, false);

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

		portletIds = buildInstanceablePortletIds(portletIds);

		WSRPProducer wsrpProducer = wsrpProducerPersistence.findByPrimaryKey(
			wsrpProducerId);

		wsrpProducer.setModifiedDate(new Date());
		wsrpProducer.setName(name);
		wsrpProducer.setPortletIds(portletIds);

		wsrpProducerPersistence.update(wsrpProducer, false);

		return wsrpProducer;
	}

	protected String buildInstanceablePortletIds(String portletIds) {
		String[] ids = StringUtil.split(portletIds);

		for (int i = 0; i < ids.length; i++) {
			String portletId = ids[i];

			if (portletId.indexOf(PortletConstants.INSTANCE_SEPARATOR) != -1) {
				continue;
			}

			Portlet portlet = PortletLocalServiceUtil.getPortletById(portletId);

			if (portlet.isInstanceable()) {
				String instanceId = PwdGenerator.getPassword(
					PwdGenerator.KEY1 + PwdGenerator.KEY2 +
					PwdGenerator.KEY3, 4);

				ids[i] = portletId + PortletConstants.INSTANCE_SEPARATOR +
					instanceId;
			}
		}

		return StringUtil.merge(ids);
	}

	protected void validate(String name) throws PortalException {
		if (Validator.isNull(name)) {
			throw new WSRPProducerNameException();
		}
	}

}