/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.mail;

import java.io.File;

/**
 * <a href="MailFile.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 *
 */
public class MailFile {

	public MailFile(String contentPath, String filename, long size) {
		this.contentPath = contentPath;
		this.filename = filename;
		this.size = size;
	}

	public MailFile(File file, String filename, long size) {
		this.file = file;
		this.filename = filename;
		this.size = size;
	}

	public String getContentPath() {
		return this.contentPath;
	}

	public File getFile() {
		return this.file;
	}

	public String getFilename() {
		return this.filename;
	}

	public long getSize() {
		return this.size;
	}

	protected String contentPath;
	protected File file;
	protected String filename;
	protected long size;

}