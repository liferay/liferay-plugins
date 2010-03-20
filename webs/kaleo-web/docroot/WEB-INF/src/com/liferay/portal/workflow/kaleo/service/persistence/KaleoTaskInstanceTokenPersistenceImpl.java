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
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
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

	public KaleoTaskInstanceToken remove(
		KaleoTaskInstanceToken kaleoTaskInstanceToken)
		throws SystemException {
		for (ModelListener<KaleoTaskInstanceToken> listener : listeners) {
			listener.onBeforeRemove(kaleoTaskInstanceToken);
		}

		kaleoTaskInstanceToken = removeImpl(kaleoTaskInstanceToken);

		for (ModelListener<KaleoTaskInstanceToken> listener : listeners) {
			listener.onAfterRemove(kaleoTaskInstanceToken);
		}

		return kaleoTaskInstanceToken;
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
		kaleoTaskInstanceTokenImpl.setCompanyId(kaleoTaskInstanceToken.getCompanyId());
		kaleoTaskInstanceTokenImpl.setUserId(kaleoTaskInstanceToken.getUserId());
		kaleoTaskInstanceTokenImpl.setUserName(kaleoTaskInstanceToken.getUserName());
		kaleoTaskInstanceTokenImpl.setCreateDate(kaleoTaskInstanceToken.getCreateDate());
		kaleoTaskInstanceTokenImpl.setModifiedDate(kaleoTaskInstanceToken.getModifiedDate());
		kaleoTaskInstanceTokenImpl.setKaleoInstanceId(kaleoTaskInstanceToken.getKaleoInstanceId());
		kaleoTaskInstanceTokenImpl.setKaleoInstanceTokenId(kaleoTaskInstanceToken.getKaleoInstanceTokenId());
		kaleoTaskInstanceTokenImpl.setKaleoTaskId(kaleoTaskInstanceToken.getKaleoTaskId());
		kaleoTaskInstanceTokenImpl.setCompletionUserId(kaleoTaskInstanceToken.getCompletionUserId());
		kaleoTaskInstanceTokenImpl.setCompletionDate(kaleoTaskInstanceToken.getCompletionDate());
		kaleoTaskInstanceTokenImpl.setDueDate(kaleoTaskInstanceToken.getDueDate());
		kaleoTaskInstanceTokenImpl.setContext(kaleoTaskInstanceToken.getContext());

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

	public void removeAll() throws SystemException {
		for (KaleoTaskInstanceToken kaleoTaskInstanceToken : findAll()) {
			remove(kaleoTaskInstanceToken);
		}
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

	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<KaleoTaskInstanceToken>> listenersList = new ArrayList<ModelListener<KaleoTaskInstanceToken>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<KaleoTaskInstanceToken>)Class.forName(
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
	private static final String _SQL_SELECT_KALEOTASKINSTANCETOKEN = "SELECT kaleoTaskInstanceToken FROM KaleoTaskInstanceToken kaleoTaskInstanceToken";
	private static final String _SQL_COUNT_KALEOTASKINSTANCETOKEN = "SELECT COUNT(kaleoTaskInstanceToken) FROM KaleoTaskInstanceToken kaleoTaskInstanceToken";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoTaskInstanceToken.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoTaskInstanceToken exists with the primary key ";
	private static Log _log = LogFactoryUtil.getLog(KaleoTaskInstanceTokenPersistenceImpl.class);
}