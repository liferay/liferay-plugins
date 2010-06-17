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

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistry;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.RolePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="KaleoTaskAssignmentPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTaskAssignmentPersistence
 * @see       KaleoTaskAssignmentUtil
 * @generated
 */
public class KaleoTaskAssignmentPersistenceImpl extends BasePersistenceImpl<KaleoTaskAssignment>
	implements KaleoTaskAssignmentPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoTaskAssignmentImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_KALEODEFINITIONID = new FinderPath(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByKaleoDefinitionId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEODEFINITIONID = new FinderPath(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByKaleoDefinitionId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_KALEOTASKID = new FinderPath(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByKaleoTaskId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEOTASKID = new FinderPath(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByKaleoTaskId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_ACN_KTI = new FinderPath(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByACN_KTI",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_ACN_KTI = new FinderPath(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByACN_KTI",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	public void cacheResult(KaleoTaskAssignment kaleoTaskAssignment) {
		EntityCacheUtil.putResult(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentImpl.class, kaleoTaskAssignment.getPrimaryKey(),
			kaleoTaskAssignment);
	}

	public void cacheResult(List<KaleoTaskAssignment> kaleoTaskAssignments) {
		for (KaleoTaskAssignment kaleoTaskAssignment : kaleoTaskAssignments) {
			if (EntityCacheUtil.getResult(
						KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
						KaleoTaskAssignmentImpl.class,
						kaleoTaskAssignment.getPrimaryKey(), this) == null) {
				cacheResult(kaleoTaskAssignment);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(KaleoTaskAssignmentImpl.class.getName());
		EntityCacheUtil.clearCache(KaleoTaskAssignmentImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public void clearCache(KaleoTaskAssignment kaleoTaskAssignment) {
		EntityCacheUtil.removeResult(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentImpl.class, kaleoTaskAssignment.getPrimaryKey());
	}

	public KaleoTaskAssignment create(long kaleoTaskAssignmentId) {
		KaleoTaskAssignment kaleoTaskAssignment = new KaleoTaskAssignmentImpl();

		kaleoTaskAssignment.setNew(true);
		kaleoTaskAssignment.setPrimaryKey(kaleoTaskAssignmentId);

		return kaleoTaskAssignment;
	}

	public KaleoTaskAssignment remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public KaleoTaskAssignment remove(long kaleoTaskAssignmentId)
		throws NoSuchTaskAssignmentException, SystemException {
		Session session = null;

		try {
			session = openSession();

			KaleoTaskAssignment kaleoTaskAssignment = (KaleoTaskAssignment)session.get(KaleoTaskAssignmentImpl.class,
					new Long(kaleoTaskAssignmentId));

			if (kaleoTaskAssignment == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						kaleoTaskAssignmentId);
				}

				throw new NoSuchTaskAssignmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					kaleoTaskAssignmentId);
			}

			return remove(kaleoTaskAssignment);
		}
		catch (NoSuchTaskAssignmentException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoTaskAssignment removeImpl(
		KaleoTaskAssignment kaleoTaskAssignment) throws SystemException {
		kaleoTaskAssignment = toUnwrappedModel(kaleoTaskAssignment);

		Session session = null;

		try {
			session = openSession();

			if (kaleoTaskAssignment.isCachedModel() ||
					BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(KaleoTaskAssignmentImpl.class,
						kaleoTaskAssignment.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(kaleoTaskAssignment);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentImpl.class, kaleoTaskAssignment.getPrimaryKey());

		return kaleoTaskAssignment;
	}

	public KaleoTaskAssignment updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment kaleoTaskAssignment,
		boolean merge) throws SystemException {
		kaleoTaskAssignment = toUnwrappedModel(kaleoTaskAssignment);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, kaleoTaskAssignment, merge);

			kaleoTaskAssignment.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentImpl.class, kaleoTaskAssignment.getPrimaryKey(),
			kaleoTaskAssignment);

		return kaleoTaskAssignment;
	}

	protected KaleoTaskAssignment toUnwrappedModel(
		KaleoTaskAssignment kaleoTaskAssignment) {
		if (kaleoTaskAssignment instanceof KaleoTaskAssignmentImpl) {
			return kaleoTaskAssignment;
		}

		KaleoTaskAssignmentImpl kaleoTaskAssignmentImpl = new KaleoTaskAssignmentImpl();

		kaleoTaskAssignmentImpl.setNew(kaleoTaskAssignment.isNew());
		kaleoTaskAssignmentImpl.setPrimaryKey(kaleoTaskAssignment.getPrimaryKey());

		kaleoTaskAssignmentImpl.setKaleoTaskAssignmentId(kaleoTaskAssignment.getKaleoTaskAssignmentId());
		kaleoTaskAssignmentImpl.setGroupId(kaleoTaskAssignment.getGroupId());
		kaleoTaskAssignmentImpl.setCompanyId(kaleoTaskAssignment.getCompanyId());
		kaleoTaskAssignmentImpl.setUserId(kaleoTaskAssignment.getUserId());
		kaleoTaskAssignmentImpl.setUserName(kaleoTaskAssignment.getUserName());
		kaleoTaskAssignmentImpl.setCreateDate(kaleoTaskAssignment.getCreateDate());
		kaleoTaskAssignmentImpl.setModifiedDate(kaleoTaskAssignment.getModifiedDate());
		kaleoTaskAssignmentImpl.setKaleoDefinitionId(kaleoTaskAssignment.getKaleoDefinitionId());
		kaleoTaskAssignmentImpl.setKaleoNodeId(kaleoTaskAssignment.getKaleoNodeId());
		kaleoTaskAssignmentImpl.setKaleoTaskId(kaleoTaskAssignment.getKaleoTaskId());
		kaleoTaskAssignmentImpl.setAssigneeClassName(kaleoTaskAssignment.getAssigneeClassName());
		kaleoTaskAssignmentImpl.setAssigneeClassPK(kaleoTaskAssignment.getAssigneeClassPK());

		return kaleoTaskAssignmentImpl;
	}

	public KaleoTaskAssignment findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	public KaleoTaskAssignment findByPrimaryKey(long kaleoTaskAssignmentId)
		throws NoSuchTaskAssignmentException, SystemException {
		KaleoTaskAssignment kaleoTaskAssignment = fetchByPrimaryKey(kaleoTaskAssignmentId);

		if (kaleoTaskAssignment == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					kaleoTaskAssignmentId);
			}

			throw new NoSuchTaskAssignmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				kaleoTaskAssignmentId);
		}

		return kaleoTaskAssignment;
	}

	public KaleoTaskAssignment fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public KaleoTaskAssignment fetchByPrimaryKey(long kaleoTaskAssignmentId)
		throws SystemException {
		KaleoTaskAssignment kaleoTaskAssignment = (KaleoTaskAssignment)EntityCacheUtil.getResult(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
				KaleoTaskAssignmentImpl.class, kaleoTaskAssignmentId, this);

		if (kaleoTaskAssignment == null) {
			Session session = null;

			try {
				session = openSession();

				kaleoTaskAssignment = (KaleoTaskAssignment)session.get(KaleoTaskAssignmentImpl.class,
						new Long(kaleoTaskAssignmentId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (kaleoTaskAssignment != null) {
					cacheResult(kaleoTaskAssignment);
				}

				closeSession(session);
			}
		}

		return kaleoTaskAssignment;
	}

	public List<KaleoTaskAssignment> findByKaleoDefinitionId(
		long kaleoDefinitionId) throws SystemException {
		return findByKaleoDefinitionId(kaleoDefinitionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	public List<KaleoTaskAssignment> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end) throws SystemException {
		return findByKaleoDefinitionId(kaleoDefinitionId, start, end, null);
	}

	public List<KaleoTaskAssignment> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(kaleoDefinitionId),
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoTaskAssignment> list = (List<KaleoTaskAssignment>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_KALEODEFINITIONID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;

				if (orderByComparator != null) {
					query = new StringBundler(3 +
							(orderByComparator.getOrderByFields().length * 3));
				}
				else {
					query = new StringBundler(3);
				}

				query.append(_SQL_SELECT_KALEOTASKASSIGNMENT_WHERE);

				query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				else {
					query.append(KaleoTaskAssignmentModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoDefinitionId);

				list = (List<KaleoTaskAssignment>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<KaleoTaskAssignment>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_KALEODEFINITIONID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public KaleoTaskAssignment findByKaleoDefinitionId_First(
		long kaleoDefinitionId, OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentException, SystemException {
		List<KaleoTaskAssignment> list = findByKaleoDefinitionId(kaleoDefinitionId,
				0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoDefinitionId=");
			msg.append(kaleoDefinitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskAssignmentException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoTaskAssignment findByKaleoDefinitionId_Last(
		long kaleoDefinitionId, OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentException, SystemException {
		int count = countByKaleoDefinitionId(kaleoDefinitionId);

		List<KaleoTaskAssignment> list = findByKaleoDefinitionId(kaleoDefinitionId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoDefinitionId=");
			msg.append(kaleoDefinitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskAssignmentException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoTaskAssignment[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoTaskAssignmentId, long kaleoDefinitionId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentException, SystemException {
		KaleoTaskAssignment kaleoTaskAssignment = findByPrimaryKey(kaleoTaskAssignmentId);

		Session session = null;

		try {
			session = openSession();

			KaleoTaskAssignment[] array = new KaleoTaskAssignmentImpl[3];

			array[0] = getByKaleoDefinitionId_PrevAndNext(session,
					kaleoTaskAssignment, kaleoDefinitionId, orderByComparator,
					true);

			array[1] = kaleoTaskAssignment;

			array[2] = getByKaleoDefinitionId_PrevAndNext(session,
					kaleoTaskAssignment, kaleoDefinitionId, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoTaskAssignment getByKaleoDefinitionId_PrevAndNext(
		Session session, KaleoTaskAssignment kaleoTaskAssignment,
		long kaleoDefinitionId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTASKASSIGNMENT_WHERE);

		query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
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
			query.append(KaleoTaskAssignmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoDefinitionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoTaskAssignment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTaskAssignment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	public List<KaleoTaskAssignment> findByKaleoTaskId(long kaleoTaskId)
		throws SystemException {
		return findByKaleoTaskId(kaleoTaskId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	public List<KaleoTaskAssignment> findByKaleoTaskId(long kaleoTaskId,
		int start, int end) throws SystemException {
		return findByKaleoTaskId(kaleoTaskId, start, end, null);
	}

	public List<KaleoTaskAssignment> findByKaleoTaskId(long kaleoTaskId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(kaleoTaskId),
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoTaskAssignment> list = (List<KaleoTaskAssignment>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_KALEOTASKID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;

				if (orderByComparator != null) {
					query = new StringBundler(3 +
							(orderByComparator.getOrderByFields().length * 3));
				}
				else {
					query = new StringBundler(3);
				}

				query.append(_SQL_SELECT_KALEOTASKASSIGNMENT_WHERE);

				query.append(_FINDER_COLUMN_KALEOTASKID_KALEOTASKID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				else {
					query.append(KaleoTaskAssignmentModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoTaskId);

				list = (List<KaleoTaskAssignment>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<KaleoTaskAssignment>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_KALEOTASKID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public KaleoTaskAssignment findByKaleoTaskId_First(long kaleoTaskId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentException, SystemException {
		List<KaleoTaskAssignment> list = findByKaleoTaskId(kaleoTaskId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoTaskId=");
			msg.append(kaleoTaskId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskAssignmentException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoTaskAssignment findByKaleoTaskId_Last(long kaleoTaskId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentException, SystemException {
		int count = countByKaleoTaskId(kaleoTaskId);

		List<KaleoTaskAssignment> list = findByKaleoTaskId(kaleoTaskId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoTaskId=");
			msg.append(kaleoTaskId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskAssignmentException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoTaskAssignment[] findByKaleoTaskId_PrevAndNext(
		long kaleoTaskAssignmentId, long kaleoTaskId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentException, SystemException {
		KaleoTaskAssignment kaleoTaskAssignment = findByPrimaryKey(kaleoTaskAssignmentId);

		Session session = null;

		try {
			session = openSession();

			KaleoTaskAssignment[] array = new KaleoTaskAssignmentImpl[3];

			array[0] = getByKaleoTaskId_PrevAndNext(session,
					kaleoTaskAssignment, kaleoTaskId, orderByComparator, true);

			array[1] = kaleoTaskAssignment;

			array[2] = getByKaleoTaskId_PrevAndNext(session,
					kaleoTaskAssignment, kaleoTaskId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoTaskAssignment getByKaleoTaskId_PrevAndNext(
		Session session, KaleoTaskAssignment kaleoTaskAssignment,
		long kaleoTaskId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTASKASSIGNMENT_WHERE);

		query.append(_FINDER_COLUMN_KALEOTASKID_KALEOTASKID_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
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
			query.append(KaleoTaskAssignmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoTaskId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoTaskAssignment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTaskAssignment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	public List<KaleoTaskAssignment> findByACN_KTI(String assigneeClassName,
		long kaleoTaskId) throws SystemException {
		return findByACN_KTI(assigneeClassName, kaleoTaskId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	public List<KaleoTaskAssignment> findByACN_KTI(String assigneeClassName,
		long kaleoTaskId, int start, int end) throws SystemException {
		return findByACN_KTI(assigneeClassName, kaleoTaskId, start, end, null);
	}

	public List<KaleoTaskAssignment> findByACN_KTI(String assigneeClassName,
		long kaleoTaskId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				assigneeClassName, new Long(kaleoTaskId),
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoTaskAssignment> list = (List<KaleoTaskAssignment>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_ACN_KTI,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;

				if (orderByComparator != null) {
					query = new StringBundler(4 +
							(orderByComparator.getOrderByFields().length * 3));
				}
				else {
					query = new StringBundler(4);
				}

				query.append(_SQL_SELECT_KALEOTASKASSIGNMENT_WHERE);

				if (assigneeClassName == null) {
					query.append(_FINDER_COLUMN_ACN_KTI_ASSIGNEECLASSNAME_1);
				}
				else {
					if (assigneeClassName.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_ACN_KTI_ASSIGNEECLASSNAME_3);
					}
					else {
						query.append(_FINDER_COLUMN_ACN_KTI_ASSIGNEECLASSNAME_2);
					}
				}

				query.append(_FINDER_COLUMN_ACN_KTI_KALEOTASKID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				else {
					query.append(KaleoTaskAssignmentModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (assigneeClassName != null) {
					qPos.add(assigneeClassName);
				}

				qPos.add(kaleoTaskId);

				list = (List<KaleoTaskAssignment>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<KaleoTaskAssignment>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_ACN_KTI,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public KaleoTaskAssignment findByACN_KTI_First(String assigneeClassName,
		long kaleoTaskId, OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentException, SystemException {
		List<KaleoTaskAssignment> list = findByACN_KTI(assigneeClassName,
				kaleoTaskId, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("assigneeClassName=");
			msg.append(assigneeClassName);

			msg.append(", kaleoTaskId=");
			msg.append(kaleoTaskId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskAssignmentException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoTaskAssignment findByACN_KTI_Last(String assigneeClassName,
		long kaleoTaskId, OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentException, SystemException {
		int count = countByACN_KTI(assigneeClassName, kaleoTaskId);

		List<KaleoTaskAssignment> list = findByACN_KTI(assigneeClassName,
				kaleoTaskId, count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("assigneeClassName=");
			msg.append(assigneeClassName);

			msg.append(", kaleoTaskId=");
			msg.append(kaleoTaskId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskAssignmentException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoTaskAssignment[] findByACN_KTI_PrevAndNext(
		long kaleoTaskAssignmentId, String assigneeClassName, long kaleoTaskId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentException, SystemException {
		KaleoTaskAssignment kaleoTaskAssignment = findByPrimaryKey(kaleoTaskAssignmentId);

		Session session = null;

		try {
			session = openSession();

			KaleoTaskAssignment[] array = new KaleoTaskAssignmentImpl[3];

			array[0] = getByACN_KTI_PrevAndNext(session, kaleoTaskAssignment,
					assigneeClassName, kaleoTaskId, orderByComparator, true);

			array[1] = kaleoTaskAssignment;

			array[2] = getByACN_KTI_PrevAndNext(session, kaleoTaskAssignment,
					assigneeClassName, kaleoTaskId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoTaskAssignment getByACN_KTI_PrevAndNext(Session session,
		KaleoTaskAssignment kaleoTaskAssignment, String assigneeClassName,
		long kaleoTaskId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTASKASSIGNMENT_WHERE);

		if (assigneeClassName == null) {
			query.append(_FINDER_COLUMN_ACN_KTI_ASSIGNEECLASSNAME_1);
		}
		else {
			if (assigneeClassName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ACN_KTI_ASSIGNEECLASSNAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_ACN_KTI_ASSIGNEECLASSNAME_2);
			}
		}

		query.append(_FINDER_COLUMN_ACN_KTI_KALEOTASKID_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
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
			query.append(KaleoTaskAssignmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (assigneeClassName != null) {
			qPos.add(assigneeClassName);
		}

		qPos.add(kaleoTaskId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoTaskAssignment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTaskAssignment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	public List<KaleoTaskAssignment> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<KaleoTaskAssignment> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<KaleoTaskAssignment> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoTaskAssignment> list = (List<KaleoTaskAssignment>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;
				String sql = null;

				if (orderByComparator != null) {
					query = new StringBundler(2 +
							(orderByComparator.getOrderByFields().length * 3));

					query.append(_SQL_SELECT_KALEOTASKASSIGNMENT);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}

				else {
					sql = _SQL_SELECT_KALEOTASKASSIGNMENT.concat(KaleoTaskAssignmentModelImpl.ORDER_BY_JPQL);
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<KaleoTaskAssignment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<KaleoTaskAssignment>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<KaleoTaskAssignment>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		for (KaleoTaskAssignment kaleoTaskAssignment : findByKaleoDefinitionId(
				kaleoDefinitionId)) {
			remove(kaleoTaskAssignment);
		}
	}

	public void removeByKaleoTaskId(long kaleoTaskId) throws SystemException {
		for (KaleoTaskAssignment kaleoTaskAssignment : findByKaleoTaskId(
				kaleoTaskId)) {
			remove(kaleoTaskAssignment);
		}
	}

	public void removeByACN_KTI(String assigneeClassName, long kaleoTaskId)
		throws SystemException {
		for (KaleoTaskAssignment kaleoTaskAssignment : findByACN_KTI(
				assigneeClassName, kaleoTaskId)) {
			remove(kaleoTaskAssignment);
		}
	}

	public void removeAll() throws SystemException {
		for (KaleoTaskAssignment kaleoTaskAssignment : findAll()) {
			remove(kaleoTaskAssignment);
		}
	}

	public int countByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(kaleoDefinitionId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_KALEOTASKASSIGNMENT_WHERE);

				query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoDefinitionId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByKaleoTaskId(long kaleoTaskId) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(kaleoTaskId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KALEOTASKID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_KALEOTASKASSIGNMENT_WHERE);

				query.append(_FINDER_COLUMN_KALEOTASKID_KALEOTASKID_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoTaskId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KALEOTASKID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByACN_KTI(String assigneeClassName, long kaleoTaskId)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				assigneeClassName, new Long(kaleoTaskId)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ACN_KTI,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(3);

				query.append(_SQL_COUNT_KALEOTASKASSIGNMENT_WHERE);

				if (assigneeClassName == null) {
					query.append(_FINDER_COLUMN_ACN_KTI_ASSIGNEECLASSNAME_1);
				}
				else {
					if (assigneeClassName.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_ACN_KTI_ASSIGNEECLASSNAME_3);
					}
					else {
						query.append(_FINDER_COLUMN_ACN_KTI_ASSIGNEECLASSNAME_2);
					}
				}

				query.append(_FINDER_COLUMN_ACN_KTI_KALEOTASKID_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (assigneeClassName != null) {
					qPos.add(assigneeClassName);
				}

				qPos.add(kaleoTaskId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ACN_KTI,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_KALEOTASKASSIGNMENT);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<KaleoTaskAssignment>> listenersList = new ArrayList<ModelListener<KaleoTaskAssignment>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<KaleoTaskAssignment>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(type = KaleoActionPersistence.class)
	protected KaleoActionPersistence kaleoActionPersistence;
	@BeanReference(type = KaleoDefinitionPersistence.class)
	protected KaleoDefinitionPersistence kaleoDefinitionPersistence;
	@BeanReference(type = KaleoInstancePersistence.class)
	protected KaleoInstancePersistence kaleoInstancePersistence;
	@BeanReference(type = KaleoInstanceTokenPersistence.class)
	protected KaleoInstanceTokenPersistence kaleoInstanceTokenPersistence;
	@BeanReference(type = KaleoLogPersistence.class)
	protected KaleoLogPersistence kaleoLogPersistence;
	@BeanReference(type = KaleoNodePersistence.class)
	protected KaleoNodePersistence kaleoNodePersistence;
	@BeanReference(type = KaleoNotificationPersistence.class)
	protected KaleoNotificationPersistence kaleoNotificationPersistence;
	@BeanReference(type = KaleoNotificationRecipientPersistence.class)
	protected KaleoNotificationRecipientPersistence kaleoNotificationRecipientPersistence;
	@BeanReference(type = KaleoTaskPersistence.class)
	protected KaleoTaskPersistence kaleoTaskPersistence;
	@BeanReference(type = KaleoTaskAssignmentPersistence.class)
	protected KaleoTaskAssignmentPersistence kaleoTaskAssignmentPersistence;
	@BeanReference(type = KaleoTaskAssignmentInstancePersistence.class)
	protected KaleoTaskAssignmentInstancePersistence kaleoTaskAssignmentInstancePersistence;
	@BeanReference(type = KaleoTaskInstanceTokenPersistence.class)
	protected KaleoTaskInstanceTokenPersistence kaleoTaskInstanceTokenPersistence;
	@BeanReference(type = KaleoTransitionPersistence.class)
	protected KaleoTransitionPersistence kaleoTransitionPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = RolePersistence.class)
	protected RolePersistence rolePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_KALEOTASKASSIGNMENT = "SELECT kaleoTaskAssignment FROM KaleoTaskAssignment kaleoTaskAssignment";
	private static final String _SQL_SELECT_KALEOTASKASSIGNMENT_WHERE = "SELECT kaleoTaskAssignment FROM KaleoTaskAssignment kaleoTaskAssignment WHERE ";
	private static final String _SQL_COUNT_KALEOTASKASSIGNMENT = "SELECT COUNT(kaleoTaskAssignment) FROM KaleoTaskAssignment kaleoTaskAssignment";
	private static final String _SQL_COUNT_KALEOTASKASSIGNMENT_WHERE = "SELECT COUNT(kaleoTaskAssignment) FROM KaleoTaskAssignment kaleoTaskAssignment WHERE ";
	private static final String _FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2 =
		"kaleoTaskAssignment.kaleoDefinitionId = ?";
	private static final String _FINDER_COLUMN_KALEOTASKID_KALEOTASKID_2 = "kaleoTaskAssignment.kaleoTaskId = ?";
	private static final String _FINDER_COLUMN_ACN_KTI_ASSIGNEECLASSNAME_1 = "kaleoTaskAssignment.assigneeClassName IS NULL AND ";
	private static final String _FINDER_COLUMN_ACN_KTI_ASSIGNEECLASSNAME_2 = "kaleoTaskAssignment.assigneeClassName = ? AND ";
	private static final String _FINDER_COLUMN_ACN_KTI_ASSIGNEECLASSNAME_3 = "(kaleoTaskAssignment.assigneeClassName IS NULL OR kaleoTaskAssignment.assigneeClassName = ?) AND ";
	private static final String _FINDER_COLUMN_ACN_KTI_KALEOTASKID_2 = "kaleoTaskAssignment.kaleoTaskId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoTaskAssignment.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoTaskAssignment exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoTaskAssignment exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(KaleoTaskAssignmentPersistenceImpl.class);
}