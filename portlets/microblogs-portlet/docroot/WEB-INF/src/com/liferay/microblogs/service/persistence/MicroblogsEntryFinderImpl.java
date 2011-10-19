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
import com.liferay.microblogs.model.MicroblogsEntryConstants;
import com.liferay.microblogs.model.impl.MicroblogsEntryImpl;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.PortalClassInvoker;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Iterator;
import java.util.List;

/**
 * @author Jonathan Lee
 */
public class MicroblogsEntryFinderImpl
	extends BasePersistenceImpl<MicroblogsEntry>
	implements MicroblogsEntryFinder {

	public static String COUNT_BY_USER_ID =
		MicroblogsEntryFinder.class.getName() + ".countByUserId";

	public static String COUNT_BY_U_MU =
		MicroblogsEntryFinder.class.getName() + ".countByU_MU";

	public static String COUNT_BY_U_ATN =
	MicroblogsEntryFinder.class.getName() + ".countByU_ATN";

	public static String COUNT_BY_U_T_MU =
		MicroblogsEntryFinder.class.getName() + ".countByU_T_MU";

	public static String FIND_BY_USER_ID =
		MicroblogsEntryFinder.class.getName() + ".findByUserId";

	public static String FIND_BY_U_MU =
		MicroblogsEntryFinder.class.getName() + ".findByU_MU";

	public static String FIND_BY_U_ATN =
	MicroblogsEntryFinder.class.getName() + ".findByU_ATN";

	public static String FIND_BY_U_T_MU =
		MicroblogsEntryFinder.class.getName() + ".findByU_T_MU";

	public MicroblogsEntryFinderImpl() {
		try {
			MethodKey methodKey = new MethodKey(
				"com.liferay.util.dao.orm.CustomSQL", "get", String.class);

			_joinBySocialRelationSQL = (String)PortalClassInvoker.invoke(
				true, methodKey,
				"com.liferay.portal.service.persistence." +
					"UserFinder.joinBySocialRelation");
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public int countByUserId(long userId) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_USER_ID);

			sql = StringUtil.replace(
				sql, "[$JOIN_BY_SOCIAL_RELATION$]", _joinBySocialRelationSQL);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(MicroblogsEntryConstants.TYPE_EVERYONE);
			qPos.add(userId);
			qPos.add(userId);
			qPos.add(userId);

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

	public int countByU_MU(long userId, long microblogsEntryUserId)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_U_MU);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(MicroblogsEntryConstants.TYPE_EVERYONE);
			qPos.add(userId);
			qPos.add(microblogsEntryUserId);

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

	public int countByU_ATN(long userId, String assetTagName)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_U_ATN);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(MicroblogsEntryConstants.TYPE_EVERYONE);
			qPos.add(userId);
			qPos.add(assetTagName);
			qPos.add(userId);
			qPos.add(assetTagName);

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

	public int countByU_T_MU(long userId, int type, long microblogsEntryUserId)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_U_MU);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(MicroblogsEntryConstants.TYPE_EVERYONE);
			qPos.add(userId);
			qPos.add(type);
			qPos.add(microblogsEntryUserId);

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

	public List<MicroblogsEntry> findByUserId(long userId, int start, int end)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_USER_ID);

			sql = StringUtil.replace(
				sql, "[$JOIN_BY_SOCIAL_RELATION$]", _joinBySocialRelationSQL);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("MicroblogsEntry", MicroblogsEntryImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(MicroblogsEntryConstants.TYPE_EVERYONE);
			qPos.add(userId);
			qPos.add(userId);
			qPos.add(userId);

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

	public List<MicroblogsEntry> findByU_MU(
			long userId, long microblogsEntryUserId, int start, int end)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_U_MU);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("MicroblogsEntry", MicroblogsEntryImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(MicroblogsEntryConstants.TYPE_EVERYONE);
			qPos.add(userId);
			qPos.add(microblogsEntryUserId);

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

	public List<MicroblogsEntry> findByU_ATN(
			long userId, String assetTagName, int start, int end)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_U_ATN);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("MicroblogsEntry", MicroblogsEntryImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(MicroblogsEntryConstants.TYPE_EVERYONE);
			qPos.add(userId);
			qPos.add(assetTagName);
			qPos.add(userId);
			qPos.add(assetTagName);

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

	public List<MicroblogsEntry> findByU_T_MU(
			long userId, int type, long microblogsEntryUserId, int start,
			int end)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_U_MU);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("MicroblogsEntry", MicroblogsEntryImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(MicroblogsEntryConstants.TYPE_EVERYONE);
			qPos.add(userId);
			qPos.add(type);
			qPos.add(microblogsEntryUserId);

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

	private static Log _log = LogFactoryUtil.getLog(
		MicroblogsEntryFinderImpl.class);

	private String _joinBySocialRelationSQL;

}