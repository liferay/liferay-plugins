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

package com.liferay.wsrp.service;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;

import com.liferay.wsrp.model.WSRPConsumerClp;
import com.liferay.wsrp.model.WSRPConsumerPortletClp;
import com.liferay.wsrp.model.WSRPProducerClp;

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
						"wsrp-portlet-deployment-context");

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
							"wsrp-portlet-deployment-context");

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
				_servletContextName = "wsrp-portlet";
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

		if (oldModelClassName.equals(WSRPConsumerClp.class.getName())) {
			return translateInputWSRPConsumer(oldModel);
		}

		if (oldModelClassName.equals(WSRPConsumerPortletClp.class.getName())) {
			return translateInputWSRPConsumerPortlet(oldModel);
		}

		if (oldModelClassName.equals(WSRPProducerClp.class.getName())) {
			return translateInputWSRPProducer(oldModel);
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

	public static Object translateInputWSRPConsumer(BaseModel<?> oldModel) {
		WSRPConsumerClp oldCplModel = (WSRPConsumerClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.wsrp.model.impl.WSRPConsumerImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setUuid",
						new Class[] { String.class });

				String value0 = oldCplModel.getUuid();

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setWsrpConsumerId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getWsrpConsumerId());

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setCompanyId",
						new Class[] { Long.TYPE });

				Long value2 = new Long(oldCplModel.getCompanyId());

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setCreateDate",
						new Class[] { Date.class });

				Date value3 = oldCplModel.getCreateDate();

				method3.invoke(newModel, value3);

				Method method4 = newModelClass.getMethod("setModifiedDate",
						new Class[] { Date.class });

				Date value4 = oldCplModel.getModifiedDate();

				method4.invoke(newModel, value4);

				Method method5 = newModelClass.getMethod("setName",
						new Class[] { String.class });

				String value5 = oldCplModel.getName();

				method5.invoke(newModel, value5);

				Method method6 = newModelClass.getMethod("setUrl",
						new Class[] { String.class });

				String value6 = oldCplModel.getUrl();

				method6.invoke(newModel, value6);

				Method method7 = newModelClass.getMethod("setWsdl",
						new Class[] { String.class });

				String value7 = oldCplModel.getWsdl();

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setRegistrationContextString",
						new Class[] { String.class });

				String value8 = oldCplModel.getRegistrationContextString();

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setRegistrationPropertiesString",
						new Class[] { String.class });

				String value9 = oldCplModel.getRegistrationPropertiesString();

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setForwardCookies",
						new Class[] { String.class });

				String value10 = oldCplModel.getForwardCookies();

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

	public static Object translateInputWSRPConsumerPortlet(
		BaseModel<?> oldModel) {
		WSRPConsumerPortletClp oldCplModel = (WSRPConsumerPortletClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.wsrp.model.impl.WSRPConsumerPortletImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setUuid",
						new Class[] { String.class });

				String value0 = oldCplModel.getUuid();

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setWsrpConsumerPortletId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getWsrpConsumerPortletId());

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setCompanyId",
						new Class[] { Long.TYPE });

				Long value2 = new Long(oldCplModel.getCompanyId());

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setCreateDate",
						new Class[] { Date.class });

				Date value3 = oldCplModel.getCreateDate();

				method3.invoke(newModel, value3);

				Method method4 = newModelClass.getMethod("setModifiedDate",
						new Class[] { Date.class });

				Date value4 = oldCplModel.getModifiedDate();

				method4.invoke(newModel, value4);

				Method method5 = newModelClass.getMethod("setWsrpConsumerId",
						new Class[] { Long.TYPE });

				Long value5 = new Long(oldCplModel.getWsrpConsumerId());

				method5.invoke(newModel, value5);

				Method method6 = newModelClass.getMethod("setName",
						new Class[] { String.class });

				String value6 = oldCplModel.getName();

				method6.invoke(newModel, value6);

				Method method7 = newModelClass.getMethod("setPortletHandle",
						new Class[] { String.class });

				String value7 = oldCplModel.getPortletHandle();

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

	public static Object translateInputWSRPProducer(BaseModel<?> oldModel) {
		WSRPProducerClp oldCplModel = (WSRPProducerClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.wsrp.model.impl.WSRPProducerImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setUuid",
						new Class[] { String.class });

				String value0 = oldCplModel.getUuid();

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setWsrpProducerId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getWsrpProducerId());

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setGroupId",
						new Class[] { Long.TYPE });

				Long value2 = new Long(oldCplModel.getGroupId());

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setCompanyId",
						new Class[] { Long.TYPE });

				Long value3 = new Long(oldCplModel.getCompanyId());

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
						new Class[] { String.class });

				String value7 = oldCplModel.getVersion();

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setPortletIds",
						new Class[] { String.class });

				String value8 = oldCplModel.getPortletIds();

				method8.invoke(newModel, value8);

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
					"com.liferay.wsrp.model.impl.WSRPConsumerImpl")) {
			return translateOutputWSRPConsumer(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.wsrp.model.impl.WSRPConsumerPortletImpl")) {
			return translateOutputWSRPConsumerPortlet(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.wsrp.model.impl.WSRPProducerImpl")) {
			return translateOutputWSRPProducer(oldModel);
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

	public static Object translateOutputWSRPConsumer(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				WSRPConsumerClp newModel = new WSRPConsumerClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getUuid");

				String value0 = (String)method0.invoke(oldModel, (Object[])null);

				newModel.setUuid(value0);

				Method method1 = oldModelClass.getMethod("getWsrpConsumerId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setWsrpConsumerId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getCreateDate");

				Date value3 = (Date)method3.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value3);

				Method method4 = oldModelClass.getMethod("getModifiedDate");

				Date value4 = (Date)method4.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value4);

				Method method5 = oldModelClass.getMethod("getName");

				String value5 = (String)method5.invoke(oldModel, (Object[])null);

				newModel.setName(value5);

				Method method6 = oldModelClass.getMethod("getUrl");

				String value6 = (String)method6.invoke(oldModel, (Object[])null);

				newModel.setUrl(value6);

				Method method7 = oldModelClass.getMethod("getWsdl");

				String value7 = (String)method7.invoke(oldModel, (Object[])null);

				newModel.setWsdl(value7);

				Method method8 = oldModelClass.getMethod(
						"getRegistrationContextString");

				String value8 = (String)method8.invoke(oldModel, (Object[])null);

				newModel.setRegistrationContextString(value8);

				Method method9 = oldModelClass.getMethod(
						"getRegistrationPropertiesString");

				String value9 = (String)method9.invoke(oldModel, (Object[])null);

				newModel.setRegistrationPropertiesString(value9);

				Method method10 = oldModelClass.getMethod("getForwardCookies");

				String value10 = (String)method10.invoke(oldModel,
						(Object[])null);

				newModel.setForwardCookies(value10);

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

	public static Object translateOutputWSRPConsumerPortlet(
		BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				WSRPConsumerPortletClp newModel = new WSRPConsumerPortletClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getUuid");

				String value0 = (String)method0.invoke(oldModel, (Object[])null);

				newModel.setUuid(value0);

				Method method1 = oldModelClass.getMethod(
						"getWsrpConsumerPortletId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setWsrpConsumerPortletId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getCreateDate");

				Date value3 = (Date)method3.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value3);

				Method method4 = oldModelClass.getMethod("getModifiedDate");

				Date value4 = (Date)method4.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value4);

				Method method5 = oldModelClass.getMethod("getWsrpConsumerId");

				Long value5 = (Long)method5.invoke(oldModel, (Object[])null);

				newModel.setWsrpConsumerId(value5);

				Method method6 = oldModelClass.getMethod("getName");

				String value6 = (String)method6.invoke(oldModel, (Object[])null);

				newModel.setName(value6);

				Method method7 = oldModelClass.getMethod("getPortletHandle");

				String value7 = (String)method7.invoke(oldModel, (Object[])null);

				newModel.setPortletHandle(value7);

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

	public static Object translateOutputWSRPProducer(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				WSRPProducerClp newModel = new WSRPProducerClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getUuid");

				String value0 = (String)method0.invoke(oldModel, (Object[])null);

				newModel.setUuid(value0);

				Method method1 = oldModelClass.getMethod("getWsrpProducerId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setWsrpProducerId(value1);

				Method method2 = oldModelClass.getMethod("getGroupId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value2);

				Method method3 = oldModelClass.getMethod("getCompanyId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value3);

				Method method4 = oldModelClass.getMethod("getCreateDate");

				Date value4 = (Date)method4.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value4);

				Method method5 = oldModelClass.getMethod("getModifiedDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value5);

				Method method6 = oldModelClass.getMethod("getName");

				String value6 = (String)method6.invoke(oldModel, (Object[])null);

				newModel.setName(value6);

				Method method7 = oldModelClass.getMethod("getVersion");

				String value7 = (String)method7.invoke(oldModel, (Object[])null);

				newModel.setVersion(value7);

				Method method8 = oldModelClass.getMethod("getPortletIds");

				String value8 = (String)method8.invoke(oldModel, (Object[])null);

				newModel.setPortletIds(value8);

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