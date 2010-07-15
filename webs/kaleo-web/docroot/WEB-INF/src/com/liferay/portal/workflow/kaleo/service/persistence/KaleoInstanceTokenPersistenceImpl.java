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
import com.liferay.portal.kernel.util.CalendarUtil;
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
import com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceTokenImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceTokenModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author    Brian Wing Shun Chan
 * @see       KaleoInstanceTokenPersistence
 * @see       KaleoInstanceTokenUtil
 * @generated
 */
public class KaleoInstanceTokenPersistenceImpl extends BasePersistenceImpl<KaleoInstanceToken>
	implements KaleoInstanceTokenPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoInstanceTokenImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_COMPANYID = new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_KALEODEFINITIONID = new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByKaleoDefinitionId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEODEFINITIONID = new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByKaleoDefinitionId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_KALEOINSTANCEID = new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByKaleoInstanceId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEOINSTANCEID = new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByKaleoInstanceId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_C_PKITI = new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByC_PKITI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_C_PKITI = new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByC_PKITI",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_C_PKITI_CD = new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByC_PKITI_CD",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_C_PKITI_CD = new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByC_PKITI_CD",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName()
			});
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	public void cacheResult(KaleoInstanceToken kaleoInstanceToken) {
		EntityCacheUtil.putResult(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenImpl.class, kaleoInstanceToken.getPrimaryKey(),
			kaleoInstanceToken);
	}

	public void cacheResult(List<KaleoInstanceToken> kaleoInstanceTokens) {
		for (KaleoInstanceToken kaleoInstanceToken : kaleoInstanceTokens) {
			if (EntityCacheUtil.getResult(
						KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
						KaleoInstanceTokenImpl.class,
						kaleoInstanceToken.getPrimaryKey(), this) == null) {
				cacheResult(kaleoInstanceToken);
			}
		}
	}

	public void clearCache() {
		CacheRegistryUtil.clear(KaleoInstanceTokenImpl.class.getName());
		EntityCacheUtil.clearCache(KaleoInstanceTokenImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public void clearCache(KaleoInstanceToken kaleoInstanceToken) {
		EntityCacheUtil.removeResult(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenImpl.class, kaleoInstanceToken.getPrimaryKey());
	}

	public KaleoInstanceToken create(long kaleoInstanceTokenId) {
		KaleoInstanceToken kaleoInstanceToken = new KaleoInstanceTokenImpl();

		kaleoInstanceToken.setNew(true);
		kaleoInstanceToken.setPrimaryKey(kaleoInstanceTokenId);

		return kaleoInstanceToken;
	}

	public KaleoInstanceToken remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public KaleoInstanceToken remove(long kaleoInstanceTokenId)
		throws NoSuchInstanceTokenException, SystemException {
		Session session = null;

		try {
			session = openSession();

			KaleoInstanceToken kaleoInstanceToken = (KaleoInstanceToken)session.get(KaleoInstanceTokenImpl.class,
					new Long(kaleoInstanceTokenId));

			if (kaleoInstanceToken == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						kaleoInstanceTokenId);
				}

				throw new NoSuchInstanceTokenException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					kaleoInstanceTokenId);
			}

			return remove(kaleoInstanceToken);
		}
		catch (NoSuchInstanceTokenException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoInstanceToken removeImpl(
		KaleoInstanceToken kaleoInstanceToken) throws SystemException {
		kaleoInstanceToken = toUnwrappedModel(kaleoInstanceToken);

		Session session = null;

		try {
			session = openSession();

			if (kaleoInstanceToken.isCachedModel() ||
					BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(KaleoInstanceTokenImpl.class,
						kaleoInstanceToken.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(kaleoInstanceToken);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenImpl.class, kaleoInstanceToken.getPrimaryKey());

		return kaleoInstanceToken;
	}

	public KaleoInstanceToken updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken,
		boolean merge) throws SystemException {
		kaleoInstanceToken = toUnwrappedModel(kaleoInstanceToken);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, kaleoInstanceToken, merge);

			kaleoInstanceToken.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenImpl.class, kaleoInstanceToken.getPrimaryKey(),
			kaleoInstanceToken);

		return kaleoInstanceToken;
	}

	protected KaleoInstanceToken toUnwrappedModel(
		KaleoInstanceToken kaleoInstanceToken) {
		if (kaleoInstanceToken instanceof KaleoInstanceTokenImpl) {
			return kaleoInstanceToken;
		}

		KaleoInstanceTokenImpl kaleoInstanceTokenImpl = new KaleoInstanceTokenImpl();

		kaleoInstanceTokenImpl.setNew(kaleoInstanceToken.isNew());
		kaleoInstanceTokenImpl.setPrimaryKey(kaleoInstanceToken.getPrimaryKey());

		kaleoInstanceTokenImpl.setKaleoInstanceTokenId(kaleoInstanceToken.getKaleoInstanceTokenId());
		kaleoInstanceTokenImpl.setGroupId(kaleoInstanceToken.getGroupId());
		kaleoInstanceTokenImpl.setCompanyId(kaleoInstanceToken.getCompanyId());
		kaleoInstanceTokenImpl.setUserId(kaleoInstanceToken.getUserId());
		kaleoInstanceTokenImpl.setUserName(kaleoInstanceToken.getUserName());
		kaleoInstanceTokenImpl.setCreateDate(kaleoInstanceToken.getCreateDate());
		kaleoInstanceTokenImpl.setModifiedDate(kaleoInstanceToken.getModifiedDate());
		kaleoInstanceTokenImpl.setKaleoDefinitionId(kaleoInstanceToken.getKaleoDefinitionId());
		kaleoInstanceTokenImpl.setKaleoInstanceId(kaleoInstanceToken.getKaleoInstanceId());
		kaleoInstanceTokenImpl.setParentKaleoInstanceTokenId(kaleoInstanceToken.getParentKaleoInstanceTokenId());
		kaleoInstanceTokenImpl.setCurrentKaleoNodeId(kaleoInstanceToken.getCurrentKaleoNodeId());
		kaleoInstanceTokenImpl.setCurrentKaleoNodeName(kaleoInstanceToken.getCurrentKaleoNodeName());
		kaleoInstanceTokenImpl.setClassName(kaleoInstanceToken.getClassName());
		kaleoInstanceTokenImpl.setClassPK(kaleoInstanceToken.getClassPK());
		kaleoInstanceTokenImpl.setCompleted(kaleoInstanceToken.isCompleted());
		kaleoInstanceTokenImpl.setCompletionDate(kaleoInstanceToken.getCompletionDate());

		return kaleoInstanceTokenImpl;
	}

	public KaleoInstanceToken findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	public KaleoInstanceToken findByPrimaryKey(long kaleoInstanceTokenId)
		throws NoSuchInstanceTokenException, SystemException {
		KaleoInstanceToken kaleoInstanceToken = fetchByPrimaryKey(kaleoInstanceTokenId);

		if (kaleoInstanceToken == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					kaleoInstanceTokenId);
			}

			throw new NoSuchInstanceTokenException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				kaleoInstanceTokenId);
		}

		return kaleoInstanceToken;
	}

	public KaleoInstanceToken fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public KaleoInstanceToken fetchByPrimaryKey(long kaleoInstanceTokenId)
		throws SystemException {
		KaleoInstanceToken kaleoInstanceToken = (KaleoInstanceToken)EntityCacheUtil.getResult(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
				KaleoInstanceTokenImpl.class, kaleoInstanceTokenId, this);

		if (kaleoInstanceToken == null) {
			Session session = null;

			try {
				session = openSession();

				kaleoInstanceToken = (KaleoInstanceToken)session.get(KaleoInstanceTokenImpl.class,
						new Long(kaleoInstanceTokenId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (kaleoInstanceToken != null) {
					cacheResult(kaleoInstanceToken);
				}

				closeSession(session);
			}
		}

		return kaleoInstanceToken;
	}

	public List<KaleoInstanceToken> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	public List<KaleoInstanceToken> findByCompanyId(long companyId, int start,
		int end) throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	public List<KaleoInstanceToken> findByCompanyId(long companyId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoInstanceToken> list = (List<KaleoInstanceToken>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_COMPANYID,
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

				query.append(_SQL_SELECT_KALEOINSTANCETOKEN_WHERE);

				query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				else {
					query.append(KaleoInstanceTokenModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<KaleoInstanceToken>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<KaleoInstanceToken>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_COMPANYID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public KaleoInstanceToken findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchInstanceTokenException, SystemException {
		List<KaleoInstanceToken> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchInstanceTokenException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoInstanceToken findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchInstanceTokenException, SystemException {
		int count = countByCompanyId(companyId);

		List<KaleoInstanceToken> list = findByCompanyId(companyId, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchInstanceTokenException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoInstanceToken[] findByCompanyId_PrevAndNext(
		long kaleoInstanceTokenId, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchInstanceTokenException, SystemException {
		KaleoInstanceToken kaleoInstanceToken = findByPrimaryKey(kaleoInstanceTokenId);

		Session session = null;

		try {
			session = openSession();

			KaleoInstanceToken[] array = new KaleoInstanceTokenImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, kaleoInstanceToken,
					companyId, orderByComparator, true);

			array[1] = kaleoInstanceToken;

			array[2] = getByCompanyId_PrevAndNext(session, kaleoInstanceToken,
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

	protected KaleoInstanceToken getByCompanyId_PrevAndNext(Session session,
		KaleoInstanceToken kaleoInstanceToken, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOINSTANCETOKEN_WHERE);

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
			query.append(KaleoInstanceTokenModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoInstanceToken);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoInstanceToken> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	public List<KaleoInstanceToken> findByKaleoDefinitionId(
		long kaleoDefinitionId) throws SystemException {
		return findByKaleoDefinitionId(kaleoDefinitionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	public List<KaleoInstanceToken> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end) throws SystemException {
		return findByKaleoDefinitionId(kaleoDefinitionId, start, end, null);
	}

	public List<KaleoInstanceToken> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				kaleoDefinitionId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoInstanceToken> list = (List<KaleoInstanceToken>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_KALEODEFINITIONID,
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

				query.append(_SQL_SELECT_KALEOINSTANCETOKEN_WHERE);

				query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				else {
					query.append(KaleoInstanceTokenModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoDefinitionId);

				list = (List<KaleoInstanceToken>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<KaleoInstanceToken>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_KALEODEFINITIONID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public KaleoInstanceToken findByKaleoDefinitionId_First(
		long kaleoDefinitionId, OrderByComparator orderByComparator)
		throws NoSuchInstanceTokenException, SystemException {
		List<KaleoInstanceToken> list = findByKaleoDefinitionId(kaleoDefinitionId,
				0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoDefinitionId=");
			msg.append(kaleoDefinitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchInstanceTokenException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoInstanceToken findByKaleoDefinitionId_Last(
		long kaleoDefinitionId, OrderByComparator orderByComparator)
		throws NoSuchInstanceTokenException, SystemException {
		int count = countByKaleoDefinitionId(kaleoDefinitionId);

		List<KaleoInstanceToken> list = findByKaleoDefinitionId(kaleoDefinitionId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoDefinitionId=");
			msg.append(kaleoDefinitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchInstanceTokenException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoInstanceToken[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoInstanceTokenId, long kaleoDefinitionId,
		OrderByComparator orderByComparator)
		throws NoSuchInstanceTokenException, SystemException {
		KaleoInstanceToken kaleoInstanceToken = findByPrimaryKey(kaleoInstanceTokenId);

		Session session = null;

		try {
			session = openSession();

			KaleoInstanceToken[] array = new KaleoInstanceTokenImpl[3];

			array[0] = getByKaleoDefinitionId_PrevAndNext(session,
					kaleoInstanceToken, kaleoDefinitionId, orderByComparator,
					true);

			array[1] = kaleoInstanceToken;

			array[2] = getByKaleoDefinitionId_PrevAndNext(session,
					kaleoInstanceToken, kaleoDefinitionId, orderByComparator,
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

	protected KaleoInstanceToken getByKaleoDefinitionId_PrevAndNext(
		Session session, KaleoInstanceToken kaleoInstanceToken,
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

		query.append(_SQL_SELECT_KALEOINSTANCETOKEN_WHERE);

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
			query.append(KaleoInstanceTokenModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoDefinitionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoInstanceToken);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoInstanceToken> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	public List<KaleoInstanceToken> findByKaleoInstanceId(long kaleoInstanceId)
		throws SystemException {
		return findByKaleoInstanceId(kaleoInstanceId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	public List<KaleoInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end) throws SystemException {
		return findByKaleoInstanceId(kaleoInstanceId, start, end, null);
	}

	public List<KaleoInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				kaleoInstanceId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoInstanceToken> list = (List<KaleoInstanceToken>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_KALEOINSTANCEID,
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

				query.append(_SQL_SELECT_KALEOINSTANCETOKEN_WHERE);

				query.append(_FINDER_COLUMN_KALEOINSTANCEID_KALEOINSTANCEID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				else {
					query.append(KaleoInstanceTokenModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceId);

				list = (List<KaleoInstanceToken>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<KaleoInstanceToken>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_KALEOINSTANCEID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public KaleoInstanceToken findByKaleoInstanceId_First(
		long kaleoInstanceId, OrderByComparator orderByComparator)
		throws NoSuchInstanceTokenException, SystemException {
		List<KaleoInstanceToken> list = findByKaleoInstanceId(kaleoInstanceId,
				0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoInstanceId=");
			msg.append(kaleoInstanceId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchInstanceTokenException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoInstanceToken findByKaleoInstanceId_Last(long kaleoInstanceId,
		OrderByComparator orderByComparator)
		throws NoSuchInstanceTokenException, SystemException {
		int count = countByKaleoInstanceId(kaleoInstanceId);

		List<KaleoInstanceToken> list = findByKaleoInstanceId(kaleoInstanceId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoInstanceId=");
			msg.append(kaleoInstanceId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchInstanceTokenException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoInstanceToken[] findByKaleoInstanceId_PrevAndNext(
		long kaleoInstanceTokenId, long kaleoInstanceId,
		OrderByComparator orderByComparator)
		throws NoSuchInstanceTokenException, SystemException {
		KaleoInstanceToken kaleoInstanceToken = findByPrimaryKey(kaleoInstanceTokenId);

		Session session = null;

		try {
			session = openSession();

			KaleoInstanceToken[] array = new KaleoInstanceTokenImpl[3];

			array[0] = getByKaleoInstanceId_PrevAndNext(session,
					kaleoInstanceToken, kaleoInstanceId, orderByComparator, true);

			array[1] = kaleoInstanceToken;

			array[2] = getByKaleoInstanceId_PrevAndNext(session,
					kaleoInstanceToken, kaleoInstanceId, orderByComparator,
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

	protected KaleoInstanceToken getByKaleoInstanceId_PrevAndNext(
		Session session, KaleoInstanceToken kaleoInstanceToken,
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

		query.append(_SQL_SELECT_KALEOINSTANCETOKEN_WHERE);

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
			query.append(KaleoInstanceTokenModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoInstanceId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoInstanceToken);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoInstanceToken> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	public List<KaleoInstanceToken> findByC_PKITI(long companyId,
		long parentKaleoInstanceTokenId) throws SystemException {
		return findByC_PKITI(companyId, parentKaleoInstanceTokenId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<KaleoInstanceToken> findByC_PKITI(long companyId,
		long parentKaleoInstanceTokenId, int start, int end)
		throws SystemException {
		return findByC_PKITI(companyId, parentKaleoInstanceTokenId, start, end,
			null);
	}

	public List<KaleoInstanceToken> findByC_PKITI(long companyId,
		long parentKaleoInstanceTokenId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId, parentKaleoInstanceTokenId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoInstanceToken> list = (List<KaleoInstanceToken>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_C_PKITI,
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

				query.append(_SQL_SELECT_KALEOINSTANCETOKEN_WHERE);

				query.append(_FINDER_COLUMN_C_PKITI_COMPANYID_2);

				query.append(_FINDER_COLUMN_C_PKITI_PARENTKALEOINSTANCETOKENID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				else {
					query.append(KaleoInstanceTokenModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(parentKaleoInstanceTokenId);

				list = (List<KaleoInstanceToken>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<KaleoInstanceToken>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_C_PKITI,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public KaleoInstanceToken findByC_PKITI_First(long companyId,
		long parentKaleoInstanceTokenId, OrderByComparator orderByComparator)
		throws NoSuchInstanceTokenException, SystemException {
		List<KaleoInstanceToken> list = findByC_PKITI(companyId,
				parentKaleoInstanceTokenId, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", parentKaleoInstanceTokenId=");
			msg.append(parentKaleoInstanceTokenId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchInstanceTokenException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoInstanceToken findByC_PKITI_Last(long companyId,
		long parentKaleoInstanceTokenId, OrderByComparator orderByComparator)
		throws NoSuchInstanceTokenException, SystemException {
		int count = countByC_PKITI(companyId, parentKaleoInstanceTokenId);

		List<KaleoInstanceToken> list = findByC_PKITI(companyId,
				parentKaleoInstanceTokenId, count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", parentKaleoInstanceTokenId=");
			msg.append(parentKaleoInstanceTokenId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchInstanceTokenException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoInstanceToken[] findByC_PKITI_PrevAndNext(
		long kaleoInstanceTokenId, long companyId,
		long parentKaleoInstanceTokenId, OrderByComparator orderByComparator)
		throws NoSuchInstanceTokenException, SystemException {
		KaleoInstanceToken kaleoInstanceToken = findByPrimaryKey(kaleoInstanceTokenId);

		Session session = null;

		try {
			session = openSession();

			KaleoInstanceToken[] array = new KaleoInstanceTokenImpl[3];

			array[0] = getByC_PKITI_PrevAndNext(session, kaleoInstanceToken,
					companyId, parentKaleoInstanceTokenId, orderByComparator,
					true);

			array[1] = kaleoInstanceToken;

			array[2] = getByC_PKITI_PrevAndNext(session, kaleoInstanceToken,
					companyId, parentKaleoInstanceTokenId, orderByComparator,
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

	protected KaleoInstanceToken getByC_PKITI_PrevAndNext(Session session,
		KaleoInstanceToken kaleoInstanceToken, long companyId,
		long parentKaleoInstanceTokenId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOINSTANCETOKEN_WHERE);

		query.append(_FINDER_COLUMN_C_PKITI_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_PKITI_PARENTKALEOINSTANCETOKENID_2);

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
			query.append(KaleoInstanceTokenModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(parentKaleoInstanceTokenId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoInstanceToken);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoInstanceToken> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	public List<KaleoInstanceToken> findByC_PKITI_CD(long companyId,
		long parentKaleoInstanceTokenId, Date completionDate)
		throws SystemException {
		return findByC_PKITI_CD(companyId, parentKaleoInstanceTokenId,
			completionDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<KaleoInstanceToken> findByC_PKITI_CD(long companyId,
		long parentKaleoInstanceTokenId, Date completionDate, int start, int end)
		throws SystemException {
		return findByC_PKITI_CD(companyId, parentKaleoInstanceTokenId,
			completionDate, start, end, null);
	}

	public List<KaleoInstanceToken> findByC_PKITI_CD(long companyId,
		long parentKaleoInstanceTokenId, Date completionDate, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId, parentKaleoInstanceTokenId, completionDate,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoInstanceToken> list = (List<KaleoInstanceToken>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_C_PKITI_CD,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;

				if (orderByComparator != null) {
					query = new StringBundler(5 +
							(orderByComparator.getOrderByFields().length * 3));
				}
				else {
					query = new StringBundler(5);
				}

				query.append(_SQL_SELECT_KALEOINSTANCETOKEN_WHERE);

				query.append(_FINDER_COLUMN_C_PKITI_CD_COMPANYID_2);

				query.append(_FINDER_COLUMN_C_PKITI_CD_PARENTKALEOINSTANCETOKENID_2);

				if (completionDate == null) {
					query.append(_FINDER_COLUMN_C_PKITI_CD_COMPLETIONDATE_1);
				}
				else {
					query.append(_FINDER_COLUMN_C_PKITI_CD_COMPLETIONDATE_2);
				}

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				else {
					query.append(KaleoInstanceTokenModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(parentKaleoInstanceTokenId);

				if (completionDate != null) {
					qPos.add(CalendarUtil.getTimestamp(completionDate));
				}

				list = (List<KaleoInstanceToken>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<KaleoInstanceToken>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_C_PKITI_CD,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public KaleoInstanceToken findByC_PKITI_CD_First(long companyId,
		long parentKaleoInstanceTokenId, Date completionDate,
		OrderByComparator orderByComparator)
		throws NoSuchInstanceTokenException, SystemException {
		List<KaleoInstanceToken> list = findByC_PKITI_CD(companyId,
				parentKaleoInstanceTokenId, completionDate, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", parentKaleoInstanceTokenId=");
			msg.append(parentKaleoInstanceTokenId);

			msg.append(", completionDate=");
			msg.append(completionDate);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchInstanceTokenException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoInstanceToken findByC_PKITI_CD_Last(long companyId,
		long parentKaleoInstanceTokenId, Date completionDate,
		OrderByComparator orderByComparator)
		throws NoSuchInstanceTokenException, SystemException {
		int count = countByC_PKITI_CD(companyId, parentKaleoInstanceTokenId,
				completionDate);

		List<KaleoInstanceToken> list = findByC_PKITI_CD(companyId,
				parentKaleoInstanceTokenId, completionDate, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", parentKaleoInstanceTokenId=");
			msg.append(parentKaleoInstanceTokenId);

			msg.append(", completionDate=");
			msg.append(completionDate);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchInstanceTokenException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoInstanceToken[] findByC_PKITI_CD_PrevAndNext(
		long kaleoInstanceTokenId, long companyId,
		long parentKaleoInstanceTokenId, Date completionDate,
		OrderByComparator orderByComparator)
		throws NoSuchInstanceTokenException, SystemException {
		KaleoInstanceToken kaleoInstanceToken = findByPrimaryKey(kaleoInstanceTokenId);

		Session session = null;

		try {
			session = openSession();

			KaleoInstanceToken[] array = new KaleoInstanceTokenImpl[3];

			array[0] = getByC_PKITI_CD_PrevAndNext(session, kaleoInstanceToken,
					companyId, parentKaleoInstanceTokenId, completionDate,
					orderByComparator, true);

			array[1] = kaleoInstanceToken;

			array[2] = getByC_PKITI_CD_PrevAndNext(session, kaleoInstanceToken,
					companyId, parentKaleoInstanceTokenId, completionDate,
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

	protected KaleoInstanceToken getByC_PKITI_CD_PrevAndNext(Session session,
		KaleoInstanceToken kaleoInstanceToken, long companyId,
		long parentKaleoInstanceTokenId, Date completionDate,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOINSTANCETOKEN_WHERE);

		query.append(_FINDER_COLUMN_C_PKITI_CD_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_PKITI_CD_PARENTKALEOINSTANCETOKENID_2);

		if (completionDate == null) {
			query.append(_FINDER_COLUMN_C_PKITI_CD_COMPLETIONDATE_1);
		}
		else {
			query.append(_FINDER_COLUMN_C_PKITI_CD_COMPLETIONDATE_2);
		}

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
			query.append(KaleoInstanceTokenModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(parentKaleoInstanceTokenId);

		if (completionDate != null) {
			qPos.add(CalendarUtil.getTimestamp(completionDate));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoInstanceToken);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoInstanceToken> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	public List<KaleoInstanceToken> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<KaleoInstanceToken> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<KaleoInstanceToken> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoInstanceToken> list = (List<KaleoInstanceToken>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_KALEOINSTANCETOKEN);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}
				else {
					sql = _SQL_SELECT_KALEOINSTANCETOKEN.concat(KaleoInstanceTokenModelImpl.ORDER_BY_JPQL);
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<KaleoInstanceToken>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<KaleoInstanceToken>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<KaleoInstanceToken>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByCompanyId(long companyId) throws SystemException {
		for (KaleoInstanceToken kaleoInstanceToken : findByCompanyId(companyId)) {
			remove(kaleoInstanceToken);
		}
	}

	public void removeByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		for (KaleoInstanceToken kaleoInstanceToken : findByKaleoDefinitionId(
				kaleoDefinitionId)) {
			remove(kaleoInstanceToken);
		}
	}

	public void removeByKaleoInstanceId(long kaleoInstanceId)
		throws SystemException {
		for (KaleoInstanceToken kaleoInstanceToken : findByKaleoInstanceId(
				kaleoInstanceId)) {
			remove(kaleoInstanceToken);
		}
	}

	public void removeByC_PKITI(long companyId, long parentKaleoInstanceTokenId)
		throws SystemException {
		for (KaleoInstanceToken kaleoInstanceToken : findByC_PKITI(companyId,
				parentKaleoInstanceTokenId)) {
			remove(kaleoInstanceToken);
		}
	}

	public void removeByC_PKITI_CD(long companyId,
		long parentKaleoInstanceTokenId, Date completionDate)
		throws SystemException {
		for (KaleoInstanceToken kaleoInstanceToken : findByC_PKITI_CD(
				companyId, parentKaleoInstanceTokenId, completionDate)) {
			remove(kaleoInstanceToken);
		}
	}

	public void removeAll() throws SystemException {
		for (KaleoInstanceToken kaleoInstanceToken : findAll()) {
			remove(kaleoInstanceToken);
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

				query.append(_SQL_COUNT_KALEOINSTANCETOKEN_WHERE);

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

				query.append(_SQL_COUNT_KALEOINSTANCETOKEN_WHERE);

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
		Object[] finderArgs = new Object[] { kaleoInstanceId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KALEOINSTANCEID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_KALEOINSTANCETOKEN_WHERE);

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

	public int countByC_PKITI(long companyId, long parentKaleoInstanceTokenId)
		throws SystemException {
		Object[] finderArgs = new Object[] { companyId, parentKaleoInstanceTokenId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_PKITI,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(3);

				query.append(_SQL_COUNT_KALEOINSTANCETOKEN_WHERE);

				query.append(_FINDER_COLUMN_C_PKITI_COMPANYID_2);

				query.append(_FINDER_COLUMN_C_PKITI_PARENTKALEOINSTANCETOKENID_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(parentKaleoInstanceTokenId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_PKITI,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByC_PKITI_CD(long companyId,
		long parentKaleoInstanceTokenId, Date completionDate)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId, parentKaleoInstanceTokenId, completionDate
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_PKITI_CD,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(4);

				query.append(_SQL_COUNT_KALEOINSTANCETOKEN_WHERE);

				query.append(_FINDER_COLUMN_C_PKITI_CD_COMPANYID_2);

				query.append(_FINDER_COLUMN_C_PKITI_CD_PARENTKALEOINSTANCETOKENID_2);

				if (completionDate == null) {
					query.append(_FINDER_COLUMN_C_PKITI_CD_COMPLETIONDATE_1);
				}
				else {
					query.append(_FINDER_COLUMN_C_PKITI_CD_COMPLETIONDATE_2);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(parentKaleoInstanceTokenId);

				if (completionDate != null) {
					qPos.add(CalendarUtil.getTimestamp(completionDate));
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_PKITI_CD,
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

				Query q = session.createQuery(_SQL_COUNT_KALEOINSTANCETOKEN);

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
						"value.object.listener.com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<KaleoInstanceToken>> listenersList = new ArrayList<ModelListener<KaleoInstanceToken>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<KaleoInstanceToken>)InstanceFactory.newInstance(
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
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_KALEOINSTANCETOKEN = "SELECT kaleoInstanceToken FROM KaleoInstanceToken kaleoInstanceToken";
	private static final String _SQL_SELECT_KALEOINSTANCETOKEN_WHERE = "SELECT kaleoInstanceToken FROM KaleoInstanceToken kaleoInstanceToken WHERE ";
	private static final String _SQL_COUNT_KALEOINSTANCETOKEN = "SELECT COUNT(kaleoInstanceToken) FROM KaleoInstanceToken kaleoInstanceToken";
	private static final String _SQL_COUNT_KALEOINSTANCETOKEN_WHERE = "SELECT COUNT(kaleoInstanceToken) FROM KaleoInstanceToken kaleoInstanceToken WHERE ";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "kaleoInstanceToken.companyId = ?";
	private static final String _FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2 =
		"kaleoInstanceToken.kaleoDefinitionId = ?";
	private static final String _FINDER_COLUMN_KALEOINSTANCEID_KALEOINSTANCEID_2 =
		"kaleoInstanceToken.kaleoInstanceId = ?";
	private static final String _FINDER_COLUMN_C_PKITI_COMPANYID_2 = "kaleoInstanceToken.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_PKITI_PARENTKALEOINSTANCETOKENID_2 =
		"kaleoInstanceToken.parentKaleoInstanceTokenId = ?";
	private static final String _FINDER_COLUMN_C_PKITI_CD_COMPANYID_2 = "kaleoInstanceToken.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_PKITI_CD_PARENTKALEOINSTANCETOKENID_2 =
		"kaleoInstanceToken.parentKaleoInstanceTokenId = ? AND ";
	private static final String _FINDER_COLUMN_C_PKITI_CD_COMPLETIONDATE_1 = "kaleoInstanceToken.completionDate IS NULL";
	private static final String _FINDER_COLUMN_C_PKITI_CD_COMPLETIONDATE_2 = "kaleoInstanceToken.completionDate = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoInstanceToken.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoInstanceToken exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoInstanceToken exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(KaleoInstanceTokenPersistenceImpl.class);
}