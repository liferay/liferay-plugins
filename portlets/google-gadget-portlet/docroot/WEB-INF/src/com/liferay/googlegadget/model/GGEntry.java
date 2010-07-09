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

package com.liferay.googlegadget.model;

/**
 * @author Brian Wing Shun Chan
 *
 */
public class GGEntry {

	public GGEntry(String gadgetId, String name, String image) {
		_gadgetId = gadgetId;
		_name = name;
		_image = image;
	}

	public String getGadgetId() {
		return _gadgetId;
	}

	public void setGadgetId(String gadgetId) {
		_gadgetId = gadgetId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getImage() {
		return _image;
	}

	public void setImage(String image) {
		_image = image;
	}

	private String _gadgetId;
	private String _name;
	private String _image;

}