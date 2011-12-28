/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.testtransaction.service.persistence;

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
import com.liferay.portal.service.persistence.ClassNamePersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.testtransaction.NoSuchBarException;
import com.liferay.testtransaction.model.Bar;
import com.liferay.testtransaction.model.impl.BarImpl;
import com.liferay.testtransaction.model.impl.BarModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the bar service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BarPersistence
 * @see BarUtil
 * @generated
 */
public class BarPersistenceImpl extends BasePersistenceImpl<Bar>
	implements BarPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link BarUtil} to access the bar persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = BarImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TEXT = new FinderPath(BarModelImpl.ENTITY_CACHE_ENABLED,
			BarModelImpl.FINDER_CACHE_ENABLED, BarImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByText",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEXT = new FinderPath(BarModelImpl.ENTITY_CACHE_ENABLED,
			BarModelImpl.FINDER_CACHE_ENABLED, BarImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByText",
			new String[] { String.class.getName() },
			BarModelImpl.TEXT_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TEXT = new FinderPath(BarModelImpl.ENTITY_CACHE_ENABLED,
			BarModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByText",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(BarModelImpl.ENTITY_CACHE_ENABLED,
			BarModelImpl.FINDER_CACHE_ENABLED, BarImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(BarModelImpl.ENTITY_CACHE_ENABLED,
			BarModelImpl.FINDER_CACHE_ENABLED, BarImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(BarModelImpl.ENTITY_CACHE_ENABLED,
			BarModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the bar in the entity cache if it is enabled.
	 *
	 * @param bar the bar
	 */
	public void cacheResult(Bar bar) {
		EntityCacheUtil.putResult(BarModelImpl.ENTITY_CACHE_ENABLED,
			BarImpl.class, bar.getPrimaryKey(), bar);

		bar.resetOriginalValues();
	}

	/**
	 * Caches the bars in the entity cache if it is enabled.
	 *
	 * @param bars the bars
	 */
	public void cacheResult(List<Bar> bars) {
		for (Bar bar : bars) {
			if (EntityCacheUtil.getResult(BarModelImpl.ENTITY_CACHE_ENABLED,
						BarImpl.class, bar.getPrimaryKey()) == null) {
				cacheResult(bar);
			}
			else {
				bar.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all bars.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(BarImpl.class.getName());
		}

		EntityCacheUtil.clearCache(BarImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the bar.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Bar bar) {
		EntityCacheUtil.removeResult(BarModelImpl.ENTITY_CACHE_ENABLED,
			BarImpl.class, bar.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Bar> bars) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Bar bar : bars) {
			EntityCacheUtil.removeResult(BarModelImpl.ENTITY_CACHE_ENABLED,
				BarImpl.class, bar.getPrimaryKey());
		}
	}

	/**
	 * Creates a new bar with the primary key. Does not add the bar to the database.
	 *
	 * @param barId the primary key for the new bar
	 * @return the new bar
	 */
	public Bar create(long barId) {
		Bar bar = new BarImpl();

		bar.setNew(true);
		bar.setPrimaryKey(barId);

		return bar;
	}

	/**
	 * Removes the bar with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param barId the primary key of the bar
	 * @return the bar that was removed
	 * @throws com.liferay.testtransaction.NoSuchBarException if a bar with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Bar remove(long barId) throws NoSuchBarException, SystemException {
		return remove(Long.valueOf(barId));
	}

	/**
	 * Removes the bar with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the bar
	 * @return the bar that was removed
	 * @throws com.liferay.testtransaction.NoSuchBarException if a bar with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Bar remove(Serializable primaryKey)
		throws NoSuchBarException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Bar bar = (Bar)session.get(BarImpl.class, primaryKey);

			if (bar == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchBarException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(bar);
		}
		catch (NoSuchBarException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Bar removeImpl(Bar bar) throws SystemException {
		bar = toUnwrappedModel(bar);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, bar);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(bar);

		return bar;
	}

	@Override
	public Bar updateImpl(com.liferay.testtransaction.model.Bar bar,
		boolean merge) throws SystemException {
		bar = toUnwrappedModel(bar);

		boolean isNew = bar.isNew();

		BarModelImpl barModelImpl = (BarModelImpl)bar;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, bar, merge);

			bar.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !BarModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((barModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEXT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { barModelImpl.getOriginalText() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TEXT, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEXT,
					args);

				args = new Object[] { barModelImpl.getText() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TEXT, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEXT,
					args);
			}
		}

		EntityCacheUtil.putResult(BarModelImpl.ENTITY_CACHE_ENABLED,
			BarImpl.class, bar.getPrimaryKey(), bar);

		return bar;
	}

	protected Bar toUnwrappedModel(Bar bar) {
		if (bar instanceof BarImpl) {
			return bar;
		}

		BarImpl barImpl = new BarImpl();

		barImpl.setNew(bar.isNew());
		barImpl.setPrimaryKey(bar.getPrimaryKey());

		barImpl.setBarId(bar.getBarId());
		barImpl.setText(bar.getText());

		return barImpl;
	}

	/**
	 * Returns the bar with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the bar
	 * @return the bar
	 * @throws com.liferay.portal.NoSuchModelException if a bar with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Bar findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the bar with the primary key or throws a {@link com.liferay.testtransaction.NoSuchBarException} if it could not be found.
	 *
	 * @param barId the primary key of the bar
	 * @return the bar
	 * @throws com.liferay.testtransaction.NoSuchBarException if a bar with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Bar findByPrimaryKey(long barId)
		throws NoSuchBarException, SystemException {
		Bar bar = fetchByPrimaryKey(barId);

		if (bar == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + barId);
			}

			throw new NoSuchBarException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				barId);
		}

		return bar;
	}

	/**
	 * Returns the bar with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the bar
	 * @return the bar, or <code>null</code> if a bar with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Bar fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the bar with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param barId the primary key of the bar
	 * @return the bar, or <code>null</code> if a bar with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Bar fetchByPrimaryKey(long barId) throws SystemException {
		Bar bar = (Bar)EntityCacheUtil.getResult(BarModelImpl.ENTITY_CACHE_ENABLED,
				BarImpl.class, barId);

		if (bar == _nullBar) {
			return null;
		}

		if (bar == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				bar = (Bar)session.get(BarImpl.class, Long.valueOf(barId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (bar != null) {
					cacheResult(bar);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(BarModelImpl.ENTITY_CACHE_ENABLED,
						BarImpl.class, barId, _nullBar);
				}

				closeSession(session);
			}
		}

		return bar;
	}

	/**
	 * Returns all the bars where text = &#63;.
	 *
	 * @param text the text
	 * @return the matching bars
	 * @throws SystemException if a system exception occurred
	 */
	public List<Bar> findByText(String text) throws SystemException {
		return findByText(text, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the bars where text = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param text the text
	 * @param start the lower bound of the range of bars
	 * @param end the upper bound of the range of bars (not inclusive)
	 * @return the range of matching bars
	 * @throws SystemException if a system exception occurred
	 */
	public List<Bar> findByText(String text, int start, int end)
		throws SystemException {
		return findByText(text, start, end, null);
	}

	/**
	 * Returns an ordered range of all the bars where text = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param text the text
	 * @param start the lower bound of the range of bars
	 * @param end the upper bound of the range of bars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching bars
	 * @throws SystemException if a system exception occurred
	 */
	public List<Bar> findByText(String text, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEXT;
			finderArgs = new Object[] { text };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TEXT;
			finderArgs = new Object[] { text, start, end, orderByComparator };
		}

		List<Bar> list = (List<Bar>)FinderCacheUtil.getResult(finderPath,
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

			query.append(_SQL_SELECT_BAR_WHERE);

			if (text == null) {
				query.append(_FINDER_COLUMN_TEXT_TEXT_1);
			}
			else {
				if (text.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_TEXT_TEXT_3);
				}
				else {
					query.append(_FINDER_COLUMN_TEXT_TEXT_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(BarModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (text != null) {
					qPos.add(text);
				}

				list = (List<Bar>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first bar in the ordered set where text = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param text the text
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching bar
	 * @throws com.liferay.testtransaction.NoSuchBarException if a matching bar could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Bar findByText_First(String text, OrderByComparator orderByComparator)
		throws NoSuchBarException, SystemException {
		List<Bar> list = findByText(text, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("text=");
			msg.append(text);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchBarException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last bar in the ordered set where text = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param text the text
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching bar
	 * @throws com.liferay.testtransaction.NoSuchBarException if a matching bar could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Bar findByText_Last(String text, OrderByComparator orderByComparator)
		throws NoSuchBarException, SystemException {
		int count = countByText(text);

		List<Bar> list = findByText(text, count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("text=");
			msg.append(text);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchBarException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the bars before and after the current bar in the ordered set where text = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param barId the primary key of the current bar
	 * @param text the text
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next bar
	 * @throws com.liferay.testtransaction.NoSuchBarException if a bar with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Bar[] findByText_PrevAndNext(long barId, String text,
		OrderByComparator orderByComparator)
		throws NoSuchBarException, SystemException {
		Bar bar = findByPrimaryKey(barId);

		Session session = null;

		try {
			session = openSession();

			Bar[] array = new BarImpl[3];

			array[0] = getByText_PrevAndNext(session, bar, text,
					orderByComparator, true);

			array[1] = bar;

			array[2] = getByText_PrevAndNext(session, bar, text,
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

	protected Bar getByText_PrevAndNext(Session session, Bar bar, String text,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BAR_WHERE);

		if (text == null) {
			query.append(_FINDER_COLUMN_TEXT_TEXT_1);
		}
		else {
			if (text.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TEXT_TEXT_3);
			}
			else {
				query.append(_FINDER_COLUMN_TEXT_TEXT_2);
			}
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
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

			String[] orderByFields = orderByComparator.getOrderByFields();

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
			query.append(BarModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (text != null) {
			qPos.add(text);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(bar);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Bar> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the bars.
	 *
	 * @return the bars
	 * @throws SystemException if a system exception occurred
	 */
	public List<Bar> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the bars.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of bars
	 * @param end the upper bound of the range of bars (not inclusive)
	 * @return the range of bars
	 * @throws SystemException if a system exception occurred
	 */
	public List<Bar> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the bars.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of bars
	 * @param end the upper bound of the range of bars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of bars
	 * @throws SystemException if a system exception occurred
	 */
	public List<Bar> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<Bar> list = (List<Bar>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_BAR);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_BAR.concat(BarModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<Bar>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Bar>)QueryUtil.list(q, getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the bars where text = &#63; from the database.
	 *
	 * @param text the text
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByText(String text) throws SystemException {
		for (Bar bar : findByText(text)) {
			remove(bar);
		}
	}

	/**
	 * Removes all the bars from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (Bar bar : findAll()) {
			remove(bar);
		}
	}

	/**
	 * Returns the number of bars where text = &#63;.
	 *
	 * @param text the text
	 * @return the number of matching bars
	 * @throws SystemException if a system exception occurred
	 */
	public int countByText(String text) throws SystemException {
		Object[] finderArgs = new Object[] { text };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TEXT,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_BAR_WHERE);

			if (text == null) {
				query.append(_FINDER_COLUMN_TEXT_TEXT_1);
			}
			else {
				if (text.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_TEXT_TEXT_3);
				}
				else {
					query.append(_FINDER_COLUMN_TEXT_TEXT_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (text != null) {
					qPos.add(text);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TEXT,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of bars.
	 *
	 * @return the number of bars
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_BAR);

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
	 * Initializes the bar persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.testtransaction.model.Bar")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Bar>> listenersList = new ArrayList<ModelListener<Bar>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Bar>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(BarImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = BarPersistence.class)
	protected BarPersistence barPersistence;
	@BeanReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_BAR = "SELECT bar FROM Bar bar";
	private static final String _SQL_SELECT_BAR_WHERE = "SELECT bar FROM Bar bar WHERE ";
	private static final String _SQL_COUNT_BAR = "SELECT COUNT(bar) FROM Bar bar";
	private static final String _SQL_COUNT_BAR_WHERE = "SELECT COUNT(bar) FROM Bar bar WHERE ";
	private static final String _FINDER_COLUMN_TEXT_TEXT_1 = "bar.text IS NULL";
	private static final String _FINDER_COLUMN_TEXT_TEXT_2 = "bar.text = ?";
	private static final String _FINDER_COLUMN_TEXT_TEXT_3 = "(bar.text IS NULL OR bar.text = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "bar.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Bar exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Bar exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(BarPersistenceImpl.class);
	private static Bar _nullBar = new BarImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Bar> toCacheModel() {
				return _nullBarCacheModel;
			}
		};

	private static CacheModel<Bar> _nullBarCacheModel = new CacheModel<Bar>() {
			public Bar toEntityModel() {
				return _nullBar;
			}
		};
}