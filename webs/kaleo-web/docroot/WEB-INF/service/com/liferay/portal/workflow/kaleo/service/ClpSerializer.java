/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.kaleo.service;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.workflow.kaleo.model.KaleoActionClp;
import com.liferay.portal.workflow.kaleo.model.KaleoConditionClp;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinitionClp;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceClp;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceTokenClp;
import com.liferay.portal.workflow.kaleo.model.KaleoLogClp;
import com.liferay.portal.workflow.kaleo.model.KaleoNodeClp;
import com.liferay.portal.workflow.kaleo.model.KaleoNotificationClp;
import com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipientClp;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentClp;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstanceClp;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskClp;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceTokenClp;
import com.liferay.portal.workflow.kaleo.model.KaleoTimerClp;
import com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceTokenClp;
import com.liferay.portal.workflow.kaleo.model.KaleoTransitionClp;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class ClpSerializer {
	public static String getServletContextName() {
		if (Validator.isNotNull(_servletContextName)) {
			return _servletContextName;
		}

		synchronized (ClpSerializer.class) {
			if (Validator.isNotNull(_servletContextName)) {
				return _servletContextName;
			}

			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Class<?> portletPropsClass = classLoader.loadClass(
						"com.liferay.util.portlet.PortletProps");

				Method getMethod = portletPropsClass.getMethod("get",
						new Class<?>[] { String.class });

				String portletPropsServletContextName = (String)getMethod.invoke(null,
						"kaleo-web-deployment-context");

				if (Validator.isNotNull(portletPropsServletContextName)) {
					_servletContextName = portletPropsServletContextName;
				}
			}
			catch (Throwable t) {
				if (_log.isWarnEnabled()) {
					_log.warn("Unable to locate deployment context from portlet properties",
						t);
				}
			}

			if (Validator.isNull(_servletContextName)) {
				try {
					String propsUtilServletContextName = PropsUtil.get(
							"kaleo-web-deployment-context");

					if (Validator.isNotNull(propsUtilServletContextName)) {
						_servletContextName = propsUtilServletContextName;
					}
				}
				catch (Throwable t) {
					if (_log.isWarnEnabled()) {
						_log.warn("Unable to locate deployment context from portal properties",
							t);
					}
				}
			}

			if (Validator.isNull(_servletContextName)) {
				_servletContextName = "kaleo-web";
			}

			return _servletContextName;
		}
	}

	public static void setClassLoader(ClassLoader classLoader) {
		_classLoader = classLoader;
	}

	public static Object translateInput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(KaleoActionClp.class.getName())) {
			return translateInputKaleoAction(oldModel);
		}

		if (oldModelClassName.equals(KaleoConditionClp.class.getName())) {
			return translateInputKaleoCondition(oldModel);
		}

		if (oldModelClassName.equals(KaleoDefinitionClp.class.getName())) {
			return translateInputKaleoDefinition(oldModel);
		}

		if (oldModelClassName.equals(KaleoInstanceClp.class.getName())) {
			return translateInputKaleoInstance(oldModel);
		}

		if (oldModelClassName.equals(KaleoInstanceTokenClp.class.getName())) {
			return translateInputKaleoInstanceToken(oldModel);
		}

		if (oldModelClassName.equals(KaleoLogClp.class.getName())) {
			return translateInputKaleoLog(oldModel);
		}

		if (oldModelClassName.equals(KaleoNodeClp.class.getName())) {
			return translateInputKaleoNode(oldModel);
		}

		if (oldModelClassName.equals(KaleoNotificationClp.class.getName())) {
			return translateInputKaleoNotification(oldModel);
		}

		if (oldModelClassName.equals(
					KaleoNotificationRecipientClp.class.getName())) {
			return translateInputKaleoNotificationRecipient(oldModel);
		}

		if (oldModelClassName.equals(KaleoTaskClp.class.getName())) {
			return translateInputKaleoTask(oldModel);
		}

		if (oldModelClassName.equals(KaleoTaskAssignmentClp.class.getName())) {
			return translateInputKaleoTaskAssignment(oldModel);
		}

		if (oldModelClassName.equals(
					KaleoTaskAssignmentInstanceClp.class.getName())) {
			return translateInputKaleoTaskAssignmentInstance(oldModel);
		}

		if (oldModelClassName.equals(KaleoTaskInstanceTokenClp.class.getName())) {
			return translateInputKaleoTaskInstanceToken(oldModel);
		}

		if (oldModelClassName.equals(KaleoTimerClp.class.getName())) {
			return translateInputKaleoTimer(oldModel);
		}

		if (oldModelClassName.equals(KaleoTimerInstanceTokenClp.class.getName())) {
			return translateInputKaleoTimerInstanceToken(oldModel);
		}

		if (oldModelClassName.equals(KaleoTransitionClp.class.getName())) {
			return translateInputKaleoTransition(oldModel);
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

	public static Object translateInputKaleoAction(BaseModel<?> oldModel) {
		KaleoActionClp oldCplModel = (KaleoActionClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.portal.workflow.kaleo.model.impl.KaleoActionImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setKaleoActionId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getKaleoActionId());

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setGroupId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getGroupId());

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setCompanyId",
						new Class[] { Long.TYPE });

				Long value2 = new Long(oldCplModel.getCompanyId());

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setUserId",
						new Class[] { Long.TYPE });

				Long value3 = new Long(oldCplModel.getUserId());

				method3.invoke(newModel, value3);

				Method method4 = newModelClass.getMethod("setUserName",
						new Class[] { String.class });

				String value4 = oldCplModel.getUserName();

				method4.invoke(newModel, value4);

				Method method5 = newModelClass.getMethod("setCreateDate",
						new Class[] { Date.class });

				Date value5 = oldCplModel.getCreateDate();

				method5.invoke(newModel, value5);

				Method method6 = newModelClass.getMethod("setModifiedDate",
						new Class[] { Date.class });

				Date value6 = oldCplModel.getModifiedDate();

				method6.invoke(newModel, value6);

				Method method7 = newModelClass.getMethod("setKaleoDefinitionId",
						new Class[] { Long.TYPE });

				Long value7 = new Long(oldCplModel.getKaleoDefinitionId());

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setKaleoNodeId",
						new Class[] { Long.TYPE });

				Long value8 = new Long(oldCplModel.getKaleoNodeId());

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setKaleoNodeName",
						new Class[] { String.class });

				String value9 = oldCplModel.getKaleoNodeName();

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setName",
						new Class[] { String.class });

				String value10 = oldCplModel.getName();

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value11 = oldCplModel.getDescription();

				method11.invoke(newModel, value11);

				Method method12 = newModelClass.getMethod("setExecutionType",
						new Class[] { String.class });

				String value12 = oldCplModel.getExecutionType();

				method12.invoke(newModel, value12);

				Method method13 = newModelClass.getMethod("setScript",
						new Class[] { String.class });

				String value13 = oldCplModel.getScript();

				method13.invoke(newModel, value13);

				Method method14 = newModelClass.getMethod("setScriptLanguage",
						new Class[] { String.class });

				String value14 = oldCplModel.getScriptLanguage();

				method14.invoke(newModel, value14);

				Method method15 = newModelClass.getMethod("setPriority",
						new Class[] { Integer.TYPE });

				Integer value15 = new Integer(oldCplModel.getPriority());

				method15.invoke(newModel, value15);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputKaleoCondition(BaseModel<?> oldModel) {
		KaleoConditionClp oldCplModel = (KaleoConditionClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.portal.workflow.kaleo.model.impl.KaleoConditionImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setKaleoConditionId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getKaleoConditionId());

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setGroupId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getGroupId());

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setCompanyId",
						new Class[] { Long.TYPE });

				Long value2 = new Long(oldCplModel.getCompanyId());

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setUserId",
						new Class[] { Long.TYPE });

				Long value3 = new Long(oldCplModel.getUserId());

				method3.invoke(newModel, value3);

				Method method4 = newModelClass.getMethod("setUserName",
						new Class[] { String.class });

				String value4 = oldCplModel.getUserName();

				method4.invoke(newModel, value4);

				Method method5 = newModelClass.getMethod("setCreateDate",
						new Class[] { Date.class });

				Date value5 = oldCplModel.getCreateDate();

				method5.invoke(newModel, value5);

				Method method6 = newModelClass.getMethod("setModifiedDate",
						new Class[] { Date.class });

				Date value6 = oldCplModel.getModifiedDate();

				method6.invoke(newModel, value6);

				Method method7 = newModelClass.getMethod("setClassName",
						new Class[] { String.class });

				String value7 = oldCplModel.getClassName();

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setClassPK",
						new Class[] { Long.TYPE });

				Long value8 = new Long(oldCplModel.getClassPK());

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value9 = oldCplModel.getDescription();

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setScript",
						new Class[] { String.class });

				String value10 = oldCplModel.getScript();

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setScriptLanguage",
						new Class[] { String.class });

				String value11 = oldCplModel.getScriptLanguage();

				method11.invoke(newModel, value11);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputKaleoDefinition(BaseModel<?> oldModel) {
		KaleoDefinitionClp oldCplModel = (KaleoDefinitionClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setKaleoDefinitionId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getKaleoDefinitionId());

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setGroupId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getGroupId());

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setCompanyId",
						new Class[] { Long.TYPE });

				Long value2 = new Long(oldCplModel.getCompanyId());

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setUserId",
						new Class[] { Long.TYPE });

				Long value3 = new Long(oldCplModel.getUserId());

				method3.invoke(newModel, value3);

				Method method4 = newModelClass.getMethod("setUserName",
						new Class[] { String.class });

				String value4 = oldCplModel.getUserName();

				method4.invoke(newModel, value4);

				Method method5 = newModelClass.getMethod("setCreateDate",
						new Class[] { Date.class });

				Date value5 = oldCplModel.getCreateDate();

				method5.invoke(newModel, value5);

				Method method6 = newModelClass.getMethod("setModifiedDate",
						new Class[] { Date.class });

				Date value6 = oldCplModel.getModifiedDate();

				method6.invoke(newModel, value6);

				Method method7 = newModelClass.getMethod("setName",
						new Class[] { String.class });

				String value7 = oldCplModel.getName();

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setTitle",
						new Class[] { String.class });

				String value8 = oldCplModel.getTitle();

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value9 = oldCplModel.getDescription();

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setVersion",
						new Class[] { Integer.TYPE });

				Integer value10 = new Integer(oldCplModel.getVersion());

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setActive",
						new Class[] { Boolean.TYPE });

				Boolean value11 = new Boolean(oldCplModel.getActive());

				method11.invoke(newModel, value11);

				Method method12 = newModelClass.getMethod("setStartKaleoNodeId",
						new Class[] { Long.TYPE });

				Long value12 = new Long(oldCplModel.getStartKaleoNodeId());

				method12.invoke(newModel, value12);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputKaleoInstance(BaseModel<?> oldModel) {
		KaleoInstanceClp oldCplModel = (KaleoInstanceClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setKaleoInstanceId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getKaleoInstanceId());

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setGroupId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getGroupId());

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setCompanyId",
						new Class[] { Long.TYPE });

				Long value2 = new Long(oldCplModel.getCompanyId());

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setUserId",
						new Class[] { Long.TYPE });

				Long value3 = new Long(oldCplModel.getUserId());

				method3.invoke(newModel, value3);

				Method method4 = newModelClass.getMethod("setUserName",
						new Class[] { String.class });

				String value4 = oldCplModel.getUserName();

				method4.invoke(newModel, value4);

				Method method5 = newModelClass.getMethod("setCreateDate",
						new Class[] { Date.class });

				Date value5 = oldCplModel.getCreateDate();

				method5.invoke(newModel, value5);

				Method method6 = newModelClass.getMethod("setModifiedDate",
						new Class[] { Date.class });

				Date value6 = oldCplModel.getModifiedDate();

				method6.invoke(newModel, value6);

				Method method7 = newModelClass.getMethod("setKaleoDefinitionId",
						new Class[] { Long.TYPE });

				Long value7 = new Long(oldCplModel.getKaleoDefinitionId());

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setKaleoDefinitionName",
						new Class[] { String.class });

				String value8 = oldCplModel.getKaleoDefinitionName();

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setKaleoDefinitionVersion",
						new Class[] { Integer.TYPE });

				Integer value9 = new Integer(oldCplModel.getKaleoDefinitionVersion());

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setRootKaleoInstanceTokenId",
						new Class[] { Long.TYPE });

				Long value10 = new Long(oldCplModel.getRootKaleoInstanceTokenId());

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setClassName",
						new Class[] { String.class });

				String value11 = oldCplModel.getClassName();

				method11.invoke(newModel, value11);

				Method method12 = newModelClass.getMethod("setClassPK",
						new Class[] { Long.TYPE });

				Long value12 = new Long(oldCplModel.getClassPK());

				method12.invoke(newModel, value12);

				Method method13 = newModelClass.getMethod("setCompleted",
						new Class[] { Boolean.TYPE });

				Boolean value13 = new Boolean(oldCplModel.getCompleted());

				method13.invoke(newModel, value13);

				Method method14 = newModelClass.getMethod("setCompletionDate",
						new Class[] { Date.class });

				Date value14 = oldCplModel.getCompletionDate();

				method14.invoke(newModel, value14);

				Method method15 = newModelClass.getMethod("setWorkflowContext",
						new Class[] { String.class });

				String value15 = oldCplModel.getWorkflowContext();

				method15.invoke(newModel, value15);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputKaleoInstanceToken(BaseModel<?> oldModel) {
		KaleoInstanceTokenClp oldCplModel = (KaleoInstanceTokenClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceTokenImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setKaleoInstanceTokenId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getKaleoInstanceTokenId());

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setGroupId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getGroupId());

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setCompanyId",
						new Class[] { Long.TYPE });

				Long value2 = new Long(oldCplModel.getCompanyId());

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setUserId",
						new Class[] { Long.TYPE });

				Long value3 = new Long(oldCplModel.getUserId());

				method3.invoke(newModel, value3);

				Method method4 = newModelClass.getMethod("setUserName",
						new Class[] { String.class });

				String value4 = oldCplModel.getUserName();

				method4.invoke(newModel, value4);

				Method method5 = newModelClass.getMethod("setCreateDate",
						new Class[] { Date.class });

				Date value5 = oldCplModel.getCreateDate();

				method5.invoke(newModel, value5);

				Method method6 = newModelClass.getMethod("setModifiedDate",
						new Class[] { Date.class });

				Date value6 = oldCplModel.getModifiedDate();

				method6.invoke(newModel, value6);

				Method method7 = newModelClass.getMethod("setKaleoDefinitionId",
						new Class[] { Long.TYPE });

				Long value7 = new Long(oldCplModel.getKaleoDefinitionId());

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setKaleoInstanceId",
						new Class[] { Long.TYPE });

				Long value8 = new Long(oldCplModel.getKaleoInstanceId());

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setParentKaleoInstanceTokenId",
						new Class[] { Long.TYPE });

				Long value9 = new Long(oldCplModel.getParentKaleoInstanceTokenId());

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setCurrentKaleoNodeId",
						new Class[] { Long.TYPE });

				Long value10 = new Long(oldCplModel.getCurrentKaleoNodeId());

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setCurrentKaleoNodeName",
						new Class[] { String.class });

				String value11 = oldCplModel.getCurrentKaleoNodeName();

				method11.invoke(newModel, value11);

				Method method12 = newModelClass.getMethod("setClassName",
						new Class[] { String.class });

				String value12 = oldCplModel.getClassName();

				method12.invoke(newModel, value12);

				Method method13 = newModelClass.getMethod("setClassPK",
						new Class[] { Long.TYPE });

				Long value13 = new Long(oldCplModel.getClassPK());

				method13.invoke(newModel, value13);

				Method method14 = newModelClass.getMethod("setCompleted",
						new Class[] { Boolean.TYPE });

				Boolean value14 = new Boolean(oldCplModel.getCompleted());

				method14.invoke(newModel, value14);

				Method method15 = newModelClass.getMethod("setCompletionDate",
						new Class[] { Date.class });

				Date value15 = oldCplModel.getCompletionDate();

				method15.invoke(newModel, value15);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputKaleoLog(BaseModel<?> oldModel) {
		KaleoLogClp oldCplModel = (KaleoLogClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.portal.workflow.kaleo.model.impl.KaleoLogImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setKaleoLogId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getKaleoLogId());

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setGroupId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getGroupId());

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setCompanyId",
						new Class[] { Long.TYPE });

				Long value2 = new Long(oldCplModel.getCompanyId());

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setUserId",
						new Class[] { Long.TYPE });

				Long value3 = new Long(oldCplModel.getUserId());

				method3.invoke(newModel, value3);

				Method method4 = newModelClass.getMethod("setUserName",
						new Class[] { String.class });

				String value4 = oldCplModel.getUserName();

				method4.invoke(newModel, value4);

				Method method5 = newModelClass.getMethod("setCreateDate",
						new Class[] { Date.class });

				Date value5 = oldCplModel.getCreateDate();

				method5.invoke(newModel, value5);

				Method method6 = newModelClass.getMethod("setModifiedDate",
						new Class[] { Date.class });

				Date value6 = oldCplModel.getModifiedDate();

				method6.invoke(newModel, value6);

				Method method7 = newModelClass.getMethod("setKaleoDefinitionId",
						new Class[] { Long.TYPE });

				Long value7 = new Long(oldCplModel.getKaleoDefinitionId());

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setKaleoInstanceId",
						new Class[] { Long.TYPE });

				Long value8 = new Long(oldCplModel.getKaleoInstanceId());

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setKaleoInstanceTokenId",
						new Class[] { Long.TYPE });

				Long value9 = new Long(oldCplModel.getKaleoInstanceTokenId());

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setKaleoTaskInstanceTokenId",
						new Class[] { Long.TYPE });

				Long value10 = new Long(oldCplModel.getKaleoTaskInstanceTokenId());

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setKaleoNodeId",
						new Class[] { Long.TYPE });

				Long value11 = new Long(oldCplModel.getKaleoNodeId());

				method11.invoke(newModel, value11);

				Method method12 = newModelClass.getMethod("setKaleoNodeName",
						new Class[] { String.class });

				String value12 = oldCplModel.getKaleoNodeName();

				method12.invoke(newModel, value12);

				Method method13 = newModelClass.getMethod("setTerminalKaleoNode",
						new Class[] { Boolean.TYPE });

				Boolean value13 = new Boolean(oldCplModel.getTerminalKaleoNode());

				method13.invoke(newModel, value13);

				Method method14 = newModelClass.getMethod("setKaleoActionId",
						new Class[] { Long.TYPE });

				Long value14 = new Long(oldCplModel.getKaleoActionId());

				method14.invoke(newModel, value14);

				Method method15 = newModelClass.getMethod("setKaleoActionName",
						new Class[] { String.class });

				String value15 = oldCplModel.getKaleoActionName();

				method15.invoke(newModel, value15);

				Method method16 = newModelClass.getMethod("setKaleoActionDescription",
						new Class[] { String.class });

				String value16 = oldCplModel.getKaleoActionDescription();

				method16.invoke(newModel, value16);

				Method method17 = newModelClass.getMethod("setPreviousKaleoNodeId",
						new Class[] { Long.TYPE });

				Long value17 = new Long(oldCplModel.getPreviousKaleoNodeId());

				method17.invoke(newModel, value17);

				Method method18 = newModelClass.getMethod("setPreviousKaleoNodeName",
						new Class[] { String.class });

				String value18 = oldCplModel.getPreviousKaleoNodeName();

				method18.invoke(newModel, value18);

				Method method19 = newModelClass.getMethod("setPreviousAssigneeClassName",
						new Class[] { String.class });

				String value19 = oldCplModel.getPreviousAssigneeClassName();

				method19.invoke(newModel, value19);

				Method method20 = newModelClass.getMethod("setPreviousAssigneeClassPK",
						new Class[] { Long.TYPE });

				Long value20 = new Long(oldCplModel.getPreviousAssigneeClassPK());

				method20.invoke(newModel, value20);

				Method method21 = newModelClass.getMethod("setCurrentAssigneeClassName",
						new Class[] { String.class });

				String value21 = oldCplModel.getCurrentAssigneeClassName();

				method21.invoke(newModel, value21);

				Method method22 = newModelClass.getMethod("setCurrentAssigneeClassPK",
						new Class[] { Long.TYPE });

				Long value22 = new Long(oldCplModel.getCurrentAssigneeClassPK());

				method22.invoke(newModel, value22);

				Method method23 = newModelClass.getMethod("setType",
						new Class[] { String.class });

				String value23 = oldCplModel.getType();

				method23.invoke(newModel, value23);

				Method method24 = newModelClass.getMethod("setComment",
						new Class[] { String.class });

				String value24 = oldCplModel.getComment();

				method24.invoke(newModel, value24);

				Method method25 = newModelClass.getMethod("setStartDate",
						new Class[] { Date.class });

				Date value25 = oldCplModel.getStartDate();

				method25.invoke(newModel, value25);

				Method method26 = newModelClass.getMethod("setEndDate",
						new Class[] { Date.class });

				Date value26 = oldCplModel.getEndDate();

				method26.invoke(newModel, value26);

				Method method27 = newModelClass.getMethod("setDuration",
						new Class[] { Long.TYPE });

				Long value27 = new Long(oldCplModel.getDuration());

				method27.invoke(newModel, value27);

				Method method28 = newModelClass.getMethod("setWorkflowContext",
						new Class[] { String.class });

				String value28 = oldCplModel.getWorkflowContext();

				method28.invoke(newModel, value28);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputKaleoNode(BaseModel<?> oldModel) {
		KaleoNodeClp oldCplModel = (KaleoNodeClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.portal.workflow.kaleo.model.impl.KaleoNodeImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setKaleoNodeId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getKaleoNodeId());

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setGroupId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getGroupId());

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setCompanyId",
						new Class[] { Long.TYPE });

				Long value2 = new Long(oldCplModel.getCompanyId());

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setUserId",
						new Class[] { Long.TYPE });

				Long value3 = new Long(oldCplModel.getUserId());

				method3.invoke(newModel, value3);

				Method method4 = newModelClass.getMethod("setUserName",
						new Class[] { String.class });

				String value4 = oldCplModel.getUserName();

				method4.invoke(newModel, value4);

				Method method5 = newModelClass.getMethod("setCreateDate",
						new Class[] { Date.class });

				Date value5 = oldCplModel.getCreateDate();

				method5.invoke(newModel, value5);

				Method method6 = newModelClass.getMethod("setModifiedDate",
						new Class[] { Date.class });

				Date value6 = oldCplModel.getModifiedDate();

				method6.invoke(newModel, value6);

				Method method7 = newModelClass.getMethod("setKaleoDefinitionId",
						new Class[] { Long.TYPE });

				Long value7 = new Long(oldCplModel.getKaleoDefinitionId());

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setName",
						new Class[] { String.class });

				String value8 = oldCplModel.getName();

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value9 = oldCplModel.getDescription();

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setType",
						new Class[] { String.class });

				String value10 = oldCplModel.getType();

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setInitial",
						new Class[] { Boolean.TYPE });

				Boolean value11 = new Boolean(oldCplModel.getInitial());

				method11.invoke(newModel, value11);

				Method method12 = newModelClass.getMethod("setTerminal",
						new Class[] { Boolean.TYPE });

				Boolean value12 = new Boolean(oldCplModel.getTerminal());

				method12.invoke(newModel, value12);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputKaleoNotification(BaseModel<?> oldModel) {
		KaleoNotificationClp oldCplModel = (KaleoNotificationClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setKaleoNotificationId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getKaleoNotificationId());

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setGroupId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getGroupId());

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setCompanyId",
						new Class[] { Long.TYPE });

				Long value2 = new Long(oldCplModel.getCompanyId());

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setUserId",
						new Class[] { Long.TYPE });

				Long value3 = new Long(oldCplModel.getUserId());

				method3.invoke(newModel, value3);

				Method method4 = newModelClass.getMethod("setUserName",
						new Class[] { String.class });

				String value4 = oldCplModel.getUserName();

				method4.invoke(newModel, value4);

				Method method5 = newModelClass.getMethod("setCreateDate",
						new Class[] { Date.class });

				Date value5 = oldCplModel.getCreateDate();

				method5.invoke(newModel, value5);

				Method method6 = newModelClass.getMethod("setModifiedDate",
						new Class[] { Date.class });

				Date value6 = oldCplModel.getModifiedDate();

				method6.invoke(newModel, value6);

				Method method7 = newModelClass.getMethod("setKaleoDefinitionId",
						new Class[] { Long.TYPE });

				Long value7 = new Long(oldCplModel.getKaleoDefinitionId());

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setKaleoNodeId",
						new Class[] { Long.TYPE });

				Long value8 = new Long(oldCplModel.getKaleoNodeId());

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setKaleoNodeName",
						new Class[] { String.class });

				String value9 = oldCplModel.getKaleoNodeName();

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setName",
						new Class[] { String.class });

				String value10 = oldCplModel.getName();

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value11 = oldCplModel.getDescription();

				method11.invoke(newModel, value11);

				Method method12 = newModelClass.getMethod("setExecutionType",
						new Class[] { String.class });

				String value12 = oldCplModel.getExecutionType();

				method12.invoke(newModel, value12);

				Method method13 = newModelClass.getMethod("setTemplate",
						new Class[] { String.class });

				String value13 = oldCplModel.getTemplate();

				method13.invoke(newModel, value13);

				Method method14 = newModelClass.getMethod("setTemplateLanguage",
						new Class[] { String.class });

				String value14 = oldCplModel.getTemplateLanguage();

				method14.invoke(newModel, value14);

				Method method15 = newModelClass.getMethod("setNotificationTypes",
						new Class[] { String.class });

				String value15 = oldCplModel.getNotificationTypes();

				method15.invoke(newModel, value15);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputKaleoNotificationRecipient(
		BaseModel<?> oldModel) {
		KaleoNotificationRecipientClp oldCplModel = (KaleoNotificationRecipientClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationRecipientImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setKaleoNotificationRecipientId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getKaleoNotificationRecipientId());

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setGroupId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getGroupId());

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setCompanyId",
						new Class[] { Long.TYPE });

				Long value2 = new Long(oldCplModel.getCompanyId());

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setUserId",
						new Class[] { Long.TYPE });

				Long value3 = new Long(oldCplModel.getUserId());

				method3.invoke(newModel, value3);

				Method method4 = newModelClass.getMethod("setUserName",
						new Class[] { String.class });

				String value4 = oldCplModel.getUserName();

				method4.invoke(newModel, value4);

				Method method5 = newModelClass.getMethod("setCreateDate",
						new Class[] { Date.class });

				Date value5 = oldCplModel.getCreateDate();

				method5.invoke(newModel, value5);

				Method method6 = newModelClass.getMethod("setModifiedDate",
						new Class[] { Date.class });

				Date value6 = oldCplModel.getModifiedDate();

				method6.invoke(newModel, value6);

				Method method7 = newModelClass.getMethod("setKaleoDefinitionId",
						new Class[] { Long.TYPE });

				Long value7 = new Long(oldCplModel.getKaleoDefinitionId());

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setKaleoNotificationId",
						new Class[] { Long.TYPE });

				Long value8 = new Long(oldCplModel.getKaleoNotificationId());

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setRecipientClassName",
						new Class[] { String.class });

				String value9 = oldCplModel.getRecipientClassName();

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setRecipientClassPK",
						new Class[] { Long.TYPE });

				Long value10 = new Long(oldCplModel.getRecipientClassPK());

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setRecipientRoleType",
						new Class[] { Integer.TYPE });

				Integer value11 = new Integer(oldCplModel.getRecipientRoleType());

				method11.invoke(newModel, value11);

				Method method12 = newModelClass.getMethod("setAddress",
						new Class[] { String.class });

				String value12 = oldCplModel.getAddress();

				method12.invoke(newModel, value12);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputKaleoTask(BaseModel<?> oldModel) {
		KaleoTaskClp oldCplModel = (KaleoTaskClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setKaleoTaskId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getKaleoTaskId());

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setGroupId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getGroupId());

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setCompanyId",
						new Class[] { Long.TYPE });

				Long value2 = new Long(oldCplModel.getCompanyId());

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setUserId",
						new Class[] { Long.TYPE });

				Long value3 = new Long(oldCplModel.getUserId());

				method3.invoke(newModel, value3);

				Method method4 = newModelClass.getMethod("setUserName",
						new Class[] { String.class });

				String value4 = oldCplModel.getUserName();

				method4.invoke(newModel, value4);

				Method method5 = newModelClass.getMethod("setCreateDate",
						new Class[] { Date.class });

				Date value5 = oldCplModel.getCreateDate();

				method5.invoke(newModel, value5);

				Method method6 = newModelClass.getMethod("setModifiedDate",
						new Class[] { Date.class });

				Date value6 = oldCplModel.getModifiedDate();

				method6.invoke(newModel, value6);

				Method method7 = newModelClass.getMethod("setKaleoDefinitionId",
						new Class[] { Long.TYPE });

				Long value7 = new Long(oldCplModel.getKaleoDefinitionId());

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setKaleoNodeId",
						new Class[] { Long.TYPE });

				Long value8 = new Long(oldCplModel.getKaleoNodeId());

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setName",
						new Class[] { String.class });

				String value9 = oldCplModel.getName();

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value10 = oldCplModel.getDescription();

				method10.invoke(newModel, value10);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputKaleoTaskAssignment(
		BaseModel<?> oldModel) {
		KaleoTaskAssignmentClp oldCplModel = (KaleoTaskAssignmentClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setKaleoTaskAssignmentId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getKaleoTaskAssignmentId());

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setGroupId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getGroupId());

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setCompanyId",
						new Class[] { Long.TYPE });

				Long value2 = new Long(oldCplModel.getCompanyId());

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setUserId",
						new Class[] { Long.TYPE });

				Long value3 = new Long(oldCplModel.getUserId());

				method3.invoke(newModel, value3);

				Method method4 = newModelClass.getMethod("setUserName",
						new Class[] { String.class });

				String value4 = oldCplModel.getUserName();

				method4.invoke(newModel, value4);

				Method method5 = newModelClass.getMethod("setCreateDate",
						new Class[] { Date.class });

				Date value5 = oldCplModel.getCreateDate();

				method5.invoke(newModel, value5);

				Method method6 = newModelClass.getMethod("setModifiedDate",
						new Class[] { Date.class });

				Date value6 = oldCplModel.getModifiedDate();

				method6.invoke(newModel, value6);

				Method method7 = newModelClass.getMethod("setKaleoDefinitionId",
						new Class[] { Long.TYPE });

				Long value7 = new Long(oldCplModel.getKaleoDefinitionId());

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setKaleoNodeId",
						new Class[] { Long.TYPE });

				Long value8 = new Long(oldCplModel.getKaleoNodeId());

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setKaleoTaskId",
						new Class[] { Long.TYPE });

				Long value9 = new Long(oldCplModel.getKaleoTaskId());

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setAssigneeClassName",
						new Class[] { String.class });

				String value10 = oldCplModel.getAssigneeClassName();

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setAssigneeClassPK",
						new Class[] { Long.TYPE });

				Long value11 = new Long(oldCplModel.getAssigneeClassPK());

				method11.invoke(newModel, value11);

				Method method12 = newModelClass.getMethod("setAssigneeActionId",
						new Class[] { String.class });

				String value12 = oldCplModel.getAssigneeActionId();

				method12.invoke(newModel, value12);

				Method method13 = newModelClass.getMethod("setAssigneeScript",
						new Class[] { String.class });

				String value13 = oldCplModel.getAssigneeScript();

				method13.invoke(newModel, value13);

				Method method14 = newModelClass.getMethod("setAssigneeScriptLanguage",
						new Class[] { String.class });

				String value14 = oldCplModel.getAssigneeScriptLanguage();

				method14.invoke(newModel, value14);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputKaleoTaskAssignmentInstance(
		BaseModel<?> oldModel) {
		KaleoTaskAssignmentInstanceClp oldCplModel = (KaleoTaskAssignmentInstanceClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setKaleoTaskAssignmentInstanceId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getKaleoTaskAssignmentInstanceId());

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setGroupId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getGroupId());

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setCompanyId",
						new Class[] { Long.TYPE });

				Long value2 = new Long(oldCplModel.getCompanyId());

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setUserId",
						new Class[] { Long.TYPE });

				Long value3 = new Long(oldCplModel.getUserId());

				method3.invoke(newModel, value3);

				Method method4 = newModelClass.getMethod("setUserName",
						new Class[] { String.class });

				String value4 = oldCplModel.getUserName();

				method4.invoke(newModel, value4);

				Method method5 = newModelClass.getMethod("setCreateDate",
						new Class[] { Date.class });

				Date value5 = oldCplModel.getCreateDate();

				method5.invoke(newModel, value5);

				Method method6 = newModelClass.getMethod("setModifiedDate",
						new Class[] { Date.class });

				Date value6 = oldCplModel.getModifiedDate();

				method6.invoke(newModel, value6);

				Method method7 = newModelClass.getMethod("setKaleoDefinitionId",
						new Class[] { Long.TYPE });

				Long value7 = new Long(oldCplModel.getKaleoDefinitionId());

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setKaleoInstanceId",
						new Class[] { Long.TYPE });

				Long value8 = new Long(oldCplModel.getKaleoInstanceId());

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setKaleoInstanceTokenId",
						new Class[] { Long.TYPE });

				Long value9 = new Long(oldCplModel.getKaleoInstanceTokenId());

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setKaleoTaskInstanceTokenId",
						new Class[] { Long.TYPE });

				Long value10 = new Long(oldCplModel.getKaleoTaskInstanceTokenId());

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setKaleoTaskId",
						new Class[] { Long.TYPE });

				Long value11 = new Long(oldCplModel.getKaleoTaskId());

				method11.invoke(newModel, value11);

				Method method12 = newModelClass.getMethod("setKaleoTaskName",
						new Class[] { String.class });

				String value12 = oldCplModel.getKaleoTaskName();

				method12.invoke(newModel, value12);

				Method method13 = newModelClass.getMethod("setAssigneeClassName",
						new Class[] { String.class });

				String value13 = oldCplModel.getAssigneeClassName();

				method13.invoke(newModel, value13);

				Method method14 = newModelClass.getMethod("setAssigneeClassPK",
						new Class[] { Long.TYPE });

				Long value14 = new Long(oldCplModel.getAssigneeClassPK());

				method14.invoke(newModel, value14);

				Method method15 = newModelClass.getMethod("setCompleted",
						new Class[] { Boolean.TYPE });

				Boolean value15 = new Boolean(oldCplModel.getCompleted());

				method15.invoke(newModel, value15);

				Method method16 = newModelClass.getMethod("setCompletionDate",
						new Class[] { Date.class });

				Date value16 = oldCplModel.getCompletionDate();

				method16.invoke(newModel, value16);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputKaleoTaskInstanceToken(
		BaseModel<?> oldModel) {
		KaleoTaskInstanceTokenClp oldCplModel = (KaleoTaskInstanceTokenClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskInstanceTokenImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setKaleoTaskInstanceTokenId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getKaleoTaskInstanceTokenId());

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setGroupId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getGroupId());

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setCompanyId",
						new Class[] { Long.TYPE });

				Long value2 = new Long(oldCplModel.getCompanyId());

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setUserId",
						new Class[] { Long.TYPE });

				Long value3 = new Long(oldCplModel.getUserId());

				method3.invoke(newModel, value3);

				Method method4 = newModelClass.getMethod("setUserName",
						new Class[] { String.class });

				String value4 = oldCplModel.getUserName();

				method4.invoke(newModel, value4);

				Method method5 = newModelClass.getMethod("setCreateDate",
						new Class[] { Date.class });

				Date value5 = oldCplModel.getCreateDate();

				method5.invoke(newModel, value5);

				Method method6 = newModelClass.getMethod("setModifiedDate",
						new Class[] { Date.class });

				Date value6 = oldCplModel.getModifiedDate();

				method6.invoke(newModel, value6);

				Method method7 = newModelClass.getMethod("setKaleoDefinitionId",
						new Class[] { Long.TYPE });

				Long value7 = new Long(oldCplModel.getKaleoDefinitionId());

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setKaleoInstanceId",
						new Class[] { Long.TYPE });

				Long value8 = new Long(oldCplModel.getKaleoInstanceId());

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setKaleoInstanceTokenId",
						new Class[] { Long.TYPE });

				Long value9 = new Long(oldCplModel.getKaleoInstanceTokenId());

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setKaleoTaskId",
						new Class[] { Long.TYPE });

				Long value10 = new Long(oldCplModel.getKaleoTaskId());

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setKaleoTaskName",
						new Class[] { String.class });

				String value11 = oldCplModel.getKaleoTaskName();

				method11.invoke(newModel, value11);

				Method method12 = newModelClass.getMethod("setClassName",
						new Class[] { String.class });

				String value12 = oldCplModel.getClassName();

				method12.invoke(newModel, value12);

				Method method13 = newModelClass.getMethod("setClassPK",
						new Class[] { Long.TYPE });

				Long value13 = new Long(oldCplModel.getClassPK());

				method13.invoke(newModel, value13);

				Method method14 = newModelClass.getMethod("setCompletionUserId",
						new Class[] { Long.TYPE });

				Long value14 = new Long(oldCplModel.getCompletionUserId());

				method14.invoke(newModel, value14);

				Method method15 = newModelClass.getMethod("setCompleted",
						new Class[] { Boolean.TYPE });

				Boolean value15 = new Boolean(oldCplModel.getCompleted());

				method15.invoke(newModel, value15);

				Method method16 = newModelClass.getMethod("setCompletionDate",
						new Class[] { Date.class });

				Date value16 = oldCplModel.getCompletionDate();

				method16.invoke(newModel, value16);

				Method method17 = newModelClass.getMethod("setDueDate",
						new Class[] { Date.class });

				Date value17 = oldCplModel.getDueDate();

				method17.invoke(newModel, value17);

				Method method18 = newModelClass.getMethod("setWorkflowContext",
						new Class[] { String.class });

				String value18 = oldCplModel.getWorkflowContext();

				method18.invoke(newModel, value18);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputKaleoTimer(BaseModel<?> oldModel) {
		KaleoTimerClp oldCplModel = (KaleoTimerClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setKaleoTimerId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getKaleoTimerId());

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setGroupId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getGroupId());

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setCompanyId",
						new Class[] { Long.TYPE });

				Long value2 = new Long(oldCplModel.getCompanyId());

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setUserId",
						new Class[] { Long.TYPE });

				Long value3 = new Long(oldCplModel.getUserId());

				method3.invoke(newModel, value3);

				Method method4 = newModelClass.getMethod("setUserName",
						new Class[] { String.class });

				String value4 = oldCplModel.getUserName();

				method4.invoke(newModel, value4);

				Method method5 = newModelClass.getMethod("setCreateDate",
						new Class[] { Date.class });

				Date value5 = oldCplModel.getCreateDate();

				method5.invoke(newModel, value5);

				Method method6 = newModelClass.getMethod("setModifiedDate",
						new Class[] { Date.class });

				Date value6 = oldCplModel.getModifiedDate();

				method6.invoke(newModel, value6);

				Method method7 = newModelClass.getMethod("setKaleoDefinitionId",
						new Class[] { Long.TYPE });

				Long value7 = new Long(oldCplModel.getKaleoDefinitionId());

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setKaleoNodeId",
						new Class[] { Long.TYPE });

				Long value8 = new Long(oldCplModel.getKaleoNodeId());

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setParentKaleoNodeId",
						new Class[] { Long.TYPE });

				Long value9 = new Long(oldCplModel.getParentKaleoNodeId());

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setName",
						new Class[] { String.class });

				String value10 = oldCplModel.getName();

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setDefaultTimer",
						new Class[] { Boolean.TYPE });

				Boolean value11 = new Boolean(oldCplModel.getDefaultTimer());

				method11.invoke(newModel, value11);

				Method method12 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value12 = oldCplModel.getDescription();

				method12.invoke(newModel, value12);

				Method method13 = newModelClass.getMethod("setDuration",
						new Class[] { Double.TYPE });

				Double value13 = new Double(oldCplModel.getDuration());

				method13.invoke(newModel, value13);

				Method method14 = newModelClass.getMethod("setScale",
						new Class[] { String.class });

				String value14 = oldCplModel.getScale();

				method14.invoke(newModel, value14);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputKaleoTimerInstanceToken(
		BaseModel<?> oldModel) {
		KaleoTimerInstanceTokenClp oldCplModel = (KaleoTimerInstanceTokenClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerInstanceTokenImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setKaleoTimerInstanceTokenId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getKaleoTimerInstanceTokenId());

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setGroupId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getGroupId());

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setCompanyId",
						new Class[] { Long.TYPE });

				Long value2 = new Long(oldCplModel.getCompanyId());

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setUserId",
						new Class[] { Long.TYPE });

				Long value3 = new Long(oldCplModel.getUserId());

				method3.invoke(newModel, value3);

				Method method4 = newModelClass.getMethod("setUserName",
						new Class[] { String.class });

				String value4 = oldCplModel.getUserName();

				method4.invoke(newModel, value4);

				Method method5 = newModelClass.getMethod("setCreateDate",
						new Class[] { Date.class });

				Date value5 = oldCplModel.getCreateDate();

				method5.invoke(newModel, value5);

				Method method6 = newModelClass.getMethod("setModifiedDate",
						new Class[] { Date.class });

				Date value6 = oldCplModel.getModifiedDate();

				method6.invoke(newModel, value6);

				Method method7 = newModelClass.getMethod("setKaleoDefinitionId",
						new Class[] { Long.TYPE });

				Long value7 = new Long(oldCplModel.getKaleoDefinitionId());

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setKaleoInstanceId",
						new Class[] { Long.TYPE });

				Long value8 = new Long(oldCplModel.getKaleoInstanceId());

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setKaleoInstanceTokenId",
						new Class[] { Long.TYPE });

				Long value9 = new Long(oldCplModel.getKaleoInstanceTokenId());

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setKaleoTimerId",
						new Class[] { Long.TYPE });

				Long value10 = new Long(oldCplModel.getKaleoTimerId());

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setKaleoTimerName",
						new Class[] { String.class });

				String value11 = oldCplModel.getKaleoTimerName();

				method11.invoke(newModel, value11);

				Method method12 = newModelClass.getMethod("setCompletionUserId",
						new Class[] { Long.TYPE });

				Long value12 = new Long(oldCplModel.getCompletionUserId());

				method12.invoke(newModel, value12);

				Method method13 = newModelClass.getMethod("setCompleted",
						new Class[] { Boolean.TYPE });

				Boolean value13 = new Boolean(oldCplModel.getCompleted());

				method13.invoke(newModel, value13);

				Method method14 = newModelClass.getMethod("setCompletionDate",
						new Class[] { Date.class });

				Date value14 = oldCplModel.getCompletionDate();

				method14.invoke(newModel, value14);

				Method method15 = newModelClass.getMethod("setWorkflowContext",
						new Class[] { String.class });

				String value15 = oldCplModel.getWorkflowContext();

				method15.invoke(newModel, value15);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputKaleoTransition(BaseModel<?> oldModel) {
		KaleoTransitionClp oldCplModel = (KaleoTransitionClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.portal.workflow.kaleo.model.impl.KaleoTransitionImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setKaleoTransitionId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getKaleoTransitionId());

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setGroupId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getGroupId());

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setCompanyId",
						new Class[] { Long.TYPE });

				Long value2 = new Long(oldCplModel.getCompanyId());

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setUserId",
						new Class[] { Long.TYPE });

				Long value3 = new Long(oldCplModel.getUserId());

				method3.invoke(newModel, value3);

				Method method4 = newModelClass.getMethod("setUserName",
						new Class[] { String.class });

				String value4 = oldCplModel.getUserName();

				method4.invoke(newModel, value4);

				Method method5 = newModelClass.getMethod("setCreateDate",
						new Class[] { Date.class });

				Date value5 = oldCplModel.getCreateDate();

				method5.invoke(newModel, value5);

				Method method6 = newModelClass.getMethod("setModifiedDate",
						new Class[] { Date.class });

				Date value6 = oldCplModel.getModifiedDate();

				method6.invoke(newModel, value6);

				Method method7 = newModelClass.getMethod("setKaleoDefinitionId",
						new Class[] { Long.TYPE });

				Long value7 = new Long(oldCplModel.getKaleoDefinitionId());

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setKaleoNodeId",
						new Class[] { Long.TYPE });

				Long value8 = new Long(oldCplModel.getKaleoNodeId());

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setName",
						new Class[] { String.class });

				String value9 = oldCplModel.getName();

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value10 = oldCplModel.getDescription();

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setSourceKaleoNodeId",
						new Class[] { Long.TYPE });

				Long value11 = new Long(oldCplModel.getSourceKaleoNodeId());

				method11.invoke(newModel, value11);

				Method method12 = newModelClass.getMethod("setSourceKaleoNodeName",
						new Class[] { String.class });

				String value12 = oldCplModel.getSourceKaleoNodeName();

				method12.invoke(newModel, value12);

				Method method13 = newModelClass.getMethod("setTargetKaleoNodeId",
						new Class[] { Long.TYPE });

				Long value13 = new Long(oldCplModel.getTargetKaleoNodeId());

				method13.invoke(newModel, value13);

				Method method14 = newModelClass.getMethod("setTargetKaleoNodeName",
						new Class[] { String.class });

				String value14 = oldCplModel.getTargetKaleoNodeName();

				method14.invoke(newModel, value14);

				Method method15 = newModelClass.getMethod("setDefaultTransition",
						new Class[] { Boolean.TYPE });

				Boolean value15 = new Boolean(oldCplModel.getDefaultTransition());

				method15.invoke(newModel, value15);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInput(Object obj) {
		if (obj instanceof BaseModel<?>) {
			return translateInput((BaseModel<?>)obj);
		}
		else if (obj instanceof List<?>) {
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
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoActionImpl")) {
			return translateOutputKaleoAction(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoConditionImpl")) {
			return translateOutputKaleoCondition(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionImpl")) {
			return translateOutputKaleoDefinition(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceImpl")) {
			return translateOutputKaleoInstance(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceTokenImpl")) {
			return translateOutputKaleoInstanceToken(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoLogImpl")) {
			return translateOutputKaleoLog(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoNodeImpl")) {
			return translateOutputKaleoNode(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationImpl")) {
			return translateOutputKaleoNotification(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationRecipientImpl")) {
			return translateOutputKaleoNotificationRecipient(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskImpl")) {
			return translateOutputKaleoTask(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentImpl")) {
			return translateOutputKaleoTaskAssignment(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceImpl")) {
			return translateOutputKaleoTaskAssignmentInstance(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskInstanceTokenImpl")) {
			return translateOutputKaleoTaskInstanceToken(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerImpl")) {
			return translateOutputKaleoTimer(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerInstanceTokenImpl")) {
			return translateOutputKaleoTimerInstanceToken(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoTransitionImpl")) {
			return translateOutputKaleoTransition(oldModel);
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
		else if (obj instanceof List<?>) {
			return translateOutput((List<Object>)obj);
		}
		else {
			return obj;
		}
	}

	public static Object translateOutputKaleoAction(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				KaleoActionClp newModel = new KaleoActionClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getKaleoActionId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setKaleoActionId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getKaleoDefinitionId");

				Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

				newModel.setKaleoDefinitionId(value7);

				Method method8 = oldModelClass.getMethod("getKaleoNodeId");

				Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

				newModel.setKaleoNodeId(value8);

				Method method9 = oldModelClass.getMethod("getKaleoNodeName");

				String value9 = (String)method9.invoke(oldModel, (Object[])null);

				newModel.setKaleoNodeName(value9);

				Method method10 = oldModelClass.getMethod("getName");

				String value10 = (String)method10.invoke(oldModel,
						(Object[])null);

				newModel.setName(value10);

				Method method11 = oldModelClass.getMethod("getDescription");

				String value11 = (String)method11.invoke(oldModel,
						(Object[])null);

				newModel.setDescription(value11);

				Method method12 = oldModelClass.getMethod("getExecutionType");

				String value12 = (String)method12.invoke(oldModel,
						(Object[])null);

				newModel.setExecutionType(value12);

				Method method13 = oldModelClass.getMethod("getScript");

				String value13 = (String)method13.invoke(oldModel,
						(Object[])null);

				newModel.setScript(value13);

				Method method14 = oldModelClass.getMethod("getScriptLanguage");

				String value14 = (String)method14.invoke(oldModel,
						(Object[])null);

				newModel.setScriptLanguage(value14);

				Method method15 = oldModelClass.getMethod("getPriority");

				Integer value15 = (Integer)method15.invoke(oldModel,
						(Object[])null);

				newModel.setPriority(value15);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputKaleoCondition(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				KaleoConditionClp newModel = new KaleoConditionClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getKaleoConditionId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setKaleoConditionId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getClassName");

				String value7 = (String)method7.invoke(oldModel, (Object[])null);

				newModel.setClassName(value7);

				Method method8 = oldModelClass.getMethod("getClassPK");

				Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

				newModel.setClassPK(value8);

				Method method9 = oldModelClass.getMethod("getDescription");

				String value9 = (String)method9.invoke(oldModel, (Object[])null);

				newModel.setDescription(value9);

				Method method10 = oldModelClass.getMethod("getScript");

				String value10 = (String)method10.invoke(oldModel,
						(Object[])null);

				newModel.setScript(value10);

				Method method11 = oldModelClass.getMethod("getScriptLanguage");

				String value11 = (String)method11.invoke(oldModel,
						(Object[])null);

				newModel.setScriptLanguage(value11);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputKaleoDefinition(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				KaleoDefinitionClp newModel = new KaleoDefinitionClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getKaleoDefinitionId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setKaleoDefinitionId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getName");

				String value7 = (String)method7.invoke(oldModel, (Object[])null);

				newModel.setName(value7);

				Method method8 = oldModelClass.getMethod("getTitle");

				String value8 = (String)method8.invoke(oldModel, (Object[])null);

				newModel.setTitle(value8);

				Method method9 = oldModelClass.getMethod("getDescription");

				String value9 = (String)method9.invoke(oldModel, (Object[])null);

				newModel.setDescription(value9);

				Method method10 = oldModelClass.getMethod("getVersion");

				Integer value10 = (Integer)method10.invoke(oldModel,
						(Object[])null);

				newModel.setVersion(value10);

				Method method11 = oldModelClass.getMethod("getActive");

				Boolean value11 = (Boolean)method11.invoke(oldModel,
						(Object[])null);

				newModel.setActive(value11);

				Method method12 = oldModelClass.getMethod("getStartKaleoNodeId");

				Long value12 = (Long)method12.invoke(oldModel, (Object[])null);

				newModel.setStartKaleoNodeId(value12);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputKaleoInstance(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				KaleoInstanceClp newModel = new KaleoInstanceClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getKaleoInstanceId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setKaleoInstanceId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getKaleoDefinitionId");

				Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

				newModel.setKaleoDefinitionId(value7);

				Method method8 = oldModelClass.getMethod(
						"getKaleoDefinitionName");

				String value8 = (String)method8.invoke(oldModel, (Object[])null);

				newModel.setKaleoDefinitionName(value8);

				Method method9 = oldModelClass.getMethod(
						"getKaleoDefinitionVersion");

				Integer value9 = (Integer)method9.invoke(oldModel,
						(Object[])null);

				newModel.setKaleoDefinitionVersion(value9);

				Method method10 = oldModelClass.getMethod(
						"getRootKaleoInstanceTokenId");

				Long value10 = (Long)method10.invoke(oldModel, (Object[])null);

				newModel.setRootKaleoInstanceTokenId(value10);

				Method method11 = oldModelClass.getMethod("getClassName");

				String value11 = (String)method11.invoke(oldModel,
						(Object[])null);

				newModel.setClassName(value11);

				Method method12 = oldModelClass.getMethod("getClassPK");

				Long value12 = (Long)method12.invoke(oldModel, (Object[])null);

				newModel.setClassPK(value12);

				Method method13 = oldModelClass.getMethod("getCompleted");

				Boolean value13 = (Boolean)method13.invoke(oldModel,
						(Object[])null);

				newModel.setCompleted(value13);

				Method method14 = oldModelClass.getMethod("getCompletionDate");

				Date value14 = (Date)method14.invoke(oldModel, (Object[])null);

				newModel.setCompletionDate(value14);

				Method method15 = oldModelClass.getMethod("getWorkflowContext");

				String value15 = (String)method15.invoke(oldModel,
						(Object[])null);

				newModel.setWorkflowContext(value15);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputKaleoInstanceToken(
		BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				KaleoInstanceTokenClp newModel = new KaleoInstanceTokenClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod(
						"getKaleoInstanceTokenId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setKaleoInstanceTokenId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getKaleoDefinitionId");

				Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

				newModel.setKaleoDefinitionId(value7);

				Method method8 = oldModelClass.getMethod("getKaleoInstanceId");

				Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

				newModel.setKaleoInstanceId(value8);

				Method method9 = oldModelClass.getMethod(
						"getParentKaleoInstanceTokenId");

				Long value9 = (Long)method9.invoke(oldModel, (Object[])null);

				newModel.setParentKaleoInstanceTokenId(value9);

				Method method10 = oldModelClass.getMethod(
						"getCurrentKaleoNodeId");

				Long value10 = (Long)method10.invoke(oldModel, (Object[])null);

				newModel.setCurrentKaleoNodeId(value10);

				Method method11 = oldModelClass.getMethod(
						"getCurrentKaleoNodeName");

				String value11 = (String)method11.invoke(oldModel,
						(Object[])null);

				newModel.setCurrentKaleoNodeName(value11);

				Method method12 = oldModelClass.getMethod("getClassName");

				String value12 = (String)method12.invoke(oldModel,
						(Object[])null);

				newModel.setClassName(value12);

				Method method13 = oldModelClass.getMethod("getClassPK");

				Long value13 = (Long)method13.invoke(oldModel, (Object[])null);

				newModel.setClassPK(value13);

				Method method14 = oldModelClass.getMethod("getCompleted");

				Boolean value14 = (Boolean)method14.invoke(oldModel,
						(Object[])null);

				newModel.setCompleted(value14);

				Method method15 = oldModelClass.getMethod("getCompletionDate");

				Date value15 = (Date)method15.invoke(oldModel, (Object[])null);

				newModel.setCompletionDate(value15);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputKaleoLog(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				KaleoLogClp newModel = new KaleoLogClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getKaleoLogId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setKaleoLogId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getKaleoDefinitionId");

				Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

				newModel.setKaleoDefinitionId(value7);

				Method method8 = oldModelClass.getMethod("getKaleoInstanceId");

				Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

				newModel.setKaleoInstanceId(value8);

				Method method9 = oldModelClass.getMethod(
						"getKaleoInstanceTokenId");

				Long value9 = (Long)method9.invoke(oldModel, (Object[])null);

				newModel.setKaleoInstanceTokenId(value9);

				Method method10 = oldModelClass.getMethod(
						"getKaleoTaskInstanceTokenId");

				Long value10 = (Long)method10.invoke(oldModel, (Object[])null);

				newModel.setKaleoTaskInstanceTokenId(value10);

				Method method11 = oldModelClass.getMethod("getKaleoNodeId");

				Long value11 = (Long)method11.invoke(oldModel, (Object[])null);

				newModel.setKaleoNodeId(value11);

				Method method12 = oldModelClass.getMethod("getKaleoNodeName");

				String value12 = (String)method12.invoke(oldModel,
						(Object[])null);

				newModel.setKaleoNodeName(value12);

				Method method13 = oldModelClass.getMethod(
						"getTerminalKaleoNode");

				Boolean value13 = (Boolean)method13.invoke(oldModel,
						(Object[])null);

				newModel.setTerminalKaleoNode(value13);

				Method method14 = oldModelClass.getMethod("getKaleoActionId");

				Long value14 = (Long)method14.invoke(oldModel, (Object[])null);

				newModel.setKaleoActionId(value14);

				Method method15 = oldModelClass.getMethod("getKaleoActionName");

				String value15 = (String)method15.invoke(oldModel,
						(Object[])null);

				newModel.setKaleoActionName(value15);

				Method method16 = oldModelClass.getMethod(
						"getKaleoActionDescription");

				String value16 = (String)method16.invoke(oldModel,
						(Object[])null);

				newModel.setKaleoActionDescription(value16);

				Method method17 = oldModelClass.getMethod(
						"getPreviousKaleoNodeId");

				Long value17 = (Long)method17.invoke(oldModel, (Object[])null);

				newModel.setPreviousKaleoNodeId(value17);

				Method method18 = oldModelClass.getMethod(
						"getPreviousKaleoNodeName");

				String value18 = (String)method18.invoke(oldModel,
						(Object[])null);

				newModel.setPreviousKaleoNodeName(value18);

				Method method19 = oldModelClass.getMethod(
						"getPreviousAssigneeClassName");

				String value19 = (String)method19.invoke(oldModel,
						(Object[])null);

				newModel.setPreviousAssigneeClassName(value19);

				Method method20 = oldModelClass.getMethod(
						"getPreviousAssigneeClassPK");

				Long value20 = (Long)method20.invoke(oldModel, (Object[])null);

				newModel.setPreviousAssigneeClassPK(value20);

				Method method21 = oldModelClass.getMethod(
						"getCurrentAssigneeClassName");

				String value21 = (String)method21.invoke(oldModel,
						(Object[])null);

				newModel.setCurrentAssigneeClassName(value21);

				Method method22 = oldModelClass.getMethod(
						"getCurrentAssigneeClassPK");

				Long value22 = (Long)method22.invoke(oldModel, (Object[])null);

				newModel.setCurrentAssigneeClassPK(value22);

				Method method23 = oldModelClass.getMethod("getType");

				String value23 = (String)method23.invoke(oldModel,
						(Object[])null);

				newModel.setType(value23);

				Method method24 = oldModelClass.getMethod("getComment");

				String value24 = (String)method24.invoke(oldModel,
						(Object[])null);

				newModel.setComment(value24);

				Method method25 = oldModelClass.getMethod("getStartDate");

				Date value25 = (Date)method25.invoke(oldModel, (Object[])null);

				newModel.setStartDate(value25);

				Method method26 = oldModelClass.getMethod("getEndDate");

				Date value26 = (Date)method26.invoke(oldModel, (Object[])null);

				newModel.setEndDate(value26);

				Method method27 = oldModelClass.getMethod("getDuration");

				Long value27 = (Long)method27.invoke(oldModel, (Object[])null);

				newModel.setDuration(value27);

				Method method28 = oldModelClass.getMethod("getWorkflowContext");

				String value28 = (String)method28.invoke(oldModel,
						(Object[])null);

				newModel.setWorkflowContext(value28);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputKaleoNode(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				KaleoNodeClp newModel = new KaleoNodeClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getKaleoNodeId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setKaleoNodeId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getKaleoDefinitionId");

				Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

				newModel.setKaleoDefinitionId(value7);

				Method method8 = oldModelClass.getMethod("getName");

				String value8 = (String)method8.invoke(oldModel, (Object[])null);

				newModel.setName(value8);

				Method method9 = oldModelClass.getMethod("getDescription");

				String value9 = (String)method9.invoke(oldModel, (Object[])null);

				newModel.setDescription(value9);

				Method method10 = oldModelClass.getMethod("getType");

				String value10 = (String)method10.invoke(oldModel,
						(Object[])null);

				newModel.setType(value10);

				Method method11 = oldModelClass.getMethod("getInitial");

				Boolean value11 = (Boolean)method11.invoke(oldModel,
						(Object[])null);

				newModel.setInitial(value11);

				Method method12 = oldModelClass.getMethod("getTerminal");

				Boolean value12 = (Boolean)method12.invoke(oldModel,
						(Object[])null);

				newModel.setTerminal(value12);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputKaleoNotification(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				KaleoNotificationClp newModel = new KaleoNotificationClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod(
						"getKaleoNotificationId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setKaleoNotificationId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getKaleoDefinitionId");

				Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

				newModel.setKaleoDefinitionId(value7);

				Method method8 = oldModelClass.getMethod("getKaleoNodeId");

				Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

				newModel.setKaleoNodeId(value8);

				Method method9 = oldModelClass.getMethod("getKaleoNodeName");

				String value9 = (String)method9.invoke(oldModel, (Object[])null);

				newModel.setKaleoNodeName(value9);

				Method method10 = oldModelClass.getMethod("getName");

				String value10 = (String)method10.invoke(oldModel,
						(Object[])null);

				newModel.setName(value10);

				Method method11 = oldModelClass.getMethod("getDescription");

				String value11 = (String)method11.invoke(oldModel,
						(Object[])null);

				newModel.setDescription(value11);

				Method method12 = oldModelClass.getMethod("getExecutionType");

				String value12 = (String)method12.invoke(oldModel,
						(Object[])null);

				newModel.setExecutionType(value12);

				Method method13 = oldModelClass.getMethod("getTemplate");

				String value13 = (String)method13.invoke(oldModel,
						(Object[])null);

				newModel.setTemplate(value13);

				Method method14 = oldModelClass.getMethod("getTemplateLanguage");

				String value14 = (String)method14.invoke(oldModel,
						(Object[])null);

				newModel.setTemplateLanguage(value14);

				Method method15 = oldModelClass.getMethod(
						"getNotificationTypes");

				String value15 = (String)method15.invoke(oldModel,
						(Object[])null);

				newModel.setNotificationTypes(value15);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputKaleoNotificationRecipient(
		BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				KaleoNotificationRecipientClp newModel = new KaleoNotificationRecipientClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod(
						"getKaleoNotificationRecipientId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setKaleoNotificationRecipientId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getKaleoDefinitionId");

				Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

				newModel.setKaleoDefinitionId(value7);

				Method method8 = oldModelClass.getMethod(
						"getKaleoNotificationId");

				Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

				newModel.setKaleoNotificationId(value8);

				Method method9 = oldModelClass.getMethod(
						"getRecipientClassName");

				String value9 = (String)method9.invoke(oldModel, (Object[])null);

				newModel.setRecipientClassName(value9);

				Method method10 = oldModelClass.getMethod("getRecipientClassPK");

				Long value10 = (Long)method10.invoke(oldModel, (Object[])null);

				newModel.setRecipientClassPK(value10);

				Method method11 = oldModelClass.getMethod(
						"getRecipientRoleType");

				Integer value11 = (Integer)method11.invoke(oldModel,
						(Object[])null);

				newModel.setRecipientRoleType(value11);

				Method method12 = oldModelClass.getMethod("getAddress");

				String value12 = (String)method12.invoke(oldModel,
						(Object[])null);

				newModel.setAddress(value12);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputKaleoTask(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				KaleoTaskClp newModel = new KaleoTaskClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getKaleoTaskId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setKaleoTaskId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getKaleoDefinitionId");

				Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

				newModel.setKaleoDefinitionId(value7);

				Method method8 = oldModelClass.getMethod("getKaleoNodeId");

				Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

				newModel.setKaleoNodeId(value8);

				Method method9 = oldModelClass.getMethod("getName");

				String value9 = (String)method9.invoke(oldModel, (Object[])null);

				newModel.setName(value9);

				Method method10 = oldModelClass.getMethod("getDescription");

				String value10 = (String)method10.invoke(oldModel,
						(Object[])null);

				newModel.setDescription(value10);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputKaleoTaskAssignment(
		BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				KaleoTaskAssignmentClp newModel = new KaleoTaskAssignmentClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod(
						"getKaleoTaskAssignmentId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setKaleoTaskAssignmentId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getKaleoDefinitionId");

				Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

				newModel.setKaleoDefinitionId(value7);

				Method method8 = oldModelClass.getMethod("getKaleoNodeId");

				Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

				newModel.setKaleoNodeId(value8);

				Method method9 = oldModelClass.getMethod("getKaleoTaskId");

				Long value9 = (Long)method9.invoke(oldModel, (Object[])null);

				newModel.setKaleoTaskId(value9);

				Method method10 = oldModelClass.getMethod(
						"getAssigneeClassName");

				String value10 = (String)method10.invoke(oldModel,
						(Object[])null);

				newModel.setAssigneeClassName(value10);

				Method method11 = oldModelClass.getMethod("getAssigneeClassPK");

				Long value11 = (Long)method11.invoke(oldModel, (Object[])null);

				newModel.setAssigneeClassPK(value11);

				Method method12 = oldModelClass.getMethod("getAssigneeActionId");

				String value12 = (String)method12.invoke(oldModel,
						(Object[])null);

				newModel.setAssigneeActionId(value12);

				Method method13 = oldModelClass.getMethod("getAssigneeScript");

				String value13 = (String)method13.invoke(oldModel,
						(Object[])null);

				newModel.setAssigneeScript(value13);

				Method method14 = oldModelClass.getMethod(
						"getAssigneeScriptLanguage");

				String value14 = (String)method14.invoke(oldModel,
						(Object[])null);

				newModel.setAssigneeScriptLanguage(value14);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputKaleoTaskAssignmentInstance(
		BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				KaleoTaskAssignmentInstanceClp newModel = new KaleoTaskAssignmentInstanceClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod(
						"getKaleoTaskAssignmentInstanceId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setKaleoTaskAssignmentInstanceId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getKaleoDefinitionId");

				Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

				newModel.setKaleoDefinitionId(value7);

				Method method8 = oldModelClass.getMethod("getKaleoInstanceId");

				Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

				newModel.setKaleoInstanceId(value8);

				Method method9 = oldModelClass.getMethod(
						"getKaleoInstanceTokenId");

				Long value9 = (Long)method9.invoke(oldModel, (Object[])null);

				newModel.setKaleoInstanceTokenId(value9);

				Method method10 = oldModelClass.getMethod(
						"getKaleoTaskInstanceTokenId");

				Long value10 = (Long)method10.invoke(oldModel, (Object[])null);

				newModel.setKaleoTaskInstanceTokenId(value10);

				Method method11 = oldModelClass.getMethod("getKaleoTaskId");

				Long value11 = (Long)method11.invoke(oldModel, (Object[])null);

				newModel.setKaleoTaskId(value11);

				Method method12 = oldModelClass.getMethod("getKaleoTaskName");

				String value12 = (String)method12.invoke(oldModel,
						(Object[])null);

				newModel.setKaleoTaskName(value12);

				Method method13 = oldModelClass.getMethod(
						"getAssigneeClassName");

				String value13 = (String)method13.invoke(oldModel,
						(Object[])null);

				newModel.setAssigneeClassName(value13);

				Method method14 = oldModelClass.getMethod("getAssigneeClassPK");

				Long value14 = (Long)method14.invoke(oldModel, (Object[])null);

				newModel.setAssigneeClassPK(value14);

				Method method15 = oldModelClass.getMethod("getCompleted");

				Boolean value15 = (Boolean)method15.invoke(oldModel,
						(Object[])null);

				newModel.setCompleted(value15);

				Method method16 = oldModelClass.getMethod("getCompletionDate");

				Date value16 = (Date)method16.invoke(oldModel, (Object[])null);

				newModel.setCompletionDate(value16);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputKaleoTaskInstanceToken(
		BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				KaleoTaskInstanceTokenClp newModel = new KaleoTaskInstanceTokenClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod(
						"getKaleoTaskInstanceTokenId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setKaleoTaskInstanceTokenId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getKaleoDefinitionId");

				Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

				newModel.setKaleoDefinitionId(value7);

				Method method8 = oldModelClass.getMethod("getKaleoInstanceId");

				Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

				newModel.setKaleoInstanceId(value8);

				Method method9 = oldModelClass.getMethod(
						"getKaleoInstanceTokenId");

				Long value9 = (Long)method9.invoke(oldModel, (Object[])null);

				newModel.setKaleoInstanceTokenId(value9);

				Method method10 = oldModelClass.getMethod("getKaleoTaskId");

				Long value10 = (Long)method10.invoke(oldModel, (Object[])null);

				newModel.setKaleoTaskId(value10);

				Method method11 = oldModelClass.getMethod("getKaleoTaskName");

				String value11 = (String)method11.invoke(oldModel,
						(Object[])null);

				newModel.setKaleoTaskName(value11);

				Method method12 = oldModelClass.getMethod("getClassName");

				String value12 = (String)method12.invoke(oldModel,
						(Object[])null);

				newModel.setClassName(value12);

				Method method13 = oldModelClass.getMethod("getClassPK");

				Long value13 = (Long)method13.invoke(oldModel, (Object[])null);

				newModel.setClassPK(value13);

				Method method14 = oldModelClass.getMethod("getCompletionUserId");

				Long value14 = (Long)method14.invoke(oldModel, (Object[])null);

				newModel.setCompletionUserId(value14);

				Method method15 = oldModelClass.getMethod("getCompleted");

				Boolean value15 = (Boolean)method15.invoke(oldModel,
						(Object[])null);

				newModel.setCompleted(value15);

				Method method16 = oldModelClass.getMethod("getCompletionDate");

				Date value16 = (Date)method16.invoke(oldModel, (Object[])null);

				newModel.setCompletionDate(value16);

				Method method17 = oldModelClass.getMethod("getDueDate");

				Date value17 = (Date)method17.invoke(oldModel, (Object[])null);

				newModel.setDueDate(value17);

				Method method18 = oldModelClass.getMethod("getWorkflowContext");

				String value18 = (String)method18.invoke(oldModel,
						(Object[])null);

				newModel.setWorkflowContext(value18);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputKaleoTimer(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				KaleoTimerClp newModel = new KaleoTimerClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getKaleoTimerId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setKaleoTimerId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getKaleoDefinitionId");

				Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

				newModel.setKaleoDefinitionId(value7);

				Method method8 = oldModelClass.getMethod("getKaleoNodeId");

				Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

				newModel.setKaleoNodeId(value8);

				Method method9 = oldModelClass.getMethod("getParentKaleoNodeId");

				Long value9 = (Long)method9.invoke(oldModel, (Object[])null);

				newModel.setParentKaleoNodeId(value9);

				Method method10 = oldModelClass.getMethod("getName");

				String value10 = (String)method10.invoke(oldModel,
						(Object[])null);

				newModel.setName(value10);

				Method method11 = oldModelClass.getMethod("getDefaultTimer");

				Boolean value11 = (Boolean)method11.invoke(oldModel,
						(Object[])null);

				newModel.setDefaultTimer(value11);

				Method method12 = oldModelClass.getMethod("getDescription");

				String value12 = (String)method12.invoke(oldModel,
						(Object[])null);

				newModel.setDescription(value12);

				Method method13 = oldModelClass.getMethod("getDuration");

				Double value13 = (Double)method13.invoke(oldModel,
						(Object[])null);

				newModel.setDuration(value13);

				Method method14 = oldModelClass.getMethod("getScale");

				String value14 = (String)method14.invoke(oldModel,
						(Object[])null);

				newModel.setScale(value14);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputKaleoTimerInstanceToken(
		BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				KaleoTimerInstanceTokenClp newModel = new KaleoTimerInstanceTokenClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod(
						"getKaleoTimerInstanceTokenId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setKaleoTimerInstanceTokenId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getKaleoDefinitionId");

				Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

				newModel.setKaleoDefinitionId(value7);

				Method method8 = oldModelClass.getMethod("getKaleoInstanceId");

				Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

				newModel.setKaleoInstanceId(value8);

				Method method9 = oldModelClass.getMethod(
						"getKaleoInstanceTokenId");

				Long value9 = (Long)method9.invoke(oldModel, (Object[])null);

				newModel.setKaleoInstanceTokenId(value9);

				Method method10 = oldModelClass.getMethod("getKaleoTimerId");

				Long value10 = (Long)method10.invoke(oldModel, (Object[])null);

				newModel.setKaleoTimerId(value10);

				Method method11 = oldModelClass.getMethod("getKaleoTimerName");

				String value11 = (String)method11.invoke(oldModel,
						(Object[])null);

				newModel.setKaleoTimerName(value11);

				Method method12 = oldModelClass.getMethod("getCompletionUserId");

				Long value12 = (Long)method12.invoke(oldModel, (Object[])null);

				newModel.setCompletionUserId(value12);

				Method method13 = oldModelClass.getMethod("getCompleted");

				Boolean value13 = (Boolean)method13.invoke(oldModel,
						(Object[])null);

				newModel.setCompleted(value13);

				Method method14 = oldModelClass.getMethod("getCompletionDate");

				Date value14 = (Date)method14.invoke(oldModel, (Object[])null);

				newModel.setCompletionDate(value14);

				Method method15 = oldModelClass.getMethod("getWorkflowContext");

				String value15 = (String)method15.invoke(oldModel,
						(Object[])null);

				newModel.setWorkflowContext(value15);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputKaleoTransition(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				KaleoTransitionClp newModel = new KaleoTransitionClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getKaleoTransitionId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setKaleoTransitionId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getKaleoDefinitionId");

				Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

				newModel.setKaleoDefinitionId(value7);

				Method method8 = oldModelClass.getMethod("getKaleoNodeId");

				Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

				newModel.setKaleoNodeId(value8);

				Method method9 = oldModelClass.getMethod("getName");

				String value9 = (String)method9.invoke(oldModel, (Object[])null);

				newModel.setName(value9);

				Method method10 = oldModelClass.getMethod("getDescription");

				String value10 = (String)method10.invoke(oldModel,
						(Object[])null);

				newModel.setDescription(value10);

				Method method11 = oldModelClass.getMethod(
						"getSourceKaleoNodeId");

				Long value11 = (Long)method11.invoke(oldModel, (Object[])null);

				newModel.setSourceKaleoNodeId(value11);

				Method method12 = oldModelClass.getMethod(
						"getSourceKaleoNodeName");

				String value12 = (String)method12.invoke(oldModel,
						(Object[])null);

				newModel.setSourceKaleoNodeName(value12);

				Method method13 = oldModelClass.getMethod(
						"getTargetKaleoNodeId");

				Long value13 = (Long)method13.invoke(oldModel, (Object[])null);

				newModel.setTargetKaleoNodeId(value13);

				Method method14 = oldModelClass.getMethod(
						"getTargetKaleoNodeName");

				String value14 = (String)method14.invoke(oldModel,
						(Object[])null);

				newModel.setTargetKaleoNodeName(value14);

				Method method15 = oldModelClass.getMethod(
						"getDefaultTransition");

				Boolean value15 = (Boolean)method15.invoke(oldModel,
						(Object[])null);

				newModel.setDefaultTransition(value15);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	private static Log _log = LogFactoryUtil.getLog(ClpSerializer.class);
	private static ClassLoader _classLoader;
	private static String _servletContextName;
}