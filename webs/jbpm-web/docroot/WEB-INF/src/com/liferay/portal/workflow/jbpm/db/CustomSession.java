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

package com.liferay.portal.workflow.jbpm.db;

import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import org.jbpm.JbpmContext;
import org.jbpm.JbpmException;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.taskmgmt.exe.TaskInstance;

/**
 * <a href="CustomSession.java.html"><b><i>View Source</i></b></a>
 *
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class CustomSession {

	public static String COUNT_LATEST_PROCESS_DEFINITIONS =
		CustomSession.class.getName() + ".countLatestProcessDefinitions";

	public static String COUNT_PROCESS_DEFINITIONS_BY_NAME =
		CustomSession.class.getName() + ".countProcessDefinitionsByName";

	public CustomSession(JbpmContext jbpmContext) {
		_session = jbpmContext.getSession();
	}

	public void close() {
		if (_session != null) {
			_session.close();
		}
	}

	public int countProcessDefinitions(String name, boolean latest) {
		try {
			Criteria criteria = _session.createCriteria(
				ProcessDefinition.class);

			if (latest) {
				criteria.setProjection(Projections.countDistinct("name"));
			}
			else {
				criteria.setProjection(Projections.rowCount());
			}

			if (name != null) {
				criteria.add(Restrictions.eq("name", name));
			}

			Number count = (Number)criteria.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw new JbpmException(e);
		}
	}

	public int countProcessInstances(
		long processDefinitionId, Boolean completed) {

		try {
			Criteria criteria = _session.createCriteria(
				ProcessInstance.class);

			criteria.setProjection(Projections.countDistinct("id"));

			criteria.add(
				Restrictions.eq("processDefinition.id", processDefinitionId));

			if (completed != null) {
				if (completed.booleanValue()) {
					criteria.add(Restrictions.isNotNull("end"));
				}
				else {
					criteria.add(Restrictions.isNull("end"));
				}
			}

			Number count = (Number)criteria.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw new JbpmException(e);
		}
	}

	public int countTaskInstances(
		long processInstanceId, long tokenId, String[] actorIds,
		boolean pooledActors, Boolean completed) {

		if ((actorIds != null) && (actorIds.length == 0)) {
			return 0;
		}

		try {
			Criteria criteria = _session.createCriteria(TaskInstance.class);

			criteria.setProjection(Projections.countDistinct("id"));

			if (processInstanceId > 0) {
				criteria.add(
					Restrictions.eq("processInstance.id", processInstanceId));
			}
			else if (tokenId > 0) {
				criteria.add(Restrictions.eq("token.id", tokenId));
			}
			else if (actorIds != null) {
				if (pooledActors) {
					criteria.createCriteria("pooledActors");
				}

				criteria.add(Restrictions.in("actorId", actorIds));
			}

			if (completed != null) {
				if (completed.booleanValue()) {
					criteria.add(Restrictions.isNotNull("end"));
				}
				else {
					criteria.add(Restrictions.isNull("end"));
				}
			}

			Number count = (Number)criteria.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw new JbpmException(e);
		}
	}

	public List<ProcessDefinition> findProcessDefinitions(
		String name, boolean latest, int start, int end,
		OrderByComparator orderByComparator) {

		try {
			Criteria criteria = _session.createCriteria(
				ProcessDefinition.class);

			if (latest) {
				criteria.setProjection(
					Projections.distinct(Projections.groupProperty("name")));

				Order order = Order.desc("version");

				criteria.addOrder(order);
			}

			if (name != null) {
				criteria.add(Restrictions.eq("name", name));
			}

			addPagination(criteria, start, end);
			addOrder(criteria, orderByComparator);

			if (latest) {
				List<String> names = criteria.list();

				return findProcessDefinitions(names);
			}
			else {
				return criteria.list();
			}
		}
		catch (Exception e) {
			throw new JbpmException(e);
		}
	}

	public List<ProcessInstance> findProcessInstances(
		long processDefinitionId, Boolean completed, int start, int end,
		OrderByComparator orderByComparator) {

		try {
			Criteria criteria = _session.createCriteria(
				ProcessInstance.class);

			criteria.add(
				Restrictions.eq("processDefinition.id", processDefinitionId));

			if (completed != null) {
				if (completed.booleanValue()) {
					criteria.add(Restrictions.isNotNull("end"));
				}
				else {
					criteria.add(Restrictions.isNull("end"));
				}
			}

			addPagination(criteria, start, end);
			addOrder(criteria, orderByComparator);

			return criteria.list();
		}
		catch (Exception e) {
			throw new JbpmException(e);
		}
	}

	public List<TaskInstance> findTaskInstances(
		long processInstanceId, long tokenId, String[] actorIds,
		boolean pooledActors, Boolean completed, int start, int end,
		OrderByComparator orderByComparator) {

		if ((actorIds != null) && (actorIds.length == 0)) {
			return Collections.EMPTY_LIST;
		}

		try {
			Criteria criteria = _session.createCriteria(TaskInstance.class);

			if (processInstanceId > 0) {
				criteria.add(
					Restrictions.eq("processInstance.id", processInstanceId));
			}
			else if (tokenId > 0) {
				criteria.add(Restrictions.eq("token.id", tokenId));
			}
			else if (actorIds != null) {
				if (pooledActors) {
					criteria.createCriteria("pooledActors");
				}

				criteria.add(Restrictions.in("actorId", actorIds));
			}

			if (completed != null) {
				if (completed.booleanValue()) {
					criteria.add(Restrictions.isNotNull("end"));
				}
				else {
					criteria.add(Restrictions.isNull("end"));
				}
			}

			addPagination(criteria, start, end);
			addOrder(criteria, orderByComparator);

			return criteria.list();
		}
		catch (Exception e) {
			throw new JbpmException(e);
		}
	}

	protected void addOrder(
		Criteria criteria, OrderByComparator orderByComparator) {

		if (orderByComparator == null) {
			return;
		}

		String[] orderByFields = orderByComparator.getOrderByFields();

		for (String orderByField : orderByFields) {
			Order order = null;

			String jbpmField = _fieldMap.get(orderByField);

			if (jbpmField == null) {
				jbpmField = orderByField;
			}

			if (orderByComparator.isAscending()) {
				order = Order.asc(jbpmField);
			}
			else {
				order = Order.desc(jbpmField);
			}

			criteria.addOrder(order);
		}
	}

	protected void addPagination(Criteria criteria, int start, int end) {
		criteria.setFirstResult(start);
		criteria.setMaxResults(end - start);
	}

	protected ProcessDefinition findProcessDefinition(String name) {
		try {
			Criteria criteria = _session.createCriteria(
				ProcessDefinition.class);

			criteria.add(Restrictions.eq("name", name));

			Order order = Order.desc("version");

			criteria.addOrder(order);

			criteria.setFirstResult(0);
			criteria.setMaxResults(1);

			return (ProcessDefinition)criteria.uniqueResult();
		}
		catch (Exception e) {
			throw new JbpmException(e);
		}
	}

	protected List<ProcessDefinition> findProcessDefinitions(
		List<String> names) {

		try {
			List<ProcessDefinition> processDefinitions =
				new ArrayList<ProcessDefinition>();

			for (String name : names) {
				ProcessDefinition processDefinition = findProcessDefinition(
					name);

				processDefinitions.add(processDefinition);
			}

			return processDefinitions;
		}
		catch (Exception e) {
			throw new JbpmException(e);
		}
	}

	private static Map<String, String> _fieldMap =
		new HashMap<String, String>();

	static {
		_fieldMap.put("startDate", "start");
		_fieldMap.put("completionDate", "end");
		_fieldMap.put("endDate", "end");
		_fieldMap.put("createDate", "create");
		_fieldMap.put("taskName", "name");
		_fieldMap.put("userId", "actorId");
	}

	private Session _session;

}