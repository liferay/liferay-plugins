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
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.workflow.kaleo.NoSuchInstanceException;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * <a href="KaleoInstancePersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoInstancePersistence
 * @see       KaleoInstanceUtil
 * @generated
 */
public class KaleoInstancePersistenceImpl extends BasePersistenceImpl<KaleoInstance>
	implements KaleoInstancePersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoInstanceImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_C_KDN_KDV_CD = new FinderPath(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByC_KDN_KDV_CD",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Date.class.getName()
			});
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_C_KDN_KDV_CD = new FinderPath(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByC_KDN_KDV_CD",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Date.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_C_KDN_KDV_CD = new FinderPath(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByC_KDN_KDV_CD",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Date.class.getName()
			});
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	public void cacheResult(KaleoInstance kaleoInstance) {
		EntityCacheUtil.putResult(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceImpl.class, kaleoInstance.getPrimaryKey(),
			kaleoInstance);
	}

	public void cacheResult(List<KaleoInstance> kaleoInstances) {
		for (KaleoInstance kaleoInstance : kaleoInstances) {
			if (EntityCacheUtil.getResult(
						KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
						KaleoInstanceImpl.class, kaleoInstance.getPrimaryKey(),
						this) == null) {
				cacheResult(kaleoInstance);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(KaleoInstanceImpl.class.getName());
		EntityCacheUtil.clearCache(KaleoInstanceImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public KaleoInstance create(long kaleoInstanceId) {
		KaleoInstance kaleoInstance = new KaleoInstanceImpl();

		kaleoInstance.setNew(true);
		kaleoInstance.setPrimaryKey(kaleoInstanceId);

		return kaleoInstance;
	}

	public KaleoInstance remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public KaleoInstance remove(long kaleoInstanceId)
		throws NoSuchInstanceException, SystemException {
		Session session = null;

		try {
			session = openSession();

			KaleoInstance kaleoInstance = (KaleoInstance)session.get(KaleoInstanceImpl.class,
					new Long(kaleoInstanceId));

			if (kaleoInstance == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						kaleoInstanceId);
				}

				throw new NoSuchInstanceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					kaleoInstanceId);
			}

			return remove(kaleoInstance);
		}
		catch (NoSuchInstanceException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public KaleoInstance remove(KaleoInstance kaleoInstance)
		throws SystemException {
		for (ModelListener<KaleoInstance> listener : listeners) {
			listener.onBeforeRemove(kaleoInstance);
		}

		kaleoInstance = removeImpl(kaleoInstance);

		for (ModelListener<KaleoInstance> listener : listeners) {
			listener.onAfterRemove(kaleoInstance);
		}

		return kaleoInstance;
	}

	protected KaleoInstance removeImpl(KaleoInstance kaleoInstance)
		throws SystemException {
		kaleoInstance = toUnwrappedModel(kaleoInstance);

		Session session = null;

		try {
			session = openSession();

			if (kaleoInstance.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(KaleoInstanceImpl.class,
						kaleoInstance.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(kaleoInstance);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceImpl.class, kaleoInstance.getPrimaryKey());

		return kaleoInstance;
	}

	public KaleoInstance updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoInstance kaleoInstance,
		boolean merge) throws SystemException {
		kaleoInstance = toUnwrappedModel(kaleoInstance);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, kaleoInstance, merge);

			kaleoInstance.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceImpl.class, kaleoInstance.getPrimaryKey(),
			kaleoInstance);

		return kaleoInstance;
	}

	protected KaleoInstance toUnwrappedModel(KaleoInstance kaleoInstance) {
		if (kaleoInstance instanceof KaleoInstanceImpl) {
			return kaleoInstance;
		}

		KaleoInstanceImpl kaleoInstanceImpl = new KaleoInstanceImpl();

		kaleoInstanceImpl.setNew(kaleoInstance.isNew());
		kaleoInstanceImpl.setPrimaryKey(kaleoInstance.getPrimaryKey());

		kaleoInstanceImpl.setKaleoInstanceId(kaleoInstance.getKaleoInstanceId());
		kaleoInstanceImpl.setCompanyId(kaleoInstance.getCompanyId());
		kaleoInstanceImpl.setUserId(kaleoInstance.getUserId());
		kaleoInstanceImpl.setUserName(kaleoInstance.getUserName());
		kaleoInstanceImpl.setCreateDate(kaleoInstance.getCreateDate());
		kaleoInstanceImpl.setModifiedDate(kaleoInstance.getModifiedDate());
		kaleoInstanceImpl.setKaleoDefinitionId(kaleoInstance.getKaleoDefinitionId());
		kaleoInstanceImpl.setKaleoDefinitionName(kaleoInstance.getKaleoDefinitionName());
		kaleoInstanceImpl.setKaleoDefinitionVersion(kaleoInstance.getKaleoDefinitionVersion());
		kaleoInstanceImpl.setRootKaleoInstanceTokenId(kaleoInstance.getRootKaleoInstanceTokenId());
		kaleoInstanceImpl.setClassName(kaleoInstance.getClassName());
		kaleoInstanceImpl.setClassPK(kaleoInstance.getClassPK());
		kaleoInstanceImpl.setCompletionDate(kaleoInstance.getCompletionDate());
		kaleoInstanceImpl.setContext(kaleoInstance.getContext());

		return kaleoInstanceImpl;
	}

	public KaleoInstance findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	public KaleoInstance findByPrimaryKey(long kaleoInstanceId)
		throws NoSuchInstanceException, SystemException {
		KaleoInstance kaleoInstance = fetchByPrimaryKey(kaleoInstanceId);

		if (kaleoInstance == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + kaleoInstanceId);
			}

			throw new NoSuchInstanceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				kaleoInstanceId);
		}

		return kaleoInstance;
	}

	public KaleoInstance fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public KaleoInstance fetchByPrimaryKey(long kaleoInstanceId)
		throws SystemException {
		KaleoInstance kaleoInstance = (KaleoInstance)EntityCacheUtil.getResult(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
				KaleoInstanceImpl.class, kaleoInstanceId, this);

		if (kaleoInstance == null) {
			Session session = null;

			try {
				session = openSession();

				kaleoInstance = (KaleoInstance)session.get(KaleoInstanceImpl.class,
						new Long(kaleoInstanceId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (kaleoInstance != null) {
					cacheResult(kaleoInstance);
				}

				closeSession(session);
			}
		}

		return kaleoInstance;
	}

	public List<KaleoInstance> findByC_KDN_KDV_CD(long companyId,
		String kaleoDefinitionName, int kaleoDefinitionVersion,
		Date completionDate) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(companyId),
				
				kaleoDefinitionName, new Integer(kaleoDefinitionVersion),
				
				completionDate
			};

		List<KaleoInstance> list = (List<KaleoInstance>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_C_KDN_KDV_CD,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(6);

				query.append(_SQL_SELECT_KALEOINSTANCE_WHERE);

				query.append(_FINDER_COLUMN_C_KDN_KDV_CD_COMPANYID_2);

				if (kaleoDefinitionName == null) {
					query.append(_FINDER_COLUMN_C_KDN_KDV_CD_KALEODEFINITIONNAME_1);
				}
				else {
					if (kaleoDefinitionName.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_C_KDN_KDV_CD_KALEODEFINITIONNAME_3);
					}
					else {
						query.append(_FINDER_COLUMN_C_KDN_KDV_CD_KALEODEFINITIONNAME_2);
					}
				}

				query.append(_FINDER_COLUMN_C_KDN_KDV_CD_KALEODEFINITIONVERSION_2);

				if (completionDate == null) {
					query.append(_FINDER_COLUMN_C_KDN_KDV_CD_COMPLETIONDATE_1);
				}
				else {
					query.append(_FINDER_COLUMN_C_KDN_KDV_CD_COMPLETIONDATE_2);
				}

				query.append(KaleoInstanceModelImpl.ORDER_BY_JPQL);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (kaleoDefinitionName != null) {
					qPos.add(kaleoDefinitionName);
				}

				qPos.add(kaleoDefinitionVersion);

				if (completionDate != null) {
					qPos.add(CalendarUtil.getTimestamp(completionDate));
				}

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<KaleoInstance>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_C_KDN_KDV_CD,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<KaleoInstance> findByC_KDN_KDV_CD(long companyId,
		String kaleoDefinitionName, int kaleoDefinitionVersion,
		Date completionDate, int start, int end) throws SystemException {
		return findByC_KDN_KDV_CD(companyId, kaleoDefinitionName,
			kaleoDefinitionVersion, completionDate, start, end, null);
	}

	public List<KaleoInstance> findByC_KDN_KDV_CD(long companyId,
		String kaleoDefinitionName, int kaleoDefinitionVersion,
		Date completionDate, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(companyId),
				
				kaleoDefinitionName, new Integer(kaleoDefinitionVersion),
				
				completionDate,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoInstance> list = (List<KaleoInstance>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_C_KDN_KDV_CD,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;

				if (orderByComparator != null) {
					query = new StringBundler(6 +
							(orderByComparator.getOrderByFields().length * 3));
				}
				else {
					query = new StringBundler(6);
				}

				query.append(_SQL_SELECT_KALEOINSTANCE_WHERE);

				query.append(_FINDER_COLUMN_C_KDN_KDV_CD_COMPANYID_2);

				if (kaleoDefinitionName == null) {
					query.append(_FINDER_COLUMN_C_KDN_KDV_CD_KALEODEFINITIONNAME_1);
				}
				else {
					if (kaleoDefinitionName.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_C_KDN_KDV_CD_KALEODEFINITIONNAME_3);
					}
					else {
						query.append(_FINDER_COLUMN_C_KDN_KDV_CD_KALEODEFINITIONNAME_2);
					}
				}

				query.append(_FINDER_COLUMN_C_KDN_KDV_CD_KALEODEFINITIONVERSION_2);

				if (completionDate == null) {
					query.append(_FINDER_COLUMN_C_KDN_KDV_CD_COMPLETIONDATE_1);
				}
				else {
					query.append(_FINDER_COLUMN_C_KDN_KDV_CD_COMPLETIONDATE_2);
				}

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				else {
					query.append(KaleoInstanceModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (kaleoDefinitionName != null) {
					qPos.add(kaleoDefinitionName);
				}

				qPos.add(kaleoDefinitionVersion);

				if (completionDate != null) {
					qPos.add(CalendarUtil.getTimestamp(completionDate));
				}

				list = (List<KaleoInstance>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<KaleoInstance>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_C_KDN_KDV_CD,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public KaleoInstance findByC_KDN_KDV_CD_First(long companyId,
		String kaleoDefinitionName, int kaleoDefinitionVersion,
		Date completionDate, OrderByComparator orderByComparator)
		throws NoSuchInstanceException, SystemException {
		List<KaleoInstance> list = findByC_KDN_KDV_CD(companyId,
				kaleoDefinitionName, kaleoDefinitionVersion, completionDate, 0,
				1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", kaleoDefinitionName=");
			msg.append(kaleoDefinitionName);

			msg.append(", kaleoDefinitionVersion=");
			msg.append(kaleoDefinitionVersion);

			msg.append(", completionDate=");
			msg.append(completionDate);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchInstanceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoInstance findByC_KDN_KDV_CD_Last(long companyId,
		String kaleoDefinitionName, int kaleoDefinitionVersion,
		Date completionDate, OrderByComparator orderByComparator)
		throws NoSuchInstanceException, SystemException {
		int count = countByC_KDN_KDV_CD(companyId, kaleoDefinitionName,
				kaleoDefinitionVersion, completionDate);

		List<KaleoInstance> list = findByC_KDN_KDV_CD(companyId,
				kaleoDefinitionName, kaleoDefinitionVersion, completionDate,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", kaleoDefinitionName=");
			msg.append(kaleoDefinitionName);

			msg.append(", kaleoDefinitionVersion=");
			msg.append(kaleoDefinitionVersion);

			msg.append(", completionDate=");
			msg.append(completionDate);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchInstanceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoInstance[] findByC_KDN_KDV_CD_PrevAndNext(
		long kaleoInstanceId, long companyId, String kaleoDefinitionName,
		int kaleoDefinitionVersion, Date completionDate,
		OrderByComparator orderByComparator)
		throws NoSuchInstanceException, SystemException {
		KaleoInstance kaleoInstance = findByPrimaryKey(kaleoInstanceId);

		int count = countByC_KDN_KDV_CD(companyId, kaleoDefinitionName,
				kaleoDefinitionVersion, completionDate);

		Session session = null;

		try {
			session = openSession();

			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_KALEOINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_C_KDN_KDV_CD_COMPANYID_2);

			if (kaleoDefinitionName == null) {
				query.append(_FINDER_COLUMN_C_KDN_KDV_CD_KALEODEFINITIONNAME_1);
			}
			else {
				if (kaleoDefinitionName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_C_KDN_KDV_CD_KALEODEFINITIONNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_C_KDN_KDV_CD_KALEODEFINITIONNAME_2);
				}
			}

			query.append(_FINDER_COLUMN_C_KDN_KDV_CD_KALEODEFINITIONVERSION_2);

			if (completionDate == null) {
				query.append(_FINDER_COLUMN_C_KDN_KDV_CD_COMPLETIONDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_C_KDN_KDV_CD_COMPLETIONDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoInstanceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Query q = session.createQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			if (kaleoDefinitionName != null) {
				qPos.add(kaleoDefinitionName);
			}

			qPos.add(kaleoDefinitionVersion);

			if (completionDate != null) {
				qPos.add(CalendarUtil.getTimestamp(completionDate));
			}

			Object[] objArray = QueryUtil.getPrevAndNext(q, count,
					orderByComparator, kaleoInstance);

			KaleoInstance[] array = new KaleoInstanceImpl[3];

			array[0] = (KaleoInstance)objArray[0];
			array[1] = (KaleoInstance)objArray[1];
			array[2] = (KaleoInstance)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<KaleoInstance> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<KaleoInstance> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<KaleoInstance> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoInstance> list = (List<KaleoInstance>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_KALEOINSTANCE);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}

				else {
					sql = _SQL_SELECT_KALEOINSTANCE.concat(KaleoInstanceModelImpl.ORDER_BY_JPQL);
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<KaleoInstance>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<KaleoInstance>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<KaleoInstance>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByC_KDN_KDV_CD(long companyId,
		String kaleoDefinitionName, int kaleoDefinitionVersion,
		Date completionDate) throws SystemException {
		for (KaleoInstance kaleoInstance : findByC_KDN_KDV_CD(companyId,
				kaleoDefinitionName, kaleoDefinitionVersion, completionDate)) {
			remove(kaleoInstance);
		}
	}

	public void removeAll() throws SystemException {
		for (KaleoInstance kaleoInstance : findAll()) {
			remove(kaleoInstance);
		}
	}

	public int countByC_KDN_KDV_CD(long companyId, String kaleoDefinitionName,
		int kaleoDefinitionVersion, Date completionDate)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(companyId),
				
				kaleoDefinitionName, new Integer(kaleoDefinitionVersion),
				
				completionDate
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_KDN_KDV_CD,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(5);

				query.append(_SQL_COUNT_KALEOINSTANCE_WHERE);

				query.append(_FINDER_COLUMN_C_KDN_KDV_CD_COMPANYID_2);

				if (kaleoDefinitionName == null) {
					query.append(_FINDER_COLUMN_C_KDN_KDV_CD_KALEODEFINITIONNAME_1);
				}
				else {
					if (kaleoDefinitionName.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_C_KDN_KDV_CD_KALEODEFINITIONNAME_3);
					}
					else {
						query.append(_FINDER_COLUMN_C_KDN_KDV_CD_KALEODEFINITIONNAME_2);
					}
				}

				query.append(_FINDER_COLUMN_C_KDN_KDV_CD_KALEODEFINITIONVERSION_2);

				if (completionDate == null) {
					query.append(_FINDER_COLUMN_C_KDN_KDV_CD_COMPLETIONDATE_1);
				}
				else {
					query.append(_FINDER_COLUMN_C_KDN_KDV_CD_COMPLETIONDATE_2);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (kaleoDefinitionName != null) {
					qPos.add(kaleoDefinitionName);
				}

				qPos.add(kaleoDefinitionVersion);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_KDN_KDV_CD,
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

				Query q = session.createQuery(_SQL_COUNT_KALEOINSTANCE);

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
						"value.object.listener.com.liferay.portal.workflow.kaleo.model.KaleoInstance")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<KaleoInstance>> listenersList = new ArrayList<ModelListener<KaleoInstance>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<KaleoInstance>)Class.forName(
							listenerClassName).newInstance());
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
	@BeanReference(type = KaleoTaskInstanceAssignmentPersistence.class)
	protected KaleoTaskInstanceAssignmentPersistence kaleoTaskInstanceAssignmentPersistence;
	@BeanReference(type = KaleoTaskInstanceTokenPersistence.class)
	protected KaleoTaskInstanceTokenPersistence kaleoTaskInstanceTokenPersistence;
	@BeanReference(type = KaleoTransitionPersistence.class)
	protected KaleoTransitionPersistence kaleoTransitionPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_KALEOINSTANCE = "SELECT kaleoInstance FROM KaleoInstance kaleoInstance";
	private static final String _SQL_SELECT_KALEOINSTANCE_WHERE = "SELECT kaleoInstance FROM KaleoInstance kaleoInstance WHERE ";
	private static final String _SQL_COUNT_KALEOINSTANCE = "SELECT COUNT(kaleoInstance) FROM KaleoInstance kaleoInstance";
	private static final String _SQL_COUNT_KALEOINSTANCE_WHERE = "SELECT COUNT(kaleoInstance) FROM KaleoInstance kaleoInstance WHERE ";
	private static final String _FINDER_COLUMN_C_KDN_KDV_CD_COMPANYID_2 = "kaleoInstance.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_KDN_KDV_CD_KALEODEFINITIONNAME_1 =
		"kaleoInstance.kaleoDefinitionName IS NULL AND ";
	private static final String _FINDER_COLUMN_C_KDN_KDV_CD_KALEODEFINITIONNAME_2 =
		"kaleoInstance.kaleoDefinitionName = ? AND ";
	private static final String _FINDER_COLUMN_C_KDN_KDV_CD_KALEODEFINITIONNAME_3 =
		"(kaleoInstance.kaleoDefinitionName IS NULL OR kaleoInstance.kaleoDefinitionName = ?) AND ";
	private static final String _FINDER_COLUMN_C_KDN_KDV_CD_KALEODEFINITIONVERSION_2 =
		"kaleoInstance.kaleoDefinitionVersion = ? AND ";
	private static final String _FINDER_COLUMN_C_KDN_KDV_CD_COMPLETIONDATE_1 = "kaleoInstance.completionDate IS NULL";
	private static final String _FINDER_COLUMN_C_KDN_KDV_CD_COMPLETIONDATE_2 = "kaleoInstance.completionDate = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoInstance.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoInstance exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoInstance exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(KaleoInstancePersistenceImpl.class);
}