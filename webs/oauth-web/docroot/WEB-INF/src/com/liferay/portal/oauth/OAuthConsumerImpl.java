/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.oauth;

import com.liferay.portal.oauth.model.OAuthApplication;

/**
 * @author Ivica Cardic
 */
public class OAuthConsumerImpl implements OAuthConsumer {

	public OAuthConsumerImpl(OAuthApplication oAuthApplication) {
		this(new net.oauth.OAuthConsumer(
			oAuthApplication.getCallBackURL(),
			oAuthApplication.getConsumerKey(),
			oAuthApplication.getConsumerSecret(), null));

		this._oAuthApplication = oAuthApplication;
	}

	public OAuthConsumerImpl(net.oauth.OAuthConsumer oAuthConsumer) {
		this._oAuthConsumer = oAuthConsumer;
	}

	public String getCallbackURL() {
		return _oAuthConsumer.callbackURL;
	}

	public OAuthApplication getOAuthApplication() {
		return _oAuthApplication;
	}

	public Object getProperty(String name) {
		return _oAuthConsumer.getProperty(name);
	}

	public Object getWrappedOAuthConsumer() {
		return _oAuthConsumer;
	}

	public void setWrappedOAuthConsumer(Object oAuthConsumer) {
		this._oAuthConsumer = (net.oauth.OAuthConsumer)oAuthConsumer;
	}

	private OAuthApplication _oAuthApplication;
	private net.oauth.OAuthConsumer _oAuthConsumer;

}