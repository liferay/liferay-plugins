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
 * <a href="ReportRequestPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface ReportRequestPersistence extends BasePersistence {
	public void cacheResult(
		com.liferay.bi.report.model.ReportRequest reportRequest);

	public void cacheResult(
		java.util.List<com.liferay.bi.report.model.ReportRequest> reportRequests);

	public com.liferay.bi.report.model.ReportRequest create(long requestId);

	public com.liferay.bi.report.model.ReportRequest remove(long requestId)
		throws com.liferay.bi.report.NoSuchRequestException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportRequest remove(
		com.liferay.bi.report.model.ReportRequest reportRequest)
		throws com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportRequest update(
		com.liferay.bi.report.model.ReportRequest reportRequest)
		throws com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportRequest update(
		com.liferay.bi.report.model.ReportRequest reportRequest, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportRequest updateImpl(
		com.liferay.bi.report.model.ReportRequest reportRequest, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportRequest findByPrimaryKey(
		long requestId)
		throws com.liferay.bi.report.NoSuchRequestException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportRequest fetchByPrimaryKey(
		long requestId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportRequest> findByUuid(
		java.lang.String uuid) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportRequest> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportRequest> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportRequest findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportRequest findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportRequest[] findByUuid_PrevAndNext(
		long requestId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportRequest findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.bi.report.NoSuchRequestException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportRequest fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportRequest fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportRequest> findByCompanyId(
		long companyId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportRequest> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportRequest> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportRequest findByCompanyId_First(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportRequest findByCompanyId_Last(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportRequest[] findByCompanyId_PrevAndNext(
		long requestId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportRequest> findByCompanyGroupId(
		long companyId, long groupId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportRequest> findByCompanyGroupId(
		long companyId, long groupId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportRequest> findByCompanyGroupId(
		long companyId, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportRequest findByCompanyGroupId_First(
		long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportRequest findByCompanyGroupId_Last(
		long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportRequest[] findByCompanyGroupId_PrevAndNext(
		long requestId, long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportRequest> findByGroupId(
		long groupId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportRequest> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportRequest> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportRequest findByGroupId_First(
		long groupId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportRequest findByGroupId_Last(
		long groupId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportRequest[] findByGroupId_PrevAndNext(
		long requestId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportRequest> findByUserId(
		long userId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportRequest> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportRequest> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportRequest findByUserId_First(
		long userId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportRequest findByUserId_Last(
		long userId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportRequest[] findByUserId_PrevAndNext(
		long requestId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchRequestException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportRequest findByRequestId(
		long requestId)
		throws com.liferay.bi.report.NoSuchRequestException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportRequest fetchByRequestId(
		long requestId) throws com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportRequest fetchByRequestId(
		long requestId, boolean retrieveFromCache)
		throws com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportRequest findByDefinitionId(
		long definitionId)
		throws com.liferay.bi.report.NoSuchRequestException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportRequest fetchByDefinitionId(
		long definitionId) throws com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportRequest fetchByDefinitionId(
		long definitionId, boolean retrieveFromCache)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportRequest> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportRequest> findAll(
		int start, int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportRequest> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.SystemException;

	public void removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.bi.report.NoSuchRequestException,
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
		throws com.liferay.bi.report.NoSuchRequestException,
			com.liferay.portal.SystemException;

	public void removeByDefinitionId(long definitionId)
		throws com.liferay.bi.report.NoSuchRequestException,
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