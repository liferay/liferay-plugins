/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

import com.liferay.sync.engine.documentlibrary.handler.BaseHandler;
import com.liferay.sync.engine.model.SyncAccount;
import com.liferay.sync.engine.service.SyncAccountService;
import com.liferay.sync.engine.session.Session;
import com.liferay.sync.engine.session.SessionManager;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.conn.HttpHostConnectException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public abstract class BaseEvent implements Runnable {

	public BaseEvent(
		long syncAccountId, String urlPath, Map<String, Object> parameters) {

		_syncAccountId = syncAccountId;
		_urlPath = urlPath;
		_parameters = parameters;
	}

	public <T> T executeGet(
			String urlPath, ResponseHandler<? extends T> responseHandler)
		throws Exception {

		Session session = SessionManager.getSession(_syncAccountId);

		return session.executeGet(urlPath, responseHandler);
	}

	public <T> T executePost(
			String urlPath, Map<String, Object> parameters,
			ResponseHandler<? extends T> responseHandler)
		throws Exception {

		Session session = SessionManager.getSession(_syncAccountId);

		return session.executePost(urlPath, parameters, responseHandler);
	}

	@Override
	public void run() {
		try {
			String response = processRequest();

			if (response == null) {
				return;
			}

			processResponse(response);
		}
		catch (Exception e) {
			_logger.error(e.getMessage(), e);

			SyncAccount syncAccount = SyncAccountService.fetchSyncAccount(
				_syncAccountId);

			syncAccount.setState(SyncAccount.STATE_DISCONNECTED);

			if (e instanceof HttpHostConnectException) {
				syncAccount.setUiEvent(
					SyncAccount.UI_EVENT_CONNECTION_EXCEPTION);
			}
			else if (e instanceof HttpResponseException) {
				HttpResponseException hre = (HttpResponseException)e;

				int statusCode = hre.getStatusCode();

				if (statusCode == HttpServletResponse.SC_UNAUTHORIZED) {
					syncAccount.setUiEvent(
						SyncAccount.UI_EVENT_AUTHENTICATION_EXCEPTION);
				}
				else {
					syncAccount.setUiEvent(
						SyncAccount.UI_EVENT_CONNECTION_EXCEPTION);
				}
			}

			SyncAccountService.update(syncAccount);
		}
	}

	protected Map<String, Object> getParameters() {
		return _parameters;
	}

	protected Object getParameterValue(String key) {
		return _parameters.get(key);
	}

	protected long getSyncAccountId() {
		return _syncAccountId;
	}

	protected String processRequest() throws Exception {
		return executePost(_urlPath, _parameters, new BaseHandler());
	}

	protected abstract void processResponse(String response)
		throws Exception;

	private static Logger _logger = LoggerFactory.getLogger(BaseEvent.class);

	private Map<String, Object> _parameters;
	private long _syncAccountId;
	private String _urlPath;

}