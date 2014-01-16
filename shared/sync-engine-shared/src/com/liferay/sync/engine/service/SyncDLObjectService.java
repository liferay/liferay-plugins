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

package com.liferay.sync.engine.service;

import com.liferay.sync.engine.service.persistence.SyncDLObjectPersistence;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class SyncDLObjectService {

	public static SyncDLObjectPersistence getSyncDLObjectPersistence() {
		if (_syncDLObjectPersistence != null) {
			return _syncDLObjectPersistence;
		}

		try {
			_syncDLObjectPersistence = new SyncDLObjectPersistence();
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}
		}

		return _syncDLObjectPersistence;
	}

	private static Logger _logger = LoggerFactory.getLogger(
		SyncDLObjectService.class);
	private static SyncDLObjectPersistence _syncDLObjectPersistence =
		getSyncDLObjectPersistence();

}