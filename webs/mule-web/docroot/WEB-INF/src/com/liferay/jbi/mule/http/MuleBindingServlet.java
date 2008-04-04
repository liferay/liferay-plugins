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

package com.liferay.jbi.mule.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.util.servlet.ServletResponseUtil;
import com.liferay.util.servlet.UploadServletRequest;

import java.io.IOException;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mule.extras.client.MuleClient;
import org.mule.umo.UMOException;
import org.mule.umo.UMOMessage;

/**
 * <a href="MuleBindingServlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Charles May
 *
 */
public class MuleBindingServlet extends HttpServlet {

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		if (_endpoint == null) {
			_endpoint = config.getInitParameter("endpoint");
		}
	}

	public void service(HttpServletRequest req, HttpServletResponse res)
		throws IOException, ServletException {

		String contentType = req.getHeader(HttpHeaders.CONTENT_TYPE);

		if (_log.isDebugEnabled()) {
			_log.debug("Content type " + contentType);
		}

		if ((contentType != null) &&
			(contentType.startsWith(ContentTypes.MULTIPART_FORM_DATA))) {

			req = new UploadServletRequest(req);
		}

		try {
			MuleClient client = new MuleClient();

			Map payload = new HashMap();

			Enumeration enu = req.getParameterNames();

			while (enu.hasMoreElements()) {
				try {
					String name = (String)enu.nextElement();

					String value = req.getParameter(name);

					payload.put(name, value);
				}
				catch (Exception e) {
				}
			}

			UMOMessage message = client.send(_endpoint, payload, null);

			String result = (String)message.getPayload();

			res.setContentType("text/xml");

			try {
				ServletResponseUtil.write(res, result);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(e, e);
				}
			}
		}
		catch (UMOException ue) {
		}
	}

	private static Log _log = LogFactoryUtil.getLog(MuleBindingServlet.class);

	private String _endpoint;

}