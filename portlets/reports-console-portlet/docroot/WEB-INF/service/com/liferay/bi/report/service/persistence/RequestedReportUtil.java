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

/**
 * <a href="RequestedReportUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class RequestedReportUtil {
	public static void cacheResult(
		com.liferay.bi.report.model.RequestedReport requestedReport) {
		getPersistence().cacheResult(requestedReport);
	}

	public static void cacheResult(
		java.util.List<com.liferay.bi.report.model.RequestedReport> requestedReports) {
		getPersistence().cacheResult(requestedReports);
	}

	public static com.liferay.bi.report.model.RequestedReport create(
		long requestId) {
		return getPersistence().create(requestId);
	}

	public static com.liferay.bi.report.model.RequestedReport remove(
		long requestId)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException {
		return getPersistence().remove(requestId);
	}

	public static com.liferay.bi.report.model.RequestedReport remove(
		com.liferay.bi.report.model.RequestedReport requestedReport)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(requestedReport);
	}

	public static com.liferay.bi.report.model.RequestedReport update(
		com.liferay.bi.report.model.RequestedReport requestedReport)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(requestedReport);
	}

	public static com.liferay.bi.report.model.RequestedReport update(
		com.liferay.bi.report.model.RequestedReport requestedReport,
		boolean merge) throws com.liferay.portal.SystemException {
		return getPersistence().update(requestedReport, merge);
	}

	public static com.liferay.bi.report.model.RequestedReport updateImpl(
		com.liferay.bi.report.model.RequestedReport requestedReport,
		boolean merge) throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(requestedReport, merge);
	}

	public static com.liferay.bi.report.model.RequestedReport findByPrimaryKey(
		long requestId)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException {
		return getPersistence().findByPrimaryKey(requestId);
	}

	public static com.liferay.bi.report.model.RequestedReport fetchByPrimaryKey(
		long requestId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(requestId);
	}

	public static java.util.List<com.liferay.bi.report.model.RequestedReport> findByUuid(
		java.lang.String uuid) throws com.liferay.portal.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	public static java.util.List<com.liferay.bi.report.model.RequestedReport> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	public static java.util.List<com.liferay.bi.report.model.RequestedReport> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByUuid(uuid, start, end, obc);
	}

	public static com.liferay.bi.report.model.RequestedReport findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException {
		return getPersistence().findByUuid_First(uuid, obc);
	}

	public static com.liferay.bi.report.model.RequestedReport findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException {
		return getPersistence().findByUuid_Last(uuid, obc);
	}

	public static com.liferay.bi.report.model.RequestedReport[] findByUuid_PrevAndNext(
		long requestId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException {
		return getPersistence().findByUuid_PrevAndNext(requestId, uuid, obc);
	}

	public static com.liferay.bi.report.model.RequestedReport findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	public static com.liferay.bi.report.model.RequestedReport fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	public static com.liferay.bi.report.model.RequestedReport fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	public static java.util.List<com.liferay.bi.report.model.RequestedReport> findByCompanyId(
		long companyId) throws com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	public static java.util.List<com.liferay.bi.report.model.RequestedReport> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	public static java.util.List<com.liferay.bi.report.model.RequestedReport> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end, obc);
	}

	public static com.liferay.bi.report.model.RequestedReport findByCompanyId_First(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId_First(companyId, obc);
	}

	public static com.liferay.bi.report.model.RequestedReport findByCompanyId_Last(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId_Last(companyId, obc);
	}

	public static com.liferay.bi.report.model.RequestedReport[] findByCompanyId_PrevAndNext(
		long requestId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(requestId, companyId, obc);
	}

	public static java.util.List<com.liferay.bi.report.model.RequestedReport> findByCompanyGroupId(
		long companyId, long groupId) throws com.liferay.portal.SystemException {
		return getPersistence().findByCompanyGroupId(companyId, groupId);
	}

	public static java.util.List<com.liferay.bi.report.model.RequestedReport> findByCompanyGroupId(
		long companyId, long groupId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByCompanyGroupId(companyId, groupId, start, end);
	}

	public static java.util.List<com.liferay.bi.report.model.RequestedReport> findByCompanyGroupId(
		long companyId, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByCompanyGroupId(companyId, groupId, start, end, obc);
	}

	public static com.liferay.bi.report.model.RequestedReport findByCompanyGroupId_First(
		long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByCompanyGroupId_First(companyId, groupId, obc);
	}

	public static com.liferay.bi.report.model.RequestedReport findByCompanyGroupId_Last(
		long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByCompanyGroupId_Last(companyId, groupId, obc);
	}

	public static com.liferay.bi.report.model.RequestedReport[] findByCompanyGroupId_PrevAndNext(
		long requestId, long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByCompanyGroupId_PrevAndNext(requestId, companyId,
			groupId, obc);
	}

	public static java.util.List<com.liferay.bi.report.model.RequestedReport> findByGroupId(
		long groupId) throws com.liferay.portal.SystemException {
		return getPersistence().findByGroupId(groupId);
	}

	public static java.util.List<com.liferay.bi.report.model.RequestedReport> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	public static java.util.List<com.liferay.bi.report.model.RequestedReport> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByGroupId(groupId, start, end, obc);
	}

	public static com.liferay.bi.report.model.RequestedReport findByGroupId_First(
		long groupId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException {
		return getPersistence().findByGroupId_First(groupId, obc);
	}

	public static com.liferay.bi.report.model.RequestedReport findByGroupId_Last(
		long groupId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException {
		return getPersistence().findByGroupId_Last(groupId, obc);
	}

	public static com.liferay.bi.report.model.RequestedReport[] findByGroupId_PrevAndNext(
		long requestId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(requestId, groupId, obc);
	}

	public static java.util.List<com.liferay.bi.report.model.RequestedReport> findByUserId(
		long userId) throws com.liferay.portal.SystemException {
		return getPersistence().findByUserId(userId);
	}

	public static java.util.List<com.liferay.bi.report.model.RequestedReport> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByUserId(userId, start, end);
	}

	public static java.util.List<com.liferay.bi.report.model.RequestedReport> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByUserId(userId, start, end, obc);
	}

	public static com.liferay.bi.report.model.RequestedReport findByUserId_First(
		long userId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException {
		return getPersistence().findByUserId_First(userId, obc);
	}

	public static com.liferay.bi.report.model.RequestedReport findByUserId_Last(
		long userId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException {
		return getPersistence().findByUserId_Last(userId, obc);
	}

	public static com.liferay.bi.report.model.RequestedReport[] findByUserId_PrevAndNext(
		long requestId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException {
		return getPersistence().findByUserId_PrevAndNext(requestId, userId, obc);
	}

	public static com.liferay.bi.report.model.RequestedReport findByRequestId(
		long requestId)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException {
		return getPersistence().findByRequestId(requestId);
	}

	public static com.liferay.bi.report.model.RequestedReport fetchByRequestId(
		long requestId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByRequestId(requestId);
	}

	public static com.liferay.bi.report.model.RequestedReport fetchByRequestId(
		long requestId, boolean retrieveFromCache)
		throws com.liferay.portal.SystemException {
		return getPersistence().fetchByRequestId(requestId, retrieveFromCache);
	}

	public static java.util.List<com.liferay.bi.report.model.RequestedReport> findByDefinitionId(
		long definitionId) throws com.liferay.portal.SystemException {
		return getPersistence().findByDefinitionId(definitionId);
	}

	public static java.util.List<com.liferay.bi.report.model.RequestedReport> findByDefinitionId(
		long definitionId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByDefinitionId(definitionId, start, end);
	}

	public static java.util.List<com.liferay.bi.report.model.RequestedReport> findByDefinitionId(
		long definitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByDefinitionId(definitionId, start, end, obc);
	}

	public static com.liferay.bi.report.model.RequestedReport findByDefinitionId_First(
		long definitionId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException {
		return getPersistence().findByDefinitionId_First(definitionId, obc);
	}

	public static com.liferay.bi.report.model.RequestedReport findByDefinitionId_Last(
		long definitionId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException {
		return getPersistence().findByDefinitionId_Last(definitionId, obc);
	}

	public static com.liferay.bi.report.model.RequestedReport[] findByDefinitionId_PrevAndNext(
		long requestId, long definitionId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByDefinitionId_PrevAndNext(requestId, definitionId, obc);
	}

	public static java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	public static java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	public static java.util.List<com.liferay.bi.report.model.RequestedReport> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.bi.report.model.RequestedReport> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.bi.report.model.RequestedReport> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	public static void removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException {
		getPersistence().removeByUUID_G(uuid, groupId);
	}

	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	public static void removeByCompanyGroupId(long companyId, long groupId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByCompanyGroupId(companyId, groupId);
	}

	public static void removeByGroupId(long groupId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByGroupId(groupId);
	}

	public static void removeByUserId(long userId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByUserId(userId);
	}

	public static void removeByRequestId(long requestId)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException {
		getPersistence().removeByRequestId(requestId);
	}

	public static void removeByDefinitionId(long definitionId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByDefinitionId(definitionId);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	public static int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	public static int countByCompanyGroupId(long companyId, long groupId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByCompanyGroupId(companyId, groupId);
	}

	public static int countByGroupId(long groupId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByGroupId(groupId);
	}

	public static int countByUserId(long userId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByUserId(userId);
	}

	public static int countByRequestId(long requestId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByRequestId(requestId);
	}

	public static int countByDefinitionId(long definitionId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByDefinitionId(definitionId);
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static RequestedReportPersistence getPersistence() {
		return _persistence;
	}

	public void setPersistence(RequestedReportPersistence persistence) {
		_persistence = persistence;
	}

	private static RequestedReportPersistence _persistence;
}