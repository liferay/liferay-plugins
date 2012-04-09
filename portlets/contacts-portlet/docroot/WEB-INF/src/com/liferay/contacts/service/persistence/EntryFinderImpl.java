/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.contacts.service.persistence;

import com.liferay.contacts.model.Entry;
import com.liferay.contacts.model.impl.EntryImpl;
import com.liferay.contacts.service.EntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class EntryFinderImpl
	extends BasePersistenceImpl<Entry> implements EntryFinder {

	public static final String COUNT_BY_U_FN_EA =
		EntryFinder.class.getName() + ".countByU_FN_EA";

	public static final String COUNT_BY_C_U_FN_EA =
		EntryFinder.class.getName() + ".countByC_U_FN_EA";

	public static final String FIND_BY_U_FN_EA =
		EntryFinder.class.getName() + ".findByU_FN_EA";

	public static final String FIND_BY_C_U_FN_EA =
		EntryFinder.class.getName() + ".findByC_U_FN_EA";

	public int countByKeywords(long companyId, long userId, String keywords)
		throws SystemException {

		String[] fullNames = null;
		String[] emailAddresses = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			fullNames = CustomSQLUtil.keywords(keywords);
			emailAddresses = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return countByC_U_FN_EA(
			companyId, userId, fullNames, emailAddresses, andOperator);
	}

	public int countByKeywords(long userId, String keywords)
		throws SystemException {

		String[] fullNames = null;
		String[] emailAddresses = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			fullNames = CustomSQLUtil.keywords(keywords);
			emailAddresses = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return countByU_FN_EA(userId, fullNames, emailAddresses, andOperator);
	}

	public int countByU_FN_EA(
			long userId, String[] fullNames, String[] emailAddresses,
			boolean andOperator)
		throws SystemException {

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
			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			SQLQuery q = session.createSQLQuery(sql);

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

	public int countByC_U_FN_EA(
			long companyId, long userId, String[] fullNames,
			String[] emailAddresses, boolean andOperator)
		throws SystemException {

		fullNames = CustomSQLUtil.keywords(fullNames, true);
		emailAddresses = CustomSQLUtil.keywords(emailAddresses, true);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_C_U_FN_EA);

			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(User_.firstName)", StringPool.LIKE, false,
				fullNames);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(User_.middleName)", StringPool.LIKE, false,
				fullNames);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(User_.lastName)", StringPool.LIKE, false,
				fullNames);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(User_.screenName)", StringPool.LIKE, false,
				fullNames);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(User_.emailAddress)", StringPool.LIKE, true,
				emailAddresses);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(Contacts_Entry.fullName)", StringPool.LIKE, false,
				fullNames);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(Contacts_Entry.emailAddress)", StringPool.LIKE,
				true, emailAddresses);
			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);
			qPos.add(fullNames, 8);
			qPos.add(emailAddresses, 2);
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

	public List<BaseModel<?>> findByKeywords(
			long companyId, long userId, String keywords, int start, int end)
		throws SystemException {

		String[] fullNames = null;
		String[] emailAddresses = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			fullNames = CustomSQLUtil.keywords(keywords);
			emailAddresses = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return findByC_U_FN_EA(
			companyId, userId, fullNames, emailAddresses, andOperator, start,
			end);
	}

	public List<Entry> findByKeywords(
			long userId, String keywords, int start, int end)
		throws SystemException {

		String[] fullNames = null;
		String[] emailAddresses = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			fullNames = CustomSQLUtil.keywords(keywords);
			emailAddresses = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return findByU_FN_EA(
			userId, fullNames, emailAddresses, andOperator, start, end);
	}

	public List<Entry> findByU_FN_EA(
			long userId, String[] fullNames, String[] emailAddresses,
			boolean andOperator, int start, int end)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_U_FN_EA);

			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(fullName)", StringPool.LIKE, false, fullNames);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(emailAddress)", StringPool.LIKE, true,
				emailAddresses);
			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			SQLQuery q = session.createSQLQuery(sql);

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

	public List<BaseModel<?>> findByC_U_FN_EA(
			long companyId, long userId, String[] fullNames,
			String[] emailAddresses, boolean andOperator, int start, int end)
		throws SystemException {

		fullNames = CustomSQLUtil.keywords(fullNames, true);
		emailAddresses = CustomSQLUtil.keywords(emailAddresses, true);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_C_U_FN_EA);

			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(User_.firstName)", StringPool.LIKE, false,
				fullNames);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(User_.middleName)", StringPool.LIKE, false,
				fullNames);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(User_.lastName)", StringPool.LIKE, false,
				fullNames);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(User_.screenName)", StringPool.LIKE, false,
				fullNames);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(User_.emailAddress)", StringPool.LIKE, true,
				emailAddresses);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(Contacts_Entry.fullName)", StringPool.LIKE, false,
				fullNames);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(Contacts_Entry.emailAddress)", StringPool.LIKE,
				true, emailAddresses);
			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar("id", Type.LONG);
			q.addScalar("name", Type.STRING);
			q.addScalar("portalUser", Type.INTEGER);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);
			qPos.add(fullNames, 8);
			qPos.add(emailAddresses, 2);
			qPos.add(userId);
			qPos.add(fullNames, 2);
			qPos.add(emailAddresses, 2);

			List<BaseModel<?>> models = new ArrayList<BaseModel<?>>();

			Iterator<Object[]> itr = (Iterator<Object[]>)QueryUtil.iterate(
				q, getDialect(), start, end);

			while (itr.hasNext()) {
				Object[] array = itr.next();

				long id = (Long)array[0];
				//String name = (String)array[1];
				int portalUser = (Integer)array[2];

				BaseModel<?> model = null;

				if (portalUser == 1) {
					model = UserLocalServiceUtil.getUser(id);
				}
				else {
					model = EntryLocalServiceUtil.getEntry(id);
				}

				models.add(model);
			}

			return models;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

}