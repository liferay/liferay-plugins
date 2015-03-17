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
		throws PortalException {

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		WSRPConsumer wsrpConsumer = fetchStagedModelByUuidAndCompanyId(
			uuid, group.getCompanyId());

		if (wsrpConsumer != null) {
			WSRPConsumerLocalServiceUtil.deleteWSRPConsumer(wsrpConsumer);
		}
	}

	@Override
	public WSRPConsumer fetchStagedModelByUuidAndCompanyId(
		String uuid, long companyId) {

		return WSRPConsumerLocalServiceUtil.fetchWSRPConsumerByUuidAndCompanyId(
			uuid, companyId);
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
				fetchStagedModelByUuidAndCompanyId(
					wsrpConsumer.getUuid(), portletDataContext.getCompanyId());

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