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

package com.liferay.sync.engine.service.persistence;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import com.liferay.sync.engine.util.PropsValues;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

import java.sql.SQLException;

/**
 * @author Shinn Lok
 */
public class BasePersistenceImpl<TT, TID>
	extends BaseDaoImpl<TT, TID> implements BasePersistence<TT, TID> {

	public BasePersistenceImpl(Class<TT> clazz) throws SQLException {
		super(_getConnectionSource(), clazz);
	}

	@Override
	public int createTable() throws SQLException {
		return TableUtils.createTable(connectionSource, dataClass);
	}

	private static ConnectionSource _getConnectionSource() throws SQLException {
		if (_connectionSource != null) {
			return _connectionSource;
		}

		StringBuilder sb = new StringBuilder(5);

		sb.append("jdbc:h2:");
		sb.append(PropsValues.SYNC_CONFIGURATION_DIRECTORY);

		FileSystem fileSystem = FileSystems.getDefault();

		sb.append(fileSystem.getSeparator());

		sb.append(PropsValues.SYNC_DATABASE_NAME);
		sb.append(";AUTO_SERVER=TRUE");

		_connectionSource = new JdbcPooledConnectionSource(sb.toString());

		return _connectionSource;
	}

	private static ConnectionSource _connectionSource;

}