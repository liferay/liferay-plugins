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

package com.liferay.portal.workflow.jbpm;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.workflow.WorkflowLog;
import com.liferay.portal.kernel.workflow.WorkflowLogManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.db.LoggingSession;
import org.jbpm.graph.exe.Token;
import org.jbpm.logging.log.ProcessLog;

/**
 * <a href="WorkflowLogManagerImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class WorkflowLogManagerImpl implements WorkflowLogManager {

	public List<WorkflowLog> getWorkflowLogs(
			long workflowInstanceId, boolean includeChildren, int start,
			int end, OrderByComparator orderByComparator)
		throws WorkflowException {

		List<WorkflowLog> workflowLogs = new ArrayList<WorkflowLog>();

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			LoggingSession loggingSession = jbpmContext.getLoggingSession();

			Token token = jbpmContext.loadToken(workflowInstanceId);

			List<ProcessLog> processLogs = loggingSession.findLogsByToken(
				token);

			for (ProcessLog processLog : processLogs) {
				workflowLogs.add(new WorkflowLogImpl(processLog));
			}

			if (includeChildren) {
				Stack<Token> tokens = new Stack<Token>();

				tokens.addAll(token.getChildren().values());

				while (!tokens.isEmpty()) {
					Token childToken = tokens.pop();

					processLogs = loggingSession.findLogsByToken(childToken);

					for (ProcessLog processLog : processLogs) {
						workflowLogs.add(new WorkflowLogImpl(processLog));
					}

					tokens.addAll(childToken.getChildren().values());
				}
			}

			Collections.sort(workflowLogs, orderByComparator);

			if ((start != QueryUtil.ALL_POS) && (end != QueryUtil.ALL_POS)) {
				workflowLogs = ListUtil.subList(workflowLogs, start, end);
			}

			return workflowLogs;
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	public int getWorkflowLogCount(
			long workflowInstanceId, boolean includeChildren)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			LoggingSession loggingSession = jbpmContext.getLoggingSession();

			Token token = jbpmContext.loadToken(workflowInstanceId);

			List<ProcessLog> processLogs = loggingSession.findLogsByToken(
				token);

			int count = processLogs.size();

			if (includeChildren) {
				Stack<Token> tokenStack = new Stack<Token>();

				tokenStack.addAll(token.getChildren().values());

				while (tokenStack.isEmpty() == false) {
					Token childToken = tokenStack.pop();

					processLogs = loggingSession.findLogsByToken(childToken);

					count += processLogs.size();

					tokenStack.addAll(childToken.getChildren().values());
				}
			}

			return count;
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	public void setJbpmConfiguration(JbpmConfiguration jbpmConfiguration) {
		_jbpmConfiguration = jbpmConfiguration;
	}

	private JbpmConfiguration _jbpmConfiguration;

}