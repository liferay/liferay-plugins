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

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portlet.exportimport.lar.BasePortletDataHandler;
import com.liferay.portlet.exportimport.lar.DataLevel;
import com.liferay.portlet.exportimport.lar.PortletDataContext;
import com.liferay.portlet.exportimport.lar.PortletDataHandlerBoolean;
import com.liferay.portlet.exportimport.lar.PortletDataHandlerControl;
import com.liferay.portlet.exportimport.lar.StagedModelDataHandlerUtil;
import com.liferay.portlet.exportimport.lar.StagedModelType;
import com.liferay.portlet.exportimport.xstream.XStreamAliasRegistryUtil;
import com.liferay.wsrp.model.WSRPConsumer;
import com.liferay.wsrp.model.WSRPConsumerPortlet;
import com.liferay.wsrp.model.WSRPProducer;
import com.liferay.wsrp.model.impl.WSRPConsumerImpl;
import com.liferay.wsrp.model.impl.WSRPConsumerPortletImpl;
import com.liferay.wsrp.model.impl.WSRPProducerImpl;
import com.liferay.wsrp.service.WSRPConsumerLocalServiceUtil;
import com.liferay.wsrp.service.WSRPConsumerPortletLocalServiceUtil;
import com.liferay.wsrp.service.WSRPProducerLocalServiceUtil;

import java.util.List;

import javax.portlet.PortletPreferences;

/**
 * @author Michael C. Han
 */
public class AdminPortletDataHandler extends BasePortletDataHandler {

	public static final String NAMESPACE = "wsrp";

	public AdminPortletDataHandler() {
		setDataLevel(DataLevel.PORTAL);
		setDeletionSystemEventStagedModelTypes(
			new StagedModelType(WSRPConsumer.class),
			new StagedModelType(WSRPConsumerPortlet.class),
			new StagedModelType(WSRPProducer.class));
		setExportControls(
			new PortletDataHandlerBoolean(NAMESPACE, "wsrp-producers", false),
			new PortletDataHandlerBoolean(
				NAMESPACE, "wsrp-consumers", true,
				new PortletDataHandlerControl[] {
					new PortletDataHandlerBoolean(
						NAMESPACE, "wsrp-consumer-portlets")
				}));
		setPublishToLiveByDefault(true);

		XStreamAliasRegistryUtil.register(
			WSRPConsumerImpl.class, "WSRPConsumer");
		XStreamAliasRegistryUtil.register(
			WSRPConsumerPortletImpl.class, "WSRPConsumerPortlet");
		XStreamAliasRegistryUtil.register(
			WSRPProducerImpl.class, "WSRPProducer");
	}

	@Override
	protected PortletPreferences doDeleteData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		if (portletDataContext.addPrimaryKey(
				AdminPortletDataHandler.class, "deleteData")) {

			return portletPreferences;
		}

		WSRPProducerLocalServiceUtil.deleteWSRPProducers(
			portletDataContext.getCompanyId());

		WSRPConsumerLocalServiceUtil.deleteWSRPConsumers(
			portletDataContext.getCompanyId());

		return portletPreferences;
	}

	@Override
	protected String doExportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		Element rootElement = addExportDataRootElement(portletDataContext);

		rootElement.addAttribute(
			"group-id", String.valueOf(portletDataContext.getScopeGroupId()));

		if (portletDataContext.getBooleanParameter(
				NAMESPACE, "wsrp-producers")) {

			ActionableDynamicQuery wsrpProducerExportActionableDynamicQuery =
				WSRPProducerLocalServiceUtil.getExportActionableDynamicQuery(
					portletDataContext);

			wsrpProducerExportActionableDynamicQuery.performActions();
		}

		if (portletDataContext.getBooleanParameter(
				NAMESPACE, "wsrp-consumers")) {

			ActionableDynamicQuery wsrpConsumerExportActionableDynamicQuery =
				WSRPConsumerLocalServiceUtil.getExportActionableDynamicQuery(
					portletDataContext);

			wsrpConsumerExportActionableDynamicQuery.performActions();

			if (portletDataContext.getBooleanParameter(
					NAMESPACE, "wsrp-consumer-portlets")) {

				ActionableDynamicQuery
					wsrpConsumerPortletExportActionableDynamicQuery =
						WSRPConsumerPortletLocalServiceUtil.
							getExportActionableDynamicQuery(portletDataContext);

				wsrpConsumerPortletExportActionableDynamicQuery.
					performActions();
			}
		}

		return getExportDataRootElementString(rootElement);
	}

	@Override
	protected PortletPreferences doImportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences, String data)
		throws Exception {

		if (portletDataContext.getBooleanParameter(
				NAMESPACE, "wsrp-producers")) {

			Element wsrpProducersElement =
				portletDataContext.getImportDataGroupElement(
					WSRPProducer.class);

			List<Element> wsrpProducerElements =
				wsrpProducersElement.elements();

			for (Element wsrpProducerElement : wsrpProducerElements) {
				StagedModelDataHandlerUtil.importStagedModel(
					portletDataContext, wsrpProducerElement);
			}
		}

		if (portletDataContext.getBooleanParameter(
				NAMESPACE, "wsrp-consumers")) {

			Element wsrpConsumersElement =
				portletDataContext.getImportDataGroupElement(
					WSRPConsumer.class);

			List<Element> wsrpConsumerElements =
				wsrpConsumersElement.elements();

			for (Element wsrpConsumerElement : wsrpConsumerElements) {
				StagedModelDataHandlerUtil.importStagedModel(
					portletDataContext, wsrpConsumerElement);
			}

			if (portletDataContext.getBooleanParameter(
					NAMESPACE, "wsrp-consumer-portlets")) {

				Element wsrpConsumerPortletsElement =
					portletDataContext.getImportDataGroupElement(
						WSRPConsumerPortlet.class);

				List<Element> wsrpConsumerPortletElements =
					wsrpConsumerPortletsElement.elements();

				for (Element wsrpConsumerPortletElement :
						wsrpConsumerPortletElements) {

					StagedModelDataHandlerUtil.importStagedModel(
						portletDataContext, wsrpConsumerPortletElement);
				}
			}
		}

		return null;
	}

	@Override
	protected void doPrepareManifestSummary(
			PortletDataContext portletDataContext,
			PortletPreferences portletPreferences)
		throws Exception {

		ActionableDynamicQuery wsrpConsumerExportActionableDynamicQuery =
			WSRPConsumerLocalServiceUtil.getExportActionableDynamicQuery(
				portletDataContext);

		wsrpConsumerExportActionableDynamicQuery.performCount();

		ActionableDynamicQuery wsrpConsumerPortletExportActionableDynamicQuery =
			WSRPConsumerPortletLocalServiceUtil.getExportActionableDynamicQuery(
				portletDataContext);

		wsrpConsumerPortletExportActionableDynamicQuery.performCount();

		ActionableDynamicQuery wsrpProducerExportActionableDynamicQuery =
			WSRPProducerLocalServiceUtil.getExportActionableDynamicQuery(
				portletDataContext);

		wsrpProducerExportActionableDynamicQuery.performCount();
	}

}