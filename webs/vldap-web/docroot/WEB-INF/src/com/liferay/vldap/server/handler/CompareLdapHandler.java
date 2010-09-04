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

package com.liferay.vldap.server.handler;

import com.liferay.vldap.server.directory.Attribute;
import com.liferay.vldap.server.directory.Directory;
import com.liferay.vldap.server.directory.RootDirectory;
import com.liferay.vldap.server.handler.util.LdapHandlerContext;

import java.util.List;

import org.apache.directory.shared.ldap.entry.Value;
import org.apache.directory.shared.ldap.message.ResultCodeEnum;
import org.apache.directory.shared.ldap.message.internal.InternalCompareRequest;
import org.apache.directory.shared.ldap.message.internal.InternalRequest;
import org.apache.directory.shared.ldap.message.internal.InternalResponse;
import org.apache.mina.core.session.IoSession;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class CompareLdapHandler extends BaseLdapHandler {

	public List<InternalResponse> messageReceived(
			InternalRequest internalRequest, IoSession ioSession,
			LdapHandlerContext ldapHandlerContext)
		throws Exception {

		InternalCompareRequest internalCompareRequest =
			(InternalCompareRequest)internalRequest;

		String attributeId = internalCompareRequest.getAttributeId();
		Value<?> value = internalCompareRequest.getAssertionValue();

		Directory directory = new RootDirectory(internalRequest);

		directory.findBase(internalCompareRequest.getName());

		if (directory != null) {
			Attribute attribute = directory.getAttribute(
				attributeId, value.getString());

			if (attribute != null) {
				return toList(
					getInternalResponse(
						internalRequest, ResultCodeEnum.COMPARE_TRUE));
			}
		}

		return toList(
			getInternalResponse(internalRequest, ResultCodeEnum.COMPARE_FALSE));
	}

}