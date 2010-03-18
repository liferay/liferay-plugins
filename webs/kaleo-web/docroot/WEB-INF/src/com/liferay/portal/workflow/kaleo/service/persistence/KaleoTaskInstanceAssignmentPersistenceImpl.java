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

package com.liferay.portal.workflow.kaleo.service.persistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistry;
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
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceAssignmentException;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskInstanceAssignmentImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskInstanceAssignmentModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="KaleoTaskInstanceAssignmentPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTaskInstanceAssignmentPersistence
 * @see       KaleoTaskInstanceAssignmentUtil
 * @generated
 */
public class KaleoTaskInstanceAssignmentPersistenceImpl
	extends BasePersistenceImpl<KaleoTaskInstanceAssignment>
	implements KaleoTaskInstanceAssignmentPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoTaskInstanceAssignmentImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FETCH_BY_KALEOTASKINSTANCETOKENID =
		new FinderPath(KaleoTaskInstanceAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceAssignmentModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_ENTITY, "fetchByKaleoTaskInstanceTokenId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEOTASKINSTANCETOKENID =
		new FinderPath(KaleoTaskInstanceAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceAssignmentModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByKaleoTaskInstanceTokenId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_KTITI_ACPK_ACN = new FinderPath(KaleoTaskInstanceAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceAssignmentModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_ENTITY, "fetchByKTITI_ACPK_ACN",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Long.class.getName()
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_KTITI_ACPK_ACN = new FinderPath(KaleoTaskInstanceAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceAssignmentModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByKTITI_ACPK_ACN",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Long.class.getName()
			});
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(KaleoTaskInstanceAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceAssignmentModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoTaskInstanceAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceAssignmentModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	public void cacheResult(
		KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment) {
		EntityCacheUtil.putResult(KaleoTaskInstanceAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceAssignmentImpl.class,
			kaleoTaskInstanceAssignment.getPrimaryKey(),
			kaleoTaskInstanceAssignment);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KALEOTASKINSTANCETOKENID,
			new Object[] {
				new Long(kaleoTaskInstanceAssignment.getKaleoTaskInstanceTokenId())
			}, kaleoTaskInstanceAssignment);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KTITI_ACPK_ACN,
			new Object[] {
				new Long(kaleoTaskInstanceAssignment.getKaleoTaskInstanceTokenId()),
				
			kaleoTaskInstanceAssignment.getAssigneeClassName(),
				new Long(kaleoTaskInstanceAssignment.getAssigneeClassPK())
			}, kaleoTaskInstanceAssignment);
	}

	public void cacheResult(
		List<KaleoTaskInstanceAssignment> kaleoTaskInstanceAssignments) {
		for (KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment : kaleoTaskInstanceAssignments) {
			if (EntityCacheUtil.getResult(
						KaleoTaskInstanceAssignmentModelImpl.ENTITY_CACHE_ENABLED,
						KaleoTaskInstanceAssignmentImpl.class,
						kaleoTaskInstanceAssignment.getPrimaryKey(), this) == null) {
				cacheResult(kaleoTaskInstanceAssignment);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(KaleoTaskInstanceAssignmentImpl.class.getName());
		EntityCacheUtil.clearCache(KaleoTaskInstanceAssignmentImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public KaleoTaskInstanceAssignment create(
		long kaleoTaskInstanceAssignmentId) {
		KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment = new KaleoTaskInstanceAssignmentImpl();

		kaleoTaskInstanceAssignment.setNew(true);
		kaleoTaskInstanceAssignment.setPrimaryKey(kaleoTaskInstanceAssignmentId);

		return kaleoTaskInstanceAssignment;
	}

	public KaleoTaskInstanceAssignment remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public KaleoTaskInstanceAssignment remove(
		long kaleoTaskInstanceAssignmentId)
		throws NoSuchTaskInstanceAssignmentException, SystemException {
		Session session = null;

		try {
			session = openSession();

			KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment = (KaleoTaskInstanceAssignment)session.get(KaleoTaskInstanceAssignmentImpl.class,
					new Long(kaleoTaskInstanceAssignmentId));

			if (kaleoTaskInstanceAssignment == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						kaleoTaskInstanceAssignmentId);
				}

				throw new NoSuchTaskInstanceAssignmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					kaleoTaskInstanceAssignmentId);
			}

			return remove(kaleoTaskInstanceAssignment);
		}
		catch (NoSuchTaskInstanceAssignmentException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public KaleoTaskInstanceAssignment remove(
		KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment)
		throws SystemException {
		for (ModelListener<KaleoTaskInstanceAssignment> listener : listeners) {
			listener.onBeforeRemove(kaleoTaskInstanceAssignment);
		}

		kaleoTaskInstanceAssignment = removeImpl(kaleoTaskInstanceAssignment);

		for (ModelListener<KaleoTaskInstanceAssignment> listener : listeners) {
			listener.onAfterRemove(kaleoTaskInstanceAssignment);
		}

		return kaleoTaskInstanceAssignment;
	}

	protected KaleoTaskInstanceAssignment removeImpl(
		KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment)
		throws SystemException {
		kaleoTaskInstanceAssignment = toUnwrappedModel(kaleoTaskInstanceAssignment);

		Session session = null;

		try {
			session = openSession();

			if (kaleoTaskInstanceAssignment.isCachedModel() ||
					BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(KaleoTaskInstanceAssignmentImpl.class,
						kaleoTaskInstanceAssignment.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(kaleoTaskInstanceAssignment);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		KaleoTaskInstanceAssignmentModelImpl kaleoTaskInstanceAssignmentModelImpl =
			(KaleoTaskInstanceAssignmentModelImpl)kaleoTaskInstanceAssignment;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KALEOTASKINSTANCETOKENID,
			new Object[] {
				new Long(kaleoTaskInstanceAssignmentModelImpl.getOriginalKaleoTaskInstanceTokenId())
			});

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KTITI_ACPK_ACN,
			new Object[] {
				new Long(kaleoTaskInstanceAssignmentModelImpl.getOriginalKaleoTaskInstanceTokenId()),
				
			kaleoTaskInstanceAssignmentModelImpl.getOriginalAssigneeClassName(),
				new Long(kaleoTaskInstanceAssignmentModelImpl.getOriginalAssigneeClassPK())
			});

		EntityCacheUtil.removeResult(KaleoTaskInstanceAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceAssignmentImpl.class,
			kaleoTaskInstanceAssignment.getPrimaryKey());

		return kaleoTaskInstanceAssignment;
	}

	public KaleoTaskInstanceAssignment updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment,
		boolean merge) throws SystemException {
		kaleoTaskInstanceAssignment = toUnwrappedModel(kaleoTaskInstanceAssignment);

		boolean isNew = kaleoTaskInstanceAssignment.isNew();

		KaleoTaskInstanceAssignmentModelImpl kaleoTaskInstanceAssignmentModelImpl =
			(KaleoTaskInstanceAssignmentModelImpl)kaleoTaskInstanceAssignment;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, kaleoTaskInstanceAssignment, merge);

			kaleoTaskInstanceAssignment.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(KaleoTaskInstanceAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceAssignmentImpl.class,
			kaleoTaskInstanceAssignment.getPrimaryKey(),
			kaleoTaskInstanceAssignment);

		if (!isNew &&
				(kaleoTaskInstanceAssignment.getKaleoTaskInstanceTokenId() != kaleoTaskInstanceAssignmentModelImpl.getOriginalKaleoTaskInstanceTokenId())) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KALEOTASKINSTANCETOKENID,
				new Object[] {
					new Long(kaleoTaskInstanceAssignmentModelImpl.getOriginalKaleoTaskInstanceTokenId())
				});
		}

		if (isNew ||
				(kaleoTaskInstanceAssignment.getKaleoTaskInstanceTokenId() != kaleoTaskInstanceAssignmentModelImpl.getOriginalKaleoTaskInstanceTokenId())) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KALEOTASKINSTANCETOKENID,
				new Object[] {
					new Long(kaleoTaskInstanceAssignment.getKaleoTaskInstanceTokenId())
				}, kaleoTaskInstanceAssignment);
		}

		if (!isNew &&
				((kaleoTaskInstanceAssignment.getKaleoTaskInstanceTokenId() != kaleoTaskInstanceAssignmentModelImpl.getOriginalKaleoTaskInstanceTokenId()) ||
				!Validator.equals(
					kaleoTaskInstanceAssignment.getAssigneeClassName(),
					kaleoTaskInstanceAssignmentModelImpl.getOriginalAssigneeClassName()) ||
				(kaleoTaskInstanceAssignment.getAssigneeClassPK() != kaleoTaskInstanceAssignmentModelImpl.getOriginalAssigneeClassPK()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KTITI_ACPK_ACN,
				new Object[] {
					new Long(kaleoTaskInstanceAssignmentModelImpl.getOriginalKaleoTaskInstanceTokenId()),
					
				kaleoTaskInstanceAssignmentModelImpl.getOriginalAssigneeClassName(),
					new Long(kaleoTaskInstanceAssignmentModelImpl.getOriginalAssigneeClassPK())
				});
		}

		if (isNew ||
				((kaleoTaskInstanceAssignment.getKaleoTaskInstanceTokenId() != kaleoTaskInstanceAssignmentModelImpl.getOriginalKaleoTaskInstanceTokenId()) ||
				!Validator.equals(
					kaleoTaskInstanceAssignment.getAssigneeClassName(),
					kaleoTaskInstanceAssignmentModelImpl.getOriginalAssigneeClassName()) ||
				(kaleoTaskInstanceAssignment.getAssigneeClassPK() != kaleoTaskInstanceAssignmentModelImpl.getOriginalAssigneeClassPK()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KTITI_ACPK_ACN,
				new Object[] {
					new Long(kaleoTaskInstanceAssignment.getKaleoTaskInstanceTokenId()),
					
				kaleoTaskInstanceAssignment.getAssigneeClassName(),
					new Long(kaleoTaskInstanceAssignment.getAssigneeClassPK())
				}, kaleoTaskInstanceAssignment);
		}

		return kaleoTaskInstanceAssignment;
	}

	protected KaleoTaskInstanceAssignment toUnwrappedModel(
		KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment) {
		if (kaleoTaskInstanceAssignment instanceof KaleoTaskInstanceAssignmentImpl) {
			return kaleoTaskInstanceAssignment;
		}

		KaleoTaskInstanceAssignmentImpl kaleoTaskInstanceAssignmentImpl = new KaleoTaskInstanceAssignmentImpl();

		kaleoTaskInstanceAssignmentImpl.setNew(kaleoTaskInstanceAssignment.isNew());
		kaleoTaskInstanceAssignmentImpl.setPrimaryKey(kaleoTaskInstanceAssignment.getPrimaryKey());

		kaleoTaskInstanceAssignmentImpl.setKaleoTaskInstanceAssignmentId(kaleoTaskInstanceAssignment.getKaleoTaskInstanceAssignmentId());
		kaleoTaskInstanceAssignmentImpl.setCompanyId(kaleoTaskInstanceAssignment.getCompanyId());
		kaleoTaskInstanceAssignmentImpl.setUserId(kaleoTaskInstanceAssignment.getUserId());
		kaleoTaskInstanceAssignmentImpl.setUserName(kaleoTaskInstanceAssignment.getUserName());
		kaleoTaskInstanceAssignmentImpl.setCreateDate(kaleoTaskInstanceAssignment.getCreateDate());
		kaleoTaskInstanceAssignmentImpl.setModifiedDate(kaleoTaskInstanceAssignment.getModifiedDate());
		kaleoTaskInstanceAssignmentImpl.setKaleoTaskInstanceTokenId(kaleoTaskInstanceAssignment.getKaleoTaskInstanceTokenId());
		kaleoTaskInstanceAssignmentImpl.setKaleoTaskId(kaleoTaskInstanceAssignment.getKaleoTaskId());
		kaleoTaskInstanceAssignmentImpl.setAssigneeClassName(kaleoTaskInstanceAssignment.getAssigneeClassName());
		kaleoTaskInstanceAssignmentImpl.setAssigneeClassPK(kaleoTaskInstanceAssignment.getAssigneeClassPK());
		kaleoTaskInstanceAssignmentImpl.setCompletionDate(kaleoTaskInstanceAssignment.getCompletionDate());
		kaleoTaskInstanceAssignmentImpl.setContext(kaleoTaskInstanceAssignment.getContext());

		return kaleoTaskInstanceAssignmentImpl;
	}

	public KaleoTaskInstanceAssignment findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	public KaleoTaskInstanceAssignment findByPrimaryKey(
		long kaleoTaskInstanceAssignmentId)
		throws NoSuchTaskInstanceAssignmentException, SystemException {
		KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment = fetchByPrimaryKey(kaleoTaskInstanceAssignmentId);

		if (kaleoTaskInstanceAssignment == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					kaleoTaskInstanceAssignmentId);
			}

			throw new NoSuchTaskInstanceAssignmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				kaleoTaskInstanceAssignmentId);
		}

		return kaleoTaskInstanceAssignment;
	}

	public KaleoTaskInstanceAssignment fetchByPrimaryKey(
		Serializable primaryKey) throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public KaleoTaskInstanceAssignment fetchByPrimaryKey(
		long kaleoTaskInstanceAssignmentId) throws SystemException {
		KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment = (KaleoTaskInstanceAssignment)EntityCacheUtil.getResult(KaleoTaskInstanceAssignmentModelImpl.ENTITY_CACHE_ENABLED,
				KaleoTaskInstanceAssignmentImpl.class,
				kaleoTaskInstanceAssignmentId, this);

		if (kaleoTaskInstanceAssignment == null) {
			Session session = null;

			try {
				session = openSession();

				kaleoTaskInstanceAssignment = (KaleoTaskInstanceAssignment)session.get(KaleoTaskInstanceAssignmentImpl.class,
						new Long(kaleoTaskInstanceAssignmentId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (kaleoTaskInstanceAssignment != null) {
					cacheResult(kaleoTaskInstanceAssignment);
				}

				closeSession(session);
			}
		}

		return kaleoTaskInstanceAssignment;
	}

	public KaleoTaskInstanceAssignment findByKaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId)
		throws NoSuchTaskInstanceAssignmentException, SystemException {
		KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment = fetchByKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);

		if (kaleoTaskInstanceAssignment == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoTaskInstanceTokenId=");
			msg.append(kaleoTaskInstanceTokenId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchTaskInstanceAssignmentException(msg.toString());
		}

		return kaleoTaskInstanceAssignment;
	}

	public KaleoTaskInstanceAssignment fetchByKaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId) throws SystemException {
		return fetchByKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId, true);
	}

	public KaleoTaskInstanceAssignment fetchByKaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(kaleoTaskInstanceTokenId) };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_KALEOTASKINSTANCETOKENID,
					finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(3);

				query.append(_SQL_SELECT_KALEOTASKINSTANCEASSIGNMENT_WHERE);

				query.append(_FINDER_COLUMN_KALEOTASKINSTANCETOKENID_KALEOTASKINSTANCETOKENID_2);

				query.append(KaleoTaskInstanceAssignmentModelImpl.ORDER_BY_JPQL);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoTaskInstanceTokenId);

				List<KaleoTaskInstanceAssignment> list = q.list();

				result = list;

				KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KALEOTASKINSTANCETOKENID,
						finderArgs, list);
				}
				else {
					kaleoTaskInstanceAssignment = list.get(0);

					cacheResult(kaleoTaskInstanceAssignment);

					if ((kaleoTaskInstanceAssignment.getKaleoTaskInstanceTokenId() != kaleoTaskInstanceTokenId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KALEOTASKINSTANCETOKENID,
							finderArgs, kaleoTaskInstanceAssignment);
					}
				}

				return kaleoTaskInstanceAssignment;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KALEOTASKINSTANCETOKENID,
						finderArgs, new ArrayList<KaleoTaskInstanceAssignment>());
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (KaleoTaskInstanceAssignment)result;
			}
		}
	}

	public KaleoTaskInstanceAssignment findByKTITI_ACPK_ACN(
		long kaleoTaskInstanceTokenId, String assigneeClassName,
		long assigneeClassPK)
		throws NoSuchTaskInstanceAssignmentException, SystemException {
		KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment = fetchByKTITI_ACPK_ACN(kaleoTaskInstanceTokenId,
				assigneeClassName, assigneeClassPK);

		if (kaleoTaskInstanceAssignment == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoTaskInstanceTokenId=");
			msg.append(kaleoTaskInstanceTokenId);

			msg.append(", assigneeClassName=");
			msg.append(assigneeClassName);

			msg.append(", assigneeClassPK=");
			msg.append(assigneeClassPK);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchTaskInstanceAssignmentException(msg.toString());
		}

		return kaleoTaskInstanceAssignment;
	}

	public KaleoTaskInstanceAssignment fetchByKTITI_ACPK_ACN(
		long kaleoTaskInstanceTokenId, String assigneeClassName,
		long assigneeClassPK) throws SystemException {
		return fetchByKTITI_ACPK_ACN(kaleoTaskInstanceTokenId,
			assigneeClassName, assigneeClassPK, true);
	}

	public KaleoTaskInstanceAssignment fetchByKTITI_ACPK_ACN(
		long kaleoTaskInstanceTokenId, String assigneeClassName,
		long assigneeClassPK, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(kaleoTaskInstanceTokenId),
				
				assigneeClassName, new Long(assigneeClassPK)
			};

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_KTITI_ACPK_ACN,
					finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(5);

				query.append(_SQL_SELECT_KALEOTASKINSTANCEASSIGNMENT_WHERE);

				query.append(_FINDER_COLUMN_KTITI_ACPK_ACN_KALEOTASKINSTANCETOKENID_2);

				if (assigneeClassName == null) {
					query.append(_FINDER_COLUMN_KTITI_ACPK_ACN_ASSIGNEECLASSNAME_1);
				}
				else {
					if (assigneeClassName.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_KTITI_ACPK_ACN_ASSIGNEECLASSNAME_3);
					}
					else {
						query.append(_FINDER_COLUMN_KTITI_ACPK_ACN_ASSIGNEECLASSNAME_2);
					}
				}

				query.append(_FINDER_COLUMN_KTITI_ACPK_ACN_ASSIGNEECLASSPK_2);

				query.append(KaleoTaskInstanceAssignmentModelImpl.ORDER_BY_JPQL);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoTaskInstanceTokenId);

				if (assigneeClassName != null) {
					qPos.add(assigneeClassName);
				}

				qPos.add(assigneeClassPK);

				List<KaleoTaskInstanceAssignment> list = q.list();

				result = list;

				KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KTITI_ACPK_ACN,
						finderArgs, list);
				}
				else {
					kaleoTaskInstanceAssignment = list.get(0);

					cacheResult(kaleoTaskInstanceAssignment);

					if ((kaleoTaskInstanceAssignment.getKaleoTaskInstanceTokenId() != kaleoTaskInstanceTokenId) ||
							(kaleoTaskInstanceAssignment.getAssigneeClassName() == null) ||
							!kaleoTaskInstanceAssignment.getAssigneeClassName()
															.equals(assigneeClassName) ||
							(kaleoTaskInstanceAssignment.getAssigneeClassPK() != assigneeClassPK)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KTITI_ACPK_ACN,
							finderArgs, kaleoTaskInstanceAssignment);
					}
				}

				return kaleoTaskInstanceAssignment;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KTITI_ACPK_ACN,
						finderArgs, new ArrayList<KaleoTaskInstanceAssignment>());
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (KaleoTaskInstanceAssignment)result;
			}
		}
	}

	public List<KaleoTaskInstanceAssignment> findAll()
		throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<KaleoTaskInstanceAssignment> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<KaleoTaskInstanceAssignment> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoTaskInstanceAssignment> list = (List<KaleoTaskInstanceAssignment>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_KALEOTASKINSTANCEASSIGNMENT);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}

				else {
					sql = _SQL_SELECT_KALEOTASKINSTANCEASSIGNMENT.concat(KaleoTaskInstanceAssignmentModelImpl.ORDER_BY_JPQL);
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<KaleoTaskInstanceAssignment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<KaleoTaskInstanceAssignment>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<KaleoTaskInstanceAssignment>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByKaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId)
		throws NoSuchTaskInstanceAssignmentException, SystemException {
		KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment = findByKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);

		remove(kaleoTaskInstanceAssignment);
	}

	public void removeByKTITI_ACPK_ACN(long kaleoTaskInstanceTokenId,
		String assigneeClassName, long assigneeClassPK)
		throws NoSuchTaskInstanceAssignmentException, SystemException {
		KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment = findByKTITI_ACPK_ACN(kaleoTaskInstanceTokenId,
				assigneeClassName, assigneeClassPK);

		remove(kaleoTaskInstanceAssignment);
	}

	public void removeAll() throws SystemException {
		for (KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment : findAll()) {
			remove(kaleoTaskInstanceAssignment);
		}
	}

	public int countByKaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(kaleoTaskInstanceTokenId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KALEOTASKINSTANCETOKENID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_KALEOTASKINSTANCEASSIGNMENT_WHERE);

				query.append(_FINDER_COLUMN_KALEOTASKINSTANCETOKENID_KALEOTASKINSTANCETOKENID_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoTaskInstanceTokenId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KALEOTASKINSTANCETOKENID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByKTITI_ACPK_ACN(long kaleoTaskInstanceTokenId,
		String assigneeClassName, long assigneeClassPK)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(kaleoTaskInstanceTokenId),
				
				assigneeClassName, new Long(assigneeClassPK)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KTITI_ACPK_ACN,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(4);

				query.append(_SQL_COUNT_KALEOTASKINSTANCEASSIGNMENT_WHERE);

				query.append(_FINDER_COLUMN_KTITI_ACPK_ACN_KALEOTASKINSTANCETOKENID_2);

				if (assigneeClassName == null) {
					query.append(_FINDER_COLUMN_KTITI_ACPK_ACN_ASSIGNEECLASSNAME_1);
				}
				else {
					if (assigneeClassName.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_KTITI_ACPK_ACN_ASSIGNEECLASSNAME_3);
					}
					else {
						query.append(_FINDER_COLUMN_KTITI_ACPK_ACN_ASSIGNEECLASSNAME_2);
					}
				}

				query.append(_FINDER_COLUMN_KTITI_ACPK_ACN_ASSIGNEECLASSPK_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoTaskInstanceTokenId);

				if (assigneeClassName != null) {
					qPos.add(assigneeClassName);
				}

				qPos.add(assigneeClassPK);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KTITI_ACPK_ACN,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_KALEOTASKINSTANCEASSIGNMENT);

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

	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<KaleoTaskInstanceAssignment>> listenersList = new ArrayList<ModelListener<KaleoTaskInstanceAssignment>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<KaleoTaskInstanceAssignment>)Class.forName(
							listenerClassName).newInstance());
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(type = KaleoActionPersistence.class)
	protected KaleoActionPersistence kaleoActionPersistence;
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
	@BeanReference(type = KaleoTaskPersistence.class)
	protected KaleoTaskPersistence kaleoTaskPersistence;
	@BeanReference(type = KaleoTaskAssignmentPersistence.class)
	protected KaleoTaskAssignmentPersistence kaleoTaskAssignmentPersistence;
	@BeanReference(type = KaleoTaskInstanceAssignmentPersistence.class)
	protected KaleoTaskInstanceAssignmentPersistence kaleoTaskInstanceAssignmentPersistence;
	@BeanReference(type = KaleoTaskInstanceTokenPersistence.class)
	protected KaleoTaskInstanceTokenPersistence kaleoTaskInstanceTokenPersistence;
	@BeanReference(type = KaleoTransitionPersistence.class)
	protected KaleoTransitionPersistence kaleoTransitionPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_KALEOTASKINSTANCEASSIGNMENT = "SELECT kaleoTaskInstanceAssignment FROM KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment";
	private static final String _SQL_SELECT_KALEOTASKINSTANCEASSIGNMENT_WHERE = "SELECT kaleoTaskInstanceAssignment FROM KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment WHERE ";
	private static final String _SQL_COUNT_KALEOTASKINSTANCEASSIGNMENT = "SELECT COUNT(kaleoTaskInstanceAssignment) FROM KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment";
	private static final String _SQL_COUNT_KALEOTASKINSTANCEASSIGNMENT_WHERE = "SELECT COUNT(kaleoTaskInstanceAssignment) FROM KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment WHERE ";
	private static final String _FINDER_COLUMN_KALEOTASKINSTANCETOKENID_KALEOTASKINSTANCETOKENID_2 =
		"kaleoTaskInstanceAssignment.kaleoTaskInstanceTokenId = ?";
	private static final String _FINDER_COLUMN_KTITI_ACPK_ACN_KALEOTASKINSTANCETOKENID_2 =
		"kaleoTaskInstanceAssignment.kaleoTaskInstanceTokenId = ? AND ";
	private static final String _FINDER_COLUMN_KTITI_ACPK_ACN_ASSIGNEECLASSNAME_1 =
		"kaleoTaskInstanceAssignment.assigneeClassName IS NULL AND ";
	private static final String _FINDER_COLUMN_KTITI_ACPK_ACN_ASSIGNEECLASSNAME_2 =
		"kaleoTaskInstanceAssignment.assigneeClassName = ? AND ";
	private static final String _FINDER_COLUMN_KTITI_ACPK_ACN_ASSIGNEECLASSNAME_3 =
		"(kaleoTaskInstanceAssignment.assigneeClassName IS NULL OR kaleoTaskInstanceAssignment.assigneeClassName = ?) AND ";
	private static final String _FINDER_COLUMN_KTITI_ACPK_ACN_ASSIGNEECLASSPK_2 = "kaleoTaskInstanceAssignment.assigneeClassPK = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoTaskInstanceAssignment.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoTaskInstanceAssignment exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoTaskInstanceAssignment exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(KaleoTaskInstanceAssignmentPersistenceImpl.class);
}