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

package com.liferay.portal.servlet.context;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Lawrence Lee
 */
public class ExtAddEntryWebXmlPortalContextLoaderListener
	implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		System.out.println("EXT_ADD_ENTRY_WEBXML_INSTALLED_SUCCESSFULLY");
	}

}