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

package com.liferay.compat.portal.kernel.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Brian Wing Shun Chan
 * @author Wesley Gong
 * @author Calvin Keum
 */
public class BigDecimalUtil {

	public static double add(Number x, Number y) {
		if (x == null) {
			x = 0;
		}

		if (y == null) {
			y = 0;
		}

		BigDecimal xBigDecimal = new BigDecimal(x.toString());
		BigDecimal yBigDecimal = new BigDecimal(y.toString());

		BigDecimal resultBigDecimal = xBigDecimal.add(yBigDecimal);

		return resultBigDecimal.doubleValue();
	}

	public static double divide(
		Number x, Number y, int scale, RoundingMode roundingMode) {

		if (x == null) {
			x = 0;
		}

		if (y == null) {
			y = 0;
		}

		BigDecimal xBigDecimal = new BigDecimal(x.toString());
		BigDecimal yBigDecimal = new BigDecimal(y.toString());

		BigDecimal resultBigDecimal = xBigDecimal.divide(
			yBigDecimal, scale, roundingMode);

		return resultBigDecimal.doubleValue();
	}

	public static double multiply(Number x, Number y) {
		if (x == null) {
			x = 0;
		}

		if (y == null) {
			y = 0;
		}

		BigDecimal xBigDecimal = new BigDecimal(x.toString());
		BigDecimal yBigDecimal = new BigDecimal(y.toString());

		BigDecimal resultBigDecimal = xBigDecimal.multiply(yBigDecimal);

		return resultBigDecimal.doubleValue();
	}

	public static double scale(Number x, int scale, RoundingMode roundingMode) {
		if (x == null) {
			x = 0;
		}

		BigDecimal xBigDecimal = new BigDecimal(x.toString());

		xBigDecimal = xBigDecimal.setScale(scale, roundingMode);

		return xBigDecimal.doubleValue();
	}

	public static double subtract(Number x, Number y) {
		if (x == null) {
			x = 0;
		}

		if (y == null) {
			y = 0;
		}

		BigDecimal xBigDecimal = new BigDecimal(x.toString());
		BigDecimal yBigDecimal = new BigDecimal(y.toString());

		BigDecimal resultBigDecimal = xBigDecimal.subtract(yBigDecimal);

		return resultBigDecimal.doubleValue();
	}

}