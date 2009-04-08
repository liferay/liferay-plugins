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
 * <a href="ReportDefinitionPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface ReportDefinitionPersistence extends BasePersistence {
	public void cacheResult(
		com.liferay.bi.report.model.ReportDefinition reportDefinition);

	public void cacheResult(
		java.util.List<com.liferay.bi.report.model.ReportDefinition> reportDefinitions);

	public com.liferay.bi.report.model.ReportDefinition create(
		long definitionId);

	public com.liferay.bi.report.model.ReportDefinition remove(
		long definitionId)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportDefinition remove(
		com.liferay.bi.report.model.ReportDefinition reportDefinition)
		throws com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportDefinition update(
		com.liferay.bi.report.model.ReportDefinition reportDefinition)
		throws com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportDefinition update(
		com.liferay.bi.report.model.ReportDefinition reportDefinition,
		boolean merge) throws com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportDefinition updateImpl(
		com.liferay.bi.report.model.ReportDefinition reportDefinition,
		boolean merge) throws com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportDefinition findByPrimaryKey(
		long definitionId)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportDefinition fetchByPrimaryKey(
		long definitionId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportDefinition> findByUuid(
		java.lang.String uuid) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportDefinition> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportDefinition> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportDefinition findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportDefinition findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportDefinition[] findByUuid_PrevAndNext(
		long definitionId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportDefinition findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportDefinition fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportDefinition fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportDefinition> findByCompanyId(
		long companyId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportDefinition> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportDefinition> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportDefinition findByCompanyId_First(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportDefinition findByCompanyId_Last(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportDefinition[] findByCompanyId_PrevAndNext(
		long definitionId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportDefinition> findByCompanyGroupId(
		long companyId, long groupId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportDefinition> findByCompanyGroupId(
		long companyId, long groupId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportDefinition> findByCompanyGroupId(
		long companyId, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportDefinition findByCompanyGroupId_First(
		long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportDefinition findByCompanyGroupId_Last(
		long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportDefinition[] findByCompanyGroupId_PrevAndNext(
		long definitionId, long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportDefinition> findByGroupId(
		long groupId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportDefinition> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportDefinition> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportDefinition findByGroupId_First(
		long groupId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportDefinition findByGroupId_Last(
		long groupId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportDefinition[] findByGroupId_PrevAndNext(
		long definitionId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportDefinition> findByUserId(
		long userId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportDefinition> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportDefinition> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportDefinition findByUserId_First(
		long userId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportDefinition findByUserId_Last(
		long userId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportDefinition[] findByUserId_PrevAndNext(
		long definitionId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportDefinition> findByName(
		java.lang.String name) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportDefinition> findByName(
		java.lang.String name, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportDefinition> findByName(
		java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportDefinition findByName_First(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportDefinition findByName_Last(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException;

	public com.liferay.bi.report.model.ReportDefinition[] findByName_PrevAndNext(
		long definitionId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportDefinition> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportDefinition> findAll(
		int start, int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.bi.report.model.ReportDefinition> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.SystemException;

	public void removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.bi.report.NoSuchDefinitionException,
			com.liferay.portal.SystemException;

	public void removeByCompanyId(long companyId)
		throws com.liferay.portal.SystemException;

	public void removeByCompanyGroupId(long companyId, long groupId)
		throws com.liferay.portal.SystemException;

	public void removeByGroupId(long groupId)
		throws com.liferay.portal.SystemException;

	public void removeByUserId(long userId)
		throws com.liferay.portal.SystemException;

	public void removeByName(java.lang.String name)
		throws com.liferay.portal.SystemException;

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

	public int countByName(java.lang.String name)
		throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;
}