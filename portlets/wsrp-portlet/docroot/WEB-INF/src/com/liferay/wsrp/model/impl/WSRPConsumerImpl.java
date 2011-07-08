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

package com.liferay.wsrp.model.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import oasis.names.tc.wsrp.v2.types.RegistrationContext;

/**
 * @author Brian Wing Shun Chan
 */
public class WSRPConsumerImpl
	extends WSRPConsumerBaseImpl {

	public WSRPConsumerImpl() {
	}

	public RegistrationContext getRegistrationContext() {
		if (_registrationContext != null) {
			return _registrationContext;
		}

		String registrationContextString = getRegistrationContextString();

		if (Validator.isNotNull(registrationContextString)) {
			_registrationContext =
				(RegistrationContext)Base64.stringToObject(
					registrationContextString, getClass().getClassLoader());
		}

		return _registrationContext;
	}

	public UnicodeProperties getRegistrationProperties() {
		if (_registrationProperties != null) {
			return _registrationProperties;
		}

		_registrationProperties = new UnicodeProperties();

		String registrationPropertiesString = getRegistrationPropertiesString();

		try {
			_registrationProperties.load(registrationPropertiesString);
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);
		}

		return _registrationProperties;
	}

	public void setRegistrationContext(
		RegistrationContext registrationContext) {

		setRegistrationContextString(
			Base64.objectToString(registrationContext));

		_registrationContext = registrationContext;
	}

	public void setRegistrationProperties(
		UnicodeProperties registrationProperties) {

		setRegistrationPropertiesString(registrationProperties.toString());

		_registrationProperties = registrationProperties;
	}

	private static Log _log = LogFactoryUtil.getLog(WSRPConsumerImpl.class);

	private RegistrationContext _registrationContext;
	private UnicodeProperties _registrationProperties;

}