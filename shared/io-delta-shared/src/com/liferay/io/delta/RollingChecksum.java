/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.sync.io.delta;

import java.io.IOException;

import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Connor McKay
 */
public class RollingChecksum {

	public RollingChecksum(
			ReadableByteChannel readableByteChannel, int blockLength)
		throws IOException {

		_blockLength = blockLength;
		_byteChannelReader = new ByteChannelReader(
			readableByteChannel, _blockLength * DeltaUtil.BUFFER_FACTOR);

		generateWeakChecksum();
	}

	public int currentBlockLength() {
		return Math.min(_byteChannelReader.remaining(), _blockLength);
	}

	/**
	 * Returns the first byte of data in the current block.
	 */
	public byte getFirstByte() {
		return _byteChannelReader.get(0);
	}

	/**
	 * Returns the position of the start of the current block in the file.
	 */
	public int getPosition() {
		return _filePosition;
	}

	public boolean hasNext() throws IOException {
		_byteChannelReader.maybeRead(1);

		if (_byteChannelReader.remaining() >= 1) {
			return true;
		}
		else {
			return false;
		}
	}

	public void nextBlock() throws IOException {
		_filePosition += _byteChannelReader.skip(_blockLength);

		generateWeakChecksum();
	}

	public void nextByte() throws IOException {
		int blockLength = currentBlockLength();
		int x = _byteChannelReader.get();

		_filePosition++;

		_a -= x;
		_b -= blockLength * x;

		_byteChannelReader.maybeRead(_blockLength);

		if (_byteChannelReader.remaining() >= _blockLength) {
			x = _byteChannelReader.get(_blockLength - 1);

			_a += x;
			_b += _a;
		}
	}

	/**
	 * Returns the strong checksum of the current block.
	 */
	public byte[] strongChecksum() {
		ByteBuffer buffer = _byteChannelReader.getBuffer();

		int oldPosition = buffer.position();
		int oldLimit = buffer.limit();

		buffer.limit(buffer.position() + currentBlockLength());

		_messageDigest.update(buffer);

		buffer.limit(oldLimit);
		buffer.position(oldPosition);

		return _messageDigest.digest();
	}

	/**
	 * Returns the weak checksum of the current block.
	 */
	public int weakChecksum() {
		return (_a & 0xffff) | (_b << 16);
	}

	protected void generateWeakChecksum() throws IOException {
		_byteChannelReader.maybeRead(_blockLength);

		_a = 0;
		_b = 0;

		for (int i = 0; i < currentBlockLength(); i++) {
			_a += _byteChannelReader.get(i);
			_b += _a;
		}
	}

	private static MessageDigest _messageDigest;

	private int _a;
	private int _b;
	private int _blockLength;
	private ByteChannelReader _byteChannelReader;
	private int _filePosition;

	static {
		try {
			_messageDigest = MessageDigest.getInstance("MD5");
		}
		catch (NoSuchAlgorithmException nsae) {
			throw new ExceptionInInitializerError(nsae);
		}
	}

}