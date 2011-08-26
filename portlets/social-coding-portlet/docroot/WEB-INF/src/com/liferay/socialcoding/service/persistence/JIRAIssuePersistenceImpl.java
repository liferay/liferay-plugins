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

package com.liferay.socialcoding.service.persistence;

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
import com.liferay.portal.kernel.util.CalendarUtil;
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

import com.liferay.socialcoding.NoSuchJIRAIssueException;
import com.liferay.socialcoding.model.JIRAIssue;
import com.liferay.socialcoding.model.impl.JIRAIssueImpl;
import com.liferay.socialcoding.model.impl.JIRAIssueModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * The persistence implementation for the j i r a issue service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAIssuePersistence
 * @see JIRAIssueUtil
 * @generated
 */
public class JIRAIssuePersistenceImpl extends BasePersistenceImpl<JIRAIssue>
	implements JIRAIssuePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link JIRAIssueUtil} to access the j i r a issue persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = JIRAIssueImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_PROJECTID = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, JIRAIssueImpl.class,
			FINDER_CLASS_NAME_LIST, "findByProjectId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_PROJECTID = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByProjectId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_KEY = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, JIRAIssueImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByKey",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_KEY = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByKey",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_REPORTERJIRAUSERID = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, JIRAIssueImpl.class,
			FINDER_CLASS_NAME_LIST, "findByReporterJiraUserId",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_REPORTERJIRAUSERID = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByReporterJiraUserId",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_ASSIGNEEJIRAUSERID = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, JIRAIssueImpl.class,
			FINDER_CLASS_NAME_LIST, "findByAssigneeJiraUserId",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_ASSIGNEEJIRAUSERID = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByAssigneeJiraUserId",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_MD_P = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, JIRAIssueImpl.class,
			FINDER_CLASS_NAME_LIST, "findByMD_P",
			new String[] {
				Date.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_MD_P = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByMD_P",
			new String[] { Date.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_P_RJUI = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, JIRAIssueImpl.class,
			FINDER_CLASS_NAME_LIST, "findByP_RJUI",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_P_RJUI = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByP_RJUI",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_P_AJUI = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, JIRAIssueImpl.class,
			FINDER_CLASS_NAME_LIST, "findByP_AJUI",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_P_AJUI = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByP_AJUI",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_MD_P_RJUI = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, JIRAIssueImpl.class,
			FINDER_CLASS_NAME_LIST, "findByMD_P_RJUI",
			new String[] {
				Date.class.getName(), Long.class.getName(),
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_MD_P_RJUI = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByMD_P_RJUI",
			new String[] {
				Date.class.getName(), Long.class.getName(),
				String.class.getName()
			});
	public static final FinderPath FINDER_PATH_FIND_BY_MD_P_AJUI = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, JIRAIssueImpl.class,
			FINDER_CLASS_NAME_LIST, "findByMD_P_AJUI",
			new String[] {
				Date.class.getName(), Long.class.getName(),
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_MD_P_AJUI = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByMD_P_AJUI",
			new String[] {
				Date.class.getName(), Long.class.getName(),
				String.class.getName()
			});
	public static final FinderPath FINDER_PATH_FIND_BY_P_RJUI_S = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, JIRAIssueImpl.class,
			FINDER_CLASS_NAME_LIST, "findByP_RJUI_S",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_P_RJUI_S = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByP_RJUI_S",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			});
	public static final FinderPath FINDER_PATH_FIND_BY_P_AJUI_S = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, JIRAIssueImpl.class,
			FINDER_CLASS_NAME_LIST, "findByP_AJUI_S",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_P_AJUI_S = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByP_AJUI_S",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			});
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, JIRAIssueImpl.class,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the j i r a issue in the entity cache if it is enabled.
	 *
	 * @param jiraIssue the j i r a issue
	 */
	public void cacheResult(JIRAIssue jiraIssue) {
		EntityCacheUtil.putResult(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueImpl.class, jiraIssue.getPrimaryKey(), jiraIssue);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KEY,
			new Object[] { jiraIssue.getKey() }, jiraIssue);

		jiraIssue.resetOriginalValues();
	}

	/**
	 * Caches the j i r a issues in the entity cache if it is enabled.
	 *
	 * @param jiraIssues the j i r a issues
	 */
	public void cacheResult(List<JIRAIssue> jiraIssues) {
		for (JIRAIssue jiraIssue : jiraIssues) {
			if (EntityCacheUtil.getResult(
						JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
						JIRAIssueImpl.class, jiraIssue.getPrimaryKey(), this) == null) {
				cacheResult(jiraIssue);
			}
		}
	}

	/**
	 * Clears the cache for all j i r a issues.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(JIRAIssueImpl.class.getName());
		}

		EntityCacheUtil.clearCache(JIRAIssueImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the j i r a issue.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(JIRAIssue jiraIssue) {
		EntityCacheUtil.removeResult(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueImpl.class, jiraIssue.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KEY,
			new Object[] { jiraIssue.getKey() });
	}

	/**
	 * Creates a new j i r a issue with the primary key. Does not add the j i r a issue to the database.
	 *
	 * @param jiraIssueId the primary key for the new j i r a issue
	 * @return the new j i r a issue
	 */
	public JIRAIssue create(long jiraIssueId) {
		JIRAIssue jiraIssue = new JIRAIssueImpl();

		jiraIssue.setNew(true);
		jiraIssue.setPrimaryKey(jiraIssueId);

		return jiraIssue;
	}

	/**
	 * Removes the j i r a issue with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the j i r a issue
	 * @return the j i r a issue that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a j i r a issue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public JIRAIssue remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the j i r a issue with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param jiraIssueId the primary key of the j i r a issue
	 * @return the j i r a issue that was removed
	 * @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAIssue remove(long jiraIssueId)
		throws NoSuchJIRAIssueException, SystemException {
		Session session = null;

		try {
			session = openSession();

			JIRAIssue jiraIssue = (JIRAIssue)session.get(JIRAIssueImpl.class,
					Long.valueOf(jiraIssueId));

			if (jiraIssue == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + jiraIssueId);
				}

				throw new NoSuchJIRAIssueException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					jiraIssueId);
			}

			return jiraIssuePersistence.remove(jiraIssue);
		}
		catch (NoSuchJIRAIssueException nsee) {
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
	 * Removes the j i r a issue from the database. Also notifies the appropriate model listeners.
	 *
	 * @param jiraIssue the j i r a issue
	 * @return the j i r a issue that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public JIRAIssue remove(JIRAIssue jiraIssue) throws SystemException {
		return super.remove(jiraIssue);
	}

	@Override
	protected JIRAIssue removeImpl(JIRAIssue jiraIssue)
		throws SystemException {
		jiraIssue = toUnwrappedModel(jiraIssue);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, jiraIssue);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		JIRAIssueModelImpl jiraIssueModelImpl = (JIRAIssueModelImpl)jiraIssue;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KEY,
			new Object[] { jiraIssueModelImpl.getKey() });

		EntityCacheUtil.removeResult(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueImpl.class, jiraIssue.getPrimaryKey());

		return jiraIssue;
	}

	@Override
	public JIRAIssue updateImpl(
		com.liferay.socialcoding.model.JIRAIssue jiraIssue, boolean merge)
		throws SystemException {
		jiraIssue = toUnwrappedModel(jiraIssue);

		boolean isNew = jiraIssue.isNew();

		JIRAIssueModelImpl jiraIssueModelImpl = (JIRAIssueModelImpl)jiraIssue;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, jiraIssue, merge);

			jiraIssue.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueImpl.class, jiraIssue.getPrimaryKey(), jiraIssue);

		if (!isNew &&
				(!Validator.equals(jiraIssue.getKey(),
					jiraIssueModelImpl.getOriginalKey()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KEY,
				new Object[] { jiraIssueModelImpl.getOriginalKey() });
		}

		if (isNew ||
				(!Validator.equals(jiraIssue.getKey(),
					jiraIssueModelImpl.getOriginalKey()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KEY,
				new Object[] { jiraIssue.getKey() }, jiraIssue);
		}

		return jiraIssue;
	}

	protected JIRAIssue toUnwrappedModel(JIRAIssue jiraIssue) {
		if (jiraIssue instanceof JIRAIssueImpl) {
			return jiraIssue;
		}

		JIRAIssueImpl jiraIssueImpl = new JIRAIssueImpl();

		jiraIssueImpl.setNew(jiraIssue.isNew());
		jiraIssueImpl.setPrimaryKey(jiraIssue.getPrimaryKey());

		jiraIssueImpl.setJiraIssueId(jiraIssue.getJiraIssueId());
		jiraIssueImpl.setCreateDate(jiraIssue.getCreateDate());
		jiraIssueImpl.setModifiedDate(jiraIssue.getModifiedDate());
		jiraIssueImpl.setProjectId(jiraIssue.getProjectId());
		jiraIssueImpl.setKey(jiraIssue.getKey());
		jiraIssueImpl.setSummary(jiraIssue.getSummary());
		jiraIssueImpl.setDescription(jiraIssue.getDescription());
		jiraIssueImpl.setReporterJiraUserId(jiraIssue.getReporterJiraUserId());
		jiraIssueImpl.setAssigneeJiraUserId(jiraIssue.getAssigneeJiraUserId());
		jiraIssueImpl.setResolution(jiraIssue.getResolution());
		jiraIssueImpl.setStatus(jiraIssue.getStatus());

		return jiraIssueImpl;
	}

	/**
	 * Returns the j i r a issue with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the j i r a issue
	 * @return the j i r a issue
	 * @throws com.liferay.portal.NoSuchModelException if a j i r a issue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public JIRAIssue findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the j i r a issue with the primary key or throws a {@link com.liferay.socialcoding.NoSuchJIRAIssueException} if it could not be found.
	 *
	 * @param jiraIssueId the primary key of the j i r a issue
	 * @return the j i r a issue
	 * @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAIssue findByPrimaryKey(long jiraIssueId)
		throws NoSuchJIRAIssueException, SystemException {
		JIRAIssue jiraIssue = fetchByPrimaryKey(jiraIssueId);

		if (jiraIssue == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + jiraIssueId);
			}

			throw new NoSuchJIRAIssueException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				jiraIssueId);
		}

		return jiraIssue;
	}

	/**
	 * Returns the j i r a issue with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the j i r a issue
	 * @return the j i r a issue, or <code>null</code> if a j i r a issue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public JIRAIssue fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the j i r a issue with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param jiraIssueId the primary key of the j i r a issue
	 * @return the j i r a issue, or <code>null</code> if a j i r a issue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAIssue fetchByPrimaryKey(long jiraIssueId)
		throws SystemException {
		JIRAIssue jiraIssue = (JIRAIssue)EntityCacheUtil.getResult(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
				JIRAIssueImpl.class, jiraIssueId, this);

		if (jiraIssue == _nullJIRAIssue) {
			return null;
		}

		if (jiraIssue == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				jiraIssue = (JIRAIssue)session.get(JIRAIssueImpl.class,
						Long.valueOf(jiraIssueId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (jiraIssue != null) {
					cacheResult(jiraIssue);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
						JIRAIssueImpl.class, jiraIssueId, _nullJIRAIssue);
				}

				closeSession(session);
			}
		}

		return jiraIssue;
	}

	/**
	 * Returns all the j i r a issues where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @return the matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAIssue> findByProjectId(long projectId)
		throws SystemException {
		return findByProjectId(projectId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the j i r a issues where projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @return the range of matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAIssue> findByProjectId(long projectId, int start, int end)
		throws SystemException {
		return findByProjectId(projectId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a issues where projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAIssue> findByProjectId(long projectId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				projectId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<JIRAIssue> list = (List<JIRAIssue>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_PROJECTID,
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

			query.append(_SQL_SELECT_JIRAISSUE_WHERE);

			query.append(_FINDER_COLUMN_PROJECTID_PROJECTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

				list = (List<JIRAIssue>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_PROJECTID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_PROJECTID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first j i r a issue in the ordered set where projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a issue
	 * @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAIssue findByProjectId_First(long projectId,
		OrderByComparator orderByComparator)
		throws NoSuchJIRAIssueException, SystemException {
		List<JIRAIssue> list = findByProjectId(projectId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("projectId=");
			msg.append(projectId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last j i r a issue in the ordered set where projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a issue
	 * @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAIssue findByProjectId_Last(long projectId,
		OrderByComparator orderByComparator)
		throws NoSuchJIRAIssueException, SystemException {
		int count = countByProjectId(projectId);

		List<JIRAIssue> list = findByProjectId(projectId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("projectId=");
			msg.append(projectId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the j i r a issues before and after the current j i r a issue in the ordered set where projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jiraIssueId the primary key of the current j i r a issue
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next j i r a issue
	 * @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAIssue[] findByProjectId_PrevAndNext(long jiraIssueId,
		long projectId, OrderByComparator orderByComparator)
		throws NoSuchJIRAIssueException, SystemException {
		JIRAIssue jiraIssue = findByPrimaryKey(jiraIssueId);

		Session session = null;

		try {
			session = openSession();

			JIRAIssue[] array = new JIRAIssueImpl[3];

			array[0] = getByProjectId_PrevAndNext(session, jiraIssue,
					projectId, orderByComparator, true);

			array[1] = jiraIssue;

			array[2] = getByProjectId_PrevAndNext(session, jiraIssue,
					projectId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected JIRAIssue getByProjectId_PrevAndNext(Session session,
		JIRAIssue jiraIssue, long projectId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JIRAISSUE_WHERE);

		query.append(_FINDER_COLUMN_PROJECTID_PROJECTID_2);

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
			query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(projectId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(jiraIssue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JIRAIssue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the j i r a issue where key = &#63; or throws a {@link com.liferay.socialcoding.NoSuchJIRAIssueException} if it could not be found.
	 *
	 * @param key the key
	 * @return the matching j i r a issue
	 * @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAIssue findByKey(String key)
		throws NoSuchJIRAIssueException, SystemException {
		JIRAIssue jiraIssue = fetchByKey(key);

		if (jiraIssue == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("key=");
			msg.append(key);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchJIRAIssueException(msg.toString());
		}

		return jiraIssue;
	}

	/**
	 * Returns the j i r a issue where key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param key the key
	 * @return the matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAIssue fetchByKey(String key) throws SystemException {
		return fetchByKey(key, true);
	}

	/**
	 * Returns the j i r a issue where key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param key the key
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAIssue fetchByKey(String key, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { key };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_KEY,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_JIRAISSUE_WHERE);

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

			query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (key != null) {
					qPos.add(key);
				}

				List<JIRAIssue> list = q.list();

				result = list;

				JIRAIssue jiraIssue = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KEY,
						finderArgs, list);
				}
				else {
					jiraIssue = list.get(0);

					cacheResult(jiraIssue);

					if ((jiraIssue.getKey() == null) ||
							!jiraIssue.getKey().equals(key)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KEY,
							finderArgs, jiraIssue);
					}
				}

				return jiraIssue;
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
				return (JIRAIssue)result;
			}
		}
	}

	/**
	 * Returns all the j i r a issues where reporterJiraUserId = &#63;.
	 *
	 * @param reporterJiraUserId the reporter jira user ID
	 * @return the matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAIssue> findByReporterJiraUserId(String reporterJiraUserId)
		throws SystemException {
		return findByReporterJiraUserId(reporterJiraUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j i r a issues where reporterJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @return the range of matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAIssue> findByReporterJiraUserId(String reporterJiraUserId,
		int start, int end) throws SystemException {
		return findByReporterJiraUserId(reporterJiraUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a issues where reporterJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAIssue> findByReporterJiraUserId(String reporterJiraUserId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				reporterJiraUserId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<JIRAIssue> list = (List<JIRAIssue>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_REPORTERJIRAUSERID,
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

			query.append(_SQL_SELECT_JIRAISSUE_WHERE);

			if (reporterJiraUserId == null) {
				query.append(_FINDER_COLUMN_REPORTERJIRAUSERID_REPORTERJIRAUSERID_1);
			}
			else {
				if (reporterJiraUserId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_REPORTERJIRAUSERID_REPORTERJIRAUSERID_3);
				}
				else {
					query.append(_FINDER_COLUMN_REPORTERJIRAUSERID_REPORTERJIRAUSERID_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (reporterJiraUserId != null) {
					qPos.add(reporterJiraUserId);
				}

				list = (List<JIRAIssue>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_REPORTERJIRAUSERID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_REPORTERJIRAUSERID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first j i r a issue in the ordered set where reporterJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a issue
	 * @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAIssue findByReporterJiraUserId_First(String reporterJiraUserId,
		OrderByComparator orderByComparator)
		throws NoSuchJIRAIssueException, SystemException {
		List<JIRAIssue> list = findByReporterJiraUserId(reporterJiraUserId, 0,
				1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("reporterJiraUserId=");
			msg.append(reporterJiraUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last j i r a issue in the ordered set where reporterJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a issue
	 * @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAIssue findByReporterJiraUserId_Last(String reporterJiraUserId,
		OrderByComparator orderByComparator)
		throws NoSuchJIRAIssueException, SystemException {
		int count = countByReporterJiraUserId(reporterJiraUserId);

		List<JIRAIssue> list = findByReporterJiraUserId(reporterJiraUserId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("reporterJiraUserId=");
			msg.append(reporterJiraUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the j i r a issues before and after the current j i r a issue in the ordered set where reporterJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jiraIssueId the primary key of the current j i r a issue
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next j i r a issue
	 * @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAIssue[] findByReporterJiraUserId_PrevAndNext(long jiraIssueId,
		String reporterJiraUserId, OrderByComparator orderByComparator)
		throws NoSuchJIRAIssueException, SystemException {
		JIRAIssue jiraIssue = findByPrimaryKey(jiraIssueId);

		Session session = null;

		try {
			session = openSession();

			JIRAIssue[] array = new JIRAIssueImpl[3];

			array[0] = getByReporterJiraUserId_PrevAndNext(session, jiraIssue,
					reporterJiraUserId, orderByComparator, true);

			array[1] = jiraIssue;

			array[2] = getByReporterJiraUserId_PrevAndNext(session, jiraIssue,
					reporterJiraUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected JIRAIssue getByReporterJiraUserId_PrevAndNext(Session session,
		JIRAIssue jiraIssue, String reporterJiraUserId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JIRAISSUE_WHERE);

		if (reporterJiraUserId == null) {
			query.append(_FINDER_COLUMN_REPORTERJIRAUSERID_REPORTERJIRAUSERID_1);
		}
		else {
			if (reporterJiraUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_REPORTERJIRAUSERID_REPORTERJIRAUSERID_3);
			}
			else {
				query.append(_FINDER_COLUMN_REPORTERJIRAUSERID_REPORTERJIRAUSERID_2);
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
			query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (reporterJiraUserId != null) {
			qPos.add(reporterJiraUserId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(jiraIssue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JIRAIssue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the j i r a issues where assigneeJiraUserId = &#63;.
	 *
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @return the matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAIssue> findByAssigneeJiraUserId(String assigneeJiraUserId)
		throws SystemException {
		return findByAssigneeJiraUserId(assigneeJiraUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j i r a issues where assigneeJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @return the range of matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAIssue> findByAssigneeJiraUserId(String assigneeJiraUserId,
		int start, int end) throws SystemException {
		return findByAssigneeJiraUserId(assigneeJiraUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a issues where assigneeJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAIssue> findByAssigneeJiraUserId(String assigneeJiraUserId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				assigneeJiraUserId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<JIRAIssue> list = (List<JIRAIssue>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_ASSIGNEEJIRAUSERID,
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

			query.append(_SQL_SELECT_JIRAISSUE_WHERE);

			if (assigneeJiraUserId == null) {
				query.append(_FINDER_COLUMN_ASSIGNEEJIRAUSERID_ASSIGNEEJIRAUSERID_1);
			}
			else {
				if (assigneeJiraUserId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ASSIGNEEJIRAUSERID_ASSIGNEEJIRAUSERID_3);
				}
				else {
					query.append(_FINDER_COLUMN_ASSIGNEEJIRAUSERID_ASSIGNEEJIRAUSERID_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (assigneeJiraUserId != null) {
					qPos.add(assigneeJiraUserId);
				}

				list = (List<JIRAIssue>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_ASSIGNEEJIRAUSERID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_ASSIGNEEJIRAUSERID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first j i r a issue in the ordered set where assigneeJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a issue
	 * @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAIssue findByAssigneeJiraUserId_First(String assigneeJiraUserId,
		OrderByComparator orderByComparator)
		throws NoSuchJIRAIssueException, SystemException {
		List<JIRAIssue> list = findByAssigneeJiraUserId(assigneeJiraUserId, 0,
				1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("assigneeJiraUserId=");
			msg.append(assigneeJiraUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last j i r a issue in the ordered set where assigneeJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a issue
	 * @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAIssue findByAssigneeJiraUserId_Last(String assigneeJiraUserId,
		OrderByComparator orderByComparator)
		throws NoSuchJIRAIssueException, SystemException {
		int count = countByAssigneeJiraUserId(assigneeJiraUserId);

		List<JIRAIssue> list = findByAssigneeJiraUserId(assigneeJiraUserId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("assigneeJiraUserId=");
			msg.append(assigneeJiraUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the j i r a issues before and after the current j i r a issue in the ordered set where assigneeJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jiraIssueId the primary key of the current j i r a issue
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next j i r a issue
	 * @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAIssue[] findByAssigneeJiraUserId_PrevAndNext(long jiraIssueId,
		String assigneeJiraUserId, OrderByComparator orderByComparator)
		throws NoSuchJIRAIssueException, SystemException {
		JIRAIssue jiraIssue = findByPrimaryKey(jiraIssueId);

		Session session = null;

		try {
			session = openSession();

			JIRAIssue[] array = new JIRAIssueImpl[3];

			array[0] = getByAssigneeJiraUserId_PrevAndNext(session, jiraIssue,
					assigneeJiraUserId, orderByComparator, true);

			array[1] = jiraIssue;

			array[2] = getByAssigneeJiraUserId_PrevAndNext(session, jiraIssue,
					assigneeJiraUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected JIRAIssue getByAssigneeJiraUserId_PrevAndNext(Session session,
		JIRAIssue jiraIssue, String assigneeJiraUserId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JIRAISSUE_WHERE);

		if (assigneeJiraUserId == null) {
			query.append(_FINDER_COLUMN_ASSIGNEEJIRAUSERID_ASSIGNEEJIRAUSERID_1);
		}
		else {
			if (assigneeJiraUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ASSIGNEEJIRAUSERID_ASSIGNEEJIRAUSERID_3);
			}
			else {
				query.append(_FINDER_COLUMN_ASSIGNEEJIRAUSERID_ASSIGNEEJIRAUSERID_2);
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
			query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (assigneeJiraUserId != null) {
			qPos.add(assigneeJiraUserId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(jiraIssue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JIRAIssue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @return the matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAIssue> findByMD_P(Date modifiedDate, long projectId)
		throws SystemException {
		return findByMD_P(modifiedDate, projectId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @return the range of matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAIssue> findByMD_P(Date modifiedDate, long projectId,
		int start, int end) throws SystemException {
		return findByMD_P(modifiedDate, projectId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAIssue> findByMD_P(Date modifiedDate, long projectId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				modifiedDate, projectId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<JIRAIssue> list = (List<JIRAIssue>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_MD_P,
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

			query.append(_SQL_SELECT_JIRAISSUE_WHERE);

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_MD_P_MODIFIEDDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_MD_P_MODIFIEDDATE_2);
			}

			query.append(_FINDER_COLUMN_MD_P_PROJECTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (modifiedDate != null) {
					qPos.add(CalendarUtil.getTimestamp(modifiedDate));
				}

				qPos.add(projectId);

				list = (List<JIRAIssue>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_MD_P,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_MD_P,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a issue
	 * @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAIssue findByMD_P_First(Date modifiedDate, long projectId,
		OrderByComparator orderByComparator)
		throws NoSuchJIRAIssueException, SystemException {
		List<JIRAIssue> list = findByMD_P(modifiedDate, projectId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("modifiedDate=");
			msg.append(modifiedDate);

			msg.append(", projectId=");
			msg.append(projectId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a issue
	 * @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAIssue findByMD_P_Last(Date modifiedDate, long projectId,
		OrderByComparator orderByComparator)
		throws NoSuchJIRAIssueException, SystemException {
		int count = countByMD_P(modifiedDate, projectId);

		List<JIRAIssue> list = findByMD_P(modifiedDate, projectId, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("modifiedDate=");
			msg.append(modifiedDate);

			msg.append(", projectId=");
			msg.append(projectId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the j i r a issues before and after the current j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jiraIssueId the primary key of the current j i r a issue
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next j i r a issue
	 * @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAIssue[] findByMD_P_PrevAndNext(long jiraIssueId,
		Date modifiedDate, long projectId, OrderByComparator orderByComparator)
		throws NoSuchJIRAIssueException, SystemException {
		JIRAIssue jiraIssue = findByPrimaryKey(jiraIssueId);

		Session session = null;

		try {
			session = openSession();

			JIRAIssue[] array = new JIRAIssueImpl[3];

			array[0] = getByMD_P_PrevAndNext(session, jiraIssue, modifiedDate,
					projectId, orderByComparator, true);

			array[1] = jiraIssue;

			array[2] = getByMD_P_PrevAndNext(session, jiraIssue, modifiedDate,
					projectId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected JIRAIssue getByMD_P_PrevAndNext(Session session,
		JIRAIssue jiraIssue, Date modifiedDate, long projectId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JIRAISSUE_WHERE);

		if (modifiedDate == null) {
			query.append(_FINDER_COLUMN_MD_P_MODIFIEDDATE_1);
		}
		else {
			query.append(_FINDER_COLUMN_MD_P_MODIFIEDDATE_2);
		}

		query.append(_FINDER_COLUMN_MD_P_PROJECTID_2);

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
			query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (modifiedDate != null) {
			qPos.add(CalendarUtil.getTimestamp(modifiedDate));
		}

		qPos.add(projectId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(jiraIssue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JIRAIssue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the j i r a issues where projectId = &#63; and reporterJiraUserId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @return the matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAIssue> findByP_RJUI(long projectId,
		String reporterJiraUserId) throws SystemException {
		return findByP_RJUI(projectId, reporterJiraUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j i r a issues where projectId = &#63; and reporterJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @return the range of matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAIssue> findByP_RJUI(long projectId,
		String reporterJiraUserId, int start, int end)
		throws SystemException {
		return findByP_RJUI(projectId, reporterJiraUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a issues where projectId = &#63; and reporterJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAIssue> findByP_RJUI(long projectId,
		String reporterJiraUserId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				projectId, reporterJiraUserId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<JIRAIssue> list = (List<JIRAIssue>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_P_RJUI,
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

			query.append(_SQL_SELECT_JIRAISSUE_WHERE);

			query.append(_FINDER_COLUMN_P_RJUI_PROJECTID_2);

			if (reporterJiraUserId == null) {
				query.append(_FINDER_COLUMN_P_RJUI_REPORTERJIRAUSERID_1);
			}
			else {
				if (reporterJiraUserId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_P_RJUI_REPORTERJIRAUSERID_3);
				}
				else {
					query.append(_FINDER_COLUMN_P_RJUI_REPORTERJIRAUSERID_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

				if (reporterJiraUserId != null) {
					qPos.add(reporterJiraUserId);
				}

				list = (List<JIRAIssue>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_P_RJUI,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_P_RJUI,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a issue
	 * @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAIssue findByP_RJUI_First(long projectId,
		String reporterJiraUserId, OrderByComparator orderByComparator)
		throws NoSuchJIRAIssueException, SystemException {
		List<JIRAIssue> list = findByP_RJUI(projectId, reporterJiraUserId, 0,
				1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("projectId=");
			msg.append(projectId);

			msg.append(", reporterJiraUserId=");
			msg.append(reporterJiraUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a issue
	 * @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAIssue findByP_RJUI_Last(long projectId,
		String reporterJiraUserId, OrderByComparator orderByComparator)
		throws NoSuchJIRAIssueException, SystemException {
		int count = countByP_RJUI(projectId, reporterJiraUserId);

		List<JIRAIssue> list = findByP_RJUI(projectId, reporterJiraUserId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("projectId=");
			msg.append(projectId);

			msg.append(", reporterJiraUserId=");
			msg.append(reporterJiraUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the j i r a issues before and after the current j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jiraIssueId the primary key of the current j i r a issue
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next j i r a issue
	 * @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAIssue[] findByP_RJUI_PrevAndNext(long jiraIssueId,
		long projectId, String reporterJiraUserId,
		OrderByComparator orderByComparator)
		throws NoSuchJIRAIssueException, SystemException {
		JIRAIssue jiraIssue = findByPrimaryKey(jiraIssueId);

		Session session = null;

		try {
			session = openSession();

			JIRAIssue[] array = new JIRAIssueImpl[3];

			array[0] = getByP_RJUI_PrevAndNext(session, jiraIssue, projectId,
					reporterJiraUserId, orderByComparator, true);

			array[1] = jiraIssue;

			array[2] = getByP_RJUI_PrevAndNext(session, jiraIssue, projectId,
					reporterJiraUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected JIRAIssue getByP_RJUI_PrevAndNext(Session session,
		JIRAIssue jiraIssue, long projectId, String reporterJiraUserId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JIRAISSUE_WHERE);

		query.append(_FINDER_COLUMN_P_RJUI_PROJECTID_2);

		if (reporterJiraUserId == null) {
			query.append(_FINDER_COLUMN_P_RJUI_REPORTERJIRAUSERID_1);
		}
		else {
			if (reporterJiraUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_P_RJUI_REPORTERJIRAUSERID_3);
			}
			else {
				query.append(_FINDER_COLUMN_P_RJUI_REPORTERJIRAUSERID_2);
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
			query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(projectId);

		if (reporterJiraUserId != null) {
			qPos.add(reporterJiraUserId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(jiraIssue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JIRAIssue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @return the matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAIssue> findByP_AJUI(long projectId,
		String assigneeJiraUserId) throws SystemException {
		return findByP_AJUI(projectId, assigneeJiraUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @return the range of matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAIssue> findByP_AJUI(long projectId,
		String assigneeJiraUserId, int start, int end)
		throws SystemException {
		return findByP_AJUI(projectId, assigneeJiraUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAIssue> findByP_AJUI(long projectId,
		String assigneeJiraUserId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				projectId, assigneeJiraUserId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<JIRAIssue> list = (List<JIRAIssue>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_P_AJUI,
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

			query.append(_SQL_SELECT_JIRAISSUE_WHERE);

			query.append(_FINDER_COLUMN_P_AJUI_PROJECTID_2);

			if (assigneeJiraUserId == null) {
				query.append(_FINDER_COLUMN_P_AJUI_ASSIGNEEJIRAUSERID_1);
			}
			else {
				if (assigneeJiraUserId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_P_AJUI_ASSIGNEEJIRAUSERID_3);
				}
				else {
					query.append(_FINDER_COLUMN_P_AJUI_ASSIGNEEJIRAUSERID_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

				if (assigneeJiraUserId != null) {
					qPos.add(assigneeJiraUserId);
				}

				list = (List<JIRAIssue>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_P_AJUI,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_P_AJUI,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a issue
	 * @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAIssue findByP_AJUI_First(long projectId,
		String assigneeJiraUserId, OrderByComparator orderByComparator)
		throws NoSuchJIRAIssueException, SystemException {
		List<JIRAIssue> list = findByP_AJUI(projectId, assigneeJiraUserId, 0,
				1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("projectId=");
			msg.append(projectId);

			msg.append(", assigneeJiraUserId=");
			msg.append(assigneeJiraUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a issue
	 * @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAIssue findByP_AJUI_Last(long projectId,
		String assigneeJiraUserId, OrderByComparator orderByComparator)
		throws NoSuchJIRAIssueException, SystemException {
		int count = countByP_AJUI(projectId, assigneeJiraUserId);

		List<JIRAIssue> list = findByP_AJUI(projectId, assigneeJiraUserId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("projectId=");
			msg.append(projectId);

			msg.append(", assigneeJiraUserId=");
			msg.append(assigneeJiraUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the j i r a issues before and after the current j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jiraIssueId the primary key of the current j i r a issue
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next j i r a issue
	 * @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAIssue[] findByP_AJUI_PrevAndNext(long jiraIssueId,
		long projectId, String assigneeJiraUserId,
		OrderByComparator orderByComparator)
		throws NoSuchJIRAIssueException, SystemException {
		JIRAIssue jiraIssue = findByPrimaryKey(jiraIssueId);

		Session session = null;

		try {
			session = openSession();

			JIRAIssue[] array = new JIRAIssueImpl[3];

			array[0] = getByP_AJUI_PrevAndNext(session, jiraIssue, projectId,
					assigneeJiraUserId, orderByComparator, true);

			array[1] = jiraIssue;

			array[2] = getByP_AJUI_PrevAndNext(session, jiraIssue, projectId,
					assigneeJiraUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected JIRAIssue getByP_AJUI_PrevAndNext(Session session,
		JIRAIssue jiraIssue, long projectId, String assigneeJiraUserId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JIRAISSUE_WHERE);

		query.append(_FINDER_COLUMN_P_AJUI_PROJECTID_2);

		if (assigneeJiraUserId == null) {
			query.append(_FINDER_COLUMN_P_AJUI_ASSIGNEEJIRAUSERID_1);
		}
		else {
			if (assigneeJiraUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_P_AJUI_ASSIGNEEJIRAUSERID_3);
			}
			else {
				query.append(_FINDER_COLUMN_P_AJUI_ASSIGNEEJIRAUSERID_2);
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
			query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(projectId);

		if (assigneeJiraUserId != null) {
			qPos.add(assigneeJiraUserId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(jiraIssue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JIRAIssue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @return the matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAIssue> findByMD_P_RJUI(Date modifiedDate, long projectId,
		String reporterJiraUserId) throws SystemException {
		return findByMD_P_RJUI(modifiedDate, projectId, reporterJiraUserId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @return the range of matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAIssue> findByMD_P_RJUI(Date modifiedDate, long projectId,
		String reporterJiraUserId, int start, int end)
		throws SystemException {
		return findByMD_P_RJUI(modifiedDate, projectId, reporterJiraUserId,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAIssue> findByMD_P_RJUI(Date modifiedDate, long projectId,
		String reporterJiraUserId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				modifiedDate, projectId, reporterJiraUserId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<JIRAIssue> list = (List<JIRAIssue>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_MD_P_RJUI,
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

			query.append(_SQL_SELECT_JIRAISSUE_WHERE);

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_MD_P_RJUI_MODIFIEDDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_MD_P_RJUI_MODIFIEDDATE_2);
			}

			query.append(_FINDER_COLUMN_MD_P_RJUI_PROJECTID_2);

			if (reporterJiraUserId == null) {
				query.append(_FINDER_COLUMN_MD_P_RJUI_REPORTERJIRAUSERID_1);
			}
			else {
				if (reporterJiraUserId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MD_P_RJUI_REPORTERJIRAUSERID_3);
				}
				else {
					query.append(_FINDER_COLUMN_MD_P_RJUI_REPORTERJIRAUSERID_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (modifiedDate != null) {
					qPos.add(CalendarUtil.getTimestamp(modifiedDate));
				}

				qPos.add(projectId);

				if (reporterJiraUserId != null) {
					qPos.add(reporterJiraUserId);
				}

				list = (List<JIRAIssue>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_MD_P_RJUI,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_MD_P_RJUI,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a issue
	 * @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAIssue findByMD_P_RJUI_First(Date modifiedDate, long projectId,
		String reporterJiraUserId, OrderByComparator orderByComparator)
		throws NoSuchJIRAIssueException, SystemException {
		List<JIRAIssue> list = findByMD_P_RJUI(modifiedDate, projectId,
				reporterJiraUserId, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("modifiedDate=");
			msg.append(modifiedDate);

			msg.append(", projectId=");
			msg.append(projectId);

			msg.append(", reporterJiraUserId=");
			msg.append(reporterJiraUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a issue
	 * @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAIssue findByMD_P_RJUI_Last(Date modifiedDate, long projectId,
		String reporterJiraUserId, OrderByComparator orderByComparator)
		throws NoSuchJIRAIssueException, SystemException {
		int count = countByMD_P_RJUI(modifiedDate, projectId, reporterJiraUserId);

		List<JIRAIssue> list = findByMD_P_RJUI(modifiedDate, projectId,
				reporterJiraUserId, count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("modifiedDate=");
			msg.append(modifiedDate);

			msg.append(", projectId=");
			msg.append(projectId);

			msg.append(", reporterJiraUserId=");
			msg.append(reporterJiraUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the j i r a issues before and after the current j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jiraIssueId the primary key of the current j i r a issue
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next j i r a issue
	 * @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAIssue[] findByMD_P_RJUI_PrevAndNext(long jiraIssueId,
		Date modifiedDate, long projectId, String reporterJiraUserId,
		OrderByComparator orderByComparator)
		throws NoSuchJIRAIssueException, SystemException {
		JIRAIssue jiraIssue = findByPrimaryKey(jiraIssueId);

		Session session = null;

		try {
			session = openSession();

			JIRAIssue[] array = new JIRAIssueImpl[3];

			array[0] = getByMD_P_RJUI_PrevAndNext(session, jiraIssue,
					modifiedDate, projectId, reporterJiraUserId,
					orderByComparator, true);

			array[1] = jiraIssue;

			array[2] = getByMD_P_RJUI_PrevAndNext(session, jiraIssue,
					modifiedDate, projectId, reporterJiraUserId,
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

	protected JIRAIssue getByMD_P_RJUI_PrevAndNext(Session session,
		JIRAIssue jiraIssue, Date modifiedDate, long projectId,
		String reporterJiraUserId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JIRAISSUE_WHERE);

		if (modifiedDate == null) {
			query.append(_FINDER_COLUMN_MD_P_RJUI_MODIFIEDDATE_1);
		}
		else {
			query.append(_FINDER_COLUMN_MD_P_RJUI_MODIFIEDDATE_2);
		}

		query.append(_FINDER_COLUMN_MD_P_RJUI_PROJECTID_2);

		if (reporterJiraUserId == null) {
			query.append(_FINDER_COLUMN_MD_P_RJUI_REPORTERJIRAUSERID_1);
		}
		else {
			if (reporterJiraUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_MD_P_RJUI_REPORTERJIRAUSERID_3);
			}
			else {
				query.append(_FINDER_COLUMN_MD_P_RJUI_REPORTERJIRAUSERID_2);
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
			query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (modifiedDate != null) {
			qPos.add(CalendarUtil.getTimestamp(modifiedDate));
		}

		qPos.add(projectId);

		if (reporterJiraUserId != null) {
			qPos.add(reporterJiraUserId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(jiraIssue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JIRAIssue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @return the matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAIssue> findByMD_P_AJUI(Date modifiedDate, long projectId,
		String assigneeJiraUserId) throws SystemException {
		return findByMD_P_AJUI(modifiedDate, projectId, assigneeJiraUserId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @return the range of matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAIssue> findByMD_P_AJUI(Date modifiedDate, long projectId,
		String assigneeJiraUserId, int start, int end)
		throws SystemException {
		return findByMD_P_AJUI(modifiedDate, projectId, assigneeJiraUserId,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAIssue> findByMD_P_AJUI(Date modifiedDate, long projectId,
		String assigneeJiraUserId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				modifiedDate, projectId, assigneeJiraUserId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<JIRAIssue> list = (List<JIRAIssue>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_MD_P_AJUI,
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

			query.append(_SQL_SELECT_JIRAISSUE_WHERE);

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_MD_P_AJUI_MODIFIEDDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_MD_P_AJUI_MODIFIEDDATE_2);
			}

			query.append(_FINDER_COLUMN_MD_P_AJUI_PROJECTID_2);

			if (assigneeJiraUserId == null) {
				query.append(_FINDER_COLUMN_MD_P_AJUI_ASSIGNEEJIRAUSERID_1);
			}
			else {
				if (assigneeJiraUserId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MD_P_AJUI_ASSIGNEEJIRAUSERID_3);
				}
				else {
					query.append(_FINDER_COLUMN_MD_P_AJUI_ASSIGNEEJIRAUSERID_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (modifiedDate != null) {
					qPos.add(CalendarUtil.getTimestamp(modifiedDate));
				}

				qPos.add(projectId);

				if (assigneeJiraUserId != null) {
					qPos.add(assigneeJiraUserId);
				}

				list = (List<JIRAIssue>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_MD_P_AJUI,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_MD_P_AJUI,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a issue
	 * @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAIssue findByMD_P_AJUI_First(Date modifiedDate, long projectId,
		String assigneeJiraUserId, OrderByComparator orderByComparator)
		throws NoSuchJIRAIssueException, SystemException {
		List<JIRAIssue> list = findByMD_P_AJUI(modifiedDate, projectId,
				assigneeJiraUserId, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("modifiedDate=");
			msg.append(modifiedDate);

			msg.append(", projectId=");
			msg.append(projectId);

			msg.append(", assigneeJiraUserId=");
			msg.append(assigneeJiraUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a issue
	 * @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAIssue findByMD_P_AJUI_Last(Date modifiedDate, long projectId,
		String assigneeJiraUserId, OrderByComparator orderByComparator)
		throws NoSuchJIRAIssueException, SystemException {
		int count = countByMD_P_AJUI(modifiedDate, projectId, assigneeJiraUserId);

		List<JIRAIssue> list = findByMD_P_AJUI(modifiedDate, projectId,
				assigneeJiraUserId, count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("modifiedDate=");
			msg.append(modifiedDate);

			msg.append(", projectId=");
			msg.append(projectId);

			msg.append(", assigneeJiraUserId=");
			msg.append(assigneeJiraUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the j i r a issues before and after the current j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jiraIssueId the primary key of the current j i r a issue
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next j i r a issue
	 * @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAIssue[] findByMD_P_AJUI_PrevAndNext(long jiraIssueId,
		Date modifiedDate, long projectId, String assigneeJiraUserId,
		OrderByComparator orderByComparator)
		throws NoSuchJIRAIssueException, SystemException {
		JIRAIssue jiraIssue = findByPrimaryKey(jiraIssueId);

		Session session = null;

		try {
			session = openSession();

			JIRAIssue[] array = new JIRAIssueImpl[3];

			array[0] = getByMD_P_AJUI_PrevAndNext(session, jiraIssue,
					modifiedDate, projectId, assigneeJiraUserId,
					orderByComparator, true);

			array[1] = jiraIssue;

			array[2] = getByMD_P_AJUI_PrevAndNext(session, jiraIssue,
					modifiedDate, projectId, assigneeJiraUserId,
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

	protected JIRAIssue getByMD_P_AJUI_PrevAndNext(Session session,
		JIRAIssue jiraIssue, Date modifiedDate, long projectId,
		String assigneeJiraUserId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JIRAISSUE_WHERE);

		if (modifiedDate == null) {
			query.append(_FINDER_COLUMN_MD_P_AJUI_MODIFIEDDATE_1);
		}
		else {
			query.append(_FINDER_COLUMN_MD_P_AJUI_MODIFIEDDATE_2);
		}

		query.append(_FINDER_COLUMN_MD_P_AJUI_PROJECTID_2);

		if (assigneeJiraUserId == null) {
			query.append(_FINDER_COLUMN_MD_P_AJUI_ASSIGNEEJIRAUSERID_1);
		}
		else {
			if (assigneeJiraUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_MD_P_AJUI_ASSIGNEEJIRAUSERID_3);
			}
			else {
				query.append(_FINDER_COLUMN_MD_P_AJUI_ASSIGNEEJIRAUSERID_2);
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
			query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (modifiedDate != null) {
			qPos.add(CalendarUtil.getTimestamp(modifiedDate));
		}

		qPos.add(projectId);

		if (assigneeJiraUserId != null) {
			qPos.add(assigneeJiraUserId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(jiraIssue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JIRAIssue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the j i r a issues where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	 *
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param status the status
	 * @return the matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAIssue> findByP_RJUI_S(long projectId,
		String reporterJiraUserId, String status) throws SystemException {
		return findByP_RJUI_S(projectId, reporterJiraUserId, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j i r a issues where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param status the status
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @return the range of matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAIssue> findByP_RJUI_S(long projectId,
		String reporterJiraUserId, String status, int start, int end)
		throws SystemException {
		return findByP_RJUI_S(projectId, reporterJiraUserId, status, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a issues where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param status the status
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAIssue> findByP_RJUI_S(long projectId,
		String reporterJiraUserId, String status, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				projectId, reporterJiraUserId, status,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<JIRAIssue> list = (List<JIRAIssue>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_P_RJUI_S,
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

			query.append(_SQL_SELECT_JIRAISSUE_WHERE);

			query.append(_FINDER_COLUMN_P_RJUI_S_PROJECTID_2);

			if (reporterJiraUserId == null) {
				query.append(_FINDER_COLUMN_P_RJUI_S_REPORTERJIRAUSERID_1);
			}
			else {
				if (reporterJiraUserId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_P_RJUI_S_REPORTERJIRAUSERID_3);
				}
				else {
					query.append(_FINDER_COLUMN_P_RJUI_S_REPORTERJIRAUSERID_2);
				}
			}

			if (status == null) {
				query.append(_FINDER_COLUMN_P_RJUI_S_STATUS_1);
			}
			else {
				if (status.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_P_RJUI_S_STATUS_3);
				}
				else {
					query.append(_FINDER_COLUMN_P_RJUI_S_STATUS_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

				if (reporterJiraUserId != null) {
					qPos.add(reporterJiraUserId);
				}

				if (status != null) {
					qPos.add(status);
				}

				list = (List<JIRAIssue>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_P_RJUI_S,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_P_RJUI_S,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a issue
	 * @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAIssue findByP_RJUI_S_First(long projectId,
		String reporterJiraUserId, String status,
		OrderByComparator orderByComparator)
		throws NoSuchJIRAIssueException, SystemException {
		List<JIRAIssue> list = findByP_RJUI_S(projectId, reporterJiraUserId,
				status, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("projectId=");
			msg.append(projectId);

			msg.append(", reporterJiraUserId=");
			msg.append(reporterJiraUserId);

			msg.append(", status=");
			msg.append(status);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a issue
	 * @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAIssue findByP_RJUI_S_Last(long projectId,
		String reporterJiraUserId, String status,
		OrderByComparator orderByComparator)
		throws NoSuchJIRAIssueException, SystemException {
		int count = countByP_RJUI_S(projectId, reporterJiraUserId, status);

		List<JIRAIssue> list = findByP_RJUI_S(projectId, reporterJiraUserId,
				status, count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("projectId=");
			msg.append(projectId);

			msg.append(", reporterJiraUserId=");
			msg.append(reporterJiraUserId);

			msg.append(", status=");
			msg.append(status);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the j i r a issues before and after the current j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jiraIssueId the primary key of the current j i r a issue
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next j i r a issue
	 * @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAIssue[] findByP_RJUI_S_PrevAndNext(long jiraIssueId,
		long projectId, String reporterJiraUserId, String status,
		OrderByComparator orderByComparator)
		throws NoSuchJIRAIssueException, SystemException {
		JIRAIssue jiraIssue = findByPrimaryKey(jiraIssueId);

		Session session = null;

		try {
			session = openSession();

			JIRAIssue[] array = new JIRAIssueImpl[3];

			array[0] = getByP_RJUI_S_PrevAndNext(session, jiraIssue, projectId,
					reporterJiraUserId, status, orderByComparator, true);

			array[1] = jiraIssue;

			array[2] = getByP_RJUI_S_PrevAndNext(session, jiraIssue, projectId,
					reporterJiraUserId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected JIRAIssue getByP_RJUI_S_PrevAndNext(Session session,
		JIRAIssue jiraIssue, long projectId, String reporterJiraUserId,
		String status, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JIRAISSUE_WHERE);

		query.append(_FINDER_COLUMN_P_RJUI_S_PROJECTID_2);

		if (reporterJiraUserId == null) {
			query.append(_FINDER_COLUMN_P_RJUI_S_REPORTERJIRAUSERID_1);
		}
		else {
			if (reporterJiraUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_P_RJUI_S_REPORTERJIRAUSERID_3);
			}
			else {
				query.append(_FINDER_COLUMN_P_RJUI_S_REPORTERJIRAUSERID_2);
			}
		}

		if (status == null) {
			query.append(_FINDER_COLUMN_P_RJUI_S_STATUS_1);
		}
		else {
			if (status.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_P_RJUI_S_STATUS_3);
			}
			else {
				query.append(_FINDER_COLUMN_P_RJUI_S_STATUS_2);
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
			query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(projectId);

		if (reporterJiraUserId != null) {
			qPos.add(reporterJiraUserId);
		}

		if (status != null) {
			qPos.add(status);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(jiraIssue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JIRAIssue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	 *
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param status the status
	 * @return the matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAIssue> findByP_AJUI_S(long projectId,
		String assigneeJiraUserId, String status) throws SystemException {
		return findByP_AJUI_S(projectId, assigneeJiraUserId, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param status the status
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @return the range of matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAIssue> findByP_AJUI_S(long projectId,
		String assigneeJiraUserId, String status, int start, int end)
		throws SystemException {
		return findByP_AJUI_S(projectId, assigneeJiraUserId, status, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param status the status
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAIssue> findByP_AJUI_S(long projectId,
		String assigneeJiraUserId, String status, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				projectId, assigneeJiraUserId, status,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<JIRAIssue> list = (List<JIRAIssue>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_P_AJUI_S,
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

			query.append(_SQL_SELECT_JIRAISSUE_WHERE);

			query.append(_FINDER_COLUMN_P_AJUI_S_PROJECTID_2);

			if (assigneeJiraUserId == null) {
				query.append(_FINDER_COLUMN_P_AJUI_S_ASSIGNEEJIRAUSERID_1);
			}
			else {
				if (assigneeJiraUserId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_P_AJUI_S_ASSIGNEEJIRAUSERID_3);
				}
				else {
					query.append(_FINDER_COLUMN_P_AJUI_S_ASSIGNEEJIRAUSERID_2);
				}
			}

			if (status == null) {
				query.append(_FINDER_COLUMN_P_AJUI_S_STATUS_1);
			}
			else {
				if (status.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_P_AJUI_S_STATUS_3);
				}
				else {
					query.append(_FINDER_COLUMN_P_AJUI_S_STATUS_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

				if (assigneeJiraUserId != null) {
					qPos.add(assigneeJiraUserId);
				}

				if (status != null) {
					qPos.add(status);
				}

				list = (List<JIRAIssue>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_P_AJUI_S,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_P_AJUI_S,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a issue
	 * @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAIssue findByP_AJUI_S_First(long projectId,
		String assigneeJiraUserId, String status,
		OrderByComparator orderByComparator)
		throws NoSuchJIRAIssueException, SystemException {
		List<JIRAIssue> list = findByP_AJUI_S(projectId, assigneeJiraUserId,
				status, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("projectId=");
			msg.append(projectId);

			msg.append(", assigneeJiraUserId=");
			msg.append(assigneeJiraUserId);

			msg.append(", status=");
			msg.append(status);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a issue
	 * @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAIssue findByP_AJUI_S_Last(long projectId,
		String assigneeJiraUserId, String status,
		OrderByComparator orderByComparator)
		throws NoSuchJIRAIssueException, SystemException {
		int count = countByP_AJUI_S(projectId, assigneeJiraUserId, status);

		List<JIRAIssue> list = findByP_AJUI_S(projectId, assigneeJiraUserId,
				status, count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("projectId=");
			msg.append(projectId);

			msg.append(", assigneeJiraUserId=");
			msg.append(assigneeJiraUserId);

			msg.append(", status=");
			msg.append(status);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the j i r a issues before and after the current j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jiraIssueId the primary key of the current j i r a issue
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next j i r a issue
	 * @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JIRAIssue[] findByP_AJUI_S_PrevAndNext(long jiraIssueId,
		long projectId, String assigneeJiraUserId, String status,
		OrderByComparator orderByComparator)
		throws NoSuchJIRAIssueException, SystemException {
		JIRAIssue jiraIssue = findByPrimaryKey(jiraIssueId);

		Session session = null;

		try {
			session = openSession();

			JIRAIssue[] array = new JIRAIssueImpl[3];

			array[0] = getByP_AJUI_S_PrevAndNext(session, jiraIssue, projectId,
					assigneeJiraUserId, status, orderByComparator, true);

			array[1] = jiraIssue;

			array[2] = getByP_AJUI_S_PrevAndNext(session, jiraIssue, projectId,
					assigneeJiraUserId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected JIRAIssue getByP_AJUI_S_PrevAndNext(Session session,
		JIRAIssue jiraIssue, long projectId, String assigneeJiraUserId,
		String status, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JIRAISSUE_WHERE);

		query.append(_FINDER_COLUMN_P_AJUI_S_PROJECTID_2);

		if (assigneeJiraUserId == null) {
			query.append(_FINDER_COLUMN_P_AJUI_S_ASSIGNEEJIRAUSERID_1);
		}
		else {
			if (assigneeJiraUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_P_AJUI_S_ASSIGNEEJIRAUSERID_3);
			}
			else {
				query.append(_FINDER_COLUMN_P_AJUI_S_ASSIGNEEJIRAUSERID_2);
			}
		}

		if (status == null) {
			query.append(_FINDER_COLUMN_P_AJUI_S_STATUS_1);
		}
		else {
			if (status.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_P_AJUI_S_STATUS_3);
			}
			else {
				query.append(_FINDER_COLUMN_P_AJUI_S_STATUS_2);
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
			query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(projectId);

		if (assigneeJiraUserId != null) {
			qPos.add(assigneeJiraUserId);
		}

		if (status != null) {
			qPos.add(status);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(jiraIssue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JIRAIssue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the j i r a issues.
	 *
	 * @return the j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAIssue> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j i r a issues.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @return the range of j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAIssue> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a issues.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JIRAIssue> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<JIRAIssue> list = (List<JIRAIssue>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_JIRAISSUE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_JIRAISSUE.concat(JIRAIssueModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<JIRAIssue>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<JIRAIssue>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the j i r a issues where projectId = &#63; from the database.
	 *
	 * @param projectId the project ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByProjectId(long projectId) throws SystemException {
		for (JIRAIssue jiraIssue : findByProjectId(projectId)) {
			jiraIssuePersistence.remove(jiraIssue);
		}
	}

	/**
	 * Removes the j i r a issue where key = &#63; from the database.
	 *
	 * @param key the key
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByKey(String key)
		throws NoSuchJIRAIssueException, SystemException {
		JIRAIssue jiraIssue = findByKey(key);

		jiraIssuePersistence.remove(jiraIssue);
	}

	/**
	 * Removes all the j i r a issues where reporterJiraUserId = &#63; from the database.
	 *
	 * @param reporterJiraUserId the reporter jira user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByReporterJiraUserId(String reporterJiraUserId)
		throws SystemException {
		for (JIRAIssue jiraIssue : findByReporterJiraUserId(reporterJiraUserId)) {
			jiraIssuePersistence.remove(jiraIssue);
		}
	}

	/**
	 * Removes all the j i r a issues where assigneeJiraUserId = &#63; from the database.
	 *
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAssigneeJiraUserId(String assigneeJiraUserId)
		throws SystemException {
		for (JIRAIssue jiraIssue : findByAssigneeJiraUserId(assigneeJiraUserId)) {
			jiraIssuePersistence.remove(jiraIssue);
		}
	}

	/**
	 * Removes all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; from the database.
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByMD_P(Date modifiedDate, long projectId)
		throws SystemException {
		for (JIRAIssue jiraIssue : findByMD_P(modifiedDate, projectId)) {
			jiraIssuePersistence.remove(jiraIssue);
		}
	}

	/**
	 * Removes all the j i r a issues where projectId = &#63; and reporterJiraUserId = &#63; from the database.
	 *
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByP_RJUI(long projectId, String reporterJiraUserId)
		throws SystemException {
		for (JIRAIssue jiraIssue : findByP_RJUI(projectId, reporterJiraUserId)) {
			jiraIssuePersistence.remove(jiraIssue);
		}
	}

	/**
	 * Removes all the j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63; from the database.
	 *
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByP_AJUI(long projectId, String assigneeJiraUserId)
		throws SystemException {
		for (JIRAIssue jiraIssue : findByP_AJUI(projectId, assigneeJiraUserId)) {
			jiraIssuePersistence.remove(jiraIssue);
		}
	}

	/**
	 * Removes all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63; from the database.
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByMD_P_RJUI(Date modifiedDate, long projectId,
		String reporterJiraUserId) throws SystemException {
		for (JIRAIssue jiraIssue : findByMD_P_RJUI(modifiedDate, projectId,
				reporterJiraUserId)) {
			jiraIssuePersistence.remove(jiraIssue);
		}
	}

	/**
	 * Removes all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63; from the database.
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByMD_P_AJUI(Date modifiedDate, long projectId,
		String assigneeJiraUserId) throws SystemException {
		for (JIRAIssue jiraIssue : findByMD_P_AJUI(modifiedDate, projectId,
				assigneeJiraUserId)) {
			jiraIssuePersistence.remove(jiraIssue);
		}
	}

	/**
	 * Removes all the j i r a issues where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63; from the database.
	 *
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByP_RJUI_S(long projectId, String reporterJiraUserId,
		String status) throws SystemException {
		for (JIRAIssue jiraIssue : findByP_RJUI_S(projectId,
				reporterJiraUserId, status)) {
			jiraIssuePersistence.remove(jiraIssue);
		}
	}

	/**
	 * Removes all the j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63; from the database.
	 *
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByP_AJUI_S(long projectId, String assigneeJiraUserId,
		String status) throws SystemException {
		for (JIRAIssue jiraIssue : findByP_AJUI_S(projectId,
				assigneeJiraUserId, status)) {
			jiraIssuePersistence.remove(jiraIssue);
		}
	}

	/**
	 * Removes all the j i r a issues from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (JIRAIssue jiraIssue : findAll()) {
			jiraIssuePersistence.remove(jiraIssue);
		}
	}

	/**
	 * Returns the number of j i r a issues where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @return the number of matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public int countByProjectId(long projectId) throws SystemException {
		Object[] finderArgs = new Object[] { projectId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PROJECTID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_JIRAISSUE_WHERE);

			query.append(_FINDER_COLUMN_PROJECTID_PROJECTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PROJECTID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of j i r a issues where key = &#63;.
	 *
	 * @param key the key
	 * @return the number of matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKey(String key) throws SystemException {
		Object[] finderArgs = new Object[] { key };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KEY,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_JIRAISSUE_WHERE);

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
	 * Returns the number of j i r a issues where reporterJiraUserId = &#63;.
	 *
	 * @param reporterJiraUserId the reporter jira user ID
	 * @return the number of matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public int countByReporterJiraUserId(String reporterJiraUserId)
		throws SystemException {
		Object[] finderArgs = new Object[] { reporterJiraUserId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_REPORTERJIRAUSERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_JIRAISSUE_WHERE);

			if (reporterJiraUserId == null) {
				query.append(_FINDER_COLUMN_REPORTERJIRAUSERID_REPORTERJIRAUSERID_1);
			}
			else {
				if (reporterJiraUserId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_REPORTERJIRAUSERID_REPORTERJIRAUSERID_3);
				}
				else {
					query.append(_FINDER_COLUMN_REPORTERJIRAUSERID_REPORTERJIRAUSERID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (reporterJiraUserId != null) {
					qPos.add(reporterJiraUserId);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_REPORTERJIRAUSERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of j i r a issues where assigneeJiraUserId = &#63;.
	 *
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @return the number of matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAssigneeJiraUserId(String assigneeJiraUserId)
		throws SystemException {
		Object[] finderArgs = new Object[] { assigneeJiraUserId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ASSIGNEEJIRAUSERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_JIRAISSUE_WHERE);

			if (assigneeJiraUserId == null) {
				query.append(_FINDER_COLUMN_ASSIGNEEJIRAUSERID_ASSIGNEEJIRAUSERID_1);
			}
			else {
				if (assigneeJiraUserId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ASSIGNEEJIRAUSERID_ASSIGNEEJIRAUSERID_3);
				}
				else {
					query.append(_FINDER_COLUMN_ASSIGNEEJIRAUSERID_ASSIGNEEJIRAUSERID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (assigneeJiraUserId != null) {
					qPos.add(assigneeJiraUserId);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ASSIGNEEJIRAUSERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of j i r a issues where modifiedDate &gt; &#63; and projectId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @return the number of matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public int countByMD_P(Date modifiedDate, long projectId)
		throws SystemException {
		Object[] finderArgs = new Object[] { modifiedDate, projectId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_MD_P,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_JIRAISSUE_WHERE);

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_MD_P_MODIFIEDDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_MD_P_MODIFIEDDATE_2);
			}

			query.append(_FINDER_COLUMN_MD_P_PROJECTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (modifiedDate != null) {
					qPos.add(CalendarUtil.getTimestamp(modifiedDate));
				}

				qPos.add(projectId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MD_P,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of j i r a issues where projectId = &#63; and reporterJiraUserId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @return the number of matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public int countByP_RJUI(long projectId, String reporterJiraUserId)
		throws SystemException {
		Object[] finderArgs = new Object[] { projectId, reporterJiraUserId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_P_RJUI,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_JIRAISSUE_WHERE);

			query.append(_FINDER_COLUMN_P_RJUI_PROJECTID_2);

			if (reporterJiraUserId == null) {
				query.append(_FINDER_COLUMN_P_RJUI_REPORTERJIRAUSERID_1);
			}
			else {
				if (reporterJiraUserId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_P_RJUI_REPORTERJIRAUSERID_3);
				}
				else {
					query.append(_FINDER_COLUMN_P_RJUI_REPORTERJIRAUSERID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

				if (reporterJiraUserId != null) {
					qPos.add(reporterJiraUserId);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_P_RJUI,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @return the number of matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public int countByP_AJUI(long projectId, String assigneeJiraUserId)
		throws SystemException {
		Object[] finderArgs = new Object[] { projectId, assigneeJiraUserId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_P_AJUI,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_JIRAISSUE_WHERE);

			query.append(_FINDER_COLUMN_P_AJUI_PROJECTID_2);

			if (assigneeJiraUserId == null) {
				query.append(_FINDER_COLUMN_P_AJUI_ASSIGNEEJIRAUSERID_1);
			}
			else {
				if (assigneeJiraUserId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_P_AJUI_ASSIGNEEJIRAUSERID_3);
				}
				else {
					query.append(_FINDER_COLUMN_P_AJUI_ASSIGNEEJIRAUSERID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

				if (assigneeJiraUserId != null) {
					qPos.add(assigneeJiraUserId);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_P_AJUI,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @return the number of matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public int countByMD_P_RJUI(Date modifiedDate, long projectId,
		String reporterJiraUserId) throws SystemException {
		Object[] finderArgs = new Object[] {
				modifiedDate, projectId, reporterJiraUserId
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_MD_P_RJUI,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_JIRAISSUE_WHERE);

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_MD_P_RJUI_MODIFIEDDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_MD_P_RJUI_MODIFIEDDATE_2);
			}

			query.append(_FINDER_COLUMN_MD_P_RJUI_PROJECTID_2);

			if (reporterJiraUserId == null) {
				query.append(_FINDER_COLUMN_MD_P_RJUI_REPORTERJIRAUSERID_1);
			}
			else {
				if (reporterJiraUserId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MD_P_RJUI_REPORTERJIRAUSERID_3);
				}
				else {
					query.append(_FINDER_COLUMN_MD_P_RJUI_REPORTERJIRAUSERID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (modifiedDate != null) {
					qPos.add(CalendarUtil.getTimestamp(modifiedDate));
				}

				qPos.add(projectId);

				if (reporterJiraUserId != null) {
					qPos.add(reporterJiraUserId);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MD_P_RJUI,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @return the number of matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public int countByMD_P_AJUI(Date modifiedDate, long projectId,
		String assigneeJiraUserId) throws SystemException {
		Object[] finderArgs = new Object[] {
				modifiedDate, projectId, assigneeJiraUserId
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_MD_P_AJUI,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_JIRAISSUE_WHERE);

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_MD_P_AJUI_MODIFIEDDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_MD_P_AJUI_MODIFIEDDATE_2);
			}

			query.append(_FINDER_COLUMN_MD_P_AJUI_PROJECTID_2);

			if (assigneeJiraUserId == null) {
				query.append(_FINDER_COLUMN_MD_P_AJUI_ASSIGNEEJIRAUSERID_1);
			}
			else {
				if (assigneeJiraUserId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MD_P_AJUI_ASSIGNEEJIRAUSERID_3);
				}
				else {
					query.append(_FINDER_COLUMN_MD_P_AJUI_ASSIGNEEJIRAUSERID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (modifiedDate != null) {
					qPos.add(CalendarUtil.getTimestamp(modifiedDate));
				}

				qPos.add(projectId);

				if (assigneeJiraUserId != null) {
					qPos.add(assigneeJiraUserId);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MD_P_AJUI,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of j i r a issues where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	 *
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param status the status
	 * @return the number of matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public int countByP_RJUI_S(long projectId, String reporterJiraUserId,
		String status) throws SystemException {
		Object[] finderArgs = new Object[] { projectId, reporterJiraUserId, status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_P_RJUI_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_JIRAISSUE_WHERE);

			query.append(_FINDER_COLUMN_P_RJUI_S_PROJECTID_2);

			if (reporterJiraUserId == null) {
				query.append(_FINDER_COLUMN_P_RJUI_S_REPORTERJIRAUSERID_1);
			}
			else {
				if (reporterJiraUserId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_P_RJUI_S_REPORTERJIRAUSERID_3);
				}
				else {
					query.append(_FINDER_COLUMN_P_RJUI_S_REPORTERJIRAUSERID_2);
				}
			}

			if (status == null) {
				query.append(_FINDER_COLUMN_P_RJUI_S_STATUS_1);
			}
			else {
				if (status.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_P_RJUI_S_STATUS_3);
				}
				else {
					query.append(_FINDER_COLUMN_P_RJUI_S_STATUS_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

				if (reporterJiraUserId != null) {
					qPos.add(reporterJiraUserId);
				}

				if (status != null) {
					qPos.add(status);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_P_RJUI_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	 *
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param status the status
	 * @return the number of matching j i r a issues
	 * @throws SystemException if a system exception occurred
	 */
	public int countByP_AJUI_S(long projectId, String assigneeJiraUserId,
		String status) throws SystemException {
		Object[] finderArgs = new Object[] { projectId, assigneeJiraUserId, status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_P_AJUI_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_JIRAISSUE_WHERE);

			query.append(_FINDER_COLUMN_P_AJUI_S_PROJECTID_2);

			if (assigneeJiraUserId == null) {
				query.append(_FINDER_COLUMN_P_AJUI_S_ASSIGNEEJIRAUSERID_1);
			}
			else {
				if (assigneeJiraUserId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_P_AJUI_S_ASSIGNEEJIRAUSERID_3);
				}
				else {
					query.append(_FINDER_COLUMN_P_AJUI_S_ASSIGNEEJIRAUSERID_2);
				}
			}

			if (status == null) {
				query.append(_FINDER_COLUMN_P_AJUI_S_STATUS_1);
			}
			else {
				if (status.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_P_AJUI_S_STATUS_3);
				}
				else {
					query.append(_FINDER_COLUMN_P_AJUI_S_STATUS_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

				if (assigneeJiraUserId != null) {
					qPos.add(assigneeJiraUserId);
				}

				if (status != null) {
					qPos.add(status);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_P_AJUI_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of j i r a issues.
	 *
	 * @return the number of j i r a issues
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

				Query q = session.createQuery(_SQL_COUNT_JIRAISSUE);

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
	 * Initializes the j i r a issue persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.socialcoding.model.JIRAIssue")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<JIRAIssue>> listenersList = new ArrayList<ModelListener<JIRAIssue>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<JIRAIssue>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(JIRAIssueImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST);
	}

	@BeanReference(type = JIRAActionPersistence.class)
	protected JIRAActionPersistence jiraActionPersistence;
	@BeanReference(type = JIRAChangeGroupPersistence.class)
	protected JIRAChangeGroupPersistence jiraChangeGroupPersistence;
	@BeanReference(type = JIRAChangeItemPersistence.class)
	protected JIRAChangeItemPersistence jiraChangeItemPersistence;
	@BeanReference(type = JIRAIssuePersistence.class)
	protected JIRAIssuePersistence jiraIssuePersistence;
	@BeanReference(type = SVNRepositoryPersistence.class)
	protected SVNRepositoryPersistence svnRepositoryPersistence;
	@BeanReference(type = SVNRevisionPersistence.class)
	protected SVNRevisionPersistence svnRevisionPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_JIRAISSUE = "SELECT jiraIssue FROM JIRAIssue jiraIssue";
	private static final String _SQL_SELECT_JIRAISSUE_WHERE = "SELECT jiraIssue FROM JIRAIssue jiraIssue WHERE ";
	private static final String _SQL_COUNT_JIRAISSUE = "SELECT COUNT(jiraIssue) FROM JIRAIssue jiraIssue";
	private static final String _SQL_COUNT_JIRAISSUE_WHERE = "SELECT COUNT(jiraIssue) FROM JIRAIssue jiraIssue WHERE ";
	private static final String _FINDER_COLUMN_PROJECTID_PROJECTID_2 = "jiraIssue.projectId = ?";
	private static final String _FINDER_COLUMN_KEY_KEY_1 = "jiraIssue.key IS NULL";
	private static final String _FINDER_COLUMN_KEY_KEY_2 = "jiraIssue.key = ?";
	private static final String _FINDER_COLUMN_KEY_KEY_3 = "(jiraIssue.key IS NULL OR jiraIssue.key = ?)";
	private static final String _FINDER_COLUMN_REPORTERJIRAUSERID_REPORTERJIRAUSERID_1 =
		"jiraIssue.reporterJiraUserId IS NULL";
	private static final String _FINDER_COLUMN_REPORTERJIRAUSERID_REPORTERJIRAUSERID_2 =
		"jiraIssue.reporterJiraUserId = ?";
	private static final String _FINDER_COLUMN_REPORTERJIRAUSERID_REPORTERJIRAUSERID_3 =
		"(jiraIssue.reporterJiraUserId IS NULL OR jiraIssue.reporterJiraUserId = ?)";
	private static final String _FINDER_COLUMN_ASSIGNEEJIRAUSERID_ASSIGNEEJIRAUSERID_1 =
		"jiraIssue.assigneeJiraUserId IS NULL";
	private static final String _FINDER_COLUMN_ASSIGNEEJIRAUSERID_ASSIGNEEJIRAUSERID_2 =
		"jiraIssue.assigneeJiraUserId = ?";
	private static final String _FINDER_COLUMN_ASSIGNEEJIRAUSERID_ASSIGNEEJIRAUSERID_3 =
		"(jiraIssue.assigneeJiraUserId IS NULL OR jiraIssue.assigneeJiraUserId = ?)";
	private static final String _FINDER_COLUMN_MD_P_MODIFIEDDATE_1 = "jiraIssue.modifiedDate > NULL AND ";
	private static final String _FINDER_COLUMN_MD_P_MODIFIEDDATE_2 = "jiraIssue.modifiedDate > ? AND ";
	private static final String _FINDER_COLUMN_MD_P_PROJECTID_2 = "jiraIssue.projectId = ?";
	private static final String _FINDER_COLUMN_P_RJUI_PROJECTID_2 = "jiraIssue.projectId = ? AND ";
	private static final String _FINDER_COLUMN_P_RJUI_REPORTERJIRAUSERID_1 = "jiraIssue.reporterJiraUserId IS NULL";
	private static final String _FINDER_COLUMN_P_RJUI_REPORTERJIRAUSERID_2 = "jiraIssue.reporterJiraUserId = ?";
	private static final String _FINDER_COLUMN_P_RJUI_REPORTERJIRAUSERID_3 = "(jiraIssue.reporterJiraUserId IS NULL OR jiraIssue.reporterJiraUserId = ?)";
	private static final String _FINDER_COLUMN_P_AJUI_PROJECTID_2 = "jiraIssue.projectId = ? AND ";
	private static final String _FINDER_COLUMN_P_AJUI_ASSIGNEEJIRAUSERID_1 = "jiraIssue.assigneeJiraUserId IS NULL";
	private static final String _FINDER_COLUMN_P_AJUI_ASSIGNEEJIRAUSERID_2 = "jiraIssue.assigneeJiraUserId = ?";
	private static final String _FINDER_COLUMN_P_AJUI_ASSIGNEEJIRAUSERID_3 = "(jiraIssue.assigneeJiraUserId IS NULL OR jiraIssue.assigneeJiraUserId = ?)";
	private static final String _FINDER_COLUMN_MD_P_RJUI_MODIFIEDDATE_1 = "jiraIssue.modifiedDate > NULL AND ";
	private static final String _FINDER_COLUMN_MD_P_RJUI_MODIFIEDDATE_2 = "jiraIssue.modifiedDate > ? AND ";
	private static final String _FINDER_COLUMN_MD_P_RJUI_PROJECTID_2 = "jiraIssue.projectId = ? AND ";
	private static final String _FINDER_COLUMN_MD_P_RJUI_REPORTERJIRAUSERID_1 = "jiraIssue.reporterJiraUserId IS NULL";
	private static final String _FINDER_COLUMN_MD_P_RJUI_REPORTERJIRAUSERID_2 = "jiraIssue.reporterJiraUserId = ?";
	private static final String _FINDER_COLUMN_MD_P_RJUI_REPORTERJIRAUSERID_3 = "(jiraIssue.reporterJiraUserId IS NULL OR jiraIssue.reporterJiraUserId = ?)";
	private static final String _FINDER_COLUMN_MD_P_AJUI_MODIFIEDDATE_1 = "jiraIssue.modifiedDate > NULL AND ";
	private static final String _FINDER_COLUMN_MD_P_AJUI_MODIFIEDDATE_2 = "jiraIssue.modifiedDate > ? AND ";
	private static final String _FINDER_COLUMN_MD_P_AJUI_PROJECTID_2 = "jiraIssue.projectId = ? AND ";
	private static final String _FINDER_COLUMN_MD_P_AJUI_ASSIGNEEJIRAUSERID_1 = "jiraIssue.assigneeJiraUserId IS NULL";
	private static final String _FINDER_COLUMN_MD_P_AJUI_ASSIGNEEJIRAUSERID_2 = "jiraIssue.assigneeJiraUserId = ?";
	private static final String _FINDER_COLUMN_MD_P_AJUI_ASSIGNEEJIRAUSERID_3 = "(jiraIssue.assigneeJiraUserId IS NULL OR jiraIssue.assigneeJiraUserId = ?)";
	private static final String _FINDER_COLUMN_P_RJUI_S_PROJECTID_2 = "jiraIssue.projectId = ? AND ";
	private static final String _FINDER_COLUMN_P_RJUI_S_REPORTERJIRAUSERID_1 = "jiraIssue.reporterJiraUserId IS NULL AND ";
	private static final String _FINDER_COLUMN_P_RJUI_S_REPORTERJIRAUSERID_2 = "jiraIssue.reporterJiraUserId = ? AND ";
	private static final String _FINDER_COLUMN_P_RJUI_S_REPORTERJIRAUSERID_3 = "(jiraIssue.reporterJiraUserId IS NULL OR jiraIssue.reporterJiraUserId = ?) AND ";
	private static final String _FINDER_COLUMN_P_RJUI_S_STATUS_1 = "jiraIssue.status IS NULL";
	private static final String _FINDER_COLUMN_P_RJUI_S_STATUS_2 = "jiraIssue.status = ?";
	private static final String _FINDER_COLUMN_P_RJUI_S_STATUS_3 = "(jiraIssue.status IS NULL OR jiraIssue.status = ?)";
	private static final String _FINDER_COLUMN_P_AJUI_S_PROJECTID_2 = "jiraIssue.projectId = ? AND ";
	private static final String _FINDER_COLUMN_P_AJUI_S_ASSIGNEEJIRAUSERID_1 = "jiraIssue.assigneeJiraUserId IS NULL AND ";
	private static final String _FINDER_COLUMN_P_AJUI_S_ASSIGNEEJIRAUSERID_2 = "jiraIssue.assigneeJiraUserId = ? AND ";
	private static final String _FINDER_COLUMN_P_AJUI_S_ASSIGNEEJIRAUSERID_3 = "(jiraIssue.assigneeJiraUserId IS NULL OR jiraIssue.assigneeJiraUserId = ?) AND ";
	private static final String _FINDER_COLUMN_P_AJUI_S_STATUS_1 = "jiraIssue.status IS NULL";
	private static final String _FINDER_COLUMN_P_AJUI_S_STATUS_2 = "jiraIssue.status = ?";
	private static final String _FINDER_COLUMN_P_AJUI_S_STATUS_3 = "(jiraIssue.status IS NULL OR jiraIssue.status = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "jiraIssue.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No JIRAIssue exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No JIRAIssue exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(JIRAIssuePersistenceImpl.class);
	private static JIRAIssue _nullJIRAIssue = new JIRAIssueImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<JIRAIssue> toCacheModel() {
				return _nullJIRAIssueCacheModel;
			}
		};

	private static CacheModel<JIRAIssue> _nullJIRAIssueCacheModel = new CacheModel<JIRAIssue>() {
			public JIRAIssue toEntityModel() {
				return _nullJIRAIssue;
			}
		};
}