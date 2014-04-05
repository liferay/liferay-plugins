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

package com.liferay.io.delta;

import java.io.IOException;

import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

/**
 * @author Connor McKay
 */
public class ByteChannelWriter {

	public ByteChannelWriter(WritableByteChannel writableByteChannel) {
		this(writableByteChannel, 1024);
	}

	public ByteChannelWriter(
		WritableByteChannel writableByteChannel, int bufferLength) {

		_writableByteChannel = writableByteChannel;

		_byteBuffer = ByteBuffer.allocate(bufferLength);
	}

	public void ensureSpace(int length) throws IOException {
		if (_byteBuffer.remaining() < length) {
			write();
		}
	}

	public void finish() throws IOException {
		_byteBuffer.flip();

		_writableByteChannel.write(_byteBuffer);
	}

	public ByteBuffer getBuffer() {
		return _byteBuffer;
	}

	public void resizeBuffer(int minBufferLength) {
		if (_byteBuffer.capacity() >= minBufferLength) {
			return;
		}

		ByteBuffer newBuffer = ByteBuffer.allocate(minBufferLength);

		_byteBuffer.flip();

		newBuffer.put(_byteBuffer);

		_byteBuffer = newBuffer;
	}

	protected void write() throws IOException {
		_byteBuffer.flip();

		_writableByteChannel.write(_byteBuffer);

		_byteBuffer.clear();
	}

	private ByteBuffer _byteBuffer;
	private WritableByteChannel _writableByteChannel;

}