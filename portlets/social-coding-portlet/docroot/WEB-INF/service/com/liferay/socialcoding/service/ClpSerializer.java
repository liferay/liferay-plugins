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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;

import com.liferay.socialcoding.model.JIRAActionClp;
import com.liferay.socialcoding.model.JIRAChangeGroupClp;
import com.liferay.socialcoding.model.JIRAChangeItemClp;
import com.liferay.socialcoding.model.JIRAIssueClp;
import com.liferay.socialcoding.model.SVNRepositoryClp;
import com.liferay.socialcoding.model.SVNRevisionClp;

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

	public static void setClassLoader(ClassLoader classLoader) {
		_classLoader = classLoader;
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
		JIRAActionClp oldCplModel = (JIRAActionClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.socialcoding.model.impl.JIRAActionImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setJiraActionId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getJiraActionId());

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setJiraUserId",
						new Class[] { String.class });

				String value1 = oldCplModel.getJiraUserId();

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setCreateDate",
						new Class[] { Date.class });

				Date value2 = oldCplModel.getCreateDate();

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setModifiedDate",
						new Class[] { Date.class });

				Date value3 = oldCplModel.getModifiedDate();

				method3.invoke(newModel, value3);

				Method method4 = newModelClass.getMethod("setJiraIssueId",
						new Class[] { Long.TYPE });

				Long value4 = new Long(oldCplModel.getJiraIssueId());

				method4.invoke(newModel, value4);

				Method method5 = newModelClass.getMethod("setType",
						new Class[] { String.class });

				String value5 = oldCplModel.getType();

				method5.invoke(newModel, value5);

				Method method6 = newModelClass.getMethod("setBody",
						new Class[] { String.class });

				String value6 = oldCplModel.getBody();

				method6.invoke(newModel, value6);

				Method method7 = newModelClass.getMethod("setJiraGroupName",
						new Class[] { String.class });

				String value7 = oldCplModel.getJiraGroupName();

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

	public static Object translateInputJIRAChangeGroup(BaseModel<?> oldModel) {
		JIRAChangeGroupClp oldCplModel = (JIRAChangeGroupClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.socialcoding.model.impl.JIRAChangeGroupImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setJiraChangeGroupId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getJiraChangeGroupId());

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setJiraUserId",
						new Class[] { String.class });

				String value1 = oldCplModel.getJiraUserId();

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setCreateDate",
						new Class[] { Date.class });

				Date value2 = oldCplModel.getCreateDate();

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setJiraIssueId",
						new Class[] { Long.TYPE });

				Long value3 = new Long(oldCplModel.getJiraIssueId());

				method3.invoke(newModel, value3);

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

	public static Object translateInputJIRAChangeItem(BaseModel<?> oldModel) {
		JIRAChangeItemClp oldCplModel = (JIRAChangeItemClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.socialcoding.model.impl.JIRAChangeItemImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setJiraChangeItemId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getJiraChangeItemId());

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setJiraChangeGroupId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getJiraChangeGroupId());

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setField",
						new Class[] { String.class });

				String value2 = oldCplModel.getField();

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setOldValue",
						new Class[] { String.class });

				String value3 = oldCplModel.getOldValue();

				method3.invoke(newModel, value3);

				Method method4 = newModelClass.getMethod("setOldString",
						new Class[] { String.class });

				String value4 = oldCplModel.getOldString();

				method4.invoke(newModel, value4);

				Method method5 = newModelClass.getMethod("setNewValue",
						new Class[] { String.class });

				String value5 = oldCplModel.getNewValue();

				method5.invoke(newModel, value5);

				Method method6 = newModelClass.getMethod("setNewString",
						new Class[] { String.class });

				String value6 = oldCplModel.getNewString();

				method6.invoke(newModel, value6);

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

	public static Object translateInputJIRAIssue(BaseModel<?> oldModel) {
		JIRAIssueClp oldCplModel = (JIRAIssueClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.socialcoding.model.impl.JIRAIssueImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setJiraIssueId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getJiraIssueId());

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setCreateDate",
						new Class[] { Date.class });

				Date value1 = oldCplModel.getCreateDate();

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setModifiedDate",
						new Class[] { Date.class });

				Date value2 = oldCplModel.getModifiedDate();

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setProjectId",
						new Class[] { Long.TYPE });

				Long value3 = new Long(oldCplModel.getProjectId());

				method3.invoke(newModel, value3);

				Method method4 = newModelClass.getMethod("setKey",
						new Class[] { String.class });

				String value4 = oldCplModel.getKey();

				method4.invoke(newModel, value4);

				Method method5 = newModelClass.getMethod("setSummary",
						new Class[] { String.class });

				String value5 = oldCplModel.getSummary();

				method5.invoke(newModel, value5);

				Method method6 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value6 = oldCplModel.getDescription();

				method6.invoke(newModel, value6);

				Method method7 = newModelClass.getMethod("setReporterJiraUserId",
						new Class[] { String.class });

				String value7 = oldCplModel.getReporterJiraUserId();

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setAssigneeJiraUserId",
						new Class[] { String.class });

				String value8 = oldCplModel.getAssigneeJiraUserId();

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setResolution",
						new Class[] { String.class });

				String value9 = oldCplModel.getResolution();

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setStatus",
						new Class[] { String.class });

				String value10 = oldCplModel.getStatus();

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

	public static Object translateInputSVNRepository(BaseModel<?> oldModel) {
		SVNRepositoryClp oldCplModel = (SVNRepositoryClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.socialcoding.model.impl.SVNRepositoryImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setSvnRepositoryId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getSvnRepositoryId());

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setUrl",
						new Class[] { String.class });

				String value1 = oldCplModel.getUrl();

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setRevisionNumber",
						new Class[] { Long.TYPE });

				Long value2 = new Long(oldCplModel.getRevisionNumber());

				method2.invoke(newModel, value2);

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

	public static Object translateInputSVNRevision(BaseModel<?> oldModel) {
		SVNRevisionClp oldCplModel = (SVNRevisionClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.socialcoding.model.impl.SVNRevisionImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setSvnRevisionId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getSvnRevisionId());

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setSvnUserId",
						new Class[] { String.class });

				String value1 = oldCplModel.getSvnUserId();

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setCreateDate",
						new Class[] { Date.class });

				Date value2 = oldCplModel.getCreateDate();

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setSvnRepositoryId",
						new Class[] { Long.TYPE });

				Long value3 = new Long(oldCplModel.getSvnRepositoryId());

				method3.invoke(newModel, value3);

				Method method4 = newModelClass.getMethod("setRevisionNumber",
						new Class[] { Long.TYPE });

				Long value4 = new Long(oldCplModel.getRevisionNumber());

				method4.invoke(newModel, value4);

				Method method5 = newModelClass.getMethod("setComments",
						new Class[] { String.class });

				String value5 = oldCplModel.getComments();

				method5.invoke(newModel, value5);

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

	public static Object translateOutputJIRAAction(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				JIRAActionClp newModel = new JIRAActionClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getJiraActionId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setJiraActionId(value0);

				Method method1 = oldModelClass.getMethod("getJiraUserId");

				String value1 = (String)method1.invoke(oldModel, (Object[])null);

				newModel.setJiraUserId(value1);

				Method method2 = oldModelClass.getMethod("getCreateDate");

				Date value2 = (Date)method2.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value2);

				Method method3 = oldModelClass.getMethod("getModifiedDate");

				Date value3 = (Date)method3.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value3);

				Method method4 = oldModelClass.getMethod("getJiraIssueId");

				Long value4 = (Long)method4.invoke(oldModel, (Object[])null);

				newModel.setJiraIssueId(value4);

				Method method5 = oldModelClass.getMethod("getType");

				String value5 = (String)method5.invoke(oldModel, (Object[])null);

				newModel.setType(value5);

				Method method6 = oldModelClass.getMethod("getBody");

				String value6 = (String)method6.invoke(oldModel, (Object[])null);

				newModel.setBody(value6);

				Method method7 = oldModelClass.getMethod("getJiraGroupName");

				String value7 = (String)method7.invoke(oldModel, (Object[])null);

				newModel.setJiraGroupName(value7);

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

	public static Object translateOutputJIRAChangeGroup(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				JIRAChangeGroupClp newModel = new JIRAChangeGroupClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getJiraChangeGroupId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setJiraChangeGroupId(value0);

				Method method1 = oldModelClass.getMethod("getJiraUserId");

				String value1 = (String)method1.invoke(oldModel, (Object[])null);

				newModel.setJiraUserId(value1);

				Method method2 = oldModelClass.getMethod("getCreateDate");

				Date value2 = (Date)method2.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value2);

				Method method3 = oldModelClass.getMethod("getJiraIssueId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setJiraIssueId(value3);

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

	public static Object translateOutputJIRAChangeItem(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				JIRAChangeItemClp newModel = new JIRAChangeItemClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getJiraChangeItemId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setJiraChangeItemId(value0);

				Method method1 = oldModelClass.getMethod("getJiraChangeGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setJiraChangeGroupId(value1);

				Method method2 = oldModelClass.getMethod("getField");

				String value2 = (String)method2.invoke(oldModel, (Object[])null);

				newModel.setField(value2);

				Method method3 = oldModelClass.getMethod("getOldValue");

				String value3 = (String)method3.invoke(oldModel, (Object[])null);

				newModel.setOldValue(value3);

				Method method4 = oldModelClass.getMethod("getOldString");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setOldString(value4);

				Method method5 = oldModelClass.getMethod("getNewValue");

				String value5 = (String)method5.invoke(oldModel, (Object[])null);

				newModel.setNewValue(value5);

				Method method6 = oldModelClass.getMethod("getNewString");

				String value6 = (String)method6.invoke(oldModel, (Object[])null);

				newModel.setNewString(value6);

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

	public static Object translateOutputJIRAIssue(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				JIRAIssueClp newModel = new JIRAIssueClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getJiraIssueId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setJiraIssueId(value0);

				Method method1 = oldModelClass.getMethod("getCreateDate");

				Date value1 = (Date)method1.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value1);

				Method method2 = oldModelClass.getMethod("getModifiedDate");

				Date value2 = (Date)method2.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value2);

				Method method3 = oldModelClass.getMethod("getProjectId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setProjectId(value3);

				Method method4 = oldModelClass.getMethod("getKey");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setKey(value4);

				Method method5 = oldModelClass.getMethod("getSummary");

				String value5 = (String)method5.invoke(oldModel, (Object[])null);

				newModel.setSummary(value5);

				Method method6 = oldModelClass.getMethod("getDescription");

				String value6 = (String)method6.invoke(oldModel, (Object[])null);

				newModel.setDescription(value6);

				Method method7 = oldModelClass.getMethod(
						"getReporterJiraUserId");

				String value7 = (String)method7.invoke(oldModel, (Object[])null);

				newModel.setReporterJiraUserId(value7);

				Method method8 = oldModelClass.getMethod(
						"getAssigneeJiraUserId");

				String value8 = (String)method8.invoke(oldModel, (Object[])null);

				newModel.setAssigneeJiraUserId(value8);

				Method method9 = oldModelClass.getMethod("getResolution");

				String value9 = (String)method9.invoke(oldModel, (Object[])null);

				newModel.setResolution(value9);

				Method method10 = oldModelClass.getMethod("getStatus");

				String value10 = (String)method10.invoke(oldModel,
						(Object[])null);

				newModel.setStatus(value10);

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

	public static Object translateOutputSVNRepository(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				SVNRepositoryClp newModel = new SVNRepositoryClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getSvnRepositoryId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setSvnRepositoryId(value0);

				Method method1 = oldModelClass.getMethod("getUrl");

				String value1 = (String)method1.invoke(oldModel, (Object[])null);

				newModel.setUrl(value1);

				Method method2 = oldModelClass.getMethod("getRevisionNumber");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setRevisionNumber(value2);

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

	public static Object translateOutputSVNRevision(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				SVNRevisionClp newModel = new SVNRevisionClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getSvnRevisionId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setSvnRevisionId(value0);

				Method method1 = oldModelClass.getMethod("getSvnUserId");

				String value1 = (String)method1.invoke(oldModel, (Object[])null);

				newModel.setSvnUserId(value1);

				Method method2 = oldModelClass.getMethod("getCreateDate");

				Date value2 = (Date)method2.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value2);

				Method method3 = oldModelClass.getMethod("getSvnRepositoryId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setSvnRepositoryId(value3);

				Method method4 = oldModelClass.getMethod("getRevisionNumber");

				Long value4 = (Long)method4.invoke(oldModel, (Object[])null);

				newModel.setRevisionNumber(value4);

				Method method5 = oldModelClass.getMethod("getComments");

				String value5 = (String)method5.invoke(oldModel, (Object[])null);

				newModel.setComments(value5);

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