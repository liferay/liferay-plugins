/**
 * Copyright (c) 2000-2007 Liferay, Inc. All rights reserved.
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

package com.sample.servicebuilder.service.persistence;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelListener;

import com.liferay.portlet.service.PropsUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="FooUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class FooUtil {
	public static com.sample.servicebuilder.model.Foo create(long fooId) {
		return getPersistence().create(fooId);
	}

	public static com.sample.servicebuilder.model.Foo remove(long fooId)
		throws com.liferay.portal.SystemException, 
			com.sample.servicebuilder.NoSuchFooException {
		ModelListener listener = _getListener();

		if (listener != null) {
			listener.onBeforeRemove(findByPrimaryKey(fooId));
		}

		com.sample.servicebuilder.model.Foo foo = getPersistence().remove(fooId);

		if (listener != null) {
			listener.onAfterRemove(foo);
		}

		return foo;
	}

	public static com.sample.servicebuilder.model.Foo remove(
		com.sample.servicebuilder.model.Foo foo)
		throws com.liferay.portal.SystemException {
		ModelListener listener = _getListener();

		if (listener != null) {
			listener.onBeforeRemove(foo);
		}

		foo = getPersistence().remove(foo);

		if (listener != null) {
			listener.onAfterRemove(foo);
		}

		return foo;
	}

	public static com.sample.servicebuilder.model.Foo update(
		com.sample.servicebuilder.model.Foo foo)
		throws com.liferay.portal.SystemException {
		ModelListener listener = _getListener();
		boolean isNew = foo.isNew();

		if (listener != null) {
			if (isNew) {
				listener.onBeforeCreate(foo);
			}
			else {
				listener.onBeforeUpdate(foo);
			}
		}

		foo = getPersistence().update(foo);

		if (listener != null) {
			if (isNew) {
				listener.onAfterCreate(foo);
			}
			else {
				listener.onAfterUpdate(foo);
			}
		}

		return foo;
	}

	public static com.sample.servicebuilder.model.Foo update(
		com.sample.servicebuilder.model.Foo foo, boolean saveOrUpdate)
		throws com.liferay.portal.SystemException {
		ModelListener listener = _getListener();
		boolean isNew = foo.isNew();

		if (listener != null) {
			if (isNew) {
				listener.onBeforeCreate(foo);
			}
			else {
				listener.onBeforeUpdate(foo);
			}
		}

		foo = getPersistence().update(foo, saveOrUpdate);

		if (listener != null) {
			if (isNew) {
				listener.onAfterCreate(foo);
			}
			else {
				listener.onAfterUpdate(foo);
			}
		}

		return foo;
	}

	public static com.sample.servicebuilder.model.Foo findByPrimaryKey(
		long fooId)
		throws com.liferay.portal.SystemException, 
			com.sample.servicebuilder.NoSuchFooException {
		return getPersistence().findByPrimaryKey(fooId);
	}

	public static com.sample.servicebuilder.model.Foo fetchByPrimaryKey(
		long fooId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(fooId);
	}

	public static java.util.List findByField2(boolean field2)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByField2(field2);
	}

	public static java.util.List findByField2(boolean field2, int begin, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByField2(field2, begin, end);
	}

	public static java.util.List findByField2(boolean field2, int begin,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByField2(field2, begin, end, obc);
	}

	public static com.sample.servicebuilder.model.Foo findByField2_First(
		boolean field2, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException, 
			com.sample.servicebuilder.NoSuchFooException {
		return getPersistence().findByField2_First(field2, obc);
	}

	public static com.sample.servicebuilder.model.Foo findByField2_Last(
		boolean field2, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException, 
			com.sample.servicebuilder.NoSuchFooException {
		return getPersistence().findByField2_Last(field2, obc);
	}

	public static com.sample.servicebuilder.model.Foo[] findByField2_PrevAndNext(
		long fooId, boolean field2,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException, 
			com.sample.servicebuilder.NoSuchFooException {
		return getPersistence().findByField2_PrevAndNext(fooId, field2, obc);
	}

	public static java.util.List findWithDynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer)
		throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(queryInitializer);
	}

	public static java.util.List findWithDynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer,
		int begin, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(queryInitializer, begin,
			end);
	}

	public static java.util.List findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List findAll(int begin, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(begin, end);
	}

	public static java.util.List findAll(int begin, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(begin, end, obc);
	}

	public static void removeByField2(boolean field2)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByField2(field2);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByField2(boolean field2)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByField2(field2);
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static FooPersistence getPersistence() {
		return _getUtil()._persistence;
	}

	public void setPersistence(FooPersistence persistence) {
		_persistence = persistence;
	}

	private static FooUtil _getUtil() {
		if (_util == null) {
			_util = (FooUtil)com.liferay.portlet.service.BeanLocatorUtil.locate(_UTIL);
		}

		return _util;
	}

	private static ModelListener _getListener() {
		if (Validator.isNotNull(_LISTENER)) {
			try {
				return (ModelListener)Class.forName(_LISTENER).newInstance();
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		return null;
	}

	private static final String _UTIL = FooUtil.class.getName();
	private static final String _LISTENER = GetterUtil.getString(PropsUtil.get(
				"value.object.listener.com.sample.servicebuilder.model.Foo"));
	private static Log _log = LogFactory.getLog(FooUtil.class);
	private static FooUtil _util;
	private FooPersistence _persistence;
}