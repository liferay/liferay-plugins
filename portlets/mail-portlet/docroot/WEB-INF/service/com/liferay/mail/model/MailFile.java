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

package com.liferay.mail.model;

import java.io.File;

/**
 * @author Scott Lee
 */
public class MailFile {

	public MailFile(String contentPath, String fileName, long size) {
		_contentPath = contentPath;
		_fileName = fileName;
		_size = size;
	}

	public MailFile(File file, String fileName, long size) {
		_file = file;
		_fileName = fileName;
		_size = size;
	}

	public void cleanUp() {
		if ((_file != null) && _file.exists()) {
			_file.delete();
		}
	}

	public String getContentPath() {
		return _contentPath;
	}

	public File getFile() {
		return _file;
	}

	public String getFileName() {
		return _fileName;
	}

	public long getSize() {
		return _size;
	}

	private String _contentPath;
	private File _file;
	private String _fileName;
	private long _size;

}