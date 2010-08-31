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

package com.liferay.vldap.util;

import org.apache.directory.shared.ldap.name.DN;
import org.apache.directory.shared.ldap.name.RDN;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class DNUtil {

	public static String getValue(DN dn, String normType) {
		for (RDN rdn : dn) {
			String rdnNormType = rdn.getNormType();
			String rdnNormValue = rdn.getNormValue();

			if (rdnNormType.equalsIgnoreCase(normType)) {
				return rdnNormValue;
			}
		}

		return null;
	}

}