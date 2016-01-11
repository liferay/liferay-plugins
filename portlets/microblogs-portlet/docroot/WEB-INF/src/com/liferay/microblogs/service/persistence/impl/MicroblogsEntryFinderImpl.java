/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.microblogs.service.persistence.impl;

import com.liferay.microblogs.model.MicroblogsEntry;
import com.liferay.microblogs.model.MicroblogsEntryConstants;
import com.liferay.microblogs.model.impl.MicroblogsEntryImpl;
import com.liferay.microblogs.service.MicroblogsEntryLocalServiceUtil;
import com.liferay.microblogs.service.persistence.MicroblogsEntryFinder;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.social.model.SocialRelationConstants;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Jonathan Lee
 */
public class MicroblogsEntryFinderImpl
	extends MicroblogsEntryFinderBaseImpl implements MicroblogsEntryFinder {

	public static final String COUNT_BY_USER_ID =
		MicroblogsEntryFinder.class.getName() + ".countByUserId";

	public static final String COUNT_BY_U_MU =
		MicroblogsEntryFinder.class.getName() + ".countByU_MU";

	public static final String COUNT_BY_U_ATN =
		MicroblogsEntryFinder.class.getName() + ".countByU_ATN";

	public static final String COUNT_BY_CCNI_ATN =
		MicroblogsEntryFinder.class.getName() + ".countByCCNI_ATN";

	public static final String COUNT_BY_U_T_MU =
		MicroblogsEntryFinder.class.getName() + ".countByU_T_MU";

	public static final String COUNT_BY_CCNI_CCPK_ATN =
		MicroblogsEntryFinder.class.getName() + ".countByCCNI_CCPK_ATN";

	public static final String FIND_BY_USER_ID =
		MicroblogsEntryFinder.class.getName() + ".findByUserId";

	public static final String FIND_BY_U_MU =
		MicroblogsEntryFinder.class.getName() + ".findByU_MU";

	public static final String FIND_BY_U_ATN =
		MicroblogsEntryFinder.class.getName() + ".findByU_ATN";

	public static final String FIND_BY_CCNI_ATN =
		MicroblogsEntryFinder.class.getName() + ".findByCCNI_ATN";

	public static final String FIND_BY_U_T_MU =
		MicroblogsEntryFinder.class.getName() + ".findByU_T_MU";

	public static final String FIND_BY_CCNI_CCPK_ATN =
		MicroblogsEntryFinder.class.getName() + ".findByCCNI_CCPK_ATN";

	public int countByUserId(long userId) {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_USER_ID);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(MicroblogsEntryConstants.TYPE_EVERYONE);
			qPos.add(userId);
			qPos.add(SocialRelationConstants.TYPE_UNI_ENEMY);
			qPos.add(userId);
			qPos.add(userId);
			qPos.add(MicroblogsEntryConstants.TYPE_REPLY);

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

	public int countByU_MU(long userId, long microblogsEntryUserId) {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_U_MU);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(MicroblogsEntryConstants.TYPE_EVERYONE);
			qPos.add(userId);
			qPos.add(microblogsEntryUserId);
			qPos.add(MicroblogsEntryConstants.TYPE_REPLY);

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

	public int countByU_ATN(long userId, String assetTagName) {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_U_ATN);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

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

	public int countByCCNI_ATN(long creatorClassNameId, String assetTagName) {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_CCNI_ATN);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(creatorClassNameId);
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

	public int countByU_T_MU(
		long userId, int type, long microblogsEntryUserId) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_U_T_MU);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

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

	public int countByCCNI_CCPK_ATN(
		long creatorClassNameId, long creatorClassPK, String assetTagName,
		boolean andOperator) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_CCNI_CCPK_ATN);

			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(creatorClassNameId);
			qPos.add(creatorClassPK);
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

	public List<MicroblogsEntry> findByUserId(long userId, int start, int end) {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_USER_ID);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar("microblogsEntryId", Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(MicroblogsEntryConstants.TYPE_EVERYONE);
			qPos.add(userId);
			qPos.add(SocialRelationConstants.TYPE_UNI_ENEMY);
			qPos.add(userId);
			qPos.add(userId);
			qPos.add(MicroblogsEntryConstants.TYPE_REPLY);

			Iterator<Long> itr = (Iterator<Long>)QueryUtil.iterate(
				q, getDialect(), start, end);

			List<MicroblogsEntry> microblogsEntries = new ArrayList<>();

			while (itr.hasNext()) {
				microblogsEntries.add(
					MicroblogsEntryLocalServiceUtil.fetchMicroblogsEntry(
						(Long)itr.next()));
			}

			return microblogsEntries;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<MicroblogsEntry> findByU_MU(
		long userId, long microblogsEntryUserId, int start, int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_U_MU);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("MicroblogsEntry", MicroblogsEntryImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(MicroblogsEntryConstants.TYPE_EVERYONE);
			qPos.add(userId);
			qPos.add(microblogsEntryUserId);
			qPos.add(MicroblogsEntryConstants.TYPE_REPLY);

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
		long userId, String assetTagName, int start, int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_U_ATN);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

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

	public List<MicroblogsEntry> findByCCNI_ATN(
		long creatorClassNameId, String assetTagName, int start, int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_CCNI_ATN);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("MicroblogsEntry", MicroblogsEntryImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(creatorClassNameId);
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
		long userId, int type, long microblogsEntryUserId, int start, int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_U_T_MU);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

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

	public List<MicroblogsEntry> findByCCNI_CCPK_ATN(
		long creatorClassNameId, long creatorClassPK, String assetTagName,
		boolean andOperator, int start, int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_CCNI_CCPK_ATN);

			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("MicroblogsEntry", MicroblogsEntryImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(creatorClassNameId);
			qPos.add(creatorClassPK);
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

}