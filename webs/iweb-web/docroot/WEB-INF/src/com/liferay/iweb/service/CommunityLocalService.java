/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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
 * <a href="CommunityLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface CommunityLocalService {
	public com.liferay.iweb.model.Community addCommunity(
		com.liferay.iweb.model.Community community)
		throws com.liferay.portal.SystemException;

	public void deleteCommunity(long cid)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public void deleteCommunity(com.liferay.iweb.model.Community community)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	public com.liferay.iweb.model.Community getCommunity(long cid)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public com.liferay.iweb.model.Community updateCommunity(
		com.liferay.iweb.model.Community community)
		throws com.liferay.portal.SystemException;

	public java.util.Set<com.liferay.iweb.model.Community> getAllCommunities()
		throws com.liferay.iweb.IWebException;

	public java.util.Map getAppliedSemantics(long communityId)
		throws com.liferay.iweb.IWebException;

	public java.util.Map<Long, java.util.Set<com.liferay.iweb.model.Semantics>> getAppliedSemantics(
		java.util.Set<Long> communityIds) throws com.liferay.iweb.IWebException;

	public java.util.Set<com.liferay.iweb.model.Community> getRelatedCommunitiesByAppliedSemantics(
		long communityId) throws com.liferay.iweb.IWebException;

	public void updateCommunityWithSemantics(long communityId,
		java.util.Set<String> semanticsSet)
		throws com.liferay.iweb.IWebException;

	public void updateCommunityWithSemantics(
		java.util.Map<Long, java.util.Set<String>> communitySemanticsMap)
		throws com.liferay.iweb.IWebException;
}