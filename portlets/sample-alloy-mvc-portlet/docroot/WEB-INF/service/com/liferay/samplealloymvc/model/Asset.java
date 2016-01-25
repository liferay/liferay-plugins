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

package com.liferay.samplealloymvc.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Asset service. Represents a row in the &quot;SAMVC_Asset&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see AssetModel
 * @see com.liferay.samplealloymvc.model.impl.AssetImpl
 * @see com.liferay.samplealloymvc.model.impl.AssetModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.samplealloymvc.model.impl.AssetImpl")
@ProviderType
public interface Asset extends AssetModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.samplealloymvc.model.impl.AssetImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Asset, Long> ASSET_ID_ACCESSOR = new Accessor<Asset, Long>() {
			@Override
			public Long get(Asset asset) {
				return asset.getAssetId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Asset> getTypeClass() {
				return Asset.class;
			}
		};
}