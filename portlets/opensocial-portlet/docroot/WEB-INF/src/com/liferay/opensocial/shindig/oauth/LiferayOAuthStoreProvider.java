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

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import com.liferay.opensocial.model.OAuthConsumer;
import com.liferay.opensocial.model.OAuthConsumerConstants;
import com.liferay.opensocial.model.impl.OAuthConsumerImpl;
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
		OAuthConsumer oAuthConsumer = _getOAuthConsumer(
			PortletPropsValues.SHINDIG_OAUTH_KEY_FILE_NAME,
			PortletPropsValues.SHINDIG_OAUTH_KEY_NAME);

		_oAuthStore = new LiferayOAuthStore(oAuthConsumer);
	}

	public OAuthStore get() {
		return _oAuthStore;
	}

	private String _convertFromOpenSsl(String key) {
		key = key.replaceAll(_OPEN_SSL_A_Z, StringPool.BLANK);
		key = key.replace(StringPool.NEW_LINE, StringPool.BLANK);

		return key;
	}

	private OAuthConsumer _getOAuthConsumer(
		String keyFileName, String keyName) {

		OAuthConsumer oAuthConsumer = new OAuthConsumerImpl();

		oAuthConsumer.setConsumerKey(_DEFAULT_CONSUMER_KEY);
		oAuthConsumer.setServiceName(_DEFAULT_SERVICE_NAME);

		String consumerSecret = null;

		String path = PropsUtil.get(PropsKeys.LIFERAY_HOME).concat(_KEY_DIR);

		path = path.replaceAll(StringPool.QUOTE, StringPool.BLANK);

		keyFileName = path.concat(keyFileName);

		try {
			consumerSecret = FileUtil.read(keyFileName);
		}
		catch (Exception e) {
		}
		finally {
			if (consumerSecret == null) {
				if (!FileUtil.exists(path)) {
					FileUtil.mkdirs(path);
				}

				if (_log.isWarnEnabled()) {
					_log.warn("Unable to load OAuth key from " + keyFileName);
				}

				return null;
			}
		}

		consumerSecret = _convertFromOpenSsl(consumerSecret);

		oAuthConsumer.setConsumerSecret(consumerSecret);
		oAuthConsumer.setKeyType(OAuthConsumerConstants.KEY_TYPE_RSA_PRIVATE);
		oAuthConsumer.setKeyName(keyName);

		return oAuthConsumer;
	}

	private static final String _DEFAULT_CONSUMER_KEY = "DEFAULT_CONSUMER_KEY";

	private static final String _DEFAULT_SERVICE_NAME = "LIFERAY";

	private static final String _KEY_DIR = "/data/opensocial/";

	private static final String _OPEN_SSL_A_Z = "-----[A-Z ]*-----";

	private static Log _log = LogFactoryUtil.getLog(
		LiferayOAuthStoreProvider.class);

	private final OAuthStore _oAuthStore;

}