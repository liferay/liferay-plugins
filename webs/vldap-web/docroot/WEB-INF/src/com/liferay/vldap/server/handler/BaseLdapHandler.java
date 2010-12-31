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

package com.liferay.vldap.server.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.directory.shared.ldap.message.ResultCodeEnum;
import org.apache.directory.shared.ldap.message.internal.InternalLdapResult;
import org.apache.directory.shared.ldap.message.internal.InternalRequest;
import org.apache.directory.shared.ldap.message.internal.InternalResponse;
import org.apache.directory.shared.ldap.message.internal.InternalResultResponse;
import org.apache.directory.shared.ldap.message.internal.InternalResultResponseRequest;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public abstract class BaseLdapHandler implements LdapHandler {

	protected InternalResponse getInternalResponse(
		InternalRequest internalRequest) {

		return getInternalResponse(internalRequest, ResultCodeEnum.SUCCESS);
	}

	protected InternalResponse getInternalResponse(
		InternalRequest internalRequest, ResultCodeEnum resultCode) {

		InternalResultResponseRequest internalResultResponseRequest =
			(InternalResultResponseRequest)internalRequest;

		InternalResultResponse internalResultResponse =
			internalResultResponseRequest.getResultResponse();

		InternalLdapResult internalLdapResult =
			internalResultResponse.getLdapResult();

		internalLdapResult.setResultCode(resultCode);

		return internalResultResponse;
	}

	protected List<InternalResponse> toList(InternalResponse internalResponse) {
		List<InternalResponse> internalResponses =
			new ArrayList<InternalResponse>();

		internalResponses.add(internalResponse);

		return internalResponses;
	}

}