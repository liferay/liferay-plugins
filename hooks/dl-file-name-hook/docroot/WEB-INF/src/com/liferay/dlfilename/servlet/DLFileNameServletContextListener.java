/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.dlfilename.servlet;

import com.liferay.dlfilename.hook.util.DLFileNameDLImpl;
import com.liferay.portal.kernel.util.BasePortalLifecycle;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portlet.documentlibrary.util.DL;
import com.liferay.portlet.documentlibrary.util.DLUtil;

import java.lang.reflect.Field;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Preston Crary
 */
public class DLFileNameServletContextListener
	extends BasePortalLifecycle implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		portalDestroy();
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		registerPortalLifecycle();
	}

	@Override
	protected void doPortalDestroy() throws Exception {
		updateDL();
	}

	@Override
	protected void doPortalInit() throws Exception {
		updateDL();
	}

	protected void updateDL() throws Exception {
		ClassLoader classLoader = PortalClassLoaderUtil.getClassLoader();

		Class<?> clazz = classLoader.loadClass(DLUtil.class.getName());

		Field dlField = ReflectionUtil.getDeclaredField(clazz, "_dl");

		DL dl = (DL)dlField.get(null);

		if (dl instanceof DLFileNameDLImpl) {
			DLFileNameDLImpl dlFileNameDLImpl = (DLFileNameDLImpl)dl;

			dlField.set(null, dlFileNameDLImpl.getDL());
		}
		else {
			dlField.set(null, new DLFileNameDLImpl(dl));
		}
	}

}