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
			WebDAVRequest webDAVRequest, Resource resource, String destination,
			boolean overwrite, long depth)
		throws WebDAVException {

		return _webDAVStorage.copyCollectionResource(
			webDAVRequest, resource, destination, overwrite, depth);
	}

	public int copySimpleResource(
			WebDAVRequest webDAVRequest, Resource resource, String destination,
			boolean overwrite)
		throws WebDAVException {

		return _webDAVStorage.copySimpleResource(
			webDAVRequest, resource, destination, overwrite);
	}

	public int deleteResource(WebDAVRequest webDAVRequest)
		throws WebDAVException {

		return _webDAVStorage.deleteResource(webDAVRequest);
	}

	public Resource getResource(WebDAVRequest webDAVRequest)
		throws WebDAVException {

		return _webDAVStorage.getResource(webDAVRequest);
	}

	public List<Resource> getResources(WebDAVRequest webDAVRequest)
		throws WebDAVException {

		return _webDAVStorage.getResources(webDAVRequest);
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

	public boolean isAvailable(WebDAVRequest webDAVRequest)
		throws WebDAVException {

		return _webDAVStorage.isAvailable(webDAVRequest);
	}

	public boolean isSupportsClassTwo() {
		return _webDAVStorage.isSupportsClassTwo();
	}

	public Status lockResource(
			WebDAVRequest webDAVRequest, String owner, long timeout)
		throws WebDAVException {

		return _webDAVStorage.lockResource(webDAVRequest, owner, timeout);
	}

	public Status makeCollection(WebDAVRequest webDAVRequest)
		throws WebDAVException {

		return _webDAVStorage.makeCollection(webDAVRequest);
	}

	public int moveCollectionResource(
			WebDAVRequest webDAVRequest, Resource resource, String destination,
			boolean overwrite)
		throws WebDAVException {

		return _webDAVStorage.moveCollectionResource(
			webDAVRequest, resource, destination, overwrite);
	}

	public int moveSimpleResource(
			WebDAVRequest webDAVRequest, Resource resource, String destination,
			boolean overwrite)
		throws WebDAVException {

		return _webDAVStorage.moveSimpleResource(
			webDAVRequest, resource, destination, overwrite);
	}

	public int putResource(WebDAVRequest webDAVRequest) throws WebDAVException {
		return _webDAVStorage.putResource(webDAVRequest);
	}

	public Lock refreshResourceLock(
			WebDAVRequest webDAVRequest, String uuid, long timeout)
		throws WebDAVException {

		return _webDAVStorage.refreshResourceLock(webDAVRequest, uuid, timeout);
	}

	public void setRootPath(String rootPath) {
		_webDAVStorage.setRootPath(rootPath);
	}

	public void setToken(String token) {
		_webDAVStorage.setToken(token);
	}

	public boolean unlockResource(WebDAVRequest webDAVRequest, String token)
		throws WebDAVException {

		return _webDAVStorage.unlockResource(webDAVRequest, token);
	}

	private WebDAVStorage _webDAVStorage;

}