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

package com.liferay.wsrp.lar;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.BasePortletDataHandler;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.PortletDataHandlerBoolean;
import com.liferay.portal.kernel.lar.PortletDataHandlerControl;
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

	public PortletDataHandlerControl[] getExportControls() {
		return new PortletDataHandlerControl[] {_wsrpProducers, _wsrpConsumers};
	}

	public PortletDataHandlerControl[] getImportControls() {
		return new PortletDataHandlerControl[] {_wsrpProducers, _wsrpConsumers};
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
			Element wsrpProducersElement = rootElement.addElement(
				"wsrp-producers");

			List<WSRPProducer> wsrpProducers =
				WSRPProducerLocalServiceUtil.getWSRPProducers(
					context.getCompanyId(), QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

			for (WSRPProducer wsrpProducer : wsrpProducers) {
				exportWSRPProducer(context, wsrpProducersElement, wsrpProducer);
			}
		}

		if (context.getBooleanParameter(_NAMESPACE, "wsrp-consumers")) {
			Element wsrpConsumersElement = rootElement.addElement(
				"wsrp-consumers");

			List<WSRPConsumer> wsrpConsumers =
				WSRPConsumerLocalServiceUtil.getWSRPConsumers(
					context.getCompanyId(), QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

			for (WSRPConsumer wsrpConsumer : wsrpConsumers) {
				exportWSRPConsumer(context, wsrpConsumersElement, wsrpConsumer);
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

				String wsrpConsumerPath = wsrpConsumerElement.attributeValue(
					"path");

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
			PortletDataContext context, Element wsrpConsumersElement,
			WSRPConsumer wsrpConsumer)
		throws SystemException {

		String path = getWSRPConsumerPath(context, wsrpConsumer);

		if (!context.isPathNotProcessed(path)) {
			return;
		}

		Element wsrpConsumerElement = wsrpConsumersElement.addElement(
			"wsrp-consumer");

		wsrpConsumerElement.addAttribute("path", path);

		context.addZipEntry(path, wsrpConsumer);

		if (context.getBooleanParameter(_NAMESPACE, "wsrp-consumer-portlets")) {
			List<WSRPConsumerPortlet> wsrpConsumerPortlets =
				WSRPConsumerPortletLocalServiceUtil.getWSRPConsumerPortlets(
					wsrpConsumer.getWsrpConsumerId(), QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

			Element wsrpConsumerPortletsElement =
				wsrpConsumerElement.addElement("wsrp-consumer-portlets");

			for (WSRPConsumerPortlet wsrpConsumerPortlet :
					wsrpConsumerPortlets) {

				exportWSRPConsumerPortlet(
					context, wsrpConsumerPortletsElement, wsrpConsumerPortlet);
			}
		}
	}

	protected void exportWSRPConsumerPortlet(
			PortletDataContext context, Element wsrpConsumerPortletsElement,
			WSRPConsumerPortlet wsrpConsumerPortlet)
		throws SystemException {

		String path = getWSRPConsumerPortletsPath(context, wsrpConsumerPortlet);

		if (!context.isPathNotProcessed(path)) {
			return;
		}

		Element wsrpConsumerPortletElement =
			wsrpConsumerPortletsElement.addElement("wsrp-consumer-portlet");

		wsrpConsumerPortletElement.addAttribute("path", path);

		context.addZipEntry(path, wsrpConsumerPortlet);
	}

	protected void exportWSRPProducer(
			PortletDataContext context, Element wsrpProducersElement,
			WSRPProducer wsrpProducer)
		throws SystemException {

		String path = getWSRPProducerPath(context, wsrpProducer);

		if (!context.isPathNotProcessed(path)) {
			return;
		}

		Element wsrpProducerElement = wsrpProducersElement.addElement(
			"wsrp-producer");

		wsrpProducerElement.addAttribute("path", path);

		context.addZipEntry(path, wsrpProducer);
	}

	protected String getWSRPConsumerPath(
		PortletDataContext context, WSRPConsumer wsrpConsumer) {

		StringBundler sb = new StringBundler(4);

		sb.append(context.getPortletPath(_PORTLET_KEY));
		sb.append("/wsrp-consumers/");
		sb.append(wsrpConsumer.getUuid());
		sb.append(".xml");

		return sb.toString();
	}

	protected String getWSRPConsumerPortletsPath(
		PortletDataContext context, WSRPConsumerPortlet wsrpConsumerPortlet) {

		StringBundler sb = new StringBundler(4);

		sb.append(context.getPortletPath(_PORTLET_KEY));
		sb.append("/wsrp-consumer-portlets/");
		sb.append(wsrpConsumerPortlet.getWsrpConsumerPortletId());
		sb.append(".xml");

		return sb.toString();
	}

	protected String getWSRPProducerPath(
		PortletDataContext context, WSRPProducer wsrpProducer) {

		StringBundler sb = new StringBundler(4);

		sb.append(context.getPortletPath(_PORTLET_KEY));
		sb.append("/wsrp-producers/");
		sb.append(wsrpProducer.getWsrpProducerId());
		sb.append(".xml");

		return sb.toString();
	}

	protected WSRPConsumer importWSRPConsumer(
			PortletDataContext context, WSRPConsumer wsrpConsumer)
		throws PortalException, SystemException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setUuid(wsrpConsumer.getUuid());

		return WSRPConsumerLocalServiceUtil.addWSRPConsumer(
			context.getCompanyId(), null, wsrpConsumer.getName(),
			wsrpConsumer.getUrl(), null, serviceContext);
	}

	protected void importWSRPConsumerPortlet(
			WSRPConsumer wsrpConsumer, WSRPConsumerPortlet wsrpConsumerPortlet)
		throws PortalException, SystemException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setUuid(wsrpConsumerPortlet.getUuid());

		WSRPConsumerPortletLocalServiceUtil.addWSRPConsumerPortlet(
			wsrpConsumer.getUuid(), wsrpConsumerPortlet.getName(),
			wsrpConsumerPortlet.getPortletHandle(), null, serviceContext);
	}

	protected void importWSRPProducer(
			PortletDataContext context, WSRPProducer wsrpProducer)
		throws PortalException, SystemException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setUuid(wsrpProducer.getUuid());

		WSRPProducerLocalServiceUtil.addWSRPProducer(
			context.getUserId(null), wsrpProducer.getName(),
			wsrpProducer.getVersion(), wsrpProducer.getPortletIds(),
			serviceContext);
	}

	private static final String _NAMESPACE = "wsrp";

	private static final String _PORTLET_KEY = "1_WAR_wsrpportlet";

	private static final boolean _PUBLISH_TO_LIVE_BY_DEFAULT = true;

	private static PortletDataHandlerBoolean _wsrpConsumerPortlets =
		new PortletDataHandlerBoolean(_NAMESPACE, "wsrp-consumer-portlets");

	private static PortletDataHandlerBoolean _wsrpProducers =
		new PortletDataHandlerBoolean(_NAMESPACE, "wsrp-producers");

	private static PortletDataHandlerBoolean _wsrpConsumers =
		new PortletDataHandlerBoolean(
			_NAMESPACE, "wsrp-consumers", true,
			new PortletDataHandlerControl[] {_wsrpConsumerPortlets});

}