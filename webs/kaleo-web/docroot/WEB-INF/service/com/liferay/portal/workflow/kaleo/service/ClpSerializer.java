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

package com.liferay.portal.workflow.kaleo.service;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ClassLoaderObjectInputStream;
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

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.lang.reflect.Method;

import java.util.ArrayList;
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
				if (_log.isInfoEnabled()) {
					_log.info(
						"Unable to locate deployment context from portlet properties");
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
					if (_log.isInfoEnabled()) {
						_log.info(
							"Unable to locate deployment context from portal properties");
					}
				}
			}

			if (Validator.isNull(_servletContextName)) {
				_servletContextName = "kaleo-web";
			}

			return _servletContextName;
		}
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
		KaleoActionClp oldClpModel = (KaleoActionClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getKaleoActionRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputKaleoCondition(BaseModel<?> oldModel) {
		KaleoConditionClp oldClpModel = (KaleoConditionClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getKaleoConditionRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputKaleoDefinition(BaseModel<?> oldModel) {
		KaleoDefinitionClp oldClpModel = (KaleoDefinitionClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getKaleoDefinitionRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputKaleoInstance(BaseModel<?> oldModel) {
		KaleoInstanceClp oldClpModel = (KaleoInstanceClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getKaleoInstanceRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputKaleoInstanceToken(BaseModel<?> oldModel) {
		KaleoInstanceTokenClp oldClpModel = (KaleoInstanceTokenClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getKaleoInstanceTokenRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputKaleoLog(BaseModel<?> oldModel) {
		KaleoLogClp oldClpModel = (KaleoLogClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getKaleoLogRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputKaleoNode(BaseModel<?> oldModel) {
		KaleoNodeClp oldClpModel = (KaleoNodeClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getKaleoNodeRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputKaleoNotification(BaseModel<?> oldModel) {
		KaleoNotificationClp oldClpModel = (KaleoNotificationClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getKaleoNotificationRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputKaleoNotificationRecipient(
		BaseModel<?> oldModel) {
		KaleoNotificationRecipientClp oldClpModel = (KaleoNotificationRecipientClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getKaleoNotificationRecipientRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputKaleoTask(BaseModel<?> oldModel) {
		KaleoTaskClp oldClpModel = (KaleoTaskClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getKaleoTaskRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputKaleoTaskAssignment(
		BaseModel<?> oldModel) {
		KaleoTaskAssignmentClp oldClpModel = (KaleoTaskAssignmentClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getKaleoTaskAssignmentRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputKaleoTaskAssignmentInstance(
		BaseModel<?> oldModel) {
		KaleoTaskAssignmentInstanceClp oldClpModel = (KaleoTaskAssignmentInstanceClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getKaleoTaskAssignmentInstanceRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputKaleoTaskInstanceToken(
		BaseModel<?> oldModel) {
		KaleoTaskInstanceTokenClp oldClpModel = (KaleoTaskInstanceTokenClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getKaleoTaskInstanceTokenRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputKaleoTimer(BaseModel<?> oldModel) {
		KaleoTimerClp oldClpModel = (KaleoTimerClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getKaleoTimerRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputKaleoTimerInstanceToken(
		BaseModel<?> oldModel) {
		KaleoTimerInstanceTokenClp oldClpModel = (KaleoTimerInstanceTokenClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getKaleoTimerInstanceTokenRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputKaleoTransition(BaseModel<?> oldModel) {
		KaleoTransitionClp oldClpModel = (KaleoTransitionClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getKaleoTransitionRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
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
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoConditionImpl")) {
			return translateOutputKaleoCondition(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionImpl")) {
			return translateOutputKaleoDefinition(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceImpl")) {
			return translateOutputKaleoInstance(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceTokenImpl")) {
			return translateOutputKaleoInstanceToken(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoLogImpl")) {
			return translateOutputKaleoLog(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoNodeImpl")) {
			return translateOutputKaleoNode(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationImpl")) {
			return translateOutputKaleoNotification(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationRecipientImpl")) {
			return translateOutputKaleoNotificationRecipient(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskImpl")) {
			return translateOutputKaleoTask(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentImpl")) {
			return translateOutputKaleoTaskAssignment(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceImpl")) {
			return translateOutputKaleoTaskAssignmentInstance(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskInstanceTokenImpl")) {
			return translateOutputKaleoTaskInstanceToken(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerImpl")) {
			return translateOutputKaleoTimer(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerInstanceTokenImpl")) {
			return translateOutputKaleoTimerInstanceToken(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.portal.workflow.kaleo.model.impl.KaleoTransitionImpl")) {
			return translateOutputKaleoTransition(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
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
		else if (obj instanceof List<?>) {
			return translateOutput((List<Object>)obj);
		}
		else {
			return obj;
		}
	}

	public static Throwable translateThrowable(Throwable throwable) {
		if (_useReflectionToTranslateThrowable) {
			try {
				UnsyncByteArrayOutputStream unsyncByteArrayOutputStream = new UnsyncByteArrayOutputStream();
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(unsyncByteArrayOutputStream);

				objectOutputStream.writeObject(throwable);

				objectOutputStream.flush();
				objectOutputStream.close();

				UnsyncByteArrayInputStream unsyncByteArrayInputStream = new UnsyncByteArrayInputStream(unsyncByteArrayOutputStream.unsafeGetByteArray(),
						0, unsyncByteArrayOutputStream.size());

				Thread currentThread = Thread.currentThread();

				ClassLoader contextClassLoader = currentThread.getContextClassLoader();

				ObjectInputStream objectInputStream = new ClassLoaderObjectInputStream(unsyncByteArrayInputStream,
						contextClassLoader);

				throwable = (Throwable)objectInputStream.readObject();

				objectInputStream.close();

				return throwable;
			}
			catch (ClassNotFoundException cnfe) {
				if (_log.isInfoEnabled()) {
					_log.info("Do not use reflection to translate throwable");
				}

				_useReflectionToTranslateThrowable = false;
			}
			catch (SecurityException se) {
				if (_log.isInfoEnabled()) {
					_log.info("Do not use reflection to translate throwable");
				}

				_useReflectionToTranslateThrowable = false;
			}
			catch (Throwable throwable2) {
				_log.error(throwable2, throwable2);

				return throwable2;
			}
		}

		Class<?> clazz = throwable.getClass();

		String className = clazz.getName();

		if (className.equals(
					"com.liferay.portal.workflow.kaleo.NoSuchActionException")) {
			return new com.liferay.portal.workflow.kaleo.NoSuchActionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.portal.workflow.kaleo.NoSuchConditionException")) {
			return new com.liferay.portal.workflow.kaleo.NoSuchConditionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.portal.workflow.kaleo.NoSuchDefinitionException")) {
			return new com.liferay.portal.workflow.kaleo.NoSuchDefinitionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.portal.workflow.kaleo.NoSuchInstanceException")) {
			return new com.liferay.portal.workflow.kaleo.NoSuchInstanceException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException")) {
			return new com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.portal.workflow.kaleo.NoSuchLogException")) {
			return new com.liferay.portal.workflow.kaleo.NoSuchLogException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.portal.workflow.kaleo.NoSuchNodeException")) {
			return new com.liferay.portal.workflow.kaleo.NoSuchNodeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.portal.workflow.kaleo.NoSuchNotificationException")) {
			return new com.liferay.portal.workflow.kaleo.NoSuchNotificationException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.portal.workflow.kaleo.NoSuchNotificationRecipientException")) {
			return new com.liferay.portal.workflow.kaleo.NoSuchNotificationRecipientException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.portal.workflow.kaleo.NoSuchTaskException")) {
			return new com.liferay.portal.workflow.kaleo.NoSuchTaskException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException")) {
			return new com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException")) {
			return new com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException")) {
			return new com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.portal.workflow.kaleo.NoSuchTimerException")) {
			return new com.liferay.portal.workflow.kaleo.NoSuchTimerException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.portal.workflow.kaleo.NoSuchTimerInstanceTokenException")) {
			return new com.liferay.portal.workflow.kaleo.NoSuchTimerInstanceTokenException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.portal.workflow.kaleo.NoSuchTransitionException")) {
			return new com.liferay.portal.workflow.kaleo.NoSuchTransitionException(throwable.getMessage(),
				throwable.getCause());
		}

		return throwable;
	}

	public static Object translateOutputKaleoAction(BaseModel<?> oldModel) {
		KaleoActionClp newModel = new KaleoActionClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setKaleoActionRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputKaleoCondition(BaseModel<?> oldModel) {
		KaleoConditionClp newModel = new KaleoConditionClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setKaleoConditionRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputKaleoDefinition(BaseModel<?> oldModel) {
		KaleoDefinitionClp newModel = new KaleoDefinitionClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setKaleoDefinitionRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputKaleoInstance(BaseModel<?> oldModel) {
		KaleoInstanceClp newModel = new KaleoInstanceClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setKaleoInstanceRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputKaleoInstanceToken(
		BaseModel<?> oldModel) {
		KaleoInstanceTokenClp newModel = new KaleoInstanceTokenClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setKaleoInstanceTokenRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputKaleoLog(BaseModel<?> oldModel) {
		KaleoLogClp newModel = new KaleoLogClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setKaleoLogRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputKaleoNode(BaseModel<?> oldModel) {
		KaleoNodeClp newModel = new KaleoNodeClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setKaleoNodeRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputKaleoNotification(BaseModel<?> oldModel) {
		KaleoNotificationClp newModel = new KaleoNotificationClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setKaleoNotificationRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputKaleoNotificationRecipient(
		BaseModel<?> oldModel) {
		KaleoNotificationRecipientClp newModel = new KaleoNotificationRecipientClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setKaleoNotificationRecipientRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputKaleoTask(BaseModel<?> oldModel) {
		KaleoTaskClp newModel = new KaleoTaskClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setKaleoTaskRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputKaleoTaskAssignment(
		BaseModel<?> oldModel) {
		KaleoTaskAssignmentClp newModel = new KaleoTaskAssignmentClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setKaleoTaskAssignmentRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputKaleoTaskAssignmentInstance(
		BaseModel<?> oldModel) {
		KaleoTaskAssignmentInstanceClp newModel = new KaleoTaskAssignmentInstanceClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setKaleoTaskAssignmentInstanceRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputKaleoTaskInstanceToken(
		BaseModel<?> oldModel) {
		KaleoTaskInstanceTokenClp newModel = new KaleoTaskInstanceTokenClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setKaleoTaskInstanceTokenRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputKaleoTimer(BaseModel<?> oldModel) {
		KaleoTimerClp newModel = new KaleoTimerClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setKaleoTimerRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputKaleoTimerInstanceToken(
		BaseModel<?> oldModel) {
		KaleoTimerInstanceTokenClp newModel = new KaleoTimerInstanceTokenClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setKaleoTimerInstanceTokenRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputKaleoTransition(BaseModel<?> oldModel) {
		KaleoTransitionClp newModel = new KaleoTransitionClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setKaleoTransitionRemoteModel(oldModel);

		return newModel;
	}

	private static Log _log = LogFactoryUtil.getLog(ClpSerializer.class);
	private static String _servletContextName;
	private static boolean _useReflectionToTranslateThrowable = true;
}