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
 * @author Brian Wing Shun Chan
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