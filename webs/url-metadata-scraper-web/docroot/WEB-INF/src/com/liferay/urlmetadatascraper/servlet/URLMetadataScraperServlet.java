/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.urlmetadatascraper.servlet;

import com.liferay.urlmetadatascraper.util.URLMetadataScraperProcessor;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Evan Thibodeau
 * @author Matthew Kong
 */
public class URLMetadataScraperServlet extends HttpServlet {

	@Override
	public void init() {
		_urlMetadataScraperProcessor = new URLMetadataScraperProcessor();
	}

	@Override
	public void service(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		String url = request.getParameter("url");

		try {
			String json = _urlMetadataScraperProcessor.getURLMetadataJSON(url);

			write(response, new ByteArrayInputStream(json.getBytes()));
		}
		catch (Exception e) {
			_log.error(e, e);

			sendError(response, e.getMessage());
		}
	}

	protected void sendError(HttpServletResponse response, String message)
		throws IOException {

		write(response, new ByteArrayInputStream(message.getBytes()));
	}

	protected void write(HttpServletResponse response, InputStream inputStream)
		throws IOException {

		OutputStream outputStream = null;

		try {
			response.setHeader("Cache-Control", "public");

			if (!response.isCommitted()) {
				outputStream = new BufferedOutputStream(
					response.getOutputStream());

				int c = inputStream.read();

				while (c != -1) {
					outputStream.write(c);

					c = inputStream.read();
				}
			}
		}
		finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
				}
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(e);
				}
			}

			try {
				if (outputStream != null) {
					outputStream.close();
				}
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(e);
				}
			}

			try {
				if (inputStream != null) {
					inputStream.close();
				}
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(e);
				}
			}
		}
	}

	private static Log _log = LogFactory.getLog(
		URLMetadataScraperServlet.class);

	private URLMetadataScraperProcessor _urlMetadataScraperProcessor;

}