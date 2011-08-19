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

package com.liferay.opensocial.service.persistence;

import com.liferay.opensocial.NoSuchOAuthTokenException;
import com.liferay.opensocial.model.OAuthToken;
import com.liferay.opensocial.model.impl.OAuthTokenImpl;
import com.liferay.opensocial.model.impl.OAuthTokenModelImpl;

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
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the o auth token service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OAuthTokenPersistence
 * @see OAuthTokenUtil
 * @generated
 */
public class OAuthTokenPersistenceImpl extends BasePersistenceImpl<OAuthToken>
	implements OAuthTokenPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link OAuthTokenUtil} to access the o auth token persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = OAuthTokenImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_G_S = new FinderPath(OAuthTokenModelImpl.ENTITY_CACHE_ENABLED,
			OAuthTokenModelImpl.FINDER_CACHE_ENABLED, OAuthTokenImpl.class,
			FINDER_CLASS_NAME_LIST, "findByG_S",
			new String[] {
				String.class.getName(), String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_G_S = new FinderPath(OAuthTokenModelImpl.ENTITY_CACHE_ENABLED,
			OAuthTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByG_S",
			new String[] { String.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_U_G_S_M_T = new FinderPath(OAuthTokenModelImpl.ENTITY_CACHE_ENABLED,
			OAuthTokenModelImpl.FINDER_CACHE_ENABLED, OAuthTokenImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByU_G_S_M_T",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), Long.class.getName(),
				String.class.getName()
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_U_G_S_M_T = new FinderPath(OAuthTokenModelImpl.ENTITY_CACHE_ENABLED,
			OAuthTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByU_G_S_M_T",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), Long.class.getName(),
				String.class.getName()
			});
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(OAuthTokenModelImpl.ENTITY_CACHE_ENABLED,
			OAuthTokenModelImpl.FINDER_CACHE_ENABLED, OAuthTokenImpl.class,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OAuthTokenModelImpl.ENTITY_CACHE_ENABLED,
			OAuthTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the o auth token in the entity cache if it is enabled.
	 *
	 * @param oAuthToken the o auth token
	 */
	public void cacheResult(OAuthToken oAuthToken) {
		EntityCacheUtil.putResult(OAuthTokenModelImpl.ENTITY_CACHE_ENABLED,
			OAuthTokenImpl.class, oAuthToken.getPrimaryKey(), oAuthToken);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_G_S_M_T,
			new Object[] {
				Long.valueOf(oAuthToken.getUserId()),
				
			oAuthToken.getGadgetKey(),
				
			oAuthToken.getServiceName(), Long.valueOf(oAuthToken.getModuleId()),
				
			oAuthToken.getTokenName()
			}, oAuthToken);

		oAuthToken.resetOriginalValues();
	}

	/**
	 * Caches the o auth tokens in the entity cache if it is enabled.
	 *
	 * @param oAuthTokens the o auth tokens
	 */
	public void cacheResult(List<OAuthToken> oAuthTokens) {
		for (OAuthToken oAuthToken : oAuthTokens) {
			if (EntityCacheUtil.getResult(
						OAuthTokenModelImpl.ENTITY_CACHE_ENABLED,
						OAuthTokenImpl.class, oAuthToken.getPrimaryKey(), this) == null) {
				cacheResult(oAuthToken);
			}
		}
	}

	/**
	 * Clears the cache for all o auth tokens.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(OAuthTokenImpl.class.getName());
		}

		EntityCacheUtil.clearCache(OAuthTokenImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the o auth token.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(OAuthToken oAuthToken) {
		EntityCacheUtil.removeResult(OAuthTokenModelImpl.ENTITY_CACHE_ENABLED,
			OAuthTokenImpl.class, oAuthToken.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_G_S_M_T,
			new Object[] {
				Long.valueOf(oAuthToken.getUserId()),
				
			oAuthToken.getGadgetKey(),
				
			oAuthToken.getServiceName(), Long.valueOf(oAuthToken.getModuleId()),
				
			oAuthToken.getTokenName()
			});
	}

	/**
	 * Creates a new o auth token with the primary key. Does not add the o auth token to the database.
	 *
	 * @param oAuthTokenId the primary key for the new o auth token
	 * @return the new o auth token
	 */
	public OAuthToken create(long oAuthTokenId) {
		OAuthToken oAuthToken = new OAuthTokenImpl();

		oAuthToken.setNew(true);
		oAuthToken.setPrimaryKey(oAuthTokenId);

		return oAuthToken;
	}

	/**
	 * Removes the o auth token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the o auth token
	 * @return the o auth token that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a o auth token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthToken remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the o auth token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param oAuthTokenId the primary key of the o auth token
	 * @return the o auth token that was removed
	 * @throws com.liferay.opensocial.NoSuchOAuthTokenException if a o auth token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthToken remove(long oAuthTokenId)
		throws NoSuchOAuthTokenException, SystemException {
		Session session = null;

		try {
			session = openSession();

			OAuthToken oAuthToken = (OAuthToken)session.get(OAuthTokenImpl.class,
					Long.valueOf(oAuthTokenId));

			if (oAuthToken == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + oAuthTokenId);
				}

				throw new NoSuchOAuthTokenException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					oAuthTokenId);
			}

			return oAuthTokenPersistence.remove(oAuthToken);
		}
		catch (NoSuchOAuthTokenException nsee) {
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
	 * Removes the o auth token from the database. Also notifies the appropriate model listeners.
	 *
	 * @param oAuthToken the o auth token
	 * @return the o auth token that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthToken remove(OAuthToken oAuthToken) throws SystemException {
		return super.remove(oAuthToken);
	}

	@Override
	protected OAuthToken removeImpl(OAuthToken oAuthToken)
		throws SystemException {
		oAuthToken = toUnwrappedModel(oAuthToken);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, oAuthToken);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		OAuthTokenModelImpl oAuthTokenModelImpl = (OAuthTokenModelImpl)oAuthToken;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_G_S_M_T,
			new Object[] {
				Long.valueOf(oAuthTokenModelImpl.getUserId()),
				
			oAuthTokenModelImpl.getGadgetKey(),
				
			oAuthTokenModelImpl.getServiceName(),
				Long.valueOf(oAuthTokenModelImpl.getModuleId()),
				
			oAuthTokenModelImpl.getTokenName()
			});

		EntityCacheUtil.removeResult(OAuthTokenModelImpl.ENTITY_CACHE_ENABLED,
			OAuthTokenImpl.class, oAuthToken.getPrimaryKey());

		return oAuthToken;
	}

	@Override
	public OAuthToken updateImpl(
		com.liferay.opensocial.model.OAuthToken oAuthToken, boolean merge)
		throws SystemException {
		oAuthToken = toUnwrappedModel(oAuthToken);

		boolean isNew = oAuthToken.isNew();

		OAuthTokenModelImpl oAuthTokenModelImpl = (OAuthTokenModelImpl)oAuthToken;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, oAuthToken, merge);

			oAuthToken.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(OAuthTokenModelImpl.ENTITY_CACHE_ENABLED,
			OAuthTokenImpl.class, oAuthToken.getPrimaryKey(), oAuthToken);

		if (!isNew &&
				((oAuthToken.getUserId() != oAuthTokenModelImpl.getOriginalUserId()) ||
				!Validator.equals(oAuthToken.getGadgetKey(),
					oAuthTokenModelImpl.getOriginalGadgetKey()) ||
				!Validator.equals(oAuthToken.getServiceName(),
					oAuthTokenModelImpl.getOriginalServiceName()) ||
				(oAuthToken.getModuleId() != oAuthTokenModelImpl.getOriginalModuleId()) ||
				!Validator.equals(oAuthToken.getTokenName(),
					oAuthTokenModelImpl.getOriginalTokenName()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_G_S_M_T,
				new Object[] {
					Long.valueOf(oAuthTokenModelImpl.getOriginalUserId()),
					
				oAuthTokenModelImpl.getOriginalGadgetKey(),
					
				oAuthTokenModelImpl.getOriginalServiceName(),
					Long.valueOf(oAuthTokenModelImpl.getOriginalModuleId()),
					
				oAuthTokenModelImpl.getOriginalTokenName()
				});
		}

		if (isNew ||
				((oAuthToken.getUserId() != oAuthTokenModelImpl.getOriginalUserId()) ||
				!Validator.equals(oAuthToken.getGadgetKey(),
					oAuthTokenModelImpl.getOriginalGadgetKey()) ||
				!Validator.equals(oAuthToken.getServiceName(),
					oAuthTokenModelImpl.getOriginalServiceName()) ||
				(oAuthToken.getModuleId() != oAuthTokenModelImpl.getOriginalModuleId()) ||
				!Validator.equals(oAuthToken.getTokenName(),
					oAuthTokenModelImpl.getOriginalTokenName()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_G_S_M_T,
				new Object[] {
					Long.valueOf(oAuthToken.getUserId()),
					
				oAuthToken.getGadgetKey(),
					
				oAuthToken.getServiceName(),
					Long.valueOf(oAuthToken.getModuleId()),
					
				oAuthToken.getTokenName()
				}, oAuthToken);
		}

		return oAuthToken;
	}

	protected OAuthToken toUnwrappedModel(OAuthToken oAuthToken) {
		if (oAuthToken instanceof OAuthTokenImpl) {
			return oAuthToken;
		}

		OAuthTokenImpl oAuthTokenImpl = new OAuthTokenImpl();

		oAuthTokenImpl.setNew(oAuthToken.isNew());
		oAuthTokenImpl.setPrimaryKey(oAuthToken.getPrimaryKey());

		oAuthTokenImpl.setOAuthTokenId(oAuthToken.getOAuthTokenId());
		oAuthTokenImpl.setCompanyId(oAuthToken.getCompanyId());
		oAuthTokenImpl.setUserId(oAuthToken.getUserId());
		oAuthTokenImpl.setUserName(oAuthToken.getUserName());
		oAuthTokenImpl.setCreateDate(oAuthToken.getCreateDate());
		oAuthTokenImpl.setModifiedDate(oAuthToken.getModifiedDate());
		oAuthTokenImpl.setGadgetKey(oAuthToken.getGadgetKey());
		oAuthTokenImpl.setServiceName(oAuthToken.getServiceName());
		oAuthTokenImpl.setModuleId(oAuthToken.getModuleId());
		oAuthTokenImpl.setAccessToken(oAuthToken.getAccessToken());
		oAuthTokenImpl.setTokenName(oAuthToken.getTokenName());
		oAuthTokenImpl.setTokenSecret(oAuthToken.getTokenSecret());
		oAuthTokenImpl.setSessionHandle(oAuthToken.getSessionHandle());
		oAuthTokenImpl.setExpiration(oAuthToken.getExpiration());

		return oAuthTokenImpl;
	}

	/**
	 * Returns the o auth token with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the o auth token
	 * @return the o auth token
	 * @throws com.liferay.portal.NoSuchModelException if a o auth token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthToken findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the o auth token with the primary key or throws a {@link com.liferay.opensocial.NoSuchOAuthTokenException} if it could not be found.
	 *
	 * @param oAuthTokenId the primary key of the o auth token
	 * @return the o auth token
	 * @throws com.liferay.opensocial.NoSuchOAuthTokenException if a o auth token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthToken findByPrimaryKey(long oAuthTokenId)
		throws NoSuchOAuthTokenException, SystemException {
		OAuthToken oAuthToken = fetchByPrimaryKey(oAuthTokenId);

		if (oAuthToken == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + oAuthTokenId);
			}

			throw new NoSuchOAuthTokenException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				oAuthTokenId);
		}

		return oAuthToken;
	}

	/**
	 * Returns the o auth token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the o auth token
	 * @return the o auth token, or <code>null</code> if a o auth token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthToken fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the o auth token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param oAuthTokenId the primary key of the o auth token
	 * @return the o auth token, or <code>null</code> if a o auth token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthToken fetchByPrimaryKey(long oAuthTokenId)
		throws SystemException {
		OAuthToken oAuthToken = (OAuthToken)EntityCacheUtil.getResult(OAuthTokenModelImpl.ENTITY_CACHE_ENABLED,
				OAuthTokenImpl.class, oAuthTokenId, this);

		if (oAuthToken == _nullOAuthToken) {
			return null;
		}

		if (oAuthToken == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				oAuthToken = (OAuthToken)session.get(OAuthTokenImpl.class,
						Long.valueOf(oAuthTokenId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (oAuthToken != null) {
					cacheResult(oAuthToken);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(OAuthTokenModelImpl.ENTITY_CACHE_ENABLED,
						OAuthTokenImpl.class, oAuthTokenId, _nullOAuthToken);
				}

				closeSession(session);
			}
		}

		return oAuthToken;
	}

	/**
	 * Returns all the o auth tokens where gadgetKey = &#63; and serviceName = &#63;.
	 *
	 * @param gadgetKey the gadget key
	 * @param serviceName the service name
	 * @return the matching o auth tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthToken> findByG_S(String gadgetKey, String serviceName)
		throws SystemException {
		return findByG_S(gadgetKey, serviceName, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the o auth tokens where gadgetKey = &#63; and serviceName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param gadgetKey the gadget key
	 * @param serviceName the service name
	 * @param start the lower bound of the range of o auth tokens
	 * @param end the upper bound of the range of o auth tokens (not inclusive)
	 * @return the range of matching o auth tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthToken> findByG_S(String gadgetKey, String serviceName,
		int start, int end) throws SystemException {
		return findByG_S(gadgetKey, serviceName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the o auth tokens where gadgetKey = &#63; and serviceName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param gadgetKey the gadget key
	 * @param serviceName the service name
	 * @param start the lower bound of the range of o auth tokens
	 * @param end the upper bound of the range of o auth tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching o auth tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthToken> findByG_S(String gadgetKey, String serviceName,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				gadgetKey, serviceName,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<OAuthToken> list = (List<OAuthToken>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_G_S,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_OAUTHTOKEN_WHERE);

			if (gadgetKey == null) {
				query.append(_FINDER_COLUMN_G_S_GADGETKEY_1);
			}
			else {
				if (gadgetKey.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_S_GADGETKEY_3);
				}
				else {
					query.append(_FINDER_COLUMN_G_S_GADGETKEY_2);
				}
			}

			if (serviceName == null) {
				query.append(_FINDER_COLUMN_G_S_SERVICENAME_1);
			}
			else {
				if (serviceName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_S_SERVICENAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_G_S_SERVICENAME_2);
				}
			}

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

				if (gadgetKey != null) {
					qPos.add(gadgetKey);
				}

				if (serviceName != null) {
					qPos.add(serviceName);
				}

				list = (List<OAuthToken>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_G_S,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_G_S,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first o auth token in the ordered set where gadgetKey = &#63; and serviceName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param gadgetKey the gadget key
	 * @param serviceName the service name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching o auth token
	 * @throws com.liferay.opensocial.NoSuchOAuthTokenException if a matching o auth token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthToken findByG_S_First(String gadgetKey, String serviceName,
		OrderByComparator orderByComparator)
		throws NoSuchOAuthTokenException, SystemException {
		List<OAuthToken> list = findByG_S(gadgetKey, serviceName, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("gadgetKey=");
			msg.append(gadgetKey);

			msg.append(", serviceName=");
			msg.append(serviceName);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchOAuthTokenException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last o auth token in the ordered set where gadgetKey = &#63; and serviceName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param gadgetKey the gadget key
	 * @param serviceName the service name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching o auth token
	 * @throws com.liferay.opensocial.NoSuchOAuthTokenException if a matching o auth token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthToken findByG_S_Last(String gadgetKey, String serviceName,
		OrderByComparator orderByComparator)
		throws NoSuchOAuthTokenException, SystemException {
		int count = countByG_S(gadgetKey, serviceName);

		List<OAuthToken> list = findByG_S(gadgetKey, serviceName, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("gadgetKey=");
			msg.append(gadgetKey);

			msg.append(", serviceName=");
			msg.append(serviceName);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchOAuthTokenException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the o auth tokens before and after the current o auth token in the ordered set where gadgetKey = &#63; and serviceName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param oAuthTokenId the primary key of the current o auth token
	 * @param gadgetKey the gadget key
	 * @param serviceName the service name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next o auth token
	 * @throws com.liferay.opensocial.NoSuchOAuthTokenException if a o auth token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthToken[] findByG_S_PrevAndNext(long oAuthTokenId,
		String gadgetKey, String serviceName,
		OrderByComparator orderByComparator)
		throws NoSuchOAuthTokenException, SystemException {
		OAuthToken oAuthToken = findByPrimaryKey(oAuthTokenId);

		Session session = null;

		try {
			session = openSession();

			OAuthToken[] array = new OAuthTokenImpl[3];

			array[0] = getByG_S_PrevAndNext(session, oAuthToken, gadgetKey,
					serviceName, orderByComparator, true);

			array[1] = oAuthToken;

			array[2] = getByG_S_PrevAndNext(session, oAuthToken, gadgetKey,
					serviceName, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OAuthToken getByG_S_PrevAndNext(Session session,
		OAuthToken oAuthToken, String gadgetKey, String serviceName,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_OAUTHTOKEN_WHERE);

		if (gadgetKey == null) {
			query.append(_FINDER_COLUMN_G_S_GADGETKEY_1);
		}
		else {
			if (gadgetKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_S_GADGETKEY_3);
			}
			else {
				query.append(_FINDER_COLUMN_G_S_GADGETKEY_2);
			}
		}

		if (serviceName == null) {
			query.append(_FINDER_COLUMN_G_S_SERVICENAME_1);
		}
		else {
			if (serviceName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_S_SERVICENAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_G_S_SERVICENAME_2);
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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (gadgetKey != null) {
			qPos.add(gadgetKey);
		}

		if (serviceName != null) {
			qPos.add(serviceName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(oAuthToken);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OAuthToken> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the o auth token where userId = &#63; and gadgetKey = &#63; and serviceName = &#63; and moduleId = &#63; and tokenName = &#63; or throws a {@link com.liferay.opensocial.NoSuchOAuthTokenException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @param gadgetKey the gadget key
	 * @param serviceName the service name
	 * @param moduleId the module ID
	 * @param tokenName the token name
	 * @return the matching o auth token
	 * @throws com.liferay.opensocial.NoSuchOAuthTokenException if a matching o auth token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthToken findByU_G_S_M_T(long userId, String gadgetKey,
		String serviceName, long moduleId, String tokenName)
		throws NoSuchOAuthTokenException, SystemException {
		OAuthToken oAuthToken = fetchByU_G_S_M_T(userId, gadgetKey,
				serviceName, moduleId, tokenName);

		if (oAuthToken == null) {
			StringBundler msg = new StringBundler(12);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", gadgetKey=");
			msg.append(gadgetKey);

			msg.append(", serviceName=");
			msg.append(serviceName);

			msg.append(", moduleId=");
			msg.append(moduleId);

			msg.append(", tokenName=");
			msg.append(tokenName);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchOAuthTokenException(msg.toString());
		}

		return oAuthToken;
	}

	/**
	 * Returns the o auth token where userId = &#63; and gadgetKey = &#63; and serviceName = &#63; and moduleId = &#63; and tokenName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param gadgetKey the gadget key
	 * @param serviceName the service name
	 * @param moduleId the module ID
	 * @param tokenName the token name
	 * @return the matching o auth token, or <code>null</code> if a matching o auth token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthToken fetchByU_G_S_M_T(long userId, String gadgetKey,
		String serviceName, long moduleId, String tokenName)
		throws SystemException {
		return fetchByU_G_S_M_T(userId, gadgetKey, serviceName, moduleId,
			tokenName, true);
	}

	/**
	 * Returns the o auth token where userId = &#63; and gadgetKey = &#63; and serviceName = &#63; and moduleId = &#63; and tokenName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param gadgetKey the gadget key
	 * @param serviceName the service name
	 * @param moduleId the module ID
	 * @param tokenName the token name
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching o auth token, or <code>null</code> if a matching o auth token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthToken fetchByU_G_S_M_T(long userId, String gadgetKey,
		String serviceName, long moduleId, String tokenName,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] {
				userId, gadgetKey, serviceName, moduleId, tokenName
			};

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_U_G_S_M_T,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_SELECT_OAUTHTOKEN_WHERE);

			query.append(_FINDER_COLUMN_U_G_S_M_T_USERID_2);

			if (gadgetKey == null) {
				query.append(_FINDER_COLUMN_U_G_S_M_T_GADGETKEY_1);
			}
			else {
				if (gadgetKey.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_U_G_S_M_T_GADGETKEY_3);
				}
				else {
					query.append(_FINDER_COLUMN_U_G_S_M_T_GADGETKEY_2);
				}
			}

			if (serviceName == null) {
				query.append(_FINDER_COLUMN_U_G_S_M_T_SERVICENAME_1);
			}
			else {
				if (serviceName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_U_G_S_M_T_SERVICENAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_U_G_S_M_T_SERVICENAME_2);
				}
			}

			query.append(_FINDER_COLUMN_U_G_S_M_T_MODULEID_2);

			if (tokenName == null) {
				query.append(_FINDER_COLUMN_U_G_S_M_T_TOKENNAME_1);
			}
			else {
				if (tokenName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_U_G_S_M_T_TOKENNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_U_G_S_M_T_TOKENNAME_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (gadgetKey != null) {
					qPos.add(gadgetKey);
				}

				if (serviceName != null) {
					qPos.add(serviceName);
				}

				qPos.add(moduleId);

				if (tokenName != null) {
					qPos.add(tokenName);
				}

				List<OAuthToken> list = q.list();

				result = list;

				OAuthToken oAuthToken = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_G_S_M_T,
						finderArgs, list);
				}
				else {
					oAuthToken = list.get(0);

					cacheResult(oAuthToken);

					if ((oAuthToken.getUserId() != userId) ||
							(oAuthToken.getGadgetKey() == null) ||
							!oAuthToken.getGadgetKey().equals(gadgetKey) ||
							(oAuthToken.getServiceName() == null) ||
							!oAuthToken.getServiceName().equals(serviceName) ||
							(oAuthToken.getModuleId() != moduleId) ||
							(oAuthToken.getTokenName() == null) ||
							!oAuthToken.getTokenName().equals(tokenName)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_G_S_M_T,
							finderArgs, oAuthToken);
					}
				}

				return oAuthToken;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_G_S_M_T,
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
				return (OAuthToken)result;
			}
		}
	}

	/**
	 * Returns all the o auth tokens.
	 *
	 * @return the o auth tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthToken> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the o auth tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of o auth tokens
	 * @param end the upper bound of the range of o auth tokens (not inclusive)
	 * @return the range of o auth tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthToken> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the o auth tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of o auth tokens
	 * @param end the upper bound of the range of o auth tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of o auth tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthToken> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<OAuthToken> list = (List<OAuthToken>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_OAUTHTOKEN);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_OAUTHTOKEN;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<OAuthToken>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<OAuthToken>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the o auth tokens where gadgetKey = &#63; and serviceName = &#63; from the database.
	 *
	 * @param gadgetKey the gadget key
	 * @param serviceName the service name
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_S(String gadgetKey, String serviceName)
		throws SystemException {
		for (OAuthToken oAuthToken : findByG_S(gadgetKey, serviceName)) {
			oAuthTokenPersistence.remove(oAuthToken);
		}
	}

	/**
	 * Removes the o auth token where userId = &#63; and gadgetKey = &#63; and serviceName = &#63; and moduleId = &#63; and tokenName = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param gadgetKey the gadget key
	 * @param serviceName the service name
	 * @param moduleId the module ID
	 * @param tokenName the token name
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByU_G_S_M_T(long userId, String gadgetKey,
		String serviceName, long moduleId, String tokenName)
		throws NoSuchOAuthTokenException, SystemException {
		OAuthToken oAuthToken = findByU_G_S_M_T(userId, gadgetKey, serviceName,
				moduleId, tokenName);

		oAuthTokenPersistence.remove(oAuthToken);
	}

	/**
	 * Removes all the o auth tokens from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (OAuthToken oAuthToken : findAll()) {
			oAuthTokenPersistence.remove(oAuthToken);
		}
	}

	/**
	 * Returns the number of o auth tokens where gadgetKey = &#63; and serviceName = &#63;.
	 *
	 * @param gadgetKey the gadget key
	 * @param serviceName the service name
	 * @return the number of matching o auth tokens
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_S(String gadgetKey, String serviceName)
		throws SystemException {
		Object[] finderArgs = new Object[] { gadgetKey, serviceName };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_OAUTHTOKEN_WHERE);

			if (gadgetKey == null) {
				query.append(_FINDER_COLUMN_G_S_GADGETKEY_1);
			}
			else {
				if (gadgetKey.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_S_GADGETKEY_3);
				}
				else {
					query.append(_FINDER_COLUMN_G_S_GADGETKEY_2);
				}
			}

			if (serviceName == null) {
				query.append(_FINDER_COLUMN_G_S_SERVICENAME_1);
			}
			else {
				if (serviceName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_S_SERVICENAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_G_S_SERVICENAME_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (gadgetKey != null) {
					qPos.add(gadgetKey);
				}

				if (serviceName != null) {
					qPos.add(serviceName);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_S, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of o auth tokens where userId = &#63; and gadgetKey = &#63; and serviceName = &#63; and moduleId = &#63; and tokenName = &#63;.
	 *
	 * @param userId the user ID
	 * @param gadgetKey the gadget key
	 * @param serviceName the service name
	 * @param moduleId the module ID
	 * @param tokenName the token name
	 * @return the number of matching o auth tokens
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_G_S_M_T(long userId, String gadgetKey,
		String serviceName, long moduleId, String tokenName)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				userId, gadgetKey, serviceName, moduleId, tokenName
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_U_G_S_M_T,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_COUNT_OAUTHTOKEN_WHERE);

			query.append(_FINDER_COLUMN_U_G_S_M_T_USERID_2);

			if (gadgetKey == null) {
				query.append(_FINDER_COLUMN_U_G_S_M_T_GADGETKEY_1);
			}
			else {
				if (gadgetKey.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_U_G_S_M_T_GADGETKEY_3);
				}
				else {
					query.append(_FINDER_COLUMN_U_G_S_M_T_GADGETKEY_2);
				}
			}

			if (serviceName == null) {
				query.append(_FINDER_COLUMN_U_G_S_M_T_SERVICENAME_1);
			}
			else {
				if (serviceName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_U_G_S_M_T_SERVICENAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_U_G_S_M_T_SERVICENAME_2);
				}
			}

			query.append(_FINDER_COLUMN_U_G_S_M_T_MODULEID_2);

			if (tokenName == null) {
				query.append(_FINDER_COLUMN_U_G_S_M_T_TOKENNAME_1);
			}
			else {
				if (tokenName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_U_G_S_M_T_TOKENNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_U_G_S_M_T_TOKENNAME_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (gadgetKey != null) {
					qPos.add(gadgetKey);
				}

				if (serviceName != null) {
					qPos.add(serviceName);
				}

				qPos.add(moduleId);

				if (tokenName != null) {
					qPos.add(tokenName);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_G_S_M_T,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of o auth tokens.
	 *
	 * @return the number of o auth tokens
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

				Query q = session.createQuery(_SQL_COUNT_OAUTHTOKEN);

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
	 * Initializes the o auth token persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.opensocial.model.OAuthToken")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<OAuthToken>> listenersList = new ArrayList<ModelListener<OAuthToken>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<OAuthToken>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(OAuthTokenImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST);
	}

	@BeanReference(type = GadgetPersistence.class)
	protected GadgetPersistence gadgetPersistence;
	@BeanReference(type = OAuthConsumerPersistence.class)
	protected OAuthConsumerPersistence oAuthConsumerPersistence;
	@BeanReference(type = OAuthTokenPersistence.class)
	protected OAuthTokenPersistence oAuthTokenPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_OAUTHTOKEN = "SELECT oAuthToken FROM OAuthToken oAuthToken";
	private static final String _SQL_SELECT_OAUTHTOKEN_WHERE = "SELECT oAuthToken FROM OAuthToken oAuthToken WHERE ";
	private static final String _SQL_COUNT_OAUTHTOKEN = "SELECT COUNT(oAuthToken) FROM OAuthToken oAuthToken";
	private static final String _SQL_COUNT_OAUTHTOKEN_WHERE = "SELECT COUNT(oAuthToken) FROM OAuthToken oAuthToken WHERE ";
	private static final String _FINDER_COLUMN_G_S_GADGETKEY_1 = "oAuthToken.gadgetKey IS NULL AND ";
	private static final String _FINDER_COLUMN_G_S_GADGETKEY_2 = "oAuthToken.gadgetKey = ? AND ";
	private static final String _FINDER_COLUMN_G_S_GADGETKEY_3 = "(oAuthToken.gadgetKey IS NULL OR oAuthToken.gadgetKey = ?) AND ";
	private static final String _FINDER_COLUMN_G_S_SERVICENAME_1 = "oAuthToken.serviceName IS NULL";
	private static final String _FINDER_COLUMN_G_S_SERVICENAME_2 = "oAuthToken.serviceName = ?";
	private static final String _FINDER_COLUMN_G_S_SERVICENAME_3 = "(oAuthToken.serviceName IS NULL OR oAuthToken.serviceName = ?)";
	private static final String _FINDER_COLUMN_U_G_S_M_T_USERID_2 = "oAuthToken.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_G_S_M_T_GADGETKEY_1 = "oAuthToken.gadgetKey IS NULL AND ";
	private static final String _FINDER_COLUMN_U_G_S_M_T_GADGETKEY_2 = "oAuthToken.gadgetKey = ? AND ";
	private static final String _FINDER_COLUMN_U_G_S_M_T_GADGETKEY_3 = "(oAuthToken.gadgetKey IS NULL OR oAuthToken.gadgetKey = ?) AND ";
	private static final String _FINDER_COLUMN_U_G_S_M_T_SERVICENAME_1 = "oAuthToken.serviceName IS NULL AND ";
	private static final String _FINDER_COLUMN_U_G_S_M_T_SERVICENAME_2 = "oAuthToken.serviceName = ? AND ";
	private static final String _FINDER_COLUMN_U_G_S_M_T_SERVICENAME_3 = "(oAuthToken.serviceName IS NULL OR oAuthToken.serviceName = ?) AND ";
	private static final String _FINDER_COLUMN_U_G_S_M_T_MODULEID_2 = "oAuthToken.moduleId = ? AND ";
	private static final String _FINDER_COLUMN_U_G_S_M_T_TOKENNAME_1 = "oAuthToken.tokenName IS NULL";
	private static final String _FINDER_COLUMN_U_G_S_M_T_TOKENNAME_2 = "oAuthToken.tokenName = ?";
	private static final String _FINDER_COLUMN_U_G_S_M_T_TOKENNAME_3 = "(oAuthToken.tokenName IS NULL OR oAuthToken.tokenName = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "oAuthToken.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OAuthToken exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No OAuthToken exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(OAuthTokenPersistenceImpl.class);
	private static OAuthToken _nullOAuthToken = new OAuthTokenImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<OAuthToken> toCacheModel() {
				return _nullOAuthTokenCacheModel;
			}
		};

	private static CacheModel<OAuthToken> _nullOAuthTokenCacheModel = new CacheModel<OAuthToken>() {
			public OAuthToken toEntityModel() {
				return _nullOAuthToken;
			}
		};
}