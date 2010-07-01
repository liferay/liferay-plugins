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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.workflow.kaleo.NoSuchTransitionException;
import com.liferay.portal.workflow.kaleo.model.KaleoTransition;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTransitionImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTransitionModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="KaleoTransitionPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTransitionPersistence
 * @see       KaleoTransitionUtil
 * @generated
 */
public class KaleoTransitionPersistenceImpl extends BasePersistenceImpl<KaleoTransition>
	implements KaleoTransitionPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoTransitionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_COMPANYID = new FinderPath(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_KALEODEFINITIONID = new FinderPath(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByKaleoDefinitionId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEODEFINITIONID = new FinderPath(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByKaleoDefinitionId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_KALEONODEID = new FinderPath(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByKaleoNodeId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEONODEID = new FinderPath(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByKaleoNodeId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_KNI_N = new FinderPath(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_ENTITY, "fetchByKNI_N",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_KNI_N = new FinderPath(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByKNI_N",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_KNI_DT = new FinderPath(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_ENTITY, "fetchByKNI_DT",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_KNI_DT = new FinderPath(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByKNI_DT",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	public void cacheResult(KaleoTransition kaleoTransition) {
		EntityCacheUtil.putResult(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionImpl.class, kaleoTransition.getPrimaryKey(),
			kaleoTransition);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KNI_N,
			new Object[] {
				new Long(kaleoTransition.getKaleoNodeId()),
				
			kaleoTransition.getName()
			}, kaleoTransition);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KNI_DT,
			new Object[] {
				new Long(kaleoTransition.getKaleoNodeId()),
				Boolean.valueOf(kaleoTransition.getDefaultTransition())
			}, kaleoTransition);
	}

	public void cacheResult(List<KaleoTransition> kaleoTransitions) {
		for (KaleoTransition kaleoTransition : kaleoTransitions) {
			if (EntityCacheUtil.getResult(
						KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
						KaleoTransitionImpl.class,
						kaleoTransition.getPrimaryKey(), this) == null) {
				cacheResult(kaleoTransition);
			}
		}
	}

	public void clearCache() {
		CacheRegistryUtil.clear(KaleoTransitionImpl.class.getName());
		EntityCacheUtil.clearCache(KaleoTransitionImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public void clearCache(KaleoTransition kaleoTransition) {
		EntityCacheUtil.removeResult(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionImpl.class, kaleoTransition.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KNI_N,
			new Object[] {
				new Long(kaleoTransition.getKaleoNodeId()),
				
			kaleoTransition.getName()
			});

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KNI_DT,
			new Object[] {
				new Long(kaleoTransition.getKaleoNodeId()),
				Boolean.valueOf(kaleoTransition.getDefaultTransition())
			});
	}

	public KaleoTransition create(long kaleoTransitionId) {
		KaleoTransition kaleoTransition = new KaleoTransitionImpl();

		kaleoTransition.setNew(true);
		kaleoTransition.setPrimaryKey(kaleoTransitionId);

		return kaleoTransition;
	}

	public KaleoTransition remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public KaleoTransition remove(long kaleoTransitionId)
		throws NoSuchTransitionException, SystemException {
		Session session = null;

		try {
			session = openSession();

			KaleoTransition kaleoTransition = (KaleoTransition)session.get(KaleoTransitionImpl.class,
					new Long(kaleoTransitionId));

			if (kaleoTransition == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						kaleoTransitionId);
				}

				throw new NoSuchTransitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					kaleoTransitionId);
			}

			return remove(kaleoTransition);
		}
		catch (NoSuchTransitionException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoTransition removeImpl(KaleoTransition kaleoTransition)
		throws SystemException {
		kaleoTransition = toUnwrappedModel(kaleoTransition);

		Session session = null;

		try {
			session = openSession();

			if (kaleoTransition.isCachedModel() ||
					BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(KaleoTransitionImpl.class,
						kaleoTransition.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(kaleoTransition);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		KaleoTransitionModelImpl kaleoTransitionModelImpl = (KaleoTransitionModelImpl)kaleoTransition;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KNI_N,
			new Object[] {
				new Long(kaleoTransitionModelImpl.getOriginalKaleoNodeId()),
				
			kaleoTransitionModelImpl.getOriginalName()
			});

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KNI_DT,
			new Object[] {
				new Long(kaleoTransitionModelImpl.getOriginalKaleoNodeId()),
				Boolean.valueOf(
					kaleoTransitionModelImpl.getOriginalDefaultTransition())
			});

		EntityCacheUtil.removeResult(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionImpl.class, kaleoTransition.getPrimaryKey());

		return kaleoTransition;
	}

	public KaleoTransition updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoTransition kaleoTransition,
		boolean merge) throws SystemException {
		kaleoTransition = toUnwrappedModel(kaleoTransition);

		boolean isNew = kaleoTransition.isNew();

		KaleoTransitionModelImpl kaleoTransitionModelImpl = (KaleoTransitionModelImpl)kaleoTransition;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, kaleoTransition, merge);

			kaleoTransition.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionImpl.class, kaleoTransition.getPrimaryKey(),
			kaleoTransition);

		if (!isNew &&
				((kaleoTransition.getKaleoNodeId() != kaleoTransitionModelImpl.getOriginalKaleoNodeId()) ||
				!Validator.equals(kaleoTransition.getName(),
					kaleoTransitionModelImpl.getOriginalName()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KNI_N,
				new Object[] {
					new Long(kaleoTransitionModelImpl.getOriginalKaleoNodeId()),
					
				kaleoTransitionModelImpl.getOriginalName()
				});
		}

		if (isNew ||
				((kaleoTransition.getKaleoNodeId() != kaleoTransitionModelImpl.getOriginalKaleoNodeId()) ||
				!Validator.equals(kaleoTransition.getName(),
					kaleoTransitionModelImpl.getOriginalName()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KNI_N,
				new Object[] {
					new Long(kaleoTransition.getKaleoNodeId()),
					
				kaleoTransition.getName()
				}, kaleoTransition);
		}

		if (!isNew &&
				((kaleoTransition.getKaleoNodeId() != kaleoTransitionModelImpl.getOriginalKaleoNodeId()) ||
				(kaleoTransition.getDefaultTransition() != kaleoTransitionModelImpl.getOriginalDefaultTransition()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KNI_DT,
				new Object[] {
					new Long(kaleoTransitionModelImpl.getOriginalKaleoNodeId()),
					Boolean.valueOf(
						kaleoTransitionModelImpl.getOriginalDefaultTransition())
				});
		}

		if (isNew ||
				((kaleoTransition.getKaleoNodeId() != kaleoTransitionModelImpl.getOriginalKaleoNodeId()) ||
				(kaleoTransition.getDefaultTransition() != kaleoTransitionModelImpl.getOriginalDefaultTransition()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KNI_DT,
				new Object[] {
					new Long(kaleoTransition.getKaleoNodeId()),
					Boolean.valueOf(kaleoTransition.getDefaultTransition())
				}, kaleoTransition);
		}

		return kaleoTransition;
	}

	protected KaleoTransition toUnwrappedModel(KaleoTransition kaleoTransition) {
		if (kaleoTransition instanceof KaleoTransitionImpl) {
			return kaleoTransition;
		}

		KaleoTransitionImpl kaleoTransitionImpl = new KaleoTransitionImpl();

		kaleoTransitionImpl.setNew(kaleoTransition.isNew());
		kaleoTransitionImpl.setPrimaryKey(kaleoTransition.getPrimaryKey());

		kaleoTransitionImpl.setKaleoTransitionId(kaleoTransition.getKaleoTransitionId());
		kaleoTransitionImpl.setGroupId(kaleoTransition.getGroupId());
		kaleoTransitionImpl.setCompanyId(kaleoTransition.getCompanyId());
		kaleoTransitionImpl.setUserId(kaleoTransition.getUserId());
		kaleoTransitionImpl.setUserName(kaleoTransition.getUserName());
		kaleoTransitionImpl.setCreateDate(kaleoTransition.getCreateDate());
		kaleoTransitionImpl.setModifiedDate(kaleoTransition.getModifiedDate());
		kaleoTransitionImpl.setKaleoDefinitionId(kaleoTransition.getKaleoDefinitionId());
		kaleoTransitionImpl.setKaleoNodeId(kaleoTransition.getKaleoNodeId());
		kaleoTransitionImpl.setName(kaleoTransition.getName());
		kaleoTransitionImpl.setDescription(kaleoTransition.getDescription());
		kaleoTransitionImpl.setSourceKaleoNodeId(kaleoTransition.getSourceKaleoNodeId());
		kaleoTransitionImpl.setSourceKaleoNodeName(kaleoTransition.getSourceKaleoNodeName());
		kaleoTransitionImpl.setTargetKaleoNodeId(kaleoTransition.getTargetKaleoNodeId());
		kaleoTransitionImpl.setTargetKaleoNodeName(kaleoTransition.getTargetKaleoNodeName());
		kaleoTransitionImpl.setDefaultTransition(kaleoTransition.isDefaultTransition());

		return kaleoTransitionImpl;
	}

	public KaleoTransition findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	public KaleoTransition findByPrimaryKey(long kaleoTransitionId)
		throws NoSuchTransitionException, SystemException {
		KaleoTransition kaleoTransition = fetchByPrimaryKey(kaleoTransitionId);

		if (kaleoTransition == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + kaleoTransitionId);
			}

			throw new NoSuchTransitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				kaleoTransitionId);
		}

		return kaleoTransition;
	}

	public KaleoTransition fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public KaleoTransition fetchByPrimaryKey(long kaleoTransitionId)
		throws SystemException {
		KaleoTransition kaleoTransition = (KaleoTransition)EntityCacheUtil.getResult(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
				KaleoTransitionImpl.class, kaleoTransitionId, this);

		if (kaleoTransition == null) {
			Session session = null;

			try {
				session = openSession();

				kaleoTransition = (KaleoTransition)session.get(KaleoTransitionImpl.class,
						new Long(kaleoTransitionId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (kaleoTransition != null) {
					cacheResult(kaleoTransition);
				}

				closeSession(session);
			}
		}

		return kaleoTransition;
	}

	public List<KaleoTransition> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	public List<KaleoTransition> findByCompanyId(long companyId, int start,
		int end) throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	public List<KaleoTransition> findByCompanyId(long companyId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoTransition> list = (List<KaleoTransition>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_COMPANYID,
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

				query.append(_SQL_SELECT_KALEOTRANSITION_WHERE);

				query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				else {
					query.append(KaleoTransitionModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<KaleoTransition>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<KaleoTransition>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_COMPANYID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public KaleoTransition findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchTransitionException, SystemException {
		List<KaleoTransition> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTransitionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoTransition findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchTransitionException, SystemException {
		int count = countByCompanyId(companyId);

		List<KaleoTransition> list = findByCompanyId(companyId, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTransitionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoTransition[] findByCompanyId_PrevAndNext(
		long kaleoTransitionId, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchTransitionException, SystemException {
		KaleoTransition kaleoTransition = findByPrimaryKey(kaleoTransitionId);

		Session session = null;

		try {
			session = openSession();

			KaleoTransition[] array = new KaleoTransitionImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, kaleoTransition,
					companyId, orderByComparator, true);

			array[1] = kaleoTransition;

			array[2] = getByCompanyId_PrevAndNext(session, kaleoTransition,
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

	protected KaleoTransition getByCompanyId_PrevAndNext(Session session,
		KaleoTransition kaleoTransition, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTRANSITION_WHERE);

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
			query.append(KaleoTransitionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoTransition);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTransition> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	public List<KaleoTransition> findByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		return findByKaleoDefinitionId(kaleoDefinitionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	public List<KaleoTransition> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end) throws SystemException {
		return findByKaleoDefinitionId(kaleoDefinitionId, start, end, null);
	}

	public List<KaleoTransition> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				kaleoDefinitionId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoTransition> list = (List<KaleoTransition>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_KALEODEFINITIONID,
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

				query.append(_SQL_SELECT_KALEOTRANSITION_WHERE);

				query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				else {
					query.append(KaleoTransitionModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoDefinitionId);

				list = (List<KaleoTransition>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<KaleoTransition>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_KALEODEFINITIONID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public KaleoTransition findByKaleoDefinitionId_First(
		long kaleoDefinitionId, OrderByComparator orderByComparator)
		throws NoSuchTransitionException, SystemException {
		List<KaleoTransition> list = findByKaleoDefinitionId(kaleoDefinitionId,
				0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoDefinitionId=");
			msg.append(kaleoDefinitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTransitionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoTransition findByKaleoDefinitionId_Last(
		long kaleoDefinitionId, OrderByComparator orderByComparator)
		throws NoSuchTransitionException, SystemException {
		int count = countByKaleoDefinitionId(kaleoDefinitionId);

		List<KaleoTransition> list = findByKaleoDefinitionId(kaleoDefinitionId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoDefinitionId=");
			msg.append(kaleoDefinitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTransitionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoTransition[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoTransitionId, long kaleoDefinitionId,
		OrderByComparator orderByComparator)
		throws NoSuchTransitionException, SystemException {
		KaleoTransition kaleoTransition = findByPrimaryKey(kaleoTransitionId);

		Session session = null;

		try {
			session = openSession();

			KaleoTransition[] array = new KaleoTransitionImpl[3];

			array[0] = getByKaleoDefinitionId_PrevAndNext(session,
					kaleoTransition, kaleoDefinitionId, orderByComparator, true);

			array[1] = kaleoTransition;

			array[2] = getByKaleoDefinitionId_PrevAndNext(session,
					kaleoTransition, kaleoDefinitionId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoTransition getByKaleoDefinitionId_PrevAndNext(
		Session session, KaleoTransition kaleoTransition,
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

		query.append(_SQL_SELECT_KALEOTRANSITION_WHERE);

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
			query.append(KaleoTransitionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoDefinitionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoTransition);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTransition> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	public List<KaleoTransition> findByKaleoNodeId(long kaleoNodeId)
		throws SystemException {
		return findByKaleoNodeId(kaleoNodeId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	public List<KaleoTransition> findByKaleoNodeId(long kaleoNodeId, int start,
		int end) throws SystemException {
		return findByKaleoNodeId(kaleoNodeId, start, end, null);
	}

	public List<KaleoTransition> findByKaleoNodeId(long kaleoNodeId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				kaleoNodeId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoTransition> list = (List<KaleoTransition>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_KALEONODEID,
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

				query.append(_SQL_SELECT_KALEOTRANSITION_WHERE);

				query.append(_FINDER_COLUMN_KALEONODEID_KALEONODEID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				else {
					query.append(KaleoTransitionModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoNodeId);

				list = (List<KaleoTransition>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<KaleoTransition>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_KALEONODEID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public KaleoTransition findByKaleoNodeId_First(long kaleoNodeId,
		OrderByComparator orderByComparator)
		throws NoSuchTransitionException, SystemException {
		List<KaleoTransition> list = findByKaleoNodeId(kaleoNodeId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoNodeId=");
			msg.append(kaleoNodeId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTransitionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoTransition findByKaleoNodeId_Last(long kaleoNodeId,
		OrderByComparator orderByComparator)
		throws NoSuchTransitionException, SystemException {
		int count = countByKaleoNodeId(kaleoNodeId);

		List<KaleoTransition> list = findByKaleoNodeId(kaleoNodeId, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoNodeId=");
			msg.append(kaleoNodeId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTransitionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoTransition[] findByKaleoNodeId_PrevAndNext(
		long kaleoTransitionId, long kaleoNodeId,
		OrderByComparator orderByComparator)
		throws NoSuchTransitionException, SystemException {
		KaleoTransition kaleoTransition = findByPrimaryKey(kaleoTransitionId);

		Session session = null;

		try {
			session = openSession();

			KaleoTransition[] array = new KaleoTransitionImpl[3];

			array[0] = getByKaleoNodeId_PrevAndNext(session, kaleoTransition,
					kaleoNodeId, orderByComparator, true);

			array[1] = kaleoTransition;

			array[2] = getByKaleoNodeId_PrevAndNext(session, kaleoTransition,
					kaleoNodeId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoTransition getByKaleoNodeId_PrevAndNext(Session session,
		KaleoTransition kaleoTransition, long kaleoNodeId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTRANSITION_WHERE);

		query.append(_FINDER_COLUMN_KALEONODEID_KALEONODEID_2);

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
			query.append(KaleoTransitionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoNodeId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoTransition);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTransition> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	public KaleoTransition findByKNI_N(long kaleoNodeId, String name)
		throws NoSuchTransitionException, SystemException {
		KaleoTransition kaleoTransition = fetchByKNI_N(kaleoNodeId, name);

		if (kaleoTransition == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoNodeId=");
			msg.append(kaleoNodeId);

			msg.append(", name=");
			msg.append(name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchTransitionException(msg.toString());
		}

		return kaleoTransition;
	}

	public KaleoTransition fetchByKNI_N(long kaleoNodeId, String name)
		throws SystemException {
		return fetchByKNI_N(kaleoNodeId, name, true);
	}

	public KaleoTransition fetchByKNI_N(long kaleoNodeId, String name,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { kaleoNodeId, name };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_KNI_N,
					finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(4);

				query.append(_SQL_SELECT_KALEOTRANSITION_WHERE);

				query.append(_FINDER_COLUMN_KNI_N_KALEONODEID_2);

				if (name == null) {
					query.append(_FINDER_COLUMN_KNI_N_NAME_1);
				}
				else {
					if (name.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_KNI_N_NAME_3);
					}
					else {
						query.append(_FINDER_COLUMN_KNI_N_NAME_2);
					}
				}

				query.append(KaleoTransitionModelImpl.ORDER_BY_JPQL);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoNodeId);

				if (name != null) {
					qPos.add(name);
				}

				List<KaleoTransition> list = q.list();

				result = list;

				KaleoTransition kaleoTransition = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KNI_N,
						finderArgs, list);
				}
				else {
					kaleoTransition = list.get(0);

					cacheResult(kaleoTransition);

					if ((kaleoTransition.getKaleoNodeId() != kaleoNodeId) ||
							(kaleoTransition.getName() == null) ||
							!kaleoTransition.getName().equals(name)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KNI_N,
							finderArgs, kaleoTransition);
					}
				}

				return kaleoTransition;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KNI_N,
						finderArgs, new ArrayList<KaleoTransition>());
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (KaleoTransition)result;
			}
		}
	}

	public KaleoTransition findByKNI_DT(long kaleoNodeId,
		boolean defaultTransition)
		throws NoSuchTransitionException, SystemException {
		KaleoTransition kaleoTransition = fetchByKNI_DT(kaleoNodeId,
				defaultTransition);

		if (kaleoTransition == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoNodeId=");
			msg.append(kaleoNodeId);

			msg.append(", defaultTransition=");
			msg.append(defaultTransition);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchTransitionException(msg.toString());
		}

		return kaleoTransition;
	}

	public KaleoTransition fetchByKNI_DT(long kaleoNodeId,
		boolean defaultTransition) throws SystemException {
		return fetchByKNI_DT(kaleoNodeId, defaultTransition, true);
	}

	public KaleoTransition fetchByKNI_DT(long kaleoNodeId,
		boolean defaultTransition, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { kaleoNodeId, defaultTransition };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_KNI_DT,
					finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(4);

				query.append(_SQL_SELECT_KALEOTRANSITION_WHERE);

				query.append(_FINDER_COLUMN_KNI_DT_KALEONODEID_2);

				query.append(_FINDER_COLUMN_KNI_DT_DEFAULTTRANSITION_2);

				query.append(KaleoTransitionModelImpl.ORDER_BY_JPQL);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoNodeId);

				qPos.add(defaultTransition);

				List<KaleoTransition> list = q.list();

				result = list;

				KaleoTransition kaleoTransition = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KNI_DT,
						finderArgs, list);
				}
				else {
					kaleoTransition = list.get(0);

					cacheResult(kaleoTransition);

					if ((kaleoTransition.getKaleoNodeId() != kaleoNodeId) ||
							(kaleoTransition.getDefaultTransition() != defaultTransition)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KNI_DT,
							finderArgs, kaleoTransition);
					}
				}

				return kaleoTransition;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KNI_DT,
						finderArgs, new ArrayList<KaleoTransition>());
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (KaleoTransition)result;
			}
		}
	}

	public List<KaleoTransition> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<KaleoTransition> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<KaleoTransition> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoTransition> list = (List<KaleoTransition>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_KALEOTRANSITION);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}
				else {
					sql = _SQL_SELECT_KALEOTRANSITION.concat(KaleoTransitionModelImpl.ORDER_BY_JPQL);
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<KaleoTransition>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<KaleoTransition>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<KaleoTransition>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByCompanyId(long companyId) throws SystemException {
		for (KaleoTransition kaleoTransition : findByCompanyId(companyId)) {
			remove(kaleoTransition);
		}
	}

	public void removeByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		for (KaleoTransition kaleoTransition : findByKaleoDefinitionId(
				kaleoDefinitionId)) {
			remove(kaleoTransition);
		}
	}

	public void removeByKaleoNodeId(long kaleoNodeId) throws SystemException {
		for (KaleoTransition kaleoTransition : findByKaleoNodeId(kaleoNodeId)) {
			remove(kaleoTransition);
		}
	}

	public void removeByKNI_N(long kaleoNodeId, String name)
		throws NoSuchTransitionException, SystemException {
		KaleoTransition kaleoTransition = findByKNI_N(kaleoNodeId, name);

		remove(kaleoTransition);
	}

	public void removeByKNI_DT(long kaleoNodeId, boolean defaultTransition)
		throws NoSuchTransitionException, SystemException {
		KaleoTransition kaleoTransition = findByKNI_DT(kaleoNodeId,
				defaultTransition);

		remove(kaleoTransition);
	}

	public void removeAll() throws SystemException {
		for (KaleoTransition kaleoTransition : findAll()) {
			remove(kaleoTransition);
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

				query.append(_SQL_COUNT_KALEOTRANSITION_WHERE);

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

				query.append(_SQL_COUNT_KALEOTRANSITION_WHERE);

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

				query.append(_SQL_COUNT_KALEOTRANSITION_WHERE);

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

	public int countByKNI_N(long kaleoNodeId, String name)
		throws SystemException {
		Object[] finderArgs = new Object[] { kaleoNodeId, name };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KNI_N,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(3);

				query.append(_SQL_COUNT_KALEOTRANSITION_WHERE);

				query.append(_FINDER_COLUMN_KNI_N_KALEONODEID_2);

				if (name == null) {
					query.append(_FINDER_COLUMN_KNI_N_NAME_1);
				}
				else {
					if (name.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_KNI_N_NAME_3);
					}
					else {
						query.append(_FINDER_COLUMN_KNI_N_NAME_2);
					}
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoNodeId);

				if (name != null) {
					qPos.add(name);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KNI_N,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByKNI_DT(long kaleoNodeId, boolean defaultTransition)
		throws SystemException {
		Object[] finderArgs = new Object[] { kaleoNodeId, defaultTransition };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KNI_DT,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(3);

				query.append(_SQL_COUNT_KALEOTRANSITION_WHERE);

				query.append(_FINDER_COLUMN_KNI_DT_KALEONODEID_2);

				query.append(_FINDER_COLUMN_KNI_DT_DEFAULTTRANSITION_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoNodeId);

				qPos.add(defaultTransition);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KNI_DT,
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

				Query q = session.createQuery(_SQL_COUNT_KALEOTRANSITION);

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
						"value.object.listener.com.liferay.portal.workflow.kaleo.model.KaleoTransition")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<KaleoTransition>> listenersList = new ArrayList<ModelListener<KaleoTransition>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<KaleoTransition>)InstanceFactory.newInstance(
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
	private static final String _SQL_SELECT_KALEOTRANSITION = "SELECT kaleoTransition FROM KaleoTransition kaleoTransition";
	private static final String _SQL_SELECT_KALEOTRANSITION_WHERE = "SELECT kaleoTransition FROM KaleoTransition kaleoTransition WHERE ";
	private static final String _SQL_COUNT_KALEOTRANSITION = "SELECT COUNT(kaleoTransition) FROM KaleoTransition kaleoTransition";
	private static final String _SQL_COUNT_KALEOTRANSITION_WHERE = "SELECT COUNT(kaleoTransition) FROM KaleoTransition kaleoTransition WHERE ";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "kaleoTransition.companyId = ?";
	private static final String _FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2 =
		"kaleoTransition.kaleoDefinitionId = ?";
	private static final String _FINDER_COLUMN_KALEONODEID_KALEONODEID_2 = "kaleoTransition.kaleoNodeId = ?";
	private static final String _FINDER_COLUMN_KNI_N_KALEONODEID_2 = "kaleoTransition.kaleoNodeId = ? AND ";
	private static final String _FINDER_COLUMN_KNI_N_NAME_1 = "kaleoTransition.name IS NULL";
	private static final String _FINDER_COLUMN_KNI_N_NAME_2 = "kaleoTransition.name = ?";
	private static final String _FINDER_COLUMN_KNI_N_NAME_3 = "(kaleoTransition.name IS NULL OR kaleoTransition.name = ?)";
	private static final String _FINDER_COLUMN_KNI_DT_KALEONODEID_2 = "kaleoTransition.kaleoNodeId = ? AND ";
	private static final String _FINDER_COLUMN_KNI_DT_DEFAULTTRANSITION_2 = "kaleoTransition.defaultTransition = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoTransition.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoTransition exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoTransition exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(KaleoTransitionPersistenceImpl.class);
}