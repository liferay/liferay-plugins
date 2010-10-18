/*
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

package com.liferay.wsrp.portlet.lar;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.BasePortletDataHandler;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.PortletDataException;
import com.liferay.portal.kernel.lar.PortletDataHandlerBoolean;
import com.liferay.portal.kernel.lar.PortletDataHandlerControl;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.wsrp.model.WSRPConsumer;
import com.liferay.wsrp.model.WSRPConsumerPortlet;
import com.liferay.wsrp.model.WSRPProducer;
import com.liferay.wsrp.service.WSRPConsumerLocalServiceUtil;
import com.liferay.wsrp.service.WSRPConsumerPortletLocalServiceUtil;
import com.liferay.wsrp.service.WSRPProducerLocalServiceUtil;

import java.util.List;

import javax.portlet.PortletPreferences;

/**
 * @author Michael C. Han
 */
public class AdminPortletDataHandlerImpl extends BasePortletDataHandler {
	public PortletDataHandlerControl[] getExportControls()
		throws PortletDataException {

		return new PortletDataHandlerControl[] {_producers, _consumers};
	}

	public PortletDataHandlerControl[] getImportControls()
		throws PortletDataException {

		return new PortletDataHandlerControl[] {_producers, _consumers};
	}

	public boolean isPublishToLiveByDefault() {
		return _PUBLISH_TO_LIVE_BY_DEFAULT;
	}

