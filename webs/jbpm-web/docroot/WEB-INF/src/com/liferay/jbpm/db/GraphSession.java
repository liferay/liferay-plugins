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

package com.liferay.jbpm.db;

import com.liferay.jbpm.util.WorkflowUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.dao.hibernate.QueryPos;
import com.liferay.util.dao.hibernate.QueryUtil;

import java.sql.Timestamp;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.SessionFactoryImplementor;

import org.jbpm.JbpmContext;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.taskmgmt.exe.TaskInstance;

/**
 * <a href="GraphSession.java.html"><b><i>View Source</i></b></a>
 *
 * @author Charles May
 *
 */
public class GraphSession extends org.jbpm.db.GraphSession {

	public static String COUNT_PROCESS_DEFINITIONS_BY_NAME =
		GraphSession.class.getName() + ".countProcessDefinitionsByName";

	public static String COUNT_PROCESS_INSTANCES_BY_SEARCH_TERMS =
		GraphSession.class.getName() + ".countProcessInstancesBySearchTerms";

	public static String COUNT_TASK_INSTANCES_BY_ACTOR =
		GraphSession.class.getName() + ".countTaskInstancesByActor";

	public static String COUNT_TASK_INSTANCES_BY_POOL =
		GraphSession.class.getName() + ".countTaskInstancesByPool";

	public static String FIND_PROCESS_DEFINITIONS_BY_NAME =
		GraphSession.class.getName() + ".findProcessDefinitionsByName";

	public static String FIND_PROCESS_INSTANCES_BY_SEARCH_TERMS =
		GraphSession.class.getName() + ".findProcessInstancesBySearchTerms";

	public static String FIND_TASK_INSTANCES_BY_ACTOR =
		GraphSession.class.getName() + ".findTaskInstancesByActor";

	public static String FIND_TASK_INSTANCES_BY_POOL =
		GraphSession.class.getName() + ".findTaskInstancesByPool";

	public GraphSession(String userId, String timeZoneId,
						JbpmContext jbpmContext) {

		super(jbpmContext.getSession());

		_userId = userId;
		_timeZoneId = timeZoneId;
		_jbpmContext = jbpmContext;
		_session = jbpmContext.getSession();

		if (_session != null) {
			SessionFactoryImplementor sessionFactory =
				(SessionFactoryImplementor)_session.getSessionFactory();

			_dialect = sessionFactory.getDialect();
		}
	}

	public void close() {
		if (_session != null) {
			_session.close();
		}
	}

