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
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQuery;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQueryFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.RowMapper;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
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
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.workflow.kaleo.NoSuchTaskException;
import com.liferay.portal.workflow.kaleo.model.KaleoTask;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTaskPersistence
 * @see       KaleoTaskUtil
 * @generated
 */
public class KaleoTaskPersistenceImpl extends BasePersistenceImpl<KaleoTask>
	implements KaleoTaskPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoTaskImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_COMPANYID = new FinderPath(KaleoTaskModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(KaleoTaskModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByCompanyId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_KALEODEFINITIONID = new FinderPath(KaleoTaskModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByKaleoDefinitionId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEODEFINITIONID = new FinderPath(KaleoTaskModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByKaleoDefinitionId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_KALEONODEID = new FinderPath(KaleoTaskModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_ENTITY,
			"fetchByKaleoNodeId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEONODEID = new FinderPath(KaleoTaskModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByKaleoNodeId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(KaleoTaskModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoTaskModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	public void cacheResult(KaleoTask kaleoTask) {
		EntityCacheUtil.putResult(KaleoTaskModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskImpl.class, kaleoTask.getPrimaryKey(), kaleoTask);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KALEONODEID,
			new Object[] { new Long(kaleoTask.getKaleoNodeId()) }, kaleoTask);
	}

	public void cacheResult(List<KaleoTask> kaleoTasks) {
		for (KaleoTask kaleoTask : kaleoTasks) {
			if (EntityCacheUtil.getResult(
						KaleoTaskModelImpl.ENTITY_CACHE_ENABLED,
						KaleoTaskImpl.class, kaleoTask.getPrimaryKey(), this) == null) {
				cacheResult(kaleoTask);
			}
		}
	}

	public void clearCache() {
		CacheRegistryUtil.clear(KaleoTaskImpl.class.getName());
		EntityCacheUtil.clearCache(KaleoTaskImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public void clearCache(KaleoTask kaleoTask) {
		EntityCacheUtil.removeResult(KaleoTaskModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskImpl.class, kaleoTask.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KALEONODEID,
			new Object[] { new Long(kaleoTask.getKaleoNodeId()) });
	}

	public KaleoTask create(long kaleoTaskId) {
		KaleoTask kaleoTask = new KaleoTaskImpl();

		kaleoTask.setNew(true);
		kaleoTask.setPrimaryKey(kaleoTaskId);

		return kaleoTask;
	}

	public KaleoTask remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public KaleoTask remove(long kaleoTaskId)
		throws NoSuchTaskException, SystemException {
		Session session = null;

		try {
			session = openSession();

			KaleoTask kaleoTask = (KaleoTask)session.get(KaleoTaskImpl.class,
					new Long(kaleoTaskId));

			if (kaleoTask == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + kaleoTaskId);
				}

				throw new NoSuchTaskException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					kaleoTaskId);
			}

			return remove(kaleoTask);
		}
		catch (NoSuchTaskException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoTask removeImpl(KaleoTask kaleoTask)
		throws SystemException {
		kaleoTask = toUnwrappedModel(kaleoTask);

		Session session = null;

		try {
			session = openSession();

			if (kaleoTask.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(KaleoTaskImpl.class,
						kaleoTask.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(kaleoTask);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		KaleoTaskModelImpl kaleoTaskModelImpl = (KaleoTaskModelImpl)kaleoTask;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KALEONODEID,
			new Object[] { new Long(kaleoTaskModelImpl.getOriginalKaleoNodeId()) });

		EntityCacheUtil.removeResult(KaleoTaskModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskImpl.class, kaleoTask.getPrimaryKey());

		return kaleoTask;
	}

	public KaleoTask updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoTask kaleoTask,
		boolean merge) throws SystemException {
		kaleoTask = toUnwrappedModel(kaleoTask);

		boolean isNew = kaleoTask.isNew();

		KaleoTaskModelImpl kaleoTaskModelImpl = (KaleoTaskModelImpl)kaleoTask;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, kaleoTask, merge);

			kaleoTask.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(KaleoTaskModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskImpl.class, kaleoTask.getPrimaryKey(), kaleoTask);

		if (!isNew &&
				(kaleoTask.getKaleoNodeId() != kaleoTaskModelImpl.getOriginalKaleoNodeId())) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KALEONODEID,
				new Object[] {
					new Long(kaleoTaskModelImpl.getOriginalKaleoNodeId())
				});
		}

		if (isNew ||
				(kaleoTask.getKaleoNodeId() != kaleoTaskModelImpl.getOriginalKaleoNodeId())) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KALEONODEID,
				new Object[] { new Long(kaleoTask.getKaleoNodeId()) }, kaleoTask);
		}

		return kaleoTask;
	}

	protected KaleoTask toUnwrappedModel(KaleoTask kaleoTask) {
		if (kaleoTask instanceof KaleoTaskImpl) {
			return kaleoTask;
		}

		KaleoTaskImpl kaleoTaskImpl = new KaleoTaskImpl();

		kaleoTaskImpl.setNew(kaleoTask.isNew());
		kaleoTaskImpl.setPrimaryKey(kaleoTask.getPrimaryKey());

		kaleoTaskImpl.setKaleoTaskId(kaleoTask.getKaleoTaskId());
		kaleoTaskImpl.setGroupId(kaleoTask.getGroupId());
		kaleoTaskImpl.setCompanyId(kaleoTask.getCompanyId());
		kaleoTaskImpl.setUserId(kaleoTask.getUserId());
		kaleoTaskImpl.setUserName(kaleoTask.getUserName());
		kaleoTaskImpl.setCreateDate(kaleoTask.getCreateDate());
		kaleoTaskImpl.setModifiedDate(kaleoTask.getModifiedDate());
		kaleoTaskImpl.setKaleoDefinitionId(kaleoTask.getKaleoDefinitionId());
		kaleoTaskImpl.setKaleoNodeId(kaleoTask.getKaleoNodeId());
		kaleoTaskImpl.setName(kaleoTask.getName());
		kaleoTaskImpl.setDescription(kaleoTask.getDescription());
		kaleoTaskImpl.setDueDateDuration(kaleoTask.getDueDateDuration());
		kaleoTaskImpl.setDueDateScale(kaleoTask.getDueDateScale());

		return kaleoTaskImpl;
	}

	public KaleoTask findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	public KaleoTask findByPrimaryKey(long kaleoTaskId)
		throws NoSuchTaskException, SystemException {
		KaleoTask kaleoTask = fetchByPrimaryKey(kaleoTaskId);

		if (kaleoTask == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + kaleoTaskId);
			}

			throw new NoSuchTaskException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				kaleoTaskId);
		}

		return kaleoTask;
	}

	public KaleoTask fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public KaleoTask fetchByPrimaryKey(long kaleoTaskId)
		throws SystemException {
		KaleoTask kaleoTask = (KaleoTask)EntityCacheUtil.getResult(KaleoTaskModelImpl.ENTITY_CACHE_ENABLED,
				KaleoTaskImpl.class, kaleoTaskId, this);

		if (kaleoTask == null) {
			Session session = null;

			try {
				session = openSession();

				kaleoTask = (KaleoTask)session.get(KaleoTaskImpl.class,
						new Long(kaleoTaskId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (kaleoTask != null) {
					cacheResult(kaleoTask);
				}

				closeSession(session);
			}
		}

		return kaleoTask;
	}

	public List<KaleoTask> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	public List<KaleoTask> findByCompanyId(long companyId, int start, int end)
		throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	public List<KaleoTask> findByCompanyId(long companyId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoTask> list = (List<KaleoTask>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_COMPANYID,
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

				query.append(_SQL_SELECT_KALEOTASK_WHERE);

				query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				else {
					query.append(KaleoTaskModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<KaleoTask>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<KaleoTask>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_COMPANYID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public KaleoTask findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskException, SystemException {
		List<KaleoTask> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoTask findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskException, SystemException {
		int count = countByCompanyId(companyId);

		List<KaleoTask> list = findByCompanyId(companyId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoTask[] findByCompanyId_PrevAndNext(long kaleoTaskId,
		long companyId, OrderByComparator orderByComparator)
		throws NoSuchTaskException, SystemException {
		KaleoTask kaleoTask = findByPrimaryKey(kaleoTaskId);

		Session session = null;

		try {
			session = openSession();

			KaleoTask[] array = new KaleoTaskImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, kaleoTask,
					companyId, orderByComparator, true);

			array[1] = kaleoTask;

			array[2] = getByCompanyId_PrevAndNext(session, kaleoTask,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoTask getByCompanyId_PrevAndNext(Session session,
		KaleoTask kaleoTask, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTASK_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

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
			query.append(KaleoTaskModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoTask);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTask> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	public List<KaleoTask> findByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		return findByKaleoDefinitionId(kaleoDefinitionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	public List<KaleoTask> findByKaleoDefinitionId(long kaleoDefinitionId,
		int start, int end) throws SystemException {
		return findByKaleoDefinitionId(kaleoDefinitionId, start, end, null);
	}

	public List<KaleoTask> findByKaleoDefinitionId(long kaleoDefinitionId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				kaleoDefinitionId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoTask> list = (List<KaleoTask>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_KALEODEFINITIONID,
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

				query.append(_SQL_SELECT_KALEOTASK_WHERE);

				query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				else {
					query.append(KaleoTaskModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoDefinitionId);

				list = (List<KaleoTask>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<KaleoTask>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_KALEODEFINITIONID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public KaleoTask findByKaleoDefinitionId_First(long kaleoDefinitionId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskException, SystemException {
		List<KaleoTask> list = findByKaleoDefinitionId(kaleoDefinitionId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoDefinitionId=");
			msg.append(kaleoDefinitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoTask findByKaleoDefinitionId_Last(long kaleoDefinitionId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskException, SystemException {
		int count = countByKaleoDefinitionId(kaleoDefinitionId);

		List<KaleoTask> list = findByKaleoDefinitionId(kaleoDefinitionId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoDefinitionId=");
			msg.append(kaleoDefinitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoTask[] findByKaleoDefinitionId_PrevAndNext(long kaleoTaskId,
		long kaleoDefinitionId, OrderByComparator orderByComparator)
		throws NoSuchTaskException, SystemException {
		KaleoTask kaleoTask = findByPrimaryKey(kaleoTaskId);

		Session session = null;

		try {
			session = openSession();

			KaleoTask[] array = new KaleoTaskImpl[3];

			array[0] = getByKaleoDefinitionId_PrevAndNext(session, kaleoTask,
					kaleoDefinitionId, orderByComparator, true);

			array[1] = kaleoTask;

			array[2] = getByKaleoDefinitionId_PrevAndNext(session, kaleoTask,
					kaleoDefinitionId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoTask getByKaleoDefinitionId_PrevAndNext(Session session,
		KaleoTask kaleoTask, long kaleoDefinitionId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTASK_WHERE);

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
			query.append(KaleoTaskModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoDefinitionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoTask);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTask> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	public KaleoTask findByKaleoNodeId(long kaleoNodeId)
		throws NoSuchTaskException, SystemException {
		KaleoTask kaleoTask = fetchByKaleoNodeId(kaleoNodeId);

		if (kaleoTask == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoNodeId=");
			msg.append(kaleoNodeId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchTaskException(msg.toString());
		}

		return kaleoTask;
	}

	public KaleoTask fetchByKaleoNodeId(long kaleoNodeId)
		throws SystemException {
		return fetchByKaleoNodeId(kaleoNodeId, true);
	}

	public KaleoTask fetchByKaleoNodeId(long kaleoNodeId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { kaleoNodeId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_KALEONODEID,
					finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(3);

				query.append(_SQL_SELECT_KALEOTASK_WHERE);

				query.append(_FINDER_COLUMN_KALEONODEID_KALEONODEID_2);

				query.append(KaleoTaskModelImpl.ORDER_BY_JPQL);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoNodeId);

				List<KaleoTask> list = q.list();

				result = list;

				KaleoTask kaleoTask = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KALEONODEID,
						finderArgs, list);
				}
				else {
					kaleoTask = list.get(0);

					cacheResult(kaleoTask);

					if ((kaleoTask.getKaleoNodeId() != kaleoNodeId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KALEONODEID,
							finderArgs, kaleoTask);
					}
				}

				return kaleoTask;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KALEONODEID,
						finderArgs, new ArrayList<KaleoTask>());
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (KaleoTask)result;
			}
		}
	}

	public List<KaleoTask> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<KaleoTask> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<KaleoTask> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoTask> list = (List<KaleoTask>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_KALEOTASK);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}
				else {
					sql = _SQL_SELECT_KALEOTASK.concat(KaleoTaskModelImpl.ORDER_BY_JPQL);
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<KaleoTask>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<KaleoTask>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<KaleoTask>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByCompanyId(long companyId) throws SystemException {
		for (KaleoTask kaleoTask : findByCompanyId(companyId)) {
			remove(kaleoTask);
		}
	}

	public void removeByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		for (KaleoTask kaleoTask : findByKaleoDefinitionId(kaleoDefinitionId)) {
			remove(kaleoTask);
		}
	}

	public void removeByKaleoNodeId(long kaleoNodeId)
		throws NoSuchTaskException, SystemException {
		KaleoTask kaleoTask = findByKaleoNodeId(kaleoNodeId);

		remove(kaleoTask);
	}

	public void removeAll() throws SystemException {
		for (KaleoTask kaleoTask : findAll()) {
			remove(kaleoTask);
		}
	}

	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_KALEOTASK_WHERE);

				query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COMPANYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		Object[] finderArgs = new Object[] { kaleoDefinitionId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_KALEOTASK_WHERE);

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

	public int countByKaleoNodeId(long kaleoNodeId) throws SystemException {
		Object[] finderArgs = new Object[] { kaleoNodeId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KALEONODEID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_KALEOTASK_WHERE);

				query.append(_FINDER_COLUMN_KALEONODEID_KALEONODEID_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoNodeId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KALEONODEID,
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

				Query q = session.createQuery(_SQL_COUNT_KALEOTASK);

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

	public List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> getKaleoTaskAssignments(
		long pk) throws SystemException {
		return getKaleoTaskAssignments(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> getKaleoTaskAssignments(
		long pk, int start, int end) throws SystemException {
		return getKaleoTaskAssignments(pk, start, end, null);
	}

	public static final FinderPath FINDER_PATH_GET_KALEOTASKASSIGNMENTS = new FinderPath(com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.service.persistence.KaleoTaskAssignmentPersistenceImpl.FINDER_CLASS_NAME_LIST,
			"getKaleoTaskAssignments",
			new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});

	public List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> getKaleoTaskAssignments(
		long pk, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				pk, String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> list = (List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment>)FinderCacheUtil.getResult(FINDER_PATH_GET_KALEOTASKASSIGNMENTS,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				String sql = null;

				if (orderByComparator != null) {
					sql = _SQL_GETKALEOTASKASSIGNMENTS.concat(ORDER_BY_CLAUSE)
													  .concat(orderByComparator.getOrderBy());
				}
				else {
					sql = _SQL_GETKALEOTASKASSIGNMENTS.concat(com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentModelImpl.ORDER_BY_SQL);
				}

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("Kaleo_KaleoTaskAssignment",
					com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				list = (List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment>();
				}

				kaleoTaskAssignmentPersistence.cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_GET_KALEOTASKASSIGNMENTS,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public static final FinderPath FINDER_PATH_GET_KALEOTASKASSIGNMENTS_SIZE = new FinderPath(com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.service.persistence.KaleoTaskAssignmentPersistenceImpl.FINDER_CLASS_NAME_LIST,
			"getKaleoTaskAssignmentsSize", new String[] { Long.class.getName() });

	public int getKaleoTaskAssignmentsSize(long pk) throws SystemException {
		Object[] finderArgs = new Object[] { pk };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_GET_KALEOTASKASSIGNMENTS_SIZE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETKALEOTASKASSIGNMENTSSIZE);

				q.addScalar(COUNT_COLUMN_NAME,
					com.liferay.portal.kernel.dao.orm.Type.LONG);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_GET_KALEOTASKASSIGNMENTS_SIZE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public static final FinderPath FINDER_PATH_CONTAINS_KALEOTASKASSIGNMENT = new FinderPath(com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.service.persistence.KaleoTaskAssignmentPersistenceImpl.FINDER_CLASS_NAME_LIST,
			"containsKaleoTaskAssignment",
			new String[] { Long.class.getName(), Long.class.getName() });

	public boolean containsKaleoTaskAssignment(long pk,
		long kaleoTaskAssignmentPK) throws SystemException {
		Object[] finderArgs = new Object[] { pk, kaleoTaskAssignmentPK };

		Boolean value = (Boolean)FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_KALEOTASKASSIGNMENT,
				finderArgs, this);

		if (value == null) {
			try {
				value = Boolean.valueOf(containsKaleoTaskAssignment.contains(
							pk, kaleoTaskAssignmentPK));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (value == null) {
					value = Boolean.FALSE;
				}

				FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_KALEOTASKASSIGNMENT,
					finderArgs, value);
			}
		}

		return value.booleanValue();
	}

	public boolean containsKaleoTaskAssignments(long pk)
		throws SystemException {
		if (getKaleoTaskAssignmentsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portal.workflow.kaleo.model.KaleoTask")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<KaleoTask>> listenersList = new ArrayList<ModelListener<KaleoTask>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<KaleoTask>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		containsKaleoTaskAssignment = new ContainsKaleoTaskAssignment(this);
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
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	protected ContainsKaleoTaskAssignment containsKaleoTaskAssignment;

	protected class ContainsKaleoTaskAssignment {
		protected ContainsKaleoTaskAssignment(
			KaleoTaskPersistenceImpl persistenceImpl) {
			super();

			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSKALEOTASKASSIGNMENT,
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
					RowMapper.COUNT);
		}

		protected boolean contains(long kaleoTaskId, long kaleoTaskAssignmentId) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						new Long(kaleoTaskId), new Long(kaleoTaskAssignmentId)
					});

			if (results.size() > 0) {
				Integer count = results.get(0);

				if (count.intValue() > 0) {
					return true;
				}
			}

			return false;
		}

		private MappingSqlQuery<Integer> _mappingSqlQuery;
	}

	private static final String _SQL_SELECT_KALEOTASK = "SELECT kaleoTask FROM KaleoTask kaleoTask";
	private static final String _SQL_SELECT_KALEOTASK_WHERE = "SELECT kaleoTask FROM KaleoTask kaleoTask WHERE ";
	private static final String _SQL_COUNT_KALEOTASK = "SELECT COUNT(kaleoTask) FROM KaleoTask kaleoTask";
	private static final String _SQL_COUNT_KALEOTASK_WHERE = "SELECT COUNT(kaleoTask) FROM KaleoTask kaleoTask WHERE ";
	private static final String _SQL_GETKALEOTASKASSIGNMENTS = "SELECT {Kaleo_KaleoTaskAssignment.*} FROM Kaleo_KaleoTaskAssignment INNER JOIN Kaleo_KaleoTask ON (Kaleo_KaleoTask.kaleoTaskId = Kaleo_KaleoTaskAssignment.kaleoTaskId) WHERE (Kaleo_KaleoTask.kaleoTaskId = ?)";
	private static final String _SQL_GETKALEOTASKASSIGNMENTSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM Kaleo_KaleoTaskAssignment WHERE kaleoTaskId = ?";
	private static final String _SQL_CONTAINSKALEOTASKASSIGNMENT = "SELECT COUNT(*) AS COUNT_VALUE FROM Kaleo_KaleoTaskAssignment WHERE kaleoTaskId = ? AND kaleoTaskAssignmentId = ?";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "kaleoTask.companyId = ?";
	private static final String _FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2 =
		"kaleoTask.kaleoDefinitionId = ?";
	private static final String _FINDER_COLUMN_KALEONODEID_KALEONODEID_2 = "kaleoTask.kaleoNodeId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoTask.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoTask exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoTask exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(KaleoTaskPersistenceImpl.class);
}