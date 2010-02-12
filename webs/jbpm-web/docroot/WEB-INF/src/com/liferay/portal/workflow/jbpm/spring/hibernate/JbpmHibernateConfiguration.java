/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
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
 * <a href="JbpmHibernateConfiguration.java.html"><b><i>View Source</i></b></a>
 *
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