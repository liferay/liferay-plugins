/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

/**
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://www.sun.com/cddl/cddl.html and
 * legal/CDDLv1.0.txt. See the License for the specific language governing
 * permission and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at legal/CDDLv1.0.txt.
 *
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Copyright 2009 Sun Microsystems Inc. All rights reserved.
 */

package com.liferay.saw.messaging;

import com.liferay.portal.kernel.jbi.WorkflowComponentException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.saw.service.SAWWorkflowLocalServiceUtil;

import java.util.Map;
import java.util.Set;

/**
 * <a href="SAWMessageListener.java.html"><b><i>View Source</i></b></a>
 *
 * @author Ganesh Ram
 *
 */

public class SAWMessageListener implements MessageListener{

	public void receive(Message message) {

		try {
			doReceive((Message)message);
		}
		catch (Exception e) {
			_log.error("Unable to process message " + message, e);
		}
	}

	protected void doReceive(Message message) throws Exception {

		String responseDestination = message.getResponseDestination();

		Map commandmap = (Map)message.getPayload();
		String cmd= "";
		Object[] parameters = null;
		Set<Map.Entry> entryset = commandmap.entrySet();

		for(Map.Entry entry: entryset){
			cmd = (String) entry.getKey();
			parameters = (Object[])entry.getValue();
			break;
		}
		if (cmd == null){
			throw new Exception("cmd cannot be null or empty.");
		}
		else if (cmd.equals("deploy")) {

			String defId = _deploy(parameters);
			message.setPayload(defId);
			_sendResponse(responseDestination,message);
		}
		else if (cmd.equals("getCurrentTasksXml")) {

			String currentTasks = _getCurrentTasksXml(parameters);
			message.setPayload(currentTasks);
			_sendResponse(responseDestination,message);

		}
		else if (cmd.equals("getDefinitionsCountXml")) {

			String countXml = _getDefinitionsCountXml(parameters);
			message.setPayload(countXml);
			_sendResponse(responseDestination,message);

		}
		else if (cmd.equals("getDefinitionsXml")) {
			String defnXml =_getDefinitionsXml(parameters);
			message.setPayload(defnXml);
			_sendResponse(responseDestination,message);

		}
		else if (cmd.equals("getDefinitionXml")) {

			String defId = _getDefinitionXml(parameters);
			message.setPayload(defId);
			_sendResponse(responseDestination,message);

		}
		else if (cmd.equals("getInstancesCountXml")) {

			String instancesXml = _getInstancesCountXml(parameters);
			message.setPayload(instancesXml);
			_sendResponse(responseDestination,message);

		}
		else if (cmd.equals("getInstancesXml")) {

			String instancesXml = _getInstancesXml(parameters);
			message.setPayload(instancesXml);
			_sendResponse(responseDestination,message);

		}
		else if (cmd.equals("getTaskXml")) {

			String taskXml = _getTaskXml(parameters);
			message.setPayload(taskXml);
			_sendResponse(responseDestination,message);

		}
		else if (cmd.equals("getTaskFormElementsXml")) {

			String taskFormElementsXml = _getTaskFormElementsXml(parameters);
			message.setPayload(taskFormElementsXml);
			_sendResponse(responseDestination,message);

		}
		else if (cmd.equals("getTaskTransitionsXml")) {

			String taskTransitionsXml = _getTaskTransitionsXml(parameters);
			message.setPayload(taskTransitionsXml);
			_sendResponse(responseDestination,message);

		}
		else if (cmd.equals("getUserTasksCountXml")) {

			String userTasksCountXml = _getUserTasksCountXml(parameters);
			message.setPayload(userTasksCountXml);
			_sendResponse(responseDestination,message);

		}
		else if (cmd.equals("getUserTasksXml")) {

			String userTasksXml = _getUserTasksXml(parameters);
			message.setPayload(userTasksXml);
			_sendResponse(responseDestination,message);

		}
		else if (cmd.equals("signalInstance")) {

			_signalInstance(parameters);

		}
		else if (cmd.equals("signalToken")) {

			_signalToken(parameters);

		}
		else if (cmd.equals("startWorkflow")) {

			String workflow = _startWorkflow(parameters);
			message.setPayload(workflow);
			_sendResponse(responseDestination,message);

		}
		else if (cmd.equals("updateTaskXml")) {

			String updateTaskXml = _updateTaskXml(parameters);
			message.setPayload(updateTaskXml);
			_sendResponse(responseDestination,message);
		}
		else if (cmd.equals("checkoutTasks")){
			//TODO Add SAW check out task functionality
		}
		else if (cmd.equals("saveTasks")){
			//TODO Add SAW saveTasks functionality
		}
		else if (cmd.equals("completeTasks")){
			//TODO Add SAW completeTasks functionality
		}
		else if (cmd.equals("getTasks")){
			//TODO Add SAW getTasks functionality
		}
		else if (cmd.equals("countTasks")){
			//TODO Add SAW countTasks functionality
		}
		else if (cmd.equals("escalateTasks")){
			//TODO Add SAW escalateTasks functionality
		}

	}

