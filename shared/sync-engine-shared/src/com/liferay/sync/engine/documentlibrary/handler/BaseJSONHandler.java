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

package com.liferay.sync.engine.documentlibrary.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.sync.engine.documentlibrary.event.Event;
import com.liferay.sync.engine.model.SyncAccount;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.service.SyncAccountService;
import com.liferay.sync.engine.service.SyncFileService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.util.EntityUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class BaseJSONHandler extends BaseHandler {

	public BaseJSONHandler(Event event) {
		super(event);
	}

	@Override
	public Void handleResponse(HttpResponse httpResponse) {
		try {
			StatusLine statusLine = httpResponse.getStatusLine();

			if (statusLine.getStatusCode() != HttpStatus.SC_OK) {
				String response = getResponseString(httpResponse);

				if (handlePortalException(getException(response))) {
					return null;
				}

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

	@Override
	protected void doHandleResponse(HttpResponse httpResponse)
		throws Exception {

		String response = getResponseString(httpResponse);

		if (handlePortalException(getException(response))) {
			return;
		}

		if (_logger.isTraceEnabled()) {
			_logger.trace("Handling response {}", response);
		}

		processResponse(response);
	}

	protected String getException(String response) {
		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode responseJsonNode = null;

		try {
			response = StringEscapeUtils.unescapeJava(response);

			responseJsonNode = objectMapper.readTree(response);
		}
		catch (Exception e) {
			return "";
		}

		JsonNode errorJsonNode = responseJsonNode.get("error");

		if (errorJsonNode == null) {
			JsonNode exceptionJsonNode = responseJsonNode.get("exception");

			if (exceptionJsonNode == null) {
				return "";
			}

			return exceptionJsonNode.asText();
		}

		JsonNode typeJsonNode = errorJsonNode.get("type");

		return typeJsonNode.asText();
	}

	protected String getResponseString(HttpResponse httpResponse)
		throws Exception {

		HttpEntity httpEntity = httpResponse.getEntity();

		return EntityUtils.toString(httpEntity);
	}

	protected boolean handlePortalException(String exception) throws Exception {
		if (exception.equals("")) {
			return false;
		}

		if (_logger.isDebugEnabled()) {
			_logger.debug("Handling exception {}", exception);
		}

		if (exception.equals("com.liferay.portal.DuplicateLockException")) {
			SyncFile syncFile = (SyncFile)getParameterValue("syncFile");

			syncFile.setState(SyncFile.STATE_ERROR);
			syncFile.setUiEvent(SyncFile.UI_EVENT_DUPLICATE_LOCK);

			SyncFileService.update(syncFile);
		}
		else if (exception.equals(
					"com.liferay.portal.kernel.upload.UploadException") ||
				 exception.contains("SizeLimitExceededException")) {

			SyncFile syncFile = (SyncFile)getParameterValue("syncFile");

			syncFile.setState(SyncFile.STATE_ERROR);
			syncFile.setUiEvent(SyncFile.UI_EVENT_EXCEEDED_SIZE_LIMIT);

			SyncFileService.update(syncFile);
		}
		else if (exception.equals(
					"com.liferay.portal.security.auth.PrincipalException")) {

			SyncFile syncFile = (SyncFile)getParameterValue("syncFile");

			syncFile.setState(SyncFile.STATE_ERROR);
			syncFile.setUiEvent(SyncFile.UI_EVENT_INVALID_PERMISSIONS);

			SyncFileService.update(syncFile);
		}
		else if (exception.equals(
					"com.liferay.portlet.documentlibrary.FileNameException")) {

			SyncFile syncFile = (SyncFile)getParameterValue("syncFile");

			syncFile.setState(SyncFile.STATE_ERROR);
			syncFile.setUiEvent(SyncFile.UI_EVENT_INVALID_FILE_NAME);

			SyncFileService.update(syncFile);
		}
		else if (exception.equals(
					"com.liferay.portlet.documentlibrary." +
						"NoSuchFileEntryException")) {

			SyncFile syncFile = (SyncFile)getParameterValue("syncFile");

			Path filePath = Paths.get(syncFile.getFilePathName());

			Files.deleteIfExists(filePath);

			SyncFileService.deleteSyncFile(syncFile);
		}
		else if (exception.equals(
					"com.liferay.sync.SyncServicesUnavailableException")) {

			SyncAccount syncAccount = SyncAccountService.fetchSyncAccount(
				getSyncAccountId());

			syncAccount.setState(SyncAccount.STATE_DISCONNECTED);
			syncAccount.setUiEvent(
				SyncAccount.UI_EVENT_SYNC_SERVICES_NOT_ACTIVE);

			SyncAccountService.update(syncAccount);

			retryServerConnection();
		}
		else if (exception.equals(
					"com.liferay.portal.kernel.jsonwebservice." +
						"NoSuchJSONWebServiceException") ||
				 exception.equals("java.lang.RuntimeException")) {

			SyncAccount syncAccount = SyncAccountService.fetchSyncAccount(
				getSyncAccountId());

			syncAccount.setState(SyncAccount.STATE_DISCONNECTED);
			syncAccount.setUiEvent(SyncAccount.UI_EVENT_SYNC_WEB_MISSING);

			SyncAccountService.update(syncAccount);

			retryServerConnection();
		}
		else if (exception.equals("java.lang.SecurityException")) {
			throw new HttpResponseException(
				HttpStatus.SC_UNAUTHORIZED, "Authenticated access required");
		}

		return true;
	}

	protected void processResponse(String response) throws Exception {
	}

	private static Logger _logger = LoggerFactory.getLogger(
		BaseJSONHandler.class);

}