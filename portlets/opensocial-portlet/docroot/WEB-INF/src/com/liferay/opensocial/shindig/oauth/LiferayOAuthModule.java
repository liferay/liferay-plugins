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

package com.liferay.opensocial.shindig.oauth;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import org.apache.shindig.common.crypto.BlobCrypter;
import org.apache.shindig.gadgets.oauth.OAuthFetcherConfig;
import org.apache.shindig.gadgets.oauth.OAuthModule.OAuthCrypterProvider;
import org.apache.shindig.gadgets.oauth.OAuthModule.OAuthRequestProvider;
import org.apache.shindig.gadgets.oauth.OAuthRequest;
import org.apache.shindig.gadgets.oauth.OAuthStore;

/**
 * @author Dennis Ju
 */
public class LiferayOAuthModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(BlobCrypter.class).annotatedWith(
			Names.named(OAuthFetcherConfig.OAUTH_STATE_CRYPTER)).toProvider(
				OAuthCrypterProvider.class);
		bind(OAuthRequest.class).toProvider(OAuthRequestProvider.class);
		bind(OAuthStore.class).toProvider(LiferayOAuthStoreProvider.class);
	}

}