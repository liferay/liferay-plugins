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

package com.liferay.jbi.servicemix.util;

import com.liferay.portal.kernel.util.HttpUtil;

import java.io.IOException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.jbi.messaging.MessageExchange;
import javax.jbi.messaging.MessagingException;
import javax.jbi.messaging.NormalizedMessage;

import org.apache.servicemix.components.util.TransformComponentSupport;
import org.apache.servicemix.jbi.jaxp.StringSource;

/**
 * <a href="URLTransformComponent.java.html"><b><i>View Source</i></b></a>
 *
 * @author Charles May
 * @author Brian Wing Shun Chan
 *
 */
public class URLTransformComponent extends TransformComponentSupport {

	public String getUrl() {
		return _url;
	}

	public void setUrl(String url) {
		_url = url;
	}

	public String getUrlResult(NormalizedMessage in) throws MessagingException {
		Map parts = new HashMap();

		Iterator itr = in.getPropertyNames().iterator();

		while (itr.hasNext()) {
			String key = (String)itr.next();

			Object value = (Object)in.getProperty(key);

			if (value instanceof String) {

				parts.put(key, value);
			}
		}

		try {
			return HttpUtil.URLtoString(_url, null, parts, true);
		}
		catch (IOException ioe) {
			throw new MessagingException(ioe.getMessage());
		}
	}

	public boolean transform(
			MessageExchange exchange, NormalizedMessage in,
			NormalizedMessage out)
		throws MessagingException {

		out.setContent(new StringSource(getUrlResult(in)));

		return true;
	}

	private String _url;

}