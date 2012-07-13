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

package com.liferay.knowledgebase.service.persistence;

import com.liferay.knowledgebase.NoSuchArticleException;
import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.model.impl.KBArticleImpl;
import com.liferay.knowledgebase.model.impl.KBArticleModelImpl;

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
import com.liferay.portal.kernel.util.ArrayUtil;
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
import com.liferay.portal.service.persistence.CompanyPersistence;
import com.liferay.portal.service.persistence.GroupPersistence;
import com.liferay.portal.service.persistence.LayoutPersistence;
import com.liferay.portal.service.persistence.PortletPreferencesPersistence;
import com.liferay.portal.service.persistence.SubscriptionPersistence;
import com.liferay.portal.service.persistence.TicketPersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.WorkflowInstanceLinkPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.portlet.asset.service.persistence.AssetEntryPersistence;
import com.liferay.portlet.ratings.service.persistence.RatingsStatsPersistence;
import com.liferay.portlet.social.service.persistence.SocialActivityPersistence;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the k b article service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KBArticlePersistence
 * @see KBArticleUtil
 * @generated
 */
public class KBArticlePersistenceImpl extends BasePersistenceImpl<KBArticle>
	implements KBArticlePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link KBArticleUtil} to access the k b article persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = KBArticleImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			KBArticleModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			KBArticleModelImpl.UUID_COLUMN_BITMASK |
			KBArticleModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			KBArticleModelImpl.UUID_COLUMN_BITMASK |
			KBArticleModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_RESOURCEPRIMKEY =
		new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByResourcePrimKey",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RESOURCEPRIMKEY =
		new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByResourcePrimKey",
			new String[] { Long.class.getName() },
			KBArticleModelImpl.RESOURCEPRIMKEY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_RESOURCEPRIMKEY = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByResourcePrimKey", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_R_G = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByR_G",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByR_G",
			new String[] { Long.class.getName(), Long.class.getName() },
			KBArticleModelImpl.RESOURCEPRIMKEY_COLUMN_BITMASK |
			KBArticleModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_R_G = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByR_G",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_R_V = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByR_V",
			new String[] { Long.class.getName(), Integer.class.getName() },
			KBArticleModelImpl.RESOURCEPRIMKEY_COLUMN_BITMASK |
			KBArticleModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_R_V = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByR_V",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_R_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByR_L",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByR_L",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			KBArticleModelImpl.RESOURCEPRIMKEY_COLUMN_BITMASK |
			KBArticleModelImpl.LATEST_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_R_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByR_L",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByR_L",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_R_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByR_M",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByR_M",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			KBArticleModelImpl.RESOURCEPRIMKEY_COLUMN_BITMASK |
			KBArticleModelImpl.MAIN_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_R_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByR_M",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByR_M",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_R_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByR_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByR_S",
			new String[] { Long.class.getName(), Integer.class.getName() },
			KBArticleModelImpl.RESOURCEPRIMKEY_COLUMN_BITMASK |
			KBArticleModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_R_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByR_S",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByR_S",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_L",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_L",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			KBArticleModelImpl.GROUPID_COLUMN_BITMASK |
			KBArticleModelImpl.LATEST_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_L",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_M",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_M",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			KBArticleModelImpl.GROUPID_COLUMN_BITMASK |
			KBArticleModelImpl.MAIN_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_M",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_S",
			new String[] { Long.class.getName(), Integer.class.getName() },
			KBArticleModelImpl.GROUPID_COLUMN_BITMASK |
			KBArticleModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_S",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_L",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_L",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			KBArticleModelImpl.COMPANYID_COLUMN_BITMASK |
			KBArticleModelImpl.LATEST_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_L",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_M",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_M",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			KBArticleModelImpl.COMPANYID_COLUMN_BITMASK |
			KBArticleModelImpl.MAIN_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_M",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_S",
			new String[] { Long.class.getName(), Integer.class.getName() },
			KBArticleModelImpl.COMPANYID_COLUMN_BITMASK |
			KBArticleModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_S",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_P_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByP_L",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByP_L",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			KBArticleModelImpl.PARENTRESOURCEPRIMKEY_COLUMN_BITMASK |
			KBArticleModelImpl.LATEST_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_P_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByP_L",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_P_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByP_L",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_P_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByP_M",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByP_M",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			KBArticleModelImpl.PARENTRESOURCEPRIMKEY_COLUMN_BITMASK |
			KBArticleModelImpl.MAIN_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_P_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByP_M",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_P_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByP_M",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_P_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByP_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByP_S",
			new String[] { Long.class.getName(), Integer.class.getName() },
			KBArticleModelImpl.PARENTRESOURCEPRIMKEY_COLUMN_BITMASK |
			KBArticleModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_P_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByP_S",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_P_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByP_S",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_R_G_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByR_G_L",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByR_G_L",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			},
			KBArticleModelImpl.RESOURCEPRIMKEY_COLUMN_BITMASK |
			KBArticleModelImpl.GROUPID_COLUMN_BITMASK |
			KBArticleModelImpl.LATEST_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_R_G_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByR_G_L",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_G_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByR_G_L",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_R_G_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByR_G_M",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByR_G_M",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			},
			KBArticleModelImpl.RESOURCEPRIMKEY_COLUMN_BITMASK |
			KBArticleModelImpl.GROUPID_COLUMN_BITMASK |
			KBArticleModelImpl.MAIN_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_R_G_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByR_G_M",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_G_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByR_G_M",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_R_G_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByR_G_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByR_G_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			KBArticleModelImpl.RESOURCEPRIMKEY_COLUMN_BITMASK |
			KBArticleModelImpl.GROUPID_COLUMN_BITMASK |
			KBArticleModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_R_G_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByR_G_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_G_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByR_G_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_P_L",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_P_L",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			},
			KBArticleModelImpl.GROUPID_COLUMN_BITMASK |
			KBArticleModelImpl.PARENTRESOURCEPRIMKEY_COLUMN_BITMASK |
			KBArticleModelImpl.LATEST_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_P_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_P_L",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_P_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_P_L",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_P_M",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_P_M",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			},
			KBArticleModelImpl.GROUPID_COLUMN_BITMASK |
			KBArticleModelImpl.PARENTRESOURCEPRIMKEY_COLUMN_BITMASK |
			KBArticleModelImpl.MAIN_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_P_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_P_M",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_P_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_P_M",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_P_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_P_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			KBArticleModelImpl.GROUPID_COLUMN_BITMASK |
			KBArticleModelImpl.PARENTRESOURCEPRIMKEY_COLUMN_BITMASK |
			KBArticleModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_P_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_P_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_P_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_P_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_S_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_P_S_L",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_P_S_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_P_S_L",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Boolean.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_S_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_P_S_M",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_P_S_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_P_S_M",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Boolean.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_S_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_P_S_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_P_S_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_P_S_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the k b article in the entity cache if it is enabled.
	 *
	 * @param kbArticle the k b article
	 */
	public void cacheResult(KBArticle kbArticle) {
		EntityCacheUtil.putResult(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleImpl.class, kbArticle.getPrimaryKey(), kbArticle);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				kbArticle.getUuid(), Long.valueOf(kbArticle.getGroupId())
			}, kbArticle);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_R_V,
			new Object[] {
				Long.valueOf(kbArticle.getResourcePrimKey()),
				Integer.valueOf(kbArticle.getVersion())
			}, kbArticle);

		kbArticle.resetOriginalValues();
	}

	/**
	 * Caches the k b articles in the entity cache if it is enabled.
	 *
	 * @param kbArticles the k b articles
	 */
	public void cacheResult(List<KBArticle> kbArticles) {
		for (KBArticle kbArticle : kbArticles) {
			if (EntityCacheUtil.getResult(
						KBArticleModelImpl.ENTITY_CACHE_ENABLED,
						KBArticleImpl.class, kbArticle.getPrimaryKey()) == null) {
				cacheResult(kbArticle);
			}
			else {
				kbArticle.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all k b articles.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(KBArticleImpl.class.getName());
		}

		EntityCacheUtil.clearCache(KBArticleImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the k b article.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(KBArticle kbArticle) {
		EntityCacheUtil.removeResult(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleImpl.class, kbArticle.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(kbArticle);
	}

	@Override
	public void clearCache(List<KBArticle> kbArticles) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (KBArticle kbArticle : kbArticles) {
			EntityCacheUtil.removeResult(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
				KBArticleImpl.class, kbArticle.getPrimaryKey());

			clearUniqueFindersCache(kbArticle);
		}
	}

	protected void clearUniqueFindersCache(KBArticle kbArticle) {
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				kbArticle.getUuid(), Long.valueOf(kbArticle.getGroupId())
			});

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_R_V,
			new Object[] {
				Long.valueOf(kbArticle.getResourcePrimKey()),
				Integer.valueOf(kbArticle.getVersion())
			});
	}

	/**
	 * Creates a new k b article with the primary key. Does not add the k b article to the database.
	 *
	 * @param kbArticleId the primary key for the new k b article
	 * @return the new k b article
	 */
	public KBArticle create(long kbArticleId) {
		KBArticle kbArticle = new KBArticleImpl();

		kbArticle.setNew(true);
		kbArticle.setPrimaryKey(kbArticleId);

		String uuid = PortalUUIDUtil.generate();

		kbArticle.setUuid(uuid);

		return kbArticle;
	}

	/**
	 * Removes the k b article with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kbArticleId the primary key of the k b article
	 * @return the k b article that was removed
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle remove(long kbArticleId)
		throws NoSuchArticleException, SystemException {
		return remove(Long.valueOf(kbArticleId));
	}

	/**
	 * Removes the k b article with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the k b article
	 * @return the k b article that was removed
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KBArticle remove(Serializable primaryKey)
		throws NoSuchArticleException, SystemException {
		Session session = null;

		try {
			session = openSession();

			KBArticle kbArticle = (KBArticle)session.get(KBArticleImpl.class,
					primaryKey);

			if (kbArticle == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchArticleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(kbArticle);
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

	@Override
	protected KBArticle removeImpl(KBArticle kbArticle)
		throws SystemException {
		kbArticle = toUnwrappedModel(kbArticle);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, kbArticle);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(kbArticle);

		return kbArticle;
	}

	@Override
	public KBArticle updateImpl(
		com.liferay.knowledgebase.model.KBArticle kbArticle, boolean merge)
		throws SystemException {
		kbArticle = toUnwrappedModel(kbArticle);

		boolean isNew = kbArticle.isNew();

		KBArticleModelImpl kbArticleModelImpl = (KBArticleModelImpl)kbArticle;

		if (Validator.isNull(kbArticle.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			kbArticle.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, kbArticle, merge);

			kbArticle.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !KBArticleModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbArticleModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { kbArticleModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbArticleModelImpl.getOriginalUuid(),
						Long.valueOf(kbArticleModelImpl.getOriginalCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						kbArticleModelImpl.getUuid(),
						Long.valueOf(kbArticleModelImpl.getCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RESOURCEPRIMKEY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getOriginalResourcePrimKey())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_RESOURCEPRIMKEY,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RESOURCEPRIMKEY,
					args);

				args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getResourcePrimKey())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_RESOURCEPRIMKEY,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RESOURCEPRIMKEY,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getOriginalResourcePrimKey()),
						Long.valueOf(kbArticleModelImpl.getOriginalGroupId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_G, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G,
					args);

				args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getResourcePrimKey()),
						Long.valueOf(kbArticleModelImpl.getGroupId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_G, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_L.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getOriginalResourcePrimKey()),
						Boolean.valueOf(kbArticleModelImpl.getOriginalLatest())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_L, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_L,
					args);

				args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getResourcePrimKey()),
						Boolean.valueOf(kbArticleModelImpl.getLatest())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_L, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_L,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_M.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getOriginalResourcePrimKey()),
						Boolean.valueOf(kbArticleModelImpl.getOriginalMain())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_M, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_M,
					args);

				args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getResourcePrimKey()),
						Boolean.valueOf(kbArticleModelImpl.getMain())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_M, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_M,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getOriginalResourcePrimKey()),
						Integer.valueOf(kbArticleModelImpl.getOriginalStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_S,
					args);

				args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getResourcePrimKey()),
						Integer.valueOf(kbArticleModelImpl.getStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_S,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_L.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getOriginalGroupId()),
						Boolean.valueOf(kbArticleModelImpl.getOriginalLatest())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_L, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_L,
					args);

				args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getGroupId()),
						Boolean.valueOf(kbArticleModelImpl.getLatest())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_L, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_L,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_M.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getOriginalGroupId()),
						Boolean.valueOf(kbArticleModelImpl.getOriginalMain())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_M, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_M,
					args);

				args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getGroupId()),
						Boolean.valueOf(kbArticleModelImpl.getMain())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_M, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_M,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getOriginalGroupId()),
						Integer.valueOf(kbArticleModelImpl.getOriginalStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S,
					args);

				args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getGroupId()),
						Integer.valueOf(kbArticleModelImpl.getStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_L.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getOriginalCompanyId()),
						Boolean.valueOf(kbArticleModelImpl.getOriginalLatest())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_L, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_L,
					args);

				args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getCompanyId()),
						Boolean.valueOf(kbArticleModelImpl.getLatest())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_L, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_L,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_M.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getOriginalCompanyId()),
						Boolean.valueOf(kbArticleModelImpl.getOriginalMain())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_M, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_M,
					args);

				args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getCompanyId()),
						Boolean.valueOf(kbArticleModelImpl.getMain())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_M, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_M,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getOriginalCompanyId()),
						Integer.valueOf(kbArticleModelImpl.getOriginalStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S,
					args);

				args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getCompanyId()),
						Integer.valueOf(kbArticleModelImpl.getStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_L.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getOriginalParentResourcePrimKey()),
						Boolean.valueOf(kbArticleModelImpl.getOriginalLatest())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_P_L, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_L,
					args);

				args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getParentResourcePrimKey()),
						Boolean.valueOf(kbArticleModelImpl.getLatest())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_P_L, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_L,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_M.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getOriginalParentResourcePrimKey()),
						Boolean.valueOf(kbArticleModelImpl.getOriginalMain())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_P_M, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_M,
					args);

				args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getParentResourcePrimKey()),
						Boolean.valueOf(kbArticleModelImpl.getMain())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_P_M, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_M,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getOriginalParentResourcePrimKey()),
						Integer.valueOf(kbArticleModelImpl.getOriginalStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_P_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_S,
					args);

				args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getParentResourcePrimKey()),
						Integer.valueOf(kbArticleModelImpl.getStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_P_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_S,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G_L.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getOriginalResourcePrimKey()),
						Long.valueOf(kbArticleModelImpl.getOriginalGroupId()),
						Boolean.valueOf(kbArticleModelImpl.getOriginalLatest())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_G_L, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G_L,
					args);

				args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getResourcePrimKey()),
						Long.valueOf(kbArticleModelImpl.getGroupId()),
						Boolean.valueOf(kbArticleModelImpl.getLatest())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_G_L, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G_L,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G_M.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getOriginalResourcePrimKey()),
						Long.valueOf(kbArticleModelImpl.getOriginalGroupId()),
						Boolean.valueOf(kbArticleModelImpl.getOriginalMain())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_G_M, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G_M,
					args);

				args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getResourcePrimKey()),
						Long.valueOf(kbArticleModelImpl.getGroupId()),
						Boolean.valueOf(kbArticleModelImpl.getMain())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_G_M, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G_M,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getOriginalResourcePrimKey()),
						Long.valueOf(kbArticleModelImpl.getOriginalGroupId()),
						Integer.valueOf(kbArticleModelImpl.getOriginalStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_G_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G_S,
					args);

				args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getResourcePrimKey()),
						Long.valueOf(kbArticleModelImpl.getGroupId()),
						Integer.valueOf(kbArticleModelImpl.getStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_G_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G_S,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_L.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getOriginalGroupId()),
						Long.valueOf(kbArticleModelImpl.getOriginalParentResourcePrimKey()),
						Boolean.valueOf(kbArticleModelImpl.getOriginalLatest())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_P_L, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_L,
					args);

				args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getGroupId()),
						Long.valueOf(kbArticleModelImpl.getParentResourcePrimKey()),
						Boolean.valueOf(kbArticleModelImpl.getLatest())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_P_L, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_L,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_M.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getOriginalGroupId()),
						Long.valueOf(kbArticleModelImpl.getOriginalParentResourcePrimKey()),
						Boolean.valueOf(kbArticleModelImpl.getOriginalMain())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_P_M, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_M,
					args);

				args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getGroupId()),
						Long.valueOf(kbArticleModelImpl.getParentResourcePrimKey()),
						Boolean.valueOf(kbArticleModelImpl.getMain())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_P_M, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_M,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getOriginalGroupId()),
						Long.valueOf(kbArticleModelImpl.getOriginalParentResourcePrimKey()),
						Integer.valueOf(kbArticleModelImpl.getOriginalStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_P_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_S,
					args);

				args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getGroupId()),
						Long.valueOf(kbArticleModelImpl.getParentResourcePrimKey()),
						Integer.valueOf(kbArticleModelImpl.getStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_P_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_S,
					args);
			}
		}

		EntityCacheUtil.putResult(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleImpl.class, kbArticle.getPrimaryKey(), kbArticle);

		if (isNew) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					kbArticle.getUuid(), Long.valueOf(kbArticle.getGroupId())
				}, kbArticle);

			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_R_V,
				new Object[] {
					Long.valueOf(kbArticle.getResourcePrimKey()),
					Integer.valueOf(kbArticle.getVersion())
				}, kbArticle);
		}
		else {
			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbArticleModelImpl.getOriginalUuid(),
						Long.valueOf(kbArticleModelImpl.getOriginalGroupId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);

				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
					new Object[] {
						kbArticle.getUuid(),
						Long.valueOf(kbArticle.getGroupId())
					}, kbArticle);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_R_V.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(kbArticleModelImpl.getOriginalResourcePrimKey()),
						Integer.valueOf(kbArticleModelImpl.getOriginalVersion())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_V, args);

				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_R_V, args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_R_V,
					new Object[] {
						Long.valueOf(kbArticle.getResourcePrimKey()),
						Integer.valueOf(kbArticle.getVersion())
					}, kbArticle);
			}
		}

		return kbArticle;
	}

	protected KBArticle toUnwrappedModel(KBArticle kbArticle) {
		if (kbArticle instanceof KBArticleImpl) {
			return kbArticle;
		}

		KBArticleImpl kbArticleImpl = new KBArticleImpl();

		kbArticleImpl.setNew(kbArticle.isNew());
		kbArticleImpl.setPrimaryKey(kbArticle.getPrimaryKey());

		kbArticleImpl.setUuid(kbArticle.getUuid());
		kbArticleImpl.setKbArticleId(kbArticle.getKbArticleId());
		kbArticleImpl.setResourcePrimKey(kbArticle.getResourcePrimKey());
		kbArticleImpl.setGroupId(kbArticle.getGroupId());
		kbArticleImpl.setCompanyId(kbArticle.getCompanyId());
		kbArticleImpl.setUserId(kbArticle.getUserId());
		kbArticleImpl.setUserName(kbArticle.getUserName());
		kbArticleImpl.setCreateDate(kbArticle.getCreateDate());
		kbArticleImpl.setModifiedDate(kbArticle.getModifiedDate());
		kbArticleImpl.setRootResourcePrimKey(kbArticle.getRootResourcePrimKey());
		kbArticleImpl.setParentResourcePrimKey(kbArticle.getParentResourcePrimKey());
		kbArticleImpl.setVersion(kbArticle.getVersion());
		kbArticleImpl.setTitle(kbArticle.getTitle());
		kbArticleImpl.setContent(kbArticle.getContent());
		kbArticleImpl.setDescription(kbArticle.getDescription());
		kbArticleImpl.setPriority(kbArticle.getPriority());
		kbArticleImpl.setSections(kbArticle.getSections());
		kbArticleImpl.setViewCount(kbArticle.getViewCount());
		kbArticleImpl.setLatest(kbArticle.isLatest());
		kbArticleImpl.setMain(kbArticle.isMain());
		kbArticleImpl.setStatus(kbArticle.getStatus());
		kbArticleImpl.setStatusByUserId(kbArticle.getStatusByUserId());
		kbArticleImpl.setStatusByUserName(kbArticle.getStatusByUserName());
		kbArticleImpl.setStatusDate(kbArticle.getStatusDate());

		return kbArticleImpl;
	}

	/**
	 * Returns the k b article with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the k b article
	 * @return the k b article
	 * @throws com.liferay.portal.NoSuchModelException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KBArticle findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the k b article with the primary key or throws a {@link com.liferay.knowledgebase.NoSuchArticleException} if it could not be found.
	 *
	 * @param kbArticleId the primary key of the k b article
	 * @return the k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByPrimaryKey(long kbArticleId)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByPrimaryKey(kbArticleId);

		if (kbArticle == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + kbArticleId);
			}

			throw new NoSuchArticleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				kbArticleId);
		}

		return kbArticle;
	}

	/**
	 * Returns the k b article with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the k b article
	 * @return the k b article, or <code>null</code> if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KBArticle fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the k b article with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kbArticleId the primary key of the k b article
	 * @return the k b article, or <code>null</code> if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByPrimaryKey(long kbArticleId)
		throws SystemException {
		KBArticle kbArticle = (KBArticle)EntityCacheUtil.getResult(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
				KBArticleImpl.class, kbArticleId);

		if (kbArticle == _nullKBArticle) {
			return null;
		}

		if (kbArticle == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				kbArticle = (KBArticle)session.get(KBArticleImpl.class,
						Long.valueOf(kbArticleId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (kbArticle != null) {
					cacheResult(kbArticle);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
						KBArticleImpl.class, kbArticleId, _nullKBArticle);
				}

				closeSession(session);
			}
		}

		return kbArticle;
	}

	/**
	 * Returns all the k b articles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByUuid(String uuid) throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByUuid(String uuid, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if (!Validator.equals(uuid, kbArticle.getUuid())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
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

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first k b article in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByUuid_First(uuid, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<KBArticle> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByUuid_Last(uuid, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		List<KBArticle> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where uuid = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle[] findByUuid_PrevAndNext(long kbArticleId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByUuid_PrevAndNext(session, kbArticle, uuid,
					orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByUuid_PrevAndNext(session, kbArticle, uuid,
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

	protected KBArticle getByUuid_PrevAndNext(Session session,
		KBArticle kbArticle, String uuid, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the k b article where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.knowledgebase.NoSuchArticleException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByUUID_G(String uuid, long groupId)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByUUID_G(uuid, groupId);

		if (kbArticle == null) {
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

		return kbArticle;
	}

	/**
	 * Returns the k b article where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the k b article where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof KBArticle) {
			KBArticle kbArticle = (KBArticle)result;

			if (!Validator.equals(uuid, kbArticle.getUuid()) ||
					(groupId != kbArticle.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

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

			query.append(KBArticleModelImpl.ORDER_BY_JPQL);

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

				List<KBArticle> list = q.list();

				result = list;

				KBArticle kbArticle = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					kbArticle = list.get(0);

					cacheResult(kbArticle);

					if ((kbArticle.getUuid() == null) ||
							!kbArticle.getUuid().equals(uuid) ||
							(kbArticle.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, kbArticle);
					}
				}

				return kbArticle;
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
				return (KBArticle)result;
			}
		}
	}

	/**
	 * Returns all the k b articles where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByUuid_C(String uuid, long companyId)
		throws SystemException {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByUuid_C(String uuid, long companyId, int start,
		int end) throws SystemException {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] { uuid, companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] {
					uuid, companyId,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if (!Validator.equals(uuid, kbArticle.getUuid()) ||
						(companyId != kbArticle.getCompanyId())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_C_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_C_UUID_2);
				}
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
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

				qPos.add(companyId);

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first k b article in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByUuid_C_First(String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<KBArticle> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid_C(uuid, companyId);

		List<KBArticle> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle[] findByUuid_C_PrevAndNext(long kbArticleId, String uuid,
		long companyId, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, kbArticle, uuid,
					companyId, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByUuid_C_PrevAndNext(session, kbArticle, uuid,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle getByUuid_C_PrevAndNext(Session session,
		KBArticle kbArticle, String uuid, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_1);
		}
		else {
			if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (uuid != null) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles where resourcePrimKey = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByResourcePrimKey(long resourcePrimKey)
		throws SystemException {
		return findByResourcePrimKey(resourcePrimKey, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where resourcePrimKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByResourcePrimKey(long resourcePrimKey,
		int start, int end) throws SystemException {
		return findByResourcePrimKey(resourcePrimKey, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where resourcePrimKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByResourcePrimKey(long resourcePrimKey,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RESOURCEPRIMKEY;
			finderArgs = new Object[] { resourcePrimKey };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_RESOURCEPRIMKEY;
			finderArgs = new Object[] {
					resourcePrimKey,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((resourcePrimKey != kbArticle.getResourcePrimKey())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_RESOURCEPRIMKEY_RESOURCEPRIMKEY_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first k b article in the ordered set where resourcePrimKey = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByResourcePrimKey_First(long resourcePrimKey,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByResourcePrimKey_First(resourcePrimKey,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourcePrimKey=");
		msg.append(resourcePrimKey);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where resourcePrimKey = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByResourcePrimKey_First(long resourcePrimKey,
		OrderByComparator orderByComparator) throws SystemException {
		List<KBArticle> list = findByResourcePrimKey(resourcePrimKey, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where resourcePrimKey = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByResourcePrimKey_Last(long resourcePrimKey,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByResourcePrimKey_Last(resourcePrimKey,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourcePrimKey=");
		msg.append(resourcePrimKey);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where resourcePrimKey = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByResourcePrimKey_Last(long resourcePrimKey,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByResourcePrimKey(resourcePrimKey);

		List<KBArticle> list = findByResourcePrimKey(resourcePrimKey,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param resourcePrimKey the resource prim key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle[] findByResourcePrimKey_PrevAndNext(long kbArticleId,
		long resourcePrimKey, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByResourcePrimKey_PrevAndNext(session, kbArticle,
					resourcePrimKey, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByResourcePrimKey_PrevAndNext(session, kbArticle,
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

	protected KBArticle getByResourcePrimKey_PrevAndNext(Session session,
		KBArticle kbArticle, long resourcePrimKey,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_RESOURCEPRIMKEY_RESOURCEPRIMKEY_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resourcePrimKey);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles where resourcePrimKey = &#63; and groupId = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_G(long resourcePrimKey, long groupId)
		throws SystemException {
		return findByR_G(resourcePrimKey, groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_G(long resourcePrimKey, long groupId,
		int start, int end) throws SystemException {
		return findByR_G(resourcePrimKey, groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_G(long resourcePrimKey, long groupId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G;
			finderArgs = new Object[] { resourcePrimKey, groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_R_G;
			finderArgs = new Object[] {
					resourcePrimKey, groupId,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((resourcePrimKey != kbArticle.getResourcePrimKey()) ||
						(groupId != kbArticle.getGroupId())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_G_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_G_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(groupId);

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByR_G_First(long resourcePrimKey, long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByR_G_First(resourcePrimKey, groupId,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourcePrimKey=");
		msg.append(resourcePrimKey);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByR_G_First(long resourcePrimKey, long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		List<KBArticle> list = findByR_G(resourcePrimKey, groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByR_G_Last(long resourcePrimKey, long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByR_G_Last(resourcePrimKey, groupId,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourcePrimKey=");
		msg.append(resourcePrimKey);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByR_G_Last(long resourcePrimKey, long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByR_G(resourcePrimKey, groupId);

		List<KBArticle> list = findByR_G(resourcePrimKey, groupId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle[] findByR_G_PrevAndNext(long kbArticleId,
		long resourcePrimKey, long groupId, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByR_G_PrevAndNext(session, kbArticle,
					resourcePrimKey, groupId, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByR_G_PrevAndNext(session, kbArticle,
					resourcePrimKey, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle getByR_G_PrevAndNext(Session session,
		KBArticle kbArticle, long resourcePrimKey, long groupId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_R_G_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_GROUPID_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resourcePrimKey);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @return the matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByR_G(long resourcePrimKey, long groupId)
		throws SystemException {
		return filterFindByR_G(resourcePrimKey, groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByR_G(long resourcePrimKey, long groupId,
		int start, int end) throws SystemException {
		return filterFindByR_G(resourcePrimKey, groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permissions to view where resourcePrimKey = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByR_G(long resourcePrimKey, long groupId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByR_G(resourcePrimKey, groupId, start, end,
				orderByComparator);
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
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_R_G_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(resourcePrimKey);

			qPos.add(groupId);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle[] filterFindByR_G_PrevAndNext(long kbArticleId,
		long resourcePrimKey, long groupId, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByR_G_PrevAndNext(kbArticleId, resourcePrimKey, groupId,
				orderByComparator);
		}

		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = filterGetByR_G_PrevAndNext(session, kbArticle,
					resourcePrimKey, groupId, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = filterGetByR_G_PrevAndNext(session, kbArticle,
					resourcePrimKey, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle filterGetByR_G_PrevAndNext(Session session,
		KBArticle kbArticle, long resourcePrimKey, long groupId,
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
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_R_G_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resourcePrimKey);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the k b article where resourcePrimKey = &#63; and version = &#63; or throws a {@link com.liferay.knowledgebase.NoSuchArticleException} if it could not be found.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param version the version
	 * @return the matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByR_V(long resourcePrimKey, int version)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByR_V(resourcePrimKey, version);

		if (kbArticle == null) {
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

		return kbArticle;
	}

	/**
	 * Returns the k b article where resourcePrimKey = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param version the version
	 * @return the matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByR_V(long resourcePrimKey, int version)
		throws SystemException {
		return fetchByR_V(resourcePrimKey, version, true);
	}

	/**
	 * Returns the k b article where resourcePrimKey = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param version the version
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByR_V(long resourcePrimKey, int version,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { resourcePrimKey, version };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_R_V,
					finderArgs, this);
		}

		if (result instanceof KBArticle) {
			KBArticle kbArticle = (KBArticle)result;

			if ((resourcePrimKey != kbArticle.getResourcePrimKey()) ||
					(version != kbArticle.getVersion())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_V_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_V_VERSION_2);

			query.append(KBArticleModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(version);

				List<KBArticle> list = q.list();

				result = list;

				KBArticle kbArticle = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_R_V,
						finderArgs, list);
				}
				else {
					kbArticle = list.get(0);

					cacheResult(kbArticle);

					if ((kbArticle.getResourcePrimKey() != resourcePrimKey) ||
							(kbArticle.getVersion() != version)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_R_V,
							finderArgs, kbArticle);
					}
				}

				return kbArticle;
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
				return (KBArticle)result;
			}
		}
	}

	/**
	 * Returns all the k b articles where resourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param latest the latest
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_L(long resourcePrimKey, boolean latest)
		throws SystemException {
		return findByR_L(resourcePrimKey, latest, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where resourcePrimKey = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_L(long resourcePrimKey, boolean latest,
		int start, int end) throws SystemException {
		return findByR_L(resourcePrimKey, latest, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where resourcePrimKey = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_L(long resourcePrimKey, boolean latest,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_L;
			finderArgs = new Object[] { resourcePrimKey, latest };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_R_L;
			finderArgs = new Object[] {
					resourcePrimKey, latest,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((resourcePrimKey != kbArticle.getResourcePrimKey()) ||
						(latest != kbArticle.getLatest())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_L_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_L_LATEST_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(latest);

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first k b article in the ordered set where resourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByR_L_First(long resourcePrimKey, boolean latest,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByR_L_First(resourcePrimKey, latest,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourcePrimKey=");
		msg.append(resourcePrimKey);

		msg.append(", latest=");
		msg.append(latest);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where resourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByR_L_First(long resourcePrimKey, boolean latest,
		OrderByComparator orderByComparator) throws SystemException {
		List<KBArticle> list = findByR_L(resourcePrimKey, latest, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where resourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByR_L_Last(long resourcePrimKey, boolean latest,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByR_L_Last(resourcePrimKey, latest,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourcePrimKey=");
		msg.append(resourcePrimKey);

		msg.append(", latest=");
		msg.append(latest);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where resourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByR_L_Last(long resourcePrimKey, boolean latest,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByR_L(resourcePrimKey, latest);

		List<KBArticle> list = findByR_L(resourcePrimKey, latest, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param resourcePrimKey the resource prim key
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle[] findByR_L_PrevAndNext(long kbArticleId,
		long resourcePrimKey, boolean latest,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByR_L_PrevAndNext(session, kbArticle,
					resourcePrimKey, latest, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByR_L_PrevAndNext(session, kbArticle,
					resourcePrimKey, latest, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle getByR_L_PrevAndNext(Session session,
		KBArticle kbArticle, long resourcePrimKey, boolean latest,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_R_L_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_L_LATEST_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resourcePrimKey);

		qPos.add(latest);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles where resourcePrimKey = any &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param latest the latest
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_L(long[] resourcePrimKeies, boolean latest)
		throws SystemException {
		return findByR_L(resourcePrimKeies, latest, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where resourcePrimKey = any &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_L(long[] resourcePrimKeies, boolean latest,
		int start, int end) throws SystemException {
		return findByR_L(resourcePrimKeies, latest, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where resourcePrimKey = any &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_L(long[] resourcePrimKeies, boolean latest,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_R_L;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderArgs = new Object[] {
					StringUtil.merge(resourcePrimKeies), latest
				};
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(resourcePrimKeies), latest,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if (!ArrayUtil.contains(resourcePrimKeies,
							kbArticle.getResourcePrimKey()) ||
						(latest != kbArticle.getLatest())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

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

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_R_L_LATEST_5);

			conjunctionable = true;

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
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

				qPos.add(latest);

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns all the k b articles where resourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param main the main
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_M(long resourcePrimKey, boolean main)
		throws SystemException {
		return findByR_M(resourcePrimKey, main, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where resourcePrimKey = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_M(long resourcePrimKey, boolean main,
		int start, int end) throws SystemException {
		return findByR_M(resourcePrimKey, main, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where resourcePrimKey = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_M(long resourcePrimKey, boolean main,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_M;
			finderArgs = new Object[] { resourcePrimKey, main };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_R_M;
			finderArgs = new Object[] {
					resourcePrimKey, main,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((resourcePrimKey != kbArticle.getResourcePrimKey()) ||
						(main != kbArticle.getMain())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_M_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_M_MAIN_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(main);

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first k b article in the ordered set where resourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByR_M_First(long resourcePrimKey, boolean main,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByR_M_First(resourcePrimKey, main,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourcePrimKey=");
		msg.append(resourcePrimKey);

		msg.append(", main=");
		msg.append(main);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where resourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByR_M_First(long resourcePrimKey, boolean main,
		OrderByComparator orderByComparator) throws SystemException {
		List<KBArticle> list = findByR_M(resourcePrimKey, main, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where resourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByR_M_Last(long resourcePrimKey, boolean main,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByR_M_Last(resourcePrimKey, main,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourcePrimKey=");
		msg.append(resourcePrimKey);

		msg.append(", main=");
		msg.append(main);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where resourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByR_M_Last(long resourcePrimKey, boolean main,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByR_M(resourcePrimKey, main);

		List<KBArticle> list = findByR_M(resourcePrimKey, main, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param resourcePrimKey the resource prim key
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle[] findByR_M_PrevAndNext(long kbArticleId,
		long resourcePrimKey, boolean main, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByR_M_PrevAndNext(session, kbArticle,
					resourcePrimKey, main, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByR_M_PrevAndNext(session, kbArticle,
					resourcePrimKey, main, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle getByR_M_PrevAndNext(Session session,
		KBArticle kbArticle, long resourcePrimKey, boolean main,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_R_M_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_M_MAIN_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resourcePrimKey);

		qPos.add(main);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles where resourcePrimKey = any &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param main the main
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_M(long[] resourcePrimKeies, boolean main)
		throws SystemException {
		return findByR_M(resourcePrimKeies, main, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where resourcePrimKey = any &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_M(long[] resourcePrimKeies, boolean main,
		int start, int end) throws SystemException {
		return findByR_M(resourcePrimKeies, main, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where resourcePrimKey = any &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_M(long[] resourcePrimKeies, boolean main,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_R_M;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderArgs = new Object[] { StringUtil.merge(resourcePrimKeies), main };
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(resourcePrimKeies), main,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if (!ArrayUtil.contains(resourcePrimKeies,
							kbArticle.getResourcePrimKey()) ||
						(main != kbArticle.getMain())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			boolean conjunctionable = false;

			if ((resourcePrimKeies == null) || (resourcePrimKeies.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < resourcePrimKeies.length; i++) {
					query.append(_FINDER_COLUMN_R_M_RESOURCEPRIMKEY_5);

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

			query.append(_FINDER_COLUMN_R_M_MAIN_5);

			conjunctionable = true;

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
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

				qPos.add(main);

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns all the k b articles where resourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param status the status
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_S(long resourcePrimKey, int status)
		throws SystemException {
		return findByR_S(resourcePrimKey, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where resourcePrimKey = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_S(long resourcePrimKey, int status,
		int start, int end) throws SystemException {
		return findByR_S(resourcePrimKey, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where resourcePrimKey = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_S(long resourcePrimKey, int status,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_S;
			finderArgs = new Object[] { resourcePrimKey, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_R_S;
			finderArgs = new Object[] {
					resourcePrimKey, status,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((resourcePrimKey != kbArticle.getResourcePrimKey()) ||
						(status != kbArticle.getStatus())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_S_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(status);

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first k b article in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByR_S_First(long resourcePrimKey, int status,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByR_S_First(resourcePrimKey, status,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourcePrimKey=");
		msg.append(resourcePrimKey);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByR_S_First(long resourcePrimKey, int status,
		OrderByComparator orderByComparator) throws SystemException {
		List<KBArticle> list = findByR_S(resourcePrimKey, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByR_S_Last(long resourcePrimKey, int status,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByR_S_Last(resourcePrimKey, status,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourcePrimKey=");
		msg.append(resourcePrimKey);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByR_S_Last(long resourcePrimKey, int status,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByR_S(resourcePrimKey, status);

		List<KBArticle> list = findByR_S(resourcePrimKey, status, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param resourcePrimKey the resource prim key
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle[] findByR_S_PrevAndNext(long kbArticleId,
		long resourcePrimKey, int status, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByR_S_PrevAndNext(session, kbArticle,
					resourcePrimKey, status, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByR_S_PrevAndNext(session, kbArticle,
					resourcePrimKey, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle getByR_S_PrevAndNext(Session session,
		KBArticle kbArticle, long resourcePrimKey, int status,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_R_S_RESOURCEPRIMKEY_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resourcePrimKey);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles where resourcePrimKey = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param status the status
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_S(long[] resourcePrimKeies, int status)
		throws SystemException {
		return findByR_S(resourcePrimKeies, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where resourcePrimKey = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_S(long[] resourcePrimKeies, int status,
		int start, int end) throws SystemException {
		return findByR_S(resourcePrimKeies, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where resourcePrimKey = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_S(long[] resourcePrimKeies, int status,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_R_S;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderArgs = new Object[] {
					StringUtil.merge(resourcePrimKeies), status
				};
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(resourcePrimKeies), status,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if (!ArrayUtil.contains(resourcePrimKeies,
							kbArticle.getResourcePrimKey()) ||
						(status != kbArticle.getStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			boolean conjunctionable = false;

			if ((resourcePrimKeies == null) || (resourcePrimKeies.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < resourcePrimKeies.length; i++) {
					query.append(_FINDER_COLUMN_R_S_RESOURCEPRIMKEY_5);

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

			query.append(_FINDER_COLUMN_R_S_STATUS_5);

			conjunctionable = true;

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
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

				qPos.add(status);

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns all the k b articles where groupId = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param latest the latest
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_L(long groupId, boolean latest)
		throws SystemException {
		return findByG_L(groupId, latest, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the k b articles where groupId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_L(long groupId, boolean latest, int start,
		int end) throws SystemException {
		return findByG_L(groupId, latest, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where groupId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_L(long groupId, boolean latest, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_L;
			finderArgs = new Object[] { groupId, latest };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_L;
			finderArgs = new Object[] {
					groupId, latest,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((groupId != kbArticle.getGroupId()) ||
						(latest != kbArticle.getLatest())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_L_GROUPID_2);

			query.append(_FINDER_COLUMN_G_L_LATEST_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(latest);

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first k b article in the ordered set where groupId = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByG_L_First(long groupId, boolean latest,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByG_L_First(groupId, latest,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", latest=");
		msg.append(latest);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where groupId = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByG_L_First(long groupId, boolean latest,
		OrderByComparator orderByComparator) throws SystemException {
		List<KBArticle> list = findByG_L(groupId, latest, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByG_L_Last(long groupId, boolean latest,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByG_L_Last(groupId, latest, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", latest=");
		msg.append(latest);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByG_L_Last(long groupId, boolean latest,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByG_L(groupId, latest);

		List<KBArticle> list = findByG_L(groupId, latest, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and latest = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle[] findByG_L_PrevAndNext(long kbArticleId, long groupId,
		boolean latest, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByG_L_PrevAndNext(session, kbArticle, groupId,
					latest, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByG_L_PrevAndNext(session, kbArticle, groupId,
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

	protected KBArticle getByG_L_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, boolean latest,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_L_GROUPID_2);

		query.append(_FINDER_COLUMN_G_L_LATEST_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(latest);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where groupId = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param latest the latest
	 * @return the matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_L(long groupId, boolean latest)
		throws SystemException {
		return filterFindByG_L(groupId, latest, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_L(long groupId, boolean latest,
		int start, int end) throws SystemException {
		return filterFindByG_L(groupId, latest, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_L(long groupId, boolean latest,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
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
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_L_GROUPID_2);

		query.append(_FINDER_COLUMN_G_L_LATEST_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(latest);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and latest = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle[] filterFindByG_L_PrevAndNext(long kbArticleId,
		long groupId, boolean latest, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_L_PrevAndNext(kbArticleId, groupId, latest,
				orderByComparator);
		}

		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = filterGetByG_L_PrevAndNext(session, kbArticle, groupId,
					latest, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = filterGetByG_L_PrevAndNext(session, kbArticle, groupId,
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

	protected KBArticle filterGetByG_L_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, boolean latest,
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
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_L_GROUPID_2);

		query.append(_FINDER_COLUMN_G_L_LATEST_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(latest);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles where groupId = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param main the main
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_M(long groupId, boolean main)
		throws SystemException {
		return findByG_M(groupId, main, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the k b articles where groupId = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_M(long groupId, boolean main, int start,
		int end) throws SystemException {
		return findByG_M(groupId, main, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where groupId = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_M(long groupId, boolean main, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_M;
			finderArgs = new Object[] { groupId, main };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_M;
			finderArgs = new Object[] {
					groupId, main,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((groupId != kbArticle.getGroupId()) ||
						(main != kbArticle.getMain())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_M_GROUPID_2);

			query.append(_FINDER_COLUMN_G_M_MAIN_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(main);

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first k b article in the ordered set where groupId = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByG_M_First(long groupId, boolean main,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByG_M_First(groupId, main, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", main=");
		msg.append(main);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where groupId = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByG_M_First(long groupId, boolean main,
		OrderByComparator orderByComparator) throws SystemException {
		List<KBArticle> list = findByG_M(groupId, main, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByG_M_Last(long groupId, boolean main,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByG_M_Last(groupId, main, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", main=");
		msg.append(main);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByG_M_Last(long groupId, boolean main,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByG_M(groupId, main);

		List<KBArticle> list = findByG_M(groupId, main, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and main = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle[] findByG_M_PrevAndNext(long kbArticleId, long groupId,
		boolean main, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByG_M_PrevAndNext(session, kbArticle, groupId, main,
					orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByG_M_PrevAndNext(session, kbArticle, groupId, main,
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

	protected KBArticle getByG_M_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, boolean main,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_M_GROUPID_2);

		query.append(_FINDER_COLUMN_G_M_MAIN_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(main);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where groupId = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param main the main
	 * @return the matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_M(long groupId, boolean main)
		throws SystemException {
		return filterFindByG_M(groupId, main, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_M(long groupId, boolean main,
		int start, int end) throws SystemException {
		return filterFindByG_M(groupId, main, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_M(long groupId, boolean main,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_M(groupId, main, start, end, orderByComparator);
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
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_M_GROUPID_2);

		query.append(_FINDER_COLUMN_G_M_MAIN_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(main);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and main = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle[] filterFindByG_M_PrevAndNext(long kbArticleId,
		long groupId, boolean main, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_M_PrevAndNext(kbArticleId, groupId, main,
				orderByComparator);
		}

		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = filterGetByG_M_PrevAndNext(session, kbArticle, groupId,
					main, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = filterGetByG_M_PrevAndNext(session, kbArticle, groupId,
					main, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle filterGetByG_M_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, boolean main,
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
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_M_GROUPID_2);

		query.append(_FINDER_COLUMN_G_M_MAIN_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(main);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_S(long groupId, int status)
		throws SystemException {
		return findByG_S(groupId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the k b articles where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_S(long groupId, int status, int start,
		int end) throws SystemException {
		return findByG_S(groupId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_S(long groupId, int status, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S;
			finderArgs = new Object[] { groupId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_S;
			finderArgs = new Object[] {
					groupId, status,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((groupId != kbArticle.getGroupId()) ||
						(status != kbArticle.getStatus())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(status);

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first k b article in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByG_S_First(long groupId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByG_S_First(groupId, status,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByG_S_First(long groupId, int status,
		OrderByComparator orderByComparator) throws SystemException {
		List<KBArticle> list = findByG_S(groupId, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByG_S_Last(long groupId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByG_S_Last(groupId, status, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByG_S_Last(long groupId, int status,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByG_S(groupId, status);

		List<KBArticle> list = findByG_S(groupId, status, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle[] findByG_S_PrevAndNext(long kbArticleId, long groupId,
		int status, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByG_S_PrevAndNext(session, kbArticle, groupId,
					status, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByG_S_PrevAndNext(session, kbArticle, groupId,
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

	protected KBArticle getByG_S_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, int status,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_S_STATUS_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_S(long groupId, int status)
		throws SystemException {
		return filterFindByG_S(groupId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_S(long groupId, int status, int start,
		int end) throws SystemException {
		return filterFindByG_S(groupId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_S(long groupId, int status, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_S(groupId, status, start, end, orderByComparator);
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
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(status);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and status = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle[] filterFindByG_S_PrevAndNext(long kbArticleId,
		long groupId, int status, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_S_PrevAndNext(kbArticleId, groupId, status,
				orderByComparator);
		}

		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = filterGetByG_S_PrevAndNext(session, kbArticle, groupId,
					status, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = filterGetByG_S_PrevAndNext(session, kbArticle, groupId,
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

	protected KBArticle filterGetByG_S_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, int status,
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
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles where companyId = &#63; and latest = &#63;.
	 *
	 * @param companyId the company ID
	 * @param latest the latest
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByC_L(long companyId, boolean latest)
		throws SystemException {
		return findByC_L(companyId, latest, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where companyId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByC_L(long companyId, boolean latest, int start,
		int end) throws SystemException {
		return findByC_L(companyId, latest, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where companyId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByC_L(long companyId, boolean latest, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_L;
			finderArgs = new Object[] { companyId, latest };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_L;
			finderArgs = new Object[] {
					companyId, latest,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((companyId != kbArticle.getCompanyId()) ||
						(latest != kbArticle.getLatest())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_C_L_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_L_LATEST_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(latest);

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first k b article in the ordered set where companyId = &#63; and latest = &#63;.
	 *
	 * @param companyId the company ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByC_L_First(long companyId, boolean latest,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByC_L_First(companyId, latest,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", latest=");
		msg.append(latest);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where companyId = &#63; and latest = &#63;.
	 *
	 * @param companyId the company ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByC_L_First(long companyId, boolean latest,
		OrderByComparator orderByComparator) throws SystemException {
		List<KBArticle> list = findByC_L(companyId, latest, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where companyId = &#63; and latest = &#63;.
	 *
	 * @param companyId the company ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByC_L_Last(long companyId, boolean latest,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByC_L_Last(companyId, latest,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", latest=");
		msg.append(latest);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where companyId = &#63; and latest = &#63;.
	 *
	 * @param companyId the company ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByC_L_Last(long companyId, boolean latest,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByC_L(companyId, latest);

		List<KBArticle> list = findByC_L(companyId, latest, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where companyId = &#63; and latest = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param companyId the company ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle[] findByC_L_PrevAndNext(long kbArticleId, long companyId,
		boolean latest, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByC_L_PrevAndNext(session, kbArticle, companyId,
					latest, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByC_L_PrevAndNext(session, kbArticle, companyId,
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

	protected KBArticle getByC_L_PrevAndNext(Session session,
		KBArticle kbArticle, long companyId, boolean latest,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_C_L_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_L_LATEST_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(latest);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles where companyId = &#63; and main = &#63;.
	 *
	 * @param companyId the company ID
	 * @param main the main
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByC_M(long companyId, boolean main)
		throws SystemException {
		return findByC_M(companyId, main, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the k b articles where companyId = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByC_M(long companyId, boolean main, int start,
		int end) throws SystemException {
		return findByC_M(companyId, main, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where companyId = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByC_M(long companyId, boolean main, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_M;
			finderArgs = new Object[] { companyId, main };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_M;
			finderArgs = new Object[] {
					companyId, main,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((companyId != kbArticle.getCompanyId()) ||
						(main != kbArticle.getMain())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_C_M_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_M_MAIN_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(main);

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first k b article in the ordered set where companyId = &#63; and main = &#63;.
	 *
	 * @param companyId the company ID
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByC_M_First(long companyId, boolean main,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByC_M_First(companyId, main,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", main=");
		msg.append(main);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where companyId = &#63; and main = &#63;.
	 *
	 * @param companyId the company ID
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByC_M_First(long companyId, boolean main,
		OrderByComparator orderByComparator) throws SystemException {
		List<KBArticle> list = findByC_M(companyId, main, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where companyId = &#63; and main = &#63;.
	 *
	 * @param companyId the company ID
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByC_M_Last(long companyId, boolean main,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByC_M_Last(companyId, main, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", main=");
		msg.append(main);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where companyId = &#63; and main = &#63;.
	 *
	 * @param companyId the company ID
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByC_M_Last(long companyId, boolean main,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByC_M(companyId, main);

		List<KBArticle> list = findByC_M(companyId, main, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where companyId = &#63; and main = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param companyId the company ID
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle[] findByC_M_PrevAndNext(long kbArticleId, long companyId,
		boolean main, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByC_M_PrevAndNext(session, kbArticle, companyId,
					main, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByC_M_PrevAndNext(session, kbArticle, companyId,
					main, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle getByC_M_PrevAndNext(Session session,
		KBArticle kbArticle, long companyId, boolean main,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_C_M_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_M_MAIN_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(main);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles where companyId = &#63; and status = &#63;.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByC_S(long companyId, int status)
		throws SystemException {
		return findByC_S(companyId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where companyId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByC_S(long companyId, int status, int start,
		int end) throws SystemException {
		return findByC_S(companyId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where companyId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByC_S(long companyId, int status, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S;
			finderArgs = new Object[] { companyId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_S;
			finderArgs = new Object[] {
					companyId, status,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((companyId != kbArticle.getCompanyId()) ||
						(status != kbArticle.getStatus())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_C_S_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(status);

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first k b article in the ordered set where companyId = &#63; and status = &#63;.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByC_S_First(long companyId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByC_S_First(companyId, status,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where companyId = &#63; and status = &#63;.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByC_S_First(long companyId, int status,
		OrderByComparator orderByComparator) throws SystemException {
		List<KBArticle> list = findByC_S(companyId, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where companyId = &#63; and status = &#63;.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByC_S_Last(long companyId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByC_S_Last(companyId, status,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where companyId = &#63; and status = &#63;.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByC_S_Last(long companyId, int status,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByC_S(companyId, status);

		List<KBArticle> list = findByC_S(companyId, status, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where companyId = &#63; and status = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param companyId the company ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle[] findByC_S_PrevAndNext(long kbArticleId, long companyId,
		int status, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByC_S_PrevAndNext(session, kbArticle, companyId,
					status, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByC_S_PrevAndNext(session, kbArticle, companyId,
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

	protected KBArticle getByC_S_PrevAndNext(Session session,
		KBArticle kbArticle, long companyId, int status,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_C_S_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_S_STATUS_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles where parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByP_L(long parentResourcePrimKey, boolean latest)
		throws SystemException {
		return findByP_L(parentResourcePrimKey, latest, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByP_L(long parentResourcePrimKey,
		boolean latest, int start, int end) throws SystemException {
		return findByP_L(parentResourcePrimKey, latest, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByP_L(long parentResourcePrimKey,
		boolean latest, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_L;
			finderArgs = new Object[] { parentResourcePrimKey, latest };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_P_L;
			finderArgs = new Object[] {
					parentResourcePrimKey, latest,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((parentResourcePrimKey != kbArticle.getParentResourcePrimKey()) ||
						(latest != kbArticle.getLatest())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_P_L_PARENTRESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_P_L_LATEST_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentResourcePrimKey);

				qPos.add(latest);

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first k b article in the ordered set where parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByP_L_First(long parentResourcePrimKey,
		boolean latest, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByP_L_First(parentResourcePrimKey, latest,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentResourcePrimKey=");
		msg.append(parentResourcePrimKey);

		msg.append(", latest=");
		msg.append(latest);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByP_L_First(long parentResourcePrimKey,
		boolean latest, OrderByComparator orderByComparator)
		throws SystemException {
		List<KBArticle> list = findByP_L(parentResourcePrimKey, latest, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByP_L_Last(long parentResourcePrimKey, boolean latest,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByP_L_Last(parentResourcePrimKey, latest,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentResourcePrimKey=");
		msg.append(parentResourcePrimKey);

		msg.append(", latest=");
		msg.append(latest);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByP_L_Last(long parentResourcePrimKey,
		boolean latest, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByP_L(parentResourcePrimKey, latest);

		List<KBArticle> list = findByP_L(parentResourcePrimKey, latest,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle[] findByP_L_PrevAndNext(long kbArticleId,
		long parentResourcePrimKey, boolean latest,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByP_L_PrevAndNext(session, kbArticle,
					parentResourcePrimKey, latest, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByP_L_PrevAndNext(session, kbArticle,
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

	protected KBArticle getByP_L_PrevAndNext(Session session,
		KBArticle kbArticle, long parentResourcePrimKey, boolean latest,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_P_L_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_P_L_LATEST_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(parentResourcePrimKey);

		qPos.add(latest);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles where parentResourcePrimKey = any &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param latest the latest
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByP_L(long[] parentResourcePrimKeies,
		boolean latest) throws SystemException {
		return findByP_L(parentResourcePrimKeies, latest, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where parentResourcePrimKey = any &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByP_L(long[] parentResourcePrimKeies,
		boolean latest, int start, int end) throws SystemException {
		return findByP_L(parentResourcePrimKeies, latest, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where parentResourcePrimKey = any &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByP_L(long[] parentResourcePrimKeies,
		boolean latest, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_P_L;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderArgs = new Object[] {
					StringUtil.merge(parentResourcePrimKeies), latest
				};
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(parentResourcePrimKeies), latest,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if (!ArrayUtil.contains(parentResourcePrimKeies,
							kbArticle.getParentResourcePrimKey()) ||
						(latest != kbArticle.getLatest())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			boolean conjunctionable = false;

			if ((parentResourcePrimKeies == null) ||
					(parentResourcePrimKeies.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < parentResourcePrimKeies.length; i++) {
					query.append(_FINDER_COLUMN_P_L_PARENTRESOURCEPRIMKEY_5);

					if ((i + 1) < parentResourcePrimKeies.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_P_L_LATEST_5);

			conjunctionable = true;

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (parentResourcePrimKeies != null) {
					qPos.add(parentResourcePrimKeies);
				}

				qPos.add(latest);

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns all the k b articles where parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByP_M(long parentResourcePrimKey, boolean main)
		throws SystemException {
		return findByP_M(parentResourcePrimKey, main, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByP_M(long parentResourcePrimKey, boolean main,
		int start, int end) throws SystemException {
		return findByP_M(parentResourcePrimKey, main, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByP_M(long parentResourcePrimKey, boolean main,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_M;
			finderArgs = new Object[] { parentResourcePrimKey, main };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_P_M;
			finderArgs = new Object[] {
					parentResourcePrimKey, main,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((parentResourcePrimKey != kbArticle.getParentResourcePrimKey()) ||
						(main != kbArticle.getMain())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_P_M_PARENTRESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_P_M_MAIN_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentResourcePrimKey);

				qPos.add(main);

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first k b article in the ordered set where parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByP_M_First(long parentResourcePrimKey, boolean main,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByP_M_First(parentResourcePrimKey, main,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentResourcePrimKey=");
		msg.append(parentResourcePrimKey);

		msg.append(", main=");
		msg.append(main);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByP_M_First(long parentResourcePrimKey, boolean main,
		OrderByComparator orderByComparator) throws SystemException {
		List<KBArticle> list = findByP_M(parentResourcePrimKey, main, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByP_M_Last(long parentResourcePrimKey, boolean main,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByP_M_Last(parentResourcePrimKey, main,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentResourcePrimKey=");
		msg.append(parentResourcePrimKey);

		msg.append(", main=");
		msg.append(main);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByP_M_Last(long parentResourcePrimKey, boolean main,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByP_M(parentResourcePrimKey, main);

		List<KBArticle> list = findByP_M(parentResourcePrimKey, main,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle[] findByP_M_PrevAndNext(long kbArticleId,
		long parentResourcePrimKey, boolean main,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByP_M_PrevAndNext(session, kbArticle,
					parentResourcePrimKey, main, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByP_M_PrevAndNext(session, kbArticle,
					parentResourcePrimKey, main, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle getByP_M_PrevAndNext(Session session,
		KBArticle kbArticle, long parentResourcePrimKey, boolean main,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_P_M_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_P_M_MAIN_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(parentResourcePrimKey);

		qPos.add(main);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles where parentResourcePrimKey = any &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param main the main
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByP_M(long[] parentResourcePrimKeies,
		boolean main) throws SystemException {
		return findByP_M(parentResourcePrimKeies, main, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where parentResourcePrimKey = any &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByP_M(long[] parentResourcePrimKeies,
		boolean main, int start, int end) throws SystemException {
		return findByP_M(parentResourcePrimKeies, main, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where parentResourcePrimKey = any &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByP_M(long[] parentResourcePrimKeies,
		boolean main, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_P_M;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderArgs = new Object[] {
					StringUtil.merge(parentResourcePrimKeies), main
				};
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(parentResourcePrimKeies), main,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if (!ArrayUtil.contains(parentResourcePrimKeies,
							kbArticle.getParentResourcePrimKey()) ||
						(main != kbArticle.getMain())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			boolean conjunctionable = false;

			if ((parentResourcePrimKeies == null) ||
					(parentResourcePrimKeies.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < parentResourcePrimKeies.length; i++) {
					query.append(_FINDER_COLUMN_P_M_PARENTRESOURCEPRIMKEY_5);

					if ((i + 1) < parentResourcePrimKeies.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_P_M_MAIN_5);

			conjunctionable = true;

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (parentResourcePrimKeies != null) {
					qPos.add(parentResourcePrimKeies);
				}

				qPos.add(main);

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns all the k b articles where parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByP_S(long parentResourcePrimKey, int status)
		throws SystemException {
		return findByP_S(parentResourcePrimKey, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByP_S(long parentResourcePrimKey, int status,
		int start, int end) throws SystemException {
		return findByP_S(parentResourcePrimKey, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByP_S(long parentResourcePrimKey, int status,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_S;
			finderArgs = new Object[] { parentResourcePrimKey, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_P_S;
			finderArgs = new Object[] {
					parentResourcePrimKey, status,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((parentResourcePrimKey != kbArticle.getParentResourcePrimKey()) ||
						(status != kbArticle.getStatus())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_P_S_PARENTRESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_P_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentResourcePrimKey);

				qPos.add(status);

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first k b article in the ordered set where parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByP_S_First(long parentResourcePrimKey, int status,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByP_S_First(parentResourcePrimKey, status,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentResourcePrimKey=");
		msg.append(parentResourcePrimKey);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByP_S_First(long parentResourcePrimKey, int status,
		OrderByComparator orderByComparator) throws SystemException {
		List<KBArticle> list = findByP_S(parentResourcePrimKey, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByP_S_Last(long parentResourcePrimKey, int status,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByP_S_Last(parentResourcePrimKey, status,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentResourcePrimKey=");
		msg.append(parentResourcePrimKey);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByP_S_Last(long parentResourcePrimKey, int status,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByP_S(parentResourcePrimKey, status);

		List<KBArticle> list = findByP_S(parentResourcePrimKey, status,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle[] findByP_S_PrevAndNext(long kbArticleId,
		long parentResourcePrimKey, int status,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByP_S_PrevAndNext(session, kbArticle,
					parentResourcePrimKey, status, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByP_S_PrevAndNext(session, kbArticle,
					parentResourcePrimKey, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle getByP_S_PrevAndNext(Session session,
		KBArticle kbArticle, long parentResourcePrimKey, int status,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_P_S_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_P_S_STATUS_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(parentResourcePrimKey);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles where parentResourcePrimKey = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param status the status
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByP_S(long[] parentResourcePrimKeies, int status)
		throws SystemException {
		return findByP_S(parentResourcePrimKeies, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where parentResourcePrimKey = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByP_S(long[] parentResourcePrimKeies,
		int status, int start, int end) throws SystemException {
		return findByP_S(parentResourcePrimKeies, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where parentResourcePrimKey = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByP_S(long[] parentResourcePrimKeies,
		int status, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_P_S;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderArgs = new Object[] {
					StringUtil.merge(parentResourcePrimKeies), status
				};
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(parentResourcePrimKeies), status,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if (!ArrayUtil.contains(parentResourcePrimKeies,
							kbArticle.getParentResourcePrimKey()) ||
						(status != kbArticle.getStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			boolean conjunctionable = false;

			if ((parentResourcePrimKeies == null) ||
					(parentResourcePrimKeies.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < parentResourcePrimKeies.length; i++) {
					query.append(_FINDER_COLUMN_P_S_PARENTRESOURCEPRIMKEY_5);

					if ((i + 1) < parentResourcePrimKeies.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_P_S_STATUS_5);

			conjunctionable = true;

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (parentResourcePrimKeies != null) {
					qPos.add(parentResourcePrimKeies);
				}

				qPos.add(status);

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param latest the latest
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_G_L(long resourcePrimKey, long groupId,
		boolean latest) throws SystemException {
		return findByR_G_L(resourcePrimKey, groupId, latest, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_G_L(long resourcePrimKey, long groupId,
		boolean latest, int start, int end) throws SystemException {
		return findByR_G_L(resourcePrimKey, groupId, latest, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_G_L(long resourcePrimKey, long groupId,
		boolean latest, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G_L;
			finderArgs = new Object[] { resourcePrimKey, groupId, latest };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_R_G_L;
			finderArgs = new Object[] {
					resourcePrimKey, groupId, latest,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((resourcePrimKey != kbArticle.getResourcePrimKey()) ||
						(groupId != kbArticle.getGroupId()) ||
						(latest != kbArticle.getLatest())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_G_L_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_G_L_GROUPID_2);

			query.append(_FINDER_COLUMN_R_G_L_LATEST_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(groupId);

				qPos.add(latest);

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByR_G_L_First(long resourcePrimKey, long groupId,
		boolean latest, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByR_G_L_First(resourcePrimKey, groupId,
				latest, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourcePrimKey=");
		msg.append(resourcePrimKey);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", latest=");
		msg.append(latest);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByR_G_L_First(long resourcePrimKey, long groupId,
		boolean latest, OrderByComparator orderByComparator)
		throws SystemException {
		List<KBArticle> list = findByR_G_L(resourcePrimKey, groupId, latest, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByR_G_L_Last(long resourcePrimKey, long groupId,
		boolean latest, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByR_G_L_Last(resourcePrimKey, groupId,
				latest, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourcePrimKey=");
		msg.append(resourcePrimKey);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", latest=");
		msg.append(latest);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByR_G_L_Last(long resourcePrimKey, long groupId,
		boolean latest, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByR_G_L(resourcePrimKey, groupId, latest);

		List<KBArticle> list = findByR_G_L(resourcePrimKey, groupId, latest,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle[] findByR_G_L_PrevAndNext(long kbArticleId,
		long resourcePrimKey, long groupId, boolean latest,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByR_G_L_PrevAndNext(session, kbArticle,
					resourcePrimKey, groupId, latest, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByR_G_L_PrevAndNext(session, kbArticle,
					resourcePrimKey, groupId, latest, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle getByR_G_L_PrevAndNext(Session session,
		KBArticle kbArticle, long resourcePrimKey, long groupId,
		boolean latest, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_R_G_L_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_L_GROUPID_2);

		query.append(_FINDER_COLUMN_R_G_L_LATEST_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resourcePrimKey);

		qPos.add(groupId);

		qPos.add(latest);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param latest the latest
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_G_L(long[] resourcePrimKeies, long groupId,
		boolean latest) throws SystemException {
		return findByR_G_L(resourcePrimKeies, groupId, latest,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_G_L(long[] resourcePrimKeies, long groupId,
		boolean latest, int start, int end) throws SystemException {
		return findByR_G_L(resourcePrimKeies, groupId, latest, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_G_L(long[] resourcePrimKeies, long groupId,
		boolean latest, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_R_G_L;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderArgs = new Object[] {
					StringUtil.merge(resourcePrimKeies), groupId, latest
				};
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(resourcePrimKeies), groupId, latest,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if (!ArrayUtil.contains(resourcePrimKeies,
							kbArticle.getResourcePrimKey()) ||
						(groupId != kbArticle.getGroupId()) ||
						(latest != kbArticle.getLatest())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			boolean conjunctionable = false;

			if ((resourcePrimKeies == null) || (resourcePrimKeies.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < resourcePrimKeies.length; i++) {
					query.append(_FINDER_COLUMN_R_G_L_RESOURCEPRIMKEY_5);

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

			query.append(_FINDER_COLUMN_R_G_L_GROUPID_5);

			conjunctionable = true;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_R_G_L_LATEST_5);

			conjunctionable = true;

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
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

				qPos.add(latest);

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns all the k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param latest the latest
	 * @return the matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByR_G_L(long resourcePrimKey,
		long groupId, boolean latest) throws SystemException {
		return filterFindByR_G_L(resourcePrimKey, groupId, latest,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByR_G_L(long resourcePrimKey,
		long groupId, boolean latest, int start, int end)
		throws SystemException {
		return filterFindByR_G_L(resourcePrimKey, groupId, latest, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permissions to view where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByR_G_L(long resourcePrimKey,
		long groupId, boolean latest, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByR_G_L(resourcePrimKey, groupId, latest, start, end,
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
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_R_G_L_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_L_GROUPID_2);

		query.append(_FINDER_COLUMN_R_G_L_LATEST_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(resourcePrimKey);

			qPos.add(groupId);

			qPos.add(latest);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle[] filterFindByR_G_L_PrevAndNext(long kbArticleId,
		long resourcePrimKey, long groupId, boolean latest,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByR_G_L_PrevAndNext(kbArticleId, resourcePrimKey,
				groupId, latest, orderByComparator);
		}

		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = filterGetByR_G_L_PrevAndNext(session, kbArticle,
					resourcePrimKey, groupId, latest, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = filterGetByR_G_L_PrevAndNext(session, kbArticle,
					resourcePrimKey, groupId, latest, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle filterGetByR_G_L_PrevAndNext(Session session,
		KBArticle kbArticle, long resourcePrimKey, long groupId,
		boolean latest, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_R_G_L_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_L_GROUPID_2);

		query.append(_FINDER_COLUMN_R_G_L_LATEST_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resourcePrimKey);

		qPos.add(groupId);

		qPos.add(latest);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param latest the latest
	 * @return the matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByR_G_L(long[] resourcePrimKeies,
		long groupId, boolean latest) throws SystemException {
		return filterFindByR_G_L(resourcePrimKeies, groupId, latest,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByR_G_L(long[] resourcePrimKeies,
		long groupId, boolean latest, int start, int end)
		throws SystemException {
		return filterFindByR_G_L(resourcePrimKeies, groupId, latest, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByR_G_L(long[] resourcePrimKeies,
		long groupId, boolean latest, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByR_G_L(resourcePrimKeies, groupId, latest, start, end,
				orderByComparator);
		}

		StringBundler query = new StringBundler();

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean conjunctionable = false;

		if ((resourcePrimKeies == null) || (resourcePrimKeies.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < resourcePrimKeies.length; i++) {
				query.append(_FINDER_COLUMN_R_G_L_RESOURCEPRIMKEY_5);

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

		query.append(_FINDER_COLUMN_R_G_L_GROUPID_5);

		conjunctionable = true;

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_R_G_L_LATEST_5);

		conjunctionable = true;

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			if (resourcePrimKeies != null) {
				qPos.add(resourcePrimKeies);
			}

			qPos.add(groupId);

			qPos.add(latest);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param main the main
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_G_M(long resourcePrimKey, long groupId,
		boolean main) throws SystemException {
		return findByR_G_M(resourcePrimKey, groupId, main, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_G_M(long resourcePrimKey, long groupId,
		boolean main, int start, int end) throws SystemException {
		return findByR_G_M(resourcePrimKey, groupId, main, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_G_M(long resourcePrimKey, long groupId,
		boolean main, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G_M;
			finderArgs = new Object[] { resourcePrimKey, groupId, main };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_R_G_M;
			finderArgs = new Object[] {
					resourcePrimKey, groupId, main,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((resourcePrimKey != kbArticle.getResourcePrimKey()) ||
						(groupId != kbArticle.getGroupId()) ||
						(main != kbArticle.getMain())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_G_M_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_G_M_GROUPID_2);

			query.append(_FINDER_COLUMN_R_G_M_MAIN_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(groupId);

				qPos.add(main);

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByR_G_M_First(long resourcePrimKey, long groupId,
		boolean main, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByR_G_M_First(resourcePrimKey, groupId,
				main, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourcePrimKey=");
		msg.append(resourcePrimKey);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", main=");
		msg.append(main);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByR_G_M_First(long resourcePrimKey, long groupId,
		boolean main, OrderByComparator orderByComparator)
		throws SystemException {
		List<KBArticle> list = findByR_G_M(resourcePrimKey, groupId, main, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByR_G_M_Last(long resourcePrimKey, long groupId,
		boolean main, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByR_G_M_Last(resourcePrimKey, groupId, main,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourcePrimKey=");
		msg.append(resourcePrimKey);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", main=");
		msg.append(main);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByR_G_M_Last(long resourcePrimKey, long groupId,
		boolean main, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByR_G_M(resourcePrimKey, groupId, main);

		List<KBArticle> list = findByR_G_M(resourcePrimKey, groupId, main,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle[] findByR_G_M_PrevAndNext(long kbArticleId,
		long resourcePrimKey, long groupId, boolean main,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByR_G_M_PrevAndNext(session, kbArticle,
					resourcePrimKey, groupId, main, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByR_G_M_PrevAndNext(session, kbArticle,
					resourcePrimKey, groupId, main, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle getByR_G_M_PrevAndNext(Session session,
		KBArticle kbArticle, long resourcePrimKey, long groupId, boolean main,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_R_G_M_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_M_GROUPID_2);

		query.append(_FINDER_COLUMN_R_G_M_MAIN_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resourcePrimKey);

		qPos.add(groupId);

		qPos.add(main);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param main the main
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_G_M(long[] resourcePrimKeies, long groupId,
		boolean main) throws SystemException {
		return findByR_G_M(resourcePrimKeies, groupId, main, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_G_M(long[] resourcePrimKeies, long groupId,
		boolean main, int start, int end) throws SystemException {
		return findByR_G_M(resourcePrimKeies, groupId, main, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_G_M(long[] resourcePrimKeies, long groupId,
		boolean main, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_R_G_M;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderArgs = new Object[] {
					StringUtil.merge(resourcePrimKeies), groupId, main
				};
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(resourcePrimKeies), groupId, main,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if (!ArrayUtil.contains(resourcePrimKeies,
							kbArticle.getResourcePrimKey()) ||
						(groupId != kbArticle.getGroupId()) ||
						(main != kbArticle.getMain())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			boolean conjunctionable = false;

			if ((resourcePrimKeies == null) || (resourcePrimKeies.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < resourcePrimKeies.length; i++) {
					query.append(_FINDER_COLUMN_R_G_M_RESOURCEPRIMKEY_5);

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

			query.append(_FINDER_COLUMN_R_G_M_GROUPID_5);

			conjunctionable = true;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_R_G_M_MAIN_5);

			conjunctionable = true;

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
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

				qPos.add(main);

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns all the k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param main the main
	 * @return the matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByR_G_M(long resourcePrimKey,
		long groupId, boolean main) throws SystemException {
		return filterFindByR_G_M(resourcePrimKey, groupId, main,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByR_G_M(long resourcePrimKey,
		long groupId, boolean main, int start, int end)
		throws SystemException {
		return filterFindByR_G_M(resourcePrimKey, groupId, main, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permissions to view where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByR_G_M(long resourcePrimKey,
		long groupId, boolean main, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByR_G_M(resourcePrimKey, groupId, main, start, end,
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
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_R_G_M_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_M_GROUPID_2);

		query.append(_FINDER_COLUMN_R_G_M_MAIN_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(resourcePrimKey);

			qPos.add(groupId);

			qPos.add(main);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle[] filterFindByR_G_M_PrevAndNext(long kbArticleId,
		long resourcePrimKey, long groupId, boolean main,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByR_G_M_PrevAndNext(kbArticleId, resourcePrimKey,
				groupId, main, orderByComparator);
		}

		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = filterGetByR_G_M_PrevAndNext(session, kbArticle,
					resourcePrimKey, groupId, main, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = filterGetByR_G_M_PrevAndNext(session, kbArticle,
					resourcePrimKey, groupId, main, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle filterGetByR_G_M_PrevAndNext(Session session,
		KBArticle kbArticle, long resourcePrimKey, long groupId, boolean main,
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
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_R_G_M_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_M_GROUPID_2);

		query.append(_FINDER_COLUMN_R_G_M_MAIN_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resourcePrimKey);

		qPos.add(groupId);

		qPos.add(main);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param main the main
	 * @return the matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByR_G_M(long[] resourcePrimKeies,
		long groupId, boolean main) throws SystemException {
		return filterFindByR_G_M(resourcePrimKeies, groupId, main,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByR_G_M(long[] resourcePrimKeies,
		long groupId, boolean main, int start, int end)
		throws SystemException {
		return filterFindByR_G_M(resourcePrimKeies, groupId, main, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByR_G_M(long[] resourcePrimKeies,
		long groupId, boolean main, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByR_G_M(resourcePrimKeies, groupId, main, start, end,
				orderByComparator);
		}

		StringBundler query = new StringBundler();

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean conjunctionable = false;

		if ((resourcePrimKeies == null) || (resourcePrimKeies.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < resourcePrimKeies.length; i++) {
				query.append(_FINDER_COLUMN_R_G_M_RESOURCEPRIMKEY_5);

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

		query.append(_FINDER_COLUMN_R_G_M_GROUPID_5);

		conjunctionable = true;

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_R_G_M_MAIN_5);

		conjunctionable = true;

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			if (resourcePrimKeies != null) {
				qPos.add(resourcePrimKeies);
			}

			qPos.add(groupId);

			qPos.add(main);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param status the status
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_G_S(long resourcePrimKey, long groupId,
		int status) throws SystemException {
		return findByR_G_S(resourcePrimKey, groupId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_G_S(long resourcePrimKey, long groupId,
		int status, int start, int end) throws SystemException {
		return findByR_G_S(resourcePrimKey, groupId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_G_S(long resourcePrimKey, long groupId,
		int status, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G_S;
			finderArgs = new Object[] { resourcePrimKey, groupId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_R_G_S;
			finderArgs = new Object[] {
					resourcePrimKey, groupId, status,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((resourcePrimKey != kbArticle.getResourcePrimKey()) ||
						(groupId != kbArticle.getGroupId()) ||
						(status != kbArticle.getStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_G_S_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_G_S_GROUPID_2);

			query.append(_FINDER_COLUMN_R_G_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(groupId);

				qPos.add(status);

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByR_G_S_First(long resourcePrimKey, long groupId,
		int status, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByR_G_S_First(resourcePrimKey, groupId,
				status, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourcePrimKey=");
		msg.append(resourcePrimKey);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByR_G_S_First(long resourcePrimKey, long groupId,
		int status, OrderByComparator orderByComparator)
		throws SystemException {
		List<KBArticle> list = findByR_G_S(resourcePrimKey, groupId, status, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByR_G_S_Last(long resourcePrimKey, long groupId,
		int status, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByR_G_S_Last(resourcePrimKey, groupId,
				status, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourcePrimKey=");
		msg.append(resourcePrimKey);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByR_G_S_Last(long resourcePrimKey, long groupId,
		int status, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByR_G_S(resourcePrimKey, groupId, status);

		List<KBArticle> list = findByR_G_S(resourcePrimKey, groupId, status,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle[] findByR_G_S_PrevAndNext(long kbArticleId,
		long resourcePrimKey, long groupId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByR_G_S_PrevAndNext(session, kbArticle,
					resourcePrimKey, groupId, status, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByR_G_S_PrevAndNext(session, kbArticle,
					resourcePrimKey, groupId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle getByR_G_S_PrevAndNext(Session session,
		KBArticle kbArticle, long resourcePrimKey, long groupId, int status,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_R_G_S_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_S_GROUPID_2);

		query.append(_FINDER_COLUMN_R_G_S_STATUS_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resourcePrimKey);

		qPos.add(groupId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param status the status
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_G_S(long[] resourcePrimKeies, long groupId,
		int status) throws SystemException {
		return findByR_G_S(resourcePrimKeies, groupId, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_G_S(long[] resourcePrimKeies, long groupId,
		int status, int start, int end) throws SystemException {
		return findByR_G_S(resourcePrimKeies, groupId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByR_G_S(long[] resourcePrimKeies, long groupId,
		int status, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_R_G_S;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderArgs = new Object[] {
					StringUtil.merge(resourcePrimKeies), groupId, status
				};
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(resourcePrimKeies), groupId, status,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if (!ArrayUtil.contains(resourcePrimKeies,
							kbArticle.getResourcePrimKey()) ||
						(groupId != kbArticle.getGroupId()) ||
						(status != kbArticle.getStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			boolean conjunctionable = false;

			if ((resourcePrimKeies == null) || (resourcePrimKeies.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < resourcePrimKeies.length; i++) {
					query.append(_FINDER_COLUMN_R_G_S_RESOURCEPRIMKEY_5);

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

			query.append(_FINDER_COLUMN_R_G_S_GROUPID_5);

			conjunctionable = true;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_R_G_S_STATUS_5);

			conjunctionable = true;

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
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

				qPos.add(status);

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns all the k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param status the status
	 * @return the matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByR_G_S(long resourcePrimKey,
		long groupId, int status) throws SystemException {
		return filterFindByR_G_S(resourcePrimKey, groupId, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByR_G_S(long resourcePrimKey,
		long groupId, int status, int start, int end) throws SystemException {
		return filterFindByR_G_S(resourcePrimKey, groupId, status, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permissions to view where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByR_G_S(long resourcePrimKey,
		long groupId, int status, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByR_G_S(resourcePrimKey, groupId, status, start, end,
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
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_R_G_S_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_S_GROUPID_2);

		query.append(_FINDER_COLUMN_R_G_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(resourcePrimKey);

			qPos.add(groupId);

			qPos.add(status);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle[] filterFindByR_G_S_PrevAndNext(long kbArticleId,
		long resourcePrimKey, long groupId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByR_G_S_PrevAndNext(kbArticleId, resourcePrimKey,
				groupId, status, orderByComparator);
		}

		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = filterGetByR_G_S_PrevAndNext(session, kbArticle,
					resourcePrimKey, groupId, status, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = filterGetByR_G_S_PrevAndNext(session, kbArticle,
					resourcePrimKey, groupId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle filterGetByR_G_S_PrevAndNext(Session session,
		KBArticle kbArticle, long resourcePrimKey, long groupId, int status,
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
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_R_G_S_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_S_GROUPID_2);

		query.append(_FINDER_COLUMN_R_G_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resourcePrimKey);

		qPos.add(groupId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param status the status
	 * @return the matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByR_G_S(long[] resourcePrimKeies,
		long groupId, int status) throws SystemException {
		return filterFindByR_G_S(resourcePrimKeies, groupId, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByR_G_S(long[] resourcePrimKeies,
		long groupId, int status, int start, int end) throws SystemException {
		return filterFindByR_G_S(resourcePrimKeies, groupId, status, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByR_G_S(long[] resourcePrimKeies,
		long groupId, int status, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByR_G_S(resourcePrimKeies, groupId, status, start, end,
				orderByComparator);
		}

		StringBundler query = new StringBundler();

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean conjunctionable = false;

		if ((resourcePrimKeies == null) || (resourcePrimKeies.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < resourcePrimKeies.length; i++) {
				query.append(_FINDER_COLUMN_R_G_S_RESOURCEPRIMKEY_5);

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

		query.append(_FINDER_COLUMN_R_G_S_GROUPID_5);

		conjunctionable = true;

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_R_G_S_STATUS_5);

		conjunctionable = true;

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			if (resourcePrimKeies != null) {
				qPos.add(resourcePrimKeies);
			}

			qPos.add(groupId);

			qPos.add(status);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_P_L(long groupId,
		long parentResourcePrimKey, boolean latest) throws SystemException {
		return findByG_P_L(groupId, parentResourcePrimKey, latest,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_P_L(long groupId,
		long parentResourcePrimKey, boolean latest, int start, int end)
		throws SystemException {
		return findByG_P_L(groupId, parentResourcePrimKey, latest, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_P_L(long groupId,
		long parentResourcePrimKey, boolean latest, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_L;
			finderArgs = new Object[] { groupId, parentResourcePrimKey, latest };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_L;
			finderArgs = new Object[] {
					groupId, parentResourcePrimKey, latest,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((groupId != kbArticle.getGroupId()) ||
						(parentResourcePrimKey != kbArticle.getParentResourcePrimKey()) ||
						(latest != kbArticle.getLatest())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_P_L_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_L_PARENTRESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_G_P_L_LATEST_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
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

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByG_P_L_First(long groupId,
		long parentResourcePrimKey, boolean latest,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByG_P_L_First(groupId,
				parentResourcePrimKey, latest, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

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

	/**
	 * Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByG_P_L_First(long groupId,
		long parentResourcePrimKey, boolean latest,
		OrderByComparator orderByComparator) throws SystemException {
		List<KBArticle> list = findByG_P_L(groupId, parentResourcePrimKey,
				latest, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByG_P_L_Last(long groupId, long parentResourcePrimKey,
		boolean latest, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByG_P_L_Last(groupId, parentResourcePrimKey,
				latest, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

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

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByG_P_L_Last(long groupId,
		long parentResourcePrimKey, boolean latest,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByG_P_L(groupId, parentResourcePrimKey, latest);

		List<KBArticle> list = findByG_P_L(groupId, parentResourcePrimKey,
				latest, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle[] findByG_P_L_PrevAndNext(long kbArticleId, long groupId,
		long parentResourcePrimKey, boolean latest,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByG_P_L_PrevAndNext(session, kbArticle, groupId,
					parentResourcePrimKey, latest, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByG_P_L_PrevAndNext(session, kbArticle, groupId,
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

	protected KBArticle getByG_P_L_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, long parentResourcePrimKey,
		boolean latest, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_P_L_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_L_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_G_P_L_LATEST_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param latest the latest
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_P_L(long groupId,
		long[] parentResourcePrimKeies, boolean latest)
		throws SystemException {
		return findByG_P_L(groupId, parentResourcePrimKeies, latest,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_P_L(long groupId,
		long[] parentResourcePrimKeies, boolean latest, int start, int end)
		throws SystemException {
		return findByG_P_L(groupId, parentResourcePrimKeies, latest, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_P_L(long groupId,
		long[] parentResourcePrimKeies, boolean latest, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_L;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderArgs = new Object[] {
					groupId, StringUtil.merge(parentResourcePrimKeies), latest
				};
		}
		else {
			finderArgs = new Object[] {
					groupId, StringUtil.merge(parentResourcePrimKeies), latest,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((groupId != kbArticle.getGroupId()) ||
						!ArrayUtil.contains(parentResourcePrimKeies,
							kbArticle.getParentResourcePrimKey()) ||
						(latest != kbArticle.getLatest())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

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

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_L_LATEST_5);

			conjunctionable = true;

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
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

				qPos.add(latest);

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @return the matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_P_L(long groupId,
		long parentResourcePrimKey, boolean latest) throws SystemException {
		return filterFindByG_P_L(groupId, parentResourcePrimKey, latest,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_P_L(long groupId,
		long parentResourcePrimKey, boolean latest, int start, int end)
		throws SystemException {
		return filterFindByG_P_L(groupId, parentResourcePrimKey, latest, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_P_L(long groupId,
		long parentResourcePrimKey, boolean latest, int start, int end,
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
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_P_L_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_L_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_G_P_L_LATEST_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentResourcePrimKey);

			qPos.add(latest);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle[] filterFindByG_P_L_PrevAndNext(long kbArticleId,
		long groupId, long parentResourcePrimKey, boolean latest,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_L_PrevAndNext(kbArticleId, groupId,
				parentResourcePrimKey, latest, orderByComparator);
		}

		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = filterGetByG_P_L_PrevAndNext(session, kbArticle,
					groupId, parentResourcePrimKey, latest, orderByComparator,
					true);

			array[1] = kbArticle;

			array[2] = filterGetByG_P_L_PrevAndNext(session, kbArticle,
					groupId, parentResourcePrimKey, latest, orderByComparator,
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

	protected KBArticle filterGetByG_P_L_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, long parentResourcePrimKey,
		boolean latest, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_P_L_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_L_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_G_P_L_LATEST_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(parentResourcePrimKey);

		qPos.add(latest);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param latest the latest
	 * @return the matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_P_L(long groupId,
		long[] parentResourcePrimKeies, boolean latest)
		throws SystemException {
		return filterFindByG_P_L(groupId, parentResourcePrimKeies, latest,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_P_L(long groupId,
		long[] parentResourcePrimKeies, boolean latest, int start, int end)
		throws SystemException {
		return filterFindByG_P_L(groupId, parentResourcePrimKeies, latest,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_P_L(long groupId,
		long[] parentResourcePrimKeies, boolean latest, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_L(groupId, parentResourcePrimKeies, latest, start,
				end, orderByComparator);
		}

		StringBundler query = new StringBundler();

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
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

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_L_LATEST_5);

		conjunctionable = true;

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (parentResourcePrimKeies != null) {
				qPos.add(parentResourcePrimKeies);
			}

			qPos.add(latest);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_P_M(long groupId,
		long parentResourcePrimKey, boolean main) throws SystemException {
		return findByG_P_M(groupId, parentResourcePrimKey, main,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_P_M(long groupId,
		long parentResourcePrimKey, boolean main, int start, int end)
		throws SystemException {
		return findByG_P_M(groupId, parentResourcePrimKey, main, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_P_M(long groupId,
		long parentResourcePrimKey, boolean main, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_M;
			finderArgs = new Object[] { groupId, parentResourcePrimKey, main };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_M;
			finderArgs = new Object[] {
					groupId, parentResourcePrimKey, main,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((groupId != kbArticle.getGroupId()) ||
						(parentResourcePrimKey != kbArticle.getParentResourcePrimKey()) ||
						(main != kbArticle.getMain())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_P_M_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_M_PARENTRESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_G_P_M_MAIN_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentResourcePrimKey);

				qPos.add(main);

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByG_P_M_First(long groupId,
		long parentResourcePrimKey, boolean main,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByG_P_M_First(groupId,
				parentResourcePrimKey, main, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentResourcePrimKey=");
		msg.append(parentResourcePrimKey);

		msg.append(", main=");
		msg.append(main);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByG_P_M_First(long groupId,
		long parentResourcePrimKey, boolean main,
		OrderByComparator orderByComparator) throws SystemException {
		List<KBArticle> list = findByG_P_M(groupId, parentResourcePrimKey,
				main, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByG_P_M_Last(long groupId, long parentResourcePrimKey,
		boolean main, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByG_P_M_Last(groupId, parentResourcePrimKey,
				main, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentResourcePrimKey=");
		msg.append(parentResourcePrimKey);

		msg.append(", main=");
		msg.append(main);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByG_P_M_Last(long groupId,
		long parentResourcePrimKey, boolean main,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByG_P_M(groupId, parentResourcePrimKey, main);

		List<KBArticle> list = findByG_P_M(groupId, parentResourcePrimKey,
				main, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle[] findByG_P_M_PrevAndNext(long kbArticleId, long groupId,
		long parentResourcePrimKey, boolean main,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByG_P_M_PrevAndNext(session, kbArticle, groupId,
					parentResourcePrimKey, main, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByG_P_M_PrevAndNext(session, kbArticle, groupId,
					parentResourcePrimKey, main, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle getByG_P_M_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, long parentResourcePrimKey,
		boolean main, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_P_M_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_M_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_G_P_M_MAIN_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(parentResourcePrimKey);

		qPos.add(main);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param main the main
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_P_M(long groupId,
		long[] parentResourcePrimKeies, boolean main) throws SystemException {
		return findByG_P_M(groupId, parentResourcePrimKeies, main,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_P_M(long groupId,
		long[] parentResourcePrimKeies, boolean main, int start, int end)
		throws SystemException {
		return findByG_P_M(groupId, parentResourcePrimKeies, main, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_P_M(long groupId,
		long[] parentResourcePrimKeies, boolean main, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_M;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderArgs = new Object[] {
					groupId, StringUtil.merge(parentResourcePrimKeies), main
				};
		}
		else {
			finderArgs = new Object[] {
					groupId, StringUtil.merge(parentResourcePrimKeies), main,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((groupId != kbArticle.getGroupId()) ||
						!ArrayUtil.contains(parentResourcePrimKeies,
							kbArticle.getParentResourcePrimKey()) ||
						(main != kbArticle.getMain())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_M_GROUPID_5);

			conjunctionable = true;

			if ((parentResourcePrimKeies == null) ||
					(parentResourcePrimKeies.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < parentResourcePrimKeies.length; i++) {
					query.append(_FINDER_COLUMN_G_P_M_PARENTRESOURCEPRIMKEY_5);

					if ((i + 1) < parentResourcePrimKeies.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_M_MAIN_5);

			conjunctionable = true;

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
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

				qPos.add(main);

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @return the matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_P_M(long groupId,
		long parentResourcePrimKey, boolean main) throws SystemException {
		return filterFindByG_P_M(groupId, parentResourcePrimKey, main,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_P_M(long groupId,
		long parentResourcePrimKey, boolean main, int start, int end)
		throws SystemException {
		return filterFindByG_P_M(groupId, parentResourcePrimKey, main, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_P_M(long groupId,
		long parentResourcePrimKey, boolean main, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_M(groupId, parentResourcePrimKey, main, start,
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
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_P_M_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_M_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_G_P_M_MAIN_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentResourcePrimKey);

			qPos.add(main);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle[] filterFindByG_P_M_PrevAndNext(long kbArticleId,
		long groupId, long parentResourcePrimKey, boolean main,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_M_PrevAndNext(kbArticleId, groupId,
				parentResourcePrimKey, main, orderByComparator);
		}

		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = filterGetByG_P_M_PrevAndNext(session, kbArticle,
					groupId, parentResourcePrimKey, main, orderByComparator,
					true);

			array[1] = kbArticle;

			array[2] = filterGetByG_P_M_PrevAndNext(session, kbArticle,
					groupId, parentResourcePrimKey, main, orderByComparator,
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

	protected KBArticle filterGetByG_P_M_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, long parentResourcePrimKey,
		boolean main, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_P_M_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_M_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_G_P_M_MAIN_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(parentResourcePrimKey);

		qPos.add(main);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param main the main
	 * @return the matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_P_M(long groupId,
		long[] parentResourcePrimKeies, boolean main) throws SystemException {
		return filterFindByG_P_M(groupId, parentResourcePrimKeies, main,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_P_M(long groupId,
		long[] parentResourcePrimKeies, boolean main, int start, int end)
		throws SystemException {
		return filterFindByG_P_M(groupId, parentResourcePrimKeies, main, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_P_M(long groupId,
		long[] parentResourcePrimKeies, boolean main, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_M(groupId, parentResourcePrimKeies, main, start,
				end, orderByComparator);
		}

		StringBundler query = new StringBundler();

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean conjunctionable = false;

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_M_GROUPID_5);

		conjunctionable = true;

		if ((parentResourcePrimKeies == null) ||
				(parentResourcePrimKeies.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < parentResourcePrimKeies.length; i++) {
				query.append(_FINDER_COLUMN_G_P_M_PARENTRESOURCEPRIMKEY_5);

				if ((i + 1) < parentResourcePrimKeies.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			conjunctionable = true;
		}

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_M_MAIN_5);

		conjunctionable = true;

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (parentResourcePrimKeies != null) {
				qPos.add(parentResourcePrimKeies);
			}

			qPos.add(main);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_P_S(long groupId,
		long parentResourcePrimKey, int status) throws SystemException {
		return findByG_P_S(groupId, parentResourcePrimKey, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_P_S(long groupId,
		long parentResourcePrimKey, int status, int start, int end)
		throws SystemException {
		return findByG_P_S(groupId, parentResourcePrimKey, status, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_P_S(long groupId,
		long parentResourcePrimKey, int status, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_S;
			finderArgs = new Object[] { groupId, parentResourcePrimKey, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_S;
			finderArgs = new Object[] {
					groupId, parentResourcePrimKey, status,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((groupId != kbArticle.getGroupId()) ||
						(parentResourcePrimKey != kbArticle.getParentResourcePrimKey()) ||
						(status != kbArticle.getStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_P_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_S_PARENTRESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_G_P_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentResourcePrimKey);

				qPos.add(status);

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByG_P_S_First(long groupId,
		long parentResourcePrimKey, int status,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByG_P_S_First(groupId,
				parentResourcePrimKey, status, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentResourcePrimKey=");
		msg.append(parentResourcePrimKey);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByG_P_S_First(long groupId,
		long parentResourcePrimKey, int status,
		OrderByComparator orderByComparator) throws SystemException {
		List<KBArticle> list = findByG_P_S(groupId, parentResourcePrimKey,
				status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByG_P_S_Last(long groupId, long parentResourcePrimKey,
		int status, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByG_P_S_Last(groupId, parentResourcePrimKey,
				status, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentResourcePrimKey=");
		msg.append(parentResourcePrimKey);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByG_P_S_Last(long groupId,
		long parentResourcePrimKey, int status,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByG_P_S(groupId, parentResourcePrimKey, status);

		List<KBArticle> list = findByG_P_S(groupId, parentResourcePrimKey,
				status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle[] findByG_P_S_PrevAndNext(long kbArticleId, long groupId,
		long parentResourcePrimKey, int status,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByG_P_S_PrevAndNext(session, kbArticle, groupId,
					parentResourcePrimKey, status, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByG_P_S_PrevAndNext(session, kbArticle, groupId,
					parentResourcePrimKey, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle getByG_P_S_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, long parentResourcePrimKey,
		int status, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_P_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_S_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_G_P_S_STATUS_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(parentResourcePrimKey);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param status the status
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_P_S(long groupId,
		long[] parentResourcePrimKeies, int status) throws SystemException {
		return findByG_P_S(groupId, parentResourcePrimKeies, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_P_S(long groupId,
		long[] parentResourcePrimKeies, int status, int start, int end)
		throws SystemException {
		return findByG_P_S(groupId, parentResourcePrimKeies, status, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_P_S(long groupId,
		long[] parentResourcePrimKeies, int status, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_S;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderArgs = new Object[] {
					groupId, StringUtil.merge(parentResourcePrimKeies), status
				};
		}
		else {
			finderArgs = new Object[] {
					groupId, StringUtil.merge(parentResourcePrimKeies), status,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((groupId != kbArticle.getGroupId()) ||
						!ArrayUtil.contains(parentResourcePrimKeies,
							kbArticle.getParentResourcePrimKey()) ||
						(status != kbArticle.getStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_S_GROUPID_5);

			conjunctionable = true;

			if ((parentResourcePrimKeies == null) ||
					(parentResourcePrimKeies.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < parentResourcePrimKeies.length; i++) {
					query.append(_FINDER_COLUMN_G_P_S_PARENTRESOURCEPRIMKEY_5);

					if ((i + 1) < parentResourcePrimKeies.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_S_STATUS_5);

			conjunctionable = true;

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
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

				qPos.add(status);

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @return the matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_P_S(long groupId,
		long parentResourcePrimKey, int status) throws SystemException {
		return filterFindByG_P_S(groupId, parentResourcePrimKey, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_P_S(long groupId,
		long parentResourcePrimKey, int status, int start, int end)
		throws SystemException {
		return filterFindByG_P_S(groupId, parentResourcePrimKey, status, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_P_S(long groupId,
		long parentResourcePrimKey, int status, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_S(groupId, parentResourcePrimKey, status, start,
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
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_P_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_S_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_G_P_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentResourcePrimKey);

			qPos.add(status);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle[] filterFindByG_P_S_PrevAndNext(long kbArticleId,
		long groupId, long parentResourcePrimKey, int status,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_S_PrevAndNext(kbArticleId, groupId,
				parentResourcePrimKey, status, orderByComparator);
		}

		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = filterGetByG_P_S_PrevAndNext(session, kbArticle,
					groupId, parentResourcePrimKey, status, orderByComparator,
					true);

			array[1] = kbArticle;

			array[2] = filterGetByG_P_S_PrevAndNext(session, kbArticle,
					groupId, parentResourcePrimKey, status, orderByComparator,
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

	protected KBArticle filterGetByG_P_S_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, long parentResourcePrimKey,
		int status, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_P_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_S_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_G_P_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(parentResourcePrimKey);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param status the status
	 * @return the matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_P_S(long groupId,
		long[] parentResourcePrimKeies, int status) throws SystemException {
		return filterFindByG_P_S(groupId, parentResourcePrimKeies, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_P_S(long groupId,
		long[] parentResourcePrimKeies, int status, int start, int end)
		throws SystemException {
		return filterFindByG_P_S(groupId, parentResourcePrimKeies, status,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_P_S(long groupId,
		long[] parentResourcePrimKeies, int status, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_S(groupId, parentResourcePrimKeies, status, start,
				end, orderByComparator);
		}

		StringBundler query = new StringBundler();

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean conjunctionable = false;

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_S_GROUPID_5);

		conjunctionable = true;

		if ((parentResourcePrimKeies == null) ||
				(parentResourcePrimKeies.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < parentResourcePrimKeies.length; i++) {
				query.append(_FINDER_COLUMN_G_P_S_PARENTRESOURCEPRIMKEY_5);

				if ((i + 1) < parentResourcePrimKeies.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			conjunctionable = true;
		}

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_S_STATUS_5);

		conjunctionable = true;

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (parentResourcePrimKeies != null) {
				qPos.add(parentResourcePrimKeies);
			}

			qPos.add(status);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param latest the latest
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_P_S_L(long groupId,
		long parentResourcePrimKey, String sections, boolean latest)
		throws SystemException {
		return findByG_P_S_L(groupId, parentResourcePrimKey, sections, latest,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_P_S_L(long groupId,
		long parentResourcePrimKey, String sections, boolean latest, int start,
		int end) throws SystemException {
		return findByG_P_S_L(groupId, parentResourcePrimKey, sections, latest,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_P_S_L(long groupId,
		long parentResourcePrimKey, String sections, boolean latest, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_S_L;
		finderArgs = new Object[] {
				groupId, parentResourcePrimKey, sections, latest,
				
				start, end, orderByComparator
			};

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((groupId != kbArticle.getGroupId()) ||
						(parentResourcePrimKey != kbArticle.getParentResourcePrimKey()) ||
						!Validator.equals(sections, kbArticle.getSections()) ||
						(latest != kbArticle.getLatest())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_P_S_L_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_S_L_PARENTRESOURCEPRIMKEY_2);

			if (sections == null) {
				query.append(_FINDER_COLUMN_G_P_S_L_SECTIONS_1);
			}
			else {
				if (sections.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_P_S_L_SECTIONS_3);
				}
				else {
					query.append(_FINDER_COLUMN_G_P_S_L_SECTIONS_2);
				}
			}

			query.append(_FINDER_COLUMN_G_P_S_L_LATEST_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentResourcePrimKey);

				if (sections != null) {
					qPos.add(sections);
				}

				qPos.add(latest);

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByG_P_S_L_First(long groupId,
		long parentResourcePrimKey, String sections, boolean latest,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByG_P_S_L_First(groupId,
				parentResourcePrimKey, sections, latest, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentResourcePrimKey=");
		msg.append(parentResourcePrimKey);

		msg.append(", sections=");
		msg.append(sections);

		msg.append(", latest=");
		msg.append(latest);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByG_P_S_L_First(long groupId,
		long parentResourcePrimKey, String sections, boolean latest,
		OrderByComparator orderByComparator) throws SystemException {
		List<KBArticle> list = findByG_P_S_L(groupId, parentResourcePrimKey,
				sections, latest, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByG_P_S_L_Last(long groupId,
		long parentResourcePrimKey, String sections, boolean latest,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByG_P_S_L_Last(groupId,
				parentResourcePrimKey, sections, latest, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentResourcePrimKey=");
		msg.append(parentResourcePrimKey);

		msg.append(", sections=");
		msg.append(sections);

		msg.append(", latest=");
		msg.append(latest);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByG_P_S_L_Last(long groupId,
		long parentResourcePrimKey, String sections, boolean latest,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByG_P_S_L(groupId, parentResourcePrimKey, sections,
				latest);

		List<KBArticle> list = findByG_P_S_L(groupId, parentResourcePrimKey,
				sections, latest, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle[] findByG_P_S_L_PrevAndNext(long kbArticleId,
		long groupId, long parentResourcePrimKey, String sections,
		boolean latest, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByG_P_S_L_PrevAndNext(session, kbArticle, groupId,
					parentResourcePrimKey, sections, latest, orderByComparator,
					true);

			array[1] = kbArticle;

			array[2] = getByG_P_S_L_PrevAndNext(session, kbArticle, groupId,
					parentResourcePrimKey, sections, latest, orderByComparator,
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

	protected KBArticle getByG_P_S_L_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, long parentResourcePrimKey,
		String sections, boolean latest, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_P_S_L_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_S_L_PARENTRESOURCEPRIMKEY_2);

		if (sections == null) {
			query.append(_FINDER_COLUMN_G_P_S_L_SECTIONS_1);
		}
		else {
			if (sections.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_P_S_L_SECTIONS_3);
			}
			else {
				query.append(_FINDER_COLUMN_G_P_S_L_SECTIONS_2);
			}
		}

		query.append(_FINDER_COLUMN_G_P_S_L_LATEST_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(parentResourcePrimKey);

		if (sections != null) {
			qPos.add(sections);
		}

		qPos.add(latest);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sectionses the sectionses
	 * @param latest the latest
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_P_S_L(long groupId,
		long parentResourcePrimKey, String[] sectionses, boolean latest)
		throws SystemException {
		return findByG_P_S_L(groupId, parentResourcePrimKey, sectionses,
			latest, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sectionses the sectionses
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_P_S_L(long groupId,
		long parentResourcePrimKey, String[] sectionses, boolean latest,
		int start, int end) throws SystemException {
		return findByG_P_S_L(groupId, parentResourcePrimKey, sectionses,
			latest, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sectionses the sectionses
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_P_S_L(long groupId,
		long parentResourcePrimKey, String[] sectionses, boolean latest,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_S_L;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderArgs = new Object[] {
					groupId, parentResourcePrimKey, StringUtil.merge(sectionses),
					latest
				};
		}
		else {
			finderArgs = new Object[] {
					groupId, parentResourcePrimKey, StringUtil.merge(sectionses),
					latest,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((groupId != kbArticle.getGroupId()) ||
						(parentResourcePrimKey != kbArticle.getParentResourcePrimKey()) ||
						!ArrayUtil.contains(sectionses, kbArticle.getSections()) ||
						(latest != kbArticle.getLatest())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_S_L_GROUPID_5);

			conjunctionable = true;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_S_L_PARENTRESOURCEPRIMKEY_5);

			conjunctionable = true;

			if ((sectionses == null) || (sectionses.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < sectionses.length; i++) {
					String sections = sectionses[i];

					if (sections == null) {
						query.append(_FINDER_COLUMN_G_P_S_L_SECTIONS_4);
					}
					else {
						if (sections.equals(StringPool.BLANK)) {
							query.append(_FINDER_COLUMN_G_P_S_L_SECTIONS_6);
						}
						else {
							query.append(_FINDER_COLUMN_G_P_S_L_SECTIONS_5);
						}
					}

					if ((i + 1) < sectionses.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_S_L_LATEST_5);

			conjunctionable = true;

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentResourcePrimKey);

				if (sectionses != null) {
					qPos.add(sectionses);
				}

				qPos.add(latest);

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param latest the latest
	 * @return the matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_P_S_L(long groupId,
		long parentResourcePrimKey, String sections, boolean latest)
		throws SystemException {
		return filterFindByG_P_S_L(groupId, parentResourcePrimKey, sections,
			latest, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_P_S_L(long groupId,
		long parentResourcePrimKey, String sections, boolean latest, int start,
		int end) throws SystemException {
		return filterFindByG_P_S_L(groupId, parentResourcePrimKey, sections,
			latest, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_P_S_L(long groupId,
		long parentResourcePrimKey, String sections, boolean latest, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_S_L(groupId, parentResourcePrimKey, sections,
				latest, start, end, orderByComparator);
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
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_P_S_L_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_S_L_PARENTRESOURCEPRIMKEY_2);

		if (sections == null) {
			query.append(_FINDER_COLUMN_G_P_S_L_SECTIONS_1);
		}
		else {
			if (sections.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_P_S_L_SECTIONS_3);
			}
			else {
				query.append(_FINDER_COLUMN_G_P_S_L_SECTIONS_2);
			}
		}

		query.append(_FINDER_COLUMN_G_P_S_L_LATEST_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentResourcePrimKey);

			if (sections != null) {
				qPos.add(sections);
			}

			qPos.add(latest);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle[] filterFindByG_P_S_L_PrevAndNext(long kbArticleId,
		long groupId, long parentResourcePrimKey, String sections,
		boolean latest, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_S_L_PrevAndNext(kbArticleId, groupId,
				parentResourcePrimKey, sections, latest, orderByComparator);
		}

		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = filterGetByG_P_S_L_PrevAndNext(session, kbArticle,
					groupId, parentResourcePrimKey, sections, latest,
					orderByComparator, true);

			array[1] = kbArticle;

			array[2] = filterGetByG_P_S_L_PrevAndNext(session, kbArticle,
					groupId, parentResourcePrimKey, sections, latest,
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

	protected KBArticle filterGetByG_P_S_L_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, long parentResourcePrimKey,
		String sections, boolean latest, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_P_S_L_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_S_L_PARENTRESOURCEPRIMKEY_2);

		if (sections == null) {
			query.append(_FINDER_COLUMN_G_P_S_L_SECTIONS_1);
		}
		else {
			if (sections.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_P_S_L_SECTIONS_3);
			}
			else {
				query.append(_FINDER_COLUMN_G_P_S_L_SECTIONS_2);
			}
		}

		query.append(_FINDER_COLUMN_G_P_S_L_LATEST_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(parentResourcePrimKey);

		if (sections != null) {
			qPos.add(sections);
		}

		qPos.add(latest);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sectionses the sectionses
	 * @param latest the latest
	 * @return the matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_P_S_L(long groupId,
		long parentResourcePrimKey, String[] sectionses, boolean latest)
		throws SystemException {
		return filterFindByG_P_S_L(groupId, parentResourcePrimKey, sectionses,
			latest, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sectionses the sectionses
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_P_S_L(long groupId,
		long parentResourcePrimKey, String[] sectionses, boolean latest,
		int start, int end) throws SystemException {
		return filterFindByG_P_S_L(groupId, parentResourcePrimKey, sectionses,
			latest, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sectionses the sectionses
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_P_S_L(long groupId,
		long parentResourcePrimKey, String[] sectionses, boolean latest,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_S_L(groupId, parentResourcePrimKey, sectionses,
				latest, start, end, orderByComparator);
		}

		StringBundler query = new StringBundler();

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean conjunctionable = false;

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_S_L_GROUPID_5);

		conjunctionable = true;

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_S_L_PARENTRESOURCEPRIMKEY_5);

		conjunctionable = true;

		if ((sectionses == null) || (sectionses.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < sectionses.length; i++) {
				String sections = sectionses[i];

				if (sections == null) {
					query.append(_FINDER_COLUMN_G_P_S_L_SECTIONS_4);
				}
				else {
					if (sections.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_G_P_S_L_SECTIONS_6);
					}
					else {
						query.append(_FINDER_COLUMN_G_P_S_L_SECTIONS_5);
					}
				}

				if ((i + 1) < sectionses.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			conjunctionable = true;
		}

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_S_L_LATEST_5);

		conjunctionable = true;

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentResourcePrimKey);

			if (sectionses != null) {
				qPos.add(sectionses);
			}

			qPos.add(latest);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param main the main
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_P_S_M(long groupId,
		long parentResourcePrimKey, String sections, boolean main)
		throws SystemException {
		return findByG_P_S_M(groupId, parentResourcePrimKey, sections, main,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_P_S_M(long groupId,
		long parentResourcePrimKey, String sections, boolean main, int start,
		int end) throws SystemException {
		return findByG_P_S_M(groupId, parentResourcePrimKey, sections, main,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_P_S_M(long groupId,
		long parentResourcePrimKey, String sections, boolean main, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_S_M;
		finderArgs = new Object[] {
				groupId, parentResourcePrimKey, sections, main,
				
				start, end, orderByComparator
			};

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((groupId != kbArticle.getGroupId()) ||
						(parentResourcePrimKey != kbArticle.getParentResourcePrimKey()) ||
						!Validator.equals(sections, kbArticle.getSections()) ||
						(main != kbArticle.getMain())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_P_S_M_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_S_M_PARENTRESOURCEPRIMKEY_2);

			if (sections == null) {
				query.append(_FINDER_COLUMN_G_P_S_M_SECTIONS_1);
			}
			else {
				if (sections.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_P_S_M_SECTIONS_3);
				}
				else {
					query.append(_FINDER_COLUMN_G_P_S_M_SECTIONS_2);
				}
			}

			query.append(_FINDER_COLUMN_G_P_S_M_MAIN_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentResourcePrimKey);

				if (sections != null) {
					qPos.add(sections);
				}

				qPos.add(main);

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByG_P_S_M_First(long groupId,
		long parentResourcePrimKey, String sections, boolean main,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByG_P_S_M_First(groupId,
				parentResourcePrimKey, sections, main, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentResourcePrimKey=");
		msg.append(parentResourcePrimKey);

		msg.append(", sections=");
		msg.append(sections);

		msg.append(", main=");
		msg.append(main);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByG_P_S_M_First(long groupId,
		long parentResourcePrimKey, String sections, boolean main,
		OrderByComparator orderByComparator) throws SystemException {
		List<KBArticle> list = findByG_P_S_M(groupId, parentResourcePrimKey,
				sections, main, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByG_P_S_M_Last(long groupId,
		long parentResourcePrimKey, String sections, boolean main,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByG_P_S_M_Last(groupId,
				parentResourcePrimKey, sections, main, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentResourcePrimKey=");
		msg.append(parentResourcePrimKey);

		msg.append(", sections=");
		msg.append(sections);

		msg.append(", main=");
		msg.append(main);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByG_P_S_M_Last(long groupId,
		long parentResourcePrimKey, String sections, boolean main,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByG_P_S_M(groupId, parentResourcePrimKey, sections,
				main);

		List<KBArticle> list = findByG_P_S_M(groupId, parentResourcePrimKey,
				sections, main, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle[] findByG_P_S_M_PrevAndNext(long kbArticleId,
		long groupId, long parentResourcePrimKey, String sections,
		boolean main, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByG_P_S_M_PrevAndNext(session, kbArticle, groupId,
					parentResourcePrimKey, sections, main, orderByComparator,
					true);

			array[1] = kbArticle;

			array[2] = getByG_P_S_M_PrevAndNext(session, kbArticle, groupId,
					parentResourcePrimKey, sections, main, orderByComparator,
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

	protected KBArticle getByG_P_S_M_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, long parentResourcePrimKey,
		String sections, boolean main, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_P_S_M_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_S_M_PARENTRESOURCEPRIMKEY_2);

		if (sections == null) {
			query.append(_FINDER_COLUMN_G_P_S_M_SECTIONS_1);
		}
		else {
			if (sections.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_P_S_M_SECTIONS_3);
			}
			else {
				query.append(_FINDER_COLUMN_G_P_S_M_SECTIONS_2);
			}
		}

		query.append(_FINDER_COLUMN_G_P_S_M_MAIN_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(parentResourcePrimKey);

		if (sections != null) {
			qPos.add(sections);
		}

		qPos.add(main);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sectionses the sectionses
	 * @param main the main
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_P_S_M(long groupId,
		long parentResourcePrimKey, String[] sectionses, boolean main)
		throws SystemException {
		return findByG_P_S_M(groupId, parentResourcePrimKey, sectionses, main,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sectionses the sectionses
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_P_S_M(long groupId,
		long parentResourcePrimKey, String[] sectionses, boolean main,
		int start, int end) throws SystemException {
		return findByG_P_S_M(groupId, parentResourcePrimKey, sectionses, main,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sectionses the sectionses
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_P_S_M(long groupId,
		long parentResourcePrimKey, String[] sectionses, boolean main,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_S_M;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderArgs = new Object[] {
					groupId, parentResourcePrimKey, StringUtil.merge(sectionses),
					main
				};
		}
		else {
			finderArgs = new Object[] {
					groupId, parentResourcePrimKey, StringUtil.merge(sectionses),
					main,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((groupId != kbArticle.getGroupId()) ||
						(parentResourcePrimKey != kbArticle.getParentResourcePrimKey()) ||
						!ArrayUtil.contains(sectionses, kbArticle.getSections()) ||
						(main != kbArticle.getMain())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_S_M_GROUPID_5);

			conjunctionable = true;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_S_M_PARENTRESOURCEPRIMKEY_5);

			conjunctionable = true;

			if ((sectionses == null) || (sectionses.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < sectionses.length; i++) {
					String sections = sectionses[i];

					if (sections == null) {
						query.append(_FINDER_COLUMN_G_P_S_M_SECTIONS_4);
					}
					else {
						if (sections.equals(StringPool.BLANK)) {
							query.append(_FINDER_COLUMN_G_P_S_M_SECTIONS_6);
						}
						else {
							query.append(_FINDER_COLUMN_G_P_S_M_SECTIONS_5);
						}
					}

					if ((i + 1) < sectionses.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_S_M_MAIN_5);

			conjunctionable = true;

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentResourcePrimKey);

				if (sectionses != null) {
					qPos.add(sectionses);
				}

				qPos.add(main);

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param main the main
	 * @return the matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_P_S_M(long groupId,
		long parentResourcePrimKey, String sections, boolean main)
		throws SystemException {
		return filterFindByG_P_S_M(groupId, parentResourcePrimKey, sections,
			main, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_P_S_M(long groupId,
		long parentResourcePrimKey, String sections, boolean main, int start,
		int end) throws SystemException {
		return filterFindByG_P_S_M(groupId, parentResourcePrimKey, sections,
			main, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_P_S_M(long groupId,
		long parentResourcePrimKey, String sections, boolean main, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_S_M(groupId, parentResourcePrimKey, sections,
				main, start, end, orderByComparator);
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
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_P_S_M_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_S_M_PARENTRESOURCEPRIMKEY_2);

		if (sections == null) {
			query.append(_FINDER_COLUMN_G_P_S_M_SECTIONS_1);
		}
		else {
			if (sections.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_P_S_M_SECTIONS_3);
			}
			else {
				query.append(_FINDER_COLUMN_G_P_S_M_SECTIONS_2);
			}
		}

		query.append(_FINDER_COLUMN_G_P_S_M_MAIN_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentResourcePrimKey);

			if (sections != null) {
				qPos.add(sections);
			}

			qPos.add(main);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle[] filterFindByG_P_S_M_PrevAndNext(long kbArticleId,
		long groupId, long parentResourcePrimKey, String sections,
		boolean main, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_S_M_PrevAndNext(kbArticleId, groupId,
				parentResourcePrimKey, sections, main, orderByComparator);
		}

		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = filterGetByG_P_S_M_PrevAndNext(session, kbArticle,
					groupId, parentResourcePrimKey, sections, main,
					orderByComparator, true);

			array[1] = kbArticle;

			array[2] = filterGetByG_P_S_M_PrevAndNext(session, kbArticle,
					groupId, parentResourcePrimKey, sections, main,
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

	protected KBArticle filterGetByG_P_S_M_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, long parentResourcePrimKey,
		String sections, boolean main, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_P_S_M_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_S_M_PARENTRESOURCEPRIMKEY_2);

		if (sections == null) {
			query.append(_FINDER_COLUMN_G_P_S_M_SECTIONS_1);
		}
		else {
			if (sections.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_P_S_M_SECTIONS_3);
			}
			else {
				query.append(_FINDER_COLUMN_G_P_S_M_SECTIONS_2);
			}
		}

		query.append(_FINDER_COLUMN_G_P_S_M_MAIN_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(parentResourcePrimKey);

		if (sections != null) {
			qPos.add(sections);
		}

		qPos.add(main);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sectionses the sectionses
	 * @param main the main
	 * @return the matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_P_S_M(long groupId,
		long parentResourcePrimKey, String[] sectionses, boolean main)
		throws SystemException {
		return filterFindByG_P_S_M(groupId, parentResourcePrimKey, sectionses,
			main, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sectionses the sectionses
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_P_S_M(long groupId,
		long parentResourcePrimKey, String[] sectionses, boolean main,
		int start, int end) throws SystemException {
		return filterFindByG_P_S_M(groupId, parentResourcePrimKey, sectionses,
			main, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sectionses the sectionses
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_P_S_M(long groupId,
		long parentResourcePrimKey, String[] sectionses, boolean main,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_S_M(groupId, parentResourcePrimKey, sectionses,
				main, start, end, orderByComparator);
		}

		StringBundler query = new StringBundler();

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean conjunctionable = false;

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_S_M_GROUPID_5);

		conjunctionable = true;

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_S_M_PARENTRESOURCEPRIMKEY_5);

		conjunctionable = true;

		if ((sectionses == null) || (sectionses.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < sectionses.length; i++) {
				String sections = sectionses[i];

				if (sections == null) {
					query.append(_FINDER_COLUMN_G_P_S_M_SECTIONS_4);
				}
				else {
					if (sections.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_G_P_S_M_SECTIONS_6);
					}
					else {
						query.append(_FINDER_COLUMN_G_P_S_M_SECTIONS_5);
					}
				}

				if ((i + 1) < sectionses.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			conjunctionable = true;
		}

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_S_M_MAIN_5);

		conjunctionable = true;

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentResourcePrimKey);

			if (sectionses != null) {
				qPos.add(sectionses);
			}

			qPos.add(main);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param status the status
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_P_S_S(long groupId,
		long parentResourcePrimKey, String sections, int status)
		throws SystemException {
		return findByG_P_S_S(groupId, parentResourcePrimKey, sections, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_P_S_S(long groupId,
		long parentResourcePrimKey, String sections, int status, int start,
		int end) throws SystemException {
		return findByG_P_S_S(groupId, parentResourcePrimKey, sections, status,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_P_S_S(long groupId,
		long parentResourcePrimKey, String sections, int status, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_S_S;
		finderArgs = new Object[] {
				groupId, parentResourcePrimKey, sections, status,
				
				start, end, orderByComparator
			};

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((groupId != kbArticle.getGroupId()) ||
						(parentResourcePrimKey != kbArticle.getParentResourcePrimKey()) ||
						!Validator.equals(sections, kbArticle.getSections()) ||
						(status != kbArticle.getStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_P_S_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_S_S_PARENTRESOURCEPRIMKEY_2);

			if (sections == null) {
				query.append(_FINDER_COLUMN_G_P_S_S_SECTIONS_1);
			}
			else {
				if (sections.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_P_S_S_SECTIONS_3);
				}
				else {
					query.append(_FINDER_COLUMN_G_P_S_S_SECTIONS_2);
				}
			}

			query.append(_FINDER_COLUMN_G_P_S_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentResourcePrimKey);

				if (sections != null) {
					qPos.add(sections);
				}

				qPos.add(status);

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByG_P_S_S_First(long groupId,
		long parentResourcePrimKey, String sections, int status,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByG_P_S_S_First(groupId,
				parentResourcePrimKey, sections, status, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentResourcePrimKey=");
		msg.append(parentResourcePrimKey);

		msg.append(", sections=");
		msg.append(sections);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByG_P_S_S_First(long groupId,
		long parentResourcePrimKey, String sections, int status,
		OrderByComparator orderByComparator) throws SystemException {
		List<KBArticle> list = findByG_P_S_S(groupId, parentResourcePrimKey,
				sections, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle findByG_P_S_S_Last(long groupId,
		long parentResourcePrimKey, String sections, int status,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByG_P_S_S_Last(groupId,
				parentResourcePrimKey, sections, status, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentResourcePrimKey=");
		msg.append(parentResourcePrimKey);

		msg.append(", sections=");
		msg.append(sections);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle fetchByG_P_S_S_Last(long groupId,
		long parentResourcePrimKey, String sections, int status,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByG_P_S_S(groupId, parentResourcePrimKey, sections,
				status);

		List<KBArticle> list = findByG_P_S_S(groupId, parentResourcePrimKey,
				sections, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle[] findByG_P_S_S_PrevAndNext(long kbArticleId,
		long groupId, long parentResourcePrimKey, String sections, int status,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByG_P_S_S_PrevAndNext(session, kbArticle, groupId,
					parentResourcePrimKey, sections, status, orderByComparator,
					true);

			array[1] = kbArticle;

			array[2] = getByG_P_S_S_PrevAndNext(session, kbArticle, groupId,
					parentResourcePrimKey, sections, status, orderByComparator,
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

	protected KBArticle getByG_P_S_S_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, long parentResourcePrimKey,
		String sections, int status, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_P_S_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_S_S_PARENTRESOURCEPRIMKEY_2);

		if (sections == null) {
			query.append(_FINDER_COLUMN_G_P_S_S_SECTIONS_1);
		}
		else {
			if (sections.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_P_S_S_SECTIONS_3);
			}
			else {
				query.append(_FINDER_COLUMN_G_P_S_S_SECTIONS_2);
			}
		}

		query.append(_FINDER_COLUMN_G_P_S_S_STATUS_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(parentResourcePrimKey);

		if (sections != null) {
			qPos.add(sections);
		}

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sectionses the sectionses
	 * @param status the status
	 * @return the matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_P_S_S(long groupId,
		long parentResourcePrimKey, String[] sectionses, int status)
		throws SystemException {
		return findByG_P_S_S(groupId, parentResourcePrimKey, sectionses,
			status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sectionses the sectionses
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_P_S_S(long groupId,
		long parentResourcePrimKey, String[] sectionses, int status, int start,
		int end) throws SystemException {
		return findByG_P_S_S(groupId, parentResourcePrimKey, sectionses,
			status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sectionses the sectionses
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findByG_P_S_S(long groupId,
		long parentResourcePrimKey, String[] sectionses, int status, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_S_S;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderArgs = new Object[] {
					groupId, parentResourcePrimKey, StringUtil.merge(sectionses),
					status
				};
		}
		else {
			finderArgs = new Object[] {
					groupId, parentResourcePrimKey, StringUtil.merge(sectionses),
					status,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((groupId != kbArticle.getGroupId()) ||
						(parentResourcePrimKey != kbArticle.getParentResourcePrimKey()) ||
						!ArrayUtil.contains(sectionses, kbArticle.getSections()) ||
						(status != kbArticle.getStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_S_S_GROUPID_5);

			conjunctionable = true;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_S_S_PARENTRESOURCEPRIMKEY_5);

			conjunctionable = true;

			if ((sectionses == null) || (sectionses.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < sectionses.length; i++) {
					String sections = sectionses[i];

					if (sections == null) {
						query.append(_FINDER_COLUMN_G_P_S_S_SECTIONS_4);
					}
					else {
						if (sections.equals(StringPool.BLANK)) {
							query.append(_FINDER_COLUMN_G_P_S_S_SECTIONS_6);
						}
						else {
							query.append(_FINDER_COLUMN_G_P_S_S_SECTIONS_5);
						}
					}

					if ((i + 1) < sectionses.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_S_S_STATUS_5);

			conjunctionable = true;

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentResourcePrimKey);

				if (sectionses != null) {
					qPos.add(sectionses);
				}

				qPos.add(status);

				list = (List<KBArticle>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param status the status
	 * @return the matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_P_S_S(long groupId,
		long parentResourcePrimKey, String sections, int status)
		throws SystemException {
		return filterFindByG_P_S_S(groupId, parentResourcePrimKey, sections,
			status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_P_S_S(long groupId,
		long parentResourcePrimKey, String sections, int status, int start,
		int end) throws SystemException {
		return filterFindByG_P_S_S(groupId, parentResourcePrimKey, sections,
			status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_P_S_S(long groupId,
		long parentResourcePrimKey, String sections, int status, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_S_S(groupId, parentResourcePrimKey, sections,
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
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_P_S_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_S_S_PARENTRESOURCEPRIMKEY_2);

		if (sections == null) {
			query.append(_FINDER_COLUMN_G_P_S_S_SECTIONS_1);
		}
		else {
			if (sections.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_P_S_S_SECTIONS_3);
			}
			else {
				query.append(_FINDER_COLUMN_G_P_S_S_SECTIONS_2);
			}
		}

		query.append(_FINDER_COLUMN_G_P_S_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentResourcePrimKey);

			if (sections != null) {
				qPos.add(sections);
			}

			qPos.add(status);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle[] filterFindByG_P_S_S_PrevAndNext(long kbArticleId,
		long groupId, long parentResourcePrimKey, String sections, int status,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_S_S_PrevAndNext(kbArticleId, groupId,
				parentResourcePrimKey, sections, status, orderByComparator);
		}

		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = filterGetByG_P_S_S_PrevAndNext(session, kbArticle,
					groupId, parentResourcePrimKey, sections, status,
					orderByComparator, true);

			array[1] = kbArticle;

			array[2] = filterGetByG_P_S_S_PrevAndNext(session, kbArticle,
					groupId, parentResourcePrimKey, sections, status,
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

	protected KBArticle filterGetByG_P_S_S_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, long parentResourcePrimKey,
		String sections, int status, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_P_S_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_S_S_PARENTRESOURCEPRIMKEY_2);

		if (sections == null) {
			query.append(_FINDER_COLUMN_G_P_S_S_SECTIONS_1);
		}
		else {
			if (sections.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_P_S_S_SECTIONS_3);
			}
			else {
				query.append(_FINDER_COLUMN_G_P_S_S_SECTIONS_2);
			}
		}

		query.append(_FINDER_COLUMN_G_P_S_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(parentResourcePrimKey);

		if (sections != null) {
			qPos.add(sections);
		}

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sectionses the sectionses
	 * @param status the status
	 * @return the matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_P_S_S(long groupId,
		long parentResourcePrimKey, String[] sectionses, int status)
		throws SystemException {
		return filterFindByG_P_S_S(groupId, parentResourcePrimKey, sectionses,
			status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sectionses the sectionses
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_P_S_S(long groupId,
		long parentResourcePrimKey, String[] sectionses, int status, int start,
		int end) throws SystemException {
		return filterFindByG_P_S_S(groupId, parentResourcePrimKey, sectionses,
			status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sectionses the sectionses
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> filterFindByG_P_S_S(long groupId,
		long parentResourcePrimKey, String[] sectionses, int status, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_S_S(groupId, parentResourcePrimKey, sectionses,
				status, start, end, orderByComparator);
		}

		StringBundler query = new StringBundler();

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean conjunctionable = false;

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_S_S_GROUPID_5);

		conjunctionable = true;

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_S_S_PARENTRESOURCEPRIMKEY_5);

		conjunctionable = true;

		if ((sectionses == null) || (sectionses.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < sectionses.length; i++) {
				String sections = sectionses[i];

				if (sections == null) {
					query.append(_FINDER_COLUMN_G_P_S_S_SECTIONS_4);
				}
				else {
					if (sections.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_G_P_S_S_SECTIONS_6);
					}
					else {
						query.append(_FINDER_COLUMN_G_P_S_S_SECTIONS_5);
					}
				}

				if ((i + 1) < sectionses.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			conjunctionable = true;
		}

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_S_S_STATUS_5);

		conjunctionable = true;

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentResourcePrimKey);

			if (sectionses != null) {
				qPos.add(sectionses);
			}

			qPos.add(status);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns all the k b articles.
	 *
	 * @return the k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBArticle> findAll(int start, int end,
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

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_KBARTICLE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_KBARTICLE.concat(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the k b articles where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (KBArticle kbArticle : findByUuid(uuid)) {
			remove(kbArticle);
		}
	}

	/**
	 * Removes the k b article where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the k b article that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle removeByUUID_G(String uuid, long groupId)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByUUID_G(uuid, groupId);

		return remove(kbArticle);
	}

	/**
	 * Removes all the k b articles where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid_C(String uuid, long companyId)
		throws SystemException {
		for (KBArticle kbArticle : findByUuid_C(uuid, companyId)) {
			remove(kbArticle);
		}
	}

	/**
	 * Removes all the k b articles where resourcePrimKey = &#63; from the database.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByResourcePrimKey(long resourcePrimKey)
		throws SystemException {
		for (KBArticle kbArticle : findByResourcePrimKey(resourcePrimKey)) {
			remove(kbArticle);
		}
	}

	/**
	 * Removes all the k b articles where resourcePrimKey = &#63; and groupId = &#63; from the database.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByR_G(long resourcePrimKey, long groupId)
		throws SystemException {
		for (KBArticle kbArticle : findByR_G(resourcePrimKey, groupId)) {
			remove(kbArticle);
		}
	}

	/**
	 * Removes the k b article where resourcePrimKey = &#63; and version = &#63; from the database.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param version the version
	 * @return the k b article that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public KBArticle removeByR_V(long resourcePrimKey, int version)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByR_V(resourcePrimKey, version);

		return remove(kbArticle);
	}

	/**
	 * Removes all the k b articles where resourcePrimKey = &#63; and latest = &#63; from the database.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param latest the latest
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByR_L(long resourcePrimKey, boolean latest)
		throws SystemException {
		for (KBArticle kbArticle : findByR_L(resourcePrimKey, latest)) {
			remove(kbArticle);
		}
	}

	/**
	 * Removes all the k b articles where resourcePrimKey = &#63; and main = &#63; from the database.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param main the main
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByR_M(long resourcePrimKey, boolean main)
		throws SystemException {
		for (KBArticle kbArticle : findByR_M(resourcePrimKey, main)) {
			remove(kbArticle);
		}
	}

	/**
	 * Removes all the k b articles where resourcePrimKey = &#63; and status = &#63; from the database.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByR_S(long resourcePrimKey, int status)
		throws SystemException {
		for (KBArticle kbArticle : findByR_S(resourcePrimKey, status)) {
			remove(kbArticle);
		}
	}

	/**
	 * Removes all the k b articles where groupId = &#63; and latest = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param latest the latest
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_L(long groupId, boolean latest)
		throws SystemException {
		for (KBArticle kbArticle : findByG_L(groupId, latest)) {
			remove(kbArticle);
		}
	}

	/**
	 * Removes all the k b articles where groupId = &#63; and main = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param main the main
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_M(long groupId, boolean main)
		throws SystemException {
		for (KBArticle kbArticle : findByG_M(groupId, main)) {
			remove(kbArticle);
		}
	}

	/**
	 * Removes all the k b articles where groupId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_S(long groupId, int status) throws SystemException {
		for (KBArticle kbArticle : findByG_S(groupId, status)) {
			remove(kbArticle);
		}
	}

	/**
	 * Removes all the k b articles where companyId = &#63; and latest = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param latest the latest
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_L(long companyId, boolean latest)
		throws SystemException {
		for (KBArticle kbArticle : findByC_L(companyId, latest)) {
			remove(kbArticle);
		}
	}

	/**
	 * Removes all the k b articles where companyId = &#63; and main = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param main the main
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_M(long companyId, boolean main)
		throws SystemException {
		for (KBArticle kbArticle : findByC_M(companyId, main)) {
			remove(kbArticle);
		}
	}

	/**
	 * Removes all the k b articles where companyId = &#63; and status = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_S(long companyId, int status)
		throws SystemException {
		for (KBArticle kbArticle : findByC_S(companyId, status)) {
			remove(kbArticle);
		}
	}

	/**
	 * Removes all the k b articles where parentResourcePrimKey = &#63; and latest = &#63; from the database.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByP_L(long parentResourcePrimKey, boolean latest)
		throws SystemException {
		for (KBArticle kbArticle : findByP_L(parentResourcePrimKey, latest)) {
			remove(kbArticle);
		}
	}

	/**
	 * Removes all the k b articles where parentResourcePrimKey = &#63; and main = &#63; from the database.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByP_M(long parentResourcePrimKey, boolean main)
		throws SystemException {
		for (KBArticle kbArticle : findByP_M(parentResourcePrimKey, main)) {
			remove(kbArticle);
		}
	}

	/**
	 * Removes all the k b articles where parentResourcePrimKey = &#63; and status = &#63; from the database.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByP_S(long parentResourcePrimKey, int status)
		throws SystemException {
		for (KBArticle kbArticle : findByP_S(parentResourcePrimKey, status)) {
			remove(kbArticle);
		}
	}

	/**
	 * Removes all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63; from the database.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param latest the latest
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByR_G_L(long resourcePrimKey, long groupId, boolean latest)
		throws SystemException {
		for (KBArticle kbArticle : findByR_G_L(resourcePrimKey, groupId, latest)) {
			remove(kbArticle);
		}
	}

	/**
	 * Removes all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and main = &#63; from the database.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param main the main
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByR_G_M(long resourcePrimKey, long groupId, boolean main)
		throws SystemException {
		for (KBArticle kbArticle : findByR_G_M(resourcePrimKey, groupId, main)) {
			remove(kbArticle);
		}
	}

	/**
	 * Removes all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and status = &#63; from the database.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByR_G_S(long resourcePrimKey, long groupId, int status)
		throws SystemException {
		for (KBArticle kbArticle : findByR_G_S(resourcePrimKey, groupId, status)) {
			remove(kbArticle);
		}
	}

	/**
	 * Removes all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_P_L(long groupId, long parentResourcePrimKey,
		boolean latest) throws SystemException {
		for (KBArticle kbArticle : findByG_P_L(groupId, parentResourcePrimKey,
				latest)) {
			remove(kbArticle);
		}
	}

	/**
	 * Removes all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_P_M(long groupId, long parentResourcePrimKey,
		boolean main) throws SystemException {
		for (KBArticle kbArticle : findByG_P_M(groupId, parentResourcePrimKey,
				main)) {
			remove(kbArticle);
		}
	}

	/**
	 * Removes all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_P_S(long groupId, long parentResourcePrimKey,
		int status) throws SystemException {
		for (KBArticle kbArticle : findByG_P_S(groupId, parentResourcePrimKey,
				status)) {
			remove(kbArticle);
		}
	}

	/**
	 * Removes all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param latest the latest
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_P_S_L(long groupId, long parentResourcePrimKey,
		String sections, boolean latest) throws SystemException {
		for (KBArticle kbArticle : findByG_P_S_L(groupId,
				parentResourcePrimKey, sections, latest)) {
			remove(kbArticle);
		}
	}

	/**
	 * Removes all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param main the main
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_P_S_M(long groupId, long parentResourcePrimKey,
		String sections, boolean main) throws SystemException {
		for (KBArticle kbArticle : findByG_P_S_M(groupId,
				parentResourcePrimKey, sections, main)) {
			remove(kbArticle);
		}
	}

	/**
	 * Removes all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_P_S_S(long groupId, long parentResourcePrimKey,
		String sections, int status) throws SystemException {
		for (KBArticle kbArticle : findByG_P_S_S(groupId,
				parentResourcePrimKey, sections, status)) {
			remove(kbArticle);
		}
	}

	/**
	 * Removes all the k b articles from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (KBArticle kbArticle : findAll()) {
			remove(kbArticle);
		}
	}

	/**
	 * Returns the number of k b articles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

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
	 * Returns the number of k b articles where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUUID_G(String uuid, long groupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID_G,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

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
	 * Returns the number of k b articles where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid_C(String uuid, long companyId)
		throws SystemException {
		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID_C,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_C_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_C_UUID_2);
				}
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_C,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles where resourcePrimKey = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByResourcePrimKey(long resourcePrimKey)
		throws SystemException {
		Object[] finderArgs = new Object[] { resourcePrimKey };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_RESOURCEPRIMKEY,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

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
	 * Returns the number of k b articles where resourcePrimKey = &#63; and groupId = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByR_G(long resourcePrimKey, long groupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { resourcePrimKey, groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_R_G,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_G_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_R_G, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @return the number of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByR_G(long resourcePrimKey, long groupId)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByR_G(resourcePrimKey, groupId);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_R_G_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_GROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(resourcePrimKey);

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
	 * Returns the number of k b articles where resourcePrimKey = &#63; and version = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param version the version
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByR_V(long resourcePrimKey, int version)
		throws SystemException {
		Object[] finderArgs = new Object[] { resourcePrimKey, version };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_R_V,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

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
	 * Returns the number of k b articles where resourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param latest the latest
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByR_L(long resourcePrimKey, boolean latest)
		throws SystemException {
		Object[] finderArgs = new Object[] { resourcePrimKey, latest };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_R_L,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

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
	 * Returns the number of k b articles where resourcePrimKey = any &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param latest the latest
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByR_L(long[] resourcePrimKeies, boolean latest)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				StringUtil.merge(resourcePrimKeies), latest
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_L,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

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

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_R_L_LATEST_5);

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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_L,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles where resourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param main the main
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByR_M(long resourcePrimKey, boolean main)
		throws SystemException {
		Object[] finderArgs = new Object[] { resourcePrimKey, main };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_R_M,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_M_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_M_MAIN_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(main);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_R_M, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles where resourcePrimKey = any &#63; and main = &#63;.
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param main the main
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByR_M(long[] resourcePrimKeies, boolean main)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				StringUtil.merge(resourcePrimKeies), main
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_M,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			boolean conjunctionable = false;

			if ((resourcePrimKeies == null) || (resourcePrimKeies.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < resourcePrimKeies.length; i++) {
					query.append(_FINDER_COLUMN_R_M_RESOURCEPRIMKEY_5);

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

			query.append(_FINDER_COLUMN_R_M_MAIN_5);

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

				qPos.add(main);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_M,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles where resourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param status the status
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByR_S(long resourcePrimKey, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] { resourcePrimKey, status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_R_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

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
	 * Returns the number of k b articles where resourcePrimKey = any &#63; and status = &#63;.
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param status the status
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByR_S(long[] resourcePrimKeies, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				StringUtil.merge(resourcePrimKeies), status
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			boolean conjunctionable = false;

			if ((resourcePrimKeies == null) || (resourcePrimKeies.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < resourcePrimKeies.length; i++) {
					query.append(_FINDER_COLUMN_R_S_RESOURCEPRIMKEY_5);

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

			query.append(_FINDER_COLUMN_R_S_STATUS_5);

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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles where groupId = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param latest the latest
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_L(long groupId, boolean latest)
		throws SystemException {
		Object[] finderArgs = new Object[] { groupId, latest };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_L,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

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
	 * Returns the number of k b articles that the user has permission to view where groupId = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param latest the latest
	 * @return the number of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByG_L(long groupId, boolean latest)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_L(groupId, latest);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_L_GROUPID_2);

		query.append(_FINDER_COLUMN_G_L_LATEST_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

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
	 * Returns the number of k b articles where groupId = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param main the main
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_M(long groupId, boolean main) throws SystemException {
		Object[] finderArgs = new Object[] { groupId, main };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_M,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_M_GROUPID_2);

			query.append(_FINDER_COLUMN_G_M_MAIN_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(main);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_M, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles that the user has permission to view where groupId = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param main the main
	 * @return the number of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByG_M(long groupId, boolean main)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_M(groupId, main);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_M_GROUPID_2);

		query.append(_FINDER_COLUMN_G_M_MAIN_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(main);

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
	 * Returns the number of k b articles where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_S(long groupId, int status) throws SystemException {
		Object[] finderArgs = new Object[] { groupId, status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_S, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles that the user has permission to view where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the number of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByG_S(long groupId, int status)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_S(groupId, status);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_S_STATUS_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

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
	 * Returns the number of k b articles where companyId = &#63; and latest = &#63;.
	 *
	 * @param companyId the company ID
	 * @param latest the latest
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_L(long companyId, boolean latest)
		throws SystemException {
		Object[] finderArgs = new Object[] { companyId, latest };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_L,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

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
	 * Returns the number of k b articles where companyId = &#63; and main = &#63;.
	 *
	 * @param companyId the company ID
	 * @param main the main
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_M(long companyId, boolean main)
		throws SystemException {
		Object[] finderArgs = new Object[] { companyId, main };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_M,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_C_M_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_M_MAIN_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(main);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_M, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles where companyId = &#63; and status = &#63;.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_S(long companyId, int status) throws SystemException {
		Object[] finderArgs = new Object[] { companyId, status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_C_S_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_S, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles where parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByP_L(long parentResourcePrimKey, boolean latest)
		throws SystemException {
		Object[] finderArgs = new Object[] { parentResourcePrimKey, latest };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_P_L,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_P_L_PARENTRESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_P_L_LATEST_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_P_L, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles where parentResourcePrimKey = any &#63; and latest = &#63;.
	 *
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param latest the latest
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByP_L(long[] parentResourcePrimKeies, boolean latest)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				StringUtil.merge(parentResourcePrimKeies), latest
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_P_L,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			boolean conjunctionable = false;

			if ((parentResourcePrimKeies == null) ||
					(parentResourcePrimKeies.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < parentResourcePrimKeies.length; i++) {
					query.append(_FINDER_COLUMN_P_L_PARENTRESOURCEPRIMKEY_5);

					if ((i + 1) < parentResourcePrimKeies.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_P_L_LATEST_5);

			conjunctionable = true;

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (parentResourcePrimKeies != null) {
					qPos.add(parentResourcePrimKeies);
				}

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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_P_L,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles where parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByP_M(long parentResourcePrimKey, boolean main)
		throws SystemException {
		Object[] finderArgs = new Object[] { parentResourcePrimKey, main };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_P_M,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_P_M_PARENTRESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_P_M_MAIN_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentResourcePrimKey);

				qPos.add(main);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_P_M, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles where parentResourcePrimKey = any &#63; and main = &#63;.
	 *
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param main the main
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByP_M(long[] parentResourcePrimKeies, boolean main)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				StringUtil.merge(parentResourcePrimKeies), main
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_P_M,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			boolean conjunctionable = false;

			if ((parentResourcePrimKeies == null) ||
					(parentResourcePrimKeies.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < parentResourcePrimKeies.length; i++) {
					query.append(_FINDER_COLUMN_P_M_PARENTRESOURCEPRIMKEY_5);

					if ((i + 1) < parentResourcePrimKeies.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_P_M_MAIN_5);

			conjunctionable = true;

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (parentResourcePrimKeies != null) {
					qPos.add(parentResourcePrimKeies);
				}

				qPos.add(main);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_P_M,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles where parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByP_S(long parentResourcePrimKey, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] { parentResourcePrimKey, status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_P_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_P_S_PARENTRESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_P_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentResourcePrimKey);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_P_S, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles where parentResourcePrimKey = any &#63; and status = &#63;.
	 *
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param status the status
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByP_S(long[] parentResourcePrimKeies, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				StringUtil.merge(parentResourcePrimKeies), status
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_P_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			boolean conjunctionable = false;

			if ((parentResourcePrimKeies == null) ||
					(parentResourcePrimKeies.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < parentResourcePrimKeies.length; i++) {
					query.append(_FINDER_COLUMN_P_S_PARENTRESOURCEPRIMKEY_5);

					if ((i + 1) < parentResourcePrimKeies.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_P_S_STATUS_5);

			conjunctionable = true;

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (parentResourcePrimKeies != null) {
					qPos.add(parentResourcePrimKeies);
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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_P_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param latest the latest
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByR_G_L(long resourcePrimKey, long groupId, boolean latest)
		throws SystemException {
		Object[] finderArgs = new Object[] { resourcePrimKey, groupId, latest };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_R_G_L,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_G_L_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_G_L_GROUPID_2);

			query.append(_FINDER_COLUMN_R_G_L_LATEST_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_R_G_L,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param latest the latest
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByR_G_L(long[] resourcePrimKeies, long groupId,
		boolean latest) throws SystemException {
		Object[] finderArgs = new Object[] {
				StringUtil.merge(resourcePrimKeies), groupId, latest
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_G_L,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			boolean conjunctionable = false;

			if ((resourcePrimKeies == null) || (resourcePrimKeies.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < resourcePrimKeies.length; i++) {
					query.append(_FINDER_COLUMN_R_G_L_RESOURCEPRIMKEY_5);

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

			query.append(_FINDER_COLUMN_R_G_L_GROUPID_5);

			conjunctionable = true;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_R_G_L_LATEST_5);

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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_G_L,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param latest the latest
	 * @return the number of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByR_G_L(long resourcePrimKey, long groupId,
		boolean latest) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByR_G_L(resourcePrimKey, groupId, latest);
		}

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_R_G_L_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_L_GROUPID_2);

		query.append(_FINDER_COLUMN_R_G_L_LATEST_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(resourcePrimKey);

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
	 * Returns the number of k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param latest the latest
	 * @return the number of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByR_G_L(long[] resourcePrimKeies, long groupId,
		boolean latest) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByR_G_L(resourcePrimKeies, groupId, latest);
		}

		StringBundler query = new StringBundler();

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		boolean conjunctionable = false;

		if ((resourcePrimKeies == null) || (resourcePrimKeies.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < resourcePrimKeies.length; i++) {
				query.append(_FINDER_COLUMN_R_G_L_RESOURCEPRIMKEY_5);

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

		query.append(_FINDER_COLUMN_R_G_L_GROUPID_5);

		conjunctionable = true;

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_R_G_L_LATEST_5);

		conjunctionable = true;

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

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
	 * Returns the number of k b articles where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param main the main
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByR_G_M(long resourcePrimKey, long groupId, boolean main)
		throws SystemException {
		Object[] finderArgs = new Object[] { resourcePrimKey, groupId, main };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_R_G_M,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_G_M_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_G_M_GROUPID_2);

			query.append(_FINDER_COLUMN_R_G_M_MAIN_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(groupId);

				qPos.add(main);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_R_G_M,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param main the main
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByR_G_M(long[] resourcePrimKeies, long groupId, boolean main)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				StringUtil.merge(resourcePrimKeies), groupId, main
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_G_M,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			boolean conjunctionable = false;

			if ((resourcePrimKeies == null) || (resourcePrimKeies.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < resourcePrimKeies.length; i++) {
					query.append(_FINDER_COLUMN_R_G_M_RESOURCEPRIMKEY_5);

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

			query.append(_FINDER_COLUMN_R_G_M_GROUPID_5);

			conjunctionable = true;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_R_G_M_MAIN_5);

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

				qPos.add(main);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_G_M,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param main the main
	 * @return the number of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByR_G_M(long resourcePrimKey, long groupId,
		boolean main) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByR_G_M(resourcePrimKey, groupId, main);
		}

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_R_G_M_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_M_GROUPID_2);

		query.append(_FINDER_COLUMN_R_G_M_MAIN_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(resourcePrimKey);

			qPos.add(groupId);

			qPos.add(main);

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
	 * Returns the number of k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param main the main
	 * @return the number of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByR_G_M(long[] resourcePrimKeies, long groupId,
		boolean main) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByR_G_M(resourcePrimKeies, groupId, main);
		}

		StringBundler query = new StringBundler();

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		boolean conjunctionable = false;

		if ((resourcePrimKeies == null) || (resourcePrimKeies.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < resourcePrimKeies.length; i++) {
				query.append(_FINDER_COLUMN_R_G_M_RESOURCEPRIMKEY_5);

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

		query.append(_FINDER_COLUMN_R_G_M_GROUPID_5);

		conjunctionable = true;

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_R_G_M_MAIN_5);

		conjunctionable = true;

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

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

			qPos.add(main);

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
	 * Returns the number of k b articles where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param status the status
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByR_G_S(long resourcePrimKey, long groupId, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] { resourcePrimKey, groupId, status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_R_G_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_G_S_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_G_S_GROUPID_2);

			query.append(_FINDER_COLUMN_R_G_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(groupId);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_R_G_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param status the status
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByR_G_S(long[] resourcePrimKeies, long groupId, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				StringUtil.merge(resourcePrimKeies), groupId, status
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_G_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			boolean conjunctionable = false;

			if ((resourcePrimKeies == null) || (resourcePrimKeies.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < resourcePrimKeies.length; i++) {
					query.append(_FINDER_COLUMN_R_G_S_RESOURCEPRIMKEY_5);

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

			query.append(_FINDER_COLUMN_R_G_S_GROUPID_5);

			conjunctionable = true;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_R_G_S_STATUS_5);

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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_G_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param status the status
	 * @return the number of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByR_G_S(long resourcePrimKey, long groupId, int status)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByR_G_S(resourcePrimKey, groupId, status);
		}

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_R_G_S_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_S_GROUPID_2);

		query.append(_FINDER_COLUMN_R_G_S_STATUS_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(resourcePrimKey);

			qPos.add(groupId);

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
	 * Returns the number of k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param status the status
	 * @return the number of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByR_G_S(long[] resourcePrimKeies, long groupId,
		int status) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByR_G_S(resourcePrimKeies, groupId, status);
		}

		StringBundler query = new StringBundler();

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		boolean conjunctionable = false;

		if ((resourcePrimKeies == null) || (resourcePrimKeies.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < resourcePrimKeies.length; i++) {
				query.append(_FINDER_COLUMN_R_G_S_RESOURCEPRIMKEY_5);

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

		query.append(_FINDER_COLUMN_R_G_S_GROUPID_5);

		conjunctionable = true;

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_R_G_S_STATUS_5);

		conjunctionable = true;

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

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
	 * Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_P_L(long groupId, long parentResourcePrimKey,
		boolean latest) throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, parentResourcePrimKey, latest
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_P_L,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

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
	 * Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param latest the latest
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_P_L(long groupId, long[] parentResourcePrimKeies,
		boolean latest) throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, StringUtil.merge(parentResourcePrimKeies), latest
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_P_L,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

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

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_L_LATEST_5);

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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_P_L,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @return the number of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByG_P_L(long groupId, long parentResourcePrimKey,
		boolean latest) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_P_L(groupId, parentResourcePrimKey, latest);
		}

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_P_L_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_L_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_G_P_L_LATEST_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

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
	 * Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param latest the latest
	 * @return the number of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByG_P_L(long groupId, long[] parentResourcePrimKeies,
		boolean latest) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_P_L(groupId, parentResourcePrimKeies, latest);
		}

		StringBundler query = new StringBundler();

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

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

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_L_LATEST_5);

		conjunctionable = true;

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

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
	 * Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_P_M(long groupId, long parentResourcePrimKey,
		boolean main) throws SystemException {
		Object[] finderArgs = new Object[] { groupId, parentResourcePrimKey, main };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_P_M,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_P_M_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_M_PARENTRESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_G_P_M_MAIN_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentResourcePrimKey);

				qPos.add(main);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_P_M,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param main the main
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_P_M(long groupId, long[] parentResourcePrimKeies,
		boolean main) throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, StringUtil.merge(parentResourcePrimKeies), main
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_P_M,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_M_GROUPID_5);

			conjunctionable = true;

			if ((parentResourcePrimKeies == null) ||
					(parentResourcePrimKeies.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < parentResourcePrimKeies.length; i++) {
					query.append(_FINDER_COLUMN_G_P_M_PARENTRESOURCEPRIMKEY_5);

					if ((i + 1) < parentResourcePrimKeies.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_M_MAIN_5);

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

				qPos.add(main);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_P_M,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @return the number of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByG_P_M(long groupId, long parentResourcePrimKey,
		boolean main) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_P_M(groupId, parentResourcePrimKey, main);
		}

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_P_M_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_M_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_G_P_M_MAIN_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentResourcePrimKey);

			qPos.add(main);

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
	 * Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param main the main
	 * @return the number of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByG_P_M(long groupId, long[] parentResourcePrimKeies,
		boolean main) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_P_M(groupId, parentResourcePrimKeies, main);
		}

		StringBundler query = new StringBundler();

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		boolean conjunctionable = false;

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_M_GROUPID_5);

		conjunctionable = true;

		if ((parentResourcePrimKeies == null) ||
				(parentResourcePrimKeies.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < parentResourcePrimKeies.length; i++) {
				query.append(_FINDER_COLUMN_G_P_M_PARENTRESOURCEPRIMKEY_5);

				if ((i + 1) < parentResourcePrimKeies.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			conjunctionable = true;
		}

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_M_MAIN_5);

		conjunctionable = true;

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

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

			qPos.add(main);

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
	 * Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_P_S(long groupId, long parentResourcePrimKey, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, parentResourcePrimKey, status
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_P_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_P_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_S_PARENTRESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_G_P_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentResourcePrimKey);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_P_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param status the status
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_P_S(long groupId, long[] parentResourcePrimKeies,
		int status) throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, StringUtil.merge(parentResourcePrimKeies), status
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_P_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_S_GROUPID_5);

			conjunctionable = true;

			if ((parentResourcePrimKeies == null) ||
					(parentResourcePrimKeies.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < parentResourcePrimKeies.length; i++) {
					query.append(_FINDER_COLUMN_G_P_S_PARENTRESOURCEPRIMKEY_5);

					if ((i + 1) < parentResourcePrimKeies.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_S_STATUS_5);

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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_P_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @return the number of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByG_P_S(long groupId, long parentResourcePrimKey,
		int status) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_P_S(groupId, parentResourcePrimKey, status);
		}

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_P_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_S_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_G_P_S_STATUS_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentResourcePrimKey);

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
	 * Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param status the status
	 * @return the number of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByG_P_S(long groupId, long[] parentResourcePrimKeies,
		int status) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_P_S(groupId, parentResourcePrimKeies, status);
		}

		StringBundler query = new StringBundler();

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		boolean conjunctionable = false;

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_S_GROUPID_5);

		conjunctionable = true;

		if ((parentResourcePrimKeies == null) ||
				(parentResourcePrimKeies.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < parentResourcePrimKeies.length; i++) {
				query.append(_FINDER_COLUMN_G_P_S_PARENTRESOURCEPRIMKEY_5);

				if ((i + 1) < parentResourcePrimKeies.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			conjunctionable = true;
		}

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_S_STATUS_5);

		conjunctionable = true;

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

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
	 * Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param latest the latest
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_P_S_L(long groupId, long parentResourcePrimKey,
		String sections, boolean latest) throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, parentResourcePrimKey, sections, latest
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_P_S_L,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_P_S_L_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_S_L_PARENTRESOURCEPRIMKEY_2);

			if (sections == null) {
				query.append(_FINDER_COLUMN_G_P_S_L_SECTIONS_1);
			}
			else {
				if (sections.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_P_S_L_SECTIONS_3);
				}
				else {
					query.append(_FINDER_COLUMN_G_P_S_L_SECTIONS_2);
				}
			}

			query.append(_FINDER_COLUMN_G_P_S_L_LATEST_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentResourcePrimKey);

				if (sections != null) {
					qPos.add(sections);
				}

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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_P_S_L,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sectionses the sectionses
	 * @param latest the latest
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_P_S_L(long groupId, long parentResourcePrimKey,
		String[] sectionses, boolean latest) throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, parentResourcePrimKey, StringUtil.merge(sectionses),
				latest
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_P_S_L,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_S_L_GROUPID_5);

			conjunctionable = true;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_S_L_PARENTRESOURCEPRIMKEY_5);

			conjunctionable = true;

			if ((sectionses == null) || (sectionses.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < sectionses.length; i++) {
					String sections = sectionses[i];

					if (sections == null) {
						query.append(_FINDER_COLUMN_G_P_S_L_SECTIONS_4);
					}
					else {
						if (sections.equals(StringPool.BLANK)) {
							query.append(_FINDER_COLUMN_G_P_S_L_SECTIONS_6);
						}
						else {
							query.append(_FINDER_COLUMN_G_P_S_L_SECTIONS_5);
						}
					}

					if ((i + 1) < sectionses.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_S_L_LATEST_5);

			conjunctionable = true;

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentResourcePrimKey);

				if (sectionses != null) {
					qPos.add(sectionses);
				}

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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_P_S_L,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param latest the latest
	 * @return the number of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByG_P_S_L(long groupId, long parentResourcePrimKey,
		String sections, boolean latest) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_P_S_L(groupId, parentResourcePrimKey, sections,
				latest);
		}

		StringBundler query = new StringBundler(5);

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_P_S_L_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_S_L_PARENTRESOURCEPRIMKEY_2);

		if (sections == null) {
			query.append(_FINDER_COLUMN_G_P_S_L_SECTIONS_1);
		}
		else {
			if (sections.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_P_S_L_SECTIONS_3);
			}
			else {
				query.append(_FINDER_COLUMN_G_P_S_L_SECTIONS_2);
			}
		}

		query.append(_FINDER_COLUMN_G_P_S_L_LATEST_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentResourcePrimKey);

			if (sections != null) {
				qPos.add(sections);
			}

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
	 * Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sectionses the sectionses
	 * @param latest the latest
	 * @return the number of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByG_P_S_L(long groupId, long parentResourcePrimKey,
		String[] sectionses, boolean latest) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_P_S_L(groupId, parentResourcePrimKey, sectionses,
				latest);
		}

		StringBundler query = new StringBundler();

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		boolean conjunctionable = false;

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_S_L_GROUPID_5);

		conjunctionable = true;

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_S_L_PARENTRESOURCEPRIMKEY_5);

		conjunctionable = true;

		if ((sectionses == null) || (sectionses.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < sectionses.length; i++) {
				String sections = sectionses[i];

				if (sections == null) {
					query.append(_FINDER_COLUMN_G_P_S_L_SECTIONS_4);
				}
				else {
					if (sections.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_G_P_S_L_SECTIONS_6);
					}
					else {
						query.append(_FINDER_COLUMN_G_P_S_L_SECTIONS_5);
					}
				}

				if ((i + 1) < sectionses.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			conjunctionable = true;
		}

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_S_L_LATEST_5);

		conjunctionable = true;

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentResourcePrimKey);

			if (sectionses != null) {
				qPos.add(sectionses);
			}

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
	 * Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param main the main
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_P_S_M(long groupId, long parentResourcePrimKey,
		String sections, boolean main) throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, parentResourcePrimKey, sections, main
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_P_S_M,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_P_S_M_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_S_M_PARENTRESOURCEPRIMKEY_2);

			if (sections == null) {
				query.append(_FINDER_COLUMN_G_P_S_M_SECTIONS_1);
			}
			else {
				if (sections.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_P_S_M_SECTIONS_3);
				}
				else {
					query.append(_FINDER_COLUMN_G_P_S_M_SECTIONS_2);
				}
			}

			query.append(_FINDER_COLUMN_G_P_S_M_MAIN_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentResourcePrimKey);

				if (sections != null) {
					qPos.add(sections);
				}

				qPos.add(main);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_P_S_M,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sectionses the sectionses
	 * @param main the main
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_P_S_M(long groupId, long parentResourcePrimKey,
		String[] sectionses, boolean main) throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, parentResourcePrimKey, StringUtil.merge(sectionses),
				main
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_P_S_M,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_S_M_GROUPID_5);

			conjunctionable = true;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_S_M_PARENTRESOURCEPRIMKEY_5);

			conjunctionable = true;

			if ((sectionses == null) || (sectionses.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < sectionses.length; i++) {
					String sections = sectionses[i];

					if (sections == null) {
						query.append(_FINDER_COLUMN_G_P_S_M_SECTIONS_4);
					}
					else {
						if (sections.equals(StringPool.BLANK)) {
							query.append(_FINDER_COLUMN_G_P_S_M_SECTIONS_6);
						}
						else {
							query.append(_FINDER_COLUMN_G_P_S_M_SECTIONS_5);
						}
					}

					if ((i + 1) < sectionses.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_S_M_MAIN_5);

			conjunctionable = true;

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentResourcePrimKey);

				if (sectionses != null) {
					qPos.add(sectionses);
				}

				qPos.add(main);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_P_S_M,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param main the main
	 * @return the number of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByG_P_S_M(long groupId, long parentResourcePrimKey,
		String sections, boolean main) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_P_S_M(groupId, parentResourcePrimKey, sections, main);
		}

		StringBundler query = new StringBundler(5);

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_P_S_M_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_S_M_PARENTRESOURCEPRIMKEY_2);

		if (sections == null) {
			query.append(_FINDER_COLUMN_G_P_S_M_SECTIONS_1);
		}
		else {
			if (sections.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_P_S_M_SECTIONS_3);
			}
			else {
				query.append(_FINDER_COLUMN_G_P_S_M_SECTIONS_2);
			}
		}

		query.append(_FINDER_COLUMN_G_P_S_M_MAIN_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentResourcePrimKey);

			if (sections != null) {
				qPos.add(sections);
			}

			qPos.add(main);

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
	 * Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sectionses the sectionses
	 * @param main the main
	 * @return the number of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByG_P_S_M(long groupId, long parentResourcePrimKey,
		String[] sectionses, boolean main) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_P_S_M(groupId, parentResourcePrimKey, sectionses,
				main);
		}

		StringBundler query = new StringBundler();

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		boolean conjunctionable = false;

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_S_M_GROUPID_5);

		conjunctionable = true;

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_S_M_PARENTRESOURCEPRIMKEY_5);

		conjunctionable = true;

		if ((sectionses == null) || (sectionses.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < sectionses.length; i++) {
				String sections = sectionses[i];

				if (sections == null) {
					query.append(_FINDER_COLUMN_G_P_S_M_SECTIONS_4);
				}
				else {
					if (sections.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_G_P_S_M_SECTIONS_6);
					}
					else {
						query.append(_FINDER_COLUMN_G_P_S_M_SECTIONS_5);
					}
				}

				if ((i + 1) < sectionses.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			conjunctionable = true;
		}

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_S_M_MAIN_5);

		conjunctionable = true;

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentResourcePrimKey);

			if (sectionses != null) {
				qPos.add(sectionses);
			}

			qPos.add(main);

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
	 * Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param status the status
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_P_S_S(long groupId, long parentResourcePrimKey,
		String sections, int status) throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, parentResourcePrimKey, sections, status
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_P_S_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_P_S_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_S_S_PARENTRESOURCEPRIMKEY_2);

			if (sections == null) {
				query.append(_FINDER_COLUMN_G_P_S_S_SECTIONS_1);
			}
			else {
				if (sections.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_P_S_S_SECTIONS_3);
				}
				else {
					query.append(_FINDER_COLUMN_G_P_S_S_SECTIONS_2);
				}
			}

			query.append(_FINDER_COLUMN_G_P_S_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentResourcePrimKey);

				if (sections != null) {
					qPos.add(sections);
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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_P_S_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sectionses the sectionses
	 * @param status the status
	 * @return the number of matching k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_P_S_S(long groupId, long parentResourcePrimKey,
		String[] sectionses, int status) throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, parentResourcePrimKey, StringUtil.merge(sectionses),
				status
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_P_S_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_S_S_GROUPID_5);

			conjunctionable = true;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_S_S_PARENTRESOURCEPRIMKEY_5);

			conjunctionable = true;

			if ((sectionses == null) || (sectionses.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < sectionses.length; i++) {
					String sections = sectionses[i];

					if (sections == null) {
						query.append(_FINDER_COLUMN_G_P_S_S_SECTIONS_4);
					}
					else {
						if (sections.equals(StringPool.BLANK)) {
							query.append(_FINDER_COLUMN_G_P_S_S_SECTIONS_6);
						}
						else {
							query.append(_FINDER_COLUMN_G_P_S_S_SECTIONS_5);
						}
					}

					if ((i + 1) < sectionses.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_S_S_STATUS_5);

			conjunctionable = true;

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentResourcePrimKey);

				if (sectionses != null) {
					qPos.add(sectionses);
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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_P_S_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sections the sections
	 * @param status the status
	 * @return the number of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByG_P_S_S(long groupId, long parentResourcePrimKey,
		String sections, int status) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_P_S_S(groupId, parentResourcePrimKey, sections,
				status);
		}

		StringBundler query = new StringBundler(5);

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_P_S_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_S_S_PARENTRESOURCEPRIMKEY_2);

		if (sections == null) {
			query.append(_FINDER_COLUMN_G_P_S_S_SECTIONS_1);
		}
		else {
			if (sections.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_P_S_S_SECTIONS_3);
			}
			else {
				query.append(_FINDER_COLUMN_G_P_S_S_SECTIONS_2);
			}
		}

		query.append(_FINDER_COLUMN_G_P_S_S_STATUS_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentResourcePrimKey);

			if (sections != null) {
				qPos.add(sections);
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
	 * Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param sectionses the sectionses
	 * @param status the status
	 * @return the number of matching k b articles that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByG_P_S_S(long groupId, long parentResourcePrimKey,
		String[] sectionses, int status) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_P_S_S(groupId, parentResourcePrimKey, sectionses,
				status);
		}

		StringBundler query = new StringBundler();

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		boolean conjunctionable = false;

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_S_S_GROUPID_5);

		conjunctionable = true;

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_S_S_PARENTRESOURCEPRIMKEY_5);

		conjunctionable = true;

		if ((sectionses == null) || (sectionses.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < sectionses.length; i++) {
				String sections = sectionses[i];

				if (sections == null) {
					query.append(_FINDER_COLUMN_G_P_S_S_SECTIONS_4);
				}
				else {
					if (sections.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_G_P_S_S_SECTIONS_6);
					}
					else {
						query.append(_FINDER_COLUMN_G_P_S_S_SECTIONS_5);
					}
				}

				if ((i + 1) < sectionses.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			conjunctionable = true;
		}

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_S_S_STATUS_5);

		conjunctionable = true;

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentResourcePrimKey);

			if (sectionses != null) {
				qPos.add(sectionses);
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
	 * Returns the number of k b articles.
	 *
	 * @return the number of k b articles
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_KBARTICLE);

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
	 * Initializes the k b article persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.knowledgebase.model.KBArticle")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<KBArticle>> listenersList = new ArrayList<ModelListener<KBArticle>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<KBArticle>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(KBArticleImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = KBArticlePersistence.class)
	protected KBArticlePersistence kbArticlePersistence;
	@BeanReference(type = KBCommentPersistence.class)
	protected KBCommentPersistence kbCommentPersistence;
	@BeanReference(type = KBTemplatePersistence.class)
	protected KBTemplatePersistence kbTemplatePersistence;
	@BeanReference(type = CompanyPersistence.class)
	protected CompanyPersistence companyPersistence;
	@BeanReference(type = GroupPersistence.class)
	protected GroupPersistence groupPersistence;
	@BeanReference(type = LayoutPersistence.class)
	protected LayoutPersistence layoutPersistence;
	@BeanReference(type = PortletPreferencesPersistence.class)
	protected PortletPreferencesPersistence portletPreferencesPersistence;
	@BeanReference(type = SubscriptionPersistence.class)
	protected SubscriptionPersistence subscriptionPersistence;
	@BeanReference(type = TicketPersistence.class)
	protected TicketPersistence ticketPersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = WorkflowInstanceLinkPersistence.class)
	protected WorkflowInstanceLinkPersistence workflowInstanceLinkPersistence;
	@BeanReference(type = AssetEntryPersistence.class)
	protected AssetEntryPersistence assetEntryPersistence;
	@BeanReference(type = RatingsStatsPersistence.class)
	protected RatingsStatsPersistence ratingsStatsPersistence;
	@BeanReference(type = SocialActivityPersistence.class)
	protected SocialActivityPersistence socialActivityPersistence;
	private static final String _SQL_SELECT_KBARTICLE = "SELECT kbArticle FROM KBArticle kbArticle";
	private static final String _SQL_SELECT_KBARTICLE_WHERE = "SELECT kbArticle FROM KBArticle kbArticle WHERE ";
	private static final String _SQL_COUNT_KBARTICLE = "SELECT COUNT(kbArticle) FROM KBArticle kbArticle";
	private static final String _SQL_COUNT_KBARTICLE_WHERE = "SELECT COUNT(kbArticle) FROM KBArticle kbArticle WHERE ";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "kbArticle.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "kbArticle.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(kbArticle.uuid IS NULL OR kbArticle.uuid = ?)";
	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "kbArticle.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "kbArticle.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(kbArticle.uuid IS NULL OR kbArticle.uuid = ?) AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "kbArticle.groupId = ?";
	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "kbArticle.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "kbArticle.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(kbArticle.uuid IS NULL OR kbArticle.uuid = ?) AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "kbArticle.companyId = ?";
	private static final String _FINDER_COLUMN_RESOURCEPRIMKEY_RESOURCEPRIMKEY_2 =
		"kbArticle.resourcePrimKey = ?";
	private static final String _FINDER_COLUMN_R_G_RESOURCEPRIMKEY_2 = "kbArticle.resourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_R_G_GROUPID_2 = "kbArticle.groupId = ?";
	private static final String _FINDER_COLUMN_R_V_RESOURCEPRIMKEY_2 = "kbArticle.resourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_R_V_VERSION_2 = "kbArticle.version = ?";
	private static final String _FINDER_COLUMN_R_L_RESOURCEPRIMKEY_2 = "kbArticle.resourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_R_L_RESOURCEPRIMKEY_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_R_L_RESOURCEPRIMKEY_2) + ")";
	private static final String _FINDER_COLUMN_R_L_LATEST_2 = "kbArticle.latest = ?";
	private static final String _FINDER_COLUMN_R_L_LATEST_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_R_L_LATEST_2) + ")";
	private static final String _FINDER_COLUMN_R_M_RESOURCEPRIMKEY_2 = "kbArticle.resourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_R_M_RESOURCEPRIMKEY_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_R_M_RESOURCEPRIMKEY_2) + ")";
	private static final String _FINDER_COLUMN_R_M_MAIN_2 = "kbArticle.main = ?";
	private static final String _FINDER_COLUMN_R_M_MAIN_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_R_M_MAIN_2) + ")";
	private static final String _FINDER_COLUMN_R_S_RESOURCEPRIMKEY_2 = "kbArticle.resourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_R_S_RESOURCEPRIMKEY_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_R_S_RESOURCEPRIMKEY_2) + ")";
	private static final String _FINDER_COLUMN_R_S_STATUS_2 = "kbArticle.status = ?";
	private static final String _FINDER_COLUMN_R_S_STATUS_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_R_S_STATUS_2) + ")";
	private static final String _FINDER_COLUMN_G_L_GROUPID_2 = "kbArticle.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_L_LATEST_2 = "kbArticle.latest = ?";
	private static final String _FINDER_COLUMN_G_M_GROUPID_2 = "kbArticle.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_M_MAIN_2 = "kbArticle.main = ?";
	private static final String _FINDER_COLUMN_G_S_GROUPID_2 = "kbArticle.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_S_STATUS_2 = "kbArticle.status = ?";
	private static final String _FINDER_COLUMN_C_L_COMPANYID_2 = "kbArticle.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_L_LATEST_2 = "kbArticle.latest = ?";
	private static final String _FINDER_COLUMN_C_M_COMPANYID_2 = "kbArticle.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_M_MAIN_2 = "kbArticle.main = ?";
	private static final String _FINDER_COLUMN_C_S_COMPANYID_2 = "kbArticle.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_S_STATUS_2 = "kbArticle.status = ?";
	private static final String _FINDER_COLUMN_P_L_PARENTRESOURCEPRIMKEY_2 = "kbArticle.parentResourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_P_L_PARENTRESOURCEPRIMKEY_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_P_L_PARENTRESOURCEPRIMKEY_2) + ")";
	private static final String _FINDER_COLUMN_P_L_LATEST_2 = "kbArticle.latest = ?";
	private static final String _FINDER_COLUMN_P_L_LATEST_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_P_L_LATEST_2) + ")";
	private static final String _FINDER_COLUMN_P_M_PARENTRESOURCEPRIMKEY_2 = "kbArticle.parentResourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_P_M_PARENTRESOURCEPRIMKEY_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_P_M_PARENTRESOURCEPRIMKEY_2) + ")";
	private static final String _FINDER_COLUMN_P_M_MAIN_2 = "kbArticle.main = ?";
	private static final String _FINDER_COLUMN_P_M_MAIN_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_P_M_MAIN_2) + ")";
	private static final String _FINDER_COLUMN_P_S_PARENTRESOURCEPRIMKEY_2 = "kbArticle.parentResourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_P_S_PARENTRESOURCEPRIMKEY_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_P_S_PARENTRESOURCEPRIMKEY_2) + ")";
	private static final String _FINDER_COLUMN_P_S_STATUS_2 = "kbArticle.status = ?";
	private static final String _FINDER_COLUMN_P_S_STATUS_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_P_S_STATUS_2) + ")";
	private static final String _FINDER_COLUMN_R_G_L_RESOURCEPRIMKEY_2 = "kbArticle.resourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_R_G_L_RESOURCEPRIMKEY_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_R_G_L_RESOURCEPRIMKEY_2) + ")";
	private static final String _FINDER_COLUMN_R_G_L_GROUPID_2 = "kbArticle.groupId = ? AND ";
	private static final String _FINDER_COLUMN_R_G_L_GROUPID_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_R_G_L_GROUPID_2) + ")";
	private static final String _FINDER_COLUMN_R_G_L_LATEST_2 = "kbArticle.latest = ?";
	private static final String _FINDER_COLUMN_R_G_L_LATEST_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_R_G_L_LATEST_2) + ")";
	private static final String _FINDER_COLUMN_R_G_M_RESOURCEPRIMKEY_2 = "kbArticle.resourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_R_G_M_RESOURCEPRIMKEY_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_R_G_M_RESOURCEPRIMKEY_2) + ")";
	private static final String _FINDER_COLUMN_R_G_M_GROUPID_2 = "kbArticle.groupId = ? AND ";
	private static final String _FINDER_COLUMN_R_G_M_GROUPID_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_R_G_M_GROUPID_2) + ")";
	private static final String _FINDER_COLUMN_R_G_M_MAIN_2 = "kbArticle.main = ?";
	private static final String _FINDER_COLUMN_R_G_M_MAIN_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_R_G_M_MAIN_2) + ")";
	private static final String _FINDER_COLUMN_R_G_S_RESOURCEPRIMKEY_2 = "kbArticle.resourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_R_G_S_RESOURCEPRIMKEY_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_R_G_S_RESOURCEPRIMKEY_2) + ")";
	private static final String _FINDER_COLUMN_R_G_S_GROUPID_2 = "kbArticle.groupId = ? AND ";
	private static final String _FINDER_COLUMN_R_G_S_GROUPID_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_R_G_S_GROUPID_2) + ")";
	private static final String _FINDER_COLUMN_R_G_S_STATUS_2 = "kbArticle.status = ?";
	private static final String _FINDER_COLUMN_R_G_S_STATUS_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_R_G_S_STATUS_2) + ")";
	private static final String _FINDER_COLUMN_G_P_L_GROUPID_2 = "kbArticle.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_L_GROUPID_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_P_L_GROUPID_2) + ")";
	private static final String _FINDER_COLUMN_G_P_L_PARENTRESOURCEPRIMKEY_2 = "kbArticle.parentResourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_G_P_L_PARENTRESOURCEPRIMKEY_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_P_L_PARENTRESOURCEPRIMKEY_2) + ")";
	private static final String _FINDER_COLUMN_G_P_L_LATEST_2 = "kbArticle.latest = ?";
	private static final String _FINDER_COLUMN_G_P_L_LATEST_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_P_L_LATEST_2) + ")";
	private static final String _FINDER_COLUMN_G_P_M_GROUPID_2 = "kbArticle.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_M_GROUPID_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_P_M_GROUPID_2) + ")";
	private static final String _FINDER_COLUMN_G_P_M_PARENTRESOURCEPRIMKEY_2 = "kbArticle.parentResourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_G_P_M_PARENTRESOURCEPRIMKEY_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_P_M_PARENTRESOURCEPRIMKEY_2) + ")";
	private static final String _FINDER_COLUMN_G_P_M_MAIN_2 = "kbArticle.main = ?";
	private static final String _FINDER_COLUMN_G_P_M_MAIN_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_P_M_MAIN_2) + ")";
	private static final String _FINDER_COLUMN_G_P_S_GROUPID_2 = "kbArticle.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_S_GROUPID_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_P_S_GROUPID_2) + ")";
	private static final String _FINDER_COLUMN_G_P_S_PARENTRESOURCEPRIMKEY_2 = "kbArticle.parentResourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_G_P_S_PARENTRESOURCEPRIMKEY_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_P_S_PARENTRESOURCEPRIMKEY_2) + ")";
	private static final String _FINDER_COLUMN_G_P_S_STATUS_2 = "kbArticle.status = ?";
	private static final String _FINDER_COLUMN_G_P_S_STATUS_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_P_S_STATUS_2) + ")";
	private static final String _FINDER_COLUMN_G_P_S_L_GROUPID_2 = "kbArticle.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_S_L_GROUPID_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_P_S_L_GROUPID_2) + ")";
	private static final String _FINDER_COLUMN_G_P_S_L_PARENTRESOURCEPRIMKEY_2 = "kbArticle.parentResourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_G_P_S_L_PARENTRESOURCEPRIMKEY_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_P_S_L_PARENTRESOURCEPRIMKEY_2) +
		")";
	private static final String _FINDER_COLUMN_G_P_S_L_SECTIONS_1 = "kbArticle.sections LIKE NULL AND ";
	private static final String _FINDER_COLUMN_G_P_S_L_SECTIONS_2 = "kbArticle.sections LIKE ? AND ";
	private static final String _FINDER_COLUMN_G_P_S_L_SECTIONS_3 = "(kbArticle.sections IS NULL OR kbArticle.sections LIKE ?) AND ";
	private static final String _FINDER_COLUMN_G_P_S_L_SECTIONS_4 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_P_S_L_SECTIONS_1) + ")";
	private static final String _FINDER_COLUMN_G_P_S_L_SECTIONS_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_P_S_L_SECTIONS_2) + ")";
	private static final String _FINDER_COLUMN_G_P_S_L_SECTIONS_6 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_P_S_L_SECTIONS_3) + ")";
	private static final String _FINDER_COLUMN_G_P_S_L_LATEST_2 = "kbArticle.latest = ?";
	private static final String _FINDER_COLUMN_G_P_S_L_LATEST_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_P_S_L_LATEST_2) + ")";
	private static final String _FINDER_COLUMN_G_P_S_M_GROUPID_2 = "kbArticle.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_S_M_GROUPID_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_P_S_M_GROUPID_2) + ")";
	private static final String _FINDER_COLUMN_G_P_S_M_PARENTRESOURCEPRIMKEY_2 = "kbArticle.parentResourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_G_P_S_M_PARENTRESOURCEPRIMKEY_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_P_S_M_PARENTRESOURCEPRIMKEY_2) +
		")";
	private static final String _FINDER_COLUMN_G_P_S_M_SECTIONS_1 = "kbArticle.sections LIKE NULL AND ";
	private static final String _FINDER_COLUMN_G_P_S_M_SECTIONS_2 = "kbArticle.sections LIKE ? AND ";
	private static final String _FINDER_COLUMN_G_P_S_M_SECTIONS_3 = "(kbArticle.sections IS NULL OR kbArticle.sections LIKE ?) AND ";
	private static final String _FINDER_COLUMN_G_P_S_M_SECTIONS_4 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_P_S_M_SECTIONS_1) + ")";
	private static final String _FINDER_COLUMN_G_P_S_M_SECTIONS_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_P_S_M_SECTIONS_2) + ")";
	private static final String _FINDER_COLUMN_G_P_S_M_SECTIONS_6 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_P_S_M_SECTIONS_3) + ")";
	private static final String _FINDER_COLUMN_G_P_S_M_MAIN_2 = "kbArticle.main = ?";
	private static final String _FINDER_COLUMN_G_P_S_M_MAIN_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_P_S_M_MAIN_2) + ")";
	private static final String _FINDER_COLUMN_G_P_S_S_GROUPID_2 = "kbArticle.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_S_S_GROUPID_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_P_S_S_GROUPID_2) + ")";
	private static final String _FINDER_COLUMN_G_P_S_S_PARENTRESOURCEPRIMKEY_2 = "kbArticle.parentResourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_G_P_S_S_PARENTRESOURCEPRIMKEY_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_P_S_S_PARENTRESOURCEPRIMKEY_2) +
		")";
	private static final String _FINDER_COLUMN_G_P_S_S_SECTIONS_1 = "kbArticle.sections LIKE NULL AND ";
	private static final String _FINDER_COLUMN_G_P_S_S_SECTIONS_2 = "kbArticle.sections LIKE ? AND ";
	private static final String _FINDER_COLUMN_G_P_S_S_SECTIONS_3 = "(kbArticle.sections IS NULL OR kbArticle.sections LIKE ?) AND ";
	private static final String _FINDER_COLUMN_G_P_S_S_SECTIONS_4 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_P_S_S_SECTIONS_1) + ")";
	private static final String _FINDER_COLUMN_G_P_S_S_SECTIONS_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_P_S_S_SECTIONS_2) + ")";
	private static final String _FINDER_COLUMN_G_P_S_S_SECTIONS_6 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_P_S_S_SECTIONS_3) + ")";
	private static final String _FINDER_COLUMN_G_P_S_S_STATUS_2 = "kbArticle.status = ?";
	private static final String _FINDER_COLUMN_G_P_S_S_STATUS_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_P_S_S_STATUS_2) + ")";

	private static String _removeConjunction(String sql) {
		int pos = sql.indexOf(" AND ");

		if (pos != -1) {
			sql = sql.substring(0, pos);
		}

		return sql;
	}

	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "kbArticle.rootResourcePrimKey";
	private static final String _FILTER_SQL_SELECT_KBARTICLE_WHERE = "SELECT DISTINCT {kbArticle.*} FROM KBArticle kbArticle WHERE ";
	private static final String _FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {KBArticle.*} FROM (SELECT DISTINCT kbArticle.kbArticleId FROM KBArticle kbArticle WHERE ";
	private static final String _FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN KBArticle ON TEMP_TABLE.kbArticleId = KBArticle.kbArticleId";
	private static final String _FILTER_SQL_COUNT_KBARTICLE_WHERE = "SELECT COUNT(DISTINCT kbArticle.kbArticleId) AS COUNT_VALUE FROM KBArticle kbArticle WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "kbArticle";
	private static final String _FILTER_ENTITY_TABLE = "KBArticle";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kbArticle.";
	private static final String _ORDER_BY_ENTITY_TABLE = "KBArticle.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KBArticle exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KBArticle exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(KBArticlePersistenceImpl.class);
	private static KBArticle _nullKBArticle = new KBArticleImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<KBArticle> toCacheModel() {
				return _nullKBArticleCacheModel;
			}
		};

	private static CacheModel<KBArticle> _nullKBArticleCacheModel = new CacheModel<KBArticle>() {
			public KBArticle toEntityModel() {
				return _nullKBArticle;
			}
		};
}