/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.so.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Account;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.so.model.FavoriteSite;
import com.liferay.so.service.base.FavoriteSiteLocalServiceBaseImpl;

import java.util.List;

/**
 * @author Jonathan Lee
 */
public class FavoriteSiteLocalServiceImpl
	extends FavoriteSiteLocalServiceBaseImpl {

	public FavoriteSite addFavoriteSite(long userId, long groupId)
		throws PortalException {

		validate(groupId);

		User user = userLocalService.getUserById(userId);

		long favoriteSiteId = counterLocalService.increment();

		FavoriteSite favoriteSite = favoriteSitePersistence.create(
			favoriteSiteId);

		favoriteSite.setGroupId(groupId);
		favoriteSite.setCompanyId(user.getCompanyId());
		favoriteSite.setUserId(userId);

		favoriteSitePersistence.update(favoriteSite);

		return favoriteSite;
	}

	@Override
	public FavoriteSite deleteFavoriteSite(long favoriteSiteId)
		throws PortalException {

		return favoriteSitePersistence.remove(favoriteSiteId);
	}

	public void deleteFavoriteSites(long userId, long groupId)
		throws PortalException {

		favoriteSitePersistence.removeByG_U(groupId, userId);
	}

	public List<FavoriteSite> getFavoriteSites(
		long userId, int start, int end) {

		return favoriteSitePersistence.findByUserId(userId, start, end);
	}

	public List<Object[]> getFavoriteSites(
		long userId, String name, int start, int end) {

		User user = userLocalService.fetchUser(userId);

		String groupRealName = getGroupRealName(user.getCompanyId(), name);

		return favoriteSiteFinder.findByU_N(
			userId, name, groupRealName, start, end);
	}

	public int getFavoriteSitesCount(long userId) {
		return favoriteSitePersistence.countByUserId(userId);
	}

	public int getFavoriteSitesCount(long userId, String name) {
		User user = userLocalService.fetchUser(userId);

		String groupRealName = getGroupRealName(user.getCompanyId(), name);

		return favoriteSiteFinder.countByU_N(userId, name, groupRealName);
	}

	public boolean isFavoriteSite(long favoriteSiteId) {
		FavoriteSite favoriteSite = favoriteSitePersistence.fetchByPrimaryKey(
			favoriteSiteId);

		if (favoriteSite != null) {
			return true;
		}

		return false;
	}

	public boolean isFavoriteSite(long userId, long groupId) {
		int count = favoriteSitePersistence.countByG_U(groupId, userId);

		if (count > 0) {
			return true;
		}

		return false;
	}

	protected String getGroupRealName(long companyId, String name) {
		if (Validator.isNull(name)) {
			return name;
		}

		String groupRealName = name;

		try {
			Company company = companyPersistence.findByPrimaryKey(companyId);

			Account account = company.getAccount();

			String companyName = account.getName();

			name = StringUtil.replace(
				name, StringPool.PERCENT, StringPool.BLANK);

			if (companyName.indexOf(name) != -1) {
				groupRealName = StringUtil.quote(
					GroupConstants.GUEST, StringPool.PERCENT);
			}
		}
		catch (PortalException pe) {
		}

		return groupRealName;
	}

	protected void validate(long groupId) throws PortalException {
		groupPersistence.findByPrimaryKey(groupId);
	}

}