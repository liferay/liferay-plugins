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

package com.liferay.servermanager.executor;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.servermanager.util.JSONKeys;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public abstract class BaseExecutor implements Executor {

	public BaseExecutor() {
		Map<String, Executor> nextExecutors = initNextExecutors();

		if (nextExecutors != null) {
			_nextExecutors = Collections.unmodifiableMap(nextExecutors);
		}
		else {
			_nextExecutors = Collections.emptyMap();
		}
	}

	public void executeCreate(
			HttpServletRequest request, JSONObject responseJSONObject,
			Queue<String> arguments)
		throws Exception {

		throw new UnsupportedOperationException();
	}

	public void executeDelete(
			HttpServletRequest request, JSONObject responseJSONObject,
			Queue<String> arguments)
		throws Exception {

		throw new UnsupportedOperationException();
	}

	public void executeRead(
			HttpServletRequest request, JSONObject responseJSONObject,
			Queue<String> arguments)
		throws Exception {

		Map<String, Executor> nextExecutors = getNextExecutors();

		List<String> paths = new ArrayList<String>(nextExecutors.keySet());

		Collections.sort(paths);

		responseJSONObject.put(
			JSONKeys.OUTPUT,
			"Valid paths are " + StringUtil.merge(paths, ", "));
	}

	public void executeUpdate(
			HttpServletRequest request, JSONObject responseJSONObject,
			Queue<String> arguments)
		throws Exception {

		throw new UnsupportedOperationException();
	}

	public Executor getNextExecutor(Queue<String> arguments) {
		Map<String, Executor> nextExecutors = getNextExecutors();

		String type = arguments.peek();

		Executor nextExecutor = nextExecutors.get(type);

		if (nextExecutor != null) {
			arguments.remove();
		}

		return nextExecutor;
	}

	public Map<String, Executor> getNextExecutors() {
		return _nextExecutors;
	}

	protected Map<String, Executor> initNextExecutors() {
		return null;
	}

	private Map<String, Executor> _nextExecutors;

}