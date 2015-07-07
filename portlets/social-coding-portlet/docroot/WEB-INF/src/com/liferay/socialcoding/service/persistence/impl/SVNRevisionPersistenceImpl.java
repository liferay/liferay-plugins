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

package com.liferay.socialcoding.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.socialcoding.NoSuchSVNRevisionException;
import com.liferay.socialcoding.model.SVNRevision;
import com.liferay.socialcoding.model.impl.SVNRevisionImpl;
import com.liferay.socialcoding.model.impl.SVNRevisionModelImpl;
import com.liferay.socialcoding.service.persistence.SVNRevisionPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the s v n revision service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SVNRevisionPersistence
 * @see com.liferay.socialcoding.service.persistence.SVNRevisionUtil
 * @generated
 */
@ProviderType
public class SVNRevisionPersistenceImpl extends BasePersistenceImpl<SVNRevision>
	implements SVNRevisionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SVNRevisionUtil} to access the s v n revision persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SVNRevisionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionModelImpl.FINDER_CACHE_ENABLED, SVNRevisionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionModelImpl.FINDER_CACHE_ENABLED, SVNRevisionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SVNUSERID =
		new FinderPath(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionModelImpl.FINDER_CACHE_ENABLED, SVNRevisionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySVNUserId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SVNUSERID =
		new FinderPath(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionModelImpl.FINDER_CACHE_ENABLED, SVNRevisionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySVNUserId",
			new String[] { String.class.getName() },
			SVNRevisionModelImpl.SVNUSERID_COLUMN_BITMASK |
			SVNRevisionModelImpl.REVISIONNUMBER_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SVNUSERID = new FinderPath(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySVNUserId",
			new String[] { String.class.getName() });

	/**
	 * Returns all the s v n revisions where svnUserId = &#63;.
	 *
	 * @param svnUserId the svn user ID
	 * @return the matching s v n revisions
	 */
	@Override
	public List<SVNRevision> findBySVNUserId(String svnUserId) {
		return findBySVNUserId(svnUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the s v n revisions where svnUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SVNRevisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param svnUserId the svn user ID
	 * @param start the lower bound of the range of s v n revisions
	 * @param end the upper bound of the range of s v n revisions (not inclusive)
	 * @return the range of matching s v n revisions
	 */
	@Override
	public List<SVNRevision> findBySVNUserId(String svnUserId, int start,
		int end) {
		return findBySVNUserId(svnUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the s v n revisions where svnUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SVNRevisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param svnUserId the svn user ID
	 * @param start the lower bound of the range of s v n revisions
	 * @param end the upper bound of the range of s v n revisions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching s v n revisions
	 */
	@Override
	public List<SVNRevision> findBySVNUserId(String svnUserId, int start,
		int end, OrderByComparator<SVNRevision> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SVNUSERID;
			finderArgs = new Object[] { svnUserId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SVNUSERID;
			finderArgs = new Object[] { svnUserId, start, end, orderByComparator };
		}

		List<SVNRevision> list = (List<SVNRevision>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SVNRevision svnRevision : list) {
				if (!Validator.equals(svnUserId, svnRevision.getSvnUserId())) {
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

			query.append(_SQL_SELECT_SVNREVISION_WHERE);

			boolean bindSvnUserId = false;

			if (svnUserId == null) {
				query.append(_FINDER_COLUMN_SVNUSERID_SVNUSERID_1);
			}
			else if (svnUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SVNUSERID_SVNUSERID_3);
			}
			else {
				bindSvnUserId = true;

				query.append(_FINDER_COLUMN_SVNUSERID_SVNUSERID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SVNRevisionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSvnUserId) {
					qPos.add(svnUserId);
				}

				if (!pagination) {
					list = (List<SVNRevision>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SVNRevision>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first s v n revision in the ordered set where svnUserId = &#63;.
	 *
	 * @param svnUserId the svn user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching s v n revision
	 * @throws NoSuchSVNRevisionException if a matching s v n revision could not be found
	 */
	@Override
	public SVNRevision findBySVNUserId_First(String svnUserId,
		OrderByComparator<SVNRevision> orderByComparator)
		throws NoSuchSVNRevisionException {
		SVNRevision svnRevision = fetchBySVNUserId_First(svnUserId,
				orderByComparator);

		if (svnRevision != null) {
			return svnRevision;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("svnUserId=");
		msg.append(svnUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSVNRevisionException(msg.toString());
	}

	/**
	 * Returns the first s v n revision in the ordered set where svnUserId = &#63;.
	 *
	 * @param svnUserId the svn user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching s v n revision, or <code>null</code> if a matching s v n revision could not be found
	 */
	@Override
	public SVNRevision fetchBySVNUserId_First(String svnUserId,
		OrderByComparator<SVNRevision> orderByComparator) {
		List<SVNRevision> list = findBySVNUserId(svnUserId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last s v n revision in the ordered set where svnUserId = &#63;.
	 *
	 * @param svnUserId the svn user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching s v n revision
	 * @throws NoSuchSVNRevisionException if a matching s v n revision could not be found
	 */
	@Override
	public SVNRevision findBySVNUserId_Last(String svnUserId,
		OrderByComparator<SVNRevision> orderByComparator)
		throws NoSuchSVNRevisionException {
		SVNRevision svnRevision = fetchBySVNUserId_Last(svnUserId,
				orderByComparator);

		if (svnRevision != null) {
			return svnRevision;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("svnUserId=");
		msg.append(svnUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSVNRevisionException(msg.toString());
	}

	/**
	 * Returns the last s v n revision in the ordered set where svnUserId = &#63;.
	 *
	 * @param svnUserId the svn user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching s v n revision, or <code>null</code> if a matching s v n revision could not be found
	 */
	@Override
	public SVNRevision fetchBySVNUserId_Last(String svnUserId,
		OrderByComparator<SVNRevision> orderByComparator) {
		int count = countBySVNUserId(svnUserId);

		if (count == 0) {
			return null;
		}

		List<SVNRevision> list = findBySVNUserId(svnUserId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the s v n revisions before and after the current s v n revision in the ordered set where svnUserId = &#63;.
	 *
	 * @param svnRevisionId the primary key of the current s v n revision
	 * @param svnUserId the svn user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next s v n revision
	 * @throws NoSuchSVNRevisionException if a s v n revision with the primary key could not be found
	 */
	@Override
	public SVNRevision[] findBySVNUserId_PrevAndNext(long svnRevisionId,
		String svnUserId, OrderByComparator<SVNRevision> orderByComparator)
		throws NoSuchSVNRevisionException {
		SVNRevision svnRevision = findByPrimaryKey(svnRevisionId);

		Session session = null;

		try {
			session = openSession();

			SVNRevision[] array = new SVNRevisionImpl[3];

			array[0] = getBySVNUserId_PrevAndNext(session, svnRevision,
					svnUserId, orderByComparator, true);

			array[1] = svnRevision;

			array[2] = getBySVNUserId_PrevAndNext(session, svnRevision,
					svnUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SVNRevision getBySVNUserId_PrevAndNext(Session session,
		SVNRevision svnRevision, String svnUserId,
		OrderByComparator<SVNRevision> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SVNREVISION_WHERE);

		boolean bindSvnUserId = false;

		if (svnUserId == null) {
			query.append(_FINDER_COLUMN_SVNUSERID_SVNUSERID_1);
		}
		else if (svnUserId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_SVNUSERID_SVNUSERID_3);
		}
		else {
			bindSvnUserId = true;

			query.append(_FINDER_COLUMN_SVNUSERID_SVNUSERID_2);
		}

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
			query.append(SVNRevisionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindSvnUserId) {
			qPos.add(svnUserId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(svnRevision);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SVNRevision> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the s v n revisions where svnUserId = &#63; from the database.
	 *
	 * @param svnUserId the svn user ID
	 */
	@Override
	public void removeBySVNUserId(String svnUserId) {
		for (SVNRevision svnRevision : findBySVNUserId(svnUserId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(svnRevision);
		}
	}

	/**
	 * Returns the number of s v n revisions where svnUserId = &#63;.
	 *
	 * @param svnUserId the svn user ID
	 * @return the number of matching s v n revisions
	 */
	@Override
	public int countBySVNUserId(String svnUserId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SVNUSERID;

		Object[] finderArgs = new Object[] { svnUserId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SVNREVISION_WHERE);

			boolean bindSvnUserId = false;

			if (svnUserId == null) {
				query.append(_FINDER_COLUMN_SVNUSERID_SVNUSERID_1);
			}
			else if (svnUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SVNUSERID_SVNUSERID_3);
			}
			else {
				bindSvnUserId = true;

				query.append(_FINDER_COLUMN_SVNUSERID_SVNUSERID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSvnUserId) {
					qPos.add(svnUserId);
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

	private static final String _FINDER_COLUMN_SVNUSERID_SVNUSERID_1 = "svnRevision.svnUserId IS NULL";
	private static final String _FINDER_COLUMN_SVNUSERID_SVNUSERID_2 = "svnRevision.svnUserId = ?";
	private static final String _FINDER_COLUMN_SVNUSERID_SVNUSERID_3 = "(svnRevision.svnUserId IS NULL OR svnRevision.svnUserId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SVNREPOSITORYID =
		new FinderPath(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionModelImpl.FINDER_CACHE_ENABLED, SVNRevisionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySVNRepositoryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SVNREPOSITORYID =
		new FinderPath(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionModelImpl.FINDER_CACHE_ENABLED, SVNRevisionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySVNRepositoryId",
			new String[] { Long.class.getName() },
			SVNRevisionModelImpl.SVNREPOSITORYID_COLUMN_BITMASK |
			SVNRevisionModelImpl.REVISIONNUMBER_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SVNREPOSITORYID = new FinderPath(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBySVNRepositoryId", new String[] { Long.class.getName() });

	/**
	 * Returns all the s v n revisions where svnRepositoryId = &#63;.
	 *
	 * @param svnRepositoryId the svn repository ID
	 * @return the matching s v n revisions
	 */
	@Override
	public List<SVNRevision> findBySVNRepositoryId(long svnRepositoryId) {
		return findBySVNRepositoryId(svnRepositoryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the s v n revisions where svnRepositoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SVNRevisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param svnRepositoryId the svn repository ID
	 * @param start the lower bound of the range of s v n revisions
	 * @param end the upper bound of the range of s v n revisions (not inclusive)
	 * @return the range of matching s v n revisions
	 */
	@Override
	public List<SVNRevision> findBySVNRepositoryId(long svnRepositoryId,
		int start, int end) {
		return findBySVNRepositoryId(svnRepositoryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the s v n revisions where svnRepositoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SVNRevisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param svnRepositoryId the svn repository ID
	 * @param start the lower bound of the range of s v n revisions
	 * @param end the upper bound of the range of s v n revisions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching s v n revisions
	 */
	@Override
	public List<SVNRevision> findBySVNRepositoryId(long svnRepositoryId,
		int start, int end, OrderByComparator<SVNRevision> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SVNREPOSITORYID;
			finderArgs = new Object[] { svnRepositoryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SVNREPOSITORYID;
			finderArgs = new Object[] {
					svnRepositoryId,
					
					start, end, orderByComparator
				};
		}

		List<SVNRevision> list = (List<SVNRevision>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SVNRevision svnRevision : list) {
				if ((svnRepositoryId != svnRevision.getSvnRepositoryId())) {
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

			query.append(_SQL_SELECT_SVNREVISION_WHERE);

			query.append(_FINDER_COLUMN_SVNREPOSITORYID_SVNREPOSITORYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SVNRevisionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(svnRepositoryId);

				if (!pagination) {
					list = (List<SVNRevision>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SVNRevision>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first s v n revision in the ordered set where svnRepositoryId = &#63;.
	 *
	 * @param svnRepositoryId the svn repository ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching s v n revision
	 * @throws NoSuchSVNRevisionException if a matching s v n revision could not be found
	 */
	@Override
	public SVNRevision findBySVNRepositoryId_First(long svnRepositoryId,
		OrderByComparator<SVNRevision> orderByComparator)
		throws NoSuchSVNRevisionException {
		SVNRevision svnRevision = fetchBySVNRepositoryId_First(svnRepositoryId,
				orderByComparator);

		if (svnRevision != null) {
			return svnRevision;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("svnRepositoryId=");
		msg.append(svnRepositoryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSVNRevisionException(msg.toString());
	}

	/**
	 * Returns the first s v n revision in the ordered set where svnRepositoryId = &#63;.
	 *
	 * @param svnRepositoryId the svn repository ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching s v n revision, or <code>null</code> if a matching s v n revision could not be found
	 */
	@Override
	public SVNRevision fetchBySVNRepositoryId_First(long svnRepositoryId,
		OrderByComparator<SVNRevision> orderByComparator) {
		List<SVNRevision> list = findBySVNRepositoryId(svnRepositoryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last s v n revision in the ordered set where svnRepositoryId = &#63;.
	 *
	 * @param svnRepositoryId the svn repository ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching s v n revision
	 * @throws NoSuchSVNRevisionException if a matching s v n revision could not be found
	 */
	@Override
	public SVNRevision findBySVNRepositoryId_Last(long svnRepositoryId,
		OrderByComparator<SVNRevision> orderByComparator)
		throws NoSuchSVNRevisionException {
		SVNRevision svnRevision = fetchBySVNRepositoryId_Last(svnRepositoryId,
				orderByComparator);

		if (svnRevision != null) {
			return svnRevision;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("svnRepositoryId=");
		msg.append(svnRepositoryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSVNRevisionException(msg.toString());
	}

	/**
	 * Returns the last s v n revision in the ordered set where svnRepositoryId = &#63;.
	 *
	 * @param svnRepositoryId the svn repository ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching s v n revision, or <code>null</code> if a matching s v n revision could not be found
	 */
	@Override
	public SVNRevision fetchBySVNRepositoryId_Last(long svnRepositoryId,
		OrderByComparator<SVNRevision> orderByComparator) {
		int count = countBySVNRepositoryId(svnRepositoryId);

		if (count == 0) {
			return null;
		}

		List<SVNRevision> list = findBySVNRepositoryId(svnRepositoryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the s v n revisions before and after the current s v n revision in the ordered set where svnRepositoryId = &#63;.
	 *
	 * @param svnRevisionId the primary key of the current s v n revision
	 * @param svnRepositoryId the svn repository ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next s v n revision
	 * @throws NoSuchSVNRevisionException if a s v n revision with the primary key could not be found
	 */
	@Override
	public SVNRevision[] findBySVNRepositoryId_PrevAndNext(long svnRevisionId,
		long svnRepositoryId, OrderByComparator<SVNRevision> orderByComparator)
		throws NoSuchSVNRevisionException {
		SVNRevision svnRevision = findByPrimaryKey(svnRevisionId);

		Session session = null;

		try {
			session = openSession();

			SVNRevision[] array = new SVNRevisionImpl[3];

			array[0] = getBySVNRepositoryId_PrevAndNext(session, svnRevision,
					svnRepositoryId, orderByComparator, true);

			array[1] = svnRevision;

			array[2] = getBySVNRepositoryId_PrevAndNext(session, svnRevision,
					svnRepositoryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SVNRevision getBySVNRepositoryId_PrevAndNext(Session session,
		SVNRevision svnRevision, long svnRepositoryId,
		OrderByComparator<SVNRevision> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SVNREVISION_WHERE);

		query.append(_FINDER_COLUMN_SVNREPOSITORYID_SVNREPOSITORYID_2);

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
			query.append(SVNRevisionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(svnRepositoryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(svnRevision);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SVNRevision> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the s v n revisions where svnRepositoryId = &#63; from the database.
	 *
	 * @param svnRepositoryId the svn repository ID
	 */
	@Override
	public void removeBySVNRepositoryId(long svnRepositoryId) {
		for (SVNRevision svnRevision : findBySVNRepositoryId(svnRepositoryId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(svnRevision);
		}
	}

	/**
	 * Returns the number of s v n revisions where svnRepositoryId = &#63;.
	 *
	 * @param svnRepositoryId the svn repository ID
	 * @return the number of matching s v n revisions
	 */
	@Override
	public int countBySVNRepositoryId(long svnRepositoryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SVNREPOSITORYID;

		Object[] finderArgs = new Object[] { svnRepositoryId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SVNREVISION_WHERE);

			query.append(_FINDER_COLUMN_SVNREPOSITORYID_SVNREPOSITORYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(svnRepositoryId);

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

	private static final String _FINDER_COLUMN_SVNREPOSITORYID_SVNREPOSITORYID_2 =
		"svnRevision.svnRepositoryId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SVNU_SVNR =
		new FinderPath(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionModelImpl.FINDER_CACHE_ENABLED, SVNRevisionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySVNU_SVNR",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SVNU_SVNR =
		new FinderPath(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionModelImpl.FINDER_CACHE_ENABLED, SVNRevisionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySVNU_SVNR",
			new String[] { String.class.getName(), Long.class.getName() },
			SVNRevisionModelImpl.SVNUSERID_COLUMN_BITMASK |
			SVNRevisionModelImpl.SVNREPOSITORYID_COLUMN_BITMASK |
			SVNRevisionModelImpl.REVISIONNUMBER_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SVNU_SVNR = new FinderPath(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySVNU_SVNR",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the s v n revisions where svnUserId = &#63; and svnRepositoryId = &#63;.
	 *
	 * @param svnUserId the svn user ID
	 * @param svnRepositoryId the svn repository ID
	 * @return the matching s v n revisions
	 */
	@Override
	public List<SVNRevision> findBySVNU_SVNR(String svnUserId,
		long svnRepositoryId) {
		return findBySVNU_SVNR(svnUserId, svnRepositoryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the s v n revisions where svnUserId = &#63; and svnRepositoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SVNRevisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param svnUserId the svn user ID
	 * @param svnRepositoryId the svn repository ID
	 * @param start the lower bound of the range of s v n revisions
	 * @param end the upper bound of the range of s v n revisions (not inclusive)
	 * @return the range of matching s v n revisions
	 */
	@Override
	public List<SVNRevision> findBySVNU_SVNR(String svnUserId,
		long svnRepositoryId, int start, int end) {
		return findBySVNU_SVNR(svnUserId, svnRepositoryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the s v n revisions where svnUserId = &#63; and svnRepositoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SVNRevisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param svnUserId the svn user ID
	 * @param svnRepositoryId the svn repository ID
	 * @param start the lower bound of the range of s v n revisions
	 * @param end the upper bound of the range of s v n revisions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching s v n revisions
	 */
	@Override
	public List<SVNRevision> findBySVNU_SVNR(String svnUserId,
		long svnRepositoryId, int start, int end,
		OrderByComparator<SVNRevision> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SVNU_SVNR;
			finderArgs = new Object[] { svnUserId, svnRepositoryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SVNU_SVNR;
			finderArgs = new Object[] {
					svnUserId, svnRepositoryId,
					
					start, end, orderByComparator
				};
		}

		List<SVNRevision> list = (List<SVNRevision>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SVNRevision svnRevision : list) {
				if (!Validator.equals(svnUserId, svnRevision.getSvnUserId()) ||
						(svnRepositoryId != svnRevision.getSvnRepositoryId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_SVNREVISION_WHERE);

			boolean bindSvnUserId = false;

			if (svnUserId == null) {
				query.append(_FINDER_COLUMN_SVNU_SVNR_SVNUSERID_1);
			}
			else if (svnUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SVNU_SVNR_SVNUSERID_3);
			}
			else {
				bindSvnUserId = true;

				query.append(_FINDER_COLUMN_SVNU_SVNR_SVNUSERID_2);
			}

			query.append(_FINDER_COLUMN_SVNU_SVNR_SVNREPOSITORYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SVNRevisionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSvnUserId) {
					qPos.add(svnUserId);
				}

				qPos.add(svnRepositoryId);

				if (!pagination) {
					list = (List<SVNRevision>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SVNRevision>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first s v n revision in the ordered set where svnUserId = &#63; and svnRepositoryId = &#63;.
	 *
	 * @param svnUserId the svn user ID
	 * @param svnRepositoryId the svn repository ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching s v n revision
	 * @throws NoSuchSVNRevisionException if a matching s v n revision could not be found
	 */
	@Override
	public SVNRevision findBySVNU_SVNR_First(String svnUserId,
		long svnRepositoryId, OrderByComparator<SVNRevision> orderByComparator)
		throws NoSuchSVNRevisionException {
		SVNRevision svnRevision = fetchBySVNU_SVNR_First(svnUserId,
				svnRepositoryId, orderByComparator);

		if (svnRevision != null) {
			return svnRevision;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("svnUserId=");
		msg.append(svnUserId);

		msg.append(", svnRepositoryId=");
		msg.append(svnRepositoryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSVNRevisionException(msg.toString());
	}

	/**
	 * Returns the first s v n revision in the ordered set where svnUserId = &#63; and svnRepositoryId = &#63;.
	 *
	 * @param svnUserId the svn user ID
	 * @param svnRepositoryId the svn repository ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching s v n revision, or <code>null</code> if a matching s v n revision could not be found
	 */
	@Override
	public SVNRevision fetchBySVNU_SVNR_First(String svnUserId,
		long svnRepositoryId, OrderByComparator<SVNRevision> orderByComparator) {
		List<SVNRevision> list = findBySVNU_SVNR(svnUserId, svnRepositoryId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last s v n revision in the ordered set where svnUserId = &#63; and svnRepositoryId = &#63;.
	 *
	 * @param svnUserId the svn user ID
	 * @param svnRepositoryId the svn repository ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching s v n revision
	 * @throws NoSuchSVNRevisionException if a matching s v n revision could not be found
	 */
	@Override
	public SVNRevision findBySVNU_SVNR_Last(String svnUserId,
		long svnRepositoryId, OrderByComparator<SVNRevision> orderByComparator)
		throws NoSuchSVNRevisionException {
		SVNRevision svnRevision = fetchBySVNU_SVNR_Last(svnUserId,
				svnRepositoryId, orderByComparator);

		if (svnRevision != null) {
			return svnRevision;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("svnUserId=");
		msg.append(svnUserId);

		msg.append(", svnRepositoryId=");
		msg.append(svnRepositoryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSVNRevisionException(msg.toString());
	}

	/**
	 * Returns the last s v n revision in the ordered set where svnUserId = &#63; and svnRepositoryId = &#63;.
	 *
	 * @param svnUserId the svn user ID
	 * @param svnRepositoryId the svn repository ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching s v n revision, or <code>null</code> if a matching s v n revision could not be found
	 */
	@Override
	public SVNRevision fetchBySVNU_SVNR_Last(String svnUserId,
		long svnRepositoryId, OrderByComparator<SVNRevision> orderByComparator) {
		int count = countBySVNU_SVNR(svnUserId, svnRepositoryId);

		if (count == 0) {
			return null;
		}

		List<SVNRevision> list = findBySVNU_SVNR(svnUserId, svnRepositoryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the s v n revisions before and after the current s v n revision in the ordered set where svnUserId = &#63; and svnRepositoryId = &#63;.
	 *
	 * @param svnRevisionId the primary key of the current s v n revision
	 * @param svnUserId the svn user ID
	 * @param svnRepositoryId the svn repository ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next s v n revision
	 * @throws NoSuchSVNRevisionException if a s v n revision with the primary key could not be found
	 */
	@Override
	public SVNRevision[] findBySVNU_SVNR_PrevAndNext(long svnRevisionId,
		String svnUserId, long svnRepositoryId,
		OrderByComparator<SVNRevision> orderByComparator)
		throws NoSuchSVNRevisionException {
		SVNRevision svnRevision = findByPrimaryKey(svnRevisionId);

		Session session = null;

		try {
			session = openSession();

			SVNRevision[] array = new SVNRevisionImpl[3];

			array[0] = getBySVNU_SVNR_PrevAndNext(session, svnRevision,
					svnUserId, svnRepositoryId, orderByComparator, true);

			array[1] = svnRevision;

			array[2] = getBySVNU_SVNR_PrevAndNext(session, svnRevision,
					svnUserId, svnRepositoryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SVNRevision getBySVNU_SVNR_PrevAndNext(Session session,
		SVNRevision svnRevision, String svnUserId, long svnRepositoryId,
		OrderByComparator<SVNRevision> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SVNREVISION_WHERE);

		boolean bindSvnUserId = false;

		if (svnUserId == null) {
			query.append(_FINDER_COLUMN_SVNU_SVNR_SVNUSERID_1);
		}
		else if (svnUserId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_SVNU_SVNR_SVNUSERID_3);
		}
		else {
			bindSvnUserId = true;

			query.append(_FINDER_COLUMN_SVNU_SVNR_SVNUSERID_2);
		}

		query.append(_FINDER_COLUMN_SVNU_SVNR_SVNREPOSITORYID_2);

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
			query.append(SVNRevisionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindSvnUserId) {
			qPos.add(svnUserId);
		}

		qPos.add(svnRepositoryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(svnRevision);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SVNRevision> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the s v n revisions where svnUserId = &#63; and svnRepositoryId = &#63; from the database.
	 *
	 * @param svnUserId the svn user ID
	 * @param svnRepositoryId the svn repository ID
	 */
	@Override
	public void removeBySVNU_SVNR(String svnUserId, long svnRepositoryId) {
		for (SVNRevision svnRevision : findBySVNU_SVNR(svnUserId,
				svnRepositoryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(svnRevision);
		}
	}

	/**
	 * Returns the number of s v n revisions where svnUserId = &#63; and svnRepositoryId = &#63;.
	 *
	 * @param svnUserId the svn user ID
	 * @param svnRepositoryId the svn repository ID
	 * @return the number of matching s v n revisions
	 */
	@Override
	public int countBySVNU_SVNR(String svnUserId, long svnRepositoryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SVNU_SVNR;

		Object[] finderArgs = new Object[] { svnUserId, svnRepositoryId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SVNREVISION_WHERE);

			boolean bindSvnUserId = false;

			if (svnUserId == null) {
				query.append(_FINDER_COLUMN_SVNU_SVNR_SVNUSERID_1);
			}
			else if (svnUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SVNU_SVNR_SVNUSERID_3);
			}
			else {
				bindSvnUserId = true;

				query.append(_FINDER_COLUMN_SVNU_SVNR_SVNUSERID_2);
			}

			query.append(_FINDER_COLUMN_SVNU_SVNR_SVNREPOSITORYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSvnUserId) {
					qPos.add(svnUserId);
				}

				qPos.add(svnRepositoryId);

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

	private static final String _FINDER_COLUMN_SVNU_SVNR_SVNUSERID_1 = "svnRevision.svnUserId IS NULL AND ";
	private static final String _FINDER_COLUMN_SVNU_SVNR_SVNUSERID_2 = "svnRevision.svnUserId = ? AND ";
	private static final String _FINDER_COLUMN_SVNU_SVNR_SVNUSERID_3 = "(svnRevision.svnUserId IS NULL OR svnRevision.svnUserId = '') AND ";
	private static final String _FINDER_COLUMN_SVNU_SVNR_SVNREPOSITORYID_2 = "svnRevision.svnRepositoryId = ?";

	public SVNRevisionPersistenceImpl() {
		setModelClass(SVNRevision.class);
	}

	/**
	 * Caches the s v n revision in the entity cache if it is enabled.
	 *
	 * @param svnRevision the s v n revision
	 */
	@Override
	public void cacheResult(SVNRevision svnRevision) {
		EntityCacheUtil.putResult(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionImpl.class, svnRevision.getPrimaryKey(), svnRevision);

		svnRevision.resetOriginalValues();
	}

	/**
	 * Caches the s v n revisions in the entity cache if it is enabled.
	 *
	 * @param svnRevisions the s v n revisions
	 */
	@Override
	public void cacheResult(List<SVNRevision> svnRevisions) {
		for (SVNRevision svnRevision : svnRevisions) {
			if (EntityCacheUtil.getResult(
						SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
						SVNRevisionImpl.class, svnRevision.getPrimaryKey()) == null) {
				cacheResult(svnRevision);
			}
			else {
				svnRevision.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all s v n revisions.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(SVNRevisionImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the s v n revision.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SVNRevision svnRevision) {
		EntityCacheUtil.removeResult(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionImpl.class, svnRevision.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<SVNRevision> svnRevisions) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SVNRevision svnRevision : svnRevisions) {
			EntityCacheUtil.removeResult(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
				SVNRevisionImpl.class, svnRevision.getPrimaryKey());
		}
	}

	/**
	 * Creates a new s v n revision with the primary key. Does not add the s v n revision to the database.
	 *
	 * @param svnRevisionId the primary key for the new s v n revision
	 * @return the new s v n revision
	 */
	@Override
	public SVNRevision create(long svnRevisionId) {
		SVNRevision svnRevision = new SVNRevisionImpl();

		svnRevision.setNew(true);
		svnRevision.setPrimaryKey(svnRevisionId);

		return svnRevision;
	}

	/**
	 * Removes the s v n revision with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param svnRevisionId the primary key of the s v n revision
	 * @return the s v n revision that was removed
	 * @throws NoSuchSVNRevisionException if a s v n revision with the primary key could not be found
	 */
	@Override
	public SVNRevision remove(long svnRevisionId)
		throws NoSuchSVNRevisionException {
		return remove((Serializable)svnRevisionId);
	}

	/**
	 * Removes the s v n revision with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the s v n revision
	 * @return the s v n revision that was removed
	 * @throws NoSuchSVNRevisionException if a s v n revision with the primary key could not be found
	 */
	@Override
	public SVNRevision remove(Serializable primaryKey)
		throws NoSuchSVNRevisionException {
		Session session = null;

		try {
			session = openSession();

			SVNRevision svnRevision = (SVNRevision)session.get(SVNRevisionImpl.class,
					primaryKey);

			if (svnRevision == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSVNRevisionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(svnRevision);
		}
		catch (NoSuchSVNRevisionException nsee) {
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
	protected SVNRevision removeImpl(SVNRevision svnRevision) {
		svnRevision = toUnwrappedModel(svnRevision);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(svnRevision)) {
				svnRevision = (SVNRevision)session.get(SVNRevisionImpl.class,
						svnRevision.getPrimaryKeyObj());
			}

			if (svnRevision != null) {
				session.delete(svnRevision);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (svnRevision != null) {
			clearCache(svnRevision);
		}

		return svnRevision;
	}

	@Override
	public SVNRevision updateImpl(SVNRevision svnRevision) {
		svnRevision = toUnwrappedModel(svnRevision);

		boolean isNew = svnRevision.isNew();

		SVNRevisionModelImpl svnRevisionModelImpl = (SVNRevisionModelImpl)svnRevision;

		Session session = null;

		try {
			session = openSession();

			if (svnRevision.isNew()) {
				session.save(svnRevision);

				svnRevision.setNew(false);
			}
			else {
				session.merge(svnRevision);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !SVNRevisionModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((svnRevisionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SVNUSERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						svnRevisionModelImpl.getOriginalSvnUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SVNUSERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SVNUSERID,
					args);

				args = new Object[] { svnRevisionModelImpl.getSvnUserId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SVNUSERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SVNUSERID,
					args);
			}

			if ((svnRevisionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SVNREPOSITORYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						svnRevisionModelImpl.getOriginalSvnRepositoryId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SVNREPOSITORYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SVNREPOSITORYID,
					args);

				args = new Object[] { svnRevisionModelImpl.getSvnRepositoryId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SVNREPOSITORYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SVNREPOSITORYID,
					args);
			}

			if ((svnRevisionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SVNU_SVNR.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						svnRevisionModelImpl.getOriginalSvnUserId(),
						svnRevisionModelImpl.getOriginalSvnRepositoryId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SVNU_SVNR,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SVNU_SVNR,
					args);

				args = new Object[] {
						svnRevisionModelImpl.getSvnUserId(),
						svnRevisionModelImpl.getSvnRepositoryId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SVNU_SVNR,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SVNU_SVNR,
					args);
			}
		}

		EntityCacheUtil.putResult(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionImpl.class, svnRevision.getPrimaryKey(), svnRevision,
			false);

		svnRevision.resetOriginalValues();

		return svnRevision;
	}

	protected SVNRevision toUnwrappedModel(SVNRevision svnRevision) {
		if (svnRevision instanceof SVNRevisionImpl) {
			return svnRevision;
		}

		SVNRevisionImpl svnRevisionImpl = new SVNRevisionImpl();

		svnRevisionImpl.setNew(svnRevision.isNew());
		svnRevisionImpl.setPrimaryKey(svnRevision.getPrimaryKey());

		svnRevisionImpl.setSvnRevisionId(svnRevision.getSvnRevisionId());
		svnRevisionImpl.setSvnUserId(svnRevision.getSvnUserId());
		svnRevisionImpl.setCreateDate(svnRevision.getCreateDate());
		svnRevisionImpl.setSvnRepositoryId(svnRevision.getSvnRepositoryId());
		svnRevisionImpl.setRevisionNumber(svnRevision.getRevisionNumber());
		svnRevisionImpl.setComments(svnRevision.getComments());

		return svnRevisionImpl;
	}

	/**
	 * Returns the s v n revision with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the s v n revision
	 * @return the s v n revision
	 * @throws NoSuchSVNRevisionException if a s v n revision with the primary key could not be found
	 */
	@Override
	public SVNRevision findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSVNRevisionException {
		SVNRevision svnRevision = fetchByPrimaryKey(primaryKey);

		if (svnRevision == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSVNRevisionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return svnRevision;
	}

	/**
	 * Returns the s v n revision with the primary key or throws a {@link NoSuchSVNRevisionException} if it could not be found.
	 *
	 * @param svnRevisionId the primary key of the s v n revision
	 * @return the s v n revision
	 * @throws NoSuchSVNRevisionException if a s v n revision with the primary key could not be found
	 */
	@Override
	public SVNRevision findByPrimaryKey(long svnRevisionId)
		throws NoSuchSVNRevisionException {
		return findByPrimaryKey((Serializable)svnRevisionId);
	}

	/**
	 * Returns the s v n revision with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the s v n revision
	 * @return the s v n revision, or <code>null</code> if a s v n revision with the primary key could not be found
	 */
	@Override
	public SVNRevision fetchByPrimaryKey(Serializable primaryKey) {
		SVNRevision svnRevision = (SVNRevision)EntityCacheUtil.getResult(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
				SVNRevisionImpl.class, primaryKey);

		if (svnRevision == _nullSVNRevision) {
			return null;
		}

		if (svnRevision == null) {
			Session session = null;

			try {
				session = openSession();

				svnRevision = (SVNRevision)session.get(SVNRevisionImpl.class,
						primaryKey);

				if (svnRevision != null) {
					cacheResult(svnRevision);
				}
				else {
					EntityCacheUtil.putResult(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
						SVNRevisionImpl.class, primaryKey, _nullSVNRevision);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
					SVNRevisionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return svnRevision;
	}

	/**
	 * Returns the s v n revision with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param svnRevisionId the primary key of the s v n revision
	 * @return the s v n revision, or <code>null</code> if a s v n revision with the primary key could not be found
	 */
	@Override
	public SVNRevision fetchByPrimaryKey(long svnRevisionId) {
		return fetchByPrimaryKey((Serializable)svnRevisionId);
	}

	@Override
	public Map<Serializable, SVNRevision> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SVNRevision> map = new HashMap<Serializable, SVNRevision>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SVNRevision svnRevision = fetchByPrimaryKey(primaryKey);

			if (svnRevision != null) {
				map.put(primaryKey, svnRevision);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			SVNRevision svnRevision = (SVNRevision)EntityCacheUtil.getResult(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
					SVNRevisionImpl.class, primaryKey);

			if (svnRevision == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, svnRevision);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SVNREVISION_WHERE_PKS_IN);

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

			for (SVNRevision svnRevision : (List<SVNRevision>)q.list()) {
				map.put(svnRevision.getPrimaryKeyObj(), svnRevision);

				cacheResult(svnRevision);

				uncachedPrimaryKeys.remove(svnRevision.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
					SVNRevisionImpl.class, primaryKey, _nullSVNRevision);
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
	 * Returns all the s v n revisions.
	 *
	 * @return the s v n revisions
	 */
	@Override
	public List<SVNRevision> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the s v n revisions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SVNRevisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of s v n revisions
	 * @param end the upper bound of the range of s v n revisions (not inclusive)
	 * @return the range of s v n revisions
	 */
	@Override
	public List<SVNRevision> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the s v n revisions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SVNRevisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of s v n revisions
	 * @param end the upper bound of the range of s v n revisions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of s v n revisions
	 */
	@Override
	public List<SVNRevision> findAll(int start, int end,
		OrderByComparator<SVNRevision> orderByComparator) {
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

		List<SVNRevision> list = (List<SVNRevision>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SVNREVISION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SVNREVISION;

				if (pagination) {
					sql = sql.concat(SVNRevisionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SVNRevision>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SVNRevision>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the s v n revisions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SVNRevision svnRevision : findAll()) {
			remove(svnRevision);
		}
	}

	/**
	 * Returns the number of s v n revisions.
	 *
	 * @return the number of s v n revisions
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SVNREVISION);

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

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SVNRevisionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the s v n revision persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(SVNRevisionImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_SVNREVISION = "SELECT svnRevision FROM SVNRevision svnRevision";
	private static final String _SQL_SELECT_SVNREVISION_WHERE_PKS_IN = "SELECT svnRevision FROM SVNRevision svnRevision WHERE svnRevisionId IN (";
	private static final String _SQL_SELECT_SVNREVISION_WHERE = "SELECT svnRevision FROM SVNRevision svnRevision WHERE ";
	private static final String _SQL_COUNT_SVNREVISION = "SELECT COUNT(svnRevision) FROM SVNRevision svnRevision";
	private static final String _SQL_COUNT_SVNREVISION_WHERE = "SELECT COUNT(svnRevision) FROM SVNRevision svnRevision WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "svnRevision.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SVNRevision exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SVNRevision exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(SVNRevisionPersistenceImpl.class);
	private static final SVNRevision _nullSVNRevision = new SVNRevisionImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<SVNRevision> toCacheModel() {
				return _nullSVNRevisionCacheModel;
			}
		};

	private static final CacheModel<SVNRevision> _nullSVNRevisionCacheModel = new CacheModel<SVNRevision>() {
			@Override
			public SVNRevision toEntityModel() {
				return _nullSVNRevision;
			}
		};
}