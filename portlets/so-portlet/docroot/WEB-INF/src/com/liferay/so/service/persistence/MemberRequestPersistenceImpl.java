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

package com.liferay.so.service.persistence;

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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
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
 * The persistence implementation for the member request service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MemberRequestPersistence
 * @see MemberRequestUtil
 * @generated
 */
public class MemberRequestPersistenceImpl extends BasePersistenceImpl<MemberRequest>
	implements MemberRequestPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MemberRequestUtil} to access the member request persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MemberRequestImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_FETCH_BY_KEY = new FinderPath(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestModelImpl.FINDER_CACHE_ENABLED,
			MemberRequestImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByKey",
			new String[] { String.class.getName() },
			MemberRequestModelImpl.KEY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KEY = new FinderPath(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByKey",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_RECEIVERUSERID =
		new FinderPath(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestModelImpl.FINDER_CACHE_ENABLED,
			MemberRequestImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByReceiverUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RECEIVERUSERID =
		new FinderPath(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestModelImpl.FINDER_CACHE_ENABLED,
			MemberRequestImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByReceiverUserId", new String[] { Long.class.getName() },
			MemberRequestModelImpl.RECEIVERUSERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_RECEIVERUSERID = new FinderPath(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByReceiverUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_R_S = new FinderPath(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestModelImpl.FINDER_CACHE_ENABLED,
			MemberRequestImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByR_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_S = new FinderPath(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestModelImpl.FINDER_CACHE_ENABLED,
			MemberRequestImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByR_S",
			new String[] { Long.class.getName(), Integer.class.getName() },
			MemberRequestModelImpl.RECEIVERUSERID_COLUMN_BITMASK |
			MemberRequestModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_R_S = new FinderPath(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByR_S",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_G_R_S = new FinderPath(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestModelImpl.FINDER_CACHE_ENABLED,
			MemberRequestImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByG_R_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			MemberRequestModelImpl.GROUPID_COLUMN_BITMASK |
			MemberRequestModelImpl.RECEIVERUSERID_COLUMN_BITMASK |
			MemberRequestModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_R_S = new FinderPath(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_R_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestModelImpl.FINDER_CACHE_ENABLED,
			MemberRequestImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestModelImpl.FINDER_CACHE_ENABLED,
			MemberRequestImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the member request in the entity cache if it is enabled.
	 *
	 * @param memberRequest the member request
	 */
	public void cacheResult(MemberRequest memberRequest) {
		EntityCacheUtil.putResult(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestImpl.class, memberRequest.getPrimaryKey(),
			memberRequest);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KEY,
			new Object[] { memberRequest.getKey() }, memberRequest);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_R_S,
			new Object[] {
				Long.valueOf(memberRequest.getGroupId()),
				Long.valueOf(memberRequest.getReceiverUserId()),
				Integer.valueOf(memberRequest.getStatus())
			}, memberRequest);

		memberRequest.resetOriginalValues();
	}

	/**
	 * Caches the member requests in the entity cache if it is enabled.
	 *
	 * @param memberRequests the member requests
	 */
	public void cacheResult(List<MemberRequest> memberRequests) {
		for (MemberRequest memberRequest : memberRequests) {
			if (EntityCacheUtil.getResult(
						MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
						MemberRequestImpl.class, memberRequest.getPrimaryKey()) == null) {
				cacheResult(memberRequest);
			}
			else {
				memberRequest.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all member requests.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(MemberRequestImpl.class.getName());
		}

		EntityCacheUtil.clearCache(MemberRequestImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the member request.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MemberRequest memberRequest) {
		EntityCacheUtil.removeResult(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestImpl.class, memberRequest.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(memberRequest);
	}

	@Override
	public void clearCache(List<MemberRequest> memberRequests) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MemberRequest memberRequest : memberRequests) {
			EntityCacheUtil.removeResult(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
				MemberRequestImpl.class, memberRequest.getPrimaryKey());

			clearUniqueFindersCache(memberRequest);
		}
	}

	protected void clearUniqueFindersCache(MemberRequest memberRequest) {
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KEY,
			new Object[] { memberRequest.getKey() });

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_R_S,
			new Object[] {
				Long.valueOf(memberRequest.getGroupId()),
				Long.valueOf(memberRequest.getReceiverUserId()),
				Integer.valueOf(memberRequest.getStatus())
			});
	}

	/**
	 * Creates a new member request with the primary key. Does not add the member request to the database.
	 *
	 * @param memberRequestId the primary key for the new member request
	 * @return the new member request
	 */
	public MemberRequest create(long memberRequestId) {
		MemberRequest memberRequest = new MemberRequestImpl();

		memberRequest.setNew(true);
		memberRequest.setPrimaryKey(memberRequestId);

		return memberRequest;
	}

	/**
	 * Removes the member request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param memberRequestId the primary key of the member request
	 * @return the member request that was removed
	 * @throws com.liferay.so.NoSuchMemberRequestException if a member request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MemberRequest remove(long memberRequestId)
		throws NoSuchMemberRequestException, SystemException {
		return remove(Long.valueOf(memberRequestId));
	}

	/**
	 * Removes the member request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the member request
	 * @return the member request that was removed
	 * @throws com.liferay.so.NoSuchMemberRequestException if a member request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MemberRequest remove(Serializable primaryKey)
		throws NoSuchMemberRequestException, SystemException {
		Session session = null;

		try {
			session = openSession();

			MemberRequest memberRequest = (MemberRequest)session.get(MemberRequestImpl.class,
					primaryKey);

			if (memberRequest == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMemberRequestException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
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

	@Override
	protected MemberRequest removeImpl(MemberRequest memberRequest)
		throws SystemException {
		memberRequest = toUnwrappedModel(memberRequest);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, memberRequest);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(memberRequest);

		return memberRequest;
	}

	@Override
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

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !MemberRequestModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else {
			if ((memberRequestModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RECEIVERUSERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(memberRequestModelImpl.getOriginalReceiverUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_RECEIVERUSERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RECEIVERUSERID,
					args);

				args = new Object[] {
						Long.valueOf(memberRequestModelImpl.getReceiverUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_RECEIVERUSERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RECEIVERUSERID,
					args);
			}

			if ((memberRequestModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(memberRequestModelImpl.getOriginalReceiverUserId()),
						Integer.valueOf(memberRequestModelImpl.getOriginalStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_S,
					args);

				args = new Object[] {
						Long.valueOf(memberRequestModelImpl.getReceiverUserId()),
						Integer.valueOf(memberRequestModelImpl.getStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_S,
					args);
			}
		}

		EntityCacheUtil.putResult(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestImpl.class, memberRequest.getPrimaryKey(),
			memberRequest);

		if (isNew) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KEY,
				new Object[] { memberRequest.getKey() }, memberRequest);

			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_R_S,
				new Object[] {
					Long.valueOf(memberRequest.getGroupId()),
					Long.valueOf(memberRequest.getReceiverUserId()),
					Integer.valueOf(memberRequest.getStatus())
				}, memberRequest);
		}
		else {
			if ((memberRequestModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_KEY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						memberRequestModelImpl.getOriginalKey()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KEY, args);
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KEY, args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KEY,
					new Object[] { memberRequest.getKey() }, memberRequest);
			}

			if ((memberRequestModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_G_R_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(memberRequestModelImpl.getOriginalGroupId()),
						Long.valueOf(memberRequestModelImpl.getOriginalReceiverUserId()),
						Integer.valueOf(memberRequestModelImpl.getOriginalStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_R_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_R_S, args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_R_S,
					new Object[] {
						Long.valueOf(memberRequest.getGroupId()),
						Long.valueOf(memberRequest.getReceiverUserId()),
						Integer.valueOf(memberRequest.getStatus())
					}, memberRequest);
			}
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

	/**
	 * Returns the member request with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the member request
	 * @return the member request
	 * @throws com.liferay.portal.NoSuchModelException if a member request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MemberRequest findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the member request with the primary key or throws a {@link com.liferay.so.NoSuchMemberRequestException} if it could not be found.
	 *
	 * @param memberRequestId the primary key of the member request
	 * @return the member request
	 * @throws com.liferay.so.NoSuchMemberRequestException if a member request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
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

	/**
	 * Returns the member request with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the member request
	 * @return the member request, or <code>null</code> if a member request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MemberRequest fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the member request with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param memberRequestId the primary key of the member request
	 * @return the member request, or <code>null</code> if a member request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MemberRequest fetchByPrimaryKey(long memberRequestId)
		throws SystemException {
		MemberRequest memberRequest = (MemberRequest)EntityCacheUtil.getResult(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
				MemberRequestImpl.class, memberRequestId);

		if (memberRequest == _nullMemberRequest) {
			return null;
		}

		if (memberRequest == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				memberRequest = (MemberRequest)session.get(MemberRequestImpl.class,
						Long.valueOf(memberRequestId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (memberRequest != null) {
					cacheResult(memberRequest);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
						MemberRequestImpl.class, memberRequestId,
						_nullMemberRequest);
				}

				closeSession(session);
			}
		}

		return memberRequest;
	}

	/**
	 * Returns the member request where key = &#63; or throws a {@link com.liferay.so.NoSuchMemberRequestException} if it could not be found.
	 *
	 * @param key the key
	 * @return the matching member request
	 * @throws com.liferay.so.NoSuchMemberRequestException if a matching member request could not be found
	 * @throws SystemException if a system exception occurred
	 */
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

	/**
	 * Returns the member request where key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param key the key
	 * @return the matching member request, or <code>null</code> if a matching member request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MemberRequest fetchByKey(String key) throws SystemException {
		return fetchByKey(key, true);
	}

	/**
	 * Returns the member request where key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param key the key
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching member request, or <code>null</code> if a matching member request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MemberRequest fetchByKey(String key, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { key };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_KEY,
					finderArgs, this);
		}

		if (result instanceof MemberRequest) {
			MemberRequest memberRequest = (MemberRequest)result;

			if (!Validator.equals(key, memberRequest.getKey())) {
				result = null;
			}
		}

		if (result == null) {
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

			Session session = null;

			try {
				session = openSession();

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
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KEY,
						finderArgs);
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

	/**
	 * Returns all the member requests where receiverUserId = &#63;.
	 *
	 * @param receiverUserId the receiver user ID
	 * @return the matching member requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<MemberRequest> findByReceiverUserId(long receiverUserId)
		throws SystemException {
		return findByReceiverUserId(receiverUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the member requests where receiverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param receiverUserId the receiver user ID
	 * @param start the lower bound of the range of member requests
	 * @param end the upper bound of the range of member requests (not inclusive)
	 * @return the range of matching member requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<MemberRequest> findByReceiverUserId(long receiverUserId,
		int start, int end) throws SystemException {
		return findByReceiverUserId(receiverUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the member requests where receiverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param receiverUserId the receiver user ID
	 * @param start the lower bound of the range of member requests
	 * @param end the upper bound of the range of member requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching member requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<MemberRequest> findByReceiverUserId(long receiverUserId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RECEIVERUSERID;
			finderArgs = new Object[] { receiverUserId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_RECEIVERUSERID;
			finderArgs = new Object[] {
					receiverUserId,
					
					start, end, orderByComparator
				};
		}

		List<MemberRequest> list = (List<MemberRequest>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (MemberRequest memberRequest : list) {
				if ((receiverUserId != memberRequest.getReceiverUserId())) {
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

			Session session = null;

			try {
				session = openSession();

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
	 * Returns the first member request in the ordered set where receiverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param receiverUserId the receiver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching member request
	 * @throws com.liferay.so.NoSuchMemberRequestException if a matching member request could not be found
	 * @throws SystemException if a system exception occurred
	 */
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

	/**
	 * Returns the last member request in the ordered set where receiverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param receiverUserId the receiver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching member request
	 * @throws com.liferay.so.NoSuchMemberRequestException if a matching member request could not be found
	 * @throws SystemException if a system exception occurred
	 */
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

	/**
	 * Returns the member requests before and after the current member request in the ordered set where receiverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param memberRequestId the primary key of the current member request
	 * @param receiverUserId the receiver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next member request
	 * @throws com.liferay.so.NoSuchMemberRequestException if a member request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
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
			query.append(MemberRequestModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(receiverUserId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(memberRequest);

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

	/**
	 * Returns all the member requests where receiverUserId = &#63; and status = &#63;.
	 *
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @return the matching member requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<MemberRequest> findByR_S(long receiverUserId, int status)
		throws SystemException {
		return findByR_S(receiverUserId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the member requests where receiverUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @param start the lower bound of the range of member requests
	 * @param end the upper bound of the range of member requests (not inclusive)
	 * @return the range of matching member requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<MemberRequest> findByR_S(long receiverUserId, int status,
		int start, int end) throws SystemException {
		return findByR_S(receiverUserId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the member requests where receiverUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @param start the lower bound of the range of member requests
	 * @param end the upper bound of the range of member requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching member requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<MemberRequest> findByR_S(long receiverUserId, int status,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_S;
			finderArgs = new Object[] { receiverUserId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_R_S;
			finderArgs = new Object[] {
					receiverUserId, status,
					
					start, end, orderByComparator
				};
		}

		List<MemberRequest> list = (List<MemberRequest>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (MemberRequest memberRequest : list) {
				if ((receiverUserId != memberRequest.getReceiverUserId()) ||
						(status != memberRequest.getStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
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

			Session session = null;

			try {
				session = openSession();

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
	 * Returns the first member request in the ordered set where receiverUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching member request
	 * @throws com.liferay.so.NoSuchMemberRequestException if a matching member request could not be found
	 * @throws SystemException if a system exception occurred
	 */
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

	/**
	 * Returns the last member request in the ordered set where receiverUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching member request
	 * @throws com.liferay.so.NoSuchMemberRequestException if a matching member request could not be found
	 * @throws SystemException if a system exception occurred
	 */
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

	/**
	 * Returns the member requests before and after the current member request in the ordered set where receiverUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param memberRequestId the primary key of the current member request
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next member request
	 * @throws com.liferay.so.NoSuchMemberRequestException if a member request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
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
			Object[] values = orderByComparator.getOrderByConditionValues(memberRequest);

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

	/**
	 * Returns the member request where groupId = &#63; and receiverUserId = &#63; and status = &#63; or throws a {@link com.liferay.so.NoSuchMemberRequestException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @return the matching member request
	 * @throws com.liferay.so.NoSuchMemberRequestException if a matching member request could not be found
	 * @throws SystemException if a system exception occurred
	 */
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

	/**
	 * Returns the member request where groupId = &#63; and receiverUserId = &#63; and status = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @return the matching member request, or <code>null</code> if a matching member request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MemberRequest fetchByG_R_S(long groupId, long receiverUserId,
		int status) throws SystemException {
		return fetchByG_R_S(groupId, receiverUserId, status, true);
	}

	/**
	 * Returns the member request where groupId = &#63; and receiverUserId = &#63; and status = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching member request, or <code>null</code> if a matching member request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MemberRequest fetchByG_R_S(long groupId, long receiverUserId,
		int status, boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { groupId, receiverUserId, status };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_G_R_S,
					finderArgs, this);
		}

		if (result instanceof MemberRequest) {
			MemberRequest memberRequest = (MemberRequest)result;

			if ((groupId != memberRequest.getGroupId()) ||
					(receiverUserId != memberRequest.getReceiverUserId()) ||
					(status != memberRequest.getStatus())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_MEMBERREQUEST_WHERE);

			query.append(_FINDER_COLUMN_G_R_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_R_S_RECEIVERUSERID_2);

			query.append(_FINDER_COLUMN_G_R_S_STATUS_2);

			query.append(MemberRequestModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

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
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_R_S,
						finderArgs);
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

	/**
	 * Returns all the member requests.
	 *
	 * @return the member requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<MemberRequest> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the member requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of member requests
	 * @param end the upper bound of the range of member requests (not inclusive)
	 * @return the range of member requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<MemberRequest> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the member requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of member requests
	 * @param end the upper bound of the range of member requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of member requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<MemberRequest> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<MemberRequest> list = (List<MemberRequest>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
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

			Session session = null;

			try {
				session = openSession();

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
	 * Removes the member request where key = &#63; from the database.
	 *
	 * @param key the key
	 * @return the member request that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public MemberRequest removeByKey(String key)
		throws NoSuchMemberRequestException, SystemException {
		MemberRequest memberRequest = findByKey(key);

		return remove(memberRequest);
	}

	/**
	 * Removes all the member requests where receiverUserId = &#63; from the database.
	 *
	 * @param receiverUserId the receiver user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByReceiverUserId(long receiverUserId)
		throws SystemException {
		for (MemberRequest memberRequest : findByReceiverUserId(receiverUserId)) {
			remove(memberRequest);
		}
	}

	/**
	 * Removes all the member requests where receiverUserId = &#63; and status = &#63; from the database.
	 *
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByR_S(long receiverUserId, int status)
		throws SystemException {
		for (MemberRequest memberRequest : findByR_S(receiverUserId, status)) {
			remove(memberRequest);
		}
	}

	/**
	 * Removes the member request where groupId = &#63; and receiverUserId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @return the member request that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public MemberRequest removeByG_R_S(long groupId, long receiverUserId,
		int status) throws NoSuchMemberRequestException, SystemException {
		MemberRequest memberRequest = findByG_R_S(groupId, receiverUserId,
				status);

		return remove(memberRequest);
	}

	/**
	 * Removes all the member requests from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (MemberRequest memberRequest : findAll()) {
			remove(memberRequest);
		}
	}

	/**
	 * Returns the number of member requests where key = &#63;.
	 *
	 * @param key the key
	 * @return the number of matching member requests
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKey(String key) throws SystemException {
		Object[] finderArgs = new Object[] { key };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KEY,
				finderArgs, this);

		if (count == null) {
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

			Session session = null;

			try {
				session = openSession();

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

	/**
	 * Returns the number of member requests where receiverUserId = &#63;.
	 *
	 * @param receiverUserId the receiver user ID
	 * @return the number of matching member requests
	 * @throws SystemException if a system exception occurred
	 */
	public int countByReceiverUserId(long receiverUserId)
		throws SystemException {
		Object[] finderArgs = new Object[] { receiverUserId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_RECEIVERUSERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MEMBERREQUEST_WHERE);

			query.append(_FINDER_COLUMN_RECEIVERUSERID_RECEIVERUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

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

	/**
	 * Returns the number of member requests where receiverUserId = &#63; and status = &#63;.
	 *
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @return the number of matching member requests
	 * @throws SystemException if a system exception occurred
	 */
	public int countByR_S(long receiverUserId, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] { receiverUserId, status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_R_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MEMBERREQUEST_WHERE);

			query.append(_FINDER_COLUMN_R_S_RECEIVERUSERID_2);

			query.append(_FINDER_COLUMN_R_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

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

	/**
	 * Returns the number of member requests where groupId = &#63; and receiverUserId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @return the number of matching member requests
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_R_S(long groupId, long receiverUserId, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] { groupId, receiverUserId, status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_R_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_MEMBERREQUEST_WHERE);

			query.append(_FINDER_COLUMN_G_R_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_R_S_RECEIVERUSERID_2);

			query.append(_FINDER_COLUMN_G_R_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

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

	/**
	 * Returns the number of member requests.
	 *
	 * @return the number of member requests
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the member request persistence.
	 */
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

	public void destroy() {
		EntityCacheUtil.removeCache(MemberRequestImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = FavoriteSitePersistence.class)
	protected FavoriteSitePersistence favoriteSitePersistence;
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
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(MemberRequestPersistenceImpl.class);
	private static MemberRequest _nullMemberRequest = new MemberRequestImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<MemberRequest> toCacheModel() {
				return _nullMemberRequestCacheModel;
			}
		};

	private static CacheModel<MemberRequest> _nullMemberRequestCacheModel = new CacheModel<MemberRequest>() {
			public MemberRequest toEntityModel() {
				return _nullMemberRequest;
			}
		};
}