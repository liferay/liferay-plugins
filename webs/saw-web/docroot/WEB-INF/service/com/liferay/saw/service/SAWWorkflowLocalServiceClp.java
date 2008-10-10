/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

package com.liferay.saw.service;

import com.liferay.portal.kernel.util.BooleanWrapper;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.LongWrapper;
import com.liferay.portal.kernel.util.NullWrapper;

/**
 * <a href="SAWWorkflowLocalServiceClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SAWWorkflowLocalServiceClp implements SAWWorkflowLocalService {
	public SAWWorkflowLocalServiceClp(ClassLoaderProxy classLoaderProxy) {
		_classLoaderProxy = classLoaderProxy;
	}

	public com.sun.saw.vo.OutputVO checkoutTasks(
		com.sun.saw.vo.CheckoutTaskVO checkoutTaskVO)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		Object paramObj0 = ClpSerializer.translateInput(checkoutTaskVO);

		if (checkoutTaskVO == null) {
			paramObj0 = new NullWrapper("com.sun.saw.vo.CheckoutTaskVO");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("checkoutTasks",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.jbi.WorkflowComponentException) {
				throw (com.liferay.portal.kernel.jbi.WorkflowComponentException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.sun.saw.vo.OutputVO)ClpSerializer.translateOutput(returnObj);
	}

	public com.sun.saw.vo.OutputVO completeTasks(
		com.sun.saw.vo.CompleteTaskVO completeTaskVO)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		Object paramObj0 = ClpSerializer.translateInput(completeTaskVO);

		if (completeTaskVO == null) {
			paramObj0 = new NullWrapper("com.sun.saw.vo.CompleteTaskVO");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("completeTasks",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.jbi.WorkflowComponentException) {
				throw (com.liferay.portal.kernel.jbi.WorkflowComponentException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.sun.saw.vo.OutputVO)ClpSerializer.translateOutput(returnObj);
	}

	public java.lang.String deploy(java.lang.String xml)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		Object paramObj0 = ClpSerializer.translateInput(xml);

		if (xml == null) {
			paramObj0 = new NullWrapper("java.lang.String");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("deploy",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.jbi.WorkflowComponentException) {
				throw (com.liferay.portal.kernel.jbi.WorkflowComponentException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	public java.lang.String getCurrentTasksXml(long instanceId, long tokenId,
		java.lang.String userId)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		Object paramObj0 = new LongWrapper(instanceId);

		Object paramObj1 = new LongWrapper(tokenId);

		Object paramObj2 = ClpSerializer.translateInput(userId);

		if (userId == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getCurrentTasksXml",
					new Object[] { paramObj0, paramObj1, paramObj2 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.jbi.WorkflowComponentException) {
				throw (com.liferay.portal.kernel.jbi.WorkflowComponentException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	public java.lang.String getDefinitionsCountXml(long definitionId,
		java.lang.String name, java.lang.String userId)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		Object paramObj0 = new LongWrapper(definitionId);

		Object paramObj1 = ClpSerializer.translateInput(name);

		if (name == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object paramObj2 = ClpSerializer.translateInput(userId);

		if (userId == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getDefinitionsCountXml",
					new Object[] { paramObj0, paramObj1, paramObj2 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.jbi.WorkflowComponentException) {
				throw (com.liferay.portal.kernel.jbi.WorkflowComponentException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	public java.lang.String getDefinitionsXml(long definitionId,
		java.lang.String name, java.lang.String userId, int begin, int end)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		Object paramObj0 = new LongWrapper(definitionId);

		Object paramObj1 = ClpSerializer.translateInput(name);

		if (name == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object paramObj2 = ClpSerializer.translateInput(userId);

		if (userId == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = new IntegerWrapper(begin);

		Object paramObj4 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getDefinitionsXml",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.jbi.WorkflowComponentException) {
				throw (com.liferay.portal.kernel.jbi.WorkflowComponentException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	public java.lang.String getDefinitionXml(long definitionId)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		Object paramObj0 = new LongWrapper(definitionId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getDefinitionXml",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.jbi.WorkflowComponentException) {
				throw (com.liferay.portal.kernel.jbi.WorkflowComponentException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	public java.lang.String getInstancesCountXml(long definitionId,
		long instanceId, java.lang.String definitionName,
		java.lang.String definitionVersion, java.lang.String startDateGT,
		java.lang.String startDateLT, java.lang.String endDateGT,
		java.lang.String endDateLT, java.lang.String userId,
		boolean hideEndedTasks, boolean retrieveUserInstances,
		boolean andOperator)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		Object paramObj0 = new LongWrapper(definitionId);

		Object paramObj1 = new LongWrapper(instanceId);

		Object paramObj2 = ClpSerializer.translateInput(definitionName);

		if (definitionName == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = ClpSerializer.translateInput(definitionVersion);

		if (definitionVersion == null) {
			paramObj3 = new NullWrapper("java.lang.String");
		}

		Object paramObj4 = ClpSerializer.translateInput(startDateGT);

		if (startDateGT == null) {
			paramObj4 = new NullWrapper("java.lang.String");
		}

		Object paramObj5 = ClpSerializer.translateInput(startDateLT);

		if (startDateLT == null) {
			paramObj5 = new NullWrapper("java.lang.String");
		}

		Object paramObj6 = ClpSerializer.translateInput(endDateGT);

		if (endDateGT == null) {
			paramObj6 = new NullWrapper("java.lang.String");
		}

		Object paramObj7 = ClpSerializer.translateInput(endDateLT);

		if (endDateLT == null) {
			paramObj7 = new NullWrapper("java.lang.String");
		}

		Object paramObj8 = ClpSerializer.translateInput(userId);

		if (userId == null) {
			paramObj8 = new NullWrapper("java.lang.String");
		}

		Object paramObj9 = new BooleanWrapper(hideEndedTasks);

		Object paramObj10 = new BooleanWrapper(retrieveUserInstances);

		Object paramObj11 = new BooleanWrapper(andOperator);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getInstancesCountXml",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4,
						paramObj5, paramObj6, paramObj7, paramObj8, paramObj9,
						paramObj10, paramObj11
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.jbi.WorkflowComponentException) {
				throw (com.liferay.portal.kernel.jbi.WorkflowComponentException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	public java.lang.String getInstancesXml(long definitionId, long instanceId,
		java.lang.String definitionName, java.lang.String definitionVersion,
		java.lang.String startDateGT, java.lang.String startDateLT,
		java.lang.String endDateGT, java.lang.String endDateLT,
		java.lang.String userId, boolean hideEndedTasks,
		boolean retrieveUserInstances, boolean andOperator, int start, int end)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		Object paramObj0 = new LongWrapper(definitionId);

		Object paramObj1 = new LongWrapper(instanceId);

		Object paramObj2 = ClpSerializer.translateInput(definitionName);

		if (definitionName == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = ClpSerializer.translateInput(definitionVersion);

		if (definitionVersion == null) {
			paramObj3 = new NullWrapper("java.lang.String");
		}

		Object paramObj4 = ClpSerializer.translateInput(startDateGT);

		if (startDateGT == null) {
			paramObj4 = new NullWrapper("java.lang.String");
		}

		Object paramObj5 = ClpSerializer.translateInput(startDateLT);

		if (startDateLT == null) {
			paramObj5 = new NullWrapper("java.lang.String");
		}

		Object paramObj6 = ClpSerializer.translateInput(endDateGT);

		if (endDateGT == null) {
			paramObj6 = new NullWrapper("java.lang.String");
		}

		Object paramObj7 = ClpSerializer.translateInput(endDateLT);

		if (endDateLT == null) {
			paramObj7 = new NullWrapper("java.lang.String");
		}

		Object paramObj8 = ClpSerializer.translateInput(userId);

		if (userId == null) {
			paramObj8 = new NullWrapper("java.lang.String");
		}

		Object paramObj9 = new BooleanWrapper(hideEndedTasks);

		Object paramObj10 = new BooleanWrapper(retrieveUserInstances);

		Object paramObj11 = new BooleanWrapper(andOperator);

		Object paramObj12 = new IntegerWrapper(start);

		Object paramObj13 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getInstancesXml",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4,
						paramObj5, paramObj6, paramObj7, paramObj8, paramObj9,
						paramObj10, paramObj11, paramObj12, paramObj13
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.jbi.WorkflowComponentException) {
				throw (com.liferay.portal.kernel.jbi.WorkflowComponentException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	public java.lang.String getTaskFormElementsXml(long taskId)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		Object paramObj0 = new LongWrapper(taskId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getTaskFormElementsXml",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.jbi.WorkflowComponentException) {
				throw (com.liferay.portal.kernel.jbi.WorkflowComponentException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	public com.sun.saw.vo.OutputVO getTasks(
		com.sun.saw.vo.FilterTaskVO filterTaskVO)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		Object paramObj0 = ClpSerializer.translateInput(filterTaskVO);

		if (filterTaskVO == null) {
			paramObj0 = new NullWrapper("com.sun.saw.vo.FilterTaskVO");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getTasks",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.jbi.WorkflowComponentException) {
				throw (com.liferay.portal.kernel.jbi.WorkflowComponentException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.sun.saw.vo.OutputVO)ClpSerializer.translateOutput(returnObj);
	}

	public java.lang.String getTaskTransitionsXml(long taskId)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		Object paramObj0 = new LongWrapper(taskId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getTaskTransitionsXml",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.jbi.WorkflowComponentException) {
				throw (com.liferay.portal.kernel.jbi.WorkflowComponentException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	public java.lang.String getTaskXml(long taskId, java.lang.String userId)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		Object paramObj0 = new LongWrapper(taskId);

		Object paramObj1 = ClpSerializer.translateInput(userId);

		if (userId == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getTaskXml",
					new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.jbi.WorkflowComponentException) {
				throw (com.liferay.portal.kernel.jbi.WorkflowComponentException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	public java.lang.String getUserTasksCountXml(long instanceId,
		java.lang.String taskName, java.lang.String definitionName,
		java.lang.String assignedTo, java.lang.String userId,
		java.lang.String createDateGT, java.lang.String createDateLT,
		java.lang.String startDateGT, java.lang.String startDateLT,
		java.lang.String endDateGT, java.lang.String endDateLT,
		boolean hideEndedTasks, boolean andOperator)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		Object paramObj0 = new LongWrapper(instanceId);

		Object paramObj1 = ClpSerializer.translateInput(taskName);

		if (taskName == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object paramObj2 = ClpSerializer.translateInput(definitionName);

		if (definitionName == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = ClpSerializer.translateInput(assignedTo);

		if (assignedTo == null) {
			paramObj3 = new NullWrapper("java.lang.String");
		}

		Object paramObj4 = ClpSerializer.translateInput(userId);

		if (userId == null) {
			paramObj4 = new NullWrapper("java.lang.String");
		}

		Object paramObj5 = ClpSerializer.translateInput(createDateGT);

		if (createDateGT == null) {
			paramObj5 = new NullWrapper("java.lang.String");
		}

		Object paramObj6 = ClpSerializer.translateInput(createDateLT);

		if (createDateLT == null) {
			paramObj6 = new NullWrapper("java.lang.String");
		}

		Object paramObj7 = ClpSerializer.translateInput(startDateGT);

		if (startDateGT == null) {
			paramObj7 = new NullWrapper("java.lang.String");
		}

		Object paramObj8 = ClpSerializer.translateInput(startDateLT);

		if (startDateLT == null) {
			paramObj8 = new NullWrapper("java.lang.String");
		}

		Object paramObj9 = ClpSerializer.translateInput(endDateGT);

		if (endDateGT == null) {
			paramObj9 = new NullWrapper("java.lang.String");
		}

		Object paramObj10 = ClpSerializer.translateInput(endDateLT);

		if (endDateLT == null) {
			paramObj10 = new NullWrapper("java.lang.String");
		}

		Object paramObj11 = new BooleanWrapper(hideEndedTasks);

		Object paramObj12 = new BooleanWrapper(andOperator);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getUserTasksCountXml",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4,
						paramObj5, paramObj6, paramObj7, paramObj8, paramObj9,
						paramObj10, paramObj11, paramObj12
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.jbi.WorkflowComponentException) {
				throw (com.liferay.portal.kernel.jbi.WorkflowComponentException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	public java.lang.String getUserTasksXml(long instanceId,
		java.lang.String taskName, java.lang.String definitionName,
		java.lang.String assignedTo, java.lang.String createDateGT,
		java.lang.String createDateLT, java.lang.String startDateGT,
		java.lang.String startDateLT, java.lang.String endDateGT,
		java.lang.String endDateLT, java.lang.String userId,
		boolean hideEndedTasks, boolean andOperator, int begin, int end)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		Object paramObj0 = new LongWrapper(instanceId);

		Object paramObj1 = ClpSerializer.translateInput(taskName);

		if (taskName == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object paramObj2 = ClpSerializer.translateInput(definitionName);

		if (definitionName == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = ClpSerializer.translateInput(assignedTo);

		if (assignedTo == null) {
			paramObj3 = new NullWrapper("java.lang.String");
		}

		Object paramObj4 = ClpSerializer.translateInput(createDateGT);

		if (createDateGT == null) {
			paramObj4 = new NullWrapper("java.lang.String");
		}

		Object paramObj5 = ClpSerializer.translateInput(createDateLT);

		if (createDateLT == null) {
			paramObj5 = new NullWrapper("java.lang.String");
		}

		Object paramObj6 = ClpSerializer.translateInput(startDateGT);

		if (startDateGT == null) {
			paramObj6 = new NullWrapper("java.lang.String");
		}

		Object paramObj7 = ClpSerializer.translateInput(startDateLT);

		if (startDateLT == null) {
			paramObj7 = new NullWrapper("java.lang.String");
		}

		Object paramObj8 = ClpSerializer.translateInput(endDateGT);

		if (endDateGT == null) {
			paramObj8 = new NullWrapper("java.lang.String");
		}

		Object paramObj9 = ClpSerializer.translateInput(endDateLT);

		if (endDateLT == null) {
			paramObj9 = new NullWrapper("java.lang.String");
		}

		Object paramObj10 = ClpSerializer.translateInput(userId);

		if (userId == null) {
			paramObj10 = new NullWrapper("java.lang.String");
		}

		Object paramObj11 = new BooleanWrapper(hideEndedTasks);

		Object paramObj12 = new BooleanWrapper(andOperator);

		Object paramObj13 = new IntegerWrapper(begin);

		Object paramObj14 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getUserTasksXml",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4,
						paramObj5, paramObj6, paramObj7, paramObj8, paramObj9,
						paramObj10, paramObj11, paramObj12, paramObj13,
						paramObj14
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.jbi.WorkflowComponentException) {
				throw (com.liferay.portal.kernel.jbi.WorkflowComponentException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	public com.sun.saw.vo.OutputVO saveTasks(
		com.sun.saw.vo.SaveTaskVO saveTaskVO)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		Object paramObj0 = ClpSerializer.translateInput(saveTaskVO);

		if (saveTaskVO == null) {
			paramObj0 = new NullWrapper("com.sun.saw.vo.SaveTaskVO");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("saveTasks",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.jbi.WorkflowComponentException) {
				throw (com.liferay.portal.kernel.jbi.WorkflowComponentException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.sun.saw.vo.OutputVO)ClpSerializer.translateOutput(returnObj);
	}

	public void signalInstance(long instanceId)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		Object paramObj0 = new LongWrapper(instanceId);

		try {
			_classLoaderProxy.invoke("signalInstance",
				new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.jbi.WorkflowComponentException) {
				throw (com.liferay.portal.kernel.jbi.WorkflowComponentException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public void signalToken(long instanceId, long tokenId)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		Object paramObj0 = new LongWrapper(instanceId);

		Object paramObj1 = new LongWrapper(tokenId);

		try {
			_classLoaderProxy.invoke("signalToken",
				new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.jbi.WorkflowComponentException) {
				throw (com.liferay.portal.kernel.jbi.WorkflowComponentException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public java.lang.String startWorkflow(long definitionId,
		java.lang.String userId)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		Object paramObj0 = new LongWrapper(definitionId);

		Object paramObj1 = ClpSerializer.translateInput(userId);

		if (userId == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("startWorkflow",
					new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.jbi.WorkflowComponentException) {
				throw (com.liferay.portal.kernel.jbi.WorkflowComponentException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.Map updateTask(long taskId, java.lang.String transition,
		java.lang.String userId, java.util.Map parameterMap)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		Object paramObj0 = new LongWrapper(taskId);

		Object paramObj1 = ClpSerializer.translateInput(transition);

		if (transition == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object paramObj2 = ClpSerializer.translateInput(userId);

		if (userId == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = ClpSerializer.translateInput(parameterMap);

		if (parameterMap == null) {
			paramObj3 = new NullWrapper("java.util.Map");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateTask",
					new Object[] { paramObj0, paramObj1, paramObj2, paramObj3 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.jbi.WorkflowComponentException) {
				throw (com.liferay.portal.kernel.jbi.WorkflowComponentException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.Map)ClpSerializer.translateOutput(returnObj);
	}

	public java.lang.String updateTaskXml(long taskId,
		java.lang.String transition, java.lang.String userId,
		java.util.Map parameterMap)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		Object paramObj0 = new LongWrapper(taskId);

		Object paramObj1 = ClpSerializer.translateInput(transition);

		if (transition == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object paramObj2 = ClpSerializer.translateInput(userId);

		if (userId == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = ClpSerializer.translateInput(parameterMap);

		if (parameterMap == null) {
			paramObj3 = new NullWrapper("java.util.Map");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateTaskXml",
					new Object[] { paramObj0, paramObj1, paramObj2, paramObj3 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.jbi.WorkflowComponentException) {
				throw (com.liferay.portal.kernel.jbi.WorkflowComponentException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	private ClassLoaderProxy _classLoaderProxy;
}