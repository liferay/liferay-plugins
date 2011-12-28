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

package com.liferay.randombibleverse.model;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 */
public class Verse implements Serializable {

	public Verse() {
	}

	public Verse(String location, String text) {
		_location = location;
		_text = text;
	}

	public String getLocation() {
		return _location;
	}

	public void setLocation(String location) {
		_location = location;
	}

	public String getText() {
		return _text;
	}

	public void setText(String text) {
		_text = text;
	}

	private String _location;
	private String _text;

}