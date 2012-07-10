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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ClassLoaderObjectInputStream;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;

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
		CalendarClp oldClpModel = (CalendarClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getCalendarRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputCalendarBooking(BaseModel<?> oldModel) {
		CalendarBookingClp oldClpModel = (CalendarBookingClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getCalendarBookingRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputCalendarResource(BaseModel<?> oldModel) {
		CalendarResourceClp oldClpModel = (CalendarResourceClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getCalendarResourceRemoteModel();

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

		if (className.equals(PortalException.class.getName())) {
			return new PortalException();
		}

		if (className.equals(SystemException.class.getName())) {
			return new SystemException();
		}

		if (className.equals(
					"com.liferay.calendar.CalendarBookingDurationException")) {
			return new com.liferay.calendar.CalendarBookingDurationException();
		}

		if (className.equals(
					"com.liferay.calendar.CalendarBookingEndDateException")) {
			return new com.liferay.calendar.CalendarBookingEndDateException();
		}

		if (className.equals(
					"com.liferay.calendar.CalendarBookingStartDateException")) {
			return new com.liferay.calendar.CalendarBookingStartDateException();
		}

		if (className.equals(
					"com.liferay.calendar.CalendarBookingTitleException")) {
			return new com.liferay.calendar.CalendarBookingTitleException();
		}

		if (className.equals("com.liferay.calendar.CalendarNameException")) {
			return new com.liferay.calendar.CalendarNameException();
		}

		if (className.equals(
					"com.liferay.calendar.CalendarResourceCodeException")) {
			return new com.liferay.calendar.CalendarResourceCodeException();
		}

		if (className.equals(
					"com.liferay.calendar.DuplicateCalendarResourceException")) {
			return new com.liferay.calendar.DuplicateCalendarResourceException();
		}

		if (className.equals("com.liferay.calendar.NoSuchCalendarException")) {
			return new com.liferay.calendar.NoSuchCalendarException();
		}

		if (className.equals("com.liferay.calendar.NoSuchBookingException")) {
			return new com.liferay.calendar.NoSuchBookingException();
		}

		if (className.equals("com.liferay.calendar.NoSuchResourceException")) {
			return new com.liferay.calendar.NoSuchResourceException();
		}

		return throwable;
	}

	public static Object translateOutputCalendar(BaseModel<?> oldModel) {
		CalendarClp newModel = new CalendarClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setCalendarRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputCalendarBooking(BaseModel<?> oldModel) {
		CalendarBookingClp newModel = new CalendarBookingClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setCalendarBookingRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputCalendarResource(BaseModel<?> oldModel) {
		CalendarResourceClp newModel = new CalendarResourceClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setCalendarResourceRemoteModel(oldModel);

		return newModel;
	}

	private static Log _log = LogFactoryUtil.getLog(ClpSerializer.class);
	private static String _servletContextName;
	private static boolean _useReflectionToTranslateThrowable = true;
}