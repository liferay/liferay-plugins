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

package com.liferay.portal.workflow.jbpm.dao.comparator;

import java.util.Comparator;

import org.jbpm.graph.def.ProcessDefinition;

/**
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class ProcessDefinitionComparator
	implements Comparator<ProcessDefinition> {

	public ProcessDefinitionComparator(boolean asc) {
		_asc = asc;
	}

	public int compare(
		ProcessDefinition processDefinition1,
		ProcessDefinition processDefinition2) {

		String name1 = processDefinition1.getName();
		String name2 = processDefinition2.getName();

		int value = name1.compareTo(name2);

		if (value == 0) {
			Integer version1 = processDefinition1.getVersion();
			Integer version2 = processDefinition2.getVersion();

			value = version1.compareTo(version2);
		}

		if (_asc) {
			return value;
		}
		else {
			return -value;
		}
	}

	private boolean _asc;

}