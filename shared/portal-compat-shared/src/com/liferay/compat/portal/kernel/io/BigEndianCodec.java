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

package com.liferay.compat.portal.kernel.io;

/**
 * @author Shuyang Zhou
 */
public class BigEndianCodec {

	public static boolean getBoolean(byte[] bytes, int index) {
		return bytes[index] != 0;
	}

	public static char getChar(byte[] bytes, int index) {
		return (char)((bytes[index] << 8) + (bytes[index + 1] & 0xFF));
	}

	public static double getDouble(byte[] bytes, int index) {
		return Double.longBitsToDouble(getLong(bytes, index));
	}

	public static float getFloat(byte[] bytes, int index) {
		return Float.intBitsToFloat(getInt(bytes, index));
	}

	public static int getInt(byte[] bytes, int index) {
		return (bytes[index] << 24) + ((bytes[index + 1] & 0xFF) << 16) +
			((bytes[index + 2] & 0xFF) << 8) + (bytes[index + 3] & 0xFF);
	}

	public static long getLong(byte[] bytes, int index) {
		return (((long)bytes[index]) << 56) +
			((bytes[index + 1] & 0xFFL) << 48) +
			((bytes[index + 2] & 0xFFL) << 40) +
			((bytes[index + 3] & 0xFFL) << 32) +
			((bytes[index + 4] & 0xFFL) << 24) +
			((bytes[index + 5] & 0xFFL) << 16) +
			((bytes[index + 6] & 0xFFL) << 8) +
			(bytes[index + 7] & 0xFFL);
	}

	public static short getShort(byte[] bytes, int index) {
		return (short)((bytes[index] << 8) + (bytes[index + 1] & 0xFF));
	}

	public static void putBoolean(byte[] bytes, int index, boolean b) {
		bytes[index] = (byte)(b ? 1 : 0);
	}

	public static void putChar(byte[] bytes, int index, char c) {
		bytes[index] = (byte)(c >>> 8);
		bytes[index + 1] = (byte)c;
	}

	public static void putDouble(byte[] bytes, int index, double d) {
		putLong(bytes, index, Double.doubleToLongBits(d));
	}

	public static void putFloat(byte[] bytes, int index, float f) {
		putInt(bytes, index, Float.floatToIntBits(f));
	}

	public static void putInt(byte[] bytes, int index, int i) {
		bytes[index] = (byte)(i >>> 24);
		bytes[index + 1] = (byte)(i >>> 16);
		bytes[index + 2] = (byte)(i >>> 8);
		bytes[index + 3] = (byte)i;
	}

	public static void putLong(byte[] bytes, int index, long l) {
		bytes[index] = (byte)(l >>> 56);
		bytes[index + 1] = (byte)(l >>> 48);
		bytes[index + 2] = (byte)(l >>> 40);
		bytes[index + 3] = (byte)(l >>> 32);
		bytes[index + 4] = (byte)(l >>> 24);
		bytes[index + 5] = (byte)(l >>> 16);
		bytes[index + 6] = (byte)(l >>> 8);
		bytes[index + 7] = (byte)l;
	}

	public static void putShort(byte[] bytes, int index, short s) {
		bytes[index] = (byte)(s >>> 8);
		bytes[index + 1] = (byte)s;
	}

}