/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.wsrp.admin.lar;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.BaseStagedModelDataHandler;
import com.liferay.portal.kernel.lar.ExportImportPathUtil;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.service.ServiceContext;
import com.liferay.wsrp.model.WSRPConsumer;
import com.liferay.wsrp.model.WSRPConsumerPortlet;
import com.liferay.wsrp.service.WSRPConsumerLocalServiceUtil;
import com.liferay.wsrp.service.WSRPConsumerPortletLocalServiceUtil;

import java.util.Map;

/**
 * @author Michael C. Han
 */
public class WSRPConsumerPortletStagedModelDataHandler
	extends BaseStagedModelDataHandler<WSRPConsumerPortlet> {

	public static final String[] CLASS_NAMES =
		{WSRPConsumerPortlet.class.getName()};

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(WSRPConsumerPortlet consumerPortlet) {
		return consumerPortlet.getName();
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext,
			WSRPConsumerPortlet consumerPortlet)
		throws Exception {

		WSRPConsumer consumer =
			WSRPConsumerLocalServiceUtil.getWSRPConsumer(
				consumerPortlet.getWsrpConsumerId());

		StagedModelDataHandlerUtil.exportReferenceStagedModel(
			portletDataContext, consumerPortlet, consumer,
			PortletDataContext.REFERENCE_TYPE_STRONG);

		Element entryElement = portletDataContext.getExportDataElement(
			consumerPortlet);

		portletDataContext.addClassedModel(
			entryElement, ExportImportPathUtil.getModelPath(consumerPortlet),
			consumerPortlet);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext,
			WSRPConsumerPortlet consumerPortlet)
		throws Exception {

		StagedModelDataHandlerUtil.importReferenceStagedModel(
			portletDataContext, consumerPortlet, WSRPConsumer.class,
			consumerPortlet.getWsrpConsumerId());

		Map<Long, Long> consumerIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				WSRPConsumer.class);

		long consumerId = MapUtil.getLong(
			consumerIds, consumerPortlet.getWsrpConsumerId(),
			consumerPortlet.getWsrpConsumerId());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			consumerPortlet);

		WSRPConsumerPortlet importedConsumerPortlet = null;

		if (portletDataContext.isDataStrategyMirror()) {
			WSRPConsumerPortlet existingConsumerPortlet =
				WSRPConsumerPortletLocalServiceUtil.
					fetchWSRPConsumerPortletByUuidAndCompanyId(
						consumerPortlet.getUuid(),
						portletDataContext.getCompanyId());

			if (existingConsumerPortlet == null) {
				serviceContext.setUuid(consumerPortlet.getUuid());

				importedConsumerPortlet =
					WSRPConsumerPortletLocalServiceUtil.addWSRPConsumerPortlet(
						consumerId, consumerPortlet.getName(),
						consumerPortlet.getPortletHandle(), serviceContext);
			}
			else {
				existingConsumerPortlet.setWsrpConsumerId(consumerId);
				existingConsumerPortlet.setName(consumerPortlet.getName());
				existingConsumerPortlet.setPortletHandle(
					consumerPortlet.getPortletHandle());

				importedConsumerPortlet =
					WSRPConsumerPortletLocalServiceUtil.
						updateWSRPConsumerPortlet(existingConsumerPortlet);
			}
		}
		else {
			importedConsumerPortlet =
				WSRPConsumerPortletLocalServiceUtil.addWSRPConsumerPortlet(
					consumerId, consumerPortlet.getName(),
					consumerPortlet.getPortletHandle(), serviceContext);
		}

		portletDataContext.importClassedModel(
			consumerPortlet, importedConsumerPortlet);
	}

	@Override
	protected boolean validateMissingReference(
			String uuid, long companyId, long groupId)
		throws Exception {

		WSRPConsumerPortlet consumerPortlet =
			WSRPConsumerPortletLocalServiceUtil.
				fetchWSRPConsumerPortletByUuidAndCompanyId(uuid, companyId);

		if (consumerPortlet == null) {
			return false;
		}

		return true;
	}

}