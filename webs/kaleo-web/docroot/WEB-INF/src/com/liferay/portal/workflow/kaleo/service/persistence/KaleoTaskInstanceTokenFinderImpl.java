/*
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

package com.liferay.portal.workflow.kaleo.service.persistence;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroupRole;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskInstanceTokenImpl;
import com.liferay.portal.workflow.kaleo.util.RoleRetrievalUtil;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.sql.Timestamp;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * <a href="KaleoTaskInstanceTokenFinderImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class KaleoTaskInstanceTokenFinderImpl
	extends BasePersistenceImpl<KaleoTaskInstanceToken>
	implements KaleoTaskInstanceTokenFinder {

	public static String COUNT_BY_ACN_ACP_C_CI =
		KaleoTaskInstanceTokenFinder.class.getName() +
		".countByACN_ACP_C_CI";

	public static String COUNT_BY_ROLES_C_CI =
		KaleoTaskInstanceTokenFinder.class.getName() +
		".countByRoles_C_CI";

	public static String COUNT_BY_C_DD_K_UR =
		KaleoTaskInstanceTokenFinder.class.getName() +
		".countByC_DD_K_UR";

	public static String FIND_BY_ACN_ACP_C_CI =
		KaleoTaskInstanceTokenFinder.class.getName() +
		".findByACN_ACP_C_CI";

	private static final String FIND_BY_C_DD_K_UR =
		KaleoTaskInstanceTokenFinder.class.getName() +
			".findByC_DD_K_UR";

	public static String FIND_BY_ROLES_C_CI =
		KaleoTaskInstanceTokenFinder.class.getName() +
		".findByRoles_C_CI";

	public int countByACN_ACP_C_CI(
			String assigneeClassName, long assigneeClassPK, Boolean completed,
			ServiceContext serviceContext)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_ACN_ACP_C_CI);

			sql = StringUtil.replace(
				sql, "[$COMPLETION$]", getCompletion(completed));

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			setCompleted(qPos, completed);

			qPos.add(assigneeClassName);
			qPos.add(assigneeClassPK);
			qPos.add(serviceContext.getCompanyId());

			Iterator<Long> itr = q.list().iterator();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public int countByC_DD_K_UR(
			String taskName, String assetType, Date dueDateGT,
			Date dueDateLT, Boolean completed, Boolean searchByUserRoles,
			boolean andOperator, ServiceContext serviceContext)
		throws SystemException {

		String[] taskNames = CustomSQLUtil.keywords(taskName, false);
		String[] assetTypes = CustomSQLUtil.keywords(assetType, false);
		Timestamp dueDateGT_TS = CalendarUtil.getTimestamp(dueDateGT);
		Timestamp dueDateLT_TS = CalendarUtil.getTimestamp(dueDateLT);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_C_DD_K_UR);

			sql = StringUtil.replace(
				sql, "[$COMPLETION$]", getCompletion(completed));

			sql = StringUtil.replace(
				sql, "[$SEARCH_CRITERIA$]",
				getSearchCriteria(
					taskNames, assetTypes, dueDateGT_TS, dueDateLT_TS));

			sql = CustomSQLUtil.replaceKeywords(
				sql, "KaleoTaskInstanceToken.kaleoTaskName", StringPool.LIKE,
				false, taskNames);

			sql = CustomSQLUtil.replaceKeywords(
				sql, "KaleoTaskInstanceToken.className", StringPool.LIKE,
				false, assetTypes);

			List<Long> roleIds = null;
			List<UserGroupRole> userGroupRoles = null;

			if ((searchByUserRoles != null) && searchByUserRoles) {
				roleIds = RoleRetrievalUtil.getRoleIds(serviceContext);

				userGroupRoles =
					UserGroupRoleLocalServiceUtil.getUserGroupRoles(
						serviceContext.getUserId());
			}

			sql = StringUtil.replace(
				sql, "[$USER_ROLES$]",
				getUserRoles(searchByUserRoles, roleIds, userGroupRoles));

			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			setCompleted(qPos, completed);

			setSearchCriteria(
				qPos, taskNames, assetTypes, dueDateGT_TS, dueDateLT_TS);

			setUserRoles(
				qPos, searchByUserRoles, roleIds, userGroupRoles,
				serviceContext.getUserId());

			qPos.add(serviceContext.getCompanyId());

			Iterator<Long> itr = q.list().iterator();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public int countByRoles_C_CI(
			List<Long> roleIds, Boolean completed,
			ServiceContext serviceContext)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_ROLES_C_CI);

			sql = StringUtil.replace(
				sql, "[$COMPLETION$]", getCompletion(completed));
			sql = StringUtil.replace(
				sql, "[$ROLE_IDS$]", getRoleIds(roleIds));

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			setCompleted(qPos, completed);
			setRoleIds(qPos, roleIds);
			qPos.add(Role.class.getName());
			qPos.add(serviceContext.getCompanyId());

			Iterator<Long> itr = q.list().iterator();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<KaleoTaskInstanceToken> findByACN_ACP_C_CI(
			String assigneeClassName, long assigneeClassPK, Boolean completed,
			int start, int end, OrderByComparator orderByComparator,
			ServiceContext serviceContext)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_ACN_ACP_C_CI);

			sql = StringUtil.replace(
				sql, "[$COMPLETION$]", getCompletion(completed));

			sql = CustomSQLUtil.replaceOrderBy(sql, orderByComparator);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity(
				"Kaleo_KaleoTaskInstanceToken",
				KaleoTaskInstanceTokenImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			setCompleted(qPos, completed);

			qPos.add(assigneeClassName);
			qPos.add(assigneeClassPK);
			qPos.add(serviceContext.getCompanyId());

			return (List<KaleoTaskInstanceToken>) QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<KaleoTaskInstanceToken> findByC_DD_K_UR(
			String taskName, String assetType, Date dueDateGT,
			Date dueDateLT, Boolean completed, Boolean searchByUserRoles,
			boolean andOperator, int start, int end,
			OrderByComparator orderByComparator, ServiceContext serviceContext)
		throws SystemException {

		String[] taskNames = CustomSQLUtil.keywords(taskName, false);
		String[] assetTypes = CustomSQLUtil.keywords(assetType, false);
		Timestamp dueDateGT_TS = CalendarUtil.getTimestamp(dueDateGT);
		Timestamp dueDateLT_TS = CalendarUtil.getTimestamp(dueDateLT);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_C_DD_K_UR);

			sql = StringUtil.replace(
				sql, "[$COMPLETION$]", getCompletion(completed));

			sql = StringUtil.replace(
				sql, "[$SEARCH_CRITERIA$]",
				getSearchCriteria(
					taskNames, assetTypes, dueDateGT_TS, dueDateLT_TS));

			sql = CustomSQLUtil.replaceKeywords(
				sql, "Kaleo_KaleoTaskInstanceToken.kaleoTaskName", StringPool.LIKE,
				false, taskNames);

			sql = CustomSQLUtil.replaceKeywords(
				sql, "Kaleo_KaleoTaskInstanceToken.className", StringPool.LIKE,
				false, assetTypes);

			List<Long> roleIds = null;
			List<UserGroupRole> userGroupRoles = null;

			if ((searchByUserRoles != null) && searchByUserRoles) {
				roleIds = RoleRetrievalUtil.getRoleIds(serviceContext);

				userGroupRoles =
					UserGroupRoleLocalServiceUtil.getUserGroupRoles(
						serviceContext.getUserId());
			}

			sql = StringUtil.replace(
				sql, "[$USER_ROLES$]",
				getUserRoles(searchByUserRoles, roleIds, userGroupRoles));

			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);
			sql = CustomSQLUtil.replaceOrderBy(sql, orderByComparator);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity(
				"Kaleo_KaleoTaskInstanceToken",
				KaleoTaskInstanceTokenImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			setCompleted(qPos, completed);

			setSearchCriteria(
				qPos, taskNames, assetTypes, dueDateGT_TS, dueDateLT_TS);

			setUserRoles(
				qPos, searchByUserRoles, roleIds, userGroupRoles,
				serviceContext.getUserId());

			qPos.add(serviceContext.getCompanyId());

			return (List<KaleoTaskInstanceToken>) QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<KaleoTaskInstanceToken> findByRoles_C_CI(
			List<Long> roleIds, Boolean completed,
			int start, int end, OrderByComparator orderByComparator,
			ServiceContext serviceContext)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_ROLES_C_CI);

			sql = StringUtil.replace(
				sql, "[$COMPLETION$]", getCompletion(completed));

			sql = StringUtil.replace(
				sql, "[$ROLE_IDS$]", getRoleIds(roleIds));

			sql = CustomSQLUtil.replaceOrderBy(sql, orderByComparator);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity(
				"Kaleo_KaleoTaskInstanceToken",
				KaleoTaskInstanceTokenImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			setCompleted(qPos, completed);
			setRoleIds(qPos, roleIds);
			qPos.add(Role.class.getName());
			qPos.add(serviceContext.getCompanyId());

			return (List<KaleoTaskInstanceToken>) QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected String getCompletion(Boolean completed) {
		if (completed != null) {
			return "(Kaleo_KaleoTaskInstanceToken.completed = ?) AND ";
		}
		else {
			return StringPool.BLANK;
		}
	}

	protected String getRoleIds(List<Long> roleIds) {
		if (roleIds.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(roleIds.size() * 2 + 1);

		sb.append("(");

		for (int i = 0; i < roleIds.size(); i++) {
			sb.append("(Kaleo_KaleoTaskAssignmentInstance.assigneeClassPK = ?)");

			if ((i + 1) < roleIds.size()) {
				sb.append(" OR ");
			}
			else {
				sb.append(")");
			}
		}

		return sb.toString();
	}

	protected String getSearchCriteria(
		String[] taskNames, String[] assetTypes,
		Timestamp dueDateGT, Timestamp dueDateLT) {

		if (((taskNames == null) || (taskNames.length == 0)) &&
			((assetTypes == null) || (assetTypes.length == 0)) &&
			(dueDateGT == null) && (dueDateLT == null)) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(5);

		sb.append(" (");

		boolean addAndOrOperator = false;

		if ((taskNames != null) && (taskNames.length != 0)) {
			sb.append("(Kaleo_KaleoTaskInstanceToken.kaleoTaskName LIKE ? [$AND_OR_NULL_CHECK$])");
			addAndOrOperator = true;
		}
		if ((assetTypes != null) && (assetTypes.length != 0)) {
			if (addAndOrOperator) {
				sb.append(" [$AND_OR_CONNECTOR$] ");
			}
			sb.append("(Kaleo_KaleoTaskInstanceToken.kaleoTaskName LIKE ? [$AND_OR_NULL_CHECK$])");
		}
		if (dueDateGT != null) {
			if (addAndOrOperator) {
				sb.append(" [$AND_OR_CONNECTOR$] ");
			}
			sb.append(
				"(Kaleo_KaleoTaskInstanceToken.dueDate >= ? [$AND_OR_NULL_CHECK$])");
		}

		if (dueDateLT != null) {
			if (addAndOrOperator) {
				sb.append(" [$AND_OR_CONNECTOR$] ");
			}
			sb.append(
				"(Kaleo_KaleoTaskInstanceToken.dueDate <= ? [$AND_OR_NULL_CHECK$])");

		}
		sb.append(") AND ");

		return sb.toString();
	}

	protected String getUserRoles(
		Boolean searchByUserRoles, List<Long> roleIds,
		List<UserGroupRole> userGroupRoles) {

		if ((searchByUserRoles == null) ||
			(searchByUserRoles && ((roleIds == null) || roleIds.isEmpty()) &&
			 ((userGroupRoles == null) || userGroupRoles.isEmpty()))) {
			return StringPool.BLANK;
		}

		StringBundler sb = null;

		if (!searchByUserRoles) {
			sb = new StringBundler(3);
			sb.append("((Kaleo_KaleoTaskAssignmentInstance.assigneeClassName = ?) ");
			sb.append("AND (Kaleo_KaleoTaskAssignmentInstance.assigneeClassPK = ?))");
		}
		else {
			sb = new StringBundler(
				roleIds.size() + userGroupRoles.size() * 2 + 1);

			sb.append("((Kaleo_KaleoTaskAssignmentInstance.assigneeClassName = ?)");
			sb.append(" AND (");

			for (int i = 0; i < roleIds.size(); i++) {
				sb.append("(Kaleo_KaleoTaskAssignmentInstance.assigneeClassPK = ?)");

				if ((i + 1) < roleIds.size()) {
					sb.append(" OR ");
				}
			}

			if (!roleIds.isEmpty() && !userGroupRoles.isEmpty()) {
				sb.append(" OR ");
			}

			for (int i = 0; i < userGroupRoles.size(); i++) {
				sb.append("((Kaleo_KaleoTaskAssignmentInstance.groupId = ?)");
				sb.append(" AND ");
				sb.append("(Kaleo_KaleoTaskAssignmentInstance.assigneeClassPK = ?))");

				if ((i + 1) < userGroupRoles.size()) {
					sb.append(" OR ");
				}
			}
			sb.append("))");
		}

		sb.append(" AND ");

		return sb.toString();
	}


	protected void setCompleted(QueryPos qPos, Boolean completed) {
		if (completed != null) {
			qPos.add(completed);
		}
	}

	protected void setDueDate(
		QueryPos qPos, Timestamp dueDateGT, Timestamp dueDateLT) {

		if ((dueDateGT == null) && (dueDateLT == null)) {
			return;
		}

		if (dueDateGT != null) {
			qPos.add(dueDateGT);
		}

		if (dueDateLT != null) {
			qPos.add(dueDateGT);
		}
	}
	protected void setRoleIds(QueryPos qPos, List<Long> roleIds) {
		for (Long roleId : roleIds) {
			qPos.add(roleId);
		}
	}

	protected void setSearchCriteria(
		QueryPos qPos, String[] taskNames, String[] assetTypes,
		Timestamp dueDateGT_TS, Timestamp dueDateLT_TS) {

		if (((taskNames == null) || (taskNames.length == 0)) &&
			((assetTypes == null) || (assetTypes.length == 0)) &&
			(dueDateGT_TS == null) &&
			(dueDateLT_TS == null)) {

			return;
		}

		qPos.add(taskNames, 2);
		qPos.add(assetTypes, 2);
		setDueDate(qPos, dueDateGT_TS, dueDateLT_TS);
	}

	private void setUserRoles(
		QueryPos qPos, Boolean searchByUserRoles, List<Long> roleIds,
		List<UserGroupRole> userGroupRoles, long userId) {

		if ((searchByUserRoles == null) ||
			(searchByUserRoles && ((roleIds == null) || roleIds.isEmpty()) &&
			 ((userGroupRoles == null) || userGroupRoles.isEmpty()))) {
			return;
		}

		if (!searchByUserRoles) {
			qPos.add(User.class.getName());

			qPos.add(userId);
		}
		else {
			qPos.add(Role.class.getName());

			for (Long roleId : roleIds) {
				qPos.add(roleId);
			}

			for (UserGroupRole userGroupRole : userGroupRoles) {
				qPos.add(userGroupRole.getGroupId());
				qPos.add(userGroupRole.getRoleId());
			}
		}
	}

}