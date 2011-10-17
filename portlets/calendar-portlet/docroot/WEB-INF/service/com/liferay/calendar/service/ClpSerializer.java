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

package com.liferay.calendar.service;

import com.liferay.calendar.model.CalendarBookingClp;
import com.liferay.calendar.model.CalendarEventClp;
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
						"calendar-portlet-deployment-context");

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
							"calendar-portlet-deployment-context");

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
				_servletContextName = "calendar-portlet";
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

		if (oldModelClassName.equals(CalendarBookingClp.class.getName())) {
			return translateInputCalendarBooking(oldModel);
		}

		if (oldModelClassName.equals(CalendarEventClp.class.getName())) {
			return translateInputCalendarEvent(oldModel);
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

				Method method8 = newModelClass.getMethod("setCalendarEventId",
						new Class[] { Long.TYPE });

				Long value8 = new Long(oldCplModel.getCalendarEventId());

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setCalendarResourceId",
						new Class[] { Long.TYPE });

				Long value9 = new Long(oldCplModel.getCalendarResourceId());

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setClassNameId",
						new Class[] { Long.TYPE });

				Long value10 = new Long(oldCplModel.getClassNameId());

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setClassPK",
						new Class[] { Long.TYPE });

				Long value11 = new Long(oldCplModel.getClassPK());

				method11.invoke(newModel, value11);

				Method method12 = newModelClass.getMethod("setTitle",
						new Class[] { String.class });

				String value12 = oldCplModel.getTitle();

				method12.invoke(newModel, value12);

				Method method13 = newModelClass.getMethod("setName",
						new Class[] { String.class });

				String value13 = oldCplModel.getName();

				method13.invoke(newModel, value13);

				Method method14 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value14 = oldCplModel.getDescription();

				method14.invoke(newModel, value14);

				Method method15 = newModelClass.getMethod("setLocation",
						new Class[] { String.class });

				String value15 = oldCplModel.getLocation();

				method15.invoke(newModel, value15);

				Method method16 = newModelClass.getMethod("setStartDate",
						new Class[] { Date.class });

				Date value16 = oldCplModel.getStartDate();

				method16.invoke(newModel, value16);

				Method method17 = newModelClass.getMethod("setEndDate",
						new Class[] { Date.class });

				Date value17 = oldCplModel.getEndDate();

				method17.invoke(newModel, value17);

				Method method18 = newModelClass.getMethod("setDurationHour",
						new Class[] { Integer.TYPE });

				Integer value18 = new Integer(oldCplModel.getDurationHour());

				method18.invoke(newModel, value18);

				Method method19 = newModelClass.getMethod("setDurationMinute",
						new Class[] { Integer.TYPE });

				Integer value19 = new Integer(oldCplModel.getDurationMinute());

				method19.invoke(newModel, value19);

				Method method20 = newModelClass.getMethod("setRecurrence",
						new Class[] { String.class });

				String value20 = oldCplModel.getRecurrence();

				method20.invoke(newModel, value20);

				Method method21 = newModelClass.getMethod("setType",
						new Class[] { String.class });

				String value21 = oldCplModel.getType();

				method21.invoke(newModel, value21);

				Method method22 = newModelClass.getMethod("setRequired",
						new Class[] { Boolean.TYPE });

				Boolean value22 = new Boolean(oldCplModel.getRequired());

				method22.invoke(newModel, value22);

				Method method23 = newModelClass.getMethod("setStatus",
						new Class[] { Integer.TYPE });

				Integer value23 = new Integer(oldCplModel.getStatus());

				method23.invoke(newModel, value23);

				Method method24 = newModelClass.getMethod("setStatusByUserId",
						new Class[] { Long.TYPE });

				Long value24 = new Long(oldCplModel.getStatusByUserId());

				method24.invoke(newModel, value24);

				Method method25 = newModelClass.getMethod("setStatusByUserName",
						new Class[] { String.class });

				String value25 = oldCplModel.getStatusByUserName();

				method25.invoke(newModel, value25);

				Method method26 = newModelClass.getMethod("setStatusDate",
						new Class[] { Date.class });

				Date value26 = oldCplModel.getStatusDate();

				method26.invoke(newModel, value26);

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

	public static Object translateInputCalendarEvent(BaseModel<?> oldModel) {
		CalendarEventClp oldCplModel = (CalendarEventClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.calendar.model.impl.CalendarEventImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setUuid",
						new Class[] { String.class });

				String value0 = oldCplModel.getUuid();

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setCalendarEventId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getCalendarEventId());

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

				Method method8 = newModelClass.getMethod("setTitle",
						new Class[] { String.class });

				String value8 = oldCplModel.getTitle();

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value9 = oldCplModel.getDescription();

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setLocation",
						new Class[] { String.class });

				String value10 = oldCplModel.getLocation();

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setStartDate",
						new Class[] { Date.class });

				Date value11 = oldCplModel.getStartDate();

				method11.invoke(newModel, value11);

				Method method12 = newModelClass.getMethod("setEndDate",
						new Class[] { Date.class });

				Date value12 = oldCplModel.getEndDate();

				method12.invoke(newModel, value12);

				Method method13 = newModelClass.getMethod("setDurationHour",
						new Class[] { Integer.TYPE });

				Integer value13 = new Integer(oldCplModel.getDurationHour());

				method13.invoke(newModel, value13);

				Method method14 = newModelClass.getMethod("setDurationMinute",
						new Class[] { Integer.TYPE });

				Integer value14 = new Integer(oldCplModel.getDurationMinute());

				method14.invoke(newModel, value14);

				Method method15 = newModelClass.getMethod("setAllDay",
						new Class[] { Boolean.TYPE });

				Boolean value15 = new Boolean(oldCplModel.getAllDay());

				method15.invoke(newModel, value15);

				Method method16 = newModelClass.getMethod("setRecurrence",
						new Class[] { String.class });

				String value16 = oldCplModel.getRecurrence();

				method16.invoke(newModel, value16);

				Method method17 = newModelClass.getMethod("setType",
						new Class[] { String.class });

				String value17 = oldCplModel.getType();

				method17.invoke(newModel, value17);

				Method method18 = newModelClass.getMethod("setRemindBy",
						new Class[] { Integer.TYPE });

				Integer value18 = new Integer(oldCplModel.getRemindBy());

				method18.invoke(newModel, value18);

				Method method19 = newModelClass.getMethod("setFirstReminder",
						new Class[] { Integer.TYPE });

				Integer value19 = new Integer(oldCplModel.getFirstReminder());

				method19.invoke(newModel, value19);

				Method method20 = newModelClass.getMethod("setSecondReminder",
						new Class[] { Integer.TYPE });

				Integer value20 = new Integer(oldCplModel.getSecondReminder());

				method20.invoke(newModel, value20);

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

				Method method8 = newModelClass.getMethod("setClassNameId",
						new Class[] { Long.TYPE });

				Long value8 = new Long(oldCplModel.getClassNameId());

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setClassPK",
						new Class[] { Long.TYPE });

				Long value9 = new Long(oldCplModel.getClassPK());

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setClassUuid",
						new Class[] { String.class });

				String value10 = oldCplModel.getClassUuid();

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setName",
						new Class[] { String.class });

				String value11 = oldCplModel.getName();

				method11.invoke(newModel, value11);

				Method method12 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value12 = oldCplModel.getDescription();

				method12.invoke(newModel, value12);

				Method method13 = newModelClass.getMethod("setActive",
						new Class[] { Boolean.TYPE });

				Boolean value13 = new Boolean(oldCplModel.getActive());

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
					"com.liferay.calendar.model.impl.CalendarBookingImpl")) {
			return translateOutputCalendarBooking(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.calendar.model.impl.CalendarEventImpl")) {
			return translateOutputCalendarEvent(oldModel);
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

				Method method8 = oldModelClass.getMethod("getCalendarEventId");

				Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

				newModel.setCalendarEventId(value8);

				Method method9 = oldModelClass.getMethod(
						"getCalendarResourceId");

				Long value9 = (Long)method9.invoke(oldModel, (Object[])null);

				newModel.setCalendarResourceId(value9);

				Method method10 = oldModelClass.getMethod("getClassNameId");

				Long value10 = (Long)method10.invoke(oldModel, (Object[])null);

				newModel.setClassNameId(value10);

				Method method11 = oldModelClass.getMethod("getClassPK");

				Long value11 = (Long)method11.invoke(oldModel, (Object[])null);

				newModel.setClassPK(value11);

				Method method12 = oldModelClass.getMethod("getTitle");

				String value12 = (String)method12.invoke(oldModel,
						(Object[])null);

				newModel.setTitle(value12);

				Method method12CurrentLanguageId = oldModelClass.getMethod(
						"getTitleCurrentLanguageId");

				String value12CurrentLanguageId = (String)method12CurrentLanguageId.invoke(oldModel,
						(Object[])null);

				newModel.setTitleCurrentLanguageId(value12CurrentLanguageId);

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

				Method method15 = oldModelClass.getMethod("getLocation");

				String value15 = (String)method15.invoke(oldModel,
						(Object[])null);

				newModel.setLocation(value15);

				Method method16 = oldModelClass.getMethod("getStartDate");

				Date value16 = (Date)method16.invoke(oldModel, (Object[])null);

				newModel.setStartDate(value16);

				Method method17 = oldModelClass.getMethod("getEndDate");

				Date value17 = (Date)method17.invoke(oldModel, (Object[])null);

				newModel.setEndDate(value17);

				Method method18 = oldModelClass.getMethod("getDurationHour");

				Integer value18 = (Integer)method18.invoke(oldModel,
						(Object[])null);

				newModel.setDurationHour(value18);

				Method method19 = oldModelClass.getMethod("getDurationMinute");

				Integer value19 = (Integer)method19.invoke(oldModel,
						(Object[])null);

				newModel.setDurationMinute(value19);

				Method method20 = oldModelClass.getMethod("getRecurrence");

				String value20 = (String)method20.invoke(oldModel,
						(Object[])null);

				newModel.setRecurrence(value20);

				Method method21 = oldModelClass.getMethod("getType");

				String value21 = (String)method21.invoke(oldModel,
						(Object[])null);

				newModel.setType(value21);

				Method method22 = oldModelClass.getMethod("getRequired");

				Boolean value22 = (Boolean)method22.invoke(oldModel,
						(Object[])null);

				newModel.setRequired(value22);

				Method method23 = oldModelClass.getMethod("getStatus");

				Integer value23 = (Integer)method23.invoke(oldModel,
						(Object[])null);

				newModel.setStatus(value23);

				Method method24 = oldModelClass.getMethod("getStatusByUserId");

				Long value24 = (Long)method24.invoke(oldModel, (Object[])null);

				newModel.setStatusByUserId(value24);

				Method method25 = oldModelClass.getMethod("getStatusByUserName");

				String value25 = (String)method25.invoke(oldModel,
						(Object[])null);

				newModel.setStatusByUserName(value25);

				Method method26 = oldModelClass.getMethod("getStatusDate");

				Date value26 = (Date)method26.invoke(oldModel, (Object[])null);

				newModel.setStatusDate(value26);

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

	public static Object translateOutputCalendarEvent(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				CalendarEventClp newModel = new CalendarEventClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getUuid");

				String value0 = (String)method0.invoke(oldModel, (Object[])null);

				newModel.setUuid(value0);

				Method method1 = oldModelClass.getMethod("getCalendarEventId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setCalendarEventId(value1);

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

				Method method8 = oldModelClass.getMethod("getTitle");

				String value8 = (String)method8.invoke(oldModel, (Object[])null);

				newModel.setTitle(value8);

				Method method8CurrentLanguageId = oldModelClass.getMethod(
						"getTitleCurrentLanguageId");

				String value8CurrentLanguageId = (String)method8CurrentLanguageId.invoke(oldModel,
						(Object[])null);

				newModel.setTitleCurrentLanguageId(value8CurrentLanguageId);

				Method method9 = oldModelClass.getMethod("getDescription");

				String value9 = (String)method9.invoke(oldModel, (Object[])null);

				newModel.setDescription(value9);

				Method method9CurrentLanguageId = oldModelClass.getMethod(
						"getDescriptionCurrentLanguageId");

				String value9CurrentLanguageId = (String)method9CurrentLanguageId.invoke(oldModel,
						(Object[])null);

				newModel.setDescriptionCurrentLanguageId(value9CurrentLanguageId);

				Method method10 = oldModelClass.getMethod("getLocation");

				String value10 = (String)method10.invoke(oldModel,
						(Object[])null);

				newModel.setLocation(value10);

				Method method11 = oldModelClass.getMethod("getStartDate");

				Date value11 = (Date)method11.invoke(oldModel, (Object[])null);

				newModel.setStartDate(value11);

				Method method12 = oldModelClass.getMethod("getEndDate");

				Date value12 = (Date)method12.invoke(oldModel, (Object[])null);

				newModel.setEndDate(value12);

				Method method13 = oldModelClass.getMethod("getDurationHour");

				Integer value13 = (Integer)method13.invoke(oldModel,
						(Object[])null);

				newModel.setDurationHour(value13);

				Method method14 = oldModelClass.getMethod("getDurationMinute");

				Integer value14 = (Integer)method14.invoke(oldModel,
						(Object[])null);

				newModel.setDurationMinute(value14);

				Method method15 = oldModelClass.getMethod("getAllDay");

				Boolean value15 = (Boolean)method15.invoke(oldModel,
						(Object[])null);

				newModel.setAllDay(value15);

				Method method16 = oldModelClass.getMethod("getRecurrence");

				String value16 = (String)method16.invoke(oldModel,
						(Object[])null);

				newModel.setRecurrence(value16);

				Method method17 = oldModelClass.getMethod("getType");

				String value17 = (String)method17.invoke(oldModel,
						(Object[])null);

				newModel.setType(value17);

				Method method18 = oldModelClass.getMethod("getRemindBy");

				Integer value18 = (Integer)method18.invoke(oldModel,
						(Object[])null);

				newModel.setRemindBy(value18);

				Method method19 = oldModelClass.getMethod("getFirstReminder");

				Integer value19 = (Integer)method19.invoke(oldModel,
						(Object[])null);

				newModel.setFirstReminder(value19);

				Method method20 = oldModelClass.getMethod("getSecondReminder");

				Integer value20 = (Integer)method20.invoke(oldModel,
						(Object[])null);

				newModel.setSecondReminder(value20);

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

				Method method8 = oldModelClass.getMethod("getClassNameId");

				Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

				newModel.setClassNameId(value8);

				Method method9 = oldModelClass.getMethod("getClassPK");

				Long value9 = (Long)method9.invoke(oldModel, (Object[])null);

				newModel.setClassPK(value9);

				Method method10 = oldModelClass.getMethod("getClassUuid");

				String value10 = (String)method10.invoke(oldModel,
						(Object[])null);

				newModel.setClassUuid(value10);

				Method method11 = oldModelClass.getMethod("getName");

				String value11 = (String)method11.invoke(oldModel,
						(Object[])null);

				newModel.setName(value11);

				Method method11CurrentLanguageId = oldModelClass.getMethod(
						"getNameCurrentLanguageId");

				String value11CurrentLanguageId = (String)method11CurrentLanguageId.invoke(oldModel,
						(Object[])null);

				newModel.setNameCurrentLanguageId(value11CurrentLanguageId);

				Method method12 = oldModelClass.getMethod("getDescription");

				String value12 = (String)method12.invoke(oldModel,
						(Object[])null);

				newModel.setDescription(value12);

				Method method12CurrentLanguageId = oldModelClass.getMethod(
						"getDescriptionCurrentLanguageId");

				String value12CurrentLanguageId = (String)method12CurrentLanguageId.invoke(oldModel,
						(Object[])null);

				newModel.setDescriptionCurrentLanguageId(value12CurrentLanguageId);

				Method method13 = oldModelClass.getMethod("getActive");

				Boolean value13 = (Boolean)method13.invoke(oldModel,
						(Object[])null);

				newModel.setActive(value13);

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