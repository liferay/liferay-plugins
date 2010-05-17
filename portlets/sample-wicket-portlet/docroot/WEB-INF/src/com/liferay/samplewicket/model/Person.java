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

package com.liferay.samplewicket.model;

import java.io.Serializable;

/**
 * <a href="Person.java.html"><b><i>View Source</i></b></a>
 *
 * @author Thiago Moreira
 */
public class Person implements Serializable {

	protected String name;
	protected Character gender = new Character('M');

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character sex) {
		this.gender = sex;
	}

}