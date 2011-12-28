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

package com.liferay.sampledao.model;

/**
 * @author Brian Wing Shun Chan
 */
public class FoodItem {

	public long getFoodItemId() {
		return _foodItemId;
	}

	public void setFoodItemId(long foodItemId) {
		_foodItemId = foodItemId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public int getPoints() {
		return _points;
	}

	public void setPoints(int points) {
		_points = points;
	}

	private long _foodItemId;
	private String _name;
	private int _points;

}