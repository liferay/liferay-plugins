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

package com.liferay.documentlibrary.client.remote.handler;

import com.liferay.documentlibrary.client.data.FileData;
import com.liferay.documentlibrary.client.remote.listener.FileContentListener;

import com.ning.http.client.Response;
import com.ning.http.client.resumable.ResumableAsyncHandler;

/**
 * @author Gail Hernandez
 */
public class FileContentHandler extends ResumableAsyncHandler<Response> {

	public FileContentHandler(FileData fileData) {
		setResumableListener(new FileContentListener(fileData));
	}

}