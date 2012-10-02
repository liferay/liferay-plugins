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

package com.liferay.so.compat.servlet;

import com.liferay.portal.kernel.portlet.PortletBag;
import com.liferay.portal.kernel.portlet.PortletBagPool;
import com.liferay.portal.kernel.util.BasePortalLifecycle;
import com.liferay.portal.kernel.util.ClassUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.webdav.WebDAVStorage;
import com.liferay.portal.kernel.webdav.WebDAVStorageWrapper;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.so.compat.hook.sharepoint.SharepointInvocationHandler;
import com.liferay.so.compat.hook.webdav.SOCompatDLWebDAVStorageImpl;
import com.liferay.so.compat.util.SOCompatConstants;

import java.lang.Object;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Brian Wing Shun Chan
 */
public class SOCompatServletContextListener
	extends BasePortalLifecycle implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		portalDestroy();
	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		registerPortalLifecycle();
	}

	@Override
	protected void doPortalDestroy() throws Exception {
		updateWebDAVStorage();
	}

	@Override
	protected void doPortalInit() throws Exception {
		updateWebDAVStorage();
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
			method.invoke(null, null);
		}
		catch (Exception e) {

			// Call a method to ensure it's instantiated

		}

		Field field = methodFactoryClass.getField("_methods");

		field.setAccessible(true);

		Map<String, Object> sharepointMethods = (Map<String, Object>)field.get(
			null);

		updateSharepointMethods(classLoader, sharepointMethods);
	}

	protected void updateSharepointMethods(
			ClassLoader classLoader, Map<String, Object> sharepointMethods)
		throws Exception {

		Class<?> sharepointBaseMethodImplClass = classLoader.loadClass(
			"com.liferay.portal.sharepoint.methods.BaseMethodImpl");
		Class<?> sharepointMethodClass = classLoader.loadClass(
			"com.liferay.portal.sharepoint.methods.Method");

		Set<Map.Entry<String, Object>> entrySet =
			new HashSet<Map.Entry<String, Object>>(
				sharepointMethods.entrySet());

		for (Map.Entry<String, Object> entry : entrySet) {
			String sharepointMethodName = entry.getKey();
			Object sharepointMethod = entry.getValue();

			if (ClassUtil.isSubclass(
					sharepointMethod.getClass(),
					sharepointBaseMethodImplClass)) {

				// Override portal Sharepoint method

				Object newSharepointMethod = ProxyUtil.newProxyInstance(
					classLoader, new Class<?>[] {sharepointMethodClass},
					new SharepointInvocationHandler(sharepointMethod));

				sharepointMethods.put(
					sharepointMethodName, newSharepointMethod);
			}
			else {

				// Restore portal Sharepoint method

				InvocationHandler invocationHandler =
					ProxyUtil.getInvocationHandler(sharepointMethod);

				if (invocationHandler instanceof SharepointInvocationHandler) {
					SharepointInvocationHandler sharepointInvocationHandler =
						(SharepointInvocationHandler)invocationHandler;

					Object oldSharepointMethod =
						sharepointInvocationHandler.getSharepointMethod();

					sharepointMethods.put(
						sharepointMethodName, oldSharepointMethod);
				}
			}
		}
	}

	protected void updateWebDAVStorage() throws Exception {
		List<Portlet> portlets = PortletLocalServiceUtil.getPortlets();

		for (Portlet portlet : portlets) {
			updateWebDAVStorage(portlet);
		}
	}

	protected void updateWebDAVStorage(Portlet portlet) throws Exception {
		PortletBag portletBag = PortletBagPool.get(portlet.getRootPortletId());

		WebDAVStorage webDAVStorage = portletBag.getWebDAVStorageInstance();

		Class<?> webDAVStorageClass = webDAVStorage.getClass();

		String webDAVStorageClassName = webDAVStorageClass.getName();

		if (webDAVStorageClassName.equals(
				SOCompatConstants.CLASS_NAME_DL_WEBDAV_STORAGE_IMPL)) {

			// Override portal DLWebDAVStorageImpl

			WebDAVStorageWrapper webDAVStorageWrapper =
				new SOCompatDLWebDAVStorageImpl(webDAVStorage);

			updateWebDAVStorage(portletBag, webDAVStorageWrapper);
		}
		else if (webDAVStorage instanceof SOCompatDLWebDAVStorageImpl) {

			// Restore portal DLWebDAVStorageImpl

			WebDAVStorageWrapper webDAVStorageWrapper =
				(WebDAVStorageWrapper)webDAVStorage;

			updateWebDAVStorage(
				portletBag, webDAVStorageWrapper.getWrappedWebDAVStorage());
		}
	}

	protected void updateWebDAVStorage(
			PortletBag portletBag, WebDAVStorage webDAVStorage)
		throws Exception {

		Class<?> portletBagClass = portletBag.getClass();

		Field field = portletBagClass.getField("_webDAVStorageInstance");

		field.setAccessible(true);

		field.set(portletBag, webDAVStorage);
	}

}