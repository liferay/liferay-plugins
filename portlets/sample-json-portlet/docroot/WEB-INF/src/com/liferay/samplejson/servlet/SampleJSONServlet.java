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

package com.liferay.samplejson.servlet;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * <a href="SampleJSONServlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Ming-Gih Lam
 *
 */
public class SampleJSONServlet extends HttpServlet {

	public void service(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		String content = null;

		try {
			content = getContent(request, response);
		}
		catch (Exception e) {
			response.sendError(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());

			return;
		}

		response.setContentType("text/javascript");
		response.setHeader("Cache-Control", "no-cache");

		PrintWriter pw = response.getWriter();

		pw.write(content);

		pw.close();
	}

	protected String getContent(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		String id = ParamUtil.getString(request, "id");
		String callback = ParamUtil.getString(request, "callback");

		JSONObject jsonObj = new JSONObject();

		jsonObj.put("id", id);

		ServletContext servletContext = getServletContext();

		String script = StringUtil.read(
			servletContext.getResourceAsStream("/click_image.js"));

		String jsonObjString = jsonObj.toString();

		jsonObjString =
			jsonObjString.substring(0, jsonObjString.length() - 1) + "," +
				script + "}";

		return callback + "(" + jsonObjString + ");";
	}

}