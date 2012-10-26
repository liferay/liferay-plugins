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

import java.io.IOException;

/**
 * @author Ivica Cardic
 */
public class OAuthMessageImpl implements OAuthMessage {

	public OAuthMessageImpl(net.oauth.OAuthMessage oAuthMessage) {
		this._oAuthMessage = oAuthMessage;
	}

	public String getConsumerKey() throws IOException {
		return _oAuthMessage.getConsumerKey();
	}

	public String getParameter(String name) throws IOException {
		return _oAuthMessage.getParameter(name);
	}

	public String getToken() throws IOException {
		return _oAuthMessage.getToken();
	}

	public Object getWrappedOAuthMessage() {
		return _oAuthMessage;
	}

	private net.oauth.OAuthMessage _oAuthMessage;

}