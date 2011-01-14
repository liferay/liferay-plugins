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

package com.liferay.portal.saml;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.saml.util.PropsKeys;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Mika Koivisto
 */
public class AttributeResolverFactory {

	public static AttributeResolver getAttributeResolver(
			long companyId, String issuer) {

		AttributeResolver resolver = _attributeResolvers.get(
				companyId + "," + issuer);

		if (resolver == null) {
			Thread currentThread = Thread.currentThread();

			ClassLoader classLoader = currentThread.getContextClassLoader();

			try {
				String key = PropsKeys.SAML_SP_METADATA_ATTRIBUTES_RESOLVER + "[" + issuer + "]";

				String className = PrefsPropsUtil.getString(
						companyId, key);

				resolver =
					(AttributeResolver)classLoader.loadClass(
						className).newInstance();

				resolver.setIssuer(issuer);

				_attributeResolvers.put(companyId + "," + issuer, resolver);
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		return resolver;
	}

	private static Log _log = LogFactoryUtil.getLog(
		AttributeResolverFactory.class);

	private static Map<String, AttributeResolver> _attributeResolvers = 
		new ConcurrentHashMap<String, AttributeResolver>();
}
