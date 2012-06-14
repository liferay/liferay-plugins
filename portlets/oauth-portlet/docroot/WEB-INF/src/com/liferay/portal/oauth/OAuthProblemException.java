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

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Ivica Cardic
 */
public class OAuthProblemException extends PortalException {

	public static final String NONCE_USED = "nonce_used";
	public static final String PARAMETER_REJECTED = "parameter_rejected";
	public static final String PERMISSION_DENIED = "permission_denied";
	public static final String TIMESTAMP_REFUSED = "timestamp_refused";
	public static final String TOKEN_EXPIRED = "token_expired";
	public static final String TOKEN_REJECTED = "token_rejected";
	public static final String TOKEN_REVOKED = "token_revoked";
	public static final String VERSION_REJECTED = "version_rejected";

	public OAuthProblemException(String problem) {
		_oAuthProblemException = new net.oauth.OAuthProblemException(problem);
	}

	public Throwable getCause() {
		return _oAuthProblemException;
	}

	public String getProblem() {
		return _oAuthProblemException.getProblem();
	}

	private net.oauth.OAuthProblemException _oAuthProblemException;

}