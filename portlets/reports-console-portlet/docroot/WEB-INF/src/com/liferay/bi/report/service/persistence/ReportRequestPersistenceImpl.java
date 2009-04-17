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

import com.liferay.bi.report.NoSuchRequestException;
import com.liferay.bi.report.model.ReportRequest;
import com.liferay.bi.report.model.impl.ReportRequestImpl;
import com.liferay.bi.report.model.impl.ReportRequestModelImpl;

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
 * <a href="ReportRequestPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ReportRequestPersistenceImpl extends BasePersistenceImpl
	implements ReportRequestPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = ReportRequestImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_UUID = new FinderPath(ReportRequestModelImpl.ENTITY_CACHE_ENABLED,
			ReportRequestModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_UUID = new FinderPath(ReportRequestModelImpl.ENTITY_CACHE_ENABLED,
			ReportRequestModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ReportRequestModelImpl.ENTITY_CACHE_ENABLED,
			ReportRequestModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ReportRequestModelImpl.ENTITY_CACHE_ENABLED,
			ReportRequestModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ReportRequestModelImpl.ENTITY_CACHE_ENABLED,
			ReportRequestModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_COMPANYID = new FinderPath(ReportRequestModelImpl.ENTITY_CACHE_ENABLED,
			ReportRequestModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_COMPANYID = new FinderPath(ReportRequestModelImpl.ENTITY_CACHE_ENABLED,
			ReportRequestModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(ReportRequestModelImpl.ENTITY_CACHE_ENABLED,
			ReportRequestModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_COMPANYGROUPID = new FinderPath(ReportRequestModelImpl.ENTITY_CACHE_ENABLED,
			ReportRequestModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByCompanyGroupId",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_COMPANYGROUPID = new FinderPath(ReportRequestModelImpl.ENTITY_CACHE_ENABLED,
			ReportRequestModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByCompanyGroupId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYGROUPID = new FinderPath(ReportRequestModelImpl.ENTITY_CACHE_ENABLED,
			ReportRequestModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByCompanyGroupId",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_GROUPID = new FinderPath(ReportRequestModelImpl.ENTITY_CACHE_ENABLED,
			ReportRequestModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByGroupId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_GROUPID = new FinderPath(ReportRequestModelImpl.ENTITY_CACHE_ENABLED,
			ReportRequestModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(ReportRequestModelImpl.ENTITY_CACHE_ENABLED,
			ReportRequestModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByGroupId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_USERID = new FinderPath(ReportRequestModelImpl.ENTITY_CACHE_ENABLED,
			ReportRequestModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_USERID = new FinderPath(ReportRequestModelImpl.ENTITY_CACHE_ENABLED,
			ReportRequestModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(ReportRequestModelImpl.ENTITY_CACHE_ENABLED,
			ReportRequestModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_REQUESTID = new FinderPath(ReportRequestModelImpl.ENTITY_CACHE_ENABLED,
			ReportRequestModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_ENTITY, "fetchByRequestId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_REQUESTID = new FinderPath(ReportRequestModelImpl.ENTITY_CACHE_ENABLED,
			ReportRequestModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByRequestId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_DEFINITIONID = new FinderPath(ReportRequestModelImpl.ENTITY_CACHE_ENABLED,
			ReportRequestModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_ENTITY, "fetchByDefinitionId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_DEFINITIONID = new FinderPath(ReportRequestModelImpl.ENTITY_CACHE_ENABLED,
			ReportRequestModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByDefinitionId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(ReportRequestModelImpl.ENTITY_CACHE_ENABLED,
			ReportRequestModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ReportRequestModelImpl.ENTITY_CACHE_ENABLED,
			ReportRequestModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	public void cacheResult(ReportRequest reportRequest) {
		EntityCacheUtil.putResult(ReportRequestModelImpl.ENTITY_CACHE_ENABLED,
			ReportRequestImpl.class, reportRequest.getPrimaryKey(),
			reportRequest);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				reportRequest.getUuid(), new Long(reportRequest.getGroupId())
			}, reportRequest);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_REQUESTID,
			new Object[] { new Long(reportRequest.getRequestId()) },
			reportRequest);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEFINITIONID,
			new Object[] { new Long(reportRequest.getDefinitionId()) },
			reportRequest);
	}

	public void cacheResult(List<ReportRequest> reportRequests) {
		for (ReportRequest reportRequest : reportRequests) {
			if (EntityCacheUtil.getResult(
						ReportRequestModelImpl.ENTITY_CACHE_ENABLED,
						ReportRequestImpl.class, reportRequest.getPrimaryKey(),
						this) == null) {
				cacheResult(reportRequest);
			}
		}
	}

	public ReportRequest create(long requestId) {
		ReportRequest reportRequest = new ReportRequestImpl();

		reportRequest.setNew(true);
		reportRequest.setPrimaryKey(requestId);

		String uuid = PortalUUIDUtil.generate();

		reportRequest.setUuid(uuid);

		return reportRequest;
	}

	public ReportRequest remove(long requestId)
		throws NoSuchRequestException, SystemException {
		Session session = null;

		try {
			session = openSession();

			ReportRequest reportRequest = (ReportRequest)session.get(ReportRequestImpl.class,
					new Long(requestId));

			if (reportRequest == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No ReportRequest exists with the primary key " +
						requestId);
				}

				throw new NoSuchRequestException(
					"No ReportRequest exists with the primary key " +
					requestId);
			}

			return remove(reportRequest);
		}
		catch (NoSuchRequestException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public ReportRequest remove(ReportRequest reportRequest)
		throws SystemException {
		for (ModelListener<ReportRequest> listener : listeners) {
			listener.onBeforeRemove(reportRequest);
		}

		reportRequest = removeImpl(reportRequest);

		for (ModelListener<ReportRequest> listener : listeners) {
			listener.onAfterRemove(reportRequest);
		}

		return reportRequest;
	}

	protected ReportRequest removeImpl(ReportRequest reportRequest)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (reportRequest.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(ReportRequestImpl.class,
						reportRequest.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(reportRequest);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		ReportRequestModelImpl reportRequestModelImpl = (ReportRequestModelImpl)reportRequest;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				reportRequestModelImpl.getOriginalUuid(),
				new Long(reportRequestModelImpl.getOriginalGroupId())
			});

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_REQUESTID,
			new Object[] { new Long(reportRequestModelImpl.getOriginalRequestId()) });

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DEFINITIONID,
			new Object[] {
				new Long(reportRequestModelImpl.getOriginalDefinitionId())
			});

		EntityCacheUtil.removeResult(ReportRequestModelImpl.ENTITY_CACHE_ENABLED,
			ReportRequestImpl.class, reportRequest.getPrimaryKey());

		return reportRequest;
	}

	public ReportRequest update(ReportRequest reportRequest)
		throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(ReportRequest reportRequest) method. Use update(ReportRequest reportRequest, boolean merge) instead.");
		}

		return update(reportRequest, false);
	}

	public ReportRequest update(ReportRequest reportRequest, boolean merge)
		throws SystemException {
		boolean isNew = reportRequest.isNew();

		for (ModelListener<ReportRequest> listener : listeners) {
			if (isNew) {
				listener.onBeforeCreate(reportRequest);
			}
			else {
				listener.onBeforeUpdate(reportRequest);
			}
		}

		reportRequest = updateImpl(reportRequest, merge);

		for (ModelListener<ReportRequest> listener : listeners) {
			if (isNew) {
				listener.onAfterCreate(reportRequest);
			}
			else {
				listener.onAfterUpdate(reportRequest);
			}
		}

		return reportRequest;
	}

	public ReportRequest updateImpl(
		com.liferay.bi.report.model.ReportRequest reportRequest, boolean merge)
		throws SystemException {
		boolean isNew = reportRequest.isNew();

		if (Validator.isNull(reportRequest.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			reportRequest.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, reportRequest, merge);

			reportRequest.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(ReportRequestModelImpl.ENTITY_CACHE_ENABLED,
			ReportRequestImpl.class, reportRequest.getPrimaryKey(),
			reportRequest);

		ReportRequestModelImpl reportRequestModelImpl = (ReportRequestModelImpl)reportRequest;

		if (!isNew &&
				(!reportRequest.getUuid()
								   .equals(reportRequestModelImpl.getOriginalUuid()) ||
				(reportRequest.getGroupId() != reportRequestModelImpl.getOriginalGroupId()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					reportRequestModelImpl.getOriginalUuid(),
					new Long(reportRequestModelImpl.getOriginalGroupId())
				});
		}

		if (isNew ||
				(!reportRequest.getUuid()
								   .equals(reportRequestModelImpl.getOriginalUuid()) ||
				(reportRequest.getGroupId() != reportRequestModelImpl.getOriginalGroupId()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					reportRequest.getUuid(),
					new Long(reportRequest.getGroupId())
				}, reportRequest);
		}

		if (!isNew &&
				(reportRequest.getRequestId() != reportRequestModelImpl.getOriginalRequestId())) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_REQUESTID,
				new Object[] {
					new Long(reportRequestModelImpl.getOriginalRequestId())
				});
		}

		if (isNew ||
				(reportRequest.getRequestId() != reportRequestModelImpl.getOriginalRequestId())) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_REQUESTID,
				new Object[] { new Long(reportRequest.getRequestId()) },
				reportRequest);
		}

		if (!isNew &&
				(reportRequest.getDefinitionId() != reportRequestModelImpl.getOriginalDefinitionId())) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DEFINITIONID,
				new Object[] {
					new Long(reportRequestModelImpl.getOriginalDefinitionId())
				});
		}

		if (isNew ||
				(reportRequest.getDefinitionId() != reportRequestModelImpl.getOriginalDefinitionId())) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEFINITIONID,
				new Object[] { new Long(reportRequest.getDefinitionId()) },
				reportRequest);
		}

		return reportRequest;
	}

	public ReportRequest findByPrimaryKey(long requestId)
		throws NoSuchRequestException, SystemException {
		ReportRequest reportRequest = fetchByPrimaryKey(requestId);

		if (reportRequest == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No ReportRequest exists with the primary key " +
					requestId);
			}

			throw new NoSuchRequestException(
				"No ReportRequest exists with the primary key " + requestId);
		}

		return reportRequest;
	}

	public ReportRequest fetchByPrimaryKey(long requestId)
		throws SystemException {
		ReportRequest reportRequest = (ReportRequest)EntityCacheUtil.getResult(ReportRequestModelImpl.ENTITY_CACHE_ENABLED,
				ReportRequestImpl.class, requestId, this);

		if (reportRequest == null) {
			Session session = null;

			try {
				session = openSession();

				reportRequest = (ReportRequest)session.get(ReportRequestImpl.class,
						new Long(requestId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (reportRequest != null) {
					cacheResult(reportRequest);
				}

				closeSession(session);
			}
		}

		return reportRequest;
	}

	public List<ReportRequest> findByUuid(String uuid)
		throws SystemException {
		int count = countByUuid(uuid);

		if (count == 0) {
			return Collections.EMPTY_LIST;
		}

		Object[] finderArgs = new Object[] { uuid };

		List<ReportRequest> list = (List<ReportRequest>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_UUID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.ReportRequest WHERE ");

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
					list = new ArrayList<ReportRequest>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_UUID, finderArgs,
					list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<ReportRequest> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	public List<ReportRequest> findByUuid(String uuid, int start, int end,
		OrderByComparator obc) throws SystemException {
		int count = countByUuid(uuid);

		if (count == 0) {
			return Collections.EMPTY_LIST;
		}

		Object[] finderArgs = new Object[] {
				uuid,
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<ReportRequest> list = (List<ReportRequest>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_UUID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.ReportRequest WHERE ");

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

				list = (List<ReportRequest>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<ReportRequest>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_UUID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public ReportRequest findByUuid_First(String uuid, OrderByComparator obc)
		throws NoSuchRequestException, SystemException {
		List<ReportRequest> list = findByUuid(uuid, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No ReportRequest exists with the key {");

			msg.append("uuid=" + uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchRequestException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public ReportRequest findByUuid_Last(String uuid, OrderByComparator obc)
		throws NoSuchRequestException, SystemException {
		int count = countByUuid(uuid);

		List<ReportRequest> list = findByUuid(uuid, count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No ReportRequest exists with the key {");

			msg.append("uuid=" + uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchRequestException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public ReportRequest[] findByUuid_PrevAndNext(long requestId, String uuid,
		OrderByComparator obc) throws NoSuchRequestException, SystemException {
		ReportRequest reportRequest = findByPrimaryKey(requestId);

		int count = countByUuid(uuid);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"FROM com.liferay.bi.report.model.ReportRequest WHERE ");

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
					reportRequest);

			ReportRequest[] array = new ReportRequestImpl[3];

			array[0] = (ReportRequest)objArray[0];
			array[1] = (ReportRequest)objArray[1];
			array[2] = (ReportRequest)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public ReportRequest findByUUID_G(String uuid, long groupId)
		throws NoSuchRequestException, SystemException {
		ReportRequest reportRequest = fetchByUUID_G(uuid, groupId);

		if (reportRequest == null) {
			StringBuilder msg = new StringBuilder();

			msg.append("No ReportRequest exists with the key {");

			msg.append("uuid=" + uuid);

			msg.append(", ");
			msg.append("groupId=" + groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchRequestException(msg.toString());
		}

		return reportRequest;
	}

	public ReportRequest fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	public ReportRequest fetchByUUID_G(String uuid, long groupId,
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
					"FROM com.liferay.bi.report.model.ReportRequest WHERE ");

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

				List<ReportRequest> list = q.list();

				result = list;

				ReportRequest reportRequest = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					reportRequest = list.get(0);

					cacheResult(reportRequest);
				}

				return reportRequest;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, new ArrayList<ReportRequest>());
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List) {
				return null;
			}
			else {
				return (ReportRequest)result;
			}
		}
	}

	public List<ReportRequest> findByCompanyId(long companyId)
		throws SystemException {
		int count = countByCompanyId(companyId);

		if (count == 0) {
			return Collections.EMPTY_LIST;
		}

		Object[] finderArgs = new Object[] { new Long(companyId) };

		List<ReportRequest> list = (List<ReportRequest>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_COMPANYID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.ReportRequest WHERE ");

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
					list = new ArrayList<ReportRequest>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_COMPANYID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<ReportRequest> findByCompanyId(long companyId, int start,
		int end) throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	public List<ReportRequest> findByCompanyId(long companyId, int start,
		int end, OrderByComparator obc) throws SystemException {
		int count = countByCompanyId(companyId);

		if (count == 0) {
			return Collections.EMPTY_LIST;
		}

		Object[] finderArgs = new Object[] {
				new Long(companyId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<ReportRequest> list = (List<ReportRequest>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_COMPANYID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.ReportRequest WHERE ");

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

				list = (List<ReportRequest>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<ReportRequest>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_COMPANYID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public ReportRequest findByCompanyId_First(long companyId,
		OrderByComparator obc) throws NoSuchRequestException, SystemException {
		List<ReportRequest> list = findByCompanyId(companyId, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No ReportRequest exists with the key {");

			msg.append("companyId=" + companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchRequestException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public ReportRequest findByCompanyId_Last(long companyId,
		OrderByComparator obc) throws NoSuchRequestException, SystemException {
		int count = countByCompanyId(companyId);

		List<ReportRequest> list = findByCompanyId(companyId, count - 1, count,
				obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No ReportRequest exists with the key {");

			msg.append("companyId=" + companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchRequestException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public ReportRequest[] findByCompanyId_PrevAndNext(long requestId,
		long companyId, OrderByComparator obc)
		throws NoSuchRequestException, SystemException {
		ReportRequest reportRequest = findByPrimaryKey(requestId);

		int count = countByCompanyId(companyId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"FROM com.liferay.bi.report.model.ReportRequest WHERE ");

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
					reportRequest);

			ReportRequest[] array = new ReportRequestImpl[3];

			array[0] = (ReportRequest)objArray[0];
			array[1] = (ReportRequest)objArray[1];
			array[2] = (ReportRequest)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<ReportRequest> findByCompanyGroupId(long companyId, long groupId)
		throws SystemException {
		int count = countByCompanyGroupId(companyId, groupId);

		if (count == 0) {
			return Collections.EMPTY_LIST;
		}

		Object[] finderArgs = new Object[] {
				new Long(companyId), new Long(groupId)
			};

		List<ReportRequest> list = (List<ReportRequest>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_COMPANYGROUPID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.ReportRequest WHERE ");

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
					list = new ArrayList<ReportRequest>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_COMPANYGROUPID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<ReportRequest> findByCompanyGroupId(long companyId,
		long groupId, int start, int end) throws SystemException {
		return findByCompanyGroupId(companyId, groupId, start, end, null);
	}

	public List<ReportRequest> findByCompanyGroupId(long companyId,
		long groupId, int start, int end, OrderByComparator obc)
		throws SystemException {
		int count = countByCompanyGroupId(companyId, groupId);

		if (count == 0) {
			return Collections.EMPTY_LIST;
		}

		Object[] finderArgs = new Object[] {
				new Long(companyId), new Long(groupId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<ReportRequest> list = (List<ReportRequest>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_COMPANYGROUPID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.ReportRequest WHERE ");

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

				list = (List<ReportRequest>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<ReportRequest>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_COMPANYGROUPID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public ReportRequest findByCompanyGroupId_First(long companyId,
		long groupId, OrderByComparator obc)
		throws NoSuchRequestException, SystemException {
		List<ReportRequest> list = findByCompanyGroupId(companyId, groupId, 0,
				1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No ReportRequest exists with the key {");

			msg.append("companyId=" + companyId);

			msg.append(", ");
			msg.append("groupId=" + groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchRequestException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public ReportRequest findByCompanyGroupId_Last(long companyId,
		long groupId, OrderByComparator obc)
		throws NoSuchRequestException, SystemException {
		int count = countByCompanyGroupId(companyId, groupId);

		List<ReportRequest> list = findByCompanyGroupId(companyId, groupId,
				count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No ReportRequest exists with the key {");

			msg.append("companyId=" + companyId);

			msg.append(", ");
			msg.append("groupId=" + groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchRequestException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public ReportRequest[] findByCompanyGroupId_PrevAndNext(long requestId,
		long companyId, long groupId, OrderByComparator obc)
		throws NoSuchRequestException, SystemException {
		ReportRequest reportRequest = findByPrimaryKey(requestId);

		int count = countByCompanyGroupId(companyId, groupId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"FROM com.liferay.bi.report.model.ReportRequest WHERE ");

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
					reportRequest);

			ReportRequest[] array = new ReportRequestImpl[3];

			array[0] = (ReportRequest)objArray[0];
			array[1] = (ReportRequest)objArray[1];
			array[2] = (ReportRequest)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<ReportRequest> findByGroupId(long groupId)
		throws SystemException {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return Collections.EMPTY_LIST;
		}

		Object[] finderArgs = new Object[] { new Long(groupId) };

		List<ReportRequest> list = (List<ReportRequest>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_GROUPID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.ReportRequest WHERE ");

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
					list = new ArrayList<ReportRequest>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_GROUPID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<ReportRequest> findByGroupId(long groupId, int start, int end)
		throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

	public List<ReportRequest> findByGroupId(long groupId, int start, int end,
		OrderByComparator obc) throws SystemException {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return Collections.EMPTY_LIST;
		}

		Object[] finderArgs = new Object[] {
				new Long(groupId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<ReportRequest> list = (List<ReportRequest>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_GROUPID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.ReportRequest WHERE ");

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

				list = (List<ReportRequest>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<ReportRequest>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_GROUPID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public ReportRequest findByGroupId_First(long groupId, OrderByComparator obc)
		throws NoSuchRequestException, SystemException {
		List<ReportRequest> list = findByGroupId(groupId, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No ReportRequest exists with the key {");

			msg.append("groupId=" + groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchRequestException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public ReportRequest findByGroupId_Last(long groupId, OrderByComparator obc)
		throws NoSuchRequestException, SystemException {
		int count = countByGroupId(groupId);

		List<ReportRequest> list = findByGroupId(groupId, count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No ReportRequest exists with the key {");

			msg.append("groupId=" + groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchRequestException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public ReportRequest[] findByGroupId_PrevAndNext(long requestId,
		long groupId, OrderByComparator obc)
		throws NoSuchRequestException, SystemException {
		ReportRequest reportRequest = findByPrimaryKey(requestId);

		int count = countByGroupId(groupId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"FROM com.liferay.bi.report.model.ReportRequest WHERE ");

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
					reportRequest);

			ReportRequest[] array = new ReportRequestImpl[3];

			array[0] = (ReportRequest)objArray[0];
			array[1] = (ReportRequest)objArray[1];
			array[2] = (ReportRequest)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<ReportRequest> findByUserId(long userId)
		throws SystemException {
		int count = countByUserId(userId);

		if (count == 0) {
			return Collections.EMPTY_LIST;
		}

		Object[] finderArgs = new Object[] { new Long(userId) };

		List<ReportRequest> list = (List<ReportRequest>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_USERID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.ReportRequest WHERE ");

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
					list = new ArrayList<ReportRequest>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_USERID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<ReportRequest> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	public List<ReportRequest> findByUserId(long userId, int start, int end,
		OrderByComparator obc) throws SystemException {
		int count = countByUserId(userId);

		if (count == 0) {
			return Collections.EMPTY_LIST;
		}

		Object[] finderArgs = new Object[] {
				new Long(userId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<ReportRequest> list = (List<ReportRequest>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_USERID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.ReportRequest WHERE ");

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

				list = (List<ReportRequest>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<ReportRequest>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_USERID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public ReportRequest findByUserId_First(long userId, OrderByComparator obc)
		throws NoSuchRequestException, SystemException {
		List<ReportRequest> list = findByUserId(userId, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No ReportRequest exists with the key {");

			msg.append("userId=" + userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchRequestException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public ReportRequest findByUserId_Last(long userId, OrderByComparator obc)
		throws NoSuchRequestException, SystemException {
		int count = countByUserId(userId);

		List<ReportRequest> list = findByUserId(userId, count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No ReportRequest exists with the key {");

			msg.append("userId=" + userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchRequestException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public ReportRequest[] findByUserId_PrevAndNext(long requestId,
		long userId, OrderByComparator obc)
		throws NoSuchRequestException, SystemException {
		ReportRequest reportRequest = findByPrimaryKey(requestId);

		int count = countByUserId(userId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"FROM com.liferay.bi.report.model.ReportRequest WHERE ");

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
					reportRequest);

			ReportRequest[] array = new ReportRequestImpl[3];

			array[0] = (ReportRequest)objArray[0];
			array[1] = (ReportRequest)objArray[1];
			array[2] = (ReportRequest)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public ReportRequest findByRequestId(long requestId)
		throws NoSuchRequestException, SystemException {
		ReportRequest reportRequest = fetchByRequestId(requestId);

		if (reportRequest == null) {
			StringBuilder msg = new StringBuilder();

			msg.append("No ReportRequest exists with the key {");

			msg.append("requestId=" + requestId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchRequestException(msg.toString());
		}

		return reportRequest;
	}

	public ReportRequest fetchByRequestId(long requestId)
		throws SystemException {
		return fetchByRequestId(requestId, true);
	}

	public ReportRequest fetchByRequestId(long requestId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(requestId) };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_REQUESTID,
					finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.ReportRequest WHERE ");

				query.append("requestId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("modifiedDate ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(requestId);

				List<ReportRequest> list = q.list();

				result = list;

				ReportRequest reportRequest = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_REQUESTID,
						finderArgs, list);
				}
				else {
					reportRequest = list.get(0);

					cacheResult(reportRequest);
				}

				return reportRequest;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_REQUESTID,
						finderArgs, new ArrayList<ReportRequest>());
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List) {
				return null;
			}
			else {
				return (ReportRequest)result;
			}
		}
	}

	public ReportRequest findByDefinitionId(long definitionId)
		throws NoSuchRequestException, SystemException {
		ReportRequest reportRequest = fetchByDefinitionId(definitionId);

		if (reportRequest == null) {
			StringBuilder msg = new StringBuilder();

			msg.append("No ReportRequest exists with the key {");

			msg.append("definitionId=" + definitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchRequestException(msg.toString());
		}

		return reportRequest;
	}

	public ReportRequest fetchByDefinitionId(long definitionId)
		throws SystemException {
		return fetchByDefinitionId(definitionId, true);
	}

	public ReportRequest fetchByDefinitionId(long definitionId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(definitionId) };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_DEFINITIONID,
					finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.ReportRequest WHERE ");

				query.append("definitionId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("modifiedDate ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(definitionId);

				List<ReportRequest> list = q.list();

				result = list;

				ReportRequest reportRequest = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEFINITIONID,
						finderArgs, list);
				}
				else {
					reportRequest = list.get(0);

					cacheResult(reportRequest);
				}

				return reportRequest;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEFINITIONID,
						finderArgs, new ArrayList<ReportRequest>());
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List) {
				return null;
			}
			else {
				return (ReportRequest)result;
			}
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

	public List<ReportRequest> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<ReportRequest> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<ReportRequest> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<ReportRequest> list = (List<ReportRequest>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.bi.report.model.ReportRequest ");

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
					list = (List<ReportRequest>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<ReportRequest>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<ReportRequest>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByUuid(String uuid) throws SystemException {
		for (ReportRequest reportRequest : findByUuid(uuid)) {
			remove(reportRequest);
		}
	}

	public void removeByUUID_G(String uuid, long groupId)
		throws NoSuchRequestException, SystemException {
		ReportRequest reportRequest = findByUUID_G(uuid, groupId);

		remove(reportRequest);
	}

	public void removeByCompanyId(long companyId) throws SystemException {
		for (ReportRequest reportRequest : findByCompanyId(companyId)) {
			remove(reportRequest);
		}
	}

	public void removeByCompanyGroupId(long companyId, long groupId)
		throws SystemException {
		for (ReportRequest reportRequest : findByCompanyGroupId(companyId,
				groupId)) {
			remove(reportRequest);
		}
	}

	public void removeByGroupId(long groupId) throws SystemException {
		for (ReportRequest reportRequest : findByGroupId(groupId)) {
			remove(reportRequest);
		}
	}

	public void removeByUserId(long userId) throws SystemException {
		for (ReportRequest reportRequest : findByUserId(userId)) {
			remove(reportRequest);
		}
	}

	public void removeByRequestId(long requestId)
		throws NoSuchRequestException, SystemException {
		ReportRequest reportRequest = findByRequestId(requestId);

		remove(reportRequest);
	}

	public void removeByDefinitionId(long definitionId)
		throws NoSuchRequestException, SystemException {
		ReportRequest reportRequest = findByDefinitionId(definitionId);

		remove(reportRequest);
	}

	public void removeAll() throws SystemException {
		for (ReportRequest reportRequest : findAll()) {
			remove(reportRequest);
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
					"FROM com.liferay.bi.report.model.ReportRequest WHERE ");

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
					"FROM com.liferay.bi.report.model.ReportRequest WHERE ");

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
					"FROM com.liferay.bi.report.model.ReportRequest WHERE ");

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
					"FROM com.liferay.bi.report.model.ReportRequest WHERE ");

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
					"FROM com.liferay.bi.report.model.ReportRequest WHERE ");

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
					"FROM com.liferay.bi.report.model.ReportRequest WHERE ");

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

	public int countByRequestId(long requestId) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(requestId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_REQUESTID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append(
					"FROM com.liferay.bi.report.model.ReportRequest WHERE ");

				query.append("requestId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(requestId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_REQUESTID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByDefinitionId(long definitionId) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(definitionId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_DEFINITIONID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append(
					"FROM com.liferay.bi.report.model.ReportRequest WHERE ");

				query.append("definitionId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(definitionId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DEFINITIONID,
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
						"SELECT COUNT(*) FROM com.liferay.bi.report.model.ReportRequest");

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
						"value.object.listener.com.liferay.bi.report.model.ReportRequest")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<ReportRequest>> listenersList = new ArrayList<ModelListener<ReportRequest>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<ReportRequest>)Class.forName(
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
	@BeanReference(name = "com.liferay.bi.report.service.persistence.ReportRequestPersistence.impl")
	protected com.liferay.bi.report.service.persistence.ReportRequestPersistence reportRequestPersistence;
	private static Log _log = LogFactoryUtil.getLog(ReportRequestPersistenceImpl.class);
}