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

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

	public boolean isSyncJSONRequest() {
		try {
			String cmd = getParameter(Constants.CMD);

			if (cmd == null) {
				cmd = StringUtil.read(getInputStream());
			}

			Object jsonObject = JSONFactoryUtil.looseDeserialize(cmd);

			List<Object> jsonItems = null;

			if (jsonObject instanceof List) {
				jsonItems = (List<Object>)jsonObject;
			}
			else if (jsonObject instanceof Map) {
				jsonItems = new ArrayList<>(1);

				jsonItems.add(jsonObject);
			}

			for (Object jsonItem : jsonItems) {
				Map<String, Map<String, Object>> map =
					(Map<String, Map<String, Object>>)jsonItem;

				Set<String> keySet = map.keySet();

				Iterator<String> iterator = keySet.iterator();

				String key = iterator.next();

				if (key.startsWith("/sync-web.") ||
					key.startsWith("/sync-web/")) {

					return true;
				}
			}
		}
		catch (Exception e) {
			return false;
		}

		return false;
	}

	private ByteArrayOutputStream _byteArrayOutputStream;

}