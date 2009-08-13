/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.wsrp.model.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.wsrp.model.WSRPConsumer;

import java.io.IOException;

import oasis.names.tc.wsrp.v2.types.RegistrationContext;

import org.jabsorb.JSONSerializer;
import org.jabsorb.serializer.MarshallException;
import org.jabsorb.serializer.UnmarshallException;

/**
 * <a href="WSRPConsumerImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WSRPConsumerImpl
	extends WSRPConsumerModelImpl implements WSRPConsumer {

	public WSRPConsumerImpl() {
	}

	public RegistrationContext getRegistrationContext() {
		if (_registrationContext != null) {
			return _registrationContext;
		}

		String registrationContextString = getRegistrationContextString();

		try {
			if (Validator.isNotNull(registrationContextString)) {
				_registrationContext =
					(RegistrationContext)_getSerializer().fromJSON(
						registrationContextString);
			}
		}
		catch (UnmarshallException ue) {
			_log.error(ue, ue);
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

		try {
			setRegistrationContextString(
				_getSerializer().toJSON(registrationContext));
		}
		catch (MarshallException me) {
			_log.error(me, me);
		}

		_registrationContext = registrationContext;
	}

	public void setRegistrationProperties(
		UnicodeProperties registrationProperties) {

		setRegistrationPropertiesString(registrationProperties.toString());

		_registrationProperties = registrationProperties;
	}

	private static JSONSerializer _getSerializer() {
		if (_serializer != null) {
			return _serializer;
		}

		_serializer = new JSONSerializer();

		try {
			 _serializer.registerDefaultSerializers();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return _serializer;
	}

	private static Log _log = LogFactoryUtil.getLog(WSRPConsumerImpl.class);

	private static JSONSerializer _serializer;

	private RegistrationContext _registrationContext;
	private UnicodeProperties _registrationProperties;

}