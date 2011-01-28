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

import com.liferay.knowledgebase.model.ArticleClp;
import com.liferay.knowledgebase.model.CommentClp;
import com.liferay.knowledgebase.model.TemplateClp;

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
			catch (Exception e) {
				_log.error(e, e);
			}

			if (Validator.isNull(_servletContextName)) {
				try {
					String propsUtilServletContextName = PropsUtil.get(
							"knowledge-base-portlet-deployment-context");

					if (Validator.isNotNull(propsUtilServletContextName)) {
						_servletContextName = propsUtilServletContextName;
					}
				}
				catch (Exception e) {
					_log.error(e, e);
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

		if (oldModelClassName.equals(ArticleClp.class.getName())) {
			ArticleClp oldCplModel = (ArticleClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.knowledgebase.model.impl.ArticleImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setUuid",
							new Class[] { String.class });

					String value0 = oldCplModel.getUuid();

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setArticleId",
							new Class[] { Long.TYPE });

					Long value1 = new Long(oldCplModel.getArticleId());

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

					Method method9 = newModelClass.getMethod("setParentResourcePrimKey",
							new Class[] { Long.TYPE });

					Long value9 = new Long(oldCplModel.getParentResourcePrimKey());

					method9.invoke(newModel, value9);

					Method method10 = newModelClass.getMethod("setVersion",
							new Class[] { Integer.TYPE });

					Integer value10 = new Integer(oldCplModel.getVersion());

					method10.invoke(newModel, value10);

					Method method11 = newModelClass.getMethod("setTitle",
							new Class[] { String.class });

					String value11 = oldCplModel.getTitle();

					method11.invoke(newModel, value11);

					Method method12 = newModelClass.getMethod("setContent",
							new Class[] { String.class });

					String value12 = oldCplModel.getContent();

					method12.invoke(newModel, value12);

					Method method13 = newModelClass.getMethod("setDescription",
							new Class[] { String.class });

					String value13 = oldCplModel.getDescription();

					method13.invoke(newModel, value13);

					Method method14 = newModelClass.getMethod("setPriority",
							new Class[] { Long.TYPE });

					Long value14 = new Long(oldCplModel.getPriority());

					method14.invoke(newModel, value14);

					Method method15 = newModelClass.getMethod("setLatest",
							new Class[] { Integer.TYPE });

					Integer value15 = new Integer(oldCplModel.getLatest());

					method15.invoke(newModel, value15);

					Method method16 = newModelClass.getMethod("setStatus",
							new Class[] { Integer.TYPE });

					Integer value16 = new Integer(oldCplModel.getStatus());

					method16.invoke(newModel, value16);

					Method method17 = newModelClass.getMethod("setStatusByUserId",
							new Class[] { Long.TYPE });

					Long value17 = new Long(oldCplModel.getStatusByUserId());

					method17.invoke(newModel, value17);

					Method method18 = newModelClass.getMethod("setStatusByUserName",
							new Class[] { String.class });

					String value18 = oldCplModel.getStatusByUserName();

					method18.invoke(newModel, value18);

					Method method19 = newModelClass.getMethod("setStatusDate",
							new Class[] { Date.class });

					Date value19 = oldCplModel.getStatusDate();

					method19.invoke(newModel, value19);

					return newModel;
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
			finally {
				Thread.currentThread().setContextClassLoader(contextClassLoader);
			}
		}

		if (oldModelClassName.equals(CommentClp.class.getName())) {
			CommentClp oldCplModel = (CommentClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.knowledgebase.model.impl.CommentImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setUuid",
							new Class[] { String.class });

					String value0 = oldCplModel.getUuid();

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setCommentId",
							new Class[] { Long.TYPE });

					Long value1 = new Long(oldCplModel.getCommentId());

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
				Thread.currentThread().setContextClassLoader(contextClassLoader);
			}
		}

		if (oldModelClassName.equals(TemplateClp.class.getName())) {
			TemplateClp oldCplModel = (TemplateClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.knowledgebase.model.impl.TemplateImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setUuid",
							new Class[] { String.class });

					String value0 = oldCplModel.getUuid();

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setTemplateId",
							new Class[] { Long.TYPE });

					Long value1 = new Long(oldCplModel.getTemplateId());

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

					Method method10 = newModelClass.getMethod("setDescription",
							new Class[] { String.class });

					String value10 = oldCplModel.getDescription();

					method10.invoke(newModel, value10);

					return newModel;
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
			finally {
				Thread.currentThread().setContextClassLoader(contextClassLoader);
			}
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
					"com.liferay.knowledgebase.model.impl.ArticleImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					ArticleClp newModel = new ArticleClp();

					Method method0 = oldModelClass.getMethod("getUuid");

					String value0 = (String)method0.invoke(oldModel,
							(Object[])null);

					newModel.setUuid(value0);

					Method method1 = oldModelClass.getMethod("getArticleId");

					Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

					newModel.setArticleId(value1);

					Method method2 = oldModelClass.getMethod(
							"getResourcePrimKey");

					Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

					newModel.setResourcePrimKey(value2);

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

					String value6 = (String)method6.invoke(oldModel,
							(Object[])null);

					newModel.setUserName(value6);

					Method method7 = oldModelClass.getMethod("getCreateDate");

					Date value7 = (Date)method7.invoke(oldModel, (Object[])null);

					newModel.setCreateDate(value7);

					Method method8 = oldModelClass.getMethod("getModifiedDate");

					Date value8 = (Date)method8.invoke(oldModel, (Object[])null);

					newModel.setModifiedDate(value8);

					Method method9 = oldModelClass.getMethod(
							"getParentResourcePrimKey");

					Long value9 = (Long)method9.invoke(oldModel, (Object[])null);

					newModel.setParentResourcePrimKey(value9);

					Method method10 = oldModelClass.getMethod("getVersion");

					Integer value10 = (Integer)method10.invoke(oldModel,
							(Object[])null);

					newModel.setVersion(value10);

					Method method11 = oldModelClass.getMethod("getTitle");

					String value11 = (String)method11.invoke(oldModel,
							(Object[])null);

					newModel.setTitle(value11);

					Method method12 = oldModelClass.getMethod("getContent");

					String value12 = (String)method12.invoke(oldModel,
							(Object[])null);

					newModel.setContent(value12);

					Method method13 = oldModelClass.getMethod("getDescription");

					String value13 = (String)method13.invoke(oldModel,
							(Object[])null);

					newModel.setDescription(value13);

					Method method14 = oldModelClass.getMethod("getPriority");

					Long value14 = (Long)method14.invoke(oldModel,
							(Object[])null);

					newModel.setPriority(value14);

					Method method15 = oldModelClass.getMethod("getLatest");

					Integer value15 = (Integer)method15.invoke(oldModel,
							(Object[])null);

					newModel.setLatest(value15);

					Method method16 = oldModelClass.getMethod("getStatus");

					Integer value16 = (Integer)method16.invoke(oldModel,
							(Object[])null);

					newModel.setStatus(value16);

					Method method17 = oldModelClass.getMethod(
							"getStatusByUserId");

					Long value17 = (Long)method17.invoke(oldModel,
							(Object[])null);

					newModel.setStatusByUserId(value17);

					Method method18 = oldModelClass.getMethod(
							"getStatusByUserName");

					String value18 = (String)method18.invoke(oldModel,
							(Object[])null);

					newModel.setStatusByUserName(value18);

					Method method19 = oldModelClass.getMethod("getStatusDate");

					Date value19 = (Date)method19.invoke(oldModel,
							(Object[])null);

					newModel.setStatusDate(value19);

					return newModel;
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
			finally {
				Thread.currentThread().setContextClassLoader(contextClassLoader);
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.knowledgebase.model.impl.CommentImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					CommentClp newModel = new CommentClp();

					Method method0 = oldModelClass.getMethod("getUuid");

					String value0 = (String)method0.invoke(oldModel,
							(Object[])null);

					newModel.setUuid(value0);

					Method method1 = oldModelClass.getMethod("getCommentId");

					Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

					newModel.setCommentId(value1);

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

					String value5 = (String)method5.invoke(oldModel,
							(Object[])null);

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
				Thread.currentThread().setContextClassLoader(contextClassLoader);
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.knowledgebase.model.impl.TemplateImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					TemplateClp newModel = new TemplateClp();

					Method method0 = oldModelClass.getMethod("getUuid");

					String value0 = (String)method0.invoke(oldModel,
							(Object[])null);

					newModel.setUuid(value0);

					Method method1 = oldModelClass.getMethod("getTemplateId");

					Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

					newModel.setTemplateId(value1);

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

					String value5 = (String)method5.invoke(oldModel,
							(Object[])null);

					newModel.setUserName(value5);

					Method method6 = oldModelClass.getMethod("getCreateDate");

					Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

					newModel.setCreateDate(value6);

					Method method7 = oldModelClass.getMethod("getModifiedDate");

					Date value7 = (Date)method7.invoke(oldModel, (Object[])null);

					newModel.setModifiedDate(value7);

					Method method8 = oldModelClass.getMethod("getTitle");

					String value8 = (String)method8.invoke(oldModel,
							(Object[])null);

					newModel.setTitle(value8);

					Method method9 = oldModelClass.getMethod("getContent");

					String value9 = (String)method9.invoke(oldModel,
							(Object[])null);

					newModel.setContent(value9);

					Method method10 = oldModelClass.getMethod("getDescription");

					String value10 = (String)method10.invoke(oldModel,
							(Object[])null);

					newModel.setDescription(value10);

					return newModel;
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
			finally {
				Thread.currentThread().setContextClassLoader(contextClassLoader);
			}
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

	private static Log _log = LogFactoryUtil.getLog(ClpSerializer.class);
	private static ClassLoader _classLoader;
	private static String _servletContextName;
}