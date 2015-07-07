/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.kaleo.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.model.UserGroupGroupRole;
import com.liferay.portal.model.UserGroupRole;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserGroupGroupRoleLocalServiceUtil;
import com.liferay.portal.service.UserGroupLocalServiceUtil;
import com.liferay.portal.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskInstanceTokenModelImpl;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoTaskInstanceTokenFinder;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoTaskInstanceTokenQuery;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoTaskInstanceTokenUtil;
import com.liferay.portal.workflow.kaleo.util.RoleUtil;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class KaleoTaskInstanceTokenFinderImpl
	extends BasePersistenceImpl<KaleoTaskInstanceToken>
	implements KaleoTaskInstanceTokenFinder {

	public static final String COUNT_BY_C_KTAI =
		KaleoTaskInstanceTokenFinder.class.getName() + ".countByC_KTAI";

	public static final String FIND_BY_C_KTAI =
		KaleoTaskInstanceTokenFinder.class.getName() + ".findByC_KTAI";

	@Override
	public int countKaleoTaskInstanceTokens(
		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery) {

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = buildKaleoTaskInstanceTokenQuerySQL(
				kaleoTaskInstanceTokenQuery, true, session);

			Iterator<Long> itr = q.iterate();

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

	@Override
	public List<KaleoTaskInstanceToken> findKaleoTaskInstanceTokens(
		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery) {

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = buildKaleoTaskInstanceTokenQuerySQL(
				kaleoTaskInstanceTokenQuery, false, session);

			List<KaleoTaskInstanceToken> kaleoTaskInstanceTokens =
				new ArrayList<>();

			Iterator<Long> itr = (Iterator<Long>)QueryUtil.iterate(
				q, getDialect(), kaleoTaskInstanceTokenQuery.getStart(),
				kaleoTaskInstanceTokenQuery.getEnd());

			while (itr.hasNext()) {
				long kaleoTaskInstanceTokenId = itr.next();

				KaleoTaskInstanceToken kaleoTaskInstanceToken =
					KaleoTaskInstanceTokenUtil.findByPrimaryKey(
						kaleoTaskInstanceTokenId);

				kaleoTaskInstanceTokens.add(kaleoTaskInstanceToken);
			}

			return kaleoTaskInstanceTokens;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected boolean appendSearchCriteria(
		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery) {

		if (Validator.isNotNull(
				kaleoTaskInstanceTokenQuery.getAssetPrimaryKeys())) {

			return true;
		}

		if (Validator.isNotNull(kaleoTaskInstanceTokenQuery.getAssetTypes())) {
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
			sql, getKaleoInstanceId(kaleoTaskInstanceTokenQuery));
		sql = CustomSQLUtil.appendCriteria(
			sql, getRoleIds(kaleoTaskInstanceTokenQuery));
		sql = CustomSQLUtil.appendCriteria(
			sql, getSearchByUserRoles(kaleoTaskInstanceTokenQuery));

		if (appendSearchCriteria(kaleoTaskInstanceTokenQuery)) {
			sql = CustomSQLUtil.appendCriteria(sql, " AND (");

			if (Validator.isNotNull(
					kaleoTaskInstanceTokenQuery.getAssetPrimaryKeys()) ||
				Validator.isNotNull(
					kaleoTaskInstanceTokenQuery.getAssetTypes())) {

				sql = CustomSQLUtil.appendCriteria(sql, " (");
			}

			sql = CustomSQLUtil.appendCriteria(
				sql, getAssetPrimaryKey(kaleoTaskInstanceTokenQuery));
			sql = CustomSQLUtil.appendCriteria(
				sql,
				getAssetTypes(
					kaleoTaskInstanceTokenQuery,
					Validator.isNull(
						kaleoTaskInstanceTokenQuery.getAssetPrimaryKeys())));

			if (Validator.isNotNull(
					kaleoTaskInstanceTokenQuery.getAssetPrimaryKeys()) ||
				Validator.isNotNull(
					kaleoTaskInstanceTokenQuery.getAssetTypes())) {

				sql = CustomSQLUtil.appendCriteria(sql, ") ");
			}

			sql = CustomSQLUtil.appendCriteria(
				sql,
				getDueDateGT(
					kaleoTaskInstanceTokenQuery,
					(Validator.isNull(
						kaleoTaskInstanceTokenQuery.getAssetPrimaryKeys()) &&
					 Validator.isNull(
						 kaleoTaskInstanceTokenQuery.getAssetTypes()))));
			sql = CustomSQLUtil.appendCriteria(
				sql,
				getDueDateLT(
					kaleoTaskInstanceTokenQuery,
					(Validator.isNull(
						kaleoTaskInstanceTokenQuery.getAssetPrimaryKeys()) &&
					 Validator.isNull(
						 kaleoTaskInstanceTokenQuery.getAssetTypes()) &&
					 (kaleoTaskInstanceTokenQuery.getDueDateGT() == null))));
			sql = CustomSQLUtil.appendCriteria(
				sql,
				getTaskName(
					kaleoTaskInstanceTokenQuery,
					(Validator.isNull(
						kaleoTaskInstanceTokenQuery.getAssetPrimaryKeys()) &&
					 Validator.isNull(
						 kaleoTaskInstanceTokenQuery.getAssetTypes()) &&
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

			OrderByComparator<KaleoTaskInstanceToken> obc =
				kaleoTaskInstanceTokenQuery.getOrderByComparator();

			String[] orderByFields = obc.getOrderByFields();

			sb = new StringBundler(orderByFields.length * 3 + 1);

			sb.append(
				"DISTINCT KaleoTaskInstanceToken.kaleoTaskInstanceTokenId");

			for (String orderByField : orderByFields) {
				sb.append(", ");
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByField);
			}

			sql = sql.replace(
				"DISTINCT KaleoTaskInstanceToken.kaleoTaskInstanceTokenId",
				sb.toString());
		}

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		if (count) {
			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);
		}
		else {
			q.addScalar("KaleoTaskInstanceTokenId", Type.LONG);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoTaskInstanceTokenQuery.getCompanyId());

		setAssigneeClassName(qPos, kaleoTaskInstanceTokenQuery);
		setAssigneeClassPK(qPos, kaleoTaskInstanceTokenQuery);
		setCompleted(qPos, kaleoTaskInstanceTokenQuery);
		setKaleoInstanceId(qPos, kaleoTaskInstanceTokenQuery);
		setRoleIds(qPos, kaleoTaskInstanceTokenQuery);
		setSearchByUserRoles(qPos, kaleoTaskInstanceTokenQuery);

		setAssetPrimaryKey(qPos, kaleoTaskInstanceTokenQuery);
		setAssetType(qPos, kaleoTaskInstanceTokenQuery);
		setDueDateGT(qPos, kaleoTaskInstanceTokenQuery);
		setDueDateLT(qPos, kaleoTaskInstanceTokenQuery);
		setTaskName(qPos, kaleoTaskInstanceTokenQuery);

		return q;
	}

	protected String getAssetPrimaryKey(
		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery) {

		Long[] assetPrimaryKeys =
			kaleoTaskInstanceTokenQuery.getAssetPrimaryKeys();

		if (ArrayUtil.isEmpty(assetPrimaryKeys)) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(assetPrimaryKeys.length * 2 + 1);

		sb.append("(");

		for (int i = 0; i < assetPrimaryKeys.length; i++) {
			sb.append("(KaleoTaskInstanceToken.classPK = ?)");

			if ((i + 1) < assetPrimaryKeys.length) {
				sb.append(" OR ");
			}
			else {
				sb.append(")");
			}
		}

		return sb.toString();
	}

	protected String getAssetTypes(
		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery,
		boolean firstCriteria) {

		String[] assetTypes = CustomSQLUtil.keywords(
			kaleoTaskInstanceTokenQuery.getAssetTypes());

		if (ArrayUtil.isEmpty(assetTypes)) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(assetTypes.length * 2 + 1);

		if (!firstCriteria) {
			sb.append(" AND (");
		}
		else {
			sb.append("(");
		}

		for (int i = 0; i < assetTypes.length; i++) {
			sb.append("(lower(KaleoTaskInstanceToken.className) LIKE ?)");

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

		if (Validator.isNull(assigneeClassName)) {
			return StringPool.BLANK;
		}

		return "AND (KaleoTaskAssignmentInstance.assigneeClassName = ?)";
	}

	protected String getAssigneeClassPK(
		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery) {

		Long assigneeClassPK = kaleoTaskInstanceTokenQuery.getAssigneeClassPK();

		if (assigneeClassPK == null) {
			return StringPool.BLANK;
		}

		return "AND (KaleoTaskAssignmentInstance.assigneeClassPK = ?)";
	}

	protected String getCompleted(
		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery) {

		Boolean completed = kaleoTaskInstanceTokenQuery.isCompleted();

		if (completed == null) {
			return StringPool.BLANK;
		}

		return "AND (KaleoTaskInstanceToken.completed = ?)";
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

		sb.append("KaleoTaskInstanceToken.dueDate >= ? [$AND_OR_NULL_CHECK$])");

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

		sb.append("KaleoTaskInstanceToken.dueDate <= ? [$AND_OR_NULL_CHECK$])");

		return sb.toString();
	}

	protected String getKaleoInstanceId(
		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery) {

		Long kaleoInstanceId = kaleoTaskInstanceTokenQuery.getKaleoInstanceId();

		if (kaleoInstanceId == null) {
			return StringPool.BLANK;
		}

		return "AND (KaleoTaskInstanceToken.kaleoInstanceId = ?)";
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
			sb.append("(KaleoTaskAssignmentInstance.assigneeClassPK = ?)");

			if ((i + 1) < roleIds.size()) {
				sb.append(" OR ");
			}
			else {
				sb.append(")");
			}
		}

		return sb.toString();
	}

	protected List<Long> getSearchByUserRoleIds(
			KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery)
		throws Exception {

		List<Long> roleIds = RoleUtil.getRoleIds(
			kaleoTaskInstanceTokenQuery.getServiceContext());

		User user = UserLocalServiceUtil.getUserById(
			kaleoTaskInstanceTokenQuery.getUserId());

		List<Group> groups = new ArrayList<>();

		groups.addAll(user.getGroups());
		groups.addAll(
			GroupLocalServiceUtil.getOrganizationsGroups(
				user.getOrganizations()));
		groups.addAll(
			GroupLocalServiceUtil.getOrganizationsRelatedGroups(
				user.getOrganizations()));
		groups.addAll(
			GroupLocalServiceUtil.getUserGroupsGroups(user.getUserGroups()));
		groups.addAll(
			GroupLocalServiceUtil.getUserGroupsRelatedGroups(
				user.getUserGroups()));

		for (Group group : groups) {
			List<Role> roles = RoleLocalServiceUtil.getGroupRoles(
				group.getGroupId());

			for (Role role : roles) {
				roleIds.add(role.getRoleId());
			}
		}

		return roleIds;
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
			List<Long> roleIds = getSearchByUserRoleIds(
				kaleoTaskInstanceTokenQuery);

			List<UserGroupRole> userGroupRoles =
				UserGroupRoleLocalServiceUtil.getUserGroupRoles(
					kaleoTaskInstanceTokenQuery.getUserId());

			List<UserGroupGroupRole> userGroupGroupRoles =
				getUserGroupGroupRoles(kaleoTaskInstanceTokenQuery.getUserId());

			if (roleIds.isEmpty() && userGroupRoles.isEmpty() &&
				userGroupGroupRoles.isEmpty()) {

				return StringPool.BLANK;
			}

			StringBundler sb = new StringBundler();

			sb.append("AND ((");
			sb.append("KaleoTaskAssignmentInstance.assigneeClassName = ?) ");
			sb.append("AND (");

			for (int i = 0; i < roleIds.size(); i++) {
				sb.append("(KaleoTaskAssignmentInstance.assigneeClassPK = ?)");
				sb.append(" OR ");
			}

			for (int i = 0; i < userGroupRoles.size(); i++) {
				sb.append("((KaleoTaskAssignmentInstance.groupId = ?) AND ");
				sb.append("(KaleoTaskAssignmentInstance.assigneeClassPK = ?))");
				sb.append(" OR ");
			}

			for (int i = 0; i < userGroupGroupRoles.size(); i++) {
				sb.append("((KaleoTaskAssignmentInstance.groupId = ?) AND ");
				sb.append("(KaleoTaskAssignmentInstance.assigneeClassPK = ?))");
				sb.append(" OR ");
			}

			sb.setIndex(sb.index() - 1);

			sb.append("))");

			return sb.toString();
		}

		StringBundler sb = new StringBundler(3);

		sb.append("AND ((");
		sb.append("KaleoTaskAssignmentInstance.assigneeClassName = ?) ");
		sb.append("AND (KaleoTaskAssignmentInstance.assigneeClassPK = ?))");

		return sb.toString();
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return KaleoTaskInstanceTokenModelImpl.TABLE_COLUMNS_MAP;
	}

	protected String getTaskName(
		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery,
		boolean firstCriteria) {

		String taskName = kaleoTaskInstanceTokenQuery.getTaskName();

		if (Validator.isNull(taskName)) {
			return StringPool.BLANK;
		}

		String[] taskNames = CustomSQLUtil.keywords(taskName, false);

		if (ArrayUtil.isEmpty(taskNames)) {
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
			sb.append(
				"(lower(KaleoTaskInstanceToken.kaleoTaskName) LIKE lower(?))");

			if ((i + 1) < taskNames.length) {
				sb.append(" OR ");
			}
			else {
				sb.append(")");
			}
		}

		return sb.toString();
	}

	protected List<UserGroupGroupRole> getUserGroupGroupRoles(long userId)
		throws Exception {

		List<UserGroupGroupRole> userGroupGroupRoles = new ArrayList<>();

		List<UserGroup> userGroups =
			UserGroupLocalServiceUtil.getUserUserGroups(userId);

		for (UserGroup userGroup : userGroups) {
			userGroupGroupRoles.addAll(
				UserGroupGroupRoleLocalServiceUtil.getUserGroupGroupRoles(
					userGroup.getUserGroupId()));
		}

		return userGroupGroupRoles;
	}

	protected void setAssetPrimaryKey(
		QueryPos qPos,
		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery) {

		Long[] assetPrimaryKeys =
			kaleoTaskInstanceTokenQuery.getAssetPrimaryKeys();

		if (ArrayUtil.isEmpty(assetPrimaryKeys)) {
			return;
		}

		qPos.add(assetPrimaryKeys);
	}

	protected void setAssetType(
		QueryPos qPos,
		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery) {

		String[] assetTypes = kaleoTaskInstanceTokenQuery.getAssetTypes();

		if (ArrayUtil.isEmpty(assetTypes)) {
			return;
		}

		assetTypes = CustomSQLUtil.keywords(assetTypes, false);

		qPos.add(assetTypes);
	}

	protected void setAssigneeClassName(
		QueryPos qPos,
		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery) {

		String assigneeClassName =
			kaleoTaskInstanceTokenQuery.getAssigneeClassName();

		if (Validator.isNull(assigneeClassName)) {
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

	protected void setKaleoInstanceId(
		QueryPos qPos,
		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery) {

		Long kaleoInstanceId = kaleoTaskInstanceTokenQuery.getKaleoInstanceId();

		if (kaleoInstanceId == null) {
			return;
		}

		qPos.add(kaleoInstanceId);
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

			List<Long> roleIds = getSearchByUserRoleIds(
				kaleoTaskInstanceTokenQuery);

			for (Long roleId : roleIds) {
				qPos.add(roleId);
			}

			List<UserGroupRole> userGroupRoles =
				UserGroupRoleLocalServiceUtil.getUserGroupRoles(
					kaleoTaskInstanceTokenQuery.getUserId());

			for (UserGroupRole userGroupRole : userGroupRoles) {
				qPos.add(userGroupRole.getGroupId());
				qPos.add(userGroupRole.getRoleId());
			}

			List<UserGroupGroupRole> userGroupGroupRoles =
				getUserGroupGroupRoles(kaleoTaskInstanceTokenQuery.getUserId());

			for (UserGroupGroupRole userGroupGroupRole : userGroupGroupRoles) {
				qPos.add(userGroupGroupRole.getGroupId());
				qPos.add(userGroupGroupRole.getRoleId());
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

		if (Validator.isNull(taskName)) {
			return;
		}

		String[] taskNames = CustomSQLUtil.keywords(taskName, false);

		qPos.add(taskNames);
	}

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"KaleoTaskInstanceToken.";

}