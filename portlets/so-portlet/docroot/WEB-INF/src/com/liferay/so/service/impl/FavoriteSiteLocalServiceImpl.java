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

package com.liferay.so.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Account;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.so.model.FavoriteSite;
import com.liferay.so.service.base.FavoriteSiteLocalServiceBaseImpl;

import java.util.List;

/**
 * @author Jonathan Lee
 */
public class FavoriteSiteLocalServiceImpl
	extends FavoriteSiteLocalServiceBaseImpl {

	 public FavoriteSite addFavoriteSite(long userId, long groupId)
		throws PortalException, SystemException {

		User user = userLocalService.getUserById(userId);

		long favoriteSiteId = counterLocalService.increment();

		FavoriteSite favoriteSite = favoriteSitePersistence.create(
			favoriteSiteId);

		favoriteSite.setGroupId(groupId);
		favoriteSite.setCompanyId(user.getCompanyId());
		favoriteSite.setUserId(userId);

		favoriteSitePersistence.update(favoriteSite, false);

		return favoriteSite;
	}

	public void deleteFavoriteSite(long favoriteSiteId)
		throws PortalException, SystemException {

		favoriteSitePersistence.remove(favoriteSiteId);
	}

	public void deleteFavoriteSite(long userId, long groupId)
		throws PortalException, SystemException {

		favoriteSitePersistence.removeByU_G(userId, groupId);
	}

	public List<FavoriteSite> getFavoriteSites(long userId, int start, int end)
		throws SystemException {

		return favoriteSitePersistence.findByUserId(userId, start, end);
	}

	public List<Object[]> getFavoriteSites(
			long userId, String name, int start, int end)
		throws SystemException {

		User user = userLocalService.fetchUser(userId);

		String groupRealName = getGroupRealName(user.getCompanyId(), name);

		return favoriteSiteFinder.findByU_N(
			userId, name, groupRealName, start, end);
	}

	public int getFavoriteSitesCount(long userId)
		throws SystemException {

		return favoriteSitePersistence.countByUserId(userId);
	}

	public int getFavoriteSitesCount(long userId, String name)
		throws SystemException {

		User user = userLocalService.fetchUser(userId);

		String groupRealName = getGroupRealName(user.getCompanyId(), name);

		return favoriteSiteFinder.countByU_N(userId, name, groupRealName);
	}

	public boolean isFavoriteSite(long favoriteSiteId)
		throws SystemException {

		FavoriteSite favoriteSite = favoriteSitePersistence.fetchByPrimaryKey(
			favoriteSiteId);

		if (favoriteSite != null) {
			return true;
		}

		return false;
	}

	public boolean isFavoriteSite(long userId, long groupId)
		throws SystemException {

		int count = favoriteSitePersistence.countByU_G(userId, groupId);

		if (count > 0) {
			return true;
		}

		return false;
	}

	protected String getGroupRealName(long companyId, String name)
		throws SystemException {

		if (Validator.isNull(name)) {
			return name;
		}

		String groupRealName = name;

		try {
			Company company = CompanyLocalServiceUtil.getCompany(companyId);

			Account account = company.getAccount();

			String companyName = account.getName();

			name = StringUtil.replace(
				name, StringPool.PERCENT, StringPool.BLANK);

			if (companyName.indexOf(name) != -1) {
				groupRealName = StringPool.PERCENT + GroupConstants.GUEST +
					StringPool.PERCENT;
			}
		}
		catch (PortalException pe) {
		}

		return groupRealName;
	}

}