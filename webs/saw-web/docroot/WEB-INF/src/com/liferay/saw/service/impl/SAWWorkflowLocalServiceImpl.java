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
 * Copyright 2008 Sun Microsystems Inc. All rights reserved.
 */

package com.liferay.saw.service.impl;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.jbi.WorkflowComponentException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.AggregateClassLoader;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.saw.service.base.SAWWorkflowLocalServiceBaseImpl;
import com.liferay.util.xml.DocUtil;

import com.sun.saw.Workflow;
import com.sun.saw.WorkflowException;
import com.sun.saw.WorkflowFactory;
import com.sun.saw.vo.BusinessProcessInstanceVO;
import com.sun.saw.vo.BusinessProcessVO;
import com.sun.saw.vo.CheckoutTaskVO;
import com.sun.saw.vo.CompleteTaskVO;
import com.sun.saw.vo.FilterTaskVO;
import com.sun.saw.vo.InvokeMethodVO;
import com.sun.saw.vo.NavigationVO;
import com.sun.saw.vo.OutputVO;
import com.sun.saw.vo.SaveTaskVO;
import com.sun.saw.vo.TaskVO;
import com.sun.saw.vo.TokenVO;
import com.sun.saw.vo.XFormVO;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * <a href="SAWWorkflowLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 * This class provides the workflow functionalities through
 * SAW (Simple API for Workflow)
 *
 * @author Ganesh Ram
 * @author Vihang Pathak
 */
