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

package com.liferay.portal.kernel.security;

import java.util.Random;

/**
 * @author Shuyang Zhou
 */
public class SecureRandom extends Random {

	@Override
	protected int next(int bits) {
		int bytes = (bits + 7) / 8;

		int result = SecureRandomUtil.nextByte();

		for (int i = 1; i < bytes; i++) {
			result = (result << 8) + (SecureRandomUtil.nextByte() & 0xFF);
		}

		return result >>> (bytes * 8 - bits);
	}

}