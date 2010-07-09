/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.jbpm.util;

import org.hibernate.SessionFactory;

import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.configuration.ObjectFactory;
import org.jbpm.configuration.ObjectFactoryParser;

/**
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class JbpmConfigurationUtil {

	public static JbpmConfiguration createJbpmConfiguration(
		SessionFactory sessionFactory, String resource) {

		ObjectFactory objectFactory = ObjectFactoryParser.parseResource(
			resource);

		JbpmConfiguration jbpmConfiguration = new JbpmConfiguration(
			objectFactory);

		JbpmContext jbpmContext = null;

		try {
			jbpmContext = jbpmConfiguration.createJbpmContext();

			jbpmContext.setSessionFactory(sessionFactory);
		}
		finally {
			if (jbpmContext != null) {
				jbpmContext.close();
			}
		}

		return jbpmConfiguration;
	}

}