public class SAWWorkflowLocalServiceImpl
	extends SAWWorkflowLocalServiceBaseImpl {

	public OutputVO checkoutTasks(CheckoutTaskVO checkoutTaskVO)
		throws WorkflowComponentException{
		
		OutputVO outputVO = null;
		ClassLoader contextClassLoader =_getCurrentClassLoader();
		_setAggregateClassLoader(contextClassLoader);
		
		try {
			Workflow workflow = _getWorkflowImpl();
			outputVO = workflow.checkoutTasks(checkoutTaskVO);
			_restoreClassLoader(contextClassLoader);
			return outputVO;
		}
		catch (WorkflowException e) {
			throw new WorkflowComponentException(e);
		}
	}
	
	public OutputVO completeTasks(CompleteTaskVO completeTaskVO)
		throws WorkflowComponentException{

		OutputVO outputVO = null;
		ClassLoader contextClassLoader =_getCurrentClassLoader();
		_setAggregateClassLoader(contextClassLoader);

		try {
			Workflow workflow = _getWorkflowImpl();
			outputVO = workflow.completeTasks(completeTaskVO);
			_restoreClassLoader(contextClassLoader);
			return outputVO;
		}
		catch (WorkflowException e) {
			throw new WorkflowComponentException(e);
		}
	}
	
	
	
	public String deploy(String xml) throws WorkflowComponentException {
		BusinessProcessVO businessProcessVO = new BusinessProcessVO();
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("processDefinition", xml);
		businessProcessVO.setParamMap(paramMap);
		OutputVO outputVO = null;

		ClassLoader contextClassLoader =_getCurrentClassLoader();
		_setAggregateClassLoader(contextClassLoader);

		try {
			Workflow workflow = _getWorkflowImpl();
			outputVO = workflow.deployBusinessProcess(businessProcessVO);
			_restoreClassLoader(contextClassLoader);
			String defnId = outputVO.getBusinessProcessVOList().get(0).getId();
			return defnId;
		}
		catch (WorkflowException e) {
			throw new WorkflowComponentException(e);
		}
	}

	public String getCurrentTasksXml(
			long instanceId, long tokenId, String userId)
		throws WorkflowComponentException {

		ClassLoader contextClassLoader =_getCurrentClassLoader();
		_setAggregateClassLoader(contextClassLoader);
		List<TaskVO> tasks = _getCurrentTasks(instanceId, tokenId, userId);
		_restoreClassLoader(contextClassLoader);
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("result");

		if (tasks != null) {
			for (int i = 0; i < tasks.size(); i++) {
				TaskVO task = (TaskVO) tasks.get(i);
				_createElement(task, root,userId);
			}
		}

		return doc.asXML();

	}

	public String getDefinitionsCountXml(long definitionId,
			String name,String userId)
		throws WorkflowComponentException {

		Workflow workflow = null;
		ClassLoader contextClassLoader = null;
		contextClassLoader =_getCurrentClassLoader();
		_setAggregateClassLoader(contextClassLoader);

		try {
			workflow = _getWorkflowImpl();
		}
		catch (WorkflowException e) {
			throw new WorkflowComponentException(e);
		}

		int count = 0;
		if (definitionId > 0) {
			count = 1;
		}
		else {
			Class tmpClass = _loadSAWJbpmGraphSession();
			String timeZoneId = _getTimeZoneIdForUser(userId);
			Map resultsMap =_invokeMethod(
				tmpClass,"countProcessDefinitionsByName",
				userId,timeZoneId,
				new Class[] {String.class},
				new Object[] {name},
				workflow);

			Integer tmpCount = (Integer) resultsMap.get("invokeMethodResult");
			count = tmpCount.intValue();

		}

		_restoreClassLoader(contextClassLoader);

		StringBuilder sm = new StringBuilder();
		sm.append("<result>");
		sm.append("<count>");
		sm.append(count);
		sm.append("</count>");
		sm.append("</result>");

		return sm.toString();

	}

	public String getDefinitionsXml(
			long definitionId, String name,
			String userId, int begin, int end)
		throws WorkflowComponentException {
		
		Workflow workflow = null;
		ClassLoader contextClassLoader = null;
		contextClassLoader =_getCurrentClassLoader();
		_setAggregateClassLoader(contextClassLoader);
		try {
			workflow = _getWorkflowImpl();
		}
		catch (WorkflowException e) {
			throw new WorkflowComponentException(e);
		}

		BusinessProcessVO businessProcessVO = new BusinessProcessVO();
		List<BusinessProcessVO> bpVOList = null;

		if (definitionId > 0) {
			businessProcessVO.setId(String.valueOf(definitionId));
			OutputVO outputVO = null;
			try {
				outputVO = workflow.getBusinessProcess(businessProcessVO);
			}
			catch (WorkflowException e) {
				throw new WorkflowComponentException(e);
			}
			bpVOList = outputVO.getBusinessProcessVOList();
		}
		else {

			Class tmpClass = _loadSAWJbpmGraphSession();
			String timeZoneId = _getTimeZoneIdForUser(userId);

			Map resultsMap =
				_invokeMethod(
					tmpClass,"findProcessDefinitionsByName",
					userId,timeZoneId,
					new Class[] {String.class, int.class, int.class},
					new Object[] {name, begin, end}, workflow);

			bpVOList = (ArrayList<BusinessProcessVO>) resultsMap.get(
							"invokeMethodResult");

		}

		_restoreClassLoader(contextClassLoader);
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("result");
		for (int i = 0; i < bpVOList.size(); i++) {
			BusinessProcessVO bpVO = (BusinessProcessVO) bpVOList.get(i);
			_createElement(bpVO, root);
		}
		return doc.asXML();
	}

	public String getDefinitionXml(long definitionId)
		throws WorkflowComponentException {

		BusinessProcessVO businessProcessVO = new BusinessProcessVO();
		businessProcessVO.setId(String.valueOf(definitionId));
		OutputVO outputVO = null;

		ClassLoader contextClassLoader =_getCurrentClassLoader();
		_setAggregateClassLoader(contextClassLoader);

		try {
			Workflow workflow = _getWorkflowImpl();
			outputVO = workflow.getBusinessProcess(businessProcessVO);

		}
		catch (WorkflowException e) {
			throw new WorkflowComponentException(e);
		}

		_restoreClassLoader(contextClassLoader);

		List<BusinessProcessVO> bpList = outputVO.getBusinessProcessVOList();
		BusinessProcessVO bpVO = bpList.get(0);
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("result");
		_createElement(bpVO, root);
		return doc.asXML();

	}

	public String getInstancesCountXml(
			long definitionId, long instanceId, String definitionName,
			String definitionVersion, String startDateGT, String startDateLT,
			String endDateGT, String endDateLT, String userId,
			boolean hideEndedTasks, boolean retrieveUserInstances,
			boolean andOperator)
		throws WorkflowComponentException {

		int count = 0;
		ClassLoader contextClassLoader =_getCurrentClassLoader();
		_setAggregateClassLoader(contextClassLoader);

		if (definitionId > 0) {
			count = 1;
		}
		else if (instanceId > 0) {
			count = 1;
		}
		else {
			String assignedUserId = null;

			if (retrieveUserInstances) {
				assignedUserId = userId;
			}
			Workflow workflow = null;

			try {
				workflow = _getWorkflowImpl();
			}
			catch (WorkflowException e) {
				throw new WorkflowComponentException(e);
			}

			Class[] paramTypeArray = new Class[] {
				String.class, String.class, String.class, String.class,
				String.class, String.class, boolean.class, String.class,
				boolean.class };

			Object[] paramValueArray = new Object[] {
				definitionName, definitionVersion, startDateGT,
				startDateLT, endDateGT, endDateLT, hideEndedTasks,
				assignedUserId, andOperator };

			Class tmpClass = _loadSAWJbpmGraphSession();
			String timeZoneId = _getTimeZoneIdForUser(userId);

			Map resultsMap = _invokeMethod(
						tmpClass,"countProcessInstancesBySearchTerms",
						userId,timeZoneId, paramTypeArray,paramValueArray,
						workflow);

			count = ((Integer) resultsMap.get("invokeMethodResult")).intValue();

		}

		_restoreClassLoader(contextClassLoader);

		StringBuilder sm = new StringBuilder();
		sm.append("<result>");
		sm.append("<count>");
		sm.append(count);
		sm.append("</count>");
		sm.append("</result>");

		return sm.toString();

	}

	public String getInstancesXml(
			long definitionId, long instanceId, String definitionName,
			String definitionVersion, String startDateGT, String startDateLT,
			String endDateGT, String endDateLT, String userId,
			boolean hideEndedTasks, boolean retrieveUserInstances,
			boolean andOperator, int start, int end)
		throws WorkflowComponentException {

		List<BusinessProcessInstanceVO> instances =
			new ArrayList<BusinessProcessInstanceVO>();
		Workflow workflow = null;
		OutputVO outputVO = null;

		ClassLoader contextClassLoader =_getCurrentClassLoader();
		_setAggregateClassLoader(contextClassLoader);

		try {
			workflow = _getWorkflowImpl();
		}
		catch (WorkflowException e) {
			throw new WorkflowComponentException(e);
		}

		if (definitionId > 0) {

			BusinessProcessVO businessProcessVO = new BusinessProcessVO();
			businessProcessVO.setId(String.valueOf(definitionId));
			try {
				outputVO = workflow.getBusinessProcess(businessProcessVO);
			}
			catch (WorkflowException e1) {
				throw new WorkflowComponentException(e1);
			}
			List<BusinessProcessVO> bpVOList =
				outputVO.getBusinessProcessVOList();

			businessProcessVO = (BusinessProcessVO) bpVOList.get(0);
			BusinessProcessInstanceVO instVO = new BusinessProcessInstanceVO();
			instVO.setBusinessProcess(businessProcessVO);

			try {
				outputVO = workflow.getBusinessProcessInstances(instVO);
			}
			catch (WorkflowException e) {
				throw new WorkflowComponentException(e);
			}

			instances = outputVO.getBusinessProcessInstanceVOList();

		}
		else if (instanceId > 0) {
			BusinessProcessInstanceVO instVO = new BusinessProcessInstanceVO();
			instVO.setId(String.valueOf(instanceId));
			try {
				outputVO = workflow.getBusinessProcessInstances(instVO);
			}
			catch (WorkflowException e) {
				throw new WorkflowComponentException(e);
			}

			instances = outputVO.getBusinessProcessInstanceVOList();

		}
		else {
			String assignedUserId = null;

			if (retrieveUserInstances) {
				assignedUserId = userId;
			}

			Class[] paramTypeArray = new Class[] {
					String.class, String.class, String.class, String.class,
					String.class, String.class, boolean.class, String.class,
					boolean.class, int.class, int.class
			};

			Object[] paramValueArray = new Object[] {
					definitionName, definitionVersion, startDateGT,
					startDateLT, endDateGT, endDateLT, hideEndedTasks,
					assignedUserId, andOperator, start, end };

			Class tmpClass = _loadSAWJbpmGraphSession();
			String timeZoneId = _getTimeZoneIdForUser(userId);

			Map resultsMap = _invokeMethod(
						tmpClass,"findProcessInstancesBySearchTerms",
						userId,timeZoneId, paramTypeArray,
						paramValueArray, workflow);

			instances = (ArrayList<BusinessProcessInstanceVO>) resultsMap.get(
						"invokeMethodResult");
		}

		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("result");

		for (int i = 0; i < instances.size(); i++) {
			BusinessProcessInstanceVO instance =
				(BusinessProcessInstanceVO) instances.get(i);

			_createElement(instance, root, true,userId);
		}

		_restoreClassLoader(contextClassLoader);

		return doc.asXML();

	}

	public String getTaskFormElementsXml(long taskId)
		throws WorkflowComponentException {

		Workflow workflow = null;
		OutputVO outputVO = null;

		ClassLoader contextClassLoader =_getCurrentClassLoader();
		_setAggregateClassLoader(contextClassLoader);

		try {
			workflow = _getWorkflowImpl();
		}
		catch (WorkflowException e) {
			throw new WorkflowComponentException(e);
		}

		XFormVO xForm = new XFormVO();
		xForm.setTaskId(String.valueOf(taskId));

		try {
			outputVO = workflow.getXForm(xForm);
		}
		catch (WorkflowException e) {
			throw new WorkflowComponentException(e);
		}

		_restoreClassLoader(contextClassLoader);

		List<XFormVO> xFormVOList = null;
		xFormVOList = outputVO.getXFormElements();
		Document doc = DocumentHelper.createDocument();

		Element root = doc.addElement("result");

		for (int i = 0; i < xFormVOList.size(); i++) {
			XFormVO xFormVO = xFormVOList.get(i);
			_createElement(xFormVO, root);
		}

		return doc.asXML();

	}
	
	public OutputVO getTasks(FilterTaskVO filterTaskVO)
		throws WorkflowComponentException{

	OutputVO outputVO = null;
	ClassLoader contextClassLoader =_getCurrentClassLoader();
	_setAggregateClassLoader(contextClassLoader);

	try {
		Workflow workflow = _getWorkflowImpl();
		outputVO = workflow.getTasks(filterTaskVO);
		_restoreClassLoader(contextClassLoader);
		return outputVO;
	}
	catch (WorkflowException e) {
		throw new WorkflowComponentException(e);
	}
}

	public String getTaskTransitionsXml(long taskId)
		throws WorkflowComponentException {

		ClassLoader contextClassLoader =_getCurrentClassLoader();
		_setAggregateClassLoader(contextClassLoader);

		TaskVO taskVO = _getTaskVO(taskId);
		List<NavigationVO> transitions = taskVO.getAvailableTransitions();

		_restoreClassLoader(contextClassLoader);

		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("result");

		for (int i = 0; i < transitions.size(); i++) {
			NavigationVO navigation = (NavigationVO) transitions.get(i);
			DocUtil.add(root, "transition", navigation.getName());
		}

		return doc.asXML();

	}

	public String getTaskXml(long taskId,String userId)
		throws WorkflowComponentException {

		ClassLoader contextClassLoader =_getCurrentClassLoader();
		_setAggregateClassLoader(contextClassLoader);

		TaskVO taskVO = _getTaskVO(taskId);

		_restoreClassLoader(contextClassLoader);

		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("result");
		_createElement(taskVO, root,userId);

		return doc.asXML();

	}

	public String getUserTasksCountXml(
			long instanceId, String taskName, String definitionName,
			String assignedTo,String userId,
			String createDateGT, String createDateLT,
			String startDateGT, String startDateLT,
			String endDateGT,String endDateLT,
			boolean hideEndedTasks,boolean andOperator)
		throws WorkflowComponentException {

		Workflow workflow = null;

		ClassLoader contextClassLoader =_getCurrentClassLoader();
		_setAggregateClassLoader(contextClassLoader);

		try {
			workflow = _getWorkflowImpl();
		}
		catch (WorkflowException e) {
			throw new WorkflowComponentException(e);
		}

		Class[] paramTypeArray = new Class[] {
			String.class, String.class, String.class, String.class,
			String.class, String.class, String.class, String.class,
			String.class, boolean.class, boolean.class
			};

		Object[] paramValueArray = new Object[] {
			taskName, definitionName, assignedTo, createDateGT,
			createDateLT, startDateGT, startDateLT, endDateGT,
			endDateLT, hideEndedTasks, andOperator
			};

		Class tmpClass = _loadSAWJbpmGraphSession();
		String timeZoneId = _getTimeZoneIdForUser(userId);

		Map resultsMap = _invokeMethod(
					tmpClass,"countTaskInstancesBySearchTerms",
					userId,timeZoneId,
					paramTypeArray,
					paramValueArray, workflow);

		Integer tmpCount = (Integer) resultsMap.get("invokeMethodResult");
		int count = tmpCount.intValue();

		_restoreClassLoader(contextClassLoader);

		StringBuilder sm = new StringBuilder();
		sm.append("<result>");
		sm.append("<count>");
		sm.append(count);
		sm.append("</count>");
		sm.append("</result>");

		return sm.toString();

	}

	public String getUserTasksXml(
			long instanceId, String taskName, String definitionName,
			String assignedTo, String createDateGT, String createDateLT,
			String startDateGT, String startDateLT, String endDateGT,
			String endDateLT, String userId, boolean hideEndedTasks,
			boolean andOperator, int begin, int end)
		throws WorkflowComponentException {

		ClassLoader contextClassLoader =_getCurrentClassLoader();
		_setAggregateClassLoader(contextClassLoader);

		List<TaskVO> taskVOList = _getUserTasks(
						instanceId, taskName, definitionName, assignedTo,
						createDateGT,createDateLT,
						startDateGT, startDateLT,
						endDateGT,endDateLT,
						userId, hideEndedTasks, andOperator,
						begin, end);

		_restoreClassLoader(contextClassLoader);

		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("result");
		for (int i = 0; i < taskVOList.size(); i++) {
			TaskVO task = taskVOList.get(i);
			_createElement(task, root,userId);
		}

		return doc.asXML();

	}
	
	public OutputVO saveTasks(SaveTaskVO saveTaskVO)
		throws WorkflowComponentException{
	
	OutputVO outputVO = null;
	ClassLoader contextClassLoader =_getCurrentClassLoader();
	_setAggregateClassLoader(contextClassLoader);
	
	try {
		Workflow workflow = _getWorkflowImpl();
		outputVO = workflow.saveTasks(saveTaskVO);
		_restoreClassLoader(contextClassLoader);
		return outputVO;
	}
	catch (WorkflowException e) {
		throw new WorkflowComponentException(e);
	}
}
	
	

	public void signalInstance(long instanceId)
		throws WorkflowComponentException {

		OutputVO outputVO = null;
		BusinessProcessInstanceVO businessProcessInstance =
			new BusinessProcessInstanceVO();

		businessProcessInstance.setId(String.valueOf(instanceId));

		ClassLoader contextClassLoader =_getCurrentClassLoader();
		_setAggregateClassLoader(contextClassLoader);

		try {
			Workflow workflow = _getWorkflowImpl();
			outputVO =
				workflow.signalBusinessProcessInstance(businessProcessInstance);
		}
		catch (WorkflowException e) {
			throw new WorkflowComponentException(e);
		}

		_restoreClassLoader(contextClassLoader);
	}

	public void signalToken(long instanceId, long tokenId)
		throws WorkflowComponentException {

		TokenVO token = new TokenVO();
		token.setId(String.valueOf(tokenId));
		BusinessProcessInstanceVO bpi = new BusinessProcessInstanceVO();
		bpi.setId(String.valueOf(instanceId));
		token.setBusinessProcessInstance(bpi);

		Workflow workflow = null;
		OutputVO outputVO = null;

		ClassLoader contextClassLoader =_getCurrentClassLoader();
		_setAggregateClassLoader(contextClassLoader);

		try {
			workflow = _getWorkflowImpl();
			outputVO = workflow.signalToken(token);
		}
		catch (WorkflowException e) {
			throw new WorkflowComponentException(e);
		}

		_restoreClassLoader(contextClassLoader);

	}

	public String startWorkflow(long definitionId,String userId)
		throws WorkflowComponentException {

		Workflow workflow = null;
		OutputVO outputVO = null;

		BusinessProcessVO businessProcessVO = new BusinessProcessVO();
		businessProcessVO.setId(String.valueOf(definitionId));
		businessProcessVO.setUserId(userId);

		ClassLoader contextClassLoader =_getCurrentClassLoader();
		_setAggregateClassLoader(contextClassLoader);

		try {
			workflow = _getWorkflowImpl();
			outputVO = workflow.startBusinessProcess(businessProcessVO);
		}
		catch (WorkflowException e) {
			throw new WorkflowComponentException(e);
		}
		_restoreClassLoader(contextClassLoader);

		BusinessProcessInstanceVO bpiVO =
			outputVO.getBusinessProcessInstanceVOList().get(0);

		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("result");
		_createElement(bpiVO, root, false,userId);

		return doc.asXML();

	}

	public Map updateTask(long taskId, String transition,
			String userId, Map parameterMap)
		throws WorkflowComponentException {

		Class[] paramTypeArray = new Class[] {
				long.class, String.class, String.class, Map.class}
				;

		Object[] paramValueArray = new Object[] {
				taskId, transition, userId, parameterMap
				};

		ClassLoader contextClassLoader =_getCurrentClassLoader();
		_setAggregateClassLoader(contextClassLoader);

		Workflow workflow = null;
		try {
			workflow = _getWorkflowImpl();
		}
		catch (WorkflowException e) {
			throw new WorkflowComponentException(e);

		}
		Class tmpClass = _loadSAWJbpmGraphSession();
		String timeZoneId = _getTimeZoneIdForUser(userId);
		Map resultsMap = _invokeMethod(
						tmpClass, "updateTask",userId,timeZoneId,
						paramTypeArray, paramValueArray, workflow);

		Map errors = (HashMap) resultsMap.get("invokeMethodResult");
		_restoreClassLoader(contextClassLoader);

		return errors;
	}

	public String updateTaskXml(
			long taskId, String transition,
			String userId, Map parameterMap)
		throws WorkflowComponentException {

		Map errors = updateTask(taskId, transition, userId, parameterMap);

		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("results");
		Iterator itr = errors.entrySet().iterator();
		while (itr.hasNext()) {
			Map.Entry entry = (Map.Entry) itr.next();
			String name = (String) entry.getKey();
			String code = (String) entry.getValue();
			Element el = root.addElement("error");
			DocUtil.add(el, "name", name);
			DocUtil.add(el, "code", code);
		}

		return doc.asXML();
	}

	private void _createElement(
			BusinessProcessInstanceVO instance,
			Element root, boolean includeToken,String userId)
		throws WorkflowComponentException {

		Element el = root.addElement("instance");
		DocUtil.add(el, "instanceId", instance.getId());
		DocUtil.add(el, "startDate", instance.getStartDate());
		DocUtil.add(el, "endDate", instance.getEndDate());

		if (instance.getEndDate() != null) {
			DocUtil.add(el, "ended", "true");
		}
		else {
			DocUtil.add(el, "ended", "false");
		}

		_createElement(instance.getBusinessProcess(), el);

		if (includeToken) {
			_createElement(instance.getToken(), el, true,userId);
		}
	}

	private void _createElement(
		BusinessProcessVO businessProcessVO, Element root) {

		Element el = root.addElement("definition");
		DocUtil.add(el, "definitionId", businessProcessVO.getId());
		DocUtil.add(el, "name", businessProcessVO.getName());
		DocUtil.add(el, "version", businessProcessVO.getVersion());
	}

	private void _createElement(TaskVO task, Element root,String userId)
		throws WorkflowComponentException {

		Element el = root.addElement("task");

		DocUtil.add(el, "taskId", task.getId());
		DocUtil.add(el, "name", task.getName());
		DocUtil.add(el, "assignedUserId", (String) task.getAssignedTo().get(0));
		DocUtil.add(el, "createDate", _formatDateTime(task.getCreatedDate()));
		DocUtil.add(el, "startDate", _formatDateTime(task.getStartDate()));
		DocUtil.add(el, "endDate", _formatDateTime(task.getDueDate()));

		_createElement(task.getBusinessProcessInstance(), el, false,userId);

	}

	private void _createElement(TokenVO token, Element root,
			boolean checkChildren,String userId)
		throws WorkflowComponentException {

		Element tokenEl = root.addElement("token");
		DocUtil.add(tokenEl, "tokenId", token.getId());
		DocUtil.add(tokenEl, "name", token.getNode().getName());

		if (token.getNode().toString().startsWith("Join")) {
			DocUtil.add(tokenEl, "type", "join");
		}
		else {
			DocUtil.add(tokenEl, "type", "default");
		}

		List<TaskVO> tasks = _getCurrentTasks(
				new Long(token.getBusinessProcessInstance().getId()).longValue()
				,new Long(token.getId()).longValue(), userId);

		if (tasks == null) {
			Element task = tokenEl.addElement("task");
			task.addElement("taskId").addText("null");
		}
		else {
			for (int i = 0; i < tasks.size(); i++) {
				TaskVO task = tasks.get(i);
				_createElement(task, tokenEl,userId);
			}
		}

		if (checkChildren) {
			Map activeChildren =_getActiveChildren(new Long(
					token.getBusinessProcessInstance().getId()).longValue());

			if (_hasActiveChildren(activeChildren)) {

				Iterator itr = activeChildren.values().iterator();

				while (itr.hasNext()) {
					TokenVO child = (TokenVO) itr.next();
					_createElement(child, tokenEl, false,userId);
				}
			}
		}
	}

	private void _createElement(XFormVO xForm, Element root) {

		Element el = root.addElement("taskFormElement");
		DocUtil.add(el, "type", xForm.getType());
		DocUtil.add(el, "displayName", xForm.getDisplayName());
		DocUtil.add(el, "variableName", xForm.getVariableName());
		DocUtil.add(el, "value", xForm.getVariableValue());
		DocUtil.add(el, "readable", xForm.isReadable());
		DocUtil.add(el, "writable", xForm.isWritable());
		DocUtil.add(el, "required", xForm.isRequired());

		List values = xForm.getValueList();
		Element valuesEl = el.addElement("values");
		for (int i = 0; i < values.size(); i++) {
			String value = (String) values.get(i);

			DocUtil.add(valuesEl, "value", value);
		}

	}

	private String _formatDateTime(String date)
		throws WorkflowComponentException {

		if (date == null) {
			return null;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aaa");
		Date tmpDate = null;
		try {
			tmpDate = sdf.parse(date);
		}
		catch (ParseException e) {
			throw new WorkflowComponentException(e);
		}

		return sdf.format(tmpDate);
	}

	private Map _getActiveChildren(long instanceId)
		throws WorkflowComponentException {

		BusinessProcessInstanceVO processInstance =
			_getBusinessProcessInstanceVO(instanceId);

		Map activeChildren = processInstance.getToken().getActiveChildren();
		return activeChildren;
	}

	private List<BusinessProcessInstanceVO> _getBusinessProcessInstances(
			long instanceId)
		throws WorkflowComponentException {

		BusinessProcessInstanceVO businessProcessInstanceVO =
			new BusinessProcessInstanceVO();

		businessProcessInstanceVO.setId(String.valueOf(instanceId));

		OutputVO outputVO = null;
		Workflow workflow = null;

		try {
			workflow = _getWorkflowImpl();
			outputVO = workflow.getBusinessProcessInstances(
						businessProcessInstanceVO);
		}
		catch (WorkflowException e) {
			throw new WorkflowComponentException(e);
		}

		List<BusinessProcessInstanceVO> bpInstVOList =
			outputVO.getBusinessProcessInstanceVOList();

		return bpInstVOList;
	}

	private BusinessProcessInstanceVO _getBusinessProcessInstanceVO(
			long instanceId)
		throws WorkflowComponentException {

		List<BusinessProcessInstanceVO> bpInstVOList =
			_getBusinessProcessInstances(instanceId);

		BusinessProcessInstanceVO businessProcessInstanceVO =
			bpInstVOList.get(0);

		return businessProcessInstanceVO;
	}

	private ClassLoader _getCurrentClassLoader(){
		return Thread.currentThread().getContextClassLoader();
	}

	private List<TaskVO> _getCurrentTasks(
			long instanceId, long tokenId, String userId)
		throws WorkflowComponentException {

		List<TaskVO> userTasks = _getUserTasks(instanceId, null, null, null,
												null, null, null, null, null,
												null, userId, false, false,
												0, 0);

		List<TaskVO> currentTasks = new ArrayList<TaskVO>();

		FilterTaskVO filter = new FilterTaskVO();
		TokenVO token = new TokenVO();
		token.setId(String.valueOf(tokenId));
		filter.setToken(token);

		Workflow workflow = null;
		OutputVO outputVO = null;
		try {
			workflow = _getWorkflowImpl();
			outputVO = workflow.getTasks(filter);
		}
		catch (WorkflowException e) {
			throw new WorkflowComponentException(e);
		}

		List<TaskVO> tokenTasks = outputVO.getTaskVOList();

		if (tokenTasks.size() == 0) {
			currentTasks = null;
		}
		else {
			Iterator<TaskVO> itr = tokenTasks.iterator();
			Set<Long> tokenTaskIds = new HashSet<Long>();
			while (itr.hasNext()) {
				TaskVO task = itr.next();
				tokenTaskIds.add(new Long(task.getId()));
			}

			itr = userTasks.iterator();

			while (itr.hasNext()) {
				TaskVO task = itr.next();
				if (tokenTaskIds.contains(new Long(task.getId()))) {
					currentTasks.add(task);
				}
			}
		}

		return currentTasks;

	}

	private TaskVO _getTaskVO(long taskId) throws WorkflowComponentException {

		Workflow workflow = null;
		OutputVO outputVO = null;
		try {
			workflow = _getWorkflowImpl();
		}
		catch (WorkflowException e) {
			throw new WorkflowComponentException(e);
		}

		FilterTaskVO filterTaskVO = new FilterTaskVO();
		List<String> taskIdList = new ArrayList<String>();
		taskIdList.add(String.valueOf(taskId));
		filterTaskVO.setTaskIdList(taskIdList);

		try {
			outputVO = workflow.getTaskByTaskId(filterTaskVO);
		}
		catch (WorkflowException e) {
			throw new WorkflowComponentException(e);
		}

		List<TaskVO> taskVOList = outputVO.getTaskVOList();
		TaskVO taskVO = taskVOList.get(0);
		return taskVO;

	}

	private String _getTimeZoneIdForUser(String userId)
		throws WorkflowComponentException{
		
		User user = null;
		String timeZoneId = null; 
		
		if(Validator.isNull(userId)){
			return null;
		} 
		else if ("%".equals(userId)){
			return null;
		}

		try {
			user = UserLocalServiceUtil.getUserById(
					(new Long(userId)).longValue());
		}
		catch (NumberFormatException e) {
			throw new WorkflowComponentException(e);
		}
		catch (PortalException e) {
			throw new WorkflowComponentException(e);
		}
		catch (SystemException e) {
			throw new WorkflowComponentException(e);
		}
		timeZoneId = user.getTimeZoneId();
		return timeZoneId;
	}

	private List<TaskVO> _getUserTasks(
			long instanceId, String taskName, String definitionName,
			String assignedTo, String createDateGT, String createDateLT,
			String startDateGT, String startDateLT, String endDateGT,
			String endDateLT, String userId, boolean hideEndedTasks,
			boolean andOperator, int begin, int end)
		throws WorkflowComponentException {

		List<TaskVO> taskVOList = null;

		if (Validator.isNull(taskName) && Validator.isNull(definitionName) &&
			Validator.isNull(assignedTo) && Validator.isNull(createDateGT) &&
			Validator.isNull(createDateLT) && Validator.isNull(startDateGT) &&
			Validator.isNull(startDateLT) && Validator.isNull(endDateGT) &&
			Validator.isNull(endDateLT)) {

			taskVOList = _getUserTasks(userId, instanceId);

		}
		else {

			Workflow workflow = null;
			try {
				workflow = _getWorkflowImpl();
			}
			catch (WorkflowException e) {
				throw new WorkflowComponentException(e);
			}

			Class[] paramTypeArray = new Class[] {
					String.class, String.class, String.class, String.class,
					String.class, String.class, String.class, String.class,
					String.class, boolean.class, boolean.class,
					int.class,int.class
			};
			Object[] paramValueArray = new Object[] {
					taskName, definitionName, assignedTo, createDateGT,
					createDateLT, startDateGT, startDateLT, endDateGT,
					endDateLT, hideEndedTasks, andOperator, begin, end};

			Class tmpClass = _loadSAWJbpmGraphSession();
			String timeZoneId = _getTimeZoneIdForUser(userId);

			Map resultsMap = _invokeMethod(
							tmpClass,"findTaskInstancesBySearchTerms",
							userId,timeZoneId,
							paramTypeArray,paramValueArray, workflow);

			taskVOList =
				(ArrayList<TaskVO>) resultsMap.get("invokeMethodResult");

		}

		return taskVOList;

	}

	private List<TaskVO> _getUserTasks(String actorId, long instanceId)
		throws WorkflowComponentException {

		Workflow workflow = null;
		OutputVO outputVO = null;
		try {
			workflow = _getWorkflowImpl();
		}
		catch (WorkflowException e) {
			throw new WorkflowComponentException(e);
		}

		BusinessProcessInstanceVO instVO = new BusinessProcessInstanceVO();
		instVO.setId(String.valueOf(instanceId));

		FilterTaskVO filterTaskVO = new FilterTaskVO();
		filterTaskVO.setUserId(actorId);
		filterTaskVO.setBusinessProcessInstance(instVO);

		try {
			outputVO = workflow.getTasks(filterTaskVO);
		}
		catch (WorkflowException e) {
			throw new WorkflowComponentException(e);
		}

		List<TaskVO> taskVOList = outputVO.getTaskVOList();

		return taskVOList;

	}

	private Workflow _getWorkflowImpl() throws WorkflowException {

		Workflow workflow = null;
		WorkflowFactory workflowFactory = WorkflowFactory.getInstance();
		workflow = workflowFactory.getWorkflowInstance();

		return workflow;

	}

	private boolean _hasActiveChildren(Map activeChildren) {
		Iterator itr = activeChildren.values().iterator();
		while (itr.hasNext()) {
			TokenVO tokenVO = (TokenVO) itr.next();
			if (!tokenVO.getNode().toString().startsWith("Join")) {
				return true;
			}
		}

		return false;
	}

	private Map _invokeMethod( 
			Class className, String methodName,
			String userId,String timeZoneId, Class[] paramTypeArray,
			Object[] paramValueArray, Workflow workflow)
		throws WorkflowComponentException {

		InvokeMethodVO invokeMethodVO = new InvokeMethodVO();
		Class[] constructorParamTypeArray = new Class[] {
				String.class, String.class};
		Object[] constructorValueTypeArray = new Object[] {
				userId, timeZoneId};

		invokeMethodVO.setConstructorParamTypeArray(constructorParamTypeArray);
		invokeMethodVO.setConstructorValueTypeArray(constructorValueTypeArray);
		invokeMethodVO.setClassName(className);
		invokeMethodVO.setMethodName(methodName);
		invokeMethodVO.setParamTypeArray(paramTypeArray);
		invokeMethodVO.setParamValueArray(paramValueArray);

		OutputVO outputVO = null;
		try {
			outputVO = workflow.invokeMethod(invokeMethodVO);
		}
		catch (WorkflowException e) {
			throw new WorkflowComponentException(e);
		}

		Map resultsMap = outputVO.getCustomResultsMap();

		return resultsMap;
	}

	private Class _loadSAWJbpmGraphSession()
		throws WorkflowComponentException{

		Class tmpClass = null;
		try {
			tmpClass = _getCurrentClassLoader().loadClass(
				"com.sun.liferay.saw.ext.SAWJbpmGraphSession");
		}
		catch (ClassNotFoundException e) {
			throw new WorkflowComponentException(e);
		}

		return tmpClass;
	}

	private void _restoreClassLoader(ClassLoader cl){
		Thread.currentThread().setContextClassLoader(cl);
	}

	private void _setAggregateClassLoader(ClassLoader portletClassLoader){

		AggregateClassLoader cl =new AggregateClassLoader(
				portletClassLoader, PortalClassLoaderUtil.getClassLoader());

		Thread.currentThread().setContextClassLoader(cl);

	}

	private static final String _JRUN_ANONYMOUS = "anonymous-guest";

	private static final Log _log =
		LogFactoryUtil.getLog(SAWWorkflowLocalServiceImpl.class);

	private static final String _ORACLE_ANONYMOUS = "guest";
	private static final String _SUN_ANONYMOUS = "ANONYMOUS";
	private static final String _WEBLOGIC_ANONYMOUS = "<anonymous>";

	private static final String[] _ANONYMOUS_NAMES = {
		_JRUN_ANONYMOUS, _ORACLE_ANONYMOUS, _SUN_ANONYMOUS, _WEBLOGIC_ANONYMOUS
	};

}