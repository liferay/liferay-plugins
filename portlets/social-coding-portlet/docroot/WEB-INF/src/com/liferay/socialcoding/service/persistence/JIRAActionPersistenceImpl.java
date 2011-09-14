/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.socialcoding.service.persistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
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
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.socialcoding.NoSuchJIRAActionException;
import com.liferay.socialcoding.model.JIRAAction;
import com.liferay.socialcoding.model.impl.JIRAActionImpl;
import com.liferay.socialcoding.model.impl.JIRAActionModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the j i r a action service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAActionPersistence
 * @see JIRAActionUtil
 * @generated
 */
public class JIRAActionPersistenceImpl extends BasePersistenceImpl<JIRAAction>
	implements JIRAActionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link JIRAActionUtil} to access the j i r a action persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = JIRAActionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_JIRAUSERID = new FinderPath(JIRAActionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAActionModelImpl.FINDER_CACHE_ENABLED, JIRAActionImpl.class,
			FINDER_CLASS_NAME_LIST, "findByJiraUserId",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_JIRAUSERID = new FinderPath(JIRAActionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByJiraUserId",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_JIRAISSUEID = new FinderPath(JIRAActionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAActionModelImpl.FINDER_CACHE_ENABLED, JIRAActionImpl.class,
			FINDER_CLASS_NAME_LIST, "findByJiraIssueId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_JIRAISSUEID = new FinderPath(JIRAActionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByJiraIssueId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_TYPE = new FinderPath(JIRAActionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAActionModelImpl.FINDER_CACHE_ENABLED, JIRAActionImpl.class,
			FINDER_CLASS_NAME_LIST, "findByType",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_TYPE = new FinderPath(JIRAActionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByType",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(JIRAActionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAActionModelImpl.FINDER_CACHE_ENABLED, JIRAActionImpl.class,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(JIRAActionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the j i r a action in the entity cache if it is enabled.
	 *
	 * @param jiraAction the j i r a action
	 */
	public void cacheResult(JIRAAction jiraAction) {
		EntityCacheUtil.putResult(JIRAActionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAActionImpl.class, jiraAction.getPrimaryKey(), jiraAction);

		jiraAction.resetOriginalValues();
	}

	/**
	 * Caches the j i r a actions in the entity cache if it is enabled.
	 *
	 * @param jiraActions the j i r a actions
	 */
	public void cacheResult(List<JIRAAction> jiraActions) {
		for (JIRAAction jiraAction : jiraActions) {
			if (EntityCacheUtil.getResult(
						JIRAActionModelImpl.ENTITY_CACHE_ENABLED,
						JIRAActionImpl.class, jiraAction.getPrimaryKey()) == null) {
				cacheResult(jiraAction);
			}
		}
	}

	/**
	 * Clears the cache for all j i r a actions.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(JIRAActionImpl.class.getName());
		}

		EntityCacheUtil.clearCache(JIRAActionImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the j i r a action.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(JIRAAction jiraAction) {
		EntityCacheUtil.removeResult(JIRAActionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAActionImpl.class, jiraAction.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FIND_ALL, FINDER_ARGS_EMPTY);
	}

	/**
	 * Creates a new j i r a action with the primary key. Does not add the j i r a action to the database.
	 *
	 * @param jiraActionId the primary key for the new j i r a action
	 * @return the new j i r a action
	 */
	public JIRAAction create(long jiraActionId) {
		JIRAAction jiraAction = new JIRAActionImpl();

		jiraAction.setNew(true);
		jiraAction.setPrimaryKey(jiraActionId);

		return jiraAction;
	}

	/**
	 * Removes the j i r a action with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the j i r a action
	 * @return the j i r a action that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a j i r a action with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public JIRAAction remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the j i r a action with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param jiraActionId the primary key of the j i r a action
	 * @return the j i r a action that was removed
	 * @throws com.liferay.socialcoding.NoSuchJIRAActionException if a j i r a action with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAAction remove(long jiraActionId)
		throws NoSuchJIRAActionException, SystemException {
		Session session = null;

		try {
			session = openSession();

			JIRAAction jiraAction = (JIRAAction)session.get(JIRAActionImpl.class,
					Long.valueOf(jiraActionId));

			if (jiraAction == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + jiraActionId);
				}

				throw new NoSuchJIRAActionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					jiraActionId);
			}

			return jiraActionPersistence.remove(jiraAction);
		}
		catch (NoSuchJIRAActionException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Removes the j i r a action from the database. Also notifies the appropriate model listeners.
	 *
	 * @param jiraAction the j i r a action
	 * @return the j i r a action that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public JIRAAction remove(JIRAAction jiraAction) throws SystemException {
		return super.remove(jiraAction);
	}

	@Override
	protected JIRAAction removeImpl(JIRAAction jiraAction)
		throws SystemException {
		jiraAction = toUnwrappedModel(jiraAction);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, jiraAction);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(JIRAActionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAActionImpl.class, jiraAction.getPrimaryKey());

		return jiraAction;
	}

	@Override
	public JIRAAction updateImpl(
		com.liferay.socialcoding.model.JIRAAction jiraAction, boolean merge)
		throws SystemException {
		jiraAction = toUnwrappedModel(jiraAction);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, jiraAction, merge);

			jiraAction.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(JIRAActionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAActionImpl.class, jiraAction.getPrimaryKey(), jiraAction);

		return jiraAction;
	}

	protected JIRAAction toUnwrappedModel(JIRAAction jiraAction) {
		if (jiraAction instanceof JIRAActionImpl) {
			return jiraAction;
		}

		JIRAActionImpl jiraActionImpl = new JIRAActionImpl();

		jiraActionImpl.setNew(jiraAction.isNew());
		jiraActionImpl.setPrimaryKey(jiraAction.getPrimaryKey());

		jiraActionImpl.setJiraActionId(jiraAction.getJiraActionId());
		jiraActionImpl.setJiraUserId(jiraAction.getJiraUserId());
		jiraActionImpl.setCreateDate(jiraAction.getCreateDate());
		jiraActionImpl.setModifiedDate(jiraAction.getModifiedDate());
		jiraActionImpl.setJiraIssueId(jiraAction.getJiraIssueId());
		jiraActionImpl.setType(jiraAction.getType());
		jiraActionImpl.setBody(jiraAction.getBody());
		jiraActionImpl.setJiraGroupName(jiraAction.getJiraGroupName());

		return jiraActionImpl;
	}

	/**
	 * Returns the j i r a action with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the j i r a action
	 * @return the j i r a action
	 * @throws com.liferay.portal.NoSuchModelException if a j i r a action with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public JIRAAction findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the j i r a action with the primary key or throws a {@link com.liferay.socialcoding.NoSuchJIRAActionException} if it could not be found.
	 *
	 * @param jiraActionId the primary key of the j i r a action
	 * @return the j i r a action
	 * @throws com.liferay.socialcoding.NoSuchJIRAActionException if a j i r a action with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAAction findByPrimaryKey(long jiraActionId)
		throws NoSuchJIRAActionException, SystemException {
		JIRAAction jiraAction = fetchByPrimaryKey(jiraActionId);

		if (jiraAction == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + jiraActionId);
			}

			throw new NoSuchJIRAActionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				jiraActionId);
		}

		return jiraAction;
	}

	/**
	 * Returns the j i r a action with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the j i r a action
	 * @return the j i r a action, or <code>null</code> if a j i r a action with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public JIRAAction fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the j i r a action with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param jiraActionId the primary key of the j i r a action
	 * @return the j i r a action, or <code>null</code> if a j i r a action with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAAction fetchByPrimaryKey(long jiraActionId)
		throws SystemException {
		JIRAAction jiraAction = (JIRAAction)EntityCacheUtil.getResult(JIRAActionModelImpl.ENTITY_CACHE_ENABLED,
				JIRAActionImpl.class, jiraActionId);

		if (jiraAction == _nullJIRAAction) {
			return null;
		}

		if (jiraAction == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				jiraAction = (JIRAAction)session.get(JIRAActionImpl.class,
						Long.valueOf(jiraActionId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (jiraAction != null) {
					cacheResult(jiraAction);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(JIRAActionModelImpl.ENTITY_CACHE_ENABLED,
						JIRAActionImpl.class, jiraActionId, _nullJIRAAction);
				}

				closeSession(session);
			}
		}

		return jiraAction;
	}

	/**
	 * Returns all the j i r a actions where jiraUserId = &#63;.
	 *
	 * @param jiraUserId the jira user ID
	 * @return the matching j i r a actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAAction> findByJiraUserId(String jiraUserId)
		throws SystemException {
		return findByJiraUserId(jiraUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j i r a actions where jiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jiraUserId the jira user ID
	 * @param start the lower bound of the range of j i r a actions
	 * @param end the upper bound of the range of j i r a actions (not inclusive)
	 * @return the range of matching j i r a actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAAction> findByJiraUserId(String jiraUserId, int start,
		int end) throws SystemException {
		return findByJiraUserId(jiraUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a actions where jiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jiraUserId the jira user ID
	 * @param start the lower bound of the range of j i r a actions
	 * @param end the upper bound of the range of j i r a actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching j i r a actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAAction> findByJiraUserId(String jiraUserId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				jiraUserId,
				
				start, end, orderByComparator
			};

		List<JIRAAction> list = (List<JIRAAction>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_JIRAUSERID,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_JIRAACTION_WHERE);

			if (jiraUserId == null) {
				query.append(_FINDER_COLUMN_JIRAUSERID_JIRAUSERID_1);
			}
			else {
				if (jiraUserId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_JIRAUSERID_JIRAUSERID_3);
				}
				else {
					query.append(_FINDER_COLUMN_JIRAUSERID_JIRAUSERID_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(JIRAActionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (jiraUserId != null) {
					qPos.add(jiraUserId);
				}

				list = (List<JIRAAction>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_JIRAUSERID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_JIRAUSERID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first j i r a action in the ordered set where jiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jiraUserId the jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a action
	 * @throws com.liferay.socialcoding.NoSuchJIRAActionException if a matching j i r a action could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAAction findByJiraUserId_First(String jiraUserId,
		OrderByComparator orderByComparator)
		throws NoSuchJIRAActionException, SystemException {
		List<JIRAAction> list = findByJiraUserId(jiraUserId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("jiraUserId=");
			msg.append(jiraUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAActionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last j i r a action in the ordered set where jiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jiraUserId the jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a action
	 * @throws com.liferay.socialcoding.NoSuchJIRAActionException if a matching j i r a action could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAAction findByJiraUserId_Last(String jiraUserId,
		OrderByComparator orderByComparator)
		throws NoSuchJIRAActionException, SystemException {
		int count = countByJiraUserId(jiraUserId);

		List<JIRAAction> list = findByJiraUserId(jiraUserId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("jiraUserId=");
			msg.append(jiraUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAActionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the j i r a actions before and after the current j i r a action in the ordered set where jiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jiraActionId the primary key of the current j i r a action
	 * @param jiraUserId the jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next j i r a action
	 * @throws com.liferay.socialcoding.NoSuchJIRAActionException if a j i r a action with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAAction[] findByJiraUserId_PrevAndNext(long jiraActionId,
		String jiraUserId, OrderByComparator orderByComparator)
		throws NoSuchJIRAActionException, SystemException {
		JIRAAction jiraAction = findByPrimaryKey(jiraActionId);

		Session session = null;

		try {
			session = openSession();

			JIRAAction[] array = new JIRAActionImpl[3];

			array[0] = getByJiraUserId_PrevAndNext(session, jiraAction,
					jiraUserId, orderByComparator, true);

			array[1] = jiraAction;

			array[2] = getByJiraUserId_PrevAndNext(session, jiraAction,
					jiraUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected JIRAAction getByJiraUserId_PrevAndNext(Session session,
		JIRAAction jiraAction, String jiraUserId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JIRAACTION_WHERE);

		if (jiraUserId == null) {
			query.append(_FINDER_COLUMN_JIRAUSERID_JIRAUSERID_1);
		}
		else {
			if (jiraUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_JIRAUSERID_JIRAUSERID_3);
			}
			else {
				query.append(_FINDER_COLUMN_JIRAUSERID_JIRAUSERID_2);
			}
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
			query.append(JIRAActionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (jiraUserId != null) {
			qPos.add(jiraUserId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(jiraAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JIRAAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the j i r a actions where jiraIssueId = &#63;.
	 *
	 * @param jiraIssueId the jira issue ID
	 * @return the matching j i r a actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAAction> findByJiraIssueId(long jiraIssueId)
		throws SystemException {
		return findByJiraIssueId(jiraIssueId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j i r a actions where jiraIssueId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jiraIssueId the jira issue ID
	 * @param start the lower bound of the range of j i r a actions
	 * @param end the upper bound of the range of j i r a actions (not inclusive)
	 * @return the range of matching j i r a actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAAction> findByJiraIssueId(long jiraIssueId, int start,
		int end) throws SystemException {
		return findByJiraIssueId(jiraIssueId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a actions where jiraIssueId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jiraIssueId the jira issue ID
	 * @param start the lower bound of the range of j i r a actions
	 * @param end the upper bound of the range of j i r a actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching j i r a actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAAction> findByJiraIssueId(long jiraIssueId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				jiraIssueId,
				
				start, end, orderByComparator
			};

		List<JIRAAction> list = (List<JIRAAction>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_JIRAISSUEID,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_JIRAACTION_WHERE);

			query.append(_FINDER_COLUMN_JIRAISSUEID_JIRAISSUEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(JIRAActionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(jiraIssueId);

				list = (List<JIRAAction>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_JIRAISSUEID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_JIRAISSUEID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first j i r a action in the ordered set where jiraIssueId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jiraIssueId the jira issue ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a action
	 * @throws com.liferay.socialcoding.NoSuchJIRAActionException if a matching j i r a action could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAAction findByJiraIssueId_First(long jiraIssueId,
		OrderByComparator orderByComparator)
		throws NoSuchJIRAActionException, SystemException {
		List<JIRAAction> list = findByJiraIssueId(jiraIssueId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("jiraIssueId=");
			msg.append(jiraIssueId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAActionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last j i r a action in the ordered set where jiraIssueId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jiraIssueId the jira issue ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a action
	 * @throws com.liferay.socialcoding.NoSuchJIRAActionException if a matching j i r a action could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAAction findByJiraIssueId_Last(long jiraIssueId,
		OrderByComparator orderByComparator)
		throws NoSuchJIRAActionException, SystemException {
		int count = countByJiraIssueId(jiraIssueId);

		List<JIRAAction> list = findByJiraIssueId(jiraIssueId, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("jiraIssueId=");
			msg.append(jiraIssueId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAActionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the j i r a actions before and after the current j i r a action in the ordered set where jiraIssueId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jiraActionId the primary key of the current j i r a action
	 * @param jiraIssueId the jira issue ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next j i r a action
	 * @throws com.liferay.socialcoding.NoSuchJIRAActionException if a j i r a action with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAAction[] findByJiraIssueId_PrevAndNext(long jiraActionId,
		long jiraIssueId, OrderByComparator orderByComparator)
		throws NoSuchJIRAActionException, SystemException {
		JIRAAction jiraAction = findByPrimaryKey(jiraActionId);

		Session session = null;

		try {
			session = openSession();

			JIRAAction[] array = new JIRAActionImpl[3];

			array[0] = getByJiraIssueId_PrevAndNext(session, jiraAction,
					jiraIssueId, orderByComparator, true);

			array[1] = jiraAction;

			array[2] = getByJiraIssueId_PrevAndNext(session, jiraAction,
					jiraIssueId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected JIRAAction getByJiraIssueId_PrevAndNext(Session session,
		JIRAAction jiraAction, long jiraIssueId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JIRAACTION_WHERE);

		query.append(_FINDER_COLUMN_JIRAISSUEID_JIRAISSUEID_2);

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
			query.append(JIRAActionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(jiraIssueId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(jiraAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JIRAAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the j i r a actions where type = &#63;.
	 *
	 * @param type the type
	 * @return the matching j i r a actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAAction> findByType(String type) throws SystemException {
		return findByType(type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j i r a actions where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of j i r a actions
	 * @param end the upper bound of the range of j i r a actions (not inclusive)
	 * @return the range of matching j i r a actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAAction> findByType(String type, int start, int end)
		throws SystemException {
		return findByType(type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a actions where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of j i r a actions
	 * @param end the upper bound of the range of j i r a actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching j i r a actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAAction> findByType(String type, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] { type, start, end, orderByComparator };

		List<JIRAAction> list = (List<JIRAAction>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_TYPE,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_JIRAACTION_WHERE);

			if (type == null) {
				query.append(_FINDER_COLUMN_TYPE_TYPE_1);
			}
			else {
				if (type.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_TYPE_TYPE_3);
				}
				else {
					query.append(_FINDER_COLUMN_TYPE_TYPE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(JIRAActionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (type != null) {
					qPos.add(type);
				}

				list = (List<JIRAAction>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_TYPE,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_TYPE,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first j i r a action in the ordered set where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a action
	 * @throws com.liferay.socialcoding.NoSuchJIRAActionException if a matching j i r a action could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAAction findByType_First(String type,
		OrderByComparator orderByComparator)
		throws NoSuchJIRAActionException, SystemException {
		List<JIRAAction> list = findByType(type, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("type=");
			msg.append(type);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAActionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last j i r a action in the ordered set where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a action
	 * @throws com.liferay.socialcoding.NoSuchJIRAActionException if a matching j i r a action could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAAction findByType_Last(String type,
		OrderByComparator orderByComparator)
		throws NoSuchJIRAActionException, SystemException {
		int count = countByType(type);

		List<JIRAAction> list = findByType(type, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("type=");
			msg.append(type);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAActionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the j i r a actions before and after the current j i r a action in the ordered set where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jiraActionId the primary key of the current j i r a action
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next j i r a action
	 * @throws com.liferay.socialcoding.NoSuchJIRAActionException if a j i r a action with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAAction[] findByType_PrevAndNext(long jiraActionId, String type,
		OrderByComparator orderByComparator)
		throws NoSuchJIRAActionException, SystemException {
		JIRAAction jiraAction = findByPrimaryKey(jiraActionId);

		Session session = null;

		try {
			session = openSession();

			JIRAAction[] array = new JIRAActionImpl[3];

			array[0] = getByType_PrevAndNext(session, jiraAction, type,
					orderByComparator, true);

			array[1] = jiraAction;

			array[2] = getByType_PrevAndNext(session, jiraAction, type,
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

	protected JIRAAction getByType_PrevAndNext(Session session,
		JIRAAction jiraAction, String type,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JIRAACTION_WHERE);

		if (type == null) {
			query.append(_FINDER_COLUMN_TYPE_TYPE_1);
		}
		else {
			if (type.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TYPE_TYPE_3);
			}
			else {
				query.append(_FINDER_COLUMN_TYPE_TYPE_2);
			}
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
			query.append(JIRAActionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (type != null) {
			qPos.add(type);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(jiraAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JIRAAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the j i r a actions.
	 *
	 * @return the j i r a actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAAction> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j i r a actions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of j i r a actions
	 * @param end the upper bound of the range of j i r a actions (not inclusive)
	 * @return the range of j i r a actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAAction> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a actions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of j i r a actions
	 * @param end the upper bound of the range of j i r a actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of j i r a actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAAction> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		List<JIRAAction> list = (List<JIRAAction>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_JIRAACTION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_JIRAACTION.concat(JIRAActionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<JIRAAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<JIRAAction>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_ALL,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs,
						list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the j i r a actions where jiraUserId = &#63; from the database.
	 *
	 * @param jiraUserId the jira user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByJiraUserId(String jiraUserId) throws SystemException {
		for (JIRAAction jiraAction : findByJiraUserId(jiraUserId)) {
			jiraActionPersistence.remove(jiraAction);
		}
	}

	/**
	 * Removes all the j i r a actions where jiraIssueId = &#63; from the database.
	 *
	 * @param jiraIssueId the jira issue ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByJiraIssueId(long jiraIssueId) throws SystemException {
		for (JIRAAction jiraAction : findByJiraIssueId(jiraIssueId)) {
			jiraActionPersistence.remove(jiraAction);
		}
	}

	/**
	 * Removes all the j i r a actions where type = &#63; from the database.
	 *
	 * @param type the type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByType(String type) throws SystemException {
		for (JIRAAction jiraAction : findByType(type)) {
			jiraActionPersistence.remove(jiraAction);
		}
	}

	/**
	 * Removes all the j i r a actions from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (JIRAAction jiraAction : findAll()) {
			jiraActionPersistence.remove(jiraAction);
		}
	}

	/**
	 * Returns the number of j i r a actions where jiraUserId = &#63;.
	 *
	 * @param jiraUserId the jira user ID
	 * @return the number of matching j i r a actions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByJiraUserId(String jiraUserId) throws SystemException {
		Object[] finderArgs = new Object[] { jiraUserId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_JIRAUSERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_JIRAACTION_WHERE);

			if (jiraUserId == null) {
				query.append(_FINDER_COLUMN_JIRAUSERID_JIRAUSERID_1);
			}
			else {
				if (jiraUserId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_JIRAUSERID_JIRAUSERID_3);
				}
				else {
					query.append(_FINDER_COLUMN_JIRAUSERID_JIRAUSERID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (jiraUserId != null) {
					qPos.add(jiraUserId);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_JIRAUSERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of j i r a actions where jiraIssueId = &#63;.
	 *
	 * @param jiraIssueId the jira issue ID
	 * @return the number of matching j i r a actions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByJiraIssueId(long jiraIssueId) throws SystemException {
		Object[] finderArgs = new Object[] { jiraIssueId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_JIRAISSUEID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_JIRAACTION_WHERE);

			query.append(_FINDER_COLUMN_JIRAISSUEID_JIRAISSUEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(jiraIssueId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_JIRAISSUEID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of j i r a actions where type = &#63;.
	 *
	 * @param type the type
	 * @return the number of matching j i r a actions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByType(String type) throws SystemException {
		Object[] finderArgs = new Object[] { type };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TYPE,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_JIRAACTION_WHERE);

			if (type == null) {
				query.append(_FINDER_COLUMN_TYPE_TYPE_1);
			}
			else {
				if (type.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_TYPE_TYPE_3);
				}
				else {
					query.append(_FINDER_COLUMN_TYPE_TYPE_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (type != null) {
					qPos.add(type);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TYPE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of j i r a actions.
	 *
	 * @return the number of j i r a actions
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_JIRAACTION);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the j i r a action persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.socialcoding.model.JIRAAction")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<JIRAAction>> listenersList = new ArrayList<ModelListener<JIRAAction>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<JIRAAction>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(JIRAActionImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST);
	}

	@BeanReference(type = JIRAActionPersistence.class)
	protected JIRAActionPersistence jiraActionPersistence;
	@BeanReference(type = JIRAChangeGroupPersistence.class)
	protected JIRAChangeGroupPersistence jiraChangeGroupPersistence;
	@BeanReference(type = JIRAChangeItemPersistence.class)
	protected JIRAChangeItemPersistence jiraChangeItemPersistence;
	@BeanReference(type = JIRAIssuePersistence.class)
	protected JIRAIssuePersistence jiraIssuePersistence;
	@BeanReference(type = SVNRepositoryPersistence.class)
	protected SVNRepositoryPersistence svnRepositoryPersistence;
	@BeanReference(type = SVNRevisionPersistence.class)
	protected SVNRevisionPersistence svnRevisionPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_JIRAACTION = "SELECT jiraAction FROM JIRAAction jiraAction";
	private static final String _SQL_SELECT_JIRAACTION_WHERE = "SELECT jiraAction FROM JIRAAction jiraAction WHERE ";
	private static final String _SQL_COUNT_JIRAACTION = "SELECT COUNT(jiraAction) FROM JIRAAction jiraAction";
	private static final String _SQL_COUNT_JIRAACTION_WHERE = "SELECT COUNT(jiraAction) FROM JIRAAction jiraAction WHERE ";
	private static final String _FINDER_COLUMN_JIRAUSERID_JIRAUSERID_1 = "jiraAction.jiraUserId IS NULL";
	private static final String _FINDER_COLUMN_JIRAUSERID_JIRAUSERID_2 = "jiraAction.jiraUserId = ?";
	private static final String _FINDER_COLUMN_JIRAUSERID_JIRAUSERID_3 = "(jiraAction.jiraUserId IS NULL OR jiraAction.jiraUserId = ?)";
	private static final String _FINDER_COLUMN_JIRAISSUEID_JIRAISSUEID_2 = "jiraAction.jiraIssueId = ?";
	private static final String _FINDER_COLUMN_TYPE_TYPE_1 = "jiraAction.type IS NULL";
	private static final String _FINDER_COLUMN_TYPE_TYPE_2 = "jiraAction.type = ?";
	private static final String _FINDER_COLUMN_TYPE_TYPE_3 = "(jiraAction.type IS NULL OR jiraAction.type = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "jiraAction.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No JIRAAction exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No JIRAAction exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(JIRAActionPersistenceImpl.class);
	private static JIRAAction _nullJIRAAction = new JIRAActionImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<JIRAAction> toCacheModel() {
				return _nullJIRAActionCacheModel;
			}
		};

	private static CacheModel<JIRAAction> _nullJIRAActionCacheModel = new CacheModel<JIRAAction>() {
			public JIRAAction toEntityModel() {
				return _nullJIRAAction;
			}
		};
}