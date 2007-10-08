/**
 * Copyright (c) 2000-2007 Liferay, Inc. All rights reserved.
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

package com.liferay.portlet.service;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.service.PropsUtil;
import com.liferay.util.spring.hibernate.TransactionAwareConfiguration;

import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

/**
 * <a href="HibernateConfiguration.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class HibernateConfiguration extends org.springframework.orm.hibernate3.LocalSessionFactoryBean {

	protected Configuration newConfiguration() {
		Configuration cfg = new Configuration();

		try {
			ClassLoader classLoader = getClass().getClassLoader();

			String[] configs = PropsUtil.getArray(PropsUtil.HIBERNATE_CONFIGS);

			for (int i = 0; i < configs.length; i++) {
				try {
					InputStream is =
						classLoader.getResourceAsStream(configs[i]);

					if (is != null) {
						cfg = cfg.addInputStream(is);

						is.close();
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}

			cfg.setProperties(PropsUtil.getProperties());
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return cfg;
	}

	protected void postProcessConfiguration(Configuration cfg) {

		// Make sure that the Hibernate settings from PropsUtil are set. See the
		// buildSessionFactory implementation in the LocalSessionFactoryBean
		// class to understand how Spring automates a lot of configuration for
		// Hibernate.

		String connectionReleaseMode = PropsUtil.get(
			Environment.RELEASE_CONNECTIONS);

		if (Validator.isNotNull(connectionReleaseMode)) {
			cfg.setProperty(
				Environment.RELEASE_CONNECTIONS, connectionReleaseMode);
		}
	}

	private static Log _log = LogFactory.getLog(HibernateConfiguration.class);

}