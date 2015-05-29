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

package com.liferay.mail.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.mail.NoSuchFolderException;
import com.liferay.mail.model.Folder;
import com.liferay.mail.model.impl.FolderImpl;
import com.liferay.mail.model.impl.FolderModelImpl;
import com.liferay.mail.service.persistence.FolderPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextThreadLocal;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the folder service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FolderPersistence
 * @see com.liferay.mail.service.persistence.FolderUtil
 * @generated
 */
@ProviderType
public class FolderPersistenceImpl extends BasePersistenceImpl<Folder>
	implements FolderPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link FolderUtil} to access the folder persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = FolderImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(FolderModelImpl.ENTITY_CACHE_ENABLED,
			FolderModelImpl.FINDER_CACHE_ENABLED, FolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(FolderModelImpl.ENTITY_CACHE_ENABLED,
			FolderModelImpl.FINDER_CACHE_ENABLED, FolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FolderModelImpl.ENTITY_CACHE_ENABLED,
			FolderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTID =
		new FinderPath(FolderModelImpl.ENTITY_CACHE_ENABLED,
			FolderModelImpl.FINDER_CACHE_ENABLED, FolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAccountId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTID =
		new FinderPath(FolderModelImpl.ENTITY_CACHE_ENABLED,
			FolderModelImpl.FINDER_CACHE_ENABLED, FolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAccountId",
			new String[] { Long.class.getName() },
			FolderModelImpl.ACCOUNTID_COLUMN_BITMASK |
			FolderModelImpl.FULLNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACCOUNTID = new FinderPath(FolderModelImpl.ENTITY_CACHE_ENABLED,
			FolderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the folders where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the matching folders
	 */
	@Override
	public List<Folder> findByAccountId(long accountId) {
		return findByAccountId(accountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the folders where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of folders
	 * @param end the upper bound of the range of folders (not inclusive)
	 * @return the range of matching folders
	 */
	@Override
	public List<Folder> findByAccountId(long accountId, int start, int end) {
		return findByAccountId(accountId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the folders where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of folders
	 * @param end the upper bound of the range of folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching folders
	 */
	@Override
	public List<Folder> findByAccountId(long accountId, int start, int end,
		OrderByComparator<Folder> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTID;
			finderArgs = new Object[] { accountId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTID;
			finderArgs = new Object[] { accountId, start, end, orderByComparator };
		}

		List<Folder> list = (List<Folder>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Folder folder : list) {
				if ((accountId != folder.getAccountId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_FOLDER_WHERE);

			query.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(FolderModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountId);

				if (!pagination) {
					list = (List<Folder>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Folder>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first folder in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching folder
	 * @throws NoSuchFolderException if a matching folder could not be found
	 */
	@Override
	public Folder findByAccountId_First(long accountId,
		OrderByComparator<Folder> orderByComparator)
		throws NoSuchFolderException {
		Folder folder = fetchByAccountId_First(accountId, orderByComparator);

		if (folder != null) {
			return folder;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountId=");
		msg.append(accountId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFolderException(msg.toString());
	}

	/**
	 * Returns the first folder in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching folder, or <code>null</code> if a matching folder could not be found
	 */
	@Override
	public Folder fetchByAccountId_First(long accountId,
		OrderByComparator<Folder> orderByComparator) {
		List<Folder> list = findByAccountId(accountId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last folder in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching folder
	 * @throws NoSuchFolderException if a matching folder could not be found
	 */
	@Override
	public Folder findByAccountId_Last(long accountId,
		OrderByComparator<Folder> orderByComparator)
		throws NoSuchFolderException {
		Folder folder = fetchByAccountId_Last(accountId, orderByComparator);

		if (folder != null) {
			return folder;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountId=");
		msg.append(accountId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFolderException(msg.toString());
	}

	/**
	 * Returns the last folder in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching folder, or <code>null</code> if a matching folder could not be found
	 */
	@Override
	public Folder fetchByAccountId_Last(long accountId,
		OrderByComparator<Folder> orderByComparator) {
		int count = countByAccountId(accountId);

		if (count == 0) {
			return null;
		}

		List<Folder> list = findByAccountId(accountId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the folders before and after the current folder in the ordered set where accountId = &#63;.
	 *
	 * @param folderId the primary key of the current folder
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next folder
	 * @throws NoSuchFolderException if a folder with the primary key could not be found
	 */
	@Override
	public Folder[] findByAccountId_PrevAndNext(long folderId, long accountId,
		OrderByComparator<Folder> orderByComparator)
		throws NoSuchFolderException {
		Folder folder = findByPrimaryKey(folderId);

		Session session = null;

		try {
			session = openSession();

			Folder[] array = new FolderImpl[3];

			array[0] = getByAccountId_PrevAndNext(session, folder, accountId,
					orderByComparator, true);

			array[1] = folder;

			array[2] = getByAccountId_PrevAndNext(session, folder, accountId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Folder getByAccountId_PrevAndNext(Session session, Folder folder,
		long accountId, OrderByComparator<Folder> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_FOLDER_WHERE);

		query.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(FolderModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(folder);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Folder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the folders where accountId = &#63; from the database.
	 *
	 * @param accountId the account ID
	 */
	@Override
	public void removeByAccountId(long accountId) {
		for (Folder folder : findByAccountId(accountId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(folder);
		}
	}

	/**
	 * Returns the number of folders where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the number of matching folders
	 */
	@Override
	public int countByAccountId(long accountId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ACCOUNTID;

		Object[] finderArgs = new Object[] { accountId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_FOLDER_WHERE);

			query.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2 = "folder.accountId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_A_F = new FinderPath(FolderModelImpl.ENTITY_CACHE_ENABLED,
			FolderModelImpl.FINDER_CACHE_ENABLED, FolderImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByA_F",
			new String[] { Long.class.getName(), String.class.getName() },
			FolderModelImpl.ACCOUNTID_COLUMN_BITMASK |
			FolderModelImpl.FULLNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_A_F = new FinderPath(FolderModelImpl.ENTITY_CACHE_ENABLED,
			FolderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByA_F",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the folder where accountId = &#63; and fullName = &#63; or throws a {@link NoSuchFolderException} if it could not be found.
	 *
	 * @param accountId the account ID
	 * @param fullName the full name
	 * @return the matching folder
	 * @throws NoSuchFolderException if a matching folder could not be found
	 */
	@Override
	public Folder findByA_F(long accountId, String fullName)
		throws NoSuchFolderException {
		Folder folder = fetchByA_F(accountId, fullName);

		if (folder == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("accountId=");
			msg.append(accountId);

			msg.append(", fullName=");
			msg.append(fullName);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchFolderException(msg.toString());
		}

		return folder;
	}

	/**
	 * Returns the folder where accountId = &#63; and fullName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accountId the account ID
	 * @param fullName the full name
	 * @return the matching folder, or <code>null</code> if a matching folder could not be found
	 */
	@Override
	public Folder fetchByA_F(long accountId, String fullName) {
		return fetchByA_F(accountId, fullName, true);
	}

	/**
	 * Returns the folder where accountId = &#63; and fullName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accountId the account ID
	 * @param fullName the full name
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching folder, or <code>null</code> if a matching folder could not be found
	 */
	@Override
	public Folder fetchByA_F(long accountId, String fullName,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { accountId, fullName };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_A_F,
					finderArgs, this);
		}

		if (result instanceof Folder) {
			Folder folder = (Folder)result;

			if ((accountId != folder.getAccountId()) ||
					!Validator.equals(fullName, folder.getFullName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_FOLDER_WHERE);

			query.append(_FINDER_COLUMN_A_F_ACCOUNTID_2);

			boolean bindFullName = false;

			if (fullName == null) {
				query.append(_FINDER_COLUMN_A_F_FULLNAME_1);
			}
			else if (fullName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_A_F_FULLNAME_3);
			}
			else {
				bindFullName = true;

				query.append(_FINDER_COLUMN_A_F_FULLNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountId);

				if (bindFullName) {
					qPos.add(fullName);
				}

				List<Folder> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_A_F,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"FolderPersistenceImpl.fetchByA_F(long, String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					Folder folder = list.get(0);

					result = folder;

					cacheResult(folder);

					if ((folder.getAccountId() != accountId) ||
							(folder.getFullName() == null) ||
							!folder.getFullName().equals(fullName)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_A_F,
							finderArgs, folder);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_A_F,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (Folder)result;
		}
	}

	/**
	 * Removes the folder where accountId = &#63; and fullName = &#63; from the database.
	 *
	 * @param accountId the account ID
	 * @param fullName the full name
	 * @return the folder that was removed
	 */
	@Override
	public Folder removeByA_F(long accountId, String fullName)
		throws NoSuchFolderException {
		Folder folder = findByA_F(accountId, fullName);

		return remove(folder);
	}

	/**
	 * Returns the number of folders where accountId = &#63; and fullName = &#63;.
	 *
	 * @param accountId the account ID
	 * @param fullName the full name
	 * @return the number of matching folders
	 */
	@Override
	public int countByA_F(long accountId, String fullName) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_A_F;

		Object[] finderArgs = new Object[] { accountId, fullName };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_FOLDER_WHERE);

			query.append(_FINDER_COLUMN_A_F_ACCOUNTID_2);

			boolean bindFullName = false;

			if (fullName == null) {
				query.append(_FINDER_COLUMN_A_F_FULLNAME_1);
			}
			else if (fullName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_A_F_FULLNAME_3);
			}
			else {
				bindFullName = true;

				query.append(_FINDER_COLUMN_A_F_FULLNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountId);

				if (bindFullName) {
					qPos.add(fullName);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_A_F_ACCOUNTID_2 = "folder.accountId = ? AND ";
	private static final String _FINDER_COLUMN_A_F_FULLNAME_1 = "folder.fullName IS NULL";
	private static final String _FINDER_COLUMN_A_F_FULLNAME_2 = "folder.fullName = ?";
	private static final String _FINDER_COLUMN_A_F_FULLNAME_3 = "(folder.fullName IS NULL OR folder.fullName = '')";

	public FolderPersistenceImpl() {
		setModelClass(Folder.class);
	}

	/**
	 * Caches the folder in the entity cache if it is enabled.
	 *
	 * @param folder the folder
	 */
	@Override
	public void cacheResult(Folder folder) {
		EntityCacheUtil.putResult(FolderModelImpl.ENTITY_CACHE_ENABLED,
			FolderImpl.class, folder.getPrimaryKey(), folder);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_A_F,
			new Object[] { folder.getAccountId(), folder.getFullName() }, folder);

		folder.resetOriginalValues();
	}

	/**
	 * Caches the folders in the entity cache if it is enabled.
	 *
	 * @param folders the folders
	 */
	@Override
	public void cacheResult(List<Folder> folders) {
		for (Folder folder : folders) {
			if (EntityCacheUtil.getResult(
						FolderModelImpl.ENTITY_CACHE_ENABLED, FolderImpl.class,
						folder.getPrimaryKey()) == null) {
				cacheResult(folder);
			}
			else {
				folder.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all folders.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(FolderImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the folder.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Folder folder) {
		EntityCacheUtil.removeResult(FolderModelImpl.ENTITY_CACHE_ENABLED,
			FolderImpl.class, folder.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(folder);
	}

	@Override
	public void clearCache(List<Folder> folders) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Folder folder : folders) {
			EntityCacheUtil.removeResult(FolderModelImpl.ENTITY_CACHE_ENABLED,
				FolderImpl.class, folder.getPrimaryKey());

			clearUniqueFindersCache(folder);
		}
	}

	protected void cacheUniqueFindersCache(Folder folder) {
		if (folder.isNew()) {
			Object[] args = new Object[] {
					folder.getAccountId(), folder.getFullName()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_A_F, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_A_F, args, folder);
		}
		else {
			FolderModelImpl folderModelImpl = (FolderModelImpl)folder;

			if ((folderModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_A_F.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						folder.getAccountId(), folder.getFullName()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_A_F, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_A_F, args, folder);
			}
		}
	}

	protected void clearUniqueFindersCache(Folder folder) {
		FolderModelImpl folderModelImpl = (FolderModelImpl)folder;

		Object[] args = new Object[] { folder.getAccountId(), folder.getFullName() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_A_F, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_A_F, args);

		if ((folderModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_A_F.getColumnBitmask()) != 0) {
			args = new Object[] {
					folderModelImpl.getOriginalAccountId(),
					folderModelImpl.getOriginalFullName()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_A_F, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_A_F, args);
		}
	}

	/**
	 * Creates a new folder with the primary key. Does not add the folder to the database.
	 *
	 * @param folderId the primary key for the new folder
	 * @return the new folder
	 */
	@Override
	public Folder create(long folderId) {
		Folder folder = new FolderImpl();

		folder.setNew(true);
		folder.setPrimaryKey(folderId);

		return folder;
	}

	/**
	 * Removes the folder with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param folderId the primary key of the folder
	 * @return the folder that was removed
	 * @throws NoSuchFolderException if a folder with the primary key could not be found
	 */
	@Override
	public Folder remove(long folderId) throws NoSuchFolderException {
		return remove((Serializable)folderId);
	}

	/**
	 * Removes the folder with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the folder
	 * @return the folder that was removed
	 * @throws NoSuchFolderException if a folder with the primary key could not be found
	 */
	@Override
	public Folder remove(Serializable primaryKey) throws NoSuchFolderException {
		Session session = null;

		try {
			session = openSession();

			Folder folder = (Folder)session.get(FolderImpl.class, primaryKey);

			if (folder == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFolderException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(folder);
		}
		catch (NoSuchFolderException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Folder removeImpl(Folder folder) {
		folder = toUnwrappedModel(folder);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(folder)) {
				folder = (Folder)session.get(FolderImpl.class,
						folder.getPrimaryKeyObj());
			}

			if (folder != null) {
				session.delete(folder);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (folder != null) {
			clearCache(folder);
		}

		return folder;
	}

	@Override
	public Folder updateImpl(Folder folder) {
		folder = toUnwrappedModel(folder);

		boolean isNew = folder.isNew();

		FolderModelImpl folderModelImpl = (FolderModelImpl)folder;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (folder.getCreateDate() == null)) {
			if (serviceContext == null) {
				folder.setCreateDate(now);
			}
			else {
				folder.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!folderModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				folder.setModifiedDate(now);
			}
			else {
				folder.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (folder.isNew()) {
				session.save(folder);

				folder.setNew(false);
			}
			else {
				session.merge(folder);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !FolderModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((folderModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						folderModelImpl.getOriginalAccountId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTID,
					args);

				args = new Object[] { folderModelImpl.getAccountId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTID,
					args);
			}
		}

		EntityCacheUtil.putResult(FolderModelImpl.ENTITY_CACHE_ENABLED,
			FolderImpl.class, folder.getPrimaryKey(), folder, false);

		clearUniqueFindersCache(folder);
		cacheUniqueFindersCache(folder);

		folder.resetOriginalValues();

		return folder;
	}

	protected Folder toUnwrappedModel(Folder folder) {
		if (folder instanceof FolderImpl) {
			return folder;
		}

		FolderImpl folderImpl = new FolderImpl();

		folderImpl.setNew(folder.isNew());
		folderImpl.setPrimaryKey(folder.getPrimaryKey());

		folderImpl.setFolderId(folder.getFolderId());
		folderImpl.setCompanyId(folder.getCompanyId());
		folderImpl.setUserId(folder.getUserId());
		folderImpl.setUserName(folder.getUserName());
		folderImpl.setCreateDate(folder.getCreateDate());
		folderImpl.setModifiedDate(folder.getModifiedDate());
		folderImpl.setAccountId(folder.getAccountId());
		folderImpl.setFullName(folder.getFullName());
		folderImpl.setDisplayName(folder.getDisplayName());
		folderImpl.setRemoteMessageCount(folder.getRemoteMessageCount());

		return folderImpl;
	}

	/**
	 * Returns the folder with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the folder
	 * @return the folder
	 * @throws NoSuchFolderException if a folder with the primary key could not be found
	 */
	@Override
	public Folder findByPrimaryKey(Serializable primaryKey)
		throws NoSuchFolderException {
		Folder folder = fetchByPrimaryKey(primaryKey);

		if (folder == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFolderException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return folder;
	}

	/**
	 * Returns the folder with the primary key or throws a {@link NoSuchFolderException} if it could not be found.
	 *
	 * @param folderId the primary key of the folder
	 * @return the folder
	 * @throws NoSuchFolderException if a folder with the primary key could not be found
	 */
	@Override
	public Folder findByPrimaryKey(long folderId) throws NoSuchFolderException {
		return findByPrimaryKey((Serializable)folderId);
	}

	/**
	 * Returns the folder with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the folder
	 * @return the folder, or <code>null</code> if a folder with the primary key could not be found
	 */
	@Override
	public Folder fetchByPrimaryKey(Serializable primaryKey) {
		Folder folder = (Folder)EntityCacheUtil.getResult(FolderModelImpl.ENTITY_CACHE_ENABLED,
				FolderImpl.class, primaryKey);

		if (folder == _nullFolder) {
			return null;
		}

		if (folder == null) {
			Session session = null;

			try {
				session = openSession();

				folder = (Folder)session.get(FolderImpl.class, primaryKey);

				if (folder != null) {
					cacheResult(folder);
				}
				else {
					EntityCacheUtil.putResult(FolderModelImpl.ENTITY_CACHE_ENABLED,
						FolderImpl.class, primaryKey, _nullFolder);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(FolderModelImpl.ENTITY_CACHE_ENABLED,
					FolderImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return folder;
	}

	/**
	 * Returns the folder with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param folderId the primary key of the folder
	 * @return the folder, or <code>null</code> if a folder with the primary key could not be found
	 */
	@Override
	public Folder fetchByPrimaryKey(long folderId) {
		return fetchByPrimaryKey((Serializable)folderId);
	}

	@Override
	public Map<Serializable, Folder> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Folder> map = new HashMap<Serializable, Folder>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Folder folder = fetchByPrimaryKey(primaryKey);

			if (folder != null) {
				map.put(primaryKey, folder);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Folder folder = (Folder)EntityCacheUtil.getResult(FolderModelImpl.ENTITY_CACHE_ENABLED,
					FolderImpl.class, primaryKey);

			if (folder == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, folder);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_FOLDER_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append(String.valueOf(primaryKey));

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (Folder folder : (List<Folder>)q.list()) {
				map.put(folder.getPrimaryKeyObj(), folder);

				cacheResult(folder);

				uncachedPrimaryKeys.remove(folder.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(FolderModelImpl.ENTITY_CACHE_ENABLED,
					FolderImpl.class, primaryKey, _nullFolder);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the folders.
	 *
	 * @return the folders
	 */
	@Override
	public List<Folder> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the folders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of folders
	 * @param end the upper bound of the range of folders (not inclusive)
	 * @return the range of folders
	 */
	@Override
	public List<Folder> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the folders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of folders
	 * @param end the upper bound of the range of folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of folders
	 */
	@Override
	public List<Folder> findAll(int start, int end,
		OrderByComparator<Folder> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<Folder> list = (List<Folder>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_FOLDER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_FOLDER;

				if (pagination) {
					sql = sql.concat(FolderModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Folder>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Folder>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the folders from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Folder folder : findAll()) {
			remove(folder);
		}
	}

	/**
	 * Returns the number of folders.
	 *
	 * @return the number of folders
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_FOLDER);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the folder persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(FolderImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_FOLDER = "SELECT folder FROM Folder folder";
	private static final String _SQL_SELECT_FOLDER_WHERE_PKS_IN = "SELECT folder FROM Folder folder WHERE folderId IN (";
	private static final String _SQL_SELECT_FOLDER_WHERE = "SELECT folder FROM Folder folder WHERE ";
	private static final String _SQL_COUNT_FOLDER = "SELECT COUNT(folder) FROM Folder folder";
	private static final String _SQL_COUNT_FOLDER_WHERE = "SELECT COUNT(folder) FROM Folder folder WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "folder.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Folder exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Folder exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(FolderPersistenceImpl.class);
	private static final Folder _nullFolder = new FolderImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Folder> toCacheModel() {
				return _nullFolderCacheModel;
			}
		};

	private static final CacheModel<Folder> _nullFolderCacheModel = new CacheModel<Folder>() {
			@Override
			public Folder toEntityModel() {
				return _nullFolder;
			}
		};
}