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

package com.liferay.knowledgebase.model.impl;

import com.liferay.knowledgebase.model.KBStructureOption;

/**
 * @author Peter Shin
 */
public class KBStructureOptionImpl implements KBStructureOption {

	public KBStructureOptionImpl() {
	}

	public String getKbStructureOptionId() {
		return _kbStructureOptionId;
	}

	public String getLabel() {
		return _label;
	}

	public String getValue() {
		return _value;
	}

	public void setKbStructureOptionId(String kbStructureOptionId) {
		_kbStructureOptionId = kbStructureOptionId;
	}

	public void setLabel(String label) {
		_label = label;
	}

	public void setValue(String value) {
		_value = value;
	}

	private String _kbStructureOptionId;
	private String _label;
	private String _value;

}