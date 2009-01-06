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

package com.liferay.iweb.service.persistence;

/**
 * <a href="SemanticsFilePersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface SemanticsFilePersistence {
	public com.liferay.iweb.model.SemanticsFile create(
		java.lang.String semanticsURI);

	public com.liferay.iweb.model.SemanticsFile remove(
		java.lang.String semanticsURI)
		throws com.liferay.iweb.NoSuchSemanticsFileException,
			com.liferay.portal.SystemException;

	public com.liferay.iweb.model.SemanticsFile remove(
		com.liferay.iweb.model.SemanticsFile semanticsFile)
		throws com.liferay.portal.SystemException;

	public com.liferay.iweb.model.SemanticsFile update(
		com.liferay.iweb.model.SemanticsFile semanticsFile)
		throws com.liferay.portal.SystemException;

	public com.liferay.iweb.model.SemanticsFile update(
		com.liferay.iweb.model.SemanticsFile semanticsFile, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.iweb.model.SemanticsFile updateImpl(
		com.liferay.iweb.model.SemanticsFile semanticsFile, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.iweb.model.SemanticsFile findByPrimaryKey(
		java.lang.String semanticsURI)
		throws com.liferay.iweb.NoSuchSemanticsFileException,
			com.liferay.portal.SystemException;

	public com.liferay.iweb.model.SemanticsFile fetchByPrimaryKey(
		java.lang.String semanticsURI)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.iweb.model.SemanticsFile> findByCreatedInInterestGroup(
		long createdInInterestGroup) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.iweb.model.SemanticsFile> findByCreatedInInterestGroup(
		long createdInInterestGroup, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.iweb.model.SemanticsFile> findByCreatedInInterestGroup(
		long createdInInterestGroup, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.iweb.model.SemanticsFile findByCreatedInInterestGroup_First(
		long createdInInterestGroup,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.iweb.NoSuchSemanticsFileException,
			com.liferay.portal.SystemException;

	public com.liferay.iweb.model.SemanticsFile findByCreatedInInterestGroup_Last(
		long createdInInterestGroup,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.iweb.NoSuchSemanticsFileException,
			com.liferay.portal.SystemException;

	public com.liferay.iweb.model.SemanticsFile[] findByCreatedInInterestGroup_PrevAndNext(
		java.lang.String semanticsURI, long createdInInterestGroup,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.iweb.NoSuchSemanticsFileException,
			com.liferay.portal.SystemException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.iweb.model.SemanticsFile> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.iweb.model.SemanticsFile> findAll(
		int start, int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.iweb.model.SemanticsFile> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeByCreatedInInterestGroup(long createdInInterestGroup)
		throws com.liferay.portal.SystemException;

	public void removeAll() throws com.liferay.portal.SystemException;

	public int countByCreatedInInterestGroup(long createdInInterestGroup)
		throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.iweb.model.InterestGroup> getInterestGroups(
		java.lang.String pk) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.iweb.model.InterestGroup> getInterestGroups(
		java.lang.String pk, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.iweb.model.InterestGroup> getInterestGroups(
		java.lang.String pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public int getInterestGroupsSize(java.lang.String pk)
		throws com.liferay.portal.SystemException;

	public boolean containsInterestGroup(java.lang.String pk,
		long interestGroupPK) throws com.liferay.portal.SystemException;

	public boolean containsInterestGroups(java.lang.String pk)
		throws com.liferay.portal.SystemException;

	public void addInterestGroup(java.lang.String pk, long interestGroupPK)
		throws com.liferay.portal.SystemException;

	public void addInterestGroup(java.lang.String pk,
		com.liferay.iweb.model.InterestGroup interestGroup)
		throws com.liferay.portal.SystemException;

	public void addInterestGroups(java.lang.String pk, long[] interestGroupPKs)
		throws com.liferay.portal.SystemException;

	public void addInterestGroups(java.lang.String pk,
		java.util.List<com.liferay.iweb.model.InterestGroup> interestGroups)
		throws com.liferay.portal.SystemException;

	public void clearInterestGroups(java.lang.String pk)
		throws com.liferay.portal.SystemException;

	public void removeInterestGroup(java.lang.String pk, long interestGroupPK)
		throws com.liferay.portal.SystemException;

	public void removeInterestGroup(java.lang.String pk,
		com.liferay.iweb.model.InterestGroup interestGroup)
		throws com.liferay.portal.SystemException;

	public void removeInterestGroups(java.lang.String pk,
		long[] interestGroupPKs) throws com.liferay.portal.SystemException;

	public void removeInterestGroups(java.lang.String pk,
		java.util.List<com.liferay.iweb.model.InterestGroup> interestGroups)
		throws com.liferay.portal.SystemException;

	public void setInterestGroups(java.lang.String pk, long[] interestGroupPKs)
		throws com.liferay.portal.SystemException;

	public void setInterestGroups(java.lang.String pk,
		java.util.List<com.liferay.iweb.model.InterestGroup> interestGroups)
		throws com.liferay.portal.SystemException;

	public void registerListener(
		com.liferay.portal.model.ModelListener listener);

	public void unregisterListener(
		com.liferay.portal.model.ModelListener listener);
}