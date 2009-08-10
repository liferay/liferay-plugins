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

import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.wsrp.model.WSRPConsumer;

import com.thoughtworks.xstream.XStream;

import oasis.names.tc.wsrp.v2.types.RegistrationContext;

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

		String registrationContextString = getRegistrationContextXML();

		if (Validator.isNotNull(registrationContextString)) {
			_registrationContext = (RegistrationContext)_xStream.fromXML(
				registrationContextString);
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
			if (Validator.isNotNull(registrationPropertiesString)) {
				_registrationProperties.load(registrationPropertiesString);
			}
		}
		catch (Exception e) {
		}

		return _registrationProperties;
	}

	public void setRegistrationContext(
		RegistrationContext registrationContext) {

		setRegistrationContextXML(_xStream.toXML(registrationContext));

		_registrationContext = registrationContext;
	}

	public void setRegistrationProperties(
		UnicodeProperties registrationProperties) {

		setRegistrationPropertiesString(registrationProperties.toString());

		_registrationProperties = registrationProperties;
	}

	private static XStream _xStream = new XStream();

	private RegistrationContext _registrationContext;
	private UnicodeProperties _registrationProperties;

}