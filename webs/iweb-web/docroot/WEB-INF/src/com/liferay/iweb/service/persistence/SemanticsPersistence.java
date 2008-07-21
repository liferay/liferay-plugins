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

package com.liferay.iweb.service.persistence;

/**
 * <a href="SemanticsPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface SemanticsPersistence {
	public com.liferay.iweb.model.Semantics create(
		java.lang.String semanticsURI);

	public com.liferay.iweb.model.Semantics remove(
		java.lang.String semanticsURI)
		throws com.liferay.iweb.NoSuchSemanticsException,
			com.liferay.portal.SystemException;

	public com.liferay.iweb.model.Semantics remove(
		com.liferay.iweb.model.Semantics semantics)
		throws com.liferay.portal.SystemException;

	public com.liferay.iweb.model.Semantics update(
		com.liferay.iweb.model.Semantics semantics)
		throws com.liferay.portal.SystemException;

	public com.liferay.iweb.model.Semantics update(
		com.liferay.iweb.model.Semantics semantics, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.iweb.model.Semantics updateImpl(
		com.liferay.iweb.model.Semantics semantics, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.iweb.model.Semantics findByPrimaryKey(
		java.lang.String semanticsURI)
		throws com.liferay.iweb.NoSuchSemanticsException,
			com.liferay.portal.SystemException;

	public com.liferay.iweb.model.Semantics fetchByPrimaryKey(
		java.lang.String semanticsURI)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.iweb.model.Semantics> findByCreatedInCommunity(
		long createdInCommunity) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.iweb.model.Semantics> findByCreatedInCommunity(
		long createdInCommunity, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.iweb.model.Semantics> findByCreatedInCommunity(
		long createdInCommunity, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.iweb.model.Semantics findByCreatedInCommunity_First(
		long createdInCommunity,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.iweb.NoSuchSemanticsException,
			com.liferay.portal.SystemException;

	public com.liferay.iweb.model.Semantics findByCreatedInCommunity_Last(
		long createdInCommunity,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.iweb.NoSuchSemanticsException,
			com.liferay.portal.SystemException;

	public com.liferay.iweb.model.Semantics[] findByCreatedInCommunity_PrevAndNext(
		java.lang.String semanticsURI, long createdInCommunity,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.iweb.NoSuchSemanticsException,
			com.liferay.portal.SystemException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.iweb.model.Semantics> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.iweb.model.Semantics> findAll(int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.iweb.model.Semantics> findAll(int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeByCreatedInCommunity(long createdInCommunity)
		throws com.liferay.portal.SystemException;

	public void removeAll() throws com.liferay.portal.SystemException;

	public int countByCreatedInCommunity(long createdInCommunity)
		throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.iweb.model.Community> getCommunities(
		java.lang.String pk) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.iweb.model.Community> getCommunities(
		java.lang.String pk, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.iweb.model.Community> getCommunities(
		java.lang.String pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public int getCommunitiesSize(java.lang.String pk)
		throws com.liferay.portal.SystemException;

	public boolean containsCommunity(java.lang.String pk, long communityPK)
		throws com.liferay.portal.SystemException;

	public boolean containsCommunities(java.lang.String pk)
		throws com.liferay.portal.SystemException;

	public void addCommunity(java.lang.String pk, long communityPK)
		throws com.liferay.portal.SystemException;

	public void addCommunity(java.lang.String pk,
		com.liferay.iweb.model.Community community)
		throws com.liferay.portal.SystemException;

	public void addCommunities(java.lang.String pk, long[] communityPKs)
		throws com.liferay.portal.SystemException;

	public void addCommunities(java.lang.String pk,
		java.util.List<com.liferay.iweb.model.Community> communities)
		throws com.liferay.portal.SystemException;

	public void clearCommunities(java.lang.String pk)
		throws com.liferay.portal.SystemException;

	public void removeCommunity(java.lang.String pk, long communityPK)
		throws com.liferay.portal.SystemException;

	public void removeCommunity(java.lang.String pk,
		com.liferay.iweb.model.Community community)
		throws com.liferay.portal.SystemException;

	public void removeCommunities(java.lang.String pk, long[] communityPKs)
		throws com.liferay.portal.SystemException;

	public void removeCommunities(java.lang.String pk,
		java.util.List<com.liferay.iweb.model.Community> communities)
		throws com.liferay.portal.SystemException;

	public void setCommunities(java.lang.String pk, long[] communityPKs)
		throws com.liferay.portal.SystemException;

	public void setCommunities(java.lang.String pk,
		java.util.List<com.liferay.iweb.model.Community> communities)
		throws com.liferay.portal.SystemException;

	public void registerListener(
		com.liferay.portal.model.ModelListener listener);

	public void unregisterListener(
		com.liferay.portal.model.ModelListener listener);
}