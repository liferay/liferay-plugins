/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.testtransaction.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.testtransaction.NoSuchBarException;
import com.liferay.testtransaction.model.Bar;
import com.liferay.testtransaction.model.impl.BarImpl;
import com.liferay.testtransaction.model.impl.BarModelImpl;
import com.liferay.testtransaction.service.persistence.BarPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the bar service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BarPersistence
 * @see com.liferay.testtransaction.service.persistence.BarUtil
 * @generated
 */
@ProviderType
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
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(BarModelImpl.ENTITY_CACHE_ENABLED,
			BarModelImpl.FINDER_CACHE_ENABLED, BarImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(BarModelImpl.ENTITY_CACHE_ENABLED,
			BarModelImpl.FINDER_CACHE_ENABLED, BarImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(BarModelImpl.ENTITY_CACHE_ENABLED,
			BarModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TEXT = new FinderPath(BarModelImpl.ENTITY_CACHE_ENABLED,
			BarModelImpl.FINDER_CACHE_ENABLED, BarImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByText",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
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

	/**
	 * Returns all the bars where text = &#63;.
	 *
	 * @param text the text
	 * @return the matching bars
	 */
	@Override
	public List<Bar> findByText(String text) {
		return findByText(text, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the bars where text = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param text the text
	 * @param start the lower bound of the range of bars
	 * @param end the upper bound of the range of bars (not inclusive)
	 * @return the range of matching bars
	 */
	@Override
	public List<Bar> findByText(String text, int start, int end) {
		return findByText(text, start, end, null);
	}

	/**
	 * Returns an ordered range of all the bars where text = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param text the text
	 * @param start the lower bound of the range of bars
	 * @param end the upper bound of the range of bars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching bars
	 */
	@Override
	public List<Bar> findByText(String text, int start, int end,
		OrderByComparator<Bar> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEXT;
			finderArgs = new Object[] { text };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TEXT;
			finderArgs = new Object[] { text, start, end, orderByComparator };
		}

		List<Bar> list = (List<Bar>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Bar bar : list) {
				if (!Validator.equals(text, bar.getText())) {
					list = null;

					break;
				}
			}
		}

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

			boolean bindText = false;

			if (text == null) {
				query.append(_FINDER_COLUMN_TEXT_TEXT_1);
			}
			else if (text.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TEXT_TEXT_3);
			}
			else {
				bindText = true;

				query.append(_FINDER_COLUMN_TEXT_TEXT_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BarModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindText) {
					qPos.add(text);
				}

				if (!pagination) {
					list = (List<Bar>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Bar>)QueryUtil.list(q, getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first bar in the ordered set where text = &#63;.
	 *
	 * @param text the text
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching bar
	 * @throws NoSuchBarException if a matching bar could not be found
	 */
	@Override
	public Bar findByText_First(String text,
		OrderByComparator<Bar> orderByComparator) throws NoSuchBarException {
		Bar bar = fetchByText_First(text, orderByComparator);

		if (bar != null) {
			return bar;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("text=");
		msg.append(text);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBarException(msg.toString());
	}

	/**
	 * Returns the first bar in the ordered set where text = &#63;.
	 *
	 * @param text the text
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching bar, or <code>null</code> if a matching bar could not be found
	 */
	@Override
	public Bar fetchByText_First(String text,
		OrderByComparator<Bar> orderByComparator) {
		List<Bar> list = findByText(text, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last bar in the ordered set where text = &#63;.
	 *
	 * @param text the text
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching bar
	 * @throws NoSuchBarException if a matching bar could not be found
	 */
	@Override
	public Bar findByText_Last(String text,
		OrderByComparator<Bar> orderByComparator) throws NoSuchBarException {
		Bar bar = fetchByText_Last(text, orderByComparator);

		if (bar != null) {
			return bar;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("text=");
		msg.append(text);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBarException(msg.toString());
	}

	/**
	 * Returns the last bar in the ordered set where text = &#63;.
	 *
	 * @param text the text
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching bar, or <code>null</code> if a matching bar could not be found
	 */
	@Override
	public Bar fetchByText_Last(String text,
		OrderByComparator<Bar> orderByComparator) {
		int count = countByText(text);

		if (count == 0) {
			return null;
		}

		List<Bar> list = findByText(text, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the bars before and after the current bar in the ordered set where text = &#63;.
	 *
	 * @param barId the primary key of the current bar
	 * @param text the text
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next bar
	 * @throws NoSuchBarException if a bar with the primary key could not be found
	 */
	@Override
	public Bar[] findByText_PrevAndNext(long barId, String text,
		OrderByComparator<Bar> orderByComparator) throws NoSuchBarException {
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
		OrderByComparator<Bar> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BAR_WHERE);

		boolean bindText = false;

		if (text == null) {
			query.append(_FINDER_COLUMN_TEXT_TEXT_1);
		}
		else if (text.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_TEXT_TEXT_3);
		}
		else {
			bindText = true;

			query.append(_FINDER_COLUMN_TEXT_TEXT_2);
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

		if (bindText) {
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
	 * Removes all the bars where text = &#63; from the database.
	 *
	 * @param text the text
	 */
	@Override
	public void removeByText(String text) {
		for (Bar bar : findByText(text, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(bar);
		}
	}

	/**
	 * Returns the number of bars where text = &#63;.
	 *
	 * @param text the text
	 * @return the number of matching bars
	 */
	@Override
	public int countByText(String text) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TEXT;

		Object[] finderArgs = new Object[] { text };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_BAR_WHERE);

			boolean bindText = false;

			if (text == null) {
				query.append(_FINDER_COLUMN_TEXT_TEXT_1);
			}
			else if (text.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TEXT_TEXT_3);
			}
			else {
				bindText = true;

				query.append(_FINDER_COLUMN_TEXT_TEXT_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindText) {
					qPos.add(text);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_TEXT_TEXT_1 = "bar.text IS NULL";
	private static final String _FINDER_COLUMN_TEXT_TEXT_2 = "bar.text = ?";
	private static final String _FINDER_COLUMN_TEXT_TEXT_3 = "(bar.text IS NULL OR bar.text = '')";

	public BarPersistenceImpl() {
		setModelClass(Bar.class);
	}

	/**
	 * Caches the bar in the entity cache if it is enabled.
	 *
	 * @param bar the bar
	 */
	@Override
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
	@Override
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
		EntityCacheUtil.clearCache(BarImpl.class);

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
	@Override
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
	 * @throws NoSuchBarException if a bar with the primary key could not be found
	 */
	@Override
	public Bar remove(long barId) throws NoSuchBarException {
		return remove((Serializable)barId);
	}

	/**
	 * Removes the bar with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the bar
	 * @return the bar that was removed
	 * @throws NoSuchBarException if a bar with the primary key could not be found
	 */
	@Override
	public Bar remove(Serializable primaryKey) throws NoSuchBarException {
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
	protected Bar removeImpl(Bar bar) {
		bar = toUnwrappedModel(bar);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(bar)) {
				bar = (Bar)session.get(BarImpl.class, bar.getPrimaryKeyObj());
			}

			if (bar != null) {
				session.delete(bar);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (bar != null) {
			clearCache(bar);
		}

		return bar;
	}

	@Override
	public Bar updateImpl(Bar bar) {
		bar = toUnwrappedModel(bar);

		boolean isNew = bar.isNew();

		BarModelImpl barModelImpl = (BarModelImpl)bar;

		Session session = null;

		try {
			session = openSession();

			if (bar.isNew()) {
				session.save(bar);

				bar.setNew(false);
			}
			else {
				session.merge(bar);
			}
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
			BarImpl.class, bar.getPrimaryKey(), bar, false);

		bar.resetOriginalValues();

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
	 * @throws NoSuchBarException if a bar with the primary key could not be found
	 */
	@Override
	public Bar findByPrimaryKey(Serializable primaryKey)
		throws NoSuchBarException {
		Bar bar = fetchByPrimaryKey(primaryKey);

		if (bar == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchBarException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return bar;
	}

	/**
	 * Returns the bar with the primary key or throws a {@link NoSuchBarException} if it could not be found.
	 *
	 * @param barId the primary key of the bar
	 * @return the bar
	 * @throws NoSuchBarException if a bar with the primary key could not be found
	 */
	@Override
	public Bar findByPrimaryKey(long barId) throws NoSuchBarException {
		return findByPrimaryKey((Serializable)barId);
	}

	/**
	 * Returns the bar with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the bar
	 * @return the bar, or <code>null</code> if a bar with the primary key could not be found
	 */
	@Override
	public Bar fetchByPrimaryKey(Serializable primaryKey) {
		Bar bar = (Bar)EntityCacheUtil.getResult(BarModelImpl.ENTITY_CACHE_ENABLED,
				BarImpl.class, primaryKey);

		if (bar == _nullBar) {
			return null;
		}

		if (bar == null) {
			Session session = null;

			try {
				session = openSession();

				bar = (Bar)session.get(BarImpl.class, primaryKey);

				if (bar != null) {
					cacheResult(bar);
				}
				else {
					EntityCacheUtil.putResult(BarModelImpl.ENTITY_CACHE_ENABLED,
						BarImpl.class, primaryKey, _nullBar);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(BarModelImpl.ENTITY_CACHE_ENABLED,
					BarImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return bar;
	}

	/**
	 * Returns the bar with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param barId the primary key of the bar
	 * @return the bar, or <code>null</code> if a bar with the primary key could not be found
	 */
	@Override
	public Bar fetchByPrimaryKey(long barId) {
		return fetchByPrimaryKey((Serializable)barId);
	}

	@Override
	public Map<Serializable, Bar> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Bar> map = new HashMap<Serializable, Bar>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Bar bar = fetchByPrimaryKey(primaryKey);

			if (bar != null) {
				map.put(primaryKey, bar);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Bar bar = (Bar)EntityCacheUtil.getResult(BarModelImpl.ENTITY_CACHE_ENABLED,
					BarImpl.class, primaryKey);

			if (bar == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, bar);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_BAR_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append(String.valueOf(primaryKey));

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (Bar bar : (List<Bar>)q.list()) {
				map.put(bar.getPrimaryKeyObj(), bar);

				cacheResult(bar);

				uncachedPrimaryKeys.remove(bar.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(BarModelImpl.ENTITY_CACHE_ENABLED,
					BarImpl.class, primaryKey, _nullBar);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the bars.
	 *
	 * @return the bars
	 */
	@Override
	public List<Bar> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the bars.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of bars
	 * @param end the upper bound of the range of bars (not inclusive)
	 * @return the range of bars
	 */
	@Override
	public List<Bar> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the bars.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of bars
	 * @param end the upper bound of the range of bars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of bars
	 */
	@Override
	public List<Bar> findAll(int start, int end,
		OrderByComparator<Bar> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
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
				sql = _SQL_SELECT_BAR;

				if (pagination) {
					sql = sql.concat(BarModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Bar>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Bar>)QueryUtil.list(q, getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the bars from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Bar bar : findAll()) {
			remove(bar);
		}
	}

	/**
	 * Returns the number of bars.
	 *
	 * @return the number of bars
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_BAR);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	/**
	 * Initializes the bar persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(BarImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_BAR = "SELECT bar FROM Bar bar";
	private static final String _SQL_SELECT_BAR_WHERE_PKS_IN = "SELECT bar FROM Bar bar WHERE barId IN (";
	private static final String _SQL_SELECT_BAR_WHERE = "SELECT bar FROM Bar bar WHERE ";
	private static final String _SQL_COUNT_BAR = "SELECT COUNT(bar) FROM Bar bar";
	private static final String _SQL_COUNT_BAR_WHERE = "SELECT COUNT(bar) FROM Bar bar WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "bar.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Bar exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Bar exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(BarPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"text"
			});
	private static final Bar _nullBar = new BarImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Bar> toCacheModel() {
				return _nullBarCacheModel;
			}
		};

	private static final CacheModel<Bar> _nullBarCacheModel = new CacheModel<Bar>() {
			@Override
			public Bar toEntityModel() {
				return _nullBar;
			}
		};
}