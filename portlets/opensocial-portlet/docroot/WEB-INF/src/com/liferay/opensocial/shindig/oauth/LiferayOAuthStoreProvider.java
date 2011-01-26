/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import com.liferay.opensocial.model.OAuthConsumer;
import com.liferay.opensocial.model.OAuthConsumerConstants;
import com.liferay.opensocial.model.impl.OAuthConsumerImpl;
import com.liferay.opensocial.shindig.util.ShindigUtil;
import com.liferay.opensocial.util.PortletPropsValues;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;

import org.apache.shindig.gadgets.oauth.OAuthStore;

/**
 * @author Dennis Ju
 */
@Singleton
public class LiferayOAuthStoreProvider implements Provider<OAuthStore> {

	@Inject
	public LiferayOAuthStoreProvider() {
		String callbackURL =
			PortletPropsValues.SHINDIG_SIGNING_GLOBAL_CALLBACK_URL;

		OAuthConsumer oAuthConsumer = loadDefaultKey(
			PortletPropsValues.SHINDIG_SIGNING_KEY_FILE,
			PortletPropsValues.SHINDIG_SIGNING_KEY_NAME);

		_store = new LiferayOAuthStore(callbackURL, oAuthConsumer);
	}

	public OAuthStore get() {
		return _store;
	}

	private OAuthConsumer loadDefaultKey(
		String signingKeyFile, String signingKeyName) {

		OAuthConsumer oAuthConsumer = new OAuthConsumerImpl();

		oAuthConsumer.setServiceName(_DEFAULT_SERVICE_NAME);
		oAuthConsumer.setConsumerKey(_DEFAULT_CONSUMER_KEY);

		String consumerSecret = null;

		String path = PropsUtil.get(PropsKeys.LIFERAY_HOME).concat(
			_SIGNING_KEY_FILE_PATH);

		signingKeyFile = signingKeyFile.replaceAll(
			StringPool.QUOTE, StringPool.BLANK);

		signingKeyFile = path.concat(signingKeyFile);

		try {
			consumerSecret = FileUtil.read(signingKeyFile);
		}
		catch (Exception e) {
		}
		finally {
			if (consumerSecret == null) {
				if (!FileUtil.exists(path)) {
					FileUtil.mkdirs(path);
				}

				if (_log.isWarnEnabled()) {
					_log.warn("Failed to load OAuth signing key from " +
						signingKeyFile);
				}

				return null;
			}
		}

		consumerSecret = ShindigUtil.convertFromOpenSsl(consumerSecret);

		oAuthConsumer.setConsumerSecret(consumerSecret);
		oAuthConsumer.setKeyType(OAuthConsumerConstants.KEY_TYPE_RSA_PRIVATE);
		oAuthConsumer.setKeyName(signingKeyName);

		return oAuthConsumer;
	}

	private static final String _DEFAULT_CONSUMER_KEY = "DEFAULT_CONSUMER_KEY";

	private static final String _DEFAULT_SERVICE_NAME = "LIFERAY";

	private static final String _SIGNING_KEY_FILE_PATH = "/data/opensocial/";

	private static Log _log = LogFactoryUtil.getLog(
		LiferayOAuthStoreProvider.class);

	private final LiferayOAuthStore _store;

}