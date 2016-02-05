/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.so.service.persistence.impl;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

import com.liferay.so.model.FavoriteSite;
import com.liferay.so.service.persistence.FavoriteSitePersistence;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class FavoriteSiteFinderBaseImpl extends BasePersistenceImpl<FavoriteSite> {
	/**
	 * Returns the favorite site persistence.
	 *
	 * @return the favorite site persistence
	 */
	public FavoriteSitePersistence getFavoriteSitePersistence() {
		return favoriteSitePersistence;
	}

	/**
	 * Sets the favorite site persistence.
	 *
	 * @param favoriteSitePersistence the favorite site persistence
	 */
	public void setFavoriteSitePersistence(
		FavoriteSitePersistence favoriteSitePersistence) {
		this.favoriteSitePersistence = favoriteSitePersistence;
	}

	@BeanReference(type = FavoriteSitePersistence.class)
	protected FavoriteSitePersistence favoriteSitePersistence;
}