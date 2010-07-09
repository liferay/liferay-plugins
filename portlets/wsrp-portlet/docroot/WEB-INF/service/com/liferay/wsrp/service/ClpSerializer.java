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

package com.liferay.wsrp.service;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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
	public static final String SERVLET_CONTEXT_NAME = "wsrp-portlet";

	public static void setClassLoader(ClassLoader classLoader) {
		_classLoader = classLoader;
	}

	public static Object translateInput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(WSRPConsumerClp.class.getName())) {
			WSRPConsumerClp oldCplModel = (WSRPConsumerClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.wsrp.model.impl.WSRPConsumerImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setWsrpConsumerId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getWsrpConsumerId());

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setCompanyId",
							new Class[] { Long.TYPE });

					Long value1 = new Long(oldCplModel.getCompanyId());

					method1.invoke(newModel, value1);

					Method method2 = newModelClass.getMethod("setCreateDate",
							new Class[] { Date.class });

					Date value2 = oldCplModel.getCreateDate();

					method2.invoke(newModel, value2);

					Method method3 = newModelClass.getMethod("setModifiedDate",
							new Class[] { Date.class });

					Date value3 = oldCplModel.getModifiedDate();

					method3.invoke(newModel, value3);

					Method method4 = newModelClass.getMethod("setName",
							new Class[] { String.class });

					String value4 = oldCplModel.getName();

					method4.invoke(newModel, value4);

					Method method5 = newModelClass.getMethod("setUrl",
							new Class[] { String.class });

					String value5 = oldCplModel.getUrl();

					method5.invoke(newModel, value5);

					Method method6 = newModelClass.getMethod("setWsdl",
							new Class[] { String.class });

					String value6 = oldCplModel.getWsdl();

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setRegistrationContextString",
							new Class[] { String.class });

					String value7 = oldCplModel.getRegistrationContextString();

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setRegistrationPropertiesString",
							new Class[] { String.class });

					String value8 = oldCplModel.getRegistrationPropertiesString();

					method8.invoke(newModel, value8);

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

		if (oldModelClassName.equals(WSRPConsumerPortletClp.class.getName())) {
			WSRPConsumerPortletClp oldCplModel = (WSRPConsumerPortletClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.wsrp.model.impl.WSRPConsumerPortletImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setWsrpConsumerPortletId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getWsrpConsumerPortletId());

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setCompanyId",
							new Class[] { Long.TYPE });

					Long value1 = new Long(oldCplModel.getCompanyId());

					method1.invoke(newModel, value1);

					Method method2 = newModelClass.getMethod("setCreateDate",
							new Class[] { Date.class });

					Date value2 = oldCplModel.getCreateDate();

					method2.invoke(newModel, value2);

					Method method3 = newModelClass.getMethod("setModifiedDate",
							new Class[] { Date.class });

					Date value3 = oldCplModel.getModifiedDate();

					method3.invoke(newModel, value3);

					Method method4 = newModelClass.getMethod("setWsrpConsumerId",
							new Class[] { Long.TYPE });

					Long value4 = new Long(oldCplModel.getWsrpConsumerId());

					method4.invoke(newModel, value4);

					Method method5 = newModelClass.getMethod("setName",
							new Class[] { String.class });

					String value5 = oldCplModel.getName();

					method5.invoke(newModel, value5);

					Method method6 = newModelClass.getMethod("setPortletHandle",
							new Class[] { String.class });

					String value6 = oldCplModel.getPortletHandle();

					method6.invoke(newModel, value6);

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

		if (oldModelClassName.equals(WSRPProducerClp.class.getName())) {
			WSRPProducerClp oldCplModel = (WSRPProducerClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.wsrp.model.impl.WSRPProducerImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setWsrpProducerId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getWsrpProducerId());

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setGroupId",
							new Class[] { Long.TYPE });

					Long value1 = new Long(oldCplModel.getGroupId());

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

					Method method6 = newModelClass.getMethod("setPortletIds",
							new Class[] { String.class });

					String value6 = oldCplModel.getPortletIds();

					method6.invoke(newModel, value6);

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
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					WSRPConsumerClp newModel = new WSRPConsumerClp();

					Method method0 = oldModelClass.getMethod(
							"getWsrpConsumerId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setWsrpConsumerId(value0);

					Method method1 = oldModelClass.getMethod("getCompanyId");

					Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

					newModel.setCompanyId(value1);

					Method method2 = oldModelClass.getMethod("getCreateDate");

					Date value2 = (Date)method2.invoke(oldModel, (Object[])null);

					newModel.setCreateDate(value2);

					Method method3 = oldModelClass.getMethod("getModifiedDate");

					Date value3 = (Date)method3.invoke(oldModel, (Object[])null);

					newModel.setModifiedDate(value3);

					Method method4 = oldModelClass.getMethod("getName");

					String value4 = (String)method4.invoke(oldModel,
							(Object[])null);

					newModel.setName(value4);

					Method method5 = oldModelClass.getMethod("getUrl");

					String value5 = (String)method5.invoke(oldModel,
							(Object[])null);

					newModel.setUrl(value5);

					Method method6 = oldModelClass.getMethod("getWsdl");

					String value6 = (String)method6.invoke(oldModel,
							(Object[])null);

					newModel.setWsdl(value6);

					Method method7 = oldModelClass.getMethod(
							"getRegistrationContextString");

					String value7 = (String)method7.invoke(oldModel,
							(Object[])null);

					newModel.setRegistrationContextString(value7);

					Method method8 = oldModelClass.getMethod(
							"getRegistrationPropertiesString");

					String value8 = (String)method8.invoke(oldModel,
							(Object[])null);

					newModel.setRegistrationPropertiesString(value8);

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
					"com.liferay.wsrp.model.impl.WSRPConsumerPortletImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					WSRPConsumerPortletClp newModel = new WSRPConsumerPortletClp();

					Method method0 = oldModelClass.getMethod(
							"getWsrpConsumerPortletId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setWsrpConsumerPortletId(value0);

					Method method1 = oldModelClass.getMethod("getCompanyId");

					Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

					newModel.setCompanyId(value1);

					Method method2 = oldModelClass.getMethod("getCreateDate");

					Date value2 = (Date)method2.invoke(oldModel, (Object[])null);

					newModel.setCreateDate(value2);

					Method method3 = oldModelClass.getMethod("getModifiedDate");

					Date value3 = (Date)method3.invoke(oldModel, (Object[])null);

					newModel.setModifiedDate(value3);

					Method method4 = oldModelClass.getMethod(
							"getWsrpConsumerId");

					Long value4 = (Long)method4.invoke(oldModel, (Object[])null);

					newModel.setWsrpConsumerId(value4);

					Method method5 = oldModelClass.getMethod("getName");

					String value5 = (String)method5.invoke(oldModel,
							(Object[])null);

					newModel.setName(value5);

					Method method6 = oldModelClass.getMethod("getPortletHandle");

					String value6 = (String)method6.invoke(oldModel,
							(Object[])null);

					newModel.setPortletHandle(value6);

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
					"com.liferay.wsrp.model.impl.WSRPProducerImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					WSRPProducerClp newModel = new WSRPProducerClp();

					Method method0 = oldModelClass.getMethod(
							"getWsrpProducerId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setWsrpProducerId(value0);

					Method method1 = oldModelClass.getMethod("getGroupId");

					Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

					newModel.setGroupId(value1);

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

					String value5 = (String)method5.invoke(oldModel,
							(Object[])null);

					newModel.setName(value5);

					Method method6 = oldModelClass.getMethod("getPortletIds");

					String value6 = (String)method6.invoke(oldModel,
							(Object[])null);

					newModel.setPortletIds(value6);

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
		else if (obj instanceof List<?>) {
			return translateOutput((List<Object>)obj);
		}
		else {
			return obj;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ClpSerializer.class);
	private static ClassLoader _classLoader;
}