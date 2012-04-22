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

package com.liferay.calendar.service;

import com.liferay.calendar.model.CalendarBookingClp;
import com.liferay.calendar.model.CalendarClp;
import com.liferay.calendar.model.CalendarResourceClp;

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
						"enterprise-calendar-portlet-deployment-context");

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
							"enterprise-calendar-portlet-deployment-context");

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
				_servletContextName = "enterprise-calendar-portlet";
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

		if (oldModelClassName.equals(CalendarClp.class.getName())) {
			return translateInputCalendar(oldModel);
		}

		if (oldModelClassName.equals(CalendarBookingClp.class.getName())) {
			return translateInputCalendarBooking(oldModel);
		}

		if (oldModelClassName.equals(CalendarResourceClp.class.getName())) {
			return translateInputCalendarResource(oldModel);
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

	public static Object translateInputCalendar(BaseModel<?> oldModel) {
		CalendarClp oldCplModel = (CalendarClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.calendar.model.impl.CalendarImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setUuid",
						new Class[] { String.class });

				String value0 = oldCplModel.getUuid();

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setCalendarId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getCalendarId());

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

				Method method8 = newModelClass.getMethod("setResourceBlockId",
						new Class[] { Long.TYPE });

				Long value8 = new Long(oldCplModel.getResourceBlockId());

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setCalendarResourceId",
						new Class[] { Long.TYPE });

				Long value9 = new Long(oldCplModel.getCalendarResourceId());

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setName",
						new Class[] { String.class });

				String value10 = oldCplModel.getName();

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value11 = oldCplModel.getDescription();

				method11.invoke(newModel, value11);

				Method method12 = newModelClass.getMethod("setColor",
						new Class[] { Integer.TYPE });

				Integer value12 = new Integer(oldCplModel.getColor());

				method12.invoke(newModel, value12);

				Method method13 = newModelClass.getMethod("setDefaultCalendar",
						new Class[] { Boolean.TYPE });

				Boolean value13 = new Boolean(oldCplModel.getDefaultCalendar());

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

	public static Object translateInputCalendarBooking(BaseModel<?> oldModel) {
		CalendarBookingClp oldCplModel = (CalendarBookingClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.calendar.model.impl.CalendarBookingImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setUuid",
						new Class[] { String.class });

				String value0 = oldCplModel.getUuid();

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setCalendarBookingId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getCalendarBookingId());

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

				Method method8 = newModelClass.getMethod("setCalendarId",
						new Class[] { Long.TYPE });

				Long value8 = new Long(oldCplModel.getCalendarId());

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setCalendarResourceId",
						new Class[] { Long.TYPE });

				Long value9 = new Long(oldCplModel.getCalendarResourceId());

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setParentCalendarBookingId",
						new Class[] { Long.TYPE });

				Long value10 = new Long(oldCplModel.getParentCalendarBookingId());

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setTitle",
						new Class[] { String.class });

				String value11 = oldCplModel.getTitle();

				method11.invoke(newModel, value11);

				Method method12 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value12 = oldCplModel.getDescription();

				method12.invoke(newModel, value12);

				Method method13 = newModelClass.getMethod("setLocation",
						new Class[] { String.class });

				String value13 = oldCplModel.getLocation();

				method13.invoke(newModel, value13);

				Method method14 = newModelClass.getMethod("setType",
						new Class[] { String.class });

				String value14 = oldCplModel.getType();

				method14.invoke(newModel, value14);

				Method method15 = newModelClass.getMethod("setStartDate",
						new Class[] { Date.class });

				Date value15 = oldCplModel.getStartDate();

				method15.invoke(newModel, value15);

				Method method16 = newModelClass.getMethod("setStartTimeZone",
						new Class[] { String.class });

				String value16 = oldCplModel.getStartTimeZone();

				method16.invoke(newModel, value16);

				Method method17 = newModelClass.getMethod("setEndDate",
						new Class[] { Date.class });

				Date value17 = oldCplModel.getEndDate();

				method17.invoke(newModel, value17);

				Method method18 = newModelClass.getMethod("setEndDateTimeZone",
						new Class[] { String.class });

				String value18 = oldCplModel.getEndDateTimeZone();

				method18.invoke(newModel, value18);

				Method method19 = newModelClass.getMethod("setAllDay",
						new Class[] { Boolean.TYPE });

				Boolean value19 = new Boolean(oldCplModel.getAllDay());

				method19.invoke(newModel, value19);

				Method method20 = newModelClass.getMethod("setRecurrence",
						new Class[] { String.class });

				String value20 = oldCplModel.getRecurrence();

				method20.invoke(newModel, value20);

				Method method21 = newModelClass.getMethod("setPriority",
						new Class[] { Integer.TYPE });

				Integer value21 = new Integer(oldCplModel.getPriority());

				method21.invoke(newModel, value21);

				Method method22 = newModelClass.getMethod("setOutOfOffice",
						new Class[] { Boolean.TYPE });

				Boolean value22 = new Boolean(oldCplModel.getOutOfOffice());

				method22.invoke(newModel, value22);

				Method method23 = newModelClass.getMethod("setRemindBy",
						new Class[] { Integer.TYPE });

				Integer value23 = new Integer(oldCplModel.getRemindBy());

				method23.invoke(newModel, value23);

				Method method24 = newModelClass.getMethod("setFirstReminder",
						new Class[] { Integer.TYPE });

				Integer value24 = new Integer(oldCplModel.getFirstReminder());

				method24.invoke(newModel, value24);

				Method method25 = newModelClass.getMethod("setSecondReminder",
						new Class[] { Integer.TYPE });

				Integer value25 = new Integer(oldCplModel.getSecondReminder());

				method25.invoke(newModel, value25);

				Method method26 = newModelClass.getMethod("setRequired",
						new Class[] { Boolean.TYPE });

				Boolean value26 = new Boolean(oldCplModel.getRequired());

				method26.invoke(newModel, value26);

				Method method27 = newModelClass.getMethod("setRequestMessage",
						new Class[] { String.class });

				String value27 = oldCplModel.getRequestMessage();

				method27.invoke(newModel, value27);

				Method method28 = newModelClass.getMethod("setResponseMessage",
						new Class[] { String.class });

				String value28 = oldCplModel.getResponseMessage();

				method28.invoke(newModel, value28);

				Method method29 = newModelClass.getMethod("setStatus",
						new Class[] { Integer.TYPE });

				Integer value29 = new Integer(oldCplModel.getStatus());

				method29.invoke(newModel, value29);

				Method method30 = newModelClass.getMethod("setStatusByUserId",
						new Class[] { Long.TYPE });

				Long value30 = new Long(oldCplModel.getStatusByUserId());

				method30.invoke(newModel, value30);

				Method method31 = newModelClass.getMethod("setStatusByUserName",
						new Class[] { String.class });

				String value31 = oldCplModel.getStatusByUserName();

				method31.invoke(newModel, value31);

				Method method32 = newModelClass.getMethod("setStatusDate",
						new Class[] { Date.class });

				Date value32 = oldCplModel.getStatusDate();

				method32.invoke(newModel, value32);

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

	public static Object translateInputCalendarResource(BaseModel<?> oldModel) {
		CalendarResourceClp oldCplModel = (CalendarResourceClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.calendar.model.impl.CalendarResourceImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setUuid",
						new Class[] { String.class });

				String value0 = oldCplModel.getUuid();

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setCalendarResourceId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getCalendarResourceId());

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

				Method method8 = newModelClass.getMethod("setResourceBlockId",
						new Class[] { Long.TYPE });

				Long value8 = new Long(oldCplModel.getResourceBlockId());

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setClassNameId",
						new Class[] { Long.TYPE });

				Long value9 = new Long(oldCplModel.getClassNameId());

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setClassPK",
						new Class[] { Long.TYPE });

				Long value10 = new Long(oldCplModel.getClassPK());

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setClassUuid",
						new Class[] { String.class });

				String value11 = oldCplModel.getClassUuid();

				method11.invoke(newModel, value11);

				Method method12 = newModelClass.getMethod("setCode",
						new Class[] { String.class });

				String value12 = oldCplModel.getCode();

				method12.invoke(newModel, value12);

				Method method13 = newModelClass.getMethod("setName",
						new Class[] { String.class });

				String value13 = oldCplModel.getName();

				method13.invoke(newModel, value13);

				Method method14 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value14 = oldCplModel.getDescription();

				method14.invoke(newModel, value14);

				Method method15 = newModelClass.getMethod("setType",
						new Class[] { String.class });

				String value15 = oldCplModel.getType();

				method15.invoke(newModel, value15);

				Method method16 = newModelClass.getMethod("setActive",
						new Class[] { Boolean.TYPE });

				Boolean value16 = new Boolean(oldCplModel.getActive());

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
					"com.liferay.calendar.model.impl.CalendarImpl")) {
			return translateOutputCalendar(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.calendar.model.impl.CalendarBookingImpl")) {
			return translateOutputCalendarBooking(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.calendar.model.impl.CalendarResourceImpl")) {
			return translateOutputCalendarResource(oldModel);
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

	public static Object translateOutputCalendar(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				CalendarClp newModel = new CalendarClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getUuid");

				String value0 = (String)method0.invoke(oldModel, (Object[])null);

				newModel.setUuid(value0);

				Method method1 = oldModelClass.getMethod("getCalendarId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setCalendarId(value1);

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

				Method method8 = oldModelClass.getMethod("getResourceBlockId");

				Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

				newModel.setResourceBlockId(value8);

				Method method9 = oldModelClass.getMethod(
						"getCalendarResourceId");

				Long value9 = (Long)method9.invoke(oldModel, (Object[])null);

				newModel.setCalendarResourceId(value9);

				Method method10 = oldModelClass.getMethod("getName");

				String value10 = (String)method10.invoke(oldModel,
						(Object[])null);

				newModel.setName(value10);

				Method method10CurrentLanguageId = oldModelClass.getMethod(
						"getNameCurrentLanguageId");

				String value10CurrentLanguageId = (String)method10CurrentLanguageId.invoke(oldModel,
						(Object[])null);

				newModel.setNameCurrentLanguageId(value10CurrentLanguageId);

				Method method11 = oldModelClass.getMethod("getDescription");

				String value11 = (String)method11.invoke(oldModel,
						(Object[])null);

				newModel.setDescription(value11);

				Method method11CurrentLanguageId = oldModelClass.getMethod(
						"getDescriptionCurrentLanguageId");

				String value11CurrentLanguageId = (String)method11CurrentLanguageId.invoke(oldModel,
						(Object[])null);

				newModel.setDescriptionCurrentLanguageId(value11CurrentLanguageId);

				Method method12 = oldModelClass.getMethod("getColor");

				Integer value12 = (Integer)method12.invoke(oldModel,
						(Object[])null);

				newModel.setColor(value12);

				Method method13 = oldModelClass.getMethod("getDefaultCalendar");

				Boolean value13 = (Boolean)method13.invoke(oldModel,
						(Object[])null);

				newModel.setDefaultCalendar(value13);

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

	public static Object translateOutputCalendarBooking(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				CalendarBookingClp newModel = new CalendarBookingClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getUuid");

				String value0 = (String)method0.invoke(oldModel, (Object[])null);

				newModel.setUuid(value0);

				Method method1 = oldModelClass.getMethod("getCalendarBookingId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setCalendarBookingId(value1);

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

				Method method8 = oldModelClass.getMethod("getCalendarId");

				Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

				newModel.setCalendarId(value8);

				Method method9 = oldModelClass.getMethod(
						"getCalendarResourceId");

				Long value9 = (Long)method9.invoke(oldModel, (Object[])null);

				newModel.setCalendarResourceId(value9);

				Method method10 = oldModelClass.getMethod(
						"getParentCalendarBookingId");

				Long value10 = (Long)method10.invoke(oldModel, (Object[])null);

				newModel.setParentCalendarBookingId(value10);

				Method method11 = oldModelClass.getMethod("getTitle");

				String value11 = (String)method11.invoke(oldModel,
						(Object[])null);

				newModel.setTitle(value11);

				Method method11CurrentLanguageId = oldModelClass.getMethod(
						"getTitleCurrentLanguageId");

				String value11CurrentLanguageId = (String)method11CurrentLanguageId.invoke(oldModel,
						(Object[])null);

				newModel.setTitleCurrentLanguageId(value11CurrentLanguageId);

				Method method12 = oldModelClass.getMethod("getDescription");

				String value12 = (String)method12.invoke(oldModel,
						(Object[])null);

				newModel.setDescription(value12);

				Method method12CurrentLanguageId = oldModelClass.getMethod(
						"getDescriptionCurrentLanguageId");

				String value12CurrentLanguageId = (String)method12CurrentLanguageId.invoke(oldModel,
						(Object[])null);

				newModel.setDescriptionCurrentLanguageId(value12CurrentLanguageId);

				Method method13 = oldModelClass.getMethod("getLocation");

				String value13 = (String)method13.invoke(oldModel,
						(Object[])null);

				newModel.setLocation(value13);

				Method method13CurrentLanguageId = oldModelClass.getMethod(
						"getLocationCurrentLanguageId");

				String value13CurrentLanguageId = (String)method13CurrentLanguageId.invoke(oldModel,
						(Object[])null);

				newModel.setLocationCurrentLanguageId(value13CurrentLanguageId);

				Method method14 = oldModelClass.getMethod("getType");

				String value14 = (String)method14.invoke(oldModel,
						(Object[])null);

				newModel.setType(value14);

				Method method15 = oldModelClass.getMethod("getStartDate");

				Date value15 = (Date)method15.invoke(oldModel, (Object[])null);

				newModel.setStartDate(value15);

				Method method16 = oldModelClass.getMethod("getStartTimeZone");

				String value16 = (String)method16.invoke(oldModel,
						(Object[])null);

				newModel.setStartTimeZone(value16);

				Method method17 = oldModelClass.getMethod("getEndDate");

				Date value17 = (Date)method17.invoke(oldModel, (Object[])null);

				newModel.setEndDate(value17);

				Method method18 = oldModelClass.getMethod("getEndDateTimeZone");

				String value18 = (String)method18.invoke(oldModel,
						(Object[])null);

				newModel.setEndDateTimeZone(value18);

				Method method19 = oldModelClass.getMethod("getAllDay");

				Boolean value19 = (Boolean)method19.invoke(oldModel,
						(Object[])null);

				newModel.setAllDay(value19);

				Method method20 = oldModelClass.getMethod("getRecurrence");

				String value20 = (String)method20.invoke(oldModel,
						(Object[])null);

				newModel.setRecurrence(value20);

				Method method21 = oldModelClass.getMethod("getPriority");

				Integer value21 = (Integer)method21.invoke(oldModel,
						(Object[])null);

				newModel.setPriority(value21);

				Method method22 = oldModelClass.getMethod("getOutOfOffice");

				Boolean value22 = (Boolean)method22.invoke(oldModel,
						(Object[])null);

				newModel.setOutOfOffice(value22);

				Method method23 = oldModelClass.getMethod("getRemindBy");

				Integer value23 = (Integer)method23.invoke(oldModel,
						(Object[])null);

				newModel.setRemindBy(value23);

				Method method24 = oldModelClass.getMethod("getFirstReminder");

				Integer value24 = (Integer)method24.invoke(oldModel,
						(Object[])null);

				newModel.setFirstReminder(value24);

				Method method25 = oldModelClass.getMethod("getSecondReminder");

				Integer value25 = (Integer)method25.invoke(oldModel,
						(Object[])null);

				newModel.setSecondReminder(value25);

				Method method26 = oldModelClass.getMethod("getRequired");

				Boolean value26 = (Boolean)method26.invoke(oldModel,
						(Object[])null);

				newModel.setRequired(value26);

				Method method27 = oldModelClass.getMethod("getRequestMessage");

				String value27 = (String)method27.invoke(oldModel,
						(Object[])null);

				newModel.setRequestMessage(value27);

				Method method28 = oldModelClass.getMethod("getResponseMessage");

				String value28 = (String)method28.invoke(oldModel,
						(Object[])null);

				newModel.setResponseMessage(value28);

				Method method29 = oldModelClass.getMethod("getStatus");

				Integer value29 = (Integer)method29.invoke(oldModel,
						(Object[])null);

				newModel.setStatus(value29);

				Method method30 = oldModelClass.getMethod("getStatusByUserId");

				Long value30 = (Long)method30.invoke(oldModel, (Object[])null);

				newModel.setStatusByUserId(value30);

				Method method31 = oldModelClass.getMethod("getStatusByUserName");

				String value31 = (String)method31.invoke(oldModel,
						(Object[])null);

				newModel.setStatusByUserName(value31);

				Method method32 = oldModelClass.getMethod("getStatusDate");

				Date value32 = (Date)method32.invoke(oldModel, (Object[])null);

				newModel.setStatusDate(value32);

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

	public static Object translateOutputCalendarResource(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				CalendarResourceClp newModel = new CalendarResourceClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getUuid");

				String value0 = (String)method0.invoke(oldModel, (Object[])null);

				newModel.setUuid(value0);

				Method method1 = oldModelClass.getMethod(
						"getCalendarResourceId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setCalendarResourceId(value1);

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

				Method method8 = oldModelClass.getMethod("getResourceBlockId");

				Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

				newModel.setResourceBlockId(value8);

				Method method9 = oldModelClass.getMethod("getClassNameId");

				Long value9 = (Long)method9.invoke(oldModel, (Object[])null);

				newModel.setClassNameId(value9);

				Method method10 = oldModelClass.getMethod("getClassPK");

				Long value10 = (Long)method10.invoke(oldModel, (Object[])null);

				newModel.setClassPK(value10);

				Method method11 = oldModelClass.getMethod("getClassUuid");

				String value11 = (String)method11.invoke(oldModel,
						(Object[])null);

				newModel.setClassUuid(value11);

				Method method12 = oldModelClass.getMethod("getCode");

				String value12 = (String)method12.invoke(oldModel,
						(Object[])null);

				newModel.setCode(value12);

				Method method13 = oldModelClass.getMethod("getName");

				String value13 = (String)method13.invoke(oldModel,
						(Object[])null);

				newModel.setName(value13);

				Method method13CurrentLanguageId = oldModelClass.getMethod(
						"getNameCurrentLanguageId");

				String value13CurrentLanguageId = (String)method13CurrentLanguageId.invoke(oldModel,
						(Object[])null);

				newModel.setNameCurrentLanguageId(value13CurrentLanguageId);

				Method method14 = oldModelClass.getMethod("getDescription");

				String value14 = (String)method14.invoke(oldModel,
						(Object[])null);

				newModel.setDescription(value14);

				Method method14CurrentLanguageId = oldModelClass.getMethod(
						"getDescriptionCurrentLanguageId");

				String value14CurrentLanguageId = (String)method14CurrentLanguageId.invoke(oldModel,
						(Object[])null);

				newModel.setDescriptionCurrentLanguageId(value14CurrentLanguageId);

				Method method15 = oldModelClass.getMethod("getType");

				String value15 = (String)method15.invoke(oldModel,
						(Object[])null);

				newModel.setType(value15);

				Method method16 = oldModelClass.getMethod("getActive");

				Boolean value16 = (Boolean)method16.invoke(oldModel,
						(Object[])null);

				newModel.setActive(value16);

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