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

package com.liferay.sync.engine.documentlibrary.event;

import com.liferay.sync.engine.documentlibrary.handler.Handler;
import com.liferay.sync.engine.session.Session;
import com.liferay.sync.engine.session.SessionManager;

import java.util.Map;

/**
 * @author Shinn Lok
 */
public abstract class BaseEvent implements Event {

	public BaseEvent(
		long syncAccountId, String urlPath, Map<String, Object> parameters) {

		_syncAccountId = syncAccountId;
		_urlPath = urlPath;
		_parameters = parameters;
	}

	public <T> T executeGet(String urlPath) throws Exception {
		Session session = SessionManager.getSession(_syncAccountId);

		return session.executeGet(urlPath, (Handler<? extends T>)_handler);
	}

	public <T> T executePost(String urlPath, Map<String, Object> parameters)
		throws Exception {

		Session session = SessionManager.getSession(_syncAccountId);

		return session.executePost(
			urlPath, parameters, (Handler<? extends T>)_handler);
	}

	@Override
	public Map<String, Object> getParameters() {
		return _parameters;
	}

	@Override
	public Object getParameterValue(String key) {
		return _parameters.get(key);
	}

	@Override
	public long getSyncAccountId() {
		return _syncAccountId;
	}

	@Override
	public void run() {
		_handler = getHandler();

		try {
			processRequest();
		}
		catch (Exception e) {
			_handler.handleException(e);
		}
	}

	protected abstract Handler<?> getHandler();

	protected void processRequest() throws Exception {
		executePost(_urlPath, _parameters);
	}

	private Handler<?> _handler;
	private Map<String, Object> _parameters;
	private long _syncAccountId;
	private String _urlPath;

}