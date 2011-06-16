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

package com.liferay.wsrp.servlet;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.PortletServlet;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletApp;
import com.liferay.wsrp.consumer.portlet.ConsumerPortlet;
import com.liferay.wsrp.model.WSRPConsumer;
import com.liferay.wsrp.model.WSRPConsumerPortlet;
import com.liferay.wsrp.model.WSRPProducer;
import com.liferay.wsrp.service.WSRPConsumerLocalServiceUtil;
import com.liferay.wsrp.service.WSRPConsumerPortletLocalServiceUtil;
import com.liferay.wsrp.service.WSRPProducerLocalServiceUtil;

import java.util.List;

import javax.servlet.ServletContext;

/**
 * @author Brian Wing Shun Chan
 */
public class PortalInitThread extends Thread {

	@Override
	public void run() {
		try {

			// Wait 4 seconds before initializing consumer portlets in case the
			// consumer and producer are the same machine

			Thread.sleep(4000);

			verifyUuid();

			PortletApp portletApp = (PortletApp)_servletContext.getAttribute(
				PortletServlet.PORTLET_APP);

			List<Portlet> portlets = portletApp.getPortlets();

			for (Portlet portlet : portlets) {
				String portletName = portlet.getPortletName();

				if (!portletName.startsWith(
						ConsumerPortlet.PORTLET_NAME_PREFIX)) {

					portlet.setReady(true);
				}
			}

			try {
				WSRPConsumerPortletLocalServiceUtil.initWSRPConsumerPortlets();
			}
			catch (Exception e) {
				_log.error("Unable to initialize WSRP consumer portlets", e);
			}

			portlets = portletApp.getPortlets();

			for (Portlet portlet : portlets) {
				String portletName = portlet.getPortletName();

				if (portletName.startsWith(
						ConsumerPortlet.PORTLET_NAME_PREFIX)) {

					portlet.setReady(true);
				}
			}
		}
		catch (InterruptedException ie) {
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	protected void verifyUuid() throws Exception {
		List<WSRPConsumer> wsrpConsumers =
			WSRPConsumerLocalServiceUtil.getWSRPConsumers(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (WSRPConsumer wsrpConsumer : wsrpConsumers) {
			if (Validator.isNull(wsrpConsumer.getUuid())) {
				WSRPConsumerLocalServiceUtil.updateWSRPConsumer(wsrpConsumer);
			}
		}

		List<WSRPConsumerPortlet> wsrpConsumerPortlets =
			WSRPConsumerPortletLocalServiceUtil.getWSRPConsumerPortlets(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (WSRPConsumerPortlet wsrpConsumerPortlet : wsrpConsumerPortlets) {
			if (Validator.isNull(wsrpConsumerPortlet.getUuid())) {
				WSRPConsumerPortletLocalServiceUtil.updateWSRPConsumerPortlet(
					wsrpConsumerPortlet);
			}
		}

		List<WSRPProducer> wsrpProducers =
			WSRPProducerLocalServiceUtil.getWSRPProducers(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (WSRPProducer wsrpProducer : wsrpProducers) {
			if (Validator.isNull(wsrpProducer.getUuid())) {
				WSRPProducerLocalServiceUtil.updateWSRPProducer(wsrpProducer);
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(PortalInitThread.class);

	private ServletContext _servletContext;

}