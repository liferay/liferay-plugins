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
 * <a href="PresencePersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface PresencePersistence {
	public com.liferay.ruon.model.Presence create(long presenceId);

	public com.liferay.ruon.model.Presence remove(long presenceId)
		throws com.liferay.portal.SystemException,
			com.liferay.ruon.NoSuchPresenceException;

	public com.liferay.ruon.model.Presence remove(
		com.liferay.ruon.model.Presence presence)
		throws com.liferay.portal.SystemException;

	public com.liferay.ruon.model.Presence update(
		com.liferay.ruon.model.Presence presence)
		throws com.liferay.portal.SystemException;

	public com.liferay.ruon.model.Presence update(
		com.liferay.ruon.model.Presence presence, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.ruon.model.Presence updateImpl(
		com.liferay.ruon.model.Presence presence, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.ruon.model.Presence findByPrimaryKey(long presenceId)
		throws com.liferay.portal.SystemException,
			com.liferay.ruon.NoSuchPresenceException;

	public com.liferay.ruon.model.Presence fetchByPrimaryKey(long presenceId)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.ruon.model.Presence> findByUserId(
		long userId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.ruon.model.Presence> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.ruon.model.Presence> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.ruon.model.Presence findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.ruon.NoSuchPresenceException;

	public com.liferay.ruon.model.Presence findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.ruon.NoSuchPresenceException;

	public com.liferay.ruon.model.Presence[] findByUserId_PrevAndNext(
		long presenceId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.ruon.NoSuchPresenceException;

	public com.liferay.ruon.model.Presence findByU_N(long userId, long networkId)
		throws com.liferay.portal.SystemException,
			com.liferay.ruon.NoSuchPresenceException;

	public com.liferay.ruon.model.Presence fetchByU_N(long userId,
		long networkId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.ruon.model.Presence> findByU_O(
		long userId, boolean online) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.ruon.model.Presence> findByU_O(
		long userId, boolean online, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.ruon.model.Presence> findByU_O(
		long userId, boolean online, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.ruon.model.Presence findByU_O_First(long userId,
		boolean online, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.ruon.NoSuchPresenceException;

	public com.liferay.ruon.model.Presence findByU_O_Last(long userId,
		boolean online, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.ruon.NoSuchPresenceException;

	public com.liferay.ruon.model.Presence[] findByU_O_PrevAndNext(
		long presenceId, long userId, boolean online,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.ruon.NoSuchPresenceException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.ruon.model.Presence> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.ruon.model.Presence> findAll(int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.ruon.model.Presence> findAll(int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeByUserId(long userId)
		throws com.liferay.portal.SystemException;

	public void removeByU_N(long userId, long networkId)
		throws com.liferay.portal.SystemException,
			com.liferay.ruon.NoSuchPresenceException;

	public void removeByU_O(long userId, boolean online)
		throws com.liferay.portal.SystemException;

	public void removeAll() throws com.liferay.portal.SystemException;

	public int countByUserId(long userId)
		throws com.liferay.portal.SystemException;

	public int countByU_N(long userId, long networkId)
		throws com.liferay.portal.SystemException;

	public int countByU_O(long userId, boolean online)
		throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;

	public void registerListener(
		com.liferay.portal.model.ModelListener listener);

	public void unregisterListener(
		com.liferay.portal.model.ModelListener listener);
}