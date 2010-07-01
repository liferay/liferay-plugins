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

package com.liferay.portal.workflow.kaleo.service.persistence;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroupRole;
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
 * <a href="KaleoTaskInstanceTokenFinderImpl.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Michael C. Han
 */
public class KaleoTaskInstanceTokenFinderImpl
	extends BasePersistenceImpl<KaleoTaskInstanceToken>
	implements KaleoTaskInstanceTokenFinder {

	public static String COUNT_BY_C_KTAI =
		KaleoTaskInstanceTokenFinder.class.getName() + ".countByC_KTAI";

	public static String FIND_BY_C_KTAI =
		KaleoTaskInstanceTokenFinder.class.getName() + ".findByC_KTAI";

	public int countKaleoTaskInstanceTokens(
			KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = buildKaleoTaskInstanceTokenQuerySQL(
				kaleoTaskInstanceTokenQuery, true, session);

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

	public List<KaleoTaskInstanceToken> findKaleoTaskInstanceTokens(
			KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = buildKaleoTaskInstanceTokenQuerySQL(
				kaleoTaskInstanceTokenQuery, false, session);

			return (List<KaleoTaskInstanceToken>)QueryUtil.list(
				q, getDialect(), kaleoTaskInstanceTokenQuery.getStart(),
				kaleoTaskInstanceTokenQuery.getEnd());
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SQLQuery buildKaleoTaskInstanceTokenQuerySQL(
			KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery,
			boolean count, Session session)
		throws Exception {

		String sql = null;

		if (count) {
			sql = CustomSQLUtil.get(COUNT_BY_C_KTAI);
		}
		else {
			sql = CustomSQLUtil.get(FIND_BY_C_KTAI);
		}

		sql = CustomSQLUtil.appendCriteria(
			sql, getAssigneeClassName(kaleoTaskInstanceTokenQuery));
		sql = CustomSQLUtil.appendCriteria(
			sql, getAssigneeClassPK(kaleoTaskInstanceTokenQuery));
		sql = CustomSQLUtil.appendCriteria(
			sql, getCompleted(kaleoTaskInstanceTokenQuery));
		sql = CustomSQLUtil.appendCriteria(
			sql, getRoleIds(kaleoTaskInstanceTokenQuery));
		sql = CustomSQLUtil.appendCriteria(
			sql, getSearchByUserRoles(kaleoTaskInstanceTokenQuery));

		if (appendSearchCriteria(kaleoTaskInstanceTokenQuery)) {
			sql = CustomSQLUtil.appendCriteria(sql, " AND (");
			sql = CustomSQLUtil.appendCriteria(
				sql, getAssetType(kaleoTaskInstanceTokenQuery));
			sql = CustomSQLUtil.appendCriteria(
				sql,
				getDueDateGT(
					kaleoTaskInstanceTokenQuery,
					(kaleoTaskInstanceTokenQuery.getAssetType() == null)));
			sql = CustomSQLUtil.appendCriteria(
				sql,
				getDueDateLT(
					kaleoTaskInstanceTokenQuery,
					((kaleoTaskInstanceTokenQuery.getAssetType() == null) &&
					 (kaleoTaskInstanceTokenQuery.getDueDateGT() == null))));
			sql = CustomSQLUtil.appendCriteria(
				sql,
				getTaskName(
					kaleoTaskInstanceTokenQuery,
					((kaleoTaskInstanceTokenQuery.getAssetType() == null) &&
					 (kaleoTaskInstanceTokenQuery.getDueDateGT() == null) &&
					 (kaleoTaskInstanceTokenQuery.getDueDateLT() == null))));
			sql = CustomSQLUtil.appendCriteria(sql, ")");

			sql = CustomSQLUtil.replaceAndOperator(
				sql, kaleoTaskInstanceTokenQuery.isAndOperator());
		}

		if (kaleoTaskInstanceTokenQuery.getOrderByComparator() != null) {
			StringBundler sb = new StringBundler(sql);

			appendOrderByComparator(
				sb, _ORDER_BY_ENTITY_ALIAS,
				kaleoTaskInstanceTokenQuery.getOrderByComparator());

			sql = sb.toString();
		}

		SQLQuery q = session.createSQLQuery(sql);

		if (count) {
			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);
		}
		else {
			q.addEntity(
				"Kaleo_KaleoTaskInstanceToken",
				KaleoTaskInstanceTokenImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoTaskInstanceTokenQuery.getCompanyId());

		setAssigneeClassName(qPos, kaleoTaskInstanceTokenQuery);
		setAssigneeClassPK(qPos, kaleoTaskInstanceTokenQuery);
		setCompleted(qPos, kaleoTaskInstanceTokenQuery);
		setRoleIds(qPos, kaleoTaskInstanceTokenQuery);
		setSearchByUserRoles(qPos, kaleoTaskInstanceTokenQuery);

		setAssetType(qPos, kaleoTaskInstanceTokenQuery);
		setDueDateGT(qPos, kaleoTaskInstanceTokenQuery);
		setDueDateLT(qPos, kaleoTaskInstanceTokenQuery);
		setTaskName(qPos, kaleoTaskInstanceTokenQuery);

		return q;
	}

	protected boolean appendSearchCriteria(
		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery) {

		if (Validator.isNotNull(kaleoTaskInstanceTokenQuery.getAssetType())) {
			return true;
		}

		if (kaleoTaskInstanceTokenQuery.getDueDateGT() != null) {
			return true;
		}

		if (kaleoTaskInstanceTokenQuery.getDueDateLT() != null) {
			return true;
		}

		if (Validator.isNotNull(kaleoTaskInstanceTokenQuery.getTaskName())) {
			return true;
		}

		return false;
	}

	protected String getAssetType(
		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery) {

		String assetType = kaleoTaskInstanceTokenQuery.getAssetType();

		if (assetType == null) {
			return StringPool.BLANK;
		}

		String[] assetTypes = CustomSQLUtil.keywords(assetType, false);

		if ((assetTypes == null) || (assetTypes.length == 0)) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(assetTypes.length * 2 + 1);

		sb.append("(");

		for (int i = 0; i < assetTypes.length; i++) {
			sb.append("(Kaleo_KaleoTaskInstanceToken.className LIKE ?)");

			if ((i + 1) < assetTypes.length) {
				sb.append(" OR ");
			}
			else {
				sb.append(")");
			}
		}

		return sb.toString();
	}

	protected String getAssigneeClassName(
		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery) {

		String assigneeClassName =
			kaleoTaskInstanceTokenQuery.getAssigneeClassName();

		if (assigneeClassName == null) {
			return StringPool.BLANK;
		}

		return "AND (Kaleo_KaleoTaskAssignmentInstance.assigneeClassName = ?)";
	}

	protected String getAssigneeClassPK(
		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery) {

		Long assigneeClassPK = kaleoTaskInstanceTokenQuery.getAssigneeClassPK();

		if (assigneeClassPK == null) {
			return StringPool.BLANK;
		}

		return "AND (Kaleo_KaleoTaskAssignmentInstance.assigneeClassPK = ?)";
	}

	protected String getCompleted(
		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery) {

		Boolean completed = kaleoTaskInstanceTokenQuery.isCompleted();

		if (completed == null) {
			return StringPool.BLANK;
		}

		return "AND (Kaleo_KaleoTaskInstanceToken.completed = ?)";
	}

	protected String getDueDateGT(
		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery,
		boolean firstCriteria) {

		Date dueDateGT = kaleoTaskInstanceTokenQuery.getDueDateGT();

		if (dueDateGT == null) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(3);

		if (!firstCriteria) {
			sb.append("[$AND_OR_CONNECTOR$] (");
		}
		else {
			sb.append("(");
		}

		sb.append(
			"Kaleo_KaleoTaskInstanceToken.dueDate >= ? [$AND_OR_NULL_CHECK$])");

		return sb.toString();
	}

	protected String getDueDateLT(
		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery,
		boolean firstCriteria) {

		Date dueDateLT = kaleoTaskInstanceTokenQuery.getDueDateLT();

		if (dueDateLT == null) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(3);

		if (!firstCriteria) {
			sb.append("[$AND_OR_CONNECTOR$] (");
		}
		else {
			sb.append("(");
		}

		sb.append(
			"Kaleo_KaleoTaskInstanceToken.dueDate <= ? [$AND_OR_NULL_CHECK$])");

		return sb.toString();
	}

	protected String getRoleIds(
		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery) {

		Boolean searchByUserRoles =
			kaleoTaskInstanceTokenQuery.isSearchByUserRoles();

		if (searchByUserRoles != null) {
			return StringPool.BLANK;
		}

		List<Long> roleIds = kaleoTaskInstanceTokenQuery.getRoleIds();

		if ((roleIds == null) || roleIds.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(roleIds.size() * 2 + 1);

		sb.append("AND (");

		for (int i = 0; i < roleIds.size(); i++) {
			sb.append(
				"(Kaleo_KaleoTaskAssignmentInstance.assigneeClassPK = ?)");

			if ((i + 1) < roleIds.size()) {
				sb.append(" OR ");
			}
			else {
				sb.append(")");
			}
		}

		return sb.toString();
	}

	protected String getSearchByUserRoles(
			KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery)
		throws Exception {

		Boolean searchByUserRoles =
			kaleoTaskInstanceTokenQuery.isSearchByUserRoles();

		if (searchByUserRoles == null) {
			return StringPool.BLANK;
		}

		if (searchByUserRoles) {
			List<Long> roleIds = RoleRetrievalUtil.getRoleIds(
				kaleoTaskInstanceTokenQuery.getServiceContext());

			List<UserGroupRole >userGroupRoles =
				UserGroupRoleLocalServiceUtil.getUserGroupRoles(
					kaleoTaskInstanceTokenQuery.getUserId());

			if (roleIds.isEmpty() && userGroupRoles.isEmpty()) {
				return StringPool.BLANK;
			}

			StringBundler sb = new StringBundler();

			sb.append("AND ((");
			sb.append("Kaleo_KaleoTaskAssignmentInstance.assigneeClassName = ");
			sb.append("?) AND (");

			for (int i = 0; i < roleIds.size(); i++) {
				sb.append(
					"(Kaleo_KaleoTaskAssignmentInstance.assigneeClassPK = ?)");

				if ((i + 1) < roleIds.size()) {
					sb.append(" OR ");
				}
			}

			if (!roleIds.isEmpty() && !userGroupRoles.isEmpty()) {
				sb.append(" OR ");
			}

			for (int i = 0; i < userGroupRoles.size(); i++) {
				sb.append("((Kaleo_KaleoTaskAssignmentInstance.groupId = ?) ");
				sb.append("AND (Kaleo_KaleoTaskAssignmentInstance.");
				sb.append("assigneeClassPK = ?))");

				if ((i + 1) < userGroupRoles.size()) {
					sb.append(" OR ");
				}
			}

			sb.append("))");

			return sb.toString();
		}
		else {
			StringBundler sb = new StringBundler(4);

			sb.append("AND (");
			sb.append("(Kaleo_KaleoTaskAssignmentInstance.assigneeClassName ");
			sb.append("= ?) AND (Kaleo_KaleoTaskAssignmentInstance.");
			sb.append("assigneeClassPK = ?))");

			return sb.toString();
		}
	}

	protected String getTaskName(
		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery,
		boolean firstCriteria) {

		String taskName = kaleoTaskInstanceTokenQuery.getTaskName();

		if (taskName == null) {
			return StringPool.BLANK;
		}

		String[] taskNames = CustomSQLUtil.keywords(taskName, false);

		if ((taskNames == null) || (taskNames.length == 0)) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(taskNames.length * 2 + 1);

		if (!firstCriteria) {
			sb.append("[$AND_OR_CONNECTOR$] (");
		}
		else {
			sb.append("(");
		}

		for (int i = 0; i < taskNames.length; i++) {
			sb.append("(Kaleo_KaleoTaskInstanceToken.kaleoTaskName LIKE ?)");

			if ((i + 1) < taskNames.length) {
				sb.append(" OR ");
			}
			else {
				sb.append(")");
			}
		}

		return sb.toString();
	}

	protected void setAssetType(
		QueryPos qPos,
		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery) {

		String assetType = kaleoTaskInstanceTokenQuery.getAssetType();

		if (assetType == null) {
			return;
		}

		String[] assetTypes = CustomSQLUtil.keywords(assetType, false);

		qPos.add(assetTypes);
	}

	protected void setAssigneeClassName(
		QueryPos qPos,
		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery) {

		String assigneeClassName =
			kaleoTaskInstanceTokenQuery.getAssigneeClassName();

		if (assigneeClassName == null) {
			return;
		}

		qPos.add(assigneeClassName);
	}

	protected void setAssigneeClassPK(
		QueryPos qPos,
		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery) {

		Long assigneeClassPK = kaleoTaskInstanceTokenQuery.getAssigneeClassPK();

		if (assigneeClassPK == null) {
			return;
		}

		qPos.add(assigneeClassPK);
	}

	protected void setCompleted(
		QueryPos qPos,
		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery) {

		Boolean completed = kaleoTaskInstanceTokenQuery.isCompleted();

		if (completed == null) {
			return;
		}

		qPos.add(completed);
	}

	protected void setDueDateGT(
		QueryPos qPos,
		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery) {

		Date dueDateGT = kaleoTaskInstanceTokenQuery.getDueDateGT();

		if (dueDateGT == null) {
			return;
		}

		Timestamp dueDateGT_TS = CalendarUtil.getTimestamp(dueDateGT);

		qPos.add(dueDateGT_TS);
	}

	protected void setDueDateLT(
		QueryPos qPos,
		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery) {

		Date dueDateLT = kaleoTaskInstanceTokenQuery.getDueDateLT();

		if (dueDateLT == null) {
			return;
		}

		Timestamp dueDateLT_TS = CalendarUtil.getTimestamp(dueDateLT);

		qPos.add(dueDateLT_TS);
	}

	protected void setRoleIds(
		QueryPos qPos,
		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery) {

		Boolean searchByUserRoles =
			kaleoTaskInstanceTokenQuery.isSearchByUserRoles();

		if (searchByUserRoles != null) {
			return;
		}

		List<Long> roleIds = kaleoTaskInstanceTokenQuery.getRoleIds();

		if ((roleIds == null) || roleIds.isEmpty()) {
			return;
		}

		for (Long roleId : roleIds) {
			qPos.add(roleId);
		}
	}

	protected void setSearchByUserRoles(
			QueryPos qPos,
			KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery)
		throws Exception {

		Boolean searchByUserRoles =
			kaleoTaskInstanceTokenQuery.isSearchByUserRoles();

		if (searchByUserRoles == null) {
			return;
		}

		if (searchByUserRoles) {
			qPos.add(Role.class.getName());

			List<Long> roleIds = RoleRetrievalUtil.getRoleIds(
				kaleoTaskInstanceTokenQuery.getServiceContext());

			List<UserGroupRole >userGroupRoles =
				UserGroupRoleLocalServiceUtil.getUserGroupRoles(
					kaleoTaskInstanceTokenQuery.getUserId());

			if (roleIds.isEmpty() && userGroupRoles.isEmpty()) {
				return;
			}

			for (Long roleId : roleIds) {
				qPos.add(roleId);
			}

			for (UserGroupRole userGroupRole : userGroupRoles) {
				qPos.add(userGroupRole.getGroupId());
				qPos.add(userGroupRole.getRoleId());
			}
		}
		else {
			qPos.add(User.class.getName());
			qPos.add(kaleoTaskInstanceTokenQuery.getUserId());
		}
	}

	protected void setTaskName(
		QueryPos qPos,
		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery) {

		String taskName = kaleoTaskInstanceTokenQuery.getTaskName();

		if (taskName == null) {
			return;
		}

		String[] taskNames = CustomSQLUtil.keywords(taskName, false);

		qPos.add(taskNames);
	}

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"Kaleo_KaleoTaskInstanceToken.";

}