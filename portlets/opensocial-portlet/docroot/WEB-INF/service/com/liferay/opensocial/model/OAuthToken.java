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

package com.liferay.opensocial.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the OAuthToken service. Represents a row in the &quot;OpenSocial_OAuthToken&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see OAuthTokenModel
 * @see com.liferay.opensocial.model.impl.OAuthTokenImpl
 * @see com.liferay.opensocial.model.impl.OAuthTokenModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.opensocial.model.impl.OAuthTokenImpl")
@ProviderType
public interface OAuthToken extends OAuthTokenModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.opensocial.model.impl.OAuthTokenImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<OAuthToken, Long> O_AUTH_TOKEN_ID_ACCESSOR = new Accessor<OAuthToken, Long>() {
			@Override
			public Long get(OAuthToken oAuthToken) {
				return oAuthToken.getOAuthTokenId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<OAuthToken> getTypeClass() {
				return OAuthToken.class;
			}
		};
}