/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portal.workflow.edoras.dao.model;

import com.liferay.portal.workflow.edoras.model.WorkflowInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.edorasframework.process.api.entity.ProcessInstance;
import org.edorasframework.process.api.ex.ProcessException;

/**
 * <a href="WorkflowEntityBridgeUtil.java.html"><b><i>View Source</i></b></a>
 *
 *
 * @author Micha Kiener
 */
public class WorkflowEntityBridgeUtil {

	public static Class<?> getSetupClassForName(String setupId) {
		Class<?> setupClass = _setupClassMap.get(setupId);

		if (setupClass != null) {
			return setupClass;
		}

		try {
			ClassLoader classLoader =
				WorkflowEntityBridgeUtil.class.getClassLoader();

			setupClass = classLoader.loadClass(setupId);

			_setupClassMap.put(setupId, setupClass);
		}
		catch (ClassNotFoundException cnfe) {
			throw new ProcessException(
				"Could not load setup id class " + setupId, cnfe);
		}

		return setupClass;
	}

	public static List<? extends ProcessInstance> transferLoadedObjects(
		List<WorkflowInstance> workflowInstances,
		WorkflowInstanceBridge workflowInstanceBridge, boolean loadChildren) {

		List<ProcessInstance> processInstances =
			new ArrayList<ProcessInstance>();

		if (workflowInstances == null) {
			return processInstances;
		}

		for (WorkflowInstance workflowInstance : workflowInstances) {
			processInstances.add(
				new WorkflowInstanceBridge(
					workflowInstance, workflowInstanceBridge, loadChildren));
		}

		return processInstances;
	}

	private static final Map<String, Class<?>> _setupClassMap =
		new HashMap<String, Class<?>>();

}