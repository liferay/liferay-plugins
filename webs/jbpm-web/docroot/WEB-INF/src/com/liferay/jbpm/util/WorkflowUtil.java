/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

package com.liferay.jbpm.util;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;

import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;
import org.jbpm.taskmgmt.exe.TaskInstance;

/**
 * <a href="WorkflowUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Charles May
 *
 */
public class WorkflowUtil {

	public static void initDefinition(ProcessDefinition definition) {
		Hibernate.initialize(definition);
	}

	public static void initInstance(ProcessInstance instance) {
		Hibernate.initialize(instance.getProcessDefinition());
		Hibernate.initialize(instance.getRootToken());
		Hibernate.initialize(instance.getRootToken().getNode());
	}

	public static void initInstances(List instances) {
		Iterator itr = instances.iterator();

		while (itr.hasNext()) {
			ProcessInstance instance = (ProcessInstance)itr.next();

			initInstance(instance);
		}
	}

	public static void initTask(TaskInstance task) {
		ProcessInstance instance = task.getToken().getProcessInstance();

		Hibernate.initialize(task);
		Hibernate.initialize(instance);
		Hibernate.initialize(instance.getProcessDefinition());
	}

	public static void initTasks(List tasks) {
		Iterator itr = tasks.iterator();

		while (itr.hasNext()) {
			TaskInstance task = (TaskInstance)itr.next();

			initTask(task);
		}
	}

	public static void initToken(Token token) {
		Hibernate.initialize(token.getNode());
	}

	public static void initTokens(List tokens) {
		Iterator itr = tokens.iterator();

		while (itr.hasNext()) {
			Token token = (Token)itr.next();

			initToken(token);
		}
	}

}