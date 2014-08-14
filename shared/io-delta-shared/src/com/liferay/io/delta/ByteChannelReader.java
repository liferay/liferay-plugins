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
import java.nio.channels.ReadableByteChannel;

/**
 * @author Connor McKay
 */
public class ByteChannelReader {

	public ByteChannelReader(ReadableByteChannel readableByteChannel)
		throws IOException {

		this(readableByteChannel, 1024);
	}

	public ByteChannelReader(
			ReadableByteChannel readableByteChannel, int bufferLength)
		throws IOException {

		_readableByteChannel = readableByteChannel;

		_byteBuffer = ByteBuffer.allocate(bufferLength);

		if (_readableByteChannel.read(_byteBuffer) == -1) {
			_eof = true;
		}
		else {
			_eof = false;
		}

		_byteBuffer.flip();
	}

	public void ensureData(int length) throws IOException {
		if (_byteBuffer.remaining() < length) {
			read(length);

			if (_eof || (_byteBuffer.remaining() < length)) {
				throw new IOException("Unexpected EOF");
			}
		}
	}

	public byte get() {
		return _byteBuffer.get();
	}

	public byte get(int offset) {
		return _byteBuffer.get(_byteBuffer.position() + offset);
	}

	public ByteBuffer getBuffer() {
		return _byteBuffer;
	}

	public boolean hasRemaining() {
		return _byteBuffer.hasRemaining();
	}

	public void maybeRead(int length) throws IOException {
		if (!_eof && (_byteBuffer.remaining() < length)) {
			read(length);
		}
	}

	public void read(int length) throws IOException {
		if (_eof) {
			return;
		}

		_byteBuffer.compact();

		while (_byteBuffer.position() < length) {
			int bytesRead = _readableByteChannel.read(_byteBuffer);

			if (bytesRead == -1) {
				_eof = true;

				break;
			}
		}

		_byteBuffer.flip();
	}

	public int remaining() {
		return _byteBuffer.remaining();
	}

	public void resizeBuffer(int minBufferLength) {
		if (_byteBuffer.capacity() >= minBufferLength) {
			return;
		}

		ByteBuffer newBuffer = ByteBuffer.allocate(minBufferLength);

		newBuffer.put(_byteBuffer);
		newBuffer.flip();

		_byteBuffer = newBuffer;
	}

	public int skip(int length) {
		length = Math.min(_byteBuffer.remaining(), length);

		_byteBuffer.position(_byteBuffer.position() + length);

		return length;
	}

	private ByteBuffer _byteBuffer;
	private boolean _eof;
	private ReadableByteChannel _readableByteChannel;

}