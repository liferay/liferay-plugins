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

package com.liferay.akismet.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.Accessor;
import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the AkismetData service. Represents a row in the &quot;Akismet_AkismetData&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see AkismetDataModel
 * @see com.liferay.akismet.model.impl.AkismetDataImpl
 * @see com.liferay.akismet.model.impl.AkismetDataModelImpl
 * @generated
 */
@ProviderType
public interface AkismetData extends AkismetDataModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.akismet.model.impl.AkismetDataImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<AkismetData, Long> AKISMET_DATA_ID_ACCESSOR = new Accessor<AkismetData, Long>() {
			@Override
			public Long get(AkismetData akismetData) {
				return akismetData.getAkismetDataId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<AkismetData> getTypeClass() {
				return AkismetData.class;
			}
		};
}