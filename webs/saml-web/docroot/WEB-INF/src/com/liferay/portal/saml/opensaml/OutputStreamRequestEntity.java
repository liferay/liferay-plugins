/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.saml.opensaml;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.httpclient.methods.RequestEntity;

/**
 * @author Mika Koivisto
 */
public class OutputStreamRequestEntity implements RequestEntity {

	public OutputStreamRequestEntity(ByteArrayOutputStream outputStream) {
		this(outputStream, null);
	}

	public OutputStreamRequestEntity(
		ByteArrayOutputStream outputStream, String contentType) {

		_byteArrayOutputStream = outputStream;
		_contentType = contentType;
	}

	public boolean isRepeatable() {
		return true;
	}

	public void writeRequest(OutputStream outputStream) throws IOException {
		_byteArrayOutputStream.writeTo(outputStream);
	}

	public long getContentLength() {
		return _byteArrayOutputStream.size();
	}

	public String getContentType() {
		return _contentType;
	}

	private ByteArrayOutputStream _byteArrayOutputStream;
	private String _contentType;
}
