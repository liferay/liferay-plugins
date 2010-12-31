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

package com.liferay.portal.workflow.jbpm.spring.hibernate;

import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

/**
 * @author Brian Wing Shun Chan
 */
public class JbpmHibernateConfiguration extends LocalSessionFactoryBean {

	public JbpmHibernateConfiguration() {
		DB db = DBFactoryUtil.getDB();

		String type = db.getType();

		String jbpmType = type;

		if (type.equals(DB.TYPE_HYPERSONIC)) {
			jbpmType = _JBPM_TYPE_HYPERSONIC;
		}
		else if (type.equals(DB.TYPE_INFORMIX)) {
			throw new RuntimeException("jBPM does not support Informix");
		}
		else if (type.equals(DB.TYPE_SAP)) {
			jbpmType = _JBPM_TYPE_SAP;
		}
		else if (type.equals(DB.TYPE_SQLSERVER)) {
			jbpmType = _JBPM_TYPE_SQLSERVER;
		}

		String configLocation = "/META-INF/hibernate.cfg." + jbpmType + ".xml";

		if (_log.isInfoEnabled()) {
			_log.info("Loading " + configLocation);
		}

		Resource[] resources = new ClassPathResource[] {
			new ClassPathResource(configLocation),
			new ClassPathResource("/META-INF/hibernate-ext.cfg.xml")};

		setConfigLocations(resources);
	}

	private static final String _JBPM_TYPE_HYPERSONIC = "hsqldb";

	private static final String _JBPM_TYPE_SAP = "sapdb";

	private static final String _JBPM_TYPE_SQLSERVER = "mssql";

	private static Log _log = LogFactoryUtil.getLog(
		JbpmHibernateConfiguration.class);

}