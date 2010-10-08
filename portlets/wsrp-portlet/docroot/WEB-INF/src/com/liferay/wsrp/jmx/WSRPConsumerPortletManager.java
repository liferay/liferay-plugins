/*
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.wsrp.jmx;

import com.liferay.wsrp.service.WSRPConsumerPortletLocalServiceUtil;

import javax.management.MBeanException;

/**
 * <a href="WSRPConsumerPortletManager.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class WSRPConsumerPortletManager
	implements WSRPConsumerPortletManagerMBean {

	public void reInitFailedConsumerPortlets() throws MBeanException {
		try {
			WSRPConsumerPortletLocalServiceUtil.reInitFailedConsumerPortlets();
		}
		catch (Exception e) {
			throw new MBeanException(e, "Unable to re-initialize portlets");
		}

	}
}
