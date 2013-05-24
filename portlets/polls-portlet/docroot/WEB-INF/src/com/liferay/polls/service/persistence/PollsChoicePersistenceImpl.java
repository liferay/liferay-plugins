/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.polls.service.persistence;

import com.liferay.polls.NoSuchChoiceException;
import com.liferay.polls.model.PollsChoice;
import com.liferay.polls.model.impl.PollsChoiceImpl;
import com.liferay.polls.model.impl.PollsChoiceModelImpl;

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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the polls choice service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Juan Fern√°ndez
 * @see PollsChoicePersistence
 * @see PollsChoiceUtil
 * @generated
 */
public class PollsChoicePersistenceImpl extends BasePersistenceImpl<PollsChoice>
	implements PollsChoicePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PollsChoiceUtil} to access the polls choice persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PollsChoiceImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PollsChoiceModelImpl.ENTITY_CACHE_ENABLED,
			PollsChoiceModelImpl.FINDER_CACHE_ENABLED, PollsChoiceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PollsChoiceModelImpl.ENTITY_CACHE_ENABLED,
			PollsChoiceModelImpl.FINDER_CACHE_ENABLED, PollsChoiceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PollsChoiceModelImpl.ENTITY_CACHE_ENABLED,
			PollsChoiceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(PollsChoiceModelImpl.ENTITY_CACHE_ENABLED,
			PollsChoiceModelImpl.FINDER_CACHE_ENABLED, PollsChoiceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(PollsChoiceModelImpl.ENTITY_CACHE_ENABLED,
			PollsChoiceModelImpl.FINDER_CACHE_ENABLED, PollsChoiceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			PollsChoiceModelImpl.UUID_COLUMN_BITMASK |
			PollsChoiceModelImpl.POLLSQUESTIONID_COLUMN_BITMASK |
			PollsChoiceModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(PollsChoiceModelImpl.ENTITY_CACHE_ENABLED,
			PollsChoiceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the polls choices where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching polls choices
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PollsChoice> findByUuid(String uuid) throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the polls choices where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.polls.model.impl.PollsChoiceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of polls choices
	 * @param end the upper bound of the range of polls choices (not inclusive)
	 * @return the range of matching polls choices
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PollsChoice> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the polls choices where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.polls.model.impl.PollsChoiceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of polls choices
	 * @param end the upper bound of the range of polls choices (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching polls choices
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PollsChoice> findByUuid(String uuid, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<PollsChoice> list = (List<PollsChoice>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PollsChoice pollsChoice : list) {
				if (!Validator.equals(uuid, pollsChoice.getUuid())) {
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

			query.append(_SQL_SELECT_POLLSCHOICE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PollsChoiceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<PollsChoice>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<PollsChoice>(list);
				}
				else {
					list = (List<PollsChoice>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first polls choice in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching polls choice
	 * @throws com.liferay.polls.NoSuchChoiceException if a matching polls choice could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsChoice findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchChoiceException, SystemException {
		PollsChoice pollsChoice = fetchByUuid_First(uuid, orderByComparator);

		if (pollsChoice != null) {
			return pollsChoice;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChoiceException(msg.toString());
	}

	/**
	 * Returns the first polls choice in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching polls choice, or <code>null</code> if a matching polls choice could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsChoice fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<PollsChoice> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last polls choice in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching polls choice
	 * @throws com.liferay.polls.NoSuchChoiceException if a matching polls choice could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsChoice findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchChoiceException, SystemException {
		PollsChoice pollsChoice = fetchByUuid_Last(uuid, orderByComparator);

		if (pollsChoice != null) {
			return pollsChoice;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChoiceException(msg.toString());
	}

	/**
	 * Returns the last polls choice in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching polls choice, or <code>null</code> if a matching polls choice could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsChoice fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		List<PollsChoice> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the polls choices before and after the current polls choice in the ordered set where uuid = &#63;.
	 *
	 * @param pollsChoiceId the primary key of the current polls choice
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next polls choice
	 * @throws com.liferay.polls.NoSuchChoiceException if a polls choice with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsChoice[] findByUuid_PrevAndNext(long pollsChoiceId,
		String uuid, OrderByComparator orderByComparator)
		throws NoSuchChoiceException, SystemException {
		PollsChoice pollsChoice = findByPrimaryKey(pollsChoiceId);

		Session session = null;

		try {
			session = openSession();

			PollsChoice[] array = new PollsChoiceImpl[3];

			array[0] = getByUuid_PrevAndNext(session, pollsChoice, uuid,
					orderByComparator, true);

			array[1] = pollsChoice;

			array[2] = getByUuid_PrevAndNext(session, pollsChoice, uuid,
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

	protected PollsChoice getByUuid_PrevAndNext(Session session,
		PollsChoice pollsChoice, String uuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_POLLSCHOICE_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
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
			query.append(PollsChoiceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(pollsChoice);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PollsChoice> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the polls choices where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid(String uuid) throws SystemException {
		for (PollsChoice pollsChoice : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(pollsChoice);
		}
	}

	/**
	 * Returns the number of polls choices where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching polls choices
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByUuid(String uuid) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_POLLSCHOICE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "pollsChoice.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "pollsChoice.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(pollsChoice.uuid IS NULL OR pollsChoice.uuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_POLLSQUESTIONID =
		new FinderPath(PollsChoiceModelImpl.ENTITY_CACHE_ENABLED,
			PollsChoiceModelImpl.FINDER_CACHE_ENABLED, PollsChoiceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPollsQuestionId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POLLSQUESTIONID =
		new FinderPath(PollsChoiceModelImpl.ENTITY_CACHE_ENABLED,
			PollsChoiceModelImpl.FINDER_CACHE_ENABLED, PollsChoiceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPollsQuestionId",
			new String[] { Long.class.getName() },
			PollsChoiceModelImpl.POLLSQUESTIONID_COLUMN_BITMASK |
			PollsChoiceModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_POLLSQUESTIONID = new FinderPath(PollsChoiceModelImpl.ENTITY_CACHE_ENABLED,
			PollsChoiceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByPollsQuestionId", new String[] { Long.class.getName() });

	/**
	 * Returns all the polls choices where pollsQuestionId = &#63;.
	 *
	 * @param pollsQuestionId the polls question ID
	 * @return the matching polls choices
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PollsChoice> findByPollsQuestionId(long pollsQuestionId)
		throws SystemException {
		return findByPollsQuestionId(pollsQuestionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the polls choices where pollsQuestionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.polls.model.impl.PollsChoiceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pollsQuestionId the polls question ID
	 * @param start the lower bound of the range of polls choices
	 * @param end the upper bound of the range of polls choices (not inclusive)
	 * @return the range of matching polls choices
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PollsChoice> findByPollsQuestionId(long pollsQuestionId,
		int start, int end) throws SystemException {
		return findByPollsQuestionId(pollsQuestionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the polls choices where pollsQuestionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.polls.model.impl.PollsChoiceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pollsQuestionId the polls question ID
	 * @param start the lower bound of the range of polls choices
	 * @param end the upper bound of the range of polls choices (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching polls choices
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PollsChoice> findByPollsQuestionId(long pollsQuestionId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POLLSQUESTIONID;
			finderArgs = new Object[] { pollsQuestionId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_POLLSQUESTIONID;
			finderArgs = new Object[] {
					pollsQuestionId,
					
					start, end, orderByComparator
				};
		}

		List<PollsChoice> list = (List<PollsChoice>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PollsChoice pollsChoice : list) {
				if ((pollsQuestionId != pollsChoice.getPollsQuestionId())) {
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

			query.append(_SQL_SELECT_POLLSCHOICE_WHERE);

			query.append(_FINDER_COLUMN_POLLSQUESTIONID_POLLSQUESTIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PollsChoiceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pollsQuestionId);

				if (!pagination) {
					list = (List<PollsChoice>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<PollsChoice>(list);
				}
				else {
					list = (List<PollsChoice>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first polls choice in the ordered set where pollsQuestionId = &#63;.
	 *
	 * @param pollsQuestionId the polls question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching polls choice
	 * @throws com.liferay.polls.NoSuchChoiceException if a matching polls choice could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsChoice findByPollsQuestionId_First(long pollsQuestionId,
		OrderByComparator orderByComparator)
		throws NoSuchChoiceException, SystemException {
		PollsChoice pollsChoice = fetchByPollsQuestionId_First(pollsQuestionId,
				orderByComparator);

		if (pollsChoice != null) {
			return pollsChoice;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("pollsQuestionId=");
		msg.append(pollsQuestionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChoiceException(msg.toString());
	}

	/**
	 * Returns the first polls choice in the ordered set where pollsQuestionId = &#63;.
	 *
	 * @param pollsQuestionId the polls question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching polls choice, or <code>null</code> if a matching polls choice could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsChoice fetchByPollsQuestionId_First(long pollsQuestionId,
		OrderByComparator orderByComparator) throws SystemException {
		List<PollsChoice> list = findByPollsQuestionId(pollsQuestionId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last polls choice in the ordered set where pollsQuestionId = &#63;.
	 *
	 * @param pollsQuestionId the polls question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching polls choice
	 * @throws com.liferay.polls.NoSuchChoiceException if a matching polls choice could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsChoice findByPollsQuestionId_Last(long pollsQuestionId,
		OrderByComparator orderByComparator)
		throws NoSuchChoiceException, SystemException {
		PollsChoice pollsChoice = fetchByPollsQuestionId_Last(pollsQuestionId,
				orderByComparator);

		if (pollsChoice != null) {
			return pollsChoice;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("pollsQuestionId=");
		msg.append(pollsQuestionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChoiceException(msg.toString());
	}

	/**
	 * Returns the last polls choice in the ordered set where pollsQuestionId = &#63;.
	 *
	 * @param pollsQuestionId the polls question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching polls choice, or <code>null</code> if a matching polls choice could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsChoice fetchByPollsQuestionId_Last(long pollsQuestionId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByPollsQuestionId(pollsQuestionId);

		List<PollsChoice> list = findByPollsQuestionId(pollsQuestionId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the polls choices before and after the current polls choice in the ordered set where pollsQuestionId = &#63;.
	 *
	 * @param pollsChoiceId the primary key of the current polls choice
	 * @param pollsQuestionId the polls question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next polls choice
	 * @throws com.liferay.polls.NoSuchChoiceException if a polls choice with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsChoice[] findByPollsQuestionId_PrevAndNext(long pollsChoiceId,
		long pollsQuestionId, OrderByComparator orderByComparator)
		throws NoSuchChoiceException, SystemException {
		PollsChoice pollsChoice = findByPrimaryKey(pollsChoiceId);

		Session session = null;

		try {
			session = openSession();

			PollsChoice[] array = new PollsChoiceImpl[3];

			array[0] = getByPollsQuestionId_PrevAndNext(session, pollsChoice,
					pollsQuestionId, orderByComparator, true);

			array[1] = pollsChoice;

			array[2] = getByPollsQuestionId_PrevAndNext(session, pollsChoice,
					pollsQuestionId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PollsChoice getByPollsQuestionId_PrevAndNext(Session session,
		PollsChoice pollsChoice, long pollsQuestionId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_POLLSCHOICE_WHERE);

		query.append(_FINDER_COLUMN_POLLSQUESTIONID_POLLSQUESTIONID_2);

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
			query.append(PollsChoiceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(pollsQuestionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(pollsChoice);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PollsChoice> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the polls choices where pollsQuestionId = &#63; from the database.
	 *
	 * @param pollsQuestionId the polls question ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByPollsQuestionId(long pollsQuestionId)
		throws SystemException {
		for (PollsChoice pollsChoice : findByPollsQuestionId(pollsQuestionId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(pollsChoice);
		}
	}

	/**
	 * Returns the number of polls choices where pollsQuestionId = &#63;.
	 *
	 * @param pollsQuestionId the polls question ID
	 * @return the number of matching polls choices
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByPollsQuestionId(long pollsQuestionId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_POLLSQUESTIONID;

		Object[] finderArgs = new Object[] { pollsQuestionId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_POLLSCHOICE_WHERE);

			query.append(_FINDER_COLUMN_POLLSQUESTIONID_POLLSQUESTIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pollsQuestionId);

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

	private static final String _FINDER_COLUMN_POLLSQUESTIONID_POLLSQUESTIONID_2 =
		"pollsChoice.pollsQuestionId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_PQI_N = new FinderPath(PollsChoiceModelImpl.ENTITY_CACHE_ENABLED,
			PollsChoiceModelImpl.FINDER_CACHE_ENABLED, PollsChoiceImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByPQI_N",
			new String[] { Long.class.getName(), String.class.getName() },
			PollsChoiceModelImpl.POLLSQUESTIONID_COLUMN_BITMASK |
			PollsChoiceModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PQI_N = new FinderPath(PollsChoiceModelImpl.ENTITY_CACHE_ENABLED,
			PollsChoiceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPQI_N",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the polls choice where pollsQuestionId = &#63; and name = &#63; or throws a {@link com.liferay.polls.NoSuchChoiceException} if it could not be found.
	 *
	 * @param pollsQuestionId the polls question ID
	 * @param name the name
	 * @return the matching polls choice
	 * @throws com.liferay.polls.NoSuchChoiceException if a matching polls choice could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsChoice findByPQI_N(long pollsQuestionId, String name)
		throws NoSuchChoiceException, SystemException {
		PollsChoice pollsChoice = fetchByPQI_N(pollsQuestionId, name);

		if (pollsChoice == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("pollsQuestionId=");
			msg.append(pollsQuestionId);

			msg.append(", name=");
			msg.append(name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchChoiceException(msg.toString());
		}

		return pollsChoice;
	}

	/**
	 * Returns the polls choice where pollsQuestionId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param pollsQuestionId the polls question ID
	 * @param name the name
	 * @return the matching polls choice, or <code>null</code> if a matching polls choice could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsChoice fetchByPQI_N(long pollsQuestionId, String name)
		throws SystemException {
		return fetchByPQI_N(pollsQuestionId, name, true);
	}

	/**
	 * Returns the polls choice where pollsQuestionId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param pollsQuestionId the polls question ID
	 * @param name the name
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching polls choice, or <code>null</code> if a matching polls choice could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsChoice fetchByPQI_N(long pollsQuestionId, String name,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { pollsQuestionId, name };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_PQI_N,
					finderArgs, this);
		}

		if (result instanceof PollsChoice) {
			PollsChoice pollsChoice = (PollsChoice)result;

			if ((pollsQuestionId != pollsChoice.getPollsQuestionId()) ||
					!Validator.equals(name, pollsChoice.getName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_POLLSCHOICE_WHERE);

			query.append(_FINDER_COLUMN_PQI_N_POLLSQUESTIONID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_PQI_N_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PQI_N_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_PQI_N_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pollsQuestionId);

				if (bindName) {
					qPos.add(name);
				}

				List<PollsChoice> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PQI_N,
						finderArgs, list);
				}
				else {
					PollsChoice pollsChoice = list.get(0);

					result = pollsChoice;

					cacheResult(pollsChoice);

					if ((pollsChoice.getPollsQuestionId() != pollsQuestionId) ||
							(pollsChoice.getName() == null) ||
							!pollsChoice.getName().equals(name)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PQI_N,
							finderArgs, pollsChoice);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PQI_N,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (PollsChoice)result;
		}
	}

	/**
	 * Removes the polls choice where pollsQuestionId = &#63; and name = &#63; from the database.
	 *
	 * @param pollsQuestionId the polls question ID
	 * @param name the name
	 * @return the polls choice that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsChoice removeByPQI_N(long pollsQuestionId, String name)
		throws NoSuchChoiceException, SystemException {
		PollsChoice pollsChoice = findByPQI_N(pollsQuestionId, name);

		return remove(pollsChoice);
	}

	/**
	 * Returns the number of polls choices where pollsQuestionId = &#63; and name = &#63;.
	 *
	 * @param pollsQuestionId the polls question ID
	 * @param name the name
	 * @return the number of matching polls choices
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByPQI_N(long pollsQuestionId, String name)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PQI_N;

		Object[] finderArgs = new Object[] { pollsQuestionId, name };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_POLLSCHOICE_WHERE);

			query.append(_FINDER_COLUMN_PQI_N_POLLSQUESTIONID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_PQI_N_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PQI_N_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_PQI_N_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pollsQuestionId);

				if (bindName) {
					qPos.add(name);
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

	private static final String _FINDER_COLUMN_PQI_N_POLLSQUESTIONID_2 = "pollsChoice.pollsQuestionId = ? AND ";
	private static final String _FINDER_COLUMN_PQI_N_NAME_1 = "pollsChoice.name IS NULL";
	private static final String _FINDER_COLUMN_PQI_N_NAME_2 = "pollsChoice.name = ?";
	private static final String _FINDER_COLUMN_PQI_N_NAME_3 = "(pollsChoice.name IS NULL OR pollsChoice.name = '')";

	/**
	 * Caches the polls choice in the entity cache if it is enabled.
	 *
	 * @param pollsChoice the polls choice
	 */
	@Override
	public void cacheResult(PollsChoice pollsChoice) {
		EntityCacheUtil.putResult(PollsChoiceModelImpl.ENTITY_CACHE_ENABLED,
			PollsChoiceImpl.class, pollsChoice.getPrimaryKey(), pollsChoice);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PQI_N,
			new Object[] { pollsChoice.getPollsQuestionId(), pollsChoice.getName() },
			pollsChoice);

		pollsChoice.resetOriginalValues();
	}

	/**
	 * Caches the polls choices in the entity cache if it is enabled.
	 *
	 * @param pollsChoices the polls choices
	 */
	@Override
	public void cacheResult(List<PollsChoice> pollsChoices) {
		for (PollsChoice pollsChoice : pollsChoices) {
			if (EntityCacheUtil.getResult(
						PollsChoiceModelImpl.ENTITY_CACHE_ENABLED,
						PollsChoiceImpl.class, pollsChoice.getPrimaryKey()) == null) {
				cacheResult(pollsChoice);
			}
			else {
				pollsChoice.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all polls choices.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(PollsChoiceImpl.class.getName());
		}

		EntityCacheUtil.clearCache(PollsChoiceImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the polls choice.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PollsChoice pollsChoice) {
		EntityCacheUtil.removeResult(PollsChoiceModelImpl.ENTITY_CACHE_ENABLED,
			PollsChoiceImpl.class, pollsChoice.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(pollsChoice);
	}

	@Override
	public void clearCache(List<PollsChoice> pollsChoices) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PollsChoice pollsChoice : pollsChoices) {
			EntityCacheUtil.removeResult(PollsChoiceModelImpl.ENTITY_CACHE_ENABLED,
				PollsChoiceImpl.class, pollsChoice.getPrimaryKey());

			clearUniqueFindersCache(pollsChoice);
		}
	}

	protected void cacheUniqueFindersCache(PollsChoice pollsChoice) {
		if (pollsChoice.isNew()) {
			Object[] args = new Object[] {
					pollsChoice.getPollsQuestionId(), pollsChoice.getName()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PQI_N, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PQI_N, args,
				pollsChoice);
		}
		else {
			PollsChoiceModelImpl pollsChoiceModelImpl = (PollsChoiceModelImpl)pollsChoice;

			if ((pollsChoiceModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_PQI_N.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						pollsChoice.getPollsQuestionId(), pollsChoice.getName()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PQI_N, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PQI_N, args,
					pollsChoice);
			}
		}
	}

	protected void clearUniqueFindersCache(PollsChoice pollsChoice) {
		PollsChoiceModelImpl pollsChoiceModelImpl = (PollsChoiceModelImpl)pollsChoice;

		Object[] args = new Object[] {
				pollsChoice.getPollsQuestionId(), pollsChoice.getName()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PQI_N, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PQI_N, args);

		if ((pollsChoiceModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_PQI_N.getColumnBitmask()) != 0) {
			args = new Object[] {
					pollsChoiceModelImpl.getOriginalPollsQuestionId(),
					pollsChoiceModelImpl.getOriginalName()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PQI_N, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PQI_N, args);
		}
	}

	/**
	 * Creates a new polls choice with the primary key. Does not add the polls choice to the database.
	 *
	 * @param pollsChoiceId the primary key for the new polls choice
	 * @return the new polls choice
	 */
	@Override
	public PollsChoice create(long pollsChoiceId) {
		PollsChoice pollsChoice = new PollsChoiceImpl();

		pollsChoice.setNew(true);
		pollsChoice.setPrimaryKey(pollsChoiceId);

		String uuid = PortalUUIDUtil.generate();

		pollsChoice.setUuid(uuid);

		return pollsChoice;
	}

	/**
	 * Removes the polls choice with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param pollsChoiceId the primary key of the polls choice
	 * @return the polls choice that was removed
	 * @throws com.liferay.polls.NoSuchChoiceException if a polls choice with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsChoice remove(long pollsChoiceId)
		throws NoSuchChoiceException, SystemException {
		return remove((Serializable)pollsChoiceId);
	}

	/**
	 * Removes the polls choice with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the polls choice
	 * @return the polls choice that was removed
	 * @throws com.liferay.polls.NoSuchChoiceException if a polls choice with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsChoice remove(Serializable primaryKey)
		throws NoSuchChoiceException, SystemException {
		Session session = null;

		try {
			session = openSession();

			PollsChoice pollsChoice = (PollsChoice)session.get(PollsChoiceImpl.class,
					primaryKey);

			if (pollsChoice == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchChoiceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(pollsChoice);
		}
		catch (NoSuchChoiceException nsee) {
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
	protected PollsChoice removeImpl(PollsChoice pollsChoice)
		throws SystemException {
		pollsChoice = toUnwrappedModel(pollsChoice);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(pollsChoice)) {
				pollsChoice = (PollsChoice)session.get(PollsChoiceImpl.class,
						pollsChoice.getPrimaryKeyObj());
			}

			if (pollsChoice != null) {
				session.delete(pollsChoice);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (pollsChoice != null) {
			clearCache(pollsChoice);
		}

		return pollsChoice;
	}

	@Override
	public PollsChoice updateImpl(
		com.liferay.polls.model.PollsChoice pollsChoice)
		throws SystemException {
		pollsChoice = toUnwrappedModel(pollsChoice);

		boolean isNew = pollsChoice.isNew();

		PollsChoiceModelImpl pollsChoiceModelImpl = (PollsChoiceModelImpl)pollsChoice;

		if (Validator.isNull(pollsChoice.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			pollsChoice.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (pollsChoice.isNew()) {
				session.save(pollsChoice);

				pollsChoice.setNew(false);
			}
			else {
				session.merge(pollsChoice);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !PollsChoiceModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((pollsChoiceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						pollsChoiceModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { pollsChoiceModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((pollsChoiceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POLLSQUESTIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						pollsChoiceModelImpl.getOriginalPollsQuestionId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_POLLSQUESTIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POLLSQUESTIONID,
					args);

				args = new Object[] { pollsChoiceModelImpl.getPollsQuestionId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_POLLSQUESTIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POLLSQUESTIONID,
					args);
			}
		}

		EntityCacheUtil.putResult(PollsChoiceModelImpl.ENTITY_CACHE_ENABLED,
			PollsChoiceImpl.class, pollsChoice.getPrimaryKey(), pollsChoice);

		clearUniqueFindersCache(pollsChoice);
		cacheUniqueFindersCache(pollsChoice);

		return pollsChoice;
	}

	protected PollsChoice toUnwrappedModel(PollsChoice pollsChoice) {
		if (pollsChoice instanceof PollsChoiceImpl) {
			return pollsChoice;
		}

		PollsChoiceImpl pollsChoiceImpl = new PollsChoiceImpl();

		pollsChoiceImpl.setNew(pollsChoice.isNew());
		pollsChoiceImpl.setPrimaryKey(pollsChoice.getPrimaryKey());

		pollsChoiceImpl.setUuid(pollsChoice.getUuid());
		pollsChoiceImpl.setPollsChoiceId(pollsChoice.getPollsChoiceId());
		pollsChoiceImpl.setPollsQuestionId(pollsChoice.getPollsQuestionId());
		pollsChoiceImpl.setName(pollsChoice.getName());
		pollsChoiceImpl.setDescription(pollsChoice.getDescription());

		return pollsChoiceImpl;
	}

	/**
	 * Returns the polls choice with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the polls choice
	 * @return the polls choice
	 * @throws com.liferay.polls.NoSuchChoiceException if a polls choice with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsChoice findByPrimaryKey(Serializable primaryKey)
		throws NoSuchChoiceException, SystemException {
		PollsChoice pollsChoice = fetchByPrimaryKey(primaryKey);

		if (pollsChoice == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchChoiceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return pollsChoice;
	}

	/**
	 * Returns the polls choice with the primary key or throws a {@link com.liferay.polls.NoSuchChoiceException} if it could not be found.
	 *
	 * @param pollsChoiceId the primary key of the polls choice
	 * @return the polls choice
	 * @throws com.liferay.polls.NoSuchChoiceException if a polls choice with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsChoice findByPrimaryKey(long pollsChoiceId)
		throws NoSuchChoiceException, SystemException {
		return findByPrimaryKey((Serializable)pollsChoiceId);
	}

	/**
	 * Returns the polls choice with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the polls choice
	 * @return the polls choice, or <code>null</code> if a polls choice with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsChoice fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		PollsChoice pollsChoice = (PollsChoice)EntityCacheUtil.getResult(PollsChoiceModelImpl.ENTITY_CACHE_ENABLED,
				PollsChoiceImpl.class, primaryKey);

		if (pollsChoice == _nullPollsChoice) {
			return null;
		}

		if (pollsChoice == null) {
			Session session = null;

			try {
				session = openSession();

				pollsChoice = (PollsChoice)session.get(PollsChoiceImpl.class,
						primaryKey);

				if (pollsChoice != null) {
					cacheResult(pollsChoice);
				}
				else {
					EntityCacheUtil.putResult(PollsChoiceModelImpl.ENTITY_CACHE_ENABLED,
						PollsChoiceImpl.class, primaryKey, _nullPollsChoice);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(PollsChoiceModelImpl.ENTITY_CACHE_ENABLED,
					PollsChoiceImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return pollsChoice;
	}

	/**
	 * Returns the polls choice with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param pollsChoiceId the primary key of the polls choice
	 * @return the polls choice, or <code>null</code> if a polls choice with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsChoice fetchByPrimaryKey(long pollsChoiceId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)pollsChoiceId);
	}

	/**
	 * Returns all the polls choices.
	 *
	 * @return the polls choices
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PollsChoice> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the polls choices.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.polls.model.impl.PollsChoiceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of polls choices
	 * @param end the upper bound of the range of polls choices (not inclusive)
	 * @return the range of polls choices
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PollsChoice> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the polls choices.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.polls.model.impl.PollsChoiceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of polls choices
	 * @param end the upper bound of the range of polls choices (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of polls choices
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PollsChoice> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
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

		List<PollsChoice> list = (List<PollsChoice>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_POLLSCHOICE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_POLLSCHOICE;

				if (pagination) {
					sql = sql.concat(PollsChoiceModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<PollsChoice>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<PollsChoice>(list);
				}
				else {
					list = (List<PollsChoice>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the polls choices from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (PollsChoice pollsChoice : findAll()) {
			remove(pollsChoice);
		}
	}

	/**
	 * Returns the number of polls choices.
	 *
	 * @return the number of polls choices
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_POLLSCHOICE);

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
	 * Initializes the polls choice persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.polls.model.PollsChoice")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<PollsChoice>> listenersList = new ArrayList<ModelListener<PollsChoice>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<PollsChoice>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(PollsChoiceImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_POLLSCHOICE = "SELECT pollsChoice FROM PollsChoice pollsChoice";
	private static final String _SQL_SELECT_POLLSCHOICE_WHERE = "SELECT pollsChoice FROM PollsChoice pollsChoice WHERE ";
	private static final String _SQL_COUNT_POLLSCHOICE = "SELECT COUNT(pollsChoice) FROM PollsChoice pollsChoice";
	private static final String _SQL_COUNT_POLLSCHOICE_WHERE = "SELECT COUNT(pollsChoice) FROM PollsChoice pollsChoice WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "pollsChoice.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PollsChoice exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PollsChoice exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(PollsChoicePersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
	private static PollsChoice _nullPollsChoice = new PollsChoiceImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<PollsChoice> toCacheModel() {
				return _nullPollsChoiceCacheModel;
			}
		};

	private static CacheModel<PollsChoice> _nullPollsChoiceCacheModel = new CacheModel<PollsChoice>() {
			@Override
			public PollsChoice toEntityModel() {
				return _nullPollsChoice;
			}
		};
}