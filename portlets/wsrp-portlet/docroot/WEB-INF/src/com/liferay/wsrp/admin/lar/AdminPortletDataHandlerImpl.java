/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
import com.liferay.wsrp.NoSuchConsumerException;
import com.liferay.wsrp.NoSuchConsumerPortletException;
import com.liferay.wsrp.NoSuchProducerException;
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
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		long companyId = portletDataContext.getCompanyId();

		if (portletDataContext.getBooleanParameter(
				_NAMESPACE, "wsrp-producers")) {

			List<WSRPProducer> wsrpProducers =
				WSRPProducerLocalServiceUtil.getWSRPProducers(
					companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (WSRPProducer wsrpProducer : wsrpProducers) {
				WSRPProducerLocalServiceUtil.deleteWSRPProducer(wsrpProducer);
			}
		}

		if (portletDataContext.getBooleanParameter(
				_NAMESPACE, "wsrp-consumers")) {

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
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		Document document = SAXReaderUtil.createDocument();

		Element rootElement = document.addElement("wsrp-data");

		if (portletDataContext.getBooleanParameter(
				_NAMESPACE, "wsrp-producers")) {

			Element wsrpProducersElement = rootElement.addElement(
				"wsrp-producers");

			List<WSRPProducer> wsrpProducers =
				WSRPProducerLocalServiceUtil.getWSRPProducers(
					portletDataContext.getCompanyId(), QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

			for (WSRPProducer wsrpProducer : wsrpProducers) {
				exportWSRPProducer(
					portletDataContext, wsrpProducersElement, wsrpProducer);
			}
		}

		if (portletDataContext.getBooleanParameter(
				_NAMESPACE, "wsrp-consumers")) {

			Element wsrpConsumersElement = rootElement.addElement(
				"wsrp-consumers");

			List<WSRPConsumer> wsrpConsumers =
				WSRPConsumerLocalServiceUtil.getWSRPConsumers(
					portletDataContext.getCompanyId(), QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

			for (WSRPConsumer wsrpConsumer : wsrpConsumers) {
				exportWSRPConsumer(
					portletDataContext, wsrpConsumersElement, wsrpConsumer);
			}
		}

		return document.formattedString();
	}

	protected PortletPreferences doImportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences, String data)
		throws Exception {

		Document document = SAXReaderUtil.read(data);

		Element rootElement = document.getRootElement();

		if (portletDataContext.getBooleanParameter(
				_NAMESPACE, "wsrp-producers")) {

			Element wsrpProducersElement = rootElement.element(
				"wsrp-producers");

			for (Element wsrpProducerElement :
					wsrpProducersElement.elements("wsrp-producer")) {

				String wsrpProducerPath = wsrpProducerElement.attributeValue(
					"path");

				if (!portletDataContext.isPathNotProcessed(wsrpProducerPath)) {
					continue;
				}

				WSRPProducer wsrpProducer =
					(WSRPProducer)portletDataContext.getZipEntryAsObject(
						wsrpProducerPath);

				importWSRPProducer(portletDataContext, wsrpProducer);
			}
		}

		if (portletDataContext.getBooleanParameter(
				_NAMESPACE, "wsrp-consumers")) {

			Element wsrpConsumersElement = rootElement.element(
				"wsrp-consumers");

			for (Element wsrpConsumerElement :
					wsrpConsumersElement.elements("wsrp-consumer")) {

				String wsrpConsumerPath = wsrpConsumerElement.attributeValue(
					"path");

				if (!portletDataContext.isPathNotProcessed(wsrpConsumerPath)) {
					continue;
				}

				WSRPConsumer wsrpConsumer =
					(WSRPConsumer)portletDataContext.getZipEntryAsObject(
						wsrpConsumerPath);

				WSRPConsumer returnedWSRPConsumer = importWSRPConsumer(
					portletDataContext, wsrpConsumer);

				if (portletDataContext.getBooleanParameter(
						_NAMESPACE, "wsrp-consumer-portlets")) {

					Element wsrpConsumerPortletsElement =
						wsrpConsumerElement.element("wsrp-consumer-portlets");

					for (Element wsrpConsumerPortletElement :
							wsrpConsumerPortletsElement.elements(
								"wsrp-consumer-portlet")) {

						String wsrpConsumerPortletPath =
							wsrpConsumerPortletElement.attributeValue("path");

						if (!portletDataContext.isPathNotProcessed(
								wsrpConsumerPortletPath)) {

							continue;
						}

						WSRPConsumerPortlet wsrpConsumerPortlet =
							(WSRPConsumerPortlet)portletDataContext.
								getZipEntryAsObject(wsrpConsumerPortletPath);

						importWSRPConsumerPortlet(
							returnedWSRPConsumer, wsrpConsumerPortlet);
					}
				}
			}
		}

		return null;
	}

	protected void exportWSRPConsumer(
			PortletDataContext portletDataContext, Element wsrpConsumersElement,
			WSRPConsumer wsrpConsumer)
		throws SystemException {

		String path = getWSRPConsumerPath(portletDataContext, wsrpConsumer);

		if (!portletDataContext.isPathNotProcessed(path)) {
			return;
		}

		Element wsrpConsumerElement = wsrpConsumersElement.addElement(
			"wsrp-consumer");

		wsrpConsumerElement.addAttribute("path", path);

		portletDataContext.addZipEntry(path, wsrpConsumer);

		if (portletDataContext.getBooleanParameter(
				_NAMESPACE, "wsrp-consumer-portlets")) {

			List<WSRPConsumerPortlet> wsrpConsumerPortlets =
				WSRPConsumerPortletLocalServiceUtil.getWSRPConsumerPortlets(
					wsrpConsumer.getWsrpConsumerId(), QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

			Element wsrpConsumerPortletsElement =
				wsrpConsumerElement.addElement("wsrp-consumer-portlets");

			for (WSRPConsumerPortlet wsrpConsumerPortlet :
					wsrpConsumerPortlets) {

				exportWSRPConsumerPortlet(
					portletDataContext, wsrpConsumerPortletsElement,
					wsrpConsumerPortlet);
			}
		}
	}

	protected void exportWSRPConsumerPortlet(
			PortletDataContext portletDataContext,
			Element wsrpConsumerPortletsElement,
			WSRPConsumerPortlet wsrpConsumerPortlet)
		throws SystemException {

		String path = getWSRPConsumerPortletsPath(
			portletDataContext, wsrpConsumerPortlet);

		if (!portletDataContext.isPathNotProcessed(path)) {
			return;
		}

		Element wsrpConsumerPortletElement =
			wsrpConsumerPortletsElement.addElement("wsrp-consumer-portlet");

		wsrpConsumerPortletElement.addAttribute("path", path);

		portletDataContext.addZipEntry(path, wsrpConsumerPortlet);
	}

	protected void exportWSRPProducer(
			PortletDataContext portletDataContext, Element wsrpProducersElement,
			WSRPProducer wsrpProducer)
		throws SystemException {

		String path = getWSRPProducerPath(portletDataContext, wsrpProducer);

		if (!portletDataContext.isPathNotProcessed(path)) {
			return;
		}

		Element wsrpProducerElement = wsrpProducersElement.addElement(
			"wsrp-producer");

		wsrpProducerElement.addAttribute("path", path);

		portletDataContext.addZipEntry(path, wsrpProducer);
	}

	protected String getWSRPConsumerPath(
		PortletDataContext portletDataContext, WSRPConsumer wsrpConsumer) {

		StringBundler sb = new StringBundler(4);

		sb.append(portletDataContext.getPortletPath(_PORTLET_KEY));
		sb.append("/wsrp-consumers/");
		sb.append(wsrpConsumer.getUuid());
		sb.append(".xml");

		return sb.toString();
	}

	protected String getWSRPConsumerPortletsPath(
		PortletDataContext portletDataContext,
		WSRPConsumerPortlet wsrpConsumerPortlet) {

		StringBundler sb = new StringBundler(4);

		sb.append(portletDataContext.getPortletPath(_PORTLET_KEY));
		sb.append("/wsrp-consumer-portlets/");
		sb.append(wsrpConsumerPortlet.getWsrpConsumerPortletId());
		sb.append(".xml");

		return sb.toString();
	}

	protected String getWSRPProducerPath(
		PortletDataContext portletDataContext, WSRPProducer wsrpProducer) {

		StringBundler sb = new StringBundler(4);

		sb.append(portletDataContext.getPortletPath(_PORTLET_KEY));
		sb.append("/wsrp-producers/");
		sb.append(wsrpProducer.getWsrpProducerId());
		sb.append(".xml");

		return sb.toString();
	}

	protected WSRPConsumer importWSRPConsumer(
			PortletDataContext portletDataContext, WSRPConsumer wsrpConsumer)
		throws PortalException, SystemException {

		WSRPConsumer importedWSRPConsumer = null;

		try {
			importedWSRPConsumer = WSRPConsumerLocalServiceUtil.getWSRPConsumer(
				wsrpConsumer.getUuid());

			importedWSRPConsumer.setName(wsrpConsumer.getName());
			importedWSRPConsumer.setUrl(wsrpConsumer.getUrl());
			importedWSRPConsumer.setWsdl(wsrpConsumer.getWsdl());
			importedWSRPConsumer.setForwardCookies(
				wsrpConsumer.getForwardCookies());

			WSRPConsumerLocalServiceUtil.updateWSRPConsumer(
				importedWSRPConsumer, false);
		}
		catch (NoSuchConsumerException nsce) {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setUuid(wsrpConsumer.getUuid());

			importedWSRPConsumer = WSRPConsumerLocalServiceUtil.addWSRPConsumer(
				portletDataContext.getCompanyId(), null, wsrpConsumer.getName(),
				wsrpConsumer.getUrl(), wsrpConsumer.getForwardCookies(), null,
				serviceContext);
		}

		return importedWSRPConsumer;
	}

	protected void importWSRPConsumerPortlet(
			WSRPConsumer wsrpConsumer,
			WSRPConsumerPortlet wsrpConsumerPortlet)
		throws PortalException, SystemException {

		try {
			WSRPConsumerPortlet importedWSRPConsumerPortlet =
				WSRPConsumerPortletLocalServiceUtil.getWSRPConsumerPortlet(
					wsrpConsumerPortlet.getUuid());

			importedWSRPConsumerPortlet.setWsrpConsumerId(
				wsrpConsumer.getWsrpConsumerId());
			importedWSRPConsumerPortlet.setName(wsrpConsumerPortlet.getName());
			importedWSRPConsumerPortlet.setPortletHandle(
				wsrpConsumerPortlet.getPortletHandle());

			WSRPConsumerPortletLocalServiceUtil.updateWSRPConsumerPortlet(
				importedWSRPConsumerPortlet, false);
		}
		catch (NoSuchConsumerPortletException nscpe) {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setUuid(wsrpConsumerPortlet.getUuid());

			WSRPConsumerPortletLocalServiceUtil.addWSRPConsumerPortlet(
				wsrpConsumer.getUuid(), wsrpConsumerPortlet.getName(),
				wsrpConsumerPortlet.getPortletHandle(), null, serviceContext);
		}
	}

	protected void importWSRPProducer(
			PortletDataContext portletDataContext, WSRPProducer wsrpProducer)
		throws PortalException, SystemException {

		try {
			WSRPProducer importedWSRPProducer =
				WSRPProducerLocalServiceUtil.getWSRPProducer(
					wsrpProducer.getUuid());

			importedWSRPProducer.setName(wsrpProducer.getName());
			importedWSRPProducer.setVersion(wsrpProducer.getVersion());
			importedWSRPProducer.setPortletIds(wsrpProducer.getPortletIds());

			WSRPProducerLocalServiceUtil.updateWSRPProducer(
				importedWSRPProducer, false);
		}
		catch (NoSuchProducerException e) {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setUuid(wsrpProducer.getUuid());

			WSRPProducerLocalServiceUtil.addWSRPProducer(
				portletDataContext.getUserId(null), wsrpProducer.getName(),
				wsrpProducer.getVersion(), wsrpProducer.getPortletIds(),
				serviceContext);
		}
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