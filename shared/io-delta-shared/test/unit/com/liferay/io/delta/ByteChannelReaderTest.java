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

import org.junit.Test;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * @author Dennis Ju
 */
public class ByteChannelReaderTest {

	@Test
	public void testRead() throws IOException {
		ReadableByteChannel readableByteChannel = Mockito.mock(
			ReadableByteChannel.class);

		int length = 128;

		ByteChannelReader byteChannelReader = new ByteChannelReader(
			readableByteChannel, length);

		ByteBuffer byteBuffer = byteChannelReader.getBuffer();

		Answer answer = new Answer<Integer>() {

			@Override
			public Integer answer(InvocationOnMock invocationOnMock)
				throws Throwable {

				int bytesRead = byteBuffer.remaining();

				if (partialRead) {
					bytesRead--;
				}

				byteBuffer.put(new byte[bytesRead], 0, bytesRead);

				partialRead = !partialRead;

				return bytesRead;
			}

			private boolean partialRead = true;

		};

		Mockito.when(readableByteChannel.read(byteBuffer)).then(answer);

		int dataRemaining = 1025;

		while (dataRemaining > 0) {
			if (dataRemaining > length) {
				dataRemaining -= length;
			}
			else {
				length = dataRemaining;

				dataRemaining = 0;
			}

			byteChannelReader.ensureData(length);

			byteBuffer.get(new byte[length]);
		}
	}

}