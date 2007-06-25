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

package com.sample.json.servlet;

import com.liferay.util.ParamUtil;
import com.liferay.util.StringUtil;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.json.JSONRaw;

/**
 * <a href="JSONServlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Ming-Gih Lam
 *
 */
public class JSONServlet extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse res)
		throws IOException, ServletException {

		String content = null;

		try {
			content = getContent(req, res);
		}
		catch (Exception e) {
			res.sendError(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());

			return;
		}

		res.setContentType("text/javascript");
		res.setHeader("Cache-Control", "no-cache");

		PrintWriter pw = res.getWriter();

		pw.write(content);

		pw.close();
	}

	protected String getContent(HttpServletRequest req, HttpServletResponse res)
		throws Exception {

		String id = ParamUtil.getString(req, "id");
		String callback = ParamUtil.getString(req, "callback");

		JSONObject jsonObj = new JSONObject();

		jsonObj.put("id", id);

		ServletContext ctx = getServletContext();

		String script = StringUtil.read(
			ctx.getResourceAsStream("/click_image.js"));

		JSONRaw jsonRaw = new JSONRaw(script);

		jsonObj.put("click_image", jsonRaw);

		return callback + "(" + jsonObj.toString() + ");";
	}

}