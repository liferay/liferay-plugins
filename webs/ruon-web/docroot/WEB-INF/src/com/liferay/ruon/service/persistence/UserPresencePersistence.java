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

 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://www.sun.com/cddl/cddl.html and
 * legal/CDDLv1.0.txt. See the License for the specific language governing
 * permission and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at legal/CDDLv1.0.txt.
 *
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Copyright 2008 Sun Microsystems Inc. All rights reserved.
 **/

package com.liferay.ruon.service.persistence;

/**
 * <a href="UserPresencePersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface UserPresencePersistence {
	public com.liferay.ruon.model.UserPresence create(long presenceUserId);

	public com.liferay.ruon.model.UserPresence remove(long presenceUserId)
		throws com.liferay.portal.SystemException,
			com.liferay.ruon.NoSuchUserPresenceException;

	public com.liferay.ruon.model.UserPresence remove(
		com.liferay.ruon.model.UserPresence userPresence)
		throws com.liferay.portal.SystemException;

	public com.liferay.ruon.model.UserPresence update(
		com.liferay.ruon.model.UserPresence userPresence)
		throws com.liferay.portal.SystemException;

	public com.liferay.ruon.model.UserPresence update(
		com.liferay.ruon.model.UserPresence userPresence, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.ruon.model.UserPresence updateImpl(
		com.liferay.ruon.model.UserPresence userPresence, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.ruon.model.UserPresence findByPrimaryKey(
		long presenceUserId)
		throws com.liferay.portal.SystemException,
			com.liferay.ruon.NoSuchUserPresenceException;

	public com.liferay.ruon.model.UserPresence fetchByPrimaryKey(
		long presenceUserId) throws com.liferay.portal.SystemException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.ruon.model.UserPresence> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.ruon.model.UserPresence> findAll(
		int start, int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.ruon.model.UserPresence> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeAll() throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;

	public void registerListener(
		com.liferay.portal.model.ModelListener listener);

	public void unregisterListener(
		com.liferay.portal.model.ModelListener listener);
}