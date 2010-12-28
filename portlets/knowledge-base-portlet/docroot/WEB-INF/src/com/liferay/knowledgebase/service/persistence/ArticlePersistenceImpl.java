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

import com.liferay.knowledgebase.NoSuchArticleException;
import com.liferay.knowledgebase.model.Article;
import com.liferay.knowledgebase.model.impl.ArticleImpl;
import com.liferay.knowledgebase.model.impl.ArticleModelImpl;

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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.CompanyPersistence;
import com.liferay.portal.service.persistence.GroupPersistence;
import com.liferay.portal.service.persistence.LayoutPersistence;
import com.liferay.portal.service.persistence.PortletPreferencesPersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.SubscriptionPersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.WorkflowInstanceLinkPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.portlet.asset.service.persistence.AssetEntryPersistence;
import com.liferay.portlet.expando.service.persistence.ExpandoValuePersistence;
import com.liferay.portlet.social.service.persistence.SocialActivityPersistence;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the article service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ArticlePersistence
 * @see ArticleUtil
 * @generated
 */
public class ArticlePersistenceImpl extends BasePersistenceImpl<Article>
	implements ArticlePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ArticleUtil} to access the article persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ArticleImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_UUID = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByUuid", new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_RESOURCEPRIMKEY = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByResourcePrimKey",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_RESOURCEPRIMKEY = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByResourcePrimKey", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_R_V = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_ENTITY,
			"fetchByR_V",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_R_V = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByR_V",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_R_L = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByR_L",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_R_L = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByR_L",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_R_S = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByR_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_R_S = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByR_S",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_G_L = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByG_L",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_G_L = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByG_L",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_C_L = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByC_L",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_C_L = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByC_L",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_R_L_S = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByR_L_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_R_L_S = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByR_L_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_FIND_BY_G_P_L = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByG_P_L",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_G_P_L = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByG_P_L",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_FIND_BY_G_L_S = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByG_L_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_G_L_S = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByG_L_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_FIND_BY_C_L_S = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByC_L_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_C_L_S = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByC_L_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_FIND_BY_R_G_P_L = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByR_G_P_L",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_R_G_P_L = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByR_G_P_L",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_FIND_BY_G_P_L_S = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByG_P_L_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_G_P_L_S = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByG_P_L_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_FIND_BY_R_G_P_L_S = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByR_G_P_L_S",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_R_G_P_L_S = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByR_G_P_L_S",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	/**
	 * Caches the article in the entity cache if it is enabled.
	 *
	 * @param article the article to cache
	 */
	public void cacheResult(Article article) {
		EntityCacheUtil.putResult(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleImpl.class, article.getPrimaryKey(), article);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { article.getUuid(), new Long(article.getGroupId()) },
			article);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_R_V,
			new Object[] {
				new Long(article.getResourcePrimKey()),
				new Integer(article.getVersion())
			}, article);
	}

	/**
	 * Caches the articles in the entity cache if it is enabled.
	 *
	 * @param articles the articles to cache
	 */
	public void cacheResult(List<Article> articles) {
		for (Article article : articles) {
			if (EntityCacheUtil.getResult(
						ArticleModelImpl.ENTITY_CACHE_ENABLED,
						ArticleImpl.class, article.getPrimaryKey(), this) == null) {
				cacheResult(article);
			}
		}
	}

	/**
	 * Clears the cache for all articles.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache() {
		CacheRegistryUtil.clear(ArticleImpl.class.getName());
		EntityCacheUtil.clearCache(ArticleImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the article.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(Article article) {
		EntityCacheUtil.removeResult(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleImpl.class, article.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { article.getUuid(), new Long(article.getGroupId()) });

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_R_V,
			new Object[] {
				new Long(article.getResourcePrimKey()),
				new Integer(article.getVersion())
			});
	}

	/**
	 * Creates a new article with the primary key. Does not add the article to the database.
	 *
	 * @param articleId the primary key for the new article
	 * @return the new article
	 */
	public Article create(long articleId) {
		Article article = new ArticleImpl();

		article.setNew(true);
		article.setPrimaryKey(articleId);

		String uuid = PortalUUIDUtil.generate();

		article.setUuid(uuid);

		return article;
	}

	/**
	 * Removes the article with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the article to remove
	 * @return the article that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the article with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param articleId the primary key of the article to remove
	 * @return the article that was removed
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article remove(long articleId)
		throws NoSuchArticleException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Article article = (Article)session.get(ArticleImpl.class,
					new Long(articleId));

			if (article == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + articleId);
				}

				throw new NoSuchArticleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					articleId);
			}

			return remove(article);
		}
		catch (NoSuchArticleException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Article removeImpl(Article article) throws SystemException {
		article = toUnwrappedModel(article);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, article);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		ArticleModelImpl articleModelImpl = (ArticleModelImpl)article;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				articleModelImpl.getOriginalUuid(),
				new Long(articleModelImpl.getOriginalGroupId())
			});

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_R_V,
			new Object[] {
				new Long(articleModelImpl.getOriginalResourcePrimKey()),
				new Integer(articleModelImpl.getOriginalVersion())
			});

		EntityCacheUtil.removeResult(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleImpl.class, article.getPrimaryKey());

		return article;
	}

	public Article updateImpl(com.liferay.knowledgebase.model.Article article,
		boolean merge) throws SystemException {
		article = toUnwrappedModel(article);

		boolean isNew = article.isNew();

		ArticleModelImpl articleModelImpl = (ArticleModelImpl)article;

		if (Validator.isNull(article.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			article.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, article, merge);

			article.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleImpl.class, article.getPrimaryKey(), article);

		if (!isNew &&
				(!Validator.equals(article.getUuid(),
					articleModelImpl.getOriginalUuid()) ||
				(article.getGroupId() != articleModelImpl.getOriginalGroupId()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					articleModelImpl.getOriginalUuid(),
					new Long(articleModelImpl.getOriginalGroupId())
				});
		}

		if (isNew ||
				(!Validator.equals(article.getUuid(),
					articleModelImpl.getOriginalUuid()) ||
				(article.getGroupId() != articleModelImpl.getOriginalGroupId()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] { article.getUuid(), new Long(article.getGroupId()) },
				article);
		}

		if (!isNew &&
				((article.getResourcePrimKey() != articleModelImpl.getOriginalResourcePrimKey()) ||
				(article.getVersion() != articleModelImpl.getOriginalVersion()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_R_V,
				new Object[] {
					new Long(articleModelImpl.getOriginalResourcePrimKey()),
					new Integer(articleModelImpl.getOriginalVersion())
				});
		}

		if (isNew ||
				((article.getResourcePrimKey() != articleModelImpl.getOriginalResourcePrimKey()) ||
				(article.getVersion() != articleModelImpl.getOriginalVersion()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_R_V,
				new Object[] {
					new Long(article.getResourcePrimKey()),
					new Integer(article.getVersion())
				}, article);
		}

		return article;
	}

	protected Article toUnwrappedModel(Article article) {
		if (article instanceof ArticleImpl) {
			return article;
		}

		ArticleImpl articleImpl = new ArticleImpl();

		articleImpl.setNew(article.isNew());
		articleImpl.setPrimaryKey(article.getPrimaryKey());

		articleImpl.setUuid(article.getUuid());
		articleImpl.setArticleId(article.getArticleId());
		articleImpl.setResourcePrimKey(article.getResourcePrimKey());
		articleImpl.setGroupId(article.getGroupId());
		articleImpl.setCompanyId(article.getCompanyId());
		articleImpl.setUserId(article.getUserId());
		articleImpl.setUserName(article.getUserName());
		articleImpl.setCreateDate(article.getCreateDate());
		articleImpl.setModifiedDate(article.getModifiedDate());
		articleImpl.setParentResourcePrimKey(article.getParentResourcePrimKey());
		articleImpl.setVersion(article.getVersion());
		articleImpl.setTitle(article.getTitle());
		articleImpl.setContent(article.getContent());
		articleImpl.setDescription(article.getDescription());
		articleImpl.setPriority(article.getPriority());
		articleImpl.setLatest(article.getLatest());
		articleImpl.setStatus(article.getStatus());
		articleImpl.setStatusByUserId(article.getStatusByUserId());
		articleImpl.setStatusByUserName(article.getStatusByUserName());
		articleImpl.setStatusDate(article.getStatusDate());

		return articleImpl;
	}

	/**
	 * Finds the article with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the article to find
	 * @return the article
	 * @throws com.liferay.portal.NoSuchModelException if a article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the article with the primary key or throws a {@link com.liferay.knowledgebase.NoSuchArticleException} if it could not be found.
	 *
	 * @param articleId the primary key of the article to find
	 * @return the article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article findByPrimaryKey(long articleId)
		throws NoSuchArticleException, SystemException {
		Article article = fetchByPrimaryKey(articleId);

		if (article == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + articleId);
			}

			throw new NoSuchArticleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				articleId);
		}

		return article;
	}

	/**
	 * Finds the article with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the article to find
	 * @return the article, or <code>null</code> if a article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the article with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param articleId the primary key of the article to find
	 * @return the article, or <code>null</code> if a article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article fetchByPrimaryKey(long articleId) throws SystemException {
		Article article = (Article)EntityCacheUtil.getResult(ArticleModelImpl.ENTITY_CACHE_ENABLED,
				ArticleImpl.class, articleId, this);

		if (article == null) {
			Session session = null;

			try {
				session = openSession();

				article = (Article)session.get(ArticleImpl.class,
						new Long(articleId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (article != null) {
					cacheResult(article);
				}

				closeSession(session);
			}
		}

		return article;
	}

	/**
	 * Finds all the articles where uuid = &#63;.
	 *
	 * @param uuid the uuid to search with
	 * @return the matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByUuid(String uuid) throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the articles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @return the range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Finds an ordered range of all the articles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByUuid(String uuid, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				uuid,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Article> list = (List<Article>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_UUID,
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

			query.append(_SQL_SELECT_ARTICLE_WHERE);

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
				query.append(ArticleModelImpl.ORDER_BY_JPQL);
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

				list = (List<Article>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Finds the first article in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		List<Article> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last article in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		int count = countByUuid(uuid);

		List<Article> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the articles before and after the current article in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param articleId the primary key of the current article
	 * @param uuid the uuid to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article[] findByUuid_PrevAndNext(long articleId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		Article article = findByPrimaryKey(articleId);

		Session session = null;

		try {
			session = openSession();

			Article[] array = new ArticleImpl[3];

			array[0] = getByUuid_PrevAndNext(session, article, uuid,
					orderByComparator, true);

			array[1] = article;

			array[2] = getByUuid_PrevAndNext(session, article, uuid,
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

	protected Article getByUuid_PrevAndNext(Session session, Article article,
		String uuid, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ARTICLE_WHERE);

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
			query.append(ArticleModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByValues(article);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Article> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds the article where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.knowledgebase.NoSuchArticleException} if it could not be found.
	 *
	 * @param uuid the uuid to search with
	 * @param groupId the group ID to search with
	 * @return the matching article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article findByUUID_G(String uuid, long groupId)
		throws NoSuchArticleException, SystemException {
		Article article = fetchByUUID_G(uuid, groupId);

		if (article == null) {
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

			throw new NoSuchArticleException(msg.toString());
		}

		return article;
	}

	/**
	 * Finds the article where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid to search with
	 * @param groupId the group ID to search with
	 * @return the matching article, or <code>null</code> if a matching article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Finds the article where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid to search with
	 * @param groupId the group ID to search with
	 * @return the matching article, or <code>null</code> if a matching article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_ARTICLE_WHERE);

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

			query.append(ArticleModelImpl.ORDER_BY_JPQL);

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

				List<Article> list = q.list();

				result = list;

				Article article = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					article = list.get(0);

					cacheResult(article);

					if ((article.getUuid() == null) ||
							!article.getUuid().equals(uuid) ||
							(article.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, article);
					}
				}

				return article;
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
				return (Article)result;
			}
		}
	}

	/**
	 * Finds all the articles where resourcePrimKey = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @return the matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByResourcePrimKey(long resourcePrimKey)
		throws SystemException {
		return findByResourcePrimKey(resourcePrimKey, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the articles where resourcePrimKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @return the range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByResourcePrimKey(long resourcePrimKey, int start,
		int end) throws SystemException {
		return findByResourcePrimKey(resourcePrimKey, start, end, null);
	}

	/**
	 * Finds an ordered range of all the articles where resourcePrimKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByResourcePrimKey(long resourcePrimKey, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				resourcePrimKey,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Article> list = (List<Article>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_RESOURCEPRIMKEY,
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

			query.append(_SQL_SELECT_ARTICLE_WHERE);

			query.append(_FINDER_COLUMN_RESOURCEPRIMKEY_RESOURCEPRIMKEY_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				list = (List<Article>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_RESOURCEPRIMKEY,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_RESOURCEPRIMKEY,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first article in the ordered set where resourcePrimKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article findByResourcePrimKey_First(long resourcePrimKey,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		List<Article> list = findByResourcePrimKey(resourcePrimKey, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("resourcePrimKey=");
			msg.append(resourcePrimKey);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last article in the ordered set where resourcePrimKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article findByResourcePrimKey_Last(long resourcePrimKey,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		int count = countByResourcePrimKey(resourcePrimKey);

		List<Article> list = findByResourcePrimKey(resourcePrimKey, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("resourcePrimKey=");
			msg.append(resourcePrimKey);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the articles before and after the current article in the ordered set where resourcePrimKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param articleId the primary key of the current article
	 * @param resourcePrimKey the resource prim key to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article[] findByResourcePrimKey_PrevAndNext(long articleId,
		long resourcePrimKey, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		Article article = findByPrimaryKey(articleId);

		Session session = null;

		try {
			session = openSession();

			Article[] array = new ArticleImpl[3];

			array[0] = getByResourcePrimKey_PrevAndNext(session, article,
					resourcePrimKey, orderByComparator, true);

			array[1] = article;

			array[2] = getByResourcePrimKey_PrevAndNext(session, article,
					resourcePrimKey, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Article getByResourcePrimKey_PrevAndNext(Session session,
		Article article, long resourcePrimKey,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ARTICLE_WHERE);

		query.append(_FINDER_COLUMN_RESOURCEPRIMKEY_RESOURCEPRIMKEY_2);

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
			query.append(ArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resourcePrimKey);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(article);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Article> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds the article where resourcePrimKey = &#63; and version = &#63; or throws a {@link com.liferay.knowledgebase.NoSuchArticleException} if it could not be found.
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param version the version to search with
	 * @return the matching article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article findByR_V(long resourcePrimKey, int version)
		throws NoSuchArticleException, SystemException {
		Article article = fetchByR_V(resourcePrimKey, version);

		if (article == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("resourcePrimKey=");
			msg.append(resourcePrimKey);

			msg.append(", version=");
			msg.append(version);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchArticleException(msg.toString());
		}

		return article;
	}

	/**
	 * Finds the article where resourcePrimKey = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param version the version to search with
	 * @return the matching article, or <code>null</code> if a matching article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article fetchByR_V(long resourcePrimKey, int version)
		throws SystemException {
		return fetchByR_V(resourcePrimKey, version, true);
	}

	/**
	 * Finds the article where resourcePrimKey = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param version the version to search with
	 * @return the matching article, or <code>null</code> if a matching article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article fetchByR_V(long resourcePrimKey, int version,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { resourcePrimKey, version };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_R_V,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_ARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_V_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_V_VERSION_2);

			query.append(ArticleModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(version);

				List<Article> list = q.list();

				result = list;

				Article article = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_R_V,
						finderArgs, list);
				}
				else {
					article = list.get(0);

					cacheResult(article);

					if ((article.getResourcePrimKey() != resourcePrimKey) ||
							(article.getVersion() != version)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_R_V,
							finderArgs, article);
					}
				}

				return article;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_R_V,
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
				return (Article)result;
			}
		}
	}

	/**
	 * Finds all the articles where resourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param latest the latest to search with
	 * @return the matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByR_L(long resourcePrimKey, int latest)
		throws SystemException {
		return findByR_L(resourcePrimKey, latest, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the articles where resourcePrimKey = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param latest the latest to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @return the range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByR_L(long resourcePrimKey, int latest, int start,
		int end) throws SystemException {
		return findByR_L(resourcePrimKey, latest, start, end, null);
	}

	/**
	 * Finds an ordered range of all the articles where resourcePrimKey = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param latest the latest to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByR_L(long resourcePrimKey, int latest, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				resourcePrimKey, latest,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Article> list = (List<Article>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_R_L,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_L_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_L_LATEST_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(latest);

				list = (List<Article>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_R_L,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_R_L,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first article in the ordered set where resourcePrimKey = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param latest the latest to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article findByR_L_First(long resourcePrimKey, int latest,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		List<Article> list = findByR_L(resourcePrimKey, latest, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("resourcePrimKey=");
			msg.append(resourcePrimKey);

			msg.append(", latest=");
			msg.append(latest);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last article in the ordered set where resourcePrimKey = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param latest the latest to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article findByR_L_Last(long resourcePrimKey, int latest,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		int count = countByR_L(resourcePrimKey, latest);

		List<Article> list = findByR_L(resourcePrimKey, latest, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("resourcePrimKey=");
			msg.append(resourcePrimKey);

			msg.append(", latest=");
			msg.append(latest);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the articles before and after the current article in the ordered set where resourcePrimKey = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param articleId the primary key of the current article
	 * @param resourcePrimKey the resource prim key to search with
	 * @param latest the latest to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article[] findByR_L_PrevAndNext(long articleId,
		long resourcePrimKey, int latest, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		Article article = findByPrimaryKey(articleId);

		Session session = null;

		try {
			session = openSession();

			Article[] array = new ArticleImpl[3];

			array[0] = getByR_L_PrevAndNext(session, article, resourcePrimKey,
					latest, orderByComparator, true);

			array[1] = article;

			array[2] = getByR_L_PrevAndNext(session, article, resourcePrimKey,
					latest, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Article getByR_L_PrevAndNext(Session session, Article article,
		long resourcePrimKey, int latest, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ARTICLE_WHERE);

		query.append(_FINDER_COLUMN_R_L_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_L_LATEST_2);

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
			query.append(ArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resourcePrimKey);

		qPos.add(latest);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(article);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Article> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the articles where resourcePrimKey = any &#63; and latest = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies to search with
	 * @param latests the latests to search with
	 * @return the matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByR_L(long[] resourcePrimKeies, int[] latests)
		throws SystemException {
		return findByR_L(resourcePrimKeies, latests, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the articles where resourcePrimKey = any &#63; and latest = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies to search with
	 * @param latests the latests to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @return the range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByR_L(long[] resourcePrimKeies, int[] latests,
		int start, int end) throws SystemException {
		return findByR_L(resourcePrimKeies, latests, start, end, null);
	}

	/**
	 * Finds an ordered range of all the articles where resourcePrimKey = any &#63; and latest = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies to search with
	 * @param latests the latests to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByR_L(long[] resourcePrimKeies, int[] latests,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				StringUtil.merge(resourcePrimKeies), StringUtil.merge(latests),
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Article> list = (List<Article>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_R_L,
				finderArgs, this);

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_ARTICLE_WHERE);

			boolean conjunctionable = false;

			if ((resourcePrimKeies == null) || (resourcePrimKeies.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < resourcePrimKeies.length; i++) {
					query.append(_FINDER_COLUMN_R_L_RESOURCEPRIMKEY_5);

					if ((i + 1) < resourcePrimKeies.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if ((latests == null) || (latests.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < latests.length; i++) {
					query.append(_FINDER_COLUMN_R_L_LATEST_5);

					if ((i + 1) < latests.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (resourcePrimKeies != null) {
					qPos.add(resourcePrimKeies);
				}

				if (latests != null) {
					qPos.add(latests);
				}

				list = (List<Article>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_R_L,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_R_L,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds all the articles where resourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param status the status to search with
	 * @return the matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByR_S(long resourcePrimKey, int status)
		throws SystemException {
		return findByR_S(resourcePrimKey, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the articles where resourcePrimKey = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param status the status to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @return the range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByR_S(long resourcePrimKey, int status, int start,
		int end) throws SystemException {
		return findByR_S(resourcePrimKey, status, start, end, null);
	}

	/**
	 * Finds an ordered range of all the articles where resourcePrimKey = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param status the status to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByR_S(long resourcePrimKey, int status, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				resourcePrimKey, status,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Article> list = (List<Article>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_R_S,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_S_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(status);

				list = (List<Article>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_R_S,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_R_S,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first article in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param status the status to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article findByR_S_First(long resourcePrimKey, int status,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		List<Article> list = findByR_S(resourcePrimKey, status, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("resourcePrimKey=");
			msg.append(resourcePrimKey);

			msg.append(", status=");
			msg.append(status);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last article in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param status the status to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article findByR_S_Last(long resourcePrimKey, int status,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		int count = countByR_S(resourcePrimKey, status);

		List<Article> list = findByR_S(resourcePrimKey, status, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("resourcePrimKey=");
			msg.append(resourcePrimKey);

			msg.append(", status=");
			msg.append(status);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the articles before and after the current article in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param articleId the primary key of the current article
	 * @param resourcePrimKey the resource prim key to search with
	 * @param status the status to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article[] findByR_S_PrevAndNext(long articleId,
		long resourcePrimKey, int status, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		Article article = findByPrimaryKey(articleId);

		Session session = null;

		try {
			session = openSession();

			Article[] array = new ArticleImpl[3];

			array[0] = getByR_S_PrevAndNext(session, article, resourcePrimKey,
					status, orderByComparator, true);

			array[1] = article;

			array[2] = getByR_S_PrevAndNext(session, article, resourcePrimKey,
					status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Article getByR_S_PrevAndNext(Session session, Article article,
		long resourcePrimKey, int status, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ARTICLE_WHERE);

		query.append(_FINDER_COLUMN_R_S_RESOURCEPRIMKEY_2);

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
			query.append(ArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resourcePrimKey);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(article);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Article> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the articles where groupId = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @param latest the latest to search with
	 * @return the matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByG_L(long groupId, int latest)
		throws SystemException {
		return findByG_L(groupId, latest, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Finds a range of all the articles where groupId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param latest the latest to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @return the range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByG_L(long groupId, int latest, int start, int end)
		throws SystemException {
		return findByG_L(groupId, latest, start, end, null);
	}

	/**
	 * Finds an ordered range of all the articles where groupId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param latest the latest to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByG_L(long groupId, int latest, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, latest,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Article> list = (List<Article>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_G_L,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_L_GROUPID_2);

			query.append(_FINDER_COLUMN_G_L_LATEST_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(latest);

				list = (List<Article>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_G_L,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_G_L,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first article in the ordered set where groupId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param latest the latest to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article findByG_L_First(long groupId, int latest,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		List<Article> list = findByG_L(groupId, latest, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", latest=");
			msg.append(latest);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last article in the ordered set where groupId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param latest the latest to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article findByG_L_Last(long groupId, int latest,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		int count = countByG_L(groupId, latest);

		List<Article> list = findByG_L(groupId, latest, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", latest=");
			msg.append(latest);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the articles before and after the current article in the ordered set where groupId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param articleId the primary key of the current article
	 * @param groupId the group ID to search with
	 * @param latest the latest to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article[] findByG_L_PrevAndNext(long articleId, long groupId,
		int latest, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		Article article = findByPrimaryKey(articleId);

		Session session = null;

		try {
			session = openSession();

			Article[] array = new ArticleImpl[3];

			array[0] = getByG_L_PrevAndNext(session, article, groupId, latest,
					orderByComparator, true);

			array[1] = article;

			array[2] = getByG_L_PrevAndNext(session, article, groupId, latest,
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

	protected Article getByG_L_PrevAndNext(Session session, Article article,
		long groupId, int latest, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_L_GROUPID_2);

		query.append(_FINDER_COLUMN_G_L_LATEST_2);

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
			query.append(ArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(latest);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(article);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Article> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the articles where groupId = &#63; and latest = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param latests the latests to search with
	 * @return the matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByG_L(long groupId, int[] latests)
		throws SystemException {
		return findByG_L(groupId, latests, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the articles where groupId = &#63; and latest = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param latests the latests to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @return the range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByG_L(long groupId, int[] latests, int start,
		int end) throws SystemException {
		return findByG_L(groupId, latests, start, end, null);
	}

	/**
	 * Finds an ordered range of all the articles where groupId = &#63; and latest = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param latests the latests to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByG_L(long groupId, int[] latests, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, StringUtil.merge(latests),
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Article> list = (List<Article>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_G_L,
				finderArgs, this);

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_ARTICLE_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_L_GROUPID_5);

			conjunctionable = true;

			if ((latests == null) || (latests.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < latests.length; i++) {
					query.append(_FINDER_COLUMN_G_L_LATEST_5);

					if ((i + 1) < latests.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (latests != null) {
					qPos.add(latests);
				}

				list = (List<Article>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_G_L,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_G_L,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Filters by the user's permissions and finds all the articles where groupId = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @param latest the latest to search with
	 * @return the matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> filterFindByG_L(long groupId, int latest)
		throws SystemException {
		return filterFindByG_L(groupId, latest, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Filters by the user's permissions and finds a range of all the articles where groupId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param latest the latest to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @return the range of matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> filterFindByG_L(long groupId, int latest, int start,
		int end) throws SystemException {
		return filterFindByG_L(groupId, latest, start, end, null);
	}

	/**
	 * Filters by the user's permissions and finds an ordered range of all the articles where groupId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param latest the latest to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> filterFindByG_L(long groupId, int latest, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_L(groupId, latest, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_L_GROUPID_2);

		query.append(_FINDER_COLUMN_G_L_LATEST_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Article.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(latest);

			return (List<Article>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Filters by the user's permissions and finds all the articles where groupId = &#63; and latest = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param latests the latests to search with
	 * @return the matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> filterFindByG_L(long groupId, int[] latests)
		throws SystemException {
		return filterFindByG_L(groupId, latests, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Filters by the user's permissions and finds a range of all the articles where groupId = &#63; and latest = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param latests the latests to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @return the range of matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> filterFindByG_L(long groupId, int[] latests,
		int start, int end) throws SystemException {
		return filterFindByG_L(groupId, latests, start, end, null);
	}

	/**
	 * Filters by the user's permissions and finds an ordered range of all the articles where groupId = &#63; and latest = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param latests the latests to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> filterFindByG_L(long groupId, int[] latests,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_L(groupId, latests, start, end, orderByComparator);
		}

		StringBundler query = new StringBundler();

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean conjunctionable = false;

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_L_GROUPID_5);

		conjunctionable = true;

		if ((latests == null) || (latests.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < latests.length; i++) {
				query.append(_FINDER_COLUMN_G_L_LATEST_5);

				if ((i + 1) < latests.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			conjunctionable = true;
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Article.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (latests != null) {
				qPos.add(latests);
			}

			return (List<Article>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Finds all the articles where companyId = &#63; and latest = &#63;.
	 *
	 * @param companyId the company ID to search with
	 * @param latest the latest to search with
	 * @return the matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByC_L(long companyId, int latest)
		throws SystemException {
		return findByC_L(companyId, latest, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the articles where companyId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID to search with
	 * @param latest the latest to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @return the range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByC_L(long companyId, int latest, int start,
		int end) throws SystemException {
		return findByC_L(companyId, latest, start, end, null);
	}

	/**
	 * Finds an ordered range of all the articles where companyId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID to search with
	 * @param latest the latest to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByC_L(long companyId, int latest, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId, latest,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Article> list = (List<Article>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_C_L,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ARTICLE_WHERE);

			query.append(_FINDER_COLUMN_C_L_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_L_LATEST_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(latest);

				list = (List<Article>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_C_L,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_C_L,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first article in the ordered set where companyId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID to search with
	 * @param latest the latest to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article findByC_L_First(long companyId, int latest,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		List<Article> list = findByC_L(companyId, latest, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", latest=");
			msg.append(latest);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last article in the ordered set where companyId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID to search with
	 * @param latest the latest to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article findByC_L_Last(long companyId, int latest,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		int count = countByC_L(companyId, latest);

		List<Article> list = findByC_L(companyId, latest, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", latest=");
			msg.append(latest);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the articles before and after the current article in the ordered set where companyId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param articleId the primary key of the current article
	 * @param companyId the company ID to search with
	 * @param latest the latest to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article[] findByC_L_PrevAndNext(long articleId, long companyId,
		int latest, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		Article article = findByPrimaryKey(articleId);

		Session session = null;

		try {
			session = openSession();

			Article[] array = new ArticleImpl[3];

			array[0] = getByC_L_PrevAndNext(session, article, companyId,
					latest, orderByComparator, true);

			array[1] = article;

			array[2] = getByC_L_PrevAndNext(session, article, companyId,
					latest, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Article getByC_L_PrevAndNext(Session session, Article article,
		long companyId, int latest, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ARTICLE_WHERE);

		query.append(_FINDER_COLUMN_C_L_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_L_LATEST_2);

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
			query.append(ArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(latest);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(article);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Article> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the articles where companyId = &#63; and latest = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID to search with
	 * @param latests the latests to search with
	 * @return the matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByC_L(long companyId, int[] latests)
		throws SystemException {
		return findByC_L(companyId, latests, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the articles where companyId = &#63; and latest = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID to search with
	 * @param latests the latests to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @return the range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByC_L(long companyId, int[] latests, int start,
		int end) throws SystemException {
		return findByC_L(companyId, latests, start, end, null);
	}

	/**
	 * Finds an ordered range of all the articles where companyId = &#63; and latest = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID to search with
	 * @param latests the latests to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByC_L(long companyId, int[] latests, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId, StringUtil.merge(latests),
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Article> list = (List<Article>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_C_L,
				finderArgs, this);

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_ARTICLE_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_C_L_COMPANYID_5);

			conjunctionable = true;

			if ((latests == null) || (latests.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < latests.length; i++) {
					query.append(_FINDER_COLUMN_C_L_LATEST_5);

					if ((i + 1) < latests.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (latests != null) {
					qPos.add(latests);
				}

				list = (List<Article>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_C_L,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_C_L,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds all the articles where resourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @return the matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByR_L_S(long resourcePrimKey, int latest,
		int status) throws SystemException {
		return findByR_L_S(resourcePrimKey, latest, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the articles where resourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @return the range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByR_L_S(long resourcePrimKey, int latest,
		int status, int start, int end) throws SystemException {
		return findByR_L_S(resourcePrimKey, latest, status, start, end, null);
	}

	/**
	 * Finds an ordered range of all the articles where resourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByR_L_S(long resourcePrimKey, int latest,
		int status, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				resourcePrimKey, latest, status,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Article> list = (List<Article>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_R_L_S,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_ARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_L_S_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_L_S_LATEST_2);

			query.append(_FINDER_COLUMN_R_L_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(latest);

				qPos.add(status);

				list = (List<Article>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_R_L_S,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_R_L_S,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first article in the ordered set where resourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article findByR_L_S_First(long resourcePrimKey, int latest,
		int status, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		List<Article> list = findByR_L_S(resourcePrimKey, latest, status, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("resourcePrimKey=");
			msg.append(resourcePrimKey);

			msg.append(", latest=");
			msg.append(latest);

			msg.append(", status=");
			msg.append(status);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last article in the ordered set where resourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article findByR_L_S_Last(long resourcePrimKey, int latest,
		int status, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		int count = countByR_L_S(resourcePrimKey, latest, status);

		List<Article> list = findByR_L_S(resourcePrimKey, latest, status,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("resourcePrimKey=");
			msg.append(resourcePrimKey);

			msg.append(", latest=");
			msg.append(latest);

			msg.append(", status=");
			msg.append(status);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the articles before and after the current article in the ordered set where resourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param articleId the primary key of the current article
	 * @param resourcePrimKey the resource prim key to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article[] findByR_L_S_PrevAndNext(long articleId,
		long resourcePrimKey, int latest, int status,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		Article article = findByPrimaryKey(articleId);

		Session session = null;

		try {
			session = openSession();

			Article[] array = new ArticleImpl[3];

			array[0] = getByR_L_S_PrevAndNext(session, article,
					resourcePrimKey, latest, status, orderByComparator, true);

			array[1] = article;

			array[2] = getByR_L_S_PrevAndNext(session, article,
					resourcePrimKey, latest, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Article getByR_L_S_PrevAndNext(Session session, Article article,
		long resourcePrimKey, int latest, int status,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ARTICLE_WHERE);

		query.append(_FINDER_COLUMN_R_L_S_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_L_S_LATEST_2);

		query.append(_FINDER_COLUMN_R_L_S_STATUS_2);

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
			query.append(ArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resourcePrimKey);

		qPos.add(latest);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(article);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Article> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the articles where resourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies to search with
	 * @param latests the latests to search with
	 * @param status the status to search with
	 * @return the matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByR_L_S(long[] resourcePrimKeies, int[] latests,
		int status) throws SystemException {
		return findByR_L_S(resourcePrimKeies, latests, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the articles where resourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies to search with
	 * @param latests the latests to search with
	 * @param status the status to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @return the range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByR_L_S(long[] resourcePrimKeies, int[] latests,
		int status, int start, int end) throws SystemException {
		return findByR_L_S(resourcePrimKeies, latests, status, start, end, null);
	}

	/**
	 * Finds an ordered range of all the articles where resourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies to search with
	 * @param latests the latests to search with
	 * @param status the status to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByR_L_S(long[] resourcePrimKeies, int[] latests,
		int status, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				StringUtil.merge(resourcePrimKeies), StringUtil.merge(latests),
				status,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Article> list = (List<Article>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_R_L_S,
				finderArgs, this);

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_ARTICLE_WHERE);

			boolean conjunctionable = false;

			if ((resourcePrimKeies == null) || (resourcePrimKeies.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < resourcePrimKeies.length; i++) {
					query.append(_FINDER_COLUMN_R_L_S_RESOURCEPRIMKEY_5);

					if ((i + 1) < resourcePrimKeies.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if ((latests == null) || (latests.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < latests.length; i++) {
					query.append(_FINDER_COLUMN_R_L_S_LATEST_5);

					if ((i + 1) < latests.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_R_L_S_STATUS_5);

			conjunctionable = true;

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (resourcePrimKeies != null) {
					qPos.add(resourcePrimKeies);
				}

				if (latests != null) {
					qPos.add(latests);
				}

				qPos.add(status);

				list = (List<Article>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_R_L_S,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_R_L_S,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @return the matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByG_P_L(long groupId, long parentResourcePrimKey,
		int latest) throws SystemException {
		return findByG_P_L(groupId, parentResourcePrimKey, latest,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @return the range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByG_P_L(long groupId, long parentResourcePrimKey,
		int latest, int start, int end) throws SystemException {
		return findByG_P_L(groupId, parentResourcePrimKey, latest, start, end,
			null);
	}

	/**
	 * Finds an ordered range of all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByG_P_L(long groupId, long parentResourcePrimKey,
		int latest, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, parentResourcePrimKey, latest,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Article> list = (List<Article>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_G_P_L,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_ARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_P_L_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_L_PARENTRESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_G_P_L_LATEST_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentResourcePrimKey);

				qPos.add(latest);

				list = (List<Article>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_G_P_L,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_G_P_L,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article findByG_P_L_First(long groupId, long parentResourcePrimKey,
		int latest, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		List<Article> list = findByG_P_L(groupId, parentResourcePrimKey,
				latest, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", parentResourcePrimKey=");
			msg.append(parentResourcePrimKey);

			msg.append(", latest=");
			msg.append(latest);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article findByG_P_L_Last(long groupId, long parentResourcePrimKey,
		int latest, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		int count = countByG_P_L(groupId, parentResourcePrimKey, latest);

		List<Article> list = findByG_P_L(groupId, parentResourcePrimKey,
				latest, count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", parentResourcePrimKey=");
			msg.append(parentResourcePrimKey);

			msg.append(", latest=");
			msg.append(latest);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the articles before and after the current article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param articleId the primary key of the current article
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article[] findByG_P_L_PrevAndNext(long articleId, long groupId,
		long parentResourcePrimKey, int latest,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		Article article = findByPrimaryKey(articleId);

		Session session = null;

		try {
			session = openSession();

			Article[] array = new ArticleImpl[3];

			array[0] = getByG_P_L_PrevAndNext(session, article, groupId,
					parentResourcePrimKey, latest, orderByComparator, true);

			array[1] = article;

			array[2] = getByG_P_L_PrevAndNext(session, article, groupId,
					parentResourcePrimKey, latest, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Article getByG_P_L_PrevAndNext(Session session, Article article,
		long groupId, long parentResourcePrimKey, int latest,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_P_L_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_L_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_G_P_L_LATEST_2);

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
			query.append(ArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(parentResourcePrimKey);

		qPos.add(latest);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(article);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Article> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKeies the parent resource prim keies to search with
	 * @param latests the latests to search with
	 * @return the matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByG_P_L(long groupId,
		long[] parentResourcePrimKeies, int[] latests)
		throws SystemException {
		return findByG_P_L(groupId, parentResourcePrimKeies, latests,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKeies the parent resource prim keies to search with
	 * @param latests the latests to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @return the range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByG_P_L(long groupId,
		long[] parentResourcePrimKeies, int[] latests, int start, int end)
		throws SystemException {
		return findByG_P_L(groupId, parentResourcePrimKeies, latests, start,
			end, null);
	}

	/**
	 * Finds an ordered range of all the articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKeies the parent resource prim keies to search with
	 * @param latests the latests to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByG_P_L(long groupId,
		long[] parentResourcePrimKeies, int[] latests, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, StringUtil.merge(parentResourcePrimKeies),
				StringUtil.merge(latests),
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Article> list = (List<Article>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_G_P_L,
				finderArgs, this);

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_ARTICLE_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_L_GROUPID_5);

			conjunctionable = true;

			if ((parentResourcePrimKeies == null) ||
					(parentResourcePrimKeies.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < parentResourcePrimKeies.length; i++) {
					query.append(_FINDER_COLUMN_G_P_L_PARENTRESOURCEPRIMKEY_5);

					if ((i + 1) < parentResourcePrimKeies.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if ((latests == null) || (latests.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < latests.length; i++) {
					query.append(_FINDER_COLUMN_G_P_L_LATEST_5);

					if ((i + 1) < latests.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (parentResourcePrimKeies != null) {
					qPos.add(parentResourcePrimKeies);
				}

				if (latests != null) {
					qPos.add(latests);
				}

				list = (List<Article>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_G_P_L,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_G_P_L,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Filters by the user's permissions and finds all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @return the matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> filterFindByG_P_L(long groupId,
		long parentResourcePrimKey, int latest) throws SystemException {
		return filterFindByG_P_L(groupId, parentResourcePrimKey, latest,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Filters by the user's permissions and finds a range of all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @return the range of matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> filterFindByG_P_L(long groupId,
		long parentResourcePrimKey, int latest, int start, int end)
		throws SystemException {
		return filterFindByG_P_L(groupId, parentResourcePrimKey, latest, start,
			end, null);
	}

	/**
	 * Filters by the user's permissions and finds an ordered range of all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> filterFindByG_P_L(long groupId,
		long parentResourcePrimKey, int latest, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_L(groupId, parentResourcePrimKey, latest, start,
				end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_P_L_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_L_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_G_P_L_LATEST_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Article.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentResourcePrimKey);

			qPos.add(latest);

			return (List<Article>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Filters by the user's permissions and finds all the articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKeies the parent resource prim keies to search with
	 * @param latests the latests to search with
	 * @return the matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> filterFindByG_P_L(long groupId,
		long[] parentResourcePrimKeies, int[] latests)
		throws SystemException {
		return filterFindByG_P_L(groupId, parentResourcePrimKeies, latests,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Filters by the user's permissions and finds a range of all the articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKeies the parent resource prim keies to search with
	 * @param latests the latests to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @return the range of matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> filterFindByG_P_L(long groupId,
		long[] parentResourcePrimKeies, int[] latests, int start, int end)
		throws SystemException {
		return filterFindByG_P_L(groupId, parentResourcePrimKeies, latests,
			start, end, null);
	}

	/**
	 * Filters by the user's permissions and finds an ordered range of all the articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKeies the parent resource prim keies to search with
	 * @param latests the latests to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> filterFindByG_P_L(long groupId,
		long[] parentResourcePrimKeies, int[] latests, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_L(groupId, parentResourcePrimKeies, latests,
				start, end, orderByComparator);
		}

		StringBundler query = new StringBundler();

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean conjunctionable = false;

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_L_GROUPID_5);

		conjunctionable = true;

		if ((parentResourcePrimKeies == null) ||
				(parentResourcePrimKeies.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < parentResourcePrimKeies.length; i++) {
				query.append(_FINDER_COLUMN_G_P_L_PARENTRESOURCEPRIMKEY_5);

				if ((i + 1) < parentResourcePrimKeies.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			conjunctionable = true;
		}

		if ((latests == null) || (latests.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < latests.length; i++) {
				query.append(_FINDER_COLUMN_G_P_L_LATEST_5);

				if ((i + 1) < latests.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			conjunctionable = true;
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Article.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (parentResourcePrimKeies != null) {
				qPos.add(parentResourcePrimKeies);
			}

			if (latests != null) {
				qPos.add(latests);
			}

			return (List<Article>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Finds all the articles where groupId = &#63; and latest = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @return the matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByG_L_S(long groupId, int latest, int status)
		throws SystemException {
		return findByG_L_S(groupId, latest, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the articles where groupId = &#63; and latest = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @return the range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByG_L_S(long groupId, int latest, int status,
		int start, int end) throws SystemException {
		return findByG_L_S(groupId, latest, status, start, end, null);
	}

	/**
	 * Finds an ordered range of all the articles where groupId = &#63; and latest = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByG_L_S(long groupId, int latest, int status,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, latest, status,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Article> list = (List<Article>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_G_L_S,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_ARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_L_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_L_S_LATEST_2);

			query.append(_FINDER_COLUMN_G_L_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(latest);

				qPos.add(status);

				list = (List<Article>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_G_L_S,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_G_L_S,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first article in the ordered set where groupId = &#63; and latest = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article findByG_L_S_First(long groupId, int latest, int status,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		List<Article> list = findByG_L_S(groupId, latest, status, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", latest=");
			msg.append(latest);

			msg.append(", status=");
			msg.append(status);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last article in the ordered set where groupId = &#63; and latest = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article findByG_L_S_Last(long groupId, int latest, int status,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		int count = countByG_L_S(groupId, latest, status);

		List<Article> list = findByG_L_S(groupId, latest, status, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", latest=");
			msg.append(latest);

			msg.append(", status=");
			msg.append(status);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the articles before and after the current article in the ordered set where groupId = &#63; and latest = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param articleId the primary key of the current article
	 * @param groupId the group ID to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article[] findByG_L_S_PrevAndNext(long articleId, long groupId,
		int latest, int status, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		Article article = findByPrimaryKey(articleId);

		Session session = null;

		try {
			session = openSession();

			Article[] array = new ArticleImpl[3];

			array[0] = getByG_L_S_PrevAndNext(session, article, groupId,
					latest, status, orderByComparator, true);

			array[1] = article;

			array[2] = getByG_L_S_PrevAndNext(session, article, groupId,
					latest, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Article getByG_L_S_PrevAndNext(Session session, Article article,
		long groupId, int latest, int status,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_L_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_L_S_LATEST_2);

		query.append(_FINDER_COLUMN_G_L_S_STATUS_2);

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
			query.append(ArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(latest);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(article);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Article> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the articles where groupId = &#63; and latest = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param latests the latests to search with
	 * @param status the status to search with
	 * @return the matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByG_L_S(long groupId, int[] latests, int status)
		throws SystemException {
		return findByG_L_S(groupId, latests, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the articles where groupId = &#63; and latest = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param latests the latests to search with
	 * @param status the status to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @return the range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByG_L_S(long groupId, int[] latests, int status,
		int start, int end) throws SystemException {
		return findByG_L_S(groupId, latests, status, start, end, null);
	}

	/**
	 * Finds an ordered range of all the articles where groupId = &#63; and latest = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param latests the latests to search with
	 * @param status the status to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByG_L_S(long groupId, int[] latests, int status,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, StringUtil.merge(latests), status,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Article> list = (List<Article>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_G_L_S,
				finderArgs, this);

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_ARTICLE_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_L_S_GROUPID_5);

			conjunctionable = true;

			if ((latests == null) || (latests.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < latests.length; i++) {
					query.append(_FINDER_COLUMN_G_L_S_LATEST_5);

					if ((i + 1) < latests.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_L_S_STATUS_5);

			conjunctionable = true;

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (latests != null) {
					qPos.add(latests);
				}

				qPos.add(status);

				list = (List<Article>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_G_L_S,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_G_L_S,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Filters by the user's permissions and finds all the articles where groupId = &#63; and latest = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @return the matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> filterFindByG_L_S(long groupId, int latest, int status)
		throws SystemException {
		return filterFindByG_L_S(groupId, latest, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Filters by the user's permissions and finds a range of all the articles where groupId = &#63; and latest = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @return the range of matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> filterFindByG_L_S(long groupId, int latest,
		int status, int start, int end) throws SystemException {
		return filterFindByG_L_S(groupId, latest, status, start, end, null);
	}

	/**
	 * Filters by the user's permissions and finds an ordered range of all the articles where groupId = &#63; and latest = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> filterFindByG_L_S(long groupId, int latest,
		int status, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_L_S(groupId, latest, status, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_L_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_L_S_LATEST_2);

		query.append(_FINDER_COLUMN_G_L_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Article.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(latest);

			qPos.add(status);

			return (List<Article>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Filters by the user's permissions and finds all the articles where groupId = &#63; and latest = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param latests the latests to search with
	 * @param status the status to search with
	 * @return the matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> filterFindByG_L_S(long groupId, int[] latests,
		int status) throws SystemException {
		return filterFindByG_L_S(groupId, latests, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Filters by the user's permissions and finds a range of all the articles where groupId = &#63; and latest = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param latests the latests to search with
	 * @param status the status to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @return the range of matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> filterFindByG_L_S(long groupId, int[] latests,
		int status, int start, int end) throws SystemException {
		return filterFindByG_L_S(groupId, latests, status, start, end, null);
	}

	/**
	 * Filters by the user's permissions and finds an ordered range of all the articles where groupId = &#63; and latest = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param latests the latests to search with
	 * @param status the status to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> filterFindByG_L_S(long groupId, int[] latests,
		int status, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_L_S(groupId, latests, status, start, end,
				orderByComparator);
		}

		StringBundler query = new StringBundler();

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean conjunctionable = false;

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_L_S_GROUPID_5);

		conjunctionable = true;

		if ((latests == null) || (latests.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < latests.length; i++) {
				query.append(_FINDER_COLUMN_G_L_S_LATEST_5);

				if ((i + 1) < latests.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			conjunctionable = true;
		}

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_L_S_STATUS_5);

		conjunctionable = true;

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Article.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (latests != null) {
				qPos.add(latests);
			}

			qPos.add(status);

			return (List<Article>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Finds all the articles where companyId = &#63; and latest = &#63; and status = &#63;.
	 *
	 * @param companyId the company ID to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @return the matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByC_L_S(long companyId, int latest, int status)
		throws SystemException {
		return findByC_L_S(companyId, latest, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the articles where companyId = &#63; and latest = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @return the range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByC_L_S(long companyId, int latest, int status,
		int start, int end) throws SystemException {
		return findByC_L_S(companyId, latest, status, start, end, null);
	}

	/**
	 * Finds an ordered range of all the articles where companyId = &#63; and latest = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByC_L_S(long companyId, int latest, int status,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId, latest, status,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Article> list = (List<Article>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_C_L_S,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_ARTICLE_WHERE);

			query.append(_FINDER_COLUMN_C_L_S_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_L_S_LATEST_2);

			query.append(_FINDER_COLUMN_C_L_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(latest);

				qPos.add(status);

				list = (List<Article>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_C_L_S,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_C_L_S,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first article in the ordered set where companyId = &#63; and latest = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article findByC_L_S_First(long companyId, int latest, int status,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		List<Article> list = findByC_L_S(companyId, latest, status, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", latest=");
			msg.append(latest);

			msg.append(", status=");
			msg.append(status);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last article in the ordered set where companyId = &#63; and latest = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article findByC_L_S_Last(long companyId, int latest, int status,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		int count = countByC_L_S(companyId, latest, status);

		List<Article> list = findByC_L_S(companyId, latest, status, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", latest=");
			msg.append(latest);

			msg.append(", status=");
			msg.append(status);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the articles before and after the current article in the ordered set where companyId = &#63; and latest = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param articleId the primary key of the current article
	 * @param companyId the company ID to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article[] findByC_L_S_PrevAndNext(long articleId, long companyId,
		int latest, int status, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		Article article = findByPrimaryKey(articleId);

		Session session = null;

		try {
			session = openSession();

			Article[] array = new ArticleImpl[3];

			array[0] = getByC_L_S_PrevAndNext(session, article, companyId,
					latest, status, orderByComparator, true);

			array[1] = article;

			array[2] = getByC_L_S_PrevAndNext(session, article, companyId,
					latest, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Article getByC_L_S_PrevAndNext(Session session, Article article,
		long companyId, int latest, int status,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ARTICLE_WHERE);

		query.append(_FINDER_COLUMN_C_L_S_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_L_S_LATEST_2);

		query.append(_FINDER_COLUMN_C_L_S_STATUS_2);

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
			query.append(ArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(latest);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(article);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Article> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the articles where companyId = &#63; and latest = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID to search with
	 * @param latests the latests to search with
	 * @param status the status to search with
	 * @return the matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByC_L_S(long companyId, int[] latests, int status)
		throws SystemException {
		return findByC_L_S(companyId, latests, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the articles where companyId = &#63; and latest = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID to search with
	 * @param latests the latests to search with
	 * @param status the status to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @return the range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByC_L_S(long companyId, int[] latests, int status,
		int start, int end) throws SystemException {
		return findByC_L_S(companyId, latests, status, start, end, null);
	}

	/**
	 * Finds an ordered range of all the articles where companyId = &#63; and latest = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID to search with
	 * @param latests the latests to search with
	 * @param status the status to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByC_L_S(long companyId, int[] latests, int status,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId, StringUtil.merge(latests), status,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Article> list = (List<Article>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_C_L_S,
				finderArgs, this);

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_ARTICLE_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_C_L_S_COMPANYID_5);

			conjunctionable = true;

			if ((latests == null) || (latests.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < latests.length; i++) {
					query.append(_FINDER_COLUMN_C_L_S_LATEST_5);

					if ((i + 1) < latests.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_C_L_S_STATUS_5);

			conjunctionable = true;

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (latests != null) {
					qPos.add(latests);
				}

				qPos.add(status);

				list = (List<Article>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_C_L_S,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_C_L_S,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds all the articles where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @return the matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByR_G_P_L(long resourcePrimKey, long groupId,
		long parentResourcePrimKey, int latest) throws SystemException {
		return findByR_G_P_L(resourcePrimKey, groupId, parentResourcePrimKey,
			latest, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the articles where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @return the range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByR_G_P_L(long resourcePrimKey, long groupId,
		long parentResourcePrimKey, int latest, int start, int end)
		throws SystemException {
		return findByR_G_P_L(resourcePrimKey, groupId, parentResourcePrimKey,
			latest, start, end, null);
	}

	/**
	 * Finds an ordered range of all the articles where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByR_G_P_L(long resourcePrimKey, long groupId,
		long parentResourcePrimKey, int latest, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				resourcePrimKey, groupId, parentResourcePrimKey, latest,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Article> list = (List<Article>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_R_G_P_L,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_ARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_G_P_L_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_G_P_L_GROUPID_2);

			query.append(_FINDER_COLUMN_R_G_P_L_PARENTRESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_G_P_L_LATEST_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(groupId);

				qPos.add(parentResourcePrimKey);

				qPos.add(latest);

				list = (List<Article>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_R_G_P_L,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_R_G_P_L,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article findByR_G_P_L_First(long resourcePrimKey, long groupId,
		long parentResourcePrimKey, int latest,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		List<Article> list = findByR_G_P_L(resourcePrimKey, groupId,
				parentResourcePrimKey, latest, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("resourcePrimKey=");
			msg.append(resourcePrimKey);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(", parentResourcePrimKey=");
			msg.append(parentResourcePrimKey);

			msg.append(", latest=");
			msg.append(latest);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article findByR_G_P_L_Last(long resourcePrimKey, long groupId,
		long parentResourcePrimKey, int latest,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		int count = countByR_G_P_L(resourcePrimKey, groupId,
				parentResourcePrimKey, latest);

		List<Article> list = findByR_G_P_L(resourcePrimKey, groupId,
				parentResourcePrimKey, latest, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("resourcePrimKey=");
			msg.append(resourcePrimKey);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(", parentResourcePrimKey=");
			msg.append(parentResourcePrimKey);

			msg.append(", latest=");
			msg.append(latest);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the articles before and after the current article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param articleId the primary key of the current article
	 * @param resourcePrimKey the resource prim key to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article[] findByR_G_P_L_PrevAndNext(long articleId,
		long resourcePrimKey, long groupId, long parentResourcePrimKey,
		int latest, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		Article article = findByPrimaryKey(articleId);

		Session session = null;

		try {
			session = openSession();

			Article[] array = new ArticleImpl[3];

			array[0] = getByR_G_P_L_PrevAndNext(session, article,
					resourcePrimKey, groupId, parentResourcePrimKey, latest,
					orderByComparator, true);

			array[1] = article;

			array[2] = getByR_G_P_L_PrevAndNext(session, article,
					resourcePrimKey, groupId, parentResourcePrimKey, latest,
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

	protected Article getByR_G_P_L_PrevAndNext(Session session,
		Article article, long resourcePrimKey, long groupId,
		long parentResourcePrimKey, int latest,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ARTICLE_WHERE);

		query.append(_FINDER_COLUMN_R_G_P_L_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_P_L_GROUPID_2);

		query.append(_FINDER_COLUMN_R_G_P_L_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_P_L_LATEST_2);

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
			query.append(ArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resourcePrimKey);

		qPos.add(groupId);

		qPos.add(parentResourcePrimKey);

		qPos.add(latest);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(article);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Article> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the articles where resourcePrimKey = any &#63; and groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKeies the parent resource prim keies to search with
	 * @param latests the latests to search with
	 * @return the matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByR_G_P_L(long[] resourcePrimKeies, long groupId,
		long[] parentResourcePrimKeies, int[] latests)
		throws SystemException {
		return findByR_G_P_L(resourcePrimKeies, groupId,
			parentResourcePrimKeies, latests, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the articles where resourcePrimKey = any &#63; and groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKeies the parent resource prim keies to search with
	 * @param latests the latests to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @return the range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByR_G_P_L(long[] resourcePrimKeies, long groupId,
		long[] parentResourcePrimKeies, int[] latests, int start, int end)
		throws SystemException {
		return findByR_G_P_L(resourcePrimKeies, groupId,
			parentResourcePrimKeies, latests, start, end, null);
	}

	/**
	 * Finds an ordered range of all the articles where resourcePrimKey = any &#63; and groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKeies the parent resource prim keies to search with
	 * @param latests the latests to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByR_G_P_L(long[] resourcePrimKeies, long groupId,
		long[] parentResourcePrimKeies, int[] latests, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				StringUtil.merge(resourcePrimKeies), groupId,
				StringUtil.merge(parentResourcePrimKeies),
				StringUtil.merge(latests),
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Article> list = (List<Article>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_R_G_P_L,
				finderArgs, this);

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_ARTICLE_WHERE);

			boolean conjunctionable = false;

			if ((resourcePrimKeies == null) || (resourcePrimKeies.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < resourcePrimKeies.length; i++) {
					query.append(_FINDER_COLUMN_R_G_P_L_RESOURCEPRIMKEY_5);

					if ((i + 1) < resourcePrimKeies.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_R_G_P_L_GROUPID_5);

			conjunctionable = true;

			if ((parentResourcePrimKeies == null) ||
					(parentResourcePrimKeies.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < parentResourcePrimKeies.length; i++) {
					query.append(_FINDER_COLUMN_R_G_P_L_PARENTRESOURCEPRIMKEY_5);

					if ((i + 1) < parentResourcePrimKeies.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if ((latests == null) || (latests.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < latests.length; i++) {
					query.append(_FINDER_COLUMN_R_G_P_L_LATEST_5);

					if ((i + 1) < latests.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (resourcePrimKeies != null) {
					qPos.add(resourcePrimKeies);
				}

				qPos.add(groupId);

				if (parentResourcePrimKeies != null) {
					qPos.add(parentResourcePrimKeies);
				}

				if (latests != null) {
					qPos.add(latests);
				}

				list = (List<Article>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_R_G_P_L,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_R_G_P_L,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Filters by the user's permissions and finds all the articles where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @return the matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> filterFindByR_G_P_L(long resourcePrimKey,
		long groupId, long parentResourcePrimKey, int latest)
		throws SystemException {
		return filterFindByR_G_P_L(resourcePrimKey, groupId,
			parentResourcePrimKey, latest, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Filters by the user's permissions and finds a range of all the articles where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @return the range of matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> filterFindByR_G_P_L(long resourcePrimKey,
		long groupId, long parentResourcePrimKey, int latest, int start, int end)
		throws SystemException {
		return filterFindByR_G_P_L(resourcePrimKey, groupId,
			parentResourcePrimKey, latest, start, end, null);
	}

	/**
	 * Filters by the user's permissions and finds an ordered range of all the articles where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> filterFindByR_G_P_L(long resourcePrimKey,
		long groupId, long parentResourcePrimKey, int latest, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByR_G_P_L(resourcePrimKey, groupId,
				parentResourcePrimKey, latest, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_R_G_P_L_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_P_L_GROUPID_2);

		query.append(_FINDER_COLUMN_R_G_P_L_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_P_L_LATEST_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Article.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(resourcePrimKey);

			qPos.add(groupId);

			qPos.add(parentResourcePrimKey);

			qPos.add(latest);

			return (List<Article>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Filters by the user's permissions and finds all the articles where resourcePrimKey = any &#63; and groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKeies the parent resource prim keies to search with
	 * @param latests the latests to search with
	 * @return the matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> filterFindByR_G_P_L(long[] resourcePrimKeies,
		long groupId, long[] parentResourcePrimKeies, int[] latests)
		throws SystemException {
		return filterFindByR_G_P_L(resourcePrimKeies, groupId,
			parentResourcePrimKeies, latests, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Filters by the user's permissions and finds a range of all the articles where resourcePrimKey = any &#63; and groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKeies the parent resource prim keies to search with
	 * @param latests the latests to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @return the range of matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> filterFindByR_G_P_L(long[] resourcePrimKeies,
		long groupId, long[] parentResourcePrimKeies, int[] latests, int start,
		int end) throws SystemException {
		return filterFindByR_G_P_L(resourcePrimKeies, groupId,
			parentResourcePrimKeies, latests, start, end, null);
	}

	/**
	 * Filters by the user's permissions and finds an ordered range of all the articles where resourcePrimKey = any &#63; and groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKeies the parent resource prim keies to search with
	 * @param latests the latests to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> filterFindByR_G_P_L(long[] resourcePrimKeies,
		long groupId, long[] parentResourcePrimKeies, int[] latests, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByR_G_P_L(resourcePrimKeies, groupId,
				parentResourcePrimKeies, latests, start, end, orderByComparator);
		}

		StringBundler query = new StringBundler();

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean conjunctionable = false;

		if ((resourcePrimKeies == null) || (resourcePrimKeies.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < resourcePrimKeies.length; i++) {
				query.append(_FINDER_COLUMN_R_G_P_L_RESOURCEPRIMKEY_5);

				if ((i + 1) < resourcePrimKeies.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			conjunctionable = true;
		}

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_R_G_P_L_GROUPID_5);

		conjunctionable = true;

		if ((parentResourcePrimKeies == null) ||
				(parentResourcePrimKeies.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < parentResourcePrimKeies.length; i++) {
				query.append(_FINDER_COLUMN_R_G_P_L_PARENTRESOURCEPRIMKEY_5);

				if ((i + 1) < parentResourcePrimKeies.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			conjunctionable = true;
		}

		if ((latests == null) || (latests.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < latests.length; i++) {
				query.append(_FINDER_COLUMN_R_G_P_L_LATEST_5);

				if ((i + 1) < latests.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			conjunctionable = true;
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Article.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			if (resourcePrimKeies != null) {
				qPos.add(resourcePrimKeies);
			}

			qPos.add(groupId);

			if (parentResourcePrimKeies != null) {
				qPos.add(parentResourcePrimKeies);
			}

			if (latests != null) {
				qPos.add(latests);
			}

			return (List<Article>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Finds all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @return the matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByG_P_L_S(long groupId,
		long parentResourcePrimKey, int latest, int status)
		throws SystemException {
		return findByG_P_L_S(groupId, parentResourcePrimKey, latest, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @return the range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByG_P_L_S(long groupId,
		long parentResourcePrimKey, int latest, int status, int start, int end)
		throws SystemException {
		return findByG_P_L_S(groupId, parentResourcePrimKey, latest, status,
			start, end, null);
	}

	/**
	 * Finds an ordered range of all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByG_P_L_S(long groupId,
		long parentResourcePrimKey, int latest, int status, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, parentResourcePrimKey, latest, status,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Article> list = (List<Article>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_G_P_L_S,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_ARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_P_L_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_L_S_PARENTRESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_G_P_L_S_LATEST_2);

			query.append(_FINDER_COLUMN_G_P_L_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentResourcePrimKey);

				qPos.add(latest);

				qPos.add(status);

				list = (List<Article>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_G_P_L_S,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_G_P_L_S,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article findByG_P_L_S_First(long groupId,
		long parentResourcePrimKey, int latest, int status,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		List<Article> list = findByG_P_L_S(groupId, parentResourcePrimKey,
				latest, status, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", parentResourcePrimKey=");
			msg.append(parentResourcePrimKey);

			msg.append(", latest=");
			msg.append(latest);

			msg.append(", status=");
			msg.append(status);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article findByG_P_L_S_Last(long groupId, long parentResourcePrimKey,
		int latest, int status, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		int count = countByG_P_L_S(groupId, parentResourcePrimKey, latest,
				status);

		List<Article> list = findByG_P_L_S(groupId, parentResourcePrimKey,
				latest, status, count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", parentResourcePrimKey=");
			msg.append(parentResourcePrimKey);

			msg.append(", latest=");
			msg.append(latest);

			msg.append(", status=");
			msg.append(status);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the articles before and after the current article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param articleId the primary key of the current article
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article[] findByG_P_L_S_PrevAndNext(long articleId, long groupId,
		long parentResourcePrimKey, int latest, int status,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		Article article = findByPrimaryKey(articleId);

		Session session = null;

		try {
			session = openSession();

			Article[] array = new ArticleImpl[3];

			array[0] = getByG_P_L_S_PrevAndNext(session, article, groupId,
					parentResourcePrimKey, latest, status, orderByComparator,
					true);

			array[1] = article;

			array[2] = getByG_P_L_S_PrevAndNext(session, article, groupId,
					parentResourcePrimKey, latest, status, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Article getByG_P_L_S_PrevAndNext(Session session,
		Article article, long groupId, long parentResourcePrimKey, int latest,
		int status, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_P_L_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_L_S_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_G_P_L_S_LATEST_2);

		query.append(_FINDER_COLUMN_G_P_L_S_STATUS_2);

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
			query.append(ArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(parentResourcePrimKey);

		qPos.add(latest);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(article);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Article> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKeies the parent resource prim keies to search with
	 * @param latests the latests to search with
	 * @param status the status to search with
	 * @return the matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByG_P_L_S(long groupId,
		long[] parentResourcePrimKeies, int[] latests, int status)
		throws SystemException {
		return findByG_P_L_S(groupId, parentResourcePrimKeies, latests, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKeies the parent resource prim keies to search with
	 * @param latests the latests to search with
	 * @param status the status to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @return the range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByG_P_L_S(long groupId,
		long[] parentResourcePrimKeies, int[] latests, int status, int start,
		int end) throws SystemException {
		return findByG_P_L_S(groupId, parentResourcePrimKeies, latests, status,
			start, end, null);
	}

	/**
	 * Finds an ordered range of all the articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKeies the parent resource prim keies to search with
	 * @param latests the latests to search with
	 * @param status the status to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByG_P_L_S(long groupId,
		long[] parentResourcePrimKeies, int[] latests, int status, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, StringUtil.merge(parentResourcePrimKeies),
				StringUtil.merge(latests), status,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Article> list = (List<Article>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_G_P_L_S,
				finderArgs, this);

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_ARTICLE_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_L_S_GROUPID_5);

			conjunctionable = true;

			if ((parentResourcePrimKeies == null) ||
					(parentResourcePrimKeies.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < parentResourcePrimKeies.length; i++) {
					query.append(_FINDER_COLUMN_G_P_L_S_PARENTRESOURCEPRIMKEY_5);

					if ((i + 1) < parentResourcePrimKeies.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if ((latests == null) || (latests.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < latests.length; i++) {
					query.append(_FINDER_COLUMN_G_P_L_S_LATEST_5);

					if ((i + 1) < latests.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_L_S_STATUS_5);

			conjunctionable = true;

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (parentResourcePrimKeies != null) {
					qPos.add(parentResourcePrimKeies);
				}

				if (latests != null) {
					qPos.add(latests);
				}

				qPos.add(status);

				list = (List<Article>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_G_P_L_S,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_G_P_L_S,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Filters by the user's permissions and finds all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @return the matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> filterFindByG_P_L_S(long groupId,
		long parentResourcePrimKey, int latest, int status)
		throws SystemException {
		return filterFindByG_P_L_S(groupId, parentResourcePrimKey, latest,
			status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Filters by the user's permissions and finds a range of all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @return the range of matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> filterFindByG_P_L_S(long groupId,
		long parentResourcePrimKey, int latest, int status, int start, int end)
		throws SystemException {
		return filterFindByG_P_L_S(groupId, parentResourcePrimKey, latest,
			status, start, end, null);
	}

	/**
	 * Filters by the user's permissions and finds an ordered range of all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> filterFindByG_P_L_S(long groupId,
		long parentResourcePrimKey, int latest, int status, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_L_S(groupId, parentResourcePrimKey, latest,
				status, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_P_L_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_L_S_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_G_P_L_S_LATEST_2);

		query.append(_FINDER_COLUMN_G_P_L_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Article.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentResourcePrimKey);

			qPos.add(latest);

			qPos.add(status);

			return (List<Article>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Filters by the user's permissions and finds all the articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKeies the parent resource prim keies to search with
	 * @param latests the latests to search with
	 * @param status the status to search with
	 * @return the matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> filterFindByG_P_L_S(long groupId,
		long[] parentResourcePrimKeies, int[] latests, int status)
		throws SystemException {
		return filterFindByG_P_L_S(groupId, parentResourcePrimKeies, latests,
			status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Filters by the user's permissions and finds a range of all the articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKeies the parent resource prim keies to search with
	 * @param latests the latests to search with
	 * @param status the status to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @return the range of matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> filterFindByG_P_L_S(long groupId,
		long[] parentResourcePrimKeies, int[] latests, int status, int start,
		int end) throws SystemException {
		return filterFindByG_P_L_S(groupId, parentResourcePrimKeies, latests,
			status, start, end, null);
	}

	/**
	 * Filters by the user's permissions and finds an ordered range of all the articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKeies the parent resource prim keies to search with
	 * @param latests the latests to search with
	 * @param status the status to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> filterFindByG_P_L_S(long groupId,
		long[] parentResourcePrimKeies, int[] latests, int status, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_L_S(groupId, parentResourcePrimKeies, latests,
				status, start, end, orderByComparator);
		}

		StringBundler query = new StringBundler();

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean conjunctionable = false;

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_L_S_GROUPID_5);

		conjunctionable = true;

		if ((parentResourcePrimKeies == null) ||
				(parentResourcePrimKeies.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < parentResourcePrimKeies.length; i++) {
				query.append(_FINDER_COLUMN_G_P_L_S_PARENTRESOURCEPRIMKEY_5);

				if ((i + 1) < parentResourcePrimKeies.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			conjunctionable = true;
		}

		if ((latests == null) || (latests.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < latests.length; i++) {
				query.append(_FINDER_COLUMN_G_P_L_S_LATEST_5);

				if ((i + 1) < latests.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			conjunctionable = true;
		}

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_L_S_STATUS_5);

		conjunctionable = true;

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Article.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (parentResourcePrimKeies != null) {
				qPos.add(parentResourcePrimKeies);
			}

			if (latests != null) {
				qPos.add(latests);
			}

			qPos.add(status);

			return (List<Article>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Finds all the articles where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @return the matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByR_G_P_L_S(long resourcePrimKey, long groupId,
		long parentResourcePrimKey, int latest, int status)
		throws SystemException {
		return findByR_G_P_L_S(resourcePrimKey, groupId, parentResourcePrimKey,
			latest, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the articles where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @return the range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByR_G_P_L_S(long resourcePrimKey, long groupId,
		long parentResourcePrimKey, int latest, int status, int start, int end)
		throws SystemException {
		return findByR_G_P_L_S(resourcePrimKey, groupId, parentResourcePrimKey,
			latest, status, start, end, null);
	}

	/**
	 * Finds an ordered range of all the articles where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByR_G_P_L_S(long resourcePrimKey, long groupId,
		long parentResourcePrimKey, int latest, int status, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				resourcePrimKey, groupId, parentResourcePrimKey, latest, status,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Article> list = (List<Article>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_R_G_P_L_S,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(7 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(7);
			}

			query.append(_SQL_SELECT_ARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_G_P_L_S_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_G_P_L_S_GROUPID_2);

			query.append(_FINDER_COLUMN_R_G_P_L_S_PARENTRESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_G_P_L_S_LATEST_2);

			query.append(_FINDER_COLUMN_R_G_P_L_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(groupId);

				qPos.add(parentResourcePrimKey);

				qPos.add(latest);

				qPos.add(status);

				list = (List<Article>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_R_G_P_L_S,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_R_G_P_L_S,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article findByR_G_P_L_S_First(long resourcePrimKey, long groupId,
		long parentResourcePrimKey, int latest, int status,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		List<Article> list = findByR_G_P_L_S(resourcePrimKey, groupId,
				parentResourcePrimKey, latest, status, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(12);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("resourcePrimKey=");
			msg.append(resourcePrimKey);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(", parentResourcePrimKey=");
			msg.append(parentResourcePrimKey);

			msg.append(", latest=");
			msg.append(latest);

			msg.append(", status=");
			msg.append(status);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article findByR_G_P_L_S_Last(long resourcePrimKey, long groupId,
		long parentResourcePrimKey, int latest, int status,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		int count = countByR_G_P_L_S(resourcePrimKey, groupId,
				parentResourcePrimKey, latest, status);

		List<Article> list = findByR_G_P_L_S(resourcePrimKey, groupId,
				parentResourcePrimKey, latest, status, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(12);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("resourcePrimKey=");
			msg.append(resourcePrimKey);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(", parentResourcePrimKey=");
			msg.append(parentResourcePrimKey);

			msg.append(", latest=");
			msg.append(latest);

			msg.append(", status=");
			msg.append(status);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the articles before and after the current article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param articleId the primary key of the current article
	 * @param resourcePrimKey the resource prim key to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Article[] findByR_G_P_L_S_PrevAndNext(long articleId,
		long resourcePrimKey, long groupId, long parentResourcePrimKey,
		int latest, int status, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		Article article = findByPrimaryKey(articleId);

		Session session = null;

		try {
			session = openSession();

			Article[] array = new ArticleImpl[3];

			array[0] = getByR_G_P_L_S_PrevAndNext(session, article,
					resourcePrimKey, groupId, parentResourcePrimKey, latest,
					status, orderByComparator, true);

			array[1] = article;

			array[2] = getByR_G_P_L_S_PrevAndNext(session, article,
					resourcePrimKey, groupId, parentResourcePrimKey, latest,
					status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Article getByR_G_P_L_S_PrevAndNext(Session session,
		Article article, long resourcePrimKey, long groupId,
		long parentResourcePrimKey, int latest, int status,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ARTICLE_WHERE);

		query.append(_FINDER_COLUMN_R_G_P_L_S_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_P_L_S_GROUPID_2);

		query.append(_FINDER_COLUMN_R_G_P_L_S_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_P_L_S_LATEST_2);

		query.append(_FINDER_COLUMN_R_G_P_L_S_STATUS_2);

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
			query.append(ArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resourcePrimKey);

		qPos.add(groupId);

		qPos.add(parentResourcePrimKey);

		qPos.add(latest);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(article);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Article> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the articles where resourcePrimKey = any &#63; and groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKeies the parent resource prim keies to search with
	 * @param latests the latests to search with
	 * @param status the status to search with
	 * @return the matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByR_G_P_L_S(long[] resourcePrimKeies,
		long groupId, long[] parentResourcePrimKeies, int[] latests, int status)
		throws SystemException {
		return findByR_G_P_L_S(resourcePrimKeies, groupId,
			parentResourcePrimKeies, latests, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the articles where resourcePrimKey = any &#63; and groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKeies the parent resource prim keies to search with
	 * @param latests the latests to search with
	 * @param status the status to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @return the range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByR_G_P_L_S(long[] resourcePrimKeies,
		long groupId, long[] parentResourcePrimKeies, int[] latests,
		int status, int start, int end) throws SystemException {
		return findByR_G_P_L_S(resourcePrimKeies, groupId,
			parentResourcePrimKeies, latests, status, start, end, null);
	}

	/**
	 * Finds an ordered range of all the articles where resourcePrimKey = any &#63; and groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKeies the parent resource prim keies to search with
	 * @param latests the latests to search with
	 * @param status the status to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findByR_G_P_L_S(long[] resourcePrimKeies,
		long groupId, long[] parentResourcePrimKeies, int[] latests,
		int status, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				StringUtil.merge(resourcePrimKeies), groupId,
				StringUtil.merge(parentResourcePrimKeies),
				StringUtil.merge(latests), status,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Article> list = (List<Article>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_R_G_P_L_S,
				finderArgs, this);

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_ARTICLE_WHERE);

			boolean conjunctionable = false;

			if ((resourcePrimKeies == null) || (resourcePrimKeies.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < resourcePrimKeies.length; i++) {
					query.append(_FINDER_COLUMN_R_G_P_L_S_RESOURCEPRIMKEY_5);

					if ((i + 1) < resourcePrimKeies.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_R_G_P_L_S_GROUPID_5);

			conjunctionable = true;

			if ((parentResourcePrimKeies == null) ||
					(parentResourcePrimKeies.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < parentResourcePrimKeies.length; i++) {
					query.append(_FINDER_COLUMN_R_G_P_L_S_PARENTRESOURCEPRIMKEY_5);

					if ((i + 1) < parentResourcePrimKeies.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if ((latests == null) || (latests.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < latests.length; i++) {
					query.append(_FINDER_COLUMN_R_G_P_L_S_LATEST_5);

					if ((i + 1) < latests.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_R_G_P_L_S_STATUS_5);

			conjunctionable = true;

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (resourcePrimKeies != null) {
					qPos.add(resourcePrimKeies);
				}

				qPos.add(groupId);

				if (parentResourcePrimKeies != null) {
					qPos.add(parentResourcePrimKeies);
				}

				if (latests != null) {
					qPos.add(latests);
				}

				qPos.add(status);

				list = (List<Article>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_R_G_P_L_S,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_R_G_P_L_S,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Filters by the user's permissions and finds all the articles where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @return the matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> filterFindByR_G_P_L_S(long resourcePrimKey,
		long groupId, long parentResourcePrimKey, int latest, int status)
		throws SystemException {
		return filterFindByR_G_P_L_S(resourcePrimKey, groupId,
			parentResourcePrimKey, latest, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Filters by the user's permissions and finds a range of all the articles where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @return the range of matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> filterFindByR_G_P_L_S(long resourcePrimKey,
		long groupId, long parentResourcePrimKey, int latest, int status,
		int start, int end) throws SystemException {
		return filterFindByR_G_P_L_S(resourcePrimKey, groupId,
			parentResourcePrimKey, latest, status, start, end, null);
	}

	/**
	 * Filters by the user's permissions and finds an ordered range of all the articles where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> filterFindByR_G_P_L_S(long resourcePrimKey,
		long groupId, long parentResourcePrimKey, int latest, int status,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByR_G_P_L_S(resourcePrimKey, groupId,
				parentResourcePrimKey, latest, status, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(7);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_R_G_P_L_S_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_P_L_S_GROUPID_2);

		query.append(_FINDER_COLUMN_R_G_P_L_S_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_P_L_S_LATEST_2);

		query.append(_FINDER_COLUMN_R_G_P_L_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Article.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(resourcePrimKey);

			qPos.add(groupId);

			qPos.add(parentResourcePrimKey);

			qPos.add(latest);

			qPos.add(status);

			return (List<Article>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Filters by the user's permissions and finds all the articles where resourcePrimKey = any &#63; and groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKeies the parent resource prim keies to search with
	 * @param latests the latests to search with
	 * @param status the status to search with
	 * @return the matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> filterFindByR_G_P_L_S(long[] resourcePrimKeies,
		long groupId, long[] parentResourcePrimKeies, int[] latests, int status)
		throws SystemException {
		return filterFindByR_G_P_L_S(resourcePrimKeies, groupId,
			parentResourcePrimKeies, latests, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Filters by the user's permissions and finds a range of all the articles where resourcePrimKey = any &#63; and groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKeies the parent resource prim keies to search with
	 * @param latests the latests to search with
	 * @param status the status to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @return the range of matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> filterFindByR_G_P_L_S(long[] resourcePrimKeies,
		long groupId, long[] parentResourcePrimKeies, int[] latests,
		int status, int start, int end) throws SystemException {
		return filterFindByR_G_P_L_S(resourcePrimKeies, groupId,
			parentResourcePrimKeies, latests, status, start, end, null);
	}

	/**
	 * Filters by the user's permissions and finds an ordered range of all the articles where resourcePrimKey = any &#63; and groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKeies the parent resource prim keies to search with
	 * @param latests the latests to search with
	 * @param status the status to search with
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> filterFindByR_G_P_L_S(long[] resourcePrimKeies,
		long groupId, long[] parentResourcePrimKeies, int[] latests,
		int status, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByR_G_P_L_S(resourcePrimKeies, groupId,
				parentResourcePrimKeies, latests, status, start, end,
				orderByComparator);
		}

		StringBundler query = new StringBundler();

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean conjunctionable = false;

		if ((resourcePrimKeies == null) || (resourcePrimKeies.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < resourcePrimKeies.length; i++) {
				query.append(_FINDER_COLUMN_R_G_P_L_S_RESOURCEPRIMKEY_5);

				if ((i + 1) < resourcePrimKeies.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			conjunctionable = true;
		}

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_R_G_P_L_S_GROUPID_5);

		conjunctionable = true;

		if ((parentResourcePrimKeies == null) ||
				(parentResourcePrimKeies.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < parentResourcePrimKeies.length; i++) {
				query.append(_FINDER_COLUMN_R_G_P_L_S_PARENTRESOURCEPRIMKEY_5);

				if ((i + 1) < parentResourcePrimKeies.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			conjunctionable = true;
		}

		if ((latests == null) || (latests.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < latests.length; i++) {
				query.append(_FINDER_COLUMN_R_G_P_L_S_LATEST_5);

				if ((i + 1) < latests.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			conjunctionable = true;
		}

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_R_G_P_L_S_STATUS_5);

		conjunctionable = true;

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(ArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Article.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			if (resourcePrimKeies != null) {
				qPos.add(resourcePrimKeies);
			}

			qPos.add(groupId);

			if (parentResourcePrimKeies != null) {
				qPos.add(parentResourcePrimKeies);
			}

			if (latests != null) {
				qPos.add(latests);
			}

			qPos.add(status);

			return (List<Article>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Finds all the articles.
	 *
	 * @return the articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the articles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @return the range of articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the articles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of articles to return
	 * @param end the upper bound of the range of articles to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<Article> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Article> list = (List<Article>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ARTICLE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ARTICLE.concat(ArticleModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<Article>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Article>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the articles where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (Article article : findByUuid(uuid)) {
			remove(article);
		}
	}

	/**
	 * Removes the article where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid to search with
	 * @param groupId the group ID to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUUID_G(String uuid, long groupId)
		throws NoSuchArticleException, SystemException {
		Article article = findByUUID_G(uuid, groupId);

		remove(article);
	}

	/**
	 * Removes all the articles where resourcePrimKey = &#63; from the database.
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByResourcePrimKey(long resourcePrimKey)
		throws SystemException {
		for (Article article : findByResourcePrimKey(resourcePrimKey)) {
			remove(article);
		}
	}

	/**
	 * Removes the article where resourcePrimKey = &#63; and version = &#63; from the database.
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param version the version to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByR_V(long resourcePrimKey, int version)
		throws NoSuchArticleException, SystemException {
		Article article = findByR_V(resourcePrimKey, version);

		remove(article);
	}

	/**
	 * Removes all the articles where resourcePrimKey = &#63; and latest = &#63; from the database.
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param latest the latest to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByR_L(long resourcePrimKey, int latest)
		throws SystemException {
		for (Article article : findByR_L(resourcePrimKey, latest)) {
			remove(article);
		}
	}

	/**
	 * Removes all the articles where resourcePrimKey = &#63; and status = &#63; from the database.
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param status the status to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByR_S(long resourcePrimKey, int status)
		throws SystemException {
		for (Article article : findByR_S(resourcePrimKey, status)) {
			remove(article);
		}
	}

	/**
	 * Removes all the articles where groupId = &#63; and latest = &#63; from the database.
	 *
	 * @param groupId the group ID to search with
	 * @param latest the latest to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_L(long groupId, int latest) throws SystemException {
		for (Article article : findByG_L(groupId, latest)) {
			remove(article);
		}
	}

	/**
	 * Removes all the articles where companyId = &#63; and latest = &#63; from the database.
	 *
	 * @param companyId the company ID to search with
	 * @param latest the latest to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_L(long companyId, int latest)
		throws SystemException {
		for (Article article : findByC_L(companyId, latest)) {
			remove(article);
		}
	}

	/**
	 * Removes all the articles where resourcePrimKey = &#63; and latest = &#63; and status = &#63; from the database.
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByR_L_S(long resourcePrimKey, int latest, int status)
		throws SystemException {
		for (Article article : findByR_L_S(resourcePrimKey, latest, status)) {
			remove(article);
		}
	}

	/**
	 * Removes all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; from the database.
	 *
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_P_L(long groupId, long parentResourcePrimKey,
		int latest) throws SystemException {
		for (Article article : findByG_P_L(groupId, parentResourcePrimKey,
				latest)) {
			remove(article);
		}
	}

	/**
	 * Removes all the articles where groupId = &#63; and latest = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_L_S(long groupId, int latest, int status)
		throws SystemException {
		for (Article article : findByG_L_S(groupId, latest, status)) {
			remove(article);
		}
	}

	/**
	 * Removes all the articles where companyId = &#63; and latest = &#63; and status = &#63; from the database.
	 *
	 * @param companyId the company ID to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_L_S(long companyId, int latest, int status)
		throws SystemException {
		for (Article article : findByC_L_S(companyId, latest, status)) {
			remove(article);
		}
	}

	/**
	 * Removes all the articles where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; from the database.
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByR_G_P_L(long resourcePrimKey, long groupId,
		long parentResourcePrimKey, int latest) throws SystemException {
		for (Article article : findByR_G_P_L(resourcePrimKey, groupId,
				parentResourcePrimKey, latest)) {
			remove(article);
		}
	}

	/**
	 * Removes all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_P_L_S(long groupId, long parentResourcePrimKey,
		int latest, int status) throws SystemException {
		for (Article article : findByG_P_L_S(groupId, parentResourcePrimKey,
				latest, status)) {
			remove(article);
		}
	}

	/**
	 * Removes all the articles where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63; from the database.
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByR_G_P_L_S(long resourcePrimKey, long groupId,
		long parentResourcePrimKey, int latest, int status)
		throws SystemException {
		for (Article article : findByR_G_P_L_S(resourcePrimKey, groupId,
				parentResourcePrimKey, latest, status)) {
			remove(article);
		}
	}

	/**
	 * Removes all the articles from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (Article article : findAll()) {
			remove(article);
		}
	}

	/**
	 * Counts all the articles where uuid = &#63;.
	 *
	 * @param uuid the uuid to search with
	 * @return the number of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ARTICLE_WHERE);

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
	 * Counts all the articles where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid to search with
	 * @param groupId the group ID to search with
	 * @return the number of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUUID_G(String uuid, long groupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID_G,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ARTICLE_WHERE);

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
	 * Counts all the articles where resourcePrimKey = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @return the number of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByResourcePrimKey(long resourcePrimKey)
		throws SystemException {
		Object[] finderArgs = new Object[] { resourcePrimKey };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_RESOURCEPRIMKEY,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ARTICLE_WHERE);

			query.append(_FINDER_COLUMN_RESOURCEPRIMKEY_RESOURCEPRIMKEY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_RESOURCEPRIMKEY,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the articles where resourcePrimKey = &#63; and version = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param version the version to search with
	 * @return the number of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByR_V(long resourcePrimKey, int version)
		throws SystemException {
		Object[] finderArgs = new Object[] { resourcePrimKey, version };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_R_V,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_V_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_V_VERSION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(version);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_R_V, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the articles where resourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param latest the latest to search with
	 * @return the number of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByR_L(long resourcePrimKey, int latest)
		throws SystemException {
		Object[] finderArgs = new Object[] { resourcePrimKey, latest };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_R_L,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_L_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_L_LATEST_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(latest);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_R_L, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the articles where resourcePrimKey = any &#63; and latest = any &#63;.
	 *
	 * @param resourcePrimKeies the resource prim keies to search with
	 * @param latests the latests to search with
	 * @return the number of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByR_L(long[] resourcePrimKeies, int[] latests)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				StringUtil.merge(resourcePrimKeies), StringUtil.merge(latests)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_R_L,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_ARTICLE_WHERE);

			boolean conjunctionable = false;

			if ((resourcePrimKeies == null) || (resourcePrimKeies.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < resourcePrimKeies.length; i++) {
					query.append(_FINDER_COLUMN_R_L_RESOURCEPRIMKEY_5);

					if ((i + 1) < resourcePrimKeies.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if ((latests == null) || (latests.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < latests.length; i++) {
					query.append(_FINDER_COLUMN_R_L_LATEST_5);

					if ((i + 1) < latests.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (resourcePrimKeies != null) {
					qPos.add(resourcePrimKeies);
				}

				if (latests != null) {
					qPos.add(latests);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_R_L, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the articles where resourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param status the status to search with
	 * @return the number of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByR_S(long resourcePrimKey, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] { resourcePrimKey, status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_R_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_S_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

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
	 * Counts all the articles where groupId = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @param latest the latest to search with
	 * @return the number of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_L(long groupId, int latest) throws SystemException {
		Object[] finderArgs = new Object[] { groupId, latest };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_L,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_L_GROUPID_2);

			query.append(_FINDER_COLUMN_G_L_LATEST_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(latest);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_L, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the articles where groupId = &#63; and latest = any &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @param latests the latests to search with
	 * @return the number of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_L(long groupId, int[] latests)
		throws SystemException {
		Object[] finderArgs = new Object[] { groupId, StringUtil.merge(latests) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_L,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_ARTICLE_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_L_GROUPID_5);

			conjunctionable = true;

			if ((latests == null) || (latests.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < latests.length; i++) {
					query.append(_FINDER_COLUMN_G_L_LATEST_5);

					if ((i + 1) < latests.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (latests != null) {
					qPos.add(latests);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_L, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Filters by the user's permissions and counts all the articles where groupId = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @param latest the latest to search with
	 * @return the number of matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByG_L(long groupId, int latest)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_L(groupId, latest);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_ARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_L_GROUPID_2);

		query.append(_FINDER_COLUMN_G_L_LATEST_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Article.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(latest);

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
	 * Filters by the user's permissions and counts all the articles where groupId = &#63; and latest = any &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @param latests the latests to search with
	 * @return the number of matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByG_L(long groupId, int[] latests)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_L(groupId, latests);
		}

		StringBundler query = new StringBundler();

		query.append(_FILTER_SQL_COUNT_ARTICLE_WHERE);

		boolean conjunctionable = false;

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_L_GROUPID_5);

		conjunctionable = true;

		if ((latests == null) || (latests.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < latests.length; i++) {
				query.append(_FINDER_COLUMN_G_L_LATEST_5);

				if ((i + 1) < latests.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			conjunctionable = true;
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Article.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (latests != null) {
				qPos.add(latests);
			}

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
	 * Counts all the articles where companyId = &#63; and latest = &#63;.
	 *
	 * @param companyId the company ID to search with
	 * @param latest the latest to search with
	 * @return the number of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_L(long companyId, int latest) throws SystemException {
		Object[] finderArgs = new Object[] { companyId, latest };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_L,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ARTICLE_WHERE);

			query.append(_FINDER_COLUMN_C_L_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_L_LATEST_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(latest);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_L, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the articles where companyId = &#63; and latest = any &#63;.
	 *
	 * @param companyId the company ID to search with
	 * @param latests the latests to search with
	 * @return the number of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_L(long companyId, int[] latests)
		throws SystemException {
		Object[] finderArgs = new Object[] { companyId, StringUtil.merge(latests) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_L,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_ARTICLE_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_C_L_COMPANYID_5);

			conjunctionable = true;

			if ((latests == null) || (latests.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < latests.length; i++) {
					query.append(_FINDER_COLUMN_C_L_LATEST_5);

					if ((i + 1) < latests.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (latests != null) {
					qPos.add(latests);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_L, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the articles where resourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @return the number of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByR_L_S(long resourcePrimKey, int latest, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] { resourcePrimKey, latest, status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_R_L_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_L_S_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_L_S_LATEST_2);

			query.append(_FINDER_COLUMN_R_L_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(latest);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_R_L_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the articles where resourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	 *
	 * @param resourcePrimKeies the resource prim keies to search with
	 * @param latests the latests to search with
	 * @param status the status to search with
	 * @return the number of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByR_L_S(long[] resourcePrimKeies, int[] latests, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				StringUtil.merge(resourcePrimKeies), StringUtil.merge(latests),
				status
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_R_L_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_ARTICLE_WHERE);

			boolean conjunctionable = false;

			if ((resourcePrimKeies == null) || (resourcePrimKeies.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < resourcePrimKeies.length; i++) {
					query.append(_FINDER_COLUMN_R_L_S_RESOURCEPRIMKEY_5);

					if ((i + 1) < resourcePrimKeies.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if ((latests == null) || (latests.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < latests.length; i++) {
					query.append(_FINDER_COLUMN_R_L_S_LATEST_5);

					if ((i + 1) < latests.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_R_L_S_STATUS_5);

			conjunctionable = true;

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (resourcePrimKeies != null) {
					qPos.add(resourcePrimKeies);
				}

				if (latests != null) {
					qPos.add(latests);
				}

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_R_L_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @return the number of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_P_L(long groupId, long parentResourcePrimKey, int latest)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, parentResourcePrimKey, latest
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_P_L,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_P_L_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_L_PARENTRESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_G_P_L_LATEST_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentResourcePrimKey);

				qPos.add(latest);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_P_L,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKeies the parent resource prim keies to search with
	 * @param latests the latests to search with
	 * @return the number of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_P_L(long groupId, long[] parentResourcePrimKeies,
		int[] latests) throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, StringUtil.merge(parentResourcePrimKeies),
				StringUtil.merge(latests)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_P_L,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_ARTICLE_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_L_GROUPID_5);

			conjunctionable = true;

			if ((parentResourcePrimKeies == null) ||
					(parentResourcePrimKeies.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < parentResourcePrimKeies.length; i++) {
					query.append(_FINDER_COLUMN_G_P_L_PARENTRESOURCEPRIMKEY_5);

					if ((i + 1) < parentResourcePrimKeies.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if ((latests == null) || (latests.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < latests.length; i++) {
					query.append(_FINDER_COLUMN_G_P_L_LATEST_5);

					if ((i + 1) < latests.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (parentResourcePrimKeies != null) {
					qPos.add(parentResourcePrimKeies);
				}

				if (latests != null) {
					qPos.add(latests);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_P_L,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Filters by the user's permissions and counts all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @return the number of matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByG_P_L(long groupId, long parentResourcePrimKey,
		int latest) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_P_L(groupId, parentResourcePrimKey, latest);
		}

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_ARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_P_L_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_L_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_G_P_L_LATEST_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Article.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentResourcePrimKey);

			qPos.add(latest);

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
	 * Filters by the user's permissions and counts all the articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKeies the parent resource prim keies to search with
	 * @param latests the latests to search with
	 * @return the number of matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByG_P_L(long groupId, long[] parentResourcePrimKeies,
		int[] latests) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_P_L(groupId, parentResourcePrimKeies, latests);
		}

		StringBundler query = new StringBundler();

		query.append(_FILTER_SQL_COUNT_ARTICLE_WHERE);

		boolean conjunctionable = false;

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_L_GROUPID_5);

		conjunctionable = true;

		if ((parentResourcePrimKeies == null) ||
				(parentResourcePrimKeies.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < parentResourcePrimKeies.length; i++) {
				query.append(_FINDER_COLUMN_G_P_L_PARENTRESOURCEPRIMKEY_5);

				if ((i + 1) < parentResourcePrimKeies.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			conjunctionable = true;
		}

		if ((latests == null) || (latests.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < latests.length; i++) {
				query.append(_FINDER_COLUMN_G_P_L_LATEST_5);

				if ((i + 1) < latests.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			conjunctionable = true;
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Article.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (parentResourcePrimKeies != null) {
				qPos.add(parentResourcePrimKeies);
			}

			if (latests != null) {
				qPos.add(latests);
			}

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
	 * Counts all the articles where groupId = &#63; and latest = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @return the number of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_L_S(long groupId, int latest, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] { groupId, latest, status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_L_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_L_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_L_S_LATEST_2);

			query.append(_FINDER_COLUMN_G_L_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(latest);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_L_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the articles where groupId = &#63; and latest = any &#63; and status = &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @param latests the latests to search with
	 * @param status the status to search with
	 * @return the number of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_L_S(long groupId, int[] latests, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, StringUtil.merge(latests), status
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_L_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_ARTICLE_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_L_S_GROUPID_5);

			conjunctionable = true;

			if ((latests == null) || (latests.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < latests.length; i++) {
					query.append(_FINDER_COLUMN_G_L_S_LATEST_5);

					if ((i + 1) < latests.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_L_S_STATUS_5);

			conjunctionable = true;

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (latests != null) {
					qPos.add(latests);
				}

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_L_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Filters by the user's permissions and counts all the articles where groupId = &#63; and latest = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @return the number of matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByG_L_S(long groupId, int latest, int status)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_L_S(groupId, latest, status);
		}

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_ARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_L_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_L_S_LATEST_2);

		query.append(_FINDER_COLUMN_G_L_S_STATUS_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Article.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(latest);

			qPos.add(status);

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
	 * Filters by the user's permissions and counts all the articles where groupId = &#63; and latest = any &#63; and status = &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @param latests the latests to search with
	 * @param status the status to search with
	 * @return the number of matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByG_L_S(long groupId, int[] latests, int status)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_L_S(groupId, latests, status);
		}

		StringBundler query = new StringBundler();

		query.append(_FILTER_SQL_COUNT_ARTICLE_WHERE);

		boolean conjunctionable = false;

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_L_S_GROUPID_5);

		conjunctionable = true;

		if ((latests == null) || (latests.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < latests.length; i++) {
				query.append(_FINDER_COLUMN_G_L_S_LATEST_5);

				if ((i + 1) < latests.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			conjunctionable = true;
		}

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_L_S_STATUS_5);

		conjunctionable = true;

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Article.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (latests != null) {
				qPos.add(latests);
			}

			qPos.add(status);

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
	 * Counts all the articles where companyId = &#63; and latest = &#63; and status = &#63;.
	 *
	 * @param companyId the company ID to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @return the number of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_L_S(long companyId, int latest, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] { companyId, latest, status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_L_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ARTICLE_WHERE);

			query.append(_FINDER_COLUMN_C_L_S_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_L_S_LATEST_2);

			query.append(_FINDER_COLUMN_C_L_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(latest);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_L_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the articles where companyId = &#63; and latest = any &#63; and status = &#63;.
	 *
	 * @param companyId the company ID to search with
	 * @param latests the latests to search with
	 * @param status the status to search with
	 * @return the number of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_L_S(long companyId, int[] latests, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId, StringUtil.merge(latests), status
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_L_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_ARTICLE_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_C_L_S_COMPANYID_5);

			conjunctionable = true;

			if ((latests == null) || (latests.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < latests.length; i++) {
					query.append(_FINDER_COLUMN_C_L_S_LATEST_5);

					if ((i + 1) < latests.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_C_L_S_STATUS_5);

			conjunctionable = true;

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (latests != null) {
					qPos.add(latests);
				}

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_L_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the articles where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @return the number of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByR_G_P_L(long resourcePrimKey, long groupId,
		long parentResourcePrimKey, int latest) throws SystemException {
		Object[] finderArgs = new Object[] {
				resourcePrimKey, groupId, parentResourcePrimKey, latest
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_R_G_P_L,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_ARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_G_P_L_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_G_P_L_GROUPID_2);

			query.append(_FINDER_COLUMN_R_G_P_L_PARENTRESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_G_P_L_LATEST_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(groupId);

				qPos.add(parentResourcePrimKey);

				qPos.add(latest);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_R_G_P_L,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the articles where resourcePrimKey = any &#63; and groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63;.
	 *
	 * @param resourcePrimKeies the resource prim keies to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKeies the parent resource prim keies to search with
	 * @param latests the latests to search with
	 * @return the number of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByR_G_P_L(long[] resourcePrimKeies, long groupId,
		long[] parentResourcePrimKeies, int[] latests)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				StringUtil.merge(resourcePrimKeies), groupId,
				StringUtil.merge(parentResourcePrimKeies),
				StringUtil.merge(latests)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_R_G_P_L,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_ARTICLE_WHERE);

			boolean conjunctionable = false;

			if ((resourcePrimKeies == null) || (resourcePrimKeies.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < resourcePrimKeies.length; i++) {
					query.append(_FINDER_COLUMN_R_G_P_L_RESOURCEPRIMKEY_5);

					if ((i + 1) < resourcePrimKeies.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_R_G_P_L_GROUPID_5);

			conjunctionable = true;

			if ((parentResourcePrimKeies == null) ||
					(parentResourcePrimKeies.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < parentResourcePrimKeies.length; i++) {
					query.append(_FINDER_COLUMN_R_G_P_L_PARENTRESOURCEPRIMKEY_5);

					if ((i + 1) < parentResourcePrimKeies.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if ((latests == null) || (latests.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < latests.length; i++) {
					query.append(_FINDER_COLUMN_R_G_P_L_LATEST_5);

					if ((i + 1) < latests.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (resourcePrimKeies != null) {
					qPos.add(resourcePrimKeies);
				}

				qPos.add(groupId);

				if (parentResourcePrimKeies != null) {
					qPos.add(parentResourcePrimKeies);
				}

				if (latests != null) {
					qPos.add(latests);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_R_G_P_L,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Filters by the user's permissions and counts all the articles where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @return the number of matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByR_G_P_L(long resourcePrimKey, long groupId,
		long parentResourcePrimKey, int latest) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByR_G_P_L(resourcePrimKey, groupId,
				parentResourcePrimKey, latest);
		}

		StringBundler query = new StringBundler(5);

		query.append(_FILTER_SQL_COUNT_ARTICLE_WHERE);

		query.append(_FINDER_COLUMN_R_G_P_L_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_P_L_GROUPID_2);

		query.append(_FINDER_COLUMN_R_G_P_L_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_P_L_LATEST_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Article.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(resourcePrimKey);

			qPos.add(groupId);

			qPos.add(parentResourcePrimKey);

			qPos.add(latest);

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
	 * Filters by the user's permissions and counts all the articles where resourcePrimKey = any &#63; and groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63;.
	 *
	 * @param resourcePrimKeies the resource prim keies to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKeies the parent resource prim keies to search with
	 * @param latests the latests to search with
	 * @return the number of matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByR_G_P_L(long[] resourcePrimKeies, long groupId,
		long[] parentResourcePrimKeies, int[] latests)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByR_G_P_L(resourcePrimKeies, groupId,
				parentResourcePrimKeies, latests);
		}

		StringBundler query = new StringBundler();

		query.append(_FILTER_SQL_COUNT_ARTICLE_WHERE);

		boolean conjunctionable = false;

		if ((resourcePrimKeies == null) || (resourcePrimKeies.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < resourcePrimKeies.length; i++) {
				query.append(_FINDER_COLUMN_R_G_P_L_RESOURCEPRIMKEY_5);

				if ((i + 1) < resourcePrimKeies.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			conjunctionable = true;
		}

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_R_G_P_L_GROUPID_5);

		conjunctionable = true;

		if ((parentResourcePrimKeies == null) ||
				(parentResourcePrimKeies.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < parentResourcePrimKeies.length; i++) {
				query.append(_FINDER_COLUMN_R_G_P_L_PARENTRESOURCEPRIMKEY_5);

				if ((i + 1) < parentResourcePrimKeies.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			conjunctionable = true;
		}

		if ((latests == null) || (latests.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < latests.length; i++) {
				query.append(_FINDER_COLUMN_R_G_P_L_LATEST_5);

				if ((i + 1) < latests.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			conjunctionable = true;
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Article.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			if (resourcePrimKeies != null) {
				qPos.add(resourcePrimKeies);
			}

			qPos.add(groupId);

			if (parentResourcePrimKeies != null) {
				qPos.add(parentResourcePrimKeies);
			}

			if (latests != null) {
				qPos.add(latests);
			}

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
	 * Counts all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @return the number of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_P_L_S(long groupId, long parentResourcePrimKey,
		int latest, int status) throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, parentResourcePrimKey, latest, status
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_P_L_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_ARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_P_L_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_L_S_PARENTRESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_G_P_L_S_LATEST_2);

			query.append(_FINDER_COLUMN_G_P_L_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentResourcePrimKey);

				qPos.add(latest);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_P_L_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKeies the parent resource prim keies to search with
	 * @param latests the latests to search with
	 * @param status the status to search with
	 * @return the number of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_P_L_S(long groupId, long[] parentResourcePrimKeies,
		int[] latests, int status) throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, StringUtil.merge(parentResourcePrimKeies),
				StringUtil.merge(latests), status
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_P_L_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_ARTICLE_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_L_S_GROUPID_5);

			conjunctionable = true;

			if ((parentResourcePrimKeies == null) ||
					(parentResourcePrimKeies.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < parentResourcePrimKeies.length; i++) {
					query.append(_FINDER_COLUMN_G_P_L_S_PARENTRESOURCEPRIMKEY_5);

					if ((i + 1) < parentResourcePrimKeies.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if ((latests == null) || (latests.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < latests.length; i++) {
					query.append(_FINDER_COLUMN_G_P_L_S_LATEST_5);

					if ((i + 1) < latests.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_L_S_STATUS_5);

			conjunctionable = true;

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (parentResourcePrimKeies != null) {
					qPos.add(parentResourcePrimKeies);
				}

				if (latests != null) {
					qPos.add(latests);
				}

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_P_L_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Filters by the user's permissions and counts all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @return the number of matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByG_P_L_S(long groupId, long parentResourcePrimKey,
		int latest, int status) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_P_L_S(groupId, parentResourcePrimKey, latest, status);
		}

		StringBundler query = new StringBundler(5);

		query.append(_FILTER_SQL_COUNT_ARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_P_L_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_L_S_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_G_P_L_S_LATEST_2);

		query.append(_FINDER_COLUMN_G_P_L_S_STATUS_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Article.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentResourcePrimKey);

			qPos.add(latest);

			qPos.add(status);

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
	 * Filters by the user's permissions and counts all the articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKeies the parent resource prim keies to search with
	 * @param latests the latests to search with
	 * @param status the status to search with
	 * @return the number of matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByG_P_L_S(long groupId,
		long[] parentResourcePrimKeies, int[] latests, int status)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_P_L_S(groupId, parentResourcePrimKeies, latests,
				status);
		}

		StringBundler query = new StringBundler();

		query.append(_FILTER_SQL_COUNT_ARTICLE_WHERE);

		boolean conjunctionable = false;

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_L_S_GROUPID_5);

		conjunctionable = true;

		if ((parentResourcePrimKeies == null) ||
				(parentResourcePrimKeies.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < parentResourcePrimKeies.length; i++) {
				query.append(_FINDER_COLUMN_G_P_L_S_PARENTRESOURCEPRIMKEY_5);

				if ((i + 1) < parentResourcePrimKeies.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			conjunctionable = true;
		}

		if ((latests == null) || (latests.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < latests.length; i++) {
				query.append(_FINDER_COLUMN_G_P_L_S_LATEST_5);

				if ((i + 1) < latests.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			conjunctionable = true;
		}

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_L_S_STATUS_5);

		conjunctionable = true;

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Article.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (parentResourcePrimKeies != null) {
				qPos.add(parentResourcePrimKeies);
			}

			if (latests != null) {
				qPos.add(latests);
			}

			qPos.add(status);

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
	 * Counts all the articles where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @return the number of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByR_G_P_L_S(long resourcePrimKey, long groupId,
		long parentResourcePrimKey, int latest, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				resourcePrimKey, groupId, parentResourcePrimKey, latest, status
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_R_G_P_L_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_COUNT_ARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_G_P_L_S_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_G_P_L_S_GROUPID_2);

			query.append(_FINDER_COLUMN_R_G_P_L_S_PARENTRESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_G_P_L_S_LATEST_2);

			query.append(_FINDER_COLUMN_R_G_P_L_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(groupId);

				qPos.add(parentResourcePrimKey);

				qPos.add(latest);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_R_G_P_L_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the articles where resourcePrimKey = any &#63; and groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	 *
	 * @param resourcePrimKeies the resource prim keies to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKeies the parent resource prim keies to search with
	 * @param latests the latests to search with
	 * @param status the status to search with
	 * @return the number of matching articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByR_G_P_L_S(long[] resourcePrimKeies, long groupId,
		long[] parentResourcePrimKeies, int[] latests, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				StringUtil.merge(resourcePrimKeies), groupId,
				StringUtil.merge(parentResourcePrimKeies),
				StringUtil.merge(latests), status
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_R_G_P_L_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_ARTICLE_WHERE);

			boolean conjunctionable = false;

			if ((resourcePrimKeies == null) || (resourcePrimKeies.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < resourcePrimKeies.length; i++) {
					query.append(_FINDER_COLUMN_R_G_P_L_S_RESOURCEPRIMKEY_5);

					if ((i + 1) < resourcePrimKeies.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_R_G_P_L_S_GROUPID_5);

			conjunctionable = true;

			if ((parentResourcePrimKeies == null) ||
					(parentResourcePrimKeies.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < parentResourcePrimKeies.length; i++) {
					query.append(_FINDER_COLUMN_R_G_P_L_S_PARENTRESOURCEPRIMKEY_5);

					if ((i + 1) < parentResourcePrimKeies.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if ((latests == null) || (latests.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < latests.length; i++) {
					query.append(_FINDER_COLUMN_R_G_P_L_S_LATEST_5);

					if ((i + 1) < latests.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_R_G_P_L_S_STATUS_5);

			conjunctionable = true;

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (resourcePrimKeies != null) {
					qPos.add(resourcePrimKeies);
				}

				qPos.add(groupId);

				if (parentResourcePrimKeies != null) {
					qPos.add(parentResourcePrimKeies);
				}

				if (latests != null) {
					qPos.add(latests);
				}

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_R_G_P_L_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Filters by the user's permissions and counts all the articles where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKey the parent resource prim key to search with
	 * @param latest the latest to search with
	 * @param status the status to search with
	 * @return the number of matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByR_G_P_L_S(long resourcePrimKey, long groupId,
		long parentResourcePrimKey, int latest, int status)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByR_G_P_L_S(resourcePrimKey, groupId,
				parentResourcePrimKey, latest, status);
		}

		StringBundler query = new StringBundler(6);

		query.append(_FILTER_SQL_COUNT_ARTICLE_WHERE);

		query.append(_FINDER_COLUMN_R_G_P_L_S_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_P_L_S_GROUPID_2);

		query.append(_FINDER_COLUMN_R_G_P_L_S_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_P_L_S_LATEST_2);

		query.append(_FINDER_COLUMN_R_G_P_L_S_STATUS_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Article.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(resourcePrimKey);

			qPos.add(groupId);

			qPos.add(parentResourcePrimKey);

			qPos.add(latest);

			qPos.add(status);

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
	 * Filters by the user's permissions and counts all the articles where resourcePrimKey = any &#63; and groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	 *
	 * @param resourcePrimKeies the resource prim keies to search with
	 * @param groupId the group ID to search with
	 * @param parentResourcePrimKeies the parent resource prim keies to search with
	 * @param latests the latests to search with
	 * @param status the status to search with
	 * @return the number of matching articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByR_G_P_L_S(long[] resourcePrimKeies, long groupId,
		long[] parentResourcePrimKeies, int[] latests, int status)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByR_G_P_L_S(resourcePrimKeies, groupId,
				parentResourcePrimKeies, latests, status);
		}

		StringBundler query = new StringBundler();

		query.append(_FILTER_SQL_COUNT_ARTICLE_WHERE);

		boolean conjunctionable = false;

		if ((resourcePrimKeies == null) || (resourcePrimKeies.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < resourcePrimKeies.length; i++) {
				query.append(_FINDER_COLUMN_R_G_P_L_S_RESOURCEPRIMKEY_5);

				if ((i + 1) < resourcePrimKeies.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			conjunctionable = true;
		}

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_R_G_P_L_S_GROUPID_5);

		conjunctionable = true;

		if ((parentResourcePrimKeies == null) ||
				(parentResourcePrimKeies.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < parentResourcePrimKeies.length; i++) {
				query.append(_FINDER_COLUMN_R_G_P_L_S_PARENTRESOURCEPRIMKEY_5);

				if ((i + 1) < parentResourcePrimKeies.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			conjunctionable = true;
		}

		if ((latests == null) || (latests.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < latests.length; i++) {
				query.append(_FINDER_COLUMN_R_G_P_L_S_LATEST_5);

				if ((i + 1) < latests.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			conjunctionable = true;
		}

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_R_G_P_L_S_STATUS_5);

		conjunctionable = true;

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Article.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			if (resourcePrimKeies != null) {
				qPos.add(resourcePrimKeies);
			}

			qPos.add(groupId);

			if (parentResourcePrimKeies != null) {
				qPos.add(parentResourcePrimKeies);
			}

			if (latests != null) {
				qPos.add(latests);
			}

			qPos.add(status);

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
	 * Counts all the articles.
	 *
	 * @return the number of articles
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

				Query q = session.createQuery(_SQL_COUNT_ARTICLE);

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
	 * Initializes the article persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.knowledgebase.model.Article")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Article>> listenersList = new ArrayList<ModelListener<Article>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Article>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(ArticleImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST);
	}

	@BeanReference(type = ArticlePersistence.class)
	protected ArticlePersistence articlePersistence;
	@BeanReference(type = CommentPersistence.class)
	protected CommentPersistence commentPersistence;
	@BeanReference(type = TemplatePersistence.class)
	protected TemplatePersistence templatePersistence;
	@BeanReference(type = CompanyPersistence.class)
	protected CompanyPersistence companyPersistence;
	@BeanReference(type = GroupPersistence.class)
	protected GroupPersistence groupPersistence;
	@BeanReference(type = LayoutPersistence.class)
	protected LayoutPersistence layoutPersistence;
	@BeanReference(type = PortletPreferencesPersistence.class)
	protected PortletPreferencesPersistence portletPreferencesPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = SubscriptionPersistence.class)
	protected SubscriptionPersistence subscriptionPersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = WorkflowInstanceLinkPersistence.class)
	protected WorkflowInstanceLinkPersistence workflowInstanceLinkPersistence;
	@BeanReference(type = AssetEntryPersistence.class)
	protected AssetEntryPersistence assetEntryPersistence;
	@BeanReference(type = ExpandoValuePersistence.class)
	protected ExpandoValuePersistence expandoValuePersistence;
	@BeanReference(type = SocialActivityPersistence.class)
	protected SocialActivityPersistence socialActivityPersistence;
	private static final String _SQL_SELECT_ARTICLE = "SELECT article FROM Article article";
	private static final String _SQL_SELECT_ARTICLE_WHERE = "SELECT article FROM Article article WHERE ";
	private static final String _SQL_COUNT_ARTICLE = "SELECT COUNT(article) FROM Article article";
	private static final String _SQL_COUNT_ARTICLE_WHERE = "SELECT COUNT(article) FROM Article article WHERE ";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "article.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "article.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(article.uuid IS NULL OR article.uuid = ?)";
	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "article.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "article.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(article.uuid IS NULL OR article.uuid = ?) AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "article.groupId = ?";
	private static final String _FINDER_COLUMN_RESOURCEPRIMKEY_RESOURCEPRIMKEY_2 =
		"article.resourcePrimKey = ?";
	private static final String _FINDER_COLUMN_R_V_RESOURCEPRIMKEY_2 = "article.resourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_R_V_VERSION_2 = "article.version = ?";
	private static final String _FINDER_COLUMN_R_L_RESOURCEPRIMKEY_2 = "article.resourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_R_L_RESOURCEPRIMKEY_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_R_L_RESOURCEPRIMKEY_2) + ")";
	private static final String _FINDER_COLUMN_R_L_LATEST_2 = "article.latest = ?";
	private static final String _FINDER_COLUMN_R_L_LATEST_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_R_L_LATEST_2) + ")";
	private static final String _FINDER_COLUMN_R_S_RESOURCEPRIMKEY_2 = "article.resourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_R_S_STATUS_2 = "article.status = ?";
	private static final String _FINDER_COLUMN_G_L_GROUPID_2 = "article.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_L_GROUPID_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_L_GROUPID_2) + ")";
	private static final String _FINDER_COLUMN_G_L_LATEST_2 = "article.latest = ?";
	private static final String _FINDER_COLUMN_G_L_LATEST_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_L_LATEST_2) + ")";
	private static final String _FINDER_COLUMN_C_L_COMPANYID_2 = "article.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_L_COMPANYID_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_C_L_COMPANYID_2) + ")";
	private static final String _FINDER_COLUMN_C_L_LATEST_2 = "article.latest = ?";
	private static final String _FINDER_COLUMN_C_L_LATEST_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_C_L_LATEST_2) + ")";
	private static final String _FINDER_COLUMN_R_L_S_RESOURCEPRIMKEY_2 = "article.resourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_R_L_S_RESOURCEPRIMKEY_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_R_L_S_RESOURCEPRIMKEY_2) + ")";
	private static final String _FINDER_COLUMN_R_L_S_LATEST_2 = "article.latest = ? AND ";
	private static final String _FINDER_COLUMN_R_L_S_LATEST_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_R_L_S_LATEST_2) + ")";
	private static final String _FINDER_COLUMN_R_L_S_STATUS_2 = "article.status = ?";
	private static final String _FINDER_COLUMN_R_L_S_STATUS_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_R_L_S_STATUS_2) + ")";
	private static final String _FINDER_COLUMN_G_P_L_GROUPID_2 = "article.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_L_GROUPID_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_P_L_GROUPID_2) + ")";
	private static final String _FINDER_COLUMN_G_P_L_PARENTRESOURCEPRIMKEY_2 = "article.parentResourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_G_P_L_PARENTRESOURCEPRIMKEY_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_P_L_PARENTRESOURCEPRIMKEY_2) + ")";
	private static final String _FINDER_COLUMN_G_P_L_LATEST_2 = "article.latest = ?";
	private static final String _FINDER_COLUMN_G_P_L_LATEST_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_P_L_LATEST_2) + ")";
	private static final String _FINDER_COLUMN_G_L_S_GROUPID_2 = "article.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_L_S_GROUPID_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_L_S_GROUPID_2) + ")";
	private static final String _FINDER_COLUMN_G_L_S_LATEST_2 = "article.latest = ? AND ";
	private static final String _FINDER_COLUMN_G_L_S_LATEST_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_L_S_LATEST_2) + ")";
	private static final String _FINDER_COLUMN_G_L_S_STATUS_2 = "article.status = ?";
	private static final String _FINDER_COLUMN_G_L_S_STATUS_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_L_S_STATUS_2) + ")";
	private static final String _FINDER_COLUMN_C_L_S_COMPANYID_2 = "article.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_L_S_COMPANYID_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_C_L_S_COMPANYID_2) + ")";
	private static final String _FINDER_COLUMN_C_L_S_LATEST_2 = "article.latest = ? AND ";
	private static final String _FINDER_COLUMN_C_L_S_LATEST_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_C_L_S_LATEST_2) + ")";
	private static final String _FINDER_COLUMN_C_L_S_STATUS_2 = "article.status = ?";
	private static final String _FINDER_COLUMN_C_L_S_STATUS_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_C_L_S_STATUS_2) + ")";
	private static final String _FINDER_COLUMN_R_G_P_L_RESOURCEPRIMKEY_2 = "article.resourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_R_G_P_L_RESOURCEPRIMKEY_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_R_G_P_L_RESOURCEPRIMKEY_2) + ")";
	private static final String _FINDER_COLUMN_R_G_P_L_GROUPID_2 = "article.groupId = ? AND ";
	private static final String _FINDER_COLUMN_R_G_P_L_GROUPID_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_R_G_P_L_GROUPID_2) + ")";
	private static final String _FINDER_COLUMN_R_G_P_L_PARENTRESOURCEPRIMKEY_2 = "article.parentResourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_R_G_P_L_PARENTRESOURCEPRIMKEY_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_R_G_P_L_PARENTRESOURCEPRIMKEY_2) +
		")";
	private static final String _FINDER_COLUMN_R_G_P_L_LATEST_2 = "article.latest = ?";
	private static final String _FINDER_COLUMN_R_G_P_L_LATEST_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_R_G_P_L_LATEST_2) + ")";
	private static final String _FINDER_COLUMN_G_P_L_S_GROUPID_2 = "article.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_L_S_GROUPID_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_P_L_S_GROUPID_2) + ")";
	private static final String _FINDER_COLUMN_G_P_L_S_PARENTRESOURCEPRIMKEY_2 = "article.parentResourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_G_P_L_S_PARENTRESOURCEPRIMKEY_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_P_L_S_PARENTRESOURCEPRIMKEY_2) +
		")";
	private static final String _FINDER_COLUMN_G_P_L_S_LATEST_2 = "article.latest = ? AND ";
	private static final String _FINDER_COLUMN_G_P_L_S_LATEST_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_P_L_S_LATEST_2) + ")";
	private static final String _FINDER_COLUMN_G_P_L_S_STATUS_2 = "article.status = ?";
	private static final String _FINDER_COLUMN_G_P_L_S_STATUS_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_P_L_S_STATUS_2) + ")";
	private static final String _FINDER_COLUMN_R_G_P_L_S_RESOURCEPRIMKEY_2 = "article.resourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_R_G_P_L_S_RESOURCEPRIMKEY_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_R_G_P_L_S_RESOURCEPRIMKEY_2) + ")";
	private static final String _FINDER_COLUMN_R_G_P_L_S_GROUPID_2 = "article.groupId = ? AND ";
	private static final String _FINDER_COLUMN_R_G_P_L_S_GROUPID_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_R_G_P_L_S_GROUPID_2) + ")";
	private static final String _FINDER_COLUMN_R_G_P_L_S_PARENTRESOURCEPRIMKEY_2 =
		"article.parentResourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_R_G_P_L_S_PARENTRESOURCEPRIMKEY_5 =
		"(" +
		_removeConjunction(_FINDER_COLUMN_R_G_P_L_S_PARENTRESOURCEPRIMKEY_2) +
		")";
	private static final String _FINDER_COLUMN_R_G_P_L_S_LATEST_2 = "article.latest = ? AND ";
	private static final String _FINDER_COLUMN_R_G_P_L_S_LATEST_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_R_G_P_L_S_LATEST_2) + ")";
	private static final String _FINDER_COLUMN_R_G_P_L_S_STATUS_2 = "article.status = ?";
	private static final String _FINDER_COLUMN_R_G_P_L_S_STATUS_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_R_G_P_L_S_STATUS_2) + ")";

	private static String _removeConjunction(String sql) {
		int pos = sql.indexOf(" AND ");

		if (pos != -1) {
			sql = sql.substring(0, pos);
		}

		return sql;
	}

	private static final String _FILTER_SQL_SELECT_ARTICLE_WHERE = "SELECT DISTINCT {article.*} FROM KB_Article article WHERE ";
	private static final String _FILTER_SQL_SELECT_ARTICLE_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {KB_Article.*} FROM (SELECT DISTINCT article.articleId FROM KB_Article article WHERE ";
	private static final String _FILTER_SQL_SELECT_ARTICLE_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN KB_Article ON TEMP_TABLE.articleId = KB_Article.articleId";
	private static final String _FILTER_SQL_COUNT_ARTICLE_WHERE = "SELECT COUNT(DISTINCT article.articleId) AS COUNT_VALUE FROM KB_Article article WHERE ";
	private static final String _FILTER_COLUMN_PK = "article.resourcePrimKey";
	private static final String _FILTER_COLUMN_USERID = "article.userId";
	private static final String _FILTER_ENTITY_ALIAS = "article";
	private static final String _FILTER_ENTITY_TABLE = "KB_Article";
	private static final String _ORDER_BY_ENTITY_ALIAS = "article.";
	private static final String _ORDER_BY_ENTITY_TABLE = "KB_Article.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Article exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Article exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(ArticlePersistenceImpl.class);
}