	protected PortletPreferences doDeleteData(
			PortletDataContext context, String portletId,
			PortletPreferences preferences)
		throws Exception {

		long companyId = context.getCompanyId();

		if (context.getBooleanParameter(_NAMESPACE, "wsrp-producers")) {
			List<WSRPProducer> wsrpProducers =
				WSRPProducerLocalServiceUtil.getWSRPProducers(
					companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (WSRPProducer wsrpProducer : wsrpProducers) {
				WSRPProducerLocalServiceUtil.deleteWSRPProducer(wsrpProducer);
			}
		}

		if (context.getBooleanParameter(_NAMESPACE, "wsrp-consumers")) {
			List<WSRPConsumer> wsrpConsumers =
				WSRPConsumerLocalServiceUtil.getWSRPConsumers(
					companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (WSRPConsumer wsrpConsumer : wsrpConsumers) {
				WSRPConsumerLocalServiceUtil.deleteWSRPConsumer(wsrpConsumer);
			}
		}

		return null;

	}

	protected String doExportData(
			PortletDataContext context, String portletId,
			PortletPreferences preferences)
		throws Exception {

		Document document = SAXReaderUtil.createDocument();

		Element rootElement = document.addElement("wsrp-data");

		if (context.getBooleanParameter(_NAMESPACE, "wsrp-producers")) {
			Element producersElement = rootElement.addElement("wsrp-producers");

			List<WSRPProducer> producers =
				WSRPProducerLocalServiceUtil.getWSRPProducers(
					context.getCompanyId(),
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (WSRPProducer producer : producers) {
				exportWSRPProducer(context, producersElement, producer);
			}
		}

		if (context.getBooleanParameter(_NAMESPACE, "wsrp-consumers")) {
			Element consumersElement = rootElement.addElement("wsrp-consumers");

			List<WSRPConsumer> consumers =
				WSRPConsumerLocalServiceUtil.getWSRPConsumers(
					context.getCompanyId(),
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (WSRPConsumer consumer : consumers) {
				exportWSRPConsumer(context, consumersElement, consumer);
			}
		}

		return document.formattedString();
	}

	protected PortletPreferences doImportData(
			PortletDataContext context, String portletId,
			PortletPreferences preferences, String data)
		throws Exception {

		Document document = SAXReaderUtil.read(data);

		Element rootElement = document.getRootElement();

		if (context.getBooleanParameter(_NAMESPACE, "wsrp-producers")) {
			Element wsrpProducersElement = rootElement.element(
				"wsrp-producers");

			for (Element wsrpProducerElement :
					wsrpProducersElement.elements("wsrp-producer")) {

				String wsrpProducerPath = wsrpProducerElement.attributeValue(
					"path");

				if (!context.isPathNotProcessed(wsrpProducerPath)) {
					continue;
				}

				WSRPProducer wsrpProducer =
					(WSRPProducer)context.getZipEntryAsObject(wsrpProducerPath);

				importWSRPProducer(context, wsrpProducer);
			}
		}

		if (context.getBooleanParameter(_NAMESPACE, "wsrp-consumers")) {
			Element wsrpConsumersElement = rootElement.element(
				"wsrp-consumers");

			for (Element wsrpConsumerElement :
					wsrpConsumersElement.elements("wsrp-consumer")) {

				String wsrpConsumerPath = wsrpConsumerElement.attributeValue("path");

				if (!context.isPathNotProcessed(wsrpConsumerPath)) {
					continue;
				}

				WSRPConsumer wsrpConsumer =
					(WSRPConsumer)context.getZipEntryAsObject(wsrpConsumerPath);

				WSRPConsumer returnedWSRPConsumer = importWSRPConsumer(
					context, wsrpConsumer);

				if (context.getBooleanParameter(
						_NAMESPACE, "wsrp-consumer-portlets")) {

					Element wsrpConsumerPortletsElement =
						wsrpConsumerElement.element("wsrp-consumer-portlets");

					for (Element wsrpConsumerPortletElement :
						wsrpConsumerPortletsElement.elements(
							"wsrp-consumer-portlet")) {

						String wsrpConsumerPortletPath =
							wsrpConsumerPortletElement.attributeValue("path");

						if (!context.isPathNotProcessed(
								wsrpConsumerPortletPath)) {
							continue;
						}

						WSRPConsumerPortlet wsrpConsumerPortlet =
							(WSRPConsumerPortlet)context.getZipEntryAsObject(
								wsrpConsumerPortletPath);

						importWSRPConsumerPortlet(
							returnedWSRPConsumer, wsrpConsumerPortlet);
					}
				}
			}
		}

		return null;
	}

	protected void exportWSRPConsumer(
			PortletDataContext context, Element consumersElement,
			WSRPConsumer wsrpConsumer)
		throws SystemException {

		String path = getWSRPConsumerPath(context, wsrpConsumer);

		if (!context.isPathNotProcessed(path)) {
			return;
		}

		Element consumerElement = consumersElement.addElement("wsrp-consumer");

		consumerElement.addAttribute("path", path);

		context.addZipEntry(path, wsrpConsumer);

		if (context.getBooleanParameter(_NAMESPACE, "wsrp-consumer-portlets")) {
			List<WSRPConsumerPortlet> consumerPortlets =
				WSRPConsumerPortletLocalServiceUtil.getWSRPConsumerPortlets(
					wsrpConsumer.getWsrpConsumerId(),
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			Element consumerPortletsElement = consumerElement.addElement(
				"wsrp-consumer-portlets");

			for (WSRPConsumerPortlet consumerPortlet : consumerPortlets) {
				exportWSRPConsumerPortlet(
					context, consumerPortletsElement, consumerPortlet);
			}
		}

	}

	private void exportWSRPConsumerPortlet(
			PortletDataContext context, Element consumerPortletsElement,
			WSRPConsumerPortlet wsrpConsumerPortlet)
		throws SystemException {

		String path = getWSRPConsumerPortletsPath(context, wsrpConsumerPortlet);

		if (!context.isPathNotProcessed(path)) {
			return;
		}

		Element consumerPortletElement = consumerPortletsElement.addElement(
			"wsrp-consumer-portlet");

		consumerPortletElement.addAttribute("path", path);

		context.addZipEntry(path, wsrpConsumerPortlet);
	}

	protected void exportWSRPProducer(
			PortletDataContext context, Element producersElement,
			WSRPProducer producer)
		throws SystemException {

		String path = getWSRPProducerPath(context, producer);

		if (!context.isPathNotProcessed(path)) {
			return;
		}

		Element producerElement = producersElement.addElement("wsrp-producer");

		producerElement.addAttribute("path", path);

		context.addZipEntry(path, producer);
	}

	protected String getWSRPConsumerPath(
		PortletDataContext context, WSRPConsumer wsrpConsumer) {

		StringBundler sb = new StringBundler(4);

		sb.append(context.getPortletPath(_PORTLET_KEY));
		sb.append("/wsrpConsumers/");
		sb.append(wsrpConsumer.getUuid());
		sb.append(".xml");

		return sb.toString();
	}

	protected String getWSRPConsumerPortletsPath(
		PortletDataContext context, WSRPConsumerPortlet wsrpConsumerPortlet) {

		StringBundler sb = new StringBundler(4);

		sb.append(context.getPortletPath(_PORTLET_KEY));
		sb.append("/wsrpConsumerPortlets/");
		sb.append(wsrpConsumerPortlet.getWsrpConsumerPortletId());
		sb.append(".xml");

		return sb.toString();
	}

	protected String getWSRPProducerPath(
		PortletDataContext context, WSRPProducer wsrpProducer) {

		StringBundler sb = new StringBundler(4);

		sb.append(context.getPortletPath(_PORTLET_KEY));
		sb.append("/wsrpProducers/");
		sb.append(wsrpProducer.getWsrpProducerId());
		sb.append(".xml");

		return sb.toString();
	}

	protected WSRPConsumer importWSRPConsumer(
			PortletDataContext context, WSRPConsumer wsrpConsumer)
		throws SystemException, PortalException {

		long companyId = context.getCompanyId();
		String name = wsrpConsumer.getName();
		String url = wsrpConsumer.getUrl();

		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setUuid(wsrpConsumer.getUuid());

		WSRPConsumer createdWSRPConsumer =
			WSRPConsumerLocalServiceUtil.addWSRPConsumer(
				companyId, null, name, url, null, serviceContext);

		return createdWSRPConsumer;
	}

	protected void importWSRPConsumerPortlet(
			WSRPConsumer wsrpConsumer, WSRPConsumerPortlet wsrpConsumerPortlet)
		throws SystemException, PortalException {

		String wsrpConsumerUUID = wsrpConsumer.getUuid();
		String name = wsrpConsumerPortlet.getName();
		String portletHandle = wsrpConsumerPortlet.getPortletHandle();

		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setUuid(wsrpConsumerPortlet.getUuid());

		WSRPConsumerPortletLocalServiceUtil.addWSRPConsumerPortlet(
			wsrpConsumerUUID, name, portletHandle, null, serviceContext);
	}

	protected void importWSRPProducer(
			PortletDataContext context, WSRPProducer wsrpProducer)
		throws SystemException, PortalException {

		long userId = context.getUserId(null);
		String name = wsrpProducer.getName();
		String version = wsrpProducer.getVersion();
		String portletIds = wsrpProducer.getPortletIds();

		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setUuid(wsrpProducer.getUuid());

		WSRPProducerLocalServiceUtil.addWSRPProducer(
			userId, name, version, portletIds, serviceContext);
	}

	private static final String _NAMESPACE = "wsrp";

	private static final String _PORTLET_KEY = "1_WAR_wsrpportlet";

	private static final boolean _PUBLISH_TO_LIVE_BY_DEFAULT = true;

	private static Log _log = LogFactoryUtil.getLog(
		AdminPortletDataHandlerImpl.class);

	private static PortletDataHandlerBoolean _producers =
		new PortletDataHandlerBoolean(_NAMESPACE, "wsrp-producers");

	private static PortletDataHandlerBoolean _consumerPortlets =
		new PortletDataHandlerBoolean(_NAMESPACE, "wsrp-consumer-portlets");

	private static PortletDataHandlerBoolean _consumers =
		new PortletDataHandlerBoolean(
			_NAMESPACE, "wsrp-consumers", true,
			new PortletDataHandlerControl[] {_consumerPortlets});
}