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
 * <a href="PostLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class PostLocalServiceUtil {
	public static com.liferay.iweb.model.Post addPost(
		com.liferay.iweb.model.Post post)
		throws com.liferay.portal.SystemException {
		PostLocalService postLocalService = PostLocalServiceFactory.getService();

		return postLocalService.addPost(post);
	}

	public static void deletePost(long uid)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		PostLocalService postLocalService = PostLocalServiceFactory.getService();

		postLocalService.deletePost(uid);
	}

	public static void deletePost(com.liferay.iweb.model.Post post)
		throws com.liferay.portal.SystemException {
		PostLocalService postLocalService = PostLocalServiceFactory.getService();

		postLocalService.deletePost(post);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		PostLocalService postLocalService = PostLocalServiceFactory.getService();

		return postLocalService.dynamicQuery(dynamicQuery);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		PostLocalService postLocalService = PostLocalServiceFactory.getService();

		return postLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	public static com.liferay.iweb.model.Post getPost(long uid)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		PostLocalService postLocalService = PostLocalServiceFactory.getService();

		return postLocalService.getPost(uid);
	}

	public static com.liferay.iweb.model.Post updatePost(
		com.liferay.iweb.model.Post post)
		throws com.liferay.portal.SystemException {
		PostLocalService postLocalService = PostLocalServiceFactory.getService();

		return postLocalService.updatePost(post);
	}

	public static java.util.Map<Long, java.util.Set<com.liferay.iweb.model.SemanticsElement>> getAppliedSemantics(
		java.util.Map<Long, String> postId_Type)
		throws com.liferay.iweb.IWebException {
		PostLocalService postLocalService = PostLocalServiceFactory.getService();

		return postLocalService.getAppliedSemantics(postId_Type);
	}

	public static java.util.Set<com.liferay.iweb.model.SemanticsElement> getAppliedSemanticsElements(
		long postId, java.lang.String postType)
		throws com.liferay.iweb.IWebException {
		PostLocalService postLocalService = PostLocalServiceFactory.getService();

		return postLocalService.getAppliedSemanticsElements(postId, postType);
	}

	public static java.util.Set<com.liferay.iweb.model.Post> getRelatedPostsByAppliedSemanticsElements(
		java.lang.Long postId, java.lang.String postType,
		java.util.Set<String> requiredTypes, java.util.Set<Long> communityIds,
		boolean reason) throws com.liferay.iweb.IWebException {
		PostLocalService postLocalService = PostLocalServiceFactory.getService();

		return postLocalService.getRelatedPostsByAppliedSemanticsElements(postId,
			postType, requiredTypes, communityIds, reason);
	}

	public static void updatePostsWithSemanticsElements(long postId,
		java.lang.String type, java.util.Set<String> semanticsElementSet)
		throws com.liferay.iweb.IWebException {
		PostLocalService postLocalService = PostLocalServiceFactory.getService();

		postLocalService.updatePostsWithSemanticsElements(postId, type,
			semanticsElementSet);
	}
}