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

package com.liferay.jbpm.handler;

import com.liferay.client.portal.model.UserSoap;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.taskmgmt.def.Swimlane;
import org.jbpm.taskmgmt.def.TaskMgmtDefinition;
import org.jbpm.taskmgmt.exe.SwimlaneInstance;
import org.jbpm.taskmgmt.exe.TaskMgmtInstance;

/**
 * <a href="NotifyRequestorActionHandler.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Charles May
 *
 */
public class NotifyRequestorActionHandler
	extends DefaultHandler implements ActionHandler {

	public void execute(ExecutionContext executionContext) {
		ProcessDefinition definition = executionContext.getProcessDefinition();

		TaskMgmtDefinition taskMgmtDefinition =
			definition.getTaskMgmtDefinition();
		TaskMgmtInstance taskMgmtInstance =
			executionContext.getTaskMgmtInstance();

		Swimlane requestorSwimlane =
			taskMgmtDefinition.getSwimlane("requestor");
		SwimlaneInstance requestorSwimlaneInstance =
			taskMgmtInstance.getInitializedSwimlaneInstance(
				executionContext, requestorSwimlane);

		String userId = requestorSwimlaneInstance.getActorId();

		String displayMsg = null;

		try {
			UserSoap user = getUserService().getUserById(
				GetterUtil.getLong(userId));

			displayMsg = StringUtil.replace(
				msg, "${id}", user.getEmailAddress());
		}
		catch (Exception e) {
			displayMsg = StringUtil.replace(msg, "${id}", userId);
		}

		if (_log.isInfoEnabled()) {
			_log.info(displayMsg);
		}

		executionContext.leaveNode();
	}

	private static Log _log =
		LogFactoryUtil.getLog(NotifyRequestorActionHandler.class);

}