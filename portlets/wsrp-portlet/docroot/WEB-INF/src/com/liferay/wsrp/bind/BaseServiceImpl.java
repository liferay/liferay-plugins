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

package com.liferay.wsrp.bind;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.util.axis.ServletUtil;
import com.liferay.wsrp.model.WSRPProducer;
import com.liferay.wsrp.service.WSRPProducerLocalServiceUtil;

import javax.servlet.http.HttpServletRequest;

import oasis.names.tc.wsrp.v2.types.LocalizedString;

/**
 * <a href="BaseServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class BaseServiceImpl {

	protected LocalizedString getLocalizedString(String value) {
		return new LocalizedString(value, null);
	}

	protected LocalizedString[] getLocalizedStrings(String[] values) {
		LocalizedString[] localizedStrings = new LocalizedString[values.length];

		for (int i = 0; i < values.length; i++) {
			String value = values[i];

			localizedStrings[i] = getLocalizedString(value);
		}

		return localizedStrings;
	}

	protected WSRPProducer getWSRPProducer() throws Exception {
		HttpServletRequest request = ServletUtil.getRequest();

		long wsrpProducerId = ParamUtil.getLong(request, "wsrpProducerId");

		return WSRPProducerLocalServiceUtil.getWSRPProducer(wsrpProducerId);
	}

}