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

package com.liferay.deploylistener.servlet;

import com.liferay.deploylistener.hook.deploy.DeployListenerDeployManagerImpl;
import com.liferay.portal.kernel.deploy.DeployManager;
import com.liferay.portal.kernel.deploy.DeployManagerUtil;
import com.liferay.portal.kernel.util.BasePortalLifecycle;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Brian Wing Shun Chan
 */
public class DeployListenerServletContextListener
	extends BasePortalLifecycle implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		portalDestroy();
	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		registerPortalLifecycle();
	}

	@Override
	protected void doPortalDestroy() throws Exception {
		DeployManager deployManager = DeployManagerUtil.getDeployManager();

		if (deployManager instanceof DeployListenerDeployManagerImpl) {
			DeployManagerUtil deployManagerUtil = new DeployManagerUtil();

			DeployListenerDeployManagerImpl deployListenerDeployManagerImpl =
				(DeployListenerDeployManagerImpl)deployManager;

			deployManagerUtil.setDeployManager(
				deployListenerDeployManagerImpl.getWrappedDeployManager());
		}
	}

	@Override
	protected void doPortalInit() {
		DeployManagerUtil deployManagerUtil = new DeployManagerUtil();

		DeployListenerDeployManagerImpl deployListenerDeployManagerImpl =
			new DeployListenerDeployManagerImpl(
				DeployManagerUtil.getDeployManager());

		deployManagerUtil.setDeployManager(deployListenerDeployManagerImpl);
	}

}