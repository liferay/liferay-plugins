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

import com.liferay.knowledgebase.NoSuchTemplateException;
import com.liferay.knowledgebase.model.KBTemplate;
import com.liferay.knowledgebase.model.impl.KBTemplateImpl;
import com.liferay.knowledgebase.model.impl.KBTemplateModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
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
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
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
 * The persistence implementation for the k b template service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KBTemplatePersistence
 * @see KBTemplateUtil
 * @generated
 */
public class KBTemplatePersistenceImpl extends BasePersistenceImpl<KBTemplate>
	implements KBTemplatePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link KBTemplateUtil} to access the k b template persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = KBTemplateImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_UUID = new FinderPath(KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
			KBTemplateModelImpl.FINDER_CACHE_ENABLED, KBTemplateImpl.class,
			FINDER_CLASS_NAME_LIST, "findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
			KBTemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
			KBTemplateModelImpl.FINDER_CACHE_ENABLED, KBTemplateImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
			KBTemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_GROUPID = new FinderPath(KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
			KBTemplateModelImpl.FINDER_CACHE_ENABLED, KBTemplateImpl.class,
			FINDER_CLASS_NAME_LIST, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
			KBTemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByGroupId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
			KBTemplateModelImpl.FINDER_CACHE_ENABLED, KBTemplateImpl.class,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
			KBTemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the k b template in the entity cache if it is enabled.
	 *
	 * @param kbTemplate the k b template
	 */
	public void cacheResult(KBTemplate kbTemplate) {
		EntityCacheUtil.putResult(KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
			KBTemplateImpl.class, kbTemplate.getPrimaryKey(), kbTemplate);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				kbTemplate.getUuid(), Long.valueOf(kbTemplate.getGroupId())
			}, kbTemplate);

		kbTemplate.resetOriginalValues();
	}

	/**
	 * Caches the k b templates in the entity cache if it is enabled.
	 *
	 * @param kbTemplates the k b templates
	 */
	public void cacheResult(List<KBTemplate> kbTemplates) {
		for (KBTemplate kbTemplate : kbTemplates) {
			if (EntityCacheUtil.getResult(
						KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
						KBTemplateImpl.class, kbTemplate.getPrimaryKey(), this) == null) {
				cacheResult(kbTemplate);
			}
		}
	}

	/**
	 * Clears the cache for all k b templates.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(KBTemplateImpl.class.getName());
		}

		EntityCacheUtil.clearCache(KBTemplateImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the k b template.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(KBTemplate kbTemplate) {
		EntityCacheUtil.removeResult(KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
			KBTemplateImpl.class, kbTemplate.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				kbTemplate.getUuid(), Long.valueOf(kbTemplate.getGroupId())
			});
	}

	/**
	 * Creates a new k b template with the primary key. Does not add the k b template to the database.
	 *
	 * @param kbTemplateId the primary key for the new k b template
	 * @return the new k b template
	 */
	public KBTemplate create(long kbTemplateId) {
		KBTemplate kbTemplate = new KBTemplateImpl();

		kbTemplate.setNew(true);
		kbTemplate.setPrimaryKey(kbTemplateId);

		String uuid = PortalUUIDUtil.generate();

		kbTemplate.setUuid(uuid);

		return kbTemplate;
	}

	/**
	 * Removes the k b template with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the k b template
	 * @return the k b template that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a k b template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KBTemplate remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the k b template with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kbTemplateId the primary key of the k b template
	 * @return the k b template that was removed
	 * @throws com.liferay.knowledgebase.NoSuchTemplateException if a k b template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBTemplate remove(long kbTemplateId)
		throws NoSuchTemplateException, SystemException {
		Session session = null;

		try {
			session = openSession();

			KBTemplate kbTemplate = (KBTemplate)session.get(KBTemplateImpl.class,
					Long.valueOf(kbTemplateId));

			if (kbTemplate == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + kbTemplateId);
				}

				throw new NoSuchTemplateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					kbTemplateId);
			}

			return kbTemplatePersistence.remove(kbTemplate);
		}
		catch (NoSuchTemplateException nsee) {
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
	 * Removes the k b template from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kbTemplate the k b template
	 * @return the k b template that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KBTemplate remove(KBTemplate kbTemplate) throws SystemException {
		return super.remove(kbTemplate);
	}

	@Override
	protected KBTemplate removeImpl(KBTemplate kbTemplate)
		throws SystemException {
		kbTemplate = toUnwrappedModel(kbTemplate);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, kbTemplate);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		KBTemplateModelImpl kbTemplateModelImpl = (KBTemplateModelImpl)kbTemplate;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				kbTemplateModelImpl.getUuid(),
				Long.valueOf(kbTemplateModelImpl.getGroupId())
			});

		EntityCacheUtil.removeResult(KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
			KBTemplateImpl.class, kbTemplate.getPrimaryKey());

		return kbTemplate;
	}

	@Override
	public KBTemplate updateImpl(
		com.liferay.knowledgebase.model.KBTemplate kbTemplate, boolean merge)
		throws SystemException {
		kbTemplate = toUnwrappedModel(kbTemplate);

		boolean isNew = kbTemplate.isNew();

		KBTemplateModelImpl kbTemplateModelImpl = (KBTemplateModelImpl)kbTemplate;

		if (Validator.isNull(kbTemplate.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			kbTemplate.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, kbTemplate, merge);

			kbTemplate.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
			KBTemplateImpl.class, kbTemplate.getPrimaryKey(), kbTemplate);

		if (!isNew &&
				(!Validator.equals(kbTemplate.getUuid(),
					kbTemplateModelImpl.getOriginalUuid()) ||
				(kbTemplate.getGroupId() != kbTemplateModelImpl.getOriginalGroupId()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					kbTemplateModelImpl.getOriginalUuid(),
					Long.valueOf(kbTemplateModelImpl.getOriginalGroupId())
				});
		}

		if (isNew ||
				(!Validator.equals(kbTemplate.getUuid(),
					kbTemplateModelImpl.getOriginalUuid()) ||
				(kbTemplate.getGroupId() != kbTemplateModelImpl.getOriginalGroupId()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					kbTemplate.getUuid(), Long.valueOf(kbTemplate.getGroupId())
				}, kbTemplate);
		}

		return kbTemplate;
	}

	protected KBTemplate toUnwrappedModel(KBTemplate kbTemplate) {
		if (kbTemplate instanceof KBTemplateImpl) {
			return kbTemplate;
		}

		KBTemplateImpl kbTemplateImpl = new KBTemplateImpl();

		kbTemplateImpl.setNew(kbTemplate.isNew());
		kbTemplateImpl.setPrimaryKey(kbTemplate.getPrimaryKey());

		kbTemplateImpl.setUuid(kbTemplate.getUuid());
		kbTemplateImpl.setKbTemplateId(kbTemplate.getKbTemplateId());
		kbTemplateImpl.setGroupId(kbTemplate.getGroupId());
		kbTemplateImpl.setCompanyId(kbTemplate.getCompanyId());
		kbTemplateImpl.setUserId(kbTemplate.getUserId());
		kbTemplateImpl.setUserName(kbTemplate.getUserName());
		kbTemplateImpl.setCreateDate(kbTemplate.getCreateDate());
		kbTemplateImpl.setModifiedDate(kbTemplate.getModifiedDate());
		kbTemplateImpl.setTitle(kbTemplate.getTitle());
		kbTemplateImpl.setContent(kbTemplate.getContent());

		return kbTemplateImpl;
	}

	/**
	 * Returns the k b template with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the k b template
	 * @return the k b template
	 * @throws com.liferay.portal.NoSuchModelException if a k b template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KBTemplate findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the k b template with the primary key or throws a {@link com.liferay.knowledgebase.NoSuchTemplateException} if it could not be found.
	 *
	 * @param kbTemplateId the primary key of the k b template
	 * @return the k b template
	 * @throws com.liferay.knowledgebase.NoSuchTemplateException if a k b template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBTemplate findByPrimaryKey(long kbTemplateId)
		throws NoSuchTemplateException, SystemException {
		KBTemplate kbTemplate = fetchByPrimaryKey(kbTemplateId);

		if (kbTemplate == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + kbTemplateId);
			}

			throw new NoSuchTemplateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				kbTemplateId);
		}

		return kbTemplate;
	}

	/**
	 * Returns the k b template with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the k b template
	 * @return the k b template, or <code>null</code> if a k b template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KBTemplate fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the k b template with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kbTemplateId the primary key of the k b template
	 * @return the k b template, or <code>null</code> if a k b template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBTemplate fetchByPrimaryKey(long kbTemplateId)
		throws SystemException {
		KBTemplate kbTemplate = (KBTemplate)EntityCacheUtil.getResult(KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
				KBTemplateImpl.class, kbTemplateId, this);

		if (kbTemplate == _nullKBTemplate) {
			return null;
		}

		if (kbTemplate == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				kbTemplate = (KBTemplate)session.get(KBTemplateImpl.class,
						Long.valueOf(kbTemplateId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (kbTemplate != null) {
					cacheResult(kbTemplate);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
						KBTemplateImpl.class, kbTemplateId, _nullKBTemplate);
				}

				closeSession(session);
			}
		}

		return kbTemplate;
	}

	/**
	 * Returns all the k b templates where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching k b templates
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBTemplate> findByUuid(String uuid) throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b templates where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of k b templates
	 * @param end the upper bound of the range of k b templates (not inclusive)
	 * @return the range of matching k b templates
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBTemplate> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b templates where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of k b templates
	 * @param end the upper bound of the range of k b templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b templates
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBTemplate> findByUuid(String uuid, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				uuid,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KBTemplate> list = (List<KBTemplate>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_UUID,
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

			query.append(_SQL_SELECT_KBTEMPLATE_WHERE);

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
				query.append(KBTemplateModelImpl.ORDER_BY_JPQL);
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

				list = (List<KBTemplate>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first k b template in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b template
	 * @throws com.liferay.knowledgebase.NoSuchTemplateException if a matching k b template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBTemplate findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchTemplateException, SystemException {
		List<KBTemplate> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTemplateException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last k b template in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b template
	 * @throws com.liferay.knowledgebase.NoSuchTemplateException if a matching k b template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBTemplate findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchTemplateException, SystemException {
		int count = countByUuid(uuid);

		List<KBTemplate> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTemplateException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the k b templates before and after the current k b template in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kbTemplateId the primary key of the current k b template
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b template
	 * @throws com.liferay.knowledgebase.NoSuchTemplateException if a k b template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBTemplate[] findByUuid_PrevAndNext(long kbTemplateId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchTemplateException, SystemException {
		KBTemplate kbTemplate = findByPrimaryKey(kbTemplateId);

		Session session = null;

		try {
			session = openSession();

			KBTemplate[] array = new KBTemplateImpl[3];

			array[0] = getByUuid_PrevAndNext(session, kbTemplate, uuid,
					orderByComparator, true);

			array[1] = kbTemplate;

			array[2] = getByUuid_PrevAndNext(session, kbTemplate, uuid,
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

	protected KBTemplate getByUuid_PrevAndNext(Session session,
		KBTemplate kbTemplate, String uuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBTEMPLATE_WHERE);

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
			query.append(KBTemplateModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByValues(kbTemplate);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBTemplate> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the k b template where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.knowledgebase.NoSuchTemplateException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching k b template
	 * @throws com.liferay.knowledgebase.NoSuchTemplateException if a matching k b template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBTemplate findByUUID_G(String uuid, long groupId)
		throws NoSuchTemplateException, SystemException {
		KBTemplate kbTemplate = fetchByUUID_G(uuid, groupId);

		if (kbTemplate == null) {
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

			throw new NoSuchTemplateException(msg.toString());
		}

		return kbTemplate;
	}

	/**
	 * Returns the k b template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching k b template, or <code>null</code> if a matching k b template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBTemplate fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the k b template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching k b template, or <code>null</code> if a matching k b template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBTemplate fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_KBTEMPLATE_WHERE);

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

			query.append(KBTemplateModelImpl.ORDER_BY_JPQL);

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

				List<KBTemplate> list = q.list();

				result = list;

				KBTemplate kbTemplate = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					kbTemplate = list.get(0);

					cacheResult(kbTemplate);

					if ((kbTemplate.getUuid() == null) ||
							!kbTemplate.getUuid().equals(uuid) ||
							(kbTemplate.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, kbTemplate);
					}
				}

				return kbTemplate;
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
				return (KBTemplate)result;
			}
		}
	}

	/**
	 * Returns all the k b templates where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching k b templates
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBTemplate> findByGroupId(long groupId)
		throws SystemException {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b templates where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of k b templates
	 * @param end the upper bound of the range of k b templates (not inclusive)
	 * @return the range of matching k b templates
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBTemplate> findByGroupId(long groupId, int start, int end)
		throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b templates where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of k b templates
	 * @param end the upper bound of the range of k b templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b templates
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBTemplate> findByGroupId(long groupId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KBTemplate> list = (List<KBTemplate>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_GROUPID,
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

			query.append(_SQL_SELECT_KBTEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBTemplateModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				list = (List<KBTemplate>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first k b template in the ordered set where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b template
	 * @throws com.liferay.knowledgebase.NoSuchTemplateException if a matching k b template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBTemplate findByGroupId_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchTemplateException, SystemException {
		List<KBTemplate> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTemplateException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last k b template in the ordered set where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b template
	 * @throws com.liferay.knowledgebase.NoSuchTemplateException if a matching k b template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBTemplate findByGroupId_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchTemplateException, SystemException {
		int count = countByGroupId(groupId);

		List<KBTemplate> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTemplateException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the k b templates before and after the current k b template in the ordered set where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kbTemplateId the primary key of the current k b template
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b template
	 * @throws com.liferay.knowledgebase.NoSuchTemplateException if a k b template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBTemplate[] findByGroupId_PrevAndNext(long kbTemplateId,
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchTemplateException, SystemException {
		KBTemplate kbTemplate = findByPrimaryKey(kbTemplateId);

		Session session = null;

		try {
			session = openSession();

			KBTemplate[] array = new KBTemplateImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, kbTemplate, groupId,
					orderByComparator, true);

			array[1] = kbTemplate;

			array[2] = getByGroupId_PrevAndNext(session, kbTemplate, groupId,
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

	protected KBTemplate getByGroupId_PrevAndNext(Session session,
		KBTemplate kbTemplate, long groupId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBTEMPLATE_WHERE);

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
			query.append(KBTemplateModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kbTemplate);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBTemplate> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b templates that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching k b templates that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBTemplate> filterFindByGroupId(long groupId)
		throws SystemException {
		return filterFindByGroupId(groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b templates that the user has permission to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of k b templates
	 * @param end the upper bound of the range of k b templates (not inclusive)
	 * @return the range of matching k b templates that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBTemplate> filterFindByGroupId(long groupId, int start, int end)
		throws SystemException {
		return filterFindByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b templates that the user has permissions to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of k b templates
	 * @param end the upper bound of the range of k b templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b templates that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBTemplate> filterFindByGroupId(long groupId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId(groupId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBTEMPLATE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBTEMPLATE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBTEMPLATE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(KBTemplateModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBTemplateModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBTemplate.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBTemplateImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBTemplateImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return (List<KBTemplate>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the k b templates before and after the current k b template in the ordered set of k b templates that the user has permission to view where groupId = &#63;.
	 *
	 * @param kbTemplateId the primary key of the current k b template
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b template
	 * @throws com.liferay.knowledgebase.NoSuchTemplateException if a k b template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBTemplate[] filterFindByGroupId_PrevAndNext(long kbTemplateId,
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchTemplateException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId_PrevAndNext(kbTemplateId, groupId,
				orderByComparator);
		}

		KBTemplate kbTemplate = findByPrimaryKey(kbTemplateId);

		Session session = null;

		try {
			session = openSession();

			KBTemplate[] array = new KBTemplateImpl[3];

			array[0] = filterGetByGroupId_PrevAndNext(session, kbTemplate,
					groupId, orderByComparator, true);

			array[1] = kbTemplate;

			array[2] = filterGetByGroupId_PrevAndNext(session, kbTemplate,
					groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBTemplate filterGetByGroupId_PrevAndNext(Session session,
		KBTemplate kbTemplate, long groupId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBTEMPLATE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBTEMPLATE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBTEMPLATE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(KBTemplateModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBTemplateModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBTemplate.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, KBTemplateImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, KBTemplateImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kbTemplate);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBTemplate> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b templates.
	 *
	 * @return the k b templates
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBTemplate> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b templates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of k b templates
	 * @param end the upper bound of the range of k b templates (not inclusive)
	 * @return the range of k b templates
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBTemplate> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b templates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of k b templates
	 * @param end the upper bound of the range of k b templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of k b templates
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBTemplate> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KBTemplate> list = (List<KBTemplate>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_KBTEMPLATE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_KBTEMPLATE.concat(KBTemplateModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<KBTemplate>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<KBTemplate>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the k b templates where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (KBTemplate kbTemplate : findByUuid(uuid)) {
			kbTemplatePersistence.remove(kbTemplate);
		}
	}

	/**
	 * Removes the k b template where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUUID_G(String uuid, long groupId)
		throws NoSuchTemplateException, SystemException {
		KBTemplate kbTemplate = findByUUID_G(uuid, groupId);

		kbTemplatePersistence.remove(kbTemplate);
	}

	/**
	 * Removes all the k b templates where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByGroupId(long groupId) throws SystemException {
		for (KBTemplate kbTemplate : findByGroupId(groupId)) {
			kbTemplatePersistence.remove(kbTemplate);
		}
	}

	/**
	 * Removes all the k b templates from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (KBTemplate kbTemplate : findAll()) {
			kbTemplatePersistence.remove(kbTemplate);
		}
	}

	/**
	 * Returns the number of k b templates where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching k b templates
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KBTEMPLATE_WHERE);

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
	 * Returns the number of k b templates where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching k b templates
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUUID_G(String uuid, long groupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID_G,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBTEMPLATE_WHERE);

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
	 * Returns the number of k b templates where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching k b templates
	 * @throws SystemException if a system exception occurred
	 */
	public int countByGroupId(long groupId) throws SystemException {
		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_GROUPID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KBTEMPLATE_WHERE);

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
	 * Returns the number of k b templates that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching k b templates that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByGroupId(long groupId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupId(groupId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_KBTEMPLATE_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBTemplate.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the number of k b templates.
	 *
	 * @return the number of k b templates
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

				Query q = session.createQuery(_SQL_COUNT_KBTEMPLATE);

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
	 * Initializes the k b template persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.knowledgebase.model.KBTemplate")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<KBTemplate>> listenersList = new ArrayList<ModelListener<KBTemplate>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<KBTemplate>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(KBTemplateImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST);
	}

	@BeanReference(type = KBArticlePersistence.class)
	protected KBArticlePersistence kbArticlePersistence;
	@BeanReference(type = KBCommentPersistence.class)
	protected KBCommentPersistence kbCommentPersistence;
	@BeanReference(type = KBTemplatePersistence.class)
	protected KBTemplatePersistence kbTemplatePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = SocialActivityPersistence.class)
	protected SocialActivityPersistence socialActivityPersistence;
	private static final String _SQL_SELECT_KBTEMPLATE = "SELECT kbTemplate FROM KBTemplate kbTemplate";
	private static final String _SQL_SELECT_KBTEMPLATE_WHERE = "SELECT kbTemplate FROM KBTemplate kbTemplate WHERE ";
	private static final String _SQL_COUNT_KBTEMPLATE = "SELECT COUNT(kbTemplate) FROM KBTemplate kbTemplate";
	private static final String _SQL_COUNT_KBTEMPLATE_WHERE = "SELECT COUNT(kbTemplate) FROM KBTemplate kbTemplate WHERE ";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "kbTemplate.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "kbTemplate.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(kbTemplate.uuid IS NULL OR kbTemplate.uuid = ?)";
	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "kbTemplate.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "kbTemplate.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(kbTemplate.uuid IS NULL OR kbTemplate.uuid = ?) AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "kbTemplate.groupId = ?";
	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "kbTemplate.groupId = ?";
	private static final String _FILTER_SQL_SELECT_KBTEMPLATE_WHERE = "SELECT DISTINCT {kbTemplate.*} FROM KBTemplate kbTemplate WHERE ";
	private static final String _FILTER_SQL_SELECT_KBTEMPLATE_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {KBTemplate.*} FROM (SELECT DISTINCT kbTemplate.kbTemplateId FROM KBTemplate kbTemplate WHERE ";
	private static final String _FILTER_SQL_SELECT_KBTEMPLATE_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN KBTemplate ON TEMP_TABLE.kbTemplateId = KBTemplate.kbTemplateId";
	private static final String _FILTER_SQL_COUNT_KBTEMPLATE_WHERE = "SELECT COUNT(DISTINCT kbTemplate.kbTemplateId) AS COUNT_VALUE FROM KBTemplate kbTemplate WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "kbTemplate";
	private static final String _FILTER_ENTITY_TABLE = "KBTemplate";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "kbTemplate.kbTemplateId";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kbTemplate.";
	private static final String _ORDER_BY_ENTITY_TABLE = "KBTemplate.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KBTemplate exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KBTemplate exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(KBTemplatePersistenceImpl.class);
	private static KBTemplate _nullKBTemplate = new KBTemplateImpl() {
			public Object clone() {
				return this;
			}

			public CacheModel<KBTemplate> toCacheModel() {
				return _nullKBTemplateCacheModel;
			}
		};

	private static CacheModel<KBTemplate> _nullKBTemplateCacheModel = new CacheModel<KBTemplate>() {
			public KBTemplate toEntityModel() {
				return _nullKBTemplate;
			}
		};
}