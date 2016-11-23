/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.jsonwebserviceclient.jcifs;

import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthSchemeProvider;
import org.apache.http.impl.auth.NTLMScheme;
import org.apache.http.protocol.HttpContext;

/**
 * @author Mladen Cikara
 */
public class JCIFSNTLMSchemeFactory implements AuthSchemeProvider {

	public JCIFSNTLMSchemeFactory(String domain, String workstation) {
		_domain = domain;
		_workstation = workstation;
	}

	@Override
	public AuthScheme create(HttpContext context) {
		return new NTLMScheme(new JCIFSEngine(_domain, _workstation));
	}

	private final String _domain;
	private final String _workstation;

}