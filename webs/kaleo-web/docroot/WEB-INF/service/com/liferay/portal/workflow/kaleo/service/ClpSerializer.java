/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.workflow.kaleo.model.KaleoActionClp;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinitionClp;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceClp;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceTokenClp;
import com.liferay.portal.workflow.kaleo.model.KaleoLogClp;
import com.liferay.portal.workflow.kaleo.model.KaleoNodeClp;
import com.liferay.portal.workflow.kaleo.model.KaleoNotificationClp;
import com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipientClp;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentClp;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskClp;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignmentClp;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceTokenClp;
import com.liferay.portal.workflow.kaleo.model.KaleoTransitionClp;

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
	public static final String SERVLET_CONTEXT_NAME = "kaleo-web";

	public static void setClassLoader(ClassLoader classLoader) {
		_classLoader = classLoader;
	}

	public static Object translateInput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(KaleoActionClp.class.getName())) {
			KaleoActionClp oldCplModel = (KaleoActionClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.portal.workflow.kaleo.model.impl.KaleoActionImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setKaleoActionId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getKaleoActionId());

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

					Method method6 = newModelClass.getMethod("setKaleoDefinitionId",
							new Class[] { Long.TYPE });

					Long value6 = new Long(oldCplModel.getKaleoDefinitionId());

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setKaleoNodeId",
							new Class[] { Long.TYPE });

					Long value7 = new Long(oldCplModel.getKaleoNodeId());

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setKaleoNodeName",
							new Class[] { String.class });

					String value8 = oldCplModel.getKaleoNodeName();

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setName",
							new Class[] { String.class });

					String value9 = oldCplModel.getName();

					method9.invoke(newModel, value9);

					Method method10 = newModelClass.getMethod("setDescription",
							new Class[] { String.class });

					String value10 = oldCplModel.getDescription();

					method10.invoke(newModel, value10);

					Method method11 = newModelClass.getMethod("setExecutionType",
							new Class[] { String.class });

					String value11 = oldCplModel.getExecutionType();

					method11.invoke(newModel, value11);

					Method method12 = newModelClass.getMethod("setScript",
							new Class[] { String.class });

					String value12 = oldCplModel.getScript();

					method12.invoke(newModel, value12);

					Method method13 = newModelClass.getMethod("setScriptLanguage",
							new Class[] { String.class });

					String value13 = oldCplModel.getScriptLanguage();

					method13.invoke(newModel, value13);

					Method method14 = newModelClass.getMethod("setPriority",
							new Class[] { Integer.TYPE });

					Integer value14 = new Integer(oldCplModel.getPriority());

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

		if (oldModelClassName.equals(KaleoDefinitionClp.class.getName())) {
			KaleoDefinitionClp oldCplModel = (KaleoDefinitionClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setKaleoDefinitionId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getKaleoDefinitionId());

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

					Method method7 = newModelClass.getMethod("setTitle",
							new Class[] { String.class });

					String value7 = oldCplModel.getTitle();

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setDescription",
							new Class[] { String.class });

					String value8 = oldCplModel.getDescription();

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setVersion",
							new Class[] { Integer.TYPE });

					Integer value9 = new Integer(oldCplModel.getVersion());

					method9.invoke(newModel, value9);

					Method method10 = newModelClass.getMethod("setActive",
							new Class[] { Boolean.TYPE });

					Boolean value10 = new Boolean(oldCplModel.getActive());

					method10.invoke(newModel, value10);

					Method method11 = newModelClass.getMethod("setStartKaleoNodeId",
							new Class[] { Long.TYPE });

					Long value11 = new Long(oldCplModel.getStartKaleoNodeId());

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

		if (oldModelClassName.equals(KaleoInstanceClp.class.getName())) {
			KaleoInstanceClp oldCplModel = (KaleoInstanceClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setKaleoInstanceId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getKaleoInstanceId());

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

					Method method6 = newModelClass.getMethod("setKaleoDefinitionId",
							new Class[] { Long.TYPE });

					Long value6 = new Long(oldCplModel.getKaleoDefinitionId());

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setKaleoDefinitionName",
							new Class[] { String.class });

					String value7 = oldCplModel.getKaleoDefinitionName();

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setKaleoDefinitionVersion",
							new Class[] { Integer.TYPE });

					Integer value8 = new Integer(oldCplModel.getKaleoDefinitionVersion());

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setRootKaleoInstanceTokenId",
							new Class[] { Long.TYPE });

					Long value9 = new Long(oldCplModel.getRootKaleoInstanceTokenId());

					method9.invoke(newModel, value9);

					Method method10 = newModelClass.getMethod("setClassName",
							new Class[] { String.class });

					String value10 = oldCplModel.getClassName();

					method10.invoke(newModel, value10);

					Method method11 = newModelClass.getMethod("setClassPK",
							new Class[] { Long.TYPE });

					Long value11 = new Long(oldCplModel.getClassPK());

					method11.invoke(newModel, value11);

					Method method12 = newModelClass.getMethod("setCompletionDate",
							new Class[] { Date.class });

					Date value12 = oldCplModel.getCompletionDate();

					method12.invoke(newModel, value12);

					Method method13 = newModelClass.getMethod("setContext",
							new Class[] { String.class });

					String value13 = oldCplModel.getContext();

					method13.invoke(newModel, value13);

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

		if (oldModelClassName.equals(KaleoInstanceTokenClp.class.getName())) {
			KaleoInstanceTokenClp oldCplModel = (KaleoInstanceTokenClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceTokenImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setKaleoInstanceTokenId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getKaleoInstanceTokenId());

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

					Method method6 = newModelClass.getMethod("setKaleoInstanceId",
							new Class[] { Long.TYPE });

					Long value6 = new Long(oldCplModel.getKaleoInstanceId());

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setParentKaleoInstanceTokenId",
							new Class[] { Long.TYPE });

					Long value7 = new Long(oldCplModel.getParentKaleoInstanceTokenId());

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setCurrentKaleoNodeId",
							new Class[] { Long.TYPE });

					Long value8 = new Long(oldCplModel.getCurrentKaleoNodeId());

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setCompletionDate",
							new Class[] { Date.class });

					Date value9 = oldCplModel.getCompletionDate();

					method9.invoke(newModel, value9);

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

		if (oldModelClassName.equals(KaleoLogClp.class.getName())) {
			KaleoLogClp oldCplModel = (KaleoLogClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.portal.workflow.kaleo.model.impl.KaleoLogImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setKaleoLogId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getKaleoLogId());

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

					Method method6 = newModelClass.getMethod("setKaleoInstanceId",
							new Class[] { Long.TYPE });

					Long value6 = new Long(oldCplModel.getKaleoInstanceId());

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setKaleoInstanceTokenId",
							new Class[] { Long.TYPE });

					Long value7 = new Long(oldCplModel.getKaleoInstanceTokenId());

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setKaleoTaskInstanceTokenId",
							new Class[] { Long.TYPE });

					Long value8 = new Long(oldCplModel.getKaleoTaskInstanceTokenId());

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setKaleoNodeId",
							new Class[] { Long.TYPE });

					Long value9 = new Long(oldCplModel.getKaleoNodeId());

					method9.invoke(newModel, value9);

					Method method10 = newModelClass.getMethod("setKaleoNodeName",
							new Class[] { String.class });

					String value10 = oldCplModel.getKaleoNodeName();

					method10.invoke(newModel, value10);

					Method method11 = newModelClass.getMethod("setTerminalKaleoNode",
							new Class[] { Boolean.TYPE });

					Boolean value11 = new Boolean(oldCplModel.getTerminalKaleoNode());

					method11.invoke(newModel, value11);

					Method method12 = newModelClass.getMethod("setKaleoActionId",
							new Class[] { Long.TYPE });

					Long value12 = new Long(oldCplModel.getKaleoActionId());

					method12.invoke(newModel, value12);

					Method method13 = newModelClass.getMethod("setKaleoActionName",
							new Class[] { String.class });

					String value13 = oldCplModel.getKaleoActionName();

					method13.invoke(newModel, value13);

					Method method14 = newModelClass.getMethod("setKaleoActionDescription",
							new Class[] { String.class });

					String value14 = oldCplModel.getKaleoActionDescription();

					method14.invoke(newModel, value14);

					Method method15 = newModelClass.getMethod("setPreviousKaleoNodeId",
							new Class[] { Long.TYPE });

					Long value15 = new Long(oldCplModel.getPreviousKaleoNodeId());

					method15.invoke(newModel, value15);

					Method method16 = newModelClass.getMethod("setPreviousKaleoNodeName",
							new Class[] { String.class });

					String value16 = oldCplModel.getPreviousKaleoNodeName();

					method16.invoke(newModel, value16);

					Method method17 = newModelClass.getMethod("setPreviousAssigneeClassName",
							new Class[] { String.class });

					String value17 = oldCplModel.getPreviousAssigneeClassName();

					method17.invoke(newModel, value17);

					Method method18 = newModelClass.getMethod("setPreviousAssigneeClassPK",
							new Class[] { Long.TYPE });

					Long value18 = new Long(oldCplModel.getPreviousAssigneeClassPK());

					method18.invoke(newModel, value18);

					Method method19 = newModelClass.getMethod("setCurrentAssigneeClassName",
							new Class[] { String.class });

					String value19 = oldCplModel.getCurrentAssigneeClassName();

					method19.invoke(newModel, value19);

					Method method20 = newModelClass.getMethod("setCurrentAssigneeClassPK",
							new Class[] { Long.TYPE });

					Long value20 = new Long(oldCplModel.getCurrentAssigneeClassPK());

					method20.invoke(newModel, value20);

					Method method21 = newModelClass.getMethod("setType",
							new Class[] { String.class });

					String value21 = oldCplModel.getType();

					method21.invoke(newModel, value21);

					Method method22 = newModelClass.getMethod("setComment",
							new Class[] { String.class });

					String value22 = oldCplModel.getComment();

					method22.invoke(newModel, value22);

					Method method23 = newModelClass.getMethod("setStartDate",
							new Class[] { Date.class });

					Date value23 = oldCplModel.getStartDate();

					method23.invoke(newModel, value23);

					Method method24 = newModelClass.getMethod("setEndDate",
							new Class[] { Date.class });

					Date value24 = oldCplModel.getEndDate();

					method24.invoke(newModel, value24);

					Method method25 = newModelClass.getMethod("setDuration",
							new Class[] { Long.TYPE });

					Long value25 = new Long(oldCplModel.getDuration());

					method25.invoke(newModel, value25);

					Method method26 = newModelClass.getMethod("setContext",
							new Class[] { String.class });

					String value26 = oldCplModel.getContext();

					method26.invoke(newModel, value26);

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

		if (oldModelClassName.equals(KaleoNodeClp.class.getName())) {
			KaleoNodeClp oldCplModel = (KaleoNodeClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.portal.workflow.kaleo.model.impl.KaleoNodeImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setKaleoNodeId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getKaleoNodeId());

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

					Method method6 = newModelClass.getMethod("setKaleoDefinitionId",
							new Class[] { Long.TYPE });

					Long value6 = new Long(oldCplModel.getKaleoDefinitionId());

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setName",
							new Class[] { String.class });

					String value7 = oldCplModel.getName();

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setDescription",
							new Class[] { String.class });

					String value8 = oldCplModel.getDescription();

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setType",
							new Class[] { String.class });

					String value9 = oldCplModel.getType();

					method9.invoke(newModel, value9);

					Method method10 = newModelClass.getMethod("setInitial",
							new Class[] { Boolean.TYPE });

					Boolean value10 = new Boolean(oldCplModel.getInitial());

					method10.invoke(newModel, value10);

					Method method11 = newModelClass.getMethod("setTerminal",
							new Class[] { Boolean.TYPE });

					Boolean value11 = new Boolean(oldCplModel.getTerminal());

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

		if (oldModelClassName.equals(KaleoNotificationClp.class.getName())) {
			KaleoNotificationClp oldCplModel = (KaleoNotificationClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setKaleoNotificationId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getKaleoNotificationId());

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

					Method method6 = newModelClass.getMethod("setKaleoDefinitionId",
							new Class[] { Long.TYPE });

					Long value6 = new Long(oldCplModel.getKaleoDefinitionId());

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setKaleoNodeId",
							new Class[] { Long.TYPE });

					Long value7 = new Long(oldCplModel.getKaleoNodeId());

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setKaleoNodeName",
							new Class[] { String.class });

					String value8 = oldCplModel.getKaleoNodeName();

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setName",
							new Class[] { String.class });

					String value9 = oldCplModel.getName();

					method9.invoke(newModel, value9);

					Method method10 = newModelClass.getMethod("setDescription",
							new Class[] { String.class });

					String value10 = oldCplModel.getDescription();

					method10.invoke(newModel, value10);

					Method method11 = newModelClass.getMethod("setExecutionType",
							new Class[] { String.class });

					String value11 = oldCplModel.getExecutionType();

					method11.invoke(newModel, value11);

					Method method12 = newModelClass.getMethod("setTemplate",
							new Class[] { String.class });

					String value12 = oldCplModel.getTemplate();

					method12.invoke(newModel, value12);

					Method method13 = newModelClass.getMethod("setTemplateLanguage",
							new Class[] { String.class });

					String value13 = oldCplModel.getTemplateLanguage();

					method13.invoke(newModel, value13);

					Method method14 = newModelClass.getMethod("setNotificationTypes",
							new Class[] { String.class });

					String value14 = oldCplModel.getNotificationTypes();

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

		if (oldModelClassName.equals(
					KaleoNotificationRecipientClp.class.getName())) {
			KaleoNotificationRecipientClp oldCplModel = (KaleoNotificationRecipientClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationRecipientImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setKaleoNotificationRecipientId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getKaleoNotificationRecipientId());

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

					Method method6 = newModelClass.getMethod("setKaleoNotificationId",
							new Class[] { Long.TYPE });

					Long value6 = new Long(oldCplModel.getKaleoNotificationId());

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setRecipientClassName",
							new Class[] { String.class });

					String value7 = oldCplModel.getRecipientClassName();

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setRecipientClassPK",
							new Class[] { Long.TYPE });

					Long value8 = new Long(oldCplModel.getRecipientClassPK());

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setAddress",
							new Class[] { String.class });

					String value9 = oldCplModel.getAddress();

					method9.invoke(newModel, value9);

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

		if (oldModelClassName.equals(KaleoTaskClp.class.getName())) {
			KaleoTaskClp oldCplModel = (KaleoTaskClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setKaleoTaskId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getKaleoTaskId());

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

					Method method6 = newModelClass.getMethod("setKaleoDefinitionId",
							new Class[] { Long.TYPE });

					Long value6 = new Long(oldCplModel.getKaleoDefinitionId());

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setKaleoNodeId",
							new Class[] { Long.TYPE });

					Long value7 = new Long(oldCplModel.getKaleoNodeId());

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setName",
							new Class[] { String.class });

					String value8 = oldCplModel.getName();

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setDescription",
							new Class[] { String.class });

					String value9 = oldCplModel.getDescription();

					method9.invoke(newModel, value9);

					Method method10 = newModelClass.getMethod("setDueDateDuration",
							new Class[] { Double.TYPE });

					Double value10 = new Double(oldCplModel.getDueDateDuration());

					method10.invoke(newModel, value10);

					Method method11 = newModelClass.getMethod("setDueDateScale",
							new Class[] { String.class });

					String value11 = oldCplModel.getDueDateScale();

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

		if (oldModelClassName.equals(KaleoTaskAssignmentClp.class.getName())) {
			KaleoTaskAssignmentClp oldCplModel = (KaleoTaskAssignmentClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setKaleoTaskAssignmentId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getKaleoTaskAssignmentId());

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

					Method method6 = newModelClass.getMethod("setKaleoDefinitionId",
							new Class[] { Long.TYPE });

					Long value6 = new Long(oldCplModel.getKaleoDefinitionId());

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setKaleoNodeId",
							new Class[] { Long.TYPE });

					Long value7 = new Long(oldCplModel.getKaleoNodeId());

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setKaleoTaskId",
							new Class[] { Long.TYPE });

					Long value8 = new Long(oldCplModel.getKaleoTaskId());

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setAssigneeClassName",
							new Class[] { String.class });

					String value9 = oldCplModel.getAssigneeClassName();

					method9.invoke(newModel, value9);

					Method method10 = newModelClass.getMethod("setAssigneeClassPK",
							new Class[] { Long.TYPE });

					Long value10 = new Long(oldCplModel.getAssigneeClassPK());

					method10.invoke(newModel, value10);

					Method method11 = newModelClass.getMethod("setDefaultAssignment",
							new Class[] { Boolean.TYPE });

					Boolean value11 = new Boolean(oldCplModel.getDefaultAssignment());

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

		if (oldModelClassName.equals(
					KaleoTaskInstanceAssignmentClp.class.getName())) {
			KaleoTaskInstanceAssignmentClp oldCplModel = (KaleoTaskInstanceAssignmentClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskInstanceAssignmentImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setKaleoTaskInstanceAssignmentId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getKaleoTaskInstanceAssignmentId());

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

					Method method6 = newModelClass.getMethod("setKaleoTaskInstanceTokenId",
							new Class[] { Long.TYPE });

					Long value6 = new Long(oldCplModel.getKaleoTaskInstanceTokenId());

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setKaleoTaskId",
							new Class[] { Long.TYPE });

					Long value7 = new Long(oldCplModel.getKaleoTaskId());

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setAssigneeClassName",
							new Class[] { String.class });

					String value8 = oldCplModel.getAssigneeClassName();

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setAssigneeClassPK",
							new Class[] { Long.TYPE });

					Long value9 = new Long(oldCplModel.getAssigneeClassPK());

					method9.invoke(newModel, value9);

					Method method10 = newModelClass.getMethod("setCompletionDate",
							new Class[] { Date.class });

					Date value10 = oldCplModel.getCompletionDate();

					method10.invoke(newModel, value10);

					Method method11 = newModelClass.getMethod("setContext",
							new Class[] { String.class });

					String value11 = oldCplModel.getContext();

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

		if (oldModelClassName.equals(KaleoTaskInstanceTokenClp.class.getName())) {
			KaleoTaskInstanceTokenClp oldCplModel = (KaleoTaskInstanceTokenClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskInstanceTokenImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setKaleoTaskInstanceTokenId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getKaleoTaskInstanceTokenId());

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

					Method method6 = newModelClass.getMethod("setKaleoInstanceId",
							new Class[] { Long.TYPE });

					Long value6 = new Long(oldCplModel.getKaleoInstanceId());

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setKaleoInstanceTokenId",
							new Class[] { Long.TYPE });

					Long value7 = new Long(oldCplModel.getKaleoInstanceTokenId());

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setKaleoTaskId",
							new Class[] { Long.TYPE });

					Long value8 = new Long(oldCplModel.getKaleoTaskId());

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setCompletionUserId",
							new Class[] { Long.TYPE });

					Long value9 = new Long(oldCplModel.getCompletionUserId());

					method9.invoke(newModel, value9);

					Method method10 = newModelClass.getMethod("setCompletionDate",
							new Class[] { Date.class });

					Date value10 = oldCplModel.getCompletionDate();

					method10.invoke(newModel, value10);

					Method method11 = newModelClass.getMethod("setDueDate",
							new Class[] { Date.class });

					Date value11 = oldCplModel.getDueDate();

					method11.invoke(newModel, value11);

					Method method12 = newModelClass.getMethod("setContext",
							new Class[] { String.class });

					String value12 = oldCplModel.getContext();

					method12.invoke(newModel, value12);

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

		if (oldModelClassName.equals(KaleoTransitionClp.class.getName())) {
			KaleoTransitionClp oldCplModel = (KaleoTransitionClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.portal.workflow.kaleo.model.impl.KaleoTransitionImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setKaleoTransitionId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getKaleoTransitionId());

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

					Method method6 = newModelClass.getMethod("setKaleoDefinitionId",
							new Class[] { Long.TYPE });

					Long value6 = new Long(oldCplModel.getKaleoDefinitionId());

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setKaleoNodeId",
							new Class[] { Long.TYPE });

					Long value7 = new Long(oldCplModel.getKaleoNodeId());

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setName",
							new Class[] { String.class });

					String value8 = oldCplModel.getName();

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setDescription",
							new Class[] { String.class });

					String value9 = oldCplModel.getDescription();

					method9.invoke(newModel, value9);

					Method method10 = newModelClass.getMethod("setSourceKaleoNodeId",
							new Class[] { Long.TYPE });

					Long value10 = new Long(oldCplModel.getSourceKaleoNodeId());

					method10.invoke(newModel, value10);

					Method method11 = newModelClass.getMethod("setSourceKaleoNodeName",
							new Class[] { String.class });

					String value11 = oldCplModel.getSourceKaleoNodeName();

					method11.invoke(newModel, value11);

					Method method12 = newModelClass.getMethod("setTargetKaleoNodeId",
							new Class[] { Long.TYPE });

					Long value12 = new Long(oldCplModel.getTargetKaleoNodeId());

					method12.invoke(newModel, value12);

					Method method13 = newModelClass.getMethod("setTargetKaleoNodeName",
							new Class[] { String.class });

					String value13 = oldCplModel.getTargetKaleoNodeName();

					method13.invoke(newModel, value13);

					Method method14 = newModelClass.getMethod("setDefaultTransition",
							new Class[] { Boolean.TYPE });

					Boolean value14 = new Boolean(oldCplModel.getDefaultTransition());

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
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoActionImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					KaleoActionClp newModel = new KaleoActionClp();

					Method method0 = oldModelClass.getMethod("getKaleoActionId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setKaleoActionId(value0.longValue());

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

					Method method6 = oldModelClass.getMethod(
							"getKaleoDefinitionId");

					Long value6 = (Long)method6.invoke(oldModel, (Object[])null);

					newModel.setKaleoDefinitionId(value6.longValue());

					Method method7 = oldModelClass.getMethod("getKaleoNodeId");

					Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

					newModel.setKaleoNodeId(value7.longValue());

					Method method8 = oldModelClass.getMethod("getKaleoNodeName");

					String value8 = (String)method8.invoke(oldModel,
							(Object[])null);

					newModel.setKaleoNodeName(value8);

					Method method9 = oldModelClass.getMethod("getName");

					String value9 = (String)method9.invoke(oldModel,
							(Object[])null);

					newModel.setName(value9);

					Method method10 = oldModelClass.getMethod("getDescription");

					String value10 = (String)method10.invoke(oldModel,
							(Object[])null);

					newModel.setDescription(value10);

					Method method11 = oldModelClass.getMethod(
							"getExecutionType");

					String value11 = (String)method11.invoke(oldModel,
							(Object[])null);

					newModel.setExecutionType(value11);

					Method method12 = oldModelClass.getMethod("getScript");

					String value12 = (String)method12.invoke(oldModel,
							(Object[])null);

					newModel.setScript(value12);

					Method method13 = oldModelClass.getMethod(
							"getScriptLanguage");

					String value13 = (String)method13.invoke(oldModel,
							(Object[])null);

					newModel.setScriptLanguage(value13);

					Method method14 = oldModelClass.getMethod("getPriority");

					Integer value14 = (Integer)method14.invoke(oldModel,
							(Object[])null);

					newModel.setPriority(value14.intValue());

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
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					KaleoDefinitionClp newModel = new KaleoDefinitionClp();

					Method method0 = oldModelClass.getMethod(
							"getKaleoDefinitionId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setKaleoDefinitionId(value0.longValue());

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

					Method method7 = oldModelClass.getMethod("getTitle");

					String value7 = (String)method7.invoke(oldModel,
							(Object[])null);

					newModel.setTitle(value7);

					Method method8 = oldModelClass.getMethod("getDescription");

					String value8 = (String)method8.invoke(oldModel,
							(Object[])null);

					newModel.setDescription(value8);

					Method method9 = oldModelClass.getMethod("getVersion");

					Integer value9 = (Integer)method9.invoke(oldModel,
							(Object[])null);

					newModel.setVersion(value9.intValue());

					Method method10 = oldModelClass.getMethod("getActive");

					Boolean value10 = (Boolean)method10.invoke(oldModel,
							(Object[])null);

					newModel.setActive(value10.booleanValue());

					Method method11 = oldModelClass.getMethod(
							"getStartKaleoNodeId");

					Long value11 = (Long)method11.invoke(oldModel,
							(Object[])null);

					newModel.setStartKaleoNodeId(value11.longValue());

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
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					KaleoInstanceClp newModel = new KaleoInstanceClp();

					Method method0 = oldModelClass.getMethod(
							"getKaleoInstanceId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setKaleoInstanceId(value0.longValue());

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

					Method method6 = oldModelClass.getMethod(
							"getKaleoDefinitionId");

					Long value6 = (Long)method6.invoke(oldModel, (Object[])null);

					newModel.setKaleoDefinitionId(value6.longValue());

					Method method7 = oldModelClass.getMethod(
							"getKaleoDefinitionName");

					String value7 = (String)method7.invoke(oldModel,
							(Object[])null);

					newModel.setKaleoDefinitionName(value7);

					Method method8 = oldModelClass.getMethod(
							"getKaleoDefinitionVersion");

					Integer value8 = (Integer)method8.invoke(oldModel,
							(Object[])null);

					newModel.setKaleoDefinitionVersion(value8.intValue());

					Method method9 = oldModelClass.getMethod(
							"getRootKaleoInstanceTokenId");

					Long value9 = (Long)method9.invoke(oldModel, (Object[])null);

					newModel.setRootKaleoInstanceTokenId(value9.longValue());

					Method method10 = oldModelClass.getMethod("getClassName");

					String value10 = (String)method10.invoke(oldModel,
							(Object[])null);

					newModel.setClassName(value10);

					Method method11 = oldModelClass.getMethod("getClassPK");

					Long value11 = (Long)method11.invoke(oldModel,
							(Object[])null);

					newModel.setClassPK(value11.longValue());

					Method method12 = oldModelClass.getMethod(
							"getCompletionDate");

					Date value12 = (Date)method12.invoke(oldModel,
							(Object[])null);

					newModel.setCompletionDate(value12);

					Method method13 = oldModelClass.getMethod("getContext");

					String value13 = (String)method13.invoke(oldModel,
							(Object[])null);

					newModel.setContext(value13);

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
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceTokenImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					KaleoInstanceTokenClp newModel = new KaleoInstanceTokenClp();

					Method method0 = oldModelClass.getMethod(
							"getKaleoInstanceTokenId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setKaleoInstanceTokenId(value0.longValue());

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

					Method method6 = oldModelClass.getMethod(
							"getKaleoInstanceId");

					Long value6 = (Long)method6.invoke(oldModel, (Object[])null);

					newModel.setKaleoInstanceId(value6.longValue());

					Method method7 = oldModelClass.getMethod(
							"getParentKaleoInstanceTokenId");

					Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

					newModel.setParentKaleoInstanceTokenId(value7.longValue());

					Method method8 = oldModelClass.getMethod(
							"getCurrentKaleoNodeId");

					Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

					newModel.setCurrentKaleoNodeId(value8.longValue());

					Method method9 = oldModelClass.getMethod(
							"getCompletionDate");

					Date value9 = (Date)method9.invoke(oldModel, (Object[])null);

					newModel.setCompletionDate(value9);

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
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoLogImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					KaleoLogClp newModel = new KaleoLogClp();

					Method method0 = oldModelClass.getMethod("getKaleoLogId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setKaleoLogId(value0.longValue());

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

					Method method6 = oldModelClass.getMethod(
							"getKaleoInstanceId");

					Long value6 = (Long)method6.invoke(oldModel, (Object[])null);

					newModel.setKaleoInstanceId(value6.longValue());

					Method method7 = oldModelClass.getMethod(
							"getKaleoInstanceTokenId");

					Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

					newModel.setKaleoInstanceTokenId(value7.longValue());

					Method method8 = oldModelClass.getMethod(
							"getKaleoTaskInstanceTokenId");

					Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

					newModel.setKaleoTaskInstanceTokenId(value8.longValue());

					Method method9 = oldModelClass.getMethod("getKaleoNodeId");

					Long value9 = (Long)method9.invoke(oldModel, (Object[])null);

					newModel.setKaleoNodeId(value9.longValue());

					Method method10 = oldModelClass.getMethod(
							"getKaleoNodeName");

					String value10 = (String)method10.invoke(oldModel,
							(Object[])null);

					newModel.setKaleoNodeName(value10);

					Method method11 = oldModelClass.getMethod(
							"getTerminalKaleoNode");

					Boolean value11 = (Boolean)method11.invoke(oldModel,
							(Object[])null);

					newModel.setTerminalKaleoNode(value11.booleanValue());

					Method method12 = oldModelClass.getMethod(
							"getKaleoActionId");

					Long value12 = (Long)method12.invoke(oldModel,
							(Object[])null);

					newModel.setKaleoActionId(value12.longValue());

					Method method13 = oldModelClass.getMethod(
							"getKaleoActionName");

					String value13 = (String)method13.invoke(oldModel,
							(Object[])null);

					newModel.setKaleoActionName(value13);

					Method method14 = oldModelClass.getMethod(
							"getKaleoActionDescription");

					String value14 = (String)method14.invoke(oldModel,
							(Object[])null);

					newModel.setKaleoActionDescription(value14);

					Method method15 = oldModelClass.getMethod(
							"getPreviousKaleoNodeId");

					Long value15 = (Long)method15.invoke(oldModel,
							(Object[])null);

					newModel.setPreviousKaleoNodeId(value15.longValue());

					Method method16 = oldModelClass.getMethod(
							"getPreviousKaleoNodeName");

					String value16 = (String)method16.invoke(oldModel,
							(Object[])null);

					newModel.setPreviousKaleoNodeName(value16);

					Method method17 = oldModelClass.getMethod(
							"getPreviousAssigneeClassName");

					String value17 = (String)method17.invoke(oldModel,
							(Object[])null);

					newModel.setPreviousAssigneeClassName(value17);

					Method method18 = oldModelClass.getMethod(
							"getPreviousAssigneeClassPK");

					Long value18 = (Long)method18.invoke(oldModel,
							(Object[])null);

					newModel.setPreviousAssigneeClassPK(value18.longValue());

					Method method19 = oldModelClass.getMethod(
							"getCurrentAssigneeClassName");

					String value19 = (String)method19.invoke(oldModel,
							(Object[])null);

					newModel.setCurrentAssigneeClassName(value19);

					Method method20 = oldModelClass.getMethod(
							"getCurrentAssigneeClassPK");

					Long value20 = (Long)method20.invoke(oldModel,
							(Object[])null);

					newModel.setCurrentAssigneeClassPK(value20.longValue());

					Method method21 = oldModelClass.getMethod("getType");

					String value21 = (String)method21.invoke(oldModel,
							(Object[])null);

					newModel.setType(value21);

					Method method22 = oldModelClass.getMethod("getComment");

					String value22 = (String)method22.invoke(oldModel,
							(Object[])null);

					newModel.setComment(value22);

					Method method23 = oldModelClass.getMethod("getStartDate");

					Date value23 = (Date)method23.invoke(oldModel,
							(Object[])null);

					newModel.setStartDate(value23);

					Method method24 = oldModelClass.getMethod("getEndDate");

					Date value24 = (Date)method24.invoke(oldModel,
							(Object[])null);

					newModel.setEndDate(value24);

					Method method25 = oldModelClass.getMethod("getDuration");

					Long value25 = (Long)method25.invoke(oldModel,
							(Object[])null);

					newModel.setDuration(value25.longValue());

					Method method26 = oldModelClass.getMethod("getContext");

					String value26 = (String)method26.invoke(oldModel,
							(Object[])null);

					newModel.setContext(value26);

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
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoNodeImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					KaleoNodeClp newModel = new KaleoNodeClp();

					Method method0 = oldModelClass.getMethod("getKaleoNodeId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setKaleoNodeId(value0.longValue());

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

					Method method6 = oldModelClass.getMethod(
							"getKaleoDefinitionId");

					Long value6 = (Long)method6.invoke(oldModel, (Object[])null);

					newModel.setKaleoDefinitionId(value6.longValue());

					Method method7 = oldModelClass.getMethod("getName");

					String value7 = (String)method7.invoke(oldModel,
							(Object[])null);

					newModel.setName(value7);

					Method method8 = oldModelClass.getMethod("getDescription");

					String value8 = (String)method8.invoke(oldModel,
							(Object[])null);

					newModel.setDescription(value8);

					Method method9 = oldModelClass.getMethod("getType");

					String value9 = (String)method9.invoke(oldModel,
							(Object[])null);

					newModel.setType(value9);

					Method method10 = oldModelClass.getMethod("getInitial");

					Boolean value10 = (Boolean)method10.invoke(oldModel,
							(Object[])null);

					newModel.setInitial(value10.booleanValue());

					Method method11 = oldModelClass.getMethod("getTerminal");

					Boolean value11 = (Boolean)method11.invoke(oldModel,
							(Object[])null);

					newModel.setTerminal(value11.booleanValue());

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
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					KaleoNotificationClp newModel = new KaleoNotificationClp();

					Method method0 = oldModelClass.getMethod(
							"getKaleoNotificationId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setKaleoNotificationId(value0.longValue());

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

					Method method6 = oldModelClass.getMethod(
							"getKaleoDefinitionId");

					Long value6 = (Long)method6.invoke(oldModel, (Object[])null);

					newModel.setKaleoDefinitionId(value6.longValue());

					Method method7 = oldModelClass.getMethod("getKaleoNodeId");

					Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

					newModel.setKaleoNodeId(value7.longValue());

					Method method8 = oldModelClass.getMethod("getKaleoNodeName");

					String value8 = (String)method8.invoke(oldModel,
							(Object[])null);

					newModel.setKaleoNodeName(value8);

					Method method9 = oldModelClass.getMethod("getName");

					String value9 = (String)method9.invoke(oldModel,
							(Object[])null);

					newModel.setName(value9);

					Method method10 = oldModelClass.getMethod("getDescription");

					String value10 = (String)method10.invoke(oldModel,
							(Object[])null);

					newModel.setDescription(value10);

					Method method11 = oldModelClass.getMethod(
							"getExecutionType");

					String value11 = (String)method11.invoke(oldModel,
							(Object[])null);

					newModel.setExecutionType(value11);

					Method method12 = oldModelClass.getMethod("getTemplate");

					String value12 = (String)method12.invoke(oldModel,
							(Object[])null);

					newModel.setTemplate(value12);

					Method method13 = oldModelClass.getMethod(
							"getTemplateLanguage");

					String value13 = (String)method13.invoke(oldModel,
							(Object[])null);

					newModel.setTemplateLanguage(value13);

					Method method14 = oldModelClass.getMethod(
							"getNotificationTypes");

					String value14 = (String)method14.invoke(oldModel,
							(Object[])null);

					newModel.setNotificationTypes(value14);

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
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationRecipientImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					KaleoNotificationRecipientClp newModel = new KaleoNotificationRecipientClp();

					Method method0 = oldModelClass.getMethod(
							"getKaleoNotificationRecipientId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setKaleoNotificationRecipientId(value0.longValue());

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

					Method method6 = oldModelClass.getMethod(
							"getKaleoNotificationId");

					Long value6 = (Long)method6.invoke(oldModel, (Object[])null);

					newModel.setKaleoNotificationId(value6.longValue());

					Method method7 = oldModelClass.getMethod(
							"getRecipientClassName");

					String value7 = (String)method7.invoke(oldModel,
							(Object[])null);

					newModel.setRecipientClassName(value7);

					Method method8 = oldModelClass.getMethod(
							"getRecipientClassPK");

					Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

					newModel.setRecipientClassPK(value8.longValue());

					Method method9 = oldModelClass.getMethod("getAddress");

					String value9 = (String)method9.invoke(oldModel,
							(Object[])null);

					newModel.setAddress(value9);

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
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					KaleoTaskClp newModel = new KaleoTaskClp();

					Method method0 = oldModelClass.getMethod("getKaleoTaskId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setKaleoTaskId(value0.longValue());

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

					Method method6 = oldModelClass.getMethod(
							"getKaleoDefinitionId");

					Long value6 = (Long)method6.invoke(oldModel, (Object[])null);

					newModel.setKaleoDefinitionId(value6.longValue());

					Method method7 = oldModelClass.getMethod("getKaleoNodeId");

					Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

					newModel.setKaleoNodeId(value7.longValue());

					Method method8 = oldModelClass.getMethod("getName");

					String value8 = (String)method8.invoke(oldModel,
							(Object[])null);

					newModel.setName(value8);

					Method method9 = oldModelClass.getMethod("getDescription");

					String value9 = (String)method9.invoke(oldModel,
							(Object[])null);

					newModel.setDescription(value9);

					Method method10 = oldModelClass.getMethod(
							"getDueDateDuration");

					Double value10 = (Double)method10.invoke(oldModel,
							(Object[])null);

					newModel.setDueDateDuration(value10.doubleValue());

					Method method11 = oldModelClass.getMethod("getDueDateScale");

					String value11 = (String)method11.invoke(oldModel,
							(Object[])null);

					newModel.setDueDateScale(value11);

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
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					KaleoTaskAssignmentClp newModel = new KaleoTaskAssignmentClp();

					Method method0 = oldModelClass.getMethod(
							"getKaleoTaskAssignmentId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setKaleoTaskAssignmentId(value0.longValue());

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

					Method method6 = oldModelClass.getMethod(
							"getKaleoDefinitionId");

					Long value6 = (Long)method6.invoke(oldModel, (Object[])null);

					newModel.setKaleoDefinitionId(value6.longValue());

					Method method7 = oldModelClass.getMethod("getKaleoNodeId");

					Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

					newModel.setKaleoNodeId(value7.longValue());

					Method method8 = oldModelClass.getMethod("getKaleoTaskId");

					Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

					newModel.setKaleoTaskId(value8.longValue());

					Method method9 = oldModelClass.getMethod(
							"getAssigneeClassName");

					String value9 = (String)method9.invoke(oldModel,
							(Object[])null);

					newModel.setAssigneeClassName(value9);

					Method method10 = oldModelClass.getMethod(
							"getAssigneeClassPK");

					Long value10 = (Long)method10.invoke(oldModel,
							(Object[])null);

					newModel.setAssigneeClassPK(value10.longValue());

					Method method11 = oldModelClass.getMethod(
							"getDefaultAssignment");

					Boolean value11 = (Boolean)method11.invoke(oldModel,
							(Object[])null);

					newModel.setDefaultAssignment(value11.booleanValue());

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
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskInstanceAssignmentImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					KaleoTaskInstanceAssignmentClp newModel = new KaleoTaskInstanceAssignmentClp();

					Method method0 = oldModelClass.getMethod(
							"getKaleoTaskInstanceAssignmentId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setKaleoTaskInstanceAssignmentId(value0.longValue());

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

					Method method6 = oldModelClass.getMethod(
							"getKaleoTaskInstanceTokenId");

					Long value6 = (Long)method6.invoke(oldModel, (Object[])null);

					newModel.setKaleoTaskInstanceTokenId(value6.longValue());

					Method method7 = oldModelClass.getMethod("getKaleoTaskId");

					Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

					newModel.setKaleoTaskId(value7.longValue());

					Method method8 = oldModelClass.getMethod(
							"getAssigneeClassName");

					String value8 = (String)method8.invoke(oldModel,
							(Object[])null);

					newModel.setAssigneeClassName(value8);

					Method method9 = oldModelClass.getMethod(
							"getAssigneeClassPK");

					Long value9 = (Long)method9.invoke(oldModel, (Object[])null);

					newModel.setAssigneeClassPK(value9.longValue());

					Method method10 = oldModelClass.getMethod(
							"getCompletionDate");

					Date value10 = (Date)method10.invoke(oldModel,
							(Object[])null);

					newModel.setCompletionDate(value10);

					Method method11 = oldModelClass.getMethod("getContext");

					String value11 = (String)method11.invoke(oldModel,
							(Object[])null);

					newModel.setContext(value11);

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
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskInstanceTokenImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					KaleoTaskInstanceTokenClp newModel = new KaleoTaskInstanceTokenClp();

					Method method0 = oldModelClass.getMethod(
							"getKaleoTaskInstanceTokenId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setKaleoTaskInstanceTokenId(value0.longValue());

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

					Method method6 = oldModelClass.getMethod(
							"getKaleoInstanceId");

					Long value6 = (Long)method6.invoke(oldModel, (Object[])null);

					newModel.setKaleoInstanceId(value6.longValue());

					Method method7 = oldModelClass.getMethod(
							"getKaleoInstanceTokenId");

					Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

					newModel.setKaleoInstanceTokenId(value7.longValue());

					Method method8 = oldModelClass.getMethod("getKaleoTaskId");

					Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

					newModel.setKaleoTaskId(value8.longValue());

					Method method9 = oldModelClass.getMethod(
							"getCompletionUserId");

					Long value9 = (Long)method9.invoke(oldModel, (Object[])null);

					newModel.setCompletionUserId(value9.longValue());

					Method method10 = oldModelClass.getMethod(
							"getCompletionDate");

					Date value10 = (Date)method10.invoke(oldModel,
							(Object[])null);

					newModel.setCompletionDate(value10);

					Method method11 = oldModelClass.getMethod("getDueDate");

					Date value11 = (Date)method11.invoke(oldModel,
							(Object[])null);

					newModel.setDueDate(value11);

					Method method12 = oldModelClass.getMethod("getContext");

					String value12 = (String)method12.invoke(oldModel,
							(Object[])null);

					newModel.setContext(value12);

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
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoTransitionImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					KaleoTransitionClp newModel = new KaleoTransitionClp();

					Method method0 = oldModelClass.getMethod(
							"getKaleoTransitionId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setKaleoTransitionId(value0.longValue());

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

					Method method6 = oldModelClass.getMethod(
							"getKaleoDefinitionId");

					Long value6 = (Long)method6.invoke(oldModel, (Object[])null);

					newModel.setKaleoDefinitionId(value6.longValue());

					Method method7 = oldModelClass.getMethod("getKaleoNodeId");

					Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

					newModel.setKaleoNodeId(value7.longValue());

					Method method8 = oldModelClass.getMethod("getName");

					String value8 = (String)method8.invoke(oldModel,
							(Object[])null);

					newModel.setName(value8);

					Method method9 = oldModelClass.getMethod("getDescription");

					String value9 = (String)method9.invoke(oldModel,
							(Object[])null);

					newModel.setDescription(value9);

					Method method10 = oldModelClass.getMethod(
							"getSourceKaleoNodeId");

					Long value10 = (Long)method10.invoke(oldModel,
							(Object[])null);

					newModel.setSourceKaleoNodeId(value10.longValue());

					Method method11 = oldModelClass.getMethod(
							"getSourceKaleoNodeName");

					String value11 = (String)method11.invoke(oldModel,
							(Object[])null);

					newModel.setSourceKaleoNodeName(value11);

					Method method12 = oldModelClass.getMethod(
							"getTargetKaleoNodeId");

					Long value12 = (Long)method12.invoke(oldModel,
							(Object[])null);

					newModel.setTargetKaleoNodeId(value12.longValue());

					Method method13 = oldModelClass.getMethod(
							"getTargetKaleoNodeName");

					String value13 = (String)method13.invoke(oldModel,
							(Object[])null);

					newModel.setTargetKaleoNodeName(value13);

					Method method14 = oldModelClass.getMethod(
							"getDefaultTransition");

					Boolean value14 = (Boolean)method14.invoke(oldModel,
							(Object[])null);

					newModel.setDefaultTransition(value14.booleanValue());

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