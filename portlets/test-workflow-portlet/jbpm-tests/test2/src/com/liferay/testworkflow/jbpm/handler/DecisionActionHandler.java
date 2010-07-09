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

package com.liferay.testworkflow.jbpm.handler;

import org.jbpm.context.exe.ContextInstance;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.node.DecisionHandler;

/**
 * @author Shuyang Zhou
 */
public class DecisionActionHandler implements DecisionHandler {

	public String decide(ExecutionContext executionContext) throws Exception {
		ContextInstance contextInstance = executionContext.getContextInstance();

		String branchName = (String)contextInstance.getVariable(key);

		if (branchName != null) {
			return branchName;
		}
		else {
			return defaultValue;
		}
	}

	protected String defaultValue = "default";
	protected String key;

}