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

package com.liferay.samplestruts.servlet;

import com.liferay.portal.kernel.resiliency.spi.agent.annotation.Direction;
import com.liferay.portal.kernel.resiliency.spi.agent.annotation.DistributedRegistry;
import com.liferay.portal.kernel.resiliency.spi.agent.annotation.MatchType;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.jsp.PageContext;

/**
 *
 * @author Shuyang Zhou
 */
public class DistributedAttributeServletContextListener
	implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		DistributedRegistry.unregisterDistributed(
			PageContext.EXCEPTION, Direction.DUPLEX, MatchType.POSTFIX);

		DistributedRegistry.unregisterDistributed(
			"file_name", Direction.DUPLEX, MatchType.POSTFIX);
		DistributedRegistry.unregisterDistributed(
			"x_param", Direction.DUPLEX, MatchType.POSTFIX);
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		DistributedRegistry.registerDistributed(
			PageContext.EXCEPTION, Direction.DUPLEX, MatchType.POSTFIX);

		DistributedRegistry.registerDistributed(
			"file_name", Direction.DUPLEX, MatchType.POSTFIX);
		DistributedRegistry.registerDistributed(
			"x_param", Direction.DUPLEX, MatchType.POSTFIX);
	}

}