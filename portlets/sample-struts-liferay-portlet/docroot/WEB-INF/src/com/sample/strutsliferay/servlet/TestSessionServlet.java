/**
 * Copyright (c) 2000-2007 Liferay, Inc. All rights reserved.
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

package com.sample.strutsliferay.servlet;

import com.liferay.portal.kernel.util.StringMaker;

import java.io.IOException;

import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <a href="TestSessionServlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class TestSessionServlet extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse res)
		throws IOException, ServletException {

		HttpSession ses = req.getSession();

		StringMaker sm = new StringMaker();

		// Remote User

		sm.append("<b>Remote User:</b><br><br>");
		sm.append(req.getRemoteUser());
		sm.append("<br><br>");

		// Session ID

		sm.append("<b>Session ID:</b><br><br>");
		sm.append(req.getRequestedSessionId());
		sm.append("<br><br>");

		// Servlet Session Attributes

		sm.append("<b>Servlet Session Attributes:</b><br><br>");

		Enumeration enu = ses.getAttributeNames();

		while (enu.hasMoreElements()) {
			String attrName = (String)enu.nextElement();

			Object attrValue = ses.getAttribute(attrName);

			sm.append(attrName);
			sm.append("=");
			sm.append(attrValue);
			sm.append("<br>");
		}

		res.setContentType("text/html");

		res.getOutputStream().print(sm.toString());
		res.getOutputStream().flush();
	}

}