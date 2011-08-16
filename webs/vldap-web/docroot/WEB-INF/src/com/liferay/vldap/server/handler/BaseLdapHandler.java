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

import org.apache.directory.shared.ldap.model.message.LdapResult;
import org.apache.directory.shared.ldap.model.message.Response;
import org.apache.directory.shared.ldap.model.message.ResultCodeEnum;
import org.apache.directory.shared.ldap.model.message.ResultResponse;
import org.apache.directory.shared.ldap.model.message.ResultResponseRequest;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public abstract class BaseLdapHandler implements LdapHandler {

	protected <R extends ResultResponse, T extends ResultResponseRequest<R>> R
		getResponse(T request) {

		return getResponse(request, ResultCodeEnum.SUCCESS);
	}

	protected <R extends ResultResponse, T extends ResultResponseRequest<R>> R
		getResponse(T request, ResultCodeEnum resultCode) {

		R response = request.getResultResponse();

		LdapResult ldapResult = response.getLdapResult();

		ldapResult.setResultCode(resultCode);

		return response;
	}

	protected List<Response> toList(Response response) {
		List<Response> responses = new ArrayList<Response>();

		responses.add(response);

		return responses;
	}

}