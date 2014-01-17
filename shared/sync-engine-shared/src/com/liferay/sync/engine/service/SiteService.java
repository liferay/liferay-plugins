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

import com.liferay.sync.engine.model.Site;
import com.liferay.sync.engine.service.persistence.SitePersistence;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class SiteService {

	public static Site addSite(long accountId, long groupId, String filePath)
		throws Exception {

		Site site = new Site();

		site.setAccountId(accountId);
		site.setFilePath(filePath);
		site.setGroupId(groupId);

		_sitePersistence.create(site);

		return site;
	}

	public static Site fetchSite(long accountId, long groupId) {
		try {
			return _sitePersistence.fetchSite(accountId, groupId);
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return site;
		}
	}

	public static SitePersistence getSitePersistence() {
		if (_sitePersistence != null) {
			return _sitePersistence;
		}

		try {
			_sitePersistence = new SitePersistence();
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}
		}

		return _sitePersistence;
	}

	private static Logger _logger = LoggerFactory.getLogger(SiteService.class);
	private static SitePersistence _sitePersistence = getSitePersistence();

}