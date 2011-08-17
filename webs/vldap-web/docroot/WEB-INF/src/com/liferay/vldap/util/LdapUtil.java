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

package com.liferay.vldap.util;

import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.Company;

import org.apache.directory.shared.ldap.model.name.Dn;
import org.apache.directory.shared.ldap.model.name.Rdn;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class LdapUtil {

	public static String buildName(
		String top, Company company, String... organizationUnits) {

		StringBundler sb = new StringBundler(
			3 + (organizationUnits.length * 3));

		for (int i = organizationUnits.length - 1; i >= 0; --i) {
			String organizationUnit = organizationUnits[i];

			if (!organizationUnit.startsWith("cn=") &&
				!organizationUnit.startsWith("ou=")) {

				sb.append("ou=");
			}

			sb.append(escape(organizationUnit));
			sb.append(",");
		}

		sb.append("ou=");
		sb.append(escape(company.getWebId()));
		sb.append(",o=");
		sb.append(escape(top));

		return sb.toString();
	}

	public static String escape(String name) {
		int pos = name.indexOf(CharPool.EQUAL);

		String suffix = name.substring(pos + 1);

		char[] charArray = suffix.toCharArray();

		StringBundler sb = new StringBundler();

		for (char c : charArray) {
			for (char escapeChar : _ESCAPE_CHARS) {
				if (c == escapeChar) {
					sb.append(CharPool.BACK_SLASH);

					break;
				}
			}

			sb.append(c);
		}

		String escapedSuffix = sb.toString();

		return name.substring(0, pos + 1) + escapedSuffix;
	}

	public static String getRdnValue(Dn dn, int index) {
		try {
			index = dn.size() - 1 - index;

			if (index < dn.size()) {
				Rdn rdn = dn.getRdn(index);

				return rdn.getValue(rdn.getNormType()).toString();
			}
		}
		catch (Exception e) {
		}

		return null;
	}

	/**
	 * http://www.rlmueller.net/CharactersEscaped.htm
	 */
	private static final char[] _ESCAPE_CHARS = {
		 ',', '\\', '#', '+', '<', '>', ';', '"', '='
	};

}