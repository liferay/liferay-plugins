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

package com.liferay.iweb.service;

/**
 * <a href="InterestGroupLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface InterestGroupLocalService {
	public com.liferay.iweb.model.InterestGroup addInterestGroup(
		com.liferay.iweb.model.InterestGroup interestGroup)
		throws com.liferay.portal.SystemException;

	public void deleteInterestGroup(long cid)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public void deleteInterestGroup(
		com.liferay.iweb.model.InterestGroup interestGroup)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	public com.liferay.iweb.model.InterestGroup getInterestGroup(long cid)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public java.util.List<com.liferay.iweb.model.InterestGroup> getInterestGroups(
		int start, int end) throws com.liferay.portal.SystemException;

	public int getInterestGroupsCount()
		throws com.liferay.portal.SystemException;

	public com.liferay.iweb.model.InterestGroup updateInterestGroup(
		com.liferay.iweb.model.InterestGroup interestGroup)
		throws com.liferay.portal.SystemException;

	public java.util.Set<com.liferay.iweb.model.InterestGroup> getAllCommunities()
		throws com.liferay.iweb.IWebException;

	public java.util.Map<Long, java.util.Set<com.liferay.iweb.model.SemanticsFile>> getAppliedSemanticsFiles(
		long interestGroupId) throws com.liferay.iweb.IWebException;

	public java.util.Map<Long, java.util.Set<com.liferay.iweb.model.SemanticsFile>> getAppliedSemanticsFiles(
		java.util.Set<Long> interestGroupIds)
		throws com.liferay.iweb.IWebException;

	public java.util.Set<com.liferay.iweb.model.InterestGroup> getRelatedInterestGroupsByAppliedSemantics(
		long interestGroupId) throws com.liferay.iweb.IWebException;

	public void updateInterestGroupWithSemantics(long interestGroupId,
		java.util.Set<String> semanticsSet)
		throws com.liferay.iweb.IWebException;

	public void updateInterestGroupWithSemantics(
		java.util.Map<Long, java.util.Set<String>> interestGroupSemanticsMap)
		throws com.liferay.iweb.IWebException;
}