	private String _deploy(Object[] args) throws Exception{

		String xml = (String)args[0];
		return SAWWorkflowLocalServiceUtil.deploy(xml);

	}

	private String _getCurrentTasksXml(Object[] args)
		throws WorkflowComponentException{

		long instanceId = ((Long)args[0]).longValue();
		long tokenId = ((Long)args[1]).longValue();
		String userId = (String)args[2];
		return SAWWorkflowLocalServiceUtil.getCurrentTasksXml(instanceId,
				tokenId, userId);
	}

	private String _getDefinitionsCountXml(Object[] args)
		throws WorkflowComponentException{

		long definitionId = ((Long)args[0]).longValue();
		String name = (String)args[1];
		String userId = (String)args[2];
		return SAWWorkflowLocalServiceUtil.getDefinitionsCountXml(definitionId,
				name,userId);
	}

	private String _getDefinitionsXml(Object[] args)
		throws WorkflowComponentException{

		long definitionId = ((Long)args[0]).longValue();
		String name = (String)args[1];
		String userId = (String)args[2];
		int begin = ((Integer)args[3]).intValue();
		int end = ((Integer)args[4]).intValue();

		return SAWWorkflowLocalServiceUtil.getDefinitionsXml(
			definitionId, name,userId, begin, end);
	}

	private String _getDefinitionXml(Object[] args) throws Exception{

		long definitionId = ((Long)args[0]).longValue();
		return SAWWorkflowLocalServiceUtil.getDefinitionXml(definitionId);

	}

	private String _getInstancesCountXml(Object[] args)
		throws WorkflowComponentException{

		long definitionId =((Long)args[0]).longValue();
		long instanceId = ((Long)args[1]).longValue();
		String definitionName = (String)args[2];
		String definitionVersion = (String)args[3];
		String startDateGT = (String)args[4];
		String startDateLT = (String)args[5];
		String endDateGT = (String)args[6];
		String endDateLT = (String)args[7];
		String userId = (String)args[8];
		boolean hideEndedTasks = ((Boolean)args[9]).booleanValue();
		boolean retrieveUserInstances = ((Boolean)args[10]).booleanValue();
		boolean andOperator = ((Boolean)args[11]).booleanValue();

		return SAWWorkflowLocalServiceUtil.getInstancesCountXml(
				definitionId, instanceId, definitionName, definitionVersion,
				startDateGT, startDateLT, endDateGT, endDateLT,
				userId, hideEndedTasks, retrieveUserInstances, andOperator);
	}

	private String _getInstancesXml(Object[] args)
		throws WorkflowComponentException{

		long definitionId =((Long)args[0]).longValue();
		long instanceId = ((Long)args[1]).longValue();
		String definitionName = (String)args[2];
		String definitionVersion = (String)args[3];
		String startDateGT = (String)args[4];
		String startDateLT = (String)args[5];
		String endDateGT = (String)args[6];
		String endDateLT = (String)args[7];
		String userId = (String)args[8];
		boolean hideEndedTasks = ((Boolean)args[9]).booleanValue();
		boolean retrieveUserInstances = ((Boolean)args[10]).booleanValue();
		boolean andOperator = ((Boolean)args[11]).booleanValue();
		int start = ((Integer)args[12]).intValue();
		int end = ((Integer)args[13]).intValue();

		return SAWWorkflowLocalServiceUtil.getInstancesXml(
				definitionId,instanceId, definitionName, definitionVersion,
				startDateGT, startDateLT,
				endDateGT, endDateLT,
				userId, hideEndedTasks, retrieveUserInstances,
				andOperator, start, end);
	}

