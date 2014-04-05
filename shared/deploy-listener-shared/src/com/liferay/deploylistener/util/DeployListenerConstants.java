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

package com.liferay.deploylistener.util;

/**
 * @author Brian Wing Shun Chan
 */
public interface DeployListenerConstants {

	public static final String COMMAND_ON_AFTER_DEPLOY = "onAfterDeploy";

	public static final String COMMAND_ON_AFTER_REDEPLOY = "onAfterRedeploy";

	public static final String COMMAND_ON_AFTER_UNDEPLOY = "onAfterUndeploy";

	public static final String COMMAND_ON_BEFORE_DEPLOY = "onBeforeDeploy";

	public static final String COMMAND_ON_BEFORE_REDEPLOY = "onBeforeRedeploy";

	public static final String COMMAND_ON_BEFORE_UNDEPLOY = "onBeforeUndeploy";

	public static final String MESSAGE_KEY_COMMAND = "command";

	public static final String MESSAGE_KEY_CONTEXT = "context";

	public static final String MESSAGE_RESPONSE_ERROR = "error";

	public static final String MESSAGE_RESPONSE_SKIP = "skip";

	public static final String MESSAGE_RESPONSE_SUCCESS = "success";

	public static final String MESSAGE_RESPONSE_WAIT = "wait";

}