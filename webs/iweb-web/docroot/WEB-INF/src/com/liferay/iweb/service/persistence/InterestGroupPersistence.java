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
 * <a href="InterestGroupPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface InterestGroupPersistence {
	public com.liferay.iweb.model.InterestGroup create(long cid);

	public com.liferay.iweb.model.InterestGroup remove(long cid)
		throws com.liferay.iweb.NoSuchInterestGroupException,
			com.liferay.portal.SystemException;

	public com.liferay.iweb.model.InterestGroup remove(
		com.liferay.iweb.model.InterestGroup interestGroup)
		throws com.liferay.portal.SystemException;

	public com.liferay.iweb.model.InterestGroup update(
		com.liferay.iweb.model.InterestGroup interestGroup)
		throws com.liferay.portal.SystemException;

	public com.liferay.iweb.model.InterestGroup update(
		com.liferay.iweb.model.InterestGroup interestGroup, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.iweb.model.InterestGroup updateImpl(
		com.liferay.iweb.model.InterestGroup interestGroup, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.iweb.model.InterestGroup findByPrimaryKey(long cid)
		throws com.liferay.iweb.NoSuchInterestGroupException,
			com.liferay.portal.SystemException;

	public com.liferay.iweb.model.InterestGroup fetchByPrimaryKey(long cid)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.iweb.model.InterestGroup> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.iweb.model.InterestGroup> findAll(
		int start, int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.iweb.model.InterestGroup> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeAll() throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.iweb.model.SemanticsFile> getSemanticsFiles(
		long pk) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.iweb.model.SemanticsFile> getSemanticsFiles(
		long pk, int start, int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.iweb.model.SemanticsFile> getSemanticsFiles(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public int getSemanticsFilesSize(long pk)
		throws com.liferay.portal.SystemException;

	public boolean containsSemanticsFile(long pk,
		java.lang.String semanticsFilePK)
		throws com.liferay.portal.SystemException;

	public boolean containsSemanticsFiles(long pk)
		throws com.liferay.portal.SystemException;

	public void addSemanticsFile(long pk, java.lang.String semanticsFilePK)
		throws com.liferay.portal.SystemException;

	public void addSemanticsFile(long pk,
		com.liferay.iweb.model.SemanticsFile semanticsFile)
		throws com.liferay.portal.SystemException;

	public void addSemanticsFiles(long pk, java.lang.String[] semanticsFilePKs)
		throws com.liferay.portal.SystemException;

	public void addSemanticsFiles(long pk,
		java.util.List<com.liferay.iweb.model.SemanticsFile> semanticsFiles)
		throws com.liferay.portal.SystemException;

	public void clearSemanticsFiles(long pk)
		throws com.liferay.portal.SystemException;

	public void removeSemanticsFile(long pk, java.lang.String semanticsFilePK)
		throws com.liferay.portal.SystemException;

	public void removeSemanticsFile(long pk,
		com.liferay.iweb.model.SemanticsFile semanticsFile)
		throws com.liferay.portal.SystemException;

	public void removeSemanticsFiles(long pk,
		java.lang.String[] semanticsFilePKs)
		throws com.liferay.portal.SystemException;

	public void removeSemanticsFiles(long pk,
		java.util.List<com.liferay.iweb.model.SemanticsFile> semanticsFiles)
		throws com.liferay.portal.SystemException;

	public void setSemanticsFiles(long pk, java.lang.String[] semanticsFilePKs)
		throws com.liferay.portal.SystemException;

	public void setSemanticsFiles(long pk,
		java.util.List<com.liferay.iweb.model.SemanticsFile> semanticsFiles)
		throws com.liferay.portal.SystemException;

	public void registerListener(
		com.liferay.portal.model.ModelListener listener);

	public void unregisterListener(
		com.liferay.portal.model.ModelListener listener);
}