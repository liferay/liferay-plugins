/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

package com.liferay.wsrp.consumer.model.impl;

import com.liferay.portal.kernel.util.Base64;
import com.liferay.wsrp.consumer.model.Producer;
import com.liferay.wsrp.soap.v2.types.RegistrationContext;
import com.liferay.wsrp.soap.v2.types.ServiceDescription;
import com.thoughtworks.xstream.XStream;

/**
 * <a href="ProducerImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael Young
 *
 */
public class ProducerImpl extends ProducerModelImpl implements Producer {
	public ProducerImpl() {
	}
	
	public Object getRegistrationContextObj() {
		if (_registrationContextObj == null) {
			String rc = (String)Base64.stringToObject(getRegistrationContext());
		
			_registrationContextObj = _xStream.fromXML(rc);
		}
		
		return _registrationContextObj;
	}
		
	public Object getServiceDescriptionObj() {
		if (_serviceDescriptionObj == null) {
			String sd = (String)Base64.stringToObject(getServiceDescription());
				
			_serviceDescriptionObj = _xStream.fromXML(sd);
		}
		
		return _serviceDescriptionObj;
	}
	
	public void setRegistrationContextObj(Object rc) {
		if (rc == null) {
			_registrationContextObj = null;
			setRegistrationContext(null);

			return;
		}
		
		if (!(rc instanceof RegistrationContext)) {
			throw new IllegalArgumentException();
		}

		_registrationContextObj = rc;

		String rcEncoded = Base64.objectToString(_xStream.toXML(rc));
		
		setRegistrationContext(rcEncoded);
	}

	public void setServiceDescriptionObj(Object sd) {
		if (sd == null) {
			_serviceDescriptionObj = null;
			setServiceDescription(null);

			return;
		}

		if (!(sd instanceof ServiceDescription)) {
			throw new IllegalArgumentException();
		}
		
		_serviceDescriptionObj = sd;
		
		String sdEncoded = Base64.objectToString(_xStream.toXML(sd));
		
		setServiceDescription(sdEncoded);
	}
	
	private Object _registrationContextObj;
	private Object _serviceDescriptionObj;
	
	private static XStream _xStream = new XStream();	
}