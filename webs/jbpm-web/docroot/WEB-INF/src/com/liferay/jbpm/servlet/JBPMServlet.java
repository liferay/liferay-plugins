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

package com.liferay.jbpm.servlet;

import com.liferay.jbpm.WorkflowComponentImpl;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.util.servlet.ServletResponseUtil;
import com.liferay.util.servlet.UploadServletRequest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <a href="JBPMServlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Charles May
 *
 */
public class JBPMServlet extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse res)
		throws IOException, ServletException {

		try {
			String contentType = req.getHeader(HttpHeaders.CONTENT_TYPE);

			if (_log.isDebugEnabled()) {
				_log.debug("Content type " + contentType);
			}

			if ((contentType != null) &&
				(contentType.startsWith(ContentTypes.MULTIPART_FORM_DATA))) {

				req = new UploadServletRequest(req);
			}

			WorkflowComponentImpl workflowComponentImpl =
				new WorkflowComponentImpl();

			String result = workflowComponentImpl.process(req);

			res.setContentType("text/xml");

			ServletResponseUtil.write(res, result);
		}
		catch (Exception e) {
			_log.error(e, e);

			res.sendError(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(JBPMServlet.class);

}