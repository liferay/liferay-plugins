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
import com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskInstanceTokenImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskInstanceTokenModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="KaleoTaskInstanceTokenPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTaskInstanceTokenPersistence
 * @see       KaleoTaskInstanceTokenUtil
 * @generated
 */
public class KaleoTaskInstanceTokenPersistenceImpl extends BasePersistenceImpl<KaleoTaskInstanceToken>
	implements KaleoTaskInstanceTokenPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoTaskInstanceTokenImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_COMPANYID = new FinderPath(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_KALEODEFINITIONID = new FinderPath(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByKaleoDefinitionId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEODEFINITIONID = new FinderPath(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByKaleoDefinitionId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_KALEOINSTANCEID = new FinderPath(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByKaleoInstanceId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEOINSTANCEID = new FinderPath(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByKaleoInstanceId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	public void cacheResult(KaleoTaskInstanceToken kaleoTaskInstanceToken) {
		EntityCacheUtil.putResult(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenImpl.class,
			kaleoTaskInstanceToken.getPrimaryKey(), kaleoTaskInstanceToken);
	}

	public void cacheResult(
		List<KaleoTaskInstanceToken> kaleoTaskInstanceTokens) {
		for (KaleoTaskInstanceToken kaleoTaskInstanceToken : kaleoTaskInstanceTokens) {
			if (EntityCacheUtil.getResult(
						KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
						KaleoTaskInstanceTokenImpl.class,
						kaleoTaskInstanceToken.getPrimaryKey(), this) == null) {
				cacheResult(kaleoTaskInstanceToken);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(KaleoTaskInstanceTokenImpl.class.getName());
		EntityCacheUtil.clearCache(KaleoTaskInstanceTokenImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public void clearCache(KaleoTaskInstanceToken kaleoTaskInstanceToken) {
		EntityCacheUtil.removeResult(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenImpl.class,
			kaleoTaskInstanceToken.getPrimaryKey());
	}

	public KaleoTaskInstanceToken create(long kaleoTaskInstanceTokenId) {
		KaleoTaskInstanceToken kaleoTaskInstanceToken = new KaleoTaskInstanceTokenImpl();

		kaleoTaskInstanceToken.setNew(true);
		kaleoTaskInstanceToken.setPrimaryKey(kaleoTaskInstanceTokenId);

		return kaleoTaskInstanceToken;
	}

	public KaleoTaskInstanceToken remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public KaleoTaskInstanceToken remove(long kaleoTaskInstanceTokenId)
		throws NoSuchTaskInstanceTokenException, SystemException {
		Session session = null;

		try {
			session = openSession();

			KaleoTaskInstanceToken kaleoTaskInstanceToken = (KaleoTaskInstanceToken)session.get(KaleoTaskInstanceTokenImpl.class,
					new Long(kaleoTaskInstanceTokenId));

			if (kaleoTaskInstanceToken == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						kaleoTaskInstanceTokenId);
				}

				throw new NoSuchTaskInstanceTokenException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					kaleoTaskInstanceTokenId);
			}

			return remove(kaleoTaskInstanceToken);
		}
		catch (NoSuchTaskInstanceTokenException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoTaskInstanceToken removeImpl(
		KaleoTaskInstanceToken kaleoTaskInstanceToken)
		throws SystemException {
		kaleoTaskInstanceToken = toUnwrappedModel(kaleoTaskInstanceToken);

		Session session = null;

		try {
			session = openSession();

			if (kaleoTaskInstanceToken.isCachedModel() ||
					BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(KaleoTaskInstanceTokenImpl.class,
						kaleoTaskInstanceToken.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(kaleoTaskInstanceToken);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenImpl.class,
			kaleoTaskInstanceToken.getPrimaryKey());

		return kaleoTaskInstanceToken;
	}

	public KaleoTaskInstanceToken updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken,
		boolean merge) throws SystemException {
		kaleoTaskInstanceToken = toUnwrappedModel(kaleoTaskInstanceToken);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, kaleoTaskInstanceToken, merge);

			kaleoTaskInstanceToken.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenImpl.class,
			kaleoTaskInstanceToken.getPrimaryKey(), kaleoTaskInstanceToken);

		return kaleoTaskInstanceToken;
	}

	protected KaleoTaskInstanceToken toUnwrappedModel(
		KaleoTaskInstanceToken kaleoTaskInstanceToken) {
		if (kaleoTaskInstanceToken instanceof KaleoTaskInstanceTokenImpl) {
			return kaleoTaskInstanceToken;
		}

		KaleoTaskInstanceTokenImpl kaleoTaskInstanceTokenImpl = new KaleoTaskInstanceTokenImpl();

		kaleoTaskInstanceTokenImpl.setNew(kaleoTaskInstanceToken.isNew());
		kaleoTaskInstanceTokenImpl.setPrimaryKey(kaleoTaskInstanceToken.getPrimaryKey());

		kaleoTaskInstanceTokenImpl.setKaleoTaskInstanceTokenId(kaleoTaskInstanceToken.getKaleoTaskInstanceTokenId());
		kaleoTaskInstanceTokenImpl.setGroupId(kaleoTaskInstanceToken.getGroupId());
		kaleoTaskInstanceTokenImpl.setCompanyId(kaleoTaskInstanceToken.getCompanyId());
		kaleoTaskInstanceTokenImpl.setUserId(kaleoTaskInstanceToken.getUserId());
		kaleoTaskInstanceTokenImpl.setUserName(kaleoTaskInstanceToken.getUserName());
		kaleoTaskInstanceTokenImpl.setCreateDate(kaleoTaskInstanceToken.getCreateDate());
		kaleoTaskInstanceTokenImpl.setModifiedDate(kaleoTaskInstanceToken.getModifiedDate());
		kaleoTaskInstanceTokenImpl.setKaleoDefinitionId(kaleoTaskInstanceToken.getKaleoDefinitionId());
		kaleoTaskInstanceTokenImpl.setKaleoInstanceId(kaleoTaskInstanceToken.getKaleoInstanceId());
		kaleoTaskInstanceTokenImpl.setKaleoInstanceTokenId(kaleoTaskInstanceToken.getKaleoInstanceTokenId());
		kaleoTaskInstanceTokenImpl.setKaleoTaskId(kaleoTaskInstanceToken.getKaleoTaskId());
		kaleoTaskInstanceTokenImpl.setKaleoTaskName(kaleoTaskInstanceToken.getKaleoTaskName());
		kaleoTaskInstanceTokenImpl.setClassName(kaleoTaskInstanceToken.getClassName());
		kaleoTaskInstanceTokenImpl.setClassPK(kaleoTaskInstanceToken.getClassPK());
		kaleoTaskInstanceTokenImpl.setCompletionUserId(kaleoTaskInstanceToken.getCompletionUserId());
		kaleoTaskInstanceTokenImpl.setCompleted(kaleoTaskInstanceToken.isCompleted());
		kaleoTaskInstanceTokenImpl.setCompletionDate(kaleoTaskInstanceToken.getCompletionDate());
		kaleoTaskInstanceTokenImpl.setDueDate(kaleoTaskInstanceToken.getDueDate());
		kaleoTaskInstanceTokenImpl.setWorkflowContext(kaleoTaskInstanceToken.getWorkflowContext());

		return kaleoTaskInstanceTokenImpl;
	}

	public KaleoTaskInstanceToken findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	public KaleoTaskInstanceToken findByPrimaryKey(
		long kaleoTaskInstanceTokenId)
		throws NoSuchTaskInstanceTokenException, SystemException {
		KaleoTaskInstanceToken kaleoTaskInstanceToken = fetchByPrimaryKey(kaleoTaskInstanceTokenId);

		if (kaleoTaskInstanceToken == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					kaleoTaskInstanceTokenId);
			}

			throw new NoSuchTaskInstanceTokenException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				kaleoTaskInstanceTokenId);
		}

		return kaleoTaskInstanceToken;
	}

	public KaleoTaskInstanceToken fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public KaleoTaskInstanceToken fetchByPrimaryKey(
		long kaleoTaskInstanceTokenId) throws SystemException {
		KaleoTaskInstanceToken kaleoTaskInstanceToken = (KaleoTaskInstanceToken)EntityCacheUtil.getResult(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
				KaleoTaskInstanceTokenImpl.class, kaleoTaskInstanceTokenId, this);

		if (kaleoTaskInstanceToken == null) {
			Session session = null;

			try {
				session = openSession();

				kaleoTaskInstanceToken = (KaleoTaskInstanceToken)session.get(KaleoTaskInstanceTokenImpl.class,
						new Long(kaleoTaskInstanceTokenId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (kaleoTaskInstanceToken != null) {
					cacheResult(kaleoTaskInstanceToken);
				}

				closeSession(session);
			}
		}

		return kaleoTaskInstanceToken;
	}

	public List<KaleoTaskInstanceToken> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	public List<KaleoTaskInstanceToken> findByCompanyId(long companyId,
		int start, int end) throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	public List<KaleoTaskInstanceToken> findByCompanyId(long companyId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(companyId),
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoTaskInstanceToken> list = (List<KaleoTaskInstanceToken>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_COMPANYID,
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

				query.append(_SQL_SELECT_KALEOTASKINSTANCETOKEN_WHERE);

				query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				else {
					query.append(KaleoTaskInstanceTokenModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<KaleoTaskInstanceToken>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<KaleoTaskInstanceToken>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_COMPANYID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public KaleoTaskInstanceToken findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskInstanceTokenException, SystemException {
		List<KaleoTaskInstanceToken> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskInstanceTokenException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoTaskInstanceToken findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskInstanceTokenException, SystemException {
		int count = countByCompanyId(companyId);

		List<KaleoTaskInstanceToken> list = findByCompanyId(companyId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskInstanceTokenException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoTaskInstanceToken[] findByCompanyId_PrevAndNext(
		long kaleoTaskInstanceTokenId, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskInstanceTokenException, SystemException {
		KaleoTaskInstanceToken kaleoTaskInstanceToken = findByPrimaryKey(kaleoTaskInstanceTokenId);

		Session session = null;

		try {
			session = openSession();

			KaleoTaskInstanceToken[] array = new KaleoTaskInstanceTokenImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session,
					kaleoTaskInstanceToken, companyId, orderByComparator, true);

			array[1] = kaleoTaskInstanceToken;

			array[2] = getByCompanyId_PrevAndNext(session,
					kaleoTaskInstanceToken, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoTaskInstanceToken getByCompanyId_PrevAndNext(
		Session session, KaleoTaskInstanceToken kaleoTaskInstanceToken,
		long companyId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTASKINSTANCETOKEN_WHERE);

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
			query.append(KaleoTaskInstanceTokenModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoTaskInstanceToken);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTaskInstanceToken> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	public List<KaleoTaskInstanceToken> findByKaleoDefinitionId(
		long kaleoDefinitionId) throws SystemException {
		return findByKaleoDefinitionId(kaleoDefinitionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	public List<KaleoTaskInstanceToken> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end) throws SystemException {
		return findByKaleoDefinitionId(kaleoDefinitionId, start, end, null);
	}

	public List<KaleoTaskInstanceToken> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(kaleoDefinitionId),
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoTaskInstanceToken> list = (List<KaleoTaskInstanceToken>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_KALEODEFINITIONID,
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

				query.append(_SQL_SELECT_KALEOTASKINSTANCETOKEN_WHERE);

				query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				else {
					query.append(KaleoTaskInstanceTokenModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoDefinitionId);

				list = (List<KaleoTaskInstanceToken>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<KaleoTaskInstanceToken>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_KALEODEFINITIONID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public KaleoTaskInstanceToken findByKaleoDefinitionId_First(
		long kaleoDefinitionId, OrderByComparator orderByComparator)
		throws NoSuchTaskInstanceTokenException, SystemException {
		List<KaleoTaskInstanceToken> list = findByKaleoDefinitionId(kaleoDefinitionId,
				0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoDefinitionId=");
			msg.append(kaleoDefinitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskInstanceTokenException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoTaskInstanceToken findByKaleoDefinitionId_Last(
		long kaleoDefinitionId, OrderByComparator orderByComparator)
		throws NoSuchTaskInstanceTokenException, SystemException {
		int count = countByKaleoDefinitionId(kaleoDefinitionId);

		List<KaleoTaskInstanceToken> list = findByKaleoDefinitionId(kaleoDefinitionId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoDefinitionId=");
			msg.append(kaleoDefinitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskInstanceTokenException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoTaskInstanceToken[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoTaskInstanceTokenId, long kaleoDefinitionId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskInstanceTokenException, SystemException {
		KaleoTaskInstanceToken kaleoTaskInstanceToken = findByPrimaryKey(kaleoTaskInstanceTokenId);

		Session session = null;

		try {
			session = openSession();

			KaleoTaskInstanceToken[] array = new KaleoTaskInstanceTokenImpl[3];

			array[0] = getByKaleoDefinitionId_PrevAndNext(session,
					kaleoTaskInstanceToken, kaleoDefinitionId,
					orderByComparator, true);

			array[1] = kaleoTaskInstanceToken;

			array[2] = getByKaleoDefinitionId_PrevAndNext(session,
					kaleoTaskInstanceToken, kaleoDefinitionId,
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

	protected KaleoTaskInstanceToken getByKaleoDefinitionId_PrevAndNext(
		Session session, KaleoTaskInstanceToken kaleoTaskInstanceToken,
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

		query.append(_SQL_SELECT_KALEOTASKINSTANCETOKEN_WHERE);

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
			query.append(KaleoTaskInstanceTokenModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoDefinitionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoTaskInstanceToken);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTaskInstanceToken> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	public List<KaleoTaskInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId) throws SystemException {
		return findByKaleoInstanceId(kaleoInstanceId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	public List<KaleoTaskInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end) throws SystemException {
		return findByKaleoInstanceId(kaleoInstanceId, start, end, null);
	}

	public List<KaleoTaskInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(kaleoInstanceId),
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoTaskInstanceToken> list = (List<KaleoTaskInstanceToken>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_KALEOINSTANCEID,
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

				query.append(_SQL_SELECT_KALEOTASKINSTANCETOKEN_WHERE);

				query.append(_FINDER_COLUMN_KALEOINSTANCEID_KALEOINSTANCEID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				else {
					query.append(KaleoTaskInstanceTokenModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceId);

				list = (List<KaleoTaskInstanceToken>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<KaleoTaskInstanceToken>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_KALEOINSTANCEID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public KaleoTaskInstanceToken findByKaleoInstanceId_First(
		long kaleoInstanceId, OrderByComparator orderByComparator)
		throws NoSuchTaskInstanceTokenException, SystemException {
		List<KaleoTaskInstanceToken> list = findByKaleoInstanceId(kaleoInstanceId,
				0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoInstanceId=");
			msg.append(kaleoInstanceId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskInstanceTokenException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoTaskInstanceToken findByKaleoInstanceId_Last(
		long kaleoInstanceId, OrderByComparator orderByComparator)
		throws NoSuchTaskInstanceTokenException, SystemException {
		int count = countByKaleoInstanceId(kaleoInstanceId);

		List<KaleoTaskInstanceToken> list = findByKaleoInstanceId(kaleoInstanceId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoInstanceId=");
			msg.append(kaleoInstanceId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskInstanceTokenException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoTaskInstanceToken[] findByKaleoInstanceId_PrevAndNext(
		long kaleoTaskInstanceTokenId, long kaleoInstanceId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskInstanceTokenException, SystemException {
		KaleoTaskInstanceToken kaleoTaskInstanceToken = findByPrimaryKey(kaleoTaskInstanceTokenId);

		Session session = null;

		try {
			session = openSession();

			KaleoTaskInstanceToken[] array = new KaleoTaskInstanceTokenImpl[3];

			array[0] = getByKaleoInstanceId_PrevAndNext(session,
					kaleoTaskInstanceToken, kaleoInstanceId, orderByComparator,
					true);

			array[1] = kaleoTaskInstanceToken;

			array[2] = getByKaleoInstanceId_PrevAndNext(session,
					kaleoTaskInstanceToken, kaleoInstanceId, orderByComparator,
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

	protected KaleoTaskInstanceToken getByKaleoInstanceId_PrevAndNext(
		Session session, KaleoTaskInstanceToken kaleoTaskInstanceToken,
		long kaleoInstanceId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTASKINSTANCETOKEN_WHERE);

		query.append(_FINDER_COLUMN_KALEOINSTANCEID_KALEOINSTANCEID_2);

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
			query.append(KaleoTaskInstanceTokenModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoInstanceId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoTaskInstanceToken);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTaskInstanceToken> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	public List<KaleoTaskInstanceToken> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<KaleoTaskInstanceToken> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<KaleoTaskInstanceToken> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoTaskInstanceToken> list = (List<KaleoTaskInstanceToken>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_KALEOTASKINSTANCETOKEN);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}

				else {
					sql = _SQL_SELECT_KALEOTASKINSTANCETOKEN.concat(KaleoTaskInstanceTokenModelImpl.ORDER_BY_JPQL);
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<KaleoTaskInstanceToken>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<KaleoTaskInstanceToken>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<KaleoTaskInstanceToken>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByCompanyId(long companyId) throws SystemException {
		for (KaleoTaskInstanceToken kaleoTaskInstanceToken : findByCompanyId(
				companyId)) {
			remove(kaleoTaskInstanceToken);
		}
	}

	public void removeByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		for (KaleoTaskInstanceToken kaleoTaskInstanceToken : findByKaleoDefinitionId(
				kaleoDefinitionId)) {
			remove(kaleoTaskInstanceToken);
		}
	}

	public void removeByKaleoInstanceId(long kaleoInstanceId)
		throws SystemException {
		for (KaleoTaskInstanceToken kaleoTaskInstanceToken : findByKaleoInstanceId(
				kaleoInstanceId)) {
			remove(kaleoTaskInstanceToken);
		}
	}

	public void removeAll() throws SystemException {
		for (KaleoTaskInstanceToken kaleoTaskInstanceToken : findAll()) {
			remove(kaleoTaskInstanceToken);
		}
	}

	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(companyId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_KALEOTASKINSTANCETOKEN_WHERE);

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
		Object[] finderArgs = new Object[] { new Long(kaleoDefinitionId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_KALEOTASKINSTANCETOKEN_WHERE);

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

	public int countByKaleoInstanceId(long kaleoInstanceId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(kaleoInstanceId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KALEOINSTANCEID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_KALEOTASKINSTANCETOKEN_WHERE);

				query.append(_FINDER_COLUMN_KALEOINSTANCEID_KALEOINSTANCEID_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KALEOINSTANCEID,
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

				Query q = session.createQuery(_SQL_COUNT_KALEOTASKINSTANCETOKEN);

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

	public List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> getKaleoTaskAssignmentInstances(
		long pk) throws SystemException {
		return getKaleoTaskAssignmentInstances(pk, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);
	}

	public List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> getKaleoTaskAssignmentInstances(
		long pk, int start, int end) throws SystemException {
		return getKaleoTaskAssignmentInstances(pk, start, end, null);
	}

	public static final FinderPath FINDER_PATH_GET_KALEOTASKASSIGNMENTINSTANCES = new FinderPath(com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl.FINDER_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.service.persistence.KaleoTaskAssignmentInstancePersistenceImpl.FINDER_CLASS_NAME_LIST,
			"getKaleoTaskAssignmentInstances",
			new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});

	public List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> getKaleoTaskAssignmentInstances(
		long pk, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(pk), String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> list =
			(List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance>)FinderCacheUtil.getResult(FINDER_PATH_GET_KALEOTASKASSIGNMENTINSTANCES,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				String sql = null;

				if (orderByComparator != null) {
					sql = _SQL_GETKALEOTASKASSIGNMENTINSTANCES.concat(ORDER_BY_CLAUSE)
															  .concat(orderByComparator.getOrderBy());
				}

				else {
					sql = _SQL_GETKALEOTASKASSIGNMENTINSTANCES.concat(com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl.ORDER_BY_SQL);
				}

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("Kaleo_KaleoTaskAssignmentInstance",
					com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				list = (List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance>();
				}

				kaleoTaskAssignmentInstancePersistence.cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_GET_KALEOTASKASSIGNMENTINSTANCES,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public static final FinderPath FINDER_PATH_GET_KALEOTASKASSIGNMENTINSTANCES_SIZE =
		new FinderPath(com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl.FINDER_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.service.persistence.KaleoTaskAssignmentInstancePersistenceImpl.FINDER_CLASS_NAME_LIST,
			"getKaleoTaskAssignmentInstancesSize",
			new String[] { Long.class.getName() });

	public int getKaleoTaskAssignmentInstancesSize(long pk)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(pk) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_GET_KALEOTASKASSIGNMENTINSTANCES_SIZE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETKALEOTASKASSIGNMENTINSTANCESSIZE);

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

				FinderCacheUtil.putResult(FINDER_PATH_GET_KALEOTASKASSIGNMENTINSTANCES_SIZE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public static final FinderPath FINDER_PATH_CONTAINS_KALEOTASKASSIGNMENTINSTANCE =
		new FinderPath(com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl.FINDER_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.service.persistence.KaleoTaskAssignmentInstancePersistenceImpl.FINDER_CLASS_NAME_LIST,
			"containsKaleoTaskAssignmentInstance",
			new String[] { Long.class.getName(), Long.class.getName() });

	public boolean containsKaleoTaskAssignmentInstance(long pk,
		long kaleoTaskAssignmentInstancePK) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(pk),
				
				new Long(kaleoTaskAssignmentInstancePK)
			};

		Boolean value = (Boolean)FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_KALEOTASKASSIGNMENTINSTANCE,
				finderArgs, this);

		if (value == null) {
			try {
				value = Boolean.valueOf(containsKaleoTaskAssignmentInstance.contains(
							pk, kaleoTaskAssignmentInstancePK));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (value == null) {
					value = Boolean.FALSE;
				}

				FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_KALEOTASKASSIGNMENTINSTANCE,
					finderArgs, value);
			}
		}

		return value.booleanValue();
	}

	public boolean containsKaleoTaskAssignmentInstances(long pk)
		throws SystemException {
		if (getKaleoTaskAssignmentInstancesSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<KaleoTaskInstanceToken>> listenersList = new ArrayList<ModelListener<KaleoTaskInstanceToken>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<KaleoTaskInstanceToken>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		containsKaleoTaskAssignmentInstance = new ContainsKaleoTaskAssignmentInstance(this);
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
	protected ContainsKaleoTaskAssignmentInstance containsKaleoTaskAssignmentInstance;

	protected class ContainsKaleoTaskAssignmentInstance {
		protected ContainsKaleoTaskAssignmentInstance(
			KaleoTaskInstanceTokenPersistenceImpl persistenceImpl) {
			super();

			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSKALEOTASKASSIGNMENTINSTANCE,
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
					RowMapper.COUNT);
		}

		protected boolean contains(long kaleoTaskInstanceTokenId,
			long kaleoTaskAssignmentInstanceId) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						new Long(kaleoTaskInstanceTokenId),
						new Long(kaleoTaskAssignmentInstanceId)
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

	private static final String _SQL_SELECT_KALEOTASKINSTANCETOKEN = "SELECT kaleoTaskInstanceToken FROM KaleoTaskInstanceToken kaleoTaskInstanceToken";
	private static final String _SQL_SELECT_KALEOTASKINSTANCETOKEN_WHERE = "SELECT kaleoTaskInstanceToken FROM KaleoTaskInstanceToken kaleoTaskInstanceToken WHERE ";
	private static final String _SQL_COUNT_KALEOTASKINSTANCETOKEN = "SELECT COUNT(kaleoTaskInstanceToken) FROM KaleoTaskInstanceToken kaleoTaskInstanceToken";
	private static final String _SQL_COUNT_KALEOTASKINSTANCETOKEN_WHERE = "SELECT COUNT(kaleoTaskInstanceToken) FROM KaleoTaskInstanceToken kaleoTaskInstanceToken WHERE ";
	private static final String _SQL_GETKALEOTASKASSIGNMENTINSTANCES = "SELECT {Kaleo_KaleoTaskAssignmentInstance.*} FROM Kaleo_KaleoTaskAssignmentInstance INNER JOIN Kaleo_KaleoTaskInstanceToken ON (Kaleo_KaleoTaskInstanceToken.kaleoTaskInstanceTokenId = Kaleo_KaleoTaskAssignmentInstance.kaleoTaskInstanceTokenId) WHERE (Kaleo_KaleoTaskInstanceToken.kaleoTaskInstanceTokenId = ?)";
	private static final String _SQL_GETKALEOTASKASSIGNMENTINSTANCESSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM Kaleo_KaleoTaskAssignmentInstance WHERE kaleoTaskInstanceTokenId = ?";
	private static final String _SQL_CONTAINSKALEOTASKASSIGNMENTINSTANCE = "SELECT COUNT(*) AS COUNT_VALUE FROM Kaleo_KaleoTaskAssignmentInstance WHERE kaleoTaskInstanceTokenId = ? AND kaleoTaskAssignmentInstanceId = ?";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "kaleoTaskInstanceToken.companyId = ?";
	private static final String _FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2 =
		"kaleoTaskInstanceToken.kaleoDefinitionId = ?";
	private static final String _FINDER_COLUMN_KALEOINSTANCEID_KALEOINSTANCEID_2 =
		"kaleoTaskInstanceToken.kaleoInstanceId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoTaskInstanceToken.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoTaskInstanceToken exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoTaskInstanceToken exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(KaleoTaskInstanceTokenPersistenceImpl.class);
}