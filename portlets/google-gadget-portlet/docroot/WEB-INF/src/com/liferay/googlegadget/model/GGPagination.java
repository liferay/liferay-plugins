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
 * @author Alberto Montero
 */
public class GGPagination {

	public GGPagination(int prevStart, int moreStart) {
		_prevStart = prevStart;
		_moreStart = moreStart;
	}

	public int getMoreStart() {
		return _moreStart;
	}

	public int getPrevStart() {
		return _prevStart;
	}

	public void setMoreStart(int moreStart) {
		_moreStart = moreStart;
	}

	public void setPrevStart(int prevStart) {
		_prevStart = prevStart;
	}

	private int _moreStart;
	private int _prevStart;

}