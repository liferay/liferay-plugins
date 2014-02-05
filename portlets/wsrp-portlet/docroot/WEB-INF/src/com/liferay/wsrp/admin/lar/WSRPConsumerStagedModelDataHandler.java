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
import com.liferay.wsrp.model.WSRPConsumer;
import com.liferay.wsrp.service.WSRPConsumerLocalServiceUtil;

/**
 * @author Michael C. Han
 */
public class WSRPConsumerStagedModelDataHandler
	extends BaseStagedModelDataHandler<WSRPConsumer> {

	public static final String[] CLASS_NAMES = {WSRPConsumer.class.getName()};

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException, SystemException {

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		WSRPConsumer consumer =
			WSRPConsumerLocalServiceUtil.fetchWSRPConsumerByUuidAndCompanyId(
				uuid, group.getCompanyId());

		if (consumer != null) {
			WSRPConsumerLocalServiceUtil.deleteWSRPConsumer(consumer);
		}
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(WSRPConsumer consumer) {
		return consumer.getName();
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, WSRPConsumer consumer)
		throws Exception {

		Element entryElement = portletDataContext.getExportDataElement(
			consumer);

		portletDataContext.addClassedModel(
			entryElement, ExportImportPathUtil.getModelPath(consumer),
			consumer);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, WSRPConsumer consumer)
		throws Exception {

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			consumer);

		WSRPConsumer importedConsumer = null;

		if (portletDataContext.isDataStrategyMirror()) {
			WSRPConsumer existingConsumer =
				WSRPConsumerLocalServiceUtil.
					fetchWSRPConsumerByUuidAndCompanyId(
						consumer.getUuid(), portletDataContext.getCompanyId());

			if (existingConsumer == null) {
				serviceContext.setUuid(consumer.getUuid());

				importedConsumer =
					WSRPConsumerLocalServiceUtil.addWSRPConsumer(
						portletDataContext.getCompanyId(), null,
						consumer.getName(), consumer.getUrl(),
						consumer.getForwardCookies(),
						consumer.getForwardHeaders(),
						consumer.getMarkupCharacterSets(), serviceContext);
			}
			else {
				existingConsumer.setName(consumer.getName());
				existingConsumer.setUrl(consumer.getUrl());
				existingConsumer.setWsdl(consumer.getWsdl());
				existingConsumer.setForwardCookies(
					consumer.getForwardCookies());
				existingConsumer.setForwardHeaders(
					consumer.getForwardHeaders());
				existingConsumer.setMarkupCharacterSets(
					consumer.getMarkupCharacterSets());

				importedConsumer =
					WSRPConsumerLocalServiceUtil.updateWSRPConsumer(
						existingConsumer);
			}
		}
		else {
			importedConsumer =
				WSRPConsumerLocalServiceUtil.addWSRPConsumer(
					portletDataContext.getCompanyId(), null, consumer.getName(),
					consumer.getUrl(), consumer.getForwardCookies(),
					consumer.getForwardHeaders(),
					consumer.getMarkupCharacterSets(), serviceContext);
		}

		portletDataContext.importClassedModel(consumer, importedConsumer);
	}

	@Override
	protected boolean validateMissingReference(
			String uuid, long companyId, long groupId)
		throws Exception {

		WSRPConsumer consumer =
			WSRPConsumerLocalServiceUtil.fetchWSRPConsumerByUuidAndCompanyId(
				uuid, companyId);

		if (consumer == null) {
			return false;
		}

		return true;
	}

}