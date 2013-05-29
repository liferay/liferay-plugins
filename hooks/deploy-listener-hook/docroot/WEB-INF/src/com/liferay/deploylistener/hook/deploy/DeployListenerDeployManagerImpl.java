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

package com.liferay.deploylistener.hook.deploy;

import com.liferay.deploylistener.util.DeployListenerConstants;
import com.liferay.deploylistener.util.PortletPropsValues;
import com.liferay.portal.kernel.deploy.DeployManager;
import com.liferay.portal.kernel.deploy.auto.context.AutoDeploymentContext;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusException;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.plugin.PluginPackage;

import java.util.List;
import java.util.Properties;

/**
 * @author Brian Wing Shun Chan
 */
public class DeployListenerDeployManagerImpl
	implements DeployListenerConstants, DeployManager {

	public DeployListenerDeployManagerImpl(DeployManager deployManager) {
		_deployManager = deployManager;
	}

	@Override
	public void deploy(AutoDeploymentContext autoDeploymentContext)
		throws Exception {

		if (!sendMesssage(
				autoDeploymentContext.getContext(), COMMAND_ON_BEFORE_DEPLOY)) {

			return;
		}

		_deployManager.deploy(autoDeploymentContext);

		sendMesssage(
			autoDeploymentContext.getContext(), COMMAND_ON_AFTER_DEPLOY);
	}

	@Override
	public String getDeployDir() throws Exception {
		return _deployManager.getDeployDir();
	}

	@Override
	public String getInstalledDir() throws Exception {
		return _deployManager.getInstalledDir();
	}

	@Override
	public PluginPackage getInstalledPluginPackage(String context) {
		return _deployManager.getInstalledPluginPackage(context);
	}

	@Override
	public List<PluginPackage> getInstalledPluginPackages() {
		return _deployManager.getInstalledPluginPackages();
	}

	public DeployManager getWrappedDeployManager() {
		return _deployManager;
	}

	@Override
	public boolean isDeployed(String context) {
		return _deployManager.isDeployed(context);
	}

	@Override
	public PluginPackage readPluginPackageProperties(
		String displayName, Properties properties) {

		return _deployManager.readPluginPackageProperties(
			displayName, properties);
	}

	@Override
	public PluginPackage readPluginPackageXml(String xml) throws Exception {
		return _deployManager.readPluginPackageXml(xml);
	}

	@Override
	public void redeploy(String context) throws Exception {
		if (!sendMesssage(context, COMMAND_ON_BEFORE_REDEPLOY)) {
			return;
		}

		_deployManager.redeploy(context);

		sendMesssage(context, COMMAND_ON_AFTER_REDEPLOY);
	}

	@Override
	public void undeploy(String context) throws Exception {
		if (!isAllowUndeploy(context)) {
			return;
		}

		if (!sendMesssage(context, COMMAND_ON_BEFORE_UNDEPLOY)) {
			return;
		}

		_deployManager.undeploy(context);

		sendMesssage(context, COMMAND_ON_AFTER_UNDEPLOY);
	}

	protected boolean isAllowUndeploy(String context) {
		return !context.equals("deploy-listener-hook");
	}

	protected boolean sendMesssage(String context, String command) {
		String destinationName = "liferay/deploy_listener/" + context;

		if (!MessageBusUtil.hasMessageListener(destinationName)) {
			return true;
		}

		for (int i = 0; i < PortletPropsValues.MESSAGE_MAX_ATTEMPTS; i++) {
			Message message = new Message();

			message.put(MESSAGE_KEY_COMMAND, command);
			message.put(MESSAGE_KEY_CONTEXT, context);

			try {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Send message " + i + " for context " + context +
							" with command " + command);
				}

				Object response = MessageBusUtil.sendSynchronousMessage(
					destinationName, message,
					PortletPropsValues.MESSAGE_TIMEOUT);

				if (_log.isDebugEnabled()) {
					_log.debug(
						"Message " + i + " for context " + context +
							" with command " + command + " received response " +
								response);
				}

				if (response.equals(MESSAGE_RESPONSE_SKIP)) {
					return false;
				}
				else if (response.equals(MESSAGE_RESPONSE_SUCCESS)) {
					return true;
				}
				else if (response.equals(MESSAGE_RESPONSE_WAIT)) {
					continue;
				}
				else {
					return true;
				}
			}
			catch (MessageBusException mbe) {
				if (_log.isWarnEnabled()) {
					_log.warn(mbe.getMessage());
				}
			}

			try {
				Thread.sleep(PortletPropsValues.MESSAGE_TIMEOUT);
			}
			catch (InterruptedException ie) {
				_log.error(ie, ie);

				return true;
			}
		}

		return true;
	}

	private static Log _log = LogFactoryUtil.getLog(
		DeployListenerDeployManagerImpl.class);

	private DeployManager _deployManager;

}