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

package com.liferay.sampletapestry.bean;

import java.io.Serializable;

/**
 * @author Joseph Shum
 */
public class Book implements Serializable {

	public Book() {
	}

	public String getAuthor() {
		return _author;
	}

	public void setAuthor(String author) {
		_author = author;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description= description;
	}

	public boolean isRecommended() {
		return _recommended;
	}

	public void setRecommended(boolean recommended) {
		_recommended = recommended;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public int getYear() {
		return _year;
	}

	public void setYear(int year) {
		_year = year;
	}

	private String _author;
	private String _description;
	private boolean _recommended;
	private String _title;
	private int _year;

}