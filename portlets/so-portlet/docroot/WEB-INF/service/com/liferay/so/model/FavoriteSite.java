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

package com.liferay.so.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the FavoriteSite service. Represents a row in the &quot;SO_FavoriteSite&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see FavoriteSiteModel
 * @see com.liferay.so.model.impl.FavoriteSiteImpl
 * @see com.liferay.so.model.impl.FavoriteSiteModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.so.model.impl.FavoriteSiteImpl")
@ProviderType
public interface FavoriteSite extends FavoriteSiteModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.so.model.impl.FavoriteSiteImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<FavoriteSite, Long> FAVORITE_SITE_ID_ACCESSOR = new Accessor<FavoriteSite, Long>() {
			@Override
			public Long get(FavoriteSite favoriteSite) {
				return favoriteSite.getFavoriteSiteId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<FavoriteSite> getTypeClass() {
				return FavoriteSite.class;
			}
		};
}