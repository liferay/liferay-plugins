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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * <a href="RequestedReportPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface RequestedReportPersistence extends BasePersistence {
	public void cacheResult(
		com.liferay.bi.report.model.RequestedReport requestedReport);

	public void cacheResult(
		java.util.List<com.liferay.bi.report.model.RequestedReport> requestedReports);

	public com.liferay.bi.report.model.RequestedReport create(long requestId);

	public com.liferay.bi.report.model.RequestedReport remove(long requestId)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.RequestedReport remove(
		com.liferay.bi.report.model.RequestedReport requestedReport)
		throws com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.RequestedReport update(
		com.liferay.bi.report.model.RequestedReport requestedReport)
		throws com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.RequestedReport update(
		com.liferay.bi.report.model.RequestedReport requestedReport,
		boolean merge) throws com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.RequestedReport updateImpl(
		com.liferay.bi.report.model.RequestedReport requestedReport,
		boolean merge) throws com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.RequestedReport findByPrimaryKey(
		long requestId)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.RequestedReport fetchByPrimaryKey(
		long requestId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.RequestedReport> findByUuid(
		java.lang.String uuid) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.RequestedReport> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.RequestedReport> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.RequestedReport findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.RequestedReport findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.RequestedReport[] findByUuid_PrevAndNext(
		long requestId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.RequestedReport findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.RequestedReport fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.RequestedReport fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.RequestedReport> findByCompanyId(
		long companyId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.RequestedReport> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.RequestedReport> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.RequestedReport findByCompanyId_First(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.RequestedReport findByCompanyId_Last(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.RequestedReport[] findByCompanyId_PrevAndNext(
		long requestId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.RequestedReport> findByCompanyGroupId(
		long companyId, long groupId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.RequestedReport> findByCompanyGroupId(
		long companyId, long groupId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.RequestedReport> findByCompanyGroupId(
		long companyId, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.RequestedReport findByCompanyGroupId_First(
		long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.RequestedReport findByCompanyGroupId_Last(
		long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.RequestedReport[] findByCompanyGroupId_PrevAndNext(
		long requestId, long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.RequestedReport> findByGroupId(
		long groupId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.RequestedReport> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.RequestedReport> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.RequestedReport findByGroupId_First(
		long groupId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.RequestedReport findByGroupId_Last(
		long groupId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.RequestedReport[] findByGroupId_PrevAndNext(
		long requestId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.RequestedReport> findByUserId(
		long userId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.RequestedReport> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.RequestedReport> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.RequestedReport findByUserId_First(
		long userId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.RequestedReport findByUserId_Last(
		long userId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.RequestedReport[] findByUserId_PrevAndNext(
		long requestId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.RequestedReport findByRequestId(
		long requestId)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.RequestedReport fetchByRequestId(
		long requestId) throws com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.RequestedReport fetchByRequestId(
		long requestId, boolean retrieveFromCache)
		throws com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.RequestedReport findByDefinitionId(
		long definitionId)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.RequestedReport fetchByDefinitionId(
		long definitionId) throws com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.RequestedReport fetchByDefinitionId(
		long definitionId, boolean retrieveFromCache)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.RequestedReport> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.RequestedReport> findAll(
		int start, int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.RequestedReport> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.SystemException;

	public void removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException;

	public void removeByCompanyId(long companyId)
		throws com.liferay.portal.SystemException;

	public void removeByCompanyGroupId(long companyId, long groupId)
		throws com.liferay.portal.SystemException;

	public void removeByGroupId(long groupId)
		throws com.liferay.portal.SystemException;

	public void removeByUserId(long userId)
		throws com.liferay.portal.SystemException;

	public void removeByRequestId(long requestId)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException;

	public void removeByDefinitionId(long definitionId)
		throws com.liferay.bi.report.NoSuchRequestedReportException,
			com.liferay.portal.SystemException;

	public void removeAll() throws com.liferay.portal.SystemException;

	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.SystemException;

	public int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.SystemException;

	public int countByCompanyId(long companyId)
		throws com.liferay.portal.SystemException;

	public int countByCompanyGroupId(long companyId, long groupId)
		throws com.liferay.portal.SystemException;

	public int countByGroupId(long groupId)
		throws com.liferay.portal.SystemException;

	public int countByUserId(long userId)
		throws com.liferay.portal.SystemException;

	public int countByRequestId(long requestId)
		throws com.liferay.portal.SystemException;

	public int countByDefinitionId(long definitionId)
		throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;
}