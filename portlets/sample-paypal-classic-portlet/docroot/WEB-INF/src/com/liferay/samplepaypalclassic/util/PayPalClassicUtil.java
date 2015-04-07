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

package com.liferay.samplepaypalclassic.util;

import com.paypal.svcs.services.AdaptivePaymentsService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Douglas Wong
 */

public class PayPalClassicUtil {

	protected static AdaptivePaymentsService getAdaptivePaymentsService() {
		Map<String, String> map = new HashMap<>();

		map.put("acct1.AppId", PortletPropsValues.PAYPAL_APP_ID);
		map.put("acct1.Password", PortletPropsValues.PAYPAL_PASSWORD);
		map.put("acct1.Signature", PortletPropsValues.PAYPAL_SIGNATURE);
		map.put("acct1.UserName", PortletPropsValues.PAYPAL_USER_NAME);

		if (PortletPropsValues.PAYPAL_SANDBOX_MODE) {
			map.put("mode", "sandbox");
		}

		AdaptivePaymentsService adaptivePaymentsService =
			new AdaptivePaymentsService(map);

		return adaptivePaymentsService;
	}

}