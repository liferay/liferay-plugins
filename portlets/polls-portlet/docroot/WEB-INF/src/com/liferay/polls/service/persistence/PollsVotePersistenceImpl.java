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

package com.liferay.polls.service.persistence;

import com.liferay.polls.NoSuchVoteException;
import com.liferay.polls.model.PollsVote;
import com.liferay.polls.model.impl.PollsVoteImpl;
import com.liferay.polls.model.impl.PollsVoteModelImpl;

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
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the polls vote service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Juan Fern√°ndez
 * @see PollsVotePersistence
 * @see PollsVoteUtil
 * @generated
 */
public class PollsVotePersistenceImpl extends BasePersistenceImpl<PollsVote>
	implements PollsVotePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PollsVoteUtil} to access the polls vote persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PollsVoteImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
			PollsVoteModelImpl.FINDER_CACHE_ENABLED, PollsVoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
			PollsVoteModelImpl.FINDER_CACHE_ENABLED, PollsVoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
			PollsVoteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_POLLSQUESTIONID =
		new FinderPath(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
			PollsVoteModelImpl.FINDER_CACHE_ENABLED, PollsVoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPollsQuestionId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POLLSQUESTIONID =
		new FinderPath(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
			PollsVoteModelImpl.FINDER_CACHE_ENABLED, PollsVoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPollsQuestionId",
			new String[] { Long.class.getName() },
			PollsVoteModelImpl.POLLSQUESTIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_POLLSQUESTIONID = new FinderPath(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
			PollsVoteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByPollsQuestionId", new String[] { Long.class.getName() });

	/**
	 * Returns all the polls votes where pollsQuestionId = &#63;.
	 *
	 * @param pollsQuestionId the polls question ID
	 * @return the matching polls votes
	 * @throws SystemException if a system exception occurred
	 */
	public List<PollsVote> findByPollsQuestionId(long pollsQuestionId)
		throws SystemException {
		return findByPollsQuestionId(pollsQuestionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the polls votes where pollsQuestionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.polls.model.impl.PollsVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pollsQuestionId the polls question ID
	 * @param start the lower bound of the range of polls votes
	 * @param end the upper bound of the range of polls votes (not inclusive)
	 * @return the range of matching polls votes
	 * @throws SystemException if a system exception occurred
	 */
	public List<PollsVote> findByPollsQuestionId(long pollsQuestionId,
		int start, int end) throws SystemException {
		return findByPollsQuestionId(pollsQuestionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the polls votes where pollsQuestionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.polls.model.impl.PollsVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pollsQuestionId the polls question ID
	 * @param start the lower bound of the range of polls votes
	 * @param end the upper bound of the range of polls votes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching polls votes
	 * @throws SystemException if a system exception occurred
	 */
	public List<PollsVote> findByPollsQuestionId(long pollsQuestionId,
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

		List<PollsVote> list = (List<PollsVote>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PollsVote pollsVote : list) {
				if ((pollsQuestionId != pollsVote.getPollsQuestionId())) {
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

			query.append(_SQL_SELECT_POLLSVOTE_WHERE);

			query.append(_FINDER_COLUMN_POLLSQUESTIONID_POLLSQUESTIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PollsVoteModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pollsQuestionId);

				if (!pagination) {
					list = (List<PollsVote>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<PollsVote>(list);
				}
				else {
					list = (List<PollsVote>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first polls vote in the ordered set where pollsQuestionId = &#63;.
	 *
	 * @param pollsQuestionId the polls question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching polls vote
	 * @throws com.liferay.polls.NoSuchVoteException if a matching polls vote could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PollsVote findByPollsQuestionId_First(long pollsQuestionId,
		OrderByComparator orderByComparator)
		throws NoSuchVoteException, SystemException {
		PollsVote pollsVote = fetchByPollsQuestionId_First(pollsQuestionId,
				orderByComparator);

		if (pollsVote != null) {
			return pollsVote;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("pollsQuestionId=");
		msg.append(pollsQuestionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVoteException(msg.toString());
	}

	/**
	 * Returns the first polls vote in the ordered set where pollsQuestionId = &#63;.
	 *
	 * @param pollsQuestionId the polls question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching polls vote, or <code>null</code> if a matching polls vote could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PollsVote fetchByPollsQuestionId_First(long pollsQuestionId,
		OrderByComparator orderByComparator) throws SystemException {
		List<PollsVote> list = findByPollsQuestionId(pollsQuestionId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last polls vote in the ordered set where pollsQuestionId = &#63;.
	 *
	 * @param pollsQuestionId the polls question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching polls vote
	 * @throws com.liferay.polls.NoSuchVoteException if a matching polls vote could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PollsVote findByPollsQuestionId_Last(long pollsQuestionId,
		OrderByComparator orderByComparator)
		throws NoSuchVoteException, SystemException {
		PollsVote pollsVote = fetchByPollsQuestionId_Last(pollsQuestionId,
				orderByComparator);

		if (pollsVote != null) {
			return pollsVote;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("pollsQuestionId=");
		msg.append(pollsQuestionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVoteException(msg.toString());
	}

	/**
	 * Returns the last polls vote in the ordered set where pollsQuestionId = &#63;.
	 *
	 * @param pollsQuestionId the polls question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching polls vote, or <code>null</code> if a matching polls vote could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PollsVote fetchByPollsQuestionId_Last(long pollsQuestionId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByPollsQuestionId(pollsQuestionId);

		List<PollsVote> list = findByPollsQuestionId(pollsQuestionId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the polls votes before and after the current polls vote in the ordered set where pollsQuestionId = &#63;.
	 *
	 * @param pollsVoteId the primary key of the current polls vote
	 * @param pollsQuestionId the polls question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next polls vote
	 * @throws com.liferay.polls.NoSuchVoteException if a polls vote with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PollsVote[] findByPollsQuestionId_PrevAndNext(long pollsVoteId,
		long pollsQuestionId, OrderByComparator orderByComparator)
		throws NoSuchVoteException, SystemException {
		PollsVote pollsVote = findByPrimaryKey(pollsVoteId);

		Session session = null;

		try {
			session = openSession();

			PollsVote[] array = new PollsVoteImpl[3];

			array[0] = getByPollsQuestionId_PrevAndNext(session, pollsVote,
					pollsQuestionId, orderByComparator, true);

			array[1] = pollsVote;

			array[2] = getByPollsQuestionId_PrevAndNext(session, pollsVote,
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

	protected PollsVote getByPollsQuestionId_PrevAndNext(Session session,
		PollsVote pollsVote, long pollsQuestionId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_POLLSVOTE_WHERE);

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
			query.append(PollsVoteModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(pollsQuestionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(pollsVote);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PollsVote> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the polls votes where pollsQuestionId = &#63; from the database.
	 *
	 * @param pollsQuestionId the polls question ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPollsQuestionId(long pollsQuestionId)
		throws SystemException {
		for (PollsVote pollsVote : findByPollsQuestionId(pollsQuestionId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(pollsVote);
		}
	}

	/**
	 * Returns the number of polls votes where pollsQuestionId = &#63;.
	 *
	 * @param pollsQuestionId the polls question ID
	 * @return the number of matching polls votes
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPollsQuestionId(long pollsQuestionId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_POLLSQUESTIONID;

		Object[] finderArgs = new Object[] { pollsQuestionId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_POLLSVOTE_WHERE);

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
		"pollsVote.pollsQuestionId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_POLLSCHOICEID =
		new FinderPath(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
			PollsVoteModelImpl.FINDER_CACHE_ENABLED, PollsVoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPollsChoiceId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POLLSCHOICEID =
		new FinderPath(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
			PollsVoteModelImpl.FINDER_CACHE_ENABLED, PollsVoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPollsChoiceId",
			new String[] { Long.class.getName() },
			PollsVoteModelImpl.POLLSCHOICEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_POLLSCHOICEID = new FinderPath(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
			PollsVoteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPollsChoiceId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the polls votes where pollsChoiceId = &#63;.
	 *
	 * @param pollsChoiceId the polls choice ID
	 * @return the matching polls votes
	 * @throws SystemException if a system exception occurred
	 */
	public List<PollsVote> findByPollsChoiceId(long pollsChoiceId)
		throws SystemException {
		return findByPollsChoiceId(pollsChoiceId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the polls votes where pollsChoiceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.polls.model.impl.PollsVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pollsChoiceId the polls choice ID
	 * @param start the lower bound of the range of polls votes
	 * @param end the upper bound of the range of polls votes (not inclusive)
	 * @return the range of matching polls votes
	 * @throws SystemException if a system exception occurred
	 */
	public List<PollsVote> findByPollsChoiceId(long pollsChoiceId, int start,
		int end) throws SystemException {
		return findByPollsChoiceId(pollsChoiceId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the polls votes where pollsChoiceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.polls.model.impl.PollsVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pollsChoiceId the polls choice ID
	 * @param start the lower bound of the range of polls votes
	 * @param end the upper bound of the range of polls votes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching polls votes
	 * @throws SystemException if a system exception occurred
	 */
	public List<PollsVote> findByPollsChoiceId(long pollsChoiceId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POLLSCHOICEID;
			finderArgs = new Object[] { pollsChoiceId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_POLLSCHOICEID;
			finderArgs = new Object[] {
					pollsChoiceId,
					
					start, end, orderByComparator
				};
		}

		List<PollsVote> list = (List<PollsVote>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PollsVote pollsVote : list) {
				if ((pollsChoiceId != pollsVote.getPollsChoiceId())) {
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

			query.append(_SQL_SELECT_POLLSVOTE_WHERE);

			query.append(_FINDER_COLUMN_POLLSCHOICEID_POLLSCHOICEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PollsVoteModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pollsChoiceId);

				if (!pagination) {
					list = (List<PollsVote>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<PollsVote>(list);
				}
				else {
					list = (List<PollsVote>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first polls vote in the ordered set where pollsChoiceId = &#63;.
	 *
	 * @param pollsChoiceId the polls choice ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching polls vote
	 * @throws com.liferay.polls.NoSuchVoteException if a matching polls vote could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PollsVote findByPollsChoiceId_First(long pollsChoiceId,
		OrderByComparator orderByComparator)
		throws NoSuchVoteException, SystemException {
		PollsVote pollsVote = fetchByPollsChoiceId_First(pollsChoiceId,
				orderByComparator);

		if (pollsVote != null) {
			return pollsVote;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("pollsChoiceId=");
		msg.append(pollsChoiceId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVoteException(msg.toString());
	}

	/**
	 * Returns the first polls vote in the ordered set where pollsChoiceId = &#63;.
	 *
	 * @param pollsChoiceId the polls choice ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching polls vote, or <code>null</code> if a matching polls vote could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PollsVote fetchByPollsChoiceId_First(long pollsChoiceId,
		OrderByComparator orderByComparator) throws SystemException {
		List<PollsVote> list = findByPollsChoiceId(pollsChoiceId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last polls vote in the ordered set where pollsChoiceId = &#63;.
	 *
	 * @param pollsChoiceId the polls choice ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching polls vote
	 * @throws com.liferay.polls.NoSuchVoteException if a matching polls vote could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PollsVote findByPollsChoiceId_Last(long pollsChoiceId,
		OrderByComparator orderByComparator)
		throws NoSuchVoteException, SystemException {
		PollsVote pollsVote = fetchByPollsChoiceId_Last(pollsChoiceId,
				orderByComparator);

		if (pollsVote != null) {
			return pollsVote;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("pollsChoiceId=");
		msg.append(pollsChoiceId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVoteException(msg.toString());
	}

	/**
	 * Returns the last polls vote in the ordered set where pollsChoiceId = &#63;.
	 *
	 * @param pollsChoiceId the polls choice ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching polls vote, or <code>null</code> if a matching polls vote could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PollsVote fetchByPollsChoiceId_Last(long pollsChoiceId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByPollsChoiceId(pollsChoiceId);

		List<PollsVote> list = findByPollsChoiceId(pollsChoiceId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the polls votes before and after the current polls vote in the ordered set where pollsChoiceId = &#63;.
	 *
	 * @param pollsVoteId the primary key of the current polls vote
	 * @param pollsChoiceId the polls choice ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next polls vote
	 * @throws com.liferay.polls.NoSuchVoteException if a polls vote with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PollsVote[] findByPollsChoiceId_PrevAndNext(long pollsVoteId,
		long pollsChoiceId, OrderByComparator orderByComparator)
		throws NoSuchVoteException, SystemException {
		PollsVote pollsVote = findByPrimaryKey(pollsVoteId);

		Session session = null;

		try {
			session = openSession();

			PollsVote[] array = new PollsVoteImpl[3];

			array[0] = getByPollsChoiceId_PrevAndNext(session, pollsVote,
					pollsChoiceId, orderByComparator, true);

			array[1] = pollsVote;

			array[2] = getByPollsChoiceId_PrevAndNext(session, pollsVote,
					pollsChoiceId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PollsVote getByPollsChoiceId_PrevAndNext(Session session,
		PollsVote pollsVote, long pollsChoiceId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_POLLSVOTE_WHERE);

		query.append(_FINDER_COLUMN_POLLSCHOICEID_POLLSCHOICEID_2);

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
			query.append(PollsVoteModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(pollsChoiceId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(pollsVote);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PollsVote> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the polls votes where pollsChoiceId = &#63; from the database.
	 *
	 * @param pollsChoiceId the polls choice ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPollsChoiceId(long pollsChoiceId)
		throws SystemException {
		for (PollsVote pollsVote : findByPollsChoiceId(pollsChoiceId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(pollsVote);
		}
	}

	/**
	 * Returns the number of polls votes where pollsChoiceId = &#63;.
	 *
	 * @param pollsChoiceId the polls choice ID
	 * @return the number of matching polls votes
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPollsChoiceId(long pollsChoiceId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_POLLSCHOICEID;

		Object[] finderArgs = new Object[] { pollsChoiceId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_POLLSVOTE_WHERE);

			query.append(_FINDER_COLUMN_POLLSCHOICEID_POLLSCHOICEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pollsChoiceId);

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

	private static final String _FINDER_COLUMN_POLLSCHOICEID_POLLSCHOICEID_2 = "pollsVote.pollsChoiceId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_U_PQI = new FinderPath(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
			PollsVoteModelImpl.FINDER_CACHE_ENABLED, PollsVoteImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByU_PQI",
			new String[] { Long.class.getName(), Long.class.getName() },
			PollsVoteModelImpl.USERID_COLUMN_BITMASK |
			PollsVoteModelImpl.POLLSQUESTIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_PQI = new FinderPath(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
			PollsVoteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_PQI",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the polls vote where userId = &#63; and pollsQuestionId = &#63; or throws a {@link com.liferay.polls.NoSuchVoteException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @param pollsQuestionId the polls question ID
	 * @return the matching polls vote
	 * @throws com.liferay.polls.NoSuchVoteException if a matching polls vote could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PollsVote findByU_PQI(long userId, long pollsQuestionId)
		throws NoSuchVoteException, SystemException {
		PollsVote pollsVote = fetchByU_PQI(userId, pollsQuestionId);

		if (pollsVote == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", pollsQuestionId=");
			msg.append(pollsQuestionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchVoteException(msg.toString());
		}

		return pollsVote;
	}

	/**
	 * Returns the polls vote where userId = &#63; and pollsQuestionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param pollsQuestionId the polls question ID
	 * @return the matching polls vote, or <code>null</code> if a matching polls vote could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PollsVote fetchByU_PQI(long userId, long pollsQuestionId)
		throws SystemException {
		return fetchByU_PQI(userId, pollsQuestionId, true);
	}

	/**
	 * Returns the polls vote where userId = &#63; and pollsQuestionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param pollsQuestionId the polls question ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching polls vote, or <code>null</code> if a matching polls vote could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PollsVote fetchByU_PQI(long userId, long pollsQuestionId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { userId, pollsQuestionId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_U_PQI,
					finderArgs, this);
		}

		if (result instanceof PollsVote) {
			PollsVote pollsVote = (PollsVote)result;

			if ((userId != pollsVote.getUserId()) ||
					(pollsQuestionId != pollsVote.getPollsQuestionId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_POLLSVOTE_WHERE);

			query.append(_FINDER_COLUMN_U_PQI_USERID_2);

			query.append(_FINDER_COLUMN_U_PQI_POLLSQUESTIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(pollsQuestionId);

				List<PollsVote> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_PQI,
						finderArgs, list);
				}
				else {
					PollsVote pollsVote = list.get(0);

					result = pollsVote;

					cacheResult(pollsVote);

					if ((pollsVote.getUserId() != userId) ||
							(pollsVote.getPollsQuestionId() != pollsQuestionId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_PQI,
							finderArgs, pollsVote);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_PQI,
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
			return (PollsVote)result;
		}
	}

	/**
	 * Removes the polls vote where userId = &#63; and pollsQuestionId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param pollsQuestionId the polls question ID
	 * @return the polls vote that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public PollsVote removeByU_PQI(long userId, long pollsQuestionId)
		throws NoSuchVoteException, SystemException {
		PollsVote pollsVote = findByU_PQI(userId, pollsQuestionId);

		return remove(pollsVote);
	}

	/**
	 * Returns the number of polls votes where userId = &#63; and pollsQuestionId = &#63;.
	 *
	 * @param userId the user ID
	 * @param pollsQuestionId the polls question ID
	 * @return the number of matching polls votes
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_PQI(long userId, long pollsQuestionId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_PQI;

		Object[] finderArgs = new Object[] { userId, pollsQuestionId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_POLLSVOTE_WHERE);

			query.append(_FINDER_COLUMN_U_PQI_USERID_2);

			query.append(_FINDER_COLUMN_U_PQI_POLLSQUESTIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

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

	private static final String _FINDER_COLUMN_U_PQI_USERID_2 = "pollsVote.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_PQI_POLLSQUESTIONID_2 = "pollsVote.pollsQuestionId = ?";

	/**
	 * Caches the polls vote in the entity cache if it is enabled.
	 *
	 * @param pollsVote the polls vote
	 */
	public void cacheResult(PollsVote pollsVote) {
		EntityCacheUtil.putResult(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
			PollsVoteImpl.class, pollsVote.getPrimaryKey(), pollsVote);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_PQI,
			new Object[] { pollsVote.getUserId(), pollsVote.getPollsQuestionId() },
			pollsVote);

		pollsVote.resetOriginalValues();
	}

	/**
	 * Caches the polls votes in the entity cache if it is enabled.
	 *
	 * @param pollsVotes the polls votes
	 */
	public void cacheResult(List<PollsVote> pollsVotes) {
		for (PollsVote pollsVote : pollsVotes) {
			if (EntityCacheUtil.getResult(
						PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
						PollsVoteImpl.class, pollsVote.getPrimaryKey()) == null) {
				cacheResult(pollsVote);
			}
			else {
				pollsVote.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all polls votes.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(PollsVoteImpl.class.getName());
		}

		EntityCacheUtil.clearCache(PollsVoteImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the polls vote.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PollsVote pollsVote) {
		EntityCacheUtil.removeResult(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
			PollsVoteImpl.class, pollsVote.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(pollsVote);
	}

	@Override
	public void clearCache(List<PollsVote> pollsVotes) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PollsVote pollsVote : pollsVotes) {
			EntityCacheUtil.removeResult(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
				PollsVoteImpl.class, pollsVote.getPrimaryKey());

			clearUniqueFindersCache(pollsVote);
		}
	}

	protected void cacheUniqueFindersCache(PollsVote pollsVote) {
		if (pollsVote.isNew()) {
			Object[] args = new Object[] {
					pollsVote.getUserId(), pollsVote.getPollsQuestionId()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_PQI, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_PQI, args,
				pollsVote);
		}
		else {
			PollsVoteModelImpl pollsVoteModelImpl = (PollsVoteModelImpl)pollsVote;

			if ((pollsVoteModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_U_PQI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						pollsVote.getUserId(), pollsVote.getPollsQuestionId()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_PQI, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_PQI, args,
					pollsVote);
			}
		}
	}

	protected void clearUniqueFindersCache(PollsVote pollsVote) {
		PollsVoteModelImpl pollsVoteModelImpl = (PollsVoteModelImpl)pollsVote;

		Object[] args = new Object[] {
				pollsVote.getUserId(), pollsVote.getPollsQuestionId()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_PQI, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_PQI, args);

		if ((pollsVoteModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_U_PQI.getColumnBitmask()) != 0) {
			args = new Object[] {
					pollsVoteModelImpl.getOriginalUserId(),
					pollsVoteModelImpl.getOriginalPollsQuestionId()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_PQI, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_PQI, args);
		}
	}

	/**
	 * Creates a new polls vote with the primary key. Does not add the polls vote to the database.
	 *
	 * @param pollsVoteId the primary key for the new polls vote
	 * @return the new polls vote
	 */
	public PollsVote create(long pollsVoteId) {
		PollsVote pollsVote = new PollsVoteImpl();

		pollsVote.setNew(true);
		pollsVote.setPrimaryKey(pollsVoteId);

		return pollsVote;
	}

	/**
	 * Removes the polls vote with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param pollsVoteId the primary key of the polls vote
	 * @return the polls vote that was removed
	 * @throws com.liferay.polls.NoSuchVoteException if a polls vote with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PollsVote remove(long pollsVoteId)
		throws NoSuchVoteException, SystemException {
		return remove((Serializable)pollsVoteId);
	}

	/**
	 * Removes the polls vote with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the polls vote
	 * @return the polls vote that was removed
	 * @throws com.liferay.polls.NoSuchVoteException if a polls vote with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsVote remove(Serializable primaryKey)
		throws NoSuchVoteException, SystemException {
		Session session = null;

		try {
			session = openSession();

			PollsVote pollsVote = (PollsVote)session.get(PollsVoteImpl.class,
					primaryKey);

			if (pollsVote == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVoteException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(pollsVote);
		}
		catch (NoSuchVoteException nsee) {
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
	protected PollsVote removeImpl(PollsVote pollsVote)
		throws SystemException {
		pollsVote = toUnwrappedModel(pollsVote);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(pollsVote)) {
				pollsVote = (PollsVote)session.get(PollsVoteImpl.class,
						pollsVote.getPrimaryKeyObj());
			}

			if (pollsVote != null) {
				session.delete(pollsVote);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (pollsVote != null) {
			clearCache(pollsVote);
		}

		return pollsVote;
	}

	@Override
	public PollsVote updateImpl(com.liferay.polls.model.PollsVote pollsVote)
		throws SystemException {
		pollsVote = toUnwrappedModel(pollsVote);

		boolean isNew = pollsVote.isNew();

		PollsVoteModelImpl pollsVoteModelImpl = (PollsVoteModelImpl)pollsVote;

		Session session = null;

		try {
			session = openSession();

			if (pollsVote.isNew()) {
				session.save(pollsVote);

				pollsVote.setNew(false);
			}
			else {
				session.merge(pollsVote);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !PollsVoteModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((pollsVoteModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POLLSQUESTIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						pollsVoteModelImpl.getOriginalPollsQuestionId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_POLLSQUESTIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POLLSQUESTIONID,
					args);

				args = new Object[] { pollsVoteModelImpl.getPollsQuestionId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_POLLSQUESTIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POLLSQUESTIONID,
					args);
			}

			if ((pollsVoteModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POLLSCHOICEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						pollsVoteModelImpl.getOriginalPollsChoiceId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_POLLSCHOICEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POLLSCHOICEID,
					args);

				args = new Object[] { pollsVoteModelImpl.getPollsChoiceId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_POLLSCHOICEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POLLSCHOICEID,
					args);
			}
		}

		EntityCacheUtil.putResult(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
			PollsVoteImpl.class, pollsVote.getPrimaryKey(), pollsVote);

		clearUniqueFindersCache(pollsVote);
		cacheUniqueFindersCache(pollsVote);

		return pollsVote;
	}

	protected PollsVote toUnwrappedModel(PollsVote pollsVote) {
		if (pollsVote instanceof PollsVoteImpl) {
			return pollsVote;
		}

		PollsVoteImpl pollsVoteImpl = new PollsVoteImpl();

		pollsVoteImpl.setNew(pollsVote.isNew());
		pollsVoteImpl.setPrimaryKey(pollsVote.getPrimaryKey());

		pollsVoteImpl.setPollsVoteId(pollsVote.getPollsVoteId());
		pollsVoteImpl.setCompanyId(pollsVote.getCompanyId());
		pollsVoteImpl.setUserId(pollsVote.getUserId());
		pollsVoteImpl.setUserName(pollsVote.getUserName());
		pollsVoteImpl.setCreateDate(pollsVote.getCreateDate());
		pollsVoteImpl.setModifiedDate(pollsVote.getModifiedDate());
		pollsVoteImpl.setPollsQuestionId(pollsVote.getPollsQuestionId());
		pollsVoteImpl.setPollsChoiceId(pollsVote.getPollsChoiceId());
		pollsVoteImpl.setVoteDate(pollsVote.getVoteDate());

		return pollsVoteImpl;
	}

	/**
	 * Returns the polls vote with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the polls vote
	 * @return the polls vote
	 * @throws com.liferay.polls.NoSuchVoteException if a polls vote with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsVote findByPrimaryKey(Serializable primaryKey)
		throws NoSuchVoteException, SystemException {
		PollsVote pollsVote = fetchByPrimaryKey(primaryKey);

		if (pollsVote == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchVoteException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return pollsVote;
	}

	/**
	 * Returns the polls vote with the primary key or throws a {@link com.liferay.polls.NoSuchVoteException} if it could not be found.
	 *
	 * @param pollsVoteId the primary key of the polls vote
	 * @return the polls vote
	 * @throws com.liferay.polls.NoSuchVoteException if a polls vote with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PollsVote findByPrimaryKey(long pollsVoteId)
		throws NoSuchVoteException, SystemException {
		return findByPrimaryKey((Serializable)pollsVoteId);
	}

	/**
	 * Returns the polls vote with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the polls vote
	 * @return the polls vote, or <code>null</code> if a polls vote with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PollsVote fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		PollsVote pollsVote = (PollsVote)EntityCacheUtil.getResult(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
				PollsVoteImpl.class, primaryKey);

		if (pollsVote == _nullPollsVote) {
			return null;
		}

		if (pollsVote == null) {
			Session session = null;

			try {
				session = openSession();

				pollsVote = (PollsVote)session.get(PollsVoteImpl.class,
						primaryKey);

				if (pollsVote != null) {
					cacheResult(pollsVote);
				}
				else {
					EntityCacheUtil.putResult(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
						PollsVoteImpl.class, primaryKey, _nullPollsVote);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(PollsVoteModelImpl.ENTITY_CACHE_ENABLED,
					PollsVoteImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return pollsVote;
	}

	/**
	 * Returns the polls vote with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param pollsVoteId the primary key of the polls vote
	 * @return the polls vote, or <code>null</code> if a polls vote with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PollsVote fetchByPrimaryKey(long pollsVoteId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)pollsVoteId);
	}

	/**
	 * Returns all the polls votes.
	 *
	 * @return the polls votes
	 * @throws SystemException if a system exception occurred
	 */
	public List<PollsVote> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the polls votes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.polls.model.impl.PollsVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of polls votes
	 * @param end the upper bound of the range of polls votes (not inclusive)
	 * @return the range of polls votes
	 * @throws SystemException if a system exception occurred
	 */
	public List<PollsVote> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the polls votes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.polls.model.impl.PollsVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of polls votes
	 * @param end the upper bound of the range of polls votes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of polls votes
	 * @throws SystemException if a system exception occurred
	 */
	public List<PollsVote> findAll(int start, int end,
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

		List<PollsVote> list = (List<PollsVote>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_POLLSVOTE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_POLLSVOTE;

				if (pagination) {
					sql = sql.concat(PollsVoteModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<PollsVote>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<PollsVote>(list);
				}
				else {
					list = (List<PollsVote>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the polls votes from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (PollsVote pollsVote : findAll()) {
			remove(pollsVote);
		}
	}

	/**
	 * Returns the number of polls votes.
	 *
	 * @return the number of polls votes
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_POLLSVOTE);

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

	/**
	 * Initializes the polls vote persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.polls.model.PollsVote")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<PollsVote>> listenersList = new ArrayList<ModelListener<PollsVote>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<PollsVote>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(PollsVoteImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_POLLSVOTE = "SELECT pollsVote FROM PollsVote pollsVote";
	private static final String _SQL_SELECT_POLLSVOTE_WHERE = "SELECT pollsVote FROM PollsVote pollsVote WHERE ";
	private static final String _SQL_COUNT_POLLSVOTE = "SELECT COUNT(pollsVote) FROM PollsVote pollsVote";
	private static final String _SQL_COUNT_POLLSVOTE_WHERE = "SELECT COUNT(pollsVote) FROM PollsVote pollsVote WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "pollsVote.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PollsVote exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PollsVote exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(PollsVotePersistenceImpl.class);
	private static PollsVote _nullPollsVote = new PollsVoteImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<PollsVote> toCacheModel() {
				return _nullPollsVoteCacheModel;
			}
		};

	private static CacheModel<PollsVote> _nullPollsVoteCacheModel = new CacheModel<PollsVote>() {
			public PollsVote toEntityModel() {
				return _nullPollsVote;
			}
		};
}