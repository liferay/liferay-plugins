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

package com.liferay.sync.engine.documentlibrary.handler;

import com.liferay.sync.engine.documentlibrary.event.Event;
import com.liferay.sync.engine.model.SyncAccount;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.service.SyncAccountService;
import com.liferay.sync.engine.service.SyncFileService;

import java.io.FileNotFoundException;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.conn.HttpHostConnectException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

/**
 * @author Shinn Lok
 */
public class BaseHandler implements Handler<Void> {

	public BaseHandler(Event event) {
		_event = event;
	}

	@Override
	public void handleException(Exception e) {
		_logger.error(e.getMessage(), e);

		SyncAccount syncAccount = SyncAccountService.fetchSyncAccount(
			getSyncAccountId());

		if (e instanceof FileNotFoundException) {
			SyncFile syncFile = (SyncFile)getParameterValue("syncFile");

			if (syncFile.getVersion() == null) {
				SyncFileService.deleteSyncFile(syncFile);
			}

			return;
		}
		else if (e instanceof HttpHostConnectException) {
			syncAccount.setUiEvent(SyncAccount.UI_EVENT_CONNECTION_EXCEPTION);
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

		syncAccount.setState(SyncAccount.STATE_DISCONNECTED);

		SyncAccountService.update(syncAccount);

		retryServerConnection();
	}

	@Override
	public Void handleResponse(HttpResponse httpResponse) {
		try {
			StatusLine statusLine = httpResponse.getStatusLine();

			if (statusLine.getStatusCode() != HttpServletResponse.SC_OK) {
				_logger.error("Status code {}", statusLine.getStatusCode());

				throw new HttpResponseException(
					statusLine.getStatusCode(), statusLine.getReasonPhrase());
			}

			doHandleResponse(httpResponse);
		}
		catch (Exception e) {
			handleException(e);
		}

		return null;
	}

	protected void doHandleResponse(HttpResponse httpResponse)
		throws Exception {
	}

	protected Object getParameterValue(String key) {
		return _event.getParameterValue(key);
	}

	protected long getSyncAccountId() {
		return _event.getSyncAccountId();
	}

	protected void retryServerConnection() {
		RetryTemplate retryTemplate = new RetryTemplate();

		retryTemplate.setBackOffPolicy(new ExponentialBackOffPolicy());

		SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();

		simpleRetryPolicy.setMaxAttempts(Integer.MAX_VALUE);

		retryTemplate.setRetryPolicy(simpleRetryPolicy);

		RetryCallback<Object> retryCallback = new RetryCallback<Object>() {

			@Override
			public Object doWithRetry(RetryContext retryContext)
				throws Exception {

				SyncAccount syncAccount = SyncAccountService.fetchSyncAccount(
					getSyncAccountId());

				if (_logger.isDebugEnabled()) {
					_logger.debug(
						"Attempting to reconnect to {}. Retry #{}.",
						syncAccount.getUrl(), retryContext.getRetryCount() + 1);
				}

				SyncAccountService.synchronizeSyncAccount(getSyncAccountId());

				syncAccount = SyncAccountService.synchronizeSyncAccount(
						getSyncAccountId());

				if (syncAccount.getState() == SyncAccount.STATE_DISCONNECTED) {
					throw new Exception();
				}

				return null;
			}

		};

		try {
			retryTemplate.execute(retryCallback);
		}
		catch (Exception e) {
		}
	}

	private static Logger _logger = LoggerFactory.getLogger(BaseHandler.class);

	private Event _event;

}