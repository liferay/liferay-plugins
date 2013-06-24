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

package com.liferay.deploylistener.messaging;

import com.liferay.portal.kernel.messaging.Message;

/**
 * @author Brian Wing Shun Chan
 */
public interface DeployListenerMessageListener {

	public void onAfterDeploy(Message message) throws Exception;

	public void onAfterRedeploy(Message message) throws Exception;

	public void onAfterUndeploy(Message message) throws Exception;

	public void onBeforeDeploy(Message message) throws Exception;

	public void onBeforeRedeploy(Message message) throws Exception;

	public void onBeforeUndeploy(Message message) throws Exception;

	public void process(Message message, String command) throws Exception;

}