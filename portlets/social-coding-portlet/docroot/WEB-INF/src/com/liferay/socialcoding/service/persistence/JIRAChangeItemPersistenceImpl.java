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

import com.liferay.socialcoding.NoSuchJIRAChangeItemException;
import com.liferay.socialcoding.model.JIRAChangeItem;
import com.liferay.socialcoding.model.impl.JIRAChangeItemImpl;
import com.liferay.socialcoding.model.impl.JIRAChangeItemModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the j i r a change item service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAChangeItemPersistence
 * @see JIRAChangeItemUtil
 * @generated
 */
public class JIRAChangeItemPersistenceImpl extends BasePersistenceImpl<JIRAChangeItem>
	implements JIRAChangeItemPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link JIRAChangeItemUtil} to access the j i r a change item persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = JIRAChangeItemImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_JIRACHANGEGROUPID = new FinderPath(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeItemModelImpl.FINDER_CACHE_ENABLED,
			JIRAChangeItemImpl.class, FINDER_CLASS_NAME_LIST,
			"findByJiraChangeGroupId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_JIRACHANGEGROUPID = new FinderPath(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByJiraChangeGroupId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeItemModelImpl.FINDER_CACHE_ENABLED,
			JIRAChangeItemImpl.class, FINDER_CLASS_NAME_LIST, "findAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the j i r a change item in the entity cache if it is enabled.
	 *
	 * @param jiraChangeItem the j i r a change item
	 */
	public void cacheResult(JIRAChangeItem jiraChangeItem) {
		EntityCacheUtil.putResult(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeItemImpl.class, jiraChangeItem.getPrimaryKey(),
			jiraChangeItem);

		jiraChangeItem.resetOriginalValues();
	}

	/**
	 * Caches the j i r a change items in the entity cache if it is enabled.
	 *
	 * @param jiraChangeItems the j i r a change items
	 */
	public void cacheResult(List<JIRAChangeItem> jiraChangeItems) {
		for (JIRAChangeItem jiraChangeItem : jiraChangeItems) {
			if (EntityCacheUtil.getResult(
						JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
						JIRAChangeItemImpl.class, jiraChangeItem.getPrimaryKey()) == null) {
				cacheResult(jiraChangeItem);
			}
		}
	}

	/**
	 * Clears the cache for all j i r a change items.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(JIRAChangeItemImpl.class.getName());
		}

		EntityCacheUtil.clearCache(JIRAChangeItemImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the j i r a change item.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(JIRAChangeItem jiraChangeItem) {
		EntityCacheUtil.removeResult(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeItemImpl.class, jiraChangeItem.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FIND_ALL, FINDER_ARGS_EMPTY);
	}

	/**
	 * Creates a new j i r a change item with the primary key. Does not add the j i r a change item to the database.
	 *
	 * @param jiraChangeItemId the primary key for the new j i r a change item
	 * @return the new j i r a change item
	 */
	public JIRAChangeItem create(long jiraChangeItemId) {
		JIRAChangeItem jiraChangeItem = new JIRAChangeItemImpl();

		jiraChangeItem.setNew(true);
		jiraChangeItem.setPrimaryKey(jiraChangeItemId);

		return jiraChangeItem;
	}

	/**
	 * Removes the j i r a change item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the j i r a change item
	 * @return the j i r a change item that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a j i r a change item with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public JIRAChangeItem remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the j i r a change item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param jiraChangeItemId the primary key of the j i r a change item
	 * @return the j i r a change item that was removed
	 * @throws com.liferay.socialcoding.NoSuchJIRAChangeItemException if a j i r a change item with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAChangeItem remove(long jiraChangeItemId)
		throws NoSuchJIRAChangeItemException, SystemException {
		Session session = null;

		try {
			session = openSession();

			JIRAChangeItem jiraChangeItem = (JIRAChangeItem)session.get(JIRAChangeItemImpl.class,
					Long.valueOf(jiraChangeItemId));

			if (jiraChangeItem == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						jiraChangeItemId);
				}

				throw new NoSuchJIRAChangeItemException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					jiraChangeItemId);
			}

			return jiraChangeItemPersistence.remove(jiraChangeItem);
		}
		catch (NoSuchJIRAChangeItemException nsee) {
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
	 * Removes the j i r a change item from the database. Also notifies the appropriate model listeners.
	 *
	 * @param jiraChangeItem the j i r a change item
	 * @return the j i r a change item that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public JIRAChangeItem remove(JIRAChangeItem jiraChangeItem)
		throws SystemException {
		return super.remove(jiraChangeItem);
	}

	@Override
	protected JIRAChangeItem removeImpl(JIRAChangeItem jiraChangeItem)
		throws SystemException {
		jiraChangeItem = toUnwrappedModel(jiraChangeItem);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, jiraChangeItem);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeItemImpl.class, jiraChangeItem.getPrimaryKey());

		return jiraChangeItem;
	}

	@Override
	public JIRAChangeItem updateImpl(
		com.liferay.socialcoding.model.JIRAChangeItem jiraChangeItem,
		boolean merge) throws SystemException {
		jiraChangeItem = toUnwrappedModel(jiraChangeItem);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, jiraChangeItem, merge);

			jiraChangeItem.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeItemImpl.class, jiraChangeItem.getPrimaryKey(),
			jiraChangeItem);

		return jiraChangeItem;
	}

	protected JIRAChangeItem toUnwrappedModel(JIRAChangeItem jiraChangeItem) {
		if (jiraChangeItem instanceof JIRAChangeItemImpl) {
			return jiraChangeItem;
		}

		JIRAChangeItemImpl jiraChangeItemImpl = new JIRAChangeItemImpl();

		jiraChangeItemImpl.setNew(jiraChangeItem.isNew());
		jiraChangeItemImpl.setPrimaryKey(jiraChangeItem.getPrimaryKey());

		jiraChangeItemImpl.setJiraChangeItemId(jiraChangeItem.getJiraChangeItemId());
		jiraChangeItemImpl.setJiraChangeGroupId(jiraChangeItem.getJiraChangeGroupId());
		jiraChangeItemImpl.setField(jiraChangeItem.getField());
		jiraChangeItemImpl.setOldValue(jiraChangeItem.getOldValue());
		jiraChangeItemImpl.setOldString(jiraChangeItem.getOldString());
		jiraChangeItemImpl.setNewValue(jiraChangeItem.getNewValue());
		jiraChangeItemImpl.setNewString(jiraChangeItem.getNewString());

		return jiraChangeItemImpl;
	}

	/**
	 * Returns the j i r a change item with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the j i r a change item
	 * @return the j i r a change item
	 * @throws com.liferay.portal.NoSuchModelException if a j i r a change item with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public JIRAChangeItem findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the j i r a change item with the primary key or throws a {@link com.liferay.socialcoding.NoSuchJIRAChangeItemException} if it could not be found.
	 *
	 * @param jiraChangeItemId the primary key of the j i r a change item
	 * @return the j i r a change item
	 * @throws com.liferay.socialcoding.NoSuchJIRAChangeItemException if a j i r a change item with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAChangeItem findByPrimaryKey(long jiraChangeItemId)
		throws NoSuchJIRAChangeItemException, SystemException {
		JIRAChangeItem jiraChangeItem = fetchByPrimaryKey(jiraChangeItemId);

		if (jiraChangeItem == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + jiraChangeItemId);
			}

			throw new NoSuchJIRAChangeItemException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				jiraChangeItemId);
		}

		return jiraChangeItem;
	}

	/**
	 * Returns the j i r a change item with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the j i r a change item
	 * @return the j i r a change item, or <code>null</code> if a j i r a change item with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public JIRAChangeItem fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the j i r a change item with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param jiraChangeItemId the primary key of the j i r a change item
	 * @return the j i r a change item, or <code>null</code> if a j i r a change item with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAChangeItem fetchByPrimaryKey(long jiraChangeItemId)
		throws SystemException {
		JIRAChangeItem jiraChangeItem = (JIRAChangeItem)EntityCacheUtil.getResult(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
				JIRAChangeItemImpl.class, jiraChangeItemId);

		if (jiraChangeItem == _nullJIRAChangeItem) {
			return null;
		}

		if (jiraChangeItem == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				jiraChangeItem = (JIRAChangeItem)session.get(JIRAChangeItemImpl.class,
						Long.valueOf(jiraChangeItemId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (jiraChangeItem != null) {
					cacheResult(jiraChangeItem);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
						JIRAChangeItemImpl.class, jiraChangeItemId,
						_nullJIRAChangeItem);
				}

				closeSession(session);
			}
		}

		return jiraChangeItem;
	}

	/**
	 * Returns all the j i r a change items where jiraChangeGroupId = &#63;.
	 *
	 * @param jiraChangeGroupId the jira change group ID
	 * @return the matching j i r a change items
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAChangeItem> findByJiraChangeGroupId(long jiraChangeGroupId)
		throws SystemException {
		return findByJiraChangeGroupId(jiraChangeGroupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j i r a change items where jiraChangeGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jiraChangeGroupId the jira change group ID
	 * @param start the lower bound of the range of j i r a change items
	 * @param end the upper bound of the range of j i r a change items (not inclusive)
	 * @return the range of matching j i r a change items
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAChangeItem> findByJiraChangeGroupId(
		long jiraChangeGroupId, int start, int end) throws SystemException {
		return findByJiraChangeGroupId(jiraChangeGroupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a change items where jiraChangeGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jiraChangeGroupId the jira change group ID
	 * @param start the lower bound of the range of j i r a change items
	 * @param end the upper bound of the range of j i r a change items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching j i r a change items
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAChangeItem> findByJiraChangeGroupId(
		long jiraChangeGroupId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				jiraChangeGroupId,
				
				start, end, orderByComparator
			};

		List<JIRAChangeItem> list = (List<JIRAChangeItem>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_JIRACHANGEGROUPID,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_JIRACHANGEITEM_WHERE);

			query.append(_FINDER_COLUMN_JIRACHANGEGROUPID_JIRACHANGEGROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(jiraChangeGroupId);

				list = (List<JIRAChangeItem>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_JIRACHANGEGROUPID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_JIRACHANGEGROUPID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first j i r a change item in the ordered set where jiraChangeGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jiraChangeGroupId the jira change group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a change item
	 * @throws com.liferay.socialcoding.NoSuchJIRAChangeItemException if a matching j i r a change item could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAChangeItem findByJiraChangeGroupId_First(
		long jiraChangeGroupId, OrderByComparator orderByComparator)
		throws NoSuchJIRAChangeItemException, SystemException {
		List<JIRAChangeItem> list = findByJiraChangeGroupId(jiraChangeGroupId,
				0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("jiraChangeGroupId=");
			msg.append(jiraChangeGroupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAChangeItemException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last j i r a change item in the ordered set where jiraChangeGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jiraChangeGroupId the jira change group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a change item
	 * @throws com.liferay.socialcoding.NoSuchJIRAChangeItemException if a matching j i r a change item could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAChangeItem findByJiraChangeGroupId_Last(long jiraChangeGroupId,
		OrderByComparator orderByComparator)
		throws NoSuchJIRAChangeItemException, SystemException {
		int count = countByJiraChangeGroupId(jiraChangeGroupId);

		List<JIRAChangeItem> list = findByJiraChangeGroupId(jiraChangeGroupId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("jiraChangeGroupId=");
			msg.append(jiraChangeGroupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAChangeItemException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the j i r a change items before and after the current j i r a change item in the ordered set where jiraChangeGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jiraChangeItemId the primary key of the current j i r a change item
	 * @param jiraChangeGroupId the jira change group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next j i r a change item
	 * @throws com.liferay.socialcoding.NoSuchJIRAChangeItemException if a j i r a change item with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAChangeItem[] findByJiraChangeGroupId_PrevAndNext(
		long jiraChangeItemId, long jiraChangeGroupId,
		OrderByComparator orderByComparator)
		throws NoSuchJIRAChangeItemException, SystemException {
		JIRAChangeItem jiraChangeItem = findByPrimaryKey(jiraChangeItemId);

		Session session = null;

		try {
			session = openSession();

			JIRAChangeItem[] array = new JIRAChangeItemImpl[3];

			array[0] = getByJiraChangeGroupId_PrevAndNext(session,
					jiraChangeItem, jiraChangeGroupId, orderByComparator, true);

			array[1] = jiraChangeItem;

			array[2] = getByJiraChangeGroupId_PrevAndNext(session,
					jiraChangeItem, jiraChangeGroupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected JIRAChangeItem getByJiraChangeGroupId_PrevAndNext(
		Session session, JIRAChangeItem jiraChangeItem, long jiraChangeGroupId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JIRACHANGEITEM_WHERE);

		query.append(_FINDER_COLUMN_JIRACHANGEGROUPID_JIRACHANGEGROUPID_2);

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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(jiraChangeGroupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(jiraChangeItem);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JIRAChangeItem> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the j i r a change items.
	 *
	 * @return the j i r a change items
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAChangeItem> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j i r a change items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of j i r a change items
	 * @param end the upper bound of the range of j i r a change items (not inclusive)
	 * @return the range of j i r a change items
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAChangeItem> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a change items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of j i r a change items
	 * @param end the upper bound of the range of j i r a change items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of j i r a change items
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAChangeItem> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		List<JIRAChangeItem> list = (List<JIRAChangeItem>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_JIRACHANGEITEM);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_JIRACHANGEITEM;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<JIRAChangeItem>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<JIRAChangeItem>)QueryUtil.list(q,
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
	 * Removes all the j i r a change items where jiraChangeGroupId = &#63; from the database.
	 *
	 * @param jiraChangeGroupId the jira change group ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByJiraChangeGroupId(long jiraChangeGroupId)
		throws SystemException {
		for (JIRAChangeItem jiraChangeItem : findByJiraChangeGroupId(
				jiraChangeGroupId)) {
			jiraChangeItemPersistence.remove(jiraChangeItem);
		}
	}

	/**
	 * Removes all the j i r a change items from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (JIRAChangeItem jiraChangeItem : findAll()) {
			jiraChangeItemPersistence.remove(jiraChangeItem);
		}
	}

	/**
	 * Returns the number of j i r a change items where jiraChangeGroupId = &#63;.
	 *
	 * @param jiraChangeGroupId the jira change group ID
	 * @return the number of matching j i r a change items
	 * @throws SystemException if a system exception occurred
	 */
	public int countByJiraChangeGroupId(long jiraChangeGroupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { jiraChangeGroupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_JIRACHANGEGROUPID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_JIRACHANGEITEM_WHERE);

			query.append(_FINDER_COLUMN_JIRACHANGEGROUPID_JIRACHANGEGROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(jiraChangeGroupId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_JIRACHANGEGROUPID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of j i r a change items.
	 *
	 * @return the number of j i r a change items
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_JIRACHANGEITEM);

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
	 * Initializes the j i r a change item persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.socialcoding.model.JIRAChangeItem")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<JIRAChangeItem>> listenersList = new ArrayList<ModelListener<JIRAChangeItem>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<JIRAChangeItem>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(JIRAChangeItemImpl.class.getName());
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
	private static final String _SQL_SELECT_JIRACHANGEITEM = "SELECT jiraChangeItem FROM JIRAChangeItem jiraChangeItem";
	private static final String _SQL_SELECT_JIRACHANGEITEM_WHERE = "SELECT jiraChangeItem FROM JIRAChangeItem jiraChangeItem WHERE ";
	private static final String _SQL_COUNT_JIRACHANGEITEM = "SELECT COUNT(jiraChangeItem) FROM JIRAChangeItem jiraChangeItem";
	private static final String _SQL_COUNT_JIRACHANGEITEM_WHERE = "SELECT COUNT(jiraChangeItem) FROM JIRAChangeItem jiraChangeItem WHERE ";
	private static final String _FINDER_COLUMN_JIRACHANGEGROUPID_JIRACHANGEGROUPID_2 =
		"jiraChangeItem.jiraChangeGroupId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "jiraChangeItem.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No JIRAChangeItem exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No JIRAChangeItem exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(JIRAChangeItemPersistenceImpl.class);
	private static JIRAChangeItem _nullJIRAChangeItem = new JIRAChangeItemImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<JIRAChangeItem> toCacheModel() {
				return _nullJIRAChangeItemCacheModel;
			}
		};

	private static CacheModel<JIRAChangeItem> _nullJIRAChangeItemCacheModel = new CacheModel<JIRAChangeItem>() {
			public JIRAChangeItem toEntityModel() {
				return _nullJIRAChangeItem;
			}
		};
}