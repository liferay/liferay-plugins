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

package com.liferay.samplelar.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.samplelar.service.SampleLARBookingLocalServiceUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Mate Thurzo
 */
public class SampleLARPortlet extends MVCPortlet {

	public void addSampleLARBooking(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String bookingNumber =
			"LR" + GetterUtil.getInteger(Math.random() * 100);

		SampleLARBookingLocalServiceUtil.addSampleLARBooking(
			themeDisplay.getUserId(), themeDisplay.getScopeGroupId(),
			bookingNumber, new ServiceContext());
	}

	public void deleteSampleLARBooking(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long sampleLARBookingId = ParamUtil.getLong(
			actionRequest, "sampleLARBookingId");

		SampleLARBookingLocalServiceUtil.deleteSampleLARBooking(
			sampleLARBookingId);
	}

}