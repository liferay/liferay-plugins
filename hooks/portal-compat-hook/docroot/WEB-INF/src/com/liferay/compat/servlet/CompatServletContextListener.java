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

package com.liferay.compat.servlet;

import com.liferay.compat.hook.repository.cmis.CompatCMISRepositoryFactoryInvocationHandler;
import com.liferay.compat.hook.sharepoint.CompatSharepointInvocationHandler;
import com.liferay.compat.hook.webdav.CompatDLWebDAVStorageImpl;
import com.liferay.compat.util.CompatConstants;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.BasePortalLifecycle;
import com.liferay.portal.kernel.util.ClassUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.webdav.WebDAVStorage;
import com.liferay.portal.kernel.webdav.WebDAVStorageWrapper;
import com.liferay.portal.kernel.webdav.WebDAVUtil;

import java.lang.Object;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Brian Wing Shun Chan
 */
public class CompatServletContextListener
	extends BasePortalLifecycle implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		portalDestroy();
	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		registerPortalLifecycle();
	}

	@Override
	protected void doPortalDestroy() throws Exception {
		updateRepositories();
		updateSharepointMethods();
		updateWebDAVStorages();
	}

	@Override
	protected void doPortalInit() throws Exception {
		updateRepositories();
		updateSharepointMethods();
		updateWebDAVStorages();
	}

	protected void updateRepositories() throws Exception {
		ClassLoader classLoader = PortalClassLoaderUtil.getClassLoader();

		Class<?> repositoryFactoryUtilClass = classLoader.loadClass(
			"com.liferay.portal.repository.util.RepositoryFactoryUtil");

		Field repositoryFactoriesField =
			repositoryFactoryUtilClass.getDeclaredField("_repositoryFactories");

		repositoryFactoriesField.setAccessible(true);

		Map<String, Object> repositoryFactories =
			(Map<String, Object>)repositoryFactoriesField.get(null);

		if (_log.isInfoEnabled()) {
			_log.info(
				"Retrieved " + repositoryFactories.size() +
					" repository factories");
		}

		Set<Map.Entry<String, Object>> entrySet =
			new HashSet<Map.Entry<String, Object>>(
				repositoryFactories.entrySet());

		for (Map.Entry<String, Object> entry : entrySet) {
			updateRepository(
				classLoader, repositoryFactories, entry.getKey(),
				entry.getValue());
		}
	}

	protected void updateRepository(
			ClassLoader classLoader, Map<String, Object> repositoryFactories,
			String repositoryClassName, Object repositoryFactory)
		throws Exception {

		if (!repositoryClassName.startsWith(
				"com.liferay.portal.repository.cmis.CMIS")) {

			return;
		}

		Class<?> repositoryFactoryImplClass = classLoader.loadClass(
			"com.liferay.portal.repository.util.RepositoryFactoryImpl");

		if (ClassUtil.isSubclass(
				repositoryFactory.getClass(), repositoryFactoryImplClass)) {

			Class<?> repositoryFactoryClass = classLoader.loadClass(
				"com.liferay.portal.repository.util.RepositoryFactory");

			Object newRepositoryFactory = ProxyUtil.newProxyInstance(
				classLoader, new Class<?>[] {repositoryFactoryClass},
				new CompatCMISRepositoryFactoryInvocationHandler(
					repositoryFactory));

			if (_log.isInfoEnabled()) {
				_log.info(
					"Overriding " + repositoryFactory.getClass() + " with " +
						newRepositoryFactory.getClass());
			}

			repositoryFactories.put(repositoryClassName, newRepositoryFactory);
		}
		else {
			InvocationHandler invocationHandler =
				ProxyUtil.getInvocationHandler(repositoryFactory);

			if (invocationHandler
					instanceof CompatCMISRepositoryFactoryInvocationHandler) {

				CompatCMISRepositoryFactoryInvocationHandler
					compatCMISRepositoryFactoryInvocationHandler =
						(CompatCMISRepositoryFactoryInvocationHandler)
							invocationHandler;

				Object oldRepositoryFactory =
					compatCMISRepositoryFactoryInvocationHandler.
						getRepositoryFactory();

				if (_log.isInfoEnabled()) {
					_log.info(
						"Restoring " + repositoryFactory.getClass() + " with " +
							oldRepositoryFactory.getClass());
				}

				repositoryFactories.put(
					repositoryClassName, oldRepositoryFactory);
			}
		}
	}

	protected void updateSharepointMethod(
			ClassLoader classLoader, Map<String, Object> sharepointMethods,
			String sharepointMethodName, Object sharepointMethod)
		throws Exception {

		Class<?> sharepointBaseMethodImplClass = classLoader.loadClass(
			"com.liferay.portal.sharepoint.methods.BaseMethodImpl");

		if (ClassUtil.isSubclass(
				sharepointMethod.getClass(), sharepointBaseMethodImplClass)) {

			Class<?> sharepointMethodClass = classLoader.loadClass(
				"com.liferay.portal.sharepoint.methods.Method");

			Object newSharepointMethod = ProxyUtil.newProxyInstance(
				classLoader, new Class<?>[] {sharepointMethodClass},
				new CompatSharepointInvocationHandler(sharepointMethod));

			if (_log.isInfoEnabled()) {
				_log.info(
					"Overriding " + sharepointMethod.getClass() + " with " +
						newSharepointMethod.getClass());
			}

			sharepointMethods.put(sharepointMethodName, newSharepointMethod);
		}
		else {
			InvocationHandler invocationHandler =
				ProxyUtil.getInvocationHandler(sharepointMethod);

			if (invocationHandler
					instanceof CompatSharepointInvocationHandler) {

				CompatSharepointInvocationHandler
					compatSharepointInvocationHandler =
						(CompatSharepointInvocationHandler)invocationHandler;

				Object oldSharepointMethod =
					compatSharepointInvocationHandler.getSharepointMethod();

				if (_log.isInfoEnabled()) {
					_log.info(
						"Restoring " + sharepointMethod.getClass() +
							" with " + oldSharepointMethod.getClass());
				}

				sharepointMethods.put(
					sharepointMethodName, oldSharepointMethod);
			}
		}
	}

	protected void updateSharepointMethods() throws Exception {
		ClassLoader classLoader = PortalClassLoaderUtil.getClassLoader();

		Class<?> methodFactoryClass = classLoader.loadClass(
			"com.liferay.portal.sharepoint.methods.MethodFactory");

		Class<?> sharepointRequestClass = classLoader.loadClass(
			"com.liferay.portal.sharepoint.SharepointRequest");

		Method method = methodFactoryClass.getMethod(
			"create", sharepointRequestClass);

		try {
			method.invoke(null, (Object[])null);
		}
		catch (Exception e) {

			// Call a method to ensure it's instantiated

		}

		Field instanceField = methodFactoryClass.getDeclaredField("_instance");

		instanceField.setAccessible(true);

		Object instance = instanceField.get(null);

		Field methodsField = methodFactoryClass.getDeclaredField("_methods");

		methodsField.setAccessible(true);

		Map<String, Object> sharepointMethods =
			(Map<String, Object>)methodsField.get(instance);

		if (_log.isInfoEnabled()) {
			_log.info(
				"Retrieved " + sharepointMethods.size() +
					" Sharepoint methods");
		}

		Set<Map.Entry<String, Object>> entrySet =
			new HashSet<Map.Entry<String, Object>>(
				sharepointMethods.entrySet());

		for (Map.Entry<String, Object> entry : entrySet) {
			updateSharepointMethod(
				classLoader, sharepointMethods, entry.getKey(),
				entry.getValue());
		}
	}

	protected void updateWebDAVStorage(String token) throws Exception {
		WebDAVStorage webDAVStorage = WebDAVUtil.getStorage(token);

		Class<?> webDAVStorageClass = webDAVStorage.getClass();

		String webDAVStorageClassName = webDAVStorageClass.getName();

		if (webDAVStorageClassName.equals(
				CompatConstants.CLASS_NAME_DL_WEBDAV_STORAGE_IMPL)) {

			WebDAVStorageWrapper webDAVStorageWrapper =
				new CompatDLWebDAVStorageImpl(webDAVStorage);

			if (_log.isInfoEnabled()) {
				_log.info(
					"Overriding WebDAV storage " + webDAVStorageClass +
						" with " + webDAVStorageWrapper.getClass());
			}

			WebDAVUtil.addStorage(webDAVStorageWrapper);
		}
		else if (webDAVStorage instanceof CompatDLWebDAVStorageImpl) {
			WebDAVStorageWrapper webDAVStorageWrapper =
				(WebDAVStorageWrapper)webDAVStorage;

			WebDAVStorage wrappedWebDAVStorage =
				webDAVStorageWrapper.getWrappedWebDAVStorage();

			if (_log.isInfoEnabled()) {
				_log.info(
					"Restoring WebDAV storage " +
						webDAVStorageWrapper.getClass() + " with " +
							wrappedWebDAVStorage.getClass());
			}

			WebDAVUtil.addStorage(wrappedWebDAVStorage);
		}
	}

	protected void updateWebDAVStorages() throws Exception {
		Collection<String> tokens = WebDAVUtil.getStorageTokens();

		if (_log.isInfoEnabled()) {
			_log.info("Retrieved " + tokens.size() + " WebDAV storage tokens");
		}

		for (String token : tokens) {
			updateWebDAVStorage(token);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		CompatServletContextListener.class);

}