	private String _getTaskFormElementsXml(Object[] args)
		throws WorkflowComponentException{

		long taskId = ((Long)args[0]).longValue();
		return SAWWorkflowLocalServiceUtil.getTaskFormElementsXml(taskId);
	}

	private String _getTaskTransitionsXml(Object[] args)
		throws WorkflowComponentException{

		long taskId = ((Long)args[0]).longValue();
		return SAWWorkflowLocalServiceUtil.getTaskTransitionsXml(taskId);
	}

	private String _getTaskXml(Object[] args) throws WorkflowComponentException{

		long taskId = ((Long)args[0]).longValue();
		String userId = (String)args[1];
		return SAWWorkflowLocalServiceUtil.getTaskXml(taskId,userId);
	}

	private String _getUserTasksCountXml(Object[] args)
		throws WorkflowComponentException{

		long instanceId = ((Long)args[0]).longValue();
		String taskName = (String)args[1];
		String definitionName = (String)args[2];
		String assignedTo = (String)args[3];
		String createDateGT = (String)args[4];
		String createDateLT = (String)args[5];
		String startDateGT = (String)args[6];
		String startDateLT = (String)args[7];
		String endDateGT = (String)args[8];
		String endDateLT = (String)args[9];
		String userId = (String)args[10];
		boolean hideEndedTasks = ((Boolean)args[11]).booleanValue();
		boolean andOperator =((Boolean)args[12]).booleanValue();

		return SAWWorkflowLocalServiceUtil.getUserTasksCountXml(
			instanceId, taskName, definitionName, assignedTo,userId,
			createDateGT,createDateLT,
			startDateGT, startDateLT,
			endDateGT,endDateLT,
			hideEndedTasks, andOperator);

	}

	private String _getUserTasksXml(Object[] args)
		throws WorkflowComponentException{

		long instanceId = ((Long)args[0]).longValue();
		String taskName = (String)args[1];
		String definitionName = (String)args[2];
		String assignedTo = (String)args[3];
		String createDateGT = (String)args[4];
		String createDateLT = (String)args[5];
		String startDateGT = (String)args[6];
		String startDateLT = (String)args[7];
		String endDateGT = (String)args[8];
		String endDateLT = (String)args[9];
		String userId = (String)args[10];
		boolean hideEndedTasks = ((Boolean)args[11]).booleanValue();
		boolean andOperator =((Boolean)args[12]).booleanValue();
		int begin = ((Integer)args[13]).intValue();
		int end = ((Integer)args[14]).intValue();

		return SAWWorkflowLocalServiceUtil.getUserTasksXml(
			instanceId, taskName,
			definitionName, assignedTo,
			createDateGT, createDateLT,
			startDateGT, startDateLT,
			endDateGT, endDateLT,
			userId, hideEndedTasks, andOperator,
			begin, end);

	}

	private void _sendResponse(String responseDestination,Message message){
		MessageBusUtil.sendMessage(responseDestination, message);
	}

	private void _signalInstance(Object[] args)
		throws WorkflowComponentException{

		long instanceId = ((Long)args[0]).longValue();
		SAWWorkflowLocalServiceUtil.signalInstance(instanceId);

	}

	private void _signalToken(Object[] args)
		throws WorkflowComponentException{

		long instanceId = ((Long)args[0]).longValue();
		long tokenId = ((Long)args[1]).longValue();
		SAWWorkflowLocalServiceUtil.signalToken(instanceId,tokenId);

	}

	private String _startWorkflow(Object[] args)
		throws WorkflowComponentException{

		long definitionId = ((Long)args[0]).longValue();
		String userId = (String)args[1];
		return SAWWorkflowLocalServiceUtil.startWorkflow(definitionId,userId);

	}

	private String _updateTaskXml(Object[] args)
		throws WorkflowComponentException{

		long taskId = ((Long)args[0]).longValue();
		String transition = (String)args[1];
		String userId = (String)args[2];
		Map parameterMap = (Map)args[3];

		return SAWWorkflowLocalServiceUtil.updateTaskXml(
			taskId, transition,userId, parameterMap);

	}

	private static Log _log =
		LogFactoryUtil.getLog(SAWMessageListener.class);

}