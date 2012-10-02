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

package com.liferay.portal.kernel.webdav;

import com.liferay.portal.model.Lock;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class WebDAVStorageWrapper implements WebDAVStorage {

	public WebDAVStorageWrapper(WebDAVStorage webDAVStorage) {
		_webDAVStorage = webDAVStorage;
	}

	public int copyCollectionResource(
			WebDAVRequest webDavRequest, Resource resource, String destination,
			boolean overwrite, long depth)
		throws WebDAVException {

		return _webDAVStorage.copyCollectionResource(
			webDavRequest, resource, destination, overwrite, depth);
	}

	public int copySimpleResource(
			WebDAVRequest webDavRequest, Resource resource, String destination,
			boolean overwrite)
		throws WebDAVException {

		return _webDAVStorage.copySimpleResource(
			webDavRequest, resource, destination, overwrite);
	}

	public int deleteResource(WebDAVRequest webDavRequest)
		throws WebDAVException {

		return _webDAVStorage.deleteResource(webDavRequest);
	}

	public Resource getResource(WebDAVRequest webDavRequest)
		throws WebDAVException {

		return _webDAVStorage.getResource(webDavRequest);
	}

	public List<Resource> getResources(WebDAVRequest webDavRequest)
		throws WebDAVException {

		return _webDAVStorage.getResources(webDavRequest);
	}

	public String getRootPath() {
		return _webDAVStorage.getRootPath();
	}

	public String getToken() {
		return _webDAVStorage.getToken();
	}

	public WebDAVStorage getWrappedWebDAVStorage() {
		return _webDAVStorage;
	}

	public boolean isAvailable(WebDAVRequest webDavRequest)
		throws WebDAVException {

		return _webDAVStorage.isAvailable(webDavRequest);
	}

	public boolean isSupportsClassTwo() {
		return _webDAVStorage.isSupportsClassTwo();
	}

	public Status lockResource(
			WebDAVRequest webDavRequest, String owner, long timeout)
		throws WebDAVException {

		return _webDAVStorage.lockResource(webDavRequest, owner, timeout);
	}

	public Status makeCollection(WebDAVRequest webDavRequest)
		throws WebDAVException {

		return _webDAVStorage.makeCollection(webDavRequest);
	}

	public int moveCollectionResource(
			WebDAVRequest webDavRequest, Resource resource, String destination,
			boolean overwrite)
		throws WebDAVException {

		return _webDAVStorage.moveCollectionResource(
			webDavRequest, resource, destination, overwrite);
	}

	public int moveSimpleResource(
			WebDAVRequest webDavRequest, Resource resource, String destination,
			boolean overwrite)
		throws WebDAVException {

		return _webDAVStorage.moveSimpleResource(
			webDavRequest, resource, destination, overwrite);
	}

	public int putResource(WebDAVRequest webDavRequest) throws WebDAVException {
		return _webDAVStorage.putResource(webDavRequest);
	}

	public Lock refreshResourceLock(
			WebDAVRequest webDavRequest, String uuid, long timeout)
		throws WebDAVException {

		return _webDAVStorage.refreshResourceLock(webDavRequest, uuid, timeout);
	}

	public void setRootPath(String rootPath) {
		_webDAVStorage.setRootPath(rootPath);
	}

	public void setToken(String token) {
		_webDAVStorage.setToken(token);
	}

	public boolean unlockResource(WebDAVRequest webDavRequest, String token)
		throws WebDAVException {

		return _webDAVStorage.unlockResource(webDavRequest, token);
	}

	private WebDAVStorage _webDAVStorage;

}