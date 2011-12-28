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

package com.liferay.socialnetworking.service;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;

import com.liferay.socialnetworking.model.MeetupsEntryClp;
import com.liferay.socialnetworking.model.MeetupsRegistrationClp;
import com.liferay.socialnetworking.model.WallEntryClp;

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
						"social-networking-portlet-deployment-context");

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
							"social-networking-portlet-deployment-context");

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
				_servletContextName = "social-networking-portlet";
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

		if (oldModelClassName.equals(MeetupsEntryClp.class.getName())) {
			return translateInputMeetupsEntry(oldModel);
		}

		if (oldModelClassName.equals(MeetupsRegistrationClp.class.getName())) {
			return translateInputMeetupsRegistration(oldModel);
		}

		if (oldModelClassName.equals(WallEntryClp.class.getName())) {
			return translateInputWallEntry(oldModel);
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

	public static Object translateInputMeetupsEntry(BaseModel<?> oldModel) {
		MeetupsEntryClp oldCplModel = (MeetupsEntryClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.socialnetworking.model.impl.MeetupsEntryImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setMeetupsEntryId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getMeetupsEntryId());

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

				Method method6 = newModelClass.getMethod("setTitle",
						new Class[] { String.class });

				String value6 = oldCplModel.getTitle();

				method6.invoke(newModel, value6);

				Method method7 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value7 = oldCplModel.getDescription();

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setStartDate",
						new Class[] { Date.class });

				Date value8 = oldCplModel.getStartDate();

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setEndDate",
						new Class[] { Date.class });

				Date value9 = oldCplModel.getEndDate();

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setTotalAttendees",
						new Class[] { Integer.TYPE });

				Integer value10 = new Integer(oldCplModel.getTotalAttendees());

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setMaxAttendees",
						new Class[] { Integer.TYPE });

				Integer value11 = new Integer(oldCplModel.getMaxAttendees());

				method11.invoke(newModel, value11);

				Method method12 = newModelClass.getMethod("setPrice",
						new Class[] { Double.TYPE });

				Double value12 = new Double(oldCplModel.getPrice());

				method12.invoke(newModel, value12);

				Method method13 = newModelClass.getMethod("setThumbnailId",
						new Class[] { Long.TYPE });

				Long value13 = new Long(oldCplModel.getThumbnailId());

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

	public static Object translateInputMeetupsRegistration(
		BaseModel<?> oldModel) {
		MeetupsRegistrationClp oldCplModel = (MeetupsRegistrationClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.socialnetworking.model.impl.MeetupsRegistrationImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setMeetupsRegistrationId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getMeetupsRegistrationId());

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

				Method method6 = newModelClass.getMethod("setMeetupsEntryId",
						new Class[] { Long.TYPE });

				Long value6 = new Long(oldCplModel.getMeetupsEntryId());

				method6.invoke(newModel, value6);

				Method method7 = newModelClass.getMethod("setStatus",
						new Class[] { Integer.TYPE });

				Integer value7 = new Integer(oldCplModel.getStatus());

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setComments",
						new Class[] { String.class });

				String value8 = oldCplModel.getComments();

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

	public static Object translateInputWallEntry(BaseModel<?> oldModel) {
		WallEntryClp oldCplModel = (WallEntryClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.socialnetworking.model.impl.WallEntryImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setWallEntryId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getWallEntryId());

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

				Method method7 = newModelClass.getMethod("setComments",
						new Class[] { String.class });

				String value7 = oldCplModel.getComments();

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

		if (oldModelClassName.equals(
					"com.liferay.socialnetworking.model.impl.MeetupsEntryImpl")) {
			return translateOutputMeetupsEntry(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.socialnetworking.model.impl.MeetupsRegistrationImpl")) {
			return translateOutputMeetupsRegistration(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.socialnetworking.model.impl.WallEntryImpl")) {
			return translateOutputWallEntry(oldModel);
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

	public static Object translateOutputMeetupsEntry(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				MeetupsEntryClp newModel = new MeetupsEntryClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getMeetupsEntryId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setMeetupsEntryId(value0);

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

				Method method6 = oldModelClass.getMethod("getTitle");

				String value6 = (String)method6.invoke(oldModel, (Object[])null);

				newModel.setTitle(value6);

				Method method7 = oldModelClass.getMethod("getDescription");

				String value7 = (String)method7.invoke(oldModel, (Object[])null);

				newModel.setDescription(value7);

				Method method8 = oldModelClass.getMethod("getStartDate");

				Date value8 = (Date)method8.invoke(oldModel, (Object[])null);

				newModel.setStartDate(value8);

				Method method9 = oldModelClass.getMethod("getEndDate");

				Date value9 = (Date)method9.invoke(oldModel, (Object[])null);

				newModel.setEndDate(value9);

				Method method10 = oldModelClass.getMethod("getTotalAttendees");

				Integer value10 = (Integer)method10.invoke(oldModel,
						(Object[])null);

				newModel.setTotalAttendees(value10);

				Method method11 = oldModelClass.getMethod("getMaxAttendees");

				Integer value11 = (Integer)method11.invoke(oldModel,
						(Object[])null);

				newModel.setMaxAttendees(value11);

				Method method12 = oldModelClass.getMethod("getPrice");

				Double value12 = (Double)method12.invoke(oldModel,
						(Object[])null);

				newModel.setPrice(value12);

				Method method13 = oldModelClass.getMethod("getThumbnailId");

				Long value13 = (Long)method13.invoke(oldModel, (Object[])null);

				newModel.setThumbnailId(value13);

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

	public static Object translateOutputMeetupsRegistration(
		BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				MeetupsRegistrationClp newModel = new MeetupsRegistrationClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod(
						"getMeetupsRegistrationId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setMeetupsRegistrationId(value0);

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

				Method method6 = oldModelClass.getMethod("getMeetupsEntryId");

				Long value6 = (Long)method6.invoke(oldModel, (Object[])null);

				newModel.setMeetupsEntryId(value6);

				Method method7 = oldModelClass.getMethod("getStatus");

				Integer value7 = (Integer)method7.invoke(oldModel,
						(Object[])null);

				newModel.setStatus(value7);

				Method method8 = oldModelClass.getMethod("getComments");

				String value8 = (String)method8.invoke(oldModel, (Object[])null);

				newModel.setComments(value8);

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

	public static Object translateOutputWallEntry(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				WallEntryClp newModel = new WallEntryClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getWallEntryId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setWallEntryId(value0);

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

				Method method7 = oldModelClass.getMethod("getComments");

				String value7 = (String)method7.invoke(oldModel, (Object[])null);

				newModel.setComments(value7);

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