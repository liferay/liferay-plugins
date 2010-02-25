/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.jbpm.handler;

import com.liferay.portal.kernel.workflow.ContextConstants;
import com.liferay.portal.kernel.workflow.StatusConstants;
import com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.taskmgmt.exe.TaskInstance;

/**
 * <a href="WorkflowStatusActionHandler.java.html"><b><i>View Source</i></b></a>
 *
 * @author Bruno Farache
 */
public class WorkflowStatusActionHandler implements ActionHandler {

	public void execute(ExecutionContext executionContext) throws Exception {
		TaskInstance taskInstance = executionContext.getTaskInstance();

		if ((userId == 0) && (taskInstance != null)) {
			userId = Long.parseLong(taskInstance.getActorId());
		}

		long companyId = (Long)executionContext.getVariable(
			ContextConstants.COMPANY_ID);
		long groupId = (Long)executionContext.getVariable(
			ContextConstants.GROUP_ID);
		String className = (String)executionContext.getVariable(
			ContextConstants.ENTRY_CLASS_NAME);
		long classPK = (Long)executionContext.getVariable(
			ContextConstants.ENTRY_CLASS_PK);

		WorkflowStatusManagerUtil.updateStatus(
			companyId, groupId, userId, className, classPK,
			StatusConstants.fromLabel(status));
	}

	private String status;
	private long userId;

}