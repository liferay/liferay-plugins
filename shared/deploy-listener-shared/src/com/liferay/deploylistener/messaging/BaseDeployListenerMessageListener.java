/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import com.liferay.deploylistener.util.DeployListenerConstants;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Brian Wing Shun Chan
 */
public abstract class BaseDeployListenerMessageListener
	extends BaseMessageListener
	implements DeployListenerConstants, DeployListenerMessageListener {

	public void onAfterDeploy(Message message) throws Exception {
	}

	public void onAfterRedeploy(Message message) throws Exception {
	}

	public void onAfterUndeploy(Message message) throws Exception {
	}

	public void onBeforeDeploy(Message message) throws Exception {
	}

	public void onBeforeRedeploy(Message message) throws Exception {
	}

	public void onBeforeUndeploy(Message message) throws Exception {
	}

	public void process(Message message, String command) throws Exception {
		if (processThread == null) {
			processThread = new ProcessThread();

			processThread.setCommand(command);
			processThread.setMessage(message);

			processThread.run();
		}

		if (Validator.isNotNull(processMessageResponse)) {
			message.setPayload(processMessageResponse);

			if (!processMessageResponse.equals(MESSAGE_RESPONSE_WAIT)) {
				processMessageResponse = null;

				processThread.join();

				processThread = null;
			}
		}
		else {
			message.setPayload(MESSAGE_RESPONSE_WAIT);
		}

		MessageBusUtil.sendMessage(
			message.getResponseDestinationName(), message);
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		String command = message.getString(MESSAGE_KEY_COMMAND);

		if (command.equals(COMMAND_ON_AFTER_DEPLOY) ||
			command.equals(COMMAND_ON_AFTER_REDEPLOY) ||
			command.equals(COMMAND_ON_AFTER_UNDEPLOY) ||
			command.equals(COMMAND_ON_BEFORE_DEPLOY) ||
			command.equals(COMMAND_ON_BEFORE_REDEPLOY) ||
			command.equals(COMMAND_ON_BEFORE_UNDEPLOY)) {

			process(message, command);
		}
	}

	protected String processMessageResponse;
	protected ProcessThread processThread;

	protected class ProcessThread extends Thread {

		@Override
		public void run() {
			try {
				if (_command.equals(COMMAND_ON_AFTER_DEPLOY)) {
					onAfterDeploy(_message);
				}
				else if (_command.equals(COMMAND_ON_AFTER_REDEPLOY)) {
					onAfterRedeploy(_message);
				}
				else if (_command.equals(COMMAND_ON_AFTER_UNDEPLOY)) {
					onAfterUndeploy(_message);
				}
				else if (_command.equals(COMMAND_ON_BEFORE_DEPLOY)) {
					onBeforeDeploy(_message);
				}
				else if (_command.equals(COMMAND_ON_BEFORE_REDEPLOY)) {
					onBeforeRedeploy(_message);
				}
				else if (_command.equals(COMMAND_ON_BEFORE_UNDEPLOY)) {
					onBeforeUndeploy(_message);
				}

				if (Validator.isNull(processMessageResponse)) {
					processMessageResponse = MESSAGE_RESPONSE_SUCCESS;
				}
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		protected void setCommand(String command) {
			_command = command;
		}

		protected void setMessage(Message message) {
			_message = message;
		}

		private String _command;
		private Message _message;

	}

	private static Log _log = LogFactoryUtil.getLog(
		BaseDeployListenerMessageListener.class);

}