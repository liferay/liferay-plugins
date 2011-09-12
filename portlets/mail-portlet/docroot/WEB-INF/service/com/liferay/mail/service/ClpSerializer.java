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

package com.liferay.mail.service;

import com.liferay.mail.model.AccountClp;
import com.liferay.mail.model.AttachmentClp;
import com.liferay.mail.model.FolderClp;
import com.liferay.mail.model.MessageClp;

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
						"mail-portlet-deployment-context");

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
							"mail-portlet-deployment-context");

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
				_servletContextName = "mail-portlet";
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

		if (oldModelClassName.equals(AccountClp.class.getName())) {
			return translateInputAccount(oldModel);
		}

		if (oldModelClassName.equals(AttachmentClp.class.getName())) {
			return translateInputAttachment(oldModel);
		}

		if (oldModelClassName.equals(FolderClp.class.getName())) {
			return translateInputFolder(oldModel);
		}

		if (oldModelClassName.equals(MessageClp.class.getName())) {
			return translateInputMessage(oldModel);
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

	public static Object translateInputAccount(BaseModel<?> oldModel) {
		AccountClp oldCplModel = (AccountClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.mail.model.impl.AccountImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setAccountId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getAccountId());

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

				Method method6 = newModelClass.getMethod("setAddress",
						new Class[] { String.class });

				String value6 = oldCplModel.getAddress();

				method6.invoke(newModel, value6);

				Method method7 = newModelClass.getMethod("setPersonalName",
						new Class[] { String.class });

				String value7 = oldCplModel.getPersonalName();

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setProtocol",
						new Class[] { String.class });

				String value8 = oldCplModel.getProtocol();

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setIncomingHostName",
						new Class[] { String.class });

				String value9 = oldCplModel.getIncomingHostName();

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setIncomingPort",
						new Class[] { Integer.TYPE });

				Integer value10 = new Integer(oldCplModel.getIncomingPort());

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setIncomingSecure",
						new Class[] { Boolean.TYPE });

				Boolean value11 = new Boolean(oldCplModel.getIncomingSecure());

				method11.invoke(newModel, value11);

				Method method12 = newModelClass.getMethod("setOutgoingHostName",
						new Class[] { String.class });

				String value12 = oldCplModel.getOutgoingHostName();

				method12.invoke(newModel, value12);

				Method method13 = newModelClass.getMethod("setOutgoingPort",
						new Class[] { Integer.TYPE });

				Integer value13 = new Integer(oldCplModel.getOutgoingPort());

				method13.invoke(newModel, value13);

				Method method14 = newModelClass.getMethod("setOutgoingSecure",
						new Class[] { Boolean.TYPE });

				Boolean value14 = new Boolean(oldCplModel.getOutgoingSecure());

				method14.invoke(newModel, value14);

				Method method15 = newModelClass.getMethod("setLogin",
						new Class[] { String.class });

				String value15 = oldCplModel.getLogin();

				method15.invoke(newModel, value15);

				Method method16 = newModelClass.getMethod("setPassword",
						new Class[] { String.class });

				String value16 = oldCplModel.getPassword();

				method16.invoke(newModel, value16);

				Method method17 = newModelClass.getMethod("setSavePassword",
						new Class[] { Boolean.TYPE });

				Boolean value17 = new Boolean(oldCplModel.getSavePassword());

				method17.invoke(newModel, value17);

				Method method18 = newModelClass.getMethod("setSignature",
						new Class[] { String.class });

				String value18 = oldCplModel.getSignature();

				method18.invoke(newModel, value18);

				Method method19 = newModelClass.getMethod("setUseSignature",
						new Class[] { Boolean.TYPE });

				Boolean value19 = new Boolean(oldCplModel.getUseSignature());

				method19.invoke(newModel, value19);

				Method method20 = newModelClass.getMethod("setFolderPrefix",
						new Class[] { String.class });

				String value20 = oldCplModel.getFolderPrefix();

				method20.invoke(newModel, value20);

				Method method21 = newModelClass.getMethod("setInboxFolderId",
						new Class[] { Long.TYPE });

				Long value21 = new Long(oldCplModel.getInboxFolderId());

				method21.invoke(newModel, value21);

				Method method22 = newModelClass.getMethod("setDraftFolderId",
						new Class[] { Long.TYPE });

				Long value22 = new Long(oldCplModel.getDraftFolderId());

				method22.invoke(newModel, value22);

				Method method23 = newModelClass.getMethod("setSentFolderId",
						new Class[] { Long.TYPE });

				Long value23 = new Long(oldCplModel.getSentFolderId());

				method23.invoke(newModel, value23);

				Method method24 = newModelClass.getMethod("setTrashFolderId",
						new Class[] { Long.TYPE });

				Long value24 = new Long(oldCplModel.getTrashFolderId());

				method24.invoke(newModel, value24);

				Method method25 = newModelClass.getMethod("setDefaultSender",
						new Class[] { Boolean.TYPE });

				Boolean value25 = new Boolean(oldCplModel.getDefaultSender());

				method25.invoke(newModel, value25);

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

	public static Object translateInputAttachment(BaseModel<?> oldModel) {
		AttachmentClp oldCplModel = (AttachmentClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.mail.model.impl.AttachmentImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setAttachmentId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getAttachmentId());

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setCompanyId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getCompanyId());

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setUserId",
						new Class[] { Long.TYPE });

				Long value2 = new Long(oldCplModel.getUserId());

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setAccountId",
						new Class[] { Long.TYPE });

				Long value3 = new Long(oldCplModel.getAccountId());

				method3.invoke(newModel, value3);

				Method method4 = newModelClass.getMethod("setFolderId",
						new Class[] { Long.TYPE });

				Long value4 = new Long(oldCplModel.getFolderId());

				method4.invoke(newModel, value4);

				Method method5 = newModelClass.getMethod("setMessageId",
						new Class[] { Long.TYPE });

				Long value5 = new Long(oldCplModel.getMessageId());

				method5.invoke(newModel, value5);

				Method method6 = newModelClass.getMethod("setContentPath",
						new Class[] { String.class });

				String value6 = oldCplModel.getContentPath();

				method6.invoke(newModel, value6);

				Method method7 = newModelClass.getMethod("setFileName",
						new Class[] { String.class });

				String value7 = oldCplModel.getFileName();

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setSize",
						new Class[] { Long.TYPE });

				Long value8 = new Long(oldCplModel.getSize());

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

	public static Object translateInputFolder(BaseModel<?> oldModel) {
		FolderClp oldCplModel = (FolderClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.mail.model.impl.FolderImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setFolderId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getFolderId());

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

				Method method6 = newModelClass.getMethod("setAccountId",
						new Class[] { Long.TYPE });

				Long value6 = new Long(oldCplModel.getAccountId());

				method6.invoke(newModel, value6);

				Method method7 = newModelClass.getMethod("setFullName",
						new Class[] { String.class });

				String value7 = oldCplModel.getFullName();

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setDisplayName",
						new Class[] { String.class });

				String value8 = oldCplModel.getDisplayName();

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setRemoteMessageCount",
						new Class[] { Integer.TYPE });

				Integer value9 = new Integer(oldCplModel.getRemoteMessageCount());

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

	public static Object translateInputMessage(BaseModel<?> oldModel) {
		MessageClp oldCplModel = (MessageClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.mail.model.impl.MessageImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setMessageId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getMessageId());

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

				Method method6 = newModelClass.getMethod("setAccountId",
						new Class[] { Long.TYPE });

				Long value6 = new Long(oldCplModel.getAccountId());

				method6.invoke(newModel, value6);

				Method method7 = newModelClass.getMethod("setFolderId",
						new Class[] { Long.TYPE });

				Long value7 = new Long(oldCplModel.getFolderId());

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setSender",
						new Class[] { String.class });

				String value8 = oldCplModel.getSender();

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setTo",
						new Class[] { String.class });

				String value9 = oldCplModel.getTo();

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setCc",
						new Class[] { String.class });

				String value10 = oldCplModel.getCc();

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setBcc",
						new Class[] { String.class });

				String value11 = oldCplModel.getBcc();

				method11.invoke(newModel, value11);

				Method method12 = newModelClass.getMethod("setSentDate",
						new Class[] { Date.class });

				Date value12 = oldCplModel.getSentDate();

				method12.invoke(newModel, value12);

				Method method13 = newModelClass.getMethod("setSubject",
						new Class[] { String.class });

				String value13 = oldCplModel.getSubject();

				method13.invoke(newModel, value13);

				Method method14 = newModelClass.getMethod("setPreview",
						new Class[] { String.class });

				String value14 = oldCplModel.getPreview();

				method14.invoke(newModel, value14);

				Method method15 = newModelClass.getMethod("setBody",
						new Class[] { String.class });

				String value15 = oldCplModel.getBody();

				method15.invoke(newModel, value15);

				Method method16 = newModelClass.getMethod("setFlags",
						new Class[] { String.class });

				String value16 = oldCplModel.getFlags();

				method16.invoke(newModel, value16);

				Method method17 = newModelClass.getMethod("setSize",
						new Class[] { Long.TYPE });

				Long value17 = new Long(oldCplModel.getSize());

				method17.invoke(newModel, value17);

				Method method18 = newModelClass.getMethod("setRemoteMessageId",
						new Class[] { Long.TYPE });

				Long value18 = new Long(oldCplModel.getRemoteMessageId());

				method18.invoke(newModel, value18);

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

		if (oldModelClassName.equals("com.liferay.mail.model.impl.AccountImpl")) {
			return translateOutputAccount(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.mail.model.impl.AttachmentImpl")) {
			return translateOutputAttachment(oldModel);
		}

		if (oldModelClassName.equals("com.liferay.mail.model.impl.FolderImpl")) {
			return translateOutputFolder(oldModel);
		}

		if (oldModelClassName.equals("com.liferay.mail.model.impl.MessageImpl")) {
			return translateOutputMessage(oldModel);
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

	public static Object translateOutputAccount(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				AccountClp newModel = new AccountClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getAccountId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setAccountId(value0);

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

				Method method6 = oldModelClass.getMethod("getAddress");

				String value6 = (String)method6.invoke(oldModel, (Object[])null);

				newModel.setAddress(value6);

				Method method7 = oldModelClass.getMethod("getPersonalName");

				String value7 = (String)method7.invoke(oldModel, (Object[])null);

				newModel.setPersonalName(value7);

				Method method8 = oldModelClass.getMethod("getProtocol");

				String value8 = (String)method8.invoke(oldModel, (Object[])null);

				newModel.setProtocol(value8);

				Method method9 = oldModelClass.getMethod("getIncomingHostName");

				String value9 = (String)method9.invoke(oldModel, (Object[])null);

				newModel.setIncomingHostName(value9);

				Method method10 = oldModelClass.getMethod("getIncomingPort");

				Integer value10 = (Integer)method10.invoke(oldModel,
						(Object[])null);

				newModel.setIncomingPort(value10);

				Method method11 = oldModelClass.getMethod("getIncomingSecure");

				Boolean value11 = (Boolean)method11.invoke(oldModel,
						(Object[])null);

				newModel.setIncomingSecure(value11);

				Method method12 = oldModelClass.getMethod("getOutgoingHostName");

				String value12 = (String)method12.invoke(oldModel,
						(Object[])null);

				newModel.setOutgoingHostName(value12);

				Method method13 = oldModelClass.getMethod("getOutgoingPort");

				Integer value13 = (Integer)method13.invoke(oldModel,
						(Object[])null);

				newModel.setOutgoingPort(value13);

				Method method14 = oldModelClass.getMethod("getOutgoingSecure");

				Boolean value14 = (Boolean)method14.invoke(oldModel,
						(Object[])null);

				newModel.setOutgoingSecure(value14);

				Method method15 = oldModelClass.getMethod("getLogin");

				String value15 = (String)method15.invoke(oldModel,
						(Object[])null);

				newModel.setLogin(value15);

				Method method16 = oldModelClass.getMethod("getPassword");

				String value16 = (String)method16.invoke(oldModel,
						(Object[])null);

				newModel.setPassword(value16);

				Method method17 = oldModelClass.getMethod("getSavePassword");

				Boolean value17 = (Boolean)method17.invoke(oldModel,
						(Object[])null);

				newModel.setSavePassword(value17);

				Method method18 = oldModelClass.getMethod("getSignature");

				String value18 = (String)method18.invoke(oldModel,
						(Object[])null);

				newModel.setSignature(value18);

				Method method19 = oldModelClass.getMethod("getUseSignature");

				Boolean value19 = (Boolean)method19.invoke(oldModel,
						(Object[])null);

				newModel.setUseSignature(value19);

				Method method20 = oldModelClass.getMethod("getFolderPrefix");

				String value20 = (String)method20.invoke(oldModel,
						(Object[])null);

				newModel.setFolderPrefix(value20);

				Method method21 = oldModelClass.getMethod("getInboxFolderId");

				Long value21 = (Long)method21.invoke(oldModel, (Object[])null);

				newModel.setInboxFolderId(value21);

				Method method22 = oldModelClass.getMethod("getDraftFolderId");

				Long value22 = (Long)method22.invoke(oldModel, (Object[])null);

				newModel.setDraftFolderId(value22);

				Method method23 = oldModelClass.getMethod("getSentFolderId");

				Long value23 = (Long)method23.invoke(oldModel, (Object[])null);

				newModel.setSentFolderId(value23);

				Method method24 = oldModelClass.getMethod("getTrashFolderId");

				Long value24 = (Long)method24.invoke(oldModel, (Object[])null);

				newModel.setTrashFolderId(value24);

				Method method25 = oldModelClass.getMethod("getDefaultSender");

				Boolean value25 = (Boolean)method25.invoke(oldModel,
						(Object[])null);

				newModel.setDefaultSender(value25);

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

	public static Object translateOutputAttachment(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				AttachmentClp newModel = new AttachmentClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getAttachmentId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setAttachmentId(value0);

				Method method1 = oldModelClass.getMethod("getCompanyId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value1);

				Method method2 = oldModelClass.getMethod("getUserId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setUserId(value2);

				Method method3 = oldModelClass.getMethod("getAccountId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setAccountId(value3);

				Method method4 = oldModelClass.getMethod("getFolderId");

				Long value4 = (Long)method4.invoke(oldModel, (Object[])null);

				newModel.setFolderId(value4);

				Method method5 = oldModelClass.getMethod("getMessageId");

				Long value5 = (Long)method5.invoke(oldModel, (Object[])null);

				newModel.setMessageId(value5);

				Method method6 = oldModelClass.getMethod("getContentPath");

				String value6 = (String)method6.invoke(oldModel, (Object[])null);

				newModel.setContentPath(value6);

				Method method7 = oldModelClass.getMethod("getFileName");

				String value7 = (String)method7.invoke(oldModel, (Object[])null);

				newModel.setFileName(value7);

				Method method8 = oldModelClass.getMethod("getSize");

				Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

				newModel.setSize(value8);

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

	public static Object translateOutputFolder(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				FolderClp newModel = new FolderClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getFolderId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setFolderId(value0);

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

				Method method6 = oldModelClass.getMethod("getAccountId");

				Long value6 = (Long)method6.invoke(oldModel, (Object[])null);

				newModel.setAccountId(value6);

				Method method7 = oldModelClass.getMethod("getFullName");

				String value7 = (String)method7.invoke(oldModel, (Object[])null);

				newModel.setFullName(value7);

				Method method8 = oldModelClass.getMethod("getDisplayName");

				String value8 = (String)method8.invoke(oldModel, (Object[])null);

				newModel.setDisplayName(value8);

				Method method9 = oldModelClass.getMethod(
						"getRemoteMessageCount");

				Integer value9 = (Integer)method9.invoke(oldModel,
						(Object[])null);

				newModel.setRemoteMessageCount(value9);

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

	public static Object translateOutputMessage(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				MessageClp newModel = new MessageClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getMessageId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setMessageId(value0);

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

				Method method6 = oldModelClass.getMethod("getAccountId");

				Long value6 = (Long)method6.invoke(oldModel, (Object[])null);

				newModel.setAccountId(value6);

				Method method7 = oldModelClass.getMethod("getFolderId");

				Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

				newModel.setFolderId(value7);

				Method method8 = oldModelClass.getMethod("getSender");

				String value8 = (String)method8.invoke(oldModel, (Object[])null);

				newModel.setSender(value8);

				Method method9 = oldModelClass.getMethod("getTo");

				String value9 = (String)method9.invoke(oldModel, (Object[])null);

				newModel.setTo(value9);

				Method method10 = oldModelClass.getMethod("getCc");

				String value10 = (String)method10.invoke(oldModel,
						(Object[])null);

				newModel.setCc(value10);

				Method method11 = oldModelClass.getMethod("getBcc");

				String value11 = (String)method11.invoke(oldModel,
						(Object[])null);

				newModel.setBcc(value11);

				Method method12 = oldModelClass.getMethod("getSentDate");

				Date value12 = (Date)method12.invoke(oldModel, (Object[])null);

				newModel.setSentDate(value12);

				Method method13 = oldModelClass.getMethod("getSubject");

				String value13 = (String)method13.invoke(oldModel,
						(Object[])null);

				newModel.setSubject(value13);

				Method method14 = oldModelClass.getMethod("getPreview");

				String value14 = (String)method14.invoke(oldModel,
						(Object[])null);

				newModel.setPreview(value14);

				Method method15 = oldModelClass.getMethod("getBody");

				String value15 = (String)method15.invoke(oldModel,
						(Object[])null);

				newModel.setBody(value15);

				Method method16 = oldModelClass.getMethod("getFlags");

				String value16 = (String)method16.invoke(oldModel,
						(Object[])null);

				newModel.setFlags(value16);

				Method method17 = oldModelClass.getMethod("getSize");

				Long value17 = (Long)method17.invoke(oldModel, (Object[])null);

				newModel.setSize(value17);

				Method method18 = oldModelClass.getMethod("getRemoteMessageId");

				Long value18 = (Long)method18.invoke(oldModel, (Object[])null);

				newModel.setRemoteMessageId(value18);

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