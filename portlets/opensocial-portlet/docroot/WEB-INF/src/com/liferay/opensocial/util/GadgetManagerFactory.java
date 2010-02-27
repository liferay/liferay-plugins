/**
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

package com.liferay.opensocial.util;

import com.liferay.opensocial.model.Gadget;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <a href="GadgetManagerFactory.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael Young
 */
public class GadgetManagerFactory {

	public static GadgetManager getGadgetManager(Gadget gadget)
		throws Exception {

		long gadgetId = gadget.getGadgetId();

		GadgetManager gadgetManager = _gadgetManagers.get(gadgetId);

		if (gadgetManager == null) {
			gadgetManager = new GadgetManager(gadget);

			_gadgetManagers.put(gadgetId, gadgetManager);
		}

		return gadgetManager;
	}

	private static Map<Long, GadgetManager> _gadgetManagers =
		new ConcurrentHashMap<Long, GadgetManager>();

}