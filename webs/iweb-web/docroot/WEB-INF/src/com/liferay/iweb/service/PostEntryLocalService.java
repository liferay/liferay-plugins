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
 * <a href="PostEntryLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface PostEntryLocalService {
	public com.liferay.iweb.model.PostEntry addPostEntry(
		com.liferay.iweb.model.PostEntry postEntry)
		throws com.liferay.portal.SystemException;

	public void deletePostEntry(long uid)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public void deletePostEntry(com.liferay.iweb.model.PostEntry postEntry)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	public com.liferay.iweb.model.PostEntry getPostEntry(long uid)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public com.liferay.iweb.model.PostEntry updatePostEntry(
		com.liferay.iweb.model.PostEntry postEntry)
		throws com.liferay.portal.SystemException;

	public java.util.Map<Long, java.util.Set<com.liferay.iweb.model.SemanticsElement>> getAppliedSemantics(
		java.util.Map<Long, String> postEntryId_Type)
		throws com.liferay.iweb.IWebException;

	public java.util.Set<com.liferay.iweb.model.SemanticsElement> getAppliedSemanticsElements(
		long postEntryId, java.lang.String postEntryType)
		throws com.liferay.iweb.IWebException;

	public java.util.Set<com.liferay.iweb.model.PostEntry> getRelatedPostEntriesByAppliedSemanticsElements(
		java.lang.Long postEntryId, java.lang.String postEntryType,
		java.util.Set<String> requiredTypes,
		java.util.Set<Long> interestGroupIds, boolean reason)
		throws com.liferay.iweb.IWebException;

	public void updatePostEntriesWithSemanticsElements(long postEntryId,
		java.lang.String type, java.util.Set<String> semanticsElementSet)
		throws com.liferay.iweb.IWebException;
}