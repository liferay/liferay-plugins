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

package com.liferay.samplestruts.model;

import java.io.File;
import java.io.Serializable;

/**
 * @author Scott Lee
 */
public class Book implements Serializable {

	public Book() {
		super();
	}

	public Book(String isbn, String title) {
		_isbn = isbn;
		_title = title;
	}

	public String getIsbn() {
		return _isbn;
	}

	public void setIsbn(String isbn) {
		_isbn = isbn;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public File getCover() {
		return _cover;
	}

	public void setCover(File cover) {
		_cover = cover;
	}

	private String _isbn;
	private String _title;
	private File _cover;

}