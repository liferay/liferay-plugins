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

import com.liferay.knowledgebase.model.KBStructureField;
import com.liferay.knowledgebase.model.KBStructureFieldConstants;
import com.liferay.knowledgebase.model.KBStructureOption;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Peter Shin
 */
public class KBStructureFieldImpl implements KBStructureField {

	public KBStructureFieldImpl() {
	}

	public String getKbStructureFieldId() {
		return _kbStructureFieldId;
	}

	public List<KBStructureOption> getKbStructureOptions() {
		return _kbStructureOptions;
	}

	public String getLabel() {
		return _label;
	}

	public String getName() {
		return _name;
	}

	public String getType() {
		return _type;
	}

	public void setKbStructureFieldId(String kbStructureFieldId) {
		_kbStructureFieldId = kbStructureFieldId;
	}

	public void setKbStructureOptions(
		List<KBStructureOption> kbStructureOptions) {

		_kbStructureOptions = kbStructureOptions;
	}

	public void setLabel(String label) {
		_label = label;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setType(String type) {
		_type = type;
	}

	private String _kbStructureFieldId;
	private List<KBStructureOption> _kbStructureOptions =
		new ArrayList<KBStructureOption>();
	private String _label;
	private String _name;
	private String _type = KBStructureFieldConstants.DEFAULT_TYPE;

}