/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.bi.report.service.persistence;

import com.liferay.bi.report.NoSuchDefinitionException;
import com.liferay.bi.report.model.ReportDefinition;
import com.liferay.bi.report.model.impl.ReportDefinitionImpl;
import com.liferay.bi.report.model.impl.ReportDefinitionModelImpl;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="ReportDefinitionPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ReportDefinitionPersistenceImpl extends BasePersistenceImpl
	implements ReportDefinitionPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = ReportDefinitionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_UUID = new FinderPath(ReportDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			ReportDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_UUID = new FinderPath(ReportDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			ReportDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ReportDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			ReportDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ReportDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			ReportDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ReportDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			ReportDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_COMPANYID = new FinderPath(ReportDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			ReportDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_COMPANYID = new FinderPath(ReportDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			ReportDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(ReportDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			ReportDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_COMPANYGROUPID = new FinderPath(ReportDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			ReportDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByCompanyGroupId",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_COMPANYGROUPID = new FinderPath(ReportDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			ReportDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByCompanyGroupId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYGROUPID = new FinderPath(ReportDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			ReportDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByCompanyGroupId",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_GROUPID = new FinderPath(ReportDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			ReportDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByGroupId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_GROUPID = new FinderPath(ReportDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			ReportDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(ReportDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			ReportDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByGroupId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_USERID = new FinderPath(ReportDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			ReportDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_USERID = new FinderPath(ReportDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			ReportDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(ReportDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			ReportDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_NAME = new FinderPath(ReportDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			ReportDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByName",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_NAME = new FinderPath(ReportDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			ReportDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByName",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(ReportDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			ReportDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByName",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(ReportDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			ReportDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ReportDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			ReportDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	public void cacheResult(ReportDefinition reportDefinition) {
		EntityCacheUtil.putResult(ReportDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			ReportDefinitionImpl.class, reportDefinition.getPrimaryKey(),
			reportDefinition);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				reportDefinition.getUuid(),
				new Long(reportDefinition.getGroupId())
			}, reportDefinition);
	}

	public void cacheResult(List<ReportDefinition> reportDefinitions) {
		for (ReportDefinition reportDefinition : reportDefinitions) {
			if (EntityCacheUtil.getResult(
						ReportDefinitionModelImpl.ENTITY_CACHE_ENABLED,
						ReportDefinitionImpl.class,
						reportDefinition.getPrimaryKey(), this) == null) {
				cacheResult(reportDefinition);
			}
		}
	}

	public ReportDefinition create(long definitionId) {
		ReportDefinition reportDefinition = new ReportDefinitionImpl();

		reportDefinition.setNew(true);
		reportDefinition.setPrimaryKey(definitionId);

		String uuid = PortalUUIDUtil.generate();

		reportDefinition.setUuid(uuid);

		return reportDefinition;
	}

	public ReportDefinition remove(long definitionId)
		throws NoSuchDefinitionException, SystemException {
		Session session = null;

		try {
			session = openSession();

			ReportDefinition reportDefinition = (ReportDefinition)session.get(ReportDefinitionImpl.class,
					new Long(definitionId));

			if (reportDefinition == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"No ReportDefinition exists with the primary key " +
						definitionId);
				}

				throw new NoSuchDefinitionException(
					"No ReportDefinition exists with the primary key " +
					definitionId);
			}

			return remove(reportDefinition);
		}
		catch (NoSuchDefinitionException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public ReportDefinition remove(ReportDefinition reportDefinition)
		throws SystemException {
		for (ModelListener<ReportDefinition> listener : listeners) {
			listener.onBeforeRemove(reportDefinition);
		}

		reportDefinition = removeImpl(reportDefinition);

		for (ModelListener<ReportDefinition> listener : listeners) {
			listener.onAfterRemove(reportDefinition);
		}

		return reportDefinition;
	}

	protected ReportDefinition removeImpl(ReportDefinition reportDefinition)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (reportDefinition.isCachedModel() ||
					BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(ReportDefinitionImpl.class,
						reportDefinition.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(reportDefinition);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		ReportDefinitionModelImpl reportDefinitionModelImpl = (ReportDefinitionModelImpl)reportDefinition;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				reportDefinitionModelImpl.getOriginalUuid(),
				new Long(reportDefinitionModelImpl.getOriginalGroupId())
			});

		EntityCacheUtil.removeResult(ReportDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			ReportDefinitionImpl.class, reportDefinition.getPrimaryKey());

		return reportDefinition;
	}

	public ReportDefinition update(ReportDefinition reportDefinition)
		throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(ReportDefinition reportDefinition) method. Use update(ReportDefinition reportDefinition, boolean merge) instead.");
		}

		return update(reportDefinition, false);
	}

	public ReportDefinition update(ReportDefinition reportDefinition,
		boolean merge) throws SystemException {
		boolean isNew = reportDefinition.isNew();

		for (ModelListener<ReportDefinition> listener : listeners) {
			if (isNew) {
				listener.onBeforeCreate(reportDefinition);
			}
			else {
				listener.onBeforeUpdate(reportDefinition);
			}
		}

		reportDefinition = updateImpl(reportDefinition, merge);

		for (ModelListener<ReportDefinition> listener : listeners) {
			if (isNew) {
				listener.onAfterCreate(reportDefinition);
			}
			else {
				listener.onAfterUpdate(reportDefinition);
			}
		}

		return reportDefinition;
	}

	public ReportDefinition updateImpl(
		com.liferay.bi.report.model.ReportDefinition reportDefinition,
		boolean merge) throws SystemException {
		boolean isNew = reportDefinition.isNew();

		if (Validator.isNull(reportDefinition.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			reportDefinition.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, reportDefinition, merge);

			reportDefinition.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(ReportDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			ReportDefinitionImpl.class, reportDefinition.getPrimaryKey(),
			reportDefinition);

		ReportDefinitionModelImpl reportDefinitionModelImpl = (ReportDefinitionModelImpl)reportDefinition;

		if (!isNew &&
				(!reportDefinition.getUuid()
									  .equals(reportDefinitionModelImpl.getOriginalUuid()) ||
				(reportDefinition.getGroupId() != reportDefinitionModelImpl.getOriginalGroupId()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					reportDefinitionModelImpl.getOriginalUuid(),
					new Long(reportDefinitionModelImpl.getOriginalGroupId())
				});
		}

		if (isNew ||
				(!reportDefinition.getUuid()
									  .equals(reportDefinitionModelImpl.getOriginalUuid()) ||
				(reportDefinition.getGroupId() != reportDefinitionModelImpl.getOriginalGroupId()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					reportDefinition.getUuid(),
					new Long(reportDefinition.getGroupId())
				}, reportDefinition);
		}

		return reportDefinition;
	}

	public ReportDefinition findByPrimaryKey(long definitionId)
		throws NoSuchDefinitionException, SystemException {
		ReportDefinition reportDefinition = fetchByPrimaryKey(definitionId);

		if (reportDefinition == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No ReportDefinition exists with the primary key " +
					definitionId);
			}

			throw new NoSuchDefinitionException(
				"No ReportDefinition exists with the primary key " +
				definitionId);
		}

		return reportDefinition;
	}

	public ReportDefinition fetchByPrimaryKey(long definitionId)
		throws SystemException {
		ReportDefinition reportDefinition = (ReportDefinition)EntityCacheUtil.getResult(ReportDefinitionModelImpl.ENTITY_CACHE_ENABLED,
				ReportDefinitionImpl.class, definitionId, this);

		if (reportDefinition == null) {
			Session session = null;

			try {
				session = openSession();

				reportDefinition = (ReportDefinition)session.get(ReportDefinitionImpl.class,
						new Long(definitionId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (reportDefinition != null) {
					cacheResult(reportDefinition);
				}

				closeSession(session);
			}
		}

		return reportDefinition;
	}

	public List<ReportDefinition> findByUuid(String uuid)
		throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		List<ReportDefinition> list = (List<ReportDefinition>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_UUID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.ReportDefinition WHERE ");

				if (uuid == null) {
					query.append("uuid_ IS NULL");
				}
				else {
					query.append("uuid_ = ?");
				}

				query.append(" ");

				query.append("ORDER BY ");

				query.append("modifiedDate ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<ReportDefinition>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_UUID, finderArgs,
					list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<ReportDefinition> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	public List<ReportDefinition> findByUuid(String uuid, int start, int end,
		OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				uuid,
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<ReportDefinition> list = (List<ReportDefinition>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_UUID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.ReportDefinition WHERE ");

				if (uuid == null) {
					query.append("uuid_ IS NULL");
				}
				else {
					query.append("uuid_ = ?");
				}

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("modifiedDate ASC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				list = (List<ReportDefinition>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<ReportDefinition>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_UUID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public ReportDefinition findByUuid_First(String uuid, OrderByComparator obc)
		throws NoSuchDefinitionException, SystemException {
		List<ReportDefinition> list = findByUuid(uuid, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No ReportDefinition exists with the key {");

			msg.append("uuid=" + uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchDefinitionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public ReportDefinition findByUuid_Last(String uuid, OrderByComparator obc)
		throws NoSuchDefinitionException, SystemException {
		int count = countByUuid(uuid);

		List<ReportDefinition> list = findByUuid(uuid, count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No ReportDefinition exists with the key {");

			msg.append("uuid=" + uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchDefinitionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public ReportDefinition[] findByUuid_PrevAndNext(long definitionId,
		String uuid, OrderByComparator obc)
		throws NoSuchDefinitionException, SystemException {
		ReportDefinition reportDefinition = findByPrimaryKey(definitionId);

		int count = countByUuid(uuid);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"FROM com.liferay.bi.report.model.ReportDefinition WHERE ");

			if (uuid == null) {
				query.append("uuid_ IS NULL");
			}
			else {
				query.append("uuid_ = ?");
			}

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("modifiedDate ASC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			if (uuid != null) {
				qPos.add(uuid);
			}

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					reportDefinition);

			ReportDefinition[] array = new ReportDefinitionImpl[3];

			array[0] = (ReportDefinition)objArray[0];
			array[1] = (ReportDefinition)objArray[1];
			array[2] = (ReportDefinition)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public ReportDefinition findByUUID_G(String uuid, long groupId)
		throws NoSuchDefinitionException, SystemException {
		ReportDefinition reportDefinition = fetchByUUID_G(uuid, groupId);

		if (reportDefinition == null) {
			StringBuilder msg = new StringBuilder();

			msg.append("No ReportDefinition exists with the key {");

			msg.append("uuid=" + uuid);

			msg.append(", ");
			msg.append("groupId=" + groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchDefinitionException(msg.toString());
		}

		return reportDefinition;
	}

	public ReportDefinition fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	public ReportDefinition fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, new Long(groupId) };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.ReportDefinition WHERE ");

				if (uuid == null) {
					query.append("uuid_ IS NULL");
				}
				else {
					query.append("uuid_ = ?");
				}

				query.append(" AND ");

				query.append("groupId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("modifiedDate ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<ReportDefinition> list = q.list();

				result = list;

				ReportDefinition reportDefinition = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					reportDefinition = list.get(0);

					cacheResult(reportDefinition);
				}

				return reportDefinition;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, new ArrayList<ReportDefinition>());
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List) {
				return null;
			}
			else {
				return (ReportDefinition)result;
			}
		}
	}

	public List<ReportDefinition> findByCompanyId(long companyId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(companyId) };

		List<ReportDefinition> list = (List<ReportDefinition>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_COMPANYID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.ReportDefinition WHERE ");

				query.append("companyId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("modifiedDate ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<ReportDefinition>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_COMPANYID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<ReportDefinition> findByCompanyId(long companyId, int start,
		int end) throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	public List<ReportDefinition> findByCompanyId(long companyId, int start,
		int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(companyId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<ReportDefinition> list = (List<ReportDefinition>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_COMPANYID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.ReportDefinition WHERE ");

				query.append("companyId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("modifiedDate ASC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<ReportDefinition>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<ReportDefinition>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_COMPANYID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public ReportDefinition findByCompanyId_First(long companyId,
		OrderByComparator obc)
		throws NoSuchDefinitionException, SystemException {
		List<ReportDefinition> list = findByCompanyId(companyId, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No ReportDefinition exists with the key {");

			msg.append("companyId=" + companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchDefinitionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public ReportDefinition findByCompanyId_Last(long companyId,
		OrderByComparator obc)
		throws NoSuchDefinitionException, SystemException {
		int count = countByCompanyId(companyId);

		List<ReportDefinition> list = findByCompanyId(companyId, count - 1,
				count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No ReportDefinition exists with the key {");

			msg.append("companyId=" + companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchDefinitionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public ReportDefinition[] findByCompanyId_PrevAndNext(long definitionId,
		long companyId, OrderByComparator obc)
		throws NoSuchDefinitionException, SystemException {
		ReportDefinition reportDefinition = findByPrimaryKey(definitionId);

		int count = countByCompanyId(companyId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"FROM com.liferay.bi.report.model.ReportDefinition WHERE ");

			query.append("companyId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("modifiedDate ASC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					reportDefinition);

			ReportDefinition[] array = new ReportDefinitionImpl[3];

			array[0] = (ReportDefinition)objArray[0];
			array[1] = (ReportDefinition)objArray[1];
			array[2] = (ReportDefinition)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<ReportDefinition> findByCompanyGroupId(long companyId,
		long groupId) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(companyId), new Long(groupId)
			};

		List<ReportDefinition> list = (List<ReportDefinition>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_COMPANYGROUPID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.ReportDefinition WHERE ");

				query.append("companyId = ?");

				query.append(" AND ");

				query.append("groupId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("modifiedDate ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(groupId);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<ReportDefinition>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_COMPANYGROUPID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<ReportDefinition> findByCompanyGroupId(long companyId,
		long groupId, int start, int end) throws SystemException {
		return findByCompanyGroupId(companyId, groupId, start, end, null);
	}

	public List<ReportDefinition> findByCompanyGroupId(long companyId,
		long groupId, int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(companyId), new Long(groupId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<ReportDefinition> list = (List<ReportDefinition>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_COMPANYGROUPID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.ReportDefinition WHERE ");

				query.append("companyId = ?");

				query.append(" AND ");

				query.append("groupId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("modifiedDate ASC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(groupId);

				list = (List<ReportDefinition>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<ReportDefinition>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_COMPANYGROUPID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public ReportDefinition findByCompanyGroupId_First(long companyId,
		long groupId, OrderByComparator obc)
		throws NoSuchDefinitionException, SystemException {
		List<ReportDefinition> list = findByCompanyGroupId(companyId, groupId,
				0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No ReportDefinition exists with the key {");

			msg.append("companyId=" + companyId);

			msg.append(", ");
			msg.append("groupId=" + groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchDefinitionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public ReportDefinition findByCompanyGroupId_Last(long companyId,
		long groupId, OrderByComparator obc)
		throws NoSuchDefinitionException, SystemException {
		int count = countByCompanyGroupId(companyId, groupId);

		List<ReportDefinition> list = findByCompanyGroupId(companyId, groupId,
				count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No ReportDefinition exists with the key {");

			msg.append("companyId=" + companyId);

			msg.append(", ");
			msg.append("groupId=" + groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchDefinitionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public ReportDefinition[] findByCompanyGroupId_PrevAndNext(
		long definitionId, long companyId, long groupId, OrderByComparator obc)
		throws NoSuchDefinitionException, SystemException {
		ReportDefinition reportDefinition = findByPrimaryKey(definitionId);

		int count = countByCompanyGroupId(companyId, groupId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"FROM com.liferay.bi.report.model.ReportDefinition WHERE ");

			query.append("companyId = ?");

			query.append(" AND ");

			query.append("groupId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("modifiedDate ASC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			qPos.add(groupId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					reportDefinition);

			ReportDefinition[] array = new ReportDefinitionImpl[3];

			array[0] = (ReportDefinition)objArray[0];
			array[1] = (ReportDefinition)objArray[1];
			array[2] = (ReportDefinition)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<ReportDefinition> findByGroupId(long groupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(groupId) };

		List<ReportDefinition> list = (List<ReportDefinition>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_GROUPID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.ReportDefinition WHERE ");

				query.append("groupId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("modifiedDate ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<ReportDefinition>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_GROUPID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<ReportDefinition> findByGroupId(long groupId, int start, int end)
		throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

	public List<ReportDefinition> findByGroupId(long groupId, int start,
		int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(groupId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<ReportDefinition> list = (List<ReportDefinition>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_GROUPID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.ReportDefinition WHERE ");

				query.append("groupId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("modifiedDate ASC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				list = (List<ReportDefinition>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<ReportDefinition>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_GROUPID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public ReportDefinition findByGroupId_First(long groupId,
		OrderByComparator obc)
		throws NoSuchDefinitionException, SystemException {
		List<ReportDefinition> list = findByGroupId(groupId, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No ReportDefinition exists with the key {");

			msg.append("groupId=" + groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchDefinitionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public ReportDefinition findByGroupId_Last(long groupId,
		OrderByComparator obc)
		throws NoSuchDefinitionException, SystemException {
		int count = countByGroupId(groupId);

		List<ReportDefinition> list = findByGroupId(groupId, count - 1, count,
				obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No ReportDefinition exists with the key {");

			msg.append("groupId=" + groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchDefinitionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public ReportDefinition[] findByGroupId_PrevAndNext(long definitionId,
		long groupId, OrderByComparator obc)
		throws NoSuchDefinitionException, SystemException {
		ReportDefinition reportDefinition = findByPrimaryKey(definitionId);

		int count = countByGroupId(groupId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"FROM com.liferay.bi.report.model.ReportDefinition WHERE ");

			query.append("groupId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("modifiedDate ASC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					reportDefinition);

			ReportDefinition[] array = new ReportDefinitionImpl[3];

			array[0] = (ReportDefinition)objArray[0];
			array[1] = (ReportDefinition)objArray[1];
			array[2] = (ReportDefinition)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<ReportDefinition> findByUserId(long userId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(userId) };

		List<ReportDefinition> list = (List<ReportDefinition>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_USERID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.ReportDefinition WHERE ");

				query.append("userId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("modifiedDate ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<ReportDefinition>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_USERID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<ReportDefinition> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	public List<ReportDefinition> findByUserId(long userId, int start, int end,
		OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(userId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<ReportDefinition> list = (List<ReportDefinition>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_USERID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.ReportDefinition WHERE ");

				query.append("userId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("modifiedDate ASC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				list = (List<ReportDefinition>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<ReportDefinition>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_USERID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public ReportDefinition findByUserId_First(long userId,
		OrderByComparator obc)
		throws NoSuchDefinitionException, SystemException {
		List<ReportDefinition> list = findByUserId(userId, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No ReportDefinition exists with the key {");

			msg.append("userId=" + userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchDefinitionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public ReportDefinition findByUserId_Last(long userId, OrderByComparator obc)
		throws NoSuchDefinitionException, SystemException {
		int count = countByUserId(userId);

		List<ReportDefinition> list = findByUserId(userId, count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No ReportDefinition exists with the key {");

			msg.append("userId=" + userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchDefinitionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public ReportDefinition[] findByUserId_PrevAndNext(long definitionId,
		long userId, OrderByComparator obc)
		throws NoSuchDefinitionException, SystemException {
		ReportDefinition reportDefinition = findByPrimaryKey(definitionId);

		int count = countByUserId(userId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"FROM com.liferay.bi.report.model.ReportDefinition WHERE ");

			query.append("userId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("modifiedDate ASC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					reportDefinition);

			ReportDefinition[] array = new ReportDefinitionImpl[3];

			array[0] = (ReportDefinition)objArray[0];
			array[1] = (ReportDefinition)objArray[1];
			array[2] = (ReportDefinition)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<ReportDefinition> findByName(String name)
		throws SystemException {
		Object[] finderArgs = new Object[] { name };

		List<ReportDefinition> list = (List<ReportDefinition>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_NAME,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.ReportDefinition WHERE ");

				if (name == null) {
					query.append("name IS NULL");
				}
				else {
					query.append("name = ?");
				}

				query.append(" ");

				query.append("ORDER BY ");

				query.append("modifiedDate ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (name != null) {
					qPos.add(name);
				}

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<ReportDefinition>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_NAME, finderArgs,
					list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<ReportDefinition> findByName(String name, int start, int end)
		throws SystemException {
		return findByName(name, start, end, null);
	}

	public List<ReportDefinition> findByName(String name, int start, int end,
		OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				name,
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<ReportDefinition> list = (List<ReportDefinition>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_NAME,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.ReportDefinition WHERE ");

				if (name == null) {
					query.append("name IS NULL");
				}
				else {
					query.append("name = ?");
				}

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("modifiedDate ASC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (name != null) {
					qPos.add(name);
				}

				list = (List<ReportDefinition>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<ReportDefinition>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_NAME,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public ReportDefinition findByName_First(String name, OrderByComparator obc)
		throws NoSuchDefinitionException, SystemException {
		List<ReportDefinition> list = findByName(name, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No ReportDefinition exists with the key {");

			msg.append("name=" + name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchDefinitionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public ReportDefinition findByName_Last(String name, OrderByComparator obc)
		throws NoSuchDefinitionException, SystemException {
		int count = countByName(name);

		List<ReportDefinition> list = findByName(name, count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No ReportDefinition exists with the key {");

			msg.append("name=" + name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchDefinitionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public ReportDefinition[] findByName_PrevAndNext(long definitionId,
		String name, OrderByComparator obc)
		throws NoSuchDefinitionException, SystemException {
		ReportDefinition reportDefinition = findByPrimaryKey(definitionId);

		int count = countByName(name);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"FROM com.liferay.bi.report.model.ReportDefinition WHERE ");

			if (name == null) {
				query.append("name IS NULL");
			}
			else {
				query.append("name = ?");
			}

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("modifiedDate ASC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			if (name != null) {
				qPos.add(name);
			}

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					reportDefinition);

			ReportDefinition[] array = new ReportDefinitionImpl[3];

			array[0] = (ReportDefinition)objArray[0];
			array[1] = (ReportDefinition)objArray[1];
			array[2] = (ReportDefinition)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Object> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			dynamicQuery.compile(session);

			return dynamicQuery.list();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Object> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			dynamicQuery.setLimit(start, end);

			dynamicQuery.compile(session);

			return dynamicQuery.list();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<ReportDefinition> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<ReportDefinition> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<ReportDefinition> findAll(int start, int end,
		OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<ReportDefinition> list = (List<ReportDefinition>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.ReportDefinition ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("modifiedDate ASC");
				}

				Query q = session.createQuery(query.toString());

				if (obc == null) {
					list = (List<ReportDefinition>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<ReportDefinition>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<ReportDefinition>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByUuid(String uuid) throws SystemException {
		for (ReportDefinition reportDefinition : findByUuid(uuid)) {
			remove(reportDefinition);
		}
	}

	public void removeByUUID_G(String uuid, long groupId)
		throws NoSuchDefinitionException, SystemException {
		ReportDefinition reportDefinition = findByUUID_G(uuid, groupId);

		remove(reportDefinition);
	}

	public void removeByCompanyId(long companyId) throws SystemException {
		for (ReportDefinition reportDefinition : findByCompanyId(companyId)) {
			remove(reportDefinition);
		}
	}

	public void removeByCompanyGroupId(long companyId, long groupId)
		throws SystemException {
		for (ReportDefinition reportDefinition : findByCompanyGroupId(
				companyId, groupId)) {
			remove(reportDefinition);
		}
	}

	public void removeByGroupId(long groupId) throws SystemException {
		for (ReportDefinition reportDefinition : findByGroupId(groupId)) {
			remove(reportDefinition);
		}
	}

	public void removeByUserId(long userId) throws SystemException {
		for (ReportDefinition reportDefinition : findByUserId(userId)) {
			remove(reportDefinition);
		}
	}

	public void removeByName(String name) throws SystemException {
		for (ReportDefinition reportDefinition : findByName(name)) {
			remove(reportDefinition);
		}
	}

	public void removeAll() throws SystemException {
		for (ReportDefinition reportDefinition : findAll()) {
			remove(reportDefinition);
		}
	}

	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append(
					"FROM com.liferay.bi.report.model.ReportDefinition WHERE ");

				if (uuid == null) {
					query.append("uuid_ IS NULL");
				}
				else {
					query.append("uuid_ = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

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

	public int countByUUID_G(String uuid, long groupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { uuid, new Long(groupId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID_G,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append(
					"FROM com.liferay.bi.report.model.ReportDefinition WHERE ");

				if (uuid == null) {
					query.append("uuid_ IS NULL");
				}
				else {
					query.append("uuid_ = ?");
				}

				query.append(" AND ");

				query.append("groupId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

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

	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(companyId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append(
					"FROM com.liferay.bi.report.model.ReportDefinition WHERE ");

				query.append("companyId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

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

	public int countByCompanyGroupId(long companyId, long groupId)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(companyId), new Long(groupId)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYGROUPID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append(
					"FROM com.liferay.bi.report.model.ReportDefinition WHERE ");

				query.append("companyId = ?");

				query.append(" AND ");

				query.append("groupId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COMPANYGROUPID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByGroupId(long groupId) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(groupId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_GROUPID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append(
					"FROM com.liferay.bi.report.model.ReportDefinition WHERE ");

				query.append("groupId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

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

	public int countByUserId(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(userId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append(
					"FROM com.liferay.bi.report.model.ReportDefinition WHERE ");

				query.append("userId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByName(String name) throws SystemException {
		Object[] finderArgs = new Object[] { name };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_NAME,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append(
					"FROM com.liferay.bi.report.model.ReportDefinition WHERE ");

				if (name == null) {
					query.append("name IS NULL");
				}
				else {
					query.append("name = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (name != null) {
					qPos.add(name);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_NAME,
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

				Query q = session.createQuery(
						"SELECT COUNT(*) FROM com.liferay.bi.report.model.ReportDefinition");

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
						"value.object.listener.com.liferay.bi.report.model.ReportDefinition")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<ReportDefinition>> listenersList = new ArrayList<ModelListener<ReportDefinition>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<ReportDefinition>)Class.forName(
							listenerClassName).newInstance());
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(name = "com.liferay.bi.report.service.persistence.ReportDefinitionPersistence.impl")
	protected com.liferay.bi.report.service.persistence.ReportDefinitionPersistence reportDefinitionPersistence;
	private static Log _log = LogFactoryUtil.getLog(ReportDefinitionPersistenceImpl.class);
}