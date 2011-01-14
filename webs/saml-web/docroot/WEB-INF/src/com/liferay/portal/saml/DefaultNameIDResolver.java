/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.saml;

import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.saml.opensaml.OpenSAMLUtil;
import com.liferay.portal.saml.util.PropsKeys;

import org.opensaml.saml2.core.NameID;

/**
 * @author Mika Koivisto
 */
public class DefaultNameIDResolver implements NameIDResolver {

	public NameID resolve(User user) {
		String format = _getNameIDFormat();

		NameID nameID = null;

		if (format.equals("emailAddress")) {
			nameID = OpenSAMLUtil.buildNameID(
					user.getEmailAddress(), NameID.EMAIL);
		}
		else if (format.equals("screenName")) {
			nameID = OpenSAMLUtil.buildNameID(
					user.getScreenName(), NameID.UNSPECIFIED);
		}
		else {
			nameID = OpenSAMLUtil.buildNameID(
					String.valueOf(user.getUserId()), NameID.PERSISTENT);
		}

		return nameID;
	}

	public void setIssuer(String issuer) {
		_issuer = issuer;
	}

	private String _getNameIDFormat() {
		String key = PropsKeys.SAML_SP_METADATA_NAMEID_RESOLVER_NAMEID_FORMAT + "[" + _issuer + "]";

		return PropsUtil.get(key);
	}

	private String _issuer;
}
