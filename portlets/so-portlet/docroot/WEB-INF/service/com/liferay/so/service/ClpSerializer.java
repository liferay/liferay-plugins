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

package com.liferay.so.service;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;

import com.liferay.so.model.MemberRequestClp;
import com.liferay.so.model.ProjectsEntryClp;

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
						"so-portlet-deployment-context");

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
							"so-portlet-deployment-context");

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
				_servletContextName = "so-portlet";
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

		if (oldModelClassName.equals(MemberRequestClp.class.getName())) {
			return translateInputMemberRequest(oldModel);
		}

		if (oldModelClassName.equals(ProjectsEntryClp.class.getName())) {
			return translateInputProjectsEntry(oldModel);
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

	public static Object translateInputMemberRequest(BaseModel<?> oldModel) {
		MemberRequestClp oldCplModel = (MemberRequestClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.so.model.impl.MemberRequestImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setMemberRequestId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getMemberRequestId());

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

				Method method7 = newModelClass.getMethod("setKey",
						new Class[] { String.class });

				String value7 = oldCplModel.getKey();

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setReceiverUserId",
						new Class[] { Long.TYPE });

				Long value8 = new Long(oldCplModel.getReceiverUserId());

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setInvitedRoleId",
						new Class[] { Long.TYPE });

				Long value9 = new Long(oldCplModel.getInvitedRoleId());

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setInvitedTeamId",
						new Class[] { Long.TYPE });

				Long value10 = new Long(oldCplModel.getInvitedTeamId());

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setStatus",
						new Class[] { Integer.TYPE });

				Integer value11 = new Integer(oldCplModel.getStatus());

				method11.invoke(newModel, value11);

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

	public static Object translateInputProjectsEntry(BaseModel<?> oldModel) {
		ProjectsEntryClp oldCplModel = (ProjectsEntryClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.so.model.impl.ProjectsEntryImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setProjectsEntryId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getProjectsEntryId());

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

				Method method10 = newModelClass.getMethod("setData",
						new Class[] { String.class });

				String value10 = oldCplModel.getData();

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
					"com.liferay.so.model.impl.MemberRequestImpl")) {
			return translateOutputMemberRequest(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.so.model.impl.ProjectsEntryImpl")) {
			return translateOutputProjectsEntry(oldModel);
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

	public static Object translateOutputMemberRequest(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				MemberRequestClp newModel = new MemberRequestClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getMemberRequestId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setMemberRequestId(value0);

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

				Method method7 = oldModelClass.getMethod("getKey");

				String value7 = (String)method7.invoke(oldModel, (Object[])null);

				newModel.setKey(value7);

				Method method8 = oldModelClass.getMethod("getReceiverUserId");

				Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

				newModel.setReceiverUserId(value8);

				Method method9 = oldModelClass.getMethod("getInvitedRoleId");

				Long value9 = (Long)method9.invoke(oldModel, (Object[])null);

				newModel.setInvitedRoleId(value9);

				Method method10 = oldModelClass.getMethod("getInvitedTeamId");

				Long value10 = (Long)method10.invoke(oldModel, (Object[])null);

				newModel.setInvitedTeamId(value10);

				Method method11 = oldModelClass.getMethod("getStatus");

				Integer value11 = (Integer)method11.invoke(oldModel,
						(Object[])null);

				newModel.setStatus(value11);

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

	public static Object translateOutputProjectsEntry(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				ProjectsEntryClp newModel = new ProjectsEntryClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getProjectsEntryId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setProjectsEntryId(value0);

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

				Method method10 = oldModelClass.getMethod("getData");

				String value10 = (String)method10.invoke(oldModel,
						(Object[])null);

				newModel.setData(value10);

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