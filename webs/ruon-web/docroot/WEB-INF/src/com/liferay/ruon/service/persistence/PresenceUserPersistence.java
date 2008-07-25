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

package com.liferay.ruon.service.persistence;

/**
 * <a href="PresenceUserPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface PresenceUserPersistence {
	public com.liferay.ruon.model.PresenceUser create(long presenceUserId);

	public com.liferay.ruon.model.PresenceUser remove(long presenceUserId)
		throws com.liferay.portal.SystemException,
			com.liferay.ruon.NoSuchPresenceUserException;

	public com.liferay.ruon.model.PresenceUser remove(
		com.liferay.ruon.model.PresenceUser presenceUser)
		throws com.liferay.portal.SystemException;

	public com.liferay.ruon.model.PresenceUser update(
		com.liferay.ruon.model.PresenceUser presenceUser)
		throws com.liferay.portal.SystemException;

	public com.liferay.ruon.model.PresenceUser update(
		com.liferay.ruon.model.PresenceUser presenceUser, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.ruon.model.PresenceUser updateImpl(
		com.liferay.ruon.model.PresenceUser presenceUser, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.ruon.model.PresenceUser findByPrimaryKey(
		long presenceUserId)
		throws com.liferay.portal.SystemException,
			com.liferay.ruon.NoSuchPresenceUserException;

	public com.liferay.ruon.model.PresenceUser fetchByPrimaryKey(
		long presenceUserId) throws com.liferay.portal.SystemException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.ruon.model.PresenceUser> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.ruon.model.PresenceUser> findAll(
		int start, int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.ruon.model.PresenceUser> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeAll() throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;

	public void registerListener(
		com.liferay.portal.model.ModelListener listener);

	public void unregisterListener(
		com.liferay.portal.model.ModelListener listener);
}