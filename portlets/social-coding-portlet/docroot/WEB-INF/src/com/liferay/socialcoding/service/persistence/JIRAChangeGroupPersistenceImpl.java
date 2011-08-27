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

import com.liferay.socialcoding.NoSuchJIRAChangeGroupException;
import com.liferay.socialcoding.model.JIRAChangeGroup;
import com.liferay.socialcoding.model.impl.JIRAChangeGroupImpl;
import com.liferay.socialcoding.model.impl.JIRAChangeGroupModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the j i r a change group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAChangeGroupPersistence
 * @see JIRAChangeGroupUtil
 * @generated
 */
public class JIRAChangeGroupPersistenceImpl extends BasePersistenceImpl<JIRAChangeGroup>
	implements JIRAChangeGroupPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link JIRAChangeGroupUtil} to access the j i r a change group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = JIRAChangeGroupImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_JIRAUSERID = new FinderPath(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeGroupModelImpl.FINDER_CACHE_ENABLED,
			JIRAChangeGroupImpl.class, FINDER_CLASS_NAME_LIST,
			"findByJiraUserId",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_JIRAUSERID = new FinderPath(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByJiraUserId",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_JIRAISSUEID = new FinderPath(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeGroupModelImpl.FINDER_CACHE_ENABLED,
			JIRAChangeGroupImpl.class, FINDER_CLASS_NAME_LIST,
			"findByJiraIssueId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_JIRAISSUEID = new FinderPath(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByJiraIssueId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeGroupModelImpl.FINDER_CACHE_ENABLED,
			JIRAChangeGroupImpl.class, FINDER_CLASS_NAME_LIST, "findAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the j i r a change group in the entity cache if it is enabled.
	 *
	 * @param jiraChangeGroup the j i r a change group
	 */
	public void cacheResult(JIRAChangeGroup jiraChangeGroup) {
		EntityCacheUtil.putResult(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeGroupImpl.class, jiraChangeGroup.getPrimaryKey(),
			jiraChangeGroup);

		jiraChangeGroup.resetOriginalValues();
	}

	/**
	 * Caches the j i r a change groups in the entity cache if it is enabled.
	 *
	 * @param jiraChangeGroups the j i r a change groups
	 */
	public void cacheResult(List<JIRAChangeGroup> jiraChangeGroups) {
		for (JIRAChangeGroup jiraChangeGroup : jiraChangeGroups) {
			if (EntityCacheUtil.getResult(
						JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
						JIRAChangeGroupImpl.class,
						jiraChangeGroup.getPrimaryKey(), this) == null) {
				cacheResult(jiraChangeGroup);
			}
		}
	}

	/**
	 * Clears the cache for all j i r a change groups.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(JIRAChangeGroupImpl.class.getName());
		}

		EntityCacheUtil.clearCache(JIRAChangeGroupImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the j i r a change group.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(JIRAChangeGroup jiraChangeGroup) {
		EntityCacheUtil.removeResult(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeGroupImpl.class, jiraChangeGroup.getPrimaryKey());
	}

	/**
	 * Creates a new j i r a change group with the primary key. Does not add the j i r a change group to the database.
	 *
	 * @param jiraChangeGroupId the primary key for the new j i r a change group
	 * @return the new j i r a change group
	 */
	public JIRAChangeGroup create(long jiraChangeGroupId) {
		JIRAChangeGroup jiraChangeGroup = new JIRAChangeGroupImpl();

		jiraChangeGroup.setNew(true);
		jiraChangeGroup.setPrimaryKey(jiraChangeGroupId);

		return jiraChangeGroup;
	}

	/**
	 * Removes the j i r a change group with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the j i r a change group
	 * @return the j i r a change group that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a j i r a change group with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public JIRAChangeGroup remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the j i r a change group with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param jiraChangeGroupId the primary key of the j i r a change group
	 * @return the j i r a change group that was removed
	 * @throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException if a j i r a change group with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAChangeGroup remove(long jiraChangeGroupId)
		throws NoSuchJIRAChangeGroupException, SystemException {
		Session session = null;

		try {
			session = openSession();

			JIRAChangeGroup jiraChangeGroup = (JIRAChangeGroup)session.get(JIRAChangeGroupImpl.class,
					Long.valueOf(jiraChangeGroupId));

			if (jiraChangeGroup == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						jiraChangeGroupId);
				}

				throw new NoSuchJIRAChangeGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					jiraChangeGroupId);
			}

			return jiraChangeGroupPersistence.remove(jiraChangeGroup);
		}
		catch (NoSuchJIRAChangeGroupException nsee) {
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
	 * Removes the j i r a change group from the database. Also notifies the appropriate model listeners.
	 *
	 * @param jiraChangeGroup the j i r a change group
	 * @return the j i r a change group that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public JIRAChangeGroup remove(JIRAChangeGroup jiraChangeGroup)
		throws SystemException {
		return super.remove(jiraChangeGroup);
	}

	@Override
	protected JIRAChangeGroup removeImpl(JIRAChangeGroup jiraChangeGroup)
		throws SystemException {
		jiraChangeGroup = toUnwrappedModel(jiraChangeGroup);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, jiraChangeGroup);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeGroupImpl.class, jiraChangeGroup.getPrimaryKey());

		return jiraChangeGroup;
	}

	@Override
	public JIRAChangeGroup updateImpl(
		com.liferay.socialcoding.model.JIRAChangeGroup jiraChangeGroup,
		boolean merge) throws SystemException {
		jiraChangeGroup = toUnwrappedModel(jiraChangeGroup);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, jiraChangeGroup, merge);

			jiraChangeGroup.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeGroupImpl.class, jiraChangeGroup.getPrimaryKey(),
			jiraChangeGroup);

		return jiraChangeGroup;
	}

	protected JIRAChangeGroup toUnwrappedModel(JIRAChangeGroup jiraChangeGroup) {
		if (jiraChangeGroup instanceof JIRAChangeGroupImpl) {
			return jiraChangeGroup;
		}

		JIRAChangeGroupImpl jiraChangeGroupImpl = new JIRAChangeGroupImpl();

		jiraChangeGroupImpl.setNew(jiraChangeGroup.isNew());
		jiraChangeGroupImpl.setPrimaryKey(jiraChangeGroup.getPrimaryKey());

		jiraChangeGroupImpl.setJiraChangeGroupId(jiraChangeGroup.getJiraChangeGroupId());
		jiraChangeGroupImpl.setJiraUserId(jiraChangeGroup.getJiraUserId());
		jiraChangeGroupImpl.setCreateDate(jiraChangeGroup.getCreateDate());
		jiraChangeGroupImpl.setJiraIssueId(jiraChangeGroup.getJiraIssueId());

		return jiraChangeGroupImpl;
	}

	/**
	 * Returns the j i r a change group with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the j i r a change group
	 * @return the j i r a change group
	 * @throws com.liferay.portal.NoSuchModelException if a j i r a change group with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public JIRAChangeGroup findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the j i r a change group with the primary key or throws a {@link com.liferay.socialcoding.NoSuchJIRAChangeGroupException} if it could not be found.
	 *
	 * @param jiraChangeGroupId the primary key of the j i r a change group
	 * @return the j i r a change group
	 * @throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException if a j i r a change group with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAChangeGroup findByPrimaryKey(long jiraChangeGroupId)
		throws NoSuchJIRAChangeGroupException, SystemException {
		JIRAChangeGroup jiraChangeGroup = fetchByPrimaryKey(jiraChangeGroupId);

		if (jiraChangeGroup == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + jiraChangeGroupId);
			}

			throw new NoSuchJIRAChangeGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				jiraChangeGroupId);
		}

		return jiraChangeGroup;
	}

	/**
	 * Returns the j i r a change group with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the j i r a change group
	 * @return the j i r a change group, or <code>null</code> if a j i r a change group with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public JIRAChangeGroup fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the j i r a change group with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param jiraChangeGroupId the primary key of the j i r a change group
	 * @return the j i r a change group, or <code>null</code> if a j i r a change group with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAChangeGroup fetchByPrimaryKey(long jiraChangeGroupId)
		throws SystemException {
		JIRAChangeGroup jiraChangeGroup = (JIRAChangeGroup)EntityCacheUtil.getResult(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
				JIRAChangeGroupImpl.class, jiraChangeGroupId, this);

		if (jiraChangeGroup == _nullJIRAChangeGroup) {
			return null;
		}

		if (jiraChangeGroup == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				jiraChangeGroup = (JIRAChangeGroup)session.get(JIRAChangeGroupImpl.class,
						Long.valueOf(jiraChangeGroupId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (jiraChangeGroup != null) {
					cacheResult(jiraChangeGroup);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
						JIRAChangeGroupImpl.class, jiraChangeGroupId,
						_nullJIRAChangeGroup);
				}

				closeSession(session);
			}
		}

		return jiraChangeGroup;
	}

	/**
	 * Returns all the j i r a change groups where jiraUserId = &#63;.
	 *
	 * @param jiraUserId the jira user ID
	 * @return the matching j i r a change groups
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAChangeGroup> findByJiraUserId(String jiraUserId)
		throws SystemException {
		return findByJiraUserId(jiraUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j i r a change groups where jiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jiraUserId the jira user ID
	 * @param start the lower bound of the range of j i r a change groups
	 * @param end the upper bound of the range of j i r a change groups (not inclusive)
	 * @return the range of matching j i r a change groups
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAChangeGroup> findByJiraUserId(String jiraUserId, int start,
		int end) throws SystemException {
		return findByJiraUserId(jiraUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a change groups where jiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jiraUserId the jira user ID
	 * @param start the lower bound of the range of j i r a change groups
	 * @param end the upper bound of the range of j i r a change groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching j i r a change groups
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAChangeGroup> findByJiraUserId(String jiraUserId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				jiraUserId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<JIRAChangeGroup> list = (List<JIRAChangeGroup>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_JIRAUSERID,
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

			query.append(_SQL_SELECT_JIRACHANGEGROUP_WHERE);

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
				query.append(JIRAChangeGroupModelImpl.ORDER_BY_JPQL);
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

				list = (List<JIRAChangeGroup>)QueryUtil.list(q, getDialect(),
						start, end);
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
	 * Returns the first j i r a change group in the ordered set where jiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jiraUserId the jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a change group
	 * @throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException if a matching j i r a change group could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAChangeGroup findByJiraUserId_First(String jiraUserId,
		OrderByComparator orderByComparator)
		throws NoSuchJIRAChangeGroupException, SystemException {
		List<JIRAChangeGroup> list = findByJiraUserId(jiraUserId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("jiraUserId=");
			msg.append(jiraUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAChangeGroupException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last j i r a change group in the ordered set where jiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jiraUserId the jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a change group
	 * @throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException if a matching j i r a change group could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAChangeGroup findByJiraUserId_Last(String jiraUserId,
		OrderByComparator orderByComparator)
		throws NoSuchJIRAChangeGroupException, SystemException {
		int count = countByJiraUserId(jiraUserId);

		List<JIRAChangeGroup> list = findByJiraUserId(jiraUserId, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("jiraUserId=");
			msg.append(jiraUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAChangeGroupException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the j i r a change groups before and after the current j i r a change group in the ordered set where jiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jiraChangeGroupId the primary key of the current j i r a change group
	 * @param jiraUserId the jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next j i r a change group
	 * @throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException if a j i r a change group with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAChangeGroup[] findByJiraUserId_PrevAndNext(
		long jiraChangeGroupId, String jiraUserId,
		OrderByComparator orderByComparator)
		throws NoSuchJIRAChangeGroupException, SystemException {
		JIRAChangeGroup jiraChangeGroup = findByPrimaryKey(jiraChangeGroupId);

		Session session = null;

		try {
			session = openSession();

			JIRAChangeGroup[] array = new JIRAChangeGroupImpl[3];

			array[0] = getByJiraUserId_PrevAndNext(session, jiraChangeGroup,
					jiraUserId, orderByComparator, true);

			array[1] = jiraChangeGroup;

			array[2] = getByJiraUserId_PrevAndNext(session, jiraChangeGroup,
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

	protected JIRAChangeGroup getByJiraUserId_PrevAndNext(Session session,
		JIRAChangeGroup jiraChangeGroup, String jiraUserId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JIRACHANGEGROUP_WHERE);

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
			query.append(JIRAChangeGroupModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByValues(jiraChangeGroup);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JIRAChangeGroup> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the j i r a change groups where jiraIssueId = &#63;.
	 *
	 * @param jiraIssueId the jira issue ID
	 * @return the matching j i r a change groups
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAChangeGroup> findByJiraIssueId(long jiraIssueId)
		throws SystemException {
		return findByJiraIssueId(jiraIssueId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j i r a change groups where jiraIssueId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jiraIssueId the jira issue ID
	 * @param start the lower bound of the range of j i r a change groups
	 * @param end the upper bound of the range of j i r a change groups (not inclusive)
	 * @return the range of matching j i r a change groups
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAChangeGroup> findByJiraIssueId(long jiraIssueId, int start,
		int end) throws SystemException {
		return findByJiraIssueId(jiraIssueId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a change groups where jiraIssueId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jiraIssueId the jira issue ID
	 * @param start the lower bound of the range of j i r a change groups
	 * @param end the upper bound of the range of j i r a change groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching j i r a change groups
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAChangeGroup> findByJiraIssueId(long jiraIssueId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				jiraIssueId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<JIRAChangeGroup> list = (List<JIRAChangeGroup>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_JIRAISSUEID,
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

			query.append(_SQL_SELECT_JIRACHANGEGROUP_WHERE);

			query.append(_FINDER_COLUMN_JIRAISSUEID_JIRAISSUEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(JIRAChangeGroupModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(jiraIssueId);

				list = (List<JIRAChangeGroup>)QueryUtil.list(q, getDialect(),
						start, end);
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
	 * Returns the first j i r a change group in the ordered set where jiraIssueId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jiraIssueId the jira issue ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a change group
	 * @throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException if a matching j i r a change group could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAChangeGroup findByJiraIssueId_First(long jiraIssueId,
		OrderByComparator orderByComparator)
		throws NoSuchJIRAChangeGroupException, SystemException {
		List<JIRAChangeGroup> list = findByJiraIssueId(jiraIssueId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("jiraIssueId=");
			msg.append(jiraIssueId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAChangeGroupException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last j i r a change group in the ordered set where jiraIssueId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jiraIssueId the jira issue ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a change group
	 * @throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException if a matching j i r a change group could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAChangeGroup findByJiraIssueId_Last(long jiraIssueId,
		OrderByComparator orderByComparator)
		throws NoSuchJIRAChangeGroupException, SystemException {
		int count = countByJiraIssueId(jiraIssueId);

		List<JIRAChangeGroup> list = findByJiraIssueId(jiraIssueId, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("jiraIssueId=");
			msg.append(jiraIssueId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAChangeGroupException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the j i r a change groups before and after the current j i r a change group in the ordered set where jiraIssueId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jiraChangeGroupId the primary key of the current j i r a change group
	 * @param jiraIssueId the jira issue ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next j i r a change group
	 * @throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException if a j i r a change group with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAChangeGroup[] findByJiraIssueId_PrevAndNext(
		long jiraChangeGroupId, long jiraIssueId,
		OrderByComparator orderByComparator)
		throws NoSuchJIRAChangeGroupException, SystemException {
		JIRAChangeGroup jiraChangeGroup = findByPrimaryKey(jiraChangeGroupId);

		Session session = null;

		try {
			session = openSession();

			JIRAChangeGroup[] array = new JIRAChangeGroupImpl[3];

			array[0] = getByJiraIssueId_PrevAndNext(session, jiraChangeGroup,
					jiraIssueId, orderByComparator, true);

			array[1] = jiraChangeGroup;

			array[2] = getByJiraIssueId_PrevAndNext(session, jiraChangeGroup,
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

	protected JIRAChangeGroup getByJiraIssueId_PrevAndNext(Session session,
		JIRAChangeGroup jiraChangeGroup, long jiraIssueId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JIRACHANGEGROUP_WHERE);

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
			query.append(JIRAChangeGroupModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(jiraIssueId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(jiraChangeGroup);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JIRAChangeGroup> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the j i r a change groups.
	 *
	 * @return the j i r a change groups
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAChangeGroup> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j i r a change groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of j i r a change groups
	 * @param end the upper bound of the range of j i r a change groups (not inclusive)
	 * @return the range of j i r a change groups
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAChangeGroup> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a change groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of j i r a change groups
	 * @param end the upper bound of the range of j i r a change groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of j i r a change groups
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAChangeGroup> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<JIRAChangeGroup> list = (List<JIRAChangeGroup>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_JIRACHANGEGROUP);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_JIRACHANGEGROUP.concat(JIRAChangeGroupModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<JIRAChangeGroup>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<JIRAChangeGroup>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Removes all the j i r a change groups where jiraUserId = &#63; from the database.
	 *
	 * @param jiraUserId the jira user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByJiraUserId(String jiraUserId) throws SystemException {
		for (JIRAChangeGroup jiraChangeGroup : findByJiraUserId(jiraUserId)) {
			jiraChangeGroupPersistence.remove(jiraChangeGroup);
		}
	}

	/**
	 * Removes all the j i r a change groups where jiraIssueId = &#63; from the database.
	 *
	 * @param jiraIssueId the jira issue ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByJiraIssueId(long jiraIssueId) throws SystemException {
		for (JIRAChangeGroup jiraChangeGroup : findByJiraIssueId(jiraIssueId)) {
			jiraChangeGroupPersistence.remove(jiraChangeGroup);
		}
	}

	/**
	 * Removes all the j i r a change groups from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (JIRAChangeGroup jiraChangeGroup : findAll()) {
			jiraChangeGroupPersistence.remove(jiraChangeGroup);
		}
	}

	/**
	 * Returns the number of j i r a change groups where jiraUserId = &#63;.
	 *
	 * @param jiraUserId the jira user ID
	 * @return the number of matching j i r a change groups
	 * @throws SystemException if a system exception occurred
	 */
	public int countByJiraUserId(String jiraUserId) throws SystemException {
		Object[] finderArgs = new Object[] { jiraUserId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_JIRAUSERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_JIRACHANGEGROUP_WHERE);

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
	 * Returns the number of j i r a change groups where jiraIssueId = &#63;.
	 *
	 * @param jiraIssueId the jira issue ID
	 * @return the number of matching j i r a change groups
	 * @throws SystemException if a system exception occurred
	 */
	public int countByJiraIssueId(long jiraIssueId) throws SystemException {
		Object[] finderArgs = new Object[] { jiraIssueId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_JIRAISSUEID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_JIRACHANGEGROUP_WHERE);

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
	 * Returns the number of j i r a change groups.
	 *
	 * @return the number of j i r a change groups
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_JIRACHANGEGROUP);

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

	/**
	 * Initializes the j i r a change group persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.socialcoding.model.JIRAChangeGroup")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<JIRAChangeGroup>> listenersList = new ArrayList<ModelListener<JIRAChangeGroup>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<JIRAChangeGroup>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(JIRAChangeGroupImpl.class.getName());
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
	private static final String _SQL_SELECT_JIRACHANGEGROUP = "SELECT jiraChangeGroup FROM JIRAChangeGroup jiraChangeGroup";
	private static final String _SQL_SELECT_JIRACHANGEGROUP_WHERE = "SELECT jiraChangeGroup FROM JIRAChangeGroup jiraChangeGroup WHERE ";
	private static final String _SQL_COUNT_JIRACHANGEGROUP = "SELECT COUNT(jiraChangeGroup) FROM JIRAChangeGroup jiraChangeGroup";
	private static final String _SQL_COUNT_JIRACHANGEGROUP_WHERE = "SELECT COUNT(jiraChangeGroup) FROM JIRAChangeGroup jiraChangeGroup WHERE ";
	private static final String _FINDER_COLUMN_JIRAUSERID_JIRAUSERID_1 = "jiraChangeGroup.jiraUserId IS NULL";
	private static final String _FINDER_COLUMN_JIRAUSERID_JIRAUSERID_2 = "jiraChangeGroup.jiraUserId = ?";
	private static final String _FINDER_COLUMN_JIRAUSERID_JIRAUSERID_3 = "(jiraChangeGroup.jiraUserId IS NULL OR jiraChangeGroup.jiraUserId = ?)";
	private static final String _FINDER_COLUMN_JIRAISSUEID_JIRAISSUEID_2 = "jiraChangeGroup.jiraIssueId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "jiraChangeGroup.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No JIRAChangeGroup exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No JIRAChangeGroup exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(JIRAChangeGroupPersistenceImpl.class);
	private static JIRAChangeGroup _nullJIRAChangeGroup = new JIRAChangeGroupImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<JIRAChangeGroup> toCacheModel() {
				return _nullJIRAChangeGroupCacheModel;
			}
		};

	private static CacheModel<JIRAChangeGroup> _nullJIRAChangeGroupCacheModel = new CacheModel<JIRAChangeGroup>() {
			public JIRAChangeGroup toEntityModel() {
				return _nullJIRAChangeGroup;
			}
		};
}