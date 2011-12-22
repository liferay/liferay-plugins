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

package com.liferay.chat.service;

import com.liferay.chat.model.EntryClp;
import com.liferay.chat.model.StatusClp;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;

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
						"chat-portlet-deployment-context");

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
							"chat-portlet-deployment-context");

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
				_servletContextName = "chat-portlet";
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

		if (oldModelClassName.equals(EntryClp.class.getName())) {
			return translateInputEntry(oldModel);
		}

		if (oldModelClassName.equals(StatusClp.class.getName())) {
			return translateInputStatus(oldModel);
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

	public static Object translateInputEntry(BaseModel<?> oldModel) {
		EntryClp oldCplModel = (EntryClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.chat.model.impl.EntryImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setEntryId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getEntryId());

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setCreateDate",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getCreateDate());

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setFromUserId",
						new Class[] { Long.TYPE });

				Long value2 = new Long(oldCplModel.getFromUserId());

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setToUserId",
						new Class[] { Long.TYPE });

				Long value3 = new Long(oldCplModel.getToUserId());

				method3.invoke(newModel, value3);

				Method method4 = newModelClass.getMethod("setContent",
						new Class[] { String.class });

				String value4 = oldCplModel.getContent();

				method4.invoke(newModel, value4);

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

	public static Object translateInputStatus(BaseModel<?> oldModel) {
		StatusClp oldCplModel = (StatusClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.chat.model.impl.StatusImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setStatusId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getStatusId());

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setUserId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getUserId());

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setModifiedDate",
						new Class[] { Long.TYPE });

				Long value2 = new Long(oldCplModel.getModifiedDate());

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setOnline",
						new Class[] { Boolean.TYPE });

				Boolean value3 = new Boolean(oldCplModel.getOnline());

				method3.invoke(newModel, value3);

				Method method4 = newModelClass.getMethod("setAwake",
						new Class[] { Boolean.TYPE });

				Boolean value4 = new Boolean(oldCplModel.getAwake());

				method4.invoke(newModel, value4);

				Method method5 = newModelClass.getMethod("setActivePanelId",
						new Class[] { String.class });

				String value5 = oldCplModel.getActivePanelId();

				method5.invoke(newModel, value5);

				Method method6 = newModelClass.getMethod("setMessage",
						new Class[] { String.class });

				String value6 = oldCplModel.getMessage();

				method6.invoke(newModel, value6);

				Method method7 = newModelClass.getMethod("setPlaySound",
						new Class[] { Boolean.TYPE });

				Boolean value7 = new Boolean(oldCplModel.getPlaySound());

				method7.invoke(newModel, value7);

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

		if (oldModelClassName.equals("com.liferay.chat.model.impl.EntryImpl")) {
			return translateOutputEntry(oldModel);
		}

		if (oldModelClassName.equals("com.liferay.chat.model.impl.StatusImpl")) {
			return translateOutputStatus(oldModel);
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

	public static Object translateOutputEntry(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				EntryClp newModel = new EntryClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getEntryId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setEntryId(value0);

				Method method1 = oldModelClass.getMethod("getCreateDate");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value1);

				Method method2 = oldModelClass.getMethod("getFromUserId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setFromUserId(value2);

				Method method3 = oldModelClass.getMethod("getToUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setToUserId(value3);

				Method method4 = oldModelClass.getMethod("getContent");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setContent(value4);

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

	public static Object translateOutputStatus(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				StatusClp newModel = new StatusClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getStatusId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setStatusId(value0);

				Method method1 = oldModelClass.getMethod("getUserId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setUserId(value1);

				Method method2 = oldModelClass.getMethod("getModifiedDate");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value2);

				Method method3 = oldModelClass.getMethod("getOnline");

				Boolean value3 = (Boolean)method3.invoke(oldModel,
						(Object[])null);

				newModel.setOnline(value3);

				Method method4 = oldModelClass.getMethod("getAwake");

				Boolean value4 = (Boolean)method4.invoke(oldModel,
						(Object[])null);

				newModel.setAwake(value4);

				Method method5 = oldModelClass.getMethod("getActivePanelId");

				String value5 = (String)method5.invoke(oldModel, (Object[])null);

				newModel.setActivePanelId(value5);

				Method method6 = oldModelClass.getMethod("getMessage");

				String value6 = (String)method6.invoke(oldModel, (Object[])null);

				newModel.setMessage(value6);

				Method method7 = oldModelClass.getMethod("getPlaySound");

				Boolean value7 = (Boolean)method7.invoke(oldModel,
						(Object[])null);

				newModel.setPlaySound(value7);

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