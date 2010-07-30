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

package com.liferay.knowledgebase.service.persistence;

import com.liferay.knowledgebase.NoSuchTemplateException;
import com.liferay.knowledgebase.model.Template;
import com.liferay.knowledgebase.model.impl.TemplateImpl;
import com.liferay.knowledgebase.model.impl.TemplateModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.annotation.BeanReference;
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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.portlet.messageboards.service.persistence.MBMessagePersistence;
import com.liferay.portlet.social.service.persistence.SocialActivityPersistence;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence for the template service.
 *
 * <p>
 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regnerate this class.
 * </p>
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TemplatePersistence
 * @see TemplateUtil
 * @generated
 */
public class TemplatePersistenceImpl extends BasePersistenceImpl<Template>
	implements TemplatePersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = TemplateImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_UUID = new FinderPath(TemplateModelImpl.ENTITY_CACHE_ENABLED,
			TemplateModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(TemplateModelImpl.ENTITY_CACHE_ENABLED,
			TemplateModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByUuid", new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(TemplateModelImpl.ENTITY_CACHE_ENABLED,
			TemplateModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(TemplateModelImpl.ENTITY_CACHE_ENABLED,
			TemplateModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_GROUPID = new FinderPath(TemplateModelImpl.ENTITY_CACHE_ENABLED,
			TemplateModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByGroupId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(TemplateModelImpl.ENTITY_CACHE_ENABLED,
			TemplateModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByGroupId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(TemplateModelImpl.ENTITY_CACHE_ENABLED,
			TemplateModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TemplateModelImpl.ENTITY_CACHE_ENABLED,
			TemplateModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	/**
	 * Caches the template in the entity cache if it is enabled.
	 *
	 * @param template the template to cache
	 */
	public void cacheResult(Template template) {
		EntityCacheUtil.putResult(TemplateModelImpl.ENTITY_CACHE_ENABLED,
			TemplateImpl.class, template.getPrimaryKey(), template);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { template.getUuid(), new Long(template.getGroupId()) },
			template);
	}

	/**
	 * Caches the templates in the entity cache if it is enabled.
	 *
	 * @param templates the templates to cache
	 */
	public void cacheResult(List<Template> templates) {
		for (Template template : templates) {
			if (EntityCacheUtil.getResult(
						TemplateModelImpl.ENTITY_CACHE_ENABLED,
						TemplateImpl.class, template.getPrimaryKey(), this) == null) {
				cacheResult(template);
			}
		}
	}

	/**
	 * Clears the cache for all templates.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache() {
		CacheRegistryUtil.clear(TemplateImpl.class.getName());
		EntityCacheUtil.clearCache(TemplateImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the template.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(Template template) {
		EntityCacheUtil.removeResult(TemplateModelImpl.ENTITY_CACHE_ENABLED,
			TemplateImpl.class, template.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { template.getUuid(), new Long(template.getGroupId()) });
	}

	/**
	 * Creates a new template with the primary key.
	 *
	 * @param templateId the primary key for the new template
	 * @return the new template
	 */
	public Template create(long templateId) {
		Template template = new TemplateImpl();

		template.setNew(true);
		template.setPrimaryKey(templateId);

		String uuid = PortalUUIDUtil.generate();

		template.setUuid(uuid);

		return template;
	}

	/**
	 * Removes the template with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the template to remove
	 * @return the template that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Template remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the template with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param templateId the primary key of the template to remove
	 * @return the template that was removed
	 * @throws com.liferay.knowledgebase.NoSuchTemplateException if a template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Template remove(long templateId)
		throws NoSuchTemplateException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Template template = (Template)session.get(TemplateImpl.class,
					new Long(templateId));

			if (template == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + templateId);
				}

				throw new NoSuchTemplateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					templateId);
			}

			return remove(template);
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

	protected Template removeImpl(Template template) throws SystemException {
		template = toUnwrappedModel(template);

		Session session = null;

		try {
			session = openSession();

			if (template.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(TemplateImpl.class,
						template.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(template);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		TemplateModelImpl templateModelImpl = (TemplateModelImpl)template;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				templateModelImpl.getOriginalUuid(),
				new Long(templateModelImpl.getOriginalGroupId())
			});

		EntityCacheUtil.removeResult(TemplateModelImpl.ENTITY_CACHE_ENABLED,
			TemplateImpl.class, template.getPrimaryKey());

		return template;
	}

	public Template updateImpl(
		com.liferay.knowledgebase.model.Template template, boolean merge)
		throws SystemException {
		template = toUnwrappedModel(template);

		boolean isNew = template.isNew();

		TemplateModelImpl templateModelImpl = (TemplateModelImpl)template;

		if (Validator.isNull(template.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			template.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, template, merge);

			template.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(TemplateModelImpl.ENTITY_CACHE_ENABLED,
			TemplateImpl.class, template.getPrimaryKey(), template);

		if (!isNew &&
				(!Validator.equals(template.getUuid(),
					templateModelImpl.getOriginalUuid()) ||
				(template.getGroupId() != templateModelImpl.getOriginalGroupId()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					templateModelImpl.getOriginalUuid(),
					new Long(templateModelImpl.getOriginalGroupId())
				});
		}

		if (isNew ||
				(!Validator.equals(template.getUuid(),
					templateModelImpl.getOriginalUuid()) ||
				(template.getGroupId() != templateModelImpl.getOriginalGroupId()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] { template.getUuid(), new Long(
						template.getGroupId()) }, template);
		}

		return template;
	}

	protected Template toUnwrappedModel(Template template) {
		if (template instanceof TemplateImpl) {
			return template;
		}

		TemplateImpl templateImpl = new TemplateImpl();

		templateImpl.setNew(template.isNew());
		templateImpl.setPrimaryKey(template.getPrimaryKey());

		templateImpl.setUuid(template.getUuid());
		templateImpl.setTemplateId(template.getTemplateId());
		templateImpl.setGroupId(template.getGroupId());
		templateImpl.setCompanyId(template.getCompanyId());
		templateImpl.setUserId(template.getUserId());
		templateImpl.setUserName(template.getUserName());
		templateImpl.setCreateDate(template.getCreateDate());
		templateImpl.setModifiedDate(template.getModifiedDate());
		templateImpl.setTitle(template.getTitle());
		templateImpl.setContent(template.getContent());
		templateImpl.setDescription(template.getDescription());

		return templateImpl;
	}

	/**
	 * Finds the template with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the template to find
	 * @return the template
	 * @throws com.liferay.portal.NoSuchModelException if a template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Template findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the template with the primary key or throws a {@link com.liferay.knowledgebase.NoSuchTemplateException} if it could not be found.
	 *
	 * @param templateId the primary key of the template to find
	 * @return the template
	 * @throws com.liferay.knowledgebase.NoSuchTemplateException if a template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Template findByPrimaryKey(long templateId)
		throws NoSuchTemplateException, SystemException {
		Template template = fetchByPrimaryKey(templateId);

		if (template == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + templateId);
			}

			throw new NoSuchTemplateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				templateId);
		}

		return template;
	}

	/**
	 * Finds the template with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the template to find
	 * @return the template, or <code>null</code> if a template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Template fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the template with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param templateId the primary key of the template to find
	 * @return the template, or <code>null</code> if a template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Template fetchByPrimaryKey(long templateId)
		throws SystemException {
		Template template = (Template)EntityCacheUtil.getResult(TemplateModelImpl.ENTITY_CACHE_ENABLED,
				TemplateImpl.class, templateId, this);

		if (template == null) {
			Session session = null;

			try {
				session = openSession();

				template = (Template)session.get(TemplateImpl.class,
						new Long(templateId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (template != null) {
					cacheResult(template);
				}

				closeSession(session);
			}
		}

		return template;
	}

	/**
	 * Finds all the templates where uuid = &#63;.
	 *
	 * @param uuid the uuid to search with
	 * @return the matching templates
	 * @throws SystemException if a system exception occurred
	 */
	public List<Template> findByUuid(String uuid) throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the templates where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid to search with
	 * @param start the lower bound of the range of templates to return
	 * @param end the upper bound of the range of templates to return (not inclusive)
	 * @return the range of matching templates
	 * @throws SystemException if a system exception occurred
	 */
	public List<Template> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Finds an ordered range of all the templates where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid to search with
	 * @param start the lower bound of the range of templates to return
	 * @param end the upper bound of the range of templates to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching templates
	 * @throws SystemException if a system exception occurred
	 */
	public List<Template> findByUuid(String uuid, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				uuid,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Template> list = (List<Template>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_UUID,
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

				query.append(_SQL_SELECT_TEMPLATE_WHERE);

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
					query.append(TemplateModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				list = (List<Template>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Template>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_UUID, finderArgs,
					list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first template in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching template
	 * @throws com.liferay.knowledgebase.NoSuchTemplateException if a matching template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Template findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchTemplateException, SystemException {
		List<Template> list = findByUuid(uuid, 0, 1, orderByComparator);

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
	 * Finds the last template in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching template
	 * @throws com.liferay.knowledgebase.NoSuchTemplateException if a matching template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Template findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchTemplateException, SystemException {
		int count = countByUuid(uuid);

		List<Template> list = findByUuid(uuid, count - 1, count,
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
	 * Finds the templates before and after the current template in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param templateId the primary key of the current template
	 * @param uuid the uuid to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next template
	 * @throws com.liferay.knowledgebase.NoSuchTemplateException if a template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Template[] findByUuid_PrevAndNext(long templateId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchTemplateException, SystemException {
		Template template = findByPrimaryKey(templateId);

		Session session = null;

		try {
			session = openSession();

			Template[] array = new TemplateImpl[3];

			array[0] = getByUuid_PrevAndNext(session, template, uuid,
					orderByComparator, true);

			array[1] = template;

			array[2] = getByUuid_PrevAndNext(session, template, uuid,
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

	protected Template getByUuid_PrevAndNext(Session session,
		Template template, String uuid, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TEMPLATE_WHERE);

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
			query.append(TemplateModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByValues(template);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Template> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds the template where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.knowledgebase.NoSuchTemplateException} if it could not be found.
	 *
	 * @param uuid the uuid to search with
	 * @param groupId the group id to search with
	 * @return the matching template
	 * @throws com.liferay.knowledgebase.NoSuchTemplateException if a matching template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Template findByUUID_G(String uuid, long groupId)
		throws NoSuchTemplateException, SystemException {
		Template template = fetchByUUID_G(uuid, groupId);

		if (template == null) {
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

		return template;
	}

	/**
	 * Finds the template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid to search with
	 * @param groupId the group id to search with
	 * @return the matching template, or <code>null</code> if a matching template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Template fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Finds the template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid to search with
	 * @param groupId the group id to search with
	 * @return the matching template, or <code>null</code> if a matching template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Template fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(4);

				query.append(_SQL_SELECT_TEMPLATE_WHERE);

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

				query.append(TemplateModelImpl.ORDER_BY_JPQL);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<Template> list = q.list();

				result = list;

				Template template = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					template = list.get(0);

					cacheResult(template);

					if ((template.getUuid() == null) ||
							!template.getUuid().equals(uuid) ||
							(template.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, template);
					}
				}

				return template;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, new ArrayList<Template>());
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (Template)result;
			}
		}
	}

	/**
	 * Finds all the templates where groupId = &#63;.
	 *
	 * @param groupId the group id to search with
	 * @return the matching templates
	 * @throws SystemException if a system exception occurred
	 */
	public List<Template> findByGroupId(long groupId) throws SystemException {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the templates where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group id to search with
	 * @param start the lower bound of the range of templates to return
	 * @param end the upper bound of the range of templates to return (not inclusive)
	 * @return the range of matching templates
	 * @throws SystemException if a system exception occurred
	 */
	public List<Template> findByGroupId(long groupId, int start, int end)
		throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the templates where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group id to search with
	 * @param start the lower bound of the range of templates to return
	 * @param end the upper bound of the range of templates to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching templates
	 * @throws SystemException if a system exception occurred
	 */
	public List<Template> findByGroupId(long groupId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Template> list = (List<Template>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_GROUPID,
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

				query.append(_SQL_SELECT_TEMPLATE_WHERE);

				query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				else {
					query.append(TemplateModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				list = (List<Template>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Template>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_GROUPID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first template in the ordered set where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching template
	 * @throws com.liferay.knowledgebase.NoSuchTemplateException if a matching template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Template findByGroupId_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchTemplateException, SystemException {
		List<Template> list = findByGroupId(groupId, 0, 1, orderByComparator);

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
	 * Finds the last template in the ordered set where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching template
	 * @throws com.liferay.knowledgebase.NoSuchTemplateException if a matching template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Template findByGroupId_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchTemplateException, SystemException {
		int count = countByGroupId(groupId);

		List<Template> list = findByGroupId(groupId, count - 1, count,
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
	 * Finds the templates before and after the current template in the ordered set where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param templateId the primary key of the current template
	 * @param groupId the group id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next template
	 * @throws com.liferay.knowledgebase.NoSuchTemplateException if a template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Template[] findByGroupId_PrevAndNext(long templateId, long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchTemplateException, SystemException {
		Template template = findByPrimaryKey(templateId);

		Session session = null;

		try {
			session = openSession();

			Template[] array = new TemplateImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, template, groupId,
					orderByComparator, true);

			array[1] = template;

			array[2] = getByGroupId_PrevAndNext(session, template, groupId,
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

	protected Template getByGroupId_PrevAndNext(Session session,
		Template template, long groupId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TEMPLATE_WHERE);

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
			query.append(TemplateModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(template);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Template> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Filters by the user's permissions and finds all the templates where groupId = &#63;.
	 *
	 * @param groupId the group id to search with
	 * @return the matching templates that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Template> filterFindByGroupId(long groupId)
		throws SystemException {
		return filterFindByGroupId(groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Filters by the user's permissions and finds a range of all the templates where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group id to search with
	 * @param start the lower bound of the range of templates to return
	 * @param end the upper bound of the range of templates to return (not inclusive)
	 * @return the range of matching templates that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Template> filterFindByGroupId(long groupId, int start, int end)
		throws SystemException {
		return filterFindByGroupId(groupId, start, end, null);
	}

	/**
	 * Filters by the user's permissions and finds an ordered range of all the templates where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group id to search with
	 * @param start the lower bound of the range of templates to return
	 * @param end the upper bound of the range of templates to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching templates that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Template> filterFindByGroupId(long groupId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId(groupId, start, end, orderByComparator);
		}

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

			query.append(_FILTER_SQL_SELECT_TEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TemplateModelImpl.ORDER_BY_JPQL);
			}

			String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
					Template.class.getName(), _FILTER_COLUMN_PK,
					_FILTER_COLUMN_USERID, groupId);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity(_FILTER_ENTITY_ALIAS, TemplateImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return (List<Template>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Finds all the templates.
	 *
	 * @return the templates
	 * @throws SystemException if a system exception occurred
	 */
	public List<Template> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the templates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of templates to return
	 * @param end the upper bound of the range of templates to return (not inclusive)
	 * @return the range of templates
	 * @throws SystemException if a system exception occurred
	 */
	public List<Template> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the templates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of templates to return
	 * @param end the upper bound of the range of templates to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of templates
	 * @throws SystemException if a system exception occurred
	 */
	public List<Template> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Template> list = (List<Template>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_TEMPLATE);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}
				else {
					sql = _SQL_SELECT_TEMPLATE.concat(TemplateModelImpl.ORDER_BY_JPQL);
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<Template>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Template>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Template>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the templates where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (Template template : findByUuid(uuid)) {
			remove(template);
		}
	}

	/**
	 * Removes the template where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid to search with
	 * @param groupId the group id to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUUID_G(String uuid, long groupId)
		throws NoSuchTemplateException, SystemException {
		Template template = findByUUID_G(uuid, groupId);

		remove(template);
	}

	/**
	 * Removes all the templates where groupId = &#63; from the database.
	 *
	 * @param groupId the group id to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByGroupId(long groupId) throws SystemException {
		for (Template template : findByGroupId(groupId)) {
			remove(template);
		}
	}

	/**
	 * Removes all the templates from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (Template template : findAll()) {
			remove(template);
		}
	}

	/**
	 * Counts all the templates where uuid = &#63;.
	 *
	 * @param uuid the uuid to search with
	 * @return the number of matching templates
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_TEMPLATE_WHERE);

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
	 * Counts all the templates where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid to search with
	 * @param groupId the group id to search with
	 * @return the number of matching templates
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUUID_G(String uuid, long groupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID_G,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(3);

				query.append(_SQL_COUNT_TEMPLATE_WHERE);

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
	 * Counts all the templates where groupId = &#63;.
	 *
	 * @param groupId the group id to search with
	 * @return the number of matching templates
	 * @throws SystemException if a system exception occurred
	 */
	public int countByGroupId(long groupId) throws SystemException {
		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_GROUPID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_TEMPLATE_WHERE);

				query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

				String sql = query.toString();

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
	 * Filters by the user's permissions and counts all the templates where groupId = &#63;.
	 *
	 * @param groupId the group id to search with
	 * @return the number of matching templates that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByGroupId(long groupId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupId(groupId);
		}

		Session session = null;

		try {
			session = openSession();

			StringBundler query = new StringBundler(2);

			query.append(_FILTER_SQL_COUNT_TEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
					Template.class.getName(), _FILTER_COLUMN_PK,
					_FILTER_COLUMN_USERID, groupId);

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
	 * Counts all the templates.
	 *
	 * @return the number of templates
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

				Query q = session.createQuery(_SQL_COUNT_TEMPLATE);

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
	 * Initializes the template persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.knowledgebase.model.Template")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Template>> listenersList = new ArrayList<ModelListener<Template>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Template>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(type = ArticlePersistence.class)
	protected ArticlePersistence articlePersistence;
	@BeanReference(type = TemplatePersistence.class)
	protected TemplatePersistence templatePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = MBMessagePersistence.class)
	protected MBMessagePersistence mbMessagePersistence;
	@BeanReference(type = SocialActivityPersistence.class)
	protected SocialActivityPersistence socialActivityPersistence;
	private static final String _SQL_SELECT_TEMPLATE = "SELECT template FROM Template template";
	private static final String _SQL_SELECT_TEMPLATE_WHERE = "SELECT template FROM Template template WHERE ";
	private static final String _SQL_COUNT_TEMPLATE = "SELECT COUNT(template) FROM Template template";
	private static final String _SQL_COUNT_TEMPLATE_WHERE = "SELECT COUNT(template) FROM Template template WHERE ";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "template.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "template.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(template.uuid IS NULL OR template.uuid = ?)";
	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "template.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "template.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(template.uuid IS NULL OR template.uuid = ?) AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "template.groupId = ?";
	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "template.groupId = ?";
	private static final String _FILTER_SQL_SELECT_TEMPLATE_WHERE = "SELECT DISTINCT {template.*} FROM KB_Template template WHERE ";
	private static final String _FILTER_SQL_COUNT_TEMPLATE_WHERE = "SELECT COUNT(DISTINCT template.templateId) AS COUNT_VALUE FROM KB_Template template WHERE ";
	private static final String _FILTER_COLUMN_PK = "template.templateId";
	private static final String _FILTER_COLUMN_USERID = "template.userId";
	private static final String _FILTER_ENTITY_ALIAS = "template";
	private static final String _ORDER_BY_ENTITY_ALIAS = "template.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Template exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Template exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(TemplatePersistenceImpl.class);
}