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

package com.liferay.so.service.persistence;

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
import com.liferay.portal.service.persistence.GroupPersistence;
import com.liferay.portal.service.persistence.LayoutPersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserGroupRolePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.so.NoSuchMemberRequestException;
import com.liferay.so.model.MemberRequest;
import com.liferay.so.model.impl.MemberRequestImpl;
import com.liferay.so.model.impl.MemberRequestModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author    Brian Wing Shun Chan
 * @see       MemberRequestPersistence
 * @see       MemberRequestUtil
 * @generated
 */
public class MemberRequestPersistenceImpl extends BasePersistenceImpl<MemberRequest>
	implements MemberRequestPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = MemberRequestImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FETCH_BY_KEY = new FinderPath(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_ENTITY, "fetchByKey",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_KEY = new FinderPath(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByKey",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_RECEIVERUSERID = new FinderPath(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByReceiverUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_RECEIVERUSERID = new FinderPath(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByReceiverUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_R_S = new FinderPath(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByR_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_R_S = new FinderPath(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByR_S",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_G_R_S = new FinderPath(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_ENTITY, "fetchByG_R_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_G_R_S = new FinderPath(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByG_R_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	public void cacheResult(MemberRequest memberRequest) {
		EntityCacheUtil.putResult(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestImpl.class, memberRequest.getPrimaryKey(),
			memberRequest);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KEY,
			new Object[] { memberRequest.getKey() }, memberRequest);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_R_S,
			new Object[] {
				new Long(memberRequest.getGroupId()),
				new Long(memberRequest.getReceiverUserId()),
				new Integer(memberRequest.getStatus())
			}, memberRequest);
	}

	public void cacheResult(List<MemberRequest> memberRequests) {
		for (MemberRequest memberRequest : memberRequests) {
			if (EntityCacheUtil.getResult(
						MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
						MemberRequestImpl.class, memberRequest.getPrimaryKey(),
						this) == null) {
				cacheResult(memberRequest);
			}
		}
	}

	public void clearCache() {
		CacheRegistryUtil.clear(MemberRequestImpl.class.getName());
		EntityCacheUtil.clearCache(MemberRequestImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public void clearCache(MemberRequest memberRequest) {
		EntityCacheUtil.removeResult(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestImpl.class, memberRequest.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KEY,
			new Object[] { memberRequest.getKey() });

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_R_S,
			new Object[] {
				new Long(memberRequest.getGroupId()),
				new Long(memberRequest.getReceiverUserId()),
				new Integer(memberRequest.getStatus())
			});
	}

	public MemberRequest create(long memberRequestId) {
		MemberRequest memberRequest = new MemberRequestImpl();

		memberRequest.setNew(true);
		memberRequest.setPrimaryKey(memberRequestId);

		return memberRequest;
	}

	public MemberRequest remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public MemberRequest remove(long memberRequestId)
		throws NoSuchMemberRequestException, SystemException {
		Session session = null;

		try {
			session = openSession();

			MemberRequest memberRequest = (MemberRequest)session.get(MemberRequestImpl.class,
					new Long(memberRequestId));

			if (memberRequest == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						memberRequestId);
				}

				throw new NoSuchMemberRequestException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					memberRequestId);
			}

			return remove(memberRequest);
		}
		catch (NoSuchMemberRequestException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MemberRequest removeImpl(MemberRequest memberRequest)
		throws SystemException {
		memberRequest = toUnwrappedModel(memberRequest);

		Session session = null;

		try {
			session = openSession();

			if (memberRequest.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(MemberRequestImpl.class,
						memberRequest.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(memberRequest);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		MemberRequestModelImpl memberRequestModelImpl = (MemberRequestModelImpl)memberRequest;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KEY,
			new Object[] { memberRequestModelImpl.getOriginalKey() });

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_R_S,
			new Object[] {
				new Long(memberRequestModelImpl.getOriginalGroupId()),
				new Long(memberRequestModelImpl.getOriginalReceiverUserId()),
				new Integer(memberRequestModelImpl.getOriginalStatus())
			});

		EntityCacheUtil.removeResult(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestImpl.class, memberRequest.getPrimaryKey());

		return memberRequest;
	}

	public MemberRequest updateImpl(
		com.liferay.so.model.MemberRequest memberRequest, boolean merge)
		throws SystemException {
		memberRequest = toUnwrappedModel(memberRequest);

		boolean isNew = memberRequest.isNew();

		MemberRequestModelImpl memberRequestModelImpl = (MemberRequestModelImpl)memberRequest;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, memberRequest, merge);

			memberRequest.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestImpl.class, memberRequest.getPrimaryKey(),
			memberRequest);

		if (!isNew &&
				(!Validator.equals(memberRequest.getKey(),
					memberRequestModelImpl.getOriginalKey()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KEY,
				new Object[] { memberRequestModelImpl.getOriginalKey() });
		}

		if (isNew ||
				(!Validator.equals(memberRequest.getKey(),
					memberRequestModelImpl.getOriginalKey()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KEY,
				new Object[] { memberRequest.getKey() }, memberRequest);
		}

		if (!isNew &&
				((memberRequest.getGroupId() != memberRequestModelImpl.getOriginalGroupId()) ||
				(memberRequest.getReceiverUserId() != memberRequestModelImpl.getOriginalReceiverUserId()) ||
				(memberRequest.getStatus() != memberRequestModelImpl.getOriginalStatus()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_R_S,
				new Object[] {
					new Long(memberRequestModelImpl.getOriginalGroupId()),
					new Long(memberRequestModelImpl.getOriginalReceiverUserId()),
					new Integer(memberRequestModelImpl.getOriginalStatus())
				});
		}

		if (isNew ||
				((memberRequest.getGroupId() != memberRequestModelImpl.getOriginalGroupId()) ||
				(memberRequest.getReceiverUserId() != memberRequestModelImpl.getOriginalReceiverUserId()) ||
				(memberRequest.getStatus() != memberRequestModelImpl.getOriginalStatus()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_R_S,
				new Object[] {
					new Long(memberRequest.getGroupId()),
					new Long(memberRequest.getReceiverUserId()),
					new Integer(memberRequest.getStatus())
				}, memberRequest);
		}

		return memberRequest;
	}

	protected MemberRequest toUnwrappedModel(MemberRequest memberRequest) {
		if (memberRequest instanceof MemberRequestImpl) {
			return memberRequest;
		}

		MemberRequestImpl memberRequestImpl = new MemberRequestImpl();

		memberRequestImpl.setNew(memberRequest.isNew());
		memberRequestImpl.setPrimaryKey(memberRequest.getPrimaryKey());

		memberRequestImpl.setMemberRequestId(memberRequest.getMemberRequestId());
		memberRequestImpl.setGroupId(memberRequest.getGroupId());
		memberRequestImpl.setCompanyId(memberRequest.getCompanyId());
		memberRequestImpl.setUserId(memberRequest.getUserId());
		memberRequestImpl.setUserName(memberRequest.getUserName());
		memberRequestImpl.setCreateDate(memberRequest.getCreateDate());
		memberRequestImpl.setModifiedDate(memberRequest.getModifiedDate());
		memberRequestImpl.setKey(memberRequest.getKey());
		memberRequestImpl.setReceiverUserId(memberRequest.getReceiverUserId());
		memberRequestImpl.setInvitedRoleId(memberRequest.getInvitedRoleId());
		memberRequestImpl.setInvitedTeamId(memberRequest.getInvitedTeamId());
		memberRequestImpl.setStatus(memberRequest.getStatus());

		return memberRequestImpl;
	}

	public MemberRequest findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	public MemberRequest findByPrimaryKey(long memberRequestId)
		throws NoSuchMemberRequestException, SystemException {
		MemberRequest memberRequest = fetchByPrimaryKey(memberRequestId);

		if (memberRequest == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + memberRequestId);
			}

			throw new NoSuchMemberRequestException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				memberRequestId);
		}

		return memberRequest;
	}

	public MemberRequest fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public MemberRequest fetchByPrimaryKey(long memberRequestId)
		throws SystemException {
		MemberRequest memberRequest = (MemberRequest)EntityCacheUtil.getResult(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
				MemberRequestImpl.class, memberRequestId, this);

		if (memberRequest == null) {
			Session session = null;

			try {
				session = openSession();

				memberRequest = (MemberRequest)session.get(MemberRequestImpl.class,
						new Long(memberRequestId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (memberRequest != null) {
					cacheResult(memberRequest);
				}

				closeSession(session);
			}
		}

		return memberRequest;
	}

	public MemberRequest findByKey(String key)
		throws NoSuchMemberRequestException, SystemException {
		MemberRequest memberRequest = fetchByKey(key);

		if (memberRequest == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("key=");
			msg.append(key);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchMemberRequestException(msg.toString());
		}

		return memberRequest;
	}

	public MemberRequest fetchByKey(String key) throws SystemException {
		return fetchByKey(key, true);
	}

	public MemberRequest fetchByKey(String key, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { key };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_KEY,
					finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(3);

				query.append(_SQL_SELECT_MEMBERREQUEST_WHERE);

				if (key == null) {
					query.append(_FINDER_COLUMN_KEY_KEY_1);
				}
				else {
					if (key.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_KEY_KEY_3);
					}
					else {
						query.append(_FINDER_COLUMN_KEY_KEY_2);
					}
				}

				query.append(MemberRequestModelImpl.ORDER_BY_JPQL);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (key != null) {
					qPos.add(key);
				}

				List<MemberRequest> list = q.list();

				result = list;

				MemberRequest memberRequest = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KEY,
						finderArgs, list);
				}
				else {
					memberRequest = list.get(0);

					cacheResult(memberRequest);

					if ((memberRequest.getKey() == null) ||
							!memberRequest.getKey().equals(key)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KEY,
							finderArgs, memberRequest);
					}
				}

				return memberRequest;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KEY,
						finderArgs, new ArrayList<MemberRequest>());
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (MemberRequest)result;
			}
		}
	}

	public List<MemberRequest> findByReceiverUserId(long receiverUserId)
		throws SystemException {
		return findByReceiverUserId(receiverUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	public List<MemberRequest> findByReceiverUserId(long receiverUserId,
		int start, int end) throws SystemException {
		return findByReceiverUserId(receiverUserId, start, end, null);
	}

	public List<MemberRequest> findByReceiverUserId(long receiverUserId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				receiverUserId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<MemberRequest> list = (List<MemberRequest>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_RECEIVERUSERID,
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

				query.append(_SQL_SELECT_MEMBERREQUEST_WHERE);

				query.append(_FINDER_COLUMN_RECEIVERUSERID_RECEIVERUSERID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				else {
					query.append(MemberRequestModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(receiverUserId);

				list = (List<MemberRequest>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<MemberRequest>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_RECEIVERUSERID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public MemberRequest findByReceiverUserId_First(long receiverUserId,
		OrderByComparator orderByComparator)
		throws NoSuchMemberRequestException, SystemException {
		List<MemberRequest> list = findByReceiverUserId(receiverUserId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("receiverUserId=");
			msg.append(receiverUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchMemberRequestException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public MemberRequest findByReceiverUserId_Last(long receiverUserId,
		OrderByComparator orderByComparator)
		throws NoSuchMemberRequestException, SystemException {
		int count = countByReceiverUserId(receiverUserId);

		List<MemberRequest> list = findByReceiverUserId(receiverUserId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("receiverUserId=");
			msg.append(receiverUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchMemberRequestException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public MemberRequest[] findByReceiverUserId_PrevAndNext(
		long memberRequestId, long receiverUserId,
		OrderByComparator orderByComparator)
		throws NoSuchMemberRequestException, SystemException {
		MemberRequest memberRequest = findByPrimaryKey(memberRequestId);

		Session session = null;

		try {
			session = openSession();

			MemberRequest[] array = new MemberRequestImpl[3];

			array[0] = getByReceiverUserId_PrevAndNext(session, memberRequest,
					receiverUserId, orderByComparator, true);

			array[1] = memberRequest;

			array[2] = getByReceiverUserId_PrevAndNext(session, memberRequest,
					receiverUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MemberRequest getByReceiverUserId_PrevAndNext(Session session,
		MemberRequest memberRequest, long receiverUserId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MEMBERREQUEST_WHERE);

		query.append(_FINDER_COLUMN_RECEIVERUSERID_RECEIVERUSERID_2);

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
			query.append(MemberRequestModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(receiverUserId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(memberRequest);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MemberRequest> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	public List<MemberRequest> findByR_S(long receiverUserId, int status)
		throws SystemException {
		return findByR_S(receiverUserId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	public List<MemberRequest> findByR_S(long receiverUserId, int status,
		int start, int end) throws SystemException {
		return findByR_S(receiverUserId, status, start, end, null);
	}

	public List<MemberRequest> findByR_S(long receiverUserId, int status,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				receiverUserId, status,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<MemberRequest> list = (List<MemberRequest>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_R_S,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;

				if (orderByComparator != null) {
					query = new StringBundler(4 +
							(orderByComparator.getOrderByFields().length * 3));
				}
				else {
					query = new StringBundler(4);
				}

				query.append(_SQL_SELECT_MEMBERREQUEST_WHERE);

				query.append(_FINDER_COLUMN_R_S_RECEIVERUSERID_2);

				query.append(_FINDER_COLUMN_R_S_STATUS_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				else {
					query.append(MemberRequestModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(receiverUserId);

				qPos.add(status);

				list = (List<MemberRequest>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<MemberRequest>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_R_S, finderArgs,
					list);

				closeSession(session);
			}
		}

		return list;
	}

	public MemberRequest findByR_S_First(long receiverUserId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchMemberRequestException, SystemException {
		List<MemberRequest> list = findByR_S(receiverUserId, status, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("receiverUserId=");
			msg.append(receiverUserId);

			msg.append(", status=");
			msg.append(status);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchMemberRequestException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public MemberRequest findByR_S_Last(long receiverUserId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchMemberRequestException, SystemException {
		int count = countByR_S(receiverUserId, status);

		List<MemberRequest> list = findByR_S(receiverUserId, status, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("receiverUserId=");
			msg.append(receiverUserId);

			msg.append(", status=");
			msg.append(status);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchMemberRequestException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public MemberRequest[] findByR_S_PrevAndNext(long memberRequestId,
		long receiverUserId, int status, OrderByComparator orderByComparator)
		throws NoSuchMemberRequestException, SystemException {
		MemberRequest memberRequest = findByPrimaryKey(memberRequestId);

		Session session = null;

		try {
			session = openSession();

			MemberRequest[] array = new MemberRequestImpl[3];

			array[0] = getByR_S_PrevAndNext(session, memberRequest,
					receiverUserId, status, orderByComparator, true);

			array[1] = memberRequest;

			array[2] = getByR_S_PrevAndNext(session, memberRequest,
					receiverUserId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MemberRequest getByR_S_PrevAndNext(Session session,
		MemberRequest memberRequest, long receiverUserId, int status,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MEMBERREQUEST_WHERE);

		query.append(_FINDER_COLUMN_R_S_RECEIVERUSERID_2);

		query.append(_FINDER_COLUMN_R_S_STATUS_2);

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
			query.append(MemberRequestModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(receiverUserId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(memberRequest);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MemberRequest> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	public MemberRequest findByG_R_S(long groupId, long receiverUserId,
		int status) throws NoSuchMemberRequestException, SystemException {
		MemberRequest memberRequest = fetchByG_R_S(groupId, receiverUserId,
				status);

		if (memberRequest == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", receiverUserId=");
			msg.append(receiverUserId);

			msg.append(", status=");
			msg.append(status);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchMemberRequestException(msg.toString());
		}

		return memberRequest;
	}

	public MemberRequest fetchByG_R_S(long groupId, long receiverUserId,
		int status) throws SystemException {
		return fetchByG_R_S(groupId, receiverUserId, status, true);
	}

	public MemberRequest fetchByG_R_S(long groupId, long receiverUserId,
		int status, boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { groupId, receiverUserId, status };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_G_R_S,
					finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(5);

				query.append(_SQL_SELECT_MEMBERREQUEST_WHERE);

				query.append(_FINDER_COLUMN_G_R_S_GROUPID_2);

				query.append(_FINDER_COLUMN_G_R_S_RECEIVERUSERID_2);

				query.append(_FINDER_COLUMN_G_R_S_STATUS_2);

				query.append(MemberRequestModelImpl.ORDER_BY_JPQL);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(receiverUserId);

				qPos.add(status);

				List<MemberRequest> list = q.list();

				result = list;

				MemberRequest memberRequest = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_R_S,
						finderArgs, list);
				}
				else {
					memberRequest = list.get(0);

					cacheResult(memberRequest);

					if ((memberRequest.getGroupId() != groupId) ||
							(memberRequest.getReceiverUserId() != receiverUserId) ||
							(memberRequest.getStatus() != status)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_R_S,
							finderArgs, memberRequest);
					}
				}

				return memberRequest;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_R_S,
						finderArgs, new ArrayList<MemberRequest>());
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (MemberRequest)result;
			}
		}
	}

	public List<MemberRequest> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<MemberRequest> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<MemberRequest> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<MemberRequest> list = (List<MemberRequest>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_MEMBERREQUEST);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}
				else {
					sql = _SQL_SELECT_MEMBERREQUEST.concat(MemberRequestModelImpl.ORDER_BY_JPQL);
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<MemberRequest>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<MemberRequest>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<MemberRequest>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByKey(String key)
		throws NoSuchMemberRequestException, SystemException {
		MemberRequest memberRequest = findByKey(key);

		remove(memberRequest);
	}

	public void removeByReceiverUserId(long receiverUserId)
		throws SystemException {
		for (MemberRequest memberRequest : findByReceiverUserId(receiverUserId)) {
			remove(memberRequest);
		}
	}

	public void removeByR_S(long receiverUserId, int status)
		throws SystemException {
		for (MemberRequest memberRequest : findByR_S(receiverUserId, status)) {
			remove(memberRequest);
		}
	}

	public void removeByG_R_S(long groupId, long receiverUserId, int status)
		throws NoSuchMemberRequestException, SystemException {
		MemberRequest memberRequest = findByG_R_S(groupId, receiverUserId,
				status);

		remove(memberRequest);
	}

	public void removeAll() throws SystemException {
		for (MemberRequest memberRequest : findAll()) {
			remove(memberRequest);
		}
	}

	public int countByKey(String key) throws SystemException {
		Object[] finderArgs = new Object[] { key };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KEY,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_MEMBERREQUEST_WHERE);

				if (key == null) {
					query.append(_FINDER_COLUMN_KEY_KEY_1);
				}
				else {
					if (key.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_KEY_KEY_3);
					}
					else {
						query.append(_FINDER_COLUMN_KEY_KEY_2);
					}
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (key != null) {
					qPos.add(key);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KEY, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByReceiverUserId(long receiverUserId)
		throws SystemException {
		Object[] finderArgs = new Object[] { receiverUserId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_RECEIVERUSERID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_MEMBERREQUEST_WHERE);

				query.append(_FINDER_COLUMN_RECEIVERUSERID_RECEIVERUSERID_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(receiverUserId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_RECEIVERUSERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByR_S(long receiverUserId, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] { receiverUserId, status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_R_S,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(3);

				query.append(_SQL_COUNT_MEMBERREQUEST_WHERE);

				query.append(_FINDER_COLUMN_R_S_RECEIVERUSERID_2);

				query.append(_FINDER_COLUMN_R_S_STATUS_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(receiverUserId);

				qPos.add(status);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_R_S, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByG_R_S(long groupId, long receiverUserId, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] { groupId, receiverUserId, status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_R_S,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(4);

				query.append(_SQL_COUNT_MEMBERREQUEST_WHERE);

				query.append(_FINDER_COLUMN_G_R_S_GROUPID_2);

				query.append(_FINDER_COLUMN_G_R_S_RECEIVERUSERID_2);

				query.append(_FINDER_COLUMN_G_R_S_STATUS_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(receiverUserId);

				qPos.add(status);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_R_S,
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

				Query q = session.createQuery(_SQL_COUNT_MEMBERREQUEST);

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
						"value.object.listener.com.liferay.so.model.MemberRequest")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<MemberRequest>> listenersList = new ArrayList<ModelListener<MemberRequest>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<MemberRequest>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(type = MemberRequestPersistence.class)
	protected MemberRequestPersistence memberRequestPersistence;
	@BeanReference(type = ProjectsEntryPersistence.class)
	protected ProjectsEntryPersistence projectsEntryPersistence;
	@BeanReference(type = GroupPersistence.class)
	protected GroupPersistence groupPersistence;
	@BeanReference(type = LayoutPersistence.class)
	protected LayoutPersistence layoutPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = UserGroupRolePersistence.class)
	protected UserGroupRolePersistence userGroupRolePersistence;
	private static final String _SQL_SELECT_MEMBERREQUEST = "SELECT memberRequest FROM MemberRequest memberRequest";
	private static final String _SQL_SELECT_MEMBERREQUEST_WHERE = "SELECT memberRequest FROM MemberRequest memberRequest WHERE ";
	private static final String _SQL_COUNT_MEMBERREQUEST = "SELECT COUNT(memberRequest) FROM MemberRequest memberRequest";
	private static final String _SQL_COUNT_MEMBERREQUEST_WHERE = "SELECT COUNT(memberRequest) FROM MemberRequest memberRequest WHERE ";
	private static final String _FINDER_COLUMN_KEY_KEY_1 = "memberRequest.key IS NULL";
	private static final String _FINDER_COLUMN_KEY_KEY_2 = "memberRequest.key = ?";
	private static final String _FINDER_COLUMN_KEY_KEY_3 = "(memberRequest.key IS NULL OR memberRequest.key = ?)";
	private static final String _FINDER_COLUMN_RECEIVERUSERID_RECEIVERUSERID_2 = "memberRequest.receiverUserId = ?";
	private static final String _FINDER_COLUMN_R_S_RECEIVERUSERID_2 = "memberRequest.receiverUserId = ? AND ";
	private static final String _FINDER_COLUMN_R_S_STATUS_2 = "memberRequest.status = ?";
	private static final String _FINDER_COLUMN_G_R_S_GROUPID_2 = "memberRequest.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_R_S_RECEIVERUSERID_2 = "memberRequest.receiverUserId = ? AND ";
	private static final String _FINDER_COLUMN_G_R_S_STATUS_2 = "memberRequest.status = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "memberRequest.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MemberRequest exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No MemberRequest exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(MemberRequestPersistenceImpl.class);
}