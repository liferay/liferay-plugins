/**
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

package com.liferay.sampledao.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Properties;

/**
 * <a href="ConnectionPool.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ConnectionPool {

	public static void cleanUp(Connection con) {
		_instance._cleanUp(con);
	}

	public static void cleanUp(Connection con, Statement s) {
		_instance._cleanUp(con, s);
	}

	public static void cleanUp(Connection con, Statement s, ResultSet rs) {
		_instance._cleanUp(con, s, rs);
	}

	public static void destroy() throws SQLException {
		_instance._destroy();
	}

	public static Connection getConnection() throws SQLException {
		return _instance._getConnection();
	}

	public static Properties getProperties() {
		return _instance._props;
	}

	private ConnectionPool() {
		try {

			// Properties

			ClassLoader classLoader = getClass().getClassLoader();

			_props = new Properties();

			_props.load(classLoader.getResourceAsStream(
				"connection-pool.properties"));

			_props.list(System.out);

			// Pooled data source

			String driverClass = _props.getProperty("driver.class");
			String jdbcUrl = _props.getProperty("jdbc.url");
			String user = _props.getProperty("user");
			String password = _props.getProperty("password");

			int minPoolSize = 5;

			try {
				minPoolSize = Integer.parseInt(
					_props.getProperty("min.pool.size"));
			}
			catch (Exception e) {
			}

			int maxPoolSize = 5;

			try {
				maxPoolSize = Integer.parseInt(
					_props.getProperty("max.pool.size"));
			}
			catch (Exception e) {
			}

			int acquireIncrement = 5;

			try {
				acquireIncrement = Integer.parseInt(
					_props.getProperty("acquire.increment"));
			}
			catch (Exception e) {
			}

			_cpds = new ComboPooledDataSource();

			_cpds.setDriverClass(driverClass);
			_cpds.setJdbcUrl(jdbcUrl);
			_cpds.setUser(user);
			_cpds.setPassword(password);

			_cpds.setMinPoolSize(minPoolSize);
			_cpds.setMaxPoolSize(maxPoolSize);
			_cpds.setAcquireIncrement(acquireIncrement);
		}
		catch (Exception e) {
			_log.error(e);
		}
	}

	private void _cleanUp(Connection con) {
		_cleanUp(con, null, null);
	}

	private void _cleanUp(Connection con, Statement s) {
		_cleanUp(con, s, null);
	}

	private void _cleanUp(Connection con, Statement s, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		}
		catch (SQLException sqle) {
			_log.error(sqle);
		}

		try {
			if (s != null) {
				s.close();
			}
		}
		catch (SQLException sqle) {
			_log.error(sqle);
		}

		try {
			if (con != null) {
				con.close();
			}
		}
		catch (SQLException sqle) {
			_log.error(sqle);
		}
	}

	private void _destroy() throws SQLException {
		DataSources.destroy(_cpds);
	}

	private Connection _getConnection() throws SQLException {
		return _cpds.getConnection();
	}

	private static Log _log = LogFactoryUtil.getLog(ConnectionPool.class);

	private static ConnectionPool _instance = new ConnectionPool();

	private Properties _props;
	private ComboPooledDataSource _cpds;

}