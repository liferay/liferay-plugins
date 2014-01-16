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

import com.liferay.sync.engine.model.SyncAccount;
import com.liferay.sync.engine.service.persistence.SyncAccountPersistence;
import com.liferay.sync.engine.util.Encryptor;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class SyncAccountService {

	public static SyncAccount addSyncAccount(
			String login, String password, String url)
		throws Exception {

		SyncAccount syncAccount = new SyncAccount();

		syncAccount.setLogin(login);
		syncAccount.setPassword(Encryptor.encrypt(password));
		syncAccount.setUrl(url);

		_syncAccountPersistence.create(syncAccount);

		return syncAccount;
	}

	public static SyncAccount getSyncAccount(long syncAccountId) {
		SyncAccount syncAccount = null;

		try {
			syncAccount = _syncAccountPersistence.queryForId(syncAccountId);
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}
		}

		return syncAccount;
	}

	public static SyncAccountPersistence getSyncAccountPersistence() {
		if (_syncAccountPersistence != null) {
			return _syncAccountPersistence;
		}

		try {
			_syncAccountPersistence = new SyncAccountPersistence();
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}
		}

		return _syncAccountPersistence;
	}

	private static Logger _logger = LoggerFactory.getLogger(
		SyncAccountService.class);
	private static SyncAccountPersistence _syncAccountPersistence =
		getSyncAccountPersistence();

}