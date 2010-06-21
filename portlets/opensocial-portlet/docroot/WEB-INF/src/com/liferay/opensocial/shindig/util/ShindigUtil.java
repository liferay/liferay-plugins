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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;

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
 */
public class ShindigUtil {

	public static String createSecurityToken(
			String ownerId, long viewerId, String appId, String domain,
			String appUrl, long moduleId, String activeUrl)
		throws Exception {

		String securityToken = StringPool.BLANK;

		String securityTokenType = _containerConfig.getString(
			ContainerConfig.DEFAULT_CONTAINER, "gadgets.securityTokenType");

		if (securityTokenType.equals("secure")) {
			String securityTokenKeyPath = _containerConfig.getString(
				ContainerConfig.DEFAULT_CONTAINER,
				"gadgets.securityTokenKeyFile");

			File securityTokenKeyFile = new File(securityTokenKeyPath);

			BlobCrypter blobCrypter = new BasicBlobCrypter(
				securityTokenKeyFile);

			BlobCrypterSecurityToken blobCrypterSecurityToken =
				new BlobCrypterSecurityToken(
					blobCrypter, ContainerConfig.DEFAULT_CONTAINER, domain);

			blobCrypterSecurityToken.setAppUrl(appUrl);
			blobCrypterSecurityToken.setModuleId(moduleId);
			blobCrypterSecurityToken.setOwnerId(ownerId);
			blobCrypterSecurityToken.setViewerId(String.valueOf(viewerId));

			securityToken = blobCrypterSecurityToken.encrypt();
		}
		else if (securityTokenType.equals("insecure")) {
			BasicSecurityToken basicSecurityToken = new BasicSecurityToken(
				ownerId, String.valueOf(viewerId), appId, domain, appUrl,
				String.valueOf(moduleId), ContainerConfig.DEFAULT_CONTAINER,
				activeUrl);

			securityToken = _basicSecurityTokenDecoder.encodeToken(
				basicSecurityToken);
		}

		securityToken = HttpUtil.encodeURL(securityToken);

		return securityToken;
	}

	public static String getColumnUserPrefs(String namespace) {
		return _COLUMN_USER_PREFS.concat(namespace);
	}

	public static long getModuleId(String namespace) {
		return namespace.hashCode();
	}

	public static String getOwnerId(Layout layout)
		throws PortalException, SystemException {

		Group group = layout.getGroup();

		long classPK = group.getClassPK();

		String ownerId = "G-" + classPK;

		if (group.isUser()) {
			ownerId = String.valueOf(classPK);
		}

		return ownerId;
	}

	public static String getTableOpenSocial() {
		return _TABLE_OPEN_SOCIAL;
	}

	private static final String _COLUMN_USER_PREFS = "USER_PREFS_";

	private static final String _TABLE_OPEN_SOCIAL = "OPEN_SOCIAL_DATA_";

	@Inject
	private static BasicSecurityTokenDecoder _basicSecurityTokenDecoder;

	@Inject
	private static ContainerConfig _containerConfig;

}