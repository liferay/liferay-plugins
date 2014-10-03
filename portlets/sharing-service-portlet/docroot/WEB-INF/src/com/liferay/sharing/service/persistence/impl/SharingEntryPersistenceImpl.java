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

package com.liferay.sharing.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.sharing.NoSuchEntryException;
import com.liferay.sharing.model.SharingEntry;
import com.liferay.sharing.model.impl.SharingEntryImpl;
import com.liferay.sharing.model.impl.SharingEntryModelImpl;
import com.liferay.sharing.service.persistence.SharingEntryPK;
import com.liferay.sharing.service.persistence.SharingEntryPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the sharing entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SharingEntryPersistence
 * @see SharingEntryUtil
 * @generated
 */
@ProviderType
public class SharingEntryPersistenceImpl extends BasePersistenceImpl<SharingEntry>
	implements SharingEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SharingEntryUtil} to access the sharing entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SharingEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SharingEntryModelImpl.ENTITY_CACHE_ENABLED,
			SharingEntryModelImpl.FINDER_CACHE_ENABLED, SharingEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SharingEntryModelImpl.ENTITY_CACHE_ENABLED,
			SharingEntryModelImpl.FINDER_CACHE_ENABLED, SharingEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SharingEntryModelImpl.ENTITY_CACHE_ENABLED,
			SharingEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C = new FinderPath(SharingEntryModelImpl.ENTITY_CACHE_ENABLED,
			SharingEntryModelImpl.FINDER_CACHE_ENABLED, SharingEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C = new FinderPath(SharingEntryModelImpl.ENTITY_CACHE_ENABLED,
			SharingEntryModelImpl.FINDER_CACHE_ENABLED, SharingEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C",
			new String[] { Long.class.getName(), Long.class.getName() },
			SharingEntryModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			SharingEntryModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C = new FinderPath(SharingEntryModelImpl.ENTITY_CACHE_ENABLED,
			SharingEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the sharing entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching sharing entries
	 */
	@Override
	public List<SharingEntry> findByC_C(long classNameId, long classPK) {
		return findByC_C(classNameId, classPK, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sharing entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sharing.model.impl.SharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of sharing entries
	 * @param end the upper bound of the range of sharing entries (not inclusive)
	 * @return the range of matching sharing entries
	 */
	@Override
	public List<SharingEntry> findByC_C(long classNameId, long classPK,
		int start, int end) {
		return findByC_C(classNameId, classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sharing entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sharing.model.impl.SharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of sharing entries
	 * @param end the upper bound of the range of sharing entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sharing entries
	 */
	@Override
	public List<SharingEntry> findByC_C(long classNameId, long classPK,
		int start, int end, OrderByComparator<SharingEntry> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C;
			finderArgs = new Object[] { classNameId, classPK };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C;
			finderArgs = new Object[] {
					classNameId, classPK,
					
					start, end, orderByComparator
				};
		}

		List<SharingEntry> list = (List<SharingEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SharingEntry sharingEntry : list) {
				if ((classNameId != sharingEntry.getClassNameId()) ||
						(classPK != sharingEntry.getClassPK())) {
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

			query.append(_SQL_SELECT_SHARINGENTRY_WHERE);

			query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SharingEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				if (!pagination) {
					list = (List<SharingEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SharingEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first sharing entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sharing entry
	 * @throws com.liferay.sharing.NoSuchEntryException if a matching sharing entry could not be found
	 */
	@Override
	public SharingEntry findByC_C_First(long classNameId, long classPK,
		OrderByComparator<SharingEntry> orderByComparator)
		throws NoSuchEntryException {
		SharingEntry sharingEntry = fetchByC_C_First(classNameId, classPK,
				orderByComparator);

		if (sharingEntry != null) {
			return sharingEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first sharing entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sharing entry, or <code>null</code> if a matching sharing entry could not be found
	 */
	@Override
	public SharingEntry fetchByC_C_First(long classNameId, long classPK,
		OrderByComparator<SharingEntry> orderByComparator) {
		List<SharingEntry> list = findByC_C(classNameId, classPK, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sharing entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sharing entry
	 * @throws com.liferay.sharing.NoSuchEntryException if a matching sharing entry could not be found
	 */
	@Override
	public SharingEntry findByC_C_Last(long classNameId, long classPK,
		OrderByComparator<SharingEntry> orderByComparator)
		throws NoSuchEntryException {
		SharingEntry sharingEntry = fetchByC_C_Last(classNameId, classPK,
				orderByComparator);

		if (sharingEntry != null) {
			return sharingEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last sharing entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sharing entry, or <code>null</code> if a matching sharing entry could not be found
	 */
	@Override
	public SharingEntry fetchByC_C_Last(long classNameId, long classPK,
		OrderByComparator<SharingEntry> orderByComparator) {
		int count = countByC_C(classNameId, classPK);

		if (count == 0) {
			return null;
		}

		List<SharingEntry> list = findByC_C(classNameId, classPK, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sharing entries before and after the current sharing entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param sharingEntryPK the primary key of the current sharing entry
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sharing entry
	 * @throws com.liferay.sharing.NoSuchEntryException if a sharing entry with the primary key could not be found
	 */
	@Override
	public SharingEntry[] findByC_C_PrevAndNext(SharingEntryPK sharingEntryPK,
		long classNameId, long classPK,
		OrderByComparator<SharingEntry> orderByComparator)
		throws NoSuchEntryException {
		SharingEntry sharingEntry = findByPrimaryKey(sharingEntryPK);

		Session session = null;

		try {
			session = openSession();

			SharingEntry[] array = new SharingEntryImpl[3];

			array[0] = getByC_C_PrevAndNext(session, sharingEntry, classNameId,
					classPK, orderByComparator, true);

			array[1] = sharingEntry;

			array[2] = getByC_C_PrevAndNext(session, sharingEntry, classNameId,
					classPK, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SharingEntry getByC_C_PrevAndNext(Session session,
		SharingEntry sharingEntry, long classNameId, long classPK,
		OrderByComparator<SharingEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SHARINGENTRY_WHERE);

		query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

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
			query.append(SharingEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(sharingEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SharingEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sharing entries where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 */
	@Override
	public void removeByC_C(long classNameId, long classPK) {
		for (SharingEntry sharingEntry : findByC_C(classNameId, classPK,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(sharingEntry);
		}
	}

	/**
	 * Returns the number of sharing entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the number of matching sharing entries
	 */
	@Override
	public int countByC_C(long classNameId, long classPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_C;

		Object[] finderArgs = new Object[] { classNameId, classPK };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SHARINGENTRY_WHERE);

			query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

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

	private static final String _FINDER_COLUMN_C_C_CLASSNAMEID_2 = "sharingEntry.id.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_CLASSPK_2 = "sharingEntry.id.classPK = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_S_S = new FinderPath(SharingEntryModelImpl.ENTITY_CACHE_ENABLED,
			SharingEntryModelImpl.FINDER_CACHE_ENABLED, SharingEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByS_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_S = new FinderPath(SharingEntryModelImpl.ENTITY_CACHE_ENABLED,
			SharingEntryModelImpl.FINDER_CACHE_ENABLED, SharingEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByS_S",
			new String[] { Long.class.getName(), Long.class.getName() },
			SharingEntryModelImpl.SHARINGCLASSNAMEID_COLUMN_BITMASK |
			SharingEntryModelImpl.SHARINGCLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_S_S = new FinderPath(SharingEntryModelImpl.ENTITY_CACHE_ENABLED,
			SharingEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByS_S",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the sharing entries where sharingClassNameId = &#63; and sharingClassPK = &#63;.
	 *
	 * @param sharingClassNameId the sharing class name ID
	 * @param sharingClassPK the sharing class p k
	 * @return the matching sharing entries
	 */
	@Override
	public List<SharingEntry> findByS_S(long sharingClassNameId,
		long sharingClassPK) {
		return findByS_S(sharingClassNameId, sharingClassPK, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sharing entries where sharingClassNameId = &#63; and sharingClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sharing.model.impl.SharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sharingClassNameId the sharing class name ID
	 * @param sharingClassPK the sharing class p k
	 * @param start the lower bound of the range of sharing entries
	 * @param end the upper bound of the range of sharing entries (not inclusive)
	 * @return the range of matching sharing entries
	 */
	@Override
	public List<SharingEntry> findByS_S(long sharingClassNameId,
		long sharingClassPK, int start, int end) {
		return findByS_S(sharingClassNameId, sharingClassPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sharing entries where sharingClassNameId = &#63; and sharingClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sharing.model.impl.SharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sharingClassNameId the sharing class name ID
	 * @param sharingClassPK the sharing class p k
	 * @param start the lower bound of the range of sharing entries
	 * @param end the upper bound of the range of sharing entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sharing entries
	 */
	@Override
	public List<SharingEntry> findByS_S(long sharingClassNameId,
		long sharingClassPK, int start, int end,
		OrderByComparator<SharingEntry> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_S;
			finderArgs = new Object[] { sharingClassNameId, sharingClassPK };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_S_S;
			finderArgs = new Object[] {
					sharingClassNameId, sharingClassPK,
					
					start, end, orderByComparator
				};
		}

		List<SharingEntry> list = (List<SharingEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SharingEntry sharingEntry : list) {
				if ((sharingClassNameId != sharingEntry.getSharingClassNameId()) ||
						(sharingClassPK != sharingEntry.getSharingClassPK())) {
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

			query.append(_SQL_SELECT_SHARINGENTRY_WHERE);

			query.append(_FINDER_COLUMN_S_S_SHARINGCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_S_S_SHARINGCLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SharingEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(sharingClassNameId);

				qPos.add(sharingClassPK);

				if (!pagination) {
					list = (List<SharingEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SharingEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first sharing entry in the ordered set where sharingClassNameId = &#63; and sharingClassPK = &#63;.
	 *
	 * @param sharingClassNameId the sharing class name ID
	 * @param sharingClassPK the sharing class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sharing entry
	 * @throws com.liferay.sharing.NoSuchEntryException if a matching sharing entry could not be found
	 */
	@Override
	public SharingEntry findByS_S_First(long sharingClassNameId,
		long sharingClassPK, OrderByComparator<SharingEntry> orderByComparator)
		throws NoSuchEntryException {
		SharingEntry sharingEntry = fetchByS_S_First(sharingClassNameId,
				sharingClassPK, orderByComparator);

		if (sharingEntry != null) {
			return sharingEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("sharingClassNameId=");
		msg.append(sharingClassNameId);

		msg.append(", sharingClassPK=");
		msg.append(sharingClassPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first sharing entry in the ordered set where sharingClassNameId = &#63; and sharingClassPK = &#63;.
	 *
	 * @param sharingClassNameId the sharing class name ID
	 * @param sharingClassPK the sharing class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sharing entry, or <code>null</code> if a matching sharing entry could not be found
	 */
	@Override
	public SharingEntry fetchByS_S_First(long sharingClassNameId,
		long sharingClassPK, OrderByComparator<SharingEntry> orderByComparator) {
		List<SharingEntry> list = findByS_S(sharingClassNameId, sharingClassPK,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sharing entry in the ordered set where sharingClassNameId = &#63; and sharingClassPK = &#63;.
	 *
	 * @param sharingClassNameId the sharing class name ID
	 * @param sharingClassPK the sharing class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sharing entry
	 * @throws com.liferay.sharing.NoSuchEntryException if a matching sharing entry could not be found
	 */
	@Override
	public SharingEntry findByS_S_Last(long sharingClassNameId,
		long sharingClassPK, OrderByComparator<SharingEntry> orderByComparator)
		throws NoSuchEntryException {
		SharingEntry sharingEntry = fetchByS_S_Last(sharingClassNameId,
				sharingClassPK, orderByComparator);

		if (sharingEntry != null) {
			return sharingEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("sharingClassNameId=");
		msg.append(sharingClassNameId);

		msg.append(", sharingClassPK=");
		msg.append(sharingClassPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last sharing entry in the ordered set where sharingClassNameId = &#63; and sharingClassPK = &#63;.
	 *
	 * @param sharingClassNameId the sharing class name ID
	 * @param sharingClassPK the sharing class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sharing entry, or <code>null</code> if a matching sharing entry could not be found
	 */
	@Override
	public SharingEntry fetchByS_S_Last(long sharingClassNameId,
		long sharingClassPK, OrderByComparator<SharingEntry> orderByComparator) {
		int count = countByS_S(sharingClassNameId, sharingClassPK);

		if (count == 0) {
			return null;
		}

		List<SharingEntry> list = findByS_S(sharingClassNameId, sharingClassPK,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sharing entries before and after the current sharing entry in the ordered set where sharingClassNameId = &#63; and sharingClassPK = &#63;.
	 *
	 * @param sharingEntryPK the primary key of the current sharing entry
	 * @param sharingClassNameId the sharing class name ID
	 * @param sharingClassPK the sharing class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sharing entry
	 * @throws com.liferay.sharing.NoSuchEntryException if a sharing entry with the primary key could not be found
	 */
	@Override
	public SharingEntry[] findByS_S_PrevAndNext(SharingEntryPK sharingEntryPK,
		long sharingClassNameId, long sharingClassPK,
		OrderByComparator<SharingEntry> orderByComparator)
		throws NoSuchEntryException {
		SharingEntry sharingEntry = findByPrimaryKey(sharingEntryPK);

		Session session = null;

		try {
			session = openSession();

			SharingEntry[] array = new SharingEntryImpl[3];

			array[0] = getByS_S_PrevAndNext(session, sharingEntry,
					sharingClassNameId, sharingClassPK, orderByComparator, true);

			array[1] = sharingEntry;

			array[2] = getByS_S_PrevAndNext(session, sharingEntry,
					sharingClassNameId, sharingClassPK, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SharingEntry getByS_S_PrevAndNext(Session session,
		SharingEntry sharingEntry, long sharingClassNameId,
		long sharingClassPK, OrderByComparator<SharingEntry> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SHARINGENTRY_WHERE);

		query.append(_FINDER_COLUMN_S_S_SHARINGCLASSNAMEID_2);

		query.append(_FINDER_COLUMN_S_S_SHARINGCLASSPK_2);

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
			query.append(SharingEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(sharingClassNameId);

		qPos.add(sharingClassPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(sharingEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SharingEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sharing entries where sharingClassNameId = &#63; and sharingClassPK = &#63; from the database.
	 *
	 * @param sharingClassNameId the sharing class name ID
	 * @param sharingClassPK the sharing class p k
	 */
	@Override
	public void removeByS_S(long sharingClassNameId, long sharingClassPK) {
		for (SharingEntry sharingEntry : findByS_S(sharingClassNameId,
				sharingClassPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(sharingEntry);
		}
	}

	/**
	 * Returns the number of sharing entries where sharingClassNameId = &#63; and sharingClassPK = &#63;.
	 *
	 * @param sharingClassNameId the sharing class name ID
	 * @param sharingClassPK the sharing class p k
	 * @return the number of matching sharing entries
	 */
	@Override
	public int countByS_S(long sharingClassNameId, long sharingClassPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_S_S;

		Object[] finderArgs = new Object[] { sharingClassNameId, sharingClassPK };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SHARINGENTRY_WHERE);

			query.append(_FINDER_COLUMN_S_S_SHARINGCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_S_S_SHARINGCLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(sharingClassNameId);

				qPos.add(sharingClassPK);

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

	private static final String _FINDER_COLUMN_S_S_SHARINGCLASSNAMEID_2 = "sharingEntry.id.sharingClassNameId = ? AND ";
	private static final String _FINDER_COLUMN_S_S_SHARINGCLASSPK_2 = "sharingEntry.id.sharingClassPK = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_S = new FinderPath(SharingEntryModelImpl.ENTITY_CACHE_ENABLED,
			SharingEntryModelImpl.FINDER_CACHE_ENABLED, SharingEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C_S",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_S = new FinderPath(SharingEntryModelImpl.ENTITY_CACHE_ENABLED,
			SharingEntryModelImpl.FINDER_CACHE_ENABLED, SharingEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C_S",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			SharingEntryModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			SharingEntryModelImpl.CLASSPK_COLUMN_BITMASK |
			SharingEntryModelImpl.SHARINGCLASSNAMEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C_S = new FinderPath(SharingEntryModelImpl.ENTITY_CACHE_ENABLED,
			SharingEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_S",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns all the sharing entries where classNameId = &#63; and classPK = &#63; and sharingClassNameId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param sharingClassNameId the sharing class name ID
	 * @return the matching sharing entries
	 */
	@Override
	public List<SharingEntry> findByC_C_S(long classNameId, long classPK,
		long sharingClassNameId) {
		return findByC_C_S(classNameId, classPK, sharingClassNameId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sharing entries where classNameId = &#63; and classPK = &#63; and sharingClassNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sharing.model.impl.SharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param sharingClassNameId the sharing class name ID
	 * @param start the lower bound of the range of sharing entries
	 * @param end the upper bound of the range of sharing entries (not inclusive)
	 * @return the range of matching sharing entries
	 */
	@Override
	public List<SharingEntry> findByC_C_S(long classNameId, long classPK,
		long sharingClassNameId, int start, int end) {
		return findByC_C_S(classNameId, classPK, sharingClassNameId, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the sharing entries where classNameId = &#63; and classPK = &#63; and sharingClassNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sharing.model.impl.SharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param sharingClassNameId the sharing class name ID
	 * @param start the lower bound of the range of sharing entries
	 * @param end the upper bound of the range of sharing entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sharing entries
	 */
	@Override
	public List<SharingEntry> findByC_C_S(long classNameId, long classPK,
		long sharingClassNameId, int start, int end,
		OrderByComparator<SharingEntry> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_S;
			finderArgs = new Object[] { classNameId, classPK, sharingClassNameId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_S;
			finderArgs = new Object[] {
					classNameId, classPK, sharingClassNameId,
					
					start, end, orderByComparator
				};
		}

		List<SharingEntry> list = (List<SharingEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SharingEntry sharingEntry : list) {
				if ((classNameId != sharingEntry.getClassNameId()) ||
						(classPK != sharingEntry.getClassPK()) ||
						(sharingClassNameId != sharingEntry.getSharingClassNameId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_SHARINGENTRY_WHERE);

			query.append(_FINDER_COLUMN_C_C_S_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_S_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_S_SHARINGCLASSNAMEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SharingEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(sharingClassNameId);

				if (!pagination) {
					list = (List<SharingEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SharingEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first sharing entry in the ordered set where classNameId = &#63; and classPK = &#63; and sharingClassNameId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param sharingClassNameId the sharing class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sharing entry
	 * @throws com.liferay.sharing.NoSuchEntryException if a matching sharing entry could not be found
	 */
	@Override
	public SharingEntry findByC_C_S_First(long classNameId, long classPK,
		long sharingClassNameId,
		OrderByComparator<SharingEntry> orderByComparator)
		throws NoSuchEntryException {
		SharingEntry sharingEntry = fetchByC_C_S_First(classNameId, classPK,
				sharingClassNameId, orderByComparator);

		if (sharingEntry != null) {
			return sharingEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", sharingClassNameId=");
		msg.append(sharingClassNameId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first sharing entry in the ordered set where classNameId = &#63; and classPK = &#63; and sharingClassNameId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param sharingClassNameId the sharing class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sharing entry, or <code>null</code> if a matching sharing entry could not be found
	 */
	@Override
	public SharingEntry fetchByC_C_S_First(long classNameId, long classPK,
		long sharingClassNameId,
		OrderByComparator<SharingEntry> orderByComparator) {
		List<SharingEntry> list = findByC_C_S(classNameId, classPK,
				sharingClassNameId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sharing entry in the ordered set where classNameId = &#63; and classPK = &#63; and sharingClassNameId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param sharingClassNameId the sharing class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sharing entry
	 * @throws com.liferay.sharing.NoSuchEntryException if a matching sharing entry could not be found
	 */
	@Override
	public SharingEntry findByC_C_S_Last(long classNameId, long classPK,
		long sharingClassNameId,
		OrderByComparator<SharingEntry> orderByComparator)
		throws NoSuchEntryException {
		SharingEntry sharingEntry = fetchByC_C_S_Last(classNameId, classPK,
				sharingClassNameId, orderByComparator);

		if (sharingEntry != null) {
			return sharingEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", sharingClassNameId=");
		msg.append(sharingClassNameId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last sharing entry in the ordered set where classNameId = &#63; and classPK = &#63; and sharingClassNameId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param sharingClassNameId the sharing class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sharing entry, or <code>null</code> if a matching sharing entry could not be found
	 */
	@Override
	public SharingEntry fetchByC_C_S_Last(long classNameId, long classPK,
		long sharingClassNameId,
		OrderByComparator<SharingEntry> orderByComparator) {
		int count = countByC_C_S(classNameId, classPK, sharingClassNameId);

		if (count == 0) {
			return null;
		}

		List<SharingEntry> list = findByC_C_S(classNameId, classPK,
				sharingClassNameId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sharing entries before and after the current sharing entry in the ordered set where classNameId = &#63; and classPK = &#63; and sharingClassNameId = &#63;.
	 *
	 * @param sharingEntryPK the primary key of the current sharing entry
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param sharingClassNameId the sharing class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sharing entry
	 * @throws com.liferay.sharing.NoSuchEntryException if a sharing entry with the primary key could not be found
	 */
	@Override
	public SharingEntry[] findByC_C_S_PrevAndNext(
		SharingEntryPK sharingEntryPK, long classNameId, long classPK,
		long sharingClassNameId,
		OrderByComparator<SharingEntry> orderByComparator)
		throws NoSuchEntryException {
		SharingEntry sharingEntry = findByPrimaryKey(sharingEntryPK);

		Session session = null;

		try {
			session = openSession();

			SharingEntry[] array = new SharingEntryImpl[3];

			array[0] = getByC_C_S_PrevAndNext(session, sharingEntry,
					classNameId, classPK, sharingClassNameId,
					orderByComparator, true);

			array[1] = sharingEntry;

			array[2] = getByC_C_S_PrevAndNext(session, sharingEntry,
					classNameId, classPK, sharingClassNameId,
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

	protected SharingEntry getByC_C_S_PrevAndNext(Session session,
		SharingEntry sharingEntry, long classNameId, long classPK,
		long sharingClassNameId,
		OrderByComparator<SharingEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SHARINGENTRY_WHERE);

		query.append(_FINDER_COLUMN_C_C_S_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_C_S_CLASSPK_2);

		query.append(_FINDER_COLUMN_C_C_S_SHARINGCLASSNAMEID_2);

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
			query.append(SharingEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		qPos.add(sharingClassNameId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(sharingEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SharingEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sharing entries where classNameId = &#63; and classPK = &#63; and sharingClassNameId = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param sharingClassNameId the sharing class name ID
	 */
	@Override
	public void removeByC_C_S(long classNameId, long classPK,
		long sharingClassNameId) {
		for (SharingEntry sharingEntry : findByC_C_S(classNameId, classPK,
				sharingClassNameId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(sharingEntry);
		}
	}

	/**
	 * Returns the number of sharing entries where classNameId = &#63; and classPK = &#63; and sharingClassNameId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param sharingClassNameId the sharing class name ID
	 * @return the number of matching sharing entries
	 */
	@Override
	public int countByC_C_S(long classNameId, long classPK,
		long sharingClassNameId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_C_S;

		Object[] finderArgs = new Object[] {
				classNameId, classPK, sharingClassNameId
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_SHARINGENTRY_WHERE);

			query.append(_FINDER_COLUMN_C_C_S_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_S_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_S_SHARINGCLASSNAMEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(sharingClassNameId);

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

	private static final String _FINDER_COLUMN_C_C_S_CLASSNAMEID_2 = "sharingEntry.id.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_S_CLASSPK_2 = "sharingEntry.id.classPK = ? AND ";
	private static final String _FINDER_COLUMN_C_C_S_SHARINGCLASSNAMEID_2 = "sharingEntry.id.sharingClassNameId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_S_S = new FinderPath(SharingEntryModelImpl.ENTITY_CACHE_ENABLED,
			SharingEntryModelImpl.FINDER_CACHE_ENABLED, SharingEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_S_S",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S_S = new FinderPath(SharingEntryModelImpl.ENTITY_CACHE_ENABLED,
			SharingEntryModelImpl.FINDER_CACHE_ENABLED, SharingEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_S_S",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			SharingEntryModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			SharingEntryModelImpl.SHARINGCLASSNAMEID_COLUMN_BITMASK |
			SharingEntryModelImpl.SHARINGCLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_S_S = new FinderPath(SharingEntryModelImpl.ENTITY_CACHE_ENABLED,
			SharingEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_S_S",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns all the sharing entries where classNameId = &#63; and sharingClassNameId = &#63; and sharingClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param sharingClassNameId the sharing class name ID
	 * @param sharingClassPK the sharing class p k
	 * @return the matching sharing entries
	 */
	@Override
	public List<SharingEntry> findByC_S_S(long classNameId,
		long sharingClassNameId, long sharingClassPK) {
		return findByC_S_S(classNameId, sharingClassNameId, sharingClassPK,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sharing entries where classNameId = &#63; and sharingClassNameId = &#63; and sharingClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sharing.model.impl.SharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param sharingClassNameId the sharing class name ID
	 * @param sharingClassPK the sharing class p k
	 * @param start the lower bound of the range of sharing entries
	 * @param end the upper bound of the range of sharing entries (not inclusive)
	 * @return the range of matching sharing entries
	 */
	@Override
	public List<SharingEntry> findByC_S_S(long classNameId,
		long sharingClassNameId, long sharingClassPK, int start, int end) {
		return findByC_S_S(classNameId, sharingClassNameId, sharingClassPK,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the sharing entries where classNameId = &#63; and sharingClassNameId = &#63; and sharingClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sharing.model.impl.SharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param sharingClassNameId the sharing class name ID
	 * @param sharingClassPK the sharing class p k
	 * @param start the lower bound of the range of sharing entries
	 * @param end the upper bound of the range of sharing entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sharing entries
	 */
	@Override
	public List<SharingEntry> findByC_S_S(long classNameId,
		long sharingClassNameId, long sharingClassPK, int start, int end,
		OrderByComparator<SharingEntry> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S_S;
			finderArgs = new Object[] {
					classNameId, sharingClassNameId, sharingClassPK
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_S_S;
			finderArgs = new Object[] {
					classNameId, sharingClassNameId, sharingClassPK,
					
					start, end, orderByComparator
				};
		}

		List<SharingEntry> list = (List<SharingEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SharingEntry sharingEntry : list) {
				if ((classNameId != sharingEntry.getClassNameId()) ||
						(sharingClassNameId != sharingEntry.getSharingClassNameId()) ||
						(sharingClassPK != sharingEntry.getSharingClassPK())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_SHARINGENTRY_WHERE);

			query.append(_FINDER_COLUMN_C_S_S_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_S_S_SHARINGCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_S_S_SHARINGCLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SharingEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(sharingClassNameId);

				qPos.add(sharingClassPK);

				if (!pagination) {
					list = (List<SharingEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SharingEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first sharing entry in the ordered set where classNameId = &#63; and sharingClassNameId = &#63; and sharingClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param sharingClassNameId the sharing class name ID
	 * @param sharingClassPK the sharing class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sharing entry
	 * @throws com.liferay.sharing.NoSuchEntryException if a matching sharing entry could not be found
	 */
	@Override
	public SharingEntry findByC_S_S_First(long classNameId,
		long sharingClassNameId, long sharingClassPK,
		OrderByComparator<SharingEntry> orderByComparator)
		throws NoSuchEntryException {
		SharingEntry sharingEntry = fetchByC_S_S_First(classNameId,
				sharingClassNameId, sharingClassPK, orderByComparator);

		if (sharingEntry != null) {
			return sharingEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", sharingClassNameId=");
		msg.append(sharingClassNameId);

		msg.append(", sharingClassPK=");
		msg.append(sharingClassPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first sharing entry in the ordered set where classNameId = &#63; and sharingClassNameId = &#63; and sharingClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param sharingClassNameId the sharing class name ID
	 * @param sharingClassPK the sharing class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sharing entry, or <code>null</code> if a matching sharing entry could not be found
	 */
	@Override
	public SharingEntry fetchByC_S_S_First(long classNameId,
		long sharingClassNameId, long sharingClassPK,
		OrderByComparator<SharingEntry> orderByComparator) {
		List<SharingEntry> list = findByC_S_S(classNameId, sharingClassNameId,
				sharingClassPK, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sharing entry in the ordered set where classNameId = &#63; and sharingClassNameId = &#63; and sharingClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param sharingClassNameId the sharing class name ID
	 * @param sharingClassPK the sharing class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sharing entry
	 * @throws com.liferay.sharing.NoSuchEntryException if a matching sharing entry could not be found
	 */
	@Override
	public SharingEntry findByC_S_S_Last(long classNameId,
		long sharingClassNameId, long sharingClassPK,
		OrderByComparator<SharingEntry> orderByComparator)
		throws NoSuchEntryException {
		SharingEntry sharingEntry = fetchByC_S_S_Last(classNameId,
				sharingClassNameId, sharingClassPK, orderByComparator);

		if (sharingEntry != null) {
			return sharingEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", sharingClassNameId=");
		msg.append(sharingClassNameId);

		msg.append(", sharingClassPK=");
		msg.append(sharingClassPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last sharing entry in the ordered set where classNameId = &#63; and sharingClassNameId = &#63; and sharingClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param sharingClassNameId the sharing class name ID
	 * @param sharingClassPK the sharing class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sharing entry, or <code>null</code> if a matching sharing entry could not be found
	 */
	@Override
	public SharingEntry fetchByC_S_S_Last(long classNameId,
		long sharingClassNameId, long sharingClassPK,
		OrderByComparator<SharingEntry> orderByComparator) {
		int count = countByC_S_S(classNameId, sharingClassNameId, sharingClassPK);

		if (count == 0) {
			return null;
		}

		List<SharingEntry> list = findByC_S_S(classNameId, sharingClassNameId,
				sharingClassPK, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sharing entries before and after the current sharing entry in the ordered set where classNameId = &#63; and sharingClassNameId = &#63; and sharingClassPK = &#63;.
	 *
	 * @param sharingEntryPK the primary key of the current sharing entry
	 * @param classNameId the class name ID
	 * @param sharingClassNameId the sharing class name ID
	 * @param sharingClassPK the sharing class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sharing entry
	 * @throws com.liferay.sharing.NoSuchEntryException if a sharing entry with the primary key could not be found
	 */
	@Override
	public SharingEntry[] findByC_S_S_PrevAndNext(
		SharingEntryPK sharingEntryPK, long classNameId,
		long sharingClassNameId, long sharingClassPK,
		OrderByComparator<SharingEntry> orderByComparator)
		throws NoSuchEntryException {
		SharingEntry sharingEntry = findByPrimaryKey(sharingEntryPK);

		Session session = null;

		try {
			session = openSession();

			SharingEntry[] array = new SharingEntryImpl[3];

			array[0] = getByC_S_S_PrevAndNext(session, sharingEntry,
					classNameId, sharingClassNameId, sharingClassPK,
					orderByComparator, true);

			array[1] = sharingEntry;

			array[2] = getByC_S_S_PrevAndNext(session, sharingEntry,
					classNameId, sharingClassNameId, sharingClassPK,
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

	protected SharingEntry getByC_S_S_PrevAndNext(Session session,
		SharingEntry sharingEntry, long classNameId, long sharingClassNameId,
		long sharingClassPK, OrderByComparator<SharingEntry> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SHARINGENTRY_WHERE);

		query.append(_FINDER_COLUMN_C_S_S_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_S_S_SHARINGCLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_S_S_SHARINGCLASSPK_2);

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
			query.append(SharingEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(sharingClassNameId);

		qPos.add(sharingClassPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(sharingEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SharingEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sharing entries where classNameId = &#63; and sharingClassNameId = &#63; and sharingClassPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param sharingClassNameId the sharing class name ID
	 * @param sharingClassPK the sharing class p k
	 */
	@Override
	public void removeByC_S_S(long classNameId, long sharingClassNameId,
		long sharingClassPK) {
		for (SharingEntry sharingEntry : findByC_S_S(classNameId,
				sharingClassNameId, sharingClassPK, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(sharingEntry);
		}
	}

	/**
	 * Returns the number of sharing entries where classNameId = &#63; and sharingClassNameId = &#63; and sharingClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param sharingClassNameId the sharing class name ID
	 * @param sharingClassPK the sharing class p k
	 * @return the number of matching sharing entries
	 */
	@Override
	public int countByC_S_S(long classNameId, long sharingClassNameId,
		long sharingClassPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_S_S;

		Object[] finderArgs = new Object[] {
				classNameId, sharingClassNameId, sharingClassPK
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_SHARINGENTRY_WHERE);

			query.append(_FINDER_COLUMN_C_S_S_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_S_S_SHARINGCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_S_S_SHARINGCLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(sharingClassNameId);

				qPos.add(sharingClassPK);

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

	private static final String _FINDER_COLUMN_C_S_S_CLASSNAMEID_2 = "sharingEntry.id.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_S_S_SHARINGCLASSNAMEID_2 = "sharingEntry.id.sharingClassNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_S_S_SHARINGCLASSPK_2 = "sharingEntry.id.sharingClassPK = ?";

	public SharingEntryPersistenceImpl() {
		setModelClass(SharingEntry.class);
	}

	/**
	 * Caches the sharing entry in the entity cache if it is enabled.
	 *
	 * @param sharingEntry the sharing entry
	 */
	@Override
	public void cacheResult(SharingEntry sharingEntry) {
		EntityCacheUtil.putResult(SharingEntryModelImpl.ENTITY_CACHE_ENABLED,
			SharingEntryImpl.class, sharingEntry.getPrimaryKey(), sharingEntry);

		sharingEntry.resetOriginalValues();
	}

	/**
	 * Caches the sharing entries in the entity cache if it is enabled.
	 *
	 * @param sharingEntries the sharing entries
	 */
	@Override
	public void cacheResult(List<SharingEntry> sharingEntries) {
		for (SharingEntry sharingEntry : sharingEntries) {
			if (EntityCacheUtil.getResult(
						SharingEntryModelImpl.ENTITY_CACHE_ENABLED,
						SharingEntryImpl.class, sharingEntry.getPrimaryKey()) == null) {
				cacheResult(sharingEntry);
			}
			else {
				sharingEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all sharing entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(SharingEntryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(SharingEntryImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the sharing entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SharingEntry sharingEntry) {
		EntityCacheUtil.removeResult(SharingEntryModelImpl.ENTITY_CACHE_ENABLED,
			SharingEntryImpl.class, sharingEntry.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<SharingEntry> sharingEntries) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SharingEntry sharingEntry : sharingEntries) {
			EntityCacheUtil.removeResult(SharingEntryModelImpl.ENTITY_CACHE_ENABLED,
				SharingEntryImpl.class, sharingEntry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new sharing entry with the primary key. Does not add the sharing entry to the database.
	 *
	 * @param sharingEntryPK the primary key for the new sharing entry
	 * @return the new sharing entry
	 */
	@Override
	public SharingEntry create(SharingEntryPK sharingEntryPK) {
		SharingEntry sharingEntry = new SharingEntryImpl();

		sharingEntry.setNew(true);
		sharingEntry.setPrimaryKey(sharingEntryPK);

		return sharingEntry;
	}

	/**
	 * Removes the sharing entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param sharingEntryPK the primary key of the sharing entry
	 * @return the sharing entry that was removed
	 * @throws com.liferay.sharing.NoSuchEntryException if a sharing entry with the primary key could not be found
	 */
	@Override
	public SharingEntry remove(SharingEntryPK sharingEntryPK)
		throws NoSuchEntryException {
		return remove((Serializable)sharingEntryPK);
	}

	/**
	 * Removes the sharing entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the sharing entry
	 * @return the sharing entry that was removed
	 * @throws com.liferay.sharing.NoSuchEntryException if a sharing entry with the primary key could not be found
	 */
	@Override
	public SharingEntry remove(Serializable primaryKey)
		throws NoSuchEntryException {
		Session session = null;

		try {
			session = openSession();

			SharingEntry sharingEntry = (SharingEntry)session.get(SharingEntryImpl.class,
					primaryKey);

			if (sharingEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(sharingEntry);
		}
		catch (NoSuchEntryException nsee) {
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
	protected SharingEntry removeImpl(SharingEntry sharingEntry) {
		sharingEntry = toUnwrappedModel(sharingEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(sharingEntry)) {
				sharingEntry = (SharingEntry)session.get(SharingEntryImpl.class,
						sharingEntry.getPrimaryKeyObj());
			}

			if (sharingEntry != null) {
				session.delete(sharingEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (sharingEntry != null) {
			clearCache(sharingEntry);
		}

		return sharingEntry;
	}

	@Override
	public SharingEntry updateImpl(
		com.liferay.sharing.model.SharingEntry sharingEntry) {
		sharingEntry = toUnwrappedModel(sharingEntry);

		boolean isNew = sharingEntry.isNew();

		SharingEntryModelImpl sharingEntryModelImpl = (SharingEntryModelImpl)sharingEntry;

		Session session = null;

		try {
			session = openSession();

			if (sharingEntry.isNew()) {
				session.save(sharingEntry);

				sharingEntry.setNew(false);
			}
			else {
				session.merge(sharingEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !SharingEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((sharingEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						sharingEntryModelImpl.getOriginalClassNameId(),
						sharingEntryModelImpl.getOriginalClassPK()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);

				args = new Object[] {
						sharingEntryModelImpl.getClassNameId(),
						sharingEntryModelImpl.getClassPK()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);
			}

			if ((sharingEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						sharingEntryModelImpl.getOriginalSharingClassNameId(),
						sharingEntryModelImpl.getOriginalSharingClassPK()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_S_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_S,
					args);

				args = new Object[] {
						sharingEntryModelImpl.getSharingClassNameId(),
						sharingEntryModelImpl.getSharingClassPK()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_S_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_S,
					args);
			}

			if ((sharingEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						sharingEntryModelImpl.getOriginalClassNameId(),
						sharingEntryModelImpl.getOriginalClassPK(),
						sharingEntryModelImpl.getOriginalSharingClassNameId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_S,
					args);

				args = new Object[] {
						sharingEntryModelImpl.getClassNameId(),
						sharingEntryModelImpl.getClassPK(),
						sharingEntryModelImpl.getSharingClassNameId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_S,
					args);
			}

			if ((sharingEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						sharingEntryModelImpl.getOriginalClassNameId(),
						sharingEntryModelImpl.getOriginalSharingClassNameId(),
						sharingEntryModelImpl.getOriginalSharingClassPK()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_S_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S_S,
					args);

				args = new Object[] {
						sharingEntryModelImpl.getClassNameId(),
						sharingEntryModelImpl.getSharingClassNameId(),
						sharingEntryModelImpl.getSharingClassPK()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_S_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S_S,
					args);
			}
		}

		EntityCacheUtil.putResult(SharingEntryModelImpl.ENTITY_CACHE_ENABLED,
			SharingEntryImpl.class, sharingEntry.getPrimaryKey(), sharingEntry,
			false);

		sharingEntry.resetOriginalValues();

		return sharingEntry;
	}

	protected SharingEntry toUnwrappedModel(SharingEntry sharingEntry) {
		if (sharingEntry instanceof SharingEntryImpl) {
			return sharingEntry;
		}

		SharingEntryImpl sharingEntryImpl = new SharingEntryImpl();

		sharingEntryImpl.setNew(sharingEntry.isNew());
		sharingEntryImpl.setPrimaryKey(sharingEntry.getPrimaryKey());

		sharingEntryImpl.setClassNameId(sharingEntry.getClassNameId());
		sharingEntryImpl.setClassPK(sharingEntry.getClassPK());
		sharingEntryImpl.setSharingClassNameId(sharingEntry.getSharingClassNameId());
		sharingEntryImpl.setSharingClassPK(sharingEntry.getSharingClassPK());

		return sharingEntryImpl;
	}

	/**
	 * Returns the sharing entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the sharing entry
	 * @return the sharing entry
	 * @throws com.liferay.sharing.NoSuchEntryException if a sharing entry with the primary key could not be found
	 */
	@Override
	public SharingEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEntryException {
		SharingEntry sharingEntry = fetchByPrimaryKey(primaryKey);

		if (sharingEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return sharingEntry;
	}

	/**
	 * Returns the sharing entry with the primary key or throws a {@link com.liferay.sharing.NoSuchEntryException} if it could not be found.
	 *
	 * @param sharingEntryPK the primary key of the sharing entry
	 * @return the sharing entry
	 * @throws com.liferay.sharing.NoSuchEntryException if a sharing entry with the primary key could not be found
	 */
	@Override
	public SharingEntry findByPrimaryKey(SharingEntryPK sharingEntryPK)
		throws NoSuchEntryException {
		return findByPrimaryKey((Serializable)sharingEntryPK);
	}

	/**
	 * Returns the sharing entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the sharing entry
	 * @return the sharing entry, or <code>null</code> if a sharing entry with the primary key could not be found
	 */
	@Override
	public SharingEntry fetchByPrimaryKey(Serializable primaryKey) {
		SharingEntry sharingEntry = (SharingEntry)EntityCacheUtil.getResult(SharingEntryModelImpl.ENTITY_CACHE_ENABLED,
				SharingEntryImpl.class, primaryKey);

		if (sharingEntry == _nullSharingEntry) {
			return null;
		}

		if (sharingEntry == null) {
			Session session = null;

			try {
				session = openSession();

				sharingEntry = (SharingEntry)session.get(SharingEntryImpl.class,
						primaryKey);

				if (sharingEntry != null) {
					cacheResult(sharingEntry);
				}
				else {
					EntityCacheUtil.putResult(SharingEntryModelImpl.ENTITY_CACHE_ENABLED,
						SharingEntryImpl.class, primaryKey, _nullSharingEntry);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(SharingEntryModelImpl.ENTITY_CACHE_ENABLED,
					SharingEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return sharingEntry;
	}

	/**
	 * Returns the sharing entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param sharingEntryPK the primary key of the sharing entry
	 * @return the sharing entry, or <code>null</code> if a sharing entry with the primary key could not be found
	 */
	@Override
	public SharingEntry fetchByPrimaryKey(SharingEntryPK sharingEntryPK) {
		return fetchByPrimaryKey((Serializable)sharingEntryPK);
	}

	@Override
	public Map<Serializable, SharingEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SharingEntry> map = new HashMap<Serializable, SharingEntry>();

		for (Serializable primaryKey : primaryKeys) {
			SharingEntry sharingEntry = fetchByPrimaryKey(primaryKey);

			if (sharingEntry != null) {
				map.put(primaryKey, sharingEntry);
			}
		}

		return map;
	}

	/**
	 * Returns all the sharing entries.
	 *
	 * @return the sharing entries
	 */
	@Override
	public List<SharingEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sharing entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sharing.model.impl.SharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sharing entries
	 * @param end the upper bound of the range of sharing entries (not inclusive)
	 * @return the range of sharing entries
	 */
	@Override
	public List<SharingEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the sharing entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sharing.model.impl.SharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sharing entries
	 * @param end the upper bound of the range of sharing entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of sharing entries
	 */
	@Override
	public List<SharingEntry> findAll(int start, int end,
		OrderByComparator<SharingEntry> orderByComparator) {
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

		List<SharingEntry> list = (List<SharingEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SHARINGENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SHARINGENTRY;

				if (pagination) {
					sql = sql.concat(SharingEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SharingEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SharingEntry>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the sharing entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SharingEntry sharingEntry : findAll()) {
			remove(sharingEntry);
		}
	}

	/**
	 * Returns the number of sharing entries.
	 *
	 * @return the number of sharing entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SHARINGENTRY);

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
	 * Initializes the sharing entry persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(SharingEntryImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_SHARINGENTRY = "SELECT sharingEntry FROM SharingEntry sharingEntry";
	private static final String _SQL_SELECT_SHARINGENTRY_WHERE = "SELECT sharingEntry FROM SharingEntry sharingEntry WHERE ";
	private static final String _SQL_COUNT_SHARINGENTRY = "SELECT COUNT(sharingEntry) FROM SharingEntry sharingEntry";
	private static final String _SQL_COUNT_SHARINGENTRY_WHERE = "SELECT COUNT(sharingEntry) FROM SharingEntry sharingEntry WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "sharingEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SharingEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SharingEntry exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static final Log _log = LogFactoryUtil.getLog(SharingEntryPersistenceImpl.class);
	private static final SharingEntry _nullSharingEntry = new SharingEntryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<SharingEntry> toCacheModel() {
				return _nullSharingEntryCacheModel;
			}
		};

	private static final CacheModel<SharingEntry> _nullSharingEntryCacheModel = new CacheModel<SharingEntry>() {
			@Override
			public SharingEntry toEntityModel() {
				return _nullSharingEntry;
			}
		};
}