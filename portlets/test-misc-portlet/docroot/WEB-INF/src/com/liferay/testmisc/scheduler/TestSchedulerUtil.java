/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.testmisc.scheduler;

/**
 * @author Shuyang Zhou
 */
public class TestSchedulerUtil {

	public static boolean isReceivedBeforeSpringInitialzed() {
		return _receivedBeforeSpringInitialzed;
	}

	public static boolean isScheduledBeforeSpringInitialized() {
		return _scheduledBeforeSpringInitialized;
	}

	public static void setReceivedBeforeSpringInitialzed(
		boolean receivedBeforeSpringInitialzed) {

		_receivedBeforeSpringInitialzed = receivedBeforeSpringInitialzed;
	}

	public static void setScheduledBeforeSpringInitialized(
		boolean scheduledBeforeSpringInitialized) {

		_scheduledBeforeSpringInitialized = scheduledBeforeSpringInitialized;
	}

	private static boolean _receivedBeforeSpringInitialzed;
	private static boolean _scheduledBeforeSpringInitialized;

}