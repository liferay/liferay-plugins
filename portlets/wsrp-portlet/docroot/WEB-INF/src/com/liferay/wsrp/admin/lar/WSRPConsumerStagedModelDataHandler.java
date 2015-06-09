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

package com.liferay.wsrp.admin.lar;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.exportimport.lar.BaseStagedModelDataHandler;
import com.liferay.portlet.exportimport.lar.ExportImportPathUtil;
import com.liferay.portlet.exportimport.lar.PortletDataContext;
import com.liferay.wsrp.model.WSRPConsumer;
import com.liferay.wsrp.service.WSRPConsumerLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael C. Han
 */
public class WSRPConsumerStagedModelDataHandler
	extends BaseStagedModelDataHandler<WSRPConsumer> {

	public static final String[] CLASS_NAMES = {WSRPConsumer.class.getName()};

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException {

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		WSRPConsumer wsrpConsumer =
			WSRPConsumerLocalServiceUtil.fetchWSRPConsumerByUuidAndCompanyId(
				uuid, group.getCompanyId());

		if (wsrpConsumer != null) {
			deleteStagedModel(wsrpConsumer);
		}
	}

	@Override
	public void deleteStagedModel(WSRPConsumer wsrpConsumer)
		throws PortalException {

		WSRPConsumerLocalServiceUtil.deleteWSRPConsumer(wsrpConsumer);
	}

	@Override
	public List<WSRPConsumer> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {

		List<WSRPConsumer> wsrpConsumers = new ArrayList<>();

		wsrpConsumers.add(
			WSRPConsumerLocalServiceUtil.fetchWSRPConsumerByUuidAndCompanyId(
				uuid, companyId));

		return wsrpConsumers;
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(WSRPConsumer wsrpConsumer) {
		return wsrpConsumer.getName();
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, WSRPConsumer wsrpConsumer)
		throws Exception {

		Element wsrpConsumerElement = portletDataContext.getExportDataElement(
			wsrpConsumer);

		portletDataContext.addClassedModel(
			wsrpConsumerElement,
			ExportImportPathUtil.getModelPath(wsrpConsumer), wsrpConsumer);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, WSRPConsumer wsrpConsumer)
		throws Exception {

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			wsrpConsumer);

		WSRPConsumer importedWSRPConsumer = null;

		if (portletDataContext.isDataStrategyMirror()) {
			WSRPConsumer existingWSRPConsumer =
				WSRPConsumerLocalServiceUtil.
					fetchWSRPConsumerByUuidAndCompanyId(
						wsrpConsumer.getUuid(),
						portletDataContext.getCompanyId());

			if (existingWSRPConsumer == null) {
				serviceContext.setUuid(wsrpConsumer.getUuid());

				importedWSRPConsumer =
					WSRPConsumerLocalServiceUtil.addWSRPConsumer(
						portletDataContext.getCompanyId(), null,
						wsrpConsumer.getName(), wsrpConsumer.getUrl(),
						wsrpConsumer.getForwardCookies(),
						wsrpConsumer.getForwardHeaders(),
						wsrpConsumer.getMarkupCharacterSets(), serviceContext);
			}
			else {
				existingWSRPConsumer.setName(wsrpConsumer.getName());
				existingWSRPConsumer.setUrl(wsrpConsumer.getUrl());
				existingWSRPConsumer.setWsdl(wsrpConsumer.getWsdl());
				existingWSRPConsumer.setForwardCookies(
					wsrpConsumer.getForwardCookies());
				existingWSRPConsumer.setForwardHeaders(
					wsrpConsumer.getForwardHeaders());
				existingWSRPConsumer.setMarkupCharacterSets(
					wsrpConsumer.getMarkupCharacterSets());

				importedWSRPConsumer =
					WSRPConsumerLocalServiceUtil.updateWSRPConsumer(
						existingWSRPConsumer);
			}
		}
		else {
			importedWSRPConsumer = WSRPConsumerLocalServiceUtil.addWSRPConsumer(
				portletDataContext.getCompanyId(), null, wsrpConsumer.getName(),
				wsrpConsumer.getUrl(), wsrpConsumer.getForwardCookies(),
				wsrpConsumer.getForwardHeaders(),
				wsrpConsumer.getMarkupCharacterSets(), serviceContext);
		}

		portletDataContext.importClassedModel(
			wsrpConsumer, importedWSRPConsumer);
	}

}