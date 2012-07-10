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

package com.liferay.socialcoding.service;

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

import com.liferay.socialcoding.model.JIRAActionClp;
import com.liferay.socialcoding.model.JIRAChangeGroupClp;
import com.liferay.socialcoding.model.JIRAChangeItemClp;
import com.liferay.socialcoding.model.JIRAIssueClp;
import com.liferay.socialcoding.model.SVNRepositoryClp;
import com.liferay.socialcoding.model.SVNRevisionClp;

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
						"social-coding-portlet-deployment-context");

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
							"social-coding-portlet-deployment-context");

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
				_servletContextName = "social-coding-portlet";
			}

			return _servletContextName;
		}
	}

	public static Object translateInput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(JIRAActionClp.class.getName())) {
			return translateInputJIRAAction(oldModel);
		}

		if (oldModelClassName.equals(JIRAChangeGroupClp.class.getName())) {
			return translateInputJIRAChangeGroup(oldModel);
		}

		if (oldModelClassName.equals(JIRAChangeItemClp.class.getName())) {
			return translateInputJIRAChangeItem(oldModel);
		}

		if (oldModelClassName.equals(JIRAIssueClp.class.getName())) {
			return translateInputJIRAIssue(oldModel);
		}

		if (oldModelClassName.equals(SVNRepositoryClp.class.getName())) {
			return translateInputSVNRepository(oldModel);
		}

		if (oldModelClassName.equals(SVNRevisionClp.class.getName())) {
			return translateInputSVNRevision(oldModel);
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

	public static Object translateInputJIRAAction(BaseModel<?> oldModel) {
		JIRAActionClp oldClpModel = (JIRAActionClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getJIRAActionRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputJIRAChangeGroup(BaseModel<?> oldModel) {
		JIRAChangeGroupClp oldClpModel = (JIRAChangeGroupClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getJIRAChangeGroupRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputJIRAChangeItem(BaseModel<?> oldModel) {
		JIRAChangeItemClp oldClpModel = (JIRAChangeItemClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getJIRAChangeItemRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputJIRAIssue(BaseModel<?> oldModel) {
		JIRAIssueClp oldClpModel = (JIRAIssueClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getJIRAIssueRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputSVNRepository(BaseModel<?> oldModel) {
		SVNRepositoryClp oldClpModel = (SVNRepositoryClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getSVNRepositoryRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputSVNRevision(BaseModel<?> oldModel) {
		SVNRevisionClp oldClpModel = (SVNRevisionClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getSVNRevisionRemoteModel();

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
					"com.liferay.socialcoding.model.impl.JIRAActionImpl")) {
			return translateOutputJIRAAction(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.socialcoding.model.impl.JIRAChangeGroupImpl")) {
			return translateOutputJIRAChangeGroup(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.socialcoding.model.impl.JIRAChangeItemImpl")) {
			return translateOutputJIRAChangeItem(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.socialcoding.model.impl.JIRAIssueImpl")) {
			return translateOutputJIRAIssue(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.socialcoding.model.impl.SVNRepositoryImpl")) {
			return translateOutputSVNRepository(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.socialcoding.model.impl.SVNRevisionImpl")) {
			return translateOutputSVNRevision(oldModel);
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
					"com.liferay.socialcoding.NoSuchJIRAActionException")) {
			return new com.liferay.socialcoding.NoSuchJIRAActionException();
		}

		if (className.equals(
					"com.liferay.socialcoding.NoSuchJIRAChangeGroupException")) {
			return new com.liferay.socialcoding.NoSuchJIRAChangeGroupException();
		}

		if (className.equals(
					"com.liferay.socialcoding.NoSuchJIRAChangeItemException")) {
			return new com.liferay.socialcoding.NoSuchJIRAChangeItemException();
		}

		if (className.equals(
					"com.liferay.socialcoding.NoSuchJIRAIssueException")) {
			return new com.liferay.socialcoding.NoSuchJIRAIssueException();
		}

		if (className.equals(
					"com.liferay.socialcoding.NoSuchSVNRepositoryException")) {
			return new com.liferay.socialcoding.NoSuchSVNRepositoryException();
		}

		if (className.equals(
					"com.liferay.socialcoding.NoSuchSVNRevisionException")) {
			return new com.liferay.socialcoding.NoSuchSVNRevisionException();
		}

		return throwable;
	}

	public static Object translateOutputJIRAAction(BaseModel<?> oldModel) {
		JIRAActionClp newModel = new JIRAActionClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setJIRAActionRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputJIRAChangeGroup(BaseModel<?> oldModel) {
		JIRAChangeGroupClp newModel = new JIRAChangeGroupClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setJIRAChangeGroupRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputJIRAChangeItem(BaseModel<?> oldModel) {
		JIRAChangeItemClp newModel = new JIRAChangeItemClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setJIRAChangeItemRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputJIRAIssue(BaseModel<?> oldModel) {
		JIRAIssueClp newModel = new JIRAIssueClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setJIRAIssueRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputSVNRepository(BaseModel<?> oldModel) {
		SVNRepositoryClp newModel = new SVNRepositoryClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setSVNRepositoryRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputSVNRevision(BaseModel<?> oldModel) {
		SVNRevisionClp newModel = new SVNRevisionClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setSVNRevisionRemoteModel(oldModel);

		return newModel;
	}

	private static Log _log = LogFactoryUtil.getLog(ClpSerializer.class);
	private static String _servletContextName;
	private static boolean _useReflectionToTranslateThrowable = true;
}