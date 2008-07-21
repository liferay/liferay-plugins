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
 * <a href="CommunityLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class CommunityLocalServiceUtil {
	public static com.liferay.iweb.model.Community addCommunity(
		com.liferay.iweb.model.Community community)
		throws com.liferay.portal.SystemException {
		CommunityLocalService communityLocalService = CommunityLocalServiceFactory.getService();

		return communityLocalService.addCommunity(community);
	}

	public static void deleteCommunity(long cid)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		CommunityLocalService communityLocalService = CommunityLocalServiceFactory.getService();

		communityLocalService.deleteCommunity(cid);
	}

	public static void deleteCommunity(
		com.liferay.iweb.model.Community community)
		throws com.liferay.portal.SystemException {
		CommunityLocalService communityLocalService = CommunityLocalServiceFactory.getService();

		communityLocalService.deleteCommunity(community);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		CommunityLocalService communityLocalService = CommunityLocalServiceFactory.getService();

		return communityLocalService.dynamicQuery(dynamicQuery);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		CommunityLocalService communityLocalService = CommunityLocalServiceFactory.getService();

		return communityLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	public static com.liferay.iweb.model.Community getCommunity(long cid)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		CommunityLocalService communityLocalService = CommunityLocalServiceFactory.getService();

		return communityLocalService.getCommunity(cid);
	}

	public static com.liferay.iweb.model.Community updateCommunity(
		com.liferay.iweb.model.Community community)
		throws com.liferay.portal.SystemException {
		CommunityLocalService communityLocalService = CommunityLocalServiceFactory.getService();

		return communityLocalService.updateCommunity(community);
	}

	public static java.util.Set<com.liferay.iweb.model.Community> getAllCommunities()
		throws com.liferay.iweb.IWebException {
		CommunityLocalService communityLocalService = CommunityLocalServiceFactory.getService();

		return communityLocalService.getAllCommunities();
	}

	public static java.util.Map getAppliedSemantics(long communityId)
		throws com.liferay.iweb.IWebException {
		CommunityLocalService communityLocalService = CommunityLocalServiceFactory.getService();

		return communityLocalService.getAppliedSemantics(communityId);
	}

	public static java.util.Map<Long, java.util.Set<com.liferay.iweb.model.Semantics>> getAppliedSemantics(
		java.util.Set<Long> communityIds) throws com.liferay.iweb.IWebException {
		CommunityLocalService communityLocalService = CommunityLocalServiceFactory.getService();

		return communityLocalService.getAppliedSemantics(communityIds);
	}

	public static java.util.Set<com.liferay.iweb.model.Community> getRelatedCommunitiesByAppliedSemantics(
		long communityId) throws com.liferay.iweb.IWebException {
		CommunityLocalService communityLocalService = CommunityLocalServiceFactory.getService();

		return communityLocalService.getRelatedCommunitiesByAppliedSemantics(communityId);
	}

	public static void updateCommunityWithSemantics(long communityId,
		java.util.Set<String> semanticsSet)
		throws com.liferay.iweb.IWebException {
		CommunityLocalService communityLocalService = CommunityLocalServiceFactory.getService();

		communityLocalService.updateCommunityWithSemantics(communityId,
			semanticsSet);
	}

	public static void updateCommunityWithSemantics(
		java.util.Map<Long, java.util.Set<String>> communitySemanticsMap)
		throws com.liferay.iweb.IWebException {
		CommunityLocalService communityLocalService = CommunityLocalServiceFactory.getService();

		communityLocalService.updateCommunityWithSemantics(communitySemanticsMap);
	}
}