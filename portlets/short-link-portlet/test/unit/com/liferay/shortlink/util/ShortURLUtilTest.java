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

package com.liferay.shortlink.util;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Miroslav Ligas
 */
public class ShortURLUtilTest {

	@Before
	public void setup() {
		_random = new Random();
	}

	@Test
	public void testDecode() throws Exception {
		long decodedId = ShortURLUtil.decode("22");

		assertEquals(55l, decodedId);

		decodedId = ShortURLUtil.decode("21");

		assertEquals(54l, decodedId);

		decodedId = ShortURLUtil.decode("_");

		assertEquals(53l, decodedId);

		decodedId = ShortURLUtil.decode("2");

		assertEquals(1l, decodedId);

		decodedId = ShortURLUtil.decode("QMmnKztQyLt");

		assertEquals(Long.MAX_VALUE, decodedId);
	}

	@Test
	public void testEncode() throws Exception {
		String encodedId = ShortURLUtil.encode(0);

		assertEquals("", encodedId);

		encodedId = ShortURLUtil.encode(1);

		assertEquals("2", encodedId);

		encodedId = ShortURLUtil.encode(53);

		assertEquals("_", encodedId);

		encodedId = ShortURLUtil.encode(54);

		assertEquals("21", encodedId);

		encodedId = ShortURLUtil.encode(55);

		assertEquals("22", encodedId);

		encodedId = ShortURLUtil.encode(Long.MAX_VALUE);

		assertEquals("QMmnKztQyLt", encodedId);
	}

	@Test
	public void testEncodeDecode() throws Exception {
		for (int i = 0; i < 100; i++) {
			long id = _random.nextLong();

			if (id > 0) {
				String encodedId = ShortURLUtil.encode(id);

				long decodedId = ShortURLUtil.decode(encodedId);

				assertEquals(id, decodedId);
			}
		}
	}

	private Random _random;

}