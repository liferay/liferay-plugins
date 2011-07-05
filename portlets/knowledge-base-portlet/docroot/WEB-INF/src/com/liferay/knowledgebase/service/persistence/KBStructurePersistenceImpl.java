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

package com.liferay.knowledgebase.service.persistence;

import com.liferay.knowledgebase.NoSuchStructureException;
import com.liferay.knowledgebase.model.KBStructure;
import com.liferay.knowledgebase.model.impl.KBStructureImpl;
import com.liferay.knowledgebase.model.impl.KBStructureModelImpl;

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
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.portlet.social.service.persistence.SocialActivityPersistence;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the k b structure service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KBStructurePersistence
 * @see KBStructureUtil
 * @generated
 */
public class KBStructurePersistenceImpl extends BasePersistenceImpl<KBStructure>
	implements KBStructurePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link KBStructureUtil} to access the k b structure persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = KBStructureImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_UUID = new FinderPath(KBStructureModelImpl.ENTITY_CACHE_ENABLED,
			KBStructureModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(KBStructureModelImpl.ENTITY_CACHE_ENABLED,
			KBStructureModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByUuid", new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(KBStructureModelImpl.ENTITY_CACHE_ENABLED,
			KBStructureModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(KBStructureModelImpl.ENTITY_CACHE_ENABLED,
			KBStructureModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_GROUPID = new FinderPath(KBStructureModelImpl.ENTITY_CACHE_ENABLED,
			KBStructureModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByGroupId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(KBStructureModelImpl.ENTITY_CACHE_ENABLED,
			KBStructureModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByGroupId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(KBStructureModelImpl.ENTITY_CACHE_ENABLED,
			KBStructureModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KBStructureModelImpl.ENTITY_CACHE_ENABLED,
			KBStructureModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	/**
	 * Caches the k b structure in the entity cache if it is enabled.
	 *
	 * @param kbStructure the k b structure
	 */
	public void cacheResult(KBStructure kbStructure) {
		EntityCacheUtil.putResult(KBStructureModelImpl.ENTITY_CACHE_ENABLED,
			KBStructureImpl.class, kbStructure.getPrimaryKey(), kbStructure);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				kbStructure.getUuid(), Long.valueOf(kbStructure.getGroupId())
			}, kbStructure);

		kbStructure.resetOriginalValues();
	}

	/**
	 * Caches the k b structures in the entity cache if it is enabled.
	 *
	 * @param kbStructures the k b structures
	 */
	public void cacheResult(List<KBStructure> kbStructures) {
		for (KBStructure kbStructure : kbStructures) {
			if (EntityCacheUtil.getResult(
						KBStructureModelImpl.ENTITY_CACHE_ENABLED,
						KBStructureImpl.class, kbStructure.getPrimaryKey(), this) == null) {
				cacheResult(kbStructure);
			}
		}
	}

	/**
	 * Clears the cache for all k b structures.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(KBStructureImpl.class.getName());
		}

		EntityCacheUtil.clearCache(KBStructureImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the k b structure.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(KBStructure kbStructure) {
		EntityCacheUtil.removeResult(KBStructureModelImpl.ENTITY_CACHE_ENABLED,
			KBStructureImpl.class, kbStructure.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				kbStructure.getUuid(), Long.valueOf(kbStructure.getGroupId())
			});
	}

	/**
	 * Creates a new k b structure with the primary key. Does not add the k b structure to the database.
	 *
	 * @param kbStructureId the primary key for the new k b structure
	 * @return the new k b structure
	 */
	public KBStructure create(long kbStructureId) {
		KBStructure kbStructure = new KBStructureImpl();

		kbStructure.setNew(true);
		kbStructure.setPrimaryKey(kbStructureId);

		String uuid = PortalUUIDUtil.generate();

		kbStructure.setUuid(uuid);

		return kbStructure;
	}

	/**
	 * Removes the k b structure with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the k b structure
	 * @return the k b structure that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a k b structure with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KBStructure remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the k b structure with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kbStructureId the primary key of the k b structure
	 * @return the k b structure that was removed
	 * @throws com.liferay.knowledgebase.NoSuchStructureException if a k b structure with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBStructure remove(long kbStructureId)
		throws NoSuchStructureException, SystemException {
		Session session = null;

		try {
			session = openSession();

			KBStructure kbStructure = (KBStructure)session.get(KBStructureImpl.class,
					Long.valueOf(kbStructureId));

			if (kbStructure == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + kbStructureId);
				}

				throw new NoSuchStructureException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					kbStructureId);
			}

			return kbStructurePersistence.remove(kbStructure);
		}
		catch (NoSuchStructureException nsee) {
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
	 * Removes the k b structure from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kbStructure the k b structure
	 * @return the k b structure that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KBStructure remove(KBStructure kbStructure)
		throws SystemException {
		return super.remove(kbStructure);
	}

	@Override
	protected KBStructure removeImpl(KBStructure kbStructure)
		throws SystemException {
		kbStructure = toUnwrappedModel(kbStructure);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, kbStructure);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		KBStructureModelImpl kbStructureModelImpl = (KBStructureModelImpl)kbStructure;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				kbStructureModelImpl.getUuid(),
				Long.valueOf(kbStructureModelImpl.getGroupId())
			});

		EntityCacheUtil.removeResult(KBStructureModelImpl.ENTITY_CACHE_ENABLED,
			KBStructureImpl.class, kbStructure.getPrimaryKey());

		return kbStructure;
	}

	@Override
	public KBStructure updateImpl(
		com.liferay.knowledgebase.model.KBStructure kbStructure, boolean merge)
		throws SystemException {
		kbStructure = toUnwrappedModel(kbStructure);

		boolean isNew = kbStructure.isNew();

		KBStructureModelImpl kbStructureModelImpl = (KBStructureModelImpl)kbStructure;

		if (Validator.isNull(kbStructure.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			kbStructure.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, kbStructure, merge);

			kbStructure.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(KBStructureModelImpl.ENTITY_CACHE_ENABLED,
			KBStructureImpl.class, kbStructure.getPrimaryKey(), kbStructure);

		if (!isNew &&
				(!Validator.equals(kbStructure.getUuid(),
					kbStructureModelImpl.getOriginalUuid()) ||
				(kbStructure.getGroupId() != kbStructureModelImpl.getOriginalGroupId()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					kbStructureModelImpl.getOriginalUuid(),
					Long.valueOf(kbStructureModelImpl.getOriginalGroupId())
				});
		}

		if (isNew ||
				(!Validator.equals(kbStructure.getUuid(),
					kbStructureModelImpl.getOriginalUuid()) ||
				(kbStructure.getGroupId() != kbStructureModelImpl.getOriginalGroupId()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					kbStructure.getUuid(),
					Long.valueOf(kbStructure.getGroupId())
				}, kbStructure);
		}

		return kbStructure;
	}

	protected KBStructure toUnwrappedModel(KBStructure kbStructure) {
		if (kbStructure instanceof KBStructureImpl) {
			return kbStructure;
		}

		KBStructureImpl kbStructureImpl = new KBStructureImpl();

		kbStructureImpl.setNew(kbStructure.isNew());
		kbStructureImpl.setPrimaryKey(kbStructure.getPrimaryKey());

		kbStructureImpl.setUuid(kbStructure.getUuid());
		kbStructureImpl.setKbStructureId(kbStructure.getKbStructureId());
		kbStructureImpl.setGroupId(kbStructure.getGroupId());
		kbStructureImpl.setCompanyId(kbStructure.getCompanyId());
		kbStructureImpl.setUserId(kbStructure.getUserId());
		kbStructureImpl.setUserName(kbStructure.getUserName());
		kbStructureImpl.setCreateDate(kbStructure.getCreateDate());
		kbStructureImpl.setModifiedDate(kbStructure.getModifiedDate());
		kbStructureImpl.setTitle(kbStructure.getTitle());
		kbStructureImpl.setContent(kbStructure.getContent());

		return kbStructureImpl;
	}

	/**
	 * Returns the k b structure with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the k b structure
	 * @return the k b structure
	 * @throws com.liferay.portal.NoSuchModelException if a k b structure with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KBStructure findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the k b structure with the primary key or throws a {@link com.liferay.knowledgebase.NoSuchStructureException} if it could not be found.
	 *
	 * @param kbStructureId the primary key of the k b structure
	 * @return the k b structure
	 * @throws com.liferay.knowledgebase.NoSuchStructureException if a k b structure with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBStructure findByPrimaryKey(long kbStructureId)
		throws NoSuchStructureException, SystemException {
		KBStructure kbStructure = fetchByPrimaryKey(kbStructureId);

		if (kbStructure == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + kbStructureId);
			}

			throw new NoSuchStructureException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				kbStructureId);
		}

		return kbStructure;
	}

	/**
	 * Returns the k b structure with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the k b structure
	 * @return the k b structure, or <code>null</code> if a k b structure with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KBStructure fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the k b structure with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kbStructureId the primary key of the k b structure
	 * @return the k b structure, or <code>null</code> if a k b structure with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBStructure fetchByPrimaryKey(long kbStructureId)
		throws SystemException {
		KBStructure kbStructure = (KBStructure)EntityCacheUtil.getResult(KBStructureModelImpl.ENTITY_CACHE_ENABLED,
				KBStructureImpl.class, kbStructureId, this);

		if (kbStructure == _nullKBStructure) {
			return null;
		}

		if (kbStructure == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				kbStructure = (KBStructure)session.get(KBStructureImpl.class,
						Long.valueOf(kbStructureId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (kbStructure != null) {
					cacheResult(kbStructure);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(KBStructureModelImpl.ENTITY_CACHE_ENABLED,
						KBStructureImpl.class, kbStructureId, _nullKBStructure);
				}

				closeSession(session);
			}
		}

		return kbStructure;
	}

	/**
	 * Returns all the k b structures where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching k b structures
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBStructure> findByUuid(String uuid) throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b structures where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of k b structures
	 * @param end the upper bound of the range of k b structures (not inclusive)
	 * @return the range of matching k b structures
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBStructure> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b structures where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of k b structures
	 * @param end the upper bound of the range of k b structures (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b structures
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBStructure> findByUuid(String uuid, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				uuid,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KBStructure> list = (List<KBStructure>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_UUID,
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

			query.append(_SQL_SELECT_KBSTRUCTURE_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_UUID_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBStructureModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				list = (List<KBStructure>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_UUID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_UUID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first k b structure in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b structure
	 * @throws com.liferay.knowledgebase.NoSuchStructureException if a matching k b structure could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBStructure findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchStructureException, SystemException {
		List<KBStructure> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchStructureException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last k b structure in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b structure
	 * @throws com.liferay.knowledgebase.NoSuchStructureException if a matching k b structure could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBStructure findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchStructureException, SystemException {
		int count = countByUuid(uuid);

		List<KBStructure> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchStructureException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the k b structures before and after the current k b structure in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kbStructureId the primary key of the current k b structure
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b structure
	 * @throws com.liferay.knowledgebase.NoSuchStructureException if a k b structure with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBStructure[] findByUuid_PrevAndNext(long kbStructureId,
		String uuid, OrderByComparator orderByComparator)
		throws NoSuchStructureException, SystemException {
		KBStructure kbStructure = findByPrimaryKey(kbStructureId);

		Session session = null;

		try {
			session = openSession();

			KBStructure[] array = new KBStructureImpl[3];

			array[0] = getByUuid_PrevAndNext(session, kbStructure, uuid,
					orderByComparator, true);

			array[1] = kbStructure;

			array[2] = getByUuid_PrevAndNext(session, kbStructure, uuid,
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

	protected KBStructure getByUuid_PrevAndNext(Session session,
		KBStructure kbStructure, String uuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBSTRUCTURE_WHERE);

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else {
			if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				query.append(_FINDER_COLUMN_UUID_UUID_2);
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
			query.append(KBStructureModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (uuid != null) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kbStructure);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBStructure> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the k b structure where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.knowledgebase.NoSuchStructureException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching k b structure
	 * @throws com.liferay.knowledgebase.NoSuchStructureException if a matching k b structure could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBStructure findByUUID_G(String uuid, long groupId)
		throws NoSuchStructureException, SystemException {
		KBStructure kbStructure = fetchByUUID_G(uuid, groupId);

		if (kbStructure == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchStructureException(msg.toString());
		}

		return kbStructure;
	}

	/**
	 * Returns the k b structure where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching k b structure, or <code>null</code> if a matching k b structure could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBStructure fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the k b structure where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching k b structure, or <code>null</code> if a matching k b structure could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBStructure fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_KBSTRUCTURE_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_G_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_G_UUID_2);
				}
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			query.append(KBStructureModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<KBStructure> list = q.list();

				result = list;

				KBStructure kbStructure = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					kbStructure = list.get(0);

					cacheResult(kbStructure);

					if ((kbStructure.getUuid() == null) ||
							!kbStructure.getUuid().equals(uuid) ||
							(kbStructure.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, kbStructure);
					}
				}

				return kbStructure;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
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
				return (KBStructure)result;
			}
		}
	}

	/**
	 * Returns all the k b structures where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching k b structures
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBStructure> findByGroupId(long groupId)
		throws SystemException {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b structures where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of k b structures
	 * @param end the upper bound of the range of k b structures (not inclusive)
	 * @return the range of matching k b structures
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBStructure> findByGroupId(long groupId, int start, int end)
		throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b structures where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of k b structures
	 * @param end the upper bound of the range of k b structures (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b structures
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBStructure> findByGroupId(long groupId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KBStructure> list = (List<KBStructure>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_GROUPID,
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

			query.append(_SQL_SELECT_KBSTRUCTURE_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBStructureModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				list = (List<KBStructure>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_GROUPID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_GROUPID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first k b structure in the ordered set where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b structure
	 * @throws com.liferay.knowledgebase.NoSuchStructureException if a matching k b structure could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBStructure findByGroupId_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchStructureException, SystemException {
		List<KBStructure> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchStructureException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last k b structure in the ordered set where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b structure
	 * @throws com.liferay.knowledgebase.NoSuchStructureException if a matching k b structure could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBStructure findByGroupId_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchStructureException, SystemException {
		int count = countByGroupId(groupId);

		List<KBStructure> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchStructureException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the k b structures before and after the current k b structure in the ordered set where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kbStructureId the primary key of the current k b structure
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b structure
	 * @throws com.liferay.knowledgebase.NoSuchStructureException if a k b structure with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBStructure[] findByGroupId_PrevAndNext(long kbStructureId,
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchStructureException, SystemException {
		KBStructure kbStructure = findByPrimaryKey(kbStructureId);

		Session session = null;

		try {
			session = openSession();

			KBStructure[] array = new KBStructureImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, kbStructure, groupId,
					orderByComparator, true);

			array[1] = kbStructure;

			array[2] = getByGroupId_PrevAndNext(session, kbStructure, groupId,
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

	protected KBStructure getByGroupId_PrevAndNext(Session session,
		KBStructure kbStructure, long groupId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBSTRUCTURE_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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
			query.append(KBStructureModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kbStructure);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBStructure> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b structures.
	 *
	 * @return the k b structures
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBStructure> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b structures.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of k b structures
	 * @param end the upper bound of the range of k b structures (not inclusive)
	 * @return the range of k b structures
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBStructure> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b structures.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of k b structures
	 * @param end the upper bound of the range of k b structures (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of k b structures
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBStructure> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KBStructure> list = (List<KBStructure>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_KBSTRUCTURE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_KBSTRUCTURE.concat(KBStructureModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<KBStructure>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<KBStructure>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the k b structures where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (KBStructure kbStructure : findByUuid(uuid)) {
			kbStructurePersistence.remove(kbStructure);
		}
	}

	/**
	 * Removes the k b structure where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUUID_G(String uuid, long groupId)
		throws NoSuchStructureException, SystemException {
		KBStructure kbStructure = findByUUID_G(uuid, groupId);

		kbStructurePersistence.remove(kbStructure);
	}

	/**
	 * Removes all the k b structures where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByGroupId(long groupId) throws SystemException {
		for (KBStructure kbStructure : findByGroupId(groupId)) {
			kbStructurePersistence.remove(kbStructure);
		}
	}

	/**
	 * Removes all the k b structures from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (KBStructure kbStructure : findAll()) {
			kbStructurePersistence.remove(kbStructure);
		}
	}

	/**
	 * Returns the number of k b structures where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching k b structures
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KBSTRUCTURE_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_UUID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b structures where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching k b structures
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUUID_G(String uuid, long groupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID_G,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBSTRUCTURE_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_G_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_G_UUID_2);
				}
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b structures where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching k b structures
	 * @throws SystemException if a system exception occurred
	 */
	public int countByGroupId(long groupId) throws SystemException {
		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_GROUPID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KBSTRUCTURE_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_GROUPID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b structures.
	 *
	 * @return the number of k b structures
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

				Query q = session.createQuery(_SQL_COUNT_KBSTRUCTURE);

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
	 * Initializes the k b structure persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.knowledgebase.model.KBStructure")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<KBStructure>> listenersList = new ArrayList<ModelListener<KBStructure>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<KBStructure>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(KBStructureImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST);
	}

	@BeanReference(type = KBArticlePersistence.class)
	protected KBArticlePersistence kbArticlePersistence;
	@BeanReference(type = KBCommentPersistence.class)
	protected KBCommentPersistence kbCommentPersistence;
	@BeanReference(type = KBStructurePersistence.class)
	protected KBStructurePersistence kbStructurePersistence;
	@BeanReference(type = KBTemplatePersistence.class)
	protected KBTemplatePersistence kbTemplatePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = SocialActivityPersistence.class)
	protected SocialActivityPersistence socialActivityPersistence;
	private static final String _SQL_SELECT_KBSTRUCTURE = "SELECT kbStructure FROM KBStructure kbStructure";
	private static final String _SQL_SELECT_KBSTRUCTURE_WHERE = "SELECT kbStructure FROM KBStructure kbStructure WHERE ";
	private static final String _SQL_COUNT_KBSTRUCTURE = "SELECT COUNT(kbStructure) FROM KBStructure kbStructure";
	private static final String _SQL_COUNT_KBSTRUCTURE_WHERE = "SELECT COUNT(kbStructure) FROM KBStructure kbStructure WHERE ";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "kbStructure.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "kbStructure.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(kbStructure.uuid IS NULL OR kbStructure.uuid = ?)";
	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "kbStructure.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "kbStructure.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(kbStructure.uuid IS NULL OR kbStructure.uuid = ?) AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "kbStructure.groupId = ?";
	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "kbStructure.groupId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kbStructure.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KBStructure exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KBStructure exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(KBStructurePersistenceImpl.class);
	private static KBStructure _nullKBStructure = new KBStructureImpl() {
			public Object clone() {
				return this;
			}
		};
}