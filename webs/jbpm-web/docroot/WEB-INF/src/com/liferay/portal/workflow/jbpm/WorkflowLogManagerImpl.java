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

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;

/**
 * <a href="WorkflowLogManagerImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class WorkflowLogManagerImpl implements WorkflowLogManager {

	public List<WorkflowLog> getWorkflowLogs(
			long workflowTaskId, int start,	int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			Session session = jbpmContext.getSession();

			Criteria criteria = session.createCriteria(WorkflowLogImpl.class);

			criteria.add(
				Restrictions.eq("taskInstance.id", workflowTaskId));

			List<WorkflowLog> workflowLogs = criteria.list();

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

	public int getWorkflowLogCount(long workflowTaskId)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			Session session = jbpmContext.getSession();

			Criteria criteria = session.createCriteria(WorkflowLogImpl.class);

			criteria.add(
				Restrictions.eq("taskInstance.id", workflowTaskId));

			criteria.setProjection(Projections.rowCount());

			Iterator<Integer> itr = criteria.list().iterator();

			int count = 0;

			if (itr.hasNext()) {
				count = itr.next();
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