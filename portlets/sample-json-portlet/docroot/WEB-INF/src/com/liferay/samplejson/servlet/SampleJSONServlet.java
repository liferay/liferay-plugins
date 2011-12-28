/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
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
 * @author Ming-Gih Lam
 */
public class SampleJSONServlet extends HttpServlet {

	@Override
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