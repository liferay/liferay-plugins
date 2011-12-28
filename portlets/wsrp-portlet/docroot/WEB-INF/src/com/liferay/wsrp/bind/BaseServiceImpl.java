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

package com.liferay.wsrp.bind;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.util.axis.ServletUtil;
import com.liferay.wsrp.model.WSRPProducer;
import com.liferay.wsrp.service.WSRPProducerLocalServiceUtil;

import javax.servlet.http.HttpServletRequest;

import oasis.names.tc.wsrp.v2.types.LocalizedString;

/**
 * @author Brian Wing Shun Chan
 */
public class BaseServiceImpl {

	protected LocalizedString getLocalizedString(String value) {
		return new LocalizedString(value, "en", null);
	}

	protected LocalizedString[] getLocalizedStrings(String[] values) {
		LocalizedString[] localizedStrings = new LocalizedString[values.length];

		for (int i = 0; i < values.length; i++) {
			String value = values[i];

			localizedStrings[i] = getLocalizedString(value);
		}

		return localizedStrings;
	}

	protected WSRPProducer getWSRPProducer() throws Exception {
		HttpServletRequest request = ServletUtil.getRequest();

		String wsrpProducerUuid = ParamUtil.getString(
			request, "wsrpProducerUuid");

		return WSRPProducerLocalServiceUtil.getWSRPProducer(wsrpProducerUuid);
	}

}