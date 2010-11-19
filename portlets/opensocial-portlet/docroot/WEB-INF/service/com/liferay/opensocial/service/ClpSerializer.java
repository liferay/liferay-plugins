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

package com.liferay.opensocial.service;

import com.liferay.opensocial.model.GadgetClp;
import com.liferay.opensocial.model.OAuthConsumerClp;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.BaseModel;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class ClpSerializer {
	public static final String SERVLET_CONTEXT_NAME = "opensocial-portlet";

	public static void setClassLoader(ClassLoader classLoader) {
		_classLoader = classLoader;
	}

	public static Object translateInput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(GadgetClp.class.getName())) {
			GadgetClp oldCplModel = (GadgetClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.opensocial.model.impl.GadgetImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setUuid",
							new Class[] { String.class });

					String value0 = oldCplModel.getUuid();

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setGadgetId",
							new Class[] { Long.TYPE });

					Long value1 = new Long(oldCplModel.getGadgetId());

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

		if (oldModelClassName.equals(OAuthConsumerClp.class.getName())) {
			OAuthConsumerClp oldCplModel = (OAuthConsumerClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.opensocial.model.impl.OAuthConsumerImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setOauthConsumerId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getOauthConsumerId());

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

					Method method4 = newModelClass.getMethod("setGadgetId",
							new Class[] { Long.TYPE });

					Long value4 = new Long(oldCplModel.getGadgetId());

					method4.invoke(newModel, value4);

					Method method5 = newModelClass.getMethod("setServiceName",
							new Class[] { String.class });

					String value5 = oldCplModel.getServiceName();

					method5.invoke(newModel, value5);

					Method method6 = newModelClass.getMethod("setConsumerKey",
							new Class[] { String.class });

					String value6 = oldCplModel.getConsumerKey();

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setConsumerSecret",
							new Class[] { String.class });

					String value7 = oldCplModel.getConsumerSecret();

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setKeyType",
							new Class[] { String.class });

					String value8 = oldCplModel.getKeyType();

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
					"com.liferay.opensocial.model.impl.GadgetImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					GadgetClp newModel = new GadgetClp();

					Method method0 = oldModelClass.getMethod("getUuid");

					String value0 = (String)method0.invoke(oldModel,
							(Object[])null);

					newModel.setUuid(value0);

					Method method1 = oldModelClass.getMethod("getGadgetId");

					Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

					newModel.setGadgetId(value1);

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

					Method method6 = oldModelClass.getMethod("getUrl");

					String value6 = (String)method6.invoke(oldModel,
							(Object[])null);

					newModel.setUrl(value6);

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
					"com.liferay.opensocial.model.impl.OAuthConsumerImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					OAuthConsumerClp newModel = new OAuthConsumerClp();

					Method method0 = oldModelClass.getMethod(
							"getOauthConsumerId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setOauthConsumerId(value0);

					Method method1 = oldModelClass.getMethod("getCompanyId");

					Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

					newModel.setCompanyId(value1);

					Method method2 = oldModelClass.getMethod("getCreateDate");

					Date value2 = (Date)method2.invoke(oldModel, (Object[])null);

					newModel.setCreateDate(value2);

					Method method3 = oldModelClass.getMethod("getModifiedDate");

					Date value3 = (Date)method3.invoke(oldModel, (Object[])null);

					newModel.setModifiedDate(value3);

					Method method4 = oldModelClass.getMethod("getGadgetId");

					Long value4 = (Long)method4.invoke(oldModel, (Object[])null);

					newModel.setGadgetId(value4);

					Method method5 = oldModelClass.getMethod("getServiceName");

					String value5 = (String)method5.invoke(oldModel,
							(Object[])null);

					newModel.setServiceName(value5);

					Method method6 = oldModelClass.getMethod("getConsumerKey");

					String value6 = (String)method6.invoke(oldModel,
							(Object[])null);

					newModel.setConsumerKey(value6);

					Method method7 = oldModelClass.getMethod(
							"getConsumerSecret");

					String value7 = (String)method7.invoke(oldModel,
							(Object[])null);

					newModel.setConsumerSecret(value7);

					Method method8 = oldModelClass.getMethod("getKeyType");

					String value8 = (String)method8.invoke(oldModel,
							(Object[])null);

					newModel.setKeyType(value8);

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