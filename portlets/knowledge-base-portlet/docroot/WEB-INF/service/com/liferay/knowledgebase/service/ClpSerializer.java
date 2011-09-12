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

package com.liferay.knowledgebase.service;

import com.liferay.knowledgebase.model.KBArticleClp;
import com.liferay.knowledgebase.model.KBCommentClp;
import com.liferay.knowledgebase.model.KBTemplateClp;

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
						"knowledge-base-portlet-deployment-context");

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
							"knowledge-base-portlet-deployment-context");

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
				_servletContextName = "knowledge-base-portlet";
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

		if (oldModelClassName.equals(KBArticleClp.class.getName())) {
			return translateInputKBArticle(oldModel);
		}

		if (oldModelClassName.equals(KBCommentClp.class.getName())) {
			return translateInputKBComment(oldModel);
		}

		if (oldModelClassName.equals(KBTemplateClp.class.getName())) {
			return translateInputKBTemplate(oldModel);
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

	public static Object translateInputKBArticle(BaseModel<?> oldModel) {
		KBArticleClp oldCplModel = (KBArticleClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.knowledgebase.model.impl.KBArticleImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setUuid",
						new Class[] { String.class });

				String value0 = oldCplModel.getUuid();

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setKbArticleId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getKbArticleId());

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setResourcePrimKey",
						new Class[] { Long.TYPE });

				Long value2 = new Long(oldCplModel.getResourcePrimKey());

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setGroupId",
						new Class[] { Long.TYPE });

				Long value3 = new Long(oldCplModel.getGroupId());

				method3.invoke(newModel, value3);

				Method method4 = newModelClass.getMethod("setCompanyId",
						new Class[] { Long.TYPE });

				Long value4 = new Long(oldCplModel.getCompanyId());

				method4.invoke(newModel, value4);

				Method method5 = newModelClass.getMethod("setUserId",
						new Class[] { Long.TYPE });

				Long value5 = new Long(oldCplModel.getUserId());

				method5.invoke(newModel, value5);

				Method method6 = newModelClass.getMethod("setUserName",
						new Class[] { String.class });

				String value6 = oldCplModel.getUserName();

				method6.invoke(newModel, value6);

				Method method7 = newModelClass.getMethod("setCreateDate",
						new Class[] { Date.class });

				Date value7 = oldCplModel.getCreateDate();

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setModifiedDate",
						new Class[] { Date.class });

				Date value8 = oldCplModel.getModifiedDate();

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setRootResourcePrimKey",
						new Class[] { Long.TYPE });

				Long value9 = new Long(oldCplModel.getRootResourcePrimKey());

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setParentResourcePrimKey",
						new Class[] { Long.TYPE });

				Long value10 = new Long(oldCplModel.getParentResourcePrimKey());

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setVersion",
						new Class[] { Integer.TYPE });

				Integer value11 = new Integer(oldCplModel.getVersion());

				method11.invoke(newModel, value11);

				Method method12 = newModelClass.getMethod("setTitle",
						new Class[] { String.class });

				String value12 = oldCplModel.getTitle();

				method12.invoke(newModel, value12);

				Method method13 = newModelClass.getMethod("setContent",
						new Class[] { String.class });

				String value13 = oldCplModel.getContent();

				method13.invoke(newModel, value13);

				Method method14 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value14 = oldCplModel.getDescription();

				method14.invoke(newModel, value14);

				Method method15 = newModelClass.getMethod("setPriority",
						new Class[] { Double.TYPE });

				Double value15 = new Double(oldCplModel.getPriority());

				method15.invoke(newModel, value15);

				Method method16 = newModelClass.getMethod("setSections",
						new Class[] { String.class });

				String value16 = oldCplModel.getSections();

				method16.invoke(newModel, value16);

				Method method17 = newModelClass.getMethod("setViewCount",
						new Class[] { Integer.TYPE });

				Integer value17 = new Integer(oldCplModel.getViewCount());

				method17.invoke(newModel, value17);

				Method method18 = newModelClass.getMethod("setLatest",
						new Class[] { Boolean.TYPE });

				Boolean value18 = new Boolean(oldCplModel.getLatest());

				method18.invoke(newModel, value18);

				Method method19 = newModelClass.getMethod("setMain",
						new Class[] { Boolean.TYPE });

				Boolean value19 = new Boolean(oldCplModel.getMain());

				method19.invoke(newModel, value19);

				Method method20 = newModelClass.getMethod("setStatus",
						new Class[] { Integer.TYPE });

				Integer value20 = new Integer(oldCplModel.getStatus());

				method20.invoke(newModel, value20);

				Method method21 = newModelClass.getMethod("setStatusByUserId",
						new Class[] { Long.TYPE });

				Long value21 = new Long(oldCplModel.getStatusByUserId());

				method21.invoke(newModel, value21);

				Method method22 = newModelClass.getMethod("setStatusByUserName",
						new Class[] { String.class });

				String value22 = oldCplModel.getStatusByUserName();

				method22.invoke(newModel, value22);

				Method method23 = newModelClass.getMethod("setStatusDate",
						new Class[] { Date.class });

				Date value23 = oldCplModel.getStatusDate();

				method23.invoke(newModel, value23);

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

	public static Object translateInputKBComment(BaseModel<?> oldModel) {
		KBCommentClp oldCplModel = (KBCommentClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.knowledgebase.model.impl.KBCommentImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setUuid",
						new Class[] { String.class });

				String value0 = oldCplModel.getUuid();

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setKbCommentId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getKbCommentId());

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

				Method method10 = newModelClass.getMethod("setContent",
						new Class[] { String.class });

				String value10 = oldCplModel.getContent();

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setHelpful",
						new Class[] { Boolean.TYPE });

				Boolean value11 = new Boolean(oldCplModel.getHelpful());

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

	public static Object translateInputKBTemplate(BaseModel<?> oldModel) {
		KBTemplateClp oldCplModel = (KBTemplateClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.knowledgebase.model.impl.KBTemplateImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setUuid",
						new Class[] { String.class });

				String value0 = oldCplModel.getUuid();

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setKbTemplateId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getKbTemplateId());

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

				Method method9 = newModelClass.getMethod("setContent",
						new Class[] { String.class });

				String value9 = oldCplModel.getContent();

				method9.invoke(newModel, value9);

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
					"com.liferay.knowledgebase.model.impl.KBArticleImpl")) {
			return translateOutputKBArticle(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.knowledgebase.model.impl.KBCommentImpl")) {
			return translateOutputKBComment(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.knowledgebase.model.impl.KBTemplateImpl")) {
			return translateOutputKBTemplate(oldModel);
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

	public static Object translateOutputKBArticle(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				KBArticleClp newModel = new KBArticleClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getUuid");

				String value0 = (String)method0.invoke(oldModel, (Object[])null);

				newModel.setUuid(value0);

				Method method1 = oldModelClass.getMethod("getKbArticleId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setKbArticleId(value1);

				Method method2 = oldModelClass.getMethod("getResourcePrimKey");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setResourcePrimKey(value2);

				Method methodIsResourceMain = oldModelClass.getMethod(
						"isResourceMain");

				Boolean resourceMain = (Boolean)methodIsResourceMain.invoke(oldModel,
						(Object[])null);

				newModel.setResourceMain(resourceMain);

				Method method3 = oldModelClass.getMethod("getGroupId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value3);

				Method method4 = oldModelClass.getMethod("getCompanyId");

				Long value4 = (Long)method4.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value4);

				Method method5 = oldModelClass.getMethod("getUserId");

				Long value5 = (Long)method5.invoke(oldModel, (Object[])null);

				newModel.setUserId(value5);

				Method method6 = oldModelClass.getMethod("getUserName");

				String value6 = (String)method6.invoke(oldModel, (Object[])null);

				newModel.setUserName(value6);

				Method method7 = oldModelClass.getMethod("getCreateDate");

				Date value7 = (Date)method7.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value7);

				Method method8 = oldModelClass.getMethod("getModifiedDate");

				Date value8 = (Date)method8.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value8);

				Method method9 = oldModelClass.getMethod(
						"getRootResourcePrimKey");

				Long value9 = (Long)method9.invoke(oldModel, (Object[])null);

				newModel.setRootResourcePrimKey(value9);

				Method method10 = oldModelClass.getMethod(
						"getParentResourcePrimKey");

				Long value10 = (Long)method10.invoke(oldModel, (Object[])null);

				newModel.setParentResourcePrimKey(value10);

				Method method11 = oldModelClass.getMethod("getVersion");

				Integer value11 = (Integer)method11.invoke(oldModel,
						(Object[])null);

				newModel.setVersion(value11);

				Method method12 = oldModelClass.getMethod("getTitle");

				String value12 = (String)method12.invoke(oldModel,
						(Object[])null);

				newModel.setTitle(value12);

				Method method13 = oldModelClass.getMethod("getContent");

				String value13 = (String)method13.invoke(oldModel,
						(Object[])null);

				newModel.setContent(value13);

				Method method14 = oldModelClass.getMethod("getDescription");

				String value14 = (String)method14.invoke(oldModel,
						(Object[])null);

				newModel.setDescription(value14);

				Method method15 = oldModelClass.getMethod("getPriority");

				Double value15 = (Double)method15.invoke(oldModel,
						(Object[])null);

				newModel.setPriority(value15);

				Method method16 = oldModelClass.getMethod("getSections");

				String value16 = (String)method16.invoke(oldModel,
						(Object[])null);

				newModel.setSections(value16);

				Method method17 = oldModelClass.getMethod("getViewCount");

				Integer value17 = (Integer)method17.invoke(oldModel,
						(Object[])null);

				newModel.setViewCount(value17);

				Method method18 = oldModelClass.getMethod("getLatest");

				Boolean value18 = (Boolean)method18.invoke(oldModel,
						(Object[])null);

				newModel.setLatest(value18);

				Method method19 = oldModelClass.getMethod("getMain");

				Boolean value19 = (Boolean)method19.invoke(oldModel,
						(Object[])null);

				newModel.setMain(value19);

				Method method20 = oldModelClass.getMethod("getStatus");

				Integer value20 = (Integer)method20.invoke(oldModel,
						(Object[])null);

				newModel.setStatus(value20);

				Method method21 = oldModelClass.getMethod("getStatusByUserId");

				Long value21 = (Long)method21.invoke(oldModel, (Object[])null);

				newModel.setStatusByUserId(value21);

				Method method22 = oldModelClass.getMethod("getStatusByUserName");

				String value22 = (String)method22.invoke(oldModel,
						(Object[])null);

				newModel.setStatusByUserName(value22);

				Method method23 = oldModelClass.getMethod("getStatusDate");

				Date value23 = (Date)method23.invoke(oldModel, (Object[])null);

				newModel.setStatusDate(value23);

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

	public static Object translateOutputKBComment(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				KBCommentClp newModel = new KBCommentClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getUuid");

				String value0 = (String)method0.invoke(oldModel, (Object[])null);

				newModel.setUuid(value0);

				Method method1 = oldModelClass.getMethod("getKbCommentId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setKbCommentId(value1);

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

				Method method10 = oldModelClass.getMethod("getContent");

				String value10 = (String)method10.invoke(oldModel,
						(Object[])null);

				newModel.setContent(value10);

				Method method11 = oldModelClass.getMethod("getHelpful");

				Boolean value11 = (Boolean)method11.invoke(oldModel,
						(Object[])null);

				newModel.setHelpful(value11);

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

	public static Object translateOutputKBTemplate(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				KBTemplateClp newModel = new KBTemplateClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getUuid");

				String value0 = (String)method0.invoke(oldModel, (Object[])null);

				newModel.setUuid(value0);

				Method method1 = oldModelClass.getMethod("getKbTemplateId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setKbTemplateId(value1);

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

				Method method9 = oldModelClass.getMethod("getContent");

				String value9 = (String)method9.invoke(oldModel, (Object[])null);

				newModel.setContent(value9);

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