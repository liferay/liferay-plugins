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

package com.liferay.portal.workflow.kaleo.service.persistence;

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
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.workflow.kaleo.NoSuchTaskFormException;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskForm;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskFormImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskFormModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the kaleo task form service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskFormPersistence
 * @see KaleoTaskFormUtil
 * @generated
 */
public class KaleoTaskFormPersistenceImpl extends BasePersistenceImpl<KaleoTaskForm>
	implements KaleoTaskFormPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link KaleoTaskFormUtil} to access the kaleo task form persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoTaskFormImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_COMPANYID = new FinderPath(KaleoTaskFormModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskFormModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskFormImpl.class, FINDER_CLASS_NAME_LIST, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(KaleoTaskFormModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskFormModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_KALEODEFINITIONID = new FinderPath(KaleoTaskFormModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskFormModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskFormImpl.class, FINDER_CLASS_NAME_LIST,
			"findByKaleoDefinitionId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEODEFINITIONID = new FinderPath(KaleoTaskFormModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskFormModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByKaleoDefinitionId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_KALEOTASKID = new FinderPath(KaleoTaskFormModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskFormModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskFormImpl.class, FINDER_CLASS_NAME_LIST,
			"findByKaleoTaskId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEOTASKID = new FinderPath(KaleoTaskFormModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskFormModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByKaleoTaskId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(KaleoTaskFormModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskFormModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskFormImpl.class, FINDER_CLASS_NAME_LIST, "findAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoTaskFormModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskFormModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the kaleo task form in the entity cache if it is enabled.
	 *
	 * @param kaleoTaskForm the kaleo task form
	 */
	public void cacheResult(KaleoTaskForm kaleoTaskForm) {
		EntityCacheUtil.putResult(KaleoTaskFormModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskFormImpl.class, kaleoTaskForm.getPrimaryKey(),
			kaleoTaskForm);

		kaleoTaskForm.resetOriginalValues();
	}

	/**
	 * Caches the kaleo task forms in the entity cache if it is enabled.
	 *
	 * @param kaleoTaskForms the kaleo task forms
	 */
	public void cacheResult(List<KaleoTaskForm> kaleoTaskForms) {
		for (KaleoTaskForm kaleoTaskForm : kaleoTaskForms) {
			if (EntityCacheUtil.getResult(
						KaleoTaskFormModelImpl.ENTITY_CACHE_ENABLED,
						KaleoTaskFormImpl.class, kaleoTaskForm.getPrimaryKey(),
						this) == null) {
				cacheResult(kaleoTaskForm);
			}
		}
	}

	/**
	 * Clears the cache for all kaleo task forms.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(KaleoTaskFormImpl.class.getName());
		}

		EntityCacheUtil.clearCache(KaleoTaskFormImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the kaleo task form.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(KaleoTaskForm kaleoTaskForm) {
		EntityCacheUtil.removeResult(KaleoTaskFormModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskFormImpl.class, kaleoTaskForm.getPrimaryKey());
	}

	/**
	 * Creates a new kaleo task form with the primary key. Does not add the kaleo task form to the database.
	 *
	 * @param kaleoTaskFormId the primary key for the new kaleo task form
	 * @return the new kaleo task form
	 */
	public KaleoTaskForm create(long kaleoTaskFormId) {
		KaleoTaskForm kaleoTaskForm = new KaleoTaskFormImpl();

		kaleoTaskForm.setNew(true);
		kaleoTaskForm.setPrimaryKey(kaleoTaskFormId);

		return kaleoTaskForm;
	}

	/**
	 * Removes the kaleo task form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the kaleo task form
	 * @return the kaleo task form that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a kaleo task form with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskForm remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the kaleo task form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoTaskFormId the primary key of the kaleo task form
	 * @return the kaleo task form that was removed
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskFormException if a kaleo task form with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskForm remove(long kaleoTaskFormId)
		throws NoSuchTaskFormException, SystemException {
		Session session = null;

		try {
			session = openSession();

			KaleoTaskForm kaleoTaskForm = (KaleoTaskForm)session.get(KaleoTaskFormImpl.class,
					Long.valueOf(kaleoTaskFormId));

			if (kaleoTaskForm == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						kaleoTaskFormId);
				}

				throw new NoSuchTaskFormException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					kaleoTaskFormId);
			}

			return kaleoTaskFormPersistence.remove(kaleoTaskForm);
		}
		catch (NoSuchTaskFormException nsee) {
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
	 * Removes the kaleo task form from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoTaskForm the kaleo task form
	 * @return the kaleo task form that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskForm remove(KaleoTaskForm kaleoTaskForm)
		throws SystemException {
		return super.remove(kaleoTaskForm);
	}

	@Override
	protected KaleoTaskForm removeImpl(KaleoTaskForm kaleoTaskForm)
		throws SystemException {
		kaleoTaskForm = toUnwrappedModel(kaleoTaskForm);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, kaleoTaskForm);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(KaleoTaskFormModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskFormImpl.class, kaleoTaskForm.getPrimaryKey());

		return kaleoTaskForm;
	}

	@Override
	public KaleoTaskForm updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskForm kaleoTaskForm,
		boolean merge) throws SystemException {
		kaleoTaskForm = toUnwrappedModel(kaleoTaskForm);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, kaleoTaskForm, merge);

			kaleoTaskForm.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(KaleoTaskFormModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskFormImpl.class, kaleoTaskForm.getPrimaryKey(),
			kaleoTaskForm);

		return kaleoTaskForm;
	}

	protected KaleoTaskForm toUnwrappedModel(KaleoTaskForm kaleoTaskForm) {
		if (kaleoTaskForm instanceof KaleoTaskFormImpl) {
			return kaleoTaskForm;
		}

		KaleoTaskFormImpl kaleoTaskFormImpl = new KaleoTaskFormImpl();

		kaleoTaskFormImpl.setNew(kaleoTaskForm.isNew());
		kaleoTaskFormImpl.setPrimaryKey(kaleoTaskForm.getPrimaryKey());

		kaleoTaskFormImpl.setKaleoTaskFormId(kaleoTaskForm.getKaleoTaskFormId());
		kaleoTaskFormImpl.setGroupId(kaleoTaskForm.getGroupId());
		kaleoTaskFormImpl.setCompanyId(kaleoTaskForm.getCompanyId());
		kaleoTaskFormImpl.setUserId(kaleoTaskForm.getUserId());
		kaleoTaskFormImpl.setUserName(kaleoTaskForm.getUserName());
		kaleoTaskFormImpl.setCreateDate(kaleoTaskForm.getCreateDate());
		kaleoTaskFormImpl.setModifiedDate(kaleoTaskForm.getModifiedDate());
		kaleoTaskFormImpl.setKaleoDefinitionId(kaleoTaskForm.getKaleoDefinitionId());
		kaleoTaskFormImpl.setKaleoTaskId(kaleoTaskForm.getKaleoTaskId());
		kaleoTaskFormImpl.setDescription(kaleoTaskForm.getDescription());
		kaleoTaskFormImpl.setFormTemplateId(kaleoTaskForm.getFormTemplateId());

		return kaleoTaskFormImpl;
	}

	/**
	 * Returns the kaleo task form with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo task form
	 * @return the kaleo task form
	 * @throws com.liferay.portal.NoSuchModelException if a kaleo task form with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskForm findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the kaleo task form with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchTaskFormException} if it could not be found.
	 *
	 * @param kaleoTaskFormId the primary key of the kaleo task form
	 * @return the kaleo task form
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskFormException if a kaleo task form with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskForm findByPrimaryKey(long kaleoTaskFormId)
		throws NoSuchTaskFormException, SystemException {
		KaleoTaskForm kaleoTaskForm = fetchByPrimaryKey(kaleoTaskFormId);

		if (kaleoTaskForm == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + kaleoTaskFormId);
			}

			throw new NoSuchTaskFormException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				kaleoTaskFormId);
		}

		return kaleoTaskForm;
	}

	/**
	 * Returns the kaleo task form with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo task form
	 * @return the kaleo task form, or <code>null</code> if a kaleo task form with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskForm fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the kaleo task form with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kaleoTaskFormId the primary key of the kaleo task form
	 * @return the kaleo task form, or <code>null</code> if a kaleo task form with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskForm fetchByPrimaryKey(long kaleoTaskFormId)
		throws SystemException {
		KaleoTaskForm kaleoTaskForm = (KaleoTaskForm)EntityCacheUtil.getResult(KaleoTaskFormModelImpl.ENTITY_CACHE_ENABLED,
				KaleoTaskFormImpl.class, kaleoTaskFormId, this);

		if (kaleoTaskForm == _nullKaleoTaskForm) {
			return null;
		}

		if (kaleoTaskForm == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				kaleoTaskForm = (KaleoTaskForm)session.get(KaleoTaskFormImpl.class,
						Long.valueOf(kaleoTaskFormId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (kaleoTaskForm != null) {
					cacheResult(kaleoTaskForm);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(KaleoTaskFormModelImpl.ENTITY_CACHE_ENABLED,
						KaleoTaskFormImpl.class, kaleoTaskFormId,
						_nullKaleoTaskForm);
				}

				closeSession(session);
			}
		}

		return kaleoTaskForm;
	}

	/**
	 * Returns all the kaleo task forms where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching kaleo task forms
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskForm> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the kaleo task forms where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo task forms
	 * @param end the upper bound of the range of kaleo task forms (not inclusive)
	 * @return the range of matching kaleo task forms
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskForm> findByCompanyId(long companyId, int start,
		int end) throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo task forms where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo task forms
	 * @param end the upper bound of the range of kaleo task forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo task forms
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskForm> findByCompanyId(long companyId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoTaskForm> list = (List<KaleoTaskForm>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_COMPANYID,
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

			query.append(_SQL_SELECT_KALEOTASKFORM_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoTaskFormModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<KaleoTaskForm>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_COMPANYID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_COMPANYID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first kaleo task form in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo task form
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskFormException if a matching kaleo task form could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskForm findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskFormException, SystemException {
		List<KaleoTaskForm> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskFormException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last kaleo task form in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo task form
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskFormException if a matching kaleo task form could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskForm findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskFormException, SystemException {
		int count = countByCompanyId(companyId);

		List<KaleoTaskForm> list = findByCompanyId(companyId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskFormException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the kaleo task forms before and after the current kaleo task form in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTaskFormId the primary key of the current kaleo task form
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo task form
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskFormException if a kaleo task form with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskForm[] findByCompanyId_PrevAndNext(long kaleoTaskFormId,
		long companyId, OrderByComparator orderByComparator)
		throws NoSuchTaskFormException, SystemException {
		KaleoTaskForm kaleoTaskForm = findByPrimaryKey(kaleoTaskFormId);

		Session session = null;

		try {
			session = openSession();

			KaleoTaskForm[] array = new KaleoTaskFormImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, kaleoTaskForm,
					companyId, orderByComparator, true);

			array[1] = kaleoTaskForm;

			array[2] = getByCompanyId_PrevAndNext(session, kaleoTaskForm,
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

	protected KaleoTaskForm getByCompanyId_PrevAndNext(Session session,
		KaleoTaskForm kaleoTaskForm, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTASKFORM_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

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
			query.append(KaleoTaskFormModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoTaskForm);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTaskForm> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the kaleo task forms where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the matching kaleo task forms
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskForm> findByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		return findByKaleoDefinitionId(kaleoDefinitionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo task forms where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo task forms
	 * @param end the upper bound of the range of kaleo task forms (not inclusive)
	 * @return the range of matching kaleo task forms
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskForm> findByKaleoDefinitionId(long kaleoDefinitionId,
		int start, int end) throws SystemException {
		return findByKaleoDefinitionId(kaleoDefinitionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo task forms where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo task forms
	 * @param end the upper bound of the range of kaleo task forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo task forms
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskForm> findByKaleoDefinitionId(long kaleoDefinitionId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				kaleoDefinitionId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoTaskForm> list = (List<KaleoTaskForm>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_KALEODEFINITIONID,
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

			query.append(_SQL_SELECT_KALEOTASKFORM_WHERE);

			query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoTaskFormModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoDefinitionId);

				list = (List<KaleoTaskForm>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_KALEODEFINITIONID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_KALEODEFINITIONID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first kaleo task form in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo task form
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskFormException if a matching kaleo task form could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskForm findByKaleoDefinitionId_First(long kaleoDefinitionId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskFormException, SystemException {
		List<KaleoTaskForm> list = findByKaleoDefinitionId(kaleoDefinitionId,
				0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoDefinitionId=");
			msg.append(kaleoDefinitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskFormException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last kaleo task form in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo task form
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskFormException if a matching kaleo task form could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskForm findByKaleoDefinitionId_Last(long kaleoDefinitionId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskFormException, SystemException {
		int count = countByKaleoDefinitionId(kaleoDefinitionId);

		List<KaleoTaskForm> list = findByKaleoDefinitionId(kaleoDefinitionId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoDefinitionId=");
			msg.append(kaleoDefinitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskFormException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the kaleo task forms before and after the current kaleo task form in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTaskFormId the primary key of the current kaleo task form
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo task form
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskFormException if a kaleo task form with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskForm[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoTaskFormId, long kaleoDefinitionId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskFormException, SystemException {
		KaleoTaskForm kaleoTaskForm = findByPrimaryKey(kaleoTaskFormId);

		Session session = null;

		try {
			session = openSession();

			KaleoTaskForm[] array = new KaleoTaskFormImpl[3];

			array[0] = getByKaleoDefinitionId_PrevAndNext(session,
					kaleoTaskForm, kaleoDefinitionId, orderByComparator, true);

			array[1] = kaleoTaskForm;

			array[2] = getByKaleoDefinitionId_PrevAndNext(session,
					kaleoTaskForm, kaleoDefinitionId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoTaskForm getByKaleoDefinitionId_PrevAndNext(
		Session session, KaleoTaskForm kaleoTaskForm, long kaleoDefinitionId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTASKFORM_WHERE);

		query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

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
			query.append(KaleoTaskFormModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoDefinitionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoTaskForm);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTaskForm> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the kaleo task forms where kaleoTaskId = &#63;.
	 *
	 * @param kaleoTaskId the kaleo task ID
	 * @return the matching kaleo task forms
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskForm> findByKaleoTaskId(long kaleoTaskId)
		throws SystemException {
		return findByKaleoTaskId(kaleoTaskId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo task forms where kaleoTaskId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTaskId the kaleo task ID
	 * @param start the lower bound of the range of kaleo task forms
	 * @param end the upper bound of the range of kaleo task forms (not inclusive)
	 * @return the range of matching kaleo task forms
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskForm> findByKaleoTaskId(long kaleoTaskId, int start,
		int end) throws SystemException {
		return findByKaleoTaskId(kaleoTaskId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo task forms where kaleoTaskId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTaskId the kaleo task ID
	 * @param start the lower bound of the range of kaleo task forms
	 * @param end the upper bound of the range of kaleo task forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo task forms
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskForm> findByKaleoTaskId(long kaleoTaskId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				kaleoTaskId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoTaskForm> list = (List<KaleoTaskForm>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_KALEOTASKID,
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

			query.append(_SQL_SELECT_KALEOTASKFORM_WHERE);

			query.append(_FINDER_COLUMN_KALEOTASKID_KALEOTASKID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoTaskFormModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoTaskId);

				list = (List<KaleoTaskForm>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_KALEOTASKID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_KALEOTASKID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first kaleo task form in the ordered set where kaleoTaskId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTaskId the kaleo task ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo task form
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskFormException if a matching kaleo task form could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskForm findByKaleoTaskId_First(long kaleoTaskId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskFormException, SystemException {
		List<KaleoTaskForm> list = findByKaleoTaskId(kaleoTaskId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoTaskId=");
			msg.append(kaleoTaskId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskFormException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last kaleo task form in the ordered set where kaleoTaskId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTaskId the kaleo task ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo task form
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskFormException if a matching kaleo task form could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskForm findByKaleoTaskId_Last(long kaleoTaskId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskFormException, SystemException {
		int count = countByKaleoTaskId(kaleoTaskId);

		List<KaleoTaskForm> list = findByKaleoTaskId(kaleoTaskId, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoTaskId=");
			msg.append(kaleoTaskId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskFormException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the kaleo task forms before and after the current kaleo task form in the ordered set where kaleoTaskId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTaskFormId the primary key of the current kaleo task form
	 * @param kaleoTaskId the kaleo task ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo task form
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskFormException if a kaleo task form with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskForm[] findByKaleoTaskId_PrevAndNext(long kaleoTaskFormId,
		long kaleoTaskId, OrderByComparator orderByComparator)
		throws NoSuchTaskFormException, SystemException {
		KaleoTaskForm kaleoTaskForm = findByPrimaryKey(kaleoTaskFormId);

		Session session = null;

		try {
			session = openSession();

			KaleoTaskForm[] array = new KaleoTaskFormImpl[3];

			array[0] = getByKaleoTaskId_PrevAndNext(session, kaleoTaskForm,
					kaleoTaskId, orderByComparator, true);

			array[1] = kaleoTaskForm;

			array[2] = getByKaleoTaskId_PrevAndNext(session, kaleoTaskForm,
					kaleoTaskId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoTaskForm getByKaleoTaskId_PrevAndNext(Session session,
		KaleoTaskForm kaleoTaskForm, long kaleoTaskId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTASKFORM_WHERE);

		query.append(_FINDER_COLUMN_KALEOTASKID_KALEOTASKID_2);

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
			query.append(KaleoTaskFormModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoTaskId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoTaskForm);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTaskForm> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the kaleo task forms.
	 *
	 * @return the kaleo task forms
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskForm> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo task forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo task forms
	 * @param end the upper bound of the range of kaleo task forms (not inclusive)
	 * @return the range of kaleo task forms
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskForm> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo task forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo task forms
	 * @param end the upper bound of the range of kaleo task forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kaleo task forms
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskForm> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoTaskForm> list = (List<KaleoTaskForm>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_KALEOTASKFORM);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_KALEOTASKFORM.concat(KaleoTaskFormModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<KaleoTaskForm>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<KaleoTaskForm>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the kaleo task forms where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCompanyId(long companyId) throws SystemException {
		for (KaleoTaskForm kaleoTaskForm : findByCompanyId(companyId)) {
			kaleoTaskFormPersistence.remove(kaleoTaskForm);
		}
	}

	/**
	 * Removes all the kaleo task forms where kaleoDefinitionId = &#63; from the database.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		for (KaleoTaskForm kaleoTaskForm : findByKaleoDefinitionId(
				kaleoDefinitionId)) {
			kaleoTaskFormPersistence.remove(kaleoTaskForm);
		}
	}

	/**
	 * Removes all the kaleo task forms where kaleoTaskId = &#63; from the database.
	 *
	 * @param kaleoTaskId the kaleo task ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByKaleoTaskId(long kaleoTaskId) throws SystemException {
		for (KaleoTaskForm kaleoTaskForm : findByKaleoTaskId(kaleoTaskId)) {
			kaleoTaskFormPersistence.remove(kaleoTaskForm);
		}
	}

	/**
	 * Removes all the kaleo task forms from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (KaleoTaskForm kaleoTaskForm : findAll()) {
			kaleoTaskFormPersistence.remove(kaleoTaskForm);
		}
	}

	/**
	 * Returns the number of kaleo task forms where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching kaleo task forms
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOTASKFORM_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COMPANYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of kaleo task forms where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the number of matching kaleo task forms
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		Object[] finderArgs = new Object[] { kaleoDefinitionId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOTASKFORM_WHERE);

			query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoDefinitionId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of kaleo task forms where kaleoTaskId = &#63;.
	 *
	 * @param kaleoTaskId the kaleo task ID
	 * @return the number of matching kaleo task forms
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKaleoTaskId(long kaleoTaskId) throws SystemException {
		Object[] finderArgs = new Object[] { kaleoTaskId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KALEOTASKID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOTASKFORM_WHERE);

			query.append(_FINDER_COLUMN_KALEOTASKID_KALEOTASKID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoTaskId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KALEOTASKID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of kaleo task forms.
	 *
	 * @return the number of kaleo task forms
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

				Query q = session.createQuery(_SQL_COUNT_KALEOTASKFORM);

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
	 * Initializes the kaleo task form persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portal.workflow.kaleo.model.KaleoTaskForm")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<KaleoTaskForm>> listenersList = new ArrayList<ModelListener<KaleoTaskForm>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<KaleoTaskForm>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(KaleoTaskFormImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST);
	}

	@BeanReference(type = KaleoActionPersistence.class)
	protected KaleoActionPersistence kaleoActionPersistence;
	@BeanReference(type = KaleoConditionPersistence.class)
	protected KaleoConditionPersistence kaleoConditionPersistence;
	@BeanReference(type = KaleoDefinitionPersistence.class)
	protected KaleoDefinitionPersistence kaleoDefinitionPersistence;
	@BeanReference(type = KaleoInstancePersistence.class)
	protected KaleoInstancePersistence kaleoInstancePersistence;
	@BeanReference(type = KaleoInstanceTokenPersistence.class)
	protected KaleoInstanceTokenPersistence kaleoInstanceTokenPersistence;
	@BeanReference(type = KaleoLogPersistence.class)
	protected KaleoLogPersistence kaleoLogPersistence;
	@BeanReference(type = KaleoNodePersistence.class)
	protected KaleoNodePersistence kaleoNodePersistence;
	@BeanReference(type = KaleoNotificationPersistence.class)
	protected KaleoNotificationPersistence kaleoNotificationPersistence;
	@BeanReference(type = KaleoNotificationRecipientPersistence.class)
	protected KaleoNotificationRecipientPersistence kaleoNotificationRecipientPersistence;
	@BeanReference(type = KaleoTaskPersistence.class)
	protected KaleoTaskPersistence kaleoTaskPersistence;
	@BeanReference(type = KaleoTaskAssignmentPersistence.class)
	protected KaleoTaskAssignmentPersistence kaleoTaskAssignmentPersistence;
	@BeanReference(type = KaleoTaskAssignmentInstancePersistence.class)
	protected KaleoTaskAssignmentInstancePersistence kaleoTaskAssignmentInstancePersistence;
	@BeanReference(type = KaleoTaskFormPersistence.class)
	protected KaleoTaskFormPersistence kaleoTaskFormPersistence;
	@BeanReference(type = KaleoTaskInstanceTokenPersistence.class)
	protected KaleoTaskInstanceTokenPersistence kaleoTaskInstanceTokenPersistence;
	@BeanReference(type = KaleoTimerPersistence.class)
	protected KaleoTimerPersistence kaleoTimerPersistence;
	@BeanReference(type = KaleoTimerInstanceTokenPersistence.class)
	protected KaleoTimerInstanceTokenPersistence kaleoTimerInstanceTokenPersistence;
	@BeanReference(type = KaleoTransitionPersistence.class)
	protected KaleoTransitionPersistence kaleoTransitionPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_KALEOTASKFORM = "SELECT kaleoTaskForm FROM KaleoTaskForm kaleoTaskForm";
	private static final String _SQL_SELECT_KALEOTASKFORM_WHERE = "SELECT kaleoTaskForm FROM KaleoTaskForm kaleoTaskForm WHERE ";
	private static final String _SQL_COUNT_KALEOTASKFORM = "SELECT COUNT(kaleoTaskForm) FROM KaleoTaskForm kaleoTaskForm";
	private static final String _SQL_COUNT_KALEOTASKFORM_WHERE = "SELECT COUNT(kaleoTaskForm) FROM KaleoTaskForm kaleoTaskForm WHERE ";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "kaleoTaskForm.companyId = ?";
	private static final String _FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2 =
		"kaleoTaskForm.kaleoDefinitionId = ?";
	private static final String _FINDER_COLUMN_KALEOTASKID_KALEOTASKID_2 = "kaleoTaskForm.kaleoTaskId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoTaskForm.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoTaskForm exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoTaskForm exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(KaleoTaskFormPersistenceImpl.class);
	private static KaleoTaskForm _nullKaleoTaskForm = new KaleoTaskFormImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<KaleoTaskForm> toCacheModel() {
				return _nullKaleoTaskFormCacheModel;
			}
		};

	private static CacheModel<KaleoTaskForm> _nullKaleoTaskFormCacheModel = new CacheModel<KaleoTaskForm>() {
			public KaleoTaskForm toEntityModel() {
				return _nullKaleoTaskForm;
			}
		};
}