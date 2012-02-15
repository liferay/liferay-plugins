/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.so.model.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.so.service.FavoriteSiteLocalServiceUtil;

/**
 * @author Jonathan Lee
 */
public class FavoriteSiteImpl extends FavoriteSiteBaseImpl {

	public FavoriteSiteImpl() {
	}

	public Group getGroup() throws PortalException, SystemException{
		return GroupLocalServiceUtil.fetchGroup(getGroupId());
	}

	public boolean isFavoriteSite() throws PortalException, SystemException {
		return FavoriteSiteLocalServiceUtil.isFavoriteSite(getPrimaryKey());
	}

}