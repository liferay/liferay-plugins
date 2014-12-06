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

package com.liferay.sync.servlet;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Dennis Ju
 */
public class DownloadServletInputStream extends InputStream {

	public DownloadServletInputStream(InputStream inputStream, long size) {
		this(inputStream, size, "");
	}

	public DownloadServletInputStream(
		InputStream inputStream, long size, String mimeType) {

		_inputStream = inputStream;
		_size = size;
		_mimeType = mimeType;
	}

	public String getMimeType() {
		return _mimeType;
	}

	public long getSize() {
		return _size;
	}

	@Override
	public int read() throws IOException {
		return _inputStream.read();
	}

	private InputStream _inputStream;
	private String _mimeType;
	private long _size;

}