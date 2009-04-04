/*
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.bi.reporting.jdbc;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.jdbc.DataSourceFactory;

import java.util.Enumeration;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;

/**
 * <a href="C3P0DataSourceFactory.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class C3P0DataSourceFactory implements DataSourceFactory {

	public DataSource createDataSource(Properties connectionProps)
		throws SystemException {
		DataSource dataSource = null;//new ComboPooledDataSource();

		if (connectionProps != null) {
			Enumeration<String> enu =
				(Enumeration<String>) connectionProps.propertyNames();

			try {
				while (enu.hasMoreElements()) {
					String key = enu.nextElement();

					String value = connectionProps.getProperty(key);
					BeanUtils.setProperty(dataSource, key, value);
				}
			}
			catch (Exception e) {
				throw new SystemException(
					"Unable to initialize datasource: " + connectionProps, e);
			}
		}

		return dataSource;
	}
}
