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
			long companyId, long workflowTaskId, int start,	int end,
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

	public int getWorkflowLogCount(long companyId, long workflowTaskId)
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