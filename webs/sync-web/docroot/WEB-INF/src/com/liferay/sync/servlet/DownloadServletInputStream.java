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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Dennis Ju
 */
public class DownloadServletInputStream extends InputStream {

	public DownloadServletInputStream(InputStream inputStream, long size) {
		this(inputStream, StringPool.BLANK, StringPool.BLANK, size);
	}

	public DownloadServletInputStream(
		InputStream inputStream, String fileName, String mimeType, long size) {

		_inputStream = inputStream;
		_fileName = fileName;
		_mimeType = mimeType;
		_size = size;
	}

	public String getFileName() {
		return _fileName;
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

	@Override
	public int read(byte[] b, int off, int len) throws IOException {

		// SYNC-1550

		try {
			return _inputStream.read(b, off, len);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new IOException(e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		DownloadServletInputStream.class);

	private String _fileName;
	private InputStream _inputStream;
	private String _mimeType;
	private long _size;

}