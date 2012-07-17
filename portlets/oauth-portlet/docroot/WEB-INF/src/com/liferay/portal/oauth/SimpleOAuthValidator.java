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

import com.liferay.portal.kernel.oauth.OAuthException;

/**
 * @author Ivica Cardic
 */
public class SimpleOAuthValidator implements OAuthValidator {

	public void validateMessage(
			OAuthMessage oAuthMessage, OAuthAccessor oAuthAccessor)
		throws OAuthException {

		try {
			_validator.validateMessage(
				(net.oauth.OAuthMessage)oAuthMessage.getWrappedOAuthMessage(),
				(net.oauth.OAuthAccessor)
					oAuthAccessor.getWrappedOAuthAccessor());
		}
		catch (Exception e) {
			throw new OAuthException(e);
		}
	}

	private static final net.oauth.OAuthValidator _validator =
		new net.oauth.SimpleOAuthValidator();

}