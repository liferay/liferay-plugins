/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.microblogs.service.persistence;

import com.liferay.microblogs.model.MicroblogsEntry;
import com.liferay.microblogs.model.impl.MicroblogsEntryImpl;
import com.liferay.microblogs.util.MicroblogsEntryConstants;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Iterator;
import java.util.List;

/**
 * @author Jonathan Lee
 */
public class MicroblogsEntryFinderImpl
	extends BasePersistenceImpl implements MicroblogsEntryFinder {

	public static String COUNT_BY_C_U_T_RU_RE_S_V =
		MicroblogsEntryFinder.class.getName() + ".countByC_U_T_RU_RE_S_V";

	public static String COUNT_BY_T_V=
		MicroblogsEntryFinder.class.getName() + ".countByT_V";

	public static String FIND_BY_C_U_T_RU_RE_S_V =
		MicroblogsEntryFinder.class.getName() + ".findByC_U_T_RU_RE_S_V";

	public static String FIND_BY_T_V =
		MicroblogsEntryFinder.class.getName() + ".findByT_V";

	public int countByC_U_T_RU_RE_S_V(
			long companyId, long[] userIds, int type, long receiverUserId,
			long receiverEntryId, int socialRelationType, long viewerUserId)
		throws SystemException {

		String userIdsString = StringPool.BLANK;

		if (userIds != null) {
			for (long userId : userIds) {
				userIdsString = StringUtil.add(
					userIdsString, String.valueOf(userId));
			}

			if (userIdsString.endsWith(StringPool.COMMA)) {
				userIdsString = userIdsString.substring(
					0, userIdsString.length() - 1);
			}
		}

		Session session = null;

		try {
			session = openSession();

			StringBundler sb = new StringBundler(15);

			sb.append(CustomSQLUtil.get(COUNT_BY_C_U_T_RU_RE_S_V));

			if (userIds != null) {
				sb.append(_AND_USER_IDS);
				sb.append("(" + userIdsString + ")");
			}

			if (companyId > 0) {
				sb.append(_AND_COMPANY_ID);
				sb.append(companyId);
			}

			if (type > 0) {
				sb.append(_AND_TYPE);
				sb.append(type);
			}

			if (receiverUserId > 0) {
				sb.append(_AND_RECEIVER_USER_ID);
				sb.append(receiverUserId);
			}

			if (receiverEntryId > 0) {
				sb.append(_AND_RECEIVER_ENTRY_ID);
				sb.append(receiverEntryId);
			}

			if (socialRelationType > 0) {
				sb.append(_AND_SOCIALRELATION_TYPE);
				sb.append(socialRelationType);
			}

			sb.append(_ORDER_BY);
			sb.append(_CREATE_DATE_DESC);

			String sql = sb.toString();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(MicroblogsEntryConstants.EVERYONE);
			qPos.add(viewerUserId);
			qPos.add(viewerUserId);

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

	public int countByT_V(String[] tagNames, long viewerUserId)
		throws SystemException {

		String tagNamesString = StringPool.BLANK;

		if (tagNames != null) {
			for (String tagName : tagNames) {
				tagNamesString = StringUtil.add(
					tagNamesString, String.valueOf("'" + tagName + "'"));
			}

			if (tagNamesString.endsWith(StringPool.COMMA)) {
				tagNamesString = tagNamesString.substring(
					0, tagNamesString.length() - 1);
			}
		}

		Session session = null;

		try {
			session = openSession();

			StringBundler sb = new StringBundler(5);

			sb.append(CustomSQLUtil.get(COUNT_BY_T_V));

			if (tagNames != null) {
				sb.append(_AND_TAG_IDS);
				sb.append("(" + tagNamesString + ")");
			}

			sb.append(_ORDER_BY);
			sb.append(_CREATE_DATE_DESC);

			String sql = sb.toString();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(MicroblogsEntryConstants.EVERYONE);
			qPos.add(viewerUserId);
			qPos.add(viewerUserId);

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

	public List<MicroblogsEntry> findByC_U_T_RU_RE_S_V(
			long companyId, long[] userIds, int type, long receiverUserId,
			long receiverEntryId, int socialRelationType, long viewerUserId,
			int start, int end)
		throws SystemException {

		String userIdsString = StringPool.BLANK;

		if (userIds != null) {
			for (long userId : userIds) {
				userIdsString = StringUtil.add(
					userIdsString, String.valueOf(userId));
			}

			if (userIdsString.endsWith(StringPool.COMMA)) {
				userIdsString = userIdsString.substring(
					0, userIdsString.length() - 1);
			}
		}

		Session session = null;

		try {
			session = openSession();

			StringBundler sb = new StringBundler(17);

			sb.append(CustomSQLUtil.get(FIND_BY_C_U_T_RU_RE_S_V));

			if (userIds != null) {
				sb.append(_AND_USER_IDS);
				sb.append("(" + userIdsString + ")");
			}

			if (companyId > 0) {
				sb.append(_AND_COMPANY_ID);
				sb.append(companyId);
			}

			if (type > 0) {
				sb.append(_AND_TYPE);
				sb.append(type);
			}

			if (receiverUserId > 0) {
				sb.append(_AND_RECEIVER_USER_ID);
				sb.append(receiverUserId);
			}

			if (receiverEntryId > 0) {
				sb.append(_AND_RECEIVER_ENTRY_ID);
				sb.append(receiverEntryId);
			}

			if (socialRelationType > 0) {
				sb.append(_AND_SOCIALRELATION_TYPE);
				sb.append(socialRelationType);
			}

			sb.append(_GROUP_BY);
			sb.append(_MICROBLOGS_ENTRY_ID);
			sb.append(_ORDER_BY);
			sb.append(_CREATE_DATE_DESC);

			String sql = sb.toString();

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity(
				"Microblogs_MicroblogsEntry", MicroblogsEntryImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(MicroblogsEntryConstants.EVERYONE);
			qPos.add(viewerUserId);
			qPos.add(viewerUserId);

			return (List<MicroblogsEntry>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<MicroblogsEntry> findByT_V(
			String[] tagNames, long viewerUserId, int start, int end)
		throws SystemException {

		String tagNamesString = StringPool.BLANK;

		if (tagNames != null) {
			for (String tagName : tagNames) {
				tagNamesString = StringUtil.add(
					tagNamesString, String.valueOf("'" + tagName + "'"));
			}

			if (tagNamesString.endsWith(StringPool.COMMA)) {
				tagNamesString = tagNamesString.substring(
					0, tagNamesString.length() - 1);
			}
		}

		Session session = null;

		try {
			session = openSession();

			StringBundler sb = new StringBundler(7);

			sb.append(CustomSQLUtil.get(FIND_BY_T_V));

			if (tagNames != null) {
				sb.append(_AND_TAG_IDS);
				sb.append("(" + tagNamesString + ")");
			}

			sb.append(_GROUP_BY);
			sb.append(_MICROBLOGS_ENTRY_ID);
			sb.append(_ORDER_BY);
			sb.append(_CREATE_DATE_DESC);

			String sql = sb.toString();

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity(
				"Microblogs_MicroblogsEntry", MicroblogsEntryImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(MicroblogsEntryConstants.EVERYONE);
			qPos.add(viewerUserId);
			qPos.add(viewerUserId);

			return (List<MicroblogsEntry>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _AND_COMPANY_ID =
		" AND Microblogs_MicroblogsEntry.companyId = ";

	private static final String _AND_RECEIVER_ENTRY_ID =
		" AND Microblogs_MicroblogsEntry.receiverEntryId = ";

	private static final String _AND_RECEIVER_USER_ID =
		" AND Microblogs_MicroblogsEntry.receiverUserId = ";

	private static final String _AND_SOCIALRELATION_TYPE =
		" AND Microblogs_MicroblogsEntry.socialRelationType = ";

	private static final String _AND_TAG_IDS = " AND AssetTag.name IN ";

	private static final String _AND_TYPE =
		" AND Microblogs_MicroblogsEntry.type_ = ";

	private static final String _AND_USER_IDS =
		" AND Microblogs_MicroblogsEntry.userId IN ";

	private static final String _CREATE_DATE_DESC =
		" Microblogs_MicroblogsEntry.createDate DESC ";

	private static final String _GROUP_BY = " GROUP BY ";

	private static final String _MICROBLOGS_ENTRY_ID =
		" Microblogs_MicroblogsEntry.microblogsEntryId ";

	private static final String _ORDER_BY = " ORDER BY ";

}