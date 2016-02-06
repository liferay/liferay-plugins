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

package com.liferay.contacts.service.persistence.impl;

import com.liferay.contacts.model.Entry;
import com.liferay.contacts.model.impl.EntryImpl;
import com.liferay.contacts.service.persistence.EntryFinder;
import com.liferay.contacts.service.persistence.EntryUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.service.persistence.UserFinderUtil;
import com.liferay.portal.kernel.service.persistence.UserUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.comparator.UserLastNameComparator;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class EntryFinderImpl
	extends EntryFinderBaseImpl implements EntryFinder {

	public static final String COUNT_BY_U_FN_EA =
		EntryFinder.class.getName() + ".countByU_FN_EA";

	public static final String FIND_BY_U_FN_EA =
		EntryFinder.class.getName() + ".findByU_FN_EA";

	@Override
	public int countByKeywords(long companyId, long userId, String keywords) {
		if (Validator.isNotNull(keywords)) {
			String[] fullNames = CustomSQLUtil.keywords(keywords);
			String[] emailAddresses = CustomSQLUtil.keywords(keywords);

			int count = UserFinderUtil.countByC_FN_MN_LN_SN_EA_S(
				companyId, fullNames, fullNames, fullNames, fullNames,
				emailAddresses, 0, null, false);

			count += countByU_FN_EA(userId, fullNames, emailAddresses);

			return count;
		}

		int count = UserUtil.countByC_DU_S(companyId, false, 0);

		count += EntryUtil.countByUserId(userId);

		return count;
	}

	@Override
	public int countByKeywords(long userId, String keywords) {
		if (Validator.isNotNull(keywords)) {
			String[] fullNames = CustomSQLUtil.keywords(keywords);
			String[] emailAddresses = CustomSQLUtil.keywords(keywords);

			return countByU_FN_EA(userId, fullNames, emailAddresses);
		}

		return EntryUtil.countByUserId(userId);
	}

	@Override
	public List<BaseModel<?>> findByKeywords(
		long companyId, long userId, String keywords, int start, int end) {

		List<BaseModel<?>> models = new ArrayList<>();

		if (Validator.isNotNull(keywords)) {
			String[] fullNames = CustomSQLUtil.keywords(keywords);
			String[] emailAddresses = CustomSQLUtil.keywords(keywords);

			models.addAll(
				UserFinderUtil.findByC_FN_MN_LN_SN_EA_S(
					companyId, fullNames, fullNames, fullNames, fullNames,
					emailAddresses, 0, null, false, start, end,
					new UserLastNameComparator(true)));

			if (models.size() < (end - start)) {
				int count = UserFinderUtil.countByC_FN_MN_LN_SN_EA_S(
					companyId, fullNames, fullNames, fullNames, fullNames,
					emailAddresses, 0, null, false);

				start -= count;
				end -= count;

				models.addAll(
					findByU_FN_EA(
						userId, fullNames, emailAddresses, start, end));
			}
		}
		else {
			models.addAll(
				UserUtil.findByC_DU_S(
					companyId, false, 0, start, end,
					new UserLastNameComparator(true)));

			if (models.size() < (end - start)) {
				int count = UserUtil.countByC_DU_S(companyId, false, 0);

				start -= count;
				end -= count;

				models.addAll(EntryUtil.findByUserId(userId, start, end));
			}
		}

		return models;
	}

	@Override
	public List<Entry> findByKeywords(
		long userId, String keywords, int start, int end) {

		if (Validator.isNotNull(keywords)) {
			String[] fullNames = CustomSQLUtil.keywords(keywords);
			String[] emailAddresses = CustomSQLUtil.keywords(keywords);

			return findByU_FN_EA(userId, fullNames, emailAddresses, start, end);
		}

		return EntryUtil.findByUserId(userId, start, end);
	}

	protected int countByU_FN_EA(
		long userId, String[] fullNames, String[] emailAddresses) {

		fullNames = CustomSQLUtil.keywords(fullNames, true);
		emailAddresses = CustomSQLUtil.keywords(emailAddresses, true);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_U_FN_EA);

			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(fullName)", StringPool.LIKE, false, fullNames);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(emailAddress)", StringPool.LIKE, true,
				emailAddresses);
			sql = CustomSQLUtil.replaceAndOperator(sql, false);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);
			qPos.add(fullNames, 2);
			qPos.add(emailAddresses, 2);

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

	protected List<Entry> findByU_FN_EA(
		long userId, String[] fullNames, String[] emailAddresses, int start,
		int end) {

		fullNames = CustomSQLUtil.keywords(fullNames, true);
		emailAddresses = CustomSQLUtil.keywords(emailAddresses, true);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_U_FN_EA);

			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(fullName)", StringPool.LIKE, false, fullNames);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(emailAddress)", StringPool.LIKE, true,
				emailAddresses);
			sql = CustomSQLUtil.replaceAndOperator(sql, false);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("Contacts_Entry", EntryImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);
			qPos.add(fullNames, 2);
			qPos.add(emailAddresses, 2);

			return (List<Entry>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

}