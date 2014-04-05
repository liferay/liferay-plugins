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

package com.liferay.portal.workflow.kaleo.definition;

import com.liferay.compat.portal.kernel.scheduler.CronText;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Peter Borkuti
 */
public class DurationScaleTest {

	public static final String[] SCALES = {
		"second", "minute", "hour", "day", "week", "month", "year"
	};

	public static final int[] VALUES = {
		CronText.SECONDLY_FREQUENCY, CronText.MINUTELY_FREQUENCY,
		CronText.HOURLY_FREQUENCY, CronText.DAILY_FREQUENCY,
		CronText.WEEKLY_FREQUENCY, CronText.MONTHLY_FREQUENCY,
		CronText.YEARLY_FREQUENCY
	};

	@Test(expected = IllegalArgumentException.class)
	public void testParseInvalidScale() throws Exception {
		DurationScale.parse("random text");
	}

	@Test
	public void testParseValidScales() throws Exception {
		for (String scale : SCALES) {
			DurationScale durationScale = DurationScale.parse(scale);

			Assert.assertEquals(scale, durationScale.getValue());
		}
	}

	@Test
	public void testScaleNum() throws Exception {
		Assert.assertEquals(7, DurationScale.values().length);
	}

	@Test
	public void testValues() throws Exception {
		for (int i = 0; i < SCALES.length; i++) {
			DurationScale durationScale = DurationScale.parse(SCALES[i]);

			Assert.assertEquals(VALUES[i], durationScale.getIntegerValue());
		}
	}

}