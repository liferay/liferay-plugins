/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.polls.service;

import com.liferay.polls.model.PollsChoiceClp;
import com.liferay.polls.model.PollsQuestionClp;
import com.liferay.polls.model.PollsVoteClp;

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
						"polls-portlet-deployment-context");

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
							"polls-portlet-deployment-context");

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
				_servletContextName = "polls-portlet";
			}

			return _servletContextName;
		}
	}

	public static Object translateInput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(PollsChoiceClp.class.getName())) {
			return translateInputPollsChoice(oldModel);
		}

		if (oldModelClassName.equals(PollsQuestionClp.class.getName())) {
			return translateInputPollsQuestion(oldModel);
		}

		if (oldModelClassName.equals(PollsVoteClp.class.getName())) {
			return translateInputPollsVote(oldModel);
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

	public static Object translateInputPollsChoice(BaseModel<?> oldModel) {
		PollsChoiceClp oldClpModel = (PollsChoiceClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getPollsChoiceRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputPollsQuestion(BaseModel<?> oldModel) {
		PollsQuestionClp oldClpModel = (PollsQuestionClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getPollsQuestionRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputPollsVote(BaseModel<?> oldModel) {
		PollsVoteClp oldClpModel = (PollsVoteClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getPollsVoteRemoteModel();

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
					"com.liferay.polls.model.impl.PollsChoiceImpl")) {
			return translateOutputPollsChoice(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.polls.model.impl.PollsQuestionImpl")) {
			return translateOutputPollsQuestion(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.polls.model.impl.PollsVoteImpl")) {
			return translateOutputPollsVote(oldModel);
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

		if (className.equals("com.liferay.polls.DuplicatePollsVoteException")) {
			return new com.liferay.polls.DuplicatePollsVoteException();
		}

		if (className.equals("com.liferay.polls.PollsQuestionChoiceException")) {
			return new com.liferay.polls.PollsQuestionChoiceException();
		}

		if (className.equals(
					"com.liferay.polls.PollsQuestionDescriptionException")) {
			return new com.liferay.polls.PollsQuestionDescriptionException();
		}

		if (className.equals(
					"com.liferay.polls.PollsQuestionExpirationDateException")) {
			return new com.liferay.polls.PollsQuestionExpirationDateException();
		}

		if (className.equals("com.liferay.polls.PollsQuestionExpiredException")) {
			return new com.liferay.polls.PollsQuestionExpiredException();
		}

		if (className.equals("com.liferay.polls.PollsQuestionTitleException")) {
			return new com.liferay.polls.PollsQuestionTitleException();
		}

		if (className.equals("com.liferay.polls.NoSuchChoiceException")) {
			return new com.liferay.polls.NoSuchChoiceException();
		}

		if (className.equals("com.liferay.polls.NoSuchQuestionException")) {
			return new com.liferay.polls.NoSuchQuestionException();
		}

		if (className.equals("com.liferay.polls.NoSuchVoteException")) {
			return new com.liferay.polls.NoSuchVoteException();
		}

		return throwable;
	}

	public static Object translateOutputPollsChoice(BaseModel<?> oldModel) {
		PollsChoiceClp newModel = new PollsChoiceClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setPollsChoiceRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputPollsQuestion(BaseModel<?> oldModel) {
		PollsQuestionClp newModel = new PollsQuestionClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setPollsQuestionRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputPollsVote(BaseModel<?> oldModel) {
		PollsVoteClp newModel = new PollsVoteClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setPollsVoteRemoteModel(oldModel);

		return newModel;
	}

	private static Log _log = LogFactoryUtil.getLog(ClpSerializer.class);
	private static String _servletContextName;
	private static boolean _useReflectionToTranslateThrowable = true;
}