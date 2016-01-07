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

package com.liferay.sync.filter;

import com.liferay.portal.kernel.util.StreamUtil;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author Dennis Ju
 */
public class SyncJSONHttpServletRequestWrapper
	extends HttpServletRequestWrapper {

	public SyncJSONHttpServletRequestWrapper(
		HttpServletRequest httpServletRequest) {

		super(httpServletRequest);
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		if (_byteArrayOutputStream == null) {
			_byteArrayOutputStream = new ByteArrayOutputStream();

			StreamUtil.transfer(super.getInputStream(), _byteArrayOutputStream);
		}

		final ByteArrayInputStream byteArrayInputStream =
			new ByteArrayInputStream(_byteArrayOutputStream.toByteArray());

		return new ServletInputStream() {

			@Override
			public int read() throws IOException {
				return byteArrayInputStream.read();
			}

		};
	}

	@Override
	public BufferedReader getReader() throws IOException {
		InputStreamReader inputStreamReader = new InputStreamReader(
			getInputStream());

		return new BufferedReader(inputStreamReader);
	}

	private ByteArrayOutputStream _byteArrayOutputStream;

}