	public int countProcessDefinitionsByName(String name) {
		try {
			String sql = CustomSQLUtil.get(COUNT_PROCESS_DEFINITIONS_BY_NAME);

			Query q = _session.createQuery(sql);

			q.setString("name", name);

			Iterator itr = q.list().iterator();

			if (itr.hasNext()) {
				Number count = (Number)itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RuntimeException(e.getMessage());
		}
	}

	public int countProcessInstancesBySearchTerms(
		String definitionName, String definitionVersion, String startDateGT,
		String startDateLT, String endDateGT, String endDateLT,
		boolean hideEndedTasks, String assignedUserId, boolean andOperator) {

		try {
			int definitionVersionInt = 0;

			if (!Validator.isNumber(definitionVersion)) {
				definitionVersion = null;
			}
			else {
				definitionVersionInt = GetterUtil.getInteger(definitionVersion);
			}

			String assignedUserIdInnerJoin = StringPool.BLANK;
			String assignedUserIdCheck = StringPool.BLANK;

			if (Validator.isNotNull(assignedUserId)) {
				assignedUserIdInnerJoin =
					"INNER JOIN JBPM_TOKEN ON JBPM_TOKEN.PROCESSINSTANCE_ = " +
						"JBPM_PROCESSINSTANCE.ID_ INNER JOIN " +
							"JBPM_TASKINSTANCE ON JBPM_TASKINSTANCE.TOKEN_ = " +
								"JBPM_TOKEN.ID_ ";
				assignedUserIdCheck = "(JBPM_TASKINSTANCE.ACTORID_ = ?) AND ";
			}

			String endDateCheck = "(JBPM_PROCESSINSTANCE.END_ IS NULL) ";

			if (!hideEndedTasks) {
				endDateCheck =
					"((JBPM_PROCESSINSTANCE.END_ >= ? [$AND_OR_NULL_CHECK$]) " +
						"AND (JBPM_PROCESSINSTANCE.END_ <= ? " +
							"[$AND_OR_NULL_CHECK$])) ";
			}

			String sql = CustomSQLUtil.get(
				COUNT_PROCESS_INSTANCES_BY_SEARCH_TERMS);

			sql = StringUtil.replace(
				sql, "[$ASSIGNED_USER_ID_INNER_JOIN$]",
				assignedUserIdInnerJoin);
			sql = StringUtil.replace(
				sql, "[$ASSIGNED_USER_ID_CHECK$]", assignedUserIdCheck);
			sql = StringUtil.replace(sql, "[$END_DATE_CHECK$]", endDateCheck);
			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			SQLQuery q = _session.createSQLQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(definitionName);
			qPos.add(definitionName);
			qPos.add(definitionVersionInt);
			qPos.add(definitionVersion);
			qPos.add(_getDate(startDateGT, true));
			qPos.add(_getDate(startDateGT, true));
			qPos.add(_getDate(startDateLT, false));
			qPos.add(_getDate(startDateLT, false));

			if (assignedUserId != null) {
				qPos.add(assignedUserId);
			}

			if (!hideEndedTasks) {
				qPos.add(_getDate(endDateGT, true));
				qPos.add(_getDate(endDateGT, true));
				qPos.add(_getDate(endDateLT, false));
				qPos.add(_getDate(endDateLT, false));
			}

			Iterator itr = q.list().iterator();

			if (itr.hasNext()) {
				Number count = (Number)itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RuntimeException(e.getMessage());
		}
	}

	public int countTaskInstancesBySearchTerms(
		String taskName, String definitionName, String assignedTo,
		String createDateGT, String createDateLT, String startDateGT,
		String startDateLT, String endDateGT, String endDateLT,
		boolean hideEndedTasks, boolean andOperator) {

		try {
			String sql = "";

			int index = 0;

			if (Validator.isNull(assignedTo) || assignedTo.equals("all")) {
				sql += "(";
				sql += CustomSQLUtil.get(COUNT_TASK_INSTANCES_BY_ACTOR);
				sql += ") ";
				sql += "UNION ";
				sql += "(";
				sql += CustomSQLUtil.get(COUNT_TASK_INSTANCES_BY_POOL);
				sql += ") ";

				index = 2;
			}
			else if (assignedTo.equals("me")) {
				sql += CustomSQLUtil.get(COUNT_TASK_INSTANCES_BY_ACTOR);

				index = 1;
			}
			else if (assignedTo.equals("pool")) {
				sql += CustomSQLUtil.get(COUNT_TASK_INSTANCES_BY_POOL);

				index = 1;
			}

			String endDateCheck = "(JBPM_TASKINSTANCE.END_ IS NULL) ";

			if (!hideEndedTasks) {
				endDateCheck =
					"((JBPM_TASKINSTANCE.END_ >= ? " +
							"[$AND_OR_NULL_CHECK$]) AND " +
						"(JBPM_TASKINSTANCE.END_ <= ? [$AND_OR_NULL_CHECK$])) ";
			}

			sql = StringUtil.replace(sql, "[$END_DATE_CHECK$]", endDateCheck);
			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			SQLQuery q = _session.createSQLQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			for (int i = 0; i < index; i++) {
				qPos.add(taskName);
				qPos.add(taskName);
				qPos.add(definitionName);
				qPos.add(definitionName);
				qPos.add(_getDate(createDateGT, true));
				qPos.add(_getDate(createDateGT, true));
				qPos.add(_getDate(createDateLT, false));
				qPos.add(_getDate(createDateLT, false));
				qPos.add(_getDate(startDateGT, true));
				qPos.add(_getDate(startDateGT, true));
				qPos.add(_getDate(startDateLT, false));
				qPos.add(_getDate(startDateLT, false));

				if (!hideEndedTasks) {
					qPos.add(_getDate(endDateGT, true));
					qPos.add(_getDate(endDateGT, true));
					qPos.add(_getDate(endDateLT, false));
					qPos.add(_getDate(endDateLT, false));
				}

				qPos.add(_userId);
			}

			int count = 0;

			Iterator itr = q.list().iterator();

			while (itr.hasNext()) {
				Number i = (Number)itr.next();

				if (i != null) {
					count += i.intValue();
				}
			}

			return count;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RuntimeException(e.getMessage());
		}
	}

	public List findProcessDefinitionsByName(String name, int begin, int end) {
		try {
			String sql = CustomSQLUtil.get(FIND_PROCESS_DEFINITIONS_BY_NAME);

			Query q = _session.createQuery(sql);

			q.setString("name", name);

			return QueryUtil.list(q, _dialect, begin, end);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RuntimeException(e.getMessage());
		}
	}

	public List findProcessInstancesBySearchTerms(
		String definitionName, String definitionVersion, String startDateGT,
		String startDateLT, String endDateGT, String endDateLT,
		boolean hideEndedTasks, String assignedUserId, boolean andOperator,
		int begin, int end) {

		List list = new ArrayList();

		try {
			int definitionVersionInt = 0;

			if (!Validator.isNumber(definitionVersion)) {
				definitionVersion = null;
			}
			else {
				definitionVersionInt = GetterUtil.getInteger(definitionVersion);
			}

			String assignedUserIdInnerJoin = StringPool.BLANK;
			String assignedUserIdCheck = StringPool.BLANK;

			if (Validator.isNotNull(assignedUserId)) {
				assignedUserIdInnerJoin =
					"INNER JOIN JBPM_TOKEN ON JBPM_TOKEN.PROCESSINSTANCE_ = " +
						"JBPM_PROCESSINSTANCE.ID_ INNER JOIN " +
							"JBPM_TASKINSTANCE ON JBPM_TASKINSTANCE.TOKEN_ = " +
								"JBPM_TOKEN.ID_ ";
				assignedUserIdCheck = "(JBPM_TASKINSTANCE.ACTORID_ = ?) AND ";
			}

			String endDateCheck = "(JBPM_PROCESSINSTANCE.END_ IS NULL) ";

			if (!hideEndedTasks) {
				endDateCheck =
					"((JBPM_PROCESSINSTANCE.END_ >= ? [$AND_OR_NULL_CHECK$]) " +
						"AND (JBPM_PROCESSINSTANCE.END_ <= ? " +
							"[$AND_OR_NULL_CHECK$])) ";
			}

			String sql = CustomSQLUtil.get(
				FIND_PROCESS_INSTANCES_BY_SEARCH_TERMS);

			sql = StringUtil.replace(
				sql, "[$ASSIGNED_USER_ID_INNER_JOIN$]",
				assignedUserIdInnerJoin);
			sql = StringUtil.replace(
				sql, "[$ASSIGNED_USER_ID_CHECK$]", assignedUserIdCheck);
			sql = StringUtil.replace(sql, "[$END_DATE_CHECK$]", endDateCheck);
			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			SQLQuery q = _session.createSQLQuery(sql);

			q.addScalar("instanceId", Hibernate.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(definitionName);
			qPos.add(definitionName);
			qPos.add(definitionVersionInt);
			qPos.add(definitionVersion);
			qPos.add(_getDate(startDateGT, true));
			qPos.add(_getDate(startDateGT, true));
			qPos.add(_getDate(startDateLT, false));
			qPos.add(_getDate(startDateLT, false));

			if (Validator.isNotNull(assignedUserId)) {
				qPos.add(assignedUserId);
			}

			if (!hideEndedTasks) {
				qPos.add(_getDate(endDateGT, true));
				qPos.add(_getDate(endDateGT, true));
				qPos.add(_getDate(endDateLT, false));
				qPos.add(_getDate(endDateLT, false));
			}

			Iterator itr = QueryUtil.iterate(q, _dialect, begin, end);

			while (itr.hasNext()) {
				Long instanceId = (Long)itr.next();

				ProcessInstance processInstance =
					_jbpmContext.loadProcessInstance(instanceId.longValue());

				WorkflowUtil.initInstance(processInstance);

				list.add(processInstance);
			}

		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RuntimeException(e.getMessage());
		}

		return list;
	}

	public List findTaskInstancesBySearchTerms(
		String taskName, String definitionName, String assignedTo,
		String createDateGT, String createDateLT, String startDateGT,
		String startDateLT, String endDateGT, String endDateLT,
		boolean hideEndedTasks, boolean andOperator, int begin, int end) {

		List list = new ArrayList();

		try {
			String sql = "";

			int index = 0;

			if (Validator.isNull(assignedTo) || assignedTo.equals("all")) {
				sql += "(";
				sql += CustomSQLUtil.get(FIND_TASK_INSTANCES_BY_ACTOR);
				sql += ") ";
				sql += "UNION ";
				sql += "(";
				sql += CustomSQLUtil.get(FIND_TASK_INSTANCES_BY_POOL);
				sql += ") ";

				index = 2;
			}
			else if (assignedTo.equals("me")) {
				sql += CustomSQLUtil.get(FIND_TASK_INSTANCES_BY_ACTOR);

				index = 1;
			}
			else if (assignedTo.equals("pool")) {
				sql += CustomSQLUtil.get(FIND_TASK_INSTANCES_BY_POOL);

				index = 1;
			}

			sql += "ORDER BY taskActorId DESC, taskCreate ASC";

			String endDateCheck = "(JBPM_TASKINSTANCE.END_ IS NULL) ";

			if (!hideEndedTasks) {
				endDateCheck =
					"((JBPM_TASKINSTANCE.END_ >= ? " +
							"[$AND_OR_NULL_CHECK$]) AND " +
						"(JBPM_TASKINSTANCE.END_ <= ? [$AND_OR_NULL_CHECK$])) ";
			}

			sql = StringUtil.replace(sql, "[$END_DATE_CHECK$]", endDateCheck);
			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			SQLQuery q = _session.createSQLQuery(sql);

			q.addScalar("taskId", Hibernate.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			for (int i = 0; i < index; i++) {
				qPos.add(taskName);
				qPos.add(taskName);
				qPos.add(definitionName);
				qPos.add(definitionName);
				qPos.add(_getDate(createDateGT, true));
				qPos.add(_getDate(createDateGT, true));
				qPos.add(_getDate(createDateLT, false));
				qPos.add(_getDate(createDateLT, false));
				qPos.add(_getDate(startDateGT, true));
				qPos.add(_getDate(startDateGT, true));
				qPos.add(_getDate(startDateLT, false));
				qPos.add(_getDate(startDateLT, false));

				if (!hideEndedTasks) {
					qPos.add(_getDate(endDateGT, true));
					qPos.add(_getDate(endDateGT, true));
					qPos.add(_getDate(endDateLT, false));
					qPos.add(_getDate(endDateLT, false));
				}

				qPos.add(_userId);
			}

			Iterator itr = QueryUtil.iterate(q, _dialect, begin, end);

			while (itr.hasNext()) {
				Long taskId = (Long)itr.next();

				TaskInstance taskInstance =
					_jbpmContext.loadTaskInstance(taskId.longValue());

				WorkflowUtil.initTask(taskInstance);

				list.add(taskInstance);
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RuntimeException(e.getMessage());
		}

		return list;
	}

	private Timestamp _getDate(String date, boolean greaterThan) {
		if (Validator.isNull(date)) {
			return null;
		}
		else {
			Calendar calendar = Calendar.getInstance();

			DateFormat dateFormat = DateUtil.getISOFormat();

			calendar.setTime(GetterUtil.getDate(date, dateFormat));

			if (greaterThan) {
				calendar.set(Calendar.HOUR_OF_DAY, 0);
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.MILLISECOND, 0);
			}
			else {
				calendar.set(Calendar.HOUR_OF_DAY, 23);
				calendar.set(Calendar.MINUTE, 59);
				calendar.set(Calendar.SECOND, 59);
				calendar.set(Calendar.MILLISECOND, 999);
			}

			TimeZone timeZone = TimeZone.getTimeZone(_timeZoneId);

			int offset = timeZone.getOffset(calendar.getTimeInMillis());

			return new Timestamp(calendar.getTimeInMillis() - offset);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(GraphSession.class);

	private String _userId;
	private String _timeZoneId;
	private JbpmContext _jbpmContext;
	private Session _session;
	private Dialect _dialect;

}