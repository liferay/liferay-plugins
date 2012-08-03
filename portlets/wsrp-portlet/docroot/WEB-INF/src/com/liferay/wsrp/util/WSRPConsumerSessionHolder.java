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

package com.liferay.wsrp.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Michael C. Han
 */
class WSRPConsumerSessionHolder implements Serializable {

	public Map<String, WSRPConsumerManager> getWsrpConsumerManagers() {
		return _wsrpConsumerManagers;
	}

	private void readObject(ObjectInputStream in)
		throws IOException, ClassNotFoundException {

		_wsrpConsumerManagers =
			new ConcurrentHashMap<String, WSRPConsumerManager>();
	}


	private transient Map<String, WSRPConsumerManager>
		_wsrpConsumerManagers =
			new ConcurrentHashMap<String, WSRPConsumerManager>();
}
