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
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.wsrp.model.WSRPProducer;
import com.liferay.wsrp.service.WSRPProducerLocalServiceUtil;

/**
 * @author Michael C. Han
 */
public class WSRPProducerStagedModelDataHandler
	extends BaseStagedModelDataHandler<WSRPProducer> {

	public static final String[] CLASS_NAMES = {WSRPProducer.class.getName()};

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException, SystemException {

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		WSRPProducer producer =
			WSRPProducerLocalServiceUtil.fetchWSRPProducerByUuidAndCompanyId(
				uuid, group.getCompanyId());

		if (producer != null) {
			WSRPProducerLocalServiceUtil.deleteWSRPProducer(producer);
		}
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(WSRPProducer producer) {
		return producer.getName();
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, WSRPProducer producer)
		throws Exception {

		Element entryElement = portletDataContext.getExportDataElement(
			producer);

		portletDataContext.addClassedModel(
			entryElement, ExportImportPathUtil.getModelPath(producer),
			producer);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, WSRPProducer producer)
		throws Exception {

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			producer);

		WSRPProducer importedProducer = null;

		if (portletDataContext.isDataStrategyMirror()) {
			WSRPProducer existingProducer =
				WSRPProducerLocalServiceUtil.
					fetchWSRPProducerByUuidAndCompanyId(
						producer.getUuid(), portletDataContext.getCompanyId());

			if (existingProducer == null) {
				serviceContext.setUuid(producer.getUuid());

				importedProducer =
					WSRPProducerLocalServiceUtil.addWSRPProducer(
						portletDataContext.getUserId(null), producer.getName(),
						producer.getVersion(), producer.getPortletIds(),
						serviceContext);
			}
			else {
				existingProducer.setName(producer.getName());
				existingProducer.setVersion(producer.getVersion());
				existingProducer.setPortletIds(producer.getPortletIds());

				WSRPProducerLocalServiceUtil.updateWSRPProducer(
					existingProducer);

				importedProducer =
					WSRPProducerLocalServiceUtil.updateWSRPProducer(
						existingProducer);
			}
		}
		else {
			importedProducer =
				WSRPProducerLocalServiceUtil.addWSRPProducer(
					portletDataContext.getUserId(null), producer.getName(),
					producer.getVersion(), producer.getPortletIds(),
					serviceContext);
		}

		portletDataContext.importClassedModel(producer, importedProducer);
	}

}