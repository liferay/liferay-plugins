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

package com.liferay.portal.workflow.kaleo.definition;

import com.liferay.portal.kernel.bean.BeanPropertiesUtil;

/**
 * @author Marcellus Tavares
 */
public class Attribute extends DefinitionNode {

	public Attribute() {
	}

	public Attribute(String name, String value) {
		_name = name;
		_value = value;
	}

	public void configureParent(DefinitionNode parentNode) {
		BeanPropertiesUtil.setProperty(parentNode, _name, _value);
	}

	public String getName() {
		return _name;
	}

	public String getValue() {
		return _value;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setValue(String value) {
		_value = value;
	}

	private String _name;
	private String _value;

}