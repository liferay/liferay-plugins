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

package com.liferay.testmisc.portlet;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncStringWriter;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.UnsyncPrintWriterPool;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.portlet.RenderResponse;
import javax.portlet.filter.RenderResponseWrapper;

/**
 * @author Mika Koivisto
 * @author Michael Young
 */
public class TestRenderResponse extends RenderResponseWrapper {

	public TestRenderResponse(RenderResponse renderResponse) {
		super(renderResponse);
	}

	@Override
	public OutputStream getPortletOutputStream() {
		if (_printWriter != null) {
			throw new IllegalStateException(
				"Cannot obtain OutputStream because Writer is already in use");
		}

		if (_byteArrayOutputStream == null) {
			_byteArrayOutputStream = new UnsyncByteArrayOutputStream();
		}

		return _byteArrayOutputStream;
	}

	public String getString() {
		if (_string != null) {
			return _string;
		}

		if (_byteArrayOutputStream != null) {
			try {
				_string = _byteArrayOutputStream.toString(StringPool.UTF8);
			}
			catch (UnsupportedEncodingException uee) {
				uee.printStackTrace();

				_string = StringPool.BLANK;
			}
		}
		else if (_printWriter != null) {
			_string = _unsyncStringWriter.toString();
		}
		else {
			_string = StringPool.BLANK;
		}

		return _string;
	}

	@Override
	public PrintWriter getWriter() {
		if (_byteArrayOutputStream != null) {
			throw new IllegalStateException(
				"Cannot obtain Writer because OutputStream is already in use");
		}

		if (_printWriter == null) {
			_unsyncStringWriter = new UnsyncStringWriter();
			_printWriter = UnsyncPrintWriterPool.borrow(_unsyncStringWriter);
		}

		return _printWriter;
	}

	public boolean isCalledGetOutputStream() {
		if (_byteArrayOutputStream != null) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isCalledGetWriter() {
		if (_printWriter != null) {
			return true;
		}
		else {
			return false;
		}
	}

	private UnsyncByteArrayOutputStream _byteArrayOutputStream;
	private PrintWriter _printWriter;
	private String _string;
	private UnsyncStringWriter _unsyncStringWriter;

}