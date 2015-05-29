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

package com.liferay.opensocial.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.opensocial.NoSuchOAuthTokenException;
import com.liferay.opensocial.model.OAuthToken;
import com.liferay.opensocial.model.impl.OAuthTokenImpl;
import com.liferay.opensocial.model.impl.OAuthTokenModelImpl;
import com.liferay.opensocial.service.persistence.OAuthTokenPersistence;

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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextThreadLocal;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the o auth token service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OAuthTokenPersistence
 * @see com.liferay.opensocial.service.persistence.OAuthTokenUtil
 * @generated
 */
@ProviderType
public class OAuthTokenPersistenceImpl extends BasePersistenceImpl<OAuthToken>
	implements OAuthTokenPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link OAuthTokenUtil} to access the o auth token persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = OAuthTokenImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(OAuthTokenModelImpl.ENTITY_CACHE_ENABLED,
			OAuthTokenModelImpl.FINDER_CACHE_ENABLED, OAuthTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(OAuthTokenModelImpl.ENTITY_CACHE_ENABLED,
			OAuthTokenModelImpl.FINDER_CACHE_ENABLED, OAuthTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OAuthTokenModelImpl.ENTITY_CACHE_ENABLED,
			OAuthTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_S = new FinderPath(OAuthTokenModelImpl.ENTITY_CACHE_ENABLED,
			OAuthTokenModelImpl.FINDER_CACHE_ENABLED, OAuthTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_S",
			new String[] {
				String.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S = new FinderPath(OAuthTokenModelImpl.ENTITY_CACHE_ENABLED,
			OAuthTokenModelImpl.FINDER_CACHE_ENABLED, OAuthTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_S",
			new String[] { String.class.getName(), String.class.getName() },
			OAuthTokenModelImpl.GADGETKEY_COLUMN_BITMASK |
			OAuthTokenModelImpl.SERVICENAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_S = new FinderPath(OAuthTokenModelImpl.ENTITY_CACHE_ENABLED,
			OAuthTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_S",
			new String[] { String.class.getName(), String.class.getName() });

	/**
	 * Returns all the o auth tokens where gadgetKey = &#63; and serviceName = &#63;.
	 *
	 * @param gadgetKey the gadget key
	 * @param serviceName the service name
	 * @return the matching o auth tokens
	 */
	@Override
	public List<OAuthToken> findByG_S(String gadgetKey, String serviceName) {
		return findByG_S(gadgetKey, serviceName, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the o auth tokens where gadgetKey = &#63; and serviceName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OAuthTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param gadgetKey the gadget key
	 * @param serviceName the service name
	 * @param start the lower bound of the range of o auth tokens
	 * @param end the upper bound of the range of o auth tokens (not inclusive)
	 * @return the range of matching o auth tokens
	 */
	@Override
	public List<OAuthToken> findByG_S(String gadgetKey, String serviceName,
		int start, int end) {
		return findByG_S(gadgetKey, serviceName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the o auth tokens where gadgetKey = &#63; and serviceName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OAuthTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param gadgetKey the gadget key
	 * @param serviceName the service name
	 * @param start the lower bound of the range of o auth tokens
	 * @param end the upper bound of the range of o auth tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching o auth tokens
	 */
	@Override
	public List<OAuthToken> findByG_S(String gadgetKey, String serviceName,
		int start, int end, OrderByComparator<OAuthToken> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S;
			finderArgs = new Object[] { gadgetKey, serviceName };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_S;
			finderArgs = new Object[] {
					gadgetKey, serviceName,
					
					start, end, orderByComparator
				};
		}

		List<OAuthToken> list = (List<OAuthToken>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (OAuthToken oAuthToken : list) {
				if (!Validator.equals(gadgetKey, oAuthToken.getGadgetKey()) ||
						!Validator.equals(serviceName,
							oAuthToken.getServiceName())) {
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

			query.append(_SQL_SELECT_OAUTHTOKEN_WHERE);

			boolean bindGadgetKey = false;

			if (gadgetKey == null) {
				query.append(_FINDER_COLUMN_G_S_GADGETKEY_1);
			}
			else if (gadgetKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_S_GADGETKEY_3);
			}
			else {
				bindGadgetKey = true;

				query.append(_FINDER_COLUMN_G_S_GADGETKEY_2);
			}

			boolean bindServiceName = false;

			if (serviceName == null) {
				query.append(_FINDER_COLUMN_G_S_SERVICENAME_1);
			}
			else if (serviceName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_S_SERVICENAME_3);
			}
			else {
				bindServiceName = true;

				query.append(_FINDER_COLUMN_G_S_SERVICENAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OAuthTokenModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindGadgetKey) {
					qPos.add(gadgetKey);
				}

				if (bindServiceName) {
					qPos.add(serviceName);
				}

				if (!pagination) {
					list = (List<OAuthToken>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OAuthToken>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first o auth token in the ordered set where gadgetKey = &#63; and serviceName = &#63;.
	 *
	 * @param gadgetKey the gadget key
	 * @param serviceName the service name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching o auth token
	 * @throws NoSuchOAuthTokenException if a matching o auth token could not be found
	 */
	@Override
	public OAuthToken findByG_S_First(String gadgetKey, String serviceName,
		OrderByComparator<OAuthToken> orderByComparator)
		throws NoSuchOAuthTokenException {
		OAuthToken oAuthToken = fetchByG_S_First(gadgetKey, serviceName,
				orderByComparator);

		if (oAuthToken != null) {
			return oAuthToken;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("gadgetKey=");
		msg.append(gadgetKey);

		msg.append(", serviceName=");
		msg.append(serviceName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOAuthTokenException(msg.toString());
	}

	/**
	 * Returns the first o auth token in the ordered set where gadgetKey = &#63; and serviceName = &#63;.
	 *
	 * @param gadgetKey the gadget key
	 * @param serviceName the service name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching o auth token, or <code>null</code> if a matching o auth token could not be found
	 */
	@Override
	public OAuthToken fetchByG_S_First(String gadgetKey, String serviceName,
		OrderByComparator<OAuthToken> orderByComparator) {
		List<OAuthToken> list = findByG_S(gadgetKey, serviceName, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last o auth token in the ordered set where gadgetKey = &#63; and serviceName = &#63;.
	 *
	 * @param gadgetKey the gadget key
	 * @param serviceName the service name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching o auth token
	 * @throws NoSuchOAuthTokenException if a matching o auth token could not be found
	 */
	@Override
	public OAuthToken findByG_S_Last(String gadgetKey, String serviceName,
		OrderByComparator<OAuthToken> orderByComparator)
		throws NoSuchOAuthTokenException {
		OAuthToken oAuthToken = fetchByG_S_Last(gadgetKey, serviceName,
				orderByComparator);

		if (oAuthToken != null) {
			return oAuthToken;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("gadgetKey=");
		msg.append(gadgetKey);

		msg.append(", serviceName=");
		msg.append(serviceName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOAuthTokenException(msg.toString());
	}

	/**
	 * Returns the last o auth token in the ordered set where gadgetKey = &#63; and serviceName = &#63;.
	 *
	 * @param gadgetKey the gadget key
	 * @param serviceName the service name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching o auth token, or <code>null</code> if a matching o auth token could not be found
	 */
	@Override
	public OAuthToken fetchByG_S_Last(String gadgetKey, String serviceName,
		OrderByComparator<OAuthToken> orderByComparator) {
		int count = countByG_S(gadgetKey, serviceName);

		if (count == 0) {
			return null;
		}

		List<OAuthToken> list = findByG_S(gadgetKey, serviceName, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the o auth tokens before and after the current o auth token in the ordered set where gadgetKey = &#63; and serviceName = &#63;.
	 *
	 * @param oAuthTokenId the primary key of the current o auth token
	 * @param gadgetKey the gadget key
	 * @param serviceName the service name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next o auth token
	 * @throws NoSuchOAuthTokenException if a o auth token with the primary key could not be found
	 */
	@Override
	public OAuthToken[] findByG_S_PrevAndNext(long oAuthTokenId,
		String gadgetKey, String serviceName,
		OrderByComparator<OAuthToken> orderByComparator)
		throws NoSuchOAuthTokenException {
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
		OrderByComparator<OAuthToken> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_OAUTHTOKEN_WHERE);

		boolean bindGadgetKey = false;

		if (gadgetKey == null) {
			query.append(_FINDER_COLUMN_G_S_GADGETKEY_1);
		}
		else if (gadgetKey.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_S_GADGETKEY_3);
		}
		else {
			bindGadgetKey = true;

			query.append(_FINDER_COLUMN_G_S_GADGETKEY_2);
		}

		boolean bindServiceName = false;

		if (serviceName == null) {
			query.append(_FINDER_COLUMN_G_S_SERVICENAME_1);
		}
		else if (serviceName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_S_SERVICENAME_3);
		}
		else {
			bindServiceName = true;

			query.append(_FINDER_COLUMN_G_S_SERVICENAME_2);
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
			query.append(OAuthTokenModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindGadgetKey) {
			qPos.add(gadgetKey);
		}

		if (bindServiceName) {
			qPos.add(serviceName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(oAuthToken);

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
	 * Removes all the o auth tokens where gadgetKey = &#63; and serviceName = &#63; from the database.
	 *
	 * @param gadgetKey the gadget key
	 * @param serviceName the service name
	 */
	@Override
	public void removeByG_S(String gadgetKey, String serviceName) {
		for (OAuthToken oAuthToken : findByG_S(gadgetKey, serviceName,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(oAuthToken);
		}
	}

	/**
	 * Returns the number of o auth tokens where gadgetKey = &#63; and serviceName = &#63;.
	 *
	 * @param gadgetKey the gadget key
	 * @param serviceName the service name
	 * @return the number of matching o auth tokens
	 */
	@Override
	public int countByG_S(String gadgetKey, String serviceName) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_S;

		Object[] finderArgs = new Object[] { gadgetKey, serviceName };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_OAUTHTOKEN_WHERE);

			boolean bindGadgetKey = false;

			if (gadgetKey == null) {
				query.append(_FINDER_COLUMN_G_S_GADGETKEY_1);
			}
			else if (gadgetKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_S_GADGETKEY_3);
			}
			else {
				bindGadgetKey = true;

				query.append(_FINDER_COLUMN_G_S_GADGETKEY_2);
			}

			boolean bindServiceName = false;

			if (serviceName == null) {
				query.append(_FINDER_COLUMN_G_S_SERVICENAME_1);
			}
			else if (serviceName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_S_SERVICENAME_3);
			}
			else {
				bindServiceName = true;

				query.append(_FINDER_COLUMN_G_S_SERVICENAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindGadgetKey) {
					qPos.add(gadgetKey);
				}

				if (bindServiceName) {
					qPos.add(serviceName);
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

	private static final String _FINDER_COLUMN_G_S_GADGETKEY_1 = "oAuthToken.gadgetKey IS NULL AND ";
	private static final String _FINDER_COLUMN_G_S_GADGETKEY_2 = "oAuthToken.gadgetKey = ? AND ";
	private static final String _FINDER_COLUMN_G_S_GADGETKEY_3 = "(oAuthToken.gadgetKey IS NULL OR oAuthToken.gadgetKey = '') AND ";
	private static final String _FINDER_COLUMN_G_S_SERVICENAME_1 = "oAuthToken.serviceName IS NULL";
	private static final String _FINDER_COLUMN_G_S_SERVICENAME_2 = "oAuthToken.serviceName = ?";
	private static final String _FINDER_COLUMN_G_S_SERVICENAME_3 = "(oAuthToken.serviceName IS NULL OR oAuthToken.serviceName = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_U_G_S_M_T = new FinderPath(OAuthTokenModelImpl.ENTITY_CACHE_ENABLED,
			OAuthTokenModelImpl.FINDER_CACHE_ENABLED, OAuthTokenImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByU_G_S_M_T",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			OAuthTokenModelImpl.USERID_COLUMN_BITMASK |
			OAuthTokenModelImpl.GADGETKEY_COLUMN_BITMASK |
			OAuthTokenModelImpl.SERVICENAME_COLUMN_BITMASK |
			OAuthTokenModelImpl.MODULEID_COLUMN_BITMASK |
			OAuthTokenModelImpl.TOKENNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_G_S_M_T = new FinderPath(OAuthTokenModelImpl.ENTITY_CACHE_ENABLED,
			OAuthTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_G_S_M_T",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns the o auth token where userId = &#63; and gadgetKey = &#63; and serviceName = &#63; and moduleId = &#63; and tokenName = &#63; or throws a {@link NoSuchOAuthTokenException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @param gadgetKey the gadget key
	 * @param serviceName the service name
	 * @param moduleId the module ID
	 * @param tokenName the token name
	 * @return the matching o auth token
	 * @throws NoSuchOAuthTokenException if a matching o auth token could not be found
	 */
	@Override
	public OAuthToken findByU_G_S_M_T(long userId, String gadgetKey,
		String serviceName, long moduleId, String tokenName)
		throws NoSuchOAuthTokenException {
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
	 */
	@Override
	public OAuthToken fetchByU_G_S_M_T(long userId, String gadgetKey,
		String serviceName, long moduleId, String tokenName) {
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
	 */
	@Override
	public OAuthToken fetchByU_G_S_M_T(long userId, String gadgetKey,
		String serviceName, long moduleId, String tokenName,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				userId, gadgetKey, serviceName, moduleId, tokenName
			};

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_U_G_S_M_T,
					finderArgs, this);
		}

		if (result instanceof OAuthToken) {
			OAuthToken oAuthToken = (OAuthToken)result;

			if ((userId != oAuthToken.getUserId()) ||
					!Validator.equals(gadgetKey, oAuthToken.getGadgetKey()) ||
					!Validator.equals(serviceName, oAuthToken.getServiceName()) ||
					(moduleId != oAuthToken.getModuleId()) ||
					!Validator.equals(tokenName, oAuthToken.getTokenName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(7);

			query.append(_SQL_SELECT_OAUTHTOKEN_WHERE);

			query.append(_FINDER_COLUMN_U_G_S_M_T_USERID_2);

			boolean bindGadgetKey = false;

			if (gadgetKey == null) {
				query.append(_FINDER_COLUMN_U_G_S_M_T_GADGETKEY_1);
			}
			else if (gadgetKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_U_G_S_M_T_GADGETKEY_3);
			}
			else {
				bindGadgetKey = true;

				query.append(_FINDER_COLUMN_U_G_S_M_T_GADGETKEY_2);
			}

			boolean bindServiceName = false;

			if (serviceName == null) {
				query.append(_FINDER_COLUMN_U_G_S_M_T_SERVICENAME_1);
			}
			else if (serviceName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_U_G_S_M_T_SERVICENAME_3);
			}
			else {
				bindServiceName = true;

				query.append(_FINDER_COLUMN_U_G_S_M_T_SERVICENAME_2);
			}

			query.append(_FINDER_COLUMN_U_G_S_M_T_MODULEID_2);

			boolean bindTokenName = false;

			if (tokenName == null) {
				query.append(_FINDER_COLUMN_U_G_S_M_T_TOKENNAME_1);
			}
			else if (tokenName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_U_G_S_M_T_TOKENNAME_3);
			}
			else {
				bindTokenName = true;

				query.append(_FINDER_COLUMN_U_G_S_M_T_TOKENNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (bindGadgetKey) {
					qPos.add(gadgetKey);
				}

				if (bindServiceName) {
					qPos.add(serviceName);
				}

				qPos.add(moduleId);

				if (bindTokenName) {
					qPos.add(tokenName);
				}

				List<OAuthToken> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_G_S_M_T,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"OAuthTokenPersistenceImpl.fetchByU_G_S_M_T(long, String, String, long, String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					OAuthToken oAuthToken = list.get(0);

					result = oAuthToken;

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
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_G_S_M_T,
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
			return (OAuthToken)result;
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
	 * @return the o auth token that was removed
	 */
	@Override
	public OAuthToken removeByU_G_S_M_T(long userId, String gadgetKey,
		String serviceName, long moduleId, String tokenName)
		throws NoSuchOAuthTokenException {
		OAuthToken oAuthToken = findByU_G_S_M_T(userId, gadgetKey, serviceName,
				moduleId, tokenName);

		return remove(oAuthToken);
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
	 */
	@Override
	public int countByU_G_S_M_T(long userId, String gadgetKey,
		String serviceName, long moduleId, String tokenName) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_G_S_M_T;

		Object[] finderArgs = new Object[] {
				userId, gadgetKey, serviceName, moduleId, tokenName
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_COUNT_OAUTHTOKEN_WHERE);

			query.append(_FINDER_COLUMN_U_G_S_M_T_USERID_2);

			boolean bindGadgetKey = false;

			if (gadgetKey == null) {
				query.append(_FINDER_COLUMN_U_G_S_M_T_GADGETKEY_1);
			}
			else if (gadgetKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_U_G_S_M_T_GADGETKEY_3);
			}
			else {
				bindGadgetKey = true;

				query.append(_FINDER_COLUMN_U_G_S_M_T_GADGETKEY_2);
			}

			boolean bindServiceName = false;

			if (serviceName == null) {
				query.append(_FINDER_COLUMN_U_G_S_M_T_SERVICENAME_1);
			}
			else if (serviceName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_U_G_S_M_T_SERVICENAME_3);
			}
			else {
				bindServiceName = true;

				query.append(_FINDER_COLUMN_U_G_S_M_T_SERVICENAME_2);
			}

			query.append(_FINDER_COLUMN_U_G_S_M_T_MODULEID_2);

			boolean bindTokenName = false;

			if (tokenName == null) {
				query.append(_FINDER_COLUMN_U_G_S_M_T_TOKENNAME_1);
			}
			else if (tokenName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_U_G_S_M_T_TOKENNAME_3);
			}
			else {
				bindTokenName = true;

				query.append(_FINDER_COLUMN_U_G_S_M_T_TOKENNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (bindGadgetKey) {
					qPos.add(gadgetKey);
				}

				if (bindServiceName) {
					qPos.add(serviceName);
				}

				qPos.add(moduleId);

				if (bindTokenName) {
					qPos.add(tokenName);
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

	private static final String _FINDER_COLUMN_U_G_S_M_T_USERID_2 = "oAuthToken.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_G_S_M_T_GADGETKEY_1 = "oAuthToken.gadgetKey IS NULL AND ";
	private static final String _FINDER_COLUMN_U_G_S_M_T_GADGETKEY_2 = "oAuthToken.gadgetKey = ? AND ";
	private static final String _FINDER_COLUMN_U_G_S_M_T_GADGETKEY_3 = "(oAuthToken.gadgetKey IS NULL OR oAuthToken.gadgetKey = '') AND ";
	private static final String _FINDER_COLUMN_U_G_S_M_T_SERVICENAME_1 = "oAuthToken.serviceName IS NULL AND ";
	private static final String _FINDER_COLUMN_U_G_S_M_T_SERVICENAME_2 = "oAuthToken.serviceName = ? AND ";
	private static final String _FINDER_COLUMN_U_G_S_M_T_SERVICENAME_3 = "(oAuthToken.serviceName IS NULL OR oAuthToken.serviceName = '') AND ";
	private static final String _FINDER_COLUMN_U_G_S_M_T_MODULEID_2 = "oAuthToken.moduleId = ? AND ";
	private static final String _FINDER_COLUMN_U_G_S_M_T_TOKENNAME_1 = "oAuthToken.tokenName IS NULL";
	private static final String _FINDER_COLUMN_U_G_S_M_T_TOKENNAME_2 = "oAuthToken.tokenName = ?";
	private static final String _FINDER_COLUMN_U_G_S_M_T_TOKENNAME_3 = "(oAuthToken.tokenName IS NULL OR oAuthToken.tokenName = '')";

	public OAuthTokenPersistenceImpl() {
		setModelClass(OAuthToken.class);
	}

	/**
	 * Caches the o auth token in the entity cache if it is enabled.
	 *
	 * @param oAuthToken the o auth token
	 */
	@Override
	public void cacheResult(OAuthToken oAuthToken) {
		EntityCacheUtil.putResult(OAuthTokenModelImpl.ENTITY_CACHE_ENABLED,
			OAuthTokenImpl.class, oAuthToken.getPrimaryKey(), oAuthToken);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_G_S_M_T,
			new Object[] {
				oAuthToken.getUserId(), oAuthToken.getGadgetKey(),
				oAuthToken.getServiceName(), oAuthToken.getModuleId(),
				oAuthToken.getTokenName()
			}, oAuthToken);

		oAuthToken.resetOriginalValues();
	}

	/**
	 * Caches the o auth tokens in the entity cache if it is enabled.
	 *
	 * @param oAuthTokens the o auth tokens
	 */
	@Override
	public void cacheResult(List<OAuthToken> oAuthTokens) {
		for (OAuthToken oAuthToken : oAuthTokens) {
			if (EntityCacheUtil.getResult(
						OAuthTokenModelImpl.ENTITY_CACHE_ENABLED,
						OAuthTokenImpl.class, oAuthToken.getPrimaryKey()) == null) {
				cacheResult(oAuthToken);
			}
			else {
				oAuthToken.resetOriginalValues();
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
		EntityCacheUtil.clearCache(OAuthTokenImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
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

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(oAuthToken);
	}

	@Override
	public void clearCache(List<OAuthToken> oAuthTokens) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (OAuthToken oAuthToken : oAuthTokens) {
			EntityCacheUtil.removeResult(OAuthTokenModelImpl.ENTITY_CACHE_ENABLED,
				OAuthTokenImpl.class, oAuthToken.getPrimaryKey());

			clearUniqueFindersCache(oAuthToken);
		}
	}

	protected void cacheUniqueFindersCache(OAuthToken oAuthToken) {
		if (oAuthToken.isNew()) {
			Object[] args = new Object[] {
					oAuthToken.getUserId(), oAuthToken.getGadgetKey(),
					oAuthToken.getServiceName(), oAuthToken.getModuleId(),
					oAuthToken.getTokenName()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_G_S_M_T, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_G_S_M_T, args,
				oAuthToken);
		}
		else {
			OAuthTokenModelImpl oAuthTokenModelImpl = (OAuthTokenModelImpl)oAuthToken;

			if ((oAuthTokenModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_U_G_S_M_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						oAuthToken.getUserId(), oAuthToken.getGadgetKey(),
						oAuthToken.getServiceName(), oAuthToken.getModuleId(),
						oAuthToken.getTokenName()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_G_S_M_T, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_G_S_M_T, args,
					oAuthToken);
			}
		}
	}

	protected void clearUniqueFindersCache(OAuthToken oAuthToken) {
		OAuthTokenModelImpl oAuthTokenModelImpl = (OAuthTokenModelImpl)oAuthToken;

		Object[] args = new Object[] {
				oAuthToken.getUserId(), oAuthToken.getGadgetKey(),
				oAuthToken.getServiceName(), oAuthToken.getModuleId(),
				oAuthToken.getTokenName()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_G_S_M_T, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_G_S_M_T, args);

		if ((oAuthTokenModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_U_G_S_M_T.getColumnBitmask()) != 0) {
			args = new Object[] {
					oAuthTokenModelImpl.getOriginalUserId(),
					oAuthTokenModelImpl.getOriginalGadgetKey(),
					oAuthTokenModelImpl.getOriginalServiceName(),
					oAuthTokenModelImpl.getOriginalModuleId(),
					oAuthTokenModelImpl.getOriginalTokenName()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_G_S_M_T, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_G_S_M_T, args);
		}
	}

	/**
	 * Creates a new o auth token with the primary key. Does not add the o auth token to the database.
	 *
	 * @param oAuthTokenId the primary key for the new o auth token
	 * @return the new o auth token
	 */
	@Override
	public OAuthToken create(long oAuthTokenId) {
		OAuthToken oAuthToken = new OAuthTokenImpl();

		oAuthToken.setNew(true);
		oAuthToken.setPrimaryKey(oAuthTokenId);

		return oAuthToken;
	}

	/**
	 * Removes the o auth token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param oAuthTokenId the primary key of the o auth token
	 * @return the o auth token that was removed
	 * @throws NoSuchOAuthTokenException if a o auth token with the primary key could not be found
	 */
	@Override
	public OAuthToken remove(long oAuthTokenId)
		throws NoSuchOAuthTokenException {
		return remove((Serializable)oAuthTokenId);
	}

	/**
	 * Removes the o auth token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the o auth token
	 * @return the o auth token that was removed
	 * @throws NoSuchOAuthTokenException if a o auth token with the primary key could not be found
	 */
	@Override
	public OAuthToken remove(Serializable primaryKey)
		throws NoSuchOAuthTokenException {
		Session session = null;

		try {
			session = openSession();

			OAuthToken oAuthToken = (OAuthToken)session.get(OAuthTokenImpl.class,
					primaryKey);

			if (oAuthToken == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOAuthTokenException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(oAuthToken);
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

	@Override
	protected OAuthToken removeImpl(OAuthToken oAuthToken) {
		oAuthToken = toUnwrappedModel(oAuthToken);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(oAuthToken)) {
				oAuthToken = (OAuthToken)session.get(OAuthTokenImpl.class,
						oAuthToken.getPrimaryKeyObj());
			}

			if (oAuthToken != null) {
				session.delete(oAuthToken);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (oAuthToken != null) {
			clearCache(oAuthToken);
		}

		return oAuthToken;
	}

	@Override
	public OAuthToken updateImpl(OAuthToken oAuthToken) {
		oAuthToken = toUnwrappedModel(oAuthToken);

		boolean isNew = oAuthToken.isNew();

		OAuthTokenModelImpl oAuthTokenModelImpl = (OAuthTokenModelImpl)oAuthToken;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (oAuthToken.getCreateDate() == null)) {
			if (serviceContext == null) {
				oAuthToken.setCreateDate(now);
			}
			else {
				oAuthToken.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!oAuthTokenModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				oAuthToken.setModifiedDate(now);
			}
			else {
				oAuthToken.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (oAuthToken.isNew()) {
				session.save(oAuthToken);

				oAuthToken.setNew(false);
			}
			else {
				session.merge(oAuthToken);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !OAuthTokenModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((oAuthTokenModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						oAuthTokenModelImpl.getOriginalGadgetKey(),
						oAuthTokenModelImpl.getOriginalServiceName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S,
					args);

				args = new Object[] {
						oAuthTokenModelImpl.getGadgetKey(),
						oAuthTokenModelImpl.getServiceName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S,
					args);
			}
		}

		EntityCacheUtil.putResult(OAuthTokenModelImpl.ENTITY_CACHE_ENABLED,
			OAuthTokenImpl.class, oAuthToken.getPrimaryKey(), oAuthToken, false);

		clearUniqueFindersCache(oAuthToken);
		cacheUniqueFindersCache(oAuthToken);

		oAuthToken.resetOriginalValues();

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
	 * @throws NoSuchOAuthTokenException if a o auth token with the primary key could not be found
	 */
	@Override
	public OAuthToken findByPrimaryKey(Serializable primaryKey)
		throws NoSuchOAuthTokenException {
		OAuthToken oAuthToken = fetchByPrimaryKey(primaryKey);

		if (oAuthToken == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchOAuthTokenException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return oAuthToken;
	}

	/**
	 * Returns the o auth token with the primary key or throws a {@link NoSuchOAuthTokenException} if it could not be found.
	 *
	 * @param oAuthTokenId the primary key of the o auth token
	 * @return the o auth token
	 * @throws NoSuchOAuthTokenException if a o auth token with the primary key could not be found
	 */
	@Override
	public OAuthToken findByPrimaryKey(long oAuthTokenId)
		throws NoSuchOAuthTokenException {
		return findByPrimaryKey((Serializable)oAuthTokenId);
	}

	/**
	 * Returns the o auth token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the o auth token
	 * @return the o auth token, or <code>null</code> if a o auth token with the primary key could not be found
	 */
	@Override
	public OAuthToken fetchByPrimaryKey(Serializable primaryKey) {
		OAuthToken oAuthToken = (OAuthToken)EntityCacheUtil.getResult(OAuthTokenModelImpl.ENTITY_CACHE_ENABLED,
				OAuthTokenImpl.class, primaryKey);

		if (oAuthToken == _nullOAuthToken) {
			return null;
		}

		if (oAuthToken == null) {
			Session session = null;

			try {
				session = openSession();

				oAuthToken = (OAuthToken)session.get(OAuthTokenImpl.class,
						primaryKey);

				if (oAuthToken != null) {
					cacheResult(oAuthToken);
				}
				else {
					EntityCacheUtil.putResult(OAuthTokenModelImpl.ENTITY_CACHE_ENABLED,
						OAuthTokenImpl.class, primaryKey, _nullOAuthToken);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(OAuthTokenModelImpl.ENTITY_CACHE_ENABLED,
					OAuthTokenImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return oAuthToken;
	}

	/**
	 * Returns the o auth token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param oAuthTokenId the primary key of the o auth token
	 * @return the o auth token, or <code>null</code> if a o auth token with the primary key could not be found
	 */
	@Override
	public OAuthToken fetchByPrimaryKey(long oAuthTokenId) {
		return fetchByPrimaryKey((Serializable)oAuthTokenId);
	}

	@Override
	public Map<Serializable, OAuthToken> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, OAuthToken> map = new HashMap<Serializable, OAuthToken>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			OAuthToken oAuthToken = fetchByPrimaryKey(primaryKey);

			if (oAuthToken != null) {
				map.put(primaryKey, oAuthToken);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			OAuthToken oAuthToken = (OAuthToken)EntityCacheUtil.getResult(OAuthTokenModelImpl.ENTITY_CACHE_ENABLED,
					OAuthTokenImpl.class, primaryKey);

			if (oAuthToken == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, oAuthToken);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_OAUTHTOKEN_WHERE_PKS_IN);

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

			for (OAuthToken oAuthToken : (List<OAuthToken>)q.list()) {
				map.put(oAuthToken.getPrimaryKeyObj(), oAuthToken);

				cacheResult(oAuthToken);

				uncachedPrimaryKeys.remove(oAuthToken.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(OAuthTokenModelImpl.ENTITY_CACHE_ENABLED,
					OAuthTokenImpl.class, primaryKey, _nullOAuthToken);
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
	 * Returns all the o auth tokens.
	 *
	 * @return the o auth tokens
	 */
	@Override
	public List<OAuthToken> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the o auth tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OAuthTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of o auth tokens
	 * @param end the upper bound of the range of o auth tokens (not inclusive)
	 * @return the range of o auth tokens
	 */
	@Override
	public List<OAuthToken> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the o auth tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OAuthTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of o auth tokens
	 * @param end the upper bound of the range of o auth tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of o auth tokens
	 */
	@Override
	public List<OAuthToken> findAll(int start, int end,
		OrderByComparator<OAuthToken> orderByComparator) {
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

		List<OAuthToken> list = (List<OAuthToken>)FinderCacheUtil.getResult(finderPath,
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

				if (pagination) {
					sql = sql.concat(OAuthTokenModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<OAuthToken>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OAuthToken>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the o auth tokens from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (OAuthToken oAuthToken : findAll()) {
			remove(oAuthToken);
		}
	}

	/**
	 * Returns the number of o auth tokens.
	 *
	 * @return the number of o auth tokens
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_OAUTHTOKEN);

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
	 * Initializes the o auth token persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(OAuthTokenImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_OAUTHTOKEN = "SELECT oAuthToken FROM OAuthToken oAuthToken";
	private static final String _SQL_SELECT_OAUTHTOKEN_WHERE_PKS_IN = "SELECT oAuthToken FROM OAuthToken oAuthToken WHERE oAuthTokenId IN (";
	private static final String _SQL_SELECT_OAUTHTOKEN_WHERE = "SELECT oAuthToken FROM OAuthToken oAuthToken WHERE ";
	private static final String _SQL_COUNT_OAUTHTOKEN = "SELECT COUNT(oAuthToken) FROM OAuthToken oAuthToken";
	private static final String _SQL_COUNT_OAUTHTOKEN_WHERE = "SELECT COUNT(oAuthToken) FROM OAuthToken oAuthToken WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "oAuthToken.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OAuthToken exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No OAuthToken exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(OAuthTokenPersistenceImpl.class);
	private static final OAuthToken _nullOAuthToken = new OAuthTokenImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<OAuthToken> toCacheModel() {
				return _nullOAuthTokenCacheModel;
			}
		};

	private static final CacheModel<OAuthToken> _nullOAuthTokenCacheModel = new CacheModel<OAuthToken>() {
			@Override
			public OAuthToken toEntityModel() {
				return _nullOAuthToken;
			}
		};
}