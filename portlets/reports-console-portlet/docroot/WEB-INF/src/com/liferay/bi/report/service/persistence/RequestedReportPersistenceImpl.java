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

import com.liferay.bi.report.NoSuchRequestedReportException;
import com.liferay.bi.report.model.RequestedReport;
import com.liferay.bi.report.model.impl.RequestedReportImpl;
import com.liferay.bi.report.model.impl.RequestedReportModelImpl;

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
 * <a href="RequestedReportPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class RequestedReportPersistenceImpl extends BasePersistenceImpl
	implements RequestedReportPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = RequestedReportImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_UUID = new FinderPath(RequestedReportModelImpl.ENTITY_CACHE_ENABLED,
			RequestedReportModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_UUID = new FinderPath(RequestedReportModelImpl.ENTITY_CACHE_ENABLED,
			RequestedReportModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(RequestedReportModelImpl.ENTITY_CACHE_ENABLED,
			RequestedReportModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(RequestedReportModelImpl.ENTITY_CACHE_ENABLED,
			RequestedReportModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(RequestedReportModelImpl.ENTITY_CACHE_ENABLED,
			RequestedReportModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_COMPANYID = new FinderPath(RequestedReportModelImpl.ENTITY_CACHE_ENABLED,
			RequestedReportModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_COMPANYID = new FinderPath(RequestedReportModelImpl.ENTITY_CACHE_ENABLED,
			RequestedReportModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(RequestedReportModelImpl.ENTITY_CACHE_ENABLED,
			RequestedReportModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_COMPANYGROUPID = new FinderPath(RequestedReportModelImpl.ENTITY_CACHE_ENABLED,
			RequestedReportModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByCompanyGroupId",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_COMPANYGROUPID = new FinderPath(RequestedReportModelImpl.ENTITY_CACHE_ENABLED,
			RequestedReportModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByCompanyGroupId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYGROUPID = new FinderPath(RequestedReportModelImpl.ENTITY_CACHE_ENABLED,
			RequestedReportModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByCompanyGroupId",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_GROUPID = new FinderPath(RequestedReportModelImpl.ENTITY_CACHE_ENABLED,
			RequestedReportModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByGroupId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_GROUPID = new FinderPath(RequestedReportModelImpl.ENTITY_CACHE_ENABLED,
			RequestedReportModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(RequestedReportModelImpl.ENTITY_CACHE_ENABLED,
			RequestedReportModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByGroupId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_USERID = new FinderPath(RequestedReportModelImpl.ENTITY_CACHE_ENABLED,
			RequestedReportModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_USERID = new FinderPath(RequestedReportModelImpl.ENTITY_CACHE_ENABLED,
			RequestedReportModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(RequestedReportModelImpl.ENTITY_CACHE_ENABLED,
			RequestedReportModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_REQUESTID = new FinderPath(RequestedReportModelImpl.ENTITY_CACHE_ENABLED,
			RequestedReportModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_ENTITY, "fetchByRequestId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_REQUESTID = new FinderPath(RequestedReportModelImpl.ENTITY_CACHE_ENABLED,
			RequestedReportModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByRequestId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_DEFINITIONID = new FinderPath(RequestedReportModelImpl.ENTITY_CACHE_ENABLED,
			RequestedReportModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByDefinitionId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_DEFINITIONID = new FinderPath(RequestedReportModelImpl.ENTITY_CACHE_ENABLED,
			RequestedReportModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByDefinitionId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_DEFINITIONID = new FinderPath(RequestedReportModelImpl.ENTITY_CACHE_ENABLED,
			RequestedReportModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByDefinitionId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(RequestedReportModelImpl.ENTITY_CACHE_ENABLED,
			RequestedReportModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(RequestedReportModelImpl.ENTITY_CACHE_ENABLED,
			RequestedReportModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	public void cacheResult(RequestedReport requestedReport) {
		EntityCacheUtil.putResult(RequestedReportModelImpl.ENTITY_CACHE_ENABLED,
			RequestedReportImpl.class, requestedReport.getPrimaryKey(),
			requestedReport);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				requestedReport.getUuid(),
				new Long(requestedReport.getGroupId())
			}, requestedReport);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_REQUESTID,
			new Object[] { new Long(requestedReport.getRequestId()) },
			requestedReport);
	}

	public void cacheResult(List<RequestedReport> requestedReports) {
		for (RequestedReport requestedReport : requestedReports) {
			if (EntityCacheUtil.getResult(
						RequestedReportModelImpl.ENTITY_CACHE_ENABLED,
						RequestedReportImpl.class,
						requestedReport.getPrimaryKey(), this) == null) {
				cacheResult(requestedReport);
			}
		}
	}

	public RequestedReport create(long requestId) {
		RequestedReport requestedReport = new RequestedReportImpl();

		requestedReport.setNew(true);
		requestedReport.setPrimaryKey(requestId);

		String uuid = PortalUUIDUtil.generate();

		requestedReport.setUuid(uuid);

		return requestedReport;
	}

	public RequestedReport remove(long requestId)
		throws NoSuchRequestedReportException, SystemException {
		Session session = null;

		try {
			session = openSession();

			RequestedReport requestedReport = (RequestedReport)session.get(RequestedReportImpl.class,
					new Long(requestId));

			if (requestedReport == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No RequestedReport exists with the primary key " +
						requestId);
				}

				throw new NoSuchRequestedReportException(
					"No RequestedReport exists with the primary key " +
					requestId);
			}

			return remove(requestedReport);
		}
		catch (NoSuchRequestedReportException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public RequestedReport remove(RequestedReport requestedReport)
		throws SystemException {
		for (ModelListener<RequestedReport> listener : listeners) {
			listener.onBeforeRemove(requestedReport);
		}

		requestedReport = removeImpl(requestedReport);

		for (ModelListener<RequestedReport> listener : listeners) {
			listener.onAfterRemove(requestedReport);
		}

		return requestedReport;
	}

	protected RequestedReport removeImpl(RequestedReport requestedReport)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (requestedReport.isCachedModel() ||
					BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(RequestedReportImpl.class,
						requestedReport.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(requestedReport);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		RequestedReportModelImpl requestedReportModelImpl = (RequestedReportModelImpl)requestedReport;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				requestedReportModelImpl.getOriginalUuid(),
				new Long(requestedReportModelImpl.getOriginalGroupId())
			});

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_REQUESTID,
			new Object[] {
				new Long(requestedReportModelImpl.getOriginalRequestId())
			});

		EntityCacheUtil.removeResult(RequestedReportModelImpl.ENTITY_CACHE_ENABLED,
			RequestedReportImpl.class, requestedReport.getPrimaryKey());

		return requestedReport;
	}

	public RequestedReport update(RequestedReport requestedReport)
		throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(RequestedReport requestedReport) method. Use update(RequestedReport requestedReport, boolean merge) instead.");
		}

		return update(requestedReport, false);
	}

	public RequestedReport update(RequestedReport requestedReport, boolean merge)
		throws SystemException {
		boolean isNew = requestedReport.isNew();

		for (ModelListener<RequestedReport> listener : listeners) {
			if (isNew) {
				listener.onBeforeCreate(requestedReport);
			}
			else {
				listener.onBeforeUpdate(requestedReport);
			}
		}

		requestedReport = updateImpl(requestedReport, merge);

		for (ModelListener<RequestedReport> listener : listeners) {
			if (isNew) {
				listener.onAfterCreate(requestedReport);
			}
			else {
				listener.onAfterUpdate(requestedReport);
			}
		}

		return requestedReport;
	}

	public RequestedReport updateImpl(
		com.liferay.bi.report.model.RequestedReport requestedReport,
		boolean merge) throws SystemException {
		boolean isNew = requestedReport.isNew();

		RequestedReportModelImpl requestedReportModelImpl = (RequestedReportModelImpl)requestedReport;

		if (Validator.isNull(requestedReport.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			requestedReport.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, requestedReport, merge);

			requestedReport.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(RequestedReportModelImpl.ENTITY_CACHE_ENABLED,
			RequestedReportImpl.class, requestedReport.getPrimaryKey(),
			requestedReport);

		if (!isNew &&
				(!requestedReport.getUuid()
									 .equals(requestedReportModelImpl.getOriginalUuid()) ||
				(requestedReport.getGroupId() != requestedReportModelImpl.getOriginalGroupId()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					requestedReportModelImpl.getOriginalUuid(),
					new Long(requestedReportModelImpl.getOriginalGroupId())
				});
		}

		if (isNew ||
				(!requestedReport.getUuid()
									 .equals(requestedReportModelImpl.getOriginalUuid()) ||
				(requestedReport.getGroupId() != requestedReportModelImpl.getOriginalGroupId()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					requestedReport.getUuid(),
					new Long(requestedReport.getGroupId())
				}, requestedReport);
		}

		if (!isNew &&
				(requestedReport.getRequestId() != requestedReportModelImpl.getOriginalRequestId())) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_REQUESTID,
				new Object[] {
					new Long(requestedReportModelImpl.getOriginalRequestId())
				});
		}

		if (isNew ||
				(requestedReport.getRequestId() != requestedReportModelImpl.getOriginalRequestId())) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_REQUESTID,
				new Object[] { new Long(requestedReport.getRequestId()) },
				requestedReport);
		}

		return requestedReport;
	}

	public RequestedReport findByPrimaryKey(long requestId)
		throws NoSuchRequestedReportException, SystemException {
		RequestedReport requestedReport = fetchByPrimaryKey(requestId);

		if (requestedReport == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No RequestedReport exists with the primary key " +
					requestId);
			}

			throw new NoSuchRequestedReportException(
				"No RequestedReport exists with the primary key " + requestId);
		}

		return requestedReport;
	}

	public RequestedReport fetchByPrimaryKey(long requestId)
		throws SystemException {
		RequestedReport requestedReport = (RequestedReport)EntityCacheUtil.getResult(RequestedReportModelImpl.ENTITY_CACHE_ENABLED,
				RequestedReportImpl.class, requestId, this);

		if (requestedReport == null) {
			Session session = null;

			try {
				session = openSession();

				requestedReport = (RequestedReport)session.get(RequestedReportImpl.class,
						new Long(requestId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (requestedReport != null) {
					cacheResult(requestedReport);
				}

				closeSession(session);
			}
		}

		return requestedReport;
	}

	public List<RequestedReport> findByUuid(String uuid)
		throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		List<RequestedReport> list = (List<RequestedReport>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_UUID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.RequestedReport WHERE ");

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
					list = new ArrayList<RequestedReport>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_UUID, finderArgs,
					list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<RequestedReport> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	public List<RequestedReport> findByUuid(String uuid, int start, int end,
		OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				uuid,
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<RequestedReport> list = (List<RequestedReport>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_UUID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.RequestedReport WHERE ");

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

				list = (List<RequestedReport>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<RequestedReport>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_UUID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public RequestedReport findByUuid_First(String uuid, OrderByComparator obc)
		throws NoSuchRequestedReportException, SystemException {
		List<RequestedReport> list = findByUuid(uuid, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No RequestedReport exists with the key {");

			msg.append("uuid=" + uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchRequestedReportException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public RequestedReport findByUuid_Last(String uuid, OrderByComparator obc)
		throws NoSuchRequestedReportException, SystemException {
		int count = countByUuid(uuid);

		List<RequestedReport> list = findByUuid(uuid, count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No RequestedReport exists with the key {");

			msg.append("uuid=" + uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchRequestedReportException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public RequestedReport[] findByUuid_PrevAndNext(long requestId,
		String uuid, OrderByComparator obc)
		throws NoSuchRequestedReportException, SystemException {
		RequestedReport requestedReport = findByPrimaryKey(requestId);

		int count = countByUuid(uuid);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"FROM com.liferay.bi.report.model.RequestedReport WHERE ");

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
					requestedReport);

			RequestedReport[] array = new RequestedReportImpl[3];

			array[0] = (RequestedReport)objArray[0];
			array[1] = (RequestedReport)objArray[1];
			array[2] = (RequestedReport)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public RequestedReport findByUUID_G(String uuid, long groupId)
		throws NoSuchRequestedReportException, SystemException {
		RequestedReport requestedReport = fetchByUUID_G(uuid, groupId);

		if (requestedReport == null) {
			StringBuilder msg = new StringBuilder();

			msg.append("No RequestedReport exists with the key {");

			msg.append("uuid=" + uuid);

			msg.append(", ");
			msg.append("groupId=" + groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchRequestedReportException(msg.toString());
		}

		return requestedReport;
	}

	public RequestedReport fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	public RequestedReport fetchByUUID_G(String uuid, long groupId,
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
					"FROM com.liferay.bi.report.model.RequestedReport WHERE ");

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

				List<RequestedReport> list = q.list();

				result = list;

				RequestedReport requestedReport = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					requestedReport = list.get(0);

					cacheResult(requestedReport);
				}

				return requestedReport;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, new ArrayList<RequestedReport>());
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List) {
				return null;
			}
			else {
				return (RequestedReport)result;
			}
		}
	}

	public List<RequestedReport> findByCompanyId(long companyId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(companyId) };

		List<RequestedReport> list = (List<RequestedReport>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_COMPANYID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.RequestedReport WHERE ");

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
					list = new ArrayList<RequestedReport>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_COMPANYID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<RequestedReport> findByCompanyId(long companyId, int start,
		int end) throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	public List<RequestedReport> findByCompanyId(long companyId, int start,
		int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(companyId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<RequestedReport> list = (List<RequestedReport>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_COMPANYID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.RequestedReport WHERE ");

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

				list = (List<RequestedReport>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<RequestedReport>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_COMPANYID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public RequestedReport findByCompanyId_First(long companyId,
		OrderByComparator obc)
		throws NoSuchRequestedReportException, SystemException {
		List<RequestedReport> list = findByCompanyId(companyId, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No RequestedReport exists with the key {");

			msg.append("companyId=" + companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchRequestedReportException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public RequestedReport findByCompanyId_Last(long companyId,
		OrderByComparator obc)
		throws NoSuchRequestedReportException, SystemException {
		int count = countByCompanyId(companyId);

		List<RequestedReport> list = findByCompanyId(companyId, count - 1,
				count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No RequestedReport exists with the key {");

			msg.append("companyId=" + companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchRequestedReportException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public RequestedReport[] findByCompanyId_PrevAndNext(long requestId,
		long companyId, OrderByComparator obc)
		throws NoSuchRequestedReportException, SystemException {
		RequestedReport requestedReport = findByPrimaryKey(requestId);

		int count = countByCompanyId(companyId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"FROM com.liferay.bi.report.model.RequestedReport WHERE ");

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
					requestedReport);

			RequestedReport[] array = new RequestedReportImpl[3];

			array[0] = (RequestedReport)objArray[0];
			array[1] = (RequestedReport)objArray[1];
			array[2] = (RequestedReport)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<RequestedReport> findByCompanyGroupId(long companyId,
		long groupId) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(companyId), new Long(groupId)
			};

		List<RequestedReport> list = (List<RequestedReport>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_COMPANYGROUPID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.RequestedReport WHERE ");

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
					list = new ArrayList<RequestedReport>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_COMPANYGROUPID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<RequestedReport> findByCompanyGroupId(long companyId,
		long groupId, int start, int end) throws SystemException {
		return findByCompanyGroupId(companyId, groupId, start, end, null);
	}

	public List<RequestedReport> findByCompanyGroupId(long companyId,
		long groupId, int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(companyId), new Long(groupId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<RequestedReport> list = (List<RequestedReport>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_COMPANYGROUPID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.RequestedReport WHERE ");

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

				list = (List<RequestedReport>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<RequestedReport>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_COMPANYGROUPID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public RequestedReport findByCompanyGroupId_First(long companyId,
		long groupId, OrderByComparator obc)
		throws NoSuchRequestedReportException, SystemException {
		List<RequestedReport> list = findByCompanyGroupId(companyId, groupId,
				0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No RequestedReport exists with the key {");

			msg.append("companyId=" + companyId);

			msg.append(", ");
			msg.append("groupId=" + groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchRequestedReportException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public RequestedReport findByCompanyGroupId_Last(long companyId,
		long groupId, OrderByComparator obc)
		throws NoSuchRequestedReportException, SystemException {
		int count = countByCompanyGroupId(companyId, groupId);

		List<RequestedReport> list = findByCompanyGroupId(companyId, groupId,
				count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No RequestedReport exists with the key {");

			msg.append("companyId=" + companyId);

			msg.append(", ");
			msg.append("groupId=" + groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchRequestedReportException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public RequestedReport[] findByCompanyGroupId_PrevAndNext(long requestId,
		long companyId, long groupId, OrderByComparator obc)
		throws NoSuchRequestedReportException, SystemException {
		RequestedReport requestedReport = findByPrimaryKey(requestId);

		int count = countByCompanyGroupId(companyId, groupId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"FROM com.liferay.bi.report.model.RequestedReport WHERE ");

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
					requestedReport);

			RequestedReport[] array = new RequestedReportImpl[3];

			array[0] = (RequestedReport)objArray[0];
			array[1] = (RequestedReport)objArray[1];
			array[2] = (RequestedReport)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<RequestedReport> findByGroupId(long groupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(groupId) };

		List<RequestedReport> list = (List<RequestedReport>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_GROUPID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.RequestedReport WHERE ");

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
					list = new ArrayList<RequestedReport>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_GROUPID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<RequestedReport> findByGroupId(long groupId, int start, int end)
		throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

	public List<RequestedReport> findByGroupId(long groupId, int start,
		int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(groupId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<RequestedReport> list = (List<RequestedReport>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_GROUPID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.RequestedReport WHERE ");

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

				list = (List<RequestedReport>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<RequestedReport>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_GROUPID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public RequestedReport findByGroupId_First(long groupId,
		OrderByComparator obc)
		throws NoSuchRequestedReportException, SystemException {
		List<RequestedReport> list = findByGroupId(groupId, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No RequestedReport exists with the key {");

			msg.append("groupId=" + groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchRequestedReportException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public RequestedReport findByGroupId_Last(long groupId,
		OrderByComparator obc)
		throws NoSuchRequestedReportException, SystemException {
		int count = countByGroupId(groupId);

		List<RequestedReport> list = findByGroupId(groupId, count - 1, count,
				obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No RequestedReport exists with the key {");

			msg.append("groupId=" + groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchRequestedReportException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public RequestedReport[] findByGroupId_PrevAndNext(long requestId,
		long groupId, OrderByComparator obc)
		throws NoSuchRequestedReportException, SystemException {
		RequestedReport requestedReport = findByPrimaryKey(requestId);

		int count = countByGroupId(groupId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"FROM com.liferay.bi.report.model.RequestedReport WHERE ");

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
					requestedReport);

			RequestedReport[] array = new RequestedReportImpl[3];

			array[0] = (RequestedReport)objArray[0];
			array[1] = (RequestedReport)objArray[1];
			array[2] = (RequestedReport)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<RequestedReport> findByUserId(long userId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(userId) };

		List<RequestedReport> list = (List<RequestedReport>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_USERID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.RequestedReport WHERE ");

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
					list = new ArrayList<RequestedReport>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_USERID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<RequestedReport> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	public List<RequestedReport> findByUserId(long userId, int start, int end,
		OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(userId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<RequestedReport> list = (List<RequestedReport>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_USERID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.RequestedReport WHERE ");

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

				list = (List<RequestedReport>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<RequestedReport>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_USERID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public RequestedReport findByUserId_First(long userId, OrderByComparator obc)
		throws NoSuchRequestedReportException, SystemException {
		List<RequestedReport> list = findByUserId(userId, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No RequestedReport exists with the key {");

			msg.append("userId=" + userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchRequestedReportException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public RequestedReport findByUserId_Last(long userId, OrderByComparator obc)
		throws NoSuchRequestedReportException, SystemException {
		int count = countByUserId(userId);

		List<RequestedReport> list = findByUserId(userId, count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No RequestedReport exists with the key {");

			msg.append("userId=" + userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchRequestedReportException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public RequestedReport[] findByUserId_PrevAndNext(long requestId,
		long userId, OrderByComparator obc)
		throws NoSuchRequestedReportException, SystemException {
		RequestedReport requestedReport = findByPrimaryKey(requestId);

		int count = countByUserId(userId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"FROM com.liferay.bi.report.model.RequestedReport WHERE ");

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
					requestedReport);

			RequestedReport[] array = new RequestedReportImpl[3];

			array[0] = (RequestedReport)objArray[0];
			array[1] = (RequestedReport)objArray[1];
			array[2] = (RequestedReport)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public RequestedReport findByRequestId(long requestId)
		throws NoSuchRequestedReportException, SystemException {
		RequestedReport requestedReport = fetchByRequestId(requestId);

		if (requestedReport == null) {
			StringBuilder msg = new StringBuilder();

			msg.append("No RequestedReport exists with the key {");

			msg.append("requestId=" + requestId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchRequestedReportException(msg.toString());
		}

		return requestedReport;
	}

	public RequestedReport fetchByRequestId(long requestId)
		throws SystemException {
		return fetchByRequestId(requestId, true);
	}

	public RequestedReport fetchByRequestId(long requestId,
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
					"FROM com.liferay.bi.report.model.RequestedReport WHERE ");

				query.append("requestId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("modifiedDate ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(requestId);

				List<RequestedReport> list = q.list();

				result = list;

				RequestedReport requestedReport = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_REQUESTID,
						finderArgs, list);
				}
				else {
					requestedReport = list.get(0);

					cacheResult(requestedReport);
				}

				return requestedReport;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_REQUESTID,
						finderArgs, new ArrayList<RequestedReport>());
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List) {
				return null;
			}
			else {
				return (RequestedReport)result;
			}
		}
	}

	public List<RequestedReport> findByDefinitionId(long definitionId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(definitionId) };

		List<RequestedReport> list = (List<RequestedReport>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_DEFINITIONID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.RequestedReport WHERE ");

				query.append("definitionId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("modifiedDate ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(definitionId);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<RequestedReport>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_DEFINITIONID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<RequestedReport> findByDefinitionId(long definitionId,
		int start, int end) throws SystemException {
		return findByDefinitionId(definitionId, start, end, null);
	}

	public List<RequestedReport> findByDefinitionId(long definitionId,
		int start, int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(definitionId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<RequestedReport> list = (List<RequestedReport>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_DEFINITIONID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.RequestedReport WHERE ");

				query.append("definitionId = ?");

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

				qPos.add(definitionId);

				list = (List<RequestedReport>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<RequestedReport>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_DEFINITIONID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public RequestedReport findByDefinitionId_First(long definitionId,
		OrderByComparator obc)
		throws NoSuchRequestedReportException, SystemException {
		List<RequestedReport> list = findByDefinitionId(definitionId, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No RequestedReport exists with the key {");

			msg.append("definitionId=" + definitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchRequestedReportException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public RequestedReport findByDefinitionId_Last(long definitionId,
		OrderByComparator obc)
		throws NoSuchRequestedReportException, SystemException {
		int count = countByDefinitionId(definitionId);

		List<RequestedReport> list = findByDefinitionId(definitionId,
				count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No RequestedReport exists with the key {");

			msg.append("definitionId=" + definitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchRequestedReportException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public RequestedReport[] findByDefinitionId_PrevAndNext(long requestId,
		long definitionId, OrderByComparator obc)
		throws NoSuchRequestedReportException, SystemException {
		RequestedReport requestedReport = findByPrimaryKey(requestId);

		int count = countByDefinitionId(definitionId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"FROM com.liferay.bi.report.model.RequestedReport WHERE ");

			query.append("definitionId = ?");

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

			qPos.add(definitionId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					requestedReport);

			RequestedReport[] array = new RequestedReportImpl[3];

			array[0] = (RequestedReport)objArray[0];
			array[1] = (RequestedReport)objArray[1];
			array[2] = (RequestedReport)objArray[2];

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

	public List<RequestedReport> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<RequestedReport> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<RequestedReport> findAll(int start, int end,
		OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<RequestedReport> list = (List<RequestedReport>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.bi.report.model.RequestedReport ");

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
					list = (List<RequestedReport>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<RequestedReport>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<RequestedReport>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByUuid(String uuid) throws SystemException {
		for (RequestedReport requestedReport : findByUuid(uuid)) {
			remove(requestedReport);
		}
	}

	public void removeByUUID_G(String uuid, long groupId)
		throws NoSuchRequestedReportException, SystemException {
		RequestedReport requestedReport = findByUUID_G(uuid, groupId);

		remove(requestedReport);
	}

	public void removeByCompanyId(long companyId) throws SystemException {
		for (RequestedReport requestedReport : findByCompanyId(companyId)) {
			remove(requestedReport);
		}
	}

	public void removeByCompanyGroupId(long companyId, long groupId)
		throws SystemException {
		for (RequestedReport requestedReport : findByCompanyGroupId(companyId,
				groupId)) {
			remove(requestedReport);
		}
	}

	public void removeByGroupId(long groupId) throws SystemException {
		for (RequestedReport requestedReport : findByGroupId(groupId)) {
			remove(requestedReport);
		}
	}

	public void removeByUserId(long userId) throws SystemException {
		for (RequestedReport requestedReport : findByUserId(userId)) {
			remove(requestedReport);
		}
	}

	public void removeByRequestId(long requestId)
		throws NoSuchRequestedReportException, SystemException {
		RequestedReport requestedReport = findByRequestId(requestId);

		remove(requestedReport);
	}

	public void removeByDefinitionId(long definitionId)
		throws SystemException {
		for (RequestedReport requestedReport : findByDefinitionId(definitionId)) {
			remove(requestedReport);
		}
	}

	public void removeAll() throws SystemException {
		for (RequestedReport requestedReport : findAll()) {
			remove(requestedReport);
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
					"FROM com.liferay.bi.report.model.RequestedReport WHERE ");

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
					"FROM com.liferay.bi.report.model.RequestedReport WHERE ");

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
					"FROM com.liferay.bi.report.model.RequestedReport WHERE ");

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
					"FROM com.liferay.bi.report.model.RequestedReport WHERE ");

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
					"FROM com.liferay.bi.report.model.RequestedReport WHERE ");

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
					"FROM com.liferay.bi.report.model.RequestedReport WHERE ");

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
					"FROM com.liferay.bi.report.model.RequestedReport WHERE ");

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
					"FROM com.liferay.bi.report.model.RequestedReport WHERE ");

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
						"SELECT COUNT(*) FROM com.liferay.bi.report.model.RequestedReport");

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
						"value.object.listener.com.liferay.bi.report.model.RequestedReport")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<RequestedReport>> listenersList = new ArrayList<ModelListener<RequestedReport>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<RequestedReport>)Class.forName(
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
	@BeanReference(name = "com.liferay.bi.report.service.persistence.RequestedReportPersistence.impl")
	protected com.liferay.bi.report.service.persistence.RequestedReportPersistence requestedReportPersistence;
	private static Log _log = LogFactoryUtil.getLog(RequestedReportPersistenceImpl.class);
}