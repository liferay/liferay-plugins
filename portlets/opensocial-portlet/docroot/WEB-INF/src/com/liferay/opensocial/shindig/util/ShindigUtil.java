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

package com.liferay.opensocial.shindig.util;

import com.google.inject.Inject;

import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.io.File;

import org.apache.shindig.auth.BasicSecurityToken;
import org.apache.shindig.auth.BasicSecurityTokenDecoder;
import org.apache.shindig.auth.BlobCrypterSecurityToken;
import org.apache.shindig.common.crypto.BasicBlobCrypter;
import org.apache.shindig.common.crypto.BlobCrypter;
import org.apache.shindig.config.ContainerConfig;

/**
 * <a href="ShindigUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael Young
 *
 */
public class ShindigUtil {

	public static String createSecurityToken(
			String ownerId, long viewerId, String appId, String domain,
			String appUrl, String activeUrl)
		throws Exception {

	    String securityTokenType =
	    	_containerConfig.getString(
	    		ContainerConfig.DEFAULT_CONTAINER, "gadgets.securityTokenType");

	    String viewerIdString = String.valueOf(viewerId);

	    String securityToken = StringPool.BLANK;

		if (securityTokenType.equals("insecure")) {
			BasicSecurityToken basicSecurityToken = new BasicSecurityToken(
				ownerId, viewerIdString, appId, domain, appUrl,
				"0", ContainerConfig.DEFAULT_CONTAINER,
				activeUrl);

			securityToken =
				_basicSecurityTokenDecoder.encodeToken(basicSecurityToken);
		}
		else if (securityTokenType.equals("secure")) {
			String securityTokenKeyPath = _containerConfig.getString(
		    	ContainerConfig.DEFAULT_CONTAINER,
		    		"gadgets.securityTokenKeyFile");

			File securityTokenKeyFile = new File(securityTokenKeyPath);

			BlobCrypter blobCrypter =
				new BasicBlobCrypter(securityTokenKeyFile);

			BlobCrypterSecurityToken blobCrypterSecurityToken =
				new BlobCrypterSecurityToken(
					blobCrypter, ContainerConfig.DEFAULT_CONTAINER, domain);

			blobCrypterSecurityToken.setOwnerId(ownerId);
			blobCrypterSecurityToken.setViewerId(viewerIdString);
			blobCrypterSecurityToken.setAppUrl(appUrl);
			blobCrypterSecurityToken.setModuleId(0);

			securityToken = blobCrypterSecurityToken.encrypt();
		}

		securityToken = HttpUtil.encodeURL(securityToken);

		return securityToken;
	}

	@Inject
	private static ContainerConfig _containerConfig;

	@Inject
	private static BasicSecurityTokenDecoder _basicSecurityTokenDecoder;
}