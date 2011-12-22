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

package com.liferay.opensocial.service;

import com.liferay.opensocial.model.GadgetClp;
import com.liferay.opensocial.model.OAuthConsumerClp;
import com.liferay.opensocial.model.OAuthTokenClp;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;

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
						"opensocial-portlet-deployment-context");

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
							"opensocial-portlet-deployment-context");

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
				_servletContextName = "opensocial-portlet";
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

		if (oldModelClassName.equals(GadgetClp.class.getName())) {
			return translateInputGadget(oldModel);
		}

		if (oldModelClassName.equals(OAuthConsumerClp.class.getName())) {
			return translateInputOAuthConsumer(oldModel);
		}

		if (oldModelClassName.equals(OAuthTokenClp.class.getName())) {
			return translateInputOAuthToken(oldModel);
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

	public static Object translateInputGadget(BaseModel<?> oldModel) {
		GadgetClp oldCplModel = (GadgetClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

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

				Method method7 = newModelClass.getMethod("setPortletCategoryNames",
						new Class[] { String.class });

				String value7 = oldCplModel.getPortletCategoryNames();

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

	public static Object translateInputOAuthConsumer(BaseModel<?> oldModel) {
		OAuthConsumerClp oldCplModel = (OAuthConsumerClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.opensocial.model.impl.OAuthConsumerImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setOAuthConsumerId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getOAuthConsumerId());

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

				Method method4 = newModelClass.getMethod("setGadgetKey",
						new Class[] { String.class });

				String value4 = oldCplModel.getGadgetKey();

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
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputOAuthToken(BaseModel<?> oldModel) {
		OAuthTokenClp oldCplModel = (OAuthTokenClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.opensocial.model.impl.OAuthTokenImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setOAuthTokenId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getOAuthTokenId());

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

				Method method6 = newModelClass.getMethod("setGadgetKey",
						new Class[] { String.class });

				String value6 = oldCplModel.getGadgetKey();

				method6.invoke(newModel, value6);

				Method method7 = newModelClass.getMethod("setServiceName",
						new Class[] { String.class });

				String value7 = oldCplModel.getServiceName();

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setModuleId",
						new Class[] { Long.TYPE });

				Long value8 = new Long(oldCplModel.getModuleId());

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setAccessToken",
						new Class[] { String.class });

				String value9 = oldCplModel.getAccessToken();

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setTokenName",
						new Class[] { String.class });

				String value10 = oldCplModel.getTokenName();

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setTokenSecret",
						new Class[] { String.class });

				String value11 = oldCplModel.getTokenSecret();

				method11.invoke(newModel, value11);

				Method method12 = newModelClass.getMethod("setSessionHandle",
						new Class[] { String.class });

				String value12 = oldCplModel.getSessionHandle();

				method12.invoke(newModel, value12);

				Method method13 = newModelClass.getMethod("setExpiration",
						new Class[] { Long.TYPE });

				Long value13 = new Long(oldCplModel.getExpiration());

				method13.invoke(newModel, value13);

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
					"com.liferay.opensocial.model.impl.GadgetImpl")) {
			return translateOutputGadget(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.opensocial.model.impl.OAuthConsumerImpl")) {
			return translateOutputOAuthConsumer(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.opensocial.model.impl.OAuthTokenImpl")) {
			return translateOutputOAuthToken(oldModel);
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

	public static Object translateOutputGadget(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				GadgetClp newModel = new GadgetClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getUuid");

				String value0 = (String)method0.invoke(oldModel, (Object[])null);

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

				String value5 = (String)method5.invoke(oldModel, (Object[])null);

				newModel.setName(value5);

				Method method6 = oldModelClass.getMethod("getUrl");

				String value6 = (String)method6.invoke(oldModel, (Object[])null);

				newModel.setUrl(value6);

				Method method7 = oldModelClass.getMethod(
						"getPortletCategoryNames");

				String value7 = (String)method7.invoke(oldModel, (Object[])null);

				newModel.setPortletCategoryNames(value7);

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

	public static Object translateOutputOAuthConsumer(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				OAuthConsumerClp newModel = new OAuthConsumerClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getOAuthConsumerId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setOAuthConsumerId(value0);

				Method method1 = oldModelClass.getMethod("getCompanyId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value1);

				Method method2 = oldModelClass.getMethod("getCreateDate");

				Date value2 = (Date)method2.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value2);

				Method method3 = oldModelClass.getMethod("getModifiedDate");

				Date value3 = (Date)method3.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value3);

				Method method4 = oldModelClass.getMethod("getGadgetKey");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setGadgetKey(value4);

				Method method5 = oldModelClass.getMethod("getServiceName");

				String value5 = (String)method5.invoke(oldModel, (Object[])null);

				newModel.setServiceName(value5);

				Method method6 = oldModelClass.getMethod("getConsumerKey");

				String value6 = (String)method6.invoke(oldModel, (Object[])null);

				newModel.setConsumerKey(value6);

				Method method7 = oldModelClass.getMethod("getConsumerSecret");

				String value7 = (String)method7.invoke(oldModel, (Object[])null);

				newModel.setConsumerSecret(value7);

				Method method8 = oldModelClass.getMethod("getKeyType");

				String value8 = (String)method8.invoke(oldModel, (Object[])null);

				newModel.setKeyType(value8);

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

	public static Object translateOutputOAuthToken(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				OAuthTokenClp newModel = new OAuthTokenClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getOAuthTokenId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setOAuthTokenId(value0);

				Method method1 = oldModelClass.getMethod("getCompanyId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value1);

				Method method2 = oldModelClass.getMethod("getUserId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setUserId(value2);

				Method method3 = oldModelClass.getMethod("getUserName");

				String value3 = (String)method3.invoke(oldModel, (Object[])null);

				newModel.setUserName(value3);

				Method method4 = oldModelClass.getMethod("getCreateDate");

				Date value4 = (Date)method4.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value4);

				Method method5 = oldModelClass.getMethod("getModifiedDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value5);

				Method method6 = oldModelClass.getMethod("getGadgetKey");

				String value6 = (String)method6.invoke(oldModel, (Object[])null);

				newModel.setGadgetKey(value6);

				Method method7 = oldModelClass.getMethod("getServiceName");

				String value7 = (String)method7.invoke(oldModel, (Object[])null);

				newModel.setServiceName(value7);

				Method method8 = oldModelClass.getMethod("getModuleId");

				Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

				newModel.setModuleId(value8);

				Method method9 = oldModelClass.getMethod("getAccessToken");

				String value9 = (String)method9.invoke(oldModel, (Object[])null);

				newModel.setAccessToken(value9);

				Method method10 = oldModelClass.getMethod("getTokenName");

				String value10 = (String)method10.invoke(oldModel,
						(Object[])null);

				newModel.setTokenName(value10);

				Method method11 = oldModelClass.getMethod("getTokenSecret");

				String value11 = (String)method11.invoke(oldModel,
						(Object[])null);

				newModel.setTokenSecret(value11);

				Method method12 = oldModelClass.getMethod("getSessionHandle");

				String value12 = (String)method12.invoke(oldModel,
						(Object[])null);

				newModel.setSessionHandle(value12);

				Method method13 = oldModelClass.getMethod("getExpiration");

				Long value13 = (Long)method13.invoke(oldModel, (Object[])null);

				newModel.setExpiration(value13);

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