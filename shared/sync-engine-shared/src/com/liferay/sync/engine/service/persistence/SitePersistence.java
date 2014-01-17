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

import com.liferay.sync.engine.model.Site;

import java.sql.SQLException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shinn Lok
 */
public class SitePersistence extends BasePersistenceImpl<Site, Long> {

	public SitePersistence() throws SQLException {
		super(Site.class);
	}

	public Site fetchSite(long accountId, long groupId) throws SQLException {
		Map<String, Object> fieldValues = new HashMap<String, Object>();

		fieldValues.put("accountId", accountId);
		fieldValues.put("groupId", groupId);

		List<Site> sites = queryForFieldValues(fieldValues);

		if ((sites == null) || sites.isEmpty()) {
			return null;
		}

		return sites.get(0);
	}

}