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

package com.liferay.knowledgebase.model;

import java.io.Serializable;

import java.util.List;

/**
 * @author Peter Shin
 */
public interface KBStructureField extends Serializable {

	public String getKbStructureFieldId();

	public List<KBStructureOption> getKbStructureOptions();

	public String getLabel();

	public String getName();

	public String getType();

	public void setKbStructureFieldId(String kbStructureFieldId);

	public void setKbStructureOptions(
		List<KBStructureOption> kbStructureOptions);

	public void setLabel(String label);

	public void setName(String name);

	public void setType(String type);

}