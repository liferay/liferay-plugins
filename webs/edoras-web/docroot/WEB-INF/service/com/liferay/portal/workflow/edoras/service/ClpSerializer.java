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

package com.liferay.portal.workflow.edoras.service;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.workflow.edoras.model.WorkflowDefinitionClp;
import com.liferay.portal.workflow.edoras.model.WorkflowInstanceClp;
import com.liferay.portal.workflow.edoras.model.WorkflowJobClp;
import com.liferay.portal.workflow.edoras.model.WorkflowLogClp;
import com.liferay.portal.workflow.edoras.model.WorkflowTaskClp;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="ClpSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class ClpSerializer {
	public static final String SERVLET_CONTEXT_NAME = "edoras-web";

	public static void setClassLoader(ClassLoader classLoader) {
		_classLoader = classLoader;
	}

	public static Object translateInput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(WorkflowDefinitionClp.class.getName())) {
			WorkflowDefinitionClp oldCplModel = (WorkflowDefinitionClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.portal.workflow.edoras.model.impl.WorkflowDefinitionImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setWorkflowDefinitionId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getWorkflowDefinitionId());

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setCompanyId",
							new Class[] { Long.TYPE });

					Long value1 = new Long(oldCplModel.getCompanyId());

					method1.invoke(newModel, value1);

					Method method2 = newModelClass.getMethod("setUserId",
							new Class[] { Long.TYPE });

					Long value2 = new Long(oldCplModel.getUserId());

					method2.invoke(newModel, value2);

					Method method3 = newModelClass.getMethod("setUserName",
							new Class[] { String.class });

					String value3 = oldCplModel.getUserName();

					method3.invoke(newModel, value3);

					Method method4 = newModelClass.getMethod("setCreateDate",
							new Class[] { Date.class });

					Date value4 = oldCplModel.getCreateDate();

					method4.invoke(newModel, value4);

					Method method5 = newModelClass.getMethod("setModifiedDate",
							new Class[] { Date.class });

					Date value5 = oldCplModel.getModifiedDate();

					method5.invoke(newModel, value5);

					Method method6 = newModelClass.getMethod("setName",
							new Class[] { String.class });

					String value6 = oldCplModel.getName();

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setVersion",
							new Class[] { Integer.TYPE });

					Integer value7 = new Integer(oldCplModel.getVersion());

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setDesignerVersion",
							new Class[] { String.class });

					String value8 = oldCplModel.getDesignerVersion();

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setModelXml",
							new Class[] { String.class });

					String value9 = oldCplModel.getModelXml();

					method9.invoke(newModel, value9);

					Method method10 = newModelClass.getMethod("setGraphicalXml",
							new Class[] { String.class });

					String value10 = oldCplModel.getGraphicalXml();

					method10.invoke(newModel, value10);

					Method method11 = newModelClass.getMethod("setPersistent",
							new Class[] { Boolean.TYPE });

					Boolean value11 = new Boolean(oldCplModel.getPersistent());

					method11.invoke(newModel, value11);

					return newModel;
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
			finally {
				Thread.currentThread().setContextClassLoader(contextClassLoader);
			}
		}

		if (oldModelClassName.equals(WorkflowInstanceClp.class.getName())) {
			WorkflowInstanceClp oldCplModel = (WorkflowInstanceClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.portal.workflow.edoras.model.impl.WorkflowInstanceImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setWorkflowInstanceId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getWorkflowInstanceId());

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setCompanyId",
							new Class[] { Long.TYPE });

					Long value1 = new Long(oldCplModel.getCompanyId());

					method1.invoke(newModel, value1);

					Method method2 = newModelClass.getMethod("setUserId",
							new Class[] { Long.TYPE });

					Long value2 = new Long(oldCplModel.getUserId());

					method2.invoke(newModel, value2);

					Method method3 = newModelClass.getMethod("setUserName",
							new Class[] { String.class });

					String value3 = oldCplModel.getUserName();

					method3.invoke(newModel, value3);

					Method method4 = newModelClass.getMethod("setCreateDate",
							new Class[] { Date.class });

					Date value4 = oldCplModel.getCreateDate();

					method4.invoke(newModel, value4);

					Method method5 = newModelClass.getMethod("setModifiedDate",
							new Class[] { Date.class });

					Date value5 = oldCplModel.getModifiedDate();

					method5.invoke(newModel, value5);

					Method method6 = newModelClass.getMethod("setSetupId",
							new Class[] { String.class });

					String value6 = oldCplModel.getSetupId();

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setFriendlyId",
							new Class[] { String.class });

					String value7 = oldCplModel.getFriendlyId();

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setWorkflowDefinitionId",
							new Class[] { Long.TYPE });

					Long value8 = new Long(oldCplModel.getWorkflowDefinitionId());

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setParentWorkflowInstanceId",
							new Class[] { Long.TYPE });

					Long value9 = new Long(oldCplModel.getParentWorkflowInstanceId());

					method9.invoke(newModel, value9);

					Method method10 = newModelClass.getMethod("setRelationClassName",
							new Class[] { String.class });

					String value10 = oldCplModel.getRelationClassName();

					method10.invoke(newModel, value10);

					Method method11 = newModelClass.getMethod("setRelationClassPK",
							new Class[] { Long.TYPE });

					Long value11 = new Long(oldCplModel.getRelationClassPK());

					method11.invoke(newModel, value11);

					Method method12 = newModelClass.getMethod("setAttributes",
							new Class[] { String.class });

					String value12 = oldCplModel.getAttributes();

					method12.invoke(newModel, value12);

					Method method13 = newModelClass.getMethod("setNestedWorkflowDefinitionIds",
							new Class[] { String.class });

					String value13 = oldCplModel.getNestedWorkflowDefinitionIds();

					method13.invoke(newModel, value13);

					Method method14 = newModelClass.getMethod("setNestedWorkflowDefinitionVersions",
							new Class[] { String.class });

					String value14 = oldCplModel.getNestedWorkflowDefinitionVersions();

					method14.invoke(newModel, value14);

					Method method15 = newModelClass.getMethod("setNestedRelatedElements",
							new Class[] { String.class });

					String value15 = oldCplModel.getNestedRelatedElements();

					method15.invoke(newModel, value15);

					Method method16 = newModelClass.getMethod("setCurrentElementName",
							new Class[] { String.class });

					String value16 = oldCplModel.getCurrentElementName();

					method16.invoke(newModel, value16);

					Method method17 = newModelClass.getMethod("setRelatedElementName",
							new Class[] { String.class });

					String value17 = oldCplModel.getRelatedElementName();

					method17.invoke(newModel, value17);

					Method method18 = newModelClass.getMethod("setFinished",
							new Class[] { Boolean.TYPE });

					Boolean value18 = new Boolean(oldCplModel.getFinished());

					method18.invoke(newModel, value18);

					Method method19 = newModelClass.getMethod("setFinishedDated",
							new Class[] { Date.class });

					Date value19 = oldCplModel.getFinishedDated();

					method19.invoke(newModel, value19);

					Method method20 = newModelClass.getMethod("setActive",
							new Class[] { Boolean.TYPE });

					Boolean value20 = new Boolean(oldCplModel.getActive());

					method20.invoke(newModel, value20);

					return newModel;
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
			finally {
				Thread.currentThread().setContextClassLoader(contextClassLoader);
			}
		}

		if (oldModelClassName.equals(WorkflowJobClp.class.getName())) {
			WorkflowJobClp oldCplModel = (WorkflowJobClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.portal.workflow.edoras.model.impl.WorkflowJobImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setWorkflowJobId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getWorkflowJobId());

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setCompanyId",
							new Class[] { Long.TYPE });

					Long value1 = new Long(oldCplModel.getCompanyId());

					method1.invoke(newModel, value1);

					Method method2 = newModelClass.getMethod("setCreateDate",
							new Class[] { Date.class });

					Date value2 = oldCplModel.getCreateDate();

					method2.invoke(newModel, value2);

					Method method3 = newModelClass.getMethod("setSetupId",
							new Class[] { String.class });

					String value3 = oldCplModel.getSetupId();

					method3.invoke(newModel, value3);

					Method method4 = newModelClass.getMethod("setWorkflowDefinitionId",
							new Class[] { Long.TYPE });

					Long value4 = new Long(oldCplModel.getWorkflowDefinitionId());

					method4.invoke(newModel, value4);

					Method method5 = newModelClass.getMethod("setWorkflowInstanceId",
							new Class[] { Long.TYPE });

					Long value5 = new Long(oldCplModel.getWorkflowInstanceId());

					method5.invoke(newModel, value5);

					Method method6 = newModelClass.getMethod("setElementName",
							new Class[] { String.class });

					String value6 = oldCplModel.getElementName();

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setCause",
							new Class[] { String.class });

					String value7 = oldCplModel.getCause();

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setDueDate",
							new Class[] { Date.class });

					Date value8 = oldCplModel.getDueDate();

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setNotBeforeDate",
							new Class[] { Date.class });

					Date value9 = oldCplModel.getNotBeforeDate();

					method9.invoke(newModel, value9);

					Method method10 = newModelClass.getMethod("setExceptionCount",
							new Class[] { Integer.TYPE });

					Integer value10 = new Integer(oldCplModel.getExceptionCount());

					method10.invoke(newModel, value10);

					return newModel;
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
			finally {
				Thread.currentThread().setContextClassLoader(contextClassLoader);
			}
		}

		if (oldModelClassName.equals(WorkflowLogClp.class.getName())) {
			WorkflowLogClp oldCplModel = (WorkflowLogClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.portal.workflow.edoras.model.impl.WorkflowLogImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setWorkflowLogId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getWorkflowLogId());

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setCompanyId",
							new Class[] { Long.TYPE });

					Long value1 = new Long(oldCplModel.getCompanyId());

					method1.invoke(newModel, value1);

					Method method2 = newModelClass.getMethod("setUserId",
							new Class[] { Long.TYPE });

					Long value2 = new Long(oldCplModel.getUserId());

					method2.invoke(newModel, value2);

					Method method3 = newModelClass.getMethod("setUserName",
							new Class[] { String.class });

					String value3 = oldCplModel.getUserName();

					method3.invoke(newModel, value3);

					Method method4 = newModelClass.getMethod("setCreateDate",
							new Class[] { Date.class });

					Date value4 = oldCplModel.getCreateDate();

					method4.invoke(newModel, value4);

					Method method5 = newModelClass.getMethod("setWorkflowDefinitionId",
							new Class[] { Long.TYPE });

					Long value5 = new Long(oldCplModel.getWorkflowDefinitionId());

					method5.invoke(newModel, value5);

					Method method6 = newModelClass.getMethod("setWorkflowInstanceId",
							new Class[] { Long.TYPE });

					Long value6 = new Long(oldCplModel.getWorkflowInstanceId());

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setWorkflowTaskId",
							new Class[] { Long.TYPE });

					Long value7 = new Long(oldCplModel.getWorkflowTaskId());

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setLogEntityType",
							new Class[] { Integer.TYPE });

					Integer value8 = new Integer(oldCplModel.getLogEntityType());

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setDescription",
							new Class[] { String.class });

					String value9 = oldCplModel.getDescription();

					method9.invoke(newModel, value9);

					Method method10 = newModelClass.getMethod("setActivityName",
							new Class[] { String.class });

					String value10 = oldCplModel.getActivityName();

					method10.invoke(newModel, value10);

					Method method11 = newModelClass.getMethod("setOldState",
							new Class[] { String.class });

					String value11 = oldCplModel.getOldState();

					method11.invoke(newModel, value11);

					Method method12 = newModelClass.getMethod("setNewState",
							new Class[] { String.class });

					String value12 = oldCplModel.getNewState();

					method12.invoke(newModel, value12);

					Method method13 = newModelClass.getMethod("setType",
							new Class[] { Integer.TYPE });

					Integer value13 = new Integer(oldCplModel.getType());

					method13.invoke(newModel, value13);

					Method method14 = newModelClass.getMethod("setComment",
							new Class[] { String.class });

					String value14 = oldCplModel.getComment();

					method14.invoke(newModel, value14);

					return newModel;
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
			finally {
				Thread.currentThread().setContextClassLoader(contextClassLoader);
			}
		}

		if (oldModelClassName.equals(WorkflowTaskClp.class.getName())) {
			WorkflowTaskClp oldCplModel = (WorkflowTaskClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.portal.workflow.edoras.model.impl.WorkflowTaskImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setWorkflowTaskId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getWorkflowTaskId());

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setCompanyId",
							new Class[] { Long.TYPE });

					Long value1 = new Long(oldCplModel.getCompanyId());

					method1.invoke(newModel, value1);

					Method method2 = newModelClass.getMethod("setCreateDate",
							new Class[] { Date.class });

					Date value2 = oldCplModel.getCreateDate();

					method2.invoke(newModel, value2);

					Method method3 = newModelClass.getMethod("setFriendlyId",
							new Class[] { String.class });

					String value3 = oldCplModel.getFriendlyId();

					method3.invoke(newModel, value3);

					Method method4 = newModelClass.getMethod("setWorkflowDefinitionId",
							new Class[] { Long.TYPE });

					Long value4 = new Long(oldCplModel.getWorkflowDefinitionId());

					method4.invoke(newModel, value4);

					Method method5 = newModelClass.getMethod("setWorkflowInstanceId",
							new Class[] { Long.TYPE });

					Long value5 = new Long(oldCplModel.getWorkflowInstanceId());

					method5.invoke(newModel, value5);

					Method method6 = newModelClass.getMethod("setMetaName",
							new Class[] { String.class });

					String value6 = oldCplModel.getMetaName();

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setRelation",
							new Class[] { String.class });

					String value7 = oldCplModel.getRelation();

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setDueDate",
							new Class[] { Date.class });

					Date value8 = oldCplModel.getDueDate();

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setCompletionDate",
							new Class[] { Date.class });

					Date value9 = oldCplModel.getCompletionDate();

					method9.invoke(newModel, value9);

					Method method10 = newModelClass.getMethod("setCompleted",
							new Class[] { Boolean.TYPE });

					Boolean value10 = new Boolean(oldCplModel.getCompleted());

					method10.invoke(newModel, value10);

					Method method11 = newModelClass.getMethod("setState",
							new Class[] { Integer.TYPE });

					Integer value11 = new Integer(oldCplModel.getState());

					method11.invoke(newModel, value11);

					Method method12 = newModelClass.getMethod("setPriority",
							new Class[] { Integer.TYPE });

					Integer value12 = new Integer(oldCplModel.getPriority());

					method12.invoke(newModel, value12);

					Method method13 = newModelClass.getMethod("setAssigneeUserId",
							new Class[] { Long.TYPE });

					Long value13 = new Long(oldCplModel.getAssigneeUserId());

					method13.invoke(newModel, value13);

					Method method14 = newModelClass.getMethod("setAssigneeUserName",
							new Class[] { String.class });

					String value14 = oldCplModel.getAssigneeUserName();

					method14.invoke(newModel, value14);

					Method method15 = newModelClass.getMethod("setAssignedGroupName",
							new Class[] { String.class });

					String value15 = oldCplModel.getAssignedGroupName();

					method15.invoke(newModel, value15);

					Method method16 = newModelClass.getMethod("setRoleId",
							new Class[] { Long.TYPE });

					Long value16 = new Long(oldCplModel.getRoleId());

					method16.invoke(newModel, value16);

					return newModel;
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
			finally {
				Thread.currentThread().setContextClassLoader(contextClassLoader);
			}
		}

		return oldModel;
	}

	public static Object translateInput(List<Object> oldList) {
		List<Object> newList = new ArrayList<Object>(oldList.size());

		for (int i = 0; i < oldList.size(); i++) {
			Object curObj = oldList.get(i);

			newList.add(translateInput(curObj));
		}

		return newList;
	}

	public static Object translateInput(Object obj) {
		if (obj instanceof BaseModel<?>) {
			return translateInput((BaseModel<?>)obj);
		}
		else if (obj instanceof List) {
			return translateInput((List<Object>)obj);
		}
		else {
			return obj;
		}
	}

	public static Object translateOutput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(
					"com.liferay.portal.workflow.edoras.model.impl.WorkflowDefinitionImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					WorkflowDefinitionClp newModel = new WorkflowDefinitionClp();

					Method method0 = oldModelClass.getMethod(
							"getWorkflowDefinitionId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setWorkflowDefinitionId(value0.longValue());

					Method method1 = oldModelClass.getMethod("getCompanyId");

					Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

					newModel.setCompanyId(value1.longValue());

					Method method2 = oldModelClass.getMethod("getUserId");

					Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

					newModel.setUserId(value2.longValue());

					Method method3 = oldModelClass.getMethod("getUserName");

					String value3 = (String)method3.invoke(oldModel,
							(Object[])null);

					newModel.setUserName(value3);

					Method method4 = oldModelClass.getMethod("getCreateDate");

					Date value4 = (Date)method4.invoke(oldModel, (Object[])null);

					newModel.setCreateDate(value4);

					Method method5 = oldModelClass.getMethod("getModifiedDate");

					Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

					newModel.setModifiedDate(value5);

					Method method6 = oldModelClass.getMethod("getName");

					String value6 = (String)method6.invoke(oldModel,
							(Object[])null);

					newModel.setName(value6);

					Method method7 = oldModelClass.getMethod("getVersion");

					Integer value7 = (Integer)method7.invoke(oldModel,
							(Object[])null);

					newModel.setVersion(value7.intValue());

					Method method8 = oldModelClass.getMethod(
							"getDesignerVersion");

					String value8 = (String)method8.invoke(oldModel,
							(Object[])null);

					newModel.setDesignerVersion(value8);

					Method method9 = oldModelClass.getMethod("getModelXml");

					String value9 = (String)method9.invoke(oldModel,
							(Object[])null);

					newModel.setModelXml(value9);

					Method method10 = oldModelClass.getMethod("getGraphicalXml");

					String value10 = (String)method10.invoke(oldModel,
							(Object[])null);

					newModel.setGraphicalXml(value10);

					Method method11 = oldModelClass.getMethod("getPersistent");

					Boolean value11 = (Boolean)method11.invoke(oldModel,
							(Object[])null);

					newModel.setPersistent(value11.booleanValue());

					return newModel;
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
			finally {
				Thread.currentThread().setContextClassLoader(contextClassLoader);
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.portal.workflow.edoras.model.impl.WorkflowInstanceImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					WorkflowInstanceClp newModel = new WorkflowInstanceClp();

					Method method0 = oldModelClass.getMethod(
							"getWorkflowInstanceId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setWorkflowInstanceId(value0.longValue());

					Method method1 = oldModelClass.getMethod("getCompanyId");

					Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

					newModel.setCompanyId(value1.longValue());

					Method method2 = oldModelClass.getMethod("getUserId");

					Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

					newModel.setUserId(value2.longValue());

					Method method3 = oldModelClass.getMethod("getUserName");

					String value3 = (String)method3.invoke(oldModel,
							(Object[])null);

					newModel.setUserName(value3);

					Method method4 = oldModelClass.getMethod("getCreateDate");

					Date value4 = (Date)method4.invoke(oldModel, (Object[])null);

					newModel.setCreateDate(value4);

					Method method5 = oldModelClass.getMethod("getModifiedDate");

					Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

					newModel.setModifiedDate(value5);

					Method method6 = oldModelClass.getMethod("getSetupId");

					String value6 = (String)method6.invoke(oldModel,
							(Object[])null);

					newModel.setSetupId(value6);

					Method method7 = oldModelClass.getMethod("getFriendlyId");

					String value7 = (String)method7.invoke(oldModel,
							(Object[])null);

					newModel.setFriendlyId(value7);

					Method method8 = oldModelClass.getMethod(
							"getWorkflowDefinitionId");

					Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

					newModel.setWorkflowDefinitionId(value8.longValue());

					Method method9 = oldModelClass.getMethod(
							"getParentWorkflowInstanceId");

					Long value9 = (Long)method9.invoke(oldModel, (Object[])null);

					newModel.setParentWorkflowInstanceId(value9.longValue());

					Method method10 = oldModelClass.getMethod(
							"getRelationClassName");

					String value10 = (String)method10.invoke(oldModel,
							(Object[])null);

					newModel.setRelationClassName(value10);

					Method method11 = oldModelClass.getMethod(
							"getRelationClassPK");

					Long value11 = (Long)method11.invoke(oldModel,
							(Object[])null);

					newModel.setRelationClassPK(value11.longValue());

					Method method12 = oldModelClass.getMethod("getAttributes");

					String value12 = (String)method12.invoke(oldModel,
							(Object[])null);

					newModel.setAttributes(value12);

					Method method13 = oldModelClass.getMethod(
							"getNestedWorkflowDefinitionIds");

					String value13 = (String)method13.invoke(oldModel,
							(Object[])null);

					newModel.setNestedWorkflowDefinitionIds(value13);

					Method method14 = oldModelClass.getMethod(
							"getNestedWorkflowDefinitionVersions");

					String value14 = (String)method14.invoke(oldModel,
							(Object[])null);

					newModel.setNestedWorkflowDefinitionVersions(value14);

					Method method15 = oldModelClass.getMethod(
							"getNestedRelatedElements");

					String value15 = (String)method15.invoke(oldModel,
							(Object[])null);

					newModel.setNestedRelatedElements(value15);

					Method method16 = oldModelClass.getMethod(
							"getCurrentElementName");

					String value16 = (String)method16.invoke(oldModel,
							(Object[])null);

					newModel.setCurrentElementName(value16);

					Method method17 = oldModelClass.getMethod(
							"getRelatedElementName");

					String value17 = (String)method17.invoke(oldModel,
							(Object[])null);

					newModel.setRelatedElementName(value17);

					Method method18 = oldModelClass.getMethod("getFinished");

					Boolean value18 = (Boolean)method18.invoke(oldModel,
							(Object[])null);

					newModel.setFinished(value18.booleanValue());

					Method method19 = oldModelClass.getMethod(
							"getFinishedDated");

					Date value19 = (Date)method19.invoke(oldModel,
							(Object[])null);

					newModel.setFinishedDated(value19);

					Method method20 = oldModelClass.getMethod("getActive");

					Boolean value20 = (Boolean)method20.invoke(oldModel,
							(Object[])null);

					newModel.setActive(value20.booleanValue());

					return newModel;
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
			finally {
				Thread.currentThread().setContextClassLoader(contextClassLoader);
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.portal.workflow.edoras.model.impl.WorkflowJobImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					WorkflowJobClp newModel = new WorkflowJobClp();

					Method method0 = oldModelClass.getMethod("getWorkflowJobId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setWorkflowJobId(value0.longValue());

					Method method1 = oldModelClass.getMethod("getCompanyId");

					Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

					newModel.setCompanyId(value1.longValue());

					Method method2 = oldModelClass.getMethod("getCreateDate");

					Date value2 = (Date)method2.invoke(oldModel, (Object[])null);

					newModel.setCreateDate(value2);

					Method method3 = oldModelClass.getMethod("getSetupId");

					String value3 = (String)method3.invoke(oldModel,
							(Object[])null);

					newModel.setSetupId(value3);

					Method method4 = oldModelClass.getMethod(
							"getWorkflowDefinitionId");

					Long value4 = (Long)method4.invoke(oldModel, (Object[])null);

					newModel.setWorkflowDefinitionId(value4.longValue());

					Method method5 = oldModelClass.getMethod(
							"getWorkflowInstanceId");

					Long value5 = (Long)method5.invoke(oldModel, (Object[])null);

					newModel.setWorkflowInstanceId(value5.longValue());

					Method method6 = oldModelClass.getMethod("getElementName");

					String value6 = (String)method6.invoke(oldModel,
							(Object[])null);

					newModel.setElementName(value6);

					Method method7 = oldModelClass.getMethod("getCause");

					String value7 = (String)method7.invoke(oldModel,
							(Object[])null);

					newModel.setCause(value7);

					Method method8 = oldModelClass.getMethod("getDueDate");

					Date value8 = (Date)method8.invoke(oldModel, (Object[])null);

					newModel.setDueDate(value8);

					Method method9 = oldModelClass.getMethod("getNotBeforeDate");

					Date value9 = (Date)method9.invoke(oldModel, (Object[])null);

					newModel.setNotBeforeDate(value9);

					Method method10 = oldModelClass.getMethod(
							"getExceptionCount");

					Integer value10 = (Integer)method10.invoke(oldModel,
							(Object[])null);

					newModel.setExceptionCount(value10.intValue());

					return newModel;
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
			finally {
				Thread.currentThread().setContextClassLoader(contextClassLoader);
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.portal.workflow.edoras.model.impl.WorkflowLogImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					WorkflowLogClp newModel = new WorkflowLogClp();

					Method method0 = oldModelClass.getMethod("getWorkflowLogId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setWorkflowLogId(value0.longValue());

					Method method1 = oldModelClass.getMethod("getCompanyId");

					Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

					newModel.setCompanyId(value1.longValue());

					Method method2 = oldModelClass.getMethod("getUserId");

					Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

					newModel.setUserId(value2.longValue());

					Method method3 = oldModelClass.getMethod("getUserName");

					String value3 = (String)method3.invoke(oldModel,
							(Object[])null);

					newModel.setUserName(value3);

					Method method4 = oldModelClass.getMethod("getCreateDate");

					Date value4 = (Date)method4.invoke(oldModel, (Object[])null);

					newModel.setCreateDate(value4);

					Method method5 = oldModelClass.getMethod(
							"getWorkflowDefinitionId");

					Long value5 = (Long)method5.invoke(oldModel, (Object[])null);

					newModel.setWorkflowDefinitionId(value5.longValue());

					Method method6 = oldModelClass.getMethod(
							"getWorkflowInstanceId");

					Long value6 = (Long)method6.invoke(oldModel, (Object[])null);

					newModel.setWorkflowInstanceId(value6.longValue());

					Method method7 = oldModelClass.getMethod(
							"getWorkflowTaskId");

					Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

					newModel.setWorkflowTaskId(value7.longValue());

					Method method8 = oldModelClass.getMethod("getLogEntityType");

					Integer value8 = (Integer)method8.invoke(oldModel,
							(Object[])null);

					newModel.setLogEntityType(value8.intValue());

					Method method9 = oldModelClass.getMethod("getDescription");

					String value9 = (String)method9.invoke(oldModel,
							(Object[])null);

					newModel.setDescription(value9);

					Method method10 = oldModelClass.getMethod("getActivityName");

					String value10 = (String)method10.invoke(oldModel,
							(Object[])null);

					newModel.setActivityName(value10);

					Method method11 = oldModelClass.getMethod("getOldState");

					String value11 = (String)method11.invoke(oldModel,
							(Object[])null);

					newModel.setOldState(value11);

					Method method12 = oldModelClass.getMethod("getNewState");

					String value12 = (String)method12.invoke(oldModel,
							(Object[])null);

					newModel.setNewState(value12);

					Method method13 = oldModelClass.getMethod("getType");

					Integer value13 = (Integer)method13.invoke(oldModel,
							(Object[])null);

					newModel.setType(value13.intValue());

					Method method14 = oldModelClass.getMethod("getComment");

					String value14 = (String)method14.invoke(oldModel,
							(Object[])null);

					newModel.setComment(value14);

					return newModel;
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
			finally {
				Thread.currentThread().setContextClassLoader(contextClassLoader);
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.portal.workflow.edoras.model.impl.WorkflowTaskImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					WorkflowTaskClp newModel = new WorkflowTaskClp();

					Method method0 = oldModelClass.getMethod(
							"getWorkflowTaskId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setWorkflowTaskId(value0.longValue());

					Method method1 = oldModelClass.getMethod("getCompanyId");

					Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

					newModel.setCompanyId(value1.longValue());

					Method method2 = oldModelClass.getMethod("getCreateDate");

					Date value2 = (Date)method2.invoke(oldModel, (Object[])null);

					newModel.setCreateDate(value2);

					Method method3 = oldModelClass.getMethod("getFriendlyId");

					String value3 = (String)method3.invoke(oldModel,
							(Object[])null);

					newModel.setFriendlyId(value3);

					Method method4 = oldModelClass.getMethod(
							"getWorkflowDefinitionId");

					Long value4 = (Long)method4.invoke(oldModel, (Object[])null);

					newModel.setWorkflowDefinitionId(value4.longValue());

					Method method5 = oldModelClass.getMethod(
							"getWorkflowInstanceId");

					Long value5 = (Long)method5.invoke(oldModel, (Object[])null);

					newModel.setWorkflowInstanceId(value5.longValue());

					Method method6 = oldModelClass.getMethod("getMetaName");

					String value6 = (String)method6.invoke(oldModel,
							(Object[])null);

					newModel.setMetaName(value6);

					Method method7 = oldModelClass.getMethod("getRelation");

					String value7 = (String)method7.invoke(oldModel,
							(Object[])null);

					newModel.setRelation(value7);

					Method method8 = oldModelClass.getMethod("getDueDate");

					Date value8 = (Date)method8.invoke(oldModel, (Object[])null);

					newModel.setDueDate(value8);

					Method method9 = oldModelClass.getMethod(
							"getCompletionDate");

					Date value9 = (Date)method9.invoke(oldModel, (Object[])null);

					newModel.setCompletionDate(value9);

					Method method10 = oldModelClass.getMethod("getCompleted");

					Boolean value10 = (Boolean)method10.invoke(oldModel,
							(Object[])null);

					newModel.setCompleted(value10.booleanValue());

					Method method11 = oldModelClass.getMethod("getState");

					Integer value11 = (Integer)method11.invoke(oldModel,
							(Object[])null);

					newModel.setState(value11.intValue());

					Method method12 = oldModelClass.getMethod("getPriority");

					Integer value12 = (Integer)method12.invoke(oldModel,
							(Object[])null);

					newModel.setPriority(value12.intValue());

					Method method13 = oldModelClass.getMethod(
							"getAssigneeUserId");

					Long value13 = (Long)method13.invoke(oldModel,
							(Object[])null);

					newModel.setAssigneeUserId(value13.longValue());

					Method method14 = oldModelClass.getMethod(
							"getAssigneeUserName");

					String value14 = (String)method14.invoke(oldModel,
							(Object[])null);

					newModel.setAssigneeUserName(value14);

					Method method15 = oldModelClass.getMethod(
							"getAssignedGroupName");

					String value15 = (String)method15.invoke(oldModel,
							(Object[])null);

					newModel.setAssignedGroupName(value15);

					Method method16 = oldModelClass.getMethod("getRoleId");

					Long value16 = (Long)method16.invoke(oldModel,
							(Object[])null);

					newModel.setRoleId(value16.longValue());

					return newModel;
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
			finally {
				Thread.currentThread().setContextClassLoader(contextClassLoader);
			}
		}

		return oldModel;
	}

	public static Object translateOutput(List<Object> oldList) {
		List<Object> newList = new ArrayList<Object>(oldList.size());

		for (int i = 0; i < oldList.size(); i++) {
			Object curObj = oldList.get(i);

			newList.add(translateOutput(curObj));
		}

		return newList;
	}

	public static Object translateOutput(Object obj) {
		if (obj instanceof BaseModel<?>) {
			return translateOutput((BaseModel<?>)obj);
		}
		else if (obj instanceof List) {
			return translateOutput((List<Object>)obj);
		}
		else {
			return obj;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ClpSerializer.class);
	private static ClassLoader _classLoader;
}