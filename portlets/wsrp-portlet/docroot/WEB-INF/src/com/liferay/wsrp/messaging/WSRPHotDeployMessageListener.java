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

package com.liferay.wsrp.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.HotDeployMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceReference;
import com.liferay.registry.ServiceTracker;
import com.liferay.registry.ServiceTrackerCustomizer;
import com.liferay.wsrp.jmx.WSRPConsumerPortletManager;
import com.liferay.wsrp.service.WSRPConsumerPortletLocalServiceUtil;
import com.liferay.wsrp.util.ExtensionHelperUtil;

import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 * @author Shuyang Zhou
 */
public class WSRPHotDeployMessageListener extends HotDeployMessageListener {

	public WSRPHotDeployMessageListener(String... servletContextNames) {
		super(servletContextNames);
	}

	public void afterPropertiesSet() {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(
			MBeanServer.class, new MBeanServerServiceTrackerCustomizer());

		_serviceTracker.open();
	}

	public void destroy() {
		_serviceTracker.close();
	}

	@Override
	protected void onDeploy(Message message) throws Exception {
		ExtensionHelperUtil.initialize();

		WSRPConsumerPortletLocalServiceUtil.destroyWSRPConsumerPortlets();

		WSRPConsumerPortletLocalServiceUtil.initWSRPConsumerPortlets();
	}

	@Override
	protected void onUndeploy(Message message) throws Exception {
		WSRPConsumerPortletLocalServiceUtil.destroyWSRPConsumerPortlets();
	}

	private static Log _log = LogFactoryUtil.getLog(
		WSRPHotDeployMessageListener.class);

	private ServiceTracker<MBeanServer, MBeanServer> _serviceTracker;

	private class MBeanServerServiceTrackerCustomizer
		implements ServiceTrackerCustomizer<MBeanServer, MBeanServer> {

		@Override
		public MBeanServer addingService(
			ServiceReference<MBeanServer> serviceReference) {

			Registry registry = RegistryUtil.getRegistry();

			MBeanServer mBeanServer = registry.getService(serviceReference);

			try {
				mBeanServer.registerMBean(
					new WSRPConsumerPortletManager(),
					new ObjectName(
						"com.liferay.wsrp:classification=wsrp," +
							"name=WSRPConsumerPortletManager"));
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to register WSRPConsumerPortletManager", e);
				}
			}

			return mBeanServer;
		}

		@Override
		public void modifiedService(
			ServiceReference<MBeanServer> serviceReference,
			MBeanServer mBeanServer) {
		}

		@Override
		public void removedService(
			ServiceReference<MBeanServer> serviceReference,
			MBeanServer mBeanServer) {
		}

	}

}