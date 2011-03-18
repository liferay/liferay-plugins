/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.testtransaction;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.testtransaction.model.Bar;
import com.liferay.testtransaction.service.BarLocalServiceUtil;

/**
 * @author Shuyang Zhou
 */
public class TestUtil {

	public static String testAddBarPortalRollback() {
		String text = "testBar";

		try {
			BarLocalServiceUtil.addBarPortalRollback(text);

			_log.error("Expected a SystemException, but did not get one.");

			return "Failed";
		}
		catch (SystemException se) {
			try {
				if (BarLocalServiceUtil.hasBar(text)) {

					_log.error("Bar object did not rollback.");

					return "Failed";
				}

				if (BarLocalServiceUtil.hasClassName()) {

					_log.error("ClassName object did not rollback.");

					return "Failed";
				}
			}
			catch (SystemException se1) {

				_log.error(se1, se1);

				return "Failed";
			}

			return "Passed";
		}
	}

	public static String testAddBarPortletRollback() {
		String text = "testBar";

		try {
			BarLocalServiceUtil.addBarPortletRollback(text);

			_log.error("Expected a SystemException, but did not get one.");

			return "Failed";
		}
		catch (SystemException se) {
			try {
				if (BarLocalServiceUtil.hasBar(text)) {

					_log.error("Bar object did not rollback.");

					return "Failed";
				}

				if (BarLocalServiceUtil.hasClassName()) {

					_log.error("ClassName object did not rollback.");

					return "Failed";
				}
			}
			catch (SystemException se1) {

				_log.error(se1, se1);

				return "Failed";
			}

			return "Passed";
		}
	}

	public static String testAddBarSuccess() {
		String text = "testBar";

		try {
			Bar bar = BarLocalServiceUtil.addBarSuccess(text);

			bar = BarLocalServiceUtil.getBar(bar.getBarId());

			if (!text.equals(bar.getText())) {
				throw new Exception("Bar text does not match, expected : " +
					text + ", got : " + bar.getText());
			}

			BarLocalServiceUtil.cleanUp(bar);

			return "Passed";
		}
		catch (Exception e) {

			_log.error(e, e);

			return "Failed";
		}
	}

	private static Log _log = LogFactoryUtil.getLog(TestUtil.class);

}