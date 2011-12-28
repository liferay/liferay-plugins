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

package com.liferay.sampleservicebuilder.service;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;

import com.liferay.sampleservicebuilder.model.FooClp;

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
						"sample-service-builder-portlet-deployment-context");

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
							"sample-service-builder-portlet-deployment-context");

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
				_servletContextName = "sample-service-builder-portlet";
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

		if (oldModelClassName.equals(FooClp.class.getName())) {
			return translateInputFoo(oldModel);
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

	public static Object translateInputFoo(BaseModel<?> oldModel) {
		FooClp oldCplModel = (FooClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.sampleservicebuilder.model.impl.FooImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setUuid",
						new Class[] { String.class });

				String value0 = oldCplModel.getUuid();

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setFooId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getFooId());

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setGroupId",
						new Class[] { Long.TYPE });

				Long value2 = new Long(oldCplModel.getGroupId());

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setCompanyId",
						new Class[] { Long.TYPE });

				Long value3 = new Long(oldCplModel.getCompanyId());

				method3.invoke(newModel, value3);

				Method method4 = newModelClass.getMethod("setUserId",
						new Class[] { Long.TYPE });

				Long value4 = new Long(oldCplModel.getUserId());

				method4.invoke(newModel, value4);

				Method method5 = newModelClass.getMethod("setUserName",
						new Class[] { String.class });

				String value5 = oldCplModel.getUserName();

				method5.invoke(newModel, value5);

				Method method6 = newModelClass.getMethod("setCreateDate",
						new Class[] { Date.class });

				Date value6 = oldCplModel.getCreateDate();

				method6.invoke(newModel, value6);

				Method method7 = newModelClass.getMethod("setModifiedDate",
						new Class[] { Date.class });

				Date value7 = oldCplModel.getModifiedDate();

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setField1",
						new Class[] { String.class });

				String value8 = oldCplModel.getField1();

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setField2",
						new Class[] { Boolean.TYPE });

				Boolean value9 = new Boolean(oldCplModel.getField2());

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setField3",
						new Class[] { Integer.TYPE });

				Integer value10 = new Integer(oldCplModel.getField3());

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setField4",
						new Class[] { Date.class });

				Date value11 = oldCplModel.getField4();

				method11.invoke(newModel, value11);

				Method method12 = newModelClass.getMethod("setField5",
						new Class[] { String.class });

				String value12 = oldCplModel.getField5();

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
					"com.liferay.sampleservicebuilder.model.impl.FooImpl")) {
			return translateOutputFoo(oldModel);
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

	public static Object translateOutputFoo(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				FooClp newModel = new FooClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getUuid");

				String value0 = (String)method0.invoke(oldModel, (Object[])null);

				newModel.setUuid(value0);

				Method method1 = oldModelClass.getMethod("getFooId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setFooId(value1);

				Method method2 = oldModelClass.getMethod("getGroupId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value2);

				Method method3 = oldModelClass.getMethod("getCompanyId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value3);

				Method method4 = oldModelClass.getMethod("getUserId");

				Long value4 = (Long)method4.invoke(oldModel, (Object[])null);

				newModel.setUserId(value4);

				Method method5 = oldModelClass.getMethod("getUserName");

				String value5 = (String)method5.invoke(oldModel, (Object[])null);

				newModel.setUserName(value5);

				Method method6 = oldModelClass.getMethod("getCreateDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value6);

				Method method7 = oldModelClass.getMethod("getModifiedDate");

				Date value7 = (Date)method7.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value7);

				Method method8 = oldModelClass.getMethod("getField1");

				String value8 = (String)method8.invoke(oldModel, (Object[])null);

				newModel.setField1(value8);

				Method method9 = oldModelClass.getMethod("getField2");

				Boolean value9 = (Boolean)method9.invoke(oldModel,
						(Object[])null);

				newModel.setField2(value9);

				Method method10 = oldModelClass.getMethod("getField3");

				Integer value10 = (Integer)method10.invoke(oldModel,
						(Object[])null);

				newModel.setField3(value10);

				Method method11 = oldModelClass.getMethod("getField4");

				Date value11 = (Date)method11.invoke(oldModel, (Object[])null);

				newModel.setField4(value11);

				Method method12 = oldModelClass.getMethod("getField5");

				String value12 = (String)method12.invoke(oldModel,
						(Object[])null);

				newModel.setField5(value12);

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