/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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
public interface KBTemplateSearchDisplay extends Serializable {

	public int[] getCurStartValues();

	public List<KBTemplate> getResults();

	public int getTotal();

	public void setCurStartValues(int[] curStartValues);

	public void setResults(List<KBTemplate> results);

	public void setTotal(